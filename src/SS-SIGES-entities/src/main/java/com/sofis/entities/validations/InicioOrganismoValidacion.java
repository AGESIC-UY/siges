package com.sofis.entities.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Pagos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class InicioOrganismoValidacion {
    
    private static final Logger logger = Logger.getLogger(InicioOrganismoValidacion.class.getName());

    public static boolean validar(Organismos org) throws BusinessException {
        logger.finest("Validar Inicio Organismo.");
        BusinessException be = new BusinessException();

        if (org == null) {
            be.addError(MensajesNegocio.ERROR_ORGANISMO_NULL);
        }else{
            if (StringsUtils.isEmpty(org.getOrgNombre())) {
                be.addError(MensajesNegocio.ERROR_ORGANISMO_NOMBRE);
            }
            if (StringsUtils.isEmpty(org.getOrgDireccion())) {
                be.addError(MensajesNegocio.ERROR_ORGANISMO_DIRECCION);
            }
//            if (org.getOrgLogo() == null) {
//                be.addError(MensajesNegocio.ERROR_ORGANISMO_LOGO);
//            }
        }
        
        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, be.getMessage(), be);
            throw be;
        }

        return true;
    }
}
