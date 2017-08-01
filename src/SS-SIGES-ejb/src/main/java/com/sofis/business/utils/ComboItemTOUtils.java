package com.sofis.business.utils;

import com.sofis.entities.tipos.ComboItemTO;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ComboItemTOUtils {

    public static List<ComboItemTO> sortByTextoCombo(List<ComboItemTO> list, boolean asc) {
        if (CollectionsUtils.isNotEmpty(list)) {
            final boolean ascOrder = asc;
            Collections.sort(list, new Comparator<ComboItemTO>() {
                @Override
                public int compare(ComboItemTO ci1, ComboItemTO ci2) {
                    if (ci1 != null && ci1.getItemNombre() != null && ci2 != null && ci2.getItemNombre() != null) {
                        if (ascOrder) {
                            return ci1.getItemNombre().compareTo(ci2.getItemNombre());
                        } else {
                            return ci2.getItemNombre().compareTo(ci1.getItemNombre());
                        }
                    }
                    return 0;
                }
            });
        }

        return list;
    }

    public static List<ComboItemTO> sortByObjIfInteger(List<ComboItemTO> list, boolean asc) {
        if (CollectionsUtils.isNotEmpty(list) && list.get(0).getItemObject() instanceof Integer) {
            final boolean ascOrder = asc;
            Collections.sort(list, new Comparator<ComboItemTO>() {
                @Override
                public int compare(ComboItemTO ci1, ComboItemTO ci2) {
                    if (ci1 != null && ci1.getItemObject() != null && ci1.getItemObject() instanceof Integer
                            && ci2 != null && ci2.getItemObject() != null && ci2.getItemObject() instanceof Integer) {
                        if (ascOrder) {
                            return ((Integer)ci1.getItemObject()).compareTo((Integer)ci2.getItemObject());
                        } else {
                            return ((Integer)ci2.getItemObject()).compareTo((Integer)ci1.getItemObject());
                        }
                    }
                    return 0;
                }
            });
        }

        return list;
    }
}
