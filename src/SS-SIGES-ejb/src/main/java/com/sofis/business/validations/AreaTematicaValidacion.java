package com.sofis.business.validations;

import com.sofis.business.utils.Utils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.AreasTags;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class AreaTematicaValidacion {

    private static final Logger logger = Logger.getLogger(AreaTematicaValidacion.class.getName());

    public static boolean validar(AreasTags at) throws BusinessException {
        BusinessException be = new BusinessException();
		
        if (at == null) {
            be.addError(MensajesNegocio.ERROR_AREAS_TEMATICAS_NULL);
        } else {
            if (StringsUtils.isEmpty(at.getAreatagNombre())) {
                be.addError(MensajesNegocio.ERROR_AREAS_TEMATICAS_NOMBRE);
            } else if (at.getAreatagNombre().length() > AreasTags.NOMBRE_LENGHT) {
                be.addError(Utils.mensajeLargoCampo("area_tem_busqueda_nombre", at.getAreatagOrgFk().getOrgPk(), AreasTags.NOMBRE_LENGHT));
            }

            if (at.getAreatagPadreFk() != null
                    && at.getAreatagPadreFk().getArastagPk().equals(at.getArastagPk())) {
                be.addError(MensajesNegocio.ERROR_AREAS_TEMATICAS_PADRE);
            }
			
            if (at.getHabilitada() == null) {
                be.addError(MensajesNegocio.ERROR_AREAS_TEMATICAS_HABILITADA);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.logp(Level.WARNING, DocumentosValidacion.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
