package com.sofis.business.messages;

import com.sofis.business.ejbs.wekan.TarjetaBean;
import com.sofis.entities.tipos.wekan.TarjetaTO;
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
			@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/cambioSalienteWekanQueue"),
			@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
			@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
			@ActivationConfigProperty(propertyName = "connectionFactoryName", propertyValue = "ConnectionFactory")
		})
public class CambioSalienteWekanMessageListener implements MessageListener {

	private static final Logger LOGGER = Logger.getLogger(CambioSalienteWekanMessageListener.class.getName());

	@Resource
	private MessageDrivenContext mdc;

	@Inject
	private TarjetaBean tarjetaBean;

	@Override
	public void onMessage(Message inMessage) {

		try {
			if (!(inMessage instanceof ObjectMessage)) {

				LOGGER.log(Level.WARNING, "wekan_cambio_saliente_error_tipo_mensaje_no_valido: {0}", inMessage.getClass().getName());
			}

			ObjectMessage message = (ObjectMessage) inMessage;

			if (!(message.getObject() instanceof TarjetaTO)) {

				LOGGER.log(Level.WARNING, "wekan_cambio_saliente_error_tipo_objeto_no_valido: {0}", inMessage.getClass().getName());
			}

			TarjetaTO tarjeta = (TarjetaTO) message.getObject();

			tarjetaBean.aplicarCambiosTarjeta(tarjeta);

			LOGGER.log(Level.FINE, "wekan_cambio_saliente_procesado: Tarjeta {0}", tarjeta.getIdTarjeta());

		} catch (JMSException e) {

			LOGGER.log(Level.SEVERE, "wekan_cambio_saliente_error_jmsexception", e);
			mdc.setRollbackOnly();

		} catch (Throwable te) {
			LOGGER.log(Level.SEVERE, "wekan_cambio_saliente_error", te);
		}
	}

}
