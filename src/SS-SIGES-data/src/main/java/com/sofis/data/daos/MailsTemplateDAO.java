package com.sofis.data.daos;

import com.sofis.entities.data.MailsTemplate;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class MailsTemplateDAO extends HibernateJpaDAOImp<MailsTemplate, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(MailsTemplateDAO.class.getName());          

    public MailsTemplateDAO(EntityManager em) {
        super(em);
    }
}
