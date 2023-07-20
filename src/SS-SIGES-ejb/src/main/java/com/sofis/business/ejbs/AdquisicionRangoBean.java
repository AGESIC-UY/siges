package com.sofis.business.ejbs;

import com.sofis.business.validations.AdquisicionRangoValidacion;
import com.sofis.data.daos.AdquisicionRangoDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.AdquisicionRango;
import com.sofis.entities.data.AdquisicionRangoAux;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "AdquisicionRangoBean")
@LocalBean
public class AdquisicionRangoBean {

    private static final Logger LOGGER = Logger.getLogger(AdquisicionRangoBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private DatosUsuario du;

    @Inject
    private AdquisicionBean adquisicionDelegate;

    private static final String HORA_EJECUCION = "0-23";
    private static final String MINUTOS_EJECUCION = "0-59";
    private static final String SEGUNDOS = "1,4,7,10,13,16,19,22,25,28,31,34,37,40,43,46,52,55,58";

    public AdquisicionRango guardar(AdquisicionRango adquisicion) throws GeneralException {

        AdquisicionRangoValidacion.validar(adquisicion);
        AdquisicionRangoDAO adquisicionDAO = new AdquisicionRangoDAO(em);

        try {
            adquisicion = adquisicionDAO.update(adquisicion, du.getCodigoUsuario(), du.getOrigen());

        } catch (TechnicalException te) {
            LOGGER.log(Level.SEVERE, te.getMessage(), te);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_GUARDAR);
            throw be;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ADQISICION_GUARDAR);
            throw be;
        }

        return adquisicion;
    }

    public List<AdquisicionRango> listarPorOrganismo(Integer orgPk) {

        AdquisicionRangoDAO adqDao = new AdquisicionRangoDAO(em);

        List<AdquisicionRango> adquisiciones = new ArrayList();

        try {
            adquisiciones = adqDao.findByOneProperty(AdquisicionRango.class, "adrOrganismo.orgPk", orgPk, "adrDesde");
        } catch (DAOGeneralException ex) {
            Logger.getLogger(AdquisicionRangoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adquisiciones;
    }

    public List<AdquisicionRango> listarPorOrganismoYArea(Integer orgPk, Integer areaPk) {

        List<AdquisicionRango> adquisiciones = new ArrayList();
        try {
            AdquisicionRangoDAO adqDao = new AdquisicionRangoDAO(em);
            adquisiciones = adqDao.obtenerAdquisicionRangoPorArea(orgPk, areaPk);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(AdquisicionRangoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adquisiciones;
    }

    public List<AdquisicionRango> listarPorOrganismoAreaDivision(Integer orgPk, Integer areaPk, Integer divisionPk) {
        List<AdquisicionRango> adquisiciones = new ArrayList();

        try {
            AdquisicionRangoDAO adqDao = new AdquisicionRangoDAO(em);
            adquisiciones = adqDao.obtenerAdquisicionRangoPorAreaDivison(orgPk, areaPk, divisionPk);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(AdquisicionRangoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adquisiciones;
    }

    public List<AdquisicionRango> listarPorOrganismoYDivision(Integer orgPk, Integer divisionPk) {

        List<AdquisicionRango> adquisiciones = new ArrayList();
        try {
            AdquisicionRangoDAO adqDao = new AdquisicionRangoDAO(em);
            adquisiciones = adqDao.obtenerAdquisicionRangoPorDivision(orgPk, divisionPk);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(AdquisicionRangoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adquisiciones;
    }

    public List<AdquisicionRango> listarPorOrganismoYAreaDivisionNull(Integer orgPk, Integer areaPk) {
        List<AdquisicionRango> adquisiciones = new ArrayList();
        AdquisicionRangoDAO adqDao = new AdquisicionRangoDAO(em);
        adquisiciones = adqDao.obtenerAdquisicionRangoPorAreaDivisionNull(orgPk, areaPk);
        return adquisiciones;
    }

    public AdquisicionRango obtenerPorId(Integer id) {

        AdquisicionRangoDAO tpdDao = new AdquisicionRangoDAO(em);
        try {
            return tpdDao.findById(AdquisicionRango.class, id);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(AdquisicionRangoBean.class.getName()).log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public void eliminar(Integer aPk) {

        if (aPk != null) {
            AdquisicionRangoDAO dao = new AdquisicionRangoDAO(em);
            try {
              
                AdquisicionRango a = obtenerPorId(aPk);
               
                dao.delete(a);
            } catch (DAOGeneralException ex) {
                Logger.getLogger(AdquisicionRangoBean.class.getName()).log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_AREAS_CONOC_ELIMINAR);
                if (ex.getCause() instanceof javax.persistence.PersistenceException
                        && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    be.addError(MensajesNegocio.ERROR_AREAS_CONOC_CONST_VIOLATION);
                }
                throw be;
            }
        }

    }

    public boolean existeRango(Integer orgPk, Integer inicio, Integer hasta, Integer pk) throws DAOGeneralException {
        AdquisicionRangoDAO tpdDao = new AdquisicionRangoDAO(em);
        return tpdDao.existeRango(orgPk, inicio, hasta, pk);
    }

    @Schedule(hour = HORA_EJECUCION, minute = MINUTOS_EJECUCION, second = SEGUNDOS, persistent = false, info = "chqueando rangos")
    public void scheduleLimpiarTokens() {
      
        List<AdquisicionRango> adquisiciones = buscarListaParaChequear();

        for (AdquisicionRango adquisicionEnEdicion : adquisiciones) {

           

            adquisicionEnEdicion.setAdrUltimo(0);

           
           AdquisicionRangoDAO tpdDao = new AdquisicionRangoDAO(em);
           
            BigInteger adqAux = tpdDao.siguienteDisponible(adquisicionEnEdicion.getAdrDesde(), adquisicionEnEdicion.getAdrHasta(),adquisicionEnEdicion.getAdrOrganismo().getOrgPk());
            
            
            if(adqAux!=null){
                adquisicionEnEdicion.setAdrUltimo(adqAux.intValue());
            }
            
            adquisicionEnEdicion.setAdrChequear(Boolean.FALSE);
            guardar(adquisicionEnEdicion);

        }

    }

    private List<AdquisicionRango> buscarListaParaChequear() {
      
        AdquisicionRangoDAO tpdDao = new AdquisicionRangoDAO(em);
        return tpdDao.listarParaChequeo();

    }

}
