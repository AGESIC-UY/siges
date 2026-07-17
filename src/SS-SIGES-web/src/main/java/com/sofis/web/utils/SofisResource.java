/*
 * 
 * 
 */
package com.sofis.web.utils;

import com.icesoft.faces.context.Resource;
import com.sofis.entities.data.DocFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class SofisResource implements Resource, Serializable {

    private static final long serialVersionUID = 1503517326229394466L;
    private final Date lastModified = new Date();
    private DocFile docFile;
    private String dir;

    public SofisResource(DocFile docFile, String dir) {
	this.docFile = docFile;
	this.dir = dir;
    }

    @Override
    public String calculateDigest() {
	return docFile != null ? docFile.getDocfilePk().toString() : "";
    }

    @Override
    public Date lastModified() {
	return lastModified;
    }

    @Override
    public InputStream open() throws IOException {
        System.out.println("rutaaa:" + dir + "/" + docFile.getDocfilePath());
        File f = new File(dir + "/" + docFile.getDocfilePath());
        System.out.println("name" + f.getName() + " " + "size" + f.getTotalSpace());
	return new FileInputStream(dir + "/" + docFile.getDocfilePath());
    }

    @Override
    public void withOptions(Options opt) throws IOException {
    }

    public DocFile getDocFile() {
	return docFile;
    }

    public void setDocFile(DocFile docFile) {
	this.docFile = docFile;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

}
