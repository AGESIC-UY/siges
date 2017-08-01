package com.sofis.data.daos;

import com.sofis.entities.data.MailsTemplate;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class MailsTemplateDAO extends HibernateJpaDAOImp<MailsTemplate, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public MailsTemplateDAO(EntityManager em) {
        super(em);
    }
}
