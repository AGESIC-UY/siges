package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.PersonaValidacion;
import com.sofis.data.daos.InteresadosDao;
import com.sofis.data.daos.PersonasDAO;
import com.sofis.data.daos.RolesInteresadosDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.Personas;
import com.sofis.entities.data.RolesInteresados;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.Collection;
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

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "PersonasBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class PersonasBean {

    @Inject
    private DatosUsuario du;
    @Inject
    private ConsultaHistorico<Areas> ch;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    
    
    //private String usuario;
    //private String origen;
    
    @PostConstruct
    public void init(){
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }
    

    public List<Personas> obtenerPersonas(Integer orgaPk) throws GeneralException {
        PersonasDAO persDao = new PersonasDAO(em);
        try {
            List<Personas> resultado = null;
            if (orgaPk == null) {
                resultado = persDao.findAll(Personas.class);
            } else {
                resultado = persDao.findByOneProperty(Personas.class, "persOrgaFk.orgaPk", orgaPk);
            }
            return resultado != null ? resultado : new ArrayList<Personas>();
        } catch (DAOGeneralException ex) {
            logger.logp(Level.SEVERE, PersonasBean.class.getName(), "obtenerPersonas", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public Personas guardar(Personas p) {
        PersonaValidacion.validar(p);
        PersonasDAO dao = new PersonasDAO(em);

        try {
            p = dao.update(p, du.getCodigoUsuario(),du.getOrigen());
        } catch (DAOGeneralException ex) {
            logger.logp(Level.SEVERE, PersonasBean.class.getName(), "guardar", ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
        return p;
    }

    public List<Personas> buscar(String bNombre, Integer bOrganismo, String orderBy, boolean asc) {
        PersonasDAO persDao = new PersonasDAO(em);
        return persDao.buscar(bNombre, bOrganismo, orderBy, asc);
    }

    public Personas obtenerPersonaPorId(Integer persId) {
        try {
            PersonasDAO persDao = new PersonasDAO(em);
            Personas pers = persDao.findById(Personas.class, persId);
            RolesInteresadosDAO rolIntDao = new RolesInteresadosDAO(em);
            pers.setPersRol(rolIntDao.findById(RolesInteresados.class, pers.getPersRolFk()));

            return pers;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void eliminarPersona(Integer persId) throws GeneralException {
        try {
            //Primero validar que no haya ningun interesado asociado a la persona intPersonaFk
            InteresadosDao intDao = new InteresadosDao(em);
            CriteriaTO crit = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "intPersonaFk.persPk", persId);
            Long cant = intDao.countByCriteria(Interesados.class, crit, null, null);
            if (cant.longValue() != 0) {
                BusinessException be = new BusinessException(MensajesNegocio.ERROR_PERSONA_ELIMINAR_ASOCIADO);
                be.addError(MensajesNegocio.ERROR_PERSONA_ELIMINAR_ASOCIADO);
                throw be;
            }
            PersonasDAO persDao = new PersonasDAO(em);
            Personas pers = persDao.findById(Personas.class, persId);
            persDao.delete(pers);
        } catch (DAOGeneralException ex) {
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public void unificar(Personas personaPrincipal, Collection<Personas> personasUnificar) throws GeneralException {
        try {
            InteresadosDao intDao = new InteresadosDao(em);
            PersonasDAO persDao = new PersonasDAO(em);
            //Recorrer todas las personas a unificar (excepto la principal) y hacer que todos los
            //interesados asociados a ella queden asociados a la persona principal
            List<Interesados> is;
            for (Personas p : personasUnificar) {
                if (p.getPersPk().intValue() != personaPrincipal.getPersPk().intValue()) {
                    is = intDao.findByOneProperty(Interesados.class, "intPersonaFk.persPk", p.getPersPk());
                    if (is != null) {
                        for (Interesados i : is) {
                            i.setIntPersonaFk(personaPrincipal);
                            intDao.update(i);
                        }
                    }
                    //Ya se puede eliminar la persona porque no hay ningun interesado asociado a ella
                    p = persDao.findById(Personas.class, p.getPersPk());
                    persDao.delete(p);
                }
            }
        } catch (DAOGeneralException ex) {
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }
}
