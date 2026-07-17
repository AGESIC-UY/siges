package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.entities.constantes.ConstanteApp;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named
@Stateless(name = "GrupoTrabajoOperacionDetalleBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class GrupoTrabajoOperacionDetalleBean {

    private static final Logger LOGGER = Logger.getLogger(GrupoTrabajoOperacionDetalleBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    public void eliminarGrupo(Integer grupoPk) {
        String queryStr = "delete from grupo_trabajo_operacion_detalle where gto_grupo_fk =" + grupoPk;
        Query query = em.createNativeQuery(queryStr);
        int result = query.executeUpdate();

    }

    public void inserTarGrupo(Integer grupoPk, Integer opPk) {
        String queryStr = "INSERT INTO grupo_trabajo_operacion_detalle\n"
                + "(gto_grupo_fk, gto_operacion_fk)\n"
                + "VALUES(" + grupoPk + ", " + opPk + ")";
        Query query = em.createNativeQuery(queryStr);
        int result = query.executeUpdate();

    }

}
