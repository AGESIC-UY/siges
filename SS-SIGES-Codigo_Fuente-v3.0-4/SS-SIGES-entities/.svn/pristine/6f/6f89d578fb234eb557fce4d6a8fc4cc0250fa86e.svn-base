package com.sofis.entities.tipos;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@XmlRootElement
public class MisTareasTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer progPk;
    private String progNombre;
    private Integer proyPk;
    private String proyNombre;
    private Integer entPk;
    private String entNombre;
    private Date entInicio;
    private Date entFin;
    private Integer entDuracion;
    private Integer entProgreso;
    private Date entInicioLineaBase;
    private Integer entDuracionLineaBase;
    private Date entFinLineaBase;

    public MisTareasTO(Integer progPk, String progNombre, Integer proyPk, String proyNombre,
            Integer entPk, String entNombre, Long entInicio, Long entFin, Integer entDuracion,
            Long entInicioLineaBase, Integer entDuracionLineaBase, Long entFinLineaBase) {
        this.progPk = progPk;
        this.progNombre = progNombre;
        this.proyPk = proyPk;
        this.proyNombre = proyNombre;
        this.entPk = entPk;
        this.entNombre = entNombre;
        this.entInicio = entInicio != null && entInicio > 0 ? new Date(entInicio) : null;
        this.entFin = entFin != null && entFin > 0 ? new Date(entFin) : null;
        this.entDuracion = entDuracion;
        this.entInicioLineaBase = entInicioLineaBase != null && entInicioLineaBase > 0 ? new Date(entInicioLineaBase) : null;
        this.entDuracionLineaBase = entDuracionLineaBase;
        this.entFinLineaBase = entFinLineaBase != null && entFinLineaBase > 0 ? new Date(entFinLineaBase) : null;
    }

    public MisTareasTO(Integer progPk, String progNombre, Integer proyPk, String proyNombre,
            Integer entPk, String entNombre, Date entInicio, Date entFin, Integer entDuracion,
            Date entInicioLineaBase, Integer entDuracionLineaBase, Date entFinLineaBase) {
        this.progPk = progPk;
        this.progNombre = progNombre;
        this.proyPk = proyPk;
        this.proyNombre = proyNombre;
        this.entPk = entPk;
        this.entNombre = entNombre;
        this.entInicio = entInicio;
        this.entFin = entFin;
        this.entDuracion = entDuracion;
        this.entInicioLineaBase = entInicioLineaBase;
        this.entDuracionLineaBase = entDuracionLineaBase;
        this.entFinLineaBase = entFinLineaBase;
    }

    public Integer getProgPk() {
        return progPk;
    }

    public void setProgPk(Integer progPk) {
        this.progPk = progPk;
    }

    public String getProgNombre() {
        return progNombre;
    }

    public void setProgNombre(String progNombre) {
        this.progNombre = progNombre;
    }

    public Integer getProyPk() {
        return proyPk;
    }

    public void setProyPk(Integer proyPk) {
        this.proyPk = proyPk;
    }

    public String getProyNombre() {
        return proyNombre;
    }

    public void setProyNombre(String proyNombre) {
        this.proyNombre = proyNombre;
    }

    public Integer getEntPk() {
        return entPk;
    }

    public void setEntPk(Integer entPk) {
        this.entPk = entPk;
    }

    public String getEntNombre() {
        return entNombre;
    }

    public void setEntNombre(String entNombre) {
        this.entNombre = entNombre;
    }

    public Date getEntInicio() {
        return entInicio;
    }

    public void setEntInicio(Date entInicio) {
        this.entInicio = entInicio;
    }

    public Date getEntFin() {
        return entFin;
    }

    public void setEntFin(Date entFin) {
        this.entFin = entFin;
    }

    public Integer getEntDuracion() {
        return entDuracion;
    }

    public void setEntDuracion(Integer entDuracion) {
        this.entDuracion = entDuracion;
    }

    public Integer getEntProgreso() {
        return entProgreso;
    }

    public void setEntProgreso(Integer entProgreso) {
        this.entProgreso = entProgreso;
    }

    public Date getEntInicioLineaBase() {
        return entInicioLineaBase;
    }

    public void setEntInicioLineaBase(Date entInicioLineaBase) {
        this.entInicioLineaBase = entInicioLineaBase;
    }

    public Integer getEntDuracionLineaBase() {
        return entDuracionLineaBase;
    }

    public void setEntDuracionLineaBase(Integer entDuracionLineaBase) {
        this.entDuracionLineaBase = entDuracionLineaBase;
    }

    public Date getEntFinLineaBase() {
        return entFinLineaBase;
    }

    public void setEntFinLineaBase(Date entFinLineaBase) {
        this.entFinLineaBase = entFinLineaBase;
    }
}
