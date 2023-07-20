package com.sofis.data.daos.wekan;

import com.sofis.entities.data.Tablero;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public class TableroDAO extends HibernateJpaDAOImp<Tablero, Integer> implements Serializable {

	public TableroDAO(EntityManager em) {
		super(em);
	}

	public Tablero obtenerPorIdTableroWekan(String idTableroWekan) throws DAOGeneralException {
		
		List<Tablero> result = findByOneProperty(Tablero.class, "idTablero", idTableroWekan);
		
		return result.isEmpty() ? null : result.get(0);
	}

	public Tablero guardar(Tablero tablero) throws DAOGeneralException {

		return super.update(tablero);
	}

}
