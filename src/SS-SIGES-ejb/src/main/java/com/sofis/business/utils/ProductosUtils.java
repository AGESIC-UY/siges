package com.sofis.business.utils;

import com.sofis.entities.data.ProdMes;
import com.sofis.entities.data.Productos;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
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

    /**
     * Ordena la lista de productos por el último mes que tenga.
     *
     * @param list
     * @param asc
     * @return List de Productos
     */
    public static List<Productos> sortByFechaFin(List<Productos> list, boolean asc) {
        if (CollectionsUtils.isNotEmpty(list)) {
            final boolean ascOrder = asc;
            Collections.sort(list, new Comparator<Productos>() {
                @Override
                public int compare(Productos p1, Productos p2) {
                    ProdMes ultMesP1 = p1.ultimoProdMes();
                    ProdMes ultMesP2 = p2.ultimoProdMes();
                    if (ultMesP1 != null && ultMesP2 != null) {
                        Calendar cal1 = new GregorianCalendar();
                        cal1.set(ultMesP1.getProdmesAnio(), ultMesP1.getProdmesMes(), 1);
                        Calendar cal2 = new GregorianCalendar();
                        cal2.set(ultMesP2.getProdmesAnio(), ultMesP2.getProdmesMes(), 1);
                        return ascOrder ? cal1.compareTo(cal2) : cal2.compareTo(cal1);
                    }
                    return 0;
                }
            });
        }

        return list;
    }
}
