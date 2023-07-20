package com.sofis.data.daos.wekan;

import com.sofis.entities.constantes.ActualizacionWekan;
import com.sofis.entities.data.Actividad;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ActividadDAO extends HibernateJpaDAOImp<Actividad, Long> implements Serializable {

	public ActividadDAO(EntityManager em) {
		super(em);
	}

	public Actividad guardar(Actividad actividad) throws DAOGeneralException {

		super.persist(actividad, null, null);

		return actividad;
	}

	public Actividad obtenerPorId(Long idActividad) throws DAOGeneralException {

		return super.findById(Actividad.class, idActividad);
	}

	public List<Actividad> obtenerActividadesCambiosEnDatosVinculacion(Long idTablero, Integer idCronograma) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Actividad> cq = cb.createQuery(Actividad.class);

		Root<Actividad> root = cq.from(Actividad.class);

		List<Predicate> predicates = new ArrayList<>();

		Predicate predicate = cb.equal(root.get("tarjeta").get("vinculacion").get("tablero"), idTablero);
		predicates.add(predicate);

		predicate = cb.equal(root.get("tarjeta").get("vinculacion").get("cronograma"), idCronograma);
		predicates.add(predicate);

		predicate = cb.not(cb.equal(root.get("actualizacion"), ActualizacionWekan.CAMBIO_LISTA_TARJETA));
		predicates.add(predicate);

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		cq.orderBy(cb.desc(root.get("fechaCreacion")));

		return em.createQuery(cq).getResultList();
	}

	public List<Actividad> obtenerPorIdTarjeta(long idTarjeta) throws DAOGeneralException {

		return findByOneProperty(Actividad.class, "tarjeta.id", idTarjeta);
	}

}
