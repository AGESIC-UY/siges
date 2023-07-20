package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.FuenteProcedimientoCompra;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.tipos.FiltroFuenteProcedimientoCompraTO;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class FuenteProcedimientoCompraDAO extends HibernateJpaDAOImp<FuenteProcedimientoCompra, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(FuenteProcedimientoCompraDAO.class.getName());

    public FuenteProcedimientoCompraDAO(EntityManager em) {
        super(em);
    }

    public List<FuenteProcedimientoCompra> obtenerPorFiltro(FiltroFuenteProcedimientoCompraTO filtro) throws DAOGeneralException {
        try {

            List<CriteriaTO> criterias = new ArrayList<CriteriaTO>();

            if (filtro.getId() != null) {
                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "fueProComPk", filtro.getId()));
            }

            if (!StringsUtils.isEmpty(filtro.getFuente())) {
//                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "cenCosNombre", filtro.getNombre()));
                CriteriaTO crit = DAOUtils.createMatchCriteriaTOString("fueProComFuente", filtro.getFuente());
                criterias.add(crit);
            }

            if (!StringsUtils.isEmpty(filtro.getProcedimientoCompra())) {
                CriteriaTO crit = DAOUtils.createMatchCriteriaTOString("fueProComProcedimientoCompra", filtro.getProcedimientoCompra());
                criterias.add(crit);
            }

            if (filtro.getHabilitado() != null) {
                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "fueProComHabilitado", filtro.getHabilitado()));
            }

            if (filtro.getOrganismo() != null) {
                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "fueProComOrgFk", filtro.getOrganismo()));
            }

            CriteriaTO criteriaTO;
            if (criterias.size() > 1) {
                criteriaTO = CriteriaTOUtils.createANDTO(criterias.toArray(new CriteriaTO[criterias.size()]));
            } else if (criterias.size() == 1) {
                criteriaTO = criterias.get(0);
            } else {
                criteriaTO = null;
            }

            String[] orderBy = {"fueProComFuente"};
            boolean[] ascending = {true};
            return this.findEntityByCriteria(FuenteProcedimientoCompra.class, criteriaTO, orderBy, ascending, null, filtro.getLargo() != null ? filtro.getLargo() : null);

        } catch (DAOGeneralException ex) {
            throw ex;
        }
    }

    public Boolean fuenteProcedimientoCompraEstaEnTabla(FuenteFinanciamiento fuente, ProcedimientoCompra procedimientoCompra) throws DAOGeneralException {
        String queryStr = "";
        queryStr = "SELECT 1 FROM FuenteProcedimientoCompra fpc"
                + " WHERE :fuente LIKE CONCAT(fpc.fueProComFuente, '%')"
                + " and :procedimientoCompra LIKE CONCAT(fpc.fueProComProcedimientoCompra, '%')"
                + " and fpc.fueProComHabilitado IS TRUE"
                + " and fpc.fueProComOrgFk = :organismo";

        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("fuente", fuente.getFueNombre());
        query.setParameter("procedimientoCompra", procedimientoCompra.getProcCompNombre());
        query.setParameter("organismo", procedimientoCompra.getProcCompOrgFk());
        query.setMaxResults(1);
        //    query.setParameter("fichaFk", fichaFk);
        List<FuenteProcedimientoCompra> result = null;
        try {
            result = query.getResultList();
            if (result != null && result.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.log(Level.SEVERE, queryStr, e);
            throw e;
        }
    }

    public FuenteProcedimientoCompra obtenerPorFuenteProcedimientoCompra(FuenteFinanciamiento fuente, ProcedimientoCompra procedimientoCompra) throws DAOGeneralException {

		return obtenerPorFuenteProcedimientoCompra(fuente.getFueNombre(), procedimientoCompra.getProcCompNombre(), fuente.getFueOrgFk().getOrgPk());
    }

	public FuenteProcedimientoCompra obtenerPorFuenteProcedimientoCompra(String nombreFuenteFinanciamiento, 
			String nombreProcedimientoCompra, Integer orgPk) throws DAOGeneralException {
	
		String queryStr = "SELECT fpc FROM FuenteProcedimientoCompra fpc"
                + " WHERE :fuente LIKE CONCAT(fpc.fueProComFuente, '%')"
                + " and :procedimientoCompra LIKE CONCAT(fpc.fueProComProcedimientoCompra, '%')"
                + " and fpc.fueProComHabilitado IS TRUE"
                + " and fpc.fueProComOrgFk.orgPk = :orgFk";
		
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("fuente", nombreFuenteFinanciamiento);
        query.setParameter("procedimientoCompra", nombreProcedimientoCompra);
        query.setParameter("orgFk", orgPk);
        query.setMaxResults(1);
		
        try {
            List<FuenteProcedimientoCompra> result = query.getResultList();
            if (result != null && result.size() > 0) {
                return result.get(0);
            }
            return null;
			
        } catch (Exception e) {
            logger.log(Level.SEVERE, queryStr, e);
            throw e;
        }
	}
}
