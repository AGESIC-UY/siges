package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.utils.DatosAuditoria;
import com.sofis.business.utils.Utils;
import com.sofis.business.validations.SsUsuarioValidacion;
import com.sofis.data.daos.SsUsuarioDAO;
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
import com.sofis.generico.utils.generalutils.EmailValidator;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.AND_TO;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.sofisform.to.OR_TO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
            logger.log(Level.INFO, "codigo usuario={0}, origen={1}", new Object[]{du.getCodigoUsuario(), du.getOrigen()});
            usuario = da.registrarDatosAuditoria(usuario, du.getCodigoUsuario(), du.getOrigen());
            SsUsuarioDAO dao = new SsUsuarioDAO(em);
            if (usuario.getUsuId() == null) {
                usuario = dao.create(usuario, du.getCodigoUsuario(), du.getOrigen());
            } else {
                usuario = dao.update(usuario, du.getCodigoUsuario(), du.getOrigen());
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
            TechnicalException ge = new TechnicalException();
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
            usuario.setUsuCuentaBloqueada((short) 0);
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
            if (listRoles.size() > 0
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
            usuario.setUsuCuentaBloqueada((short) 0);
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
            TechnicalException te = new TechnicalException();
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
            if (resultado == null || resultado.isEmpty()) {
                return null;
            } else if (resultado.size() == 1) {
                return resultado.get(0);
            } else {
                BusinessException be = new BusinessException();
                be.addError(ConstantesErrores.ERROR_DEMASIADOS_RESULTADOS);
                throw be;
            }
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException();
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
            TechnicalException te = new TechnicalException();
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
            TechnicalException te = new TechnicalException();
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
            TechnicalException te = new TechnicalException();
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
            TechnicalException te = new TechnicalException();
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
        SsUsuarioDAO dao = new SsUsuarioDAO(em);
        try {
            List<SsUsuario> resultado = dao.findByOneProperty(SsUsuario.class, "usuCorreoElectronico", mail);
            if (resultado == null || resultado.isEmpty()) {
                return null;
            } else if (resultado.size() == 1) {
                return resultado.get(0);
            } else {
                BusinessException be = new BusinessException();
                be.addError(ConstantesErrores.ERROR_DEMASIADOS_RESULTADOS);
                throw be;
            }
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, SsUsuarioBean.class.getName(), ex);
            BusinessException be = new BusinessException();
            be.setEx(ex);
            be.addError(MensajesNegocio.ERROR_USUARIO_OBTENER);
            throw be;
        }
    }

    public List<SsUsuario> obtenerUsuariosPorRol(String rolCod, Integer orgId, String[] orden, boolean[] ascending) {
        return obtenerUsuariosPorRol(new String[]{rolCod}, orgId, orden, ascending);
    }

    public List<SsUsuario> obtenerUsuariosPorRol(String[] rolCodArr, Integer orgId, String[] orden, boolean[] ascending) {
        SsUsuarioDAO dao = new SsUsuarioDAO(em);

        if (rolCodArr != null && rolCodArr.length > 0) {

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
            MatchCriteriaTO activoRol = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesActivo", Boolean.TRUE);
            MatchCriteriaTO activos = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "usuVigente", Boolean.TRUE);
            AND_TO andTO = CriteriaTOUtils.createANDTO(orgCriteria, rolCrit, activoRol, activos);

            if (orden == null || orden.length == 0) {
                orden = new String[]{"usuPrimerApellido", "usuPrimerNombre"};
                ascending = new boolean[]{true, true};
            }

            try {
                List<SsUsuario> usuarios = dao.findEntityByCriteria(SsUsuario.class, andTO, orden, ascending, null, null);
                return usuarios;
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, SsUsuarioBean.class.getName(), ex);
                TechnicalException te = new TechnicalException();
                te.addError(ex.getMessage());
                throw te;
            }
        }
        return null;
    }

    public List<SsUsuario> obtenerTodosPorOrganismo(Integer orgId) {
        if (orgId != null) {
            SsUsuarioDAO dao = new SsUsuarioDAO(em);
            try {
                MatchCriteriaTO orgCriteria = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesOficina.ofiId", orgId);
                MatchCriteriaTO activosOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "ssUsuOfiRolesCollection.usuOfiRolesActivo", Boolean.TRUE);
                MatchCriteriaTO activos = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "usuVigente", Boolean.TRUE);
                AND_TO criteria = CriteriaTOUtils.createANDTO(orgCriteria, activosOrg, activos);
                List<SsUsuario> usuarios = dao.findEntityByCriteria(SsUsuario.class, criteria, new String[]{"usuPrimerNombre", "usuPrimerApellido"}, new boolean[]{true, true}, null, null);
                return usuarios;
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, SsUsuarioBean.class.getName(), ex);
                TechnicalException te = new TechnicalException();
                te.addError(ex.getMessage());
                throw te;
            }
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
                TechnicalException te = new TechnicalException();
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

        if (usuario.getUsuId() == null && usu != null) {
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
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.CONTAINS, "usuCorreoElectronico",
                        mail.toUpperCase().trim());
                criterios.add(criterio);
            }

            if (!StringsUtils.isEmpty(nombre)) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.CONTAINS, "usuPrimerNombre",
                        nombre.toUpperCase().trim());
                criterios.add(criterio);
            }

            if (!StringsUtils.isEmpty(apellido)) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.CONTAINS, "usuPrimerApellido",
                        apellido.toUpperCase().trim());
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

    public boolean resetearContrasenia(final String email, final Integer orgPk) throws BusinessException {
        if (!StringsUtils.isEmpty(email)) {
            EmailValidator ev = new EmailValidator();
            if (ev.validate(email)) {
                final SsUsuario usu = obtenerSsUsuarioPorMail(email);
                if (usu != null) {
                    final String pass = Utils.generarContrasenia();
                    cambiarContrasenia(usu.getUsuCorreoElectronico(), pass, orgPk);
                    try {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mailBean.comunicarNuevaContrasenia(orgPk, usu.getUsuNombreApellido(), pass, email);
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
                    BusinessException be = new BusinessException();
//                    be.addError(MensajesNegocio.ERROR_RESET_PASS_USUARIO);
                    be.addError("No se encontró un usuario para la dirección de correo electrónico " + email);
                    throw be;
                }
            } else {
                BusinessException be = new BusinessException();
                be.addError("'" + email + "' no es una dirección de correo electrónico válida");
                throw be;
            }
        } else {
            BusinessException be = new BusinessException();
            be.addError("No se especificó una dirección de correo electrónico");
            throw be;
        }
    }

    /**
     * Retorna todos
     *
     * @return
     * @throws GeneralException
     */
    public List<SsUsuario> obtenerSsUsuariosCoordEnt(Integer orgPk) throws GeneralException {
        SsUsuarioDAO dao = new SsUsuarioDAO(em);
        return dao.obtenerSsUsuariosCoordEnt(orgPk);
    }
}
