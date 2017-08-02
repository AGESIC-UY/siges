package com.sofis.web.delegates;

import com.sofis.business.ejbs.PresupuestoBean;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.GeneralException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class PresupuestoDelegate {

    @Inject
    private PresupuestoBean presupuestoBean;

    public Presupuesto guardar(Presupuesto presupuesto) throws GeneralException {
	return presupuestoBean.guardar(presupuesto);
    }

    public Object guardarPresupuesto(Presupuesto pre, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) {
	return presupuestoBean.guardarPresupuesto(pre, fichaFk, tipoFicha, usuario, orgPk);
    }

    public Presupuesto obtenerPresupuestoPorId(Integer prePk) {
	return presupuestoBean.obtenerPresupuestoPorId(prePk);
    }

    public Double obtenerTotalPorMoneda(Integer prePk, Moneda mon) {
	return presupuestoBean.obtenerTotalPorMoneda(prePk, mon);
    }

    public Double obtenerTotalPorMonedaAnio(Integer prePk, Moneda mon, Integer anio) {
	return presupuestoBean.obtenerTotalPorMonedaAnio(prePk, mon, anio);
    }

    public Double obtenerPVPorMoneda(Integer prePk, Moneda mon) {
	return presupuestoBean.obtenerPVPorMoneda(prePk, mon);
    }

    public Double obtenerPVPorMoneda(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda mon, int anio, int mes) {
	return presupuestoBean.obtenerPVPorMoneda(prePk, adqPk, setAdq, mon, anio, mes);
    }

    public Double obtenerPVPorMonedaAcu(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda mon, int anio, int mes) {
	return presupuestoBean.obtenerPVPorMonedaAcu(prePk, adqPk, setAdq, mon, anio, mes);
    }

    public Double obtenerPVPorMonedaProg(Integer progPk, Moneda mon, int anio, int mes) {
	return presupuestoBean.obtenerPVPorMonedaProg(progPk, mon, anio, mes);
    }

    public Double obtenerACPorMoneda(Integer prePk, Moneda mon) {
	return presupuestoBean.obtenerACPorMoneda(prePk, mon);
    }

    public Double obtenerACPorMoneda(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda mon, int anio, int mes) {
	return presupuestoBean.obtenerACPorMoneda(prePk, adqPk, setAdq, mon, anio, mes);
    }

    public Double obtenerACPorMonedaAcu(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda mon, int anio, int mes) {
	return presupuestoBean.obtenerACPorMonedaAcu(prePk, adqPk, setAdq, mon, anio, mes);
    }

    public Double obtenerACPorMonedaProg(Integer progPk, Moneda moneda, int anioP, int mesP) {
	return presupuestoBean.obtenerACPorMonedaProg(progPk, moneda, anioP, mesP);
    }

    public Double obtenerACPorMonedaProg(Integer progPk, Integer monPk) {
	return presupuestoBean.obtenerACPorMonedaProg(progPk, monPk);
    }

    public List<Moneda> obtenerMonedasPresupuesto(Integer prePk, Boolean conImporte) {
	return presupuestoBean.obtenerMonedasPresupuesto(prePk, conImporte);
    }

    public List<Moneda> obtenerMonedasPresupuestoPrograma(Integer progPk, Boolean conImporte) {
	return presupuestoBean.obtenerMonedasPresupuestoPrograma(progPk, conImporte);
    }

    public String obtenerColorACMensual(Double pv, Double ac, Double pr, Double prAtrasado, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo, Calendar cal) {
	return presupuestoBean.obtenerColorACMensual(pv, ac, pr, prAtrasado, orgPk, limiteAmarillo, limiteRojo, cal);
    }

//    public String obtenerColorAC(Double pv, Double ac, Double pr, Double prAtrasado, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo, Calendar cal, Presupuesto pre, Moneda mon) {
//        return presupuestoBean.obtenerColorAC(pv, ac, pr, prAtrasado, orgPk, limiteAmarillo, limiteRojo, cal, pre, mon);
//    }
    public String obtenerColorAC(Integer fichaFk, Integer monPk) {
	return presupuestoBean.obtenerColorAC(fichaFk, monPk);
    }

    public String obtenerColorACMensualProg(Integer progPk, Integer monPk, Integer orgPk, Double pv, Double ac, Double pr, Double prAtrasado, int anioP, int mesP, Integer limiteAmarilloPre, Integer limiteRojoPre) {
	return presupuestoBean.obtenerColorACMensualProg(progPk, monPk, orgPk, pv, ac, pr, prAtrasado, anioP, mesP, limiteAmarilloPre, limiteRojoPre);
    }

    public int obtenerEstadoAC(Integer prePk, Integer monPk, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
	return presupuestoBean.obtenerEstadoAC(prePk, monPk, orgPk, limiteAmarillo, limiteRojo);
    }

    public int obtenerEstadoAC(Set<Proyectos> proySet, Integer monPk, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
	return presupuestoBean.obtenerEstadoAC(proySet, monPk, orgPk, limiteAmarillo, limiteRojo);
    }

    public Presupuesto obtenerPresupuestoPorProy(Integer proyPk) {
	return presupuestoBean.obtenerPresupuestoPorProy(proyPk);
    }

    public Date obtenerPrimeraFechaPre(Presupuesto pre) {
	return presupuestoBean.obtenerPrimeraFechaPre(pre);
    }

    public Date obtenerPrimeraFechaPreProg(Integer progPk) {
	return presupuestoBean.obtenerPrimeraFechaPreProg(progPk);
    }

    public Date obtenerPrimeraFecha() {
	return presupuestoBean.obtenerPrimeraFecha();
    }

    public Date obtenerUltimaFecha() {
	return presupuestoBean.obtenerUltimaFecha();
    }

    public Date obtenerUltimaFechaPre(Presupuesto pre) {
	return presupuestoBean.obtenerUltimaFechaPre(pre);
    }

    public Date obtenerUltimaFechaPreProg(Integer progPk) {
	return presupuestoBean.obtenerUltimaFechaPreProg(progPk);
    }

    public Double obtenerTotalPorMonedaAnioProg(Integer progPk, Moneda p, Integer anio) {
	return presupuestoBean.obtenerTotalPorMonedaAnioProg(progPk, p, anio);
    }

    public Double obtenerPVPorMonedaProg(Integer progPk, Integer moneda) {
	return presupuestoBean.obtenerPVPorMonedaProg(progPk, moneda);
    }

    public Double obtenerPRPorMoneda(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda adqMoneda, int anioP, int mesP, Boolean atrasado) {
	return presupuestoBean.obtenerPRPorMoneda(prePk, adqPk, setAdq, adqMoneda, anioP, mesP, atrasado);
    }

    public Double obtenerPRPorMonedaAcu(Integer prePk, Integer adqPk, Set<Adquisicion> setAdq, Moneda adqMoneda, int anioP, int mesP) {
	return presupuestoBean.obtenerPRPorMonedaAcu(prePk, adqPk, setAdq, adqMoneda, anioP, mesP);
    }

    public Double obtenerPRPorMonedaProg(Integer progPk, Integer monPk, int anioP, int mesP, boolean atrasado) {
	return presupuestoBean.obtenerPRPorMonedaProg(progPk, monPk, anioP, mesP, atrasado);
    }

    public boolean obtenerPRAtrasado(Integer prePk, Integer adqPk, Moneda adqMoneda, int anioP, int mesP) {
	return presupuestoBean.obtenerPRAtrasado(prePk, adqPk, adqMoneda, anioP, mesP);
    }

    public boolean obtenerPRAtrasadoProg(Integer prePk, Moneda adqMoneda, int anioP, int mesP) {
	return presupuestoBean.obtenerPRAtrasadoProg(prePk, adqMoneda, anioP, mesP);
    }

    public Date obtenerPrimeraFechaPre(Integer prePk, Integer monPk) {
	return presupuestoBean.obtenerPrimeraUltimaFechaPre(prePk, monPk, true);
    }

    public Date obtenerUltimaFechaPre(Integer prePk, Integer monPk) {
	return presupuestoBean.obtenerPrimeraUltimaFechaPre(prePk, monPk, false);
    }

    public Date obtenerPrimeraFechaPreProg(Integer prePk, Integer monPk) {
	return presupuestoBean.obtenerPrimeraUltimaFechaPreProg(prePk, monPk, true);
    }

    public Date obtenerUltimaFechaPreProg(Integer prePk, Integer monPk) {
	return presupuestoBean.obtenerPrimeraUltimaFechaPreProg(prePk, monPk, false);
    }

}
