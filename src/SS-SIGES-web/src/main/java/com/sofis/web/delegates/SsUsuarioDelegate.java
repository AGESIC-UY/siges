package com.sofis.web.delegates;

import com.sofis.business.ejbs.SsUsuarioBean;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.SsRol;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author SSGenerador
 */
public class SsUsuarioDelegate {

    @Inject
    private SsUsuarioBean ssUsuarioBean;

    public SsUsuario guardarUsuario(SsUsuario tdp, String clave, Integer orgPk) throws GeneralException {
        //System.out.println("guardarUsuario");
        return ssUsuarioBean.guardarUsuario(tdp, clave, orgPk);
    }

    public SsUsuario guardarUsuario(SsUsuario tdp, Integer orgPk) throws GeneralException {
        //System.out.println("guardarUsuario");
        return ssUsuarioBean.guardarUsuario(tdp, orgPk);
    }

    public SsUsuario obtenerSsUsuarioPorId(Integer id) throws GeneralException {
       // System.out.println("obtenerSsUsuarioPorId");
        return ssUsuarioBean.obtenerSsUsuarioPorId(id);
    }

    public SsUsuario obtenerSsUsuarioPorCodigo(String codigo) throws GeneralException {
        //System.out.println("obtenerSsUsuarioPorCodigo");
        return ssUsuarioBean.obtenerSsUsuarioPorCodigo(codigo);
    }

    public SsUsuario obtenerSsUsuarioPorMail(String mail) throws GeneralException {
        //System.out.println("obtenerSsUsuarioPorMail");
        return ssUsuarioBean.obtenerSsUsuarioPorMail(mail);
    }
	
	public SsUsuario obtenerUsuarioPorLDAPUser(String codigoUsuario) {
        return ssUsuarioBean.obtenerSsUsuarioPorLDAPUser(codigoUsuario);
    }

    public List<SsUsuario> obtenerTodos() throws GeneralException {
        //System.out.println("obtenerTodos");
        return ssUsuarioBean.obtenerTodos();
    }

    public List<SsUsuario> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
       // System.out.println("obtenerPorCriteria");
        return ssUsuarioBean.obtenerPorCriteria(cto, orderBy, ascending, startPosition, cantidad);
    }

    public SsUsuario cambiarContrasenia(String codigoUsuario, String contraseniaActual, String nuevaContrasenia, String confirmacionContrasenia, Integer orgPk) throws GeneralException {
        return ssUsuarioBean.cambiarContrasenia(codigoUsuario, contraseniaActual, nuevaContrasenia, confirmacionContrasenia, orgPk);
    }

    public List<Organismos> obtenerOrganismos(Integer usuPk) {
        //System.out.println("obtenerOrganismos");
        return ssUsuarioBean.obtenerOrganismos(usuPk);
    }

    public SsUsuario obtenerUsuarioPorCodigo(String codigoUsuario) {
        //System.out.println("obtenerUsuarioPorCodigo");
        return ssUsuarioBean.obtenerSsUsuarioPorCodigo(codigoUsuario);
    }

    public SsUsuario obtenerUsuarioPorMail(String codigoUsuario) {
        //System.out.println("obtenerUsuarioPorMail");
        return ssUsuarioBean.obtenerSsUsuarioPorMail(codigoUsuario);
    }

    public List<SsUsuario> obtenerUsuariosPorRol(String rolCod, Integer organismoId, String[] orden, boolean[] ascending) {
       // System.out.println("obtenerUsuariosPorRol");
        return ssUsuarioBean.obtenerUsuariosPorRol(rolCod, organismoId, orden, ascending);
    }

    public List<SsUsuario> obtenerUsuariosPorRol(String[] rolCod, Integer organismoId, String[] orden, boolean[] ascending) {
        //System.out.println("obtenerUsuariosPorRol 2");
        return ssUsuarioBean.obtenerUsuariosPorRol(rolCod, organismoId, orden, ascending);
    }
    
    public List<SsUsuario> obtenerUsuariosPorRol(String rolCodArr, Integer orgId, String[] orden, boolean[] ascending, Boolean activo) {
        //System.out.println("obtenerUsuariosPorRol 1");
        return ssUsuarioBean.obtenerUsuariosPorRol(rolCodArr, orgId, orden, ascending, activo);
    }

    public List<SsUsuario> obtenerUsuariosPorRol(String[] rolCodArr, Integer orgId, String[] orden, boolean[] ascending, Boolean activo) {
        //System.out.println("obtenerUsuariosPorRol 3");
        return ssUsuarioBean.obtenerUsuariosPorRol(rolCodArr, orgId, orden, ascending, activo);
    }

    public void cargarRolUsuario(SsUsuario usuario, SsRol rol, Organismos org) {
        //System.out.println("cargarRolUsuario");
        ssUsuarioBean.agregarRolUsuario(usuario, rol, org);
    }

    public void removerRolUsuario(SsUsuario usuario, SsRol rol, Organismos org) {
        ssUsuarioBean.removerRolUsuario(usuario, rol, org);
    }

    public List<SsUsuario> obtenerTodosPorOrganismo(Integer orgId) {
        //System.out.println("obtenerTodosPorOrganismo");
        return ssUsuarioBean.obtenerTodosPorOrganismo(orgId);
    }

    public List<SsUsuario> obtenerTodosPorOrganismo(Integer orgId, Boolean activos) {
        //System.out.println("obtenerTodosPorOrganismo");
        return ssUsuarioBean.obtenerTodosPorOrganismo(orgId, activos);
    }

    public List<SsUsuario> obtenerInactivosPorOrganismo(Integer orgId) {
        //System.out.println("obtenerInactivosPorOrganismo");
        return ssUsuarioBean.obtenerInactivosPorOrganismo(orgId);
    }

    public String obtenerTodosPorOrgCronograma(Integer orgId) {
        //System.out.println("obtenerTodosPorOrgCronograma");
        return ssUsuarioBean.obtenerTodosPorOrgCronograma(orgId);
    }

    public List<SsUsuario> busquedaUsuFiltro(Organismos org, SsUsuario usu, String mail, String nombre, String apellido) {
        //System.out.println("busquedaUsuFiltro");
        return ssUsuarioBean.busquedaUsuFiltro(org, usu, mail, nombre, apellido);
    }

    public List<SsUsuario> busquedaUsuFiltro(Organismos org, SsUsuario usu, String mail, String nombre, String apellido, Boolean activo) {
       // System.out.println("busquedaUsuFiltro");
        return ssUsuarioBean.busquedaUsuFiltro(org, usu, mail, nombre, apellido, activo);
    }

    public boolean resetearContrasenia(String email, Integer orgPk) throws BusinessException {
        return ssUsuarioBean.resetearContrasenia(email, orgPk);
    }

    public List<SsUsuario> obtenerSsUsuariosCoordEnt(Integer orgPk) throws GeneralException {
        return ssUsuarioBean.obtenerSsUsuariosCoordEnt(orgPk);
    }
    
    public String validarUsuarioToToken(String mail, String pass) {
        return ssUsuarioBean.validarUsuarioToToken(mail, pass);
    }

    public SsUsuario obtenerUsuarioPorToken(String token) {
        //System.out.println("obtenerUsuarioPorToken");
        return ssUsuarioBean.obtenerUsuarioPorToken(token);
    }
    
    public boolean eliminarToken(String token) {
        return ssUsuarioBean.eliminarToken(token);
    }
}
