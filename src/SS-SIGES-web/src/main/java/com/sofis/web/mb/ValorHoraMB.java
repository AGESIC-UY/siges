package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.ValorHora;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.MonedaDelegate;
import com.sofis.web.delegates.ValorHoraDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "valorHoraMB")
@ViewScoped
public class ValorHoraMB implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ValorHoraMB.class.getName());
    private static final String VALOR_HORA_MSG = "valHoraPopupMsg";
    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    // Injection
    @Inject
    private MonedaDelegate monedaDelegate;
    @Inject
    private ValorHoraDelegate valorHoraDelegate;
    // Componentes
    private SofisPopupUI renderPopupValorHora;
    private SofisCombo listaMonedaCombo;
    // Variables
    private ValorHora valHoraEnEdicion;
    private List<ValorHora> listaValorHora;
    private List<Moneda> listaMoneda;

    public ValorHoraMB() {
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public SofisPopupUI getRenderPopupValorHora() {
        return renderPopupValorHora;
    }

    public void setRenderPopupValorHora(SofisPopupUI renderPopupValorHora) {
        this.renderPopupValorHora = renderPopupValorHora;
    }

    public ValorHora getValHoraEnEdicion() {
        return valHoraEnEdicion;
    }

    public void setValHoraEnEdicion(ValorHora valHoraEnEdicion) {
        this.valHoraEnEdicion = valHoraEnEdicion;
    }

    public List<ValorHora> getListaValorHora() {
        return listaValorHora;
    }

    public void setListaValorHora(List<ValorHora> listaValorHora) {
        this.listaValorHora = listaValorHora;
    }

    public SofisCombo getListaMonedaCombo() {
        return listaMonedaCombo;
    }

    public void setListaMonedaCombo(SofisCombo listaMonedaCombo) {
        this.listaMonedaCombo = listaMonedaCombo;
    }

    public List<Moneda> getListaMoneda() {
        return listaMoneda;
    }

    public void setListaMoneda(List<Moneda> listaMoneda) {
        this.listaMoneda = listaMoneda;
    }

    @PostConstruct
    public void init() {
        renderPopupValorHora = new SofisPopupUI();
        listaMonedaCombo = new SofisCombo();
        valHoraEnEdicion = new ValorHora();        
    }

    private void cargarPopup(SsUsuario usu) {
        listaMoneda = monedaDelegate.obtenerMonedas();
        if (listaMoneda != null) {
            listaMonedaCombo = new SofisCombo((List) listaMoneda, "monNombre");
            listaMonedaCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }
        if (valHoraEnEdicion != null) {
            listaMonedaCombo.setSelectedObject(valHoraEnEdicion.getValHorMonedaFk());
        }

        listaValorHora = valorHoraDelegate.obtenerValorHoraPorUsu(usu.getUsuId(), inicioMB.getOrganismo().getOrgPk());
    }

    public String editar(SsUsuario usu) {
        if (usu != null) {
            cargarPopup(usu);

            valHoraEnEdicion = new ValorHora();
            valHoraEnEdicion.setValHorUsuarioFk(usu);
            valHoraEnEdicion.setValHorOrganismosFk(inicioMB.getOrganismo());
        }

        renderPopupValorHora.abrir();
        return null;
    }

    public String cerrarPopupValorHora() {
        renderPopupValorHora.cerrar();
        return null;
    }

    public String cancelar() {
        renderPopupValorHora.cerrar();
        return null;
    }

    public String guardarValHora() {
        Moneda mon = (Moneda) listaMonedaCombo.getSelectedObject();
        valHoraEnEdicion.setValHorMonedaFk(mon);
        try {
            valorHoraDelegate.guardarValHora(valHoraEnEdicion);
            SsUsuario usu = valHoraEnEdicion.getValHorUsuarioFk();
            valHoraEnEdicion = new ValorHora(usu, inicioMB.getOrganismo());
            cargarPopup(usu);
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, null, be);
            
           /*
            *  19-06-2018 Inspección de código.
            */
            //JSFUtils.agregarMsgs(null, be.getErrores());

            for(String iterStr : be.getErrores()){
                JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);                
            }                
        }

        return null;
    }

    public String editarValHora(ValorHora valh) {
        valHoraEnEdicion = valh;
        cargarPopup(valHoraEnEdicion.getValHorUsuarioFk());

//        renderPopupValorHora.abrir();
        return null;
    }
}
