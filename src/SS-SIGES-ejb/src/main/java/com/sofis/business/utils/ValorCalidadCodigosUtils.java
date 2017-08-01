package com.sofis.business.utils;

import com.sofis.entities.data.ValorCalidadCodigos;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ValorCalidadCodigosUtils {
    
    public static List<ValorCalidadCodigos> sortByNombre(List<ValorCalidadCodigos> list, boolean asc) {
        if (CollectionsUtils.isNotEmpty(list)) {
            final boolean ascOrder = asc;
            Collections.sort(list, new Comparator<ValorCalidadCodigos>() {
                @Override
                public int compare(ValorCalidadCodigos ci1, ValorCalidadCodigos ci2) {
                    if (ci1 != null && ci1.getVcaNombre() != null && ci2 != null && ci2.getVcaNombre() != null) {
                        if (ascOrder) {
                            return ci1.getVcaNombre().compareTo(ci2.getVcaNombre());
                        } else {
                            return ci2.getVcaNombre().compareTo(ci1.getVcaNombre());
                        }
                    }
                    return 0;
                }
            });
        }

        return list;
    }
}
