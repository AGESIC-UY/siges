package com.sofis.business.ejbs;

import com.sofis.data.daos.SsRolDAO;
import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.SsRol;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
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
@Stateless(name = "SsRolBean")
@LocalBean
public class SsRolBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @Inject
    private DatosUsuario du;

    public SsRol guardar(SsRol rol) {
        SsRolDAO dao = new SsRolDAO(em);

        try {
            rol = dao.update(rol, du.getCodigoUsuario(), du.getOrigen());
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ROL_INT_GUARDAR);
            throw be;
        }

        return rol;
    }

    public List<SsRol> obtenerRolesUsuarios() {
        SsRolDAO dao = new SsRolDAO(em);
        try {
            return dao.findByOneProperty(SsRol.class, "rolTipoUsuario", Boolean.TRUE, "rolNombre");
        } catch (DAOGeneralException ex) {
            Logger.getLogger(SsRolBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<SsRol> obtenerRoles() {
        SsRolDAO dao = new SsRolDAO(em);
        try {
            return dao.findAll(SsRol.class, "rolNombre");
        } catch (DAOGeneralException ex) {
            Logger.getLogger(SsRolBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public SsRol obtenerRolPorCod(String cod) {
        SsRolDAO dao = new SsRolDAO(em);
        List<SsRol> result = null;
        try {
            result = dao.findByOneProperty(SsRol.class, "rolCod", cod);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(SsRolBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (CollectionsUtils.isNotEmpty(result)) {
            if (result.size() == 1) {
                return result.get(0);
            } else {
                BusinessException be = new BusinessException();
                be.addError(ConstantesErrores.ERROR_DEMASIADOS_RESULTADOS);
                throw be;
            }
        }
        return null;
    }

    public void controlarRolesFaltantes() {
        List<SsRol> rolList = obtenerRoles();
        Map<String, SsRol> rolesMap = new HashMap<>();
        for (SsRol rol : rolList) {
            rolesMap.put(rol.getRolCod(), rol);
        }

        SsRol[] rolArr = new SsRol[]{
            new SsRol(null, SsRolCodigos.ADMINISTRADOR, "Usuario Administrador de la aplicacion", "rol_administrador", "SIGES_WEB", 0, true, false),
            new SsRol(null, SsRolCodigos.ADMIN_USU, "Administración de los Usuarios", "rol_admin_usu", "SIGES_WEB", 0, true, false),
            new SsRol(null, SsRolCodigos.DIRECTOR, "Director", "rol_dir", "SIGES_WEB", 0, true, true),
            new SsRol(null, SsRolCodigos.EDITOR_GRAL, "Editor General", "rol_editor_gral", "SIGES_WEB", 0, true, false),
            new SsRol(null, SsRolCodigos.MIGRACION, "Acceso a la migración", "rol_migracion", "SIGES_WEB", 0, true, false),
            new SsRol(null, SsRolCodigos.MIGRA_CALC_INDICA, "Acceso a los callculos de la migración", "rol_migra_calc_indica", "SIGES_WEB", 0, true, false),
            new SsRol(null, SsRolCodigos.PMO_FEDERADA, "PMO Federada", "rol_pmof", "SIGES_WEB", 0, true, true),
            new SsRol(null, SsRolCodigos.PMO_TRANSVERSAL, "PMO Transversal", "rol_pmot", "SIGES_WEB", 0, true, true),
            new SsRol(null, SsRolCodigos.REGISTRO_HORAS, "Externo (solo carga de horas)", "rol_registro_horas", "SIGES_WEB", 0, true, false),
            new SsRol(null, SsRolCodigos.USUARIO_COMUN, "Usuario", "rol_usu", "SIGES_WEB", 0, true, true)
        };

        for (int i = 0; i < rolArr.length; i++) {
            if (!rolesMap.containsKey(rolArr[i].getRolCod())) {
                guardar(rolArr[i]);
                logger.log(Level.INFO, StringsUtils.concat("Se agregó el rol '", rolArr[i].getRolCod()), "'");
            }
        }
    }
}