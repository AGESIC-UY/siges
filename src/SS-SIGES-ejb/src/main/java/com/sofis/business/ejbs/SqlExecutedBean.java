package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.SqlExecutedDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.SqlExecuted;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Named
@Stateless(name = "SqlExecutedBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class SqlExecutedBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(SqlExecutedBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    public boolean wasExecuted(int verMayor, int verMenor, int build, Integer key) {
        SqlExecutedDAO dao = new SqlExecutedDAO(em);
        return dao.wasExecuted(verMayor, verMenor, build, key);
    }

    public void addExecuted(int verMayor, int verMenor, int build, Integer update, String query) throws DAOGeneralException {
        if (update != null) {
            SqlExecuted se = new SqlExecuted();
            se.setSqlVerMayor(verMayor);
            se.setSqlVerMenor(verMenor);
            se.setSqlBuild(build);
            se.setSqlUpdate(update);
            se.setSqlDesc(query);
            se.setSqlFecha(new Date());

            SqlExecutedDAO dao = new SqlExecutedDAO(em);
            dao.update(se);
        }
    }

}
