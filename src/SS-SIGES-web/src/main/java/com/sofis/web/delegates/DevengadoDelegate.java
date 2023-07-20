package com.sofis.web.delegates;

import com.sofis.business.ejbs.DevengadoBean;
import com.sofis.entities.data.Devengado;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class DevengadoDelegate {

	@Inject
	private DevengadoBean devengadoBean;

	public Devengado obtenerDevengado(Integer adqPk, short mesDev, short anioDev) {
		return devengadoBean.obtenerDevengado(adqPk, mesDev, anioDev);
	}

	public Date obtenerPrimeraFecha(List<Devengado> devList, boolean primera) {
		return devengadoBean.obtenerPrimeraFecha(devList, primera);
	}

	public Date obtenerUltimaFecha(List<Devengado> devList) {
		return devengadoBean.obtenerUltimaFecha(devList);
	}
}
