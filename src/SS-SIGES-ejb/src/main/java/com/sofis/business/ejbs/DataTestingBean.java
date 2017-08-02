package com.sofis.business.ejbs;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Riesgos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoDocumentoInstancia;
import com.sofis.entities.enums.TipoFichaEnum;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.ejb3.annotation.TransactionTimeout;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "DataTestingBean")
@LocalBean
public class DataTestingBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @EJB
    private ProyectosBean proyectosBean;
    @EJB
    private AreasBean areasBean;
    @EJB
    private ProgramasBean programasBean;
    @EJB
    private ProgIndicesBean progIndicesBean; 
    @EJB
    private RiesgosBean riesgosBean;
    @EJB
    private TipoDocumentoInstanciaBean tipoDocumentoInstanciaBean;
    @EJB
    private PresupuestoBean presupuestoBean;
    @EJB
    private CronogramasBean cronogramasBean;

    @TransactionTimeout(value = 9999999999l)
    public void generarDatosTesting(int organismoId, Integer areaId, SsUsuario usuario) {
//        generarDatosTest(organismoId, areaId, usuario);
//        actualizarIndicadores();
    }

    public void generarDatosTest(int organismoId, Integer areaId, SsUsuario usuario) {

        /*EntityManagerFactory factory = Persistence.createEntityManagerFactory("SS-SIGES-PU-T");
         EntityManager emT = factory.createEntityManager();*/
        Areas area = areasBean.obtenerAreasPorPk(1);
        Organismos org = new Organismos(organismoId);

        //Proyectos huerfanos
        /*for (int x = 0; x < 50; x++) {
         logger.info("GUARDA HUERFANO");
         generarDatosTestingProyecto(null,org, area, usuario, x, null);
         logger.info("FIN HUERFANO");
         }*/
        //Programas con Proyectos
        for (int y = 0; y < 20; y++) {
            logger.info("GUARDA PROG");
            generarDatosTestingPrograma(null, y, org, area, usuario, 15);

            logger.info("FIN PROG");
        }
    }

    public void generarDatosTestingPrograma(EntityManager emT, int indice, Organismos org, Areas area, SsUsuario usuario, int cantProy) {

        Date d = new Date();
        Programas p = new Programas();

        p.setActivo(Boolean.TRUE);
        p.setProgNombre("Nombre -" + d.getTime());
        p.setProgAreaFk(area);
        p.setProgOrgFk(org);
        p.setProgEstFk(new Estados(Estados.ESTADOS.INICIO.estado_id));
        p.setProgUsrAdjuntoFk(usuario);
        p.setProgUsrGerenteFk(usuario);
        p.setProgUsrPmofedFk(usuario);
        p.setProgUsrSponsorFk(usuario);
        p.setProgSemaforoAmarillo(15);
        p.setProgSemaforoRojo(30);
        p.setProgObjPublico("aaa");
        p.setProgObjetivo("afdff");
        p.setAreasRestringidasSet(new HashSet<Areas>());
        p.setAreasTematicasSet(new HashSet<AreasTags>());

        p = programasBean.guardarPrograma(p, usuario, org.getOrgPk());

        progIndicesBean.guardarIndicadoresNewTrans(p.getProgPk(), org.getOrgPk());

        for (int x = 0; x < cantProy; x++) {
            generarDatosTestingProyecto(emT, org, area, usuario, x, p);
        }
    }

    public void generarDatosTestingProyecto(EntityManager emT, Organismos org, Areas area, SsUsuario usuario, int x, Programas prog) {
        Proyectos p = new Proyectos();
        p.setProyAreaFk(area);
        p.setProyNombre("Testing " + x);
        p.setActivo(Boolean.TRUE);
        p.setProyOrgFk(org);
        p.setProyEstFk(new Estados(Estados.ESTADOS.PLANIFICACION.estado_id));

        p.setProyUsrAdjuntoFk(usuario);
        p.setProyUsrGerenteFk(usuario);
        p.setProyUsrPmofedFk(usuario);
        p.setProyUsrSponsorFk(usuario);
        p.setProyPeso(10);
        p.setProySemaforoAmarillo(15);
        p.setProySemaforoRojo(30);
        p.setProyObjPublico("aaa");
        p.setProyObjetivo("afdff");
        p.setProySituacionActual("asdasd");
        p.setAreasRestringidasSet(new HashSet<Areas>());
        p.setAreasTematicasSet(new HashSet<AreasTags>());

        if (prog != null) {
            p.setProyProgFk(prog);
        }

        p = proyectosBean.guardarProyecto(p, usuario, org.getOrgPk());

        logger.info("PROYECTO " + p);
        if (p == null) {
            return;
        }

        //la lista de riesgos
        for (int y = 0; y < 40; y++) {
            Riesgos r = new Riesgos();
            r.setRiskNombre("Riesgo " + x);
            r.setRiskFechaActualizacion(new Date());
            r.setRiskFechaLimite(new Date());
            r.setRiskSuperado(false);
            r.setRiskProyFk(p);
            r.setRiskProbabilidad(40);
            r.setRiskImpacto(10);
            riesgosBean.guardar(r, org.getOrgPk());
        }

        //crea los tipos de documentos para la ficha
        List<TipoDocumentoInstancia> tipoDocInstancia
                = tipoDocumentoInstanciaBean.obtenerTipoDocsInstanciaPorProgProyId(p.getProyPk(), TipoFichaEnum.PROYECTO.id, org.getOrgPk());

        HashSet documentosSet = new HashSet();

        logger.info("INICIA DOC " + p);
        //la lista de documentos
        for (int y = 0; y < 30; y++) {
            Documentos r = new Documentos();
            r.setDocsFecha(new Date());
            r.setDocsNombre("Documento " + y);
            r.setDocsPrivado(false);
            r.setDocsTipo(tipoDocInstancia.get(0));
            r.setDocsEstado(0.5d);
            documentosSet.add(r);
        }

        logger.info("FIN DOC " + p);
        p.setDocumentosSet(documentosSet);

        p = proyectosBean.guardarProyecto(p, usuario, org.getOrgPk());
        //p = emT.merge(p);

        logger.info("INICIA CRO " + p);

        Cronogramas cro = new Cronogramas();
        cro.setCroEntSeleccionado(0);
        cro.setCroPermisoEscritura(true);
        cro.setCroPermisoEscrituraPadre(true);
        cro.setEntregablesSet(new HashSet<Entregables>());

        //Entregalbe de nivel 0
        Entregables ent = new Entregables();
        ent.setEntCodigo("Entregable " + 0);
        ent.setEntCroFk(cro);
        ent.setEntDescripcion("Desc " + 0);
        ent.setEntDuracion(13);
        ent.setEntDuracionLineaBase(13);
        ent.setEntEsfuerzo(20);
        ent.setEntFin(1398308399999L);
        ent.setEntFinEsHito(false);
        ent.setEntFinLineaBase(1398308399999L);
        ent.setEntId(0);
        ent.setEntInicio(1396839600000L);
        ent.setEntInicioEsHito(false);
        ent.setEntInicioLineaBase(1396839600000L);
        ent.setEntNivel(0);
        ent.setEntNombre("Nombre " + 0);
        ent.setEntProgreso(30);
        ent.setEntStatus("STATUS_ACTIVE");
        ent.setEntCollapsed(false);
        cro.getEntregablesSet().add(ent);

        for (int i = 1; i < 40; i++) {
            //Entregalbe de nivel 1
            Entregables ent1 = new Entregables();
            ent1.setEntCodigo("Entregable " + i);
            ent1.setEntCroFk(cro);
            ent1.setEntDescripcion("Desc " + i);
            ent1.setEntDuracion(4);
            ent1.setEntDuracionLineaBase(4);
            ent1.setEntEsfuerzo(10);
            ent1.setEntFin(1398308399999L);
            ent1.setEntFinEsHito(false);
            ent1.setEntFinLineaBase(1398308399999L);
            ent1.setEntId(i);
            ent1.setEntInicio(1397790000000L);
            ent1.setEntInicioEsHito(false);
            ent1.setEntInicioLineaBase(1397790000000L);
            ent1.setEntNivel(0);
            ent1.setEntNombre("Nombre " + i);
            ent1.setEntProgreso(30);
            ent1.setEntStatus("STATUS_ACTIVE");
            ent1.setEntCollapsed(false);
            cro.getEntregablesSet().add(ent1);
        }

        Proyectos pGu = (Proyectos) cronogramasBean.guardarCronograma(cro, p.getProyPk(), TipoFichaEnum.PROYECTO.id, usuario, org.getOrgPk());

        logger.info("FIN CRO " + p);

        logger.info("INICIA PRE " + p);
        //Presupuestos
        Presupuesto pre = new Presupuesto();
        pre.setFuenteFinanciamiento(new FuenteFinanciamiento(1));
        pre.setPreBase(10000D);
        pre.setPreMoneda(new Moneda(1));
        pre.setAdquisicionSet(new HashSet<Adquisicion>());

        pGu = (Proyectos) presupuestoBean.guardarPresupuesto(pre, p.getProyPk(), TipoFichaEnum.PROYECTO.id, usuario, org.getOrgPk());

        HashSet adqSet = new HashSet();

        for (int z = 0; z < 6; z++) {
            Adquisicion adq = new Adquisicion();
            adq.setAdqMoneda(new Moneda(1));
            adq.setAdqFuente(new FuenteFinanciamiento(1));
            adq.setAdqNombre("Nombre " + z);
            adq.setAdqPreFk(pGu.getProyPreFk());
            adq.setAdqProvOrga(new OrganiIntProve(1));

            HashSet pagosSet = new HashSet();
            for (int zz = 0; zz < 10; zz++) {
                Pagos pag = new Pagos();
                Entregables entTre = pGu.getProyCroFk().getEntregablesSet().iterator().next();
                pag.setEntregables(entTre);
                pag.setPagAdqFk(adq);
                pag.setPagFechaPlanificada(new Date());
                pag.setPagFechaReal(new Date());
                pag.setPagImportePlanificado(100d);
                pag.setPagImporteReal(90d);

                pagosSet.add(pag);
            }
            adq.setPagosSet(pagosSet);

            adqSet.add(adq);
        }

        logger.info("FIN PRE " + p);
        pre.getAdquisicionSet().addAll(adqSet);

        presupuestoBean.guardar(pre);

        proyectosBean.guardarIndicadores(p.getProyPk(), org.getOrgPk());
    }
}
