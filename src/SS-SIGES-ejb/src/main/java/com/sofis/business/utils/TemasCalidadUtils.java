package com.sofis.business.utils;

import com.sofis.entities.data.TemasCalidad;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class TemasCalidadUtils {
    
    public static List<TemasCalidad> sortByNombre(List<TemasCalidad> list, boolean asc) {
        if (CollectionsUtils.isNotEmpty(list)) {
            final boolean ascOrder = asc;
            Collections.sort(list, new Comparator<TemasCalidad>() {
                @Override
                public int compare(TemasCalidad tca1, TemasCalidad tca2) {
                    if (tca1 != null && tca1.getTcaNombre() != null && tca2 != null && tca2.getTcaNombre() != null) {
                        if (ascOrder) {
                            return tca1.getTcaNombre().compareTo(tca2.getTcaNombre());
                        } else {
                            return tca2.getTcaNombre().compareTo(tca1.getTcaNombre());
                        }
                    }
                    return 0;
                }
            });
        }

        return list;
    }
}
