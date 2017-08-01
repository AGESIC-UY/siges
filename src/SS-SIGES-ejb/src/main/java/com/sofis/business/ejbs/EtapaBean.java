package com.sofis.business.ejbs;

import com.sofis.data.daos.EtapaDAO;
import com.sofis.entities.codigueras.EtapaCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Etapa;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "EtapaBean")
@LocalBean
public class EtapaBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @Inject
    private DatosUsuario du;
    @Inject
    private OrganismoBean organismoBean;
    
    
    //private String usuario;
    //private String origen;
    
    @PostConstruct
    public void init(){
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }
    

    public List<Etapa> obtenerEtapaPorOrgId(Integer orgPk) {
        EtapaDAO dao = new EtapaDAO(em);
        try {
            return dao.findByOneProperty(Etapa.class, "etaOrgFk.orgPk", orgPk);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Etapa guardar(Etapa etapa) {
        EtapaDAO dao = new EtapaDAO(em);

        try {
            etapa = dao.update(etapa, du.getCodigoUsuario(),du.getOrigen());
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ETAPA_GUARDAR);
            throw be;
        }

        return etapa;
    }

    public void controlarEtapasFaltantes() {
        List<Organismos> organismos = organismoBean.obtenerTodos();
        if (organismos!=null) {
            for (Organismos org : organismos) {
                List<Etapa> listEtapa = obtenerEtapaPorOrgId(org.getOrgPk());
                Map<String, Etapa> etapaMap = new HashMap<>();
                if (listEtapa != null) {
                    for (Etapa etapa : listEtapa) {
                        etapaMap.put(etapa.getEtaCodigo(), etapa);
                    }
                }

                Etapa[] etapaArr = new Etapa[]{
                    new Etapa(null, EtapaCodigos.PROYECTADO, "Proyectado", null, org),
                    new Etapa(null, EtapaCodigos.EN_ADJUDICACION, "En adjudicación", null, org),
                    new Etapa(null, EtapaCodigos.EN_EJECUCION, "En Ejecución", null, org),
                    new Etapa(null, EtapaCodigos.FINALIZADO, "Finalizado", null, org)
                };

                for (Etapa etapa : etapaArr) {
                    if (!etapaMap.containsKey(etapa.getEtaCodigo())) {
                        guardar(etapa);
                        logger.log(Level.INFO, StringsUtils.concat("Se agregó el estado de publicación '", etapa.getEtaCodigo()), "'");
                    }
                }
            }
        }
    }
}
