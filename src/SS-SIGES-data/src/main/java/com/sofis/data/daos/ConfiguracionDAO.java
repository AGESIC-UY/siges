package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Configuracion;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ConfiguracionDAO extends HibernateJpaDAOImp<Configuracion, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConfiguracionDAO.class.getName());        

    public ConfiguracionDAO(EntityManager em) {
        super(em);
    }

    public Map<String, Configuracion> obtenerCnfPorCodigoYOrg(Integer orgPk, String[] codigo) {
        if (orgPk != null && codigo != null && codigo.length > 0) {
            String queryStr = "SELECT c"
                    + " FROM Configuracion c"
                    + " WHERE c.cnfOrgFk.orgPk = :orgPk";

            Map<String, Object> mapParam = new HashMap<>();
            queryStr += " AND (";
            for (int i = 0; i < codigo.length; i++) {
                if (i > 0) {
                    queryStr += " OR";
                }
                queryStr += " c.cnfCodigo = :cod" + i;
                mapParam.put("cod" + i, codigo[i]);
            }
            queryStr += ")";
            Query q = super.getEm().createQuery(queryStr);
            q.setParameter("orgPk", orgPk);

            Set<String> keys = mapParam.keySet();
            for (String key : keys) {
                q.setParameter(key, mapParam.get(key));
            }

            List<Configuracion> listConf = null;
            try {
                listConf = q.getResultList();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }

            if (listConf != null) {
                Map<String, Configuracion> result = new HashMap<>();
                for (Configuracion conf : listConf) {
                    result.put(conf.getCnfCodigo(), conf);
                }
                return result;
            }
        }
        return null;
    }
    
    public List<Configuracion> obtenerConfSinOrg() {
            String queryStr = "SELECT c"
                    + " FROM Configuracion c"
                    + " WHERE c.cnfOrgFk IS NULL";
            
            Query query = super.getEm().createQuery(queryStr);
            List<Configuracion> resultList = query.getResultList();
            
            return resultList;
    }
}
