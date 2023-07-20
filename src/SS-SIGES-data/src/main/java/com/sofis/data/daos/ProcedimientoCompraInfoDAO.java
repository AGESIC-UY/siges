package com.sofis.data.daos;

import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.data.ProcedimientoCompraInfo;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ProcedimientoCompraInfoDAO extends HibernateJpaDAOImp<ProcedimientoCompraInfo, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ProcedimientoCompraInfoDAO.class.getName());         

    public ProcedimientoCompraInfoDAO(EntityManager em) {
            super(em);
    }

    @SuppressWarnings("unchecked")
    public List<ProcedimientoCompra> obtenerHabilitadosPorOrganismo(Integer orgPk) throws DAOGeneralException {
            
        String query = "SELECT procComp"
        + " FROM ProcedimientoCompra procComp"
        + " WHERE procComp.procCompOrgFk.orgPk = :orgPk"
        + " AND procComp.procCompHabilitado = :procCompHab"
        + " ORDER BY procComp.procCompNombre";

        Query q = super.getEm().createQuery(query);
        q.setParameter("orgPk", orgPk);
        q.setParameter("procCompHab", Boolean.TRUE);

        List<ProcedimientoCompra> retorno = (List<ProcedimientoCompra>) q.getResultList();
        
        return retorno;
    }
    
}
