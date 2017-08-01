package com.sofis.business.utils;

import com.sofis.entities.data.OrganiIntProve;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class OrganiIntProveUtils {

    /**
     * Ordenar la lista de Organizaciones por el nobmre.
     *
     * @param listOrga
     * @return Lista ordenada por nobmre.
     */
    public static List<OrganiIntProve> sortByNombre(List<OrganiIntProve> listOrga) {
        if (CollectionsUtils.isNotEmpty(listOrga)) {
            Collections.sort(listOrga, new Comparator<OrganiIntProve>() {
                @Override
                public int compare(OrganiIntProve o1, OrganiIntProve o2) {
                    if (o1 != null && o1.getOrgaNombre() != null && o2 != null && o2.getOrgaNombre() != null) {
                        return o1.getOrgaNombre().compareTo(o2.getOrgaNombre());
                    }
                    return 0;
                }
            });
        }

        return listOrga;
    }
}
