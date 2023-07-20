package com.sofis.business.utils;

import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Productos;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Riesgos;
import java.util.Set;
import java.util.regex.Pattern;

public abstract class SIGESIndicadoresUtils {

	private static final Pattern NOMBRE_SIGES_INDICADORES_PATTERN = Pattern.compile("^#\\d+#(\\d+)");
		
	public static void ajustarNomenclaturaEntidades(Proyectos proyecto) {
		
		Integer idProyecto = proyecto.getProyPk();

		if (proyecto.getProyCroFk() != null) {

			Set<Entregables> entregables = proyecto.getProyCroFk().getEntregablesSet();

			for (Entregables entregable : entregables) {

				entregable.setEntNombre(NOMBRE_SIGES_INDICADORES_PATTERN.matcher(entregable.getEntNombre()).replaceFirst("#" + idProyecto + "#$1"));

				for (Productos producto : entregable.getEntProductosSet()) {

					producto.setProdDesc(NOMBRE_SIGES_INDICADORES_PATTERN.matcher(producto.getProdDesc()).replaceFirst("#" + idProyecto + "#$1"));
				}
			}
		}

		for (Riesgos riesgo : proyecto.getRiesgosList()) {

			riesgo.setRiskNombre(NOMBRE_SIGES_INDICADORES_PATTERN.matcher(riesgo.getRiskNombre()).replaceFirst("#" + idProyecto + "#$1"));
		}
	}
}
