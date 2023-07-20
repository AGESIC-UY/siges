package com.sofis.business.ejbs;

import com.sofis.data.daos.CalculoIndicadoresAgendadoDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.CalculoIndicadoresAgendado;
import com.sofis.entities.data.Programas;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "CalculoIndicadoresAgendadoBean")
@LocalBean
public class CalculoIndicadoresAgendadoBean {

	private static final Logger LOGGER = Logger.getLogger(CalculoIndicadoresAgendadoBean.class.getName());
	
	private static final String MUTEX_ID = "INDICADORES_PROGRAMA";
	
	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@Inject
	private ProgIndicesBean progIndicesBean;

	@Inject
	private MutexBean mutexBean;
	
	@Schedule(hour = "*", minute = "*", persistent = false, info= "calculo de indicadores de programasagendados")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void recalcularIndicadoresProgramas() {
		int lock = -1;
		try {
			lock = mutexBean.obtenerLock(MUTEX_ID);
			if (lock != 0) {
				return;
			}

			calcularIndicadoresAgendados();

		} finally {
			if (lock == 0) {
				LOGGER.fine("Intentando liberar el lock " + MUTEX_ID);
				lock = mutexBean.liberarLock(MUTEX_ID);
				LOGGER.log(Level.FINE, "Liberacion de lock, resp: {0}", lock);
			}
		}
	}
	
	private void calcularIndicadoresAgendados() {

		CalculoIndicadoresAgendadoDAO recalculoIndicadoresAgendadoDAO = new CalculoIndicadoresAgendadoDAO(em);

		List<CalculoIndicadoresAgendado> obtenerPendientes = recalculoIndicadoresAgendadoDAO.obtenerPendientes();

		if (!obtenerPendientes.isEmpty()) {
			
			LOGGER.log(Level.FINE, "Calculando indicadores de {0} programas ", obtenerPendientes.size());
		}
		
		for (CalculoIndicadoresAgendado ria : obtenerPendientes) {

			eliminar(ria);

			progIndicesBean.guardarIndicadores(ria.getPrograma());
		}
	}

	public void agendarCalculoIndicadores(Programas programa) {
            
            CalculoIndicadoresAgendadoDAO recalculoIndicadoresAgendadoDAO = new CalculoIndicadoresAgendadoDAO(em);

            if (recalculoIndicadoresAgendadoDAO.existeProgramaAgendado(programa.getProgPk())) {
                    return;
            }

            CalculoIndicadoresAgendado cia = new CalculoIndicadoresAgendado();
            cia.setPrograma(programa);

            recalculoIndicadoresAgendadoDAO.crear(cia);

            LOGGER.log(Level.FINE, "Se agenda programa {0} para calculo de indicadores", programa.getProgPk());
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private void eliminar(CalculoIndicadoresAgendado ria) {

		CalculoIndicadoresAgendadoDAO recalculoIndicadoresAgendadoDAO = new CalculoIndicadoresAgendadoDAO(em);

		recalculoIndicadoresAgendadoDAO.eliminar(ria);
	}
}
