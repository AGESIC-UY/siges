package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProdMes;
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
public class ProdMesDAO extends HibernateJpaDAOImp<ProdMes, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ProdMesDAO.class.getName());     

    public ProdMesDAO(EntityManager em) {
        super(em);
    }

    public List<ProdMes> obtenerOrdenadoPorFecha(Integer prodPk) {
        if (prodPk != null) {
            String quer = "SELECT pm FROM ProdMes pm"
                    + " WHERE prodmesProdFk.prodPk = :prodPk"
                    + " ORDER BY prodmesAnio ASC, prodmesMes ASC";
            Query q = super.getEm().createQuery(quer);
            q.setParameter("prodPk", prodPk);

            List<ProdMes> prodMesList = q.getResultList();
            return prodMesList;
        }
        return null;
    }
}
