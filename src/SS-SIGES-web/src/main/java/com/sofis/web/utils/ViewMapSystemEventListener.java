package com.sofis.web.utils;

import com.sun.faces.application.view.ViewScopeManager;
import java.util.Map;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

public class ViewMapSystemEventListener implements SystemEventListener {

    private String currentId;

    @Override
    public void processEvent(SystemEvent event) throws AbortProcessingException {
        /*
         * 1. Get the source of the event and its view id.
         * 2. Check if the currentId is null, if so, set it to the source id.
         * 3. Check the currentId and the new id. 
         *     a. If they are the same, continue. We are on the same page.
         *     b. If they are different, clear the view map, and remove from Session map.
         */
        UIViewRoot uivr = (UIViewRoot) event.getSource();
        String id = uivr.getViewId();
        FacesContext fctx = FacesContext.getCurrentInstance();
        ViewScopeManager vsm = ViewScopeManager.getInstance(fctx);

        System.out.println("VIEW ID " + id + "-" + currentId);

        if (currentId == null) {
            currentId = id;
        }

        if (!currentId.equals(id)) {

            System.out.println("DEBE LIMPIAR VIEW ID " + id);
            //Clear map and set currentId to new id.
//            vsm.clear(fctx);
//            String key = (String) uivr.getTransientStateHelper().getTransient(ViewScopeManager.VIEW_MAP_ID);
//            uivr.getViewMap().clear();

            Map<String, Object> viewMaps = (Map<String, Object>) fctx.getExternalContext().getSessionMap().get(ViewScopeManager.ACTIVE_VIEW_MAPS);

            for (String active : fctx.getExternalContext().getSessionMap().keySet()) {
                System.out.println("sessionMAP:" + active);
            }

            Map m = (Map) fctx.getExternalContext().getSessionMap().get("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");

            if (m != null) {
                System.out.println("size :" + m.size());
            }

            if (viewMaps != null) {
                for (String active : viewMaps.keySet()) {
                    System.out.println("active:" + active);
                }
            }

//            viewMaps.remove(key);
            currentId = id;
        }

    }

    @Override
    public boolean isListenerForSource(Object source) {
        return (source instanceof UIViewRoot);
    }

}
