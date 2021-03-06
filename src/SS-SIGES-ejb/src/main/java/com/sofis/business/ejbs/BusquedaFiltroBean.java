package com.sofis.business.ejbs;

import com.sofis.data.daos.BusquedaFiltroDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.BusquedaFiltro;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroInicioTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Maneja la lógica de la persistencia del filtro de la pantalla de inicio.
 *
 * @author Usuario
 */
@Named
@Stateless(name = "BusquedaFiltroBean")
@LocalBean
public class BusquedaFiltroBean {

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;
	@Inject
	private DatosUsuario du;
	@Inject
	private SsUsuarioBean ssUsuarioBean;
        private static final Logger logger = Logger.getLogger(BusquedaFiltroBean.class.getName());

	//private String usuario;
	//private String origen;
	@PostConstruct
	public void init() {
		//usuario = du.getCodigoUsuario();
		//origen = du.getOrigen();
	}

	public BusquedaFiltro guardar(BusquedaFiltro busquedaFiltro) {
		BusquedaFiltroDAO dao = new BusquedaFiltroDAO(em);

		try {
			busquedaFiltro = dao.update(busquedaFiltro, du.getCodigoUsuario(), du.getOrigen());
			return busquedaFiltro;
		} catch (DAOGeneralException ex) {
			Logger.getLogger(BusquedaFiltroBean.class.getName()).log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_FILTRO_GUARDAR);
			throw be;
		}
	}

	public BusquedaFiltro guardar(FiltroInicioTO filtro, SsUsuario usuario, Organismos organismo) throws GeneralException {
		BusquedaFiltro bf = null;

		if (filtro != null && usuario != null && organismo != null) {
			bf = obtenerFiltroPorUsuOrg(usuario, organismo);
			if (bf == null) {
				bf = new BusquedaFiltro();
				usuario = ssUsuarioBean.obtenerSsUsuarioPorId(usuario.getUsuId());
				bf.setBusqFiltroUsuarioFk(usuario);
				bf.setBusqFiltroOrgaFk(organismo);
			}

			OutputStream os = null;

			try {
				JAXBContext jc = JAXBContext.newInstance(FiltroInicioTO.class);
				Marshaller marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
				os = new ByteArrayOutputStream();
				marshaller.marshal(filtro, os);
				bf.setBusqFiltroXml(os.toString());

				bf = guardar(bf);
			} catch (JAXBException ex) {
				System.out.println(ex.toString());
				BusinessException be = new BusinessException();
				be.addError(MensajesNegocio.ERROR_FILTRO_GUARDAR);
				throw be;
			} finally {
				if (os != null) {
					try {
						os.close();
					} catch (IOException ex) {
						Logger.getLogger(BusquedaFiltroBean.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}
		return bf;
	}

	public BusquedaFiltro obtenerFiltroPorUsuOrg(SsUsuario usuario, Organismos organismo) {
		if (usuario != null && organismo != null) {
			BusquedaFiltroDAO dao = new BusquedaFiltroDAO(em);
			return dao.obtenerFiltroPorUsuOrg(usuario, organismo);
		}
		return null;
	}

	public FiltroInicioTO obtenerFiltroInicio(SsUsuario usuario, Organismos organismo) {
		BusquedaFiltro bf = obtenerFiltroPorUsuOrg(usuario, organismo);

		if (bf != null) {
			InputStream is = null;
			try {
				JAXBContext jc = JAXBContext.newInstance(FiltroInicioTO.class);
				String str = bf.getBusqFiltroXml();
				is = new ByteArrayInputStream(str.getBytes());
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				FiltroInicioTO fi = (FiltroInicioTO) unmarshaller.unmarshal(is);
				return fi;
			} catch (JAXBException jAXBException) {
				jAXBException.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException ex) {
						logger.log(Level.SEVERE, null, ex);
					}
				}
			}
		}

		return null;
	}
}
