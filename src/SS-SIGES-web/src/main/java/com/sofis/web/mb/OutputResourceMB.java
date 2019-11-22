package com.sofis.web.mb;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.util.Date;
import com.icesoft.faces.context.Resource;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.data.Configuracion;
import com.sofis.web.delegates.ConfiguracionDelegate;
import java.io.File;
import java.io.FileInputStream;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Sofis Solutions
 */
@ManagedBean(name = "outputResourceMB")
@ViewScoped
public class OutputResourceMB implements Serializable {

    @Inject
    private ConfiguracionDelegate configuracionDelegate;

    private String resourcePath = "";
    private String nombreArchivoManual = "";

    @PostConstruct
    public void init() {
        Configuracion confDocumentosDir = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTOS_DIR, null);
        resourcePath = confDocumentosDir.getCnfValor();
        Configuracion confNombreArchivoManual = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.NOMBRE_ARCHIVO_MANUAL, null);
        nombreArchivoManual = confNombreArchivoManual.getCnfValor();
    }


    public Resource getPdfResource() {
        return new MyResource(resourcePath, nombreArchivoManual);
    }

    class MyResource implements Resource, Serializable {

        private String camino = null;
        private String nombreArchivo = null;
        private InputStream inputStream;
        private final Date lastModified;

        public MyResource(String camino, String nombreArchivo) {
            this.camino = camino;
            this.nombreArchivo = nombreArchivo;
            this.lastModified = new Date();
        }

        public InputStream open() throws IOException {
            if (inputStream == null) {
                String archivo = camino + File.separator + nombreArchivo;
                FileInputStream stream = new FileInputStream(archivo);
                byte[] byteArray = IOUtils.toByteArray(stream);
                inputStream = new ByteArrayInputStream(byteArray);
            } else {
                inputStream.reset();
            }
            return inputStream;
        }

        public String calculateDigest() {
            return nombreArchivo;
        }

        public Date lastModified() {
            return lastModified;
        }

        public void withOptions(Options arg0) throws IOException {
        }
    }

}
