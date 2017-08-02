/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.sofisform.to;

import java.io.Serializable;
import org.jdom.Element;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 *
 *
 * 
 *
 */
public class InputFileLabelTO extends BindingComponentTO implements Serializable{

    private boolean progressBar = false;
    private String fileNamePattern = "";
    private String labelKey = "";
    private String propertyFile;
    private boolean downladInEdit = true;
    private String downloadMimeType = "application/pdf";
    private String downloadLabel = "";
    private String downloadImage = "";
    private String donwloadType = "";
    private String donwloadBeanBefore = "";
    private String donwloadBeanMethodBefore = "";

    public String getDonwloadBeanBefore() {
        return donwloadBeanBefore;
    }

    public void setDonwloadBeanBefore(String donwloadBeanBefore) {
        this.donwloadBeanBefore = donwloadBeanBefore;
    }

    public String getDonwloadBeanMethodBefore() {
        return donwloadBeanMethodBefore;
    }

    public void setDonwloadBeanMethodBefore(String donwloadBeanMethodBefore) {
        this.donwloadBeanMethodBefore = donwloadBeanMethodBefore;
    }

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    public String getFileNamePattern() {
        return fileNamePattern;
    }

    public String getPropertyFile() {
        return propertyFile;
    }

    public void setPropertyFile(String propertyFile) {
        this.propertyFile = propertyFile;
    }

    public void setFileNamePattern(String fileNamePattern) {
        this.fileNamePattern = fileNamePattern;
    }

    public boolean isProgressBar() {
        return progressBar;
    }

    public void setProgressBar(boolean progressBar) {
        this.progressBar = progressBar;
    }

    public String getDonwloadType() {
        return donwloadType;
    }

    public void setDonwloadType(String donwloadType) {
        this.donwloadType = donwloadType;
    }

    public boolean isDownladInEdit() {
        return downladInEdit;
    }

    public void setDownladInEdit(boolean downladInEdit) {
        this.downladInEdit = downladInEdit;
    }

    public String getDownloadImage() {
        return downloadImage;
    }

    public void setDownloadImage(String downloadImage) {
        this.downloadImage = downloadImage;
    }

    public String getDownloadLabel() {
        return downloadLabel;
    }

    public void setDownloadLabel(String downloadLabel) {
        this.downloadLabel = downloadLabel;
    }

    public String getDownloadMimeType() {
        return downloadMimeType;
    }

    public void setDownloadMimeType(String downloadMimeType) {
        this.downloadMimeType = downloadMimeType;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("inputfilelabel");
        toReturn = this.toXMLMetadata(toReturn);
        if (this.getPropertyFile() == null) {
            toReturn.setAttribute("propertyFile", "");
        } else {
            toReturn.setAttribute("propertyFile", this.getPropertyFile());
        }
        toReturn.setAttribute("downloadInEdit", this.isDownladInEdit() + "");
        if (this.getDownloadMimeType() == null) {
            toReturn.setAttribute("downloadMimeType", "");
        } else {
            toReturn.setAttribute("downloadMimeType", this.getDownloadMimeType());
        }
        if (this.getDownloadLabel() == null) {
            toReturn.setAttribute("downloadLabel", "");
        }else{
            toReturn.setAttribute("downloadLabel", this.getDownloadLabel());
        }
        if (this.getLabelKey() == null) {
            toReturn.setAttribute("labelKey", "");
        }else{
            toReturn.setAttribute("labelKey", this.getLabelKey());
        }

        if (this.getFileNamePattern() == null) {
            toReturn.setAttribute("fileNamePattern","");
        }else{
            toReturn.setAttribute("fileNamePattern", this.getFileNamePattern());
        }

        toReturn.setAttribute("progressBar", this.isProgressBar() + "");

        if (this.getDonwloadType() == null) {
            toReturn.setAttribute("donwloadType", "");
        }else{
            toReturn.setAttribute("donwloadType", this.getDonwloadType());
        }

        if (this.getDownloadImage() == null) {
            toReturn.setAttribute("downloadImage", "");
        }else{
            toReturn.setAttribute("downloadImage", this.getDownloadImage());
        }
        
        if (this.getDonwloadBeanBefore() == null) {
            toReturn.setAttribute("donwloadBeanBefore", "");
        }else{
            toReturn.setAttribute("donwloadBeanBefore", this.getDonwloadBeanBefore());
        }
        if (this.getDonwloadBeanMethodBefore() == null) {
            toReturn.setAttribute("donwloadBeanMethodBefore", "");
        }else{
            toReturn.setAttribute("donwloadBeanMethodBefore", this.getDonwloadBeanMethodBefore());
        }

        
        
        return toReturn;
    }
}
