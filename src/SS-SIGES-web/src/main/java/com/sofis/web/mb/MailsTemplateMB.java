package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.MailsTemplate;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.MailsTemplateDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "mailsTemplateMB")
@ViewScoped
public class MailsTemplateMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(MailsTemplateMB.class.getName());
    //private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    private static final String MAIL_CODIGO = "mailTmpCod";
    private static final String MAIL_ASUNTO = "mailTmpAsunto";
    private static final String MAIL_MENSAJE = "mailTmpMensaje";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private MailsTemplateDelegate mailsTemplateDelegate;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = MAIL_CODIGO;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroCodigo;
    private List<MailsTemplate> listaResultado;
    private MailsTemplate mailEnEdicion;

    public MailsTemplateMB() {
    }

    public SofisPopupUI getRenderPopupEdicion() {
        return renderPopupEdicion;
    }

    public void setRenderPopupEdicion(SofisPopupUI renderPopupEdicion) {
        this.renderPopupEdicion = renderPopupEdicion;
    }

    public String getCantElementosPorPagina() {
        return cantElementosPorPagina;
    }

    public void setCantElementosPorPagina(String cantElementosPorPagina) {
        this.cantElementosPorPagina = cantElementosPorPagina;
    }

    public String getElementoOrdenacion() {
        return elementoOrdenacion;
    }

    public void setElementoOrdenacion(String elementoOrdenacion) {
        this.elementoOrdenacion = elementoOrdenacion;
    }

    public int getAscendente() {
        return ascendente;
    }

    public void setAscendente(int ascendente) {
        this.ascendente = ascendente;
    }

    public String getFiltroCodigo() {
        return filtroCodigo;
    }

    public void setFiltroCodigo(String filtroCodigo) {
        this.filtroCodigo = filtroCodigo;
    }

    public List<MailsTemplate> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<MailsTemplate> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public MailsTemplate getMailEnEdicion() {
        return mailEnEdicion;
    }

    public void setMailEnEdicion(MailsTemplate mailEnEdicion) {
        this.mailEnEdicion = mailEnEdicion;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    @PostConstruct
    public void init() {
        /*
        *   30-05-2018 Nico: Se sacan las variables que se inicializan del constructor y se pasan al PostConstruct
        */        
        
//        filtroNombre = "";
        filtroCodigo = "";
        listaResultado = new ArrayList<MailsTemplate>();
        mailEnEdicion = new MailsTemplate();        
        if(mailEnEdicion.getMailTmpMensaje() == null || mailEnEdicion.getMailTmpMensaje().equals("")){
            mailEnEdicion.setMailTmpMensaje("<p></p>");
        }
        inicioMB.cargarOrganismoSeleccionado();

        buscar();
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        mailEnEdicion = new MailsTemplate();

        renderPopupEdicion.abrir();
        return null;
    }

    /**
     * Action eliminar un area tematica.
     *
     * @return
     */
    public String eliminar(Integer mailPk) {
        if (mailPk != null) {
            try {
                mailsTemplateDelegate.eliminarMail(mailPk);
                for (MailsTemplate mail : listaResultado) {
                    if (mail.getMailTmpPk().equals(mailPk)) {
                        listaResultado.remove(mail);
                        break;
                    }
                }
            } catch (BusinessException e) {
                logger.log(Level.SEVERE, null, e);
                JSFUtils.agregarMsgs("", e.getErrores());
                inicioMB.setRenderPopupMensajes(Boolean.TRUE);
            }
        }
        return null;
    }

    /**
     * Action Buscar.
     *
     * @return
     */
    public String buscar() {
        Map<String, Object> mapFiltro = new HashMap<String, Object>();
//        mapFiltro.put("nombre", filtroNombre);
        mapFiltro.put("cod", filtroCodigo);
        listaResultado = mailsTemplateDelegate.busquedaMailFiltro(inicioMB.getOrganismo().getOrgPk(), mapFiltro, elementoOrdenacion, ascendente);

        return null;
    }

    public String editar(Integer aPk) {
        try {
            mailEnEdicion = mailsTemplateDelegate.obtenerMailPorPk(aPk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
        }

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {
        mailEnEdicion.setMailTmpOrgFk(inicioMB.getOrganismo());

        try {
            mailEnEdicion = mailsTemplateDelegate.guardarMail(mailEnEdicion);

            if (mailEnEdicion != null) {
                renderPopupEdicion.cerrar();
                buscar();
            }
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            /*
            *  18-06-2018 Inspección de código.
            */

            //JSFUtils.agregarMsgs(BUSQUEDA_MSG, be.getErrores());

            for(String iterStr : be.getErrores()){
                JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);                
            }              
            
        }
        return null;
    }

    public void cancelar() {
        renderPopupEdicion.cerrar();
    }

    /**
     * Action limpiar formulario de busqueda.
     *
     * @return
     */
    public String limpiar() {
        filtroCodigo = null;
        listaResultado = null;
        elementoOrdenacion = MAIL_CODIGO;
        ascendente = 1;

        return null;
    }

    public void cambiarCantPaginas(ValueChangeEvent evt) {
        buscar();
    }

    public void cambiarCriterioOrdenacion(ValueChangeEvent evt) {
        elementoOrdenacion = evt.getNewValue().toString();
        buscar();
    }

    public void cambiarAscendenteOrdenacion(ValueChangeEvent evt) {
        ascendente = Integer.valueOf(evt.getNewValue().toString());
        buscar();
    }
}
