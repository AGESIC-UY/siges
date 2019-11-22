package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ProcedimientoCompraValidacion {

	private static final Logger logger = Logger.getLogger(ProcedimientoCompraValidacion.class.getName());

	public static boolean validar(ProcedimientoCompra procedimientoCompra) throws BusinessException {
		BusinessException be = new BusinessException();
		if (procedimientoCompra == null) {
			be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_NULL);
		} else {
			if (StringsUtils.isEmpty(procedimientoCompra.getProcCompNombre())) {
				be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_NOMBRE);
			}
			if (procedimientoCompra.getProcCompOrgFk() == null) {
				be.addError(MensajesNegocio.ERROR_PROCEDIMIENTO_COMPRA_ORG);
			}
		}
		if (be.getErrores().size() > 0) {
			logger.log(Level.WARNING, null, be);
			throw be;
		}
		return true;
	}
}
