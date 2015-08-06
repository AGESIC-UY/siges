package com.sofis.web.delegates;

import com.sofis.business.ejbs.ProyectosBean;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.exceptions.GeneralException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ProyectosDelegate {

    @Inject
    ProyectosBean proyectoBean;

    public Proyectos obtenerProyPorId(Integer id) throws GeneralException {
        return proyectoBean.obtenerProyPorId(id);
    }

    public Proyectos obtenerProyPorId(Integer id, Boolean loadOtrosDatos) throws GeneralException {
        return proyectoBean.obtenerProyPorId(id, loadOtrosDatos);
    }

    public void guardarIndicadoresSimple(Integer proyPk, boolean programas, boolean soloFaltantes, Integer orgPk) {
        proyectoBean.guardarIndicadoresSimple(proyPk, programas, soloFaltantes, orgPk);
    }

    public Proyectos guardarProyecto(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
        return proyectoBean.guardarProyecto(fichaTO, usuario, orgPk);
    }

    public Proyectos guardarProyectoAprobacion(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
        return proyectoBean.guardarProyectoAprobacion(fichaTO, usuario, orgPk);
    }

    public Proyectos guardarProyectoAprobacion(Proyectos proy, SsUsuario usuario, Integer orgPk) throws GeneralException {
        return proyectoBean.guardarProyectoAprobacion(proy, usuario, orgPk);
    }

    public Proyectos guardarProyectoRetrocederEstado(Integer proyPk, SsUsuario usuario, Integer orgPk, ProyReplanificacion replanificacion) throws GeneralException {
        return proyectoBean.guardarProyectoRetrocederEstado(proyPk, usuario, orgPk, replanificacion);
    }

    public Proyectos guardarCopiaProyecto(Integer proyPk, String nombre, Date fechaComienzoProyCopia, SsUsuario usu, Integer orgPk) {
        return proyectoBean.guardarCopiaProyecto(proyPk, nombre, fechaComienzoProyCopia, usu, orgPk);
    }

    public Set<Proyectos> obtenerProyPorProgId(Integer fichaFk) {
        return proyectoBean.obtenerProyPorProgId(fichaFk);
    }

    public Proyectos darBajaProyecto(Integer progPk, SsUsuario usuario, Integer orgPk) throws GeneralException {
        return proyectoBean.darBajaProyecto(progPk, usuario, orgPk);
    }

    public List<Proyectos> obtenerTodos() {
        return proyectoBean.obtenerTodos();
    }

    public String obtenerUltimaActualizacionColor(Estados estado, Date proyFechaAct, Integer proySemaforoAmarillo, Integer proySemaforoRojo) {
        return proyectoBean.obtenerUltimaActualizacionColor(estado, proyFechaAct, proySemaforoAmarillo, proySemaforoRojo);
    }

    public Double porcentajePesoTotalProyecto(Integer proyPk) {
        return proyectoBean.porcentajePesoTotalProyecto(proyPk);
    }

    public List<Integer> obtenerIdsProyPorOrg(Integer orgPk) throws GeneralException {
        return proyectoBean.obtenerIdsProyPorOrg(orgPk);
    }

    public List<Proyectos> obtenerActivos(Integer orgPk) {
        return proyectoBean.obtenerActivosPorOrg(orgPk);
    }

    public List<Proyectos> obtenerProyComboPorOrg(Integer orgPk) {
        return proyectoBean.obtenerProyComboPorOrg(orgPk);
    }

    public void controlarEntregables(Integer proyPk, boolean resetLineaBase) {
        proyectoBean.controlarEntregables(proyPk, resetLineaBase);
    }

    public List<Entregables> recalcularEntregablesPadres(Set<Entregables> entSet) {
        return proyectoBean.recalcularEntregablesPadres(entSet);
    }

    public List<Proyectos> obtenerProyPorGerente(Integer usuPk, Integer orgPk) {
        return proyectoBean.obtenerProyPorGerente(usuPk, orgPk);
    }

    public Date obtenerPrimeraFecha() {
        return proyectoBean.obtenerPrimeraFecha();
    }

    public Date obtenerUltimaFecha() {
        return proyectoBean.obtenerUltimaFecha();
    }

    public Integer porcentajeAvanceEnTiempo(Integer proyPk) {
        return proyectoBean.porcentajeAvanceEnTiempo(proyPk);
    }

    public List<Proyectos> obtenerPorUsuarioParticipanteActivo(Integer usuId, Integer orgPk) {
        return proyectoBean.obtenerPorUsuarioParticipanteActivo(usuId, orgPk);
    }

    public void controlarProdAcumulados(Integer proyPk) {
        proyectoBean.controlarProdAcumulados(proyPk);
    }
}
