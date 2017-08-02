package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.utils.DatosAuditoria;
import com.sofis.business.utils.Utils;
import com.sofis.business.validations.SsUsuarioValidacion;
import com.sofis.data.daos.SsUsuarioDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.SsOficina;
import com.sofis.entities.data.SsRol;
import com.sofis.entities.data.SsUsuOfiRoles;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.MailException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.EmailValidator;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.AND_TO;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.sofisform.to.OR_TO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.ejb3.annotation.TransactionTimeout;

/**
 *
 * @author SSGenerador
 */
@Named
@Stateless(name = "SsUsuarioBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class SsUsuarioBean implements UsuarioLocal {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String HORA_EJECUCION = "0-23";
    private static final String MINUTOS_EJECUCION = "0";
    private static final long TRANSACTION_TIMEOUT_MINUTES = 30;

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private DatosUsuario du;
    @Inject
    private ConsultaHistorico<SsUsuario> ch;
    @Inject
    private MailBean mailBean;
    @Inject
    private SsRolBean ssRolBean;

    //private String usuario;
    //private String origen;

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    /**
     * Este método guarda un elemento de tipo SsUsuario. Se aplica para la
     * creación de la entidad y para la modificación de una entidad existente.
     *
     * @param usuario
     * @throws GeneralException Devuelve los códigos de error de la validación
     */
    private SsUsuario guardar(SsUsuario usuario, Integer orgPk) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), usuario);
        try {
            //Validar el elemento a guardar. Si no valida, se lanza una excepcion
            validarUsuarioDuplicado(usuario);
            SsUsuarioValidacion.validar(usuario, orgPk);

            DatosAuditoria da = new DatosAuditoria();
            logger.log(Level.INFO, "codigo usuario={0}, origen={1}", new Object[]{du.getCodigoUsuario(),du.getOrigen()});
            usuario = da.registrarDatosAuditoria(usuario, this.du.getCodigoUsuario(),du.getOrigen());
            SsUsuarioDAO dao = new SsUsuarioDAO(em);
            if (usuario.getUsuId() == null) {
                usuario = dao.create(usuario, this.du.getCodigoUsuario(),du.getOrigen());
            } else {
                usuario = dao.update(usuario, this.du.getCodigoUsuario(),du.getOrigen());
            }
            return usuario;
        } catch (BusinessException be) {
            //Si es de tipo negocio envía la misma excepción
            throw be;
        } catch (com.sofis.persistence.dao.exceptions.DAOGeneralException be) {
            BusinessException ge = new BusinessException();
            if (be.getCause() instanceof javax.persistence.PersistenceException) {
                if (be.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    ge.addError(ConstantesErrores.ERROR_CODIGO_REPETIDO);
                }
            }
            throw ge;
        } catch (Exception ex) {
            //Las demás excepciones se consideran técnicas
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public SsUsuario guardarUsuario(SsUsuario usuario, Integer orgPk) throws GeneralException {
        boolean usuNuevo = usuario.getUsuId() == null;
        String pass = null;
        if (usuNuevo) {
            pass = Utils.generarContrasenia();
            String passMD5 = Utils.md5(pass);

            usuario.setUsuPassword(passMD5);
            usuario.setUsuFechaPassword(new Date());
            usuario.setUsuCuentaBloqueada(false);
            usuario.setUsuVigente(true);
        }

        if (CollectionsUtils.isNotEmpty(usuario.getSsUsuOfiRolesCollectionActivos())) {
            usuario.setUsuVigente(true);
        }

        usuario = guardar(usuario, orgPk);

        if (usuario != null && usuNuevo) {
            try {
                mailBean.comunicarNuevoUsuario(orgPk, usuario.getUsuCorreoElectronico(), pass);
            } catch (MailException me) {
                logger.log(Level.WARNING, me.getMessage(), me);
                if (usuario.getUsuId() != null) {
                    me.addError(MensajesNegocio.INFO_USUARIO_GUARDADO);
                }
                throw me;
            }
        }

        Set<SsUsuOfiRoles> roles = usuario.getSsUsuOfiRolesCollectionActivos();
        if (roles != null) {
            List<SsUsuOfiRoles> listRoles = new ArrayList<>(roles);
            List<Integer> listOfId = new ArrayList<>();
            for (SsUsuOfiRoles ssUsuOfiRoles : listRoles) {
                listOfId.add(ssUsuOfiRoles.getUsuOfiRolesOficina().getOfiId());
            }
            if (!listRoles.isEmpty()
                    && listRoles.get(0).getUsuOfiRolesOficina() != null
                    && !listOfId.contains(usuario.getUsuOficinaPorDefecto())) {
                usuario.setUsuOficinaPorDefecto(listRoles.get(0).getUsuOfiRolesOficina().getOfiId());
            }
        }

        return usuario;
    }

    public SsUsuario guardarUsuario(SsUsuario usuario, String clave, Integer orgPk) throws GeneralException {
        boolean usuNuevo = false;
        if (usuario.getUsuId() == null) {
            usuario.setUsuFechaPassword(new Date());
            usuario.setUsuCuentaBloqueada(false);
            usuNuevo = true;
        }
        usuario = guardar(usuario, orgPk);

        if (usuario != null && !StringsUtils.isEmpty(clave) && usuNuevo) {
            mailBean.comunicarNuevoUsuario(orgPk, usuario.getUsuCorreoElectronico(), clave);
        }

        return usuario;
    }

    /**
     * Devuelve el elemento configuracion por el ID
     *
     * @param id
     * @return
     * @throws GeneralException
     */
    public SsUsuario obtenerSsUsuarioPorId(Integer id) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), id);
        SsUsuarioDAO tpdDao = new SsUsuarioDAO(em);
        try {
            return tpdDao.findById(SsUsuario.class, id);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    /**
     * Devuelve el elemento de configuracion según el código Si no hay ningún
     * elemento con ese código devuelve null
     *
     * @param codigo
     * @return
     * @throws GeneralException
     */
    @Override
    public SsUsuario obtenerSsUsuarioPorCodigo(String codigo) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), codigo);
        SsUsuarioDAO dao = new SsUsuarioDAO(em);
        try {
            List<SsUsuario> resultado = dao.findByOneProperty(SsUsuario.class, "usuCod", codigo);
            return (SsUsuario) DAOUtils.obtenerSingleResult(resultado);
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
    public List<SsUsuario> obtenerTodos() throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), "");

        SsUsuarioDAO dao = new SsUsuarioDAO(em);
        try {
            return dao.findAll(SsUsuario.class);
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
    public List<SsUsuario> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
        logger.log(Level.INFO, this.getClass().getName(), "");

        SsUsuarioDAO dao = new SsUsuarioDAO(em);
        try {
            return dao.findEntityByCriteria(SsUsuario.class, cto, orderBy, ascending, startPosition, cantidad);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    @Deprecated
    public List<SsUsuario> obtenerHabilitados() {
        SsUsuarioDAO genDao = new SsUsuarioDAO(em);
        try {
            List<SsUsuario> list = genDao.obtenerHabilitados();
            return list;
        } catch (Exception ex) {
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public SsUsuario cambiarContrasenia(String codigoSsUsuario, String contraseniaActual, String nuevaContrasenia, String confirmacionContrasenia, Integer orgPk) throws GeneralException {
        SsUsuario usu = obtenerSsUsuarioPorMail(codigoSsUsuario);

        SsUsuarioValidacion.validarCambioPass(usu, contraseniaActual, nuevaContrasenia, confirmacionContrasenia);

        String pass = Utils.md5(nuevaContrasenia);
        usu.setUsuPassword(pass);
        usu.setUsuFechaPassword(new Date());
        return guardar(usu, orgPk);
    }

    public void cambiarContrasenia(String usuarioMail, String nuevaContrasenia, Integer orgPk) throws GeneralException {
        SsUsuario usu = obtenerSsUsuarioPorMail(usuarioMail);
        String pass = Utils.md5(nuevaContrasenia);
        usu.setUsuPassword(pass);
        usu.setUsuFechaPassword(new Date());
        guardar(usu, orgPk);
    }

    public List<Organismos> obtenerOrganismos(Integer usuPk) {
        try {
            SsUsuarioDAO dao = new SsUsuarioDAO(em);
            List<Organismos> list = dao.obtenerOrganismosPermitidos(usuPk);
            return list;

        } catch (Exception ex) {
            logger.log(Level.SEVERE, SsUsuarioBean.class.getName(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    /**
     * Devuelve el usuario con el mail aportado.
     *
     * @param mail
     * @return
     * @throws GeneralException
     */
    @Override
    public SsUsuario obtenerSsUsuarioPorMail(String mail) throws GeneralException {
        logger.log(Level.FINEST, "obtenerSsUsuarioPorMail...");
        if (!StringsUtils.isEmpty(mail)) {
            mail = mail.toLowerCase();
            SsUsuarioDAO dao = new SsUsuarioDAO(em);
            try {
                List<SsUsuario> resultado = dao.findByOneProperty(SsUsuario.class, "usuCorreoElectronico", mail);
                return (SsUsuario) DAOUtils.obtenerSingleResult(resultado);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, SsUsuarioBean.class.getName(), ex);
                BusinessException be = new BusinessException(ex);
                be.addError(MensajesNegocio.ERROR_USUARIO_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public List<SsUsuario> obtenerUsuariosPorRol(String rolCod, Integer orgId, String[] orden, boolean[] ascending) {
        return obtenerUsuariosPorRol(new String[]{rolCod}, orgId, orden, ascending);
    }

    public List<SsUsuario> obtenerUsuariosPorRol(String[] rolCodArr, Integer orgId, String[] orden, boolean[] ascending) {
        return obtenerUsuariosPorRol(rolCodArr, orgId, orden, ascending, Boolean.TRUE);
    }

    public List<SsUsuario> obtenerUsuariosPorRol(String rolCodArr, Integer orgId, String[] orden, boolean[] ascending, Boolean activo) {
        return obtenerUsuariosPorRol(new String[]{rolCodArr}, orgId, orden, ascending, activo);
    }

    /**
     * Retorna los usuarios del organismo aportado y de acuerdo a los roles
     * aportados. Especificar "activos" en null para obtener todos los activos y
     * los inactivos.
     *
     * @param rolCodArr
     * @param orgId
     * @param orden
     * @param ascending
     * @param activo
     * @return
     */
    public List<SsUsuario> obtenerUsuariosPorRol(String[] rolCodArr, Integer orgId, String[] orden, boolean[] ascending, Boolean activo) {
        SsUsuarioDAO dao = new SsUsuarioDAO(em);

        List<Integer> rolIds = new ArrayList();
        if (rolCodArr != null && rolCodArr.length > 0) {
            for (String rolCod : rolCodArr) {
                SsRol rol = ssRolBean.obtenerRolPorCod(rolCod);
                Integer rolId = rol.getRolId();
                rolIds.add(rolId);
            }

            try {
                return dao.obtenerUsuariosPorRol(rolIds, orgId, activo);
            } catch (Exception ex) {
                logger.log(Level.SEVERE, SsUsuarioBean.class.getName(), ex);
                TechnicalException te = new TechnicalException(ex);
                te.addError(ex.getMessage());
                throw te;
            }
        }

        return null;
        /*if (rolCodArr != null && rolCodArr.length > 0) {

         List<CriteriaTO> listCriteriaRol = new ArrayList<>();
         for (String rolCod : rolCodArr) {
         SsRol rol = ssRolBean.obtenerRolPorCod(rolCod);
         Integer rolId = rol.getRolId();
         MatchCriteriaTO porRol = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesRol.rolId", rolId);
         listCriteriaRol.add(porRol);
         }
         CriteriaTO[] arrCriteriaTo = listCriteriaRol.toArray(new CriteriaTO[listCriteriaRol.size()]);
         CriteriaTO rolCrit = arrCriteriaTo.length > 1 ? CriteriaTOUtils.createORTO(arrCriteriaTo) : arrCriteriaTo[0];

         MatchCriteriaTO orgCriteria = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesOficina.ofiId", orgId);

         CriteriaTO criteria;
         if (activo != null) {
         MatchCriteriaTO activoRol = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesActivo", activo);
         MatchCriteriaTO activos = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "usuVigente", activo);
         criteria = CriteriaTOUtils.createANDTO(orgCriteria, rolCrit, activoRol, activos);
         } else {
         criteria = CriteriaTOUtils.createANDTO(orgCriteria, rolCrit);
         }

         if (orden == null || orden.length == 0) {
         orden = new String[]{"usuPrimerApellido", "usuPrimerNombre"};
         ascending = new boolean[]{true, true};
         }

         try {
         List<SsUsuario> usuarios = dao.findEntityByCriteria(SsUsuario.class, criteria, orden, ascending, null, null);
         return usuarios;
         } catch (DAOGeneralException ex) {
         logger.log(Level.SEVERE, SsUsuarioBean.class.getName(), ex);
         TechnicalException te = new TechnicalException(ex);
         te.addError(ex.getMessage());
         throw te;
         }
         }
         return null;
         */

    }

    public List<SsUsuario> obtenerTodosPorOrganismo(Integer orgId) {
        return obtenerTodosPorOrganismo(orgId, Boolean.TRUE);
    }

    /**
     * Retorna todos los usuarios asociados al organismo aportado. Especificar
     * "activos" en null para obtener todos los activos y los inactivos.
     *
     * @param orgId
     * @param activos
     * @return
     */
    public List<SsUsuario> obtenerTodosPorOrganismo(Integer orgId, Boolean activos) {
        if (orgId != null) {
            SsUsuarioDAO dao = new SsUsuarioDAO(em);
            try {
                List<SsUsuario> usuarios = dao.obtenerTodosPorOrganismo(orgId, activos);
                return usuarios;
            } catch (Exception ex) {
                logger.log(Level.SEVERE, SsUsuarioBean.class.getName(), ex);
                TechnicalException te = new TechnicalException(ex);
                te.addError(ex.getMessage());
                throw te;
            }
            /*try {
             MatchCriteriaTO orgCriteria = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesOficina.ofiId", orgId);
             CriteriaTO criteria;
             if (activos != null) {
             MatchCriteriaTO activosOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesActivo", activos);
             MatchCriteriaTO vigente = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "usuVigente", activos);
             criteria = CriteriaTOUtils.createANDTO(orgCriteria, activosOrg, vigente);
             } else {
             criteria = orgCriteria;
             }

             List<SsUsuario> usuarios = dao.findEntityByCriteria(SsUsuario.class, criteria, new String[]{"usuPrimerNombre", "usuPrimerApellido"}, new boolean[]{true, true}, null, null);
             return usuarios;
             } catch (DAOGeneralException ex) {
             logger.log(Level.SEVERE, SsUsuarioBean.class.getName(), ex);
             TechnicalException te = new TechnicalException(ex);
             te.addError(ex.getMessage());
             throw te;
             }*/
        }
        return null;
    }

    public List<SsUsuario> obtenerInactivosPorOrganismo(Integer orgId) {
        if (orgId != null) {
            SsUsuarioDAO dao = new SsUsuarioDAO(em);
            try {
                MatchCriteriaTO orgCriteria = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesOficina.ofiId", orgId);
                MatchCriteriaTO activosOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesActivo", Boolean.FALSE);
                MatchCriteriaTO activos = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "usuVigente", Boolean.FALSE);
                OR_TO inactivos = CriteriaTOUtils.createORTO(activosOrg, activos);
                AND_TO criteria = CriteriaTOUtils.createANDTO(orgCriteria, inactivos);
                List<SsUsuario> usuarios = dao.findEntityByCriteria(SsUsuario.class, criteria, new String[]{"usuPrimerNombre", "usuPrimerApellido"}, new boolean[]{true, true}, null, null);
                return usuarios;
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, SsUsuarioBean.class.getName(), ex);
                TechnicalException te = new TechnicalException(ex);
                te.addError(ex.getMessage());
                throw te;
            }
        }
        return null;
    }

    public String obtenerTodosPorOrgCronograma(Integer orgId) {
        if (orgId != null) {
            List<SsUsuario> usuarios = obtenerTodosPorOrganismo(orgId);
            if (usuarios != null && !usuarios.isEmpty()) {
                StringBuffer result = new StringBuffer();
                result.append("[");
                boolean primero = true;
                for (SsUsuario u : usuarios) {
                    if (!primero) {
                        result = result.append(",");
                    }
                    result = result.append("\"")
                            .append(u.getUsuId().toString())
                            .append(":")
                            .append(u.getUsuNombreApellido())
                            .append("\"");
                    primero = false;
                }
                result = result.append("]");
                return result.toString();
            }
        }
        return null;
    }

    /**
     * Agrega un rol a la collección de roles de dicho usuario. Si ya tine roles
     * de tipo usuario, los elimina.
     *
     * @param usuario
     * @param rol
     * @param org
     */
    public void agregarRolUsuario(SsUsuario usuario, SsRol rol, Organismos org) {
        List<SsUsuOfiRoles> toRemove = new ArrayList<>();
        if (usuario.getSsUsuOfiRolesCollection() != null) {
            if (rol.isRolTipoUsuario()) {
                for (SsUsuOfiRoles ssUsuOfiRoles : usuario.getSsUsuOfiRolesCollection()) {
                    if (ssUsuOfiRoles.getUsuOfiRolesRol() != null
                            && ssUsuOfiRoles.getUsuOfiRolesRol().getRolTipoUsuario() != null
                            && ssUsuOfiRoles.getUsuOfiRolesRol().getRolTipoUsuario()
                            && ssUsuOfiRoles.getUsuOfiRolesOficina().getOfiId().equals(org.getOrgPk())) {
                        toRemove.add(ssUsuOfiRoles);
                    }
                }
                usuario.getSsUsuOfiRolesCollection().removeAll(toRemove);
            }
        } else {
            usuario.setSsUsuOfiRolesCollection(new ArrayList<SsUsuOfiRoles>());
        }

        SsUsuOfiRoles ofiRoles = new SsUsuOfiRoles();
        ofiRoles.setUsuOfiRolesOficina(new SsOficina(org.getOrgPk()));
        ofiRoles.setUsuOfiRolesRol(rol);
        ofiRoles.setUsuOfiRolesUsuario(usuario);
        ofiRoles.setUsuOfiRolesOrigen(org.getOrgNombre());
        usuario.getSsUsuOfiRolesCollection().add(ofiRoles);
    }

    public SsUsuario removerRolUsuario(SsUsuario usuario, SsRol rol, Organismos org) {
        if (usuario != null && rol != null && org != null
                && usuario.getSsUsuOfiRolesCollection() != null) {
            List<SsUsuOfiRoles> toRemove = new ArrayList<>();
            for (SsUsuOfiRoles ssUsuOfiRoles : usuario.getSsUsuOfiRolesCollection()) {
                if (ssUsuOfiRoles.getUsuOfiRolesRol() != null
                        && ssUsuOfiRoles.getUsuOfiRolesRol().equals(rol)
                        && ssUsuOfiRoles.getUsuOfiRolesOficina().getOfiId().equals(org.getOrgPk())) {
                    toRemove.add(ssUsuOfiRoles);
                }
            }
            usuario.getSsUsuOfiRolesCollection().removeAll(toRemove);
        }
        return usuario;
    }

    private boolean validarUsuarioDuplicado(SsUsuario usuario) {
        SsUsuario usu = obtenerSsUsuarioPorMail(usuario.getUsuCorreoElectronico());

        if (usu != null && (usuario.getUsuId() == null
                || (usuario.getUsuId() != null
                && !usuario.getUsuId().equals(usu.getUsuId()))
                && usuario.getUsuCorreoElectronico().equalsIgnoreCase(usu.getUsuCorreoElectronico()))) {
            BusinessException ge = new BusinessException();
            ge.addError(MensajesNegocio.ERROR_USUARIO_DUPLICADO);
            throw ge;
        }
        return true;
    }

    public List<SsUsuario> busquedaUsuFiltro(Organismos org, SsUsuario usu, String mail, String nombre, String apellido) {
        return busquedaUsuFiltro(org, usu, mail, nombre, apellido, null);
    }

    public List<SsUsuario> busquedaUsuFiltro(Organismos org, SsUsuario usu, String mail, String nombre, String apellido, Boolean activo) {
        boolean isUsuAdmin = usu != null ? usu.isAdministrador() : false;
        if (org != null || isUsuAdmin) {
            List<CriteriaTO> criterios = new ArrayList<>();

            if (!isUsuAdmin) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesOficina.ofiId",
                        org.getOrgPk());
                criterios.add(criterio);
            } else {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.NOT_EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesRol.rolId", 0);
                criterios.add(criterio);
            }

            if (!StringsUtils.isEmpty(mail)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "usuCorreoElectronico", mail.toUpperCase().trim());
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("usuCorreoElectronico", mail.toUpperCase().trim());
                criterios.add(criterio);
            }

            if (!StringsUtils.isEmpty(nombre)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "usuPrimerNombre", nombre.toUpperCase().trim());
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("usuPrimerNombre", nombre.toUpperCase().trim());
                criterios.add(criterio);
            }

            if (!StringsUtils.isEmpty(apellido)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "usuPrimerApellido", apellido.toUpperCase().trim());
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("usuPrimerApellido", apellido.toUpperCase().trim());
                criterios.add(criterio);
            }

            if (activo != null) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesActivo", activo);
                criterios.add(criterio);
            }

            CriteriaTO condicion;
            if (!criterios.isEmpty()) {
                if (criterios.size() == 1) {
                    condicion = criterios.get(0);
                } else {
                    condicion = CriteriaTOUtils.createANDTO(criterios
                            .toArray(new CriteriaTO[0]));
                }
            } else {
                // condicion dummy para que el count by criteria funcione
                condicion = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.NOT_NULL, "usuCorreoElectronico", 1);
            }
            String[] orderBy = {"usuCorreoElectronico"};
            boolean[] asc = {true};

            SsUsuarioDAO dao = new SsUsuarioDAO(em);
            try {
                return dao.findEntityByCriteria(SsUsuario.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
            return null;

        } else {
            throw new BusinessException("warn_usuario_sin_organismo");
        }
    }

    public boolean resetearContrasenia(final String email, Integer orgPk) throws BusinessException {
        if (!StringsUtils.isEmpty(email)) {
            EmailValidator ev = new EmailValidator();
            if (ev.validate(email)) {
                final SsUsuario usu = obtenerSsUsuarioPorMail(email);
                if (usu != null) {
                    if (orgPk == null) {
                        Set<SsUsuOfiRoles> setUsuRoles = usu.getSsUsuOfiRolesCollectionActivos();
                        if (setUsuRoles != null && !setUsuRoles.isEmpty()) {
                            orgPk = setUsuRoles.iterator().next().getUsuOfiRolesOficina().getOfiId();
                        }
                    }

                    final String pass = Utils.generarContrasenia();
                    cambiarContrasenia(usu.getUsuCorreoElectronico(), pass, orgPk);
                    try {
                        final Integer orgPkFinal = orgPk;
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean enviado = mailBean.comunicarNuevaContrasenia(orgPkFinal, usu.getUsuNombreApellido(), pass, email);
                                if (!enviado) {
                                    logger.log(Level.WARNING, "Falló el envío de la nueva contraseña a:" + email);
                                }
                            }
                        });
                        t.start();

                    } catch (MailException me) {
                        logger.log(Level.WARNING, "*** ERROR_MAIL_ENVIO");
                        logger.log(Level.WARNING, me.getMessage(), me);
                        BusinessException be = new BusinessException();
                        be.addError(MensajesNegocio.ERROR_MAIL_ENVIO);
                        throw be;
                    } catch (Exception ex) {
                        logger.log(Level.WARNING, "*** ERROR_MAIL_ENVIO - Exception");
                        logger.log(Level.WARNING, ex.getMessage(), ex);
                    }
                    return true;
                } else {
                    logger.log(Level.WARNING, "No se encontró un usuario para la dirección de correo electrónico " + email);
                    BusinessException be = new BusinessException();
//                    be.addError(MensajesNegocio.ERROR_RESET_PASS_USUARIO);
                    be.addError("No se encontró un usuario para la dirección de correo electrónico " + email);
                    throw be;
                }
            } else {
                logger.log(Level.WARNING, "'" + email + "' no es una dirección de correo electrónico válida");
                BusinessException be = new BusinessException();
                be.addError("'" + email + "' no es una dirección de correo electrónico válida");
                throw be;
            }
        } else {
            logger.log(Level.WARNING, "No se especificó una dirección de correo electrónico");
            BusinessException be = new BusinessException();
            be.addError("No se especificó una dirección de correo electrónico");
            throw be;
        }
    }

    /**
     * Retorna todos los coordinadores activos para dicho organismo.
     *
     * @return
     * @throws GeneralException
     */
    public List<SsUsuario> obtenerSsUsuariosCoordEnt(Integer orgPk) throws GeneralException {
        SsUsuarioDAO dao = new SsUsuarioDAO(em);
        List<SsUsuario> listUsu = dao.obtenerSsUsuariosCoordEnt(orgPk);
        List<SsUsuario> result = new ArrayList<>();
        for (SsUsuario usu : listUsu) {
            if (!result.contains(usu)) {
                result.add(usu);
            }
        }
        return result;
    }

    public boolean validarUsuarioPassword(String mail, String pass) {
        logger.log(Level.FINEST, "validarUsuarioPassword...");
        if (mail != null && pass != null) {
            String passEncoding = Utils.md5(pass);

            SsUsuarioDAO dao = new SsUsuarioDAO(em);
            return dao.validarUsuarioPassword(mail, passEncoding);
        }
        return false;
    }

    /**
     *
     * @param mail
     * @param pass
     * @param orgPk
     * @return Token identificando al usuario, o "0" en caso de error.
     */
    public String validarUsuarioToToken(String mail, String pass) {
        logger.log(Level.FINEST, "validarUsuarioToToken...");
        if (this.validarUsuarioPassword(mail, pass)) {
            SsUsuario usu = this.obtenerSsUsuarioPorMail(mail);
            usu.setUsuToken(Utils.generarTokenUUID());
            usu.setUsuTokenAct(new Date());
            try {
                usu = this.guardar(usu, null);
                return usu.getUsuToken();
            } catch (GeneralException ge) {
                logger.log(Level.SEVERE, null, ge);
            }
        }
        return null;
    }

    public SsUsuario obtenerUsuarioPorToken(String token) {
        if (!StringsUtils.isEmpty(token)) {
            SsUsuarioDAO dao = new SsUsuarioDAO(em);
            try {
                List<SsUsuario> result = dao.findByOneProperty(SsUsuario.class, "usuToken", token);
                return (SsUsuario) DAOUtils.obtenerSingleResult(result);
            } catch (DAOGeneralException ge) {
                logger.log(Level.SEVERE, null, ge);
            }
        }
        return null;
    }

    @Schedule(hour = HORA_EJECUCION, minute = MINUTOS_EJECUCION, info = "Eliminar tokens vencidos de usuarios.")
    @TransactionTimeout(value = TRANSACTION_TIMEOUT_MINUTES, unit = TimeUnit.MINUTES)
    public void scheduleLimpiarTokens() {
        limpiarTokens(360);
    }

    /**
     * Recorre los usuarios con tokens y borra los token que pasaron las horas
     * aportadas.
     *
     * @param horas Las horas que deben de pasar para eliminar el token.
     */
    public void limpiarTokens(Integer horas) {
        SsUsuarioDAO dao = new SsUsuarioDAO(em);
        List<SsUsuario> listUsu = null;

        CriteriaTO condicion = CriteriaTOUtils.createMatchCriteriaTO(
                MatchCriteriaTO.types.NOT_NULL, "usuToken", 1);

        try {
            String[] orderBy = new String[]{};
            boolean[] ascending = new boolean[]{};
            listUsu = dao.findEntityByCriteria(SsUsuario.class, condicion, orderBy, ascending, null, null);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        if (CollectionsUtils.isNotEmpty(listUsu)) {
            Calendar cal = new GregorianCalendar();
            cal.set(Calendar.HOUR_OF_DAY, -horas);
            Calendar calToken = new GregorianCalendar();
            for (SsUsuario usu : listUsu) {
                try {
                    Date d = usu.getUsuTokenAct();
                    calToken.setTime(d);
                    if (calToken != null
                            && calToken.before(cal)) {
                        usu.setUsuToken(null);
                        usu.setUsuTokenAct(null);
                        this.guardar(usu, null);
                    }
                } catch (Exception e) {
                    logger.log(Level.WARNING, null, e);
                }
            }
        }
    }

    public boolean eliminarToken(String token) {
        logger.log(Level.FINEST, "eliminarToken...");
        if (!StringsUtils.isEmpty(token)) {

            SsUsuario usu = obtenerUsuarioPorToken(token);
            if (usu != null) {
                usu.setUsuToken(null);
                usu.setUsuTokenAct(null);
                return true;
            }
        }
        return false;
    }
}
