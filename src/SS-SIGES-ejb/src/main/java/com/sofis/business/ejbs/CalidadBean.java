package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.properties.LabelsEJB;
import com.sofis.business.validations.CalidadValidaciones;
import com.sofis.data.daos.CalidadDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.constantes.ValorCalidadCodigosConstantes;
import com.sofis.entities.data.Calidad;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.ValorCalidadCodigos;
import com.sofis.entities.enums.TipoCalidadEnum;
import com.sofis.entities.tipos.FiltroCalidadTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "CalidadBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class CalidadBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(CalidadBean.class.getName());
    
    @Inject
    private DatosUsuario du;
    @Inject
    private ConfiguracionBean configuracionBean;
    @Inject
    private ValorCalidadCodigosBean valorCalidadCodigosBean;
    @EJB
    private ProyectosBean proyectosBean;
    
    //private String usuario;
    //private String origen;
    
    @PostConstruct
    public void init(){
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }
    

    private Calidad guardar(Calidad cal, Integer orgPk, SsUsuario usuario) {
        if (cal != null) {
            validarDuplicado(cal, orgPk);
            CalidadValidaciones.validar(cal);

            CalidadDAO dao = new CalidadDAO(em);
            try {
                cal = dao.update(cal, this.du.getCodigoUsuario(),du.getOrigen());
                proyectosBean.actualizarFecha(cal.getCalProyFk().getProyPk(), usuario);
                proyectosBean.guardarIndicadoresSimple(cal.getCalProyFk().getProyPk(), true, true, orgPk, null, true);
                return cal;

            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_CALIDAD_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    public List<Calidad> guardarCal(List<Calidad> list, Integer orgPk, SsUsuario usu) {

        if (CollectionsUtils.isNotEmpty(list)) {
            for (Calidad cal : list) {
                cal = guardarCal(cal, orgPk, usu);
            }
        }
        return list;
    }

    public Calidad guardarCal(Calidad cal, Integer orgPk, SsUsuario usu) {
        if (cal.getCalTcaFk() != null) {
            cal.setCalTipo(TipoCalidadEnum.GENERAL.ordinal());
        } else if (cal.getCalEntFk() != null) {
            cal.setCalTipo(TipoCalidadEnum.ENTREGABLE.ordinal());
        } else if (cal.getCalProdFk() != null) {
            cal.setCalTipo(TipoCalidadEnum.PRODUCTO.ordinal());
        }

        if (cal.getCalVcaFk() == null) {
            ValorCalidadCodigos vca = valorCalidadCodigosBean.obtenerPorCodigo(ValorCalidadCodigosConstantes.PENDIENTE_CARGAR);
            if ((cal.getCalTipo() == TipoCalidadEnum.ENTREGABLE.ordinal()
                    && DatesUtils.esMayor(cal.getCalEntFk().getEntInicioDate(), new Date()))
                    || (cal.getCalTipo() == TipoCalidadEnum.PRODUCTO.ordinal()
                    && DatesUtils.esMayor(cal.getCalProdFk().getProdEntregableFk().getEntInicioDate(), new Date()))) {
                vca = valorCalidadCodigosBean.obtenerPorCodigo(ValorCalidadCodigosConstantes.NO_CORRESPONDE);
            }
            cal.setCalVcaFk(vca);
        }

        cal.setCalPeso(cal.getCalPeso() == 0 ? 1 : cal.getCalPeso());

        if (tieneCambios(cal)) {
            cal.setCalActualizacion(new Date());
        }

        return guardar(cal, orgPk, usu);
    }

    public Calidad obtenerPorId(Integer calId) {
        if (calId != null) {
            CalidadDAO dao = new CalidadDAO(em);
            try {
                Calidad cal = dao.findById(Calidad.class, calId);
                return cal;
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_CALIDAD_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public List<Calidad> obtenerPorProyId(Integer proyId) {
        if (proyId != null) {
            CalidadDAO dao = new CalidadDAO(em);
            try {
                List<Calidad> list = dao.findByOneProperty(Calidad.class, "calProyFk.proyPk", proyId);
                return list;
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_CALIDAD_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public List<Calidad> obtenerPendientesCalidad(Integer proyPk, int size) {
        if (proyPk != null) {
            CalidadDAO dao = new CalidadDAO(em);
            return dao.obtenerPendientesCalidad(proyPk, size);
        }
        return null;
    }
    
    public Integer obtenerPendCalidadPrograma(Integer progPk) {
        if (progPk != null) {
            CalidadDAO dao = new CalidadDAO(em);
            Long l =  dao.obtenerPendientesCalidadPrograma(progPk);
            if (l == null){
                return null;
            }else{
                return l.intValue();
            }
        }
        return null;
    }

    public List<Calidad> buscarPorFiltro(FiltroCalidadTO filtro, Integer orgPk) {
        if (filtro != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            if (filtro.getProyPk() != null) {
                MatchCriteriaTO criterioProy = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "calProyFk.proyPk", filtro.getProyPk());
                criterios.add(criterioProy);
            }

            if (filtro.getTipo() != null) {
                MatchCriteriaTO criterioTipo = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "calTipo", filtro.getTipo());
                criterios.add(criterioTipo);
            }

            if (filtro.getValor() != null) {
                MatchCriteriaTO criterioValor = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "calVcaFk.vcaPk", filtro.getValor());
                criterios.add(criterioValor);
            }

            if (filtro.getTemaCalidadPk() != null) {
                MatchCriteriaTO criterioTipo = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "calTcaFk.tcaPk", filtro.getTemaCalidadPk());
                criterios.add(criterioTipo);

            } else if (filtro.getEntPk() != null) {
                MatchCriteriaTO criterioTipo = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "calEntFk.entPk", filtro.getEntPk());
                criterios.add(criterioTipo);

            } else if (filtro.getProdPk() != null) {
                MatchCriteriaTO criterioTipo = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "calProdFk.prodPk", filtro.getProdPk());
                criterios.add(criterioTipo);
            }

            CriteriaTO criteria = null;
            if (criterios.size() == 1) {
                criteria = criterios.get(0);
            } else if (criterios.size() > 1) {
                criteria = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));
            }

            CalidadDAO dao = new CalidadDAO(em);
            try {
                List<Calidad> list = dao.findEntityByCriteria(Calidad.class, criteria, filtro.getOrderBy(), filtro.getAsc(), null, null);
                list = controlarNoCorresponde(list, orgPk);

                return list;
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_CALIDAD_OBTENER);
                throw be;
            }
        }
        return null;
    }

    /**
     * Calcula el indice de calidad según el proyecto aportado.
     *
     * @param proyId
     * @param orgPk
     * @return Double
     */
    public Double calcularIndiceCalidad(Integer proyId, Integer orgPk) {
        if (proyId != null) {
            FiltroCalidadTO filtro = new FiltroCalidadTO();
            filtro.setProyPk(proyId);

            List<Calidad> listCal = buscarPorFiltro(filtro, orgPk);
            if (CollectionsUtils.isNotEmpty(listCal)) {
                Double promedio = 0d;
                Double totalPeso = 0d;
                for (Calidad cal : listCal) {
                    if (!cal.getCalVcaFk().getVcaCodigo().equals(ValorCalidadCodigosConstantes.NO_CORRESPONDE)) {
                        totalPeso += cal.getCalPeso();

                        Double valor = obtenerValorCalidad(cal);
                        if (valor != null) {
                            promedio += cal.getCalPeso() * valor;
                        }
                    }
                }
                return promedio > 0 ? promedio * 100 / totalPeso : null;
            }
        }
        return null;
    }

    /**
     * Retorna el color para el indice de calidad aportado.
     *
     * @param ind
     * @param orgPk
     * @return String
     */
    public String calcularIndiceCalidadColor(Double ind, Integer orgPk) {
        if (ind != null) {
            Configuracion confAmarillo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.CALIDAD_LIMITE_AMARILLO, orgPk);
            Configuracion confRojo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.CALIDAD_LIMITE_ROJO, orgPk);

            Double amarillo = null;
            Double rojo = null;
            try {
                amarillo = confAmarillo != null ? Double.valueOf(confAmarillo.getCnfValor()) : null;
                rojo = confRojo != null ? Double.valueOf(confRojo.getCnfValor()) : null;
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "La configuracion del semaforo de Calidad falta o es erroneo el valor.");
            }

            if (confAmarillo != null && confRojo != null) {
                if (ind <= rojo) {
                    return ConstantesEstandares.SEMAFORO_ROJO;
                }
                if (ind > rojo && ind <= amarillo) {
                    return ConstantesEstandares.SEMAFORO_AMARILLO;
                }
                if (ind > amarillo) {
                    return ConstantesEstandares.SEMAFORO_VERDE;
                }
            } else {
                return ConstantesEstandares.COLOR_TRANSPARENT;
            }
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public Double obtenerValorCalidad(Calidad cal) {
        if (cal != null) {
            if (cal.getCalVcaFk().getVcaCodigo().equals(ValorCalidadCodigosConstantes.ALTA)) {
                return 1D;
            } else if (cal.getCalVcaFk().getVcaCodigo().equals(ValorCalidadCodigosConstantes.MEDIA)) {
                return 0.5;
            } else if (cal.getCalVcaFk().getVcaCodigo().equals(ValorCalidadCodigosConstantes.BAJA)) {
                return 0D;
            }
        }
        return null;
    }

    private void validarDuplicado(Calidad cal, Integer orgPk) {
        if (cal != null && cal.getCalPk() == null) {

            FiltroCalidadTO filtro = new FiltroCalidadTO();
            filtro.setProyPk(cal.getCalProyFk().getProyPk());

            if (cal.getCalTcaFk() != null) {
                filtro.setTemaCalidadPk(cal.getCalTcaFk().getTcaPk());

            } else if (cal.getCalEntFk() != null) {
                filtro.setEntPk(cal.getCalEntFk().getEntPk());

            } else if (cal.getCalProdFk() != null) {
                filtro.setProdPk(cal.getCalProdFk().getProdPk());

            } else {
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_CALIDAD_SIN_ITEM);
                throw be;
            }

            List<Calidad> list = buscarPorFiltro(filtro, orgPk);
            if (CollectionsUtils.isNotEmpty(list)) {
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.WARN_CALIDAD_DUPLICADO);
                throw be;
            }
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void eliminarNewTrans(Integer calPk) {
        eliminar(calPk);
    }
    
    public void eliminar(Integer calPk) {
        if (calPk != null) {
            CalidadDAO dao = new CalidadDAO(em);
            try {
                Calidad cal = obtenerPorId(calPk);
                dao.delete(cal);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_CALIDAD_ELIMINAR);
                throw be;
            }
        }
    }

    /**
     * Retorna true si la Calidad aportada difiere de la ya persistida.
     *
     * @param cal
     * @return boolean
     */
    private boolean tieneCambios(Calidad cal) {
        if (cal != null) {
            Calidad calOld = obtenerPorId(cal.getCalPk());
            if (calOld == null) {
                return true;
            }
            if (calOld.getCalPeso() != cal.getCalPeso()) {
                return true;
            }
            if (!calOld.getCalVcaFk().equals(cal.getCalVcaFk())) {
                return true;
            }
        }
        return false;
    }
    
   
    public Double obtenerIndiceCalidadPrograma(Set<Proyectos> setProyectos, Integer orgPk) {
        if (setProyectos != null) {

            Double promedio = 0d;
            Double totalPeso = 0d;
            for (Proyectos proy : setProyectos) {
                if (proy.isActivo()) {
                    FiltroCalidadTO filtro = new FiltroCalidadTO();
                    filtro.setProyPk(proy.getProyPk());
                    List<Calidad> listCal = buscarPorFiltro(filtro, orgPk);

                    for (Calidad cal : listCal) {
                        if (!cal.getCalVcaFk().getVcaCodigo().equals(ValorCalidadCodigosConstantes.NO_CORRESPONDE)) {
                            totalPeso += cal.getCalPeso();
                            Double valor = obtenerValorCalidad(cal);
                            if (valor != null) {
                                promedio += cal.getCalPeso() * valor;
                            }
                        }
                    }
                }
            }
            return promedio > 0 ? promedio * 100 / totalPeso : null;
        }
        return null;
    }
  
    public Integer obtenerPendCalidadPrograma(Set<Proyectos> setProyectos) {
        if (setProyectos != null) {

            Integer cantPend = null;
            for (Proyectos proy : setProyectos) {
                if (proy.isActivo()) {
                    List<Calidad> listPend = obtenerPendientesCalidad(proy.getProyPk(), 0);

                    if (CollectionsUtils.isNotEmpty(listPend)) {
                        if (cantPend == null) {
                            cantPend = 0;
                        }
                        cantPend += listPend.size();
                    }
                }
            }
            return cantPend;
        }
        return null;
    }

    public Integer obtenerPendCalidad(Integer proyPk) {
        if (proyPk != null) {
            Integer cantPend = null;
            List<Calidad> listPend = obtenerPendientesCalidad(proyPk, 0);

            if (CollectionsUtils.isNotEmpty(listPend)) {
                cantPend = listPend.size();
            }
            return cantPend;
        }
        return null;
    }

    /**
     * Controla la lista y los entregables o productos que hayan iniciado se
     * pasa el valor de "No Corresponde" a "Pendiente de carga".
     *
     * @param list
     * @param orgPk
     * @return List
     */
    public List<Calidad> controlarNoCorresponde(List<Calidad> list, Integer orgPk) {
        if (CollectionsUtils.isNotEmpty(list)) {
            Date d = new Date();
            ValorCalidadCodigos vca = valorCalidadCodigosBean.obtenerPorCodigo(ValorCalidadCodigosConstantes.PENDIENTE_CARGAR);
            for (Calidad cal : list) {
                boolean isNoCorresponde = cal.getCalVcaFk().getVcaCodigo().equals(ValorCalidadCodigosConstantes.NO_CORRESPONDE);

                if (isNoCorresponde
                        && ((cal.getCalEntFk() != null && cal.getCalEntFk().getEntInicio() <= d.getTime())
                        || (cal.getCalProdFk() != null && cal.getCalProdFk().getProdEntregableFk().getEntInicio() <= d.getTime())
                        || (cal.getCalTcaFk() != null))) {
                    cal.setCalVcaFk(vca);
                    
//                    cal = guardar(cal, orgPk);
                    CalidadDAO dao = new CalidadDAO(em);
                    try {
                        cal = dao.update(cal);
                    } catch (DAOGeneralException ex) {
                        logger.log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return list;
    }

    public String tipoCalidadStr(Integer tipo, Integer org) {
        if (tipo != null) {
            if (tipo == 0) {
                return LabelsEJB.getValue("tca_general", org);
            } else if (tipo == 1) {
                return LabelsEJB.getValue("tca_entregable", org);
            } else if (tipo == 2) {
                return LabelsEJB.getValue("tca_producto", org);
            }
        }
        return "";
    }

    public String valorColorTabla(String cod) {
        if (!StringsUtils.isEmpty(cod)) {
            switch (cod) {
                case ValorCalidadCodigosConstantes.ALTA:
                    return ConstantesEstandares.SEMAFORO_VERDE;
                case ValorCalidadCodigosConstantes.MEDIA:
                    return ConstantesEstandares.SEMAFORO_AMARILLO;
                case ValorCalidadCodigosConstantes.BAJA:
                    return ConstantesEstandares.SEMAFORO_ROJO;
            }
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }
}
