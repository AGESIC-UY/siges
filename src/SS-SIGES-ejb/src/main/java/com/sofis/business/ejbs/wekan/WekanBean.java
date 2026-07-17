package com.sofis.business.ejbs.wekan;

import org.apache.commons.codec.binary.Base64;
import com.sofis.business.ejbs.ConfiguracionBean;
import com.sofis.business.wrapper.WekanApiClientWrapper;
import com.sofis.data.daos.OrganismoDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesIntegracionWekan;
import com.sofis.entities.data.Vinculacion;
import com.sofis.entities.tipos.wekan.VinculacionTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.EmailValidator;
import com.sofis.wekanapiclient.LoginApi;
import com.sofis.wekanapiclient.invoker.ApiClient;
import com.sofis.wekanapiclient.model.Login;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;

public class WekanBean {

    private static final Logger LOGGER = Logger.getLogger(WekanBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private ConfiguracionBean configuracionBean;

    public WekanApiClientWrapper getAdminApi(Vinculacion vinculacion) throws BusinessException {

        try {
            String urlServidor = vinculacion.getTablero().getUrlServidor();

            OrganismoDAO organismoDAO = new OrganismoDAO(em);
            Integer idOrganismo = organismoDAO.obtenerOrganismoPorIdCronograma(vinculacion.getCronograma().getCroPk()).getOrgPk();

            return getAdminApi(urlServidor, idOrganismo);

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GET_ADMIN_API, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_LOGIN_ADMIN_API);

            throw be;
        }
    }

    public WekanApiClientWrapper getAdminApi(VinculacionTO vinculacion) throws BusinessException {

        try {
            String urlServidor = vinculacion.getTablero().getUrlServidor();

            OrganismoDAO organismoDAO = new OrganismoDAO(em);
            Integer idOrganismo = organismoDAO.obtenerOrganismoPorIdCronograma(vinculacion.getCronograma().getId()).getOrgPk();

            return getAdminApi(urlServidor, idOrganismo);

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GET_ADMIN_API, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_LOGIN_ADMIN_API);

            throw be;
        }
    }

    public WekanApiClientWrapper getAdminApi(String urlServidor, Integer idOrganismo) throws BusinessException {

        WekanApiClientWrapper result = new WekanApiClientWrapper();
        String propiedadUsuarioWekan = "wekan_user_" + idOrganismo;
        String propiedadPasswordWekan = "wekan_password_" + idOrganismo;
        String adminWekanUsuario, adminWekanContrasenia;


        if (configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.WEKAN_ADMIN_USUARIO, idOrganismo).isEmpty()) {
            LOGGER.info("Usuario Admin de api wekan tomado de standalone");
            adminWekanUsuario = "S/D";
            if (System.getProperty(propiedadUsuarioWekan) != null) {
                adminWekanUsuario = System.getProperty(propiedadUsuarioWekan);
            } else {
                LOGGER.info("Usuario Admin de api wekan no configurado standalone");
            }

        } else {
            LOGGER.info("Usuario Admin de api wekan tomado de config");
            adminWekanUsuario = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.WEKAN_ADMIN_USUARIO, idOrganismo);

        }

        if (configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.WEKAN_ADMIN_CONTRASENIA, idOrganismo).isEmpty()) {
            LOGGER.info("Password Admin de api wekan tomado de standalone");
            adminWekanContrasenia = "S/D";
            if (System.getProperty(propiedadPasswordWekan) != null) {
                adminWekanContrasenia = System.getProperty(propiedadPasswordWekan);
            }else{
                 LOGGER.info("Password Admin de api wekan no configurado standalone");
            }

        } else {
            LOGGER.info("Password Admin de api wekan tomado de config");
            adminWekanContrasenia = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.WEKAN_ADMIN_CONTRASENIA, idOrganismo);

        }
        
        String passwordPlana = new String(DatatypeConverter.parseBase64Binary(adminWekanContrasenia));

        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(urlServidor);

        Login login;
        try {
            LoginApi loginApi = new LoginApi(apiClient);

            login = EmailValidator.validateEmail(adminWekanUsuario)
                    ? loginApi.login(passwordPlana, null, adminWekanUsuario)
                    : loginApi.login(passwordPlana, adminWekanUsuario, null);

            apiClient.setApiKeyPrefix("Bearer");
            apiClient.setApiKey(login.getToken());

            result.setApiClient(apiClient);
            result.setLoginId(login.getId());

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_LOGIN_ADMIN_API, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_LOGIN_ADMIN_API);

            throw be;
        }

        return result;
    }

    private String obtenerContraseniaAdmin(Integer idOrganismo) {

        String adminWekanContraseniaBase64 = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.WEKAN_ADMIN_CONTRASENIA, idOrganismo);

        return new String(DatatypeConverter.parseBase64Binary(adminWekanContraseniaBase64));
    }

}
