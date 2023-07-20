package com.sofis.business.messages;

import com.sofis.business.ejbs.ProyectosBean;
import com.sofis.business.ejbs.CronogramasBean;
import com.sofis.business.ejbs.wekan.TarjetaBean;
import com.sofis.data.daos.wekan.TarjetaDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Tarjeta;
import com.sofis.entities.tipos.ActivityInfoTO;
import java.util.logging.Level;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.JMSException;
import javax.annotation.Resource;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.inject.Inject;
import javax.jms.ObjectMessage;

@MessageDriven(mappedName = "sigesWekanMessageListener",
		activationConfig = {
			@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/cambioEntranteWekanQueue"),
			@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
			@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
			@ActivationConfigProperty(propertyName = "connectionFactoryName", propertyValue = "ConnectionFactory")
		})
public class CambioEntranteWekanMessageListener implements MessageListener {

	private static final Logger LOGGER = Logger.getLogger(CambioEntranteWekanMessageListener.class.getName());

	@Resource
	private MessageDrivenContext mdc;

	@Inject
	private TarjetaBean tarjetaBean;

        @Inject
	private CronogramasBean cronogramasBean;

	@Override
	public void onMessage(Message inMessage) {

		try {
			if (!(inMessage instanceof ObjectMessage)) {

				LOGGER.log(Level.WARNING, "wekan_cambio_entrante_error_tipo_mensaje_no_valido: {0}", inMessage.getClass().getName());
			}

			ObjectMessage message = (ObjectMessage) inMessage;

			if (!(message.getObject() instanceof ActivityInfoTO)) {

				LOGGER.log(Level.WARNING, "wekan_cambio_entrante_error_tipo_objeto_no_valido: {0}", inMessage.getClass().getName());
			}

			ActivityInfoTO activityInfo = (ActivityInfoTO) message.getObject();
			
                        cronogramasBean.procesarActividad(activityInfo);
			
			LOGGER.log(Level.FINE, "wekan_cambio_entrante_procesado: id tarjeta {0}", activityInfo.getCardId());
                        
		} catch (JMSException e) {

			LOGGER.log(Level.SEVERE, "wekan_cambio_entrante_error_jmsexception", e);
			mdc.setRollbackOnly();

		} catch (Throwable te) {
			
			LOGGER.log(Level.SEVERE, "wekan_cambio_entrante_error", te);
		}
	}

}
