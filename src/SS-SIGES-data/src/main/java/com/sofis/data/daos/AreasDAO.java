package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Areas;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class AreasDAO extends HibernateJpaDAOImp<Areas, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AreasDAO.class.getName());    
    

    public AreasDAO(EntityManager em) {
        super(em);
    }

    public List<Areas> obtenerAreasRestringidasPorFichaPk(int fichaFk, Integer tipoFicha) throws DAOGeneralException {
        String queryStr = "";
        if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
            queryStr = "SELECT d FROM Programas p, IN(p.areasRestringidasSet) d"
                    + " WHERE p.progPk = :fichaFk";

        } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {

            queryStr = "SELECT d FROM Proyectos p, IN(p.areasRestringidasSet) d"
                    + " WHERE p.proyPk = :fichaFk";
        }
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("fichaFk", fichaFk);
        
        List<Areas> result = null;
        try {
            result = query.getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, queryStr, e);
        }
        return result;
    }
}
