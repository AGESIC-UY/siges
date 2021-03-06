package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.TipoDocumentoPersonaValidacion;
import com.sofis.data.daos.TipoDocumentoPersonaDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.TipoDocumentoPersona;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
@Stateless(name = "TipoDocumentoPersonaBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class TipoDocumentoPersonaBean {

    @Inject
    private DatosUsuario du;
    @Inject
    private ConsultaHistorico<TipoDocumentoPersona> ch;
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(TipoDocumentoPersonaBean.class.getName());
    
    
    //private String usuario;
    //private String origen;
    
    @PostConstruct
    public void init(){
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }
    

    /**
     * Este método guarda un elemento de tipo TipoDocumentoPersona. Se aplica
     * para la creación de la entidad y para la modificación de una entidad
     * existente.
     *
     * @param cnf
     * @throws GeneralException Devuelve los códigos de error de la validación
     */
    public TipoDocumentoPersona guardar(TipoDocumentoPersona tdp) throws GeneralException {
        logger.log(Level.SEVERE, "guardar");
        try {
            //Validar el elemento a guardar. Si no valida, se lanza una excepcion
            if (TipoDocumentoPersonaValidacion.validar(tdp)) {
                TipoDocumentoPersonaDAO njuDao = new TipoDocumentoPersonaDAO(em);
                if (tdp.getTipdocperId() == null) {
                    tdp = njuDao.create(tdp, du.getCodigoUsuario(),du.getOrigen());
                } else {
                    //Si el dato ya fue guardado, se determina que haya cambiado alguno de los valores.
                    //En caso contrario no se guarda
                    TipoDocumentoPersona valorAnterior = ch.obtenerEnVersion(tdp.getClass(), tdp.getTipdocperVersion(), tdp.getTipdocperId().intValue(), "tipdocperVersion");
                    if (!TipoDocumentoPersonaValidacion.compararParaGrabar(valorAnterior, tdp)) {
                        tdp = njuDao.update(tdp, du.getCodigoUsuario(),du.getOrigen());
                    }
                }
            }
            return tdp;
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
    public TipoDocumentoPersona obtenerTipoPersonaDocumentoPorId(Integer id) throws GeneralException {
        logger.log(Level.INFO, "obtenerTipoPersonaDocumentoPorId");
        TipoDocumentoPersonaDAO tpdDao = new TipoDocumentoPersonaDAO(em);
        try {
            return tpdDao.findById(TipoDocumentoPersona.class, id);
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
    public TipoDocumentoPersona obtenerTipoPersonaDocumentoPorCodigo(String codigo) throws GeneralException {
        logger.log(Level.INFO, "obtenerTipoPersonaDocumentoPorCodigo");
        TipoDocumentoPersonaDAO cnfDao = new TipoDocumentoPersonaDAO(em);
        try {
            List<TipoDocumentoPersona> resultado = cnfDao.findByOneProperty(TipoDocumentoPersona.class, "tipdocperCodigo", codigo);
            return (TipoDocumentoPersona) DAOUtils.obtenerSingleResult(resultado);
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
    public List<TipoDocumentoPersona> obtenerTodos() throws GeneralException {
        logger.log(Level.INFO, "obtenerTodos");
        TipoDocumentoPersonaDAO cnfDao = new TipoDocumentoPersonaDAO(em);
        try {
            return cnfDao.findAll(TipoDocumentoPersona.class);
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
    public List<TipoDocumentoPersona> obtenerHabilitados() throws GeneralException {
        logger.log(Level.INFO, "obtenerTodos");
        TipoDocumentoPersonaDAO cnfDao = new TipoDocumentoPersonaDAO(em);
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
    public List<TipoDocumentoPersona> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
        logger.log(Level.INFO, "obtenerPorCriteria");
        TipoDocumentoPersonaDAO cnfDao = new TipoDocumentoPersonaDAO(em);
        try {
            return cnfDao.findEntityByCriteria(TipoDocumentoPersona.class, cto, orderBy, ascending, startPosition, cantidad);
        } catch (Exception ex) {
//            logger.log(Level.SEVERE, ex.getMessage() , ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }
}
