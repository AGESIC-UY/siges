package com.sofis.business.utils;

import com.sofis.entities.data.Calidad;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CalidadUtils {
    
    public static List<Calidad> sortByItemStr(List<Calidad> list, boolean asc) {
        if (CollectionsUtils.isNotEmpty(list)) {
            final boolean ascOrder = asc;
            Collections.sort(list, new Comparator<Calidad>() {
                @Override
                public int compare(Calidad tca1, Calidad tca2) {
                    if (tca1 != null && tca1.getItemStr() != null && tca2 != null && tca2.getItemStr() != null) {
                        if (ascOrder) {
                            return tca1.getItemStr().compareTo(tca2.getItemStr());
                        } else {
                            return tca2.getItemStr().compareTo(tca1.getItemStr());
                        }
                    }
                    return 0;
                }
            });
        }

        return list;
    }
    
    public static List<Calidad> sortByActualizacion(List<Calidad> list, boolean asc) {
        if (CollectionsUtils.isNotEmpty(list)) {
            final boolean ascOrder = asc;
            Collections.sort(list, new Comparator<Calidad>() {
                @Override
                public int compare(Calidad tca1, Calidad tca2) {
                    if (tca1 != null && tca1.getCalActualizacion() != null && tca2 != null && tca2.getCalActualizacion() != null) {
                        if (ascOrder) {
                            return tca1.getCalActualizacion().compareTo(tca2.getCalActualizacion());
                        } else {
                            return tca2.getCalActualizacion().compareTo(tca1.getCalActualizacion());
                        }
                    }
                    return 0;
                }
            });
        }

        return list;
    }
}
