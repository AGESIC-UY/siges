package com.sofis.data.daos;

import com.sofis.entities.data.Estados;
import com.sofis.entities.data.ProgIndices;
import com.sofis.entities.data.Programas;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ProgramasDAO extends HibernateJpaDAOImp<Programas, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ProgramasDAO(EntityManager em) {
        super(em);
    }

    public void updateIndices(Integer progPk, ProgIndices ind) {
        if (ind != null) {
            String queryStr = "UPDATE Programas p SET p.progIndices = :ind WHERE p.progPk = :progPk";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("ind", ind);
            query.setParameter("progPk", progPk);
            query.executeUpdate();
        }
    }

    public Integer calcularPesoPrograma(Integer progPk) {
        if (progPk == null) {
            return null;
        }
        String queryStr = "SELECT SUM(p.proyPeso) FROM Proyectos p WHERE p.proyProgFk.progPk=" + progPk + ""
                + " AND p.activo = true"
                + " AND p.proyPeso IS NOT NULL"
                + " AND  p.proyEstFk.estPk IN (" + Estados.ESTADOS.INICIO.estado_id + ","
                + Estados.ESTADOS.PLANIFICACION.estado_id + ","
                + Estados.ESTADOS.EJECUCION.estado_id + ","
                + Estados.ESTADOS.FINALIZADO.estado_id + ")";
        Query query = getEm().createQuery(queryStr);
        Long peso = (Long) query.getSingleResult();
        if (peso == null) {
            return null;
        }
        return peso.intValue();
    }

    public List<Programas> obtenerProgComboPorOrg(int orgId) {
        String queryStr = "SELECT NEW Programas(p.progPk, p.progNombre)"
                + " FROM Programas p"
                + " WHERE p.progOrgFk.orgPk = :orgId"
                + " AND p.activo != :activo"
                + " AND p.progEstFk.estOrdenProceso IS NOT NULL"
                + " AND p.progEstFk.estOrdenProceso > :iniciado"
                + " ORDER BY p.progNombre";
        Query query = getEm().createQuery(queryStr);
        query.setParameter("orgId", orgId);
        query.setParameter("activo", Boolean.FALSE);
        query.setParameter("iniciado", 1);
        List<Programas> programasList = query.getResultList();

        return programasList;
    }
}
