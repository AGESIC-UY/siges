/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.FuenteProcedimientoCompraDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.FuenteProcedimientoCompra;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.tipos.FiltroFuenteProcedimientoCompraTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.List;
import java.util.logging.Level;
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
@Stateless(name = "FuenteProcedimientoCompraBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class FuenteProcedimientoCompraBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(FuenteProcedimientoCompraBean.class.getName());
    @Inject
    private DatosUsuario du;

    //private String usuario;
    //private String origen;
    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    public FuenteProcedimientoCompra obtenerPorId(Integer tipAdqId) {
        try {
            FuenteProcedimientoCompraDAO fuenteProcedimientoCompraDAO = new FuenteProcedimientoCompraDAO(em);
            return fuenteProcedimientoCompraDAO.findById(FuenteProcedimientoCompra.class, tipAdqId);
        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }
    }

    public FuenteProcedimientoCompra guardar(FuenteProcedimientoCompra fueProCom) {
        try {
            FuenteProcedimientoCompraDAO fuenteProcedimientoCompraDAO = new FuenteProcedimientoCompraDAO(em);
            if (fueProCom.getFueProComPk() != null) {
                return fuenteProcedimientoCompraDAO.update(fueProCom, du.getCodigoUsuario(), du.getOrigen());
            } else {
                return fuenteProcedimientoCompraDAO.create(fueProCom, du.getCodigoUsuario(), du.getOrigen());
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

    public List<FuenteProcedimientoCompra> obtenerPorFiltro(FiltroFuenteProcedimientoCompraTO filtro) {
        try {
            FuenteProcedimientoCompraDAO fuenteProcedimientoCompraDAO = new FuenteProcedimientoCompraDAO(em);
            return fuenteProcedimientoCompraDAO.obtenerPorFiltro(filtro);
        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }
    }

    public void eliminar(Integer fueProComPk) {
        FuenteProcedimientoCompraDAO fuenteProcedimientoCompraDAO = new FuenteProcedimientoCompraDAO(em);
        try {
            FuenteProcedimientoCompra obj = fuenteProcedimientoCompraDAO.findById(FuenteProcedimientoCompra.class, fueProComPk);
            fuenteProcedimientoCompraDAO.delete(
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

    public Boolean fuenteProcedimientoCompraEstaEnTabla(FuenteFinanciamiento fuente, ProcedimientoCompra procedimientoCompra) {
        FuenteProcedimientoCompraDAO fuenteProcedimientoCompraDAO = new FuenteProcedimientoCompraDAO(em);
        try {
            return fuenteProcedimientoCompraDAO.fuenteProcedimientoCompraEstaEnTabla(fuente, procedimientoCompra);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            throw new TechnicalException(ex);
        }
    }

    public FuenteProcedimientoCompra obtenerPorFuenteProcedimientoCompra(FuenteFinanciamiento fuente, ProcedimientoCompra procedimientoCompra) {
        FuenteProcedimientoCompraDAO fuenteProcedimientoCompraDAO = new FuenteProcedimientoCompraDAO(em);
        try {
            return fuenteProcedimientoCompraDAO.obtenerPorFuenteProcedimientoCompra(fuente, procedimientoCompra);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            throw new TechnicalException(ex);
        }
    }
}
