package com.sofis.web.utils;

import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.properties.Labels;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class JSFUtils {

    private static String findMessage(String labelKey) {
        if (Labels.containsKey(labelKey)) {
            return Labels.getValue(labelKey);
        }
        return labelKey;
    }

    public static void agregarMsg(String summary) {
        agregarMsg(null, summary, null);
    }

    public static void agregarMsg(String clientId, String summary, String detail) {
        if (summary != null) {
            String[] msgSplit = new String[]{""};
            String mensaje;
            if (!StringsUtils.isEmpty(summary)) {
                msgSplit = summary.split("_");
                mensaje = findMessage(summary);
            } else {
                mensaje = summary;
            }

            if (msgSplit.length > 0 && msgSplit[0].equalsIgnoreCase("ERROR")
                    || mensaje.startsWith("[ERROR]")) {
                agregarMsgError(clientId, mensaje.replace("[ERROR]", ""), detail);
            } else if (msgSplit.length > 0 && msgSplit[0].equalsIgnoreCase("WARN")
                    || mensaje.startsWith("[WARN]")) {
                agregarMsgWarn(clientId, mensaje.replace("[WARN]", ""), detail);
            } else {
                agregarMsgInfo(clientId, mensaje, detail);
            }
        }
    }

    public static void agregarMsgs(String clientId, List<String> summary) {
        if (summary != null) {
            for (String mensaje : summary) {
                agregarMsg(clientId, mensaje, null);
            }
        }
    }
    
    public static void agregarMsgsErrores(String clientId, List<String> summary) {
        if (summary != null) {
            for (String mensaje : summary) {
                agregarMsgError(clientId, mensaje, null);
            }
        }
    }

    public static void agregarMensajes(FacesMessage.Severity sev, List<String> mensajes) {
        if (mensajes != null) {
            for (String mensaje : mensajes) {
                mensaje = findMessage(mensaje);
                FacesMessage msg = new FacesMessage(sev, mensaje, mensaje);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public static void agregarMensajes(String clientId, FacesMessage.Severity sev, List<String> summary) {
        if (summary != null) {
            for (String mensaje : summary) {
                mensaje = findMessage(mensaje);
                FacesMessage msg = new FacesMessage(sev, mensaje, null);
                FacesContext.getCurrentInstance().addMessage(getClientId(clientId), msg);
            }
        }
    }

    public static void agregarMsgError(String summary) {
        agregarMsgError(null, summary, null);
    }

    public static void agregarMsgInfo(String summary) {
        agregarMsgInfo(null, summary, null);
    }

    public static void agregarMsgWarn(String summary) {
        agregarMsgWarn(null, summary, null);
    }

    public static void agregarMsgError(String clientId, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(getClientId(clientId), new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }

    public static void agregarMsgInfo(String clientId, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(getClientId(clientId), new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }

    public static void agregarMsgWarn(String clientId, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(getClientId(clientId), new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }

    public static void removerMensages() {
        Iterator<FacesMessage> msgIterator = FacesContext.getCurrentInstance().getMessages();
        while (msgIterator.hasNext()) {
            msgIterator.next();
            msgIterator.remove();
        }
    }

    public static UIComponent findComponent(UIComponent parent, String id) {
        if (id != null && parent != null) {
            if (id.equals(parent.getId())) {
                return parent;
            }
            Iterator<UIComponent> kids = parent.getFacetsAndChildren();
            while (kids.hasNext()) {
                UIComponent kid = kids.next();
                UIComponent found = findComponent(kid, id);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    public static String getClientId(String id) {
        UIComponent componente = findComponent(FacesContext.getCurrentInstance().getViewRoot(), id);
        if (componente != null) {
            return componente.getClientId();
        }
        return id;
    }
}
