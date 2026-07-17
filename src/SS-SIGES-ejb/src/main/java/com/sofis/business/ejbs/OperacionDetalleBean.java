package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.GrupoTrabajoDAO;
import com.sofis.data.daos.OperacionDetalleDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.GrupoTrabajo;
import com.sofis.entities.data.OperacionDetalle;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
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

@Named
@Stateless(name = "OperacionDetalleBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class OperacionDetalleBean {

    private static final Logger LOGGER = Logger.getLogger(OperacionDetalleBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    
    
    @Inject
    private DatosUsuario du;

   

    public List<OperacionDetalle> listarPorOrg(Integer orgPk) throws GeneralException {

        OperacionDetalleDAO dao = new OperacionDetalleDAO(em);
        try {
           

            return dao.findAll(OperacionDetalle.class);

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }

    }

    


}
