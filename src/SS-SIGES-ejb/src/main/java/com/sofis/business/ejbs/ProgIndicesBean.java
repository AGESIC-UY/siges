package com.sofis.business.ejbs;

import com.sofis.data.daos.ProgIndicesDAO;
import com.sofis.data.daos.ProgramasDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
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

@Named
@Stateless(name = "ProgIndicesBean")
@LocalBean
public class ProgIndicesBean {

	private static final Logger LOGGER = Logger.getLogger(ProgIndicesBean.class.getName());
	
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
	@EJB
	private ConfiguracionBean configuracionBean;

	public ProgIndices guardar(ProgIndices ind, Integer progPk) {

		ProgIndicesDAO dao = new ProgIndicesDAO(em);
		try {
			ind.setProgindFechaAct(new Date());
			
			boolean isNew = ind.getProgindPk() == null;

			if (isNew && progPk != null) {

				ind.setProgindFechaActColor(0);

				ind = dao.update(ind);

				ProgramasDAO pDao = new ProgramasDAO(em);
				Programas prog = pDao.findById(Programas.class, progPk);
				prog.setProgIndices(ind);
				pDao.update(prog);
			} else {

				ind = dao.update(ind);
			}
		} catch (DAOGeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_PROY_IND_GUARDAR);
		}
		
		return ind;
	}

	public ProgIndices obtenerIndicadores(Integer progPk, Integer orgPk) {
		ProgIndicesDAO progDao = new ProgIndicesDAO(em);
		ProgIndices pInd;
		try {
			pInd = progDao.obtenerIndicePorProgId(progPk);
			if (pInd == null) {
				pInd = guardarIndicadoresNewTrans(progPk, orgPk);
			}
			
			return pInd;
			
		} catch (Exception ex) {
			LOGGER.logp(Level.SEVERE, ProyectosBean.class.getName(), "obtenerIndices", ex.getMessage(), ex);
			TechnicalException ge = new TechnicalException(ex);
			ge.addError(ex.getMessage());
			throw ge;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ProgIndices guardarIndicadoresNewTrans(Integer progPk, Integer orgPk) {
		
		return guardarIndicadores(progPk, orgPk);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ProgIndices guardarIndicadores(Programas prog) {

		Integer progPk = prog.getProgPk();
		Integer orgPk = prog.getProgOrgFk().getOrgPk();

		ProgIndicesDAO dao = new ProgIndicesDAO(em);
		ProgIndices ind = dao.obtenerIndicePorProgId(progPk);

		if (ind == null) {
			ind = new ProgIndices();
			ind = guardar(ind, progPk);
		}

		Boolean incluirFinalizados = Boolean.valueOf(configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.INCLUIR_CALCULAR_FINALIZADOS, orgPk));
		Set<Proyectos> setProyectos = proyectoBean.obtenerProySimplePorProgId(progPk);
		
		//Riesgo
		ind.setProgindRiesgoAlto(riesgosBean.obtenerCantRiesgosAltosPrograma(progPk, orgPk));
		ind.setProgindRiesgoExpo(riesgosBean.obtenerExposicionRiesgoPrograma(progPk));
		ind.setProgindRiesgoUltact(riesgosBean.obtenerDateUltimaActualizacionPrograma(progPk));

		//Metodologia
		ind.setProgindMetodologiaEstado(documentosBean.calcularIndiceEstadoMetodologiaPrograma(setProyectos, incluirFinalizados));
		ind.setProgindMetodologiaSinAprobar(documentosBean.calcularIndiceMetodologiaSinAprobarProgramas(progPk, incluirFinalizados));

		//Periodo
		ind.setProgindPeriodoInicio(programasBean.obtenerPrimeraFecha(progPk));
		ind.setProgindPeriodoFin(programasBean.obtenerUltimaFecha(progPk));

		//Calidad
		ind.setProgindCalInd(calidadBean.obtenerIndiceCalidadPrograma(setProyectos, incluirFinalizados, orgPk));
		ind.setProgindCalPend(calidadBean.obtenerPendCalidadPrograma(progPk, incluirFinalizados));

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
		ind.setProyActualizacion(programasBean.obtenerFechaActualización(progPk));

		//color
		ind.setProgindFechaActColor(proyectoBean.obtenerUltimaActualizacionColorNumero(prog.getProgEstFk(),
				prog.getProgFechaAct(), prog.getProgSemaforoAmarillo(), prog.getProgSemaforoRojo()));

		ind = guardar(ind, progPk);

		return ind;
	}

	public ProgIndices guardarIndicadores(Integer progPk, Integer orgPk) {

		try {
			ProgramasDAO progDAO = new ProgramasDAO(em);
			Programas prog = progDAO.findById(Programas.class, progPk);

			return guardarIndicadores(prog);

		} catch (DAOGeneralException ex) {
			Logger.getLogger(ProgIndicesBean.class.getName()).log(Level.SEVERE, null, ex);
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
