package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.data.daos.EntregablesDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.ProdMes;
import com.sofis.entities.data.Productos;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroMisTareasTO;
import com.sofis.entities.tipos.MisTareasProgProyTO;
import com.sofis.entities.tipos.MisTareasTO;
import com.sofis.entities.tipos.ReporteAcumuladoMesTO;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.sofis.business.interceptors.annotations.WekanActualizacionProgreso;
import com.sofis.data.daos.wekan.TarjetaDAO;

@Named
@Stateless(name = "EntregablesBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class EntregablesBean {

    private static final Logger LOGGER = Logger.getLogger(EntregablesBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @EJB
    private ProductosBean productosBean;

    @EJB
    private ProyectosBean proyectosBean;

    @Inject
    private ConfiguracionBean configuracionBean;

    public Entregables obtenerEntPorPk(Integer entPk) {
        return obtenerEntPorPk(entPk, false, false, false, false, false);
    }

    public Entregables obtenerEntPorPk(Integer entPk, boolean cargarProd, boolean cargarHistLB, boolean cargarDoc, boolean cargarPart, boolean cargarRegHoras) {
        EntregablesDAO dao = new EntregablesDAO(em);
        try {
            Entregables result = dao.findById(Entregables.class, entPk);

            if (cargarProd) {
                result.getEntProductosSet().isEmpty();
            }
            if (cargarHistLB) {
                result.getEntHistLBSet().isEmpty();
            }
            if (cargarPart) {
                result.getEntParticipantesSet().isEmpty();
            }
            if (cargarRegHoras) {
                result.getEntRegistrosHorasSet().isEmpty();
            }

            return result;
        } catch (DAOGeneralException ex) {
            Logger.getLogger(EntregablesBean.class.getName()).log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ENTREGABLES_OBTENER);
            throw be;
        }
    }

    public List<Entregables> obtenerEntPorProyPk(Integer proyPk) {
        EntregablesDAO dao = new EntregablesDAO(em);
        return dao.obtenerEntPorProyPk(proyPk);
    }

    public List<Entregables> obtenerEntSinReferenciaPorProyPk(Integer proyPk) {
        EntregablesDAO dao = new EntregablesDAO(em);
        List<Entregables> lista = dao.obtenerEntSinReferenciaPorProyPk(proyPk);

        for (Entregables e : lista) {
            e.getEntProductosSet().isEmpty();
        }
        return lista;

    }

    public List<Entregables> obtenerEntPorProgPk(Integer progPk, Boolean proyActivo) {
        EntregablesDAO dao = new EntregablesDAO(em);
        return dao.obtenerEntPorProgPk(progPk, proyActivo);
    }

    /**
     * Controla si está siendo usado por RegistrosHoras, Pagos, Participantes,
     * Documentos, Riesgos, Calidad o Interesados.
     *
     * @param entPk
     * @return boolean
     */
    public boolean tieneDependencias(Integer entPk) {
        EntregablesDAO dao = new EntregablesDAO(em);
        return dao.tieneDependencias(entPk);
    }

    public boolean tieneVinculacionWekan(Integer entPk) {
        TarjetaDAO dao = new TarjetaDAO(em);
        return (dao.obtenerPorIdEntregable(entPk) != null);
    }

    public Entregables guardar(Entregables ent) {
        EntregablesDAO dao = new EntregablesDAO(em);
        try {
            return dao.update(ent);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(EntregablesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Entregables> guardar(List<Entregables> listEnt) {
        if (listEnt != null) {
            List<Entregables> result = new ArrayList<>();
            for (Entregables ent : listEnt) {
                result.add(guardar(ent));
            }
            return result;
        }
        return null;
    }

    public List<Entregables> obtenerEntPorCoord(Integer proyPk, Integer coord, Boolean conHitos) {
        try {
            if (proyPk != null) {
                EntregablesDAO dao = new EntregablesDAO(em);
                return dao.obtenerEntregablesPorCoord(proyPk, coord, conHitos);
            }

            return null;

        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    /**
     * Calcular el avance de un entregable según los productos que tenga
     * asociados.
     *
     * @param entPk
     * @return Double
     */
    public Double calcularAvanceEntPorProd(Integer entPk) {
        LOGGER.log(Level.FINEST, "calcularAvanceEntPorProd...");

        if (entPk == null) {
            return null;
        }

        List<Productos> listProd = productosBean.obtenerProdPorEnt(entPk);

        if (CollectionsUtils.isEmpty(listProd)) {
            return null;
        }

        Calendar cal = new GregorianCalendar();
        int pesoPlanTotal = 0;
        double avancePesoTotal = 0;

        for (Productos prod : listProd) {
            double totalPlan = 0;
            double avanceReal = 0;

            for (ProdMes prodMes : prod.getProdMesList()) {
                if (prodMes.getProdmesReal() != null) {
                    if (prodMes.getProdmesAnio() < cal.get(Calendar.YEAR)
                            || (prodMes.getProdmesAnio() == cal.get(Calendar.YEAR)
                            && prodMes.getProdmesMes() <= cal.get(Calendar.MONTH) + 1)) {
                        avanceReal += prodMes.getProdmesReal();
                    }
                }
                totalPlan += prodMes.getProdmesPlan();
            }
            pesoPlanTotal += totalPlan * prod.getProdPeso();

            // Si el avance real de un producto super al avance planificado, tomo el planificado.
            // Esto es para que el porcentaje de avance de un producto no sea mayor al 100%.
            // Si el avance real es negativo, tomo 0. porque el avance acumulado real de los productos no puede ser 0.
            avanceReal = avanceReal < 0 ? 0 : avanceReal;
            avancePesoTotal += (avanceReal > totalPlan) ? totalPlan * prod.getProdPeso() : avanceReal * prod.getProdPeso();
        }

        return (pesoPlanTotal > 0) ? (avancePesoTotal * 100 / pesoPlanTotal) : 0d;
    }

    public void guardarAvanceReal(Integer entPk) {

        Entregables entregable = obtenerEntPorPk(entPk);

        if (entregable == null) {
            return;
        }

        Double avance = calcularAvanceEntPorProd(entPk);
        int progreso = 0;

        if (avance != null) {
            progreso = (int) Math.round(avance);
            progreso = progreso > 100 ? 100 : progreso;
        }

        entregable.setEntProgreso(progreso);

        guardar(entregable);

        List<Entregables> entregables = obtenerEntPorProyPk(entregable.getEntCroFk().getProyecto().getProyPk());

        EntregablesUtils.asignarProgresoEsfuerzoPadres(entregables);
    }

    /*
		retorna la copia de los entregables asociados al id original
     */
    public Map<Integer, Entregables> copiarProyEntregables(Set<Entregables> entregablesSet, Cronogramas nvoCro, int desfasajeDias) {

        Map<Integer, Entregables> result = new HashMap<>();

        if (CollectionsUtils.isNotEmpty(entregablesSet) && nvoCro != null) {

            if (nvoCro.getEntregablesSet() == null) {
                nvoCro.setEntregablesSet(new HashSet<Entregables>());
            }

            for (Entregables ent : entregablesSet) {
                Entregables nvoEnt = new Entregables();
                nvoEnt.setCoordinadorUsuFk(ent.getCoordinadorUsuFk());
                nvoEnt.setEntAssigs(ent.getEntAssigs());
                nvoEnt.setEntCodigo(ent.getEntCodigo());
                nvoEnt.setEntCollapsed(ent.getEntCollapsed());
                nvoEnt.setEntCroFk(nvoCro);
                nvoEnt.setEntDescripcion(ent.getEntDescripcion());
                nvoEnt.setEntDuracion(ent.getEntDuracion());
                nvoEnt.setEntDuracionLineaBase(0);
                nvoEnt.setEntEsfuerzo(ent.getEntEsfuerzo());
                nvoEnt.setEntFinEsHito(ent.getEntFinEsHito());
                nvoEnt.setEntHorasEstimadas(ent.getEntHorasEstimadas());
                nvoEnt.setEntId(ent.getEntId());
                nvoEnt.setEntInicioEsHito(ent.getEntInicioEsHito());
                nvoEnt.setEntNivel(ent.getEntNivel());
                nvoEnt.setEntNombre(ent.getEntNombre());
                nvoEnt.setEntPredecesorDias(ent.getEntPredecesorDias());
                nvoEnt.setEntPredecesorFk(ent.getEntPredecesorFk());
                nvoEnt.setEntProgreso(0);
                nvoEnt.setEntParent(ent.getEntParent());
                nvoEnt.setEntRelevante(ent.getEntRelevante());
                nvoEnt.setEntStatus(ent.getEntStatus());

                nvoEnt.setEntInicioProyecto(ent.getEntInicioProyecto());
                nvoEnt.setEntFinProyecto(ent.getEntFinProyecto());
                nvoEnt.setEsReferencia(Boolean.FALSE);

                Date date;
                if (ent.getEntFin() != null && ent.getEntFin() > 0) {
                    date = DatesUtils.incrementarDias(ent.getEntFinDate(), desfasajeDias);
                    nvoEnt.setEntFin(date.getTime());
                }
                if (ent.getEntInicio() != null && ent.getEntInicio() > 0) {
                    date = DatesUtils.incrementarDias(ent.getEntInicioDate(), desfasajeDias);
                    nvoEnt.setEntInicio(date.getTime());
                }

                nvoEnt.setEntProductosSet(productosBean.copiarProyProductos(ent, nvoEnt, desfasajeDias));

                nvoCro.getEntregablesSet().add(ent);

                result.put(ent.getEntPk(), nvoEnt);
            }
        }

        return result;
    }

    /**
     * Retorna un mapa con las fechas: primera, ultima, primeraLB, ultimaLB.
     *
     * @param entSet
     * @return Map
     */
    public Map<String, Date> obtenerPrimeraUltimaFecha(Collection<Entregables> entSet) {
        Map<String, Date> dates = new HashMap<>();
        if (CollectionsUtils.isNotEmpty(entSet)) {
            Date primera = null;
            Date ultima = null;
            Date primeraLB = null;
            Date ultimaLB = null;
            for (Entregables e : entSet) {
                if (e.getEntInicioDate() != null
                        && (primera == null || DatesUtils.esMayor(primera, e.getEntInicioDate()))) {
                    primera = e.getEntInicioDate();

                }
                if (e.getEntInicioLineaBaseDate() != null
                        && (primeraLB == null || DatesUtils.esMayor(primeraLB, e.getEntInicioLineaBaseDate()))) {
                    primeraLB = e.getEntInicioLineaBaseDate();
                }
                if (e.getEntFinDate() != null
                        && (ultima == null || DatesUtils.esMayor(e.getEntFinDate(), ultima))) {
                    ultima = e.getEntFinDate();
                }
                if (e.getEntFinLineaBaseDate() != null
                        && (ultimaLB == null || DatesUtils.esMayor(e.getEntFinLineaBaseDate(), ultimaLB))) {
                    ultimaLB = e.getEntFinLineaBaseDate();
                }
            }
            dates.put("primera", primera);
            dates.put("ultima", ultima);
            dates.put("primeraLB", primeraLB);
            dates.put("ultimaLB", ultimaLB);
        }
        return dates;
    }

    /**
     * Retorna la primera de todas las fechas de los entregables.
     *
     * @param entSet
     * @param periodoActivado: si la configuración para definir el período está
     * definida.
     * @return Date
     */
    public Date obtenerPrimeraFecha(Collection<Entregables> entSet, Boolean usarPeridoEntregable) {

        Date result = null;
        if (CollectionsUtils.isNotEmpty(entSet)) {
            for (Entregables e : entSet) {
                if (usarPeridoEntregable && e.getEntInicioProyecto() && e.getEntInicioDate() != null) {
                    return e.getEntInicioDate();
                }
                if (e.getEntInicioDate() != null
                        && (result == null || DatesUtils.esMayor(result, e.getEntInicioDate()))) {
                    result = e.getEntInicioDate();

                }
                if (e.getEntInicioLineaBaseDate() != null
                        && (result == null || DatesUtils.esMayor(result, e.getEntInicioLineaBaseDate()))) {
                    result = e.getEntInicioLineaBaseDate();
                }
            }
        }
        return result;
    }

    /**
     * Retorna la primer fecha de todos los entregables del organismo dado. Solo
     * evalua los entregables en inicio, planificacion, o ejecución.
     *
     * @param orgPk
     * @return
     */
    public Date obtenerPrimeraFecha(Integer orgPk) {
        EntregablesDAO dao = new EntregablesDAO(em);
        return dao.obtenerPrimeraFecha(orgPk);
    }

    /**
     * Retorna la ultima de todas las fechas de los entregables aportados.
     *
     * @param entregablesSet
     * @return Date
     */
    public Date obtenerUltimaFecha(Collection<Entregables> entregablesSet, Boolean usarPeridoEntregable) {
        Date result = null;
        if (CollectionsUtils.isNotEmpty(entregablesSet)) {
            for (Entregables e : entregablesSet) {
                if (usarPeridoEntregable && e.getEntFinProyecto() && e.getEntFinDate() != null) {
                    return e.getEntFinDate();
                }
                if (e.getEntFinDate() != null
                        && (result == null || DatesUtils.esMayor(e.getEntFinDate(), result))) {
                    result = e.getEntFinDate();
                }
                if (e.getEntFinLineaBaseDate() != null
                        && (result == null || DatesUtils.esMayor(e.getEntFinLineaBaseDate(), result))) {
                    result = e.getEntFinLineaBaseDate();
                }
            }
        }
        return result;
    }

    /**
     * Retorna la ultima fecha de todos los entregables del organismo dado. Solo
     * evalua los entregables en inicio, planificacion, o ejecución.
     *
     * @param orgPk
     * @return Date
     */
    public Date obtenerUltimaFecha(Integer orgPk) {
        EntregablesDAO dao = new EntregablesDAO(em);
        return dao.obtenerUltimaFecha(orgPk);
    }

    @WekanActualizacionProgreso
    public Entregables actualizarAvance(Integer proyPk, Integer entPk, Integer avance, SsUsuario usuario) {
        if (entPk != null && avance != null) {
            Entregables ent = obtenerEntPorPk(entPk);

            if (ent == null) {
                return null;
            }

            Proyectos proy = proyectosBean.obtenerProyPorId(proyPk);
            boolean isGerente = SsUsuariosUtils.isUsuarioGerenteOAdjuntoFicha(proy, usuario);
            if (isGerente || (ent.getCoordinadorUsuFk() != null && ent.getCoordinadorUsuFk().equals(usuario))) {
                ent.setEntProgreso(avance);

                ent = guardar(ent);

                List<Entregables> entregables = obtenerEntPorProyPk(proyPk);

                EntregablesUtils.asignarProgresoEsfuerzoPadres(entregables);

                proyectosBean.actualizarIndProyProg(proy, usuario, proy.getProyOrgFk().getOrgPk(), true);

                proyectosBean.actualizarFechaUltimaModificacion(proy);

                if (proy.getProyCroFk() != null) {
                    proyectosBean.postProcesarEntregablesRelacionados(proy.getProyCroFk(), null);
                }

                return ent;

            } else {
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_ENTREGABLE_AVANCE_COORD);
                throw be;
            }
        }

        return null;
    }

    /**
     *
     * @param listEnt
     * @return Map cuya clave es 5-2015, o sea mes-año.
     */
    public Map<String, ReporteAcumuladoMesTO> obtenerAcumuladoMapMes(List<Entregables> listEnt) {
        Map<String, ReporteAcumuladoMesTO> result = new HashMap<>();

        List<ReporteAcumuladoMesTO> acuMes = obtenerAcumuladoCroPorMes(listEnt, null);
        for (ReporteAcumuladoMesTO acu : acuMes) {
            String clave = acu.getMes() + "-" + acu.getAnio();
            result.put(clave, acu);
        }

        return result;
    }

    /**
     *
     * @param listEnt
     * @param orgPk
     * @return List
     */
    public List<ReporteAcumuladoMesTO> obtenerAcumuladoCroPorMes(List<Entregables> listEnt, Integer orgPk) {
        if (CollectionsUtils.isNotEmpty(listEnt)) {
            String codPlan = "plan";
            String codRealFinalizado = "realFin";
            String codRealParcial = "realPar";
            String codProyectadoAtrasadoFinalizado = "proyAtrasoFin";
            String codProyectadoAtrasadoParcial = "proyAtrasoPar";
            String codProyectadoFinalizado = "proyFin";
            String codProyectadoParcial = "proyPar";

            List<ReporteAcumuladoMesTO> result = new ArrayList<>();
            Map<String, Double> entPorMes = new HashMap<>();
            Calendar calNow = new GregorianCalendar();
            Calendar primera = null;
            Calendar ultima = null;
            Calendar cal;
            int mes;
            int anio;
            Double entPesoParcial;
            Double entPesoTotal;

            Integer limiteAmarilloAlcance = null;
            Integer limiteRojoAlcance = null;
            if (orgPk != null) {
                limiteAmarilloAlcance = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ALCANCE_INDICE_LIMITE_AMARILLO, orgPk).getCnfValor());
                limiteRojoAlcance = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ALCANCE_INDICE_LIMITE_ROJO, orgPk).getCnfValor());
            }

            List<Entregables> listE = EntregablesUtils.entregablesSinPadres(listEnt);

            double esfuerzoTotal = 0;
            boolean sinLineaBase = false;
            for (Entregables ent : listE) {
                esfuerzoTotal += ent.getEntEsfuerzo() != null ? ent.getEntEsfuerzo() : 0;
                if (!sinLineaBase && (ent.getEntInicioLineaBase() == null || ent.getEntFinLineaBase() == null
                        || ent.getEntInicioLineaBase() <= 0 || ent.getEntFinLineaBase() <= 0)) {
                    //Para saber si todos tienen linea base.
                    sinLineaBase = true;
                }
            }

            for (Entregables ent : listE) {
                cal = null;
                entPesoParcial = 0D;
                entPesoTotal = 0D;
                if (ent.getEntEsfuerzo() != null && ent.getEntProgreso() != null && esfuerzoTotal > 0) {
                    entPesoParcial = (ent.getEntEsfuerzo() * (ent.getEntProgreso() / 100.0)) * 100.0 / esfuerzoTotal;
                    //Peso total del entregable en el cronograma
                    entPesoTotal = ent.getEntEsfuerzo() * 100 / esfuerzoTotal;
                }

                if (sinLineaBase) {
                    if (ent.getEntFinDate() != null) {
                        cal = new GregorianCalendar();
                        cal.setTime(ent.getEntFinDate());
                    }
                } else if (ent.getEntFinLineaBaseDate() != null) {
                    cal = new GregorianCalendar();
                    cal.setTime(ent.getEntFinLineaBaseDate());
                }

                //Calculo lo Planificado
                if (cal != null) {
                    mes = cal.get(Calendar.MONTH) + 1;
                    anio = cal.get(Calendar.YEAR);

                    if (primera == null || DatesUtils.esMayor(primera.getTime(), cal.getTime())) {
                        primera = cal;
                    }
                    if (ultima == null || DatesUtils.esMayor(cal.getTime(), ultima.getTime())) {
                        ultima = cal;
                    }

                    String claveBase = codPlan + "-" + mes + "-" + anio;
                    entPorMes.put(claveBase, (entPorMes.containsKey(claveBase) ? entPorMes.get(claveBase) : 0) + entPesoTotal);
                }

                //Calculo lo real y lo proyectado.
                if (cal != null) {
                    if (ent.getEntInicioDate() != null && ent.getEntFinDate() != null) {
                        cal = new GregorianCalendar();
                        cal.setTime(ent.getEntFinDate());
                        mes = cal.get(Calendar.MONTH) + 1;
                        anio = cal.get(Calendar.YEAR);

                        if (primera == null || DatesUtils.esMayor(primera.getTime(), cal.getTime())) {
                            primera = cal;
                        }
                        if (ultima == null || DatesUtils.esMayor(cal.getTime(), ultima.getTime())) {
                            ultima = cal;
                        }

                        if (ent.getEntProgreso() >= 100) {
                            //100% Real Finalizado
                            String claveRealFinalizado = codRealFinalizado + "-" + mes + "-" + anio;
                            entPorMes.put(claveRealFinalizado, (entPorMes.containsKey(claveRealFinalizado) ? entPorMes.get(claveRealFinalizado) : 0) + entPesoParcial);
                            //100% Real Parcial
                            String claveRealParcial = codRealParcial + "-" + mes + "-" + anio;
                            entPorMes.put(claveRealParcial, (entPorMes.containsKey(claveRealParcial) ? entPorMes.get(claveRealParcial) : 0) + entPesoParcial);
                        } else {
                            //Avance Finalizado
                            String claveProyFinalizado;
                            String claveProyParcial;

                            if (cal.before(calNow)) {
                                //Proyectado atrasado 
                                claveProyFinalizado = codProyectadoAtrasadoFinalizado + "-" + mes + "-" + anio;
//				claveProyParcial = codProyectadoAtrasadoParcial + "-" + calNow.get(Calendar.MONTH) + "-" + calNow.get(Calendar.YEAR);
                                claveProyParcial = codProyectadoAtrasadoParcial + "-" + mes + "-" + anio;
                            } else {
                                //Proyectado >= hoy
                                claveProyFinalizado = codProyectadoFinalizado + "-" + mes + "-" + anio;
                                claveProyParcial = codProyectadoParcial + "-" + mes + "-" + anio;
                            }
                            entPorMes.put(claveProyFinalizado, (entPorMes.containsKey(claveProyFinalizado) ? entPorMes.get(claveProyFinalizado) : 0) + entPesoTotal);

                            //Avance Parcial
                            if (ent.getEntProgreso() == 0) {
                                //Proyectado 
                                entPorMes.put(claveProyParcial, (entPorMes.containsKey(claveProyParcial) ? entPorMes.get(claveProyParcial) : 0) + entPesoTotal);
                            } else {
                                //Real
//				String claveRealParcial = codRealParcial + "-" + calNow.get(Calendar.MONTH) + "-" + calNow.get(Calendar.YEAR);
                                String claveRealParcial = codRealParcial + "-" + mes + "-" + anio;
                                entPorMes.put(claveRealParcial, (entPorMes.containsKey(claveRealParcial) ? entPorMes.get(claveRealParcial) : 0) + entPesoParcial);

                                entPorMes.put(claveProyParcial, (entPorMes.containsKey(claveProyParcial) ? entPorMes.get(claveProyParcial) : 0) + (entPesoTotal - entPesoParcial));
                            }
                        }
                    }
                }
            }

            double baseAcu = 0;
            double realAcuFinalizado = 0;
            double realAcuParcial = 0;
            double proyAcuFinalizado = 0;
            double proyAcuParcial = 0;
            double proyAtrAcuFinalizado = 0;
            double proyAtrAcuParcial = 0;
            Double baseMes;
            Double realMes;
            Double proyMes;
            Double proyAtrMes;
            String claveBase;
            String claveRealFinalizado;
            String claveRealParcial;
            String claveProyFinalizado;
            String claveProyParcial;
            String claveProyAtrFinalizado;
            String claveProyAtrParcial;

            cal = new GregorianCalendar();
            int mesHoy = cal.get(Calendar.MONTH) + 1;
            int anioHoy = cal.get(Calendar.YEAR);

            int mesPrimera = primera.get(Calendar.MONTH);
            int anioPrimera = primera.get(Calendar.YEAR);
            int mesUltima = ultima.get(Calendar.MONTH);
            int anioUltima = ultima.get(Calendar.YEAR);

            while (anioUltima > anioPrimera || (anioUltima == anioPrimera && mesUltima >= mesPrimera)) {
                mes = mesPrimera + 1;
                anio = anioPrimera;
                claveBase = codPlan + "-" + mes + "-" + anio;
                claveRealFinalizado = codRealFinalizado + "-" + mes + "-" + anio;
                claveRealParcial = codRealParcial + "-" + mes + "-" + anio;
                claveProyFinalizado = codProyectadoFinalizado + "-" + mes + "-" + anio;
                claveProyParcial = codProyectadoParcial + "-" + mes + "-" + anio;
                claveProyAtrFinalizado = codProyectadoAtrasadoFinalizado + "-" + mes + "-" + anio;
                claveProyAtrParcial = codProyectadoAtrasadoParcial + "-" + mes + "-" + anio;

                ReporteAcumuladoMesTO repAcu = new ReporteAcumuladoMesTO((short) anio, (short) mes);

                baseMes = entPorMes.get(claveBase);
                baseAcu += (baseMes != null ? baseMes : 0);
                repAcu.setValorPlan(baseAcu);

                realMes = entPorMes.get(claveRealFinalizado);
                realAcuFinalizado += (realMes != null ? realMes : 0);
                realMes = entPorMes.get(claveRealParcial);
                realAcuParcial += (realMes != null ? realMes : 0);

                proyMes = entPorMes.get(claveProyFinalizado);
                proyAcuFinalizado += (proyMes != null ? proyMes : 0);
                proyMes = entPorMes.get(claveProyParcial);
                proyAcuParcial += (proyMes != null ? proyMes : 0);

                proyAtrMes = entPorMes.get(claveProyAtrFinalizado);
                proyAtrAcuFinalizado += (proyAtrMes != null ? proyAtrMes : 0);
                proyAtrMes = entPorMes.get(claveProyAtrParcial);
                proyAtrAcuParcial += (proyAtrMes != null ? proyAtrMes : 0);

                if (anio < anioHoy || (anio == anioHoy && mes <= mesHoy)) {
                    repAcu.setValorRealFinalizado(realAcuFinalizado);
                    repAcu.setValorRealParcial(realAcuParcial);

                    repAcu.setValorProyectadoFinalizado(proyAcuFinalizado);
                    repAcu.setValorProyectadoParcial(proyAcuParcial);

                    repAcu.setValorProyectadoAtrasadoFinalizado(proyAtrAcuFinalizado);
                    repAcu.setValorProyectadoAtrasadoParcial(proyAtrAcuParcial);
                } else {
                    repAcu.setValorProyectadoFinalizado(realAcuFinalizado + proyAcuFinalizado);
                    repAcu.setValorProyectadoParcial(realAcuParcial + proyAcuParcial);

                    repAcu.setValorProyectadoAtrasadoFinalizado(proyAtrAcuFinalizado);
                    repAcu.setValorProyectadoAtrasadoParcial(proyAtrAcuParcial);
                }
                repAcu.setProyectadoNegativoFinalizado(proyAtrAcuFinalizado > 0);
                repAcu.setProyectadoNegativoParcial(proyAtrAcuParcial > 0);

                if (orgPk != null) {
                    repAcu.setColorRealFinalizado(reporteMesTOColorFinalizado(repAcu, orgPk, limiteAmarilloAlcance, limiteRojoAlcance));
                    repAcu.setColorRealParcial(reporteMesTOColorParcial(repAcu, orgPk, limiteAmarilloAlcance, limiteRojoAlcance));
                }

                result.add(repAcu);

                primera.add(Calendar.MONTH, 1);
                mesPrimera = primera.get(Calendar.MONTH);
                anioPrimera = primera.get(Calendar.YEAR);
            }

            return result;
        }
        return null;
    }

    public String reporteMesTOColorFinalizado(ReporteAcumuladoMesTO repAcu, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        return reporteMesTOColor(repAcu, orgPk, limiteAmarillo, limiteRojo, 0);
    }

    public String reporteMesTOColorParcial(ReporteAcumuladoMesTO repAcu, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        return reporteMesTOColor(repAcu, orgPk, limiteAmarillo, limiteRojo, 1);
    }

    /**
     *
     * @param repAcu
     * @param orgPk
     * @param limiteAmarillo
     * @param limiteRojo
     * @param tipoAlcance 0-Finalizado, 1-Parcial
     * @return
     */
    public String reporteMesTOColor(ReporteAcumuladoMesTO repAcu, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo, int tipoAlcance) {
        if (repAcu != null
                && repAcu.getAnio() > 0
                && repAcu.getMes() > 0) {

            Calendar calNow = new GregorianCalendar();
            Calendar calMesTo = new GregorianCalendar();
            calMesTo.set(Calendar.YEAR, repAcu.getAnio());
            calMesTo.set(Calendar.MONTH, repAcu.getMes() - 1);

            if (limiteAmarillo == null) {
                limiteAmarillo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ALCANCE_INDICE_LIMITE_AMARILLO, orgPk).getCnfValor());
            }
            if (limiteRojo == null) {
                limiteRojo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.ALCANCE_INDICE_LIMITE_ROJO, orgPk).getCnfValor());
            }

            double porcentaje = 0;
            Double valorReal = (tipoAlcance == 1 ? repAcu.getValorRealParcial() : repAcu.getValorRealFinalizado());
            Double valorProyectado = (tipoAlcance == 1 ? repAcu.getValorProyectadoParcial() : repAcu.getValorProyectadoFinalizado());
            Double valorProyectadoTotal = (tipoAlcance == 1 ? repAcu.getValorProyectadoTotalParcial() : repAcu.getValorProyectadoTotalFinalizado());
            if (calMesTo.get(Calendar.YEAR) < calNow.get(Calendar.YEAR)
                    || (calMesTo.get(Calendar.YEAR) == calNow.get(Calendar.YEAR)
                    && calMesTo.get(Calendar.MONTH) < calNow.get(Calendar.MONTH))) {
                porcentaje = valorReal * 100 / repAcu.getValorPlan();
            } else if (calMesTo.get(Calendar.YEAR) == calNow.get(Calendar.YEAR)
                    && calMesTo.get(Calendar.MONTH) == calNow.get(Calendar.MONTH)) {
                porcentaje = (valorReal + valorProyectado) * 100 / repAcu.getValorPlan();
            } else {
                porcentaje = (valorReal + valorProyectadoTotal) * 100 / repAcu.getValorPlan();
            }

            if (porcentaje >= 100) {
                if (repAcu.getValorPlan() == 100 && repAcu.getValorRealFinalizado() >= 100) {
                    return ConstantesEstandares.SEMAFORO_AZUL;
                } else {
                    return ConstantesEstandares.SEMAFORO_VERDE;
                }
            } else if (porcentaje < 100 && porcentaje > limiteAmarillo) {
                return ConstantesEstandares.SEMAFORO_VERDE;
            } else if (porcentaje <= limiteAmarillo && porcentaje > limiteRojo) {
                return ConstantesEstandares.SEMAFORO_AMARILLO;
            } else if (porcentaje <= limiteRojo) {
                return ConstantesEstandares.SEMAFORO_ROJO;
            }
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public List<MisTareasTO> obtenerMisTareasPorFiltro(FiltroMisTareasTO filtro, Integer orgPk) {
        EntregablesDAO dao = new EntregablesDAO(em);
        List<MisTareasTO> result = dao.obtenerMisTareasPorFiltro(filtro, orgPk);

        //Para ordenar el resultado con los proy que tienen prog primero y los que no tienen quedan a lo último.
        if (CollectionsUtils.isNotEmpty(result)) {
            List<MisTareasTO> sinProg = new ArrayList<>();
            for (MisTareasTO mto : result) {
                if (mto.getProgPk() == null) {
                    sinProg.add(mto);
                }
            }
            result.removeAll(sinProg);
            result.addAll(sinProg);
        }

        return result;
    }

    public List<MisTareasProgProyTO> obtenerMisTareasPorFiltroGroupProyecto(FiltroMisTareasTO filtro, Integer orgPk, boolean conProductos, int anio) {

        EntregablesDAO dao = new EntregablesDAO(em);
        List<MisTareasTO> result;
        if (conProductos) {
            result = dao.obtenerMisTareasPorFiltroConEntregable(filtro, orgPk);
        } else {
            result = dao.obtenerMisTareasPorFiltro(filtro, orgPk);
        }
        List<MisTareasProgProyTO> ret = new ArrayList<>();

        //Para ordenar el resultado con los proy que tienen prog primero y los que no tienen quedan a lo último.
        if (CollectionsUtils.isNotEmpty(result)) {
            List<MisTareasTO> sinProg = new ArrayList<>();
            for (MisTareasTO mto : result) {
                if (mto.getProgPk() == null) {
                    sinProg.add(mto);
                }
            }

            result.removeAll(sinProg);

            //agrupar por programa-proyecto
            MisTareasTO item;
            MisTareasTO itemToGroup;
            MisTareasProgProyTO itemRet;
            Map<Integer, ?> allReadyProcess = new HashMap<>();
            List<Productos> productos;
            for (int i = 0; i < result.size(); ++i) {
                item = result.get(i);
                if (!allReadyProcess.containsKey(item.getEntPk())) {
                    itemRet = new MisTareasProgProyTO();
                    itemRet.setProgNombre(item.getProgNombre());
                    itemRet.setProgPk(item.getProgPk());
                    itemRet.setProyNombre(item.getProyNombre());
                    itemRet.setProyPk(item.getProyPk());
                    itemRet.setTareas(new ArrayList<MisTareasTO>());
                    itemRet.getTareas().add(item);
                    itemRet.setTieneProductos(item.getTieneProd());
                    if (item.getTieneProd() && conProductos) {
                        item.setProductos(productosBean.cargarProdMesAuxiliar(item.getProductos(), anio));
                    }
                    allReadyProcess.put(item.getEntPk(), null);
                    for (int j = i + 1; j < result.size(); ++j) {
                        itemToGroup = result.get(j);
                        if (itemRet.getKey().equals(itemToGroup.getKey())
                                && !allReadyProcess.containsKey(itemToGroup.getEntPk())) {
                            if (conProductos) {
                                productos = new ArrayList<>();
                                if (itemToGroup.getEnt().getEntProductosSet() != null) {
                                    for (Productos p : itemToGroup.getEnt().getEntProductosSet()) {
                                        productos.add(productosBean.cargarProdMesAuxiliar(p, anio));
                                    }
                                    Collections.sort(productos, new Comparator<Productos>() {
                                        @Override
                                        public int compare(Productos o1, Productos o2) {
                                            return o1.getProdPk().compareTo(o2.getProdPk());
                                        }
                                    });
                                    itemRet.setTieneProductos(itemRet.getTieneProductos() || !productos.isEmpty());
                                }
                                itemToGroup.setProductos(productos);
                            }
                            itemRet.getTareas().add(itemToGroup);
                            allReadyProcess.put(itemToGroup.getEntPk(), null);
                        }
                    }

                    //ordernar como en el cronograma
                    Collections.sort(itemRet.getTareas(),
                            new Comparator<MisTareasTO>() {
                        @Override
                        public int compare(MisTareasTO o1, MisTareasTO o2) {
                            return o1.getEntId().compareTo(o2.getEntId());
                        }
                    });
                    ret.add(itemRet);
                }
            }

            item = null;
            itemToGroup = null;
            itemRet = null;
            allReadyProcess = new HashMap<>();
            for (int i = 0; i < sinProg.size(); ++i) {
                item = sinProg.get(i);
                if (!allReadyProcess.containsKey(item.getEntPk())) {
                    itemRet = new MisTareasProgProyTO();
                    itemRet.setProgNombre(item.getProgNombre());
                    itemRet.setProgPk(item.getProgPk());
                    itemRet.setProyNombre(item.getProyNombre());
                    itemRet.setProyPk(item.getProyPk());
                    itemRet.setTareas(new ArrayList<MisTareasTO>());
                    itemRet.getTareas().add(item);
                    itemRet.setTieneProductos(item.getTieneProd());
                    if (item.getTieneProd() && conProductos) {
                        item.setProductos(productosBean.cargarProdMesAuxiliar(item.getProductos(), anio));
                    }
                    allReadyProcess.put(item.getEntPk(), null);
                    for (int j = i + 1; j < sinProg.size(); ++j) {
                        itemToGroup = sinProg.get(j);
                        if (itemRet.getKey().equals(itemToGroup.getKey())
                                && !allReadyProcess.containsKey(itemToGroup.getEntPk())) {
                            if (conProductos) {
                                productos = new ArrayList<>();
                                if (itemToGroup.getEnt().getEntProductosSet() != null) {
                                    for (Productos p : itemToGroup.getEnt().getEntProductosSet()) {
                                        productos.add(productosBean.cargarProdMesAuxiliar(p, anio));
                                    }
                                    itemRet.setTieneProductos(itemRet.getTieneProductos() || !productos.isEmpty());
                                }
                                itemToGroup.setProductos(productos);
                            }
                            itemRet.getTareas().add(itemToGroup);
                            allReadyProcess.put(itemToGroup.getEntPk(), null);
                        }
                    }

                    //ordernar como en el cronograma
                    Collections.sort(itemRet.getTareas(),
                            new Comparator<MisTareasTO>() {
                        @Override
                        public int compare(MisTareasTO o1, MisTareasTO o2) {
                            return o1.getEntId().compareTo(o2.getEntId());
                        }
                    });
                    ret.add(itemRet);
                }
            }

        }

        return ret;
    }

    public Double obtenerEsfuerzoTotal(Integer proyPk) {
        EntregablesDAO dao = new EntregablesDAO(em);
        return dao.obtenerEsfuerzoTotal(proyPk, null);
    }

    public Double obtenerEsfuerzoTerminado(Integer proyPk) {
        EntregablesDAO dao = new EntregablesDAO(em);
        return dao.obtenerEsfuerzoTotal(proyPk, 100);
    }

    public boolean mesTieneEntClave(Integer mes, Integer anio, Integer proyPk, Integer tipo) {
        EntregablesDAO dao = new EntregablesDAO(em);
        return dao.mesTieneEntClave(mes, anio, proyPk, tipo);
    }

    public boolean entregableTieneClave(Integer entPk, Integer tipo) {
        EntregablesDAO dao = new EntregablesDAO(em);
        return dao.entregableTieneClave(entPk, tipo);
    }

    public List<Integer> proyPkEntSinParent() {
        EntregablesDAO dao = new EntregablesDAO(em);
        return dao.proyPkEntSinParent();
    }

    public boolean entregableEsHito(Integer entPk) {
        EntregablesDAO dao = new EntregablesDAO(em);
        return dao.entregableEsHito(entPk);
    }
}
