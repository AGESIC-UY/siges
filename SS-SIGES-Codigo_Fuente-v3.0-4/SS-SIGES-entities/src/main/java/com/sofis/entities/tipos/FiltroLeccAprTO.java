package com.sofis.entities.tipos;

import com.sofis.entities.data.AreaConocimiento;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.TipoLeccion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Filtro de Lecciones Aprendidas.
 * @author Usuario
 */
@XmlRootElement
public class FiltroLeccAprTO {
    
    private String texto;
    private TipoLeccion tipo;
    private Integer anio;
    private Integer proyPk;
    private String proyNombre;
    private Integer progPk;
    private String progNombre;
    private OrganiIntProve interesado;
    private OrganiIntProve proveedor;
    private List<AreasTags> areasTematicas;
    private List<AreaConocimiento> areasConocimiento;

    public FiltroLeccAprTO() {
        areasTematicas = new ArrayList<AreasTags>();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public TipoLeccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoLeccion tipo) {
        this.tipo = tipo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getProyNombre() {
        return proyNombre;
    }

    public void setProyNombre(String proyNombre) {
        this.proyNombre = proyNombre;
    }

    public OrganiIntProve getInteresado() {
        return interesado;
    }

    public void setInteresado(OrganiIntProve interesado) {
        this.interesado = interesado;
    }

    public OrganiIntProve getProveedor() {
        return proveedor;
    }

    public void setProveedor(OrganiIntProve proveedor) {
        this.proveedor = proveedor;
    }

    public List<AreasTags> getAreasTematicas() {
        return areasTematicas;
    }

    public void setAreasTematicas(List<AreasTags> areasTematicas) {
        this.areasTematicas = areasTematicas;
    }

    public List<AreaConocimiento> getAreasConocimiento() {
        return areasConocimiento;
    }

    public void setAreasConocimiento(List<AreaConocimiento> areasConocimiento) {
        this.areasConocimiento = areasConocimiento;
    }

    public Integer getProyPk() {
        return proyPk;
    }

    public void setProyPk(Integer proyPk) {
        this.proyPk = proyPk;
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
 
    public Date getAnioDesde() {
        if (anio != null && anio > 0) {
            Calendar c = new GregorianCalendar(anio, 0, 1);
            return c.getTime();
        }
        return null;
    }

    public Date getAnioHasta() {
        if (anio != null && anio > 0) {
            Calendar c = new GregorianCalendar(anio, 11, 31);
            return c.getTime();
        }
        return null;
    }
    
}
