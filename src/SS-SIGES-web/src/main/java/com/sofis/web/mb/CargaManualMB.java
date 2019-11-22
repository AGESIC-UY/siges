package com.sofis.web.mb;

import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.data.Configuracion;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "cargaManualMB")
@ViewScoped
public class CargaManualMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CargaManualMB.class.getName());

	@Inject
	private ConfiguracionDelegate configuracionDelegate;
	private File upFileDoc;

	/**
	 * Creates a new instance of ConfiguracionMB
	 */
	public CargaManualMB(){
	}

	public void subirArchivoDocAction(FileEntryEvent e) {

		JSFUtils.removerMensages();
		if (e != null && e.getComponent() != null) {
			FileEntry fe = (FileEntry) e.getComponent();
			FileEntryResults results = fe.getResults();
			File archivoS = results.getFiles() != null ? results.getFiles().get(0).getFile() : null;
			if (archivoS == null) {
				JSFUtils.agregarMsgError("formularioManual", Labels.getValue("error_archivo_subido_fail_manual_usu"), null);
				return;
			}
			try {
				String mimeType = new MimetypesFileTypeMap().getContentType(archivoS);
				logger.log(Level.INFO, "Mime Type:{0}", mimeType);
				if (mimeType == null) {
//					|| !mimeType.equalsIgnoreCase("application/pdf")) {
					JSFUtils.agregarMsgError("formularioManual", Labels.getValue("error_archivo_no_permitido"), null);
					return;
				}
				upFileDoc = archivoS;
			} catch (Exception e2) {
				JSFUtils.agregarMsgError("formularioManual", Labels.getValue("error_subir_archivo"), null);
				logger.log(Level.SEVERE, e2.getMessage(), e2);
			}
		}
	}

	public String agregarDocumentoAction() {
            try {
                if (upFileDoc != null) {
                    // Controlo que lo ingresado sea un archivo de tipo pdf
                    if(upFileDoc.getName().contains(".pdf")){
                        byte[] bytesArray = new byte[(int) upFileDoc.length()];
                        FileInputStream fis = new FileInputStream(upFileDoc);
                        fis.read(bytesArray); //read file into bytes[]
                        fis.close();

                        Configuracion confDocumentosDir = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTOS_DIR, null);
                        String resourcePath = confDocumentosDir.getCnfValor();
                        Configuracion confNombreArchivoManual = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.NOMBRE_ARCHIVO_MANUAL, null);
                        String nombreArchivoManual = confNombreArchivoManual.getCnfValor();
                        String nombreCompleto = resourcePath + File.separator + nombreArchivoManual;

                        FileOutputStream fos = new FileOutputStream(new File(nombreCompleto));
                        fos.write(bytesArray);
                        fos.close();

                        JSFUtils.agregarMsgInfo("formularioManual", "Manual guardado con éxito", null);
                    }else{
                        JSFUtils.agregarMsg("formularioManual", "error_manual_tipo_de_archivo", null);
                    }
                }else{
                    JSFUtils.agregarMsg("formularioManual", "error_manual_null", null);
                }
            } catch (Exception ex) {
                    logger.log(Level.SEVERE, null, ex);
                    JSFUtils.agregarMsgError("formularioManual", Labels.getValue("error_subir_archivo"), null);
            }
            // Antes de finalizar dejo la variable en null para que no quede basura
            upFileDoc = null;
            return null;
	}
        
        public String eliminarDocumentoAction(){

            Configuracion confDocumentosDir = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTOS_DIR, null);
            String resourcePath = confDocumentosDir.getCnfValor();
            Configuracion confNombreArchivoManual = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.NOMBRE_ARCHIVO_MANUAL, null);
            String nombreArchivoManual = confNombreArchivoManual.getCnfValor();
            String nombreCompleto = resourcePath + File.separator + nombreArchivoManual;
            
            try{
                File archToDelete = new File(nombreCompleto);
                
                // Me fijo si el archivo ha sido eliminado previamente
                if(archToDelete.exists()){
                    archToDelete.delete();
                    JSFUtils.agregarMsgInfo("formularioManual", "Manual eliminado con éxito", null);                    
                }else{
                    JSFUtils.agregarMsg("formularioManual", "error_manual_arch_no_exist", null);                    
                }
            }catch(Exception ex){
                logger.log(Level.SEVERE, null, ex);
                JSFUtils.agregarMsgError("formularioManual", Labels.getValue("error_eliminar_archivo"), null);
            }

            return null;
        }

}
