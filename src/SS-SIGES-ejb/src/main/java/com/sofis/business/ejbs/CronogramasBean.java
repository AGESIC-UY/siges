package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.properties.LabelsEJB;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.validations.CronogramaValidaciones;
import com.sofis.data.daos.CronogramasDAO;
import com.sofis.data.daos.EntregablesDAO;
import com.sofis.data.daos.ProgramasDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.ProyectosConCronograma;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
@Stateless(name = "CronogramasBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class CronogramasBean {

	@Inject
	private ProgramasBean programasBean;
	@Inject
	private ProyectosBean proyectosBean;
	@Inject
	private ProductosBean productosBean;
	@Inject
	private EntregablesBean entregablesBean;
	@Inject
	private DocumentosBean documentosBean;
	@Inject
	private ParticipantesBean participantesBean;
	@Inject
	private RegistrosHorasBean registrosHorasBean;
	@Inject
	private DatosUsuario du;
	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;
        private static final Logger logger = Logger.getLogger(CronogramasBean.class.getName());

	//private String usuario;
	//private String origen;
	@PostConstruct
	public void init() {
		//usuario = du.getCodigoUsuario();
		//origen = du.getOrigen();
	}

	public Cronogramas guardar(Cronogramas cro) {
		logger.fine("CronogramasBean.guardar");

		CronogramaValidaciones.validar(cro);
		CronogramasDAO cdao = new CronogramasDAO(em);

		try {
			cro = cdao.guardarCronograma(cro, du.getCodigoUsuario(), du.getOrigen());
			productosBean.actualizarProdPorEnt(cro.getEntregablesSet(), true);

		} catch (BusinessException | TechnicalException be) {
			logger.log(Level.SEVERE, null, be);
			throw be;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_CRO_GUARDAR);
			throw be;
		}

		return cro;
	}

        
        // Se crea esta operación para que el cronograma sea guardado cuando se carga la Ficha
        public Cronogramas guardarDespuesDeCheck(Cronogramas cro) {
		CronogramasDAO cdao = new CronogramasDAO(em);

		try {
			cro = cdao.guardarCronograma(cro, du.getCodigoUsuario(), du.getOrigen());
			productosBean.actualizarProdPorEnt(cro.getEntregablesSet(), true);

		} catch (BusinessException | TechnicalException be) {
			logger.log(Level.SEVERE, null, be);
			throw be;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_CRO_GUARDAR);
			throw be;
		}

		return cro;
	}
        
	public Object guardarCronograma(Cronogramas cro, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) {
		logger.fine("CronogramasBean.guardarCronograma");
		try {

			/**
			 * Verificar si marcas de inicio y fin de periodo
			 */
			boolean error = false;
			BusinessException be = new BusinessException();
			Integer cantInicioPeriodo = 0;
			Integer cantFinPeriodo = 0;
			Date inicioProyecto = null;
			Date finProyecto = null;
			for (Entregables e : cro.getEntregablesSet()) {
				if (e.getEntInicioProyecto()) {
					cantInicioPeriodo++;
					inicioProyecto = e.getEntInicioDate();
				}
				if (e.getEntFinProyecto()) {
					cantFinPeriodo++;
					finProyecto = e.getEntFinDate();
				}
//                if(e.getEntFinEsHito() && e.getEntParent()){
//                    be.addError(LabelsEJB.getValue(MensajesNegocio.ERROR_CRO_ENTREGABLE_HITO_PADRE));
//                    error = true;
//                }
			}

			if (cantInicioPeriodo > 1 || cantFinPeriodo > 1) {
				if (cantInicioPeriodo > 1) {
					be.addError(LabelsEJB.getValue(MensajesNegocio.ERROR_CRO_GUARDAR_MULTIPLE_INI_PERIODO));
					error = true;
				}
				if (cantFinPeriodo > 1) {
					be.addError(LabelsEJB.getValue(MensajesNegocio.ERROR_CRO_GUARDAR_MULTIPLE_FIN_PERIODO));
					error = true;
				}

			}

			if (inicioProyecto != null && finProyecto != null) {
				if (inicioProyecto.after(finProyecto)) {
					be.addError(LabelsEJB.getValue(MensajesNegocio.ERROR_CRO_GUARDAR_INI_FIN_PERIODO_INCONSISTENTE));
					error = true;
				}
			}

			if (error) {
				throw be;
			}

			eliminarEntregablesBorrados(cro);
			preProcesarEntregables(cro);
                        setEsfuerzoPadres(cro);

			cro = guardar(cro);

			if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
				Programas prog = programasBean.obtenerProgPorId(fichaFk);
				prog.setProgCroFk(cro);
				prog = programasBean.guardarPrograma(prog, usuario, orgPk);
				return prog;

			} else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
				Proyectos proy = proyectosBean.obtenerProyPorId(fichaFk);
				proy.setProyCroFk(cro);
				proy = proyectosBean.guardarProyecto(proy, usuario, orgPk);
				return proy;
			}
		} catch (GeneralException ex) {
			BusinessException be = new BusinessException(ex);
			be.addError(LabelsEJB.getValue(MensajesNegocio.ERROR_CRO_GUARDAR));
			be.addErrores(ex.getErrores());
			throw be;
		}

		return null;
	}

	public int[] calcularAvanceCronoFinalizadoProg(Set<Proyectos> proyectos) {
		//Toma como hipotesis que ya existe el indicador a nivel del proyecto.
		if (proyectos != null) {
			int[] calculo = {0, 0, 0};
			int[] result = {0, 0, 0};
			int azul = 0;
			int rojo = 0;
			int cont = 0;
			for (Proyectos proy : proyectos) {
				if (proy.getProyIndices() != null && proy.isActivo()) {
					Integer peso = proy.getProyPeso();
					calculo[0] = proy.getProyIndices().getProyindAvanceFinAzul();
					calculo[1] = proy.getProyIndices().getProyindAvanceFinVerde();
					calculo[2] = proy.getProyIndices().getProyindAvanceFinRojo();
					//calculo = calcularAvanceCronoFinalizado(proy.getProyCroFk().getEntregablesSet());
					if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
						azul += calculo[0] * peso;
						rojo += calculo[2] * peso;
						cont += peso;
					}
				}
			}

			if (cont != 0) {
				double azulD = azul / cont;
				double rojoD = rojo / cont;
				result = cargarYAjustarResultado(azulD, rojoD, result);
			}

			return result;
		}
		return null;
	}
        
        /*
        *   15-05-2018 Nico: Esta operación se hace para evitar hacer un loop entre los proyectos
        *           y los ProyIndices asociados.
        */
        
	public int[] calcularAvanceCronoFinalizadoProg(Integer progPk) {
		if (progPk != null) {
                    int[] calculo = {0, 0, 0};
                    int[] result = {0, 0, 0};
                        
                    ProgramasDAO programasDAO = new ProgramasDAO(em);
                        
                    try {
                        calculo = programasDAO.obtenerProcentajeIndiceFinalizado(progPk);
                    } catch (DAOGeneralException ex) {
                        Logger.getLogger(CronogramasBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    result = cargarYAjustarResultado(calculo[0], calculo[2], result);
                    return result;
		}
		return null;
	}        

	public int[] calcularAvanceCronoFinalizadoProgConCronograma(Set<ProyectosConCronograma> proyectos) {
		if (proyectos != null) {
			int[] calculo;
			int[] result = {0, 0, 0};
			int azul = 0;
			int rojo = 0;
			int cont = 0;

			for (ProyectosConCronograma proy : proyectos) {
				if (proy.getProyCroFk() != null && proy.getActivo()) {
					Integer peso = proy.getProyPeso();
					calculo = calcularAvanceCronoFinalizado(proy.getProyCroFk().getEntregablesSet());
					if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
						azul += calculo[0] * peso;
						rojo += calculo[2] * peso;
						cont += peso;
					}
				}
			}

			if (cont != 0) {
				double azulD = azul / cont;
				double rojoD = rojo / cont;
				result = cargarYAjustarResultado(azulD, rojoD, result);
			}

			return result;
		}
		return null;
	}

	public Cronogramas obtenerCronogramaPorProy(Integer proyPk) {
		CronogramasDAO dao = new CronogramasDAO(em);
		Cronogramas cro = dao.obtenerCronogramaProProy(proyPk);
		return cro;
	}

	public Cronogramas obtenerCronograma(Integer croPk) {
		CronogramasDAO dao = new CronogramasDAO(em);

		try {
			Cronogramas cro = dao.findById(Cronogramas.class, croPk);
			return cro;
		} catch (DAOGeneralException ex) {
			logger.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_CRO_GUARDAR);
			throw be;
		}
	}

	public int[] calcularAvanceCronoFinalizado(Cronogramas cro) {
		int[] result = {0, 0, 0};
		if (cro != null) {
			result = calcularAvanceCronoFinalizado(cro.getEntregablesSet());
		}
		return result;
	}

	public int[] calcularAvanceCronoParcial(Cronogramas cro) {
		int[] result = {0, 0, 0};
		if (cro != null) {
			result = calcularAvanceCronoParcial(cro.getEntregablesSet());
		}
		return result;
	}

	public int[] calcularAvanceCronoFinalizado(Set<Entregables> entregables) {
		int[] result = {0, 0, 0};
		double azul = 0;
		double rojo = 0;
		int esfuerzoTotal = 0;

		for (Entregables ent : entregables) {
			if (ent.getEntProgreso() >= 100) {
				azul += ent.getEntEsfuerzo();
            //            } else if ((ent.getEntFinLineaBase() != null
            //                    && ent.getEntFin() != null
            //                    && ent.getEntFinLineaBase() > 0
            //                    && ent.getEntFin() > ent.getEntFinLineaBase())
            //                    || DatesUtils.esMayor(new Date(), ent.getEntFinDate())) {
                        }else if (DatesUtils.esMayor(new Date(), ent.getEntFinDate())){
				rojo += ent.getEntEsfuerzo();
			}

			esfuerzoTotal += ent.getEntEsfuerzo();
		}

		if (esfuerzoTotal > 0) {
			double azulD = azul * 100 / esfuerzoTotal;
			double rojoD = rojo * 100 / esfuerzoTotal;
			result = cargarYAjustarResultado(azulD, rojoD, result);
		}

		return result;
	}

	public int[] calcularAvanceCronoParcialProg(Set<Proyectos> proyectos) {
		if (proyectos != null) {
			int[] calculo = {0, 0, 0};
			int[] result = {0, 0, 0};
			int azul = 0;
			int rojo = 0;
			int cont = 0;

			for (Proyectos proy : proyectos) {
				if (proy.getProyIndices() != null && proy.isActivo()) {
					Integer peso = proy.getProyPeso();
					//calculo = calcularAvanceCronoParcial(proy.getProyCroFk().getEntregablesSet());
					calculo[0] = proy.getProyIndices().getProyindAvanceParAzul();
					calculo[1] = proy.getProyIndices().getProyindAvanceParVerde();
					calculo[2] = proy.getProyIndices().getProyindAvanceParRojo();
					if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
						azul += calculo[0] * peso;
						rojo += calculo[2] * peso;
						cont += peso;
					}
				}
			}

			if (cont != 0) {
				double azulD = azul / cont;
				double rojoD = rojo / cont;
				result = cargarYAjustarResultado(azulD, rojoD, result);
			}

			return result;
		}
		return null;
	}
        
        
        /*
        *   15-05-2018 Nico: Esta operación se hace para evitar hacer un loop entre los proyectos
        *           y los ProyIndices asociados.
        */
        
	public int[] calcularAvanceCronoParcialProg(Integer progPk) {
		if (progPk != null) {
                    int[] calculo = {0, 0, 0};
                    int[] result = {0, 0, 0};
                        
                    ProgramasDAO programasDAO = new ProgramasDAO(em);
                        
                    try {
                        calculo = programasDAO.obtenerProcentajeIndiceParcial(progPk);
                    } catch (DAOGeneralException ex) {
                        Logger.getLogger(CronogramasBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    result = cargarYAjustarResultado(calculo[0], calculo[2], result);
                    return result;
		}
		return null;
	}        
        

	public int[] calcularAvanceCronoParcialProgConCronograma(Set<ProyectosConCronograma> proyectos) {
		if (proyectos != null) {
			int[] calculo;
			int[] result = {0, 0, 0};
			int azul = 0;
			int rojo = 0;
			int cont = 0;

			for (ProyectosConCronograma proy : proyectos) {
				if (proy.getProyCroFk() != null && proy.getActivo()) {
					Integer peso = proy.getProyPeso();
					calculo = calcularAvanceCronoParcial(proy.getProyCroFk().getEntregablesSet());
					if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
						azul += calculo[0] * peso;
						rojo += calculo[2] * peso;
						cont += peso;
					}
				}
			}

			if (cont != 0) {
				double azulD = azul / cont;
				double rojoD = rojo / cont;
				result = cargarYAjustarResultado(azulD, rojoD, result);
			}

			return result;
		}
		return null;
	}

	public int[] calcularAvanceCronoParcial(Set<Entregables> entregables) {
		if (entregables != null) {
			int[] result = {0, 0, 0};
			double azul = 0;
			double rojo = 0;
			double esfuerzoTotal = 0;

			for (Entregables ent : entregables) {
				esfuerzoTotal += ent.getEntEsfuerzo();
			}

			if (esfuerzoTotal != 0) {
				for (Entregables ent : entregables) {
					double pesoAzul = ent.getEntProgreso().doubleValue() * ent.getEntEsfuerzo().doubleValue() / 100;
					double porcentajeAzulDelTotal = pesoAzul * 100 / esfuerzoTotal;
					azul += porcentajeAzulDelTotal;
                    //                    if ((ent.getEntFinLineaBase() != null && ent.getEntFinLineaBase() > 0
                    //                            && ent.getEntFin() > ent.getEntFinLineaBase())
                    //                            || DatesUtils.esMayor(new Date(), ent.getEntFinDate())) {
                                        if(DatesUtils.esMayor(new Date(), ent.getEntFinDate())){
						rojo += (ent.getEntEsfuerzo().doubleValue() - pesoAzul) * 100 / esfuerzoTotal;
					}
				}

				result = cargarYAjustarResultado(azul, rojo, result);
			}
			return result;
		}
		return null;
	}

	public List<Entregables> obtenerResumenCronograma(Integer proyPk, int size) {
		if (proyPk != null) {
			EntregablesDAO eDao = new EntregablesDAO(em);
			List<Entregables> entregables = eDao.obtenerEntregablesPorProy(proyPk);
			List<Entregables> result = new ArrayList<>();
			for (Entregables ent : entregables) {
				if (ent.getEntEsfuerzo() > 0 && ent.getEntProgreso() < 100
						&& DatesUtils.esMayor(new Date(), ent.getEntFinDate())) {
					result.add(ent);
				}
			}
			return EntregablesUtils.primerosEntregables(EntregablesUtils.sortByEsfuerzo(result, true), size);
		}
		return null;
	}

	public Integer esfuerzoTotal(Cronogramas cro) {
		if (cro != null && CollectionsUtils.isNotEmpty(cro.getEntregablesSet())) {
			Integer result = 0;
			for (Entregables ent : cro.getEntregablesSet()) {
				if (ent.getEntEsfuerzo() != null) {
					result += ent.getEntEsfuerzo();
				}
			}
			return result;
		}
		return null;
	}

	/**
	 * Calcula el esfurzo total solo considerando los hitos
	 *
	 * @param cro
	 * @return
	 */
	public Integer esfuerzoTotalHitos(Cronogramas cro) {
		if (cro != null && CollectionsUtils.isNotEmpty(cro.getEntregablesSet())) {
			Integer result = 0;
			for (Entregables ent : cro.getEntregablesSet()) {
				if (ent.getEntFinEsHito() != null && ent.getEntFinEsHito().booleanValue() && ent.getEntEsfuerzo() != null) {
					result += ent.getEntEsfuerzo();
				}
			}
			return result;
		}
		return null;
	}

	public String horasTotales(Cronogramas cro) {
		if (cro != null && CollectionsUtils.isNotEmpty(cro.getEntregablesSet())) {
			String result = "";
			for (Entregables ent : cro.getEntregablesSet()) {
				if (!StringsUtils.isEmpty(ent.getEntHorasEstimadas())) {
					result = DatesUtils.sumarHora(result, ent.getEntHorasEstimadas());
				}
			}
			return result;
		}
		return null;
	}

	public Cronogramas copiarProyCronograma(Cronogramas proyCroFk, int desfasajeDias) {
		if (proyCroFk != null) {
			Cronogramas nvoCro = new Cronogramas();
			nvoCro.setCroEntBorrados(proyCroFk.getCroEntBorrados());
			nvoCro.setCroEntSeleccionado(proyCroFk.getCroEntSeleccionado());
			nvoCro.setCroPermisoEscritura(proyCroFk.getCroPermisoEscritura());
			nvoCro.setCroPermisoEscrituraPadre(proyCroFk.getCroPermisoEscrituraPadre());
			nvoCro.setCroResources(proyCroFk.getCroResources());
			nvoCro.setEntregablesSet(entregablesBean.copiarProyEntregables(proyCroFk.getEntregablesSet(), nvoCro, desfasajeDias));
			return nvoCro;
		}
		return null;
	}

	private void eliminarEntregablesBorrados(Cronogramas cro) {
		if (cro != null && !StringsUtils.isEmpty(cro.getCroEntBorrados())) {
			Set<Entregables> entSet = cro.getEntregablesSet();
			cro = obtenerCronograma(cro.getCroPk());
			Set<Entregables> entSetPerist = cro.getEntregablesSet();
			List<Entregables> borradosList = new ArrayList<>();

			for (Entregables ent : entSetPerist) {
				if (!entSet.contains(ent)) {
					borradosList.add(ent);
				}
			}

			if (!borradosList.isEmpty()) {
				Estados est = cro.getProyecto().getProyEstFk();
				if (!(est.isEstado(Estados.ESTADOS.INICIO.estado_id) || est.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id))) {
					BusinessException be = new BusinessException();
					be.addError(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_ESTADO);
					throw be;
				}

				for (Entregables borrado : borradosList) {
					boolean avance = borrado.getEntProgreso() != null && borrado.getEntProgreso() > 0;
					boolean horasAsociadas = registrosHorasBean.tieneDependenciasEnt(borrado.getEntPk());
					boolean partAsociados = participantesBean.tieneDependenciasEnt(borrado.getEntPk());
//                    boolean tieneProductos = productosBean.tieneProdPorEnt(borrado.getEntPk());
					boolean tieneDependencias = entregablesBean.tieneDependencias(borrado.getEntPk());

					if (tieneDependencias) {
						BusinessException be = new BusinessException();
						be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_DEPEDENCIAS), borrado.getEntNombre()));
						throw be;
//                    } else if (tieneProductos) {
//                        BusinessException be = new BusinessException();
//                        be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_PRODUCTOS), borrado.getEntNombre()));
//                        throw be;
					} else if (avance) {
						BusinessException be = new BusinessException();
						be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_AVANCE), borrado.getEntNombre()));
						throw be;
					} else if (horasAsociadas) {
						BusinessException be = new BusinessException();
						be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_HORAS), borrado.getEntNombre()));
						throw be;
					} else if (partAsociados) {
						BusinessException be = new BusinessException();
						be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_PART), borrado.getEntNombre()));
						throw be;
					}
					documentosBean.quitarEntregable(borrado.getEntPk());
				}
			}
		}
	}

	//redondea a x decimales
	public static double round(double value, int places) {
		if (places < 0) {
			return 0;
		}

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	/**
	 * Dado los valores de azul y rojo, los redondea y luego calcula el valor
	 * del verde.
	 *
	 * @param azul
	 * @param rojo
	 * @param result
	 * @return Array de int con los valores del azul, verde y rojo.
	 */
	private int[] cargarYAjustarResultado(double azul, double rojo, int[] result) {
		int azulInt;
		int rojoInt;

		azul = azul > 100 ? 100 : azul;
		rojo = rojo > 100 ? 100 : rojo;

		azul = round(azul, 2);
		rojo = round(rojo, 2);

		double mod = azul % 1;
		double modRojo = rojo % 1;

		mod = round(mod, 2);
		modRojo = round(modRojo, 2);

		//casos del algorimto 
		//caso 1: que ya la suma da mayor que 100 el más dificl de arreglar. Se tiene que restar algun numero para que de 100, no es clara a cual. Se resta al que el mod sea neor a 0.5
		//        No queda claro que esto pueda pasar, pero este algoritmo es defensivo y lo arregla.   
		//caso 2: que da 100  pero los dos dan 0,5 entonces hace mal el tail
		//caso 3: que da menor a 100 (este caso nunca se comporta mal)
		if ((azul + rojo) > 100d) {
			//caso 1
			//se tiene que arreglar y hacer que sume 100
			double cantidadRestar = (100.0d - (azul + rojo));

			if (mod < 0.5) {
				azul = azul + cantidadRestar;
			} else if (modRojo < 0.5) {
				rojo = rojo + cantidadRestar;
			} else {
				azul = azul + cantidadRestar;
			}

			mod = azul % 1;
			modRojo = rojo % 1;

			mod = round(mod, 2);
			modRojo = round(modRojo, 2);

		}

		if (mod == 0.5 && modRojo == 0.5) {
			//caso 2
			azulInt = (int) Math.abs(Math.ceil(azul));
			rojoInt = (int) Math.abs(Math.floor(rojo));

		} else {
			if (mod >= 0.5) {
				azulInt = (int) Math.abs(Math.ceil(azul));
			} else {
				azulInt = (int) Math.abs(Math.floor(azul));
			}

			if (modRojo >= 0.5) {
				rojoInt = (int) Math.abs(Math.ceil(rojo));
			} else {
				rojoInt = (int) Math.abs(Math.floor(rojo));
			}
		}

		result[0] = azulInt;
		result[2] = rojoInt;
		int verde = 100 - result[0] - result[2];
		result[1] = verde;

		// prorrateo
		int suma = result[0] + result[1] + result[2];
		if (suma > 100) {
			result[1] = (result[0] / suma) * 100;
			result[2] = (result[0] / suma) * 100;
			result[3] = (result[0] / suma) * 100;
		}
		return result;
	}

	/**
	 * Agrega los nombres a los entregables, setea valores si es hito, y
	 * modifica línea base si pasó un entregable a hito.
	 *
	 * @param cro
	 */
	private void preProcesarEntregables(Cronogramas cro) {
		if (cro != null && cro.getEntregablesSet() != null) {
			for (Entregables ent : cro.getEntregablesSet()) {
				if (StringsUtils.isEmpty(ent.getEntNombre())) {
					ent.setEntNombre(LabelsEJB.getValue("entregable") + " " + (ent.getEntId() != null ? ent.getEntId() : ""));
				}
				if (ent.getEntFinEsHito() != null && ent.getEntFinEsHito()) {
					ent.setEntInicio(ent.getEntFin());
					ent.setEntDuracion(1);
					if (ent.getEntDuracionLineaBase() != null && ent.getEntDuracionLineaBase() > 1) {
						ent.setEntInicioLineaBase(ent.getEntInicio());
						ent.setEntFinLineaBase(ent.getEntFin());
						ent.setEntDuracionLineaBase(ent.getEntDuracion());
					}
				}
			}
		}
	}
        
    private void setEsfuerzoPadres(Cronogramas cro) { 
        for (Entregables entregable : cro.getEntregablesSet()) { 
            if (entregable.esEntParent()) { 
                entregable.setEntEsfuerzo(0); 
                entregable.setEntProgreso(0); 
            } 
        } 
    }

}
