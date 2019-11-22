/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.CausalCompraDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.tipos.FiltroCausalCompraTO;
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
@Stateless(name = "CausalCompraBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class CausalCompraBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(CausalCompraBean.class.getName());
    @Inject
    private DatosUsuario du;

    //private String usuario;
    //private String origen;
    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    public CausalCompra obtenerPorId(Integer tipAdqId) {
        try {
            CausalCompraDAO causalCompraDAO = new CausalCompraDAO(em);
            return causalCompraDAO.findById(CausalCompra.class, tipAdqId);
        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }
    }

    public CausalCompra guardar(CausalCompra cauCom) {
        try {
            CausalCompraDAO causalCompraDAO = new CausalCompraDAO(em);
            if (cauCom.getCauComPk() != null) {
                return causalCompraDAO.update(cauCom, du.getCodigoUsuario(), du.getOrigen());
            } else {
                return causalCompraDAO.create(cauCom, du.getCodigoUsuario(), du.getOrigen());
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

    public List<CausalCompra> obtenerPorFiltro(FiltroCausalCompraTO filtro) {
        try {
            CausalCompraDAO causalCompraDAO = new CausalCompraDAO(em);
            return causalCompraDAO.obtenerPorFiltro(filtro);
        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }
    }

    public void eliminar(Integer cauComPk) {
        CausalCompraDAO causalCompraDAO = new CausalCompraDAO(em);
        try {
            CausalCompra obj = causalCompraDAO.findById(CausalCompra.class, cauComPk);
            causalCompraDAO.delete(
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
