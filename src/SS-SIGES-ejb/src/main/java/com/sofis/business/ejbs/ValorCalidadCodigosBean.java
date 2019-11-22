package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.ValorCalidadCodigosDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.constantes.ValorCalidadCodigosConstantes;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.ValorCalidadCodigos;
import com.sofis.entities.tipos.ComboItemTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ValorCalidadCodigosBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ValorCalidadCodigosBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    @Inject
    private ConsultaHistorico<Configuracion> ch;
	
    private static final Logger logger = Logger.getLogger(ValorCalidadCodigosBean.class.getName());

    public ValorCalidadCodigos obtenerPorId(Integer vcaPk) {
        ValorCalidadCodigosDAO cnfDao = new ValorCalidadCodigosDAO(em);
        try {
            List<ValorCalidadCodigos> resultado = cnfDao.findByOneProperty(ValorCalidadCodigos.class, "vcaPk", vcaPk);
            return (ValorCalidadCodigos) DAOUtils.obtenerSingleResult(resultado);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public ValorCalidadCodigos obtenerPorCodigo(String codigo) throws GeneralException {
        ValorCalidadCodigosDAO cnfDao = new ValorCalidadCodigosDAO(em);
        try {
            List<ValorCalidadCodigos> resultado = cnfDao.findByOneProperty(ValorCalidadCodigos.class, "vcaCodigo", codigo);
            return (ValorCalidadCodigos) DAOUtils.obtenerSingleResult(resultado);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<ValorCalidadCodigos> obtenerTodos() {
        ValorCalidadCodigosDAO cnfDao = new ValorCalidadCodigosDAO(em);
        try {
            return cnfDao.findAll(ValorCalidadCodigos.class);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_VCA_OBTENER);
            throw be;
        }
    }

    /**
     * Retorna una lista de ComboItemTO con el id del valor y el nombre.
     *
     * @return List
     */
    public List<ComboItemTO> obtenerTodosParaCombo() {
        List<ValorCalidadCodigos> listVca = obtenerTodos();
        List<ComboItemTO> listaCombo = new ArrayList<>();
        for (ValorCalidadCodigos vc : listVca) {
            listaCombo.add(new ComboItemTO(vc.getVcaPk(), vc.getVcaNombre()));
        }
        return listaCombo;
    }

    public List<ComboItemTO> obtenerRangoValoresParaCombo() {
        List<ValorCalidadCodigos> listVca = obtenerRangoValores();

        List<ComboItemTO> listaCombo = new ArrayList<>();
        for (ValorCalidadCodigos vc : listVca) {
            listaCombo.add(new ComboItemTO(vc.getVcaPk(), vc.getVcaNombre()));
        }
        return listaCombo;
    }

    public List<ValorCalidadCodigos> obtenerRangoValores() {
        List<ValorCalidadCodigos> listVca = new ArrayList<>();
        ValorCalidadCodigos vca = obtenerPorCodigo(ValorCalidadCodigosConstantes.ALTA);
        listVca.add(vca);
        vca = obtenerPorCodigo(ValorCalidadCodigosConstantes.MEDIA);
        listVca.add(vca);
        vca = obtenerPorCodigo(ValorCalidadCodigosConstantes.BAJA);
        listVca.add(vca);
        return listVca;
    }
}
