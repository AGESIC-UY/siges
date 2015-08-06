package com.sofis.web.mb;

import com.sofis.entities.codigueras.SsRolCodigos;
import java.io.Serializable;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "menuMB")
@SessionScoped
public class MenuMB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private InicioMB inicioMB;
    private HashMap<String, Boolean> permisos = new HashMap<>();

    /**
     * Creates a new instance of MenuMB
     */
    public MenuMB() {
    }

    @PostConstruct
    public void init() {
        if ((permisos == null || permisos.isEmpty()) && inicioMB.getUsuario() != null) {
//            Collection<SsUsuOfiRoles> usuOfiRoles = inicioMB.getUsuario().getSsUsuOfiRolesCollection();
//            Set<SsUsuOfiRoles> usuOfiRoles = inicioMB.getUsuario().getSsUsuOfiRolesCollection();
//            for (SsUsuOfiRoles usuOfiRol : usuOfiRoles) {
//                permisos.put(usuOfiRol.getUsuOfiRolesRol().getRolCod(), Boolean.TRUE);
//            }

            Integer orgPk = inicioMB.getOrganismo() != null ? inicioMB.getOrganismo().getOrgPk() : null;
            boolean isAdmin = inicioMB.getUsuario().isAdministrador();
            boolean isRolAdmin = inicioMB.getUsuario().isRol(SsRolCodigos.ADMINISTRADOR, orgPk);

            boolean isDirector = inicioMB.getUsuario().isUsuarioDirector(orgPk);
            boolean isPMOF = inicioMB.getUsuario().isUsuarioPMOF(orgPk);
            boolean isPMOT = inicioMB.getUsuario().isUsuarioPMOT(orgPk);
            boolean isComun = inicioMB.getUsuario().isUsuarioComun(orgPk);
            boolean isRegHoras = inicioMB.getUsuario().isRol(SsRolCodigos.REGISTRO_HORAS, orgPk);
            boolean hasRol = (isDirector || isPMOF || isPMOT || isComun || isRegHoras);

            permisos.put("ADMINIS", isPMOT);
            permisos.put("ADMINISTRADOR", isAdmin || isRolAdmin);
            permisos.put("INICIO", !isRegHoras && (isDirector || isPMOF || isPMOT || isComun));
            permisos.put("PAGINA_INICIO_CLIENTE", (isDirector || isPMOF || isPMOT || isComun));
            permisos.put("ADMIN_NUEVOFICHA", hasRol && !(isRolAdmin || isRegHoras) ? Boolean.TRUE : Boolean.FALSE);
            permisos.put("MIGRACION", isPMOT);
            permisos.put("MIGRA_CALC_INDICA", isPMOT);
            permisos.put("LECC_APRENDIDAS", hasRol && !(isRolAdmin || isRegHoras));
            permisos.put("REPORTE_PROYECTO", (isDirector || isPMOF || isPMOT || isComun));
            permisos.put("REPORTE_PROGRAMA", (isDirector || isPMOF || isPMOT || isComun));
            permisos.put("REPORTE_CRONOGRAMA", (isDirector || isPMOF || isPMOT || isComun));
            permisos.put("REGISTRO_HORAS", hasRol);
            permisos.put("PLANTILLA_CRONOGRAMA", isPMOT);
            
            permisos.put("REPORTE_PRESUPUESTO", (isDirector || isPMOF || isPMOT || isComun));
            permisos.put("REPORTE_CRONOGRAMA_ALCANCE", (isDirector || isPMOF || isPMOT || isComun));
//            permisos.put("REPORTE_MIS_TAREAS", (isDirector || isPMOF || isPMOT || isComun));
            permisos.put("REPORTE_MIS_TAREAS", false);
            permisos.put("REPORTE_MENU", (permisos.get("REPORTE_PRESUPUESTO") || permisos.get("REPORTE_CRONOGRAMA_ALCANCE") || permisos.get("REPORTE_MIS_TAREAS")));

            permisos.put("ADM_CONF", isPMOT);
            permisos.put("CONF_ADD", isPMOT);
            permisos.put("CONF_EDIT", isPMOT);
            permisos.put("CONF_HIST", isPMOT);

            permisos.put("ADMIN_TDO", false);
            permisos.put("ADMIN_ERR", false);
            permisos.put("ADMIN_TEL", false);

            permisos.put("ADMIN_USU", isRolAdmin || isAdmin || isPMOT);
            permisos.put("ADMIN_PERSONAS", isPMOT);
            permisos.put("ADMIN_TIPO_DOC", isPMOT);
            permisos.put("ADMIN_AREA_TEMATICA", isPMOT);
            permisos.put("ADMIN_AMBITO", isPMOT);
            permisos.put("ADMIN_ORGANIZACION", isPMOT);
            permisos.put("ADMIN_AREA_CONOCIMIENTO", isPMOT);
            permisos.put("ADMIN_AREAS", isPMOT);
            permisos.put("ADMIN_FUENTE_FINANC", isPMOT);
            permisos.put("ADMIN_MAIL_TEMPLATE", isPMOT);
            permisos.put("ADMIN_MONEDA", isPMOT);
            permisos.put("ADMIN_NOTIFICACION", isPMOT);
            permisos.put("ADMIN_ROLES_INTERESADOS", isPMOT);
            permisos.put("ADMIN_TIPO_GASTOS", isPMOT);
            permisos.put("ADMIN_TEMA_CALIDAD", isPMOT);
        }
    }

    public String fireAction(String codigo) {
        return codigo;
    }

    public HashMap<String, Boolean> getPermisos() {
        return permisos;
    }

    public void setPermisos(HashMap<String, Boolean> permisos) {
        this.permisos = permisos;
    }
}
