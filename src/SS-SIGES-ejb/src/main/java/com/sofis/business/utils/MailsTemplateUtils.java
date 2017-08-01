package com.sofis.business.utils;

import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class MailsTemplateUtils {

    public static String instanciar(String plantilla, String correoElectronico, String rut, String razonSocial, String estado) {
        String resultado = plantilla;
        resultado = resultado.replaceAll("#CORREO_ELECTRONICO#", correoElectronico);
        resultado = resultado.replaceAll("#RUT#", rut);
        resultado = resultado.replaceAll("#RAZON_SOCIAL#", razonSocial);
        resultado = resultado.replaceAll("#ESTADO#", estado);
        return resultado;
    }

    public static String instanciarConObservaciones(String plantilla, String correoElectronico, String rut, String razonSocial, String estado, String observaciones, String camposCambiados) {
        String resultado = plantilla;
        resultado = resultado.replaceAll("#CORREO_ELECTRONICO#", correoElectronico);
        resultado = resultado.replaceAll("#RUT#", rut);
        resultado = resultado.replaceAll("#RAZON_SOCIAL#", razonSocial);
        resultado = resultado.replaceAll("#ESTADO#", estado);
        resultado = resultado.replaceAll("#OBSERVACIONES#", estado);
        if (!StringsUtils.isEmpty(camposCambiados)) {
            resultado = resultado.replaceAll("#CAMPOS_CAMBIADOS#", "Se han modificado los siguientes campos:<br/> " + camposCambiados);
        } else {
            resultado = resultado.replaceAll("#CAMPOS_CAMBIADOS#", "");
        }
        return resultado;
    }

    public static String instanciarConHashMap(String plantilla, Map<String, String> valores) {
        String resultado = plantilla;
        if (plantilla != null && valores != null && !valores.isEmpty()) {
            for (String strKey : valores.keySet()) {
                String str = StringsUtils.concat("#", strKey, "#");
                String strValue = valores.get(strKey);
                if (strValue != null) {
                    resultado = resultado.replaceAll(str, strValue);
                }
            }
        }
        return resultado;
    }
}
