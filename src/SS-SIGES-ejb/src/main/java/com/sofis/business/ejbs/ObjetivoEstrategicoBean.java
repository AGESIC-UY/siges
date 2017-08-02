/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.ObjetivoEstrategicoDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ObjetivoEstrategico;
import com.sofis.entities.tipos.FiltroObjectivoEstategicoTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Named
@Stateless(name = "ObjetivoEstrategicoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ObjetivoEstrategicoBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @Inject
    private DatosUsuario du;

    //private String usuario;
    //private String origen;

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    public ObjetivoEstrategico obtenerPorId(Integer objEstPk) {
        try {
            ObjetivoEstrategicoDAO objetivoEstrategicoDAO = new ObjetivoEstrategicoDAO(em);
            return objetivoEstrategicoDAO.findById(ObjetivoEstrategico.class, objEstPk);
        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }
    }

    public ObjetivoEstrategico guardar(ObjetivoEstrategico objEst) {
        try {
            ObjetivoEstrategicoDAO objetivoEstrategicoDAO = new ObjetivoEstrategicoDAO(em);
            if (objEst.getObjEstPk() != null) {
                return objetivoEstrategicoDAO.update(objEst, du.getCodigoUsuario(),du.getOrigen());
            } else {
                return objetivoEstrategicoDAO.create(objEst, du.getCodigoUsuario(),du.getOrigen());
            }
        } catch (DAOGeneralException ex) {
            Throwable cause = ex.getCause();
            if (cause != null && cause instanceof PersistenceException) {
                cause = cause.getCause();
                if (cause != null && cause instanceof ConstraintViolationException) {
                    throw new BusinessException(ex);
                }
            }
            throw new TechnicalException(ex);
        }
    }

    public List<ObjetivoEstrategico> obtenerPorOrgPk(Integer orgPk) {
        try {
            ObjetivoEstrategicoDAO objetivoEstrategicoDAO = new ObjetivoEstrategicoDAO(em);
            FiltroObjectivoEstategicoTO filtro = new FiltroObjectivoEstategicoTO();
            filtro.setOrgPk(orgPk);
            return objetivoEstrategicoDAO.obtenerPorFiltro(filtro);
        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }

    }

    public List<ObjetivoEstrategico> obtenerPorFiltro(FiltroObjectivoEstategicoTO filtro) {
        try {
            ObjetivoEstrategicoDAO objetivoEstrategicoDAO = new ObjetivoEstrategicoDAO(em);
            return objetivoEstrategicoDAO.obtenerPorFiltro(filtro);
        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }
    }

    public void eliminar(Integer objEstPk) {
        ObjetivoEstrategicoDAO objetivoEstrategicoDAO = new ObjetivoEstrategicoDAO(em);
        try {
            ObjetivoEstrategico obj = objetivoEstrategicoDAO.findById(ObjetivoEstrategico.class, objEstPk);
            objetivoEstrategicoDAO.delete(
                    obj,
                    du.getCodigoUsuario(),
                    du.getOrigen());
        } catch (Exception ex) {
            Throwable cause = ex.getCause();
            if (cause != null && cause instanceof PersistenceException) {
                cause = cause.getCause();
                if (cause != null && cause instanceof ConstraintViolationException) {
                    throw new BusinessException(ex);
                }
            }
            throw new TechnicalException(ex);

        }
    }

}
