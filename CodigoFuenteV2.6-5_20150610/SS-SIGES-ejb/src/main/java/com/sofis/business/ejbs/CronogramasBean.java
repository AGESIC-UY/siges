package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.properties.LabelsEJB;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.validations.CronogramaValidaciones;
import com.sofis.data.daos.CronogramasDAO;
import com.sofis.data.daos.EntregablesDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.ProyectosConCronograma;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
@Stateless(name = "CronogramasBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class CronogramasBean {

    @Inject
    private ProgramasBean programasBean;
    @Inject
    private ProyectosBean proyectosBean;
    @Inject
    private ProductosBean productosBean;
    @Inject
    private EntregablesBean entregablesBean;
    @Inject
    private DocumentosBean documentosBean;
    @Inject
    private ParticipantesBean participantesBean;
    @Inject
    private RegistrosHorasBean registrosHorasBean;
    @Inject
    private DatosUsuario du;
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public Cronogramas guardar(Cronogramas cro) {
        logger.fine("CronogramasBean.guardar");
        CronogramaValidaciones.validar(cro);
        CronogramasDAO cdao = new CronogramasDAO(em);

        try {
            cro = cdao.guardarCronograma(cro, du.getCodigoUsuario(), du.getOrigen());
            productosBean.actualizarProdPorEnt(cro.getEntregablesSet());

        } catch (BusinessException | TechnicalException be) {
            logger.log(Level.SEVERE, null, be);
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CRO_GUARDAR);
            throw be;
        }

        return cro;
    }

    public Object guardarCronograma(Cronogramas cro, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) {
        logger.fine("CronogramasBean.guardarCronograma");
        try {
            eliminarEntregablesBorrados(cro);

            cro = guardar(cro);

            if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
                Programas prog = programasBean.obtenerProgPorId(fichaFk);
                prog.setProgCroFk(cro);
                prog = programasBean.guardarPrograma(prog, usuario, orgPk);
                return prog;

            } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
                Proyectos proy = proyectosBean.obtenerProyPorId(fichaFk);
                proy.setProyCroFk(cro);
                proy = proyectosBean.guardarProyecto(proy, usuario, orgPk);
                return proy;
            }
        } catch (GeneralException ex) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CRO_GUARDAR);
            be.addErrores(ex.getErrores());
            throw be;
        }

        return null;
    }

    public int[] calcularAvanceCronoFinalizadoProg(Set<Proyectos> proyectos) {
        if (proyectos != null) {
            int[] calculo;
            int[] result = {0, 0, 0};
            int azul = 0;
            int rojo = 0;
            int cont = 0;

            for (Proyectos proy : proyectos) {
                if (proy.getProyCroFk() != null && proy.isActivo()) {
                    Integer peso = proy.getProyPeso();
                    calculo = calcularAvanceCronoFinalizado(proy.getProyCroFk().getEntregablesSet());
                    if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
                        azul += calculo[0] * peso;
                        rojo += calculo[2] * peso;
                        cont += peso;
                    }
                }
            }

            if (cont != 0) {
                double azulD = azul / cont;
                double rojoD = rojo / cont;
                result = cargarYAjustarResultado(azulD, rojoD, result);
            }

            return result;
        }
        return null;
    }

    public int[] calcularAvanceCronoFinalizadoProgConCronograma(Set<ProyectosConCronograma> proyectos) {
        if (proyectos != null) {
            int[] calculo;
            int[] result = {0, 0, 0};
            int azul = 0;
            int rojo = 0;
            int cont = 0;

            for (ProyectosConCronograma proy : proyectos) {
                if (proy.getProyCroFk() != null && proy.getActivo()) {
                    Integer peso = proy.getProyPeso();
                    calculo = calcularAvanceCronoFinalizado(proy.getProyCroFk().getEntregablesSet());
                    if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
                        azul += calculo[0] * peso;
                        rojo += calculo[2] * peso;
                        cont += peso;
                    }
                }
            }

            if (cont != 0) {
                double azulD = azul / cont;
                double rojoD = rojo / cont;
                result = cargarYAjustarResultado(azulD, rojoD, result);
            }

            return result;
        }
        return null;
    }

    public Cronogramas obtenerCronogramaPorProy(Integer proyPk) {
        CronogramasDAO dao = new CronogramasDAO(em);
        Cronogramas cro = dao.obtenerCronogramaProProy(proyPk);
        return cro;
    }

    public Cronogramas obtenerCronograma(Integer croPk) {
        CronogramasDAO dao = new CronogramasDAO(em);

        try {
            Cronogramas cro = dao.findById(Cronogramas.class, croPk);
            return cro;
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CRO_GUARDAR);
            throw be;
        }
    }

    public int[] calcularAvanceCronoFinalizado(Cronogramas cro) {
        int[] result = {0, 0, 0};
        if (cro != null) {
            result = calcularAvanceCronoFinalizado(cro.getEntregablesSet());
        }
        return result;
    }

    public int[] calcularAvanceCronoParcial(Cronogramas cro) {
        int[] result = {0, 0, 0};
        if (cro != null) {
            result = calcularAvanceCronoParcial(cro.getEntregablesSet());
        }
        return result;
    }

    public int[] calcularAvanceCronoFinalizado(Set<Entregables> entregables) {
        int[] result = {0, 0, 0};
        double azul = 0;
        double rojo = 0;
        int esfuerzoTotal = 0;

        for (Entregables ent : entregables) {
            if (ent.getEntProgreso() >= 100) {
                azul += ent.getEntEsfuerzo();
            } else if ((ent.getEntFinLineaBase() != null
                    && ent.getEntFin() != null
                    && ent.getEntFinLineaBase() > 0
                    && ent.getEntFin() > ent.getEntFinLineaBase())
                    || DatesUtils.esMayor(new Date(), ent.getEntFinDate())) {
                rojo += ent.getEntEsfuerzo();
            }

            esfuerzoTotal += ent.getEntEsfuerzo();
        }

        if (esfuerzoTotal > 0) {
            double azulD = azul * 100 / esfuerzoTotal;
            double rojoD = rojo * 100 / esfuerzoTotal;
            result = cargarYAjustarResultado(azulD, rojoD, result);
        }

        return result;
    }

    public int[] calcularAvanceCronoParcialProg(Set<Proyectos> proyectos) {
        if (proyectos != null) {
            int[] calculo;
            int[] result = {0, 0, 0};
            int azul = 0;
            int rojo = 0;
            int cont = 0;

            for (Proyectos proy : proyectos) {
                if (proy.getProyCroFk() != null && proy.isActivo()) {
                    Integer peso = proy.getProyPeso();
                    calculo = calcularAvanceCronoParcial(proy.getProyCroFk().getEntregablesSet());
                    if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
                        azul += calculo[0] * peso;
                        rojo += calculo[2] * peso;
                        cont += peso;
                    }
                }
            }

            if (cont != 0) {
                double azulD = azul / cont;
                double rojoD = rojo / cont;
                result = cargarYAjustarResultado(azulD, rojoD, result);
            }

            return result;
        }
        return null;
    }

    public int[] calcularAvanceCronoParcialProgConCronograma(Set<ProyectosConCronograma> proyectos) {
        if (proyectos != null) {
            int[] calculo;
            int[] result = {0, 0, 0};
            int azul = 0;
            int rojo = 0;
            int cont = 0;

            for (ProyectosConCronograma proy : proyectos) {
                if (proy.getProyCroFk() != null && proy.getActivo()) {
                    Integer peso = proy.getProyPeso();
                    calculo = calcularAvanceCronoParcial(proy.getProyCroFk().getEntregablesSet());
                    if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
                        azul += calculo[0] * peso;
                        rojo += calculo[2] * peso;
                        cont += peso;
                    }
                }
            }

            if (cont != 0) {
                double azulD = azul / cont;
                double rojoD = rojo / cont;
                result = cargarYAjustarResultado(azulD, rojoD, result);
            }

            return result;
        }
        return null;
    }

    public int[] calcularAvanceCronoParcial(Set<Entregables> entregables) {
        int[] result = {0, 0, 0};
        double azul = 0;
        double rojo = 0;
        double esfuerzoTotal = 0;

        for (Entregables ent : entregables) {
            esfuerzoTotal += ent.getEntEsfuerzo();
        }

        if (esfuerzoTotal != 0) {
            for (Entregables ent : entregables) {
                double pesoAzul = ent.getEntProgreso().doubleValue() * ent.getEntEsfuerzo().doubleValue() / 100D;
                double porcentajeAzulDelTotal = pesoAzul * 100D / esfuerzoTotal;
                azul += porcentajeAzulDelTotal;
                if ((ent.getEntFinLineaBase() != null && ent.getEntFinLineaBase() > 0
                        && ent.getEntFin() > ent.getEntFinLineaBase())
                        || DatesUtils.esMayor(new Date(), ent.getEntFinDate())) {
                    rojo += (ent.getEntEsfuerzo().doubleValue() - pesoAzul) * 100 / esfuerzoTotal;
                }
            }

            double azulD = azul;
            double rojoD = rojo;
            result = cargarYAjustarResultado(azulD, rojoD, result);
        }

        return result;
    }

    public List<Entregables> obtenerResumenCronograma(Integer proyPk, int size) {
        if (proyPk != null) {
            EntregablesDAO eDao = new EntregablesDAO(em);
            List<Entregables> entregables = eDao.obtenerEntregablesPorProy(proyPk);
            List<Entregables> result = new ArrayList<>();
            for (Entregables ent : entregables) {
                if (ent.getEntEsfuerzo() > 0 && ent.getEntProgreso() < 100
                        && ((ent.getEntFinLineaBase() != null && ent.getEntFinLineaBase() > 0
                        && ent.getEntFin() > ent.getEntFinLineaBase())
                        || DatesUtils.esMayor(new Date(), ent.getEntFinDate()))) {
                    result.add(ent);
                }
            }
            return EntregablesUtils.primerosEntregables(EntregablesUtils.sortByEsfuerzo(result, true), size);
        }
        return null;
    }

    public Integer esfuerzoTotal(Cronogramas cro) {
        if (cro != null && CollectionsUtils.isNotEmpty(cro.getEntregablesSet())) {
            Integer result = 0;
            for (Entregables ent : cro.getEntregablesSet()) {
                if (ent.getEntEsfuerzo() != null) {
                    result += ent.getEntEsfuerzo();
                }
            }
            return result;
        }
        return null;
    }

    public String horasTotales(Cronogramas cro) {
        if (cro != null && CollectionsUtils.isNotEmpty(cro.getEntregablesSet())) {
            String result = "";
            for (Entregables ent : cro.getEntregablesSet()) {
                if (!StringsUtils.isEmpty(ent.getEntHorasEstimadas())) {
                    result = DatesUtils.sumarHora(result, ent.getEntHorasEstimadas());
                }
            }
            return result;
        }
        return null;
    }

    public Cronogramas copiarProyCronograma(Cronogramas proyCroFk, int desfasajeDias) {
        if (proyCroFk != null) {
            Cronogramas nvoCro = new Cronogramas();
            nvoCro.setCroEntBorrados(proyCroFk.getCroEntBorrados());
            nvoCro.setCroEntSeleccionado(proyCroFk.getCroEntSeleccionado());
            nvoCro.setCroPermisoEscritura(proyCroFk.getCroPermisoEscritura());
            nvoCro.setCroPermisoEscrituraPadre(proyCroFk.getCroPermisoEscrituraPadre());
            nvoCro.setCroResources(proyCroFk.getCroResources());
            nvoCro.setEntregablesSet(entregablesBean.copiarProyEntregables(proyCroFk.getEntregablesSet(), nvoCro, desfasajeDias));
            return nvoCro;
        }
        return null;
    }

    private void eliminarEntregablesBorrados(Cronogramas cro) {
        if (cro != null && !StringsUtils.isEmpty(cro.getCroEntBorrados())) {
            Set<Entregables> entSet = cro.getEntregablesSet();
            cro = obtenerCronograma(cro.getCroPk());
            Set<Entregables> entSetPerist = cro.getEntregablesSet();
            List<Entregables> borradosList = new ArrayList<>();

            for (Entregables ent : entSetPerist) {
                if (!entSet.contains(ent)) {
                    borradosList.add(ent);
                }
            }

            if (borradosList.size() > 0) {
                Estados est = cro.getProyecto().getProyEstFk();
                if (!(est.isEstado(Estados.ESTADOS.INICIO.estado_id) || est.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id))) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_ESTADO);
                    throw be;
                }

                for (Entregables borrado : borradosList) {
                    boolean avance = borrado.getEntProgreso() != null && borrado.getEntProgreso() > 0;
                    boolean horasAsociadas = registrosHorasBean.tieneDependenciasEnt(borrado.getEntPk());
                    boolean partAsociados = participantesBean.tieneDependenciasEnt(borrado.getEntPk());

                    if (avance) {
                        BusinessException be = new BusinessException();
                        be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_AVANCE), borrado.getEntNombre()));
                        throw be;
                    } else if (horasAsociadas) {
                        BusinessException be = new BusinessException();
                        be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_HORAS), borrado.getEntNombre()));
                        throw be;
                    } else if (partAsociados) {
                        BusinessException be = new BusinessException();
                        be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_PART), borrado.getEntNombre()));
                        throw be;
                    }
                    documentosBean.quitarEntregable(borrado.getEntPk());
                }
            }
        }
//        return null;
    }

    /**
     * Dado los valores de azul y rojo, los redondea y luego calcula el valor
     * del verde.
     *
     * @param azul
     * @param rojo
     * @param result
     * @return Array de int con los valores del azul, rojo y verde.
     */
    private int[] cargarYAjustarResultado(double azul, double rojo, int[] result) {
        int azulInt;
        int rojoInt;

        double mod = azul % 1;
        if (mod >= 0.5) {
            azulInt = (int) Math.abs(Math.ceil(azul));
            rojoInt = (int) Math.abs(Math.floor(rojo));
        } else {
            azulInt = (int) Math.abs(Math.floor(azul));
            rojoInt = (int) Math.abs(Math.ceil(rojo));
        }

        result[0] = azulInt;
        result[2] = rojoInt;
        result[1] = 100 - result[0] - result[2];

        return result;
    }
}
