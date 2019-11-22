package com.sofis.business.ejbs;

import com.sofis.data.daos.ProgIndicesDAO;
import com.sofis.data.daos.ProgramasDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.ProgIndices;
import com.sofis.entities.data.ProgIndicesPre;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Named
@Stateless(name = "ProgIndicesBean")
@LocalBean
public class ProgIndicesBean {

	private static final Logger logger = Logger.getLogger(ProgIndicesBean.class.getName());
	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@EJB
	private ProgramasBean programasBean;
	@EJB
	private ProyectosBean proyectoBean;
	@EJB
	private RiesgosBean riesgosBean;
	@EJB
	private DocumentosBean documentosBean;
	@EJB
	private CronogramasBean cronogramasBean;
	@EJB
	private CalidadBean calidadBean;
	@EJB
	private PresupuestoBean presupuestoBean;

	public ProgIndices guardar(ProgIndices ind, Integer progPk) {
            
		ProgIndicesDAO dao = new ProgIndicesDAO(em);
		try {
                        /*
                        * 17-04-2018 Nico: Se redefine la manera de setear la fecha buscando en la menor de las fecha de actualización
                        *           de los proyectos del programa. Se hace un doble chequeo de "(progPk != null)" para evitar 
                        *           modificar mucho el código.
                        */
                        /*
                        if(progPk != null){
                            ind.setProgindFechaAct(programasBean.obtenerFechaActualización(progPk));
                        }else{*/
                            ind.setProgindFechaAct(new Date());
                        //}
                    
                        boolean isNew = ind != null && ind.getProgindPk() == null;

			if (isNew && progPk != null) {
                            /*
                            *   17-04-2018 Nico: Primero me fijo si es nuevo para poder crear el indice "dummy",
                            *           es decir, poner una  FechaActColor "dummy" para que no explote.
                            */
                            ind.setProgindFechaActColor(0);
                            
                            ind = dao.update(ind);
                            
                            ProgramasDAO pDao = new ProgramasDAO(em);
                            Programas prog = pDao.findById(Programas.class, progPk);
                            prog.setProgIndices(ind);
                            pDao.update(prog);
			}else{
                            /*
                            *   17-04-2018 Nico: Si el indice ya existe, lo guardo así como esta. De la manera que estaba antes,
                            *           el seteo de los valores "dummy" sobrescribiran los valores calculados.
                            */
                            
                            ind = dao.update(ind);
                        }
		} catch (DAOGeneralException ex) {
			logger.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_PROY_IND_GUARDAR);
		}
		return ind;
	}

	public ProgIndices obtenerIndicadores(Integer progPk, Integer orgPk) {
		ProgIndicesDAO progDao = new ProgIndicesDAO(em);
		ProgIndices pInd = null;
		try {
			pInd = progDao.obtenerIndicePorProgId(progPk);
			if (pInd == null) {
				pInd = guardarIndicadoresNewTrans(progPk, orgPk);
			}
			return pInd;
		} catch (Exception ex) {
			logger.logp(Level.SEVERE, ProyectosBean.class.getName(), "obtenerIndices", ex.getMessage(), ex);
			TechnicalException ge = new TechnicalException(ex);
			ge.addError(ex.getMessage());
			throw ge;
		}
	}

	/**
	 * Calcula los indices y los guarda.
	 *
	 * @param progPk
	 * @param orgPk
	 * @return ProgIndices
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ProgIndices guardarIndicadoresNewTrans(Integer progPk, Integer orgPk) {
		return guardarIndicadores(progPk, orgPk);
	}

	/**
	 * MARCA SCHEDULE
	 */
	public ProgIndices guardarIndicadores(Integer progPk, Integer orgPk) {
		if (progPk != null) {
			try {
				ProgIndicesDAO dao = new ProgIndicesDAO(em);
				ProgIndices ind = dao.obtenerIndicePorProgId(progPk);
				ProgramasDAO progDAO = new ProgramasDAO(em);
				Programas prog = progDAO.findById(Programas.class, progPk);

				if (ind == null) {
					ind = new ProgIndices();
					/**
					 * [BRUNO] 17-01-19: error null cuando el programa no tiene
					 * índices.
					 */
					ind = guardar(ind, progPk);
				}

				Set<Proyectos> setProyectos = proyectoBean.obtenerProySimplePorProgId(progPk);
				//Riesgo
				//ind.setProgindRiesgoAlto(riesgosBean.obtenerCantRiesgosAltosPrograma(setProyectos, orgPk));
				ind.setProgindRiesgoAlto(riesgosBean.obtenerCantRiesgosAltosPrograma(progPk, orgPk));
				//ind.setProgindRiesgoExpo(riesgosBean.obtenerExposicionRiesgoPrograma(setProyectos));
				ind.setProgindRiesgoExpo(riesgosBean.obtenerExposicionRiesgoPrograma(progPk));

				//ind.setProgindRiesgoUltact(riesgosBean.obtenerDateUltimaActualizacionPrograma(setProyectos));
				ind.setProgindRiesgoUltact(riesgosBean.obtenerDateUltimaActualizacionPrograma(progPk));

				//Metodologia
				ind.setProgindMetodologiaEstado(documentosBean.calcularIndiceEstadoMetodologiaPrograma(setProyectos));
				ind.setProgindMetodologiaSinAprobar(documentosBean.calcularIndiceMetodologiaSinAprobarProgramas(progPk));

				//Periodo
				// ind.setProgindPeriodoInicio(programasBean.obtenerPrimeraFecha(setProyectos));
				ind.setProgindPeriodoInicio(programasBean.obtenerPrimeraFecha(progPk));
				//ind.setProgindPeriodoFin(programasBean.obtenerUltimaFecha(setProyectos));
				ind.setProgindPeriodoFin(programasBean.obtenerUltimaFecha(progPk));

				//Calidad
				ind.setProgindCalInd(calidadBean.obtenerIndiceCalidadPrograma(setProyectos, orgPk));
				//ind.setProgindCalPend(calidadBean.obtenerPendCalidadPrograma(setProyectos));
				ind.setProgindCalPend(calidadBean.obtenerPendCalidadPrograma(progPk));

				//Avance
				int[] iaf = cronogramasBean.calcularAvanceCronoFinalizadoProg(progPk);
				int[] iap = cronogramasBean.calcularAvanceCronoParcialProg(progPk);
				ind.setProgindAvanceParAzul(iap[0]);
				ind.setProgindAvanceParVerde(iap[1]);
				ind.setProgindAvanceParRojo(iap[2]);
				ind.setProgindAvanceFinAzul(iaf[0]);
				ind.setProgindAvanceFinVerde(iaf[1]);
				ind.setProgindAvanceFinRojo(iaf[2]);

				//Presupuesto
				cargarIndPresupuesto(progPk, ind, setProyectos, orgPk);

				//Fecha actualizacion
                                /*
                                *   16-05-2018 Nico: Se cambia la operación obtenerUltimaActualizacionPrograma en ProyectosBean, 
                                            por obtenerFechaActualización en ProgramasBean
                                */
				ind.setProyActualizacion(programasBean.obtenerFechaActualización(progPk));

				/**
				 * Color de la actualizacion
				 */
                                ind.setProgindFechaActColor(proyectoBean.obtenerUltimaActualizacionColorNumero(prog.getProgEstFk(),
                                            prog.getProgFechaAct(), prog.getProgSemaforoAmarillo(), prog.getProgSemaforoRojo()));
                                
				ind = guardar(ind, progPk);
				return ind;
			} catch (DAOGeneralException ex) {
				Logger.getLogger(ProgIndicesBean.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return null;
	}

	private void cargarIndPresupuesto(Integer progPk, ProgIndices ind, Set<Proyectos> setProyectos, Integer orgPk) {

		if (ind != null) {

			Map<Integer, Double> totalMoneda = new HashMap<>();
			List<Moneda> monedas = presupuestoBean.obtenerMonedasPresupuestoPrograma(progPk, null);

			Presupuesto pre;
			Double total;
			for (Proyectos proy : setProyectos) {
				pre = presupuestoBean.obtenerPresupuestoPorProy(proy.getProyPk());
				proy.setProyPreFk(pre);
				if (proy.isActivo() && proy.getProyPreFk() != null) {
					for (Moneda moneda : monedas) {
						total = presupuestoBean.obtenerTotalPorMoneda(proy.getProyPreFk().getPrePk(), moneda);
						if (totalMoneda.containsKey(moneda.getMonPk())) {
							total += totalMoneda.get(moneda.getMonPk());
						}
						totalMoneda.put(moneda.getMonPk(), total);
					}
				}
			}

			Integer monPk = null;
			for (Moneda moneda : monedas) {
				monPk = moneda.getMonPk();

				ProgIndicesPre progIndicesPre = null;
				if (ind.getProgIndPreSet() != null) {
					for (ProgIndicesPre progIndPre : ind.getProgIndPreSet()) {
						if (monPk.equals(progIndPre.getProgindpreMonFk())) {
							progIndicesPre = progIndPre;
							break;
						}
					}
				}

				if (progIndicesPre == null) {
					progIndicesPre = new ProgIndicesPre();
					progIndicesPre.setProgindpreMonFk(monPk);
					progIndicesPre.setProgindpreProgindFk(ind.getProgindPk());
				}

				int estado = presupuestoBean.obtenerEstadoAC(setProyectos, monPk, orgPk, null, null);
				progIndicesPre.setProgindpreEstPre(estado);
				progIndicesPre.setProgindpreTotal(totalMoneda.get(monPk));
				Calendar calNow = new GregorianCalendar();
				Integer anio = calNow.get(Calendar.YEAR);
				progIndicesPre.setProgindpreAnio(presupuestoBean.obtenerTotalPorMonedaAnioProg(progPk, moneda, anio));
				progIndicesPre.setProgindprePV(presupuestoBean.obtenerPVPorMonedaProg(progPk, monPk));
				progIndicesPre.setProgindpreAC(presupuestoBean.obtenerACPorMonedaProg(progPk, monPk));

				if (progIndicesPre.getProgindprePk() == null) {
					ind.getProgIndPreSet().add(progIndicesPre);
				}
			}

			if (ind.getProgIndPreSet() != null) {
				List<ProgIndicesPre> listMonedaRemove = new ArrayList<>();
				Moneda mon;
				for (ProgIndicesPre progIndicesPre : ind.getProgIndPreSet()) {
					mon = new Moneda(progIndicesPre.getProgindpreMonFk());
					if (!monedas.contains(mon)) {
						listMonedaRemove.add(progIndicesPre);
					}
				}
				ind.getProgIndPreSet().removeAll(listMonedaRemove);
			}
		}
	}
}
