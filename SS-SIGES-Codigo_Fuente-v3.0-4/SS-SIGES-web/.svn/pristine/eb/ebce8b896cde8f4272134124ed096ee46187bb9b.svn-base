package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.validations.InicioOrganismoValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.OrganismoDelegate;
import com.sofis.web.genericos.constantes.ConstantesNavegacion;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
import com.sofis.web.utils.SofisComboG;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import org.icefaces.apache.commons.io.IOUtils;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "organismoMB")
@ViewScoped
public class OrganismoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String FORM_ORGANISMO_MSG = "formOrganismoMsg";
    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private OrganismoDelegate organismoDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;
    /**
     * Organismo ABM.
     */
    private Organismos organismoForm = new Organismos();
    //Copia Org.
    private SofisPopupUI renderPopupCopia = new SofisPopupUI();
    private Map<String, Object> mapCopiar;
    private SofisComboG<Organismos> organismosCombo;
    private boolean copiarAmbito;
    private boolean copiarAreasConocimiento;
    private boolean copiarAreasTematicas;
    private boolean copiarTiposDocumentos;
    private boolean copiarFuenteFinanciamiento;
//    private boolean copiarMonedas;
    private boolean copiarRolInteresados;
    private boolean copiarOrganizaciones;

    public OrganismoMB() {
        logger.finest("-- CREA OrganismoMB");
    }

    public InicioMB getInicioMB() {
        return inicioMB;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public Organismos getOrganismoForm() {
        return organismoForm;
    }

    public void setOrganismoForm(Organismos organismoForm) {
        this.organismoForm = organismoForm;
    }

    public SofisPopupUI getRenderPopupCopia() {
        return renderPopupCopia;
    }

    public void setRenderPopupCopia(SofisPopupUI renderPopupCopia) {
        this.renderPopupCopia = renderPopupCopia;
    }

    public SofisComboG<Organismos> getOrganismosCombo() {
        return organismosCombo;
    }

    public void setOrganismosCombo(SofisComboG<Organismos> organismosCombo) {
        this.organismosCombo = organismosCombo;
    }

    public boolean isCopiarAmbito() {
        return copiarAmbito;
    }

    public void setCopiarAmbito(boolean copiarAmbito) {
        this.copiarAmbito = copiarAmbito;
    }

    public boolean isCopiarAreasConocimiento() {
        return copiarAreasConocimiento;
    }

    public void setCopiarAreasConocimiento(boolean copiarAreasConocimiento) {
        this.copiarAreasConocimiento = copiarAreasConocimiento;
    }

    public boolean isCopiarAreasTematicas() {
        return copiarAreasTematicas;
    }

    public void setCopiarAreasTematicas(boolean copiarAreasTematicas) {
        this.copiarAreasTematicas = copiarAreasTematicas;
    }

    public boolean isCopiarTiposDocumentos() {
        return copiarTiposDocumentos;
    }

    public void setCopiarTiposDocumentos(boolean copiarTiposDocumentos) {
        this.copiarTiposDocumentos = copiarTiposDocumentos;
    }

    public boolean isCopiarFuenteFinanciamiento() {
        return copiarFuenteFinanciamiento;
    }

    public void setCopiarFuenteFinanciamiento(boolean copiarFuenteFinanciamiento) {
        this.copiarFuenteFinanciamiento = copiarFuenteFinanciamiento;
    }

    public boolean isCopiarRolInteresados() {
        return copiarRolInteresados;
    }

    public void setCopiarRolInteresados(boolean copiarRolInteresados) {
        this.copiarRolInteresados = copiarRolInteresados;
    }

    public boolean isCopiarOrganizaciones() {
        return copiarOrganizaciones;
    }

    public void setCopiarOrganizaciones(boolean copiarOrganizaciones) {
        this.copiarOrganizaciones = copiarOrganizaciones;
    }

    @PostConstruct
    public void init() {
        inicioMB.cargarOrganismoSeleccionado();
        cargarFormOrganismo();
    }

    public void cargarFormOrganismo() {
        if (inicioMB.getOrganismosUsuario() != null
                && inicioMB.getOrganismosUsuario().getSelectedObject() != null) {
            organismoForm = ((Organismos) inicioMB.getOrganismosUsuario().getSelectedObject()).newCopy();
        } else {
            organismoForm = new Organismos();
            organismoForm.setOrgActivo(Boolean.TRUE);
        }
    }

    public String guardarOrganismoAction() {
        JSFUtils.removerMensages();
        try {
            InicioOrganismoValidacion.validar(organismoForm);

            organismoForm = organismoDelegate.guardarOrgnanismo(organismoForm, mapCopiar);
            organismoDelegate.controlarDatosFaltantes();

            if (organismoForm != null && organismoForm.getOrgPk() != null) {
                JSFUtils.agregarMsg(FORM_ORGANISMO_MSG, "info_organismo_guardado", null);
                inicioMB.setOrganismo(organismoForm);
                inicioMB.setOrganismosUsuario(new SofisCombo(new ArrayList(organismoDelegate.obtenerTodos()), "orgNombre"));
                inicioMB.getOrganismosUsuario().setSelected(organismoForm.getOrgPk());
                cargarFormOrganismo();
            } else {
                JSFUtils.agregarMsg(FORM_ORGANISMO_MSG, "error_organismo_guardar", null);
            }
        } catch (BusinessException be) {
            JSFUtils.agregarMsgs(FORM_ORGANISMO_MSG, be.getErrores());
        }
        return null;
    }

    public void nuevoOrganismoAction() {
        JSFUtils.removerMensages();
        organismoForm = new Organismos();
    }

    public String adminUsuariosAction() {
        return ConstantesNavegacion.IR_ABM_USUARIOS;
    }

    public void subirLogoOrgAction(FileEntryEvent e) {
        JSFUtils.removerMensages();
        logger.info("[subirArchivoAction] Subiendo archivo...");
        if (e != null && e.getComponent() != null) {
            FileEntry fe = (FileEntry) e.getComponent();
            FileEntryResults results = fe.getResults();
            File archivo = results.getFiles().get(0).getFile();
            if (archivo == null) {
                JSFUtils.agregarMsg(FORM_ORGANISMO_MSG, "error_archivo_subido_fail", null);
                throw new BusinessException();
            }

            Configuracion confMaxLogo = configuracionDelegate.obtenerCnfPorCodigoYOrg("TAMANIO_MAX_LOGO_ORGANISMO", inicioMB.getOrganismo().getOrgPk());
            Integer maxLogo = Integer.valueOf(confMaxLogo.getCnfValor());
            if (maxLogo != null && archivo.length() > maxLogo) {
                JSFUtils.agregarMsgError(FORM_ORGANISMO_MSG, String.format(Labels.getValue("organismo_form_logo_size"), maxLogo), null);
                throw new BusinessException();
            }

            BufferedImage bimage = null;
            try {
                bimage = ImageIO.read(archivo);
            } catch (IOException ex) {
                Logger.getLogger(OrganismoMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            final int LOGO_ANCHO = 300;
            final int LOGO_ALTO = 80;
            if (bimage != null && (bimage.getWidth() > LOGO_ANCHO || bimage.getHeight() > LOGO_ALTO)) {
                JSFUtils.agregarMsgError(FORM_ORGANISMO_MSG, String.format(Labels.getValue("organismo_form_logo_dimension"), LOGO_ANCHO, LOGO_ALTO), null);
                throw new BusinessException();
            }

            try {
                String mimeType = new MimetypesFileTypeMap().getContentType(archivo);
                logger.log(Level.FINEST, "Mime Type:", mimeType);
                if (mimeType != null) {
                    //FIXME FD boolean esValido = archivoHechos.isAValidMimeType(mimeType);
                    boolean esValido = true;
                    if (!esValido) {
                        JSFUtils.agregarMsgError(FORM_ORGANISMO_MSG, "error_archivo_no_permitido", null);
                        return;
                    }
                } else {
                    JSFUtils.agregarMsg(FORM_ORGANISMO_MSG, "error_archivo_no_permitido", null);
                    return;
                }
                logger.info("[subirArchivoAction] Subiendo archivo finalizado");
                FileInputStream fileInputStream = new FileInputStream(archivo);
                byte[] bFile = IOUtils.toByteArray(fileInputStream);
                organismoForm.setOrgLogo(bFile);
                organismoForm.setOrgLogoNombre(archivo.getName());
            } catch (Exception e2) {
                JSFUtils.agregarMsg(FORM_ORGANISMO_MSG, "error_subir_archivo", null);
                throw new BusinessException(Labels.getValue("error_subir_archivo"));
            }
        }
    }

    public void limpiarForm() {
        JSFUtils.removerMensages();
        organismoForm = new Organismos();
        renderPopupCopia.cerrar();
        mapCopiar = null;
        copiarAmbito = false;
        copiarAreasConocimiento = false;
        copiarAreasTematicas = false;
        copiarTiposDocumentos = false;
        copiarFuenteFinanciamiento = false;
        copiarRolInteresados = false;
        copiarOrganizaciones = false;
    }

    public void eliminarLogo() {
        organismoForm.setOrgLogo(null);
        organismoForm.setOrgLogoNombre(null);
    }

    public void organismoChange(ValueChangeEvent event) {
        PhaseId phaseId = event.getPhaseId();
        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
            Organismos orgSelected = (Organismos) inicioMB.getOrganismosUsuario().getSelectedObject();
            if (orgSelected != null) {
                organismoForm = orgSelected.newCopy();
            }
            inicioMB.organismoChange(event);

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            try {
                ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
            } catch (IOException ex) {
                Logger.getLogger(OrganismoMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String copiarOrganismoAction() {
        renderPopupCopia = new SofisPopupUI();
        renderPopupCopia.setRenderPopup(true);

        List<Organismos> listOrg = organismoDelegate.obtenerTodos();
        if (listOrg != null) {
            organismosCombo = new SofisComboG(listOrg, "orgNombre");
            organismosCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }
        return null;
    }

    public String aceptarCopiaAction() {
        renderPopupCopia.setRenderPopup(false);

        if (organismosCombo != null
                && organismosCombo.getSelected() > 0) {
            mapCopiar = new HashMap<>();
            mapCopiar.put("organismo", organismosCombo.getSelectedT());
            mapCopiar.put("copiarAmbito", copiarAmbito);
            mapCopiar.put("copiarAreasConocimiento", copiarAreasConocimiento);
            mapCopiar.put("copiarAreasTematicas", copiarAreasTematicas);
            mapCopiar.put("copiarFuenteFinanciamiento", copiarFuenteFinanciamiento);
            mapCopiar.put("copiarOrganizaciones", copiarOrganizaciones);
            mapCopiar.put("copiarRolInteresados", copiarRolInteresados);
            mapCopiar.put("copiarTiposDocumentos", copiarTiposDocumentos);
        }
        return null;
    }

    public String cancelarCopiaAction() {
        renderPopupCopia.setRenderPopup(false);

        organismosCombo = null;
        copiarAmbito = false;
        copiarAreasConocimiento = false;
        copiarAreasTematicas = false;
        copiarTiposDocumentos = false;
        copiarFuenteFinanciamiento = false;
        copiarRolInteresados = false;
        copiarOrganizaciones = false;

        return null;
    }

    public Organismos getOrganismoSeleccionado() {
        if (organismosCombo != null && organismosCombo.getSelectedT() != null) {
            return (Organismos) organismosCombo.getSelectedT();
        }
        return null;
    }

    public String getCopiarDeOrgSelecionado() {
        if (getOrganismoSeleccionado() != null) {
            StringBuilder sb = new StringBuilder();
            if (copiarAmbito) {
                sb.append(Labels.getValue("organismo_copiar_Ambito")).append(", ");
            }
            if (copiarAreasConocimiento) {
                sb.append(Labels.getValue("organismo_copiar_AreasConocimiento")).append(", ");
            }
            if (copiarAreasTematicas) {
                sb.append(Labels.getValue("organismo_copiar_AreasTematicas")).append(", ");
            }
            if (copiarTiposDocumentos) {
                sb.append(Labels.getValue("organismo_copiar_TiposDocumentos")).append(", ");
            }
            if (copiarFuenteFinanciamiento) {
                sb.append(Labels.getValue("organismo_copiar_FuenteFinanciamiento")).append(", ");
            }
            if (copiarRolInteresados) {
                sb.append(Labels.getValue("organismo_copiar_RolInteresados")).append(", ");
            }
            if (copiarOrganizaciones) {
                sb.append(Labels.getValue("organismo_copiar_Organizaciones")).append(", ");
            }
            return sb.length() > 0 ? sb.substring(0, sb.length() - 2).concat(".") : null;
        }
        return null;
    }
}
