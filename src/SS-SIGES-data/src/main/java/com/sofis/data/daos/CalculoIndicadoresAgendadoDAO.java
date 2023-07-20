package com.sofis.data.daos;

import com.sofis.entities.data.CalculoIndicadoresAgendado;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public class CalculoIndicadoresAgendadoDAO extends HibernateJpaDAOImp<CalculoIndicadoresAgendado, Integer> implements Serializable {

	public CalculoIndicadoresAgendadoDAO(EntityManager em) {
		super(em);
	}

	public List<CalculoIndicadoresAgendado> obtenerPendientes() {

		return super.getEm()
                .createNamedQuery("CalculoIndicadoresAgendado.findAll", CalculoIndicadoresAgendado.class)
                .getResultList();
	}
	
	public void crear(CalculoIndicadoresAgendado entity) {
	
		em.persist(entity);
	}
	
	public void eliminar(CalculoIndicadoresAgendado entity) {
	
		em.remove(entity);
	}
	
	public boolean existeProgramaAgendado(Integer progPk) {
		
		return em.createNamedQuery("CalculoIndicadoresAgendado.existeConPrograma", Long.class)
				.setParameter("idPrograma", progPk)
				.getSingleResult() > 0;
	}
}
