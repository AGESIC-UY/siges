/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.TipoAdquisicionDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.TipoAdquisicion;
import com.sofis.entities.tipos.FiltroTipoAdquisicionTO;
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
@Stateless(name = "TipoAdquisicionBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class TipoAdquisicionBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(TipoAdquisicionBean.class.getName());
    @Inject
    private DatosUsuario du;

    //private String usuario;
    //private String origen;
    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    public TipoAdquisicion obtenerPorId(Integer tipAdqId) {
        try {
            TipoAdquisicionDAO tipoAdquisicionDAO = new TipoAdquisicionDAO(em);
            return tipoAdquisicionDAO.findById(TipoAdquisicion.class, tipAdqId);
        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }
    }

    public TipoAdquisicion guardar(TipoAdquisicion objEst) {
        try {
            TipoAdquisicionDAO tipoAdquisicionDAO = new TipoAdquisicionDAO(em);
            if (objEst.getTipAdqPk() != null) {
                return tipoAdquisicionDAO.update(objEst, du.getCodigoUsuario(), du.getOrigen());
            } else {
                return tipoAdquisicionDAO.create(objEst, du.getCodigoUsuario(), du.getOrigen());
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

    public List<TipoAdquisicion> obtenerPorFiltro(FiltroTipoAdquisicionTO filtro) {
        try {
            TipoAdquisicionDAO tipoAdquisicionDAO = new TipoAdquisicionDAO(em);
            return tipoAdquisicionDAO.obtenerPorFiltro(filtro);
        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }
    }

    public void eliminar(Integer tipAdqFk) {
        TipoAdquisicionDAO tipoAdquisicionDAO = new TipoAdquisicionDAO(em);
        try {
            TipoAdquisicion obj = tipoAdquisicionDAO.findById(TipoAdquisicion.class, tipAdqFk);
            tipoAdquisicionDAO.delete(
                    obj,
                    du.getCodigoUsuario(),
                    du.getOrigen());
        } catch (Exception ex) {
            Throwable cause = ex.getCause();
            if (cause != null && cause.getCause() instanceof PersistenceException) {
                cause = cause.getCause();
                if (cause != null && cause.getCause() instanceof ConstraintViolationException) {
                    throw new BusinessException(ex);
                }
            }
            throw new TechnicalException(ex);

        }
    }

}
