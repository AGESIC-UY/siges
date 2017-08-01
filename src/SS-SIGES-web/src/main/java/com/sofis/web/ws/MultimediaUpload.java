package com.sofis.web.ws;

import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.MediaProyectos;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.IdNombreTO;
import com.sofis.entities.tipos.ProyectoTO;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.MediaProyectosDelegate;
import com.sofis.web.delegates.OrganismoDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.ws.tipos.FileUploadTO;
import com.sofis.web.ws.tipos.MultimediaTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.icefaces.ace.json.JSONException;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

/**
 * REST Web Service para Multimedia.
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Path("/WS/multimedia")
@RequestScoped
public class MultimediaUpload {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String LOGO_ORG = "logoOrg.png";
    private static final String LOGO_PROY = "logoProy.png";

    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;
    @Inject
    private ProyectosDelegate proyectosDelegate;
    @Inject
    private OrganismoDelegate organismoDelegate;
    @Inject
    private MediaProyectosDelegate mediaProyectosDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;

//    @Context
//    private UriInfo context;
    /**
     * Creates a new instance of MultimediaUpload
     */
    public MultimediaUpload() {
    }

    @POST
    @Path("/testPost")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String testPost(String s) {
        logger.log(Level.INFO, "WS test Post...");
        if (!StringsUtils.isEmpty(s)) {
            return "El string enviado es:" + s;
        }
        return "El string es vacío.";
    }

    @GET
    @Path("/testGet")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String testGet(String s) {
        logger.log(Level.INFO, "WS test Get...");
        if (!StringsUtils.isEmpty(s)) {
            return "El string enviado es:" + s;
        }
        return "El string es vacío.";
    }

    @POST
    @Path("/validaUsu")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validarUsuario(MultimediaTO m) throws JSONException {
        logger.log(Level.INFO, "WS /validaUsu...");
        if (m != null) {
            if (!StringsUtils.isEmpty(m.getMail()) && !StringsUtils.isEmpty(m.getPass())) {
                String result = ssUsuarioDelegate.validarUsuarioToToken(m.getMail(), m.getPass());
                if (!StringsUtils.isEmpty(result)) {
                    return Response.status(Response.Status.OK).entity(result).build();
                }
            }
            return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_usu_error"));
        }
        return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_request_error"));
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logoutUsuario(MultimediaTO m) throws JSONException {
        logger.log(Level.INFO, "WS /logout...");
        if (m != null) {
            if (!StringsUtils.isEmpty(m.getToken())) {
                boolean result = ssUsuarioDelegate.eliminarToken(m.getToken());
                if (result) {
                    return Response.status(Response.Status.OK).build();
                }
            }
            return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_token_null"));
        }
        return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_request_error"));
    }

    @POST
    @Path("/getOrgs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerOrganismos(MultimediaTO m) throws JSONException {
        logger.log(Level.INFO, "WS /getOrgs...");
        if (m != null) {
            SsUsuario usuario = ssUsuarioDelegate.obtenerUsuarioPorToken(m.getToken());
            if (usuario == null) {
                return requestMsg(Response.Status.UNAUTHORIZED, Labels.getValue("appmobile_token_null"));
            }
            List<IdNombreTO> result = organismoDelegate.obtenerOrgIdNombre(usuario.getUsuId());
            if (CollectionsUtils.isNotEmpty(result)) {
                return Response.status(Response.Status.OK).entity(result).build();
            } else {
                return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_org_list_null"));
            }
        }
        return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_request_error"));
    }

    @POST
    @Path("/getProys")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerProyectos(MultimediaTO m) throws JSONException {
        logger.log(Level.INFO, "WS /getProys...");
        if (m != null) {
            SsUsuario usuario = ssUsuarioDelegate.obtenerUsuarioPorToken(m.getToken());
            if (usuario == null) {
                return requestMsg(Response.Status.UNAUTHORIZED, Labels.getValue("appmobile_token_null"));
            }
            if (m.getOrgPk() == null) {
                return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_orgpk_null"));
            }

            List<IdNombreTO> result = proyectosDelegate.obtenerProyIdNombre(usuario.getUsuId(), m.getOrgPk());
//            if (CollectionsUtils.isNotEmpty(result)) {
            return Response.status(Response.Status.OK).entity(result).build();
//            } else {
//                return Response.status(Response.Status.BAD_REQUEST).entity(Labels.getValue("appmobile_proy_list_null")).build();
//            }
        }
        return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_request_error"));
    }

    @POST
    @Path("/getProy")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerProyectoDesc(MultimediaTO m) throws JSONException {
        logger.log(Level.INFO, "WS /getProy...");
        if (m != null) {
            SsUsuario usuario = ssUsuarioDelegate.obtenerUsuarioPorToken(m.getToken());
            if (usuario == null) {
                return requestMsg(Response.Status.UNAUTHORIZED, Labels.getValue("appmobile_token_null"));
            }
            if (m.getProyPk() == null) {
                return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_proypk_null"));
            }
            if (!proyectosDelegate.esColaboradorProy(usuario.getUsuId(), m.getProyPk())) {
                return requestMsg(Response.Status.UNAUTHORIZED, Labels.getValue("appmobile_usu_colab_proy"));
            }

            ProyectoTO proyTO = proyectosDelegate.obtenerProyTO(m.getProyPk());
            if (proyTO != null) {
                return Response.status(Response.Status.OK).entity(proyTO).build();
            } else {
                return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_proy_null"));
            }
        }
        return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_request_error"));
    }

    /**
     * Retorna la imagen marcada como principal del proyecto. Si no tiene
     * retorna uno generico de la carpeta resources.
     *
     * @param token
     * @param proyPk
     * @return
     * @throws JSONException
     */
    @GET
    @Path("/getProyImg")
    public Response obtenerProyImagen(@QueryParam("proyPk") final Integer proyPk) throws JSONException {
        logger.log(Level.INFO, "WS /getProyImg...");
        if (proyPk == null) {
            return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_proypk_null"));
        }

        MediaProyectos mp = mediaProyectosDelegate.obtenerImgPrincipal(proyPk);

        String nombreFile;
        byte[] bytesFile = null;
        File file;

        if (mp != null && mp.getMediaLink() != null) {
            nombreFile = mp.getMediaLink();
            Integer orgPk = proyectosDelegate.obtenerOrgPkPorProyPk(proyPk);
            String folderMedia = configuracionDelegate.obtenerCnfValorPorCodigo(ConfiguracionCodigos.FOLDER_MEDIA, orgPk);
            file = new File(folderMedia + File.separator + mp.getMediaLink());

            try {
                FileOutputStream fos = new FileOutputStream(nombreFile);
                fos.write(bytesFile);
                fos.close();

            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error al generar el logo del Proyecto.");
            }

        } else {
            nombreFile = LOGO_PROY;
            file = null;

            try {
                InputStream templateStream = this.getClass().getClassLoader().getResourceAsStream(nombreFile);
                File tempDir = new File(System.getProperty("java.io.tmpdir"));
                file = new File(tempDir, nombreFile);
                IOUtils.copy(templateStream, new FileOutputStream(file));
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
                logger.log(Level.SEVERE, "Error al generar el logo del organismo.");
            }
        }

        if (file.exists()) {
            Response.ResponseBuilder response = Response.ok((Object) file);
            response.header("Content-Disposition", "attachment; filename=\"" + nombreFile + "\"");
            return response.build();
        }

        return requestMsg(Response.Status.NOT_FOUND, Labels.getValue("appmobile_img_empty"));
    }

    /**
     * Retorna el logo del organismo. Si no tiene retorna uno generico de la
     * carpeta resources.
     *
     * @param token
     * @param orgPk
     * @return
     * @throws JSONException
     */
    @GET
    @Path("/getOrgImg")
    public Response obtenerOrgImagen(@QueryParam("orgPk") final Integer orgPk) throws JSONException {
        logger.log(Level.INFO, "WS /getOrgImg...");
        if (orgPk == null) {
            return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_orgpk_null"));
        }

        Organismos org = organismoDelegate.obtenerOrgPorId(orgPk, Boolean.TRUE);
        if (org != null) {
            String nombreFile;
            byte[] bytesFile;
            File file;

            if (org.getOrgLogo() != null) {
                nombreFile = org.getOrgLogoNombre();
                bytesFile = org.getOrgLogo();
                file = new File(nombreFile);

                try {
                    File tempDir = new File(System.getProperty("java.io.tmpdir"));
                    file = new File(tempDir, nombreFile);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(bytesFile);
                    fos.close();

                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error al generar el logo del organismo.");
                }

            } else {
                nombreFile = LOGO_ORG;
                file = null;

                try {
                    InputStream templateStream = this.getClass().getClassLoader().getResourceAsStream(nombreFile);
                    File tempDir = new File(System.getProperty("java.io.tmpdir"));
                    file = new File(tempDir, nombreFile);
                    IOUtils.copy(templateStream, new FileOutputStream(file));
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, null, ex);
                    logger.log(Level.SEVERE, "Error al generar el logo del organismo.");
                }
            }

            if (file.exists()) {
                Response.ResponseBuilder response = Response.ok((Object) file);
                response.header("Content-Disposition", "attachment; filename=\"" + nombreFile + "\"");
                return response.build();
            }
        }
        return requestMsg(Response.Status.NOT_FOUND, Labels.getValue("appmobile_img_empty"));
    }

    @POST
    @Path("/subirFoto")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response subirFoto(@MultipartForm FileUploadTO form) throws JSONException {
        logger.log(Level.INFO, "WS /subirFoto...");

        if (form != null) {
            MultimediaTO mTO = form.getMultimediaTO();

            SsUsuario usuario = ssUsuarioDelegate.obtenerUsuarioPorToken(mTO.getToken());
            if (usuario == null) {
                return requestMsg(Response.Status.UNAUTHORIZED, Labels.getValue("appmobile_token_null"));
            }

            if (mTO.getProyPk() == null) {
                return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_proypk_null"));
            }

            if (!proyectosDelegate.esColaboradorProy(usuario.getUsuId(), mTO.getProyPk())) {
                return requestMsg(Response.Status.UNAUTHORIZED, Labels.getValue("appmobile_usu_colab_proy"));
            }

            if (form.getFile() == null) {
                return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_foto_null"));
            }

            File file = form.getFile();
            byte[] mediaBytes = new byte[(int) file.length()];

            try {
                //convert file into array of bytes
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(mediaBytes);
                fileInputStream.close();

            } catch (IOException iOException) {
                logger.log(Level.SEVERE, null, iOException);
                return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_img_null"));
            }

            MediaProyectos mp = null;
            try {
                mp = mediaProyectosDelegate.subirFoto(mTO.getProyPk(), usuario.getUsuId(), mediaBytes, mTO.getComentario());
            } catch (Exception e) {
                logger.log(Level.SEVERE, null, e);
            }
            if (mp != null) {
                return Response.status(Response.Status.OK).build();
            } else {
                return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_up_img_error"));
            }

        }
        return requestMsg(Response.Status.BAD_REQUEST, Labels.getValue("appmobile_request_error"));
    }

    /**
     * Genera un Response con el status aportado y el mensaje lo encapsula en un
     * Json.
     *
     * @param status
     * @param msg
     * @return Response
     * @throws JSONException
     */
    private Response requestMsg(Response.Status status, String msg) throws JSONException {
        logger.log(Level.WARNING, "Bad Request:" + msg);
//        JSONObject j = new JSONObject();
//        j.put("msg", msg);
//        return Response.status(status).entity(j).build();
        MultimediaTO mTO = new MultimediaTO();
        mTO.setMsg(msg);
        return Response.status(status).entity(mTO).build();
    }
}
