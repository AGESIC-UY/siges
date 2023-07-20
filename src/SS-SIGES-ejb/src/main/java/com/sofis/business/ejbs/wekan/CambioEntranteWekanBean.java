package com.sofis.business.ejbs.wekan;

import com.sofis.entities.tipos.ActivityInfoTO;
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

public class CambioEntranteWekanBean {

	private static final Logger LOGGER = Logger.getLogger(CambioEntranteWekanBean.class.getName());

	@Resource(lookup = "java:/ConnectionFactory")
	private static ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/queue/cambioEntranteWekanQueue")
	private static Queue queue;

	public void enviarCambio(ActivityInfoTO activityInfo) {

		LOGGER.log(Level.FINE, "Publicar cambio actividad {0}", activityInfo.getActivityId());
                
		Connection connection = null;
		Session session;
		MessageProducer messageProducer;
		ObjectMessage message;

		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);
			message = session.createObjectMessage();

			message.setObject(activityInfo);

			messageProducer.send(message);

			LOGGER.log(Level.FINE, "Publicado cambio actividad {0}", activityInfo.getActivityId());

		} catch (JMSException e) {
			LOGGER.log(Level.SEVERE, "Error publicando cambio actividad " + activityInfo.getActivityId(), e);
			LOGGER.log(Level.SEVERE, "Error publicando cambio actividad - LinkedException" + activityInfo.getActivityId(), e.getLinkedException());

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					LOGGER.log(Level.SEVERE, "Error publicando cambio actividad " + activityInfo.getActivityId(), e);
				}
			}

		}
	}
}
