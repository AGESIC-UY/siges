package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProdMes;
import com.sofis.entities.data.Productos;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ProductosDAO extends HibernateJpaDAOImp<Productos, Integer> implements Serializable {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final long serialVersionUID = 1L;

    public ProductosDAO(EntityManager em) {
        super(em);
    }

    
    public Double obtenerTotalAcuPlan(Integer prodId){
        
        String quer = "SELECT p.prodmesAcuPlan"
                    + " FROM ProdMes p "
                    + " WHERE p.prodmesProdFk.prodPk = :prodId"
                    + " ORDER BY p.prodmesAnio desc, p.prodmesMes desc";
            Query q = super.getEm().createQuery(quer);
            q.setParameter("prodId", prodId);
            q.setMaxResults(1);
            List<Double> productosList = q.getResultList();
            
            if (productosList.size() > 0){
                return (Double)productosList.get(0);
            }
            
            
            return null;
    }
    
    public List<Productos> obtenerProdPorProyPk(Integer proyPk) {
        if (proyPk != null) {
            String quer = "SELECT d"
                    + " FROM Proyectos p,"
                    + " IN(p.proyCroFk.entregablesSet) e,"
                    + " IN(e.entProductosSet) d"
                    + " WHERE p.proyPk = :proyPk"
                    + " ORDER BY e.entId";
            Query q = super.getEm().createQuery(quer);
            q.setParameter("proyPk", proyPk);

            List<Productos> productosList = q.getResultList();
            return productosList;
        }
        return null;
    }

    public List<Productos> obtenerProdPorEnt(Integer entPk) {
        if (entPk != null) {
            String quer = "SELECT d"
                    + " FROM Proyectos p,"
                    + " IN(p.proyCroFk.entregablesSet) e,"
                    + " IN(e.entProductosSet) d"
                    + " WHERE e.entPk = :entPk";
            Query q = super.getEm().createQuery(quer);
            q.setParameter("entPk", entPk);

            List<Productos> productosList = q.getResultList();
            return productosList;
        }
        return null;
    }

    public Calendar obtenerPrimeraFechaProd(Integer prodPk) {
        if (prodPk != null) {
            String quer = "SELECT * FROM prod_mes"
                    + " WHERE prodmes_prod_fk = :prodPk"
                    + " ORDER BY prodmes_prod_fk ASC, prodmes_anio ASC, prodmes_mes ASC";
            Query q = super.getEm().createNativeQuery(quer);
            q.setParameter("prodPk", prodPk);
            q.setMaxResults(1);

            List<ProdMes> prodMesList = q.getResultList();
            if (prodMesList == null) {
                return null;
            } else {
                ProdMes prodMes = prodMesList.get(0);
                Calendar cal = new GregorianCalendar();
                cal.set(prodMes.getProdmesAnio(), prodMes.getProdmesMes(), 1);
                return cal;
            }
        }
        return null;
    }

    public Calendar obtenerUltimaFechaProd(Integer prodPk) {
        if (prodPk != null) {
            String quer = "SELECT * FROM prod_mes"
                    + " WHERE prodmes_prod_fk = :prodPk"
                    + " ORDER BY prodmes_prod_fk ASC, prodmes_anio DESC, prodmes_mes DESC";
            Query q = super.getEm().createNativeQuery(quer);
            q.setParameter("prodPk", prodPk);
            q.setMaxResults(1);

            List<ProdMes> prodMesList = q.getResultList();
            if (prodMesList == null) {
                return null;
            } else {
                ProdMes prodMes = prodMesList.get(0);
                Calendar cal = new GregorianCalendar();
                cal.set(prodMes.getProdmesAnio(), prodMes.getProdmesMes(), 1);
                return cal;
            }
        }
        return null;
    }
}
