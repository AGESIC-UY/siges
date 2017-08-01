/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.sofisform.to.utils;

import java.io.Serializable;

/**
 *
 * @author Sofis
 */
public class EmailAttachmentTO  implements Serializable{

    String fileName = "";
    String contentType = "";
    byte[] bytes = null;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
}
