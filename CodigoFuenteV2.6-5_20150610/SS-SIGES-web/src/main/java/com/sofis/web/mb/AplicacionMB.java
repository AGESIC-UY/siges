package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Departamento;
import com.sofis.entities.data.Pais;
import com.sofis.entities.data.Paridad;
import com.sofis.entities.data.TipoDocumentoPersona;
import com.sofis.entities.data.TipoEntradaColectiva;
import com.sofis.entities.data.TipoTelefono;
import com.sofis.entities.data.TipoVialidad;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.NomenclatorDelegate;
import com.sofis.web.delegates.OrganismoDelegate;
import com.sofis.web.delegates.TipoDocumentoPersonaDelegate;
import com.sofis.web.delegates.TipoTelefonoDelegate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named(value = "aplicacionMB")
@ApplicationScoped
public class AplicacionMB {

    public static final String appVersion = "2.6";
    public static final String appBuild = "5";

    @Inject
    private ConfiguracionDelegate confDelegate;
    @Inject
    private NomenclatorDelegate nomBean;
    @Inject
    private TipoTelefonoDelegate tipTelDelegate;
    @Inject
    private TipoDocumentoPersonaDelegate tdpDelegate;
    @Inject
    private OrganismoDelegate organismoDelegate;

    //Variables
    private String timeZone = ConstantesEstandares.CALENDAR_TIME_ZONE;
    private String locale = "es";
    private String patronFechas = ConstantesEstandares.CALENDAR_PATTERN;
    private String filasHistorial = "5";
    private Long ultModNJ = 0L;
    private Long ultModCiiu = 0L;
    private Long ultModCargo = 0L;
    private Long ultModConf = 0L;
    private Long ultModDepto = 0L;
    private Long ultModTipoTel = 0L;
    private Long ultModTipoAct = 0L;
    private Long ultModTipoEst = 0L;
    private Long ultModEstSol = 0L;
    private Long ultModTipoDocumento = 0L;
    private Long ultModPais = 0L;
    private Long ultModRolEmpresa = 0L;
    private Long ultModProducto = 0L;
    private Long ultModNcm = 0L;
    private long maxPeriodoActualizacion = 300000;
    private List<TipoDocumentoPersona> listaTipoDocumentoPersona = new ArrayList<>();
    private List<Paridad> listaParidades = new ArrayList<>();
    private List<Departamento> listaDepartamento = new ArrayList<>();
    private List<TipoVialidad> listaTipoVialidad = new ArrayList<>();
    private List<TipoEntradaColectiva> listaTipoEntidadColectiva = new ArrayList<>();
    private List<TipoTelefono> listaTipoTelefono = new ArrayList<>();
    private List<Pais> listaPaises = new ArrayList<>();
    private HashMap<String, Configuracion> configuracion = new HashMap<>();

    /**
     * Creates a new instance of AplicacionMB
     */
    public AplicacionMB() {
    }

    @PostConstruct
    public void init() {
        organismoDelegate.controlarDatosFaltantes();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getAppBuild() {
        return appBuild;
    }

    private void cargarConfiguracion() {
        try {
            List<Configuracion> listaConf = confDelegate.obtenerTodos();
            configuracion = new HashMap<>();
            for (Configuracion c : listaConf) {
                configuracion.put(c.getCnfCodigo(), c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cargarDepartamentos() {
        try {
            listaDepartamento = nomBean.obtenerDepartamentos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cargarListaTiposVialidad() {
        try {
            listaTipoVialidad = nomBean.obtenerTiposVialidad();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cargarListaTipoEntradaColectiva() {
        try {
            listaTipoEntidadColectiva = nomBean.obtenerTipoEntradaColectiva();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void cargarTipoTelefono() {
        try {
            listaTipoTelefono = tipTelDelegate.obtenerHabilitados();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cargarPaises() {
        try {
            listaPaises = nomBean.obtenerPaises();
        } catch (Exception e) {
        }
    }

    public void cargarParidades() {
        try {
            listaParidades = nomBean.obtenerParidades();
        } catch (Exception e) {
        }
    }

    public void cargarNomenclator() {
        cargarDepartamentos();
        cargarListaTipoEntradaColectiva();
        cargarListaTiposVialidad();
        cargarPaises();
        cargarParidades();
    }

    public void cargarTiposDocumentoPersona() {
        try {
            listaTipoDocumentoPersona = tdpDelegate.obtenerHabilitados();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Configuracion obtenerConfiguracionPorCodigo(String codigo) {
        if (GregorianCalendar.getInstance().getTimeInMillis() - ultModConf > maxPeriodoActualizacion) {
            cargarConfiguracion();
        }
        return configuracion.get(codigo);
    }

    public List<Pais> getListaPaises() {
        if (GregorianCalendar.getInstance().getTimeInMillis() - ultModPais > maxPeriodoActualizacion) {
            cargarNomenclator();
            ultModPais = GregorianCalendar.getInstance().getTimeInMillis();
        }
        return listaPaises;
    }

    public List<Departamento> getListaDepartamento() {
        if (GregorianCalendar.getInstance().getTimeInMillis() - ultModDepto > maxPeriodoActualizacion) {
            cargarNomenclator();
            ultModDepto = GregorianCalendar.getInstance().getTimeInMillis();
        }
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<TipoVialidad> getListaTipoVialidad() {
        if (GregorianCalendar.getInstance().getTimeInMillis() - ultModDepto > maxPeriodoActualizacion) {
            cargarNomenclator();
            ultModDepto = GregorianCalendar.getInstance().getTimeInMillis();
        }
        return listaTipoVialidad;
    }

    public void setListaTipoVialidad(List<TipoVialidad> listaTipoVialidad) {
        this.listaTipoVialidad = listaTipoVialidad;
    }

    public List<TipoEntradaColectiva> getListaTipoEntidadColectiva() {
        if (GregorianCalendar.getInstance().getTimeInMillis() - ultModDepto > maxPeriodoActualizacion) {
            cargarNomenclator();
            ultModDepto = GregorianCalendar.getInstance().getTimeInMillis();
        }
        return listaTipoEntidadColectiva;
    }

    public void setListaTipoEntidadColectiva(List<TipoEntradaColectiva> listaTipoEntidadColectiva) {
        this.listaTipoEntidadColectiva = listaTipoEntidadColectiva;
    }

    public List<TipoTelefono> getListaTipoTelefono() {
        if (GregorianCalendar.getInstance().getTimeInMillis() - ultModTipoTel > maxPeriodoActualizacion) {
            cargarTipoTelefono();
            ultModTipoTel = GregorianCalendar.getInstance().getTimeInMillis();
        }
        return listaTipoTelefono;
    }

    public void setListaTipoTelefono(List<TipoTelefono> listaTipoTelefono) {
        this.listaTipoTelefono = listaTipoTelefono;
    }

    public List<TipoDocumentoPersona> getListaTipoDocumentoPersona() {
        if (GregorianCalendar.getInstance().getTimeInMillis() - ultModTipoDocumento > maxPeriodoActualizacion) {
            cargarTiposDocumentoPersona();
            ultModTipoDocumento = GregorianCalendar.getInstance().getTimeInMillis();
        }
        return listaTipoDocumentoPersona;
    }

    public void setListaTipoDocumentoPersona(List<TipoDocumentoPersona> listaTipoDocumentoPersona) {
        this.listaTipoDocumentoPersona = listaTipoDocumentoPersona;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getPatronFechas() {
        return patronFechas;
    }

    public void setPatronFechas(String patronFechas) {
        this.patronFechas = patronFechas;
    }

    public String getFilasHistorial() {
        return filasHistorial;
    }

    public void setFilasHistorial(String filasHistorial) {
        this.filasHistorial = filasHistorial;
    }
}
