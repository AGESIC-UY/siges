package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.CategoriaProyectos;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Riesgos;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Named
@Stateless(name = "DBActualizacionBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class DBActualizacionBean {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    private static final String TABLE_CATEGORIA_PROYECTOS = "categoria_proyectos";
    private static final String TABLE_PROYECTOS = "proyectos";
    private static final String TABLE_PROY_OTROS_DATOS = "proy_otros_datos";
    private static final String TABLE_PROY_OTROS_CAT_SECUNDARIAS = "proy_otros_cat_secundarias";
    private static final String TABLE_MAILS_TEMPLATE = "mails_template";
    private static final String TABLE_CRONOGRAMAS = "cronogramas";
    private static final String TABLE_PROG_INDICES_PRE = "prog_indices_pre";
    private static final String TABLE_PROD_MES = "prod_mes";
    private static final String TABLE_DEVENGADO = "devengado";
    private static final String TABLE_AREAS = "areas";
    private static final String TABLE_ESTADOS = "estados";
    private static final String TABLE_ORGANISMOS = "organismos";
    private static final String TABLE_PROGRAMAS = "programas";
    private static final String TABLE_RIESGOS = "riesgos";
    private static final String TABLE_USUARIO = "ss_usuario";
    private static final String TABLE_LATLNG_PROYECTOS = "latlng_proyectos";
    private static final String TABLE_PRESUPUESTO = "presupuesto";
    private static final String TABLE_PROG_INDICES = "prog_indices";
    private static final String TABLE_PROY_INDICES = "proy_indices";
    private static final String TABLE_PROY_INDICES_PRE = "proy_indices_pre";
    private static final String TABLE_IMAGE = "image";
    private static final String TABLE_USU_OFI_ROLES = "ss_usu_ofi_roles";
    private static final String TABLE_ENTREGABLES = "entregables";
    private static final String TABLE_SQL_EXECUTED = "sql_executed";

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private ConfiguracionBean configuracionBean;
    @Inject
    private SqlExecutedBean sqlExecutedBean;
    @Inject
    private OrganismoBean organismoBean;
    @Inject
    private CategoriaProyectosBean categoriaProyectosBean;
    @Inject
    private MailsTemplateBean mailsTemplateBean;
    @Inject
    private NotificacionBean notificacionBean;
    @Inject
    private SsRolBean ssRolBean;
    @Inject
    private EstadosPublicacionBean estadosPublicacionBean;
    @Inject
    private EtapaBean etapaBean;
    @Inject
    private TiposMediaBean tiposMediaBean;
    @Inject
    private EstadosBean estadosBean;
    @Inject
    private TipoLeccionBean tipoLeccionBean;
    @Inject
    private EntregablesBean entregablesBean;
    @Inject
    private RiesgosBean riesgosBean;
    @Inject
    private ProyectosBean proyectosBean;

    private String database = null;
    private String schemaDB = null;

    @PostConstruct
    public void init() {
        database = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.DATABASE_ENGINE, null);
        schemaDB = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.DATABASE_SCHEMA, null);
    }

    private boolean isPostgresql() {
        return !StringsUtils.isEmpty(database) && database.equalsIgnoreCase(ConstanteApp.DB_POSTGRESQL);
    }

    private boolean isMysql() {
        return StringsUtils.isEmpty(database) || database.equalsIgnoreCase(ConstanteApp.DB_MYSQL);
    }

    private String getTable(String tablaName) {
        if (isPostgresql()) {
            return schemaDB + "." + tablaName;
        }
        return tablaName;
    }

    /**
     * Actualiza la Base de Datos con los cambios que sean necesarios segun la
     * versión.
     */
    public void actualizarDB() {
        try {
//            createSqlExecuted();
//            v4_4_0();
            v4_4_1();
//            v4_4_2();
            v4_4_4();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "ERROR al actualizar DB.", ex);
        }

        controlarDatosFaltantes();
        corregirDatos();
    }

    private boolean existTable(String schema, String tableName) throws SQLException {
        Session session = em.unwrap(Session.class);
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        Connection conn = cp.getConnection();

        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null, schema, tableName, null);
        if (rs != null && rs.next()) {
            return true;
        }
        return false;
    }

    private boolean existColumn(String schema, String tableName, String columnName) throws SQLException {
        Session session = em.unwrap(Session.class);
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        Connection conn = cp.getConnection();

        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getColumns(null, schema, tableName, columnName);
        if (rs != null && rs.next()) {
            return true;
        }
        return false;
    }

    private int executeUpdate(Map<Integer, String> queriesUpdate, int verMayor, int verMenor, int build) throws DAOGeneralException {
        if (queriesUpdate != null) {
            String version = String.valueOf(verMayor) + "." + String.valueOf(verMenor) + "-" + String.valueOf(build);
            logger.log(Level.INFO, "*** SQL Actualizando v{0}", new String[]{version});
            for (Integer key : queriesUpdate.keySet()) {
                if (!sqlExecutedBean.wasExecuted(verMayor, verMenor, build, key)) {
                    String queryStr = queriesUpdate.get(key);
                    logger.log(Level.INFO, "** SQL Ejecutando {0}({1}): {2}", new String[]{version, String.valueOf(key), queryStr});
                    Query query = em.createNativeQuery(queryStr);
                    query.executeUpdate();

                    sqlExecutedBean.addExecuted(verMayor, verMenor, build, key, queryStr);
                }
            }
            return 1;
        }
        return 0;
    }

    private void createSqlExecuted() throws SQLException {
        List<String> queriesUpdate = new ArrayList<>();
        if (isPostgresql() && !existTable(schemaDB, TABLE_SQL_EXECUTED)) {
            queriesUpdate.add("CREATE TABLE " + getTable(TABLE_SQL_EXECUTED) + " ("
                    + " sql_pk serial NOT NULL,"
                    + " sql_ver_mayor integer NOT NULL,"
                    + " sql_ver_menor integer NOT NULL,"
                    + " sql_build integer NOT NULL,"
                    + " sql_desc text NULL DEFAULT NULL,"
                    + " sql_fecha DATE NULL DEFAULT NULL,"
                    + " sql_update integer NOT NULL,"
                    + " PRIMARY KEY (sql_pk))");

            queriesUpdate.add("CREATE INDEX sql_ver_mayor_idx"
                    + "  ON " + getTable(TABLE_SQL_EXECUTED)
                    + "  USING btree"
                    + "  (sql_ver_mayor)");
            queriesUpdate.add("CREATE INDEX sql_ver_menor_idx"
                    + "  ON " + getTable(TABLE_SQL_EXECUTED)
                    + "  USING btree"
                    + "  (sql_ver_menor)");
            queriesUpdate.add("CREATE INDEX sql_build_idx"
                    + "  ON " + getTable(TABLE_SQL_EXECUTED)
                    + "  USING btree"
                    + "  (sql_build)");

        } else if (!existTable(null, TABLE_SQL_EXECUTED)) {
            queriesUpdate.add("CREATE TABLE " + getTable(TABLE_SQL_EXECUTED) + " ("
                    + "  sql_pk INT(11) NOT NULL AUTO_INCREMENT,"
                    + "  sql_ver_mayor INT(11) NOT NULL,"
                    + "  sql_ver_menor INT(11) NOT NULL,"
                    + "  sql_build INT(11) NOT NULL,"
                    + "  sql_desc TEXT NULL DEFAULT NULL,"
                    + "  sql_fecha DATE NULL DEFAULT NULL,"
                    + "  sql_update INT(11) NOT NULL,"
                    + "  PRIMARY KEY (sql_pk),"
                    + "  INDEX sql_ver_mayor (sql_ver_mayor ASC),"
                    + "  INDEX sql_ver_menor (sql_ver_menor ASC),"
                    + "  INDEX sql_build (sql_build ASC))");
        }

        if (queriesUpdate != null) {
            for (String query : queriesUpdate) {
                logger.log(Level.INFO, "Ejecutando: {0}", query);
                Query query1 = em.createNativeQuery(query);
                int resultUpdate = query1.executeUpdate();
            }
        }
    }

    public void controlarDatosFaltantes() {
        //mails_template
        mailsTemplateBean.controlarMailTmpFaltantes();

        //ss_configuraciones
        configuracionBean.controlarCnfFaltantes();

        //notificacion
        notificacionBean.controlarNotifFaltantes();

        //Roles
        ssRolBean.controlarRolesFaltantes();

        //Estados Publicacion
        estadosPublicacionBean.controlarEstPubFaltantes();

        //Etapas
        etapaBean.controlarEtapasFaltantes();

        //Tipos Multimedia
        tiposMediaBean.controlarTiposMediaFaltantes();

        //Estados
        estadosBean.controlarEstadosFaltantes();

        //Tipo Leccion(tipo_leccion)
        tipoLeccionBean.controlarTipoLeccionFaltantes();
    }

    private void agregarCategoriasPorOrg() {
        List<Organismos> listOrg = organismoBean.obtenerTodos();
        if (listOrg != null) {

            for (Organismos org : listOrg) {
                logger.info("Agregar Categorias para Org:" + org.getOrgPk());

                String consultacat = "SELECT cp.cat_proy_pk"
                        + " FROM " + getTable(TABLE_CATEGORIA_PROYECTOS) + " cp"
                        + " where cp.cat_org_fk = :orgPk";
                logger.log(Level.INFO, "Ejecutando: {0}", consultacat);

                Query queryCatOrg = em.createNativeQuery(consultacat);
                queryCatOrg.setParameter("orgPk", org.getOrgPk());
                List<Integer> resultCatProy = queryCatOrg.getResultList();

                if (resultCatProy == null || resultCatProy.isEmpty()) {
                    logger.info("No tiene categorias para este organismo. Se agregarán.");

                    String consultaCatOrigen = "SELECT cat_proy_pk"
                            + " FROM " + getTable(TABLE_CATEGORIA_PROYECTOS)
                            + " WHERE cat_org_fk is null";
                    logger.log(Level.INFO, "Ejecutando: {0}", consultaCatOrigen);

                    Query queryCatOrigen = em.createNativeQuery(consultaCatOrigen);
                    List<Integer> resultCatOrigen = queryCatOrigen.getResultList();

                    if (resultCatOrigen != null) {
                        for (Integer catPk : resultCatOrigen) {
                            CategoriaProyectos cp = categoriaProyectosBean.obtenerPorId(catPk);
                            if (cp != null) {
                                CategoriaProyectos cpNew = new CategoriaProyectos();
                                cpNew.setCatIcono(cp.getCatIcono());
                                cpNew.setCatIconoMarker(cp.getCatIconoMarker());
                                cpNew.setCatOrgFk(org);
                                cpNew.setCatProyActivo(cp.getCatProyActivo());
                                cpNew.setCatProyCodigo(cp.getCatProyCodigo());
                                cpNew.setCatProyNombre(cp.getCatProyNombre());
                                cpNew.setCatTipo(cp.getCatTipo());
                                categoriaProyectosBean.guardar(cpNew);
                            }
                        }
                    }
                }
            }
        }
    }

    private void actualizarCategoriasProy() {
        List<Organismos> orgs = organismoBean.obtenerTodos();
        if (orgs != null) {
            for (Organismos org : orgs) {
                Integer orgPk = org.getOrgPk();
                logger.info("Actualizar Categorias Org:" + orgPk);

                // Actualizar Categorias por las nuevas
                String consultaCatViejas = "SELECT DISTINCT o.proy_otr_cat_fk"
                        + " FROM " + getTable(TABLE_PROYECTOS) + " as p, " + getTable(TABLE_PROY_OTROS_DATOS) + " as o, " + getTable(TABLE_CATEGORIA_PROYECTOS) + " as cp"
                        + " WHERE p.proy_otr_dat_fk = o.proy_otr_pk"
                        + " AND p.proy_org_fk = :orgPk"
                        + " AND cp.cat_proy_pk = o.proy_otr_cat_fk"
                        + " AND cp.cat_org_fk IS NULL";
                logger.log(Level.INFO, "Ejecutando: {0}", consultaCatViejas);

                Query query = em.createNativeQuery(consultaCatViejas);
                query.setParameter("orgPk", orgPk);
                List<Integer> resultCatViejas = query.getResultList();

                if (resultCatViejas != null && !resultCatViejas.isEmpty()) {
                    logger.info("Cat. pendientes:" + resultCatViejas.size());
                    for (Integer catFk : resultCatViejas) {
                        if (catFk != null) {
                            logger.info("Actualizando Proy con Categoria:" + catFk);

                            String consultaCatOrigen = "SELECT cat_proy_codigo"
                                    + " FROM " + getTable(TABLE_CATEGORIA_PROYECTOS)
                                    + " WHERE cat_org_fk IS NULL"
                                    + " AND cat_proy_pk = :catFk";
                            logger.log(Level.INFO, "Ejecutando: {0}", consultaCatOrigen);

                            Query queryCatOrigen = em.createNativeQuery(consultaCatOrigen);
                            queryCatOrigen.setParameter("catFk", catFk);
                            List<String> resultCatOrigen = queryCatOrigen.getResultList();
                            String catCodOrigen = null;
                            if (resultCatOrigen != null && resultCatOrigen.size() == 1) {
                                catCodOrigen = resultCatOrigen.get(0);
                            }

                            if (catCodOrigen != null) {
                                String consultaCatDestino = "SELECT cat_proy_pk"
                                        + " FROM " + getTable(TABLE_CATEGORIA_PROYECTOS)
                                        + " WHERE cat_org_fk = :orgPk"
                                        + " AND cat_proy_codigo = :catCod";
                                logger.log(Level.INFO, "Ejecutando: {0}", consultaCatDestino);

                                Query queryCatDestino = em.createNativeQuery(consultaCatDestino);
                                queryCatDestino.setParameter("orgPk", orgPk);
                                queryCatDestino.setParameter("catCod", catCodOrigen);
                                List<Integer> resultCatDestino = queryCatDestino.getResultList();
                                Integer catPkDestino = null;
                                if (resultCatDestino != null && resultCatDestino.size() == 1) {
                                    catPkDestino = resultCatDestino.get(0);
                                }

                                if (catPkDestino != null) {
                                    String updateCatViejas = null;
                                    if (isPostgresql()) {
                                        updateCatViejas = "UPDATE " + getTable(TABLE_PROY_OTROS_DATOS)
                                                + " SET proy_otr_cat_fk = :catPkDestino"
                                                + " FROM " + getTable(TABLE_PROYECTOS) + " as p"
                                                + " WHERE p.proy_otr_dat_fk = proy_otr_pk"
                                                + " AND p.proy_org_fk = :orgPk"
                                                + " AND proy_otr_cat_fk = :catFk";
                                    } else {
                                        updateCatViejas = "UPDATE " + getTable(TABLE_PROY_OTROS_DATOS) + " as o"
                                                + " JOIN " + getTable(TABLE_PROYECTOS) + " as p"
                                                + " ON p.proy_otr_dat_fk = o.proy_otr_pk"
                                                + " SET o.proy_otr_cat_fk = :catPkDestino"
                                                + " WHERE p.proy_org_fk = :orgPk"
                                                + " AND o.proy_otr_cat_fk = :catFk";
                                    }
                                    logger.log(Level.INFO, "Ejecutando: {0}", updateCatViejas);

                                    Query queryUpdate = em.createNativeQuery(updateCatViejas);
                                    queryUpdate.setParameter("orgPk", orgPk);
                                    queryUpdate.setParameter("catPkDestino", catPkDestino);
                                    queryUpdate.setParameter("catFk", catFk);
                                    int resultUpdate = queryUpdate.executeUpdate();
                                }
                            }
                        }
                    }

                    List<Integer> resultProy = proySinActualizarCP(orgPk);
                    if (resultProy != null && resultProy.size() > 0) {
                        logger.info("No se actualizaron " + resultProy.size() + " Proyectos");
                    } else {
                        logger.info("Se actualizaron todos los proyectos para el Organismo " + orgPk);
                    }
                }

                // Actualizar SubCategorias por las nuevas
                consultaCatViejas = "SELECT DISTINCT c.proy_cat_cat_proy_fk"
                        + " FROM " + getTable(TABLE_PROYECTOS) + " as p, "
                        + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " as c, "
                        + getTable(TABLE_CATEGORIA_PROYECTOS) + " as cp"
                        + " WHERE p.proy_otr_dat_fk = c.proy_cat_proy_otros_fk"
                        + " AND cp.cat_proy_pk = c.proy_cat_cat_proy_fk "
                        + " AND p.proy_org_fk = :orgPk "
                        + " AND cp.cat_org_fk IS NULL";
                logger.log(Level.INFO, "Ejecutando: {0}", consultaCatViejas);

                Query queryCatSec = em.createNativeQuery(consultaCatViejas);
                queryCatSec.setParameter("orgPk", orgPk);
                List<Integer> resultCatSecViejas = queryCatSec.getResultList();

                if (resultCatSecViejas != null && !resultCatSecViejas.isEmpty()) {
                    logger.info("Cat. Sec. pendientes:" + resultCatSecViejas.size());
                    for (Integer catFk : resultCatSecViejas) {
                        if (catFk != null) {
                            logger.info("Actualizando Proy con SubCategoria:" + catFk);

                            String consultaCatOrigen = "SELECT cat_proy_codigo"
                                    + " FROM " + getTable(TABLE_CATEGORIA_PROYECTOS)
                                    + " WHERE cat_org_fk IS NULL"
                                    + " AND cat_proy_pk = :catFk";
                            logger.log(Level.INFO, "Ejecutando: {0}", consultaCatOrigen);

                            Query queryCatOrigen = em.createNativeQuery(consultaCatOrigen);
                            queryCatOrigen.setParameter("catFk", catFk);
                            List<String> resultCatOrigen = queryCatOrigen.getResultList();
                            String catCodOrigen = null;
                            if (resultCatOrigen != null && resultCatOrigen.size() == 1) {
                                catCodOrigen = resultCatOrigen.get(0);
                            }

                            if (catCodOrigen != null) {
                                String consultaCatDestino = "SELECT cat_proy_pk"
                                        + " FROM " + getTable(TABLE_CATEGORIA_PROYECTOS)
                                        + " WHERE cat_org_fk = :orgPk"
                                        + " AND cat_proy_codigo = :catCod";
                                logger.log(Level.INFO, "Ejecutando: {0}", consultaCatDestino);

                                Query queryCatDestino = em.createNativeQuery(consultaCatDestino);
                                queryCatDestino.setParameter("orgPk", orgPk);
                                queryCatDestino.setParameter("catCod", catCodOrigen);
                                List<Integer> resultCatDestino = queryCatDestino.getResultList();
                                Integer catPkDestino = null;
                                if (resultCatDestino != null && resultCatDestino.size() == 1) {
                                    catPkDestino = resultCatDestino.get(0);
                                }

                                if (catPkDestino != null) {
                                    String updateCatViejas = null;
                                    if (isPostgresql()) {
                                        updateCatViejas = "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " as s "
                                                + " SET proy_cat_cat_proy_fk = :catPkDestino"
                                                + " FROM " + getTable(TABLE_PROY_OTROS_DATOS) + " as o,"
                                                + " " + getTable(TABLE_PROYECTOS) + " as p "
                                                + " WHERE o.proy_otr_pk = s.proy_cat_proy_otros_fk"
                                                + " AND p.proy_otr_dat_fk = o.proy_otr_pk"
                                                + " AND p.proy_org_fk = :orgPk"
                                                + " AND proy_cat_cat_proy_fk = :catFk";
                                    } else {
                                        updateCatViejas = "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " as s "
                                                + " JOIN " + getTable(TABLE_PROY_OTROS_DATOS) + " as o"
                                                + " ON o.proy_otr_pk = s.proy_cat_proy_otros_fk"
                                                + " JOIN " + getTable(TABLE_PROYECTOS) + " as p "
                                                + " ON p.proy_otr_dat_fk = o.proy_otr_pk"
                                                + " SET proy_cat_cat_proy_fk = :catPkDestino"
                                                + " WHERE p.proy_org_fk = :orgPk"
                                                + " AND proy_cat_cat_proy_fk = :catFk";
                                    }
                                    logger.log(Level.INFO, "Ejecutando: {0}", updateCatViejas);

                                    Query queryUpdate = em.createNativeQuery(updateCatViejas);
                                    queryUpdate.setParameter("orgPk", orgPk);
                                    queryUpdate.setParameter("catPkDestino", catPkDestino);
                                    queryUpdate.setParameter("catFk", catFk);
                                    int resultUpdate = queryUpdate.executeUpdate();
                                }
                            }
                        }
                    }
                    String consultaPendientes = "SELECT proy_pk"
                            + " FROM " + getTable(TABLE_PROYECTOS) + " as p, " + getTable(TABLE_PROY_OTROS_DATOS) + " as o, " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " as s"
                            + " where p.proy_otr_dat_fk = o.proy_otr_pk"
                            + " AND o.proy_otr_pk = s.proy_cat_proy_otros_fk"
                            + " AND proy_org_fk = :orgPk"
                            + " AND s.proy_cat_proy_otros_fk IN (SELECT cat_proy_pk FROM " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_org_fk is null)";
                    logger.log(Level.INFO, "Ejecutando: {0}", consultaPendientes);

                    Query queryPendientesSec = em.createNativeQuery(consultaPendientes);
                    queryPendientesSec.setParameter("orgPk", orgPk);
                    List<Integer> resultProy = queryPendientesSec.getResultList();
                    if (resultProy != null && resultProy.size() > 0) {
                        logger.info("No se actualizaron " + resultProy.size() + " Proyectos(Cat. Sec.)");
                    } else {
                        logger.info("Se actualizaron todos los proyectos(Cat. Sec.) para el Organismo " + orgPk);
                    }
                }
            }
        }
    }

    private List<Integer> proySinActualizarCP(Integer orgPk) {
        String consultaPendientes = "SELECT proy_pk"
                + " FROM " + getTable(TABLE_PROYECTOS) + " as p, " + getTable(TABLE_PROY_OTROS_DATOS) + " as o"
                + " where p.proy_otr_dat_fk = o.proy_otr_pk"
                + " AND proy_org_fk = :orgPk"
                + " AND o.proy_otr_cat_fk is not null"
                + " AND o.proy_otr_cat_fk IN (SELECT cat_proy_pk FROM " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_org_fk is null)";
        Query queryPendientes = em.createNativeQuery(consultaPendientes);
        queryPendientes.setParameter("orgPk", orgPk);
        List<Integer> resultProy = queryPendientes.getResultList();
        return resultProy;
    }

    private void v4_4_0() throws DAOGeneralException {
        int verMayor = 4;
        int verMenor = 4;
        int build = 0;
        if (ConstanteApp.isVersion(verMayor, verMenor, build)
                || ConstanteApp.isVersionMenor(verMayor, verMenor, build)) {

            Map<Integer, String> queriesUpdate = new HashMap<>();
            if (isPostgresql()) {
                queriesUpdate.put(1, "ALTER TABLE " + getTable(TABLE_PROY_INDICES_PRE)
                        + " ADD COLUMN proyindpre_anio double precision");
            } else {
                queriesUpdate.put(1, "ALTER TABLE " + getTable(TABLE_PROY_INDICES_PRE)
                        + " ADD COLUMN proyindpre_anio DOUBLE(11,2) NULL DEFAULT NULL");
            }

            executeUpdate(queriesUpdate, verMayor, verMenor, build);
        }
    }

    private void v4_4_1() throws DAOGeneralException {
        int verMayor = 4;
        int verMenor = 4;
        int build = 1;
        if (ConstanteApp.isVersion(verMayor, verMenor, build)
                || ConstanteApp.isVersionMenor(verMayor, verMenor, build)) {

            if (ConstanteApp.isVersion(verMayor, verMenor, build)
                    || ConstanteApp.isVersionMenor(verMayor, verMenor, build)) {

                Map<Integer, String> queriesUpdate = new HashMap<>();
                if (isPostgresql()) {
                    queriesUpdate.put(1, "CREATE TABLE " + getTable(TABLE_IMAGE) + " ("
                            + " image_pk serial NOT NULL,"
                            + " image_name character varying(45) NOT NULL,"
                            + " image_desc character varying(255) DEFAULT NULL,"
                            + " image_ext character varying(20) NOT NULL,"
                            + " image_blob bytea NOT NULL,"
                            + " image_tipo integer DEFAULT NULL,"
                            + " image_orden integer DEFAULT NULL,"
                            + " PRIMARY KEY (image_pk)"
                            + ")");

                    queriesUpdate.put(2, "ALTER TABLE " + getTable(TABLE_CATEGORIA_PROYECTOS)
                            + " ADD COLUMN cat_tipo integer NOT NULL DEFAULT 0,"
                            + " ADD COLUMN cat_icono integer NULL DEFAULT NULL,"
                            + " ADD COLUMN cat_icono_marker integer NULL DEFAULT NULL");

                    queriesUpdate.put(3, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_SALUD', cat_tipo='1' WHERE cat_proy_pk='1'");
                    queriesUpdate.put(4, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_EDUCATIVA', cat_tipo='1' WHERE cat_proy_pk='2'");
                    queriesUpdate.put(5, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_VIVIENDA', cat_tipo='1' WHERE cat_proy_pk='3'");
                    queriesUpdate.put(6, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_TERRESTRE', cat_tipo='1' WHERE cat_proy_pk='4'");
                    queriesUpdate.put(7, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_MARITIMO', cat_tipo='1' WHERE cat_proy_pk='5'");
                    queriesUpdate.put(8, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_AEREO', cat_tipo='1' WHERE cat_proy_pk='6'");
                    queriesUpdate.put(9, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_URBANA', cat_tipo='1' WHERE cat_proy_pk='7'");
                    queriesUpdate.put(10, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_SOCIAL', cat_tipo='1' WHERE cat_proy_pk='8'");
                    queriesUpdate.put(11, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_SEGURIDAD', cat_tipo='1' WHERE cat_proy_pk='9'");
                    queriesUpdate.put(12, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_ENERGETICA', cat_tipo='1' WHERE cat_proy_pk='10'");
                    queriesUpdate.put(13, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_TECNOLOGIA', cat_tipo='1' WHERE cat_proy_pk='11'");
                    queriesUpdate.put(14, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_SANIDAD', cat_tipo='1' WHERE cat_proy_pk='12'");
                    queriesUpdate.put(15, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_VARIOS', cat_tipo='1' WHERE cat_proy_pk='13'");
                    queriesUpdate.put(16, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SALUD', 'Salud', '1', '2')");
                    queriesUpdate.put(17, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_EDUCACION', 'Educativa', '1', '2')");
                    queriesUpdate.put(18, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_VIVIENDA', 'Viviendas', '1', '2')");
                    queriesUpdate.put(19, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_TERRESTRE', 'Transporte Terrestre', '1', '2')");
                    queriesUpdate.put(20, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_URBANA', 'Urbana', '1', '2')");
                    queriesUpdate.put(21, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SOCIAL', 'Social', '1', '2')");
                    queriesUpdate.put(22, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SEGURIDAD', 'Seguridad', '1', '2')");
                    queriesUpdate.put(23, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SANIDAD', 'Obras Sanitarias', '1', '2')");
                    queriesUpdate.put(24, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_VARIOS', 'Varios', '1', '2')");
                    queriesUpdate.put(25, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_MARITIMO', 'Transporte Maritimo', '1', '2')");
                    queriesUpdate.put(26, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_AEREO', 'Transporte Aereo', '1', '2')");
                    queriesUpdate.put(27, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_ENERGETICA', 'Energetica', '1', '2')");
                    queriesUpdate.put(29, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_TECNOLOGIA', 'Tecnologica', '1', '2')");

                    queriesUpdate.put(30, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_SALUD') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_SALUD')");
                    queriesUpdate.put(31, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_EDUCACION') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_VARIOS')");
                    queriesUpdate.put(32, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_VIVIENDA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_VIVIENDA')");
                    queriesUpdate.put(33, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_TERRESTRE') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_TERRESTRE')");
                    queriesUpdate.put(34, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_URBANA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_URBANA')");
                    queriesUpdate.put(35, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_SOCIAL') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_SOCIAL')");
                    queriesUpdate.put(36, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_SEGURIDAD') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_SEGURIDAD')");
                    queriesUpdate.put(37, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_SANIDAD') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_SANIDAD')");
                    queriesUpdate.put(38, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_VARIOS') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_VARIOS')");
                    queriesUpdate.put(39, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_MARITIMO') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_MARITIMO')");
                    queriesUpdate.put(40, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_AEREO') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_AEREO')");
                    queriesUpdate.put(41, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_ENERGETICA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_ENERGETICA')");
                    queriesUpdate.put(42, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_TECNOLOGIA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_TECNOLOGIA')");

                    queriesUpdate.put(43, "ALTER TABLE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " ADD COLUMN cat_org_fk integer NULL DEFAULT NULL");
                    queriesUpdate.put(44, "CREATE INDEX cat_org_fk_idx ON " + getTable(TABLE_CATEGORIA_PROYECTOS) + " USING btree(cat_org_fk)");

                } else {
                    queriesUpdate.put(1, "CREATE TABLE " + getTable(TABLE_IMAGE) + " ("
                            + " image_pk int(10) NOT NULL AUTO_INCREMENT,"
                            + " image_name varchar(45) NOT NULL,"
                            + " image_desc varchar(255) DEFAULT NULL,"
                            + " image_ext varchar(20) NOT NULL,"
                            + " image_blob mediumblob NOT NULL,"
                            + " image_tipo int(5) DEFAULT NULL,"
                            + " image_orden int(5) DEFAULT NULL,"
                            + " PRIMARY KEY (image_pk)"
                            + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8");

                    queriesUpdate.put(2, "ALTER TABLE " + getTable(TABLE_CATEGORIA_PROYECTOS)
                            + " ADD COLUMN cat_tipo INT(11) NOT NULL DEFAULT 0 AFTER cat_proy_activo,"
                            + " ADD COLUMN cat_icono INT(11) NULL DEFAULT NULL AFTER cat_tipo,"
                            + " ADD COLUMN cat_icono_marker INT(11) NULL DEFAULT NULL AFTER cat_icono,"
                            + " COMMENT = 'Categoria de los Proyectos. Principalmente para usar con el Visualizador.'");

                    queriesUpdate.put(3, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_SALUD', cat_tipo='1' WHERE cat_proy_pk='1'");
                    queriesUpdate.put(4, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_EDUCATIVA', cat_tipo='1' WHERE cat_proy_pk='2'");
                    queriesUpdate.put(5, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_VIVIENDA', cat_tipo='1' WHERE cat_proy_pk='3'");
                    queriesUpdate.put(6, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_TERRESTRE', cat_tipo='1' WHERE cat_proy_pk='4'");
                    queriesUpdate.put(7, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_MARITIMO', cat_tipo='1' WHERE cat_proy_pk='5'");
                    queriesUpdate.put(8, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_AEREO', cat_tipo='1' WHERE cat_proy_pk='6'");
                    queriesUpdate.put(9, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_URBANA', cat_tipo='1' WHERE cat_proy_pk='7'");
                    queriesUpdate.put(10, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_SOCIAL', cat_tipo='1' WHERE cat_proy_pk='8'");
                    queriesUpdate.put(11, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_SEGURIDAD', cat_tipo='1' WHERE cat_proy_pk='9'");
                    queriesUpdate.put(12, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_ENERGETICA', cat_tipo='1' WHERE cat_proy_pk='10'");
                    queriesUpdate.put(13, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_TECNOLOGIA', cat_tipo='1' WHERE cat_proy_pk='11'");
                    queriesUpdate.put(14, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_SANIDAD', cat_tipo='1' WHERE cat_proy_pk='12'");
                    queriesUpdate.put(15, "UPDATE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " SET cat_proy_codigo='TE_VARIOS', cat_tipo='1' WHERE cat_proy_pk='13'");
                    queriesUpdate.put(16, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SALUD', 'Salud', '1', '2')");
                    queriesUpdate.put(17, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_EDUCACION', 'Educativa', '1', '2')");
                    queriesUpdate.put(18, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_VIVIENDA', 'Viviendas', '1', '2')");
                    queriesUpdate.put(19, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_TERRESTRE', 'Transporte Terrestre', '1', '2')");
                    queriesUpdate.put(20, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_URBANA', 'Urbana', '1', '2')");
                    queriesUpdate.put(21, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SOCIAL', 'Social', '1', '2')");
                    queriesUpdate.put(22, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SEGURIDAD', 'Seguridad', '1', '2')");
                    queriesUpdate.put(23, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_SANIDAD', 'Obras Sanitarias', '1', '2')");
                    queriesUpdate.put(24, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_VARIOS', 'Varios', '1', '2')");
                    queriesUpdate.put(25, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_MARITIMO', 'Transporte Maritimo', '1', '2')");
                    queriesUpdate.put(26, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_AEREO', 'Transporte Aereo', '1', '2')");
                    queriesUpdate.put(27, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_ENERGETICA', 'Energetica', '1', '2')");
                    queriesUpdate.put(29, "INSERT INTO " + getTable(TABLE_CATEGORIA_PROYECTOS) + " (cat_proy_codigo, cat_proy_nombre, cat_proy_activo, cat_tipo) VALUES ('CA_TECNOLOGIA', 'Tecnologica', '1', '2')");

                    queriesUpdate.put(30, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_SALUD') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_SALUD')");
                    queriesUpdate.put(31, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_EDUCACION') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_VARIOS')");
                    queriesUpdate.put(32, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_VIVIENDA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_VIVIENDA')");
                    queriesUpdate.put(33, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_TERRESTRE') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_TERRESTRE')");
                    queriesUpdate.put(34, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_URBANA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_URBANA')");
                    queriesUpdate.put(35, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_SOCIAL') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_SOCIAL')");
                    queriesUpdate.put(36, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_SEGURIDAD') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_SEGURIDAD')");
                    queriesUpdate.put(37, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_SANIDAD') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_SANIDAD')");
                    queriesUpdate.put(38, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_VARIOS') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_VARIOS')");
                    queriesUpdate.put(39, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_MARITIMO') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_MARITIMO')");
                    queriesUpdate.put(40, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_AEREO') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_AEREO')");
                    queriesUpdate.put(41, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_ENERGETICA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_ENERGETICA')");
                    queriesUpdate.put(42, "UPDATE " + getTable(TABLE_PROY_OTROS_CAT_SECUNDARIAS) + " SET proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='CA_TECNOLOGIA') WHERE proy_cat_cat_proy_fk = (select cat_proy_pk from " + getTable(TABLE_CATEGORIA_PROYECTOS) + " where cat_proy_codigo='TE_TECNOLOGIA')");

                    queriesUpdate.put(43, "ALTER TABLE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " ADD COLUMN cat_org_fk INT(11) NULL DEFAULT NULL AFTER cat_icono_marker");
                    queriesUpdate.put(44, "ALTER TABLE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " ADD INDEX cat_org_fk_idx (cat_org_fk ASC)");
                }

//                executeUpdate(queriesUpdate, verMayor, verMenor, build);
                agregarCategoriasPorOrg();
                actualizarCategoriasProy();
            }
        }
    }

    private void v4_4_2() throws DAOGeneralException, SQLException {
        int verMayor = 4;
        int verMenor = 4;
        int build = 2;
        if (ConstanteApp.isVersion(verMayor, verMenor, build)
                || ConstanteApp.isVersionMenor(verMayor, verMenor, build)) {

            Map<Integer, String> queriesUpdate = new HashMap<>();
            if (isPostgresql()) {
                queriesUpdate.put(1, "ALTER TABLE " + getTable(TABLE_MAILS_TEMPLATE)
                        + " ALTER COLUMN mail_tmp_asunto TYPE character varying(200)");

                queriesUpdate.put(2, "update " + getTable(TABLE_CRONOGRAMAS) + " set cro_permiso_escritura = true where cro_permiso_escritura = false and cro_pk > 0");

                queriesUpdate.put(3, "ALTER TABLE " + getTable(TABLE_PROG_INDICES_PRE)
                        + " ADD COLUMN progindpre_anio double precision NULL DEFAULT NULL,"
                        + " ADD COLUMN progindpre_ac double precision NULL DEFAULT NULL,"
                        + " ADD COLUMN progindpre_pv double precision NULL DEFAULT NULL");

                queriesUpdate.put(4, "CREATE INDEX cat_tipo_idx ON " + getTable(TABLE_CATEGORIA_PROYECTOS) + " USING btree(cat_tipo)");

                queriesUpdate.put(5, "ALTER TABLE " + getTable(TABLE_PROD_MES)
                        + " ALTER COLUMN prodmes_mes TYPE smallint USING CASE WHEN prodmes_mes THEN 1 ELSE 0 END,"
                        + " ALTER COLUMN prodmes_anio TYPE smallint USING CASE WHEN prodmes_anio THEN 1 ELSE 0 END");

                queriesUpdate.put(6, "ALTER TABLE " + getTable(TABLE_DEVENGADO)
                        + " ALTER COLUMN dev_mes TYPE smallint USING CASE WHEN dev_mes THEN 1 ELSE 0 END,"
                        + " ALTER COLUMN dev_anio TYPE smallint USING CASE WHEN dev_anio THEN 1 ELSE 0 END");

                queriesUpdate.put(7, "ALTER TABLE " + getTable(TABLE_AREAS)
                        + " ADD COLUMN area_activo boolean DEFAULT TRUE");

                queriesUpdate.put(8, "CREATE INDEX area_activo_idx ON " + getTable(TABLE_AREAS) + " USING btree(area_activo)");

                queriesUpdate.put(9, "update " + getTable(TABLE_PROYECTOS) + " set proy_area_fk = "
                        + "(select area_pk from " + getTable(TABLE_AREAS) + " limit 1) where proy_area_fk not in"
                        + "(select area_pk from " + getTable(TABLE_AREAS) + ")");

                queriesUpdate.put(10, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_area_fk) REFERENCES " + getTable(TABLE_AREAS) + "(area_pk)");
                queriesUpdate.put(11, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_est_fk) REFERENCES " + getTable(TABLE_ESTADOS) + "(est_pk)");
                queriesUpdate.put(12, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_org_fk) REFERENCES " + getTable(TABLE_ORGANISMOS) + "(org_pk)");
                queriesUpdate.put(13, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_prog_fk) REFERENCES " + getTable(TABLE_PROGRAMAS) + "(prog_pk)");
                queriesUpdate.put(14, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_risk_fk) REFERENCES " + getTable(TABLE_RIESGOS) + "(risk_pk)");
                queriesUpdate.put(15, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_usr_adjunto_fk) REFERENCES " + getTable(TABLE_USUARIO) + "(usu_id)");
                queriesUpdate.put(16, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_usr_sponsor_fk) REFERENCES " + getTable(TABLE_USUARIO) + "(usu_id)");
                queriesUpdate.put(17, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_usr_pmofed_fk) REFERENCES " + getTable(TABLE_USUARIO) + "(usu_id)");
                queriesUpdate.put(18, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_est_pendiente_fk) REFERENCES " + getTable(TABLE_ESTADOS) + "(est_pk)");
                queriesUpdate.put(19, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_latlng_fk) REFERENCES " + getTable(TABLE_LATLNG_PROYECTOS) + "(latlng_pk)");
                queriesUpdate.put(20, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_otr_dat_fk) REFERENCES " + getTable(TABLE_PROY_OTROS_DATOS) + "(proy_otr_pk)");
                queriesUpdate.put(21, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_pre_fk) REFERENCES " + getTable(TABLE_PRESUPUESTO) + "(pre_pk)");
                queriesUpdate.put(22, "ALTER TABLE " + getTable(TABLE_PROYECTOS) + " ADD FOREIGN KEY (proy_proyindices_fk) REFERENCES " + getTable(TABLE_PROY_INDICES) + "(proyind_pk)");

                queriesUpdate.put(23, "update " + getTable(TABLE_USUARIO) + " set usu_oficina_por_defecto = "
                        + "(select area_pk from " + getTable(TABLE_AREAS) + " limit 1)"
                        + " where usu_oficina_por_defecto not in"
                        + "(select area_pk from " + getTable(TABLE_AREAS) + ")");

                queriesUpdate.put(24, "update " + getTable(TABLE_PROGRAMAS) + " set prog_area_fk = "
                        + "(select area_pk from " + getTable(TABLE_AREAS) + " limit 1)"
                        + " where prog_area_fk not in"
                        + "(select area_pk from " + getTable(TABLE_AREAS) + ")");

                queriesUpdate.put(25, "update " + getTable(TABLE_USU_OFI_ROLES) + " set usu_ofi_roles_usu_area = "
                        + "(select area_pk from " + getTable(TABLE_AREAS) + " limit 1)"
                        + " where usu_ofi_roles_usu_area not in"
                        + "(select area_pk from " + getTable(TABLE_AREAS) + ")");

                queriesUpdate.put(26, "ALTER TABLE " + getTable(TABLE_ENTREGABLES) + " ALTER COLUMN ent_fin_linea_base set DEFAULT 0");
                queriesUpdate.put(27, "ALTER TABLE " + getTable(TABLE_ENTREGABLES) + " ALTER COLUMN ent_inicio_linea_base set DEFAULT 0");

                queriesUpdate.put(28, "update " + getTable(TABLE_ENTREGABLES) + " set ent_fin_linea_base=0 where ent_fin_linea_base is null");
                queriesUpdate.put(29, "update " + getTable(TABLE_ENTREGABLES) + " set ent_inicio_linea_base=0 where ent_inicio_linea_base is null");

            } else {
                queriesUpdate.put(1, "ALTER TABLE " + getTable(TABLE_MAILS_TEMPLATE) + " CHANGE COLUMN mail_tmp_asunto mail_tmp_asunto VARCHAR(200) NOT NULL");

                queriesUpdate.put(2, "update " + getTable(TABLE_CRONOGRAMAS) + " set cro_permiso_escritura=1 where cro_permiso_escritura=0 and cro_pk>0");

                queriesUpdate.put(3, "ALTER TABLE " + getTable(TABLE_PROG_INDICES_PRE)
                        + " ADD COLUMN progindpre_anio DOUBLE(11,2) NULL DEFAULT NULL,"
                        + " ADD COLUMN progindpre_ac DOUBLE(11,2) NULL DEFAULT NULL,"
                        + " ADD COLUMN progindpre_pv DOUBLE(11,2) NULL DEFAULT NULL");

//                queriesUpdate.put(4, "ALTER TABLE " + getTable(TABLE_CATEGORIA_PROYECTOS) + " ADD INDEX cat_tipo_idx (cat_tipo ASC)");
                queriesUpdate.put(5, "ALTER TABLE " + getTable(TABLE_AREAS)
                        + " ADD COLUMN area_activo TINYINT NULL DEFAULT 1,"
                        + " ADD INDEX area_activo_idx (area_activo ASC)");
            }

            executeUpdate(queriesUpdate, verMayor, verMenor, build);
        }
    }

    private void v4_4_4() throws DAOGeneralException, SQLException {
        int verMayor = 4;
        int verMenor = 4;
        int build = 4;
        if (ConstanteApp.isVersion(verMayor, verMenor, build)
                || ConstanteApp.isVersionMenor(verMayor, verMenor, build)) {

            // Cargar fecha de act. para los riesgos que no lo tengan.
            String consultaRiesgosSinAct = "SELECT r.risk_pk"
                    + " FROM " + getTable(TABLE_RIESGOS) + " as r"
                    + " WHERE r.risk_fecha_actu IS NULL";
            logger.log(Level.INFO, "Ejecutando: {0}", consultaRiesgosSinAct);

            Query query = em.createNativeQuery(consultaRiesgosSinAct);
            List<Integer> resultRiesgosSinAct = query.getResultList();

            if (resultRiesgosSinAct != null) {
                Map<Integer, Proyectos> mapProy = new HashMap<>();
                for (Integer riePk : resultRiesgosSinAct) {
                    Riesgos r = riesgosBean.obtenerRiesgosPorId(riePk);
                    if (r.getRiskProyFk() != null) {
                        Integer proyPk = r.getRiskProyFk().getProyPk();
                        Proyectos proy = mapProy.get(proyPk);
                        if (proy == null) {
                            proy = proyectosBean.obtenerProyPorId(proyPk);
                            mapProy.put(proyPk, proy);
                        }
                        if (proy != null) {
                            r.setRiskFechaActualizacion(proy.getProyFechaCrea());
                            riesgosBean.guardar(r, null);
                        }
                    }
                }
            }
        }
    }

    private void corregirDatos() {
        //Agregarle Parent a los Entregables que no lo tengan definido.
        List<Integer> proysPk = entregablesBean.proyPkEntSinParent();
        if (proysPk != null) {
            for (Integer proyPk : proysPk) {
                List<Entregables> listEnt = entregablesBean.obtenerEntPorProyPk(proyPk);
                listEnt = EntregablesUtils.marcarPadres(listEnt);
                entregablesBean.guardar(listEnt);
            }
        }
    }

}
