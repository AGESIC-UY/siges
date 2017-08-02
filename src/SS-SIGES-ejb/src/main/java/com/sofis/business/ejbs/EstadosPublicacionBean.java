package com.sofis.business.ejbs;

import com.sofis.data.daos.EstadosPublicacionDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.codigueras.EstadoPublicacionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.EstadosPublicacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "EstadosPublicacionBean")
@LocalBean
public class EstadosPublicacionBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @Inject
    private DatosUsuario du;
    
    //private String usuario;
    //private String origen;
    
    @PostConstruct
    public void init(){
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }
    

    public EstadosPublicacion guardar(EstadosPublicacion estPub) {
        EstadosPublicacionDAO dao = new EstadosPublicacionDAO(em);

        try {
            estPub = dao.update(estPub, du.getCodigoUsuario(),du.getOrigen());
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_EST_PUB_GUARDAR);
            throw be;
        }

        return estPub;
    }

    public List<EstadosPublicacion> obtenerTodos() {
        EstadosPublicacionDAO dao = new EstadosPublicacionDAO(em);
        try {
            return dao.findAll(EstadosPublicacion.class, "estPubNombre");
        } catch (DAOGeneralException ex) {
            Logger.getLogger(SsRolBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public EstadosPublicacion obtenerPorCodigo(String codigo) {
        EstadosPublicacionDAO dao = new EstadosPublicacionDAO(em);
        try {
            List<EstadosPublicacion> listResult = dao.findByOneProperty(EstadosPublicacion.class, "estPubCodigo", codigo);
            return (EstadosPublicacion) DAOUtils.obtenerSingleResult(listResult);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(SsRolBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void controlarEstPubFaltantes() {
        List<EstadosPublicacion> listEstPub = obtenerTodos();
        Map<String, EstadosPublicacion> estPubMap = new HashMap<>();
        if (listEstPub != null) {
            for (EstadosPublicacion estPub : listEstPub) {
                estPubMap.put(estPub.getEstPubCodigo(), estPub);
            }
        }

        EstadosPublicacion[] estPubArr = new EstadosPublicacion[]{
            new EstadosPublicacion(null, EstadoPublicacionCodigos.NO_ES_PARA_PUBLICAR, "No es para publicar"),
            new EstadosPublicacion(null, EstadoPublicacionCodigos.PENDIENTE_CARGAR, "Pendiente de cargar datos"),
            new EstadosPublicacion(null, EstadoPublicacionCodigos.PENDIENTE_PUBLICAR, "Pendiente de publicar"),
            new EstadosPublicacion(null, EstadoPublicacionCodigos.PUBLICADO, "Publicado")
        };

        for (EstadosPublicacion estPub : estPubArr) {
            if (!estPubMap.containsKey(estPub.getEstPubCodigo())) {
                guardar(estPub);
                logger.log(Level.INFO, StringsUtils.concat("Se agregó el estado de publicación '", estPub.getEstPubCodigo()), "'");
            }
        }
    }
}
