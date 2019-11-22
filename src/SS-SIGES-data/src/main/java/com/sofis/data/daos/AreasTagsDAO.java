package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.AreasTags;
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
public class AreasTagsDAO extends HibernateJpaDAOImp<AreasTags, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AreasTagsDAO.class.getName());    

    public AreasTagsDAO(EntityManager em) {
        super(em);
    }

    public List<AreasTags> obtenerAreasTematicasPorFichaPk(int fichaFk, Integer tipoFicha) throws DAOGeneralException {
        String queryStr = null;
        if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
            queryStr = "SELECT d FROM Programas p, IN(p.areasTematicasSet) d"
                    + " WHERE p.progPk = :fichaFk"
                    + " ORDER BY d.areatagNombre";

        } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
            queryStr = "SELECT d FROM Proyectos p, IN(p.areasTematicasSet) d"
                    + " WHERE p.proyPk = :fichaFk"
                    + " ORDER BY d.areatagNombre";
        }
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("fichaFk", fichaFk);
        List<AreasTags> result = null;
        try {
            result = query.getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, queryStr, e);
        }
        return result;
    }
}
