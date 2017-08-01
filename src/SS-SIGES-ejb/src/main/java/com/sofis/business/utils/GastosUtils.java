package com.sofis.business.utils;

import com.sofis.entities.data.Gastos;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class GastosUtils {

    public static List<Gastos> sortByFecha(List<Gastos> listaGastos, boolean asc) {
        if (CollectionsUtils.isNotEmpty(listaGastos)) {
            final boolean ascOrder = asc;
            Collections.sort(listaGastos, new Comparator<Gastos>() {
                @Override
                public int compare(Gastos rh1, Gastos rh2) {
                    if (rh1 != null && rh1.getGasFecha() != null && rh2 != null && rh2.getGasFecha() != null) {
                        int compareFecha = 0;
                        if (ascOrder) {
                            compareFecha = rh1.getGasFecha().compareTo(rh2.getGasFecha());
                        } else {
                            compareFecha = rh2.getGasFecha().compareTo(rh1.getGasFecha());
                        }

                        if (compareFecha != 0) {
                            return compareFecha;
                        } else {
                            if (ascOrder) {
                                return rh1.getGasPk().compareTo(rh2.getGasPk());
                            } else {
                                return rh2.getGasPk().compareTo(rh1.getGasPk());
                            }
                        }
                    }
                    return 0;
                }
            });
        }
        return listaGastos;
    }
}
