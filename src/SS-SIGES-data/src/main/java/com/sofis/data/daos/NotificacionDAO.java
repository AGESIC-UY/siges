package com.sofis.data.daos;

import com.sofis.entities.data.Notificacion;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

public class NotificacionDAO extends HibernateJpaDAOImp<Notificacion, Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	public NotificacionDAO(EntityManager em) {
		super(em);
	}
}
