package com.sofis.data.daos;

import com.sofis.entities.data.MailTemplateDefecto;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public class MailTemplateDefectoDAO extends HibernateJpaDAOImp<MailTemplateDefecto, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public MailTemplateDefectoDAO(EntityManager em) {
        super(em);
    }

	public List<MailTemplateDefecto> findAll() throws DAOGeneralException {
		return findAll(MailTemplateDefecto.class);
	}
}
