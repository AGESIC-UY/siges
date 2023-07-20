package com.sofis.business.ejbs.wekan;

import com.sofis.entities.tipos.wekan.TarjetaTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

public class CambioSalienteWekanBean {

	private static final Logger LOGGER = Logger.getLogger(CambioSalienteWekanBean.class.getName());

	@Resource(lookup = "java:/ConnectionFactory")
	private static ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/queue/cambioSalienteWekanQueue")
	private static Queue queue;

	public void enviarCambio(TarjetaTO cambio) {

		ArrayList<TarjetaTO> cambios = new ArrayList<>();
		cambios.add(cambio);

		enviarCambio(cambios);
	}

	public void enviarCambio(List<TarjetaTO> cambios) {
		if (cambios == null || cambios.isEmpty()) {

			return;
		}

		LOGGER.log(Level.FINE, "Enviar cambios de entregables");

		Connection connection = null;
		Session session;
		MessageProducer messageProducer;
		ObjectMessage message;

		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);

			for (TarjetaTO tarjeta : cambios) {

				message = session.createObjectMessage();
				message.setObject(tarjeta);
				messageProducer.send(message);

				LOGGER.log(Level.FINE, "Enviado cambio de entregable {0} a tarjeta {1}",
						new Object[]{tarjeta.getEntregable().getId(), tarjeta.getIdTarjeta()});
			}

		} catch (JMSException e) {
			LOGGER.log(Level.SEVERE, "Error enviando cambios de entregables ", e);
			LOGGER.log(Level.SEVERE, "Error enviando cambios de entregables - LinkedException", e.getLinkedException());

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					LOGGER.log(Level.SEVERE, "Error enviando cambios de entregables ", e);
				}
			}
		}
	}
}
