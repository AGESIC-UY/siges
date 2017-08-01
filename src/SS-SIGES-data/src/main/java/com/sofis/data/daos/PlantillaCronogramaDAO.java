package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.PlantillaCronograma;
import com.sofis.entities.tipos.FiltroPlantillaCronogramaTO;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.sofisform.to.OR_TO;
import com.sofis.utils.CriteriaTOUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class PlantillaCronogramaDAO extends HibernateJpaDAOImp<PlantillaCronograma, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public PlantillaCronogramaDAO(EntityManager em) {
	super(em);
    }

    public List<PlantillaCronograma> buscarPorFiltro(FiltroPlantillaCronogramaTO filtro, Integer orgPk) {
	// Organismo
	CriteriaTO criteriaOrga = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "orgFk.orgPk", orgPk);
	// Activos
	CriteriaTO criteriaActivoTrue = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "activo", Boolean.TRUE);
	CriteriaTO criteriaActivoNull = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NULL, "activo", 1);
	OR_TO criteriaActivo = new OR_TO(criteriaActivoTrue, criteriaActivoNull);

	CriteriaTO criteriaFiltroProg = null;
	if (filtro != null) {
	    if (filtro.getNombre() != null && !filtro.getNombre().trim().equalsIgnoreCase("")) {
//                criteriaFiltroProg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "pcronoNombre", filtro.getNombre());
		criteriaFiltroProg = DAOUtils.createMatchCriteriaTOString("pcronoNombre", filtro.getNombre());
	    }
	}

	CriteriaTO criteria;
	if (criteriaFiltroProg != null) {
	    criteria = CriteriaTOUtils.createANDTO(criteriaOrga, criteriaActivo, criteriaFiltroProg);
	} else {
	    criteria = CriteriaTOUtils.createANDTO(criteriaOrga, criteriaActivo);
	}

	List<PlantillaCronograma> result;
	try {
	    result = this.findEntityByCriteria(PlantillaCronograma.class, criteria, new String[]{"pcronoNombre"}, new boolean[]{false}, null, null);
	} catch (DAOGeneralException ex) {
	    logger.log(Level.SEVERE, null, ex);
	    TechnicalException te = new TechnicalException(ex);
	    te.addError(ex.getMessage());
	    throw te;
	}

	return result;
    }
}
