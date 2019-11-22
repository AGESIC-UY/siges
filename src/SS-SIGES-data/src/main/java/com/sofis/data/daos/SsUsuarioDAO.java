package com.sofis.data.daos;

import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author SSGenerador
 */
public class SsUsuarioDAO extends HibernateJpaDAOImp<SsUsuario, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(SsUsuarioDAO.class.getName());
    
    public SsUsuarioDAO(EntityManager em) {
        super(em);
    }

    public List<SsUsuario> obtenerUsuariosPorRol(List<Integer> rolCodArr, Integer orgId, Boolean activos) {
        /*
        *   Se agrega este parámetro para poder tomar el correo electrónico del usuario al momento de requerir una solicitud de cambio de fase.
        */
        String queryStr = "SELECT DISTINCT new SsUsuario(s.usuId,s.usuFechaPassword,s.usuNroDoc,s.usuOrigen,s.usuPrimerApellido,s.usuSegundoApellido,s.usuPrimerNombre,s.usuSegundoNombre,s.usuUserCode,s.usuVigente,s.usuCorreoElectronico, b)"
                + " FROM SsUsuario s, IN(s.ssUsuOfiRolesCollection) b"
                + " WHERE ( ";
        int i = 0;
        for (Integer rolId : rolCodArr) {
            if (i == 0) {
                queryStr = queryStr + " b.usuOfiRolesRol.rolId = " + rolId;
            } else {
                queryStr = queryStr + " or b.usuOfiRolesRol.rolId = " + rolId;
            }
            i++;
        }
        queryStr = queryStr + " ) ";
        
        if (activos != null) {
            queryStr = queryStr + " AND s.usuVigente = :usuVigente"
                    + " AND b.usuOfiRolesOficina.ofiId = :org"
                    + " AND b.usuOfiRolesActivo = :activo"
                    + " order by s.usuPrimerNombre, s.usuSegundoNombre, s.usuPrimerApellido, s.usuSegundoApellido";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("usuVigente", activos);
            query.setParameter("org", orgId);
            query.setParameter("activo", activos);

            return query.getResultList();
        } else {
            queryStr = queryStr + " AND b.usuOfiRolesOficina.ofiId = :org"
                    + " order by s.usuPrimerNombre, s.usuSegundoNombre, s.usuPrimerApellido, s.usuSegundoApellido";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("org", orgId);
            return query.getResultList();
        }
    }

    public List<SsUsuario> obtenerTodosPorOrganismo(Integer orgId, Boolean activos) {

        if (activos != null) {
            String queryStr = "select distinct new SsUsuario( s.usuId,s.usuFechaPassword,s.usuNroDoc,s.usuOrigen,s.usuPrimerApellido,s.usuPrimerNombre,s.usuUserCode,s.usuVigente)"
                    + " from SsUsuario s, IN(s.ssUsuOfiRolesCollection) b"
                    + " where s.usuVigente = :usuVigente"
                    + " and  b.usuOfiRolesOficina.ofiId = :org"
                    + " and b.usuOfiRolesActivo = :activo"
                    + " order by s.usuPrimerNombre,s.usuPrimerApellido";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("usuVigente", activos);
            query.setParameter("org", orgId);
            query.setParameter("activo", activos);
            return query.getResultList();
        } else {
            String queryStr = "select distinct new SsUsuario( s.usuId,s.usuFechaPassword,s.usuNroDoc,s.usuOrigen,s.usuPrimerApellido,s.usuPrimerNombre,s.usuUserCode,s.usuVigente)"
                    + " from SsUsuario s, IN(s.ssUsuOfiRolesCollection) b"
                    + " where b.usuOfiRolesOficina.ofiId = :org"
                    + " order by s.usuPrimerNombre,s.usuPrimerApellido";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("org", orgId);
            return query.getResultList();
        }

    }

    @Deprecated
    public List<SsUsuario> obtenerHabilitados() throws DAOGeneralException {
        CodigueraDAO cdao = new CodigueraDAO(super.getEm());
        return cdao.obtenerHabilitados(SsUsuario.class, "usuVigente", "usuDescripcion");
    }

    public List<Organismos> obtenerOrganismosPermitidos(Integer usuPk) {
        if (usuPk != null) {
            String nativeQuery = "SELECT * FROM organismos"
                    + " WHERE org_pk = ("
                    + "SELECT usu_ofi_roles_oficina FROM ss_usu_ofi_roles"
                    + " WHERE usu_ofi_roles_usuario = :usuPk"
                    + " GROUP BY usu_ofi_roles_oficina)";
            Query query = super.getEm().createNativeQuery(nativeQuery);
            query.setParameter("usuPk", usuPk);

            List<Organismos> result = query.getResultList();
            return result;
        }
        return null;
    }

    public List<SsUsuario> obtenerSsUsuariosCoordEnt(Integer orgPk) {
        String queryStr = "SELECT e.coordinadorUsuFk"
                + " FROM Entregables e"
                + " WHERE e.entCroFk.proyecto.proyOrgFk.orgPk = :orgPk"
                + " AND e.coordinadorUsuFk.usuId IN ("
                + "SELECT uor.usuOfiRolesUsuario.usuId"
                + " FROM SsUsuOfiRoles uor"
                + " WHERE uor.usuOfiRolesOficina.ofiId = :orgPk"
                + " AND uor.usuOfiRolesActivo = :activo)"
//                + " GROUP BY e.coordinadorUsuFk" //Se comenta porque no funciona en Postgres
                + " ORDER BY e.coordinadorUsuFk.usuPrimerNombre, e.coordinadorUsuFk.usuPrimerApellido";

        Query query = getEm().createQuery(queryStr);
        query.setParameter("orgPk", orgPk);
        query.setParameter("activo", Boolean.TRUE);
        List<SsUsuario> result = query.getResultList();
        return result;
    }

    /**
     *
     * @param mail Correo electronico.
     * @param pass Password codificado en MD5.
     * @return boolean true si el usuario y password son correctos.
     */
    public boolean validarUsuarioPassword(String mail, String pass) {
        String queryStr = "SELECT u.usuId"
                + " FROM SsUsuario u"
                + " WHERE u.usuCorreoElectronico = :mail"
                + " AND u.usuPassword = :pass"
                + " AND u.usuVigente = :activo";

        Query query = getEm().createQuery(queryStr);
        query.setParameter("mail", mail);
        query.setParameter("pass", pass);
        query.setParameter("activo", Boolean.TRUE);
        List<SsUsuario> result = query.getResultList();

        return result != null && result.size() == 1;
    }
}
