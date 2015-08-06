package com.sofis.entities.enums;

/**
 *
 * @author Usuario
 */
public class NivelEnum {

    private Integer id;
    private String label;

    public NivelEnum(Integer id) {
        this.id = id;
    }
    
    public NivelEnum(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NivelEnum)) {
            return false;
        }
        NivelEnum other = (NivelEnum) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
