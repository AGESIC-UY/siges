package com.sofis.business.utils;

import com.sofis.entities.data.RegistrosHoras;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class RegistroHorasUtils {

    public static List<RegistrosHoras> sortByFecha(List<RegistrosHoras> listRegistrosHoras, boolean asc) {
        if (CollectionsUtils.isNotEmpty(listRegistrosHoras)) {
            final boolean ascOrder = asc;
            Collections.sort(listRegistrosHoras, new Comparator<RegistrosHoras>() {
                @Override
                public int compare(RegistrosHoras rh1, RegistrosHoras rh2) {
                    if (rh1 != null && rh1.getRhFecha() != null && rh2 != null && rh2.getRhFecha() != null) {
                        int compareFecha = 0;
                        if (ascOrder) {
                            compareFecha = rh1.getRhFecha().compareTo(rh2.getRhFecha());
                        } else {
                            compareFecha = rh2.getRhFecha().compareTo(rh1.getRhFecha());
                        }

                        if (compareFecha != 0) {
                            return compareFecha;
                        } else {
                            if (ascOrder) {
                                return rh1.getRhPk().compareTo(rh2.getRhPk());
                            } else {
                                return rh2.getRhPk().compareTo(rh1.getRhPk());
                            }
                        }
                    }
                    return 0;
                }
            });
        }

        return listRegistrosHoras;
    }
}
