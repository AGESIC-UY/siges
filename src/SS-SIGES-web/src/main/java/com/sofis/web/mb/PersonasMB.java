package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Personas;
import com.sofis.entities.data.RolesInteresados;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.delegates.OrganismoDelegate;
import com.sofis.web.delegates.PersonasDelegate;
import com.sofis.web.delegates.RolesInteresadosDelegate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author SSGenerador
 */
@ManagedBean(name = "personasMB")
@ViewScoped
public class PersonasMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    
    @ManagedProperty(value = "#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private OrganismoDelegate orgDelegate;
    @Inject
    private PersonasDelegate persDelegate;
    @Inject
    private RolesInteresadosDelegate rolIntDelegate;
    
    private String busqNombre = "";
    private Integer busqOrganismo = 0;
    private Integer busqRolInteresado = 0;
    private List<Organismos> listaOrganismos = new ArrayList<>();
    private List<RolesInteresados> listaRolesInteresados = new ArrayList<>();
    private List<Personas> listaResultado = new ArrayList<>();
    private Boolean renderResultado = false;
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = "persNombre";
    private String ascendente = "Ascendente";
    private boolean renderPopupEditar = false;
    private boolean renderPopupUnificar = false;
    private Personas personaEditar = null;
    private List<Personas> personasUnificar = new ArrayList<>();
    private Personas personaUnificar = null;

    /**
     * Creates a new instance of SsUsuarioMB
     */
    public PersonasMB() {
    }

    /**
     * Creates a new instance of SsUsuarioMB
     */
    private void reset() {
        busqNombre = "";
        busqOrganismo = 0;
        busqRolInteresado = 0;
        listaResultado = new ArrayList<>();
        renderResultado = false;
    }

    @PostConstruct
    public void init() {
        listaOrganismos = orgDelegate.obtenerTodosActivos();
        listaOrganismos.add(0, new Organismos(0, " Sin seleccionar"));
        listaRolesInteresados = rolIntDelegate.obtenerTodos();
        listaRolesInteresados.add(0, new RolesInteresados(0, " Sin seleccionar"));
        buscar();
    }

    // <editor-fold defaultstate="collapsed" desc="eventos">
    public void cambiarCantPaginas(ValueChangeEvent evt) {
        buscar();
    }

    public void cambiarCriterioOrdenacion(ValueChangeEvent evt) {
        elementoOrdenacion = evt.getNewValue().toString();
        buscar();
    }

    public void cambiarAscendenteOrdenacion(ValueChangeEvent evt) {
        ascendente = evt.getNewValue().toString();
        buscar();
    }

    public String buscar() {
        Integer organismo = inicioMB.getOrganismoSeleccionado();
        listaResultado = persDelegate.buscar(busqNombre, organismo, elementoOrdenacion, "Ascendente".equals(ascendente));
        renderResultado = true;
        return null;
    }

    public String limpiar() {
        reset();
        return null;
    }

    public List<Organismos> getListaOrganismos() {
        return listaOrganismos;
    }

    public List<RolesInteresados> getListaRolesInteresados() {
        return listaRolesInteresados;
    }

    public List<Personas> getListaResultado() {
        return listaResultado;
    }

    // </editor-fold>
    public String getBusqNombre() {
        return busqNombre;
    }

    public void setBusqNombre(String busqNombre) {
        this.busqNombre = busqNombre;
    }

    public Integer getBusqOrganismo() {
        return busqOrganismo;
    }

    public void setBusqOrganismo(Integer busqOrganismo) {
        this.busqOrganismo = busqOrganismo;
    }

    public Integer getBusqRolInteresado() {
        return busqRolInteresado;
    }

    public void setBusqRolInteresado(Integer busqRolInteresado) {
        this.busqRolInteresado = busqRolInteresado;
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

    public String getAscendente() {
        return ascendente;
    }

    public void setAscendente(String ascendente) {
        this.ascendente = ascendente;
    }

    public Boolean getRenderResultado() {
        return renderResultado;
    }

    public List<Personas> getPersonasUnificar() {
        return personasUnificar;
    }

    public InicioMB getInicioMB() {
        return inicioMB;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    //=====================================================================
    public boolean isRenderPopupEditar() {
        return renderPopupEditar;
    }

    public Personas getPersonaEditar() {
        return personaEditar;
    }

    public void cancelar() {
        renderPopupEditar = false;
    }

    public void guardar() {
        persDelegate.guardar(personaEditar);
        renderPopupEditar = false;
        buscar();
    }

    public String editar(Integer persId) {
        try {
            personaEditar = persDelegate.obtenerPersonaPorId(persId);
            if (personaEditar != null) {
                renderPopupEditar = true;
            } else {
                String msg = "La persona no puede ser editada";
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            }
        } catch (GeneralException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            String msg = "La persona no puede ser editada";
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        return null;
    }

    public String eliminar(Integer persId) {
        try {
            persDelegate.eliminarPersona(persId);
            buscar();
        } catch (GeneralException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            String msg = ex.getMessage();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        return null;
    }

    public Personas getPersonaUnificar() {
        return personaUnificar;
    }

    //=====================================================================
    public boolean isRenderPopupUnificar() {
        return renderPopupUnificar;
    }

    public String unificar() {
        personasUnificar = new ArrayList();
        for (Personas p : listaResultado) {
            if (p.getSeleccionada()) {
                personasUnificar.add(p);
            }
        }
        if (!personasUnificar.isEmpty()) {
            personaUnificar = (Personas) personasUnificar.get(0);
            renderPopupUnificar = true;
        }
        return null;
    }

    public void cambioPersonasUnificar(ValueChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();
        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
            try {
                String sPersId = (String) event.getNewValue();
                Integer persId = Integer.valueOf(sPersId);
                personaUnificar = persDelegate.obtenerPersonaPorId(persId);
//          personaEditar = personasUnificar.get(persId);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void cancelarUnificar() {
        renderPopupUnificar = false;
    }

    public void confirmarUnificar() {
        try {
            //Primero guardar los datos de la persona final (por si fueron modificados)
            persDelegate.guardar(personaUnificar);
            //Luego unificar las restantes personas
            persDelegate.unificar(personaUnificar, personasUnificar);
            renderPopupUnificar = false;
            buscar();
        } catch (GeneralException gEx) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, gEx);
            String msg = gEx.getMessage();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            gEx.printStackTrace();
        }
    }

}
