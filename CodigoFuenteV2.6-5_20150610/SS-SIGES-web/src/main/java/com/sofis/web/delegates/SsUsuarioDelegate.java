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
        return ssUsuarioBean.guardarUsuario(tdp, clave, orgPk);
    }

    public SsUsuario guardarUsuario(SsUsuario tdp, Integer orgPk) throws GeneralException {
        return ssUsuarioBean.guardarUsuario(tdp, orgPk);
    }

    public SsUsuario obtenerSsUsuarioPorId(Integer id) throws GeneralException {
        return ssUsuarioBean.obtenerSsUsuarioPorId(id);
    }

    public SsUsuario obtenerSsUsuarioPorCodigo(String codigo) throws GeneralException {
        return ssUsuarioBean.obtenerSsUsuarioPorCodigo(codigo);
    }

    public SsUsuario obtenerSsUsuarioPorMail(String mail) throws GeneralException {
        return ssUsuarioBean.obtenerSsUsuarioPorMail(mail);
    }

    public List<SsUsuario> obtenerTodos() throws GeneralException {
        return ssUsuarioBean.obtenerTodos();
    }

    public List<SsUsuario> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
        return ssUsuarioBean.obtenerPorCriteria(cto, orderBy, ascending, startPosition, cantidad);
    }

    public SsUsuario cambiarContrasenia(String codigoUsuario, String contraseniaActual, String nuevaContrasenia, String confirmacionContrasenia, Integer orgPk) throws GeneralException {
        return ssUsuarioBean.cambiarContrasenia(codigoUsuario, contraseniaActual, nuevaContrasenia, confirmacionContrasenia, orgPk);
    }

    public List<Organismos> obtenerOrganismos(Integer usuPk) {
        return ssUsuarioBean.obtenerOrganismos(usuPk);
    }

    public SsUsuario obtenerUsuarioPorCodigo(String codigoUsuario) {
        return ssUsuarioBean.obtenerSsUsuarioPorCodigo(codigoUsuario);
    }

    public SsUsuario obtenerUsuarioPorMail(String codigoUsuario) {
        return ssUsuarioBean.obtenerSsUsuarioPorMail(codigoUsuario);
    }

    public List<SsUsuario> obtenerUsuariosPorRol(String rolCod, Integer organismoId, String[] orden, boolean[] ascending) {
        return ssUsuarioBean.obtenerUsuariosPorRol(rolCod, organismoId, orden, ascending);
    }

    public List<SsUsuario> obtenerUsuariosPorRol(String[] rolCod, Integer organismoId, String[] orden, boolean[] ascending) {
        return ssUsuarioBean.obtenerUsuariosPorRol(rolCod, organismoId, orden, ascending);
    }

    public void cargarRolUsuario(SsUsuario usuario, SsRol rol, Organismos org) {
        ssUsuarioBean.agregarRolUsuario(usuario, rol, org);
    }

    public void removerRolUsuario(SsUsuario usuario, SsRol rol, Organismos org) {
        ssUsuarioBean.removerRolUsuario(usuario, rol, org);
    }

    public List<SsUsuario> obtenerTodosPorOrganismo(Integer orgId) {
        return ssUsuarioBean.obtenerTodosPorOrganismo(orgId);
    }
    
    public List<SsUsuario> obtenerInactivosPorOrganismo(Integer orgId) {
        return ssUsuarioBean.obtenerInactivosPorOrganismo(orgId);
    }

    public String obtenerTodosPorOrgCronograma(Integer orgId) {
        return ssUsuarioBean.obtenerTodosPorOrgCronograma(orgId);
    }

    public List<SsUsuario> busquedaUsuFiltro(Organismos org, SsUsuario usu, String mail, String nombre, String apellido) {
        return ssUsuarioBean.busquedaUsuFiltro(org, usu, mail, nombre, apellido);
    }

    public List<SsUsuario> busquedaUsuFiltro(Organismos org, SsUsuario usu, String mail, String nombre, String apellido, Boolean activo) {
        return ssUsuarioBean.busquedaUsuFiltro(org, usu, mail, nombre, apellido, activo);
    }

    public boolean resetearContrasenia(String email, Integer orgPk) throws BusinessException {
        return ssUsuarioBean.resetearContrasenia(email, orgPk);
    }

    public List<SsUsuario> obtenerSsUsuariosCoordEnt(Integer orgPk) throws GeneralException {
        return ssUsuarioBean.obtenerSsUsuariosCoordEnt(orgPk);
    }
}
