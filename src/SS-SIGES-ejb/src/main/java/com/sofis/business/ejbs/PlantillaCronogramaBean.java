package com.sofis.business.ejbs;

import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.PlantillaEntregablesUtils;
import com.sofis.business.validations.EntregablesValidacion;
import static com.sofis.business.validations.EntregablesValidacion.validarEntregablesPlantilla;
import com.sofis.business.validations.PlantillaCroValidacion;
import com.sofis.business.validations.PlantillaCronogramaValidacion;
import com.sofis.data.daos.PlantillaCronogramaDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.PlantillaCronograma;
import com.sofis.entities.data.PlantillaEntregables;
import com.sofis.entities.data.ProyOtrosDatos;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.FiltroPlantillaCronogramaTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
        private static final Logger logger = Logger.getLogger(PlantillaCronogramaBean.class.getName());

	//private String usuario;
	//private String origen;
	@PostConstruct
	public void init() {
		//usuario = du.getCodigoUsuario();
		//origen = du.getOrigen();
	}

	public PlantillaCronograma guardarPlantillaCronograma(PlantillaCronograma plantillaCronograma) {
		PlantillaCronogramaValidacion.validar(plantillaCronograma);

		/**
		 * Fix de la plantilla
		 */
		Cronogramas cro = this.generarCronogramaDesdePlantilla(plantillaCronograma, new Date());
		PlantillaCronogramaDAO dao = new PlantillaCronogramaDAO(em);

		try {
			plantillaCronograma = dao.update(plantillaCronograma, du.getCodigoUsuario(), du.getOrigen());

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

	/**
	 *
	 * @param plantillaCro
	 * @return
	 */
	public PlantillaCronograma ajustarPlantillaCronograma(PlantillaCronograma plantillaCro) {

		Cronogramas cro = generarCronogramaDesdePlantilla(plantillaCro, new Date());
		List<PlantillaEntregables> listPent = new ArrayList<>();
		HashMap<Integer, PlantillaEntregables> pentMap = new HashMap<>();
		for (PlantillaEntregables pent : plantillaCro.getPlantillaEntregablesSet()) {
			pentMap.put(pent.getPentregablesNumero(), pent);
		}
		PlantillaEntregables pent = null;
		for (Entregables ent : cro.getEntregablesSet()) {
			pent = pentMap.get(ent.getEntId());
			pent.setPentregableDuracion(ent.getEntDuracion());
			listPent.add(pent);
		}
		plantillaCro.setPlantillaEntregablesSet(listPent);
		return plantillaCro;
	}

	/**
	 *
	 * @param proyPk
	 * @param plantillaCro
	 * @param plantillaFechaInicio
	 * @param usuario
	 * @param orgPk
	 * @return Cronograma generado a partir de la Plantilla de Cronograma
	 */
	public Cronogramas generarCronogramaDesdePlantilla(PlantillaCronograma plantillaCro, Date plantillaFechaInicio) {

		PlantillaCroValidacion.validarGenerar(plantillaCro, plantillaFechaInicio);
		List<PlantillaEntregables> listPlantEnt = PlantillaEntregablesUtils.sortByNumero(plantillaCro.getPlantillaEntregablesSet());
		plantillaCro.setPlantillaEntregablesSet(listPlantEnt);

		Cronogramas cro = new Cronogramas();
		cro.setEntregablesSet(new HashSet<Entregables>());
		List<Entregables> entregablesListAux = new LinkedList<>();

		/**
		 * 27-09-2017 (Bruno): Debería asignarles a todos fechaInicio =
		 * plantillaFechaInicio y luego corregir el cronograma con el algoritmo
		 * de Sergio que arregla las fechas
		 */

                /*
                * 13-03-18 Nico: Hay un problema con el nivel de los entregables 
                *       algunos tiene el nivel 0 y otros no. Acá me fijo si
                *       tiene algún nivel 0.
                */   
                
                boolean decrementar=true;
		for (PlantillaEntregables pEnt : plantillaCro.getPlantillaEntregablesSet()) {
                    if(pEnt.getPentregableNivel().equals(0)){
                        decrementar = false;
                    }
                }                
                
                
		for (PlantillaEntregables pEnt : plantillaCro.getPlantillaEntregablesSet()) {
                        Entregables ent = new Entregables();
			ent.setEntCroFk(cro);
			ent.setEntNombre(pEnt.getPentregablesNombre());
			ent.setEntId(pEnt.getPentregablesNumero());
			ent.setEntEsfuerzo(pEnt.getPentregableEsfuerzo());
			ent.setEntProgreso(0);
                                              
                        /*
                        * 13-03-18 Nico: A partir del valor encontrado en el for de más arriba
                        *       me fijo si tengo que hacer pEnt.getPentregableNivel()-1
                                o pEnt.getPentregableNivel().
                        */   
                        if(decrementar){
                            ent.setEntNivel(pEnt.getPentregableNivel()-1);
                        }else{
                            ent.setEntNivel(pEnt.getPentregableNivel());
                        }
                        
                        ent.setEntFinEsHito(pEnt.getPentregablesEsHito());
			if (pEnt.getPentregablesDependencia() != null) {
				ent.setEntPredecesorFk(pEnt.getPentregablesDependencia());
			}
			ent.setEntInicio(plantillaFechaInicio.getTime());
			if (ent.getEntFinEsHito()) {
				ent.setEntFin(plantillaFechaInicio.getTime());
			} else {
				ent.setEntFin(DatesUtils.incrementarDias(plantillaFechaInicio, pEnt.getPentregableDuracion() - 1).getTime());
				ent.setEntDuracion(pEnt.getPentregableDuracion());
			}                         

                        /*
                        * 13-03-18 Nico: Posteriormente pide la fecha de la línea base en una validación
                        *           se la agrego a mano acá
                        */                                                 
                       /* 
                        ent.setEntInicioLineaBase(ent.getEntInicio());
                        ent.setEntFinLineaBase(ent.getEntFin());
                        */
                        
			cro.getEntregablesSet().add(ent);
			entregablesListAux.add(ent);
		}

		EntregablesValidacion.validarEntregablesPlantilla(entregablesListAux);

		/**
		 * Ajusta las fechas de las dependencias y de los entregables padres
		 */
		List<Entregables> entregables = EntregablesUtils.ajustarFechas(cro.getEntregablesSet());
		cro.getEntregablesSet().clear();
		cro.getEntregablesSet().addAll(entregables);
		return cro;
	}

	/**
	 *
	 * @param proyPk
	 * @param plantillaCro
	 * @param plantillaFechaInicio
	 * @param usuario
	 * @param orgPk
	 * @return Proyecto o Programa con el cronograma generado a partir de la
	 * plantilla
	 */
	public Object generarCroDesdePlantilla(Integer proyPk, PlantillaCronograma plantillaCro, Date plantillaFechaInicio, SsUsuario usuario, Integer orgPk) {

		Cronogramas cro = this.generarCronogramaDesdePlantilla(plantillaCro, plantillaFechaInicio);
                Proyectos proy = proyectosBean.obtenerProyPorId(proyPk);

                /*
                * 13-03-18 Nico: Agregue el control para ver si el Cronograma que viene de la operación
                *       "generarCronogramaDesdePlantilla" es vacio. Esto lo hice porque más abajo pide el
                *       Cronograma del proyecto y pisa la variable con la que llama a la operación
                *       "generarCronogramaDesdePlantilla".
                */
                    
                if (proy.getProyCroFk() != null) {
                    proy.getProyCroFk().getEntregablesSet().clear();
                } 
                
                /*                
                * 23-03-18 Nico: Dejo comentado el bloque else porque daba un problea
                *       cuando ingreso un cronograma a una ficha recién creada.
                */
                
                /*else {
                    cro.setCroEntSeleccionado(0);
                    cro.setCroPermisoEscritura(true);
                    cro.setCroPermisoEscrituraPadre(true);
                    cro.setEntregablesSet(new HashSet<Entregables>());
                } 
                */
                
                /*
                
                * 13-03-18 Nico: Hice los siguientes cambios:
                *       1- Seteo el proyecto al cronograma
                *       2- Pongo en "null" el valor de "Entregables" en "ProyOtrosDatos" dentro del proyecto. 
                */

                //cro.setProyecto(proy);
                
                cro.setCroEntSeleccionado(0);
                
                /*
                *   06-06-2018 Nico: Por no tener los dos valores de abajo seteados, se generaba un Bug al momento de cargar el cronograma desde las plantillas
                *           lo cual no permitía cargar ninguna de las operaciones de "GridEditor.prototype.bindRowInputEvents"
                */
                cro.setCroPermisoEscrituraPadre(true);
                cro.setCroPermisoEscritura(true);
                
                
                proy.getProyOtrosDatos().setProyOtrEntFk(null);
                proy.setProyCroFk(cro);
                
                //Seteo el coordinador a los Entregables
                for (Entregables ent : cro.getEntregablesSet()) {
                        ent.setCoordinadorUsuFk(proy.getProyUsrGerenteFk());
                }
                
		return cronogramasBean.guardarCronograma(cro, proyPk, TipoFichaEnum.PROYECTO.id, usuario, orgPk);
        }

}
