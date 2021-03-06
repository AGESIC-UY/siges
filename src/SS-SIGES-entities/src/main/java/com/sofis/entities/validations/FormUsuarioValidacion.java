package com.sofis.entities.validations;

import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.SsUsuOfiRoles;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.EmailValidator;
import com.sofis.generico.utils.generalutils.StringsUtils;

/**
 *
 * @author Usuario
 */
public class FormUsuarioValidacion {

    public static boolean validar(SsUsuario usu, Integer orgPk, boolean area) throws BusinessException {
        BusinessException ge = new BusinessException();

        if (usu == null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (StringsUtils.isEmpty(usu.getUsuCorreoElectronico())) {
                ge.addError(MensajesNegocio.ERROR_USUARIO_MAIL);
            }else if(!EmailValidator.validateEmail(usu.getUsuCorreoElectronico())){
                ge.addError(MensajesNegocio.ERROR_USUARIO_MAIL_INVALIDO);
            }
            
            if (StringsUtils.isEmpty(usu.getUsuPrimerNombre())) {
                ge.addError(MensajesNegocio.ERROR_USUARIO_NOMBRE);
            }
            
            if (StringsUtils.isEmpty(usu.getUsuPrimerApellido())) {
                ge.addError(MensajesNegocio.ERROR_USUARIO_APELLIDO);
            }

            boolean hasRol = false;
            if (usu.getSsUsuOfiRolesCollection() != null) {
                for (SsUsuOfiRoles roles : usu.getSsUsuOfiRolesCollection()) {
                    if (roles.getUsuOfiRolesOficina().getOfiId().equals(orgPk)
                            && roles.getUsuOfiRolesRol().isRolTipoUsuario()) {
                        hasRol = true;
                        break;
                    }
                }
            }
            if (!hasRol) {
                ge.addError(MensajesNegocio.ERROR_USUARIO_ROL);
            }

            if (area && usu.getUsuArea(orgPk) == null) {
                ge.addError(MensajesNegocio.ERROR_USUARIO_AREA);
            }
        }

        if (ge.getErrores().size() > 0) {
            throw ge;
        }
        return true;
    }
}
