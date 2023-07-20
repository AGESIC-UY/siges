package com.sofis.data.daos.wekan;

import com.sofis.entities.data.Vinculacion;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public class VinculacionDAO extends HibernateJpaDAOImp<Vinculacion, Integer> implements Serializable {

	public VinculacionDAO(EntityManager em) {
		super(em);
	}

	public boolean existeVinculacion(Integer idCronograma) {
		return false;
	}

	public Vinculacion obtenerPorIdCronograma(Integer idCronograma) throws DAOGeneralException {

		List<Vinculacion> result = em.createNamedQuery("Vinculacion.obtenerPorIdCronograma", Vinculacion.class)
				.setParameter("idCronograma", idCronograma)
				.getResultList();

		return result.isEmpty() ? null : result.get(0);
	}
        
        public Integer obtenerCantidadVinculacionesPorIdTablero(Long id) throws DAOGeneralException {

		List<Vinculacion> result = em.createNamedQuery("Vinculacion.obtenerCantidadVinculacionesPorIdTablero", Vinculacion.class)
				.setParameter("id", id)
				.getResultList();

		return result.isEmpty() ? 0 : result.size();
	}
        

	public Vinculacion guardar(Vinculacion vinculacion) throws DAOGeneralException {

		return update(vinculacion);
	}
}
