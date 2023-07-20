package com.sofis.web.delegates;

import com.sofis.entities.data.Programas;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.ItemInicioTO;
import com.sofis.exceptions.GeneralException;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ProgProyDelegate {

    @Inject
    ProgramasDelegate programaDelegate;
    @Inject
    ProyectosDelegate proyectoDelegate;
    @Inject
    ProgramasProyectosDelegate programasProyectosDelegate;

    public Object obtenerProgProyPorId(FichaTO fichaTO) throws GeneralException {
        if (fichaTO.isPrograma() && fichaTO.getFichaFk() != null) {
            return programaDelegate.obtenerProgPorId(fichaTO.getFichaFk());
        } else if (fichaTO.isProyecto() && fichaTO.getFichaFk() != null) {
            return proyectoDelegate.obtenerProyPorId(fichaTO.getFichaFk());
        } else {
            return null;
        }
    }

    public Object guardarProgProy(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
        if (fichaTO.isPrograma()) {
            return programaDelegate.guardarPrograma(fichaTO, usuario, orgPk);
        } else if (fichaTO.isProyecto()) {
            return proyectoDelegate.guardarProyecto(fichaTO, usuario, orgPk);
        }
        return null;
    }

    public Proyectos guardarRetrocederEstado(Integer proyPk, SsUsuario usuario, Integer orgPk, ProyReplanificacion replanificacion) throws GeneralException {
        return proyectoDelegate.guardarProyectoRetrocederEstado(proyPk, usuario, orgPk, replanificacion);
    }

    public Object guardarAprobacion(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
        if (fichaTO.isPrograma()) {
            return programaDelegate.guardarProgramaAprobacion(fichaTO, usuario, orgPk);
        } else if (fichaTO.isProyecto()) {
            return proyectoDelegate.guardarProyectoAprobacion(fichaTO, usuario, orgPk);
        }
        return null;
    }
    
    public Object guardarAprobacion(Object progProy, SsUsuario usuario, Integer orgPk) throws GeneralException {
        if (progProy instanceof Programas) {
            return programaDelegate.guardarProgramaAprobacion((Programas)progProy, usuario, orgPk);
        } else if (progProy instanceof Proyectos) {
            return proyectoDelegate.guardarProyectoAprobacion((Proyectos)progProy, usuario, orgPk);
        }
        return null;
    }

    public Object eliminarProgProy(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
        if (fichaTO.isPrograma()) {
            return programaDelegate.darBajaPrograma(fichaTO.getFichaFk(), usuario, orgPk);
        } else if (fichaTO.isProyecto()) {
            return proyectoDelegate.darBajaProyecto(fichaTO.getFichaFk());
        } else {
            return null;
        }
    }
    
    public ItemInicioTO crearFiltroInicioItem(Object o){
        return programasProyectosDelegate.crearFiltroInicioItem(o);
    }
}