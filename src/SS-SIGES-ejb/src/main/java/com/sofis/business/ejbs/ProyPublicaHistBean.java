package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.ProyPublicaHistDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProyPublicaHist;
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
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Named
@Stateless(name = "ProyPublicaHistBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ProyPublicaHistBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public List<ProyPublicaHist> obtenerHistoricoTodos(Integer proyPk) {
        ProyPublicaHistDAO dao = new ProyPublicaHistDAO(em);
        return dao.obtenerHistoricoPorFecha(proyPk);
    }

    public Date obtenerUltimaFecha(Integer proyPk) {
        ProyPublicaHistDAO dao = new ProyPublicaHistDAO(em);
        return dao.obtenerUltimaFecha(proyPk);
    }

    public ProyPublicaHist obtenerUltimo(Integer proyPk) {
        ProyPublicaHistDAO dao = new ProyPublicaHistDAO(em);
        return dao.obtenerUltimo(proyPk);
    }
}
