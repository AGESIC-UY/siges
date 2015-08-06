package com.sofis.business.utils;

import com.sofis.entities.data.Productos;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ProductosUtils {

    public static List<Productos> sortByDesc(List<Productos> list, boolean asc) {
        if (CollectionsUtils.isNotEmpty(list)) {
            final boolean ascOrder = asc;
            Collections.sort(list, new Comparator<Productos>() {
                @Override
                public int compare(Productos p1, Productos p2) {
                    if (p1 != null && p1.getProdDesc() != null && p2 != null && p2.getProdDesc() != null) {
                        if (ascOrder) {
                            return p1.getProdDesc().compareTo(p2.getProdDesc());
                        } else {
                            return p2.getProdDesc().compareTo(p1.getProdDesc());
                        }
                    }
                    return 0;
                }
            });
        }

        return list;
    }

    public static List<Productos> sortByEntProdNombre(List<Productos> list, boolean asc) {
        if (CollectionsUtils.isNotEmpty(list)) {
            final boolean ascOrder = asc;
            Collections.sort(list, new Comparator<Productos>() {
                @Override
                public int compare(Productos p1, Productos p2) {
                    if (p1 != null && p1.getEntProdNombre() != null && p2 != null && p2.getEntProdNombre() != null) {
                        if (ascOrder) {
                            return p1.getEntProdNombre().compareTo(p2.getEntProdNombre());
                        } else {
                            return p2.getEntProdNombre().compareTo(p1.getEntProdNombre());
                        }
                    }
                    return 0;
                }
            });
        }

        return list;
    }
}
