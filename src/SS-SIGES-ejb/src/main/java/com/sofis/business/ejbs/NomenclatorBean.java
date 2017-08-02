package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.PaisDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.data.Departamento;
import com.sofis.entities.data.Localidad;
import com.sofis.entities.data.Pais;
import com.sofis.entities.data.Paridad;
import com.sofis.entities.data.TipoEntradaColectiva;
import com.sofis.entities.data.TipoVialidad;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
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
@Stateless(name = "NomenclatorBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class NomenclatorBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    
    
//    @EJB(name = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    @Inject
    private EntityManagementBean emb;
    
    
    private static final Logger logger
            = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public List<Pais> obtenerPaises() {
        return emb.getEntities(Pais.class.getName());
    }

    public List<Departamento> obtenerDepartamentos() {
        return emb.getEntities(Departamento.class.getName());
    }

    public List<TipoVialidad> obtenerTiposVialidad() {
        return emb.getEntities(TipoVialidad.class.getName());
    }

    public List<TipoEntradaColectiva> obtenerTipoEntradaColectiva() {
        return emb.getEntities(TipoEntradaColectiva.class.getName());
    }

    public List<Paridad> obtenerParidades() {
        return emb.getEntities(Paridad.class.getName());
    }

    public List<Localidad> obtenerLocalidadesPorDeptoId(Integer dptoId) {

        List<CriteriaTO> criterios = new ArrayList<>();
        MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                MatchCriteriaTO.types.EQUALS, "locDeptoId.deptoId",
                dptoId);
        criterios.add(criterio);
        CriteriaTO condicion = null;
        if (!criterios.isEmpty()) {
            if (criterios.size() == 1) {
                condicion = criterios.get(0);
            } else {
                condicion = CriteriaTOUtils.createANDTO(criterios
                        .toArray(new CriteriaTO[0]));
            }
        } else {
            // condicion dummy para que el count by criteria funcione
            condicion = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.NOT_NULL, "cgoId", 1);
        }
        String[] propiedades = {"cgoId", "cgoCodigo", "cgoDescripcion", "cgoHabilitado"};
        String className = Localidad.class.getName();
        String[] orderBy = {"locNombre"};
        boolean[] asc = {true};
        return emb.getEntitiesByCriteria(className, condicion, 0, Integer.MAX_VALUE, orderBy, asc);

    }

    public Pais obtenerPaisPorCodigo2(String codigo2) {
        logger.log(Level.INFO, "obtenerCnfPorCodigo");
        PaisDAO cnfDao = new PaisDAO(em);
        try {
            List<Pais> resultado = cnfDao.findByOneProperty(Pais.class, "paiCodigo2", codigo2);
            if (resultado.size() >= 1) {
                return resultado.get(0);
            } else if (resultado.isEmpty()) {
                return null;
            } else {
                BusinessException be = new BusinessException();
                be.addError(ConstantesErrores.ERROR_DEMASIADOS_RESULTADOS);
                throw be;
            }
        } catch (DAOGeneralException ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }
}
