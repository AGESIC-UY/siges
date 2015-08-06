package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.web.properties.Labels;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "reportesMB")
@ViewScoped
public class ReportesMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public String mesAbreviadoStr(Integer mes) {
        if (mes != null) {
            return Labels.getValue("date_mes_abreviado_" + mes);
        }
        return null;
    }
}
