package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.validations.InicioOrganismoValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class OrganismosValidacion {
    private static final Logger logger = Logger.getLogger(OrganismosValidacion.class.getName());   

    public static boolean validar(Organismos org) throws BusinessException {
        logger.finest("Validar Organismo.");
        return InicioOrganismoValidacion.validar(org);
    }
}
