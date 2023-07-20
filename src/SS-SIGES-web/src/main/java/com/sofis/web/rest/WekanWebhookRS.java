package com.sofis.web.rest;

import com.sofis.entities.tipos.ActivityInfoTO;
import com.sofis.business.ejbs.wekan.TarjetaBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("wekan")
public class WekanWebhookRS {

	private static final Logger LOGGER = Logger.getLogger(WekanWebhookRS.class.getName());

	@Inject
	private TarjetaBean tarjetaBean;

	@POST
	@Path("bidirectional-webhook")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ActivityInfoTO procesarActividadBidireccional(@HeaderParam("x-wekan-token") String token, ActivityInfoTO payload) {
            
		LOGGER.log(Level.INFO, "Actividad {0} para tarjeta {1} token {2}", new Object[]{payload.getDescription(), payload.getCardId(), token});
		LOGGER.log(Level.INFO, "Datos actividad {0}", payload);

                LOGGER.log(Level.SEVERE, "Antes de procesar");
                
		tarjetaBean.procesarActividadBidireccional(payload, token);

		LOGGER.log(Level.INFO, "Actividad {0} para tarjeta {1} procesada", new Object[]{payload.getDescription(), payload.getCardId()});

		return payload;
	}

}
