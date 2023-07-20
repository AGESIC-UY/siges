package com.sofis.web.delegates;

import com.sofis.business.ejbs.ConfiguracionDefectoBean;
import com.sofis.entities.data.ConfiguracionDefecto;
import com.sofis.exceptions.GeneralException;
import java.io.Serializable;
import javax.inject.Inject;

public class ConfiguracionDefectoDelegate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConfiguracionDefectoBean cnfBean;

	public ConfiguracionDefecto guardar(ConfiguracionDefecto cnf) throws GeneralException {
		return cnfBean.guardar(cnf);
	}

	public ConfiguracionDefecto obtenerPorId(Integer id) throws GeneralException {
		return cnfBean.obtenerPorId(id);
	}

}
