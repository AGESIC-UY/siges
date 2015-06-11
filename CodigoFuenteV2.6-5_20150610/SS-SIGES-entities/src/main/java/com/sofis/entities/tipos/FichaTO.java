package com.sofis.entities.tipos;

import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Participantes;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.ProgIndices;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.ProgramasProyectos;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Riesgos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoDocumentoInstancia;
import com.sofis.entities.enums.TipoFichaEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Usuario
 */
public class FichaTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private FichaTO fichaOriginal;
    private Integer fichaFk;
    private String proyProgId;
    private Integer proyProgVersion;
    private Integer tipoFicha;
    private Boolean activo;
    private Date ultimaModificacion;
    private Date fechaCrea;
    private Date fechaAct;
    private String fechaActColor;
    private String nombre;
    private Integer progPk;
    private String progNombre;
    private String objetivo;
    private String objPublico;
    private String situacionActual;
    private String grp;
    private Integer semaforoRojo;
    private Integer semaforoAmarillo;
    private Set<AreasTags> areasTematicas;
    private Set<Areas> areasRestringidas;
    private List<Interesados> interesados;
    private List<Documentos> documentos;
    private List<Riesgos> riesgos;
    private Set<Proyectos> proyectosSet;
    private SsUsuario usrPmofedFk;
    private SsUsuario usrSponsorFk;
    private SsUsuario usrAdjuntoFk;
    private SsUsuario usrGerenteFk;
    private Estados estado;
    private Estados estadoPendiente;
    private Organismos orgFk;
    private Areas areaFk;
    private Programas progFk;
    private Integer peso;

    private String fechaUltimaSitAct;
    private String leccionAprendida;
    private Cronogramas croFk;
    private Presupuesto preFk;
    private List<TipoDocumentoInstancia> tipoDocumentoInstancias;
    private String tipoSolicitud;
    private ProgIndices progIndices;
    private ProyIndices proyIndices;
    private Documentos resumenEjecutivo;
    private List<Participantes> participantes;

    public FichaTO() {
        super();
    }

    public FichaTO(Integer fk) {
        this.fichaFk = fk;
    }

    public FichaTO(ProgramasProyectos p) {
        this.proyProgId = p.getId();
        this.fichaFk = p.getFichaFk();
        this.tipoFicha = (p.getTipoFicha()).intValue();
        this.fechaCrea = p.getFechaCrea();
        this.nombre = p.getNombre();
        this.estado = new Estados(p.getEstado());
        this.estado.setEstNombre(p.getEstadoNombre());
        this.areaFk = new Areas(p.getAreaPk());
        this.areaFk.setAreaNombre(p.getAreaNombre());
        this.usrGerenteFk = new SsUsuario(p.getGerente());
        this.usrGerenteFk.setUsuPrimerApellido(p.getGerentePrimerApellido());
        this.usrGerenteFk.setUsuPrimerNombre(p.getGerentePrimerNombre());
        this.activo = p.getActivo();

        if (p.getEstadoPendiente() != null) {
            this.estadoPendiente = new Estados(p.getEstadoPendiente());
        }
    }

    public FichaTO getFichaOriginal() {
        return fichaOriginal;
    }

    public void setFichaOriginal(FichaTO fichaOriginal) {
        this.fichaOriginal = fichaOriginal;
    }

    public Integer getTipoFicha() {
        return tipoFicha;
    }

    public void setTipoFicha(Integer tipoFicha) {
        this.tipoFicha = tipoFicha;
    }

    public Boolean getActivo() {
        return activo;
    }

    public boolean isActivo() {
        return activo != null ? activo : false;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public Date getFechaAct() {
        return fechaAct;
    }

    public void setFechaAct(Date fechaAct) {
        this.fechaAct = fechaAct;
    }

    public String getFechaActColor() {
        return fechaActColor;
    }

    public void setFechaActColor(String fechaActColor) {
        this.fechaActColor = fechaActColor;
    }

    public String getTipoFichaStr() {
        if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
            return "tipo_ficha_progamas";
        } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
            return "tipo_ficha_proyectos";
        }

        return "";
    }

    public Integer getFichaFk() {
        return fichaFk;
    }

    public void setFichaFk(Integer fichaFk) {
        this.fichaFk = fichaFk;
    }

    public String getProyProgId() {
        return proyProgId;
    }

    public void setProyProgId(String proyProgId) {
        this.proyProgId = proyProgId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFichaPkNombre() {
        return fichaFk + " - " + nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getObjPublico() {
        return objPublico;
    }

    public void setObjPublico(String objPublico) {
        this.objPublico = objPublico;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }

    public Integer getSemaforoRojo() {
        return semaforoRojo;
    }

    public void setSemaforoRojo(Integer semaforoRojo) {
        this.semaforoRojo = semaforoRojo;
    }

    public Integer getSemaforoAmarillo() {
        return semaforoAmarillo;
    }

    public void setSemaforoAmarillo(Integer semaforoAmarillo) {
        this.semaforoAmarillo = semaforoAmarillo;
    }

    public Set<AreasTags> getAreasTematicas() {
        return areasTematicas;
    }

    public void setAreasTematicas(Set<AreasTags> areasTematicas) {
        this.areasTematicas = areasTematicas;
    }

    public Set<Areas> getAreasRestringidas() {
        return areasRestringidas;
    }

    public void setAreasRestringidas(Set<Areas> areasRestringidas) {
        this.areasRestringidas = areasRestringidas;
    }

    public List<Interesados> getInteresados() {
        return interesados;
    }

    public void setInteresados(List<Interesados> interesados) {
        this.interesados = interesados;
    }

    public List<Documentos> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documentos> documentos) {
        this.documentos = documentos;
    }

    public List<Riesgos> getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(List<Riesgos> riesgos) {
        this.riesgos = riesgos;
    }

    public Set<Proyectos> getProyectosSet() {
        return proyectosSet;
    }

    public void setProyectosSet(Set<Proyectos> proyectosSet) {
        this.proyectosSet = proyectosSet;
    }

    public SsUsuario getUsrPmofedFk() {
        return usrPmofedFk;
    }

    public void setUsrPmofedFk(SsUsuario usrPmofedFk) {
        this.usrPmofedFk = usrPmofedFk;
    }

    public SsUsuario getUsrSponsorFk() {
        return usrSponsorFk;
    }

    public void setUsrSponsorFk(SsUsuario usrSponsorFk) {
        this.usrSponsorFk = usrSponsorFk;
    }

    public SsUsuario getUsrAdjuntoFk() {
        return usrAdjuntoFk;
    }

    public void setUsrAdjuntoFk(SsUsuario usrAdjuntoFk) {
        this.usrAdjuntoFk = usrAdjuntoFk;
    }

    public SsUsuario getUsrGerenteFk() {
        return usrGerenteFk;
    }

    public void setUsrGerenteFk(SsUsuario usrGerenteFk) {
        this.usrGerenteFk = usrGerenteFk;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Estados getEstadoPendiente() {
        return estadoPendiente;
    }

    public void setEstadoPendiente(Estados estadoPendiente) {
        this.estadoPendiente = estadoPendiente;
    }

    public boolean isEstadoPendiente(Integer estadoPk) {
        return estadoPendiente != null && estadoPk != null ? estadoPendiente.isEstado(estadoPk) : false;
    }

    public Organismos getOrgFk() {
        return orgFk;
    }

    public void setOrgFk(Organismos orgFk) {
        this.orgFk = orgFk;
    }

    public Areas getAreaFk() {
        return areaFk;
    }

    public void setAreaFk(Areas areaFk) {
        this.areaFk = areaFk;
    }

    public Programas getProgFk() {
        return progFk;
    }

    public void setProgFk(Programas progFk) {
        this.progFk = progFk;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getSituacionActual() {
        return situacionActual;
    }

    public void setSituacionActual(String situacionActual) {
        this.situacionActual = situacionActual;
    }

    public String getFechaUltimaSitAct() {
        return fechaUltimaSitAct;
    }

    public void setFechaUltimaSitAct(String fechaUltimaSitAct) {
        this.fechaUltimaSitAct = fechaUltimaSitAct;
    }

    public String getLeccionAprendida() {
        return leccionAprendida;
    }

    public void setLeccionAprendida(String leccionAprendida) {
        this.leccionAprendida = leccionAprendida;
    }

    public Cronogramas getCroFk() {
        return croFk;
    }

    public Presupuesto getPreFk() {
        return preFk;
    }

    public void setPreFk(Presupuesto preFk) {
        this.preFk = preFk;
    }

    public void setCroFk(Cronogramas croFk) {
        this.croFk = croFk;
    }

    public List<TipoDocumentoInstancia> getTipoDocumentoInstancias() {
        return tipoDocumentoInstancias;
    }

    public void setTipoDocumentoInstancias(List<TipoDocumentoInstancia> tipoDocumentoInstancias) {
        this.tipoDocumentoInstancias = tipoDocumentoInstancias;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public boolean isEstado(Integer estadoPk) {
        return this.estado != null ? this.estado.isEstado(estadoPk) : false;
    }

    public boolean isPrograma() {
        return this.tipoFicha.equals(TipoFichaEnum.PROGRAMA.id);
    }

    public boolean isProyecto() {
        return this.tipoFicha.equals(TipoFichaEnum.PROYECTO.id);
    }

    public void cambioEstado(Estados estado) {
        this.setEstado(estado);
        this.setEstadoPendiente(null);
//        this.setSolAprobacion(null);
    }

    public boolean hasPk() {
        return this.fichaFk != null && this.fichaFk > 0;
    }

    public ProgIndices getProgIndices() {
        return progIndices;
    }

    public void setProgIndices(ProgIndices progIndices) {
        this.progIndices = progIndices;
    }

    public ProyIndices getProyIndices() {
        return proyIndices;
    }

    public void setProyIndices(ProyIndices proyIndices) {
        this.proyIndices = proyIndices;
    }

    public Documentos getResumenEjecutivo() {
        return resumenEjecutivo;
    }

    public void setResumenEjecutivo(Documentos resumenEjecutivo) {
        this.resumenEjecutivo = resumenEjecutivo;
    }

    public Integer getProyProgVersion() {
        return proyProgVersion;
    }

    public void setProyProgVersion(Integer proyProgVersion) {
        this.proyProgVersion = proyProgVersion;
    }

    public String getFichaFkTipo() {
        return this.tipoFicha + "-" + this.fichaFk;
    }

    public List<Participantes> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participantes> participantes) {
        this.participantes = participantes;
    }
}
