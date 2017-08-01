package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.data.daos.NoticiaDAO;

import com.sofis.entities.data.Noticia;

import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.Date;
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
import com.sofis.business.validations.NoticiaValidacion;
import com.sofis.entities.constantes.ConstanteApp;
import javax.annotation.PostConstruct;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "NoticiaBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class NoticiaBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    @Inject
    private ConsultaHistorico<Noticia> ch;
    private static final Logger logger = Logger.getLogger(ConstantesEstandares.LOGGER);

    //private String usuario;
    //private String origen;

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    /**
     * Este método guarda un elemento de tipo Noticia. Se aplica para la
     * creación de la entidad y para la modificación de una entidad existente.
     *
     * @param cnf
     * @throws GeneralException Devuelve los códigos de error de la validación
     */
    public Noticia guardar(Noticia not) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), not);
        try {
            //Validar el elemento a guardar. Si no valida, se lanza una excepcion
            if (NoticiaValidacion.validar(not)) {
                DatosAuditoria da = new DatosAuditoria();
                logger.log(Level.INFO, "codigo usuario={0}, origen={1}", new Object[]{du.getCodigoUsuario(),du.getOrigen()});
                not = da.registrarDatosAuditoria(not, du.getCodigoUsuario(),du.getOrigen());
                NoticiaDAO njuDao = new NoticiaDAO(em);
                if (not.getNotId() == null) {
                    not = njuDao.create(not, du.getCodigoUsuario(),du.getOrigen());
                } else {
                    //Si el dato ya fue guardado, se determina que haya cambiado alguno de los valores.
                    //En caso contrario no se guarda
                    Noticia valorAnterior = ch.obtenerEnVersion(not.getClass(), not.getNotVersion(), not.getNotId().intValue(), "notVersion");
                    if (!NoticiaValidacion.compararParaGrabar(valorAnterior, not)) {
                        not = njuDao.update(not, du.getCodigoUsuario(),du.getOrigen());
                    }
                }
            }
            return not;
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
    public Noticia obtenerNoticiaPorId(Integer id) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), id);
        NoticiaDAO tpdDao = new NoticiaDAO(em);
        try {
            return tpdDao.findById(Noticia.class, id);
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
    public Noticia obtenerNoticiaPorCodigo(String codigo) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), codigo);
        NoticiaDAO cnfDao = new NoticiaDAO(em);
        try {
            List<Noticia> resultado = cnfDao.findByOneProperty(Noticia.class, "notCodigo", codigo);
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
    public List<Noticia> obtenerTodos() throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), "");

        NoticiaDAO cnfDao = new NoticiaDAO(em);
        try {
            return cnfDao.findAll(Noticia.class);
        } catch (Exception ex) {
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
    public List<Noticia> obtenerNoticiasVigentes() throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), "");

        NoticiaDAO cnfDao = new NoticiaDAO(em);
        try {
            return cnfDao.obtenerNoticiasVigentes(new Date());
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
    public List<Noticia> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), "");

        NoticiaDAO cnfDao = new NoticiaDAO(em);
        try {
            return cnfDao.findEntityByCriteria(Noticia.class, cto, orderBy, ascending, startPosition, cantidad);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }
}
