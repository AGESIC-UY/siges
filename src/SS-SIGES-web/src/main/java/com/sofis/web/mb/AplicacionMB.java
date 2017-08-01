package com.sofis.web.mb;

import com.sofis.entities.codigueras.TiposMediaCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Departamento;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Pais;
import com.sofis.entities.data.Paridad;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoDocumentoPersona;
import com.sofis.entities.data.TipoEntradaColectiva;
import com.sofis.entities.data.TipoTelefono;
import com.sofis.entities.data.TipoVialidad;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.NomenclatorDelegate;
import com.sofis.web.delegates.OrganiIntProveDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.delegates.TipoDocumentoPersonaDelegate;
import com.sofis.web.delegates.TipoTelefonoDelegate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "aplicacionMB")
@ApplicationScoped
public class AplicacionMB implements Serializable {

    private final TiposMediaCodigos tiposMediaCod = new TiposMediaCodigos();

    @Inject
    private ConfiguracionDelegate confDelegate;
    @Inject
    private NomenclatorDelegate nomBean;
    @Inject
    private TipoTelefonoDelegate tipTelDelegate;
    @Inject
    private TipoDocumentoPersonaDelegate tdpDelegate;
    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;
    @Inject
    private AreasDelegate areasDelegate;
    @Inject
    private OrganiIntProveDelegate organiIntProveDelegate;

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
    private ConcurrentHashMap<String, Configuracion> configuracion = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, List<SsUsuario>> usuariosPorOrganismo = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, List<SsUsuario>> usuariosPorOrganismoActivos = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, List<SsUsuario>> usuariosPorRolOrganismoActivos = new ConcurrentHashMap<>();

    private ConcurrentHashMap<Integer, List<Areas>> areasPorOrganismo = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, List<OrganiIntProve>> organiIntProveedores = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, List<OrganiIntProve>> organiIntOrganizaciones = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, List<OrganiIntProve>> organiIntNoProveedores = new ConcurrentHashMap<>();

    public static final Object usuariosMUTEX = new Object();
    public static final Object areasPorOrganismoMUTEX = new Object();
    public static final Object organiIntMUTEX = new Object();

    /**
     * Creates a new instance of AplicacionMB
     */
    public AplicacionMB() {
    }

    @PostConstruct
    public void init() {
    }

    public String getAppVersion() {
        return ConstanteApp.APP_VERSION;
    }

    public String getAppBuild() {
        return ConstanteApp.APP_BUILD;
    }

    private void cargarConfiguracion() {
        try {
            List<Configuracion> listaConf = confDelegate.obtenerTodos();
            configuracion = new ConcurrentHashMap<>();
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

    public TiposMediaCodigos getTiposMediaCod() {
        return tiposMediaCod;
    }

    public List<SsUsuario> obtenerTodosPorOrganismoActivos(Integer orgPk) {
        synchronized (usuariosMUTEX) {
            if (!usuariosPorOrganismoActivos.containsKey(orgPk)) {
                usuariosPorOrganismoActivos.put(orgPk, ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk));
            }
            return usuariosPorOrganismoActivos.get(orgPk);
        }
    }

    public List<SsUsuario> obtenerTodosPorOrganismo(Integer orgPk) {
        synchronized (usuariosMUTEX) {
            if (!usuariosPorOrganismo.containsKey(orgPk)) {
                usuariosPorOrganismo.put(orgPk, ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk, null));
            }
            return usuariosPorOrganismo.get(orgPk);
        }
    }

    public List<SsUsuario> obtenerUsuariosPorRolActivos(String rol, Integer orgPk) {
        synchronized (usuariosMUTEX) {
            String key = rol + orgPk;
            if (!usuariosPorRolOrganismoActivos.containsKey(key)) {
                usuariosPorRolOrganismoActivos.put(key, ssUsuarioDelegate.obtenerUsuariosPorRol(rol, orgPk, new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"}, new boolean[]{true, true, true, true}));
            }
            return usuariosPorRolOrganismoActivos.get(key);
        }
    }

    public List<SsUsuario> obtenerUsuariosPorRolActivos(String[] rol, Integer orgPk) {
        synchronized (usuariosMUTEX) {
            String key = "";
            for (String r : rol) {
                key = key + r;
            }
            key = key + orgPk;
            if (!usuariosPorRolOrganismoActivos.containsKey(key)) {
                usuariosPorRolOrganismoActivos.put(key, ssUsuarioDelegate.obtenerUsuariosPorRol(rol, orgPk, new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"}, new boolean[]{true, true, true, true}));
            }
            return usuariosPorRolOrganismoActivos.get(key);
        }
    }

    public void cargarUsuariosPorOrganismo(Integer orgPk) {
        synchronized (usuariosMUTEX) {
            usuariosPorOrganismo.put(orgPk, ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk, null));
            usuariosPorOrganismoActivos.put(orgPk, ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk));
            //pudo cambiar el rol de un usuario tengo que limpiar esta coleccion.
            usuariosPorRolOrganismoActivos.clear();
        }
    }

    public void cargarAreasPorOrganismo(Integer orgPk) {
        synchronized (areasPorOrganismoMUTEX) {
            areasPorOrganismo.put(orgPk, areasDelegate.obtenerAreasPorOrganismo(orgPk, false));
        }
    }

    //TODO: Corregir para que no se pierdan areas.
    public List<Areas> obtenerAreasPorOrganismo(Integer orgPk) {
        synchronized (areasPorOrganismoMUTEX) {
            if (!areasPorOrganismo.containsKey(orgPk)) {
                cargarAreasPorOrganismo(orgPk);
            }
            return areasPorOrganismo.get(orgPk);
        }
    }

    List<OrganiIntProve> obtenerOrganiIntProveedores(Integer orgPk) {
        synchronized (organiIntMUTEX) {
            if (!organiIntProveedores.containsKey(orgPk)) {
                organiIntProveedores.put(orgPk, organiIntProveDelegate.obtenerOrganiIntProvePorOrgPk(orgPk, true));
            }
            return organiIntProveedores.get(orgPk);
        }
    }

    List<OrganiIntProve> obtenerOrganiIntOrganizaciones(Integer orgPk) {
        synchronized (organiIntMUTEX) {
            if (!organiIntOrganizaciones.containsKey(orgPk)) {
                organiIntOrganizaciones.put(orgPk, organiIntProveDelegate.obtenerOrganiIntProvePorOrgPk(orgPk, null));
            }
            return organiIntOrganizaciones.get(orgPk);
        }
    }

    List<OrganiIntProve> obtenerOrganiIntNoProveedores(Integer orgPk) {
        synchronized (organiIntMUTEX) {
            if (!organiIntNoProveedores.containsKey(orgPk)) {
                organiIntNoProveedores.put(orgPk, organiIntProveDelegate.obtenerOrganiIntProvePorOrgPk(orgPk, false));
            }
            return organiIntNoProveedores.get(orgPk);
        }
    }

    public void cargarOrganiIntPorOrganismo(Integer orgPk) {
        synchronized (organiIntMUTEX) {
            organiIntProveedores.put(orgPk, organiIntProveDelegate.obtenerOrganiIntProvePorOrgPk(orgPk, true));
            organiIntOrganizaciones.put(orgPk, organiIntProveDelegate.obtenerOrganiIntProvePorOrgPk(orgPk, null));
            organiIntNoProveedores.put(orgPk, organiIntProveDelegate.obtenerOrganiIntProvePorOrgPk(orgPk, false));
        }
    }
}
