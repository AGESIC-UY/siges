package com.sofis.web.componentes;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class SofisPopupUI implements Serializable {

    private Boolean modal = Boolean.TRUE;
    private Boolean autoCenter = Boolean.TRUE;
    private String tituloPopup = "";
    private Boolean renderPopup = Boolean.FALSE;

    public SofisPopupUI() {
    }

    public SofisPopupUI(String titulo) {
        this.tituloPopup = titulo;
    }

    public String cerrar() {
        this.renderPopup = Boolean.FALSE;
        return null;
    }

    public String abrir() {
        this.renderPopup = Boolean.TRUE;
        return null;
    }

    public Boolean isModal() {
        return modal;
    }

    public void setModal(Boolean modal) {
        this.modal = modal;
    }

    public Boolean isAutoCenter() {
        return autoCenter;
    }

    public void setAutoCenter(Boolean autoCenter) {
        this.autoCenter = autoCenter;
    }

    public String getTituloPopup() {
        return tituloPopup;
    }

    public void setTituloPopup(String tituloPopup) {
        this.tituloPopup = tituloPopup;
    }

    public Boolean isRenderPopup() {
        return renderPopup;
    }

    public void setRenderPopup(Boolean renderPopup) {
        this.renderPopup = renderPopup;
    }
}
