package com.sofis.business.ejbs;

import com.sofis.business.validations.MediaProyectosValidacion;
import com.sofis.data.daos.MediaProyectosDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.codigueras.TiposMediaCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.MediaProyectos;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TiposMedia;
import com.sofis.entities.enums.EstadoMediaProyectosEnum;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.agesic.siges.visualizador.web.ws.MediaImpProyectos;
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
    @Inject
    private TiposMediaBean tiposMediaBean;
    @Inject
    private ProyectosBean proyectosBean;
    @Inject
    private SsUsuarioBean ssUsuarioBean;

    //private String usuario;
    //private String origen;

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    private MediaProyectos guardar(MediaProyectos mp) {
        if (mp != null) {
            MediaProyectosValidacion.validar(mp);

            MediaProyectosDAO dao = new MediaProyectosDAO(em);
            try {
                return dao.update(mp, du.getCodigoUsuario(),du.getOrigen());
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_MEDIA_PROY_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    public MediaProyectos guardarMP(MediaProyectos mediaProy, Integer usuId, Integer orgPk) {
        if (mediaProy != null) {
            SsUsuario usu = ssUsuarioBean.obtenerSsUsuarioPorId(usuId);
            if (mediaProy.getMediaPk() == null) {
                mediaProy.setMediaUsrPubFk(usu);
                mediaProy.setMediaPubFecha(new Date());
            } else {
                mediaProy.setMediaUsrModFk(usu);
                mediaProy.setMediaModFecha(new Date());
            }
            if (mediaProy.getMediaPublicable() == null) {
                mediaProy.setMediaPublicable(Boolean.FALSE);
            }
            if (mediaProy.getMediaPrincipal() == null) {
                mediaProy.setMediaPrincipal(Boolean.FALSE);
            }

            mediaProy = salvarFile(mediaProy, orgPk);

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

    public MediaProyectos obtenerImgPrincipal(Integer proyPk) {
        if (proyPk != null) {
            MediaProyectosDAO dao = new MediaProyectosDAO(em);

            MatchCriteriaTO critProy = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "mediaProyFk.proyPk", proyPk);
            MatchCriteriaTO critPrincipal = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "mediaPrincipal", Boolean.TRUE);
            MatchCriteriaTO critTipo = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "mediaTipoFk.tipCod", TiposMediaCodigos.IMG);

            CriteriaTO condicion = CriteriaTOUtils.createANDTO(critProy, critPrincipal, critTipo);

            try {
                String[] orderBy = new String[]{};
                boolean[] ascending = new boolean[]{};
                List<MediaProyectos> result = dao.findEntityByCriteria(MediaProyectos.class, condicion, orderBy, ascending, null, null);
                if (result != null && result.size() > 0) {
                    return result.get(0);
                }
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

    /**
     * Carga los archivos(bytes[]) en la lista de media.
     *
     * @param listMP
     * @param orgPk
     * @return
     */
    public List<MediaProyectos> cargarArchivos(List<MediaProyectos> listMP, Integer orgPk) {
        if (CollectionsUtils.isNotEmpty(listMP)) {
            for (MediaProyectos mp : listMP) {
                mp = cargarArchivo(mp, orgPk);
            }
        }
        return listMP;
    }

    public MediaProyectos cargarArchivo(MediaProyectos mp, Integer orgPk) {
        if (mp != null) {
            if (mp.getMediaTipoFk().isTipoMediaCod(TiposMediaCodigos.IMG)
                    && !StringsUtils.isEmpty(mp.getMediaLink())) {
                String folderMedia = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.FOLDER_MEDIA, orgPk);
                File f = new File(folderMedia + File.separator + mp.getMediaLink());
                try {
                    mp.setMediaBytes(FileUtils.readFileToByteArray(f));
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
        return mp;
    }

    public List<MediaImpProyectos> crearMediaProyFromImp(List<MediaProyectos> listMP) {
        if (CollectionsUtils.isNotEmpty(listMP)) {
            List<MediaImpProyectos> mipList = new ArrayList<>();
            for (MediaProyectos mp : listMP) {
                MediaImpProyectos mip = new MediaImpProyectos();
                mip.setMediaOrigenPk(mp.getMediaPk());
                mip.setMediaBytes(mp.getMediaBytes());
                mip.setMediaImpComentario(mp.getMediaComentario());
                mip.setMediaImpContenttype(mp.getMediaContenttype());
                mip.setMediaImpEstado(mp.getMediaEstado() == null ? EstadoMediaProyectosEnum.PENDIENTE_REVISION.cod : mp.getMediaEstado());
                mip.setMediaImpLink(mp.getMediaLink());
                mip.setMediaImpOrden(mp.getMediaOrden());
                mip.setMediaImpPrincipal(mp.getMediaPrincipal() != null && mp.getMediaPrincipal() ? true : false);
                if (mp.getMediaTipoFk() != null) {
                    org.agesic.siges.visualizador.web.ws.TiposMedia tm = new org.agesic.siges.visualizador.web.ws.TiposMedia();
                    tm.setTipPk(mp.getMediaTipoFk().getTipPk());
                    tm.setTipCod(mp.getMediaTipoFk().getTipCod());
                    mip.setMediaImpTipoFk(tm);
                }

                mipList.add(mip);
            }
            return mipList;
        }
        return null;
    }

    public MediaProyectos subirFoto(Integer proyPk, Integer usuId, byte[] mediaBytes, String comentario) {
        if (proyPk != null && usuId != null && (mediaBytes != null && mediaBytes.length > 0)) {
            Integer orgPk = proyectosBean.obtenerOrgPkPorProyPk(proyPk);
            validarImageSize(mediaBytes, orgPk);

            TiposMedia tm = tiposMediaBean.obtenerPorCodigo(TiposMediaCodigos.IMG);
            MediaProyectos mediaProy = new MediaProyectos();
            mediaProy.setMediaTipoFk(tm);
            mediaProy.setMediaEstado(EstadoMediaProyectosEnum.PENDIENTE_REVISION.cod);
            mediaProy.setMediaProyFk(new Proyectos(proyPk));
            mediaProy.setMediaBytes(mediaBytes);
            mediaProy.setMediaComentario(comentario);

            try {
                mediaProy = this.guardarMP(mediaProy, usuId, orgPk);
                return mediaProy;

            } catch (BusinessException be) {
                logger.log(Level.SEVERE, null, be);
            }
        }
        return null;
    }

    /**
     * Dados los bytes del MediaProyectos genera el nombre del archivo y lo
     * almacena.
     *
     * @param mediaProy
     * @return MediaProyectos
     */
    public MediaProyectos salvarFile(MediaProyectos mediaProy, Integer orgPk) {
        logger.log(Level.FINEST, "MediaProyectosBean salvarFile...");
        if (mediaProy.getMediaBytes() != null && mediaProy.getMediaBytes().length > 0) {
            validarImageSize(mediaProy.getMediaBytes(), orgPk);

            String folderMedia = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.FOLDER_MEDIA, orgPk);
            if (StringsUtils.isEmpty(folderMedia)) {
                logger.log(Level.SEVERE, "ERROR al obtener la carpeta del archivo.");
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_MEDIA_PROY_GUARDAR);
                throw be;
            }

            File f;
            String name = null;
            //debe crear el media en disco y agregar el link al lugar creado
            if (!StringsUtils.isEmpty(mediaProy.getMediaLink())) {
                name = mediaProy.getMediaLink();
                f = new File(folderMedia + File.separator + name);
            } else {
                name = RandomStringUtils.randomAlphanumeric(8);
                f = new File(folderMedia + File.separator + name);
                while (f.exists()) {
                    name = RandomStringUtils.randomAlphanumeric(8);
                    f = new File(folderMedia + File.separator + name);
                }
            }

            try {
                FileUtils.writeByteArrayToFile(f, mediaProy.getMediaBytes());
            } catch (IOException iOException) {
                logger.log(Level.SEVERE, null, iOException);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_MEDIA_PROY_GUARDAR);
                throw be;
            }
            mediaProy.setMediaLink(name);
        }
        return mediaProy;
    }

    public boolean validarImageSize(byte[] mediaBytes, Integer orgPk) {
        if (mediaBytes != null && orgPk != null) {
            String tamanioArchMult = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MULTIMEDIA, orgPk);
            Integer lenghtArchMulti = 0;
            try {
                lenghtArchMulti = Integer.valueOf(tamanioArchMult);
            } catch (NumberFormatException numberFormatException) {
                logger.log(Level.WARNING, "Error al convertir la configuración " + ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MULTIMEDIA + " a número.", numberFormatException);
            }

            if (mediaBytes.length <= lenghtArchMulti) {
                return true;
            } else {
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.WARN_EXP_VISUA_IMG_SIZE);
                throw be;
            }
        }
        return false;
    }
}
