package com.sofis.business.utils;

import com.sofis.entities.data.Proyectos;
import com.sofis.entities.tipos.ProyectoTO;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class ProyectosUtils {

    /**
     * Ordenar la lista de entregables por la descripción
     *
     * @param listProyectos
     * @return List
     */
    public static List<Proyectos> sortByNombre(List<Proyectos> listProyectos) {
        if (CollectionsUtils.isNotEmpty(listProyectos)) {
            Collections.sort(listProyectos, new Comparator<Proyectos>() {
                @Override
                public int compare(Proyectos p1, Proyectos p2) {
                    if (p1 != null && p1.getProyNombre() != null && p2 != null && p2.getProyNombre() != null) {
                        return p1.getProyNombre().compareTo(p2.getProyNombre());
                    }
                    return 0;
                }
            });
        }

        return listProyectos;
    }
    
    public static ProyectoTO convert(Proyectos proyecto) {

		ProyectoTO result = new ProyectoTO();
		result.setId(proyecto.getProyPk());
		result.setNombre(proyecto.getProyNombre());

		return result;
	}

}
