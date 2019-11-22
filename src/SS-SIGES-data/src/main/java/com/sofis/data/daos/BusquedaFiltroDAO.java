package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.BusquedaFiltro;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.SsUsuario;
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
public class BusquedaFiltroDAO extends HibernateJpaDAOImp<BusquedaFiltro, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(BusquedaFiltroDAO.class.getName());            

    public BusquedaFiltroDAO(EntityManager em) {
        super(em);
    }

    public BusquedaFiltro obtenerFiltroPorUsuOrg(SsUsuario usuario, Organismos organismo) {
        if (usuario != null && organismo != null) {
            String queryStr = "SELECT f FROM BusquedaFiltro f"
                    + " WHERE f.busqFiltroUsuarioFk.usuId = :usuId"
                    + " AND f.busqFiltroOrgaFk.orgPk = :orgPk";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("usuId", usuario.getUsuId());
            query.setParameter("orgPk", organismo.getOrgPk());
            List<BusquedaFiltro> result = query.getResultList();
            if (result != null && !result.isEmpty()) {
                return (BusquedaFiltro) result.get(0);
            }
            return null;
        }
        return null;
    }
}
