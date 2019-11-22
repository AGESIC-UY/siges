package com.sofis.business.ejbs;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProyOtrosDatos;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ProyOtrosDatosBean")
@LocalBean
public class ProyOtrosDatosBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ProyOtrosDatosBean.class.getName());

    public ProyOtrosDatos copiarProyOtrosDatos(ProyOtrosDatos pod) {
        if (pod != null) {
            ProyOtrosDatos podNew = new ProyOtrosDatos();
            podNew.setProyOtrCatFk(pod.getProyOtrCatFk() != null ? pod.getProyOtrCatFk() : null);
            podNew.setProyOtrContFk(pod.getProyOtrContFk() != null ? pod.getProyOtrContFk() : null);
            podNew.setProyOtrEntFk(pod.getProyOtrEntFk() != null ? pod.getProyOtrEntFk() : null);
            podNew.setProyOtrEstPubFk(pod.getProyOtrEstPubFk() != null ? pod.getProyOtrEstPubFk() : null);
            podNew.setProyOtrEtaFk(pod.getProyOtrEtaFk() != null ? pod.getProyOtrEtaFk() : null);
            podNew.setProyOtrInsEjeFk(pod.getProyOtrInsEjeFk() != null ? pod.getProyOtrInsEjeFk() : null);
            podNew.setProyOtrObservaciones(pod.getProyOtrObservaciones() != null ? pod.getProyOtrObservaciones() : null);
            podNew.setProyOtrOrigen(pod.getProyOtrOrigen() != null ? pod.getProyOtrOrigen() : null);
            podNew.setProyOtrPlazo(pod.getProyOtrPlazo() != null ? pod.getProyOtrPlazo() : null);
            podNew.setProyOtrosCatSecundarias(pod.getProyOtrosCatSecundarias() != null ? pod.getProyOtrosCatSecundarias() : null);
            return podNew;
        }
        return null;
    }
}
