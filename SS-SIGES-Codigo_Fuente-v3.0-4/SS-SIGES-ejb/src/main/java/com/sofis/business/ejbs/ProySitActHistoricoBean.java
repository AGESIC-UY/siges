package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.ProySitactHistoricoDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProySitactHistorico;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ProySitActHistoricoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ProySitActHistoricoBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public List<ProySitactHistorico> obtenerHistoricoSitActTodos(Integer proyPk) {
        ProySitactHistoricoDAO dao = new ProySitactHistoricoDAO(em);
        return dao.obtenerSitActHistoricoPorFecha(proyPk);
    }

    public Date obtenerUltimaFechaSitAct(Integer proyPk) {
        ProySitactHistoricoDAO dao = new ProySitactHistoricoDAO(em);
        return dao.obtenerUltimaFechaSitAct(proyPk);
    }

    public ProySitactHistorico obtenerUltimaSitAct(Integer proyPk) {
        ProySitactHistoricoDAO dao = new ProySitactHistoricoDAO(em);
        return dao.obtenerUltimaSitAct(proyPk);
    }
}
