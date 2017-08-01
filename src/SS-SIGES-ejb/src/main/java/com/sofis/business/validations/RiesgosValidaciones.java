package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Riesgos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class RiesgosValidaciones {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(List<Riesgos> riesgos) throws BusinessException {
        boolean result = true;
        if (riesgos != null) {
            for (Riesgos r : riesgos) {
                boolean valido = validar(r);
                if(!valido){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public static boolean validar(Riesgos riesgo) throws BusinessException {
        logger.finest("Validar Riesgos.");
        BusinessException be = new BusinessException();

        if (riesgo == null) {
            be.addError(MensajesNegocio.ERROR_RIESGOS_NULL);
        } else {
            if (riesgo.getRiskNombre() == null) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_NOMBRE);
            }
            if (riesgo.getRiskFechaActualizacion() == null
                    || !DatesUtils.fechasIguales(riesgo.getRiskFechaActualizacion(), new Date())) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_FECHA_ACT);
            }
            if (riesgo.getRiskProbabilidad() == null) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_PROBABILIDAD);
            } else if (riesgo.getRiskProbabilidad() != null
                    && !(riesgo.getRiskProbabilidad().equals(20)
                    || riesgo.getRiskProbabilidad().equals(40)
                    || riesgo.getRiskProbabilidad().equals(60)
                    || riesgo.getRiskProbabilidad().equals(80)
                    || riesgo.getRiskProbabilidad().equals(100))) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_PROBABILIDAD_VALOR);
            }
            if (riesgo.getRiskImpacto() == null) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_IMPACTO);
            } else if (riesgo.getRiskImpacto() != null
                    && !(riesgo.getRiskImpacto().equals(2)
                    || riesgo.getRiskImpacto().equals(4)
                    || riesgo.getRiskImpacto().equals(6)
                    || riesgo.getRiskImpacto().equals(8)
                    || riesgo.getRiskImpacto().equals(10))) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_IMPACTO_VALOR);
            }
            if (riesgo.getRiskFechaLimite() == null) {
                be.addError(MensajesNegocio.ERROR_RIESGOS_FECHA_LIMITE);
            }

//            if(riesgo.getRiskEfecto() == null){
//                be.addError(MensajesNegocio.ERROR_RIESGOS_EFECTO);
//            }
//            if(riesgo.getRiskEstrategia() == null){
//                be.addError(MensajesNegocio.ERROR_RIESGOS_ESTRATEGIA);
//            }
//            if(riesgo.getRiskDisparador() == null){
//                be.addError(MensajesNegocio.ERROR_RIESGOS_DISPARADOR);
//            }
//            if(riesgo.getRiskContingencia() == null){
//                be.addError(MensajesNegocio.ERROR_RIESGOS_CONTINGENCIA);
//            }
        }

        if (be.getErrores().size() > 0) {
            logger.logp(Level.WARNING, RiesgosValidaciones.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
