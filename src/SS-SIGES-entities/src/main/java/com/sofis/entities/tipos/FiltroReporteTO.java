package com.sofis.entities.tipos;

import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.SsUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@XmlRootElement
public class FiltroReporteTO implements Serializable {

    private static final long serialVersionUID = 1L;

    //1-Moneda, 2-Adquisición
    private Integer tipoReporte;
    private String nombre;
    private List<Object> estados;
    private List<Object> concepto;
    private String procedimiento;
    private String procedimientoGRP;
    private Areas area;
    private SsUsuario gerenteAdjunto;
    private SsUsuario sponsor;
    private SsUsuario pmof;
    private Integer anio;
    private Integer anioCronograma;
    private Integer anioBaseCronograma;
    private Integer anioPagos;
    private Integer anioDevengado;
    private Integer anioHoras;
    private Integer anioGastos;
    private List<AreasTags> areasTematicas;
    private OrganiIntProve proveedor;
    private FuenteFinanciamiento fuenteFinanc;
    private Moneda moneda;
    private Boolean acumulado;
    

    public FiltroReporteTO() {
        concepto = new ArrayList<>();
        concepto.add(1);

        estados = new ArrayList<>();
        estados.add(Estados.ESTADOS.INICIO.estado_id);
        estados.add(Estados.ESTADOS.PLANIFICACION.estado_id);
        estados.add(Estados.ESTADOS.EJECUCION.estado_id);

        tipoReporte = 1;
        
        acumulado = true;
    }

    public Integer getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(Integer tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getNombre() {
        return nombre != null ? nombre.trim() : nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Object> getEstados() {
        return estados;
    }

    public void setEstados(List<Object> estados) {
        this.estados = estados;
    }

    public List<Object> getConcepto() {
        return concepto;
    }

    public void setConcepto(List<Object> concepto) {
        this.concepto = concepto;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getProcedimientoGRP() {
        return procedimientoGRP;
    }

    public void setProcedimientoGRP(String procedimientoGRP) {
        this.procedimientoGRP = procedimientoGRP;
    }

    public Areas getArea() {
        return area;
    }

    public void setArea(Areas area) {
        this.area = area;
    }

    public SsUsuario getGerenteAdjunto() {
        return gerenteAdjunto;
    }

    public void setGerenteAdjunto(SsUsuario gerenteAdjunto) {
        this.gerenteAdjunto = gerenteAdjunto;
    }

    public SsUsuario getSponsor() {
        return sponsor;
    }

    public void setSponsor(SsUsuario sponsor) {
        this.sponsor = sponsor;
    }

    public SsUsuario getPmof() {
        return pmof;
    }

    public void setPmof(SsUsuario pmof) {
        this.pmof = pmof;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getAnioCronograma() {
        return anioCronograma;
    }

    public void setAnioCronograma(Integer anioCronograma) {
        this.anioCronograma = anioCronograma;
    }

    public Integer getAnioBaseCronograma() {
	return anioBaseCronograma;
    }

    public void setAnioBaseCronograma(Integer anioBaseCronograma) {
	this.anioBaseCronograma = anioBaseCronograma;
    }

    public Integer getAnioPagos() {
	return anioPagos;
    }

    public void setAnioPagos(Integer anioPagos) {
	this.anioPagos = anioPagos;
    }

    public Integer getAnioDevengado() {
	return anioDevengado;
    }

    public void setAnioDevengado(Integer anioDevengado) {
	this.anioDevengado = anioDevengado;
    }

    public Integer getAnioHoras() {
	return anioHoras;
    }

    public void setAnioHoras(Integer anioHoras) {
	this.anioHoras = anioHoras;
    }

    public Integer getAnioGastos() {
	return anioGastos;
    }

    public void setAnioGastos(Integer anioGastos) {
	this.anioGastos = anioGastos;
    }

    public List<AreasTags> getAreasTematicas() {
        return areasTematicas;
    }

    public void setAreasTematicas(List<AreasTags> areasTematicas) {
        this.areasTematicas = areasTematicas;
    }

    public OrganiIntProve getProveedor() {
        return proveedor;
    }

    public void setProveedor(OrganiIntProve proveedor) {
        this.proveedor = proveedor;
    }

    public FuenteFinanciamiento getFuenteFinanc() {
        return fuenteFinanc;
    }

    public void setFuenteFinanc(FuenteFinanciamiento fuenteFinanc) {
        this.fuenteFinanc = fuenteFinanc;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Boolean getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(Boolean acumulado) {
        this.acumulado = acumulado;
    }

    /**
     * 1-Pagos, 2-Devengado, 3-Horas, 4-Gastos
     * @param id
     * @return 
     */
    public boolean isConcepto(int id) {
        if (concepto != null && !concepto.isEmpty()) {
            for (Object c : concepto) {
                Integer val = Integer.valueOf((String) c);
                if (val.equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }
}
