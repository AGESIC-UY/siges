package com.sofis.business.ejbs;

import com.sofis.business.validations.PresupuestoValidacion;
import com.sofis.data.daos.PresupuestoDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.ProyectosConCronograma;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.PresupuestoEstadoAcEnum;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "PresupuestoBean")
@LocalBean
public class PresupuestoBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    @Inject
    private ProgramasBean programasBean;
    @Inject
    private ProyectosBean proyectosBean;
    @Inject
    private AdquisicionBean adquisicionBean;
    @Inject
    private ConfiguracionBean configuracionBean;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public Presupuesto guardar(Presupuesto presupuesto) {

        PresupuestoValidacion.validar(presupuesto);
        PresupuestoDAO preDao = new PresupuestoDAO(em);

        try {
            presupuesto = preDao.update(presupuesto, du.getCodigoUsuario(), du.getOrigen());

        } catch (BusinessException | TechnicalException be) {
            logger.logp(Level.SEVERE, PresupuestoBean.class.getName(), "guardar", be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, PresupuestoBean.class.getName(), "guardar", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException();
            ge.addError(ex.getMessage());
            throw ge;
        }

        return presupuesto;
    }

    public Object guardarPresupuesto(Presupuesto pre, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) {
        return guardarPresupuesto(pre, fichaFk, tipoFicha, usuario, orgPk, false);
    }

    public Object guardarPresupuesto(Presupuesto pre, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk, boolean returnPre) {
        logger.fine("PresupuestoBean.guardarPresupuesto");

        pre = guardar(pre);

        if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
            if (!returnPre) {
                return programasBean.obtenerProgPorId(fichaFk);
            }

        } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
            Proyectos proy = proyectosBean.obtenerProyPorId(fichaFk);
            proy.setProyPreFk(pre);
            proy = proyectosBean.guardarProyecto(proy, usuario, orgPk);
            if (!returnPre) {
                return proy;
            }
        }

        return pre;
    }

    public Presupuesto obtenerPresupuestoPorId(Integer prePk) {
        PresupuestoDAO preDao = new PresupuestoDAO(em);
        Presupuesto pre = null;
        try {
            pre = preDao.findById(Presupuesto.class, prePk);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(PresupuestoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pre;
    }

    public HashMap<Integer, String> obtenerColorAC(Set<ProyectosConCronograma> proyectos, List<Moneda> monedas, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        if (proyectos != null && monedas != null) {
            HashMap<Integer, String> monedaColor = new HashMap<>();
            HashMap<Integer, Double> totalPV = new HashMap<>();
            HashMap<Integer, Double> totalAC = new HashMap<>();
            for (ProyectosConCronograma proy : proyectos) {
                if (proy.getActivo()) {
                    for (Moneda moneda : monedas) {
                        if (!totalPV.containsKey(moneda.getMonPk())) {
                            totalPV.put(moneda.getMonPk(), 0d);
                        }
                        if (!totalAC.containsKey(moneda.getMonPk())) {
                            totalAC.put(moneda.getMonPk(), 0d);
                        }
                        if (proy.getProyPreFk() != null) {
                            totalPV.put(moneda.getMonPk(), totalPV.get(moneda.getMonPk()) + this.obtenerPVPorMoneda(proy.getProyPreFk(), moneda));
                            totalAC.put(moneda.getMonPk(), totalAC.get(moneda.getMonPk()) + this.obtenerACPorMoneda(proy.getProyPreFk(), moneda));
                        }
                    }
                }
            }
            for (Moneda moneda : monedas) {
                monedaColor.put(moneda.getMonPk(), obtenerColorAC(totalPV.get(moneda.getMonPk()), totalAC.get(moneda.getMonPk()), null, orgPk, limiteAmarillo, limiteRojo, null));
            }

            return monedaColor;
        }
        return null;
    }

    public HashMap<Integer, String> obtenerColorAC(Integer prePk, List<Moneda> monedas, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        HashMap<Integer, String> monedaColor = new HashMap<>();
        for (Moneda m : monedas) {
            monedaColor.put(m.getMonPk(), obtenerColorAC(prePk, m.getMonPk(), orgPk, limiteAmarillo, limiteRojo));
        }
        return monedaColor;
    }

    public String obtenerColorAC(Integer prePk, Integer monPk, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        if (prePk != null && monPk != null) {
            Double pv = this.obtenerPVPorMoneda(prePk, new Moneda(monPk));
            Double ac = this.obtenerACPorMoneda(prePk, new Moneda(monPk));
            return obtenerColorAC(pv, ac, null, orgPk, limiteAmarillo, limiteRojo, null);
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public String obtenerColorACMensual(Integer prePk, Integer adqPk, Integer monPk, Integer orgPk, int anio, int mes, Integer limiteAmarillo, Integer limiteRojo) {
        if (prePk != null && monPk != null) {
            Double pv = this.obtenerPVPorMoneda(prePk, adqPk, new Moneda(monPk), anio, mes);
            Double ac = this.obtenerACPorMoneda(prePk, adqPk, new Moneda(monPk), anio, mes);
            Double pr = this.obtenerPRPorMoneda(prePk, adqPk, new Moneda(monPk), anio, mes);
            Calendar cal = new GregorianCalendar(anio, mes, 1);
            return obtenerColorAC(pv, ac, pr, orgPk, limiteAmarillo, limiteRojo, cal);
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public String obtenerColorACMensual(Double pv, Double ac, Double pr, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo, Calendar cal) {
        if (pv != null && ac != null) {
            return obtenerColorAC(pv, ac, pr, orgPk, limiteAmarillo, limiteRojo, cal);
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public String obtenerColorACMensualProg(Integer progPk, Integer monPk, Integer orgPk, Double pv, Double ac, int anio, int mes, Integer limiteAmarillo, Integer limiteRojo) {
        if (progPk != null && monPk != null) {
            if (pv == null) {
                pv = this.obtenerPVPorMonedaProg(progPk, new Moneda(monPk), anio, mes);
            }
            if (ac == null) {
                ac = this.obtenerACPorMonedaProg(progPk, new Moneda(monPk), anio, mes);
            }
            Calendar cal = new GregorianCalendar(anio, mes, 1);
            return obtenerColorAC(pv, ac, null, orgPk, limiteAmarillo, limiteRojo, cal);
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public String obtenerColorAC(Double pv, Double ac, Double pr, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo, Calendar cal) {
        double plan = (pv != null ? pv : 0);
        double real = (ac != null ? ac : 0);
        double proyectado = (pr != null ? pr : 0);

        Calendar calNow = new GregorianCalendar();
        boolean isFechaMayor = cal == null || cal.get(Calendar.YEAR) > calNow.get(Calendar.YEAR)
                || (cal.get(Calendar.YEAR) == calNow.get(Calendar.YEAR)
                && cal.get(Calendar.MONTH) > calNow.get(Calendar.MONTH));
        boolean isFechaMenor = cal == null || (cal.get(Calendar.YEAR) < calNow.get(Calendar.YEAR)
                || (cal.get(Calendar.YEAR) == calNow.get(Calendar.YEAR)
                && cal.get(Calendar.MONTH) < calNow.get(Calendar.MONTH)));
        double porcentajeAC = 0;

        if (isFechaMenor) {
            porcentajeAC = plan > 0 ? (real * 100 / plan) : (real * 100);
        } else if (isFechaMayor) {
            porcentajeAC = plan > 0 ? (proyectado * 100 / plan) : (proyectado * 100);
        } else {
            porcentajeAC = plan > 0 ? (real * 100 / plan) : (real + proyectado) * 100;
        }

        if (limiteAmarillo == null) {
            limiteAmarillo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, orgPk).getCnfValor());
        }
        if (limiteRojo == null) {
            limiteRojo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, orgPk).getCnfValor());
        }

        if ((porcentajeAC >= (100 - limiteAmarillo) && porcentajeAC <= (100 + limiteAmarillo))) {
            return ConstantesEstandares.SEMAFORO_VERDE;
        } else if (porcentajeAC >= (100 - limiteRojo) && porcentajeAC <= (100 + limiteRojo)) {
            return ConstantesEstandares.SEMAFORO_AMARILLO;
        } else {
            if (porcentajeAC < (100 - limiteRojo)) {
                if (isFechaMenor && plan <= 0 && real <= 0) {
                    return ConstantesEstandares.COLOR_TRANSPARENT;
                }
                return ConstantesEstandares.SEMAFORO_NARANJA;
            } else {
                return ConstantesEstandares.SEMAFORO_ROJO;
            }
        }
    }

    public int obtenerEstadoAC(Set<Proyectos> proySet, Integer monPk, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        if (CollectionsUtils.isNotEmpty(proySet)) {
            Double totalPv = 0d;
            Double totalAc = 0d;
            for (Proyectos proy : proySet) {
                if (proy.isActivo() && proy.getProyPreFk() != null) {
                    Double pv = this.obtenerPVPorMoneda(proy.getProyPreFk().getPrePk(), new Moneda(monPk));
                    Double ac = this.obtenerACPorMoneda(proy.getProyPreFk().getPrePk(), new Moneda(monPk));
                    totalPv += pv != null ? pv : 0d;
                    totalAc += ac != null ? ac : 0d;
                }
            }
            return obtenerEstadoAC(totalPv, totalAc, orgPk, limiteAmarillo, limiteRojo);
        }
        return 0;
    }

    public int obtenerEstadoAC(Integer prePk, Integer monPk, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        if (prePk != null && monPk != null) {
            Double pv = this.obtenerPVPorMoneda(prePk, new Moneda(monPk));
            Double ac = this.obtenerACPorMoneda(prePk, new Moneda(monPk));
            return obtenerEstadoAC(pv, ac, orgPk, limiteAmarillo, limiteRojo);
        }
        return 0;
    }

    public int obtenerEstadoAC(Double pv, Double ac, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        if (pv != null && ac != null && (pv != 0 || ac != 0)) {
            double porcentajeAC = ac * 100 / pv;

            if (limiteAmarillo == null) {
                limiteAmarillo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, orgPk).getCnfValor());
            }
            if (limiteRojo == null) {
                limiteRojo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, orgPk).getCnfValor());
            }
            if (porcentajeAC >= (100 - limiteAmarillo) && porcentajeAC <= (100 + limiteAmarillo)) {
                return PresupuestoEstadoAcEnum.VERDE.id;
            } else if (porcentajeAC <= (100 + limiteRojo) && porcentajeAC > (100 + limiteAmarillo)) {
                return PresupuestoEstadoAcEnum.AMARILLO_MAS.id;
            } else if (porcentajeAC >= (100 - limiteRojo) && porcentajeAC < (100 - limiteAmarillo)) {
                return PresupuestoEstadoAcEnum.AMARILLO_MENOS.id;
            } else if (porcentajeAC < (100 - limiteRojo)) {
                return PresupuestoEstadoAcEnum.NARANJA.id;
            } else if (porcentajeAC > (100 + limiteRojo)) {
                return PresupuestoEstadoAcEnum.ROJO.id;
            }
        }
        return 0;
    }

    public List<Moneda> obtenerMonedasPresupuestoPrograma(Integer progPk) {
        if (progPk != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerMonedasPresupuestoPrograma(progPk);
        }
        return null;
    }

    public List<Moneda> obtenerMonedasPresupuesto(Integer prePk) {
        PresupuestoDAO pdao = new PresupuestoDAO(em);
        return pdao.obtenerMonedasPresupuesto(prePk);
    }

    public Double obtenerTotalPorMoneda(Integer prePk, Moneda m) {
        PresupuestoDAO pdao = new PresupuestoDAO(em);
        return pdao.obtenerTotalPorMoneda(prePk, m);
    }

    public Double obtenerTotalPorMonedaAnio(Integer prePk, Moneda p, Integer anio) {
        PresupuestoDAO pdao = new PresupuestoDAO(em);
        return pdao.obtenerTotalPorMonedaAnio(prePk, p, anio);
    }

    public Double obtenerTotalPorMonedaAnioProg(Integer progPk, Moneda p, Integer anio) {
        PresupuestoDAO pdao = new PresupuestoDAO(em);
        return pdao.obtenerTotalPorMonedaAnioProg(progPk, p, anio);
    }

    public Double obtenerPVPorMoneda(Integer prePk, Moneda p) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPVPorMoneda(prePk, p);
        }
        return 0D;
    }

    public Double obtenerPVPorMoneda(Integer prePk, Integer adqPk, Moneda p, int anio, int mes) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPVPorMoneda(prePk, adqPk, p, anio, mes);
        }
        return 0D;
    }

    public Double obtenerPVPorMonedaAcu(Integer prePk, Integer adqPk, Moneda p, int anio, int mes) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPVPorMonedaAcu(prePk, adqPk, p, anio, mes);
        }
        return 0D;
    }

    public Double obtenerPVPorMonedaProg(Integer progPk, Moneda p, int anio, int mes) {
        if (progPk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPVPorMonedaProg(progPk, p, anio, mes);
        }
        return 0D;
    }

    public Double obtenerPVPorMonedaProg(Integer progPk, Integer moneda) {
        if (progPk != null && moneda != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPVPorMonedaProg(progPk, moneda);
        }
        return 0D;
    }

    public Double obtenerACPorMoneda(Integer prePk, Moneda p) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerACPorMoneda(prePk, p);
        }
        return 0D;
    }

    public Double obtenerACPorMoneda(Integer prePk, Integer adqPk, Moneda p, int anio, int mes) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerACPorMoneda(prePk, adqPk, p, anio, mes);
        }
        return 0D;
    }

    public Double obtenerACPorMonedaAcu(Integer prePk, Integer adqPk, Moneda p, int anio, int mes) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerACPorMonedaAcu(prePk, adqPk, p, anio, mes);
        }
        return 0D;
    }

    public Double obtenerACPorMonedaProg(Integer progPk, Moneda moneda, int anioP, int mesP) {
        if (progPk != null && moneda != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerACPorMonedaProg(progPk, moneda, anioP, mesP);
        }
        return 0D;
    }

    public Double obtenerACPorMonedaProg(Integer progPk, Integer monPk) {
        if (progPk != null && monPk != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerACPorMonedaProg(progPk, monPk);
        }
        return 0D;
    }

    public Presupuesto copiarProyPresupuesto(Presupuesto proyPreFk, Set<Entregables> entSet, int desfasajeDias) {
        if (proyPreFk != null) {
            Presupuesto pre = new Presupuesto();
            pre.setAdquisicionSet(adquisicionBean.copiarProyAdquisicion(proyPreFk.getAdquisicionSet(), pre, entSet, desfasajeDias));
            pre.setFuenteFinanciamiento(proyPreFk.getFuenteFinanciamiento());
            pre.setPreBase(proyPreFk.getPreBase());
            pre.setPreMoneda(proyPreFk.getPreMoneda());
            return pre;
        }
        return null;
    }

    public Presupuesto obtenerPresupuestoPorProy(Integer proyPk) {
        PresupuestoDAO dao = new PresupuestoDAO(em);
        return dao.obtenerPresupuestoPorProy(proyPk);
    }

    public Date obtenerPrimeraFechaPre(Presupuesto pre) {
        if (pre != null) {
            List<Adquisicion> adqList = adquisicionBean.obtenerAdquisicionPorPre(pre.getPrePk());
            return obtenerPrimeraFecha(adqList);
        }
        return null;
    }

    public Date obtenerPrimeraFechaPreProg(Integer progPk) {
        List<Adquisicion> adqList = adquisicionBean.obtenerAdquisicionPorPreProg(progPk);
        return obtenerPrimeraFecha(adqList);
    }

    public Date obtenerUltimaFechaPre(Presupuesto pre) {
        if (pre != null) {
            List<Adquisicion> adqList = adquisicionBean.obtenerAdquisicionPorPre(pre.getPrePk());
            return ObtenerUltimaFecha(adqList);
        }
        return null;
    }

    public Date obtenerUltimaFechaPreProg(Integer progPk) {
        List<Adquisicion> adqList = adquisicionBean.obtenerAdquisicionPorPreProg(progPk);
        return ObtenerUltimaFecha(adqList);
    }

    public Date obtenerPrimeraFecha(List<Adquisicion> adqList) {
        Date result = null;
        if (CollectionsUtils.isNotEmpty(adqList)) {
            for (Adquisicion adq : adqList) {
                for (Pagos pago : adq.getPagosSet()) {
                    if (pago.getPagFechaPlanificada() != null
                            && (result == null || DatesUtils.esMayor(result, pago.getPagFechaPlanificada()))) {
                        result = pago.getPagFechaPlanificada();
                    }
                    if (pago.getPagFechaReal() != null
                            && (result == null || DatesUtils.esMayor(result, pago.getPagFechaReal()))) {
                        result = pago.getPagFechaReal();
                    }
                }
            }
        }
        return result;
    }

    public Date ObtenerUltimaFecha(List<Adquisicion> adqList) {
        Date result = null;
        if (CollectionsUtils.isNotEmpty(adqList)) {
            for (Adquisicion adq : adqList) {
                for (Pagos pago : adq.getPagosSet()) {
                    if (pago.getPagFechaPlanificada() != null
                            && (result == null || DatesUtils.esMayor(pago.getPagFechaPlanificada(), result))) {
                        result = pago.getPagFechaPlanificada();
                    }
                    if (pago.getPagFechaReal() != null
                            && (result == null || DatesUtils.esMayor(pago.getPagFechaReal(), result))) {
                        result = pago.getPagFechaReal();
                    }
                }
            }
        }
        return result;
    }

    public Double obtenerPRPorMoneda(Integer prePk, Integer adqPk, Moneda mon, int anio, int mes) {
        if (prePk != null && mon != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPRPorMoneda(prePk, adqPk, mon, anio, mes);
        }
        return 0D;
    }

    public Double obtenerPRPorMonedaAcu(Integer prePk, Integer adqPk, Moneda mon, int anio, int mes) {
        if (prePk != null && mon != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPRPorMonedaAcu(prePk, adqPk, mon, anio, mes);
        }
        return 0D;
    }

    public boolean obtenerPRAtrasado(Integer prePk, Integer adqPk, Moneda mon, int anio, int mes) {
        if (prePk != null && mon != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPRAtrasado(prePk, adqPk, mon, anio, mes);
        }
        return false;
    }
}
