package com.sofis.business.ejbs;

import com.sofis.business.validations.PresupuestoValidacion;
import com.sofis.data.daos.PagosDAO;
import com.sofis.data.daos.PresupuestoDAO;
import com.sofis.data.daos.ProyIndicesDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.ProgIndicesPre;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.ProyIndicesPre;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.ProyectosConCronograma;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.ColoresCodigosEnum;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.MathUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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

    private static final Logger logger = Logger.getLogger(PresupuestoBean.class.getName());
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
    @Inject
    private ProgramasProyectosBean programasProyectosBean;

    //private String usuario;
    //private String origen;
    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

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
            TechnicalException ge = new TechnicalException(ex);
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
                monedaColor.put(moneda.getMonPk(), obtenerColorAC(totalPV.get(moneda.getMonPk()), totalAC.get(moneda.getMonPk()), null, null, orgPk, limiteAmarillo, limiteRojo, null));
            }

            return monedaColor;
        }
        return null;
    }

    /**
     * Obtiene el color en formato RGB para el valor real.
     *
     * @param prePk
     * @param monedas
     * @param orgPk
     * @param limiteAmarillo
     * @param limiteRojo
     * @return HashMap donde la key es el pk de la moneda y el valor String es
     * el color.
     */
    public HashMap<Integer, Object[]> obtenerColorAC(Integer prePk, List<Moneda> monedas, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        HashMap<Integer, Object[]> monedaColor = new HashMap<>();
        for (Moneda m : monedas) {
            monedaColor.put(m.getMonPk(), obtenerColorAC(prePk, m.getMonPk(), orgPk, limiteAmarillo, limiteRojo));
        }
        return monedaColor;
    }

    private Object[] obtenerColorAC(Integer prePk, Integer monPk, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        if (prePk != null && monPk != null) {
            Moneda mon = new Moneda(monPk);
            Calendar calNow = new GregorianCalendar();
            Double anio = this.obtenerTotalPorMonedaAnio(prePk, mon, calNow.get(Calendar.YEAR));
            Double pv = this.obtenerPVPorMoneda(prePk, mon);
            Double ac = this.obtenerACPorMoneda(prePk, mon);
            Presupuesto pre = obtenerPresupuestoPorId(prePk);
            String color = obtenerColorAC(pv, ac, null, null, orgPk, limiteAmarillo, limiteRojo, null, pre, mon);
            return new Object[]{color, pv, ac, anio};
        }
        return new Object[]{ConstantesEstandares.COLOR_TRANSPARENT, null, null};
    }

    public String obtenerColorAC(Integer fichaFk, Integer monPk) {
        ProyIndicesDAO proyIndDao = new ProyIndicesDAO(em);
        ProyIndices proyIndices = proyIndDao.obtenerIndicePorProyId(fichaFk);
        if (proyIndices != null) {
            Set<ProyIndicesPre> pres = proyIndices.getProyIndPreSet();
            if (pres != null) {
                for (ProyIndicesPre p : pres) {
                    if (p.getProyindpreMonFk() == monPk.intValue()) {
                        return programasProyectosBean.obtenerColorEstadoAcFromCodigo(p.getProyindpreEstPre());
                    }
                }
            }
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public String obtenerColorACMensual(Double pv, Double ac, Double pr, Double prAtrasado, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo, Calendar cal) {
        if (pv != null && ac != null) {
            return obtenerColorAC(pv, ac, pr, prAtrasado, orgPk, limiteAmarillo, limiteRojo, cal);
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public String obtenerColorACMensualProg(Integer progPk, Integer monPk, Integer orgPk, Double pv, Double ac, Double pr, Double prAtrasado, int anio, int mes, Integer limiteAmarillo, Integer limiteRojo) {
        if (progPk != null && monPk != null) {
            if (pv == null) {
                pv = this.obtenerPVPorMonedaProg(progPk, new Moneda(monPk), anio, mes);
            }
            if (ac == null) {
                ac = this.obtenerACPorMonedaProg(progPk, new Moneda(monPk), anio, mes);
            }
            if (pr == null) {
                pr = this.obtenerPRPorMonedaProg(progPk, monPk, anio, mes, false);
            }
            if (prAtrasado == null) {
                prAtrasado = this.obtenerPRPorMonedaProg(progPk, monPk, anio, mes, false);
            }
            Calendar cal = new GregorianCalendar(anio, mes, 1);
            return obtenerColorAC(pv, ac, pr, prAtrasado, orgPk, limiteAmarillo, limiteRojo, cal);
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public String obtenerColorAC(Double pv, Double ac, Double pr, Double prAtrasado, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo, Calendar cal) {
        return obtenerColorAC(pv, ac, pr, prAtrasado, orgPk, limiteAmarillo, limiteRojo, cal, null, null);
    }

    public String obtenerColorAC(Double pv, Double ac, Double pr, Double prAtrasado, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo, Calendar cal, Presupuesto pre, Moneda mon) {
        double plan = (pv != null ? pv : 0);
        double real = (ac != null ? ac : 0);
        double proyectado = (pr != null ? pr : 0);
        double proyectadoAtrasado = (prAtrasado != null ? prAtrasado : 0);

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
            porcentajeAC = plan > 0 ? ((proyectadoAtrasado + proyectado) * 100 / plan) : ((proyectadoAtrasado + proyectado) * 100);
        } else {
            porcentajeAC = plan > 0 ? ((real + proyectado) * 100 / plan) : ((real + proyectado) * 100);
        }

        if (limiteAmarillo == null) {
            limiteAmarillo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, orgPk).getCnfValor());
        }
        if (limiteRojo == null) {
            limiteRojo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, orgPk).getCnfValor());
        }

        if ((porcentajeAC >= (100 - limiteAmarillo) && porcentajeAC <= (100 + limiteAmarillo))
                || (porcentajeAC == 0 && (proyectado > 0))
                || ( //                  [BRUNO] No tiene que tomar en cuenta el presupuesto aprobado 25/11/2016 
                //                  pre != null && pre.getPreBase() != null && pre.getPreBase() > 0 && 
                plan == 0
                && real == 0
                && proyectado == 0
                && proyectadoAtrasado == 0)) {
            return ConstantesEstandares.SEMAFORO_VERDE;
        } else if (porcentajeAC >= (100 - limiteRojo) && porcentajeAC <= (100 + limiteRojo)) {
//            return ConstantesEstandares.SEMAFORO_AMARILLO;
            if (porcentajeAC < 100) {
                return ConstantesEstandares.SEMAFORO_AMARILLO_MENOS;
            } else {
                return ConstantesEstandares.SEMAFORO_AMARILLO_MAS;
            }
        } else if (porcentajeAC < (100 - limiteRojo)) {
            if (plan <= 0 && real <= 0 && proyectado <= 0) {
                return ConstantesEstandares.COLOR_TRANSPARENT;
            }
            return ConstantesEstandares.SEMAFORO_NARANJA;
        } else {
            return ConstantesEstandares.SEMAFORO_ROJO;
        }
    }

    public int obtenerEstadoAC(Set<Proyectos> proySet, Integer monPk, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        if (CollectionsUtils.isNotEmpty(proySet)) {
            Double totalPv = 0d;
            Double totalAc = 0d;
            boolean tienePreBase = false;
            boolean tieneAdquisiciones = false;
            for (Proyectos proy : proySet) {
                if (proy.isActivo() && proy.getProyPreFk() != null) {
                    Moneda mon = new Moneda(monPk);
                    Double pv = this.obtenerPVPorMoneda(proy.getProyPreFk().getPrePk(), mon);
                    Double ac = this.obtenerACPorMoneda(proy.getProyPreFk().getPrePk(), mon);
                    ac = ac != null ? ac : 0d;
                    pv = pv != null ? pv : 0d;
                    totalPv += pv;
                    totalAc += ac;

                    tienePreBase = tienePreBase
                            || (this.obtenerCantidadPVPorMoneda(proy.getProyPreFk().getPrePk(), mon) > 0);
                    if (!tieneAdquisiciones) {
                        for (Adquisicion a : proy.getProyPreFk().getAdquisicionSet()) {
                            if (a.getAdqMoneda().equals(mon)) {
                                tieneAdquisiciones = true;
                                break;
                            }
                        }
                    }
                }
            }
//            return obtenerEstadoAC(totalPv, totalAc, orgPk, limiteAmarillo, limiteRojo, tienePreBase);
            /**
             * [BRUNO] 20-05-17 Se estaba enviando el parámetro de si tiene
             * prespuesto base en lugar de si tiene adquisiciones.
             */
            return obtenerEstadoAC(totalPv, totalAc, orgPk, limiteAmarillo, limiteRojo, tieneAdquisiciones);
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
        return obtenerEstadoAC(pv, ac, orgPk, limiteAmarillo, limiteRojo, false);
    }

    /**
     *
     * @param pv
     * @param ac
     * @param orgPk
     * @param limiteAmarillo
     * @param limiteRojo
     * @param tieneAdquisiciones [BRUNO] 17-1-17
     * @return
     */
    public int obtenerEstadoAC(Double pv, Double ac, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo, boolean tieneAdquisiciones) {
        if (pv != null && ac != null) {

            /**
             * [BRUNO] 17-1-17 SI NO TIENE ADQUISICIONES NO SE MUESTRA EL
             * SEMÁFORO JP
             */
            if (!tieneAdquisiciones) {
                return 0;
            }

            /**
             * [BRUNO] 20-05-2017: Agregado por seguridad en operaciones con
             * double.
             */
            ac = MathUtils.round(ac, 2);
            pv = MathUtils.round(pv, 2);

            /**
             * [BRUNO] 17-1-17: EL SEMÁFORO LO MUESTRA SIEMPRE QUE HAYA
             * ADQUISCIONES JP Si PV = 0 y AC = 0 -> Verde Si PV = 0 y AC != 0
             * -> Rojo
             */
            if (pv == 0) {
                return ac == 0 ? ColoresCodigosEnum.VERDE.id : ColoresCodigosEnum.ROJO.id;
            }

            /**
             * [BRUNO] 16-1-17: mal calculado el porcentaje de avance. [BRUNO]
             * 20-5-17: si el pv = 0 y ac >= 0 -> 100%. si pv = 0 y ac < 0 ->
             * -100%
             */
//          double porcentajeAC = pv > 0 ? (ac * 100 / pv) : (ac * 100);
            double porcentajeAC = pv != 0
                    ? (ac * 100 / pv)
                    : ac >= 0 ? 100 : -100; // 0 de 0 es 100%

            porcentajeAC = MathUtils.round(porcentajeAC, 2);
            porcentajeAC = porcentajeAC > 100 ? 100 : porcentajeAC;

            if (limiteAmarillo == null) {
                limiteAmarillo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO, orgPk).getCnfValor());
            }
            if (limiteRojo == null) {
                limiteRojo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO, orgPk).getCnfValor());
            }

            if ((porcentajeAC >= (100 - limiteAmarillo) && porcentajeAC <= (100 + limiteAmarillo))) {
                /**
                 * [BRUNO] 16-1-17 Se saca esta condición "|| (porcentajeAC == 0
                 * && tienePreBase)"
                 */
                return ColoresCodigosEnum.VERDE.id;
            } else if (porcentajeAC <= (100 + limiteRojo) && porcentajeAC > (100 + limiteAmarillo)) {
                return ColoresCodigosEnum.AMARILLO_MAS.id;
            } else if (porcentajeAC >= (100 - limiteRojo) && porcentajeAC < (100 - limiteAmarillo)) {
                return ColoresCodigosEnum.AMARILLO_MENOS.id;
            } else if (porcentajeAC < (100 - limiteRojo)) {
                /**
                 * [BRUNO] 17-1-17 Se saca esta condición
                 */
//                if (pv <= 0 && ac <= 0) {
//                    return 0;
//                }
                return ColoresCodigosEnum.NARANJA.id;
            } else if (porcentajeAC > (100 + limiteRojo)) {
                return ColoresCodigosEnum.ROJO.id;
            }
        }
        return 0;
    }

    public List<Moneda> obtenerMonedasPresupuestoPrograma(Integer progPk, Boolean conImporte) {
        if (progPk != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerMonedasPresupuestoPrograma(progPk, conImporte);
        }
        return null;
    }

    public List<Moneda> obtenerMonedasPresupuesto(Integer prePk, Boolean conImporte) {
        PresupuestoDAO pdao = new PresupuestoDAO(em);
        return pdao.obtenerMonedasPresupuesto(prePk, conImporte);
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

    public Long obtenerCantidadPVPorMoneda(Integer prePk, Moneda p) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerCantidadPVPorMoneda(prePk, p);
        }
        return 0L;
    }

    public Double obtenerPVPorMoneda(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda p, int anio, int mes) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPVPorMoneda(prePk, adqPk, setAdq, p, anio, mes);
        }
        return 0D;
    }

    public Double obtenerPVPorMonedaAcu(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda p, int anio, int mes) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPVPorMonedaAcu(prePk, adqPk, setAdq, p, anio, mes);
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

    public Integer obtenerCantidadACPorMoneda(Integer prePk, Moneda p) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerCantidadACPorMoneda(prePk, p);
        }
        return 0;
    }

    public Double obtenerACPorMoneda(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda p, int anio, int mes) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerACPorMoneda(prePk, adqPk, setAdq, p, anio, mes);
        }
        return 0D;
    }

    public Double obtenerACPorMonedaAcu(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda p, int anio, int mes) {
        if (prePk != null && p != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerACPorMonedaAcu(prePk, adqPk, setAdq, p, anio, mes);
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
            return obtenerUltimaFecha(adqList);
        }
        return null;
    }

    public Date obtenerUltimaFecha() {
        PagosDAO p = new PagosDAO(em);
        return p.obtenerUltimaFecha();
    }

    public Date obtenerPrimeraFecha() {
        PagosDAO p = new PagosDAO(em);
        return p.obtenerPrimeraFecha();
    }

    public Date obtenerUltimaFechaPreProg(Integer progPk) {
        List<Adquisicion> adqList = adquisicionBean.obtenerAdquisicionPorPreProg(progPk);
        return obtenerUltimaFecha(adqList);
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

    public Date obtenerUltimaFecha(List<Adquisicion> adqList) {
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

    public Double obtenerPRPorMoneda(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda mon, int anio, int mes, Boolean atrasado) {
        if (prePk != null && mon != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPRPorMoneda(prePk, adqPk, setAdq, mon, anio, mes, atrasado);
        }
        return 0D;
    }

    public Double obtenerPRPorMonedaAcu(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda mon, int anio, int mes) {
        if (prePk != null && mon != null) {
            PresupuestoDAO dao = new PresupuestoDAO(em);
            return dao.obtenerPRPorMonedaAcu(prePk, adqPk, setAdq, mon, anio, mes);
        }
        return 0D;
    }

    public Double obtenerPRPorMonedaProg(Integer progPk, Integer monPk, int anioP, int mesP, boolean atrasado) {
        if (progPk != null && monPk != null) {
            PresupuestoDAO dao = new PresupuestoDAO(em);
            return dao.obtenerPRPorMonedaProg(progPk, monPk, anioP, mesP, atrasado);
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

    public boolean obtenerPRAtrasadoProg(Integer progPk, Moneda mon, int anio, int mes) {
        if (progPk != null && mon != null) {
            PresupuestoDAO pdao = new PresupuestoDAO(em);
            return pdao.obtenerPRAtrasadoProg(progPk, mon, anio, mes);
        }
        return false;
    }

    public Date obtenerPrimeraUltimaFechaPre(Integer prePk, Integer monPk, boolean primeraFecha) {
        if (prePk != null && monPk != null) {
            PresupuestoDAO dao = new PresupuestoDAO(em);
            return dao.obtenerPrimeraUltimaFechaPre(prePk, monPk, primeraFecha);
        }
        return null;
    }

    public Date obtenerPrimeraUltimaFechaPreProg(Integer progPk, Integer monPk, boolean primeraFecha) {
        if (progPk != null && monPk != null) {
            PresupuestoDAO dao = new PresupuestoDAO(em);
            return dao.obtenerPrimeraUltimaFechaPreProg(progPk, monPk, primeraFecha);
        }
        return null;
    }

    /**
     * Dado el Set de ProgIndicesPre o ProyIndicesPre lo transforma en un
     * HashMap.
     *
     * @param preSet
     * @return HashMap donde la key es el pk de la moneda y el valor el String
     * RGB del color.
     */
    public HashMap<Integer, String> getProyindPreMoneda(Set<?> preSet) {
        HashMap<Integer, String> result = null;

        if (CollectionsUtils.isNotEmpty(preSet)) {
            result = new HashMap<>();
            for (Object obj : preSet) {
                if (obj instanceof ProgIndicesPre) {
                    ProgIndicesPre indPre = (ProgIndicesPre) obj;
                    result.put(indPre.getProgindpreMonFk(), programasProyectosBean.obtenerColorEstadoAcFromCodigo(indPre.getProgindpreEstPre()));
                } else if (obj instanceof ProyIndicesPre) {
                    ProyIndicesPre indPre = (ProyIndicesPre) obj;
                    result.put(indPre.getProyindpreMonFk(), programasProyectosBean.obtenerColorEstadoAcFromCodigo(indPre.getProyindpreEstPre()));
                }
            }
        }
        return result;
    }
        }
