package com.sofis.business.ejbs;

import com.sofis.business.utils.PlantillaEntregablesUtils;
import com.sofis.business.validations.PlantillaCroValidacion;
import com.sofis.business.validations.PlantillaCronogramaValidacion;
import com.sofis.data.daos.PlantillaCronogramaDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.PlantillaCronograma;
import com.sofis.entities.data.PlantillaEntregables;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.FiltroPlantillaCronogramaTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
@Stateless(name = "PlantillaCronogramaBean")
@LocalBean
public class PlantillaCronogramaBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    @Inject
    private ProyectosBean proyectosBean;
    @Inject
    private CronogramasBean cronogramasBean;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    
    //private String usuario;
    //private String origen;
    
    @PostConstruct
    public void init(){
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }
    

    public PlantillaCronograma guardarPlantillaCronograma(PlantillaCronograma plantillaCronograma) {
        PlantillaCronogramaValidacion.validar(plantillaCronograma);
        PlantillaCronogramaDAO dao = new PlantillaCronogramaDAO(em);

        try {
            plantillaCronograma = dao.update(plantillaCronograma, du.getCodigoUsuario(),du.getOrigen());

        } catch (BusinessException be) {
            be.printStackTrace();
            throw be;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }

        return plantillaCronograma;
    }

    public List<PlantillaCronograma> buscarPorFiltro(FiltroPlantillaCronogramaTO filtro, Integer orgPk) {
        PlantillaCronogramaDAO dao = new PlantillaCronogramaDAO(em);
        List<PlantillaCronograma> result = null;

        try {
            result = dao.buscarPorFiltro(filtro, orgPk);
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return result;

    }

    public PlantillaCronograma obtenerPlantillaCronogramaPorPk(Integer pk) {
        PlantillaCronogramaDAO dao = new PlantillaCronogramaDAO(em);
        try {
            return dao.findById(PlantillaCronograma.class, pk);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public void eliminarPlantillaCronograma(Integer pCronPk) {

        PlantillaCronograma plantilla = this.obtenerPlantillaCronogramaPorPk(pCronPk);
        if (plantilla != null) {
            plantilla.setActivo(false);
        }
        this.guardarPlantillaCronograma(plantilla);
    }

    public Object generarCroDesdePlantilla(Integer proyPk, PlantillaCronograma plantillaCro, Date plantillaFechaInicio, SsUsuario usuario, Integer orgPk) {

        PlantillaCroValidacion.validarGenerar(plantillaCro, plantillaFechaInicio);

        List<PlantillaEntregables> listPlantEnt = PlantillaEntregablesUtils.sortByNumero(plantillaCro.getPlantillaEntregablesSet());
        plantillaCro.setPlantillaEntregablesSet(listPlantEnt);

        Proyectos proy = proyectosBean.obtenerProyPorId(proyPk);
        Cronogramas cro = new Cronogramas();
        if (proy.getProyCroFk() != null) {
            cro = proy.getProyCroFk();
            cro.getEntregablesSet().clear();
        } else {
            cro.setCroEntSeleccionado(0);
            cro.setCroPermisoEscritura(true);
            cro.setCroPermisoEscrituraPadre(true);
            cro.setEntregablesSet(new HashSet<Entregables>());
        }

        for (PlantillaEntregables pEnt : plantillaCro.getPlantillaEntregablesSet()) {
            Entregables ent = new Entregables();
            ent.setEntCroFk(cro);
            ent.setCoordinadorUsuFk(proy.getProyUsrGerenteFk());
            ent.setEntNombre(pEnt.getPentregablesNombre());
            ent.setEntId(pEnt.getPentregablesNumero());
            ent.setEntDuracion(pEnt.getPentregableDuracion());
            ent.setEntEsfuerzo(pEnt.getPentregableEsfuerzo());
            ent.setEntProgreso(0);

            ent.setEntNivel(pEnt.getPentregableNivel());

            if (pEnt.getPentregableAntFk() != null) {
                ent.setEntPredecesorFk(pEnt.getPentregableAntFk().getPentregablesNumero().toString());
            }

            //sumar duracion de todos los antecesores de este pEnt
            Integer duracionTotal = calcularDuracionAntecesores(pEnt);
            Date fechaInicio = null;
            //fecha inicio de ent es plantillaFechaInicio + duracion antecesores
            if (duracionTotal == 0) {
                fechaInicio = plantillaFechaInicio;
            } else {
                fechaInicio = DatesUtils.incrementarDias(plantillaFechaInicio, duracionTotal);
            }
            ent.setEntInicio(fechaInicio.getTime());

            //fecha fin de ent es fechaInicio del ent + duracion del pEnt
            ent.setEntFin(DatesUtils.incrementarDias(fechaInicio, pEnt.getPentregableDuracion()).getTime());

            cro.getEntregablesSet().add(ent);
        }

        return cronogramasBean.guardarCronograma(cro, proyPk, TipoFichaEnum.PROYECTO.id, usuario, orgPk);
    }

    private Integer calcularDuracionAntecesores(PlantillaEntregables pEnt) {
        if (pEnt.getPentregableAntFk() == null) {
            return 0;
        }
        return pEnt.getPentregableAntFk().getPentregableDuracion() + calcularDuracionAntecesores(pEnt.getPentregableAntFk()) + 1;
    }
}
