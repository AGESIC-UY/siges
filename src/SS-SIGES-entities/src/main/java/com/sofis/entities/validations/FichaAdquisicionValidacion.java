package com.sofis.entities.validations;

import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class FichaAdquisicionValidacion {

    private static final Logger logger = Logger.getLogger(FichaAdquisicionValidacion.class.getName());

    public static boolean validar(Adquisicion adq, Boolean proveedorExigidoEnCompraCnf,
            Boolean existeFuenteProcedimientoCompra, Boolean camposExigidos,
            Integer largoMaximoIdAdquisicion) throws BusinessException {
        
        logger.finest("Validar Ficha Adquisicion.");
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
            if (adq.getAdqTipoAdquisicion() == null && camposExigidos) {
                be.addError(MensajesNegocio.ERROR_ADQUISICION_TIPO_ADQUISICION_VACIO);
            }

            if (proveedorExigidoEnCompraCnf && existeFuenteProcedimientoCompra && adq.getAdqProvOrga() == null
                    && (adq.getAdqCausalCompra() != null || adq.getAdqProcedimientoCompra() != null)) {
                be.addError(MensajesNegocio.ERROR_ADQUISICION_SIN_PROVEEDOR);
            }

            // validar fecha esperada inicio ejecución sea mayor o igual a la de estimada inicio compra
            if (adq.getAdqFechaEstimadaInicioCompra() != null && adq.getAdqFechaEsperadaInicioEjecucion() != null) {
                if (adq.getAdqFechaEstimadaInicioCompra().after(adq.getAdqFechaEsperadaInicioEjecucion())) {
                    be.addError(MensajesNegocio.ERROR_ADQUISICION_FECHA_INICIO_MAYOR_FECHA_EJECUCION);
                }
            }

            if (adq.getAdqIdAdquisicion() == null || adq.getAdqIdAdquisicion() == 0) {
                if (camposExigidos) {
                    be.addError(MensajesNegocio.ERROR_ADQUISICION_ID_ADQUISICION_VACIO);
                }
            } else if (String.valueOf(adq.getAdqIdAdquisicion()).length() > largoMaximoIdAdquisicion) {
               
                be.addError(MensajesNegocio.ERROR_ADQUISICION_ID_ADQUISICION_SUPERA_LARGO_MAXIMO);
            }

            if (adq.getAdqCentroCosto() == null && camposExigidos) {
                be.addError(MensajesNegocio.ERROR_ADQUISICION_CENTRO_COSTO_VACIO);
            }

            if (existeFuenteProcedimientoCompra && adq.getAdqCausalCompra() == null) {
                be.addError(MensajesNegocio.ERROR_ADQUISICION_SIN_CAUSAL);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
