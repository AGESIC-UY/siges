package com.sofis.business.ejbs;

import com.sofis.business.validations.ParticipantesValidacion;
import com.sofis.data.daos.ParticipantesDao;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Participantes;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.MonedaImporteTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ParticipantesBean")
@LocalBean
public class ParticipantesBean {

    private static final Logger logger = Logger.getLogger(ParticipantesBean.class.getName());
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private DatosUsuario du;
    @Inject
    private RegistrosHorasBean registrosHorasBean;
    @Inject
    private GastosBean gastosBean;

    public Participantes guardar(Participantes participante) throws GeneralException {

        ParticipantesValidacion.validar(participante);
        ParticipantesDao participantesDao = new ParticipantesDao(em);
        try {
            participante = participantesDao.update(participante);
        } catch (BusinessException be) {
            //Si es de tipo negocio envía la misma excepción
            logger.log(Level.SEVERE, null, be);
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

            if (ex.getCause() instanceof javax.persistence.PersistenceException
                    && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_PARTICIPANTE_CONST_VIOLATION);
                throw be;
            }

            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
        return participante;
    }

    public Participantes guardarParticipante(Participantes part) {
        if (part != null) {
            if (part.getPartActivo() == null) {
                part.setPartActivo(Boolean.TRUE);
            }
        }
        if (part.getPartUsuarioFk() != null) {
            SsUsuario s = em.find(SsUsuario.class, part.getPartUsuarioFk().getUsuId());
            part.setPartUsuarioFk(s);
        }
        return guardar(part);
    }

    public Participantes obtenerParticipantesPorPk(Integer partPk) {
        try {
            ParticipantesDao participantesDao = new ParticipantesDao(em);
            return participantesDao.findById(Participantes.class, partPk);
        } catch (BusinessException be) {
            throw be;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, ParticipantesBean.class.getName(), "obtenerParticipantesPorPk", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public List<Participantes> obtenerParticipantesPorProyPk(Integer fichaFk) {
        try {
            ParticipantesDao participantesDao = new ParticipantesDao(em);
            return participantesDao.findByOneProperty(Participantes.class, "partProyectoFk.proyPk", fichaFk);
        } catch (BusinessException be) {
            throw be;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, ParticipantesBean.class.getName(), "obtenerParticipantesPorFichaPk", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void eliminarNewTrans(Integer partPk) {
        eliminar(partPk);
    }
    
    public void eliminar(Integer partPk) {
        ParticipantesDao participantesDao = new ParticipantesDao(em);

        try {
            Participantes part = obtenerParticipantesPorPk(partPk);
            if (part != null) {
                boolean horasAprob = registrosHorasBean.usuTieneHorasAprob(part.getPartUsuarioFk().getUsuId(), part.getPartProyectoFk().getProyPk());
                boolean gastosAprob = gastosBean.usuTieneGastosAprob(part.getPartUsuarioFk().getUsuId(), part.getPartProyectoFk().getProyPk());
                if (horasAprob || gastosAprob) {
                    part.setPartActivo(false);
                    guardar(part);
                } else {
                    participantesDao.delete(part);
                }
            }
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PARTICIPANTE_ELIMINAR);
            throw be;
        }

    }

    public List<Proyectos> obtenerProyectosPorUsuarioParticipante(Integer usuId) {
        List<Proyectos> proyectos = new LinkedList<>();
        try {
            ParticipantesDao participantesDao = new ParticipantesDao(em);
            List<Participantes> parts = participantesDao.findByOneProperty(Participantes.class, "partUsuarioFk.usuId", usuId);
            if (parts != null) {
                for (Participantes part : parts) {
                    proyectos.add(part.getPartProyectoFk());
                }
            }
            return proyectos;
        } catch (BusinessException be) {
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public List<Proyectos> obtenerProyectosPorUsuarioParticipanteActivo(Integer usuId) {
        List<Proyectos> proyectos = new LinkedList<>();
        try {
            ParticipantesDao participantesDao = new ParticipantesDao(em);
            List<Participantes> parts = participantesDao.findByOneProperty(Participantes.class, "partUsuarioFk.usuId", usuId);
            if (parts != null) {
                for (Participantes part : parts) {
                    if (part.isParticipanteActivo()) {
                        proyectos.add(part.getPartProyectoFk());
                    }
                }
            }
            return proyectos;
        } catch (BusinessException be) {
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public Participantes obtenerParticipantesPorUsuId(Integer proyPk, Integer usuId) {
        if (usuId != null) {
            ParticipantesDao dao = new ParticipantesDao(em);
            try {
                MatchCriteriaTO proyCriteria = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "partProyectoFk.proyPk", proyPk);
                MatchCriteriaTO usuCriteria = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "partUsuarioFk.usuId", usuId);
                CriteriaTO criteria = CriteriaTOUtils.createANDTO(proyCriteria, usuCriteria);

                String[] orderBy = {};
                boolean[] asc = {};

                List<Participantes> result = dao.findEntityByCriteria(Participantes.class, criteria, orderBy, asc, null, null);
                return (Participantes) DAOUtils.obtenerSingleResult(result);
            } catch (DAOGeneralException ex) {
                Logger.getLogger(ParticipantesBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<Participantes> obtenerParticipantesConHoraYGasto(Integer proyPk) {
        if (proyPk != null) {
            List<Participantes> listPart = obtenerParticipantesPorProyPk(proyPk);
            for (Participantes part : listPart) {
                Integer usuId = part.getPartUsuarioFk().getUsuId();
                Double horasAprob = registrosHorasBean.obtenerHorasAbrobPorProy(proyPk, usuId);
                Double horasPendientes = registrosHorasBean.obtenerHorasPendPorProy(proyPk, usuId);
                MonedaImporteTO[] gastosAprob = gastosBean.obtenerGastosPorProyYMon(proyPk, usuId, true);
                MonedaImporteTO[] gastosPendientes = gastosBean.obtenerGastosPorProyYMon(proyPk, usuId, false);

                part.setHorasAprobadas(horasAprob);
                part.setHorasPendientes(horasPendientes);
                part.setGastosAprobados(gastosAprob);
                part.setGastosPendientes(gastosPendientes);
            }

            return listPart;
        }
        return null;
    }

    public boolean tieneDependenciasEnt(Integer entPk) {
        ParticipantesDao dao = new ParticipantesDao(em);
        return dao.tieneDependenciasEnt(entPk);
    }
}
