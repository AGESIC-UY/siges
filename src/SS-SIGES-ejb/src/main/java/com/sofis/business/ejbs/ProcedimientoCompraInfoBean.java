package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.ProcedimientoCompraDAO;
import com.sofis.data.daos.ProcedimientoCompraInfoDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.data.ProcedimientoCompraInfo;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ProcedimientoCompraInfoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ProcedimientoCompraInfoBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ProcedimientoCompraInfoBean.class.getName());

    @Inject
    private DatosUsuario du;

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    public ProcedimientoCompraInfo guardarProcedimientoCompraInfo(ProcedimientoCompraInfo procedimientoCompraEnEdicion) {

        try {
            ProcedimientoCompraInfoDAO dao = new ProcedimientoCompraInfoDAO(em);

            return dao.update(procedimientoCompraEnEdicion, du.getCodigoUsuario(), du.getOrigen());

        } catch (DAOGeneralException ex) {
            Logger.getLogger(ProcedimientoCompraInfoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public List<ProcedimientoCompraInfo> busquedaProcedimientoCompraFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        
   if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "pciOrgFk.orgPk", orgPk);

            criterios.add(criterioOrg);

            String nombre = mapFiltro != null ? (String) mapFiltro.get("nombre") : null;
            if (!StringsUtils.isEmpty(nombre)) {
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("pciNombre", nombre);
                criterios.add(criterio);
            }


            CriteriaTO condicion;
            if (criterios.size() == 1) {
                condicion = criterios.get(0);
            } else {
                condicion = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));
            }

            String[] orderBy = {};
            boolean[] asc = {};
            if (!StringsUtils.isEmpty(elementoOrdenacion)) {
                orderBy = new String[]{elementoOrdenacion};
                asc = new boolean[]{(ascendente == 1)};
            }

            ProcedimientoCompraInfoDAO dao = new ProcedimientoCompraInfoDAO(em);

            try {
                return dao.findEntityByCriteria(ProcedimientoCompraInfo.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException(ex);
                be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_OBTENER);
                throw be;
            }
        }
        return null;        
        
    }

    public ProcedimientoCompraInfo obtenerProcedimientoCompraPorPk(Integer procedimientoPk) {
        ProcedimientoCompraInfoDAO dao = new ProcedimientoCompraInfoDAO(em);

        try {
            ProcedimientoCompraInfo procedimientoCompra = dao.findById(ProcedimientoCompraInfo.class, procedimientoPk);
            return procedimientoCompra;
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_OBTENER);
            throw be;
    }
    }

}
