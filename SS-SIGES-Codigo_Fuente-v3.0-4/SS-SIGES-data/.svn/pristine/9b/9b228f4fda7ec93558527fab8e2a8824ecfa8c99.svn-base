package com.sofis.data.daos;

import com.sofis.entities.constantes.ValorCalidadCodigosConstantes;
import com.sofis.entities.data.Calidad;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class CalidadDAO extends HibernateJpaDAOImp<Calidad, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public CalidadDAO(EntityManager em) {
        super(em);
    }

    public List<Calidad> obtenerPendientesCalidad(Integer proyPk, int size) {
        if (proyPk != null) {
            String queryStr = "SELECT c.cal_pk as calPk"
                    + " FROM calidad c"
                    + " LEFT JOIN entregables e ON e.ent_pk = c.cal_ent_fk"
                    + " LEFT JOIN productos p ON p.prod_pk = c.cal_prod_fk"
                    + " LEFT JOIN temas_calidad t ON t.tca_pk = c.cal_tca_fk"
                    + " LEFT JOIN valor_calidad_codigos v ON c.cal_vca_fk = v.vca_pk"
                    + " LEFT JOIN entregables e2 ON e2.ent_pk = p.prod_ent_fk"
                    + " WHERE c.cal_proy_fk = :proyPk"
//                    + " AND (v.vca_codigo = :vcaCodPend OR v.vca_codigo = :vcaCodBaja)"
                    + " AND v.vca_codigo = :vcaCodPend"
                    + " AND ((e.ent_inicio <= :nowLong)"
                    + " OR (e2.ent_inicio <= :nowLong)"
                    + " OR (t.tca_pk > 0))"
                    + " ORDER BY v.vca_pk DESC, c.cal_actualizacion ASC";
            
            Query query = super.getEm().createNativeQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            query.setParameter("vcaCodPend", ValorCalidadCodigosConstantes.PENDIENTE_CARGAR);
//            query.setParameter("vcaCodBaja", ValorCalidadCodigosConstantes.BAJA);
            Long d = new Date().getTime();
            query.setParameter("nowLong", d);
            
            if (size > 0) {
                query.setMaxResults(size);
            }
            List<Integer> listId = query.getResultList();
            List<Calidad> resultList = new ArrayList<>();
            for (Integer id : listId) {
                try {
                    resultList.add(super.findById(Calidad.class, id));
                } catch (DAOGeneralException ex) {
                    Logger.getLogger(CalidadDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            return resultList;
        }
        return null;
    }
}
