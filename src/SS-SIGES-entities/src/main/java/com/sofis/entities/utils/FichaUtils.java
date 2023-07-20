package com.sofis.entities.utils;

import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.ItemInicioTO;
import com.sofis.generico.utils.generalutils.StringsUtils;

/**
 *
 * @author Usuario
 */
public class FichaUtils {

    public static boolean isPrograma(Object obj) {
        if (obj != null) {
            Integer tipoFicha = null;
            if (obj instanceof FichaTO) {
                tipoFicha = ((FichaTO) obj).getTipoFicha();
            } else if (obj instanceof ItemInicioTO) {
                tipoFicha = ((ItemInicioTO) obj).getTipoFicha();
            }
            if (tipoFicha != null && tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isProyecto(Object obj) {
        if (obj != null) {
            Integer tipoFicha = null;
            if (obj instanceof FichaTO) {
                tipoFicha = ((FichaTO) obj).getTipoFicha();
            } else if (obj instanceof ItemInicioTO) {
                tipoFicha = ((ItemInicioTO) obj).getTipoFicha();
            }
            if (tipoFicha != null && tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Dado el idEditar que se compone de "tipoFicha"-"pk", retorna el tipo de
     * ficha.
     *
     * @return
     */
    public static TipoFichaEnum obtenerTipoFicha(String proyProgId) {

        String[] idSplit = proyProgId.split("-");

        if (idSplit != null && !StringsUtils.isEmpty(idSplit[0])) {
            if (idSplit[0].equalsIgnoreCase(String.valueOf(TipoFichaEnum.PROGRAMA.id))) {
                return TipoFichaEnum.PROGRAMA;
            } else if (idSplit[0].equalsIgnoreCase(String.valueOf(TipoFichaEnum.PROYECTO.id))) {
                return TipoFichaEnum.PROYECTO;
            }
        }

        return null;
    }
    
    /**
     * Dado el idEditar que se compone de "tipoFicha"-"pk", retorna el pk.
     *
     * @param proyProgId
     * @return
     */
    public static Integer obtenerProyProgId(String proyProgId) {

        String[] idSplit = proyProgId.split("-");

        if (idSplit != null && !StringsUtils.isEmpty(idSplit[1])) {
            return Integer.valueOf(idSplit[1].trim());
        }

        return null;
    }
    
}
