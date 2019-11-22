package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.ProgIndices;
import com.sofis.entities.data.Programas;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ProgramasDAO extends HibernateJpaDAOImp<Programas, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ProgramasDAO.class.getName());        

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
            String queryStr = "SELECT SUM(p.proyPeso)"
                            + " FROM Proyectos p"
                            + " WHERE p.proyProgFk.progPk = " + progPk + ""
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

    public List<Programas> obtenerProgComboPorOrg(int orgId, Boolean habilitado) {
            String queryStr = "SELECT NEW Programas(p.progPk, p.progNombre)"
                            + " FROM Programas p"
                            + " WHERE p.progOrgFk.orgPk = :orgId"
                            + " AND p.activo != :activo"
                            + " AND p.progHabilitado = :habilitado"
                            + " AND p.progEstPendienteFk IS NULL"
                            + " ORDER BY p.progNombre";

            Query query = getEm().createQuery(queryStr);
            query.setParameter("orgId", orgId);
            query.setParameter("activo", Boolean.FALSE);
            query.setParameter("habilitado", habilitado);
            List<Programas> programasList = query.getResultList();

            return programasList;
    }

    public Date obtenerMenorFechaActProy(Integer progPk) {
            if (progPk == null) {
                    return null;
            }
            String queryStr = "SELECT p.proyFechaAct FROM Proyectos p"
                            + " WHERE p.proyProgFk.progPk = :progPk"
                            + " AND p.activo = :activo"
                            + " AND  p.proyEstFk.estPk IN ("
                            + Estados.ESTADOS.INICIO.estado_id + ","
                            + Estados.ESTADOS.PLANIFICACION.estado_id + ","
                            + Estados.ESTADOS.EJECUCION.estado_id + ")"
                            + " ORDER BY p.proyFechaAct ASC";

            Query query = getEm().createQuery(queryStr);
            query.setParameter("progPk", progPk);
            query.setParameter("activo", Boolean.TRUE);
            query.setMaxResults(1);

            List<Date> result = query.getResultList();
            return (Date) DAOUtils.obtenerSingleResult(result);
    }

    public Estados obtenerMenorEstadoProy(Integer progPk) {
            if (progPk == null) {
                    return null;
            }
            String queryStr = "SELECT p.proyEstFk FROM Proyectos p"
                            + " WHERE p.proyProgFk.progPk = " + progPk + ""
                            + " AND p.activo = true"
                            + " AND  p.proyEstFk.estPk IN ("
                            + Estados.ESTADOS.PENDIENTE_PMOF.estado_id + ","
                            + Estados.ESTADOS.PENDIENTE_PMOT.estado_id + ","
                            + Estados.ESTADOS.PENDIENTE.estado_id + ","
                            + Estados.ESTADOS.INICIO.estado_id + ","
                            + Estados.ESTADOS.PLANIFICACION.estado_id + ","
                            + Estados.ESTADOS.EJECUCION.estado_id + ","
                            + Estados.ESTADOS.FINALIZADO.estado_id + ")"
                            + " ORDER BY p.proyEstFk.estOrdenProceso ASC";

            Query query = getEm().createQuery(queryStr);
            query.setMaxResults(1);

            List<Estados> result = query.getResultList();
            return (Estados) DAOUtils.obtenerSingleResult(result);
    }

    public Date obtenerPrimeraFecha(Integer progPk) throws DAOGeneralException {
            String queryStr = "SELECT min(p.proyIndices.proyindPeriodoInicio) FROM Proyectos p "
                            + " WHERE p.proyProgFk.progPk = " + progPk + ""
                            + " AND p.activo = true";
            Query query = getEm().createQuery(queryStr);
            return (Date) query.getSingleResult();
    }

    public Date obtenerUltimaFecha(Integer progPk) throws DAOGeneralException {
            String queryStr = "SELECT max(p.proyIndices.proyindPeriodoFin) FROM Proyectos p "
                            + " WHERE p.proyProgFk.progPk = " + progPk + ""
                            + " AND p.activo = true";
            Query query = getEm().createQuery(queryStr);
            return (Date) query.getSingleResult();
    }

    public List<Integer> obtenerTodosIdsOrgFk(Integer orgFk) throws DAOGeneralException {
            String queryStr = "SELECT p.progPk"
                            + " FROM Programas p"
                            + " WHERE p.progOrgFk.orgPk = :orgFk"
                            + " AND p.activo = :activo";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("orgFk", orgFk);
            query.setParameter("activo", Boolean.TRUE);
            List<Integer> programasIdsList = query.getResultList();
            return programasIdsList == null ? new ArrayList<Integer>() : programasIdsList;

    }

    public List<Programas> obtenerProgramas(String codigo, String nombre, Integer orgPk) throws DAOGeneralException{

            try {
                    Query query = em.createNamedQuery("Programas.findByProgPkAndProgNombre");
                    query.setParameter("codigo", "%" + codigo + "%");
                    query.setParameter("nombre", "%" + nombre + "%");
                    query.setParameter("org", orgPk);
                    List<Integer> ids = query.getResultList();
                    if(ids == null || ids.isEmpty()){
                            return new ArrayList<>();
                    }
                    Query queryRes = em.createNamedQuery("Programas.findByIds");
                    queryRes.setParameter("ids", ids);
                    return queryRes.getResultList();
            } catch (Exception ex) {
                    throw new DAOGeneralException(ex);
            }
    }
    
    public Date obtenerFechaActualización(Integer progPk) throws DAOGeneralException{
        String queryStr = "SELECT min(p.proyFechaAct) FROM Proyectos p "
                        + " WHERE p.proyProgFk.progPk = " + progPk + ""
                        + " AND p.activo = true"
                        + " AND p.proyEstFk.estNombre <> 'Finalizado'";
        Query query = getEm().createQuery(queryStr);
        return (Date) query.getSingleResult();        
    }
    
    
    
    public int[] obtenerProcentajeIndiceFinalizado(Integer progPk) throws DAOGeneralException{
        int[] retorno = {0, 0, 0};
        
        String queryStr = "SELECT SUM(proyInd.proyindAvanceFinAzul*proy.proyPeso)/SUM(proy.proyPeso)," +
                        " SUM(proyInd.proyindAvanceFinVerde*proy.proyPeso)/SUM(proy.proyPeso)," +
                        " SUM(proyInd.proyindAvanceFinRojo*proy.proyPeso)/SUM(proy.proyPeso)" +
                        " FROM Proyectos proy" +
                        " INNER JOIN proy.proyProgFk prog" +
                        " INNER JOIN proy.proyIndices proyInd" +
                        " WHERE prog.progPk = " + progPk + " AND proy.activo = TRUE ";
        
        Query query = getEm().createQuery(queryStr);
        Object[] results = (Object[]) query.getSingleResult();

        
        for(int i=0; i<3 ;i++){
            if(results[i] != null){
                retorno[i] = ((Long) results[i]).intValue();
            }
        }

        return retorno;
    }
    
    public int[] obtenerProcentajeIndiceParcial(Integer progPk) throws DAOGeneralException{
        int[] retorno = {0, 0, 0};
        
        String queryStr = "SELECT SUM(proyInd.proyindAvanceParAzul*proy.proyPeso)/SUM(proy.proyPeso)," +
                        " SUM(proyInd.proyindAvanceParVerde*proy.proyPeso)/SUM(proy.proyPeso)," +
                        " SUM(proyInd.proyindAvanceParRojo*proy.proyPeso)/SUM(proy.proyPeso)" +
                        " FROM Proyectos proy" +
                        " INNER JOIN proy.proyProgFk prog" +
                        " INNER JOIN proy.proyIndices proyInd" +
                        " WHERE prog.progPk = " + progPk + " AND proy.activo = TRUE ";
        
        Query query = getEm().createQuery(queryStr);
        Object[] results = (Object[]) query.getSingleResult();
        
        for(int i=0; i<3 ;i++){
            if(results[i] != null){
                retorno[i] = ((Long) results[i]).intValue();
            }
        }

        return retorno;
    }

}
