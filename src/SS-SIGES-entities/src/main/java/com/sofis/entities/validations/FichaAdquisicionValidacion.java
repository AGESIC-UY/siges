package com.sofis.entities.validations;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.Date;

public class FichaAdquisicionValidacion {

	public static boolean validar(Adquisicion adq, Boolean proveedorExigidoEnCompra,
			Boolean existeFuenteProcedimientoCompra, Boolean tipoAdquisicionRequerido,
			Integer largoMaximoIdAdquisicion,Boolean centroCostoExigido, Boolean idAdquisicionRequerido) throws BusinessException {

		BusinessException be = new BusinessException();

		if (adq == null) {
			be.addError(MensajesNegocio.ERROR_ADQISICION_NULL);
		} else {

			if (StringsUtils.isEmpty(adq.getAdqNombre())) {
				be.addError(MensajesNegocio.ERROR_ADQISICION_NOMBRE);
			}

			if (adq.getAdqFuente() == null) {
				be.addError(MensajesNegocio.ERROR_ADQISICION_FUENTE);
			}
			if (adq.getAdqMoneda() == null) {
				be.addError(MensajesNegocio.ERROR_ADQISICION_MONEDAS);
			}
			if (adq.getAdqTipoRegistro() == null) {
				be.addError(MensajesNegocio.ERROR_ADQUISICION_TIPO_COMPRA_VACIO);
			}
			if (adq.getAdqTipoAdquisicion() == null && tipoAdquisicionRequerido) {
				be.addError(MensajesNegocio.ERROR_ADQUISICION_TIPO_ADQUISICION_VACIO);
			}

			validarProveedor(proveedorExigidoEnCompra, existeFuenteProcedimientoCompra,
					adq.getAdqProvOrga(), adq.getAdqProcedimientoCompra(), adq.getAdqCausalCompra(), be);

			// validar fecha esperada inicio ejecución sea mayor o igual a la de estimada inicio compra
			if (adq.getAdqFechaEstimadaInicioCompra() != null && adq.getAdqFechaEsperadaInicioEjecucion() != null) {
				if (adq.getAdqFechaEstimadaInicioCompra().after(adq.getAdqFechaEsperadaInicioEjecucion())) {
					be.addError(MensajesNegocio.ERROR_ADQUISICION_FECHA_INICIO_MAYOR_FECHA_EJECUCION);
				}
			}

			validarIdAdquisicion(be, idAdquisicionRequerido, adq.getAdqIdAdquisicion(), largoMaximoIdAdquisicion);

			if (adq.getAdqCentroCosto() == null && centroCostoExigido) {
				be.addError(MensajesNegocio.ERROR_ADQUISICION_CENTRO_COSTO_VACIO);
			}

			if (existeFuenteProcedimientoCompra && adq.getAdqCausalCompra() == null) {
				be.addError(MensajesNegocio.ERROR_ADQUISICION_SIN_CAUSAL);
			}
		}

		if (be.getErrores().size() > 0) {

			throw be;
		}

		return true;
	}

	public static boolean validarDuplicado(Adquisicion adq, Boolean proveedorExigidoEnCompra,
			Boolean existeFuenteProcedimientoCompra, Boolean camposExigidos,
			Integer largoMaximoIdAdquisicion, Date fechaPrimerPago, Double porcentajeImporte) throws BusinessException {

		BusinessException be = new BusinessException();

		if (adq == null) {
			be.addError(MensajesNegocio.ERROR_ADQISICION_NULL);
		} else {

			if (StringsUtils.isEmpty(adq.getAdqNombre())) {
				be.addError(MensajesNegocio.ERROR_ADQISICION_NOMBRE);
			}
			if (fechaPrimerPago == null) {
				be.addError(MensajesNegocio.ERROR_ADQUISICION_DUPLICAR_FECHA_PRIMER_PAGO_INVALIDA);
			}
			if (porcentajeImporte == null || porcentajeImporte <= 0) {

				be.addError(MensajesNegocio.ERROR_ADQUISICION_DUPLICAR_PORCENTAJE_IMPORTE_INVALIDO);
			}

			if (adq.getAdqFuente() == null) {
				be.addError(MensajesNegocio.ERROR_ADQISICION_FUENTE);
			}
			if (adq.getAdqTipoRegistro() == null) {
				be.addError(MensajesNegocio.ERROR_ADQUISICION_TIPO_COMPRA_VACIO);
			}

			validarProveedor(proveedorExigidoEnCompra, existeFuenteProcedimientoCompra,
					adq.getAdqProvOrga(), adq.getAdqProcedimientoCompra(), adq.getAdqCausalCompra(), be);

			validarIdAdquisicion(be, camposExigidos, adq.getAdqIdAdquisicion(), largoMaximoIdAdquisicion);

			if (existeFuenteProcedimientoCompra && adq.getAdqCausalCompra() == null) {
				be.addError(MensajesNegocio.ERROR_ADQUISICION_SIN_CAUSAL);
			}
		}

		if (be.getErrores().size() > 0) {

			throw be;
		}

		return true;
	}

	public static void validarIdAdquisicion(BusinessException be, Boolean camposExigidos,
			Integer idAdquisicion, Integer largoMaximoIdAdquisicion) {

		if (camposExigidos
				&& (idAdquisicion == null || idAdquisicion <= 0)) {

			be.addError(MensajesNegocio.ERROR_ADQUISICION_ID_ADQUISICION_VACIO);

		} else if (idAdquisicion < 0) {

			be.addError(MensajesNegocio.ERROR_ADQUISICION_ID_ADQUISICION_NO_ENTERO);

		} else if (String.valueOf(idAdquisicion).length() > largoMaximoIdAdquisicion) {

			be.addError(MensajesNegocio.ERROR_ADQUISICION_ID_ADQUISICION_SUPERA_LARGO_MAXIMO);
		}
	}

	public static void validarProveedor(Boolean proveedorExigidoEnCompra, Boolean existeFuenteProcedimientoCompra,
			OrganiIntProve proveedor, ProcedimientoCompra procedimientoCompra, CausalCompra causalCompra, BusinessException be) {

		if (proveedorExigidoEnCompra && existeFuenteProcedimientoCompra
				&& proveedor == null
				&& (causalCompra != null || procedimientoCompra != null)) {

			be.addError(MensajesNegocio.ERROR_ADQUISICION_SIN_PROVEEDOR);
		}
	}
	
	public static void validarProveedor(boolean proveedorExigidoEnCompra, boolean existeFuenteProcedimientoCompra,
			boolean existeProveedor, boolean existeProcedimientoCompra, boolean existeCausalCompra, BusinessException be) {

		if (proveedorExigidoEnCompra && existeFuenteProcedimientoCompra
				&& !existeProveedor
				&& (existeCausalCompra || existeProcedimientoCompra)) {

			be.addError(MensajesNegocio.ERROR_ADQUISICION_SIN_PROVEEDOR);
		}
	}

}
