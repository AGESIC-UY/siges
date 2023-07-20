package com.sofis.business.validations;

import com.sofis.business.utils.Utils;
import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.BooleanUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;

/**
 *
 * @author SSGenerador
 */
public class SsUsuarioValidacion {

    public static boolean validar(SsUsuario usu, Integer orgPk) throws BusinessException {
        BusinessException ge = new BusinessException();

        if (usu == null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (usu.getUsuId() == null) {
                if (StringsUtils.isEmpty(usu.getUsuPassword())) {
                    ge.addError(MensajesNegocio.ERROR_USUARIO_PASSWORD);
                }
            }

            if (StringsUtils.isEmpty(usu.getUsuCorreoElectronico())) {
                ge.addError(MensajesNegocio.ERROR_USUARIO_MAIL);
            }

            if (StringsUtils.isEmpty(usu.getUsuPrimerNombre())) {
                ge.addError(MensajesNegocio.ERROR_USUARIO_NOMBRE);
            }
            if (StringsUtils.isEmpty(usu.getUsuPrimerApellido())) {
                ge.addError(MensajesNegocio.ERROR_USUARIO_APELLIDO);
            }

            if (orgPk != null) {
                boolean isRegHoras = usu.isRol(SsRolCodigos.REGISTRO_HORAS, orgPk);
                if (usu.isUsuAprobFact() && isRegHoras) {
                    ge.addError(MensajesNegocio.ERROR_USUARIO_APROB_FACT_TIPO_USU);
                }
            }
        }

        if (ge.getErrores().size() > 0) {
            throw ge;
        }
        return true;
    }

    public static boolean validarCambioPass(SsUsuario usu, String contraseniaActual, String nuevaContrasenia, String confirmacionContrasenia) throws BusinessException {
        BusinessException ge = new BusinessException();

        if (contraseniaActual == null) {
            ge.addError(MensajesNegocio.ERROR_USUARIO_PASSWORD_ACTUAL);
        }
        if (nuevaContrasenia == null) {
            ge.addError(MensajesNegocio.ERROR_USUARIO_PASSWORD_NUEVA);
        }
        if (confirmacionContrasenia == null) {
            ge.addError(MensajesNegocio.ERROR_USUARIO_PASSWORD_REINGRESO);
        }

        String pass = Utils.md5(contraseniaActual);
        if (!usu.getUsuPassword().equalsIgnoreCase(pass)) {
            ge.addError(MensajesNegocio.ERROR_USUARIO_PASSWORD_USU);
        }
        if (contraseniaActual != null && nuevaContrasenia != null
                && contraseniaActual.equals(nuevaContrasenia)) {
            ge.addError(MensajesNegocio.ERROR_USUARIO_PASSWORD_ACTUAL_NUEVA);
        }
        if (nuevaContrasenia != null && confirmacionContrasenia != null
                && !nuevaContrasenia.equals(confirmacionContrasenia)) {
            ge.addError(MensajesNegocio.ERROR_USUARIO_PASSWORD_DIF_REINGRESO);
        }

        if (nuevaContrasenia != null && (nuevaContrasenia.length() > 12 || nuevaContrasenia.length() < 8)) {
            ge.addError(MensajesNegocio.ERROR_USUARIO_PASSWORD_LONGITUD);
        } else if (nuevaContrasenia != null) {

            int cantidadMayusculas = 0;
            int cantidadMinusculas = 0;
            int cantidadNumeros = 0;

            for (int i = 0; i < nuevaContrasenia.length(); i++) {
                if (Character.isUpperCase(nuevaContrasenia.charAt(i))) {
                    cantidadMayusculas = cantidadMayusculas + 1;
                } else if (Character.isLowerCase(nuevaContrasenia.charAt(i))) {
                    cantidadMinusculas = cantidadMinusculas + 1;
                } else if (Character.isDigit(nuevaContrasenia.charAt(i))) {
                    cantidadNumeros = cantidadNumeros + 1;
                }
            }

            if (cantidadMayusculas < 2) {
                ge.addError(MensajesNegocio.ERROR_USUARIO_PASSWORD_MAYUSUCLAS);
            }

            if (cantidadMinusculas < 2) {
                ge.addError(MensajesNegocio.ERROR_USUARIO_PASSWORD_MINUSCULAS);
            }
            if (cantidadNumeros < 2) {
                ge.addError(MensajesNegocio.ERROR_USUARIO_PASSWORD_NUMEROS);
            }

        }

        if (ge.getErrores().size() > 0) {
            throw ge;
        }
        return true;
    }

    public static boolean compararParaGrabar(SsUsuario c1, SsUsuario c2) {
        boolean respuesta = true;
        if (c1 != null) {
            if (c2 != null) {
                respuesta = StringsUtils.sonStringIguales(c1.getUsuCod(), c2.getUsuCod());
                respuesta = respuesta && StringsUtils.sonStringIguales(c1.getUsuDescripcion(), c2.getUsuDescripcion());
                respuesta = respuesta && BooleanUtils.sonBooleanIguales(c1.getUsuVigente(), c2.getUsuVigente());
            }
        } else {
            respuesta = c2 == null;
        }
        return respuesta;
    }
}
