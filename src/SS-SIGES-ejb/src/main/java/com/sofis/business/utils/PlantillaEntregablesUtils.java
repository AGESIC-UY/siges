package com.sofis.business.utils;

import com.sofis.entities.data.PlantillaEntregables;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PlantillaEntregablesUtils {

    public static List<PlantillaEntregables> sortByNumero(List<PlantillaEntregables> listPlantEnt) {
        if (CollectionsUtils.isNotEmpty(listPlantEnt)) {
            Collections.sort(listPlantEnt, new Comparator<PlantillaEntregables>() {
                @Override
                public int compare(PlantillaEntregables t, PlantillaEntregables t1) {
                    return t.getPentregablesNumero().compareTo(t1.getPentregablesNumero());
                }
            });
        }
        return listPlantEnt;
    }
}
