package com.sofis.business.ejbs;

import com.sofis.data.daos.TiposMediaDAO;
import com.sofis.entities.codigueras.TiposMediaCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.TiposMedia;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "TiposMediaBean")
@LocalBean
public class TiposMediaBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    
    @Inject
    private DatosUsuario du;
    
    public TiposMedia guardar(TiposMedia tm) {
        TiposMediaDAO dao = new TiposMediaDAO(em);

        try {
            tm = dao.update(tm, du.getCodigoUsuario(), du.getOrigen());
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_TIPOS_MEDIA_GUARDAR);
            throw be;
        }

        return tm;
    }

    public List<TiposMedia> obtenerTodos() throws GeneralException {
        TiposMediaDAO dao = new TiposMediaDAO(em);
        try {
            List<TiposMedia> result = dao.findAll(TiposMedia.class, "tipNombre");
            return result;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_TIPOS_MEDIA_OBTENER);
            throw be;
        }
    }

    public void controlarTiposMediaFaltantes() {
        List<TiposMedia> listTM=  obtenerTodos();
        Map<String, TiposMedia> tipoMediaMap=  new HashMap<>();
        if (listTM != null) {
            for (TiposMedia tm : listTM) {
                tipoMediaMap.put(tm.getTipCod(), tm);
            }
        }

        TiposMedia[] tmArr = new TiposMedia[]{
            new TiposMedia(null, TiposMediaCodigos.IMG, "Imagen"),
            new TiposMedia(null, TiposMediaCodigos.VIDEO, "Video"),
            new TiposMedia(null, TiposMediaCodigos.CAM, "Cámara")
        };

        for (TiposMedia tipoMedia : tmArr) {
            if (!tipoMediaMap.containsKey(tipoMedia.getTipCod())) {
                guardar(tipoMedia);
                logger.log(Level.INFO, StringsUtils.concat("Se agregó el estado de publicación '", tipoMedia.getTipCod()), "'");
            }
        }
    }
}
