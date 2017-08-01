package com.sofis.business.utils;

import com.sofis.entities.data.CategoriaProyectos;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CategoriaProyectosUtils {

    public static List<CategoriaProyectos> sortByNombre(List<CategoriaProyectos> listCP) {
        if (CollectionsUtils.isNotEmpty(listCP)) {
            Collections.sort(listCP, new Comparator<CategoriaProyectos>() {
                @Override
                public int compare(CategoriaProyectos cp1, CategoriaProyectos cp2) {
                    if (cp1 != null && cp1.getCatProyNombre() != null && cp2 != null && cp2.getCatProyNombre() != null) {
                        return cp1.getCatProyNombre().compareTo(cp2.getCatProyNombre());
                    }
                    return 0;
                }
            });
        }
        return listCP;
    }
}
