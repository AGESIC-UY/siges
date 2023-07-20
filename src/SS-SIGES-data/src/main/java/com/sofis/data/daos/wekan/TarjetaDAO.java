package com.sofis.data.daos.wekan;

import com.sofis.entities.data.Tarjeta;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public class TarjetaDAO extends HibernateJpaDAOImp<Tarjeta, Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	public TarjetaDAO(EntityManager em) {
		super(em);
	}

	public List<Tarjeta> obtenerPorIdTablero(Long idTablero) {

		return em.createNamedQuery("Tarjeta.findByIdTablero", Tarjeta.class)
				.setParameter("idTablero", idTablero)
				.getResultList();
	}

	public List<Tarjeta> obtenerPorIdCronograma(Integer idCronograma) {

		return em.createNamedQuery("Tarjeta.findByIdCronograma", Tarjeta.class)
				.setParameter("idCronograma", idCronograma)
				.getResultList();
	}

        public List<Tarjeta> obtenerPorIdLista(String idLista) {

		return em.createNamedQuery("Tarjeta.findByIdLista", Tarjeta.class)
				.setParameter("idLista", idLista)
				.getResultList();
	}
        
	public Tarjeta guardar(Tarjeta tablero) throws DAOGeneralException {

		return super.update(tablero);
	}

	public Tarjeta obtenerPorIdTarjeta(String idTarjeta) {

		List<Tarjeta> result = em.createNamedQuery("Tarjeta.findByIdTarjeta", Tarjeta.class)
				.setParameter("idTarjeta", idTarjeta)
				.getResultList();
		
		return result.isEmpty() ? null : result.get(0);
	}

	public Tarjeta obtenerPorIdEntregable(Integer idEntregable) {

		List<Tarjeta> result = em.createNamedQuery("Tarjeta.findByIdEntregable", Tarjeta.class)
				.setParameter("idEntregable", idEntregable)
				.getResultList();
		
		return result.isEmpty() ? null : result.get(0);
	}
}
