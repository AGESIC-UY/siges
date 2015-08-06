/*
 * 
 * 
 */
package com.sofis.web.utils;

import com.icesoft.faces.context.Resource;
import com.sofis.entities.data.DocFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class SofisResource implements Resource, Serializable {

    private static final long serialVersionUID = 1503517326229394466L;
    private final Date lastModified = new Date();
    private DocFile docFile;

    //    private Integer docId;
    //    private String fileName;
    //    private String mimeType;
    //    public SofisResource(Integer docId, String fileName, String mimeType) {
    //        ////System.out.println("*** docId = " + docId + " - fileName = " + fileName + " - mimeType = " + mimeType + ".");
    //
    //    }
    //        this.fileName = fileName;
    //        this.mimeType = mimeType;
    //    }
    public SofisResource(DocFile docFile) {
        this.docFile = docFile;
    }

    @Override
    public String calculateDigest() {
//        return docId != null ? docId.toString() : "";
        return docFile != null ? docFile.getDocfilePk().toString() : "";
    }

    @Override
    public Date lastModified() {
        return lastModified;
    }

    @Override
    public InputStream open() throws IOException {
//        ArchivoContenidoLocalDelegate dd = new ArchivoContenidoLocalDelegate();
//        byte[] content = dd.obtenerArchivoContenido(docId);
//        return new ByteArrayInputStream(content);
        if (docFile != null && docFile.getDocfileFile() != null) {
            byte[] content = docFile.getDocfileFile();
            return new ByteArrayInputStream(content);
        }
        return null;
    }

    @Override
    public void withOptions(Options opt) throws IOException {
//        if (fileName != null && mimeType != null) {
//            String resultString = Normalizer.normalize(this.fileName, Normalizer.Form.NFD);
//            resultString = resultString.replaceAll("[^\\x00-\\x7F]", "");
//            opt.setFileName(resultString);
//            opt.setMimeType(this.mimeType);
//        }
    }

    public DocFile getDocFile() {
        return docFile;
    }

    public void setDocFile(DocFile docFile) {
        this.docFile = docFile;
    }
    
}
