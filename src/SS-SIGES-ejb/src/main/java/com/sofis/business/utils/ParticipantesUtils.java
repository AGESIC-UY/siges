package com.sofis.business.utils;

import com.sofis.entities.data.Participantes;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ParticipantesUtils {

    public static List<Participantes> sortByHorasPendientes(List<Participantes> listParticipantes, boolean asc) {
        if (CollectionsUtils.isNotEmpty(listParticipantes)) {
            final boolean ascendente = asc;
            if (CollectionsUtils.isNotEmpty(listParticipantes)) {

                Collections.sort(listParticipantes, new Comparator<Participantes>() {
                    @Override
                    public int compare(Participantes p1, Participantes p2) {
                        Double d1 = p1 != null && p1.getHorasPendientes() != null ? p1.getHorasPendientes() : 0d;
                        Double d2 = p2 != null && p2.getHorasPendientes() != null ? p2.getHorasPendientes() : 0d;
                        if (ascendente) {
                            return d1.compareTo(d2);
                        }
                        return d2.compareTo(d1);
                    }
                });
            }
        }

        return listParticipantes;
    }
}
