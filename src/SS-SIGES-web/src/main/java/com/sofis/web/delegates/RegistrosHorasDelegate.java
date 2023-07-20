package com.sofis.web.delegates;

import com.sofis.business.ejbs.RegistrosHorasBean;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.RegistrosHoras;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class RegistrosHorasDelegate {

    @Inject
    private RegistrosHorasBean registrosHorasBean;
    
    public void eliminarHoras(Integer hrPk) {
        registrosHorasBean.eliminarHoras(hrPk);
    }
    
    public RegistrosHoras obtenerRegHorasPorPk(Integer rhPk) {
        return registrosHorasBean.obtenerRegHorasPorPk(rhPk);
    }

    public RegistrosHoras registrarHoras(RegistrosHoras registroHoras, Integer orgPk) {
        return registrosHorasBean.registrarHoras(registroHoras, orgPk);
    }
    
    public List<RegistrosHoras> registrarHoras(List<RegistrosHoras> registroHoras, Integer orgPk) {
        return registrosHorasBean.registrarHoras(registroHoras, orgPk);
    }

    public List<RegistrosHoras> obtenerRegistrosHoras(Integer usuId, Integer proyPk, Integer entPk, Date fDesde, Date fHasta, Long desde, Long cant, Integer aprobado, Integer orgPk) {
        return registrosHorasBean.obtenerRegistrosHoras(usuId, proyPk, entPk, fDesde, fHasta, desde, cant, aprobado, orgPk);
    }

    public Double obtenerHorasAbrobPorProy(Integer proyPk, Integer usuId) {
        return registrosHorasBean.obtenerHorasAbrobPorProy(proyPk, usuId);
    }

    public Double obtenerHorasPendPorProy(Integer proyPk, Integer usuId) {
        return registrosHorasBean.obtenerHorasPendPorProy(proyPk, usuId);
    }
    
    public boolean usuTieneHorasAprob(Integer usuId, Integer proyPk) {
        return registrosHorasBean.usuTieneHorasAprob(usuId, proyPk);
    }

    public List<RegistrosHoras> obtenerHorasPorProy(Integer proyPk) {
        return registrosHorasBean.obtenerHorasPorProy(proyPk);
    }

    public List<Moneda> obtenerMonedasPorProy(Integer proyPk) {
        return registrosHorasBean.obtenerMonedasPorProy(proyPk);
    }

    public Double obtenerImporteTotalHs(Integer proyPk, Integer orgPk, Integer monPk, Integer mes, Integer year, Integer usuPk, Boolean aprobado) {
        return registrosHorasBean.obtenerImporteTotalHs(proyPk, orgPk, monPk, mes, year, usuPk, aprobado);
    }
    
    public Double obtenerImporteTotalHsAprob(Integer proyPk, Integer orgPk,Integer monPk, Integer mes, Integer anio, Integer usuPk) {
        return registrosHorasBean.obtenerImporteTotalHsAprob(proyPk, orgPk, monPk, mes, anio, usuPk);
    }

    public Double obtenerImporteTotalHsPend(Integer proyPk, Integer orgPk, Integer monPk, Integer mes, Integer anio, Integer usuPk) {
        return registrosHorasBean.obtenerImporteTotalHsPend(proyPk, orgPk, monPk, mes, anio, usuPk);
    }
}
