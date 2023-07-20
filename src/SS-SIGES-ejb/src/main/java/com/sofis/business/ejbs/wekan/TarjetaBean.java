package com.sofis.business.ejbs.wekan;

import com.sofis.business.ejbs.CronogramasBean;
import com.sofis.business.ejbs.ProyectosBean;
import com.sofis.business.ejbs.SsUsuarioBean;
import com.sofis.business.properties.LabelsEJB;
import com.sofis.business.utils.CronogramaUtils;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.IntegracionWekanUtils;
import com.sofis.business.utils.TableroUtils;
import com.sofis.business.utils.TarjetaUtils;
import com.sofis.business.utils.VinculacionUtils;
import com.sofis.data.daos.ProyectosDAO;
import com.sofis.data.daos.wekan.ActividadDAO;
import com.sofis.data.daos.wekan.TarjetaDAO;
import com.sofis.entities.constantes.ActualizacionWekan;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesIntegracionWekan;
import com.sofis.entities.data.Actividad;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Tablero;
import com.sofis.entities.data.Tarjeta;
import com.sofis.entities.enums.OrigenActividad;
import com.sofis.entities.tipos.ActivityInfoTO;
import com.sofis.entities.tipos.CronogramaTO;
import com.sofis.entities.tipos.EntregableTO;
import com.sofis.entities.tipos.wekan.ActividadTO;
import com.sofis.entities.tipos.wekan.TableroTO;
import com.sofis.entities.tipos.wekan.TarjetaTO;
import com.sofis.entities.tipos.wekan.VinculacionTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.wekanapiclient.CardsApi;
import com.sofis.wekanapiclient.invoker.ApiClient;
import com.sofis.wekanapiclient.invoker.ApiException;
import com.sofis.wekanapiclient.model.Activity;
import com.sofis.wekanapiclient.model.Card;
import com.sofis.wekanapiclient.model.CardCustomField;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "TarjetaBean")
@LocalBean
public class TarjetaBean {

    private static final Logger LOGGER = Logger.getLogger(TarjetaBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private WekanBean wekanBean;

    @Inject
    private CambioEntranteWekanBean cambioEntranteWekanBean;

    @Inject
    private ProyectosBean proyectoBean;
      
    @Inject
    private SsUsuarioBean usuarioBean;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Tarjeta guardarTarjetaNuevaTransaccion(Tarjeta tarjeta) throws DAOGeneralException {

        LOGGER.log(Level.FINE, "guardar tarjeta - id: {0}", tarjeta.getId());

        TarjetaDAO tarjetaDAO = new TarjetaDAO(em);

        return tarjetaDAO.update(tarjeta);
    }

    public void procesarActividadBidireccional(ActivityInfoTO activityInfo, String token) {

        if (token == null) {

            LOGGER.log(Level.WARNING, "El token de la solicitud es null");

            return;
        }

        if (!Activity.SET_CUSTOM_FIELD.equals(activityInfo.getDescription())
                && !Activity.UPDATE_START_AT.equals(activityInfo.getDescription())
                && !Activity.UPDATE_END_AT.equals(activityInfo.getDescription())
                && !Activity.MOVE_CARD.equals(activityInfo.getDescription())) {

            LOGGER.log(Level.FINE, "Actividad no soportada: {0}", activityInfo.getDescription());

            return;
        }

        TarjetaDAO tarjetaDAO = new TarjetaDAO(em);

        Tarjeta tarjeta = tarjetaDAO.obtenerPorIdTarjeta(activityInfo.getCardId());

        if (tarjeta == null) {
            LOGGER.log(Level.FINE, "Tarjeta no vinculada: {0}", activityInfo.getCardId());

            return;
        }

        LOGGER.log(Level.FINE, "Tarjeta vinculada id:{0}", tarjeta.getId());

        if (!tarjeta.getVinculacion().getTablero().getToken().equals(token)) {

            LOGGER.log(Level.WARNING, "Token de actividad no valido");

            return;
        }
        cambioEntranteWekanBean.enviarCambio(activityInfo);
    }

    public void procesarActividad(ActivityInfoTO activityInfo) {

        TarjetaDAO tarjetaDAO = new TarjetaDAO(em);

        Tarjeta tarjeta = tarjetaDAO.obtenerPorIdTarjeta(activityInfo.getCardId());

        switch (activityInfo.getDescription()) {
            case Activity.SET_CUSTOM_FIELD:
                actualizarEntregableSegunCampoPersonalizado(tarjeta, activityInfo);
                break;
            case Activity.UPDATE_START_AT:
                actualizarFechaInicioEntregable(tarjeta, activityInfo);
                break;
            case Activity.UPDATE_END_AT:
                actualizarFechaFinEntregable(tarjeta, activityInfo);
                break;
            case Activity.MOVE_CARD:
                registrarCambioListaTajeta(tarjeta, activityInfo);
                break;
        }
        
        // actualizar id lista si esta presente, en caso de que haya cambiado
        if (activityInfo.getListId() != null
                && !tarjeta.getIdLista().equals(activityInfo.getListId())) {

            tarjeta.setIdLista(activityInfo.getListId());
        }
    }

    public void actualizarTarjeta(CardsApi cardsApi, Tarjeta tarjeta, Integer idProyecto, Entregables entregable) throws ApiException {

        Tablero tablero = tarjeta.getVinculacion().getTablero();
        
        actualizarTarjeta(cardsApi, tablero.getIdTablero(), tarjeta.getIdLista(), tarjeta.getIdTarjeta(),
                tablero.getIdCampoAvance(), tablero.getIdCampoProyecto(), entregable.getEntInicioDate(),
                entregable.getEntFinDate(), entregable.getEntProgreso(), entregable.getEntNombre(), idProyecto);
    }

    public void aplicarCambiosTarjeta(TarjetaTO tarjeta) throws ApiException {

        EntregableTO entregable = tarjeta.getEntregable();
        TableroTO tablero = tarjeta.getVinculacion().getTablero();

        ProyectosDAO proyectoDAO = new ProyectosDAO(em);
        Integer idProyecto = proyectoDAO.obtenerIdPorIdCronograma(tarjeta.getVinculacion().getCronograma().getId());

        ApiClient adminApi = wekanBean.getAdminApi(tarjeta.getVinculacion()).getApiClient();

        CardsApi cardsApi = new CardsApi(adminApi);

        actualizarTarjeta(cardsApi, tablero.getIdTablero(), tarjeta.getIdLista(), tarjeta.getIdTarjeta(),
                tablero.getIdCampoAvance(), tablero.getIdCampoProyecto(), entregable.getFechaInicio(),
                entregable.getFechaFin(), entregable.getProgreso(), entregable.getNombre(), idProyecto);

        ActividadDAO actividadDAO = new ActividadDAO(em);

        for (ActividadTO a : tarjeta.getActividades()) {

            try {
                Actividad actividad = actividadDAO.obtenerPorId(a.getId());
                actividad.setFechaAplicacionCambio(new Date());

            } catch (DAOGeneralException ex) {
                LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_OBTENER_ACTIVIDAD + a.getId(), ex);
            }
        }
    }

    public void actualizarTarjeta(CardsApi cardsApi, String idTablero, String idLista, String idTarjeta,
            String idCampoAvance, String idCampoProyecto, Date fechaInicio, Date fechaFin, Integer avance,
            String nombreProyecto, Integer idProyecto) throws ApiException {

        CardCustomField customFieldAvance = new CardCustomField();
        customFieldAvance.setId(idCampoAvance);
        customFieldAvance.setValue(avance);

        CardCustomField proyecto = new CardCustomField();
        proyecto.setId(idCampoProyecto);
        proyecto.setValue(idProyecto);

        Card card = new Card()
                .startAt(fechaInicio)
                .endAt(fechaFin)
                .title(nombreProyecto)
                .addCustomFieldsItem(customFieldAvance)
                .addCustomFieldsItem(proyecto);
        cardsApi.putBoardListCard(idTablero, idLista, idTarjeta, card);

        LOGGER.log(Level.FINE, "tarjeta actualizada id:{0}", idTarjeta);
    }

    public String actualizarEntregableSegunCampoPersonalizado(Tarjeta tarjeta, ActivityInfoTO activityInfo) {
        
        if (activityInfo.getCustomField().equals("Avance [SIGES]")){
            
            ProyectosDAO proyectoDAO = new ProyectosDAO(em);
            Estados estadoProyecto = proyectoDAO.obtenerEstadoPorIdCronograma(tarjeta.getVinculacion().getCronograma().getCroPk());
            if (!estadoProyecto.isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
                rollbackAvanceWekan(tarjeta);
                LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_PROYECTO_NO_EN_EJECUCION);
                return null;
            }
        }else if (activityInfo.getCustomField().equals("Proyecto [SIGES]")){
            rollbackProyectoWekan(tarjeta);
            return null;
        }
        
        //actualmente no llega la informacion de que campo cambio ni que valor tiene
        //hay que obtener los custom field de la card y comprobar sus valores
        Tablero tablero = tarjeta.getVinculacion().getTablero();

        ApiClient apiClient = wekanBean.getAdminApi(tarjeta.getVinculacion()).getApiClient();
        
        CardsApi cardsApi = new CardsApi(apiClient);
        Card card;
        try {
            card = cardsApi.getBoardListCard(tablero.getIdTablero(), tarjeta.getIdLista(), activityInfo.getCardId());

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_OBTENER_TARJETA_WEKAN, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_OBTENER_TARJETA_WEKAN);

            throw be;
        }

        String result = null;

        //buscar por el id del campo avance
        for (CardCustomField customField : card.getCustomFields()) {

            if (customField.getId().equals(tablero.getIdCampoAvance())) {
                result = actualizarCampoAvance(tarjeta, customField, activityInfo);

                break;
            }
        }

        return result;
    }

    private void rollbackAvanceWekan(Tarjeta tarjeta){
        
            TarjetaDAO tarjetaDAO = new TarjetaDAO(em);  
            Tarjeta tarjetaPersist = tarjetaDAO.obtenerPorIdTarjeta(tarjeta.getIdTarjeta());
            TarjetaTO tarjetaTO = TarjetaUtils.convert(tarjeta);
            
            EntregableTO entregableTO = EntregablesUtils.convert(tarjeta.getEntregable());
            tarjetaTO.setEntregable(entregableTO);
            
            VinculacionTO vinculacionTO = VinculacionUtils.convert(tarjetaPersist.getVinculacion()); 
            CronogramaTO cronogramaTO = CronogramaUtils.convert(tarjeta.getVinculacion().getCronograma());
            TableroTO tableroTO = TableroUtils.convert(tarjeta.getVinculacion().getTablero());
            vinculacionTO.setCronograma(cronogramaTO);
            vinculacionTO.setTablero(tableroTO);
            tarjetaTO.setVinculacion(vinculacionTO);

            try {
                this.aplicarCambiosTarjeta(tarjetaTO);
            } catch (ApiException ex) {
                Logger.getLogger(TarjetaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void rollbackProyectoWekan(Tarjeta tarjeta){
        
            TarjetaDAO tarjetaDAO = new TarjetaDAO(em);  
            Tarjeta tarjetaPersist = tarjetaDAO.obtenerPorIdTarjeta(tarjeta.getIdTarjeta());
            TarjetaTO tarjetaTO = TarjetaUtils.convert(tarjeta);
            
            EntregableTO entregableTO = EntregablesUtils.convert(tarjeta.getEntregable());
            tarjetaTO.setEntregable(entregableTO);
            
            VinculacionTO vinculacionTO = VinculacionUtils.convert(tarjetaPersist.getVinculacion()); 
            CronogramaTO cronogramaTO = CronogramaUtils.convert(tarjeta.getVinculacion().getCronograma());
            TableroTO tableroTO = TableroUtils.convert(tarjeta.getVinculacion().getTablero());
            vinculacionTO.setCronograma(cronogramaTO);
            vinculacionTO.setTablero(tableroTO);
            tarjetaTO.setVinculacion(vinculacionTO);

            try {
                this.aplicarCambiosTarjeta(tarjetaTO);
            } catch (ApiException ex) {
                Logger.getLogger(TarjetaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public String actualizarFechaInicioEntregable(Tarjeta tarjeta, ActivityInfoTO activityInfo) {
        ProyectosDAO proyectoDAO = new ProyectosDAO(em);

        Estados estadoProyecto = proyectoDAO
                .obtenerEstadoPorIdCronograma(tarjeta.getVinculacion().getCronograma().getCroPk());

        if (estadoProyecto.isEstado(Estados.ESTADOS.FINALIZADO.estado_id)) {
            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_PROYECTO_FINALIZADO);
            rollbackFechaInicioWekan(tarjeta);
            return null;
        }

        
        String dateValue = null;
        try {
            dateValue = (String)activityInfo.getTimeValue();

        }catch (Exception e){
            dateValue = activityInfo.getTimeValue().toString().substring(7, 20);
//            dateValue = ((TimeValueTO)activityInfo.getTimeValue()).get$date();
        }

        if (dateValue == null) {

            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_SIN_VALOR);
            rollbackFechaInicioWekan(tarjeta);
            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_SIN_VALOR);
        }

        Date nuevaFecha;
        try {
            Timestamp timestamp = new Timestamp(Long.parseLong(dateValue));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
            nuevaFecha = format.parse(format.format(timestamp));
        } catch (Exception ex) {
            
            try {
                nuevaFecha = IntegracionWekanUtils.DATE_FORMATTER.parse(dateValue);
            } catch (Exception ex2) {
                LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_INICIO_PARSE, ex);
                rollbackFechaInicioWekan(tarjeta);
                return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_INICIO_PARSE);
            }
            
        }

        Entregables entregable = tarjeta.getEntregable();
        if (entregable.getEntParent()) {
            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_PADRE);
            rollbackFechaInicioWekan(tarjeta);
            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_PADRE);
        }
        if (!entregable.getEntProductosSet().isEmpty()) {
            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_TIENE_PRODUCTOS);
            rollbackFechaInicioWekan(tarjeta);
            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_TIENE_PRODUCTOS);
        }
        if (entregable.getEntInicioEsHito()) {
            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_HITO);
            rollbackFechaInicioWekan(tarjeta);
            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_HITO);
        }

        Date fechaAnterior = entregable.getEntInicioDate();
        if (DatesUtils.fechasIguales(nuevaFecha, fechaAnterior)) {
            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_INICIO_VALOR_IGUAL);
            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_INICIO_VALOR_IGUAL);
        }

        //comparacion con respecto a fecha de fin: no puede ser mayor
        if (DatesUtils.esMayor(nuevaFecha, entregable.getEntFinDate())) {
            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_INICIO_MAYOR_FIN);
            rollbackFechaInicioWekan(tarjeta);
            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_INICIO_MAYOR_FIN);
        }
        
        entregable.setEntInicio(nuevaFecha.getTime());
        entregable.setEntDuracion(DatesUtils.diasEntreFechas(nuevaFecha, entregable.getEntFinDate()) + 1);

        proyectoBean.actualizarIndicadoresProyectoEntregable(entregable);
        
        

        
        
//        proy = proyectoBean.obtenerProyPorId(entregable.getEntCroFk().getProyecto().getProyPk());
//        for (Entregables entr : proy.getProyCroFk().getEntregablesSet()) {
////            List<Entregables> referencias = entregablesDAO.obtenerEntregablesReferenciando(entregable.getEntPk());
//
//            LOGGER.info("/////");
//            LOGGER.info(entr.getEntNombre());
//            LOGGER.info(""+entr.getEntInicio());
////            if (referencias.isEmpty()) {
////                    continue;
////            }
//
////            try{
////                Entregables ent = entregablesDAO.findById(Entregables.class, entregable.getEntPk());
////            }
////            catch(Exception e){
////
////            }
//        }
        
        
        Actividad actividad = new Actividad();
        actividad.setTarjeta(tarjeta);

        actividad.setOrigen(OrigenActividad.WEKAN);
        actividad.setIdActividad(activityInfo.getActivityId());
        actividad.setDescripcion(activityInfo.getDescription());
        actividad.setIdUsuario(activityInfo.getUserId());
        actividad.setUsuario(activityInfo.getUser());

        actividad.setActualizacion(ActualizacionWekan.FECHA_INICIO_ENTREGABLE);
        actividad.setValorAnterior(IntegracionWekanUtils.DATE_FORMATTER.format(fechaAnterior));
        actividad.setValorNuevo(IntegracionWekanUtils.DATE_FORMATTER.format(nuevaFecha));
        actividad.setFechaCreacion(new Date());
        actividad.setFechaAplicacionCambio(new Date());

        ActividadDAO actividadDAO = new ActividadDAO(em);

        try {
            actividadDAO.guardar(actividad);
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GUARDAR, ex);

            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_GUARDAR);
        }
       
        return LabelsEJB.getValue(ConstantesIntegracionWekan.ACTIVIDAD_FECHA_INICIO_ACTUALIZADA);
    }
    
    private void rollbackFechaInicioWekan(Tarjeta tarjeta){
        
            TarjetaDAO tarjetaDAO = new TarjetaDAO(em);  
            Tarjeta tarjetaPersist = tarjetaDAO.obtenerPorIdTarjeta(tarjeta.getIdTarjeta());
            TarjetaTO tarjetaTO = TarjetaUtils.convert(tarjeta);
            
            EntregableTO entregableTO = EntregablesUtils.convert(tarjeta.getEntregable());
            tarjetaTO.setEntregable(entregableTO);
            
            VinculacionTO vinculacionTO = VinculacionUtils.convert(tarjetaPersist.getVinculacion()); 
            CronogramaTO cronogramaTO = CronogramaUtils.convert(tarjeta.getVinculacion().getCronograma());
            TableroTO tableroTO = TableroUtils.convert(tarjeta.getVinculacion().getTablero());
            vinculacionTO.setCronograma(cronogramaTO);
            vinculacionTO.setTablero(tableroTO);
            tarjetaTO.setVinculacion(vinculacionTO);

            try {
                this.aplicarCambiosTarjeta(tarjetaTO);
            } catch (ApiException ex) {
                Logger.getLogger(TarjetaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    

    public String actualizarFechaFinEntregable(Tarjeta tarjeta, ActivityInfoTO activityInfo) {
        ProyectosDAO proyectoDAO = new ProyectosDAO(em);

        Estados estadoProyecto = proyectoDAO
                .obtenerEstadoPorIdCronograma(tarjeta.getVinculacion().getCronograma().getCroPk());

        if (estadoProyecto.isEstado(Estados.ESTADOS.FINALIZADO.estado_id)) {

            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_PROYECTO_FINALIZADO);

            return null;
        }

        String dateValue = null;
        try {
            dateValue = (String)activityInfo.getTimeValue();
        }catch (Exception e){
            dateValue = activityInfo.getTimeValue().toString().substring(7, 20);
        }
        
        if (dateValue == null) {
            
            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_SIN_VALOR);

            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_SIN_VALOR);
        }

        Date nuevaFecha;
        try {
            Timestamp timestamp = new Timestamp(Long.parseLong(dateValue));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
            nuevaFecha = format.parse(format.format(timestamp));
        } catch (Exception ex) {
            
            try {
                nuevaFecha = IntegracionWekanUtils.DATE_FORMATTER.parse(dateValue);
            } catch (Exception ex2) {
                LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_FIN_PARSE, ex);
                rollbackFechaFinWekan(tarjeta);
                return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_FIN_PARSE);
            }
            
        }
       
        Entregables entregable = tarjeta.getEntregable();

        if (entregable.getEntParent()) {
            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_PADRE);
            rollbackFechaFinWekan(tarjeta);
            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_PADRE);
        }
        if (!entregable.getEntProductosSet().isEmpty()) {
            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_TIENE_PRODUCTOS);
            rollbackFechaFinWekan(tarjeta);
            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_TIENE_PRODUCTOS);
        }
        if (entregable.getEntInicioEsHito()) {
            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_HITO);
            rollbackFechaFinWekan(tarjeta);
            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_ENTREGABLE_HITO);
        }

        Date fechaAnterior = entregable.getEntFinDate();
       if (DatesUtils.fechasIguales(nuevaFecha, fechaAnterior)) {

            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_FIN_VALOR_IGUAL);
            rollbackFechaFinWekan(tarjeta);
            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_FIN_VALOR_IGUAL);
        }

        //comparacion con respecto a fecha de inicio: no puede ser menor
        if (DatesUtils.esMayor(entregable.getEntInicioDate(), nuevaFecha)) {
            LOGGER.log(Level.FINE, ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_FIN_MENOR_INICIO);
            rollbackFechaFinWekan(tarjeta);
            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_FECHA_FIN_MENOR_INICIO);
        }

        entregable.setEntFin(nuevaFecha.getTime());
        entregable.setEntDuracion(DatesUtils.diasEntreFechas(entregable.getEntInicioDate(), nuevaFecha) + 1);

        proyectoBean.actualizarIndicadoresProyectoEntregable(entregable);
        
//        proyectoBean.postProcesarEntregablesRelacionados(entregable.getEntCroFk(), null);

        Actividad actividad = new Actividad();
        actividad.setTarjeta(tarjeta);

        actividad.setOrigen(OrigenActividad.WEKAN);
        actividad.setIdActividad(activityInfo.getActivityId());
        actividad.setDescripcion(activityInfo.getDescription());
        actividad.setIdUsuario(activityInfo.getUserId());
        actividad.setUsuario(activityInfo.getUser());

        actividad.setActualizacion(ActualizacionWekan.FECHA_FIN_ENTREGABLE);
        actividad.setValorAnterior(IntegracionWekanUtils.DATE_FORMATTER.format(fechaAnterior));
        actividad.setValorNuevo(IntegracionWekanUtils.DATE_FORMATTER.format(nuevaFecha));
        actividad.setFechaCreacion(new Date());
        actividad.setFechaAplicacionCambio(new Date());

        ActividadDAO actividadDAO = new ActividadDAO(em);
        try {
            actividadDAO.guardar(actividad);
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GUARDAR, ex);

            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_GUARDAR);
        }

        return LabelsEJB.getValue(ConstantesIntegracionWekan.ACTIVIDAD_FECHA_FIN_ACTUALIZADA);
    }
    
    public void rollbackFechaFinWekan(Tarjeta tarjeta){
        
            TarjetaDAO tarjetaDAO = new TarjetaDAO(em);  
            Tarjeta tarjetaPersist = tarjetaDAO.obtenerPorIdTarjeta(tarjeta.getIdTarjeta());
            TarjetaTO tarjetaTO = TarjetaUtils.convert(tarjeta);
            
            EntregableTO entregableTO = EntregablesUtils.convert(tarjeta.getEntregable());
            tarjetaTO.setEntregable(entregableTO);
            
            VinculacionTO vinculacionTO = VinculacionUtils.convert(tarjetaPersist.getVinculacion()); 
            CronogramaTO cronogramaTO = CronogramaUtils.convert(tarjeta.getVinculacion().getCronograma());
            TableroTO tableroTO = TableroUtils.convert(tarjeta.getVinculacion().getTablero());
            vinculacionTO.setCronograma(cronogramaTO);
            vinculacionTO.setTablero(tableroTO);
            tarjetaTO.setVinculacion(vinculacionTO);

            try {
                this.aplicarCambiosTarjeta(tarjetaTO);
            } catch (ApiException ex) {
                Logger.getLogger(TarjetaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public String registrarCambioListaTajeta(Tarjeta tarjeta, ActivityInfoTO activityInfo) {

        Actividad actividad = new Actividad();
        actividad.setTarjeta(tarjeta);

        actividad.setOrigen(OrigenActividad.WEKAN);
        actividad.setIdActividad(activityInfo.getActivityId());
        actividad.setDescripcion(activityInfo.getDescription());
        actividad.setIdUsuario(activityInfo.getUserId());
        actividad.setUsuario(activityInfo.getUser());

        actividad.setActualizacion(ActualizacionWekan.CAMBIO_LISTA_TARJETA);
        actividad.setValorAnterior(tarjeta.getIdLista());
        actividad.setValorNuevo(activityInfo.getListId());
        actividad.setFechaCreacion(new Date());
        actividad.setFechaAplicacionCambio(new Date());

        ActividadDAO actividadDAO = new ActividadDAO(em);

        try {
            actividadDAO.guardar(actividad);
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GUARDAR, ex);

            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_GUARDAR);
        }

        return null;
    }

    private String actualizarCampoAvance(Tarjeta tarjeta, CardCustomField customField, ActivityInfoTO activityInfo) {

        Object value = customField.getValue();

        Integer progreso = (value instanceof Integer) ? (Integer) value : 0;

        if (progreso < 0) {
            progreso = 0;
        } else if (progreso > 100) {
            progreso = 100;
        }

        Entregables entregable = tarjeta.getEntregable();
        
        Integer progresoAnterior = entregable.getEntProgreso();

        if (progresoAnterior.equals(progreso)) {

            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_ACTIVIDAD_PROGRESO_VALOR_IGUAL);
        }

        entregable.setEntProgreso(progreso);

        proyectoBean.actualizarIndicadoresProyectoEntregable(entregable);
        
        
        
        Proyectos proy = proyectoBean.obtenerProyPorId(entregable.getEntCroFk().getProyecto().getProyPk());
        
        Cronogramas croActual = proy.getProyCroFk();
        if(croActual!=null){
            proyectoBean.postProcesarEntregablesRelacionados(croActual, null);
        }

        Actividad actividad = new Actividad();
        actividad.setTarjeta(tarjeta);

        actividad.setOrigen(OrigenActividad.WEKAN);
        actividad.setIdActividad(activityInfo.getActivityId());
        actividad.setDescripcion(activityInfo.getDescription());
        actividad.setIdUsuario(activityInfo.getUserId());
        actividad.setUsuario(activityInfo.getUser());

        actividad.setActualizacion(ActualizacionWekan.PROGRESO_ENTREGABLE);
        actividad.setValorAnterior(progresoAnterior.toString());
        actividad.setValorNuevo(progreso.toString());
        actividad.setFechaCreacion(new Date());
        actividad.setFechaAplicacionCambio(new Date());

        ActividadDAO actividadDAO = new ActividadDAO(em);

        try {
            actividadDAO.guardar(actividad);
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GUARDAR, ex);

            return LabelsEJB.getValue(ConstantesIntegracionWekan.ERROR_GUARDAR);
        }

        return LabelsEJB.getValue(ConstantesIntegracionWekan.ACTIVIDAD_PROGRESO_ACTUALIZADO);
    }

}
