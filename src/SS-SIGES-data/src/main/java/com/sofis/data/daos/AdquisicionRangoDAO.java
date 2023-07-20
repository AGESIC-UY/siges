package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.AdquisicionRango;
import com.sofis.entities.data.AdquisicionRangoAux;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AdquisicionRangoDAO extends HibernateJpaDAOImp<AdquisicionRango, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AdquisicionRangoDAO.class.getName());

    public AdquisicionRangoDAO(EntityManager em) {
        super(em);
    }

    public List<AdquisicionRango> obtenerAdquisicionRangoPorArea(Integer orgPk, Integer areaPk) throws DAOGeneralException {
        String query = "SELECT a"
                + " FROM AdquisicionRango a"
                + " WHERE a.adrOrganismo.orgPk = :prePk"
                + " AND a.adrArea.areaPk = :monPk"
                + " ORDER BY a.adrDesde";

        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", orgPk);
        q.setParameter("monPk", areaPk);

        List<AdquisicionRango> adqList = q.getResultList();
        return adqList;
    }

    public List<AdquisicionRango> obtenerAdquisicionRangoPorAreaDivison(Integer orgPk, Integer areaPk, Integer divisionPk) throws DAOGeneralException {

       // System.out.println(orgPk + " " + areaPk + " " + divisionPk);
        String query = "SELECT a"
                + " FROM AdquisicionRango a"
                + " WHERE a.adrOrganismo.orgPk = :prePk"
                + " AND a.adrArea.areaPk = :monPk AND a.adrDivision.objEstPk = :divPk "
                + " ORDER BY a.adrDesde";

        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", orgPk);
        q.setParameter("monPk", areaPk);
        q.setParameter("divPk", divisionPk);

        List<AdquisicionRango> adqList = q.getResultList();
        return adqList;
    }

    public List<AdquisicionRango> obtenerAdquisicionRangoPorDivision(Integer orgPk, Integer divisionPk) throws DAOGeneralException {
        String query = "SELECT a"
                + " FROM AdquisicionRango a"
                + " WHERE a.adrOrganismo.orgPk = :prePk"
                + " AND a.adrDivision.objEstPk = :monPk"
                + " ORDER BY a.adrDesde";

        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", orgPk);
        q.setParameter("monPk", divisionPk);

        List<AdquisicionRango> adqList = q.getResultList();
        return adqList;
    }

    public List<AdquisicionRango> obtenerAdquisicionRangoPorAreaDivisionNull(Integer orgPk, Integer areaPk) {
        String query = "SELECT a"
                + " FROM AdquisicionRango a"
                + " WHERE a.adrOrganismo.orgPk = :prePk"
                + " AND a.adrArea.areaPk = :monPk AND a.adrDivision.objEstPk is Null"
                + " ORDER BY a.adrDesde";

        Query q = super.getEm().createQuery(query);
        q.setParameter("prePk", orgPk);
        q.setParameter("monPk", areaPk);

        List<AdquisicionRango> adqList = q.getResultList();
        return adqList;
    }

    public boolean existeRango(Integer orgPk, Integer desde, Integer hasta, Integer pk) throws DAOGeneralException {

     //   System.out.println("existe rango");

        String queryStr = "select count(*) as cant from AdquisicionRango a "
                + " where (:desde between a.adrDesde and  a.adrHasta "
                + " or :hasta between a.adrDesde and a.adrHasta) and a.adrOrganismo.orgPk= :orgPk ";

        if (pk > 0) {

            queryStr = queryStr.concat(" and a.adrPk  != :pk");

        }

        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("desde", desde);
        query.setParameter("hasta", hasta);
        query.setParameter("orgPk", orgPk);

        if (pk > 0) {
            query.setParameter("pk", pk);
        }

        try {

           
            Long cant = (Long) query.getSingleResult();
            if (cant > 0) {
                return true;
            }
            return false;
        } catch (Exception w) {
            LOGGER.log(Level.SEVERE, w.getMessage(), w);
        }

        return false;
    }

    public List<AdquisicionRango> listarParaChequeo() {
        String query = "SELECT a"
                + " FROM AdquisicionRango a"
                + " WHERE adrChequear=true";

        Query q = super.getEm().createQuery(query);

        List<AdquisicionRango> adqList = q.getResultList();
        return adqList;
    }

    public BigInteger siguienteDisponible(Integer minimo, Integer maximo, Integer orgaPk) {
        String sql = "select id from adquisiciones_rangos_aux ara where not exists (select 1 from  adquisicion ad \n"
                + "join presupuesto p on p.pre_pk =ad.adq_pre_fk \n"
                + "join proyectos p2 on p2.proy_pre_fk  = p.pre_pk  \n"
                + "join organismos o on o.org_pk =p2.proy_org_fk \n"
                + "where ad.adq_id_adquisicion =ara.id and o.org_pk="+orgaPk+") and id BETWEEN " + minimo + " and " + maximo + " limit 1";
        
        Query query = getEm().createNativeQuery(sql);
        List result = query.getResultList();
        return (BigInteger) DAOUtils.obtenerSingleResult(result);
    }

}
