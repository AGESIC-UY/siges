package com.sofis.web.ws.tipos;

import java.io.File;
import java.io.Serializable;
import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class FileUploadTO implements Serializable{

    public FileUploadTO() {
    }

    private File file;
    private MultimediaTO multimediaTO;

    public File getFile() {
        return file;
    }

    @FormParam("file")
    @PartType("application/octet-stream")
    public void setFile(File file) {
        this.file = file;
    }

    public MultimediaTO getMultimediaTO() {
        return multimediaTO;
    }

    @FormParam("multimedia")
    @PartType("application/json")
    public void setMultimediaTO(MultimediaTO multimediaTO) {
        this.multimediaTO = multimediaTO;
    }
}
