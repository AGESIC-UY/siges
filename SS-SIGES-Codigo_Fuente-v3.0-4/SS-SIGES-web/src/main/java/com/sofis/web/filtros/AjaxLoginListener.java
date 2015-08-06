package com.sofis.web.filtros;

import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class AjaxLoginListener implements PhaseListener {

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // NOOP.
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        //para enviar un nuevo redirect y que funcione la expiración de session

        FacesContext context = event.getFacesContext();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String originalURL = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
        if (context.getPartialViewContext().isAjaxRequest()
                && originalURL != null) {
            try {
                context.getExternalContext().redirect(originalURL);
            } catch (IOException e) {
                throw new FacesException(e);
            }
        }
    }
}