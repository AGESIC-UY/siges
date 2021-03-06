package com.sofis.entities.tipos;

import com.sofis.entities.data.AreaConocimiento;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Usuario
 */
public class LeccAprendidasTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer lecaprFk;
    private Integer lecaprProyFk;
    private String lecaprProyNombre;
    private Integer usuarioFk;
    private String lecaprUsuNombre;
    private Integer lecaprTipoFk;
    private Date lecaprFecha;
    private String lecaprTexto;
    private Set<AreaConocimiento> areaConocimientosSet;
    private String lecaprTipoStr;

    public Integer getLecaprFk() {
        return lecaprFk;
    }

    public void setLecaprFk(Integer lecaprFk) {
        this.lecaprFk = lecaprFk;
    }

    public Integer getLecaprProyFk() {
        return lecaprProyFk;
    }

    public String getLecaprProyFkTipo() {
        return lecaprProyFk != null ? "2-".concat(lecaprProyFk.toString()) : null;
    }

    public String getLecaprProyFkStr() {
        return lecaprProyFk.toString();
    }

    public void setLecaprProyFk(Integer lecaprProyFk) {
        this.lecaprProyFk = lecaprProyFk;
    }

    public String getLecaprProyNombre() {
        return lecaprProyNombre;
    }

    public void setLecaprProyNombre(String lecaprProyNombre) {
        this.lecaprProyNombre = lecaprProyNombre;
    }

    public Integer getUsuarioFk() {
        return usuarioFk;
    }

    public void setUsuarioFk(Integer usuarioFk) {
        this.usuarioFk = usuarioFk;
    }

    public String getLecaprUsuNombre() {
        return lecaprUsuNombre;
    }

    public void setLecaprUsuNombre(String lecaprUsuNombre) {
        this.lecaprUsuNombre = lecaprUsuNombre;
    }

    public Integer getLecaprTipoFk() {
        return lecaprTipoFk;
    }

    public void setLecaprTipoFk(Integer lecaprTipoFk) {
        this.lecaprTipoFk = lecaprTipoFk;
    }

    public Date getLecaprFecha() {
        return lecaprFecha;
    }

    public void setLecaprFecha(Date lecaprFecha) {
        this.lecaprFecha = lecaprFecha;
    }

    public String getLecaprTexto() {
        return lecaprTexto;
    }

    public String lecaprTexto(Long largo) {
        if (largo != null && largo > 0 && lecaprTexto.length() > largo) {
            return lecaprTexto.substring(0, largo.intValue()).concat("...");
        }
        return lecaprTexto;
    }

    public void setLecaprTexto(String lecaprTexto) {
        this.lecaprTexto = lecaprTexto;
    }

    public Set<AreaConocimiento> getAreaConocimientosSet() {
        return areaConocimientosSet;
    }

    public void setAreaConocimientosSet(Set<AreaConocimiento> areaConocimientosSet) {
        this.areaConocimientosSet = areaConocimientosSet;
    }

    public String getLecaprTipoStr() {
        return lecaprTipoStr;
    }

    public void setLecaprTipoStr(String lecaprTipoStr) {
        this.lecaprTipoStr = lecaprTipoStr;
    }

    public String getAreaConocimientoStr() {
        if (areaConocimientosSet != null && !areaConocimientosSet.isEmpty()) {
            StringBuffer result = new StringBuffer("");
            for (AreaConocimiento areaCon : areaConocimientosSet) {
                result = result.append(result.toString().isEmpty() ? "" : ", ").append(areaCon.getConNombre());
            }
            return result.append(".").toString();
        }
        return null;
    }
}
