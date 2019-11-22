/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.IdentificadorGrpErpDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.IdentificadorGrpErp;
import com.sofis.entities.tipos.FiltroIdentificadorGrpErpTO;
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
@Stateless(name = "IdentificadorGrpErpBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class IdentificadorGrpErpBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(IdentificadorGrpErpBean.class.getName());
    @Inject
    private DatosUsuario du;

    //private String usuario;
    //private String origen;
    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    public IdentificadorGrpErp obtenerPorId(final Integer pk) {
        try {
            final IdentificadorGrpErpDAO dao = new IdentificadorGrpErpDAO(em);
            return dao.findById(IdentificadorGrpErp.class, pk);
        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }
    }

    public IdentificadorGrpErp guardar(final IdentificadorGrpErp entity) {
        try {
            final IdentificadorGrpErpDAO dao = new IdentificadorGrpErpDAO(em);
            if (entity.getIdGrpErpPk() != null) {
                return dao.update(entity, du.getCodigoUsuario(), du.getOrigen());
            } else {
                return dao.create(entity, du.getCodigoUsuario(), du.getOrigen());
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

    public List<IdentificadorGrpErp> obtenerPorFiltro(final FiltroIdentificadorGrpErpTO filtro) {
        try {
            final IdentificadorGrpErpDAO dao = new IdentificadorGrpErpDAO(em);
            return dao.obtenerPorFiltro(filtro);
        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }
    }

    public void eliminar(final Integer tipAdqFk) {
        final IdentificadorGrpErpDAO dao = new IdentificadorGrpErpDAO(em);
        try {
            final IdentificadorGrpErp obj = dao.findById(IdentificadorGrpErp.class, tipAdqFk);
            dao.delete(obj, du.getCodigoUsuario(), du.getOrigen());
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
