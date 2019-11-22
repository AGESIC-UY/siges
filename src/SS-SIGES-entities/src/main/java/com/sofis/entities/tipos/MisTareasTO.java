package com.sofis.entities.tipos;

import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Productos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    private Boolean entParent;
    private Integer entProgreso;
    private Integer entEsfuerzo;
    private Double entEsfuerzoPorcentaje;
    private Date entInicioLineaBaseDate;
    private Integer entDuracionLineaBase;
    private Date entFinLineaBaseDate;
    private Boolean tieneProd;
    private Integer entId;
    private List<Productos> productos;
    private Estados proyEstado;
    
    @XmlTransient
    private Entregables ent;

    public String getKey() {
        return "" + (progPk != null ? progPk : "_") + "|" + (progNombre != null ? progNombre : "_") + "|" + proyPk + "|" + proyNombre;
    }

    public MisTareasTO() {
    }
    
    public MisTareasTO(Integer progPk, String progNombre, Integer proyPk, String proyNombre,
            Integer entPk, String entNombre, Long entInicio, Long entFin, Integer entDuracion,
            Boolean entParent, Long entInicioLineaBase, Integer entDuracionLineaBase, Long entFinLineaBase,
            Integer entProgreso, Integer entEsfuerzo, Integer prodIds, Entregables ent, Estados estado) {
        this.progPk = progPk;
        this.progNombre = progNombre;
        this.proyPk = proyPk;
        this.proyNombre = proyNombre;
        this.entPk = entPk;
        this.entNombre = entNombre;
        this.entInicio = entInicio != null && entInicio > 0 ? new Date(entInicio) : null;
        this.entFin = entFin != null && entFin > 0 ? new Date(entFin) : null;
        this.entDuracion = entDuracion;
        this.entParent = entParent;
        this.entInicioLineaBaseDate = entInicioLineaBase != null && entInicioLineaBase > 0 ? new Date(entInicioLineaBase) : null;
        this.entDuracionLineaBase = entDuracionLineaBase;
        this.entFinLineaBaseDate = entFinLineaBase != null && entFinLineaBase > 0 ? new Date(entFinLineaBase) : null;
        this.entProgreso = entProgreso;
        this.entEsfuerzo = entEsfuerzo;
        this.tieneProd = prodIds != null;
        this.ent = ent;
        this.entId = ent.getEntId();
        this.productos = new ArrayList<Productos>(ent.getEntProductosSet());
        this.proyEstado = estado;
    }

    public MisTareasTO(Integer progPk, String progNombre, Integer proyPk, String proyNombre,
            Integer entPk, String entNombre, Long entInicio, Long entFin, Integer entDuracion,
            Boolean entParent, Long entInicioLineaBase, Integer entDuracionLineaBase, Long entFinLineaBase,
            Integer entProgreso, Integer entEsfuerzo, Integer prodIds) {
        this.progPk = progPk;
        this.progNombre = progNombre;
        this.proyPk = proyPk;
        this.proyNombre = proyNombre;
        this.entPk = entPk;
        this.entNombre = entNombre;
        this.entInicio = entInicio != null && entInicio > 0 ? new Date(entInicio) : null;
        this.entFin = entFin != null && entFin > 0 ? new Date(entFin) : null;
        this.entDuracion = entDuracion;
        this.entParent = entParent;
        this.entInicioLineaBaseDate = entInicioLineaBase != null && entInicioLineaBase > 0 ? new Date(entInicioLineaBase) : null;
        this.entDuracionLineaBase = entDuracionLineaBase;
        this.entFinLineaBaseDate = entFinLineaBase != null && entFinLineaBase > 0 ? new Date(entFinLineaBase) : null;
        this.entProgreso = entProgreso;
        this.entEsfuerzo = entEsfuerzo;
        this.tieneProd = prodIds != null;
    }
    
    public MisTareasTO(Integer progPk, String progNombre, Integer proyPk, String proyNombre,
            Integer entPk, String entNombre, Long entInicio, Long entFin, Integer entDuracion,
            Boolean entParent, Long entInicioLineaBase, Integer entDuracionLineaBase, Long entFinLineaBase,
            Integer entProgreso, Integer entEsfuerzo, Integer prodIds, Integer entId) {
        this.progPk = progPk;
        this.progNombre = progNombre;
        this.proyPk = proyPk;
        this.proyNombre = proyNombre;
        this.entPk = entPk;
        this.entNombre = entNombre;
        this.entInicio = entInicio != null && entInicio > 0 ? new Date(entInicio) : null;
        this.entFin = entFin != null && entFin > 0 ? new Date(entFin) : null;
        this.entDuracion = entDuracion;
        this.entParent = entParent;
        this.entInicioLineaBaseDate = entInicioLineaBase != null && entInicioLineaBase > 0 ? new Date(entInicioLineaBase) : null;
        this.entDuracionLineaBase = entDuracionLineaBase;
        this.entFinLineaBaseDate = entFinLineaBase != null && entFinLineaBase > 0 ? new Date(entFinLineaBase) : null;
        this.entProgreso = entProgreso;
        this.entEsfuerzo = entEsfuerzo;
        this.tieneProd = prodIds != null;
        this.entId = entId;
    }
    

    public MisTareasTO(Integer progPk, String progNombre, Integer proyPk, String proyNombre,
            Integer entPk, String entNombre, Date entInicio, Date entFin, Integer entDuracion,
            Boolean entParent, Date entInicioLineaBase, Integer entDuracionLineaBase, Date entFinLineaBase) {
        this.progPk = progPk;
        this.progNombre = progNombre;
        this.proyPk = proyPk;
        this.proyNombre = proyNombre;
        this.entPk = entPk;
        this.entNombre = entNombre;
        this.entInicio = entInicio;
        this.entFin = entFin;
        this.entDuracion = entDuracion;
        this.entParent = entParent;
        this.entInicioLineaBaseDate = entInicioLineaBase;
        this.entDuracionLineaBase = entDuracionLineaBase;
        this.entFinLineaBaseDate = entFinLineaBase;
    }

    public MisTareasTO(Integer progPk, String progNombre, Integer proyPk,
            String proyNombre, Integer entPk, String entNombre, Long entInicio,
            Long entFin, Integer entDuracion, Boolean entParent,
            Long entInicioLineaBase, Integer entDuracionLineaBase,
            Long entFinLineaBase, Integer entProgreso, Integer entEsfuerzo,
            Integer prodIds, Integer entId, Estados estado) {
        this.progPk = progPk;
        this.progNombre = progNombre;
        this.proyPk = proyPk;
        this.proyNombre = proyNombre;
        this.entPk = entPk;
        this.entNombre = entNombre;
        this.entInicio = entInicio != null && entInicio > 0 ? new Date(entInicio) : null;
        this.entFin = entFin != null && entFin > 0 ? new Date(entFin) : null;
        this.entDuracion = entDuracion;
        this.entParent = entParent;
        this.entProgreso = entProgreso;
        this.entEsfuerzo = entEsfuerzo;
        this.entInicioLineaBaseDate = entInicioLineaBase != null && entInicioLineaBase > 0 ? new Date(entInicioLineaBase) : null;
        this.entDuracionLineaBase = entDuracionLineaBase;
        this.entFinLineaBaseDate = entFinLineaBase != null && entFinLineaBase > 0 ? new Date(entFinLineaBase) : null;
        this.tieneProd = prodIds != null;
        this.entId = entId;
        this.proyEstado = estado;
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

    /**
     * 
     * @return 
     */
    public Date getEntInicio() {
        return entInicio;
    }

    public void setEntInicio(Date entInicio) {
        this.entInicio = entInicio;
    }

    /**
     * 
     * @return 
     */
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

    public Boolean getEntParent() {
        return entParent;
    }

    public void setEntParent(Boolean entParent) {
        this.entParent = entParent;
    }

    public Integer getEntProgreso() {
        return entProgreso;
    }

    public void setEntProgreso(Integer entProgreso) {
        this.entProgreso = entProgreso;
    }

    public Date getEntInicioLineaBaseDate() {
        return entInicioLineaBaseDate;
    }

    public void setEntInicioLineaBaseDate(Date entInicioLineaBaseDate) {
        this.entInicioLineaBaseDate = entInicioLineaBaseDate;
    }

    public Integer getEntDuracionLineaBase() {
        return entDuracionLineaBase;
    }

    public void setEntDuracionLineaBase(Integer entDuracionLineaBase) {
        this.entDuracionLineaBase = entDuracionLineaBase;
    }

    public Date getEntFinLineaBaseDate() {
        return entFinLineaBaseDate;
    }

    public void setEntFinLineaBaseDate(Date entFinLineaBaseDate) {
        this.entFinLineaBaseDate = entFinLineaBaseDate;
    }

    public Boolean getTieneProd() {
        return tieneProd;
    }

    public void setTieneProd(Boolean tieneProd) {
        this.tieneProd = tieneProd;
    }

    public Integer getEntEsfuerzo() {
        return entEsfuerzo;
    }

    public void setEntEsfuerzo(Integer entEsfuerzo) {
        this.entEsfuerzo = entEsfuerzo;
    }

    public Double getEntEsfuerzoPorcentaje() {
        return entEsfuerzoPorcentaje;
    }

    public void setEntEsfuerzoPorcentaje(Double entEsfuerzoPorcentaje) {
        this.entEsfuerzoPorcentaje = entEsfuerzoPorcentaje;
    }

    /**
     * @return the entId
     */
    public Integer getEntId() {
        return entId;
    }

    /**
     * @param entId the entId to set
     */
    public void setEntId(Integer entId) {
        this.entId = entId;
    }

    /**
     * 
     * @return 
     */
    public List<Productos> getProductos() {
        return productos;
    }

    /**
     * 
     * @param productos 
     */
    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

    /**
     * @return the ent
     */
    public Entregables getEnt() {
        return ent;
    }

    /**
     * @param ent the ent to set
     */
    public void setEnt(Entregables ent) {
        this.ent = ent;
    }

    /**
     * @return the proyEstado
     */
    public Estados getProyEstado() {
        return proyEstado;
    }

    /**
     * @param proyEstado the proyEstado to set
     */
    public void setProyEstado(Estados proyEstado) {
        this.proyEstado = proyEstado;
    }
}
