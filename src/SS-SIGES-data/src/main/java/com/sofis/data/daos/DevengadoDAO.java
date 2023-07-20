package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Devengado;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DevengadoDAO extends HibernateJpaDAOImp<Devengado, Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	public DevengadoDAO(EntityManager em) {
		super(em);
	}

	public Devengado obtenerDevengado(Integer adqPk, short mesDev, short anioDev) {
		if (adqPk != null && mesDev > 0 && anioDev > 0) {
			String queryStr = "SELECT d FROM Devengado d"
					+ " WHERE d.devAdqFk.adqPk = :adqPk"
					+ " AND d.devMes = :mesDev"
					+ " AND d.devAnio = :anioDev";

			try {
				Query query = super.getEm().createQuery(queryStr);
				query.setParameter("adqPk", adqPk);
				query.setParameter("mesDev", mesDev);
				query.setParameter("anioDev", anioDev);

				List<Devengado> result = query.getResultList();

				return (Devengado) DAOUtils.obtenerSingleResult(result);

			} catch (Exception ex) {
				TechnicalException te = new TechnicalException(ex);
				te.addError(MensajesNegocio.ERROR_DEVENGADO_OBTENER);
				throw te;
			}
		}

		return null;
	}
}
