package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.codigueras.EstadosCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.tipos.IdNombreTO;
import com.sofis.entities.tipos.ProyectoTO;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ProyectosDAO extends HibernateJpaDAOImp<Proyectos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public ProyectosDAO(EntityManager em) {
        super(em);
    }

    public Proyectos detach(Proyectos proy) {
        //Recorrer las colecciones necesarias antes de hacer detach.
        if (proy.getProyPreFk() != null && proy.getProyPreFk().getAdquisicionSet() != null) {
            proy.getProyPreFk().getAdquisicionSet().isEmpty();
        }
        getEm().detach(proy);
        return proy;
    }

    public List<Proyectos> detach(List<Proyectos> listProy) {
        if (listProy != null) {
            List<Proyectos> result = new ArrayList<>();
            for (Proyectos proy : listProy) {
                proy = detach(proy);
                result.add(proy);
            }
            return result;
        }
        return listProy;
    }

    public void updateIndices(Integer proyPk, ProyIndices ind) {
        if (ind != null) {
            String queryStr = "UPDATE Proyectos p"
                    + " SET p.proyIndices = :ind"
                    + " WHERE p.proyPk = :proyPk";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("ind", ind);
            query.setParameter("proyPk", proyPk);
            query.executeUpdate();
        }
    }

    public List<Proyectos> obtenerProyHermanos(Integer proyPk) {
        if (proyPk != null) {
            String progKf = "SELECT y.proyProgFk.progPk FROM Proyectos y WHERE y.proyPk = :proyPk";
            Query query = getEm().createQuery(progKf);
            query.setParameter("proyPk", proyPk);
            Integer progFkId = (Integer) query.getSingleResult();
            if (progFkId != null) {
                String queryStr = "SELECT new Proyectos(p.proyPk, p.proyNombre,p.proyPeso,p.activo,p.proyEstFk) FROM Proyectos p"
                        + " WHERE p.proyProgFk.progPk = " + progFkId;
                query = getEm().createQuery(queryStr);
                return query.getResultList();
            } else {
                return new ArrayList();
            }
        }
        return null;
    }

    public List<Proyectos> obtenerProyComboPorOrg(int orgId) {
        String queryStr = "SELECT NEW Proyectos(p.proyPk, p.proyNombre)"
                + " FROM Proyectos p"
                + " WHERE p.proyOrgFk.orgPk = :orgId"
                + " AND p.activo != :activo"
                + " AND p.proyEstFk.estOrdenProceso IS NOT NULL"
                + " AND p.proyEstFk.estOrdenProceso > :iniciado"
                + " ORDER BY p.proyNombre";
        Query query = getEm().createQuery(queryStr);
        query.setParameter("orgId", orgId);
        query.setParameter("activo", Boolean.FALSE);
        query.setParameter("iniciado", 1);
        List<Proyectos> proyectosList = query.getResultList();

        return proyectosList;
    }

    /**
     * Retorna una lista de ID y nombre de los proyectos en la que el usuario
     * aportado es colaborador.
     *
     * @param usuId ID del usuario.
     * @param orgPk ID del Organismo.
     * @return lista de IdNombreTO conteniendo el ID y nombre de los proyectos.
     */
    public List<IdNombreTO> obtenerProyIdNombre(Integer usuId, Integer orgPk) {
        if (orgPk != null) {
            String queryStr = "SELECT NEW com.sofis.entities.tipos.IdNombreTO(p.proyPk, p.proyNombre)"
                    + " FROM Proyectos p, IN (p.participantesList) pa"
                    + " WHERE p.proyOrgFk.orgPk = :orgId"
                    + " AND p.activo != :proyActivo"
                    + " AND p.proyEstFk.estPk IN ("
                    + "SELECT e.estPk"
                    + " FROM Estados e"
                    + " WHERE e.estCodigo = :estCod1"
                    + " OR e.estCodigo = :estCod2"
                    + " OR e.estCodigo = :estCod3)"
                    + " AND (pa.partUsuarioFk.usuId = :usuId AND pa.partActivo = :partActivo)"
                    + " ORDER BY p.proyNombre";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("orgId", orgPk);
            query.setParameter("proyActivo", Boolean.FALSE);
            query.setParameter("estCod1", EstadosCodigos.INICIO);
            query.setParameter("estCod2", EstadosCodigos.PLANIFICACION);
            query.setParameter("estCod3", EstadosCodigos.EJECUCION);
            query.setParameter("usuId", usuId);
            query.setParameter("partActivo", Boolean.TRUE);

            List<IdNombreTO> proyectosList = query.getResultList();
            return proyectosList;
        }
        return null;
    }

    public boolean esColaboradorProy(Integer usuId, Integer proyPk) {
        if (proyPk != null && usuId != null) {
            String queryStr = "SELECT p.proyPk"
                    + " FROM Proyectos p, IN (p.participantesList) pa"
                    + " WHERE p.proyPk = :proyPk"
                    + " AND p.activo != :proyActivo"
                    + " AND (pa.partUsuarioFk.usuId = :usuId AND pa.partActivo = :partActivo)";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            query.setParameter("proyActivo", Boolean.FALSE);
            query.setParameter("usuId", usuId);
            query.setParameter("partActivo", Boolean.TRUE);

            List<Integer> proyectosList = query.getResultList();

            return proyectosList != null && proyectosList.size() == 1;
        }
        return false;
    }

    /**
     * Retorna true si el proyecto tiene solicitud de cambio de estado.
     * 22-05-17: tiene que filtrar los activos
     *
     * @param progPk
     * @return Boolean
     */
    public boolean cambioEstadoPorProg(Integer progPk) {
        if (progPk != null) {
//            String queryStr = "SELECT p.proyPk"
//                    + " FROM Proyectos p INNER JOIN p.proyEstPendienteFk estPend"
//                    + " WHERE p.proyProgFk.progPk = :progPk"
//                    + " AND p.proyEstPendienteFk IS NOT NULL"
//                    + " AND p.activo = TRUE";
            String queryStr = "SELECT p.proyPk"
                    + " FROM Proyectos p INNER JOIN p.proyEstPendienteFk estPend"
                    + " WHERE p.proyProgFk.progPk = :progPk"
                    + " AND estPend IS NOT NULL"
                    + " AND p.proyEstFk.estCodigo IN (:codigos)"
                    + " AND p.activo = TRUE";
            

            Query query = getEm().createQuery(queryStr);
            query.setParameter("progPk", progPk);
            query.setParameter("codigos", Arrays.asList("INICIO", "PLANIFICACION", "EJECUCION", "FINALIZADO"));
            
            return !query.getResultList().isEmpty();
        }
        return false;
    }

    public List<Proyectos> obtenerProyProProgPk(Integer progPk) {
        if (progPk != null) {
            String queryStr = "SELECT p FROM Proyectos p"
                    + " WHERE p.proyProgFk.progPk = :progPk";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("progPk", progPk);

            List<Proyectos> result = query.getResultList();
            return result;
        }
        return null;
    }

    /**
     * Retorna primer fecha de todos los proyectos.
     *
     * @return Date
     */
    public Date obtenerPrimeraFecha() {
        String sql = "SELECT proyind_periodo_inicio"
                + " FROM proy_indices"
                + " WHERE proyind_periodo_inicio IS NOT NULL"
                + " ORDER BY proyind_periodo_inicio ASC"
                + " LIMIT 1";
        Query query = getEm().createNativeQuery(sql);
        List result = query.getResultList();
        return (Date) DAOUtils.obtenerSingleResult(result);
    }

    /**
     * Retorna ultima fecha de todos los proyectos.
     *
     * @return Date
     */
    public Date obtenerUltimaFecha() {
        String sql = "SELECT proyind_periodo_fin"
                + " FROM proy_indices"
                + " WHERE proyind_periodo_fin IS NOT NULL"
                + " ORDER BY proyind_periodo_fin DESC"
                + " LIMIT 1";
        Query query = getEm().createNativeQuery(sql);
        List result = query.getResultList();
        return (Date) DAOUtils.obtenerSingleResult(result);
    }

    public Integer obtenerOrgPkPorProyPk(Integer proyPk) {
        String queryStr = "SELECT p.proyOrgFk.orgPk"
                + " FROM Proyectos p"
                + " WHERE p.proyPk = :proyPk";
        Query query = getEm().createQuery(queryStr);
        query.setParameter("proyPk", proyPk);

        List<Integer> result = query.getResultList();
        return (Integer) DAOUtils.obtenerSingleResult(result);
    }

    public ProyectoTO obtenerProyTO(Integer proyPk) {
        String queryStr = "SELECT NEW com.sofis.entities.tipos.ProyectoTO(p.proyPk, p.proyDescripcion, p.proyNombre)"
                + " FROM Proyectos p"
                + " WHERE p.proyPk = :proyPk";
        Query query = getEm().createQuery(queryStr);
        query.setParameter("proyPk", proyPk);

        List<ProyectoTO> result = query.getResultList();
        return (ProyectoTO) DAOUtils.obtenerSingleResult(result);
    }

    public List<Proyectos> obtenerProySimplePorProgId(Integer progPk) {
        String queryStr = "SELECT new Proyectos(p.proyPk, p.proyOrgFk, p.proyIndices, p.proyNombre, p.proyPeso, p.activo, p.proyEstFk, p.proyFechaAct)"
                + " FROM Proyectos p"
                + " WHERE p.proyProgFk.progPk = :progPk";
        Query query = getEm().createQuery(queryStr);
        query.setParameter("progPk", progPk);

        List<Proyectos> result = query.getResultList();
        return result;
    }

    public void actualizarFecha(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "UPDATE Proyectos p"
                    + " SET p.proyFechaAct = :dateNow"
                    + " WHERE p.proyPk = :proyPk";

            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            query.setParameter("dateNow", new Date());
            query.executeUpdate();
        }
    }
}
