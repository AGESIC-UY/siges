package com.sofis.business.ejbs;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.EtiquetaDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Etiqueta;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;

@Named
@Stateless(name = "EtiquetaBean")
@LocalBean
@Interceptors({ LoggedInterceptor.class })
public class EtiquetaBean {

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	private static final Logger logger = Logger.getLogger(EtiquetaBean.class.getName());

	public List<Etiqueta> obtenerEtiquetasPorOrgId(Integer orgId) throws GeneralException {

		EtiquetaDAO etiquetaDAO = new EtiquetaDAO(em);
		try {
			return etiquetaDAO.obtenerPorOrganismo(orgId);

		} catch (DAOGeneralException ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_FUENTE_FINANC_OBTENER);

			throw be;
		}
	}

}
