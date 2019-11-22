package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.data.daos.TipoTelefonoDAO;

import com.sofis.entities.data.TipoTelefono;

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
import com.sofis.business.validations.TipoTelefonoValidacion;
import com.sofis.entities.constantes.ConstanteApp;
import javax.annotation.PostConstruct;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "TipoTelefonoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class TipoTelefonoBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    @Inject
    private ConsultaHistorico<TipoTelefono> ch;
    private static final Logger logger = Logger.getLogger(TipoTelefonoBean.class.getName());
    
    //private String usuario;
    //private String origen;
    
    @PostConstruct
    public void init(){
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }
    

    /**
     * Este método guarda un elemento de tipo TipoTelefono. Se aplica para la
     * creación de la entidad y para la modificación de una entidad existente.
     *
     * @param cnf
     * @throws GeneralException Devuelve los códigos de error de la validación
     */
    public TipoTelefono guardar(TipoTelefono tipTel) throws GeneralException {
        logger.log(Level.SEVERE, "guardar");
        try {
            //Validar el elemento a guardar. Si no valida, se lanza una excepcion
            if (TipoTelefonoValidacion.validar(tipTel)) {
                TipoTelefonoDAO njuDao = new TipoTelefonoDAO(em);
                if (tipTel.getTipTelId() == null) {
                    tipTel = njuDao.create(tipTel, du.getCodigoUsuario(),du.getOrigen());
                } else {
                    //Si el dato ya fue guardado, se determina que haya cambiado alguno de los valores.
                    //En caso contrario no se guarda
                    TipoTelefono valorAnterior = ch.obtenerEnVersion(tipTel.getClass(), tipTel.getTipTelVersion(), tipTel.getTipTelId().intValue(), "tipTelVersion");
                    if (!TipoTelefonoValidacion.compararParaGrabar(valorAnterior, tipTel)) {
                        tipTel = njuDao.update(tipTel, du.getCodigoUsuario(),du.getOrigen());
                    }
                }
            }
            return tipTel;
        } catch (BusinessException be) {
            //Si es de tipo negocio envía la misma excepción
            throw be;
        } catch (Exception ex) {
            //Las demás excepciones se consideran técnicas
//            logger.log(Level.SEVERE, ex.getMessage() , ex);
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
    public TipoTelefono obtenerTipoTelefonoPorId(Integer id) throws GeneralException {
        logger.log(Level.INFO, "obtenerTipoTelefonoPorId");
        TipoTelefonoDAO tpdDao = new TipoTelefonoDAO(em);
        try {
            return tpdDao.findById(TipoTelefono.class, id);
        } catch (DAOGeneralException ex) {
//           logger.log(Level.SEVERE, ex.getMessage() , ex);
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
    public TipoTelefono obtenerTipoTelefonoPorCodigo(String codigo) throws GeneralException {
        logger.log(Level.INFO, "obtenerTipoTelefonoPorCodigo");
        TipoTelefonoDAO cnfDao = new TipoTelefonoDAO(em);
        try {
            List<TipoTelefono> resultado = cnfDao.findByOneProperty(TipoTelefono.class, "tipTelCodigo", codigo);
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
//            logger.log(Level.SEVERE, ex.getMessage() , ex);
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
    public List<TipoTelefono> obtenerTodos() throws GeneralException {
        logger.log(Level.INFO, "obtenerTodos");
        TipoTelefonoDAO cnfDao = new TipoTelefonoDAO(em);
        try {
            return cnfDao.findAll(TipoTelefono.class);
        } catch (Exception ex) {
//            logger.log(Level.SEVERE, ex.getMessage() , ex);
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
    public List<TipoTelefono> obtenerHabilitados() throws GeneralException {
        logger.log(Level.INFO, "obtenerTodos");
        TipoTelefonoDAO cnfDao = new TipoTelefonoDAO(em);
        try {
            return cnfDao.obtenerHabilitados();
        } catch (Exception ex) {
//            logger.log(Level.SEVERE, ex.getMessage() , ex);
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
    public List<TipoTelefono> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
        logger.log(Level.INFO, "obtenerPorCriteria");
        TipoTelefonoDAO cnfDao = new TipoTelefonoDAO(em);
        try {
            return cnfDao.findEntityByCriteria(TipoTelefono.class, cto, orderBy, ascending, startPosition, cantidad);
        } catch (Exception ex) {
//            logger.log(Level.SEVERE, ex.getMessage() , ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }
}
