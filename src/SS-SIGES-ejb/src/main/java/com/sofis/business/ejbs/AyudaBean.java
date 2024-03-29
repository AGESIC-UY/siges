package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.data.daos.AyudaDAO;

import com.sofis.entities.data.Ayuda;

import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.sofis.business.utils.DatosAuditoria;
import com.sofis.business.validations.AyudaValidacion;
import com.sofis.entities.constantes.ConstanteApp;
import javax.annotation.PostConstruct;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "AyudaBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class AyudaBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    @Inject
    private ConsultaHistorico<Ayuda> ch;
    private static final Logger logger = Logger.getLogger(AyudaBean.class.getName());
    
    //private String usuario;
    //private String origen;
    
    @PostConstruct
    public void init(){
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }
    

    /**
     * Este método guarda un elemento de tipo Ayuda. Se aplica para la creación
     * de la entidad y para la modificación de una entidad existente.
     *
     * @param cnf
     * @throws GeneralException Devuelve los códigos de error de la validación
     */
    public Ayuda guardar(Ayuda ayu) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), ayu);
        try {
            //Validar el elemento a guardar. Si no valida, se lanza una excepcion
            if (AyudaValidacion.validar(ayu)) {
                DatosAuditoria da = new DatosAuditoria();
                logger.log(Level.INFO, "codigo usuario={0}, origen={1}", new Object[]{du.getCodigoUsuario(),du.getOrigen()});
                ayu = da.registrarDatosAuditoria(ayu, du.getCodigoUsuario(),du.getOrigen());
                AyudaDAO ayuDao = new AyudaDAO(em);
                if (ayu.getAyuId() == null) {
                    ayu = ayuDao.create(ayu, du.getCodigoUsuario(),du.getOrigen());
                } else {
                    //Si el dato ya fue guardado, se determina que haya cambiado alguno de los valores.
                    //En caso contrario no se guarda
                    Ayuda valorAnterior = ch.obtenerEnVersion(ayu.getClass(), ayu.getAyuVersion(), ayu.getAyuId().intValue(), "ayuVersion");
                    if (!AyudaValidacion.compararParaGrabar(valorAnterior, ayu)) {
                        ayu = ayuDao.update(ayu, du.getCodigoUsuario(),du.getOrigen());
                    }
                }
            }
            return ayu;
        } catch (BusinessException be) {
            //Si es de tipo negocio envía la misma excepción
            throw be;
        } catch (Exception ex) {
            //Las demás excepciones se consideran técnicas
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }

    }

    /**
     * Devuelve el elemento configuracion por el ID
     *
     * @param id
     * @return
     * @throws GeneralException
     */
    public Ayuda obtenerAyudaPorId(Integer id) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), id);
        AyudaDAO tpdDao = new AyudaDAO(em);
        try {
            return tpdDao.findById(Ayuda.class, id);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    /**
     * Devuelve el elemento de configuracion según el código Si no hay ningún
     * elemento con ese código devuevle null
     *
     * @param codigo
     * @return
     * @throws GeneralException
     */
    public Ayuda obtenerAyudaPorCodigo(String codigo) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), codigo);
        AyudaDAO cnfDao = new AyudaDAO(em);
        try {
            List<Ayuda> resultado = cnfDao.findByOneProperty(Ayuda.class, "ayuCodigo", codigo);
            if (resultado.size() == 1) {
                return resultado.get(0);
            } else if (resultado.isEmpty()) {
                return null;
            } else {
                BusinessException be = new BusinessException();
                be.addError(ConstantesErrores.ERROR_DEMASIADOS_RESULTADOS);
                throw be;
            }
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    /**
     * Devuelve todos los elementos de tipo configuracion
     *
     * @return
     * @throws GeneralException
     */
    public List<Ayuda> obtenerTodos() throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), "");

        AyudaDAO cnfDao = new AyudaDAO(em);
        try {
            return cnfDao.findAll(Ayuda.class);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    /**
     * Devuelve los elementos que satisfacen el criterio ingresado
     *
     * @param cto
     * @param orderBy
     * @param ascending
     * @param startPosition
     * @param cantidad
     * @return
     * @throws GeneralException
     */
    public List<Ayuda> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), "");

        AyudaDAO cnfDao = new AyudaDAO(em);
        try {
            return cnfDao.findEntityByCriteria(Ayuda.class, cto, orderBy, ascending, startPosition, cantidad);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }
}
