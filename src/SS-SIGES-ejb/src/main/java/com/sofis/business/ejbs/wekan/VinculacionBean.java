package com.sofis.business.ejbs.wekan;

import com.sofis.business.ejbs.ConfiguracionBean;
import com.sofis.business.ejbs.DatosUsuario;
import com.sofis.business.properties.LabelsEJB;
import com.sofis.business.utils.CronogramaUtils;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.TableroUtils;
import com.sofis.business.utils.TarjetaUtils;
import com.sofis.business.utils.VinculacionUtils;
import com.sofis.business.wrapper.WekanApiClientWrapper;
import com.sofis.data.daos.CronogramasDAO;
import com.sofis.data.daos.EntregablesDAO;
import com.sofis.data.daos.ProyectosDAO;
import com.sofis.data.daos.wekan.ActividadDAO;
import com.sofis.data.daos.wekan.TableroDAO;
import com.sofis.data.daos.wekan.TarjetaDAO;
import com.sofis.data.daos.wekan.VinculacionDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesIntegracionWekan;
import com.sofis.entities.data.Actividad;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Estados.ESTADOS;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.tipos.wekan.VinculacionTO;
import com.sofis.entities.data.Tablero;
import com.sofis.entities.data.Tarjeta;
import com.sofis.entities.data.Vinculacion;
import com.sofis.entities.tipos.EntregableTO;
import com.sofis.entities.tipos.wekan.EntregableTarjetaTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.EmailValidator;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.wekanapiclient.model.Activity;
import com.sofis.wekanapiclient.BoardsApi;
import com.sofis.wekanapiclient.CardsApi;
import com.sofis.wekanapiclient.CustomFieldsApi;
import com.sofis.wekanapiclient.IntegrationsApi;
import com.sofis.wekanapiclient.ListsApi;
import com.sofis.wekanapiclient.LoginApi;
import com.sofis.wekanapiclient.SwimlanesApi;
import com.sofis.wekanapiclient.invoker.ApiClient;
import com.sofis.wekanapiclient.invoker.ApiException;
import com.sofis.wekanapiclient.model.Board;
import com.sofis.wekanapiclient.model.BoardMember;
import com.sofis.wekanapiclient.model.Card;
import com.sofis.wekanapiclient.model.CardCustomField;
import com.sofis.wekanapiclient.model.CardInline;
import com.sofis.wekanapiclient.model.CustomField;
import com.sofis.wekanapiclient.model.CustomFieldInline;
import com.sofis.wekanapiclient.model.CustomFieldSetting;
import com.sofis.wekanapiclient.model.Integration;
import com.sofis.wekanapiclient.model.IntegrationInline;
import com.sofis.wekanapiclient.model.IntegrationType;
import com.sofis.wekanapiclient.model.ListInline;
import com.sofis.wekanapiclient.model.Login;
import com.sofis.wekanapiclient.model.SwimlaneInline;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "VinculacionBean")
@LocalBean
public class VinculacionBean {

    private static final Logger LOGGER = Logger.getLogger(VinculacionBean.class.getName());

    private static final Pattern WEKAN_URL_TABLERO_PATTERN = Pattern.compile("(.*)?\\/b\\/(.*)\\/(.*)");

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private DatosUsuario datosUsuario;

    @Inject
    private ConfiguracionBean configuracionBean;

    @Inject
    private TableroBean tableroBean;

    @Inject
    private TarjetaBean tarjetaBean;

    @Inject
    private WekanBean wekanBean;

    @Inject
    private ActividadBean actividadBean;

    public VinculacionTO vincularTablero(Integer idCronograma, String urlTablero, String usuarioTablero, String contraseniaTablero) {

        try {
            CronogramasDAO cronogramaDAO = new CronogramasDAO(em);

            //control cronograma
            Cronogramas cronograma = cronogramaDAO.findById(Cronogramas.class, idCronograma);

            if (cronograma == null) {
                BusinessException be = new BusinessException();
                be.addError(ConstantesIntegracionWekan.ERROR_CRONOGRAMA_NO_ENCONTRADO);

                throw be;
            }

            // comprobar vinculacion
            VinculacionDAO vinculacionDAO = new VinculacionDAO(em);

            Vinculacion vinculacion = vinculacionDAO.obtenerPorIdCronograma(cronograma.getCroPk());

            if (vinculacion != null) {
                BusinessException be = new BusinessException();
                be.addError(ConstantesIntegracionWekan.ERROR_CRONOGRAMA_YA_VINCULADO);

                throw be;
            }

            //control proyecto
            Proyectos proyecto = cronograma.getProyecto();
            Integer idEstado = proyecto.getProyEstFk().getEstPk();

            if (!idEstado.equals(Estados.ESTADOS.INICIO.estado_id)
                    && !idEstado.equals(Estados.ESTADOS.PLANIFICACION.estado_id)
                    && !idEstado.equals(Estados.ESTADOS.EJECUCION.estado_id)) {

                BusinessException be = new BusinessException();
                be.addError(ConstantesIntegracionWekan.ERROR_ESTADO_PROYECTO_NO_VALIDO);

                throw be;
            }

            // control url tablero
            Matcher matcher = WEKAN_URL_TABLERO_PATTERN.matcher(urlTablero);
            if (!matcher.matches()) {

                BusinessException be = new BusinessException();
                be.addError(ConstantesIntegracionWekan.ERROR_URL_VINCULACION_NO_VALIDA);

                throw be;
            }

            String urlServidor = matcher.group(1);
            String idTablero = matcher.group(2);

            ApiClient apiClient = new ApiClient();
            apiClient.setBasePath(urlServidor);

            Login login = comprobarUsuario(apiClient, usuarioTablero, contraseniaTablero);

            apiClient.setApiKeyPrefix("Bearer");
            apiClient.setApiKey(login.getToken());

            String idUsuario = login.getId();

            LOGGER.log(Level.INFO, "paso Login");
            comprobarUsuarioTablero(idTablero, idUsuario, apiClient);
LOGGER.log(Level.INFO, "paso comprobacion tablero");
           
            Integer idOrganismo = cronograma.getProyecto().getProyOrgFk().getOrgPk();

            apiClient = wekanBean.getAdminApi(urlServidor, idOrganismo).getApiClient();

            Tablero tablero = comprobarEstadoTablero(urlServidor, idTablero, urlTablero, apiClient, idUsuario);

            // crear vinculacion
            vinculacion = new Vinculacion();
            vinculacion.setTablero(tablero);
            vinculacion.setCronograma(cronograma);
            vinculacion.setFechaAlta(new Date());
            vinculacion.setUsuarioAlta(datosUsuario.getCodigoUsuario());

            vinculacionDAO.guardar(vinculacion);

            VinculacionTO vinculacionTO = VinculacionUtils.convert(vinculacion);
            vinculacionTO.setTablero(TableroUtils.convert(vinculacion.getTablero()));

            return vinculacionTO;

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GUARDAR, ex);

            BusinessException be = new BusinessException(ex);
            be.addError(ConstantesIntegracionWekan.ERROR_GUARDAR);

            throw be;
        }
    }

    public void restaurarVinculacion(Integer idCronograma) {
        try {
            CronogramasDAO cronogramaDAO = new CronogramasDAO(em);

            //control cronograma
            Cronogramas cronograma = cronogramaDAO.findById(Cronogramas.class, idCronograma);

            if (cronograma == null) {
                BusinessException be = new BusinessException();
                be.addError(ConstantesIntegracionWekan.ERROR_CRONOGRAMA_NO_ENCONTRADO);

                throw be;
            }

            // comprobar vinculacion
            VinculacionDAO vinculacionDAO = new VinculacionDAO(em);

            Vinculacion vinculacion = vinculacionDAO.obtenerPorIdCronograma(cronograma.getCroPk());

            if (vinculacion == null) {
                BusinessException be = new BusinessException();
                be.addError(ConstantesIntegracionWekan.ERROR_CRONOGRAMA_NO_VINCULADO);

                throw be;
            }

            Tablero tablero = vinculacion.getTablero();

            ApiClient apiClient = wekanBean.getAdminApi(vinculacion.getTablero().getUrlServidor(), cronograma.getProyecto().getProyOrgFk().getOrgPk()).getApiClient();

            String loginId = wekanBean.getAdminApi(vinculacion).getLoginId();

            ListsApi listsApi = new ListsApi(apiClient);

            String entregablesSigesListId = obtenerIdListaEntregablesSiges(tablero, cronograma.getCroPk(), cronograma.getProyecto().getProyOrgFk().getOrgPk());

            TarjetaDAO tarjetaDAO = new TarjetaDAO(em);

            List<Tarjeta> tarjetas = tarjetaDAO.obtenerPorIdTablero(tablero.getId());

            CardsApi cardsApi = new CardsApi(apiClient);
            for (Tarjeta t : tarjetas) {

                try {
                    ListInline listaCard = listsApi.getList(tablero.getIdTablero(), t.getIdLista());
                    if (listaCard.getArchived()) {
                        throw new Exception("La lista " + listaCard.getTitle() + "está archivada");
                    }
                    cardsApi.getBoardListCard(tablero.getIdTablero(), t.getIdLista(), t.getIdTarjeta());

                } catch (Exception except) {
                    Entregables ent = t.getEntregable();
                    CardCustomField avance = new CardCustomField();
                    avance.setId(tablero.getIdCampoAvance());
                    avance.setValue(ent.getEntProgreso());

                    CardCustomField proyecto = new CardCustomField();
                    proyecto.setId(tablero.getIdCampoProyecto());
                    proyecto.setValue(ent.getEntCroFk().getProyecto().getProyPk());

                    Card card = new Card()
                            .authorId(loginId)
                            .members(new ArrayList<String>())
                            .title(ent.getEntNombre())
                            .description(ent.getEntDescripcion())
                            .swimlaneId(tablero.getIdCarril());

                    CardInline newCard;
                    try {
                        newCard = cardsApi.newCard(tablero.getIdTablero(), entregablesSigesListId, card);

                        card = new Card()
                                .startAt(ent.getEntInicioDate())
                                .endAt(ent.getEntFinDate())
                                .title(ent.getEntNombre())
                                .addCustomFieldsItem(avance)
                                .addCustomFieldsItem(proyecto);

                        cardsApi.putBoardListCard(tablero.getIdTablero(), entregablesSigesListId, newCard.getId(), card);

                        t.setIdLista(entregablesSigesListId);
                        t.setIdTarjeta(newCard.getId());
                        tarjetaBean.actualizarTarjeta(cardsApi, t, t.getEntregable().getEntCroFk().getProyecto().getProyPk(), ent);

                    } catch (Exception ex) {

                        LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_CREAR_TARJETA, ex);

                        BusinessException be = new BusinessException();
                        be.addError(ConstantesIntegracionWekan.ERROR_CREAR_TARJETA);

                        throw be;
                    }
                }
            }

            tablero.setIdLista(entregablesSigesListId);

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GUARDAR, ex);

            BusinessException be = new BusinessException(ex);
            be.addError(ConstantesIntegracionWekan.ERROR_GUARDAR);

            throw be;
        } catch (Exception ex1) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GUARDAR, ex1);

            BusinessException be = new BusinessException(ex1);
            be.addError(ConstantesIntegracionWekan.ERROR_GUARDAR);

            throw be;
        }
    }

    public String obtenerIdListaEntregablesSiges(Tablero tablero, Integer CronogramaId, Integer OrgId) throws DAOGeneralException, ApiException {

        VinculacionDAO vinculacionDAO = new VinculacionDAO(em);

        Vinculacion vinculacion = vinculacionDAO.obtenerPorIdCronograma(CronogramaId);

        ApiClient apiClient = wekanBean.getAdminApi(tablero.getUrlServidor(), OrgId).getApiClient();

        ListsApi listsApi = new ListsApi(apiClient);

        ListInline line = listsApi.getList(tablero.getIdTablero(), tablero.getIdLista());

        ListInline newList;
        String entregablesSigesListId = tablero.getIdLista();

        if (line == null) {
            try {
                newList = listsApi.newList(tablero.getIdTablero(), LabelsEJB.getValue(ConstantesIntegracionWekan.NOMBRE_LISTA_ENTREGABLES));
                entregablesSigesListId = newList.getId();
                tablero.setIdLista(entregablesSigesListId);
            } catch (Exception ex) {
                tableroBean.guardarTableroNuevaTransaccion(tablero);

                LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_CREAR_LISTA, ex);

                BusinessException be = new BusinessException();
                be.addError(ConstantesIntegracionWekan.ERROR_CREAR_LISTA);

                throw be;
            }
        }
        return entregablesSigesListId;
    }

    public void desvincularTablero(Integer idCronograma) {
        try {
            CronogramasDAO cronogramaDAO = new CronogramasDAO(em);
            //control cronograma
            Cronogramas cronograma = cronogramaDAO.findById(Cronogramas.class, idCronograma);

            if (cronograma == null) {
                BusinessException be = new BusinessException();
                be.addError(ConstantesIntegracionWekan.ERROR_CRONOGRAMA_NO_ENCONTRADO);

                throw be;
            }
            // comprobar vinculacion
            VinculacionDAO vinculacionDAO = new VinculacionDAO(em);

            Vinculacion vinculacion = vinculacionDAO.obtenerPorIdCronograma(cronograma.getCroPk());

            Integer cantidadVinculacionesTablero = vinculacionDAO.obtenerCantidadVinculacionesPorIdTablero(vinculacion.getTablero().getId());
            ApiClient apiClient = wekanBean.getAdminApi(vinculacion.getTablero().getUrlServidor(), cronograma.getProyecto().getProyOrgFk().getOrgPk()).getApiClient();

            if (vinculacion == null) {
                BusinessException be = new BusinessException();
                be.addError(ConstantesIntegracionWekan.ERROR_CRONOGRAMA_YA_VINCULADO);

                throw be;
            }

            TarjetaDAO tarjetaDAO = new TarjetaDAO(em);
            List<Tarjeta> tarjetas = tarjetaDAO.obtenerPorIdTablero(vinculacion.getTablero().getId());
            for (Tarjeta tarj : tarjetas) {
                if (tarj.getEntregable().getEntCroFk().getCroPk() == cronograma.getCroPk()) {
                    ActividadDAO actividadDAO = new ActividadDAO(em);
                    List<Actividad> actividades = actividadDAO.obtenerPorIdTarjeta(tarj.getId());
                    for (Actividad act : actividades) {
                        actividadDAO.delete(act);
                    }
                    eliminarVinculacionEntregable(tarj.getEntregable().getEntPk());
                }
            }
            TableroDAO tableroDAO = new TableroDAO(em);

            String idTablero = vinculacion.getTablero().getIdTablero();

            vinculacionDAO.delete(vinculacion);

            Tablero tablero = tableroDAO.obtenerPorIdTableroWekan(idTablero);
            tableroDAO.delete(tablero);

            if (cantidadVinculacionesTablero <= 1) {
                IntegrationsApi integrationApi = new IntegrationsApi(apiClient);
                integrationApi.deleteIntegration(idTablero, vinculacion.getTablero().getIdIntegracion());
            }

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GUARDAR, ex);

            BusinessException be = new BusinessException(ex);
            be.addError(ConstantesIntegracionWekan.ERROR_GUARDAR);

            throw be;
        } catch (ApiException ex) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_ELIMINAR_INTEGRACION, ex);
            Logger.getLogger(VinculacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<EntregableTarjetaTO> obtenerVinculacionEntregables(Integer idCronograma) {

        ProyectosDAO proyectoDAO = new ProyectosDAO(em);
        Estados estadoProyecto = proyectoDAO.obtenerEstadoPorIdCronograma(idCronograma);

        EntregablesDAO entregablesDAO = new EntregablesDAO(em);
        List<Entregables> entregables = entregablesDAO.obtenerEntPorCroPk(idCronograma);

        TarjetaDAO tarjetaDAO = new TarjetaDAO(em);
        List<Tarjeta> tarjetas = tarjetaDAO.obtenerPorIdCronograma(idCronograma);

        List<EntregableTarjetaTO> result = new ArrayList<>();
        for (Entregables e : entregables) {

            EntregableTarjetaTO et = new EntregableTarjetaTO();
            result.add(et);

            EntregableTO entregableTO = EntregablesUtils.convert(e);
            et.setEntregable(entregableTO);

            Tarjeta tarjeta = null;
            for (Tarjeta t : tarjetas) {

                if (t.getEntregable().equals(e)) {
                    tarjeta = t;
                }
            }

            boolean vinculable = false;

            if (tarjeta != null) {
                et.setRazonNoVinculable(LabelsEJB.getValue(ConstantesIntegracionWekan.ENTREGABLE_YA_VINCULADO));
                et.setTarjeta(TarjetaUtils.convert(tarjeta));

            } else if (estadoProyecto.isEstado(ESTADOS.FINALIZADO.estado_id)) {
                et.setRazonNoVinculable(LabelsEJB.getValue(ConstantesIntegracionWekan.ENTREGABLE_NO_VINCULABLE_PROYECTO_FINALIZADO));

            } else if (e.getEntParent()) {
                et.setRazonNoVinculable(LabelsEJB.getValue(ConstantesIntegracionWekan.ENTREGABLE_NO_VINCULABLE_ES_PADRE));

            } else if (!e.getEntProductosSet().isEmpty()) {
                et.setRazonNoVinculable(LabelsEJB.getValue(ConstantesIntegracionWekan.ENTREGABLE_NO_VINCULABLE_TIENE_PRODUCTOS));

            } else if (e.getEntInicioEsHito()) {
                et.setRazonNoVinculable(LabelsEJB.getValue(ConstantesIntegracionWekan.ENTREGABLE_NO_VINCULABLE_HITO));

            } else if (e.getEsReferencia()) {
                et.setRazonNoVinculable(LabelsEJB.getValue(ConstantesIntegracionWekan.ENTREGABLE_NO_VINCULABLE_ES_REFERENCIA));

            } else if (e.getEntPredecesorFk() != null && !e.getEntPredecesorFk().isEmpty()) {
                et.setRazonNoVinculable(LabelsEJB.getValue(ConstantesIntegracionWekan.ENTREGABLE_NO_VINCULABLE_TIENE_DEPENDENCIA));

            } else {
                vinculable = true;
            }

            et.setVinculable(vinculable);
        }

        return result;
    }

    public VinculacionTO obtenerPorIdCronograma(Integer idCronograma) {

        try {
            VinculacionDAO vinculacionDAO = new VinculacionDAO(em);
            Vinculacion vinculacion = vinculacionDAO.obtenerPorIdCronograma(idCronograma);

            if (vinculacion == null) {

                return null;
            }

            VinculacionTO vinculacionTO = VinculacionUtils.convert(vinculacion);

            vinculacionTO.setTablero(TableroUtils.convert(vinculacion.getTablero()));
            vinculacionTO.setCronograma(CronogramaUtils.convert(vinculacion.getCronograma()));

            return vinculacionTO;

        } catch (DAOGeneralException ex) {

            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_OBTENER_VINCULACION, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_OBTENER_VINCULACION);

            throw be;
        }
    }

    public void vincularEntregables(Integer idCronograma, List<EntregableTO> entregablesTO) {

        try {
            VinculacionDAO vinculacionDAO = new VinculacionDAO(em);
            Vinculacion vinculacion = vinculacionDAO.obtenerPorIdCronograma(idCronograma);

            if (vinculacion == null) {

                BusinessException be = new BusinessException();
                be.addError(ConstantesIntegracionWekan.ERROR_CRONOGRAMA_NO_VINCULADO);

                throw be;
            }

            ProyectosDAO proyectoDAO = new ProyectosDAO(em);

            Proyectos proyecto = proyectoDAO.obtenerPorIdCronograma(idCronograma);

            if (proyecto.getProyEstFk().isEstado(ESTADOS.FINALIZADO.estado_id)) {

                BusinessException be = new BusinessException();
                be.addError(ConstantesIntegracionWekan.ERROR_ESTADO_PROYECTO_NO_VALIDO);

                throw be;
            }

            WekanApiClientWrapper apiClientWrapper = wekanBean.getAdminApi(vinculacion);
            ApiClient apiClient = apiClientWrapper.getApiClient();
            String loginId = apiClientWrapper.getLoginId();

            TarjetaDAO tarjetaDAO = new TarjetaDAO(em);
            List<Tarjeta> tarjetas = tarjetaDAO.obtenerPorIdCronograma(idCronograma);

            EntregablesDAO entregablesDAO = new EntregablesDAO(em);
            List<Entregables> entregables = entregablesDAO.obtenerEntPorCroPk(idCronograma);

            CardsApi cardsApi = new CardsApi(apiClient);
            for (EntregableTO e : entregablesTO) {

                for (Entregables entregable : entregables) {

                    if (!entregable.getEntPk().equals(e.getId())) {

                        continue;
                    }

                    Tarjeta tarjeta = null;
                    for (Tarjeta t : tarjetas) {

                        if (t.getEntregable().equals(entregable)) {
                            tarjeta = t;
                            break;
                        }
                    }

                    if (tarjeta == null) {

                        tarjeta = vincularEntregable(cardsApi, loginId, vinculacion, entregable);

                        try {
                            tarjetaDAO.update(tarjeta);
                        } catch (DAOGeneralException ex) {
                            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GUARDAR_TARJETA, ex);

                            BusinessException be = new BusinessException();
                            be.addError(ConstantesIntegracionWekan.ERROR_GUARDAR_TARJETA);

                            throw be;
                        }
                    }

                    try {
                        tarjetaBean.actualizarTarjeta(cardsApi, tarjeta, proyecto.getProyPk(), entregable);
                    } catch (Exception ex) {

                        LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_ACTUALIZAR_TARJETA, ex);

                        BusinessException be = new BusinessException();
                        be.addError(ConstantesIntegracionWekan.ERROR_ACTUALIZAR_TARJETA);

                        throw be;
                    }
                }
            }

        } catch (DAOGeneralException ex) {

            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_GUARDAR, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_GUARDAR);

            throw be;
        }
    }

    public void eliminarVinculacionEntregable(Integer idEntregable) {

        TarjetaDAO tarjetaDAO = new TarjetaDAO(em);

        Tarjeta tarjeta = tarjetaDAO.obtenerPorIdEntregable(idEntregable);

        if (tarjeta == null) {

            return;
        }

        WekanApiClientWrapper apiClientWrapper = wekanBean.getAdminApi(tarjeta.getVinculacion());
        ApiClient apiClient = apiClientWrapper.getApiClient();
        CardsApi cardsApi = new CardsApi(apiClient);

        try {
            tarjetaBean.actualizarTarjeta(cardsApi, tarjeta, 0, tarjeta.getEntregable());

            actividadBean.eliminarActividadTarjeta(tarjeta.getId());

            tarjetaDAO.delete(tarjeta);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_TARJETA_ELIMINAR);

            throw be;
        }

    }

    private void comprobarUsuarioTablero(String idTablero, String idUsuario, ApiClient apiClient) throws BusinessException {

        LOGGER.log(Level.FINE, "obtener tablero - id:{0}", idTablero);

        BoardsApi boardsApi = new BoardsApi(apiClient);
        Board board;
        try {
            board = boardsApi.getBoard(idTablero);
            
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_OBTENER_TABLERO, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_OBTENER_TABLERO);

            throw be;
        }

        // combrobar usuario wekan
        boolean esMiembro = false;
        for (BoardMember member : board.getMembers()) {

            if (member.getUserId().equals(idUsuario)) {

                esMiembro = true;
                break;
            }
        }

        if (!esMiembro) {

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_USUARIO_NO_MIEMBRO_TABLERO);

            throw be;
        }
    }

    private Login comprobarUsuario(ApiClient apiClient, String nombreCorreoUsuario, String contrasenia) throws BusinessException {
        LoginApi loginApi = new LoginApi(apiClient);
        Login login;

        LOGGER.log(Level.FINE, "login Wekan - usuario:{0}", nombreCorreoUsuario);

        try {
            login = EmailValidator.validateEmail(nombreCorreoUsuario)
                    ? loginApi.login(contrasenia, null, nombreCorreoUsuario)
                    : loginApi.login(contrasenia, nombreCorreoUsuario, null);

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_LOGIN_USUARIO_WEKAN, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_LOGIN_USUARIO_WEKAN);
            ex.printStackTrace();

            throw be;
        }

        LOGGER.log(Level.INFO, "login Wekan - userid:{0}", login.getId());

        return login;
    }

    private Tablero comprobarEstadoTablero(String urlServidor, String idTablero, String urlTablero, ApiClient apiClient,
            String idUsuario) throws BusinessException, DAOGeneralException {

        TableroDAO tableroDAO = new TableroDAO(em);

        Tablero tablero = tableroDAO.obtenerPorIdTableroWekan(idTablero);

        if (tablero == null) {
            tablero = new Tablero();
            tablero.setIdTablero(idTablero);
            tablero.setUrlTablero(urlTablero);
            tablero.setUrlServidor(urlServidor);
        }
        comprobarEstadoIntegracion(tablero, apiClient);
        comprobarEstadoCampoProyecto(tablero, apiClient, idUsuario);
        comprobarEstadoCampoAvance(tablero, apiClient, idUsuario);
        comprobarEstadoLista(tablero, apiClient);
        comprobarEstadoCarril(tablero, apiClient);

        return tableroBean.guardarTableroNuevaTransaccion(tablero);
    }

    private void comprobarEstadoIntegracion(Tablero tablero, ApiClient apiClient) throws BusinessException, DAOGeneralException {

        // creacion integracion SIGES
        String wekanSIGESWebhook = obtenerURLWebhook();
        IntegrationsApi integrationsApi = new IntegrationsApi(apiClient);

        if (tablero.getIdIntegracion() != null) {
            return;
        }

        LOGGER.log(Level.FINE, "crear integracion SIGES");

        // creacion de la integracion
        IntegrationInline newIntegration;
        String token = UUID.randomUUID().toString();

        try {
            newIntegration = integrationsApi.newIntegration(tablero.getIdTablero(), wekanSIGESWebhook);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_CREAR_INTEGRACION, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_CREAR_INTEGRACION);

            throw be;
        }

        tablero.setIdIntegracion(newIntegration.getId());
        tablero.setToken(token);

        LOGGER.log(Level.FINE, "integracion creada - id:{0} token:{1}", new Object[]{tablero.getIdIntegracion(), tablero.getToken()});

        // setup de la integracion
        Integration integration = new Integration()
                .title(LabelsEJB.getValue(ConstantesIntegracionWekan.NOMBRE_INTEGRACION))
                .url(wekanSIGESWebhook)
                .enabled(true)
                .token(tablero.getToken())
                .addActivitiesItem(Activity.SET_CUSTOM_FIELD)
                .addActivitiesItem(Activity.UPDATE_START_AT)
                .addActivitiesItem(Activity.UPDATE_END_AT)
                .addActivitiesItem(Activity.MOVE_CARD)
                .type(IntegrationType.BIDIRECTIONAL_WEBHOOK);

        try {
            integrationsApi.editIntegration(tablero.getIdTablero(), tablero.getIdIntegracion(), integration);
        } catch (Exception ex) {

            tableroBean.guardarTableroNuevaTransaccion(tablero);

            LOGGER.log(Level.SEVERE, "wekan_error_editar_integracion", ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_EDITAR_INTEGRACION);

            throw be;
        }

        LOGGER.log(Level.FINE, "integracion actualizada");
    }

    private String obtenerURLWebhook() {

        String urlSistema = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.URL_SISTEMA, null);

        return configuracionBean
                .obtenerCnfValorPorCodigo(ConfiguracionCodigos.WEKAN_SIGES_WEBHOOK, null)
                .replaceAll('#' + ConfiguracionCodigos.URL_SISTEMA + '#', urlSistema);
    }

    private void comprobarEstadoCampoProyecto(Tablero tablero, ApiClient apiClient,
            String idUsuario) throws BusinessException, DAOGeneralException {

        if (tablero.getIdCampoProyecto() != null) {
            return;
        }

        LOGGER.log(Level.FINE, "crear campo proyecto");

        // creacion custom field avance
        CustomFieldsApi customFieldsApi = new CustomFieldsApi(apiClient);

        CustomField customField = new CustomField()
                .name(LabelsEJB.getValue(ConstantesIntegracionWekan.NOMBRE_CAMPO_PERSONALIZADO_PROYECTO))
                .type(CustomField.TypeEnum.NUMBER)
                .settings(new CustomFieldSetting())
                .showOnCard(true)
                .automaticallyOnCard(false)
                .showLabelOnMiniCard(true)
                .authorId(idUsuario);

        CustomFieldInline newCustomField;
        try {
            newCustomField = customFieldsApi.newCustomField(tablero.getIdTablero(), customField);

        } catch (Exception ex) {

            tableroBean.guardarTableroNuevaTransaccion(tablero);

            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_CREAR_CAMPO_PROYECTO, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_CREAR_CAMPO_PROYECTO);

            throw be;
        }

        tablero.setIdCampoProyecto(newCustomField.getId());

        LOGGER.log(Level.FINE, "campo proyecto creado - id:{0}", newCustomField.getId());
    }

    private void comprobarEstadoCampoAvance(Tablero tablero, ApiClient apiClient,
            String idUsuario) throws BusinessException, DAOGeneralException {

        if (tablero.getIdCampoAvance() != null) {
            return;
        }

        LOGGER.log(Level.FINE, "crear campo avance");

        // creacion custom field avance
        CustomFieldsApi customFieldsApi = new CustomFieldsApi(apiClient);

        CustomField customField = new CustomField()
                .name(LabelsEJB.getValue(ConstantesIntegracionWekan.NOMBRE_CAMPO_PERSONALIZADO_AVANCE))
                .type(CustomField.TypeEnum.NUMBER)
                .settings(new CustomFieldSetting())
                .showOnCard(true)
                .automaticallyOnCard(false)
                .showLabelOnMiniCard(true)
                .authorId(idUsuario);

        CustomFieldInline newCustomField;
        try {
            newCustomField = customFieldsApi.newCustomField(tablero.getIdTablero(), customField);

        } catch (Exception ex) {

            tableroBean.guardarTableroNuevaTransaccion(tablero);

            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_CREAR_CAMPO_AVANCE, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_CREAR_CAMPO_AVANCE);

            throw be;
        }

        tablero.setIdCampoAvance(newCustomField.getId());

        LOGGER.log(Level.FINE, "campo avance creado - id:{0}", newCustomField.getId());
    }

    private void comprobarEstadoLista(Tablero tablero, ApiClient apiClient) throws BusinessException, DAOGeneralException {

        if (tablero.getIdLista() != null) {
            return;
        }

        LOGGER.log(Level.FINE, "crear lista SIGES");

        ListsApi listsApi = new ListsApi(apiClient);
        ListInline newList;

        try {
            newList = listsApi.newList(tablero.getIdTablero(), LabelsEJB.getValue(ConstantesIntegracionWekan.NOMBRE_LISTA_ENTREGABLES));
        } catch (Exception ex) {
            tableroBean.guardarTableroNuevaTransaccion(tablero);

            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_CREAR_LISTA, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_CREAR_LISTA);

            throw be;
        }

        tablero.setIdLista(newList.getId());

        LOGGER.log(Level.FINE, "lista SIGES creada - id:{0}", newList.getId());
    }

    private void comprobarEstadoCarril(Tablero tablero, ApiClient apiClient) throws DAOGeneralException, BusinessException {

        if (tablero.getIdCarril() != null) {
            return;
        }

        LOGGER.log(Level.FINE, "obtener id. carril");

        SwimlanesApi swimlanesApi = new SwimlanesApi(apiClient);
        String swimlaneId;
        try {
            List<SwimlaneInline> allSwimlanes = swimlanesApi.getAllSwimlanes(tablero.getIdTablero());
            swimlaneId = allSwimlanes.get(0).getId();
        } catch (Exception ex) {
            tableroBean.guardarTableroNuevaTransaccion(tablero);

            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_OBTENER_CARRILES, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_OBTENER_CARRILES);

            throw be;
        }

        tablero.setIdCarril(swimlaneId);

        LOGGER.log(Level.FINE, "carril creado - id:{0}", swimlaneId);
    }

    private Tarjeta vincularEntregable(CardsApi cardsApi, String authorId, Vinculacion vinculacion,
            Entregables entregable) {

        LOGGER.log(Level.FINE, "crear tarjeta para entregable id:{0}", entregable.getEntPk());

        Tablero tablero = vinculacion.getTablero();

        Card card = new Card()
                .authorId(authorId)
                .members(new ArrayList<String>())
                .title(entregable.getEntNombre())
                .description(entregable.getEntDescripcion())
                .swimlaneId(tablero.getIdCarril());

        CardInline newCard;
        String entregablesSigesListId;
        try {
            entregablesSigesListId = obtenerIdListaEntregablesSiges(tablero, entregable.getEntCroFk().getCroPk(), entregable.getEntCroFk().getProyecto().getProyOrgFk().getOrgPk());

            newCard = cardsApi.newCard(tablero.getIdTablero(), entregablesSigesListId, card);
        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, ConstantesIntegracionWekan.ERROR_CREAR_TARJETA, ex);

            BusinessException be = new BusinessException();
            be.addError(ConstantesIntegracionWekan.ERROR_CREAR_TARJETA);

            throw be;
        }

        LOGGER.log(Level.FINE, "tarjeta creada id:{0}", newCard.getId());

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setIdTarjeta(newCard.getId());
        tarjeta.setIdLista(entregablesSigesListId);
        tarjeta.setFechaAlta(new Date());
        tarjeta.setEntregable(entregable);
        tarjeta.setVinculacion(vinculacion);
        tarjeta.setFechaAlta(new Date());

        return tarjeta;
    }

}
