package com.sofis.business.ejbs.wekan;

import com.sofis.data.daos.wekan.TableroDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Tablero;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TableroBean {

	private static final Logger LOGGER = Logger.getLogger(TableroBean.class.getName());

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Tablero guardarTableroNuevaTransaccion(Tablero tablero) throws DAOGeneralException {

		LOGGER.log(Level.FINE, "guardar tablero - id:{0}", tablero.getId());

		TableroDAO tableroDAO = new TableroDAO(em);

		return tableroDAO.update(tablero);
	}
}
