package com.sofis.business.utils;

import com.sofis.entities.data.Pagos;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PagosUtils {

    public static List<Pagos> sortByFechaPlanificada(List<Pagos> listPagos) {
        if (listPagos != null && !listPagos.isEmpty()) {
            Collections.sort(listPagos, new Comparator<Pagos>() {
                @Override
                public int compare(Pagos p1, Pagos p2) {
                    if (p1 != null && p1.getPagFechaPlanificada() != null && p2 != null && p2.getPagFechaPlanificada() != null) {
                        return p1.getPagFechaPlanificada().compareTo(p2.getPagFechaPlanificada());
                    }
                    return 0;
                }
            });
        }
        return listPagos;
    }
}
