/*
 * 
 * 
 */
package com.sofis.web.utils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author sofis
 */
public class SofisCombo {

    private HashMap<Integer, Object> objetos = new HashMap<>();
    private HashMap<Integer, Object> objetosComunes = new HashMap<>();
    private HashMap<Integer, Object> objetosNoComunes = new HashMap<>();
    private List<SelectItem> items = new LinkedList<>();
    private List<SelectItem> itemsComunes = new LinkedList<>();
    private List<SelectItem> itemsNoComunes = new LinkedList<>();
    private Integer selected = null;
    private Boolean visible = true;
    private Boolean disabled = false;
    private String propertyName;
    // Esta variable solo se setea cuando se usan los items comunes y no comunes
    private Boolean comunes = null;

    // <editor-fold defaultstate="collapsed" desc="  Constructores ">
    public SofisCombo() {

    }

    public SofisCombo(List<Object> objetos) {
        if ((objetos != null) && (objetos.size() > 0)) {
            for (Object obj : objetos) {
                add(obj);
            }
            selected = objetos.get(0).hashCode();
        }
        this.objetos = this.objetosNoComunes;
        this.items = this.itemsNoComunes;
    }

    public SofisCombo(List<Object> objetos, String propertyName) {
        this.propertyName = propertyName;
        if ((objetos != null) && (objetos.size() > 0)) {
            for (Object obj : objetos) {
                add(obj);
            }
            selected = objetos.get(0).hashCode();
        }
        this.objetos = this.objetosNoComunes;
        this.items = this.itemsNoComunes;
    }

    public SofisCombo(List<Object> objetosComunes, List<Object> objetos, String propertyName) {
        this.propertyName = propertyName;
        if ((objetos != null) && (objetos.size() > 0)) {
            for (Object obj : objetos) {
                add(obj);
            }
        }
        if ((objetosComunes != null) && (objetosComunes.size() > 0)) {
            for (Object obj : objetosComunes) {
                addComun(obj);
            }
            selected = objetos.get(0).hashCode();
        }
        this.objetos = this.objetosComunes;
        this.items = this.itemsComunes;
        SelectItem si = new SelectItem(-2, "Otro...");
        this.items.add(si);
        comunes = true;
    }

    public SofisCombo(Set<Object> objetos, String propertyName) {
        this.propertyName = propertyName;
        if ((objetos != null) && (objetos.size() > 0)) {
            for (Object obj : objetos) {
                add(obj);
            }
            selected = objetos.iterator().next().hashCode();
        }
        this.objetos = this.objetosNoComunes;
        this.items = this.itemsNoComunes;
    }

    // </editor-fold>
    /**
     * Devulve todos los objetos que tiene el combo
     *
     * @return
     */
    public List getAllObjects() {
        LinkedList resultado = new LinkedList();
        Collection c = objetos.values();
        for (Object o : c) {
            resultado.add(o);
        }
        return resultado;
    }

    /**
     * Elimina el objeto del combo
     *
     * @param objeto
     */
    public void remove(Object objeto) {
        int hc = objeto.hashCode();
        SelectItem item = null;
        for (SelectItem si : items) {
            if (si.getValue().hashCode() == hc) {
                item = si;
            }
        }
        if (item != null) {
            items.remove(item);
            objetos.remove(item.getValue());
        }
    }

    public void addComun(Object objeto) {
        String name = null;
        if (propertyName == null) {
            name = objeto.toString();
        } else {
            try {
                name = getPropertyValue(objeto, propertyName).toString();
            } catch (Exception w) {
                w.printStackTrace();
            }

        }
        SelectItem selectItem = new SelectItem(objeto.hashCode(), name);
        itemsComunes.add(selectItem);
        objetosComunes.put(objeto.hashCode(), objeto);
    }

    public void add(Object objeto) {
        String name = null;
        if (propertyName == null) {
            name = objeto.toString();
        } else {
            try {
                name = getPropertyValue(objeto, propertyName).toString();
            } catch (Exception w) {
                w.printStackTrace();
            }

        }
        SelectItem selectItem = new SelectItem(objeto.hashCode(), name);
        itemsNoComunes.add(selectItem);
        objetosNoComunes.put(objeto.hashCode(), objeto);
    }

    public Object getSelectedObject() {
        if (selected != null) {
            return objetos.get(selected);
        } else {
            return null;
        }
    }

    public void setSelectedObject(Object objeto) {
        if (comunes != null && comunes == true) {
            if (!this.objetos.containsValue(objeto)) {
                this.items = itemsNoComunes;
                this.objetos = objetosNoComunes;
                comunes = false;
            }
        }

        if (objeto != null) {
            selected = objeto.hashCode();
        }
    }

    public void addEmptyItem(String name) {
        SelectItem si = new SelectItem(-1, name);
        items.add(0, si);
        objetos.put(-1, null);
        selected = -1;
    }

    public void addEmptyWithoutSelecting(String name) {
        SelectItem si = new SelectItem(-1, name);
        items.add(0, si);
        objetos.put(-1, null);
    }

    /**
     * Este evento es solo para cuando hay elementos comunes y no comunes
     *
     * @param event
     */
    public void changeItem(ValueChangeEvent event) {
        this.selected = (Integer) event.getNewValue();
        if (this.selected == -2) {
            this.items = this.itemsNoComunes;
            this.objetos = this.objetosNoComunes;
            comunes = false;
        }
    }


// <editor-fold defaultstate="collapsed" desc="  Funciones Auxiliares ">
    private Object getPropertyValue(Object object, String property) throws Exception {
        StringTokenizer st = new StringTokenizer(property, ".");
        if (st.countTokens() == 1) {
            property = property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
            Method prpopertyMehtid = object.getClass().getMethod("get" + property);
            return prpopertyMehtid.invoke(object);
        } else {
            //tenemos que navegar por get antes de llegar al metodo que queremos
            Object navigateObj = object;
            String lastToken = "";
            int count = st.countTokens();
            while (st.hasMoreTokens()) {
                String proNav = st.nextToken();
                proNav = proNav.substring(0, 1).toUpperCase() + proNav.substring(1, proNav.length());
                Method propNavM = navigateObj.getClass().getMethod("get" + proNav);
                count = count - 1;
                lastToken = proNav;
                if (count == 0) {
                    //retorna la propiedad navegando
                    Method method = navigateObj.getClass().getMethod("get" + lastToken);
                    Object toR = method.invoke(navigateObj);
                    return toR;
                }
                navigateObj = propNavM.invoke(navigateObj);
                if (navigateObj == null) {
                    throw new IllegalAccessException("Destino inaccesible " + property);
                }

            }

        }
        return null;

    }

    /*  private Object getPropertyValue(Object o, String propertyName) {
     if (o == null) {
     return null;
     }
     String methodName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);

     Method m = null;
     try {
     m = o.getClass().getMethod(methodName);
     } catch (NoSuchMethodException nsme) {
     methodName = "is" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
     try {
     m = o.getClass().getMethod(methodName);
     } catch (NoSuchMethodException nsme1) {
     nsme.printStackTrace();
     return null;
     }
     } catch (SecurityException se) {
     se.printStackTrace();
     return null;
     }

     Object propertyValue = null;
     try {
     propertyValue = m.invoke(o);
     } catch (Exception exc) {
     exc.printStackTrace();
     return null;
     }

     return propertyValue;
     }*/

    /* private void setPropertyValue(Object o, String propertyName,
     Class propertyClass, Object value) {

     String methodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);

     Method m = null;
     try {
     m = o.getClass().getDeclaredMethod(methodName, propertyClass);
     } catch (NoSuchMethodException nsme) {
     nsme.printStackTrace();
     return;

     } catch (SecurityException se) {
     se.printStackTrace();
     return;
     }

     try {
     m.invoke(o, value);
     } catch (Exception exc) {
     exc.printStackTrace();
     return;
     }
     }*/
    private void setPropertyValue(Object object, String property, Object value, Class value_class) throws Exception {
        StringTokenizer st = new StringTokenizer(property, ".");
        if (st.countTokens() == 1) {
            property = property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
            Method prpopertyMehtid = object.getClass().getMethod("set" + property, value_class);
            prpopertyMehtid.invoke(object, value);
        } else {
            //tenemos que navegar por get antes de llegar al metodo que queremos
            Object navigateObj = object;
            String lastToken = "";
            int count = st.countTokens();
            while (st.hasMoreTokens()) {
                String proNav = st.nextToken();
                proNav = proNav.substring(0, 1).toUpperCase() + proNav.substring(1, proNav.length());
                Method propNavM = navigateObj.getClass().getMethod("get" + proNav);
                count = count - 1;
                lastToken = proNav;
                if (count == 0) {
                    //retorna la propiedad navegando
                    Method method = navigateObj.getClass().getMethod("set" + lastToken, value_class);
                    method.invoke(navigateObj, value);
                    return;
                }
                navigateObj = propNavM.invoke(navigateObj);
                if (navigateObj == null) {
                    throw new IllegalAccessException("Destino inaccesible " + property);
                }

            }
        }
    }

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="  Getters y setters para la jsf ">
    public List<SelectItem> getItems() {
        return items;
    }

    public void setItems(List<SelectItem> items) {
        this.items = items;
    }

    public List<SelectItem> getItemsNoComunes() {
        return itemsNoComunes;
    }

    public void setItemsNoComunes(List<SelectItem> itemsNoComunes) {
        this.itemsNoComunes = itemsNoComunes;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean v) {
        this.visible = v;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
    // </editor-fold>
}
