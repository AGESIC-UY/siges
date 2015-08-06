package com.sofis.business.ejbs;

import com.sofis.business.validations.MediaProyectosValidacion;
import com.sofis.data.daos.MediaProyectosDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.codigueras.TiposMediaCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.MediaProyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "MediaProyectosBean")
@LocalBean
public class MediaProyectosBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @Inject
    private DatosUsuario du;
    @Inject
    private ConfiguracionBean configuracionBean;

    private MediaProyectos guardar(MediaProyectos mp) {
        if (mp != null) {
            MediaProyectosValidacion.validar(mp);

            MediaProyectosDAO dao = new MediaProyectosDAO(em);
            try {
                return dao.update(mp, du.getCodigoUsuario(), du.getOrigen());
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_MEDIA_PROY_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    public MediaProyectos guardarMP(MediaProyectos mediaProy, SsUsuario usuario, Integer orgPk) {
        if (mediaProy != null) {
            if (mediaProy.getMediaPk() == null) {
                mediaProy.setMediaUsrPubFk(usuario);
                mediaProy.setMediaPubFecha(new Date());
            } else {
                mediaProy.setMediaUsrModFk(usuario);
                mediaProy.setMediaModFecha(new Date());
            }

            Configuracion conf = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.FOLDER_MEDIA, orgPk);
            String folderMedia = conf.getCnfValor();
            try {

                if (mediaProy.getMediaBytes() != null) {
                    //debe crear el media en disco y agregar el link al lugar creado
                    File f = new File(folderMedia + File.separator + RandomStringUtils.randomAlphanumeric(8));
                    while (f.exists()) {
                        f = new File(folderMedia + File.separator + RandomStringUtils.randomAlphanumeric(8));
                    }
                    FileUtils.writeByteArrayToFile(f, mediaProy.getMediaBytes());
                    mediaProy.setMediaLink(f.getAbsolutePath());
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_MEDIA_PROY_GUARDAR);
                throw be;
            }

            return guardar(mediaProy);
        }
        return null;
    }

    public List<MediaProyectos> obtenerPorProyId(Integer proyPk) {
        if (proyPk != null) {
            MediaProyectosDAO dao = new MediaProyectosDAO(em);
            try {
                List<MediaProyectos> result = dao.findByOneProperty(MediaProyectos.class, "mediaProyFk.proyPk", proyPk);
                return result;
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_MEDIA_PROY_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public MediaProyectos obtenerPorId(Integer mediaPk) {
        if (mediaPk != null) {
            MediaProyectosDAO dao = new MediaProyectosDAO(em);
            try {
                MediaProyectos result = dao.findById(MediaProyectos.class, mediaPk);
                return result;
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_MEDIA_PROY_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public void eliminar(Integer mediaPk) {
        logger.log(Level.INFO, "Eliminar el Multimedia con id:{0}", mediaPk);
        MediaProyectosDAO dao = new MediaProyectosDAO(em);
        try {
            MediaProyectos media = this.obtenerPorId(mediaPk);
            dao.delete(media);

        } catch (BusinessException | DAOGeneralException ex) {
            logger.logp(Level.SEVERE, RiesgosBean.class.getName(), "delete", ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(ex.getMessage());
            throw be;
        }
    }

    public List<MediaProyectos> cargarArchivos(List<MediaProyectos> listMP) {
        if (CollectionsUtils.isNotEmpty(listMP)) {
            for (MediaProyectos mp : listMP) {
                mp = cargarArchivo(mp);
            }
        }
        return listMP;
    }

    public MediaProyectos cargarArchivo(MediaProyectos mp) {
        if (mp != null) {
            if (mp.getMediaTipoFk().isTipoMediaCod(TiposMediaCodigos.IMG)) {
                File f = new File(mp.getMediaLink());
                try {
                    mp.setMediaBytes(FileUtils.readFileToByteArray(f));
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
        return mp;
    }
}
