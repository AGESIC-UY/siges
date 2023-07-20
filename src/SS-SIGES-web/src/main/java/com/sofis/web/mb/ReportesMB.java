package com.sofis.web.mb;

import com.sofis.business.ejbs.ConfiguracionBean;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.properties.Labels;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "reportesMB")
@ViewScoped
public class ReportesMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ReportesMB.class.getName());

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;
    @Inject
    private ConfiguracionBean configuracionBean;

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public InicioMB getInicioMB() {
        return inicioMB;
    }

    public String mesAbreviadoStr(Integer mes) {
        if (mes != null) {
            return Labels.getValue("date_mes_abreviado_" + mes);
        }
        return null;
    }

    public String semaforosPresupuesto() {
        Integer orgPk = inicioMB.getOrganismo().getOrgPk();

        Integer limiteAmarilloPre = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, orgPk).getCnfValor());
        Integer limiteRojoPre = Integer.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, orgPk).getCnfValor());

        StringBuilder result = new StringBuilder();
        result.append("<b>Presupuesto:</b><br/>")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_VERDE).append(";\" disabled=\"disabled\">")
                .append(String.format("Verde: Real está entre %s%% y %s%%.", (100 - limiteAmarilloPre), (100 + limiteAmarilloPre)))
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_AMARILLO).append(";\" disabled=\"disabled\">")
                .append(String.format("Amarillo: Real está entre %s%% y %s%%, o entre %s%% y %s%%.", (100 - limiteRojoPre), (100 - limiteAmarilloPre), (100 + limiteAmarilloPre), (100 + limiteRojoPre)))
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_NARANJA).append(";\" disabled=\"disabled\">")
                .append(String.format("Naranja: Real es menor a %s%%.", (100 - limiteRojoPre)))
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_ROJO).append(";\" disabled=\"disabled\">")
                .append(String.format("Rojo: Real es mayor a %s%%.<br/>", (100 + limiteRojoPre)));

        return result.toString();
    }

    public String coloresGantt() {
        StringBuilder result = new StringBuilder();
        result.append("<b>Referencia:</b><br/>")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_AZUL).append(";\" disabled=\"disabled\">")
                .append("Azul: Porcentaje de avance finalizado.")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_VERDE).append(";\" disabled=\"disabled\">")
                .append("Verde: Porcentaje de avance en plazo.")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_ROJO).append(";\" disabled=\"disabled\">")
                .append("Rojo: Porcentaje de avance atrasado.")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_GRIS).append(";\" disabled=\"disabled\">")
                .append("Gris: Entregable padre sin esfuerzo.<br/>");

        return result.toString();
    }

    public String coloresAlcance() {
        StringBuilder result = new StringBuilder();

        Integer orgPk = inicioMB.getOrganismo().getOrgPk();
        Integer limiteAmarilloAlcance = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ALCANCE_INDICE_LIMITE_AMARILLO, orgPk).getCnfValor());
        Integer limiteRojoAlcance = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ALCANCE_INDICE_LIMITE_ROJO, orgPk).getCnfValor());

        result.append("<b>Referencia:</b><br/>")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_AZUL).append(";\" disabled=\"disabled\">")
                .append("Azul: Finalizado.")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_VERDE).append(";\" disabled=\"disabled\">")
                .append("Verde: Está entre ").append(limiteAmarilloAlcance).append("% y 100%.")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_AMARILLO).append(";\" disabled=\"disabled\">")
                .append("Amarillo: Está entre ").append(limiteRojoAlcance).append("% y ").append(limiteAmarilloAlcance - 1).append("%.")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_ROJO).append(";\" disabled=\"disabled\">")
                .append("Rojo: Es menor a ").append(limiteRojoAlcance).append("%.<br/>");

        return result.toString();
    }
}
