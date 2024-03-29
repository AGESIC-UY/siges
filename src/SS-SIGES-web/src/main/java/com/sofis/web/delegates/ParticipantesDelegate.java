package com.sofis.web.delegates;

import com.sofis.business.ejbs.ParticipantesBean;
import com.sofis.entities.data.Participantes;
import com.sofis.entities.data.Proyectos;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ParticipantesDelegate {

    @Inject
    private ParticipantesBean participantesBean;

    public Participantes guardar(Participantes participante) throws GeneralException {
        return participantesBean.guardarParticipante(participante);
    }

    public Participantes obtenerParticipantesPorPk(Integer partPk) {
        return participantesBean.obtenerParticipantesPorPk(partPk);
    }

    public List<Participantes> obtenerParticipantesPorFichaPk(Integer fichaFk) {
        return participantesBean.obtenerParticipantesPorProyPk(fichaFk);
    }

    public void eliminar(Integer partPk) {
        participantesBean.eliminar(partPk);
    }

    public List<Proyectos> obtenerProyectosPorUsuarioParticipante(Integer usuId) {
        return participantesBean.obtenerProyectosPorUsuarioParticipante(usuId);
    }

    public List<Proyectos> obtenerProyectosPorUsuarioParticipanteActivo(Integer usuId) {
        return participantesBean.obtenerProyectosPorUsuarioParticipanteActivo(usuId);
    }

    public Participantes obtenerParticipantesPorUsuId(Integer proyPk, Integer usuId) {
        return participantesBean.obtenerParticipantesPorUsuId(proyPk, usuId);
    }

    public List<Participantes> obtenerParticipantesConHoraYGasto(Integer proyPk) {
        return participantesBean.obtenerParticipantesConHoraYGasto(proyPk);
    }
}
