package com.sofis.web.delegates;

import com.sofis.business.ejbs.EtiquetaBean;
import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.data.Etiqueta;
import com.sofis.exceptions.GeneralException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class EtiquetaDelegate {

	@Inject
	private EtiquetaBean etiquetaBean;

	public Map<String, String> obtenerEtiquetasPorOrgId(Integer orgId) throws GeneralException {

		List<Etiqueta> etiquetas = etiquetaBean.obtenerEtiquetasPorOrgId(orgId);

		Map<String, String> result = new HashMap<>();

		for (Etiqueta etiqueta : etiquetas) {
			result.put(etiqueta.getCodigo(), etiqueta.getValor());
		}

		return result;
	}

	public void cargarEtiquetasOrganismo(Integer orgId) {

		LabelsEJB.setEtiquetasOrganismo(obtenerEtiquetasPorOrgId(orgId), orgId);
	}

	public String obtenerEtiqueta(String codigo, Integer orgId) {

		return LabelsEJB.getValue(codigo, orgId);
	}
}
