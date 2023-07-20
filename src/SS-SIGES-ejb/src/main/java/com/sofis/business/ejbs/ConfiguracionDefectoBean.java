package com.sofis.business.ejbs;

import com.sofis.business.validations.ConfiguracionDefectoValidacion;
import com.sofis.data.daos.ConfiguracionDefectoDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ConfiguracionDefecto;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ConfiguracionDefectoBean {

    private static final Logger LOGGER = Logger.getLogger(ConfiguracionDefectoBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private DatosUsuario datosUsuario;

    public ConfiguracionDefecto guardar(ConfiguracionDefecto cnf) throws GeneralException {
        try {
            ConfiguracionDefectoValidacion.validar(cnf);

            cnf.setUsuarioModificacion(datosUsuario.getCodigoUsuario());

            ConfiguracionDefectoDAO cnfDao = new ConfiguracionDefectoDAO(em);

            cnf = cnfDao.update(cnf, datosUsuario.getCodigoUsuario(), datosUsuario.getOrigen());

            return cnf;
        } catch (BusinessException be) {
            //Si es de tipo negocio envía la misma excepción
            LOGGER.log(Level.SEVERE, be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            //Las demás excepciones se consideran técnicas
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }

    }

    public ConfiguracionDefecto obtenerPorId(Integer id) throws GeneralException {
        ConfiguracionDefectoDAO cnfDao = new ConfiguracionDefectoDAO(em);
        try {
            return cnfDao.findById(ConfiguracionDefecto.class, id);
        } catch (DAOGeneralException ex) {
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<ConfiguracionDefecto> obtenerTodas() throws GeneralException {
        ConfiguracionDefectoDAO cnfDao = new ConfiguracionDefectoDAO(em);
        try {
            return cnfDao.findAll(ConfiguracionDefecto.class);
        } catch (Exception ex) {
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<ConfiguracionDefecto> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
        ConfiguracionDefectoDAO cnfDao = new ConfiguracionDefectoDAO(em);
        try {
            return cnfDao.findEntityByCriteria(ConfiguracionDefecto.class, cto, orderBy, ascending, startPosition, cantidad);
        } catch (Exception ex) {
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public void controlarConfiguracionDefecto() {

        List<ConfiguracionDefecto> configuracionesActuales = obtenerTodas();
        Map<String, ConfiguracionDefecto> configuracionesActualesMap = new HashMap<>();

        for (ConfiguracionDefecto conf : configuracionesActuales) {
            configuracionesActualesMap.put(conf.getCodigo(), conf);
        }

        ConfiguracionDefecto[] configuracionDefecto = new ConfiguracionDefecto[]{
            new ConfiguracionDefecto(ConfiguracionCodigos.FILTRO_INICIO_POR_AREAS, "Agrupar resultado incio por areas", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_AMARILLO, "", "1.2"),
            new ConfiguracionDefecto(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO, "", "4"),
            new ConfiguracionDefecto(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO, "", "10"),
            new ConfiguracionDefecto(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO, "", "20"),
            new ConfiguracionDefecto(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO, "", "30"),
            new ConfiguracionDefecto(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO, "", "70"),
            new ConfiguracionDefecto(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_DOCUMENTO, "", "10485760"),
            new ConfiguracionDefecto(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MULTIMEDIA, "", "512000"),
            new ConfiguracionDefecto(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, "", "10"),
            new ConfiguracionDefecto(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, "", "20"),
            new ConfiguracionDefecto(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_AMARILLO, "Semaforo estado Inicio amarillo", "10"),
            new ConfiguracionDefecto(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_ROJO, "Semaforo estado Inicio rojo", "15"),
            new ConfiguracionDefecto(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_AMARILLO, "Semaforo estado Planificacion amarillo", "15"),
            new ConfiguracionDefecto(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_ROJO, "Semaforo estado Planificacion rojo", "20"),
            new ConfiguracionDefecto(ConfiguracionCodigos.TAMANIO_MAX_LOGO_ORGANISMO, "Tamaño máximo en bytes del logo del Organismo", "262144"),
            new ConfiguracionDefecto(ConfiguracionCodigos.CON_CORREO, "Si se envía correo o no", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.CON_CONTROL_ACCESO, "Si se usa el control de acceso de Agesic o no", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_AMARILLO, "Limite semaforo amarillo para Productos", "10"),
            new ConfiguracionDefecto(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_ROJO, "Limite semaforo rojo para Productos", "20"),
            new ConfiguracionDefecto(ConfiguracionCodigos.ALCANCE_INDICE_LIMITE_AMARILLO, "Limite semaforo amarillo para Alcance", "90"),
            new ConfiguracionDefecto(ConfiguracionCodigos.ALCANCE_INDICE_LIMITE_ROJO, "Limite semaforo rojo para Alcance", "70"),
            new ConfiguracionDefecto(ConfiguracionCodigos.ADJUNTO_MODIFICA_PRESUPUESTO, "Adjunto puede modificar presupuesto", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.ENVIO_NOTIFICACIONES, "Determina si se envían notificaciones", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.CALIDAD_LIMITE_AMARILLO, "Semaforo limite amarillo calidad", "70"),
            new ConfiguracionDefecto(ConfiguracionCodigos.CALIDAD_LIMITE_ROJO, "Semaforo limite rojo calidad", "30"),
            new ConfiguracionDefecto(ConfiguracionCodigos.CALIDAD_GERENTE_MODIFICA, "Permitir al Gerente modificar items calidad", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_SOAPACTION, "PGE SOAP Action", "http://127.0.0.1:8080/SigesVisualizadorPrivado/PublicarProyecto"),
            new ConfiguracionDefecto(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_WSDL, "PGE WSDL", "http://127.0.0.1:8080/SigesVisualizadorPrivado/PublicarProyecto?WSDL"),
            new ConfiguracionDefecto(ConfiguracionCodigos.VISUALIZADOR_EXPORTACION_POR_PGE, "Realizar la exportación al Visualizador por la PGE", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.PROYECTO_GANTT_PERIODO, "true/false: habilitar configuración de período de proyecto en entregables", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.LABEL_OBJETIVO_ESTRATEGICO, "Esta configuracion es utilizada para definir nuevas etiquetas de objetivos estrategicos", "Objetivo Estratégico"),
            new ConfiguracionDefecto(ConfiguracionCodigos.MAIL_FROM, "Dirección desde donde se envían los mails", "mail@agesic.gub.uy"),
            new ConfiguracionDefecto(ConfiguracionCodigos.MAIL_TLS, "Configuración TLS en envío de mail", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.MAIL_DEBUG, "Debug del envío de mail", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.MAIL_ENCODING, "Encoding de los mails a enviar", "UTF-8"),
            new ConfiguracionDefecto(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_VERSION, "Versión de la que se exportan los proyectos al visualizador", "2"),
            new ConfiguracionDefecto(ConfiguracionCodigos.APROBACION_PMOF, "Esta configuración es utilizada para que el PMO Federada cambiar de fase en los proyectos sin enviar solicitudes.", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.APROBACION_REPLANIFICACION_PMOF, "Esta configuración es utilizada para que el PMO Federada pueda cambiar del estado de Ejecución a Planificación sin realizar una solicitud.", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.PROVEEDOR_ES_EXIGIDO_EN_PAGO, "Esta configuración es utilizada para que el PMO Federada deba elegir un proveedor en el pago.", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.PROVEEDOR_ES_EXIGIDO_EN_COMPRA, "Esta configuración es utilizada para que el PMO Federada deba elegir un proveedor en la compra.", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.CLIENTE_ES_EXIGIDO_EN_PAGO, "Esta configuración es utilizada para que se deba elegir un cliente en el pago al momento de aprobarlo.", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MODULO_RIESGOS, "Mostrar el modulo de riesgos", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MODULO_PRODUCTOS, "Mostrar el modulo de productos", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MODULO_PRESUPUESTO, "Mostrar el modulo de presupuesto", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MODULO_DOCUMENTOS, "Mostrar el modulo de documentos", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MODULO_INTERESADOS, "Mostrar el modulo de interesados", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MODULO_COLABORADORES, "Mostrar el modulo de colaboradores", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MODULO_LOCALIZACIONES, "Mostrar el modulo de localizaciones", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MODULO_CALIDAD, "Mostrar el modulo de calidad", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MODULO_MULTIMEDIA, "Mostrar el modulo de multimedia", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MODULO_WEKAN, "Mostrar el modulo Wekan", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MENU_EXPORTAR, "Mostrar el menú Exportar", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SHOW_MENU_HORAS_GASTOS_CALIDAD, "Mostrar el acceso a la pantalla Horas / Gastos / Calidad en el menú", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.GERENTES_ASIGNAN_AREAS_TEMATICAS, "Habilita a Gerente y Adjunto a asignar áreas temáticas a los proyectos en los cuales se encuentran asignados.", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.LARGO_MAXIMO_ID_ADQUISICION, "Define el largo máximo del valor del campo \"Id. de Adquisición\" en Adquisición.", "5"),
            new ConfiguracionDefecto(ConfiguracionCodigos.WEKAN_ADMIN_USUARIO, "Usuario administrador Wekan - Dirección de email o nombre de usuario", "admin"),
            new ConfiguracionDefecto(ConfiguracionCodigos.WEKAN_ADMIN_CONTRASENIA, "Usuario administrador Wekan - Contraseña", "MTIzNDU2"),
            new ConfiguracionDefecto(ConfiguracionCodigos.PMOF_COPIA_PROYECTOS, "Habilita la copia de proyecto para usuarios PMOF del proyecto", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.PMOF_MODIFICA_METADATOS_PROYECTO, "Habilita al PMOF del proyecto a modificar los siguientes campos: nombre del proyecto, división, programa, peso del programa", "true"),
            new ConfiguracionDefecto(ConfiguracionCodigos.PRIMERA_NOTIFICACION_INICIO_COMPRA, "Cantidad de dias previos del primer aviso que la fecha estimada de inicio de compra está próxima a cumplirse.", "7"),
            new ConfiguracionDefecto(ConfiguracionCodigos.SEGUNDA_NOTIFICACION_INICIO_COMPRA, "Cantidad de dias previos del segundo aviso que la fecha estimada de inicio de compra está próxima a cumplirse.", "0"),
            new ConfiguracionDefecto(ConfiguracionCodigos.OCULTAR_IDENTIFICADORES_INICIO, "Oculta los identificadores de proyecto y programa en los resultados de la pantalla de inicio", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.PAGO_FILTRO_PROCEDIMIENTO_COMPRA, "Evita que se apruebe un pago si el procedimiento de compre indicado en la adquisición posee alguna de estas cadenas de texto. Las cadenas a filtrar se deben separar con \";\" (sin comillas)", ""),
            new ConfiguracionDefecto(ConfiguracionCodigos.CENTRO_DE_COSTO_EXIGIDO, "Esta configuración es utilizada para que el campo \"Centro de costo\" sea requerido al crear/modificar una adquisición.", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.PMOT_OMITIR_APROBACION_INICIAL, "Esta configuración permite a un usuario seleccionar PMOF directamente.", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.PMOF_GESTIONA_SUS_PROYECTOS, "Esta configuración permite que él PMO F de un proyecto, también pueda tener los mismos privilegios que el gerente y adjunto.", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.TIPO_ADQUISICION_REQUERIDO, "Indica si el tipo adquisicion es requerido", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.ID_ADQUISICION_REQUERIDO, "Indica si el id adquisición es requerido", "false"),
            new ConfiguracionDefecto(ConfiguracionCodigos.ACTIVAR_RANGOS_ID_ADQUISICION, "Activar Rango de adquisición", "false")

        };

        for (ConfiguracionDefecto conf : configuracionDefecto) {
            if (!configuracionesActualesMap.containsKey(conf.getCodigo())) {
                guardar(conf);
                LOGGER.log(Level.INFO, "Se agregó la configuración por defecto {0}", conf.getCodigo());
            }
        }
    }
}
