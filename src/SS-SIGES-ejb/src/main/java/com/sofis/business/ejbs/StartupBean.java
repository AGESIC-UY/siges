package com.sofis.business.ejbs;

import com.sofis.entities.constantes.ConstanteApp;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Bean que se ejecuta al iniciar la aplicación.
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Singleton
@Startup
public class StartupBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private DBActualizacionBean dBActualizacionBean;

    /**
     * Creates a new instance of StartupBean
     */
    public StartupBean() {
    }

    @PostConstruct
    public void init() {
        logger.log(Level.INFO, "-------------> StartupBean init.");
        
        dBActualizacionBean.actualizarDB();
    }
}
