package com.sofis.web.mb;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "PopupMessageMB")
@SessionScoped
public class PopupMessageMB implements Serializable {

    private boolean opened = false;
    private boolean modal = true;
    private String title = "example.compat.popup.modal.title";
    private String description = "example.compat.popup.modal.description";

    public PopupMessageMB() {
    }

    @PostConstruct
    public void initMetaData() {
    }

    public boolean isOpened() {
        return opened;
    }

    public boolean getModal() {
        return modal;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public void setModal(boolean modal) {
        this.modal = modal;
    }

    public void toggleOpened(ActionEvent event) {
        opened = !opened;
    }

    public void openEvent(ActionEvent event) {
        opened = true;
    }

    public void closeEvent(ActionEvent event) {
        opened = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
