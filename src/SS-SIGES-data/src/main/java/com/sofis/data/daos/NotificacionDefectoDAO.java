package com.sofis.data.daos;

import com.sofis.entities.data.NotificacionDefecto;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public class NotificacionDefectoDAO extends HibernateJpaDAOImp<NotificacionDefecto, Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	public NotificacionDefectoDAO(EntityManager em) {
		super(em);
	}

	public List<NotificacionDefecto> findAll() throws DAOGeneralException {
		return super.findAll(NotificacionDefecto.class);
	}

}
