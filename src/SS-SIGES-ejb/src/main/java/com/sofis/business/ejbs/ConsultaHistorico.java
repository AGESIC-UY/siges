package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Configuracion;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ConsultaHistorico")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ConsultaHistorico<T> {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    
    private static final Logger logger = Logger.getLogger(ConsultaHistorico.class.getName());

    public List<Configuracion> obtenerConfiguracion(Integer id) throws GeneralException {
        AuditReader reader = AuditReaderFactory.get(em);
        List<Configuracion> respuesta = reader.createQuery().forRevisionsOfEntity(Configuracion.class, true, true)
                .add(AuditEntity.id().eq(id))
                .addOrder(AuditEntity.property("cnfVersion").desc())
                .getResultList();
        return respuesta;
    }

    public <T> List<T> obtenerHistorialPorId(Class clase, Integer id, String campoVersion) throws GeneralException {
        AuditReader reader = AuditReaderFactory.get(em);
        List<T> respuesta = reader.createQuery().forRevisionsOfEntity(clase, true, true)
                .add(AuditEntity.id().eq(id))
                .addOrder(AuditEntity.property(campoVersion).desc())
                .getResultList();
        return respuesta;
    }

    public Configuracion obtenerConfiguracionEnVersion(Integer version, Integer id) {
        AuditReader reader = AuditReaderFactory.get(em);
        try {
            List<Configuracion> respuesta = reader.createQuery().forRevisionsOfEntity(Configuracion.class, true, true)
                    .add(AuditEntity.id().eq(id))
                    .add(AuditEntity.property("cnfVersion").eq(version))
                    .getResultList();
            if (respuesta != null && respuesta.size() > 0) {
                return respuesta.get(0);
            }
            return null;
        } catch (Exception w) {
            w.printStackTrace();
            return null;
        }

    }

    public <T> T obtenerEnVersion(Class clase, Integer version, Integer id, String campoVersion) {
        AuditReader reader = AuditReaderFactory.get(em);
        List<T> respuesta = reader.createQuery().forRevisionsOfEntity(clase, true, true)
                .add(AuditEntity.id().eq(id))
                .add(AuditEntity.property(campoVersion).eq(version))
                .getResultList();
        if (respuesta != null && respuesta.size() > 0) {
            return respuesta.get(0);
        }
        return null;
    }
}
