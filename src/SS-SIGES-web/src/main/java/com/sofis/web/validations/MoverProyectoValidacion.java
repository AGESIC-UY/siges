package com.sofis.web.validations;

import com.sofis.business.utils.Utils;
import com.sofis.business.validations.ProyectosValidacion;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class MoverProyectoValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(Proyectos proy, FichaTO fichaTo) throws BusinessException {

        BusinessException be = new BusinessException();
        be.addError(MensajesNegocio.ERROR_PROYECTO);

        if (proy == null) {
            be.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {

            if (proy.getProyAreaFk() == null || proy.getProyAreaFk().getAreaPk() == null || proy.getProyAreaFk().getAreaPk() == -1) {
                be.addError(MensajesNegocio.ERROR_FICHA_AREA);
            }
            if (proy.getProyUsrGerenteFk() == null || proy.getProyUsrGerenteFk().getUsuId() == null || proy.getProyUsrGerenteFk().getUsuId() == -1) {
                be.addError(MensajesNegocio.ERROR_FICHA_GERENTE);
            }
            if (proy.getProyUsrAdjuntoFk() == null || proy.getProyUsrAdjuntoFk().getUsuId() == null || proy.getProyUsrAdjuntoFk().getUsuId() == -1) {
                be.addError(MensajesNegocio.ERROR_FICHA_ADJUNTO);
            }
            if (proy.getProyUsrSponsorFk() == null || proy.getProyUsrSponsorFk().getUsuId() == null || proy.getProyUsrSponsorFk().getUsuId() == -1) {
                be.addError(MensajesNegocio.ERROR_FICHA_SPONSOR);
            }
            if (proy.getProyUsrPmofedFk() == null || proy.getProyUsrPmofedFk().getUsuId() == null || proy.getProyUsrPmofedFk().getUsuId() == -1) {
                be.addError(MensajesNegocio.ERROR_FICHA_PMOF);
            }

            if (StringsUtils.isEmpty(proy.getProyNombre())) {
                be.addError(MensajesNegocio.ERROR_FICHA_NOMBRE);
            } else if (proy.getProyNombre().length() > 100) {
                be.addError(Utils.mensajeLargoCampo("nombreProgProy", 100));
            }

            if (fichaTo.getPreFk() != null) {
                if (fichaTo.getPreFk().getPreMoneda() != null
                        && (proy.getProyPreFk().getPreMoneda() == null || proy.getProyPreFk().getPreMoneda().getMonPk() == null || proy.getProyPreFk().getPreMoneda().getMonPk() == -1)) {
                    be.addError(MensajesNegocio.ERROR_PRESUPUESTO_MONEDA);
                }
                if (fichaTo.getPreFk().getFuenteFinanciamiento() != null
                        && (proy.getProyPreFk().getFuenteFinanciamiento() == null || proy.getProyPreFk().getFuenteFinanciamiento().getFuePk() == null || proy.getProyPreFk().getFuenteFinanciamiento().getFuePk() == -1)) {
                    be.addError(MensajesNegocio.ERROR_PRESUPUESTO_FUENTE_FINANC);
                }
            }
            if (proy.getProyPreFk() != null
                    && proy.getProyPreFk().getAdquisicionSet() != null) {
                for (Adquisicion adq : proy.getProyPreFk().getAdquisicionSet()) {
                    if (adq.getAdqFuente() == null || adq.getAdqFuente().getFuePk() == null || adq.getAdqFuente().getFuePk() == -1) {
                        be.addError(MensajesNegocio.ERROR_ADQISICION_FUENTE);
                        break;
                    }
                    if (adq.getAdqMoneda() == null || adq.getAdqMoneda().getMonPk() == null || adq.getAdqMoneda().getMonPk() == -1) {
                        be.addError(MensajesNegocio.ERROR_ADQISICION_MONEDAS);
                        break;
                    }
                }
            }

            if (proy.getDocumentosSet() != null) {
                for (Documentos doc : proy.getDocumentosSet()) {
                    if (doc.getDocsTipo() != null
                            && doc.getDocsTipo().getTipodocInstTipoDocFk() != null
                            && doc.getDocsTipo().getTipodocInstTipoDocFk().getTipdocPk() == null
                            || doc.getDocsTipo().getTipodocInstTipoDocFk().getTipdocPk() == -1) {
                        be.addError(MensajesNegocio.ERROR_DOCS_TIPO);
                        break;
                    }
                }
            }
        }

        if (be.getErrores().size() > 1) {
            logger.logp(Level.INFO, ProyectosValidacion.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
