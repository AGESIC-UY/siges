package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.DevengadoDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Devengado;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "DevengadoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class DevengadoBean {

    private static final Logger LOGGER = Logger.getLogger(DevengadoBean.class.getName());
    
	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    
    public Devengado obtenerDevengado(Integer adqPk, short mesDev, short anioDev) {
        if (adqPk != null && mesDev > 0 && anioDev > 0) {
            DevengadoDAO dao = new DevengadoDAO(em);
            return dao.obtenerDevengado(adqPk, mesDev, anioDev);
        }
        return null;
    }

    public Date obtenerPrimeraFechaPorAdq(List<Adquisicion> adqList, boolean primera) {
        if (adqList != null) {
            Date result = null;
            for (Adquisicion adq : adqList) {
                Date date = obtenerPrimeraFecha(adq.getDevengadoList(), primera);
                if (result == null || (date != null && DatesUtils.esMayor(result, date))) {
                    result = date;
                }
            }
            return result;
        }
        return null;
    }

    public Date obtenerPrimeraFecha(List<Devengado> devList, boolean primera) {
        if (devList != null) {
            Calendar result = null;
            if (CollectionsUtils.isNotEmpty(devList)) {
                for (Devengado dev : devList) {
                    Calendar cal = new GregorianCalendar(dev.getDevAnio(), dev.getDevMes() - 1, 1);
                    boolean b;
                    if (primera) {
                        b = cal.before(result);
                    } else {
                        b = cal.after(result);
                    }
                    if (result == null || b) {
                        result = cal;
                    }
                }
            }
            return result != null ? result.getTime() : null;
        }
        return null;
    }

    public Date obtenerUltimaFecha(List<Devengado> devList) {
        Calendar result = null;
        if (CollectionsUtils.isNotEmpty(devList)) {
            for (Devengado dev : devList) {
                Calendar cal = new GregorianCalendar(dev.getDevAnio(), dev.getDevMes() - 1, 1);
                if (result == null || cal.after(result)) {
                    result = cal;
                }
            }
        }
        return result != null ? result.getTime() : null;
    }

    public List<Devengado> copiarProyDevengado(List<Devengado> devengadoList, Adquisicion nvaAdq, int desfasajeDias) {
        if (devengadoList != null && nvaAdq != null) {
            List<Devengado> result = new ArrayList<>();

            for (Devengado dev : devengadoList) {
                Devengado nvoDev = new Devengado();
                nvoDev.setDevAdqFk(dev.getDevAdqFk());

                Calendar cal = new GregorianCalendar();
                cal.set(dev.getDevAnio(), dev.getDevMes(), 1);
                Date date = DatesUtils.incrementarDias(cal.getTime(), desfasajeDias);
                cal.setTime(date);
                nvoDev.setDevAnio((short) cal.get(Calendar.YEAR));
                nvoDev.setDevMes((short) cal.get(Calendar.MONTH));

                nvoDev.setDevPlan(dev.getDevPlan());
                nvoDev.setDevReal(dev.getDevReal());

                result.add(nvoDev);
            }

            return result;
        }
        return null;
    }
}
