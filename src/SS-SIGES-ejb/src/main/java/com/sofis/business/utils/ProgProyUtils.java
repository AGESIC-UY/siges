package com.sofis.business.utils;

import com.sofis.business.ejbs.ProgramasProyectosBean;
import com.sofis.business.validations.ReplanificacionValidacion;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.LatlngProyectos;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.ws.gestion.data.ProyReplanificacionTO;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author Usuario
 */
public class ProgProyUtils {

    public static final String URI_PROYECTO = "/SS-SIGES-web/paginasPrivadas/ficha.jsf?programaProyectoId=2-";
    public static final String URI_PROGRAMA = "/SS-SIGES-web/paginasPrivadas/ficha.jsf?programaProyectoId=1-";
    
	public static Object fichaTOToProgProy(FichaTO fichaTO) {

		if (fichaTO.isPrograma()) {
//            logger.info("Is Programa");
			Programas prog = new Programas();
			prog.setProgPk(fichaTO.getFichaFk());
			prog.setProgVersion(fichaTO.getProyProgVersion());
			prog.setProgOrgFk(fichaTO.getOrgFk());
			prog.setActivo(fichaTO.getActivo());
			prog.setProgFechaCrea(fichaTO.getFechaCrea());
			prog.setProgFechaAct(fichaTO.getFechaAct());
			prog.setProgNombre(fichaTO.getNombre());

			prog.setProgDescripcion(fichaTO.getDescripcion());
			prog.setProgObjetivo(fichaTO.getObjetivo());
			prog.setProgObjPublico(fichaTO.getObjPublico());
			prog.setProgFactorImpacto(fichaTO.getFactorImpacto());
			prog.setProgGrp(fichaTO.getGrp());
			prog.setProgSemaforoAmarillo(fichaTO.getSemaforoAmarillo());
			prog.setProgSemaforoRojo(fichaTO.getSemaforoRojo());
			prog.setAreasTematicasSet(fichaTO.getAreasTematicas());
			prog.setAreasRestringidasSet(fichaTO.getAreasRestringidas());

			prog.setProgUsrPmofedFk(fichaTO.getUsrPmofedFk());
			prog.setProgUsrSponsorFk(fichaTO.getUsrSponsorFk());
			prog.setProgUsrAdjuntoFk(fichaTO.getUsrAdjuntoFk());
			prog.setProgUsrGerenteFk(fichaTO.getUsrGerenteFk());
			prog.setProgCroFk(fichaTO.getCroFk());
			prog.setProgIndices(fichaTO.getProgIndices());
			
			prog.setProgAreaFk(fichaTO.getAreaFk());
			prog.setAreasRestringidasSet(fichaTO.getAreasRestringidas());
			if (fichaTO.getDocumentos() != null) {
				prog.setDocumentosSet(new HashSet<>(fichaTO.getDocumentos()));
				for (Documentos doc : prog.getDocumentosSet()) {
					if (doc.getDocsFecha() == null) {
						doc.setDocsFecha(new Date());
					}
				}
			}

			if (fichaTO.getInteresados() != null) {
				prog.setInteresadosList(fichaTO.getInteresados());
			}

			if (fichaTO.getEstado() == null) {
				fichaTO.setEstado(new Estados(Estados.ESTADOS.PENDIENTE_PMOT.estado_id));
				fichaTO.setEstadoPendiente(new Estados(Estados.ESTADOS.PENDIENTE_PMOT.estado_id));
			}
			prog.setProgEstFk(fichaTO.getEstado());
			prog.setProgEstPendienteFk(fichaTO.getEstadoPendiente());

			prog.setObjetivoEstrategico(fichaTO.getObjetivoEstrategico());
			return prog;

		} else if (fichaTO.isProyecto()) {
			//logger.info("Is Proyecto");
			Proyectos proy = new Proyectos();
			proy.setProyPk(fichaTO.getFichaFk());
			proy.setProyOrgFk(fichaTO.getOrgFk());
			proy.setProyVersion(fichaTO.getProyProgVersion());
			proy.setActivo(fichaTO.getActivo());
			proy.setProyFechaCrea(fichaTO.getFechaCrea());
			proy.setProyFechaAct(fichaTO.getFechaAct());
			proy.setProyNombre(fichaTO.getNombre());
			proy.setProyFechaActPub(fichaTO.getFechaActPub());

			Programas prog = null;
			if (fichaTO.getProgPk() != null) {
				prog = fichaTO.getProgPk() != null ? new Programas(fichaTO.getProgPk()) : null;
				prog.setProgNombre(fichaTO.getProgNombre());
			}
			proy.setProyProgFk(prog);

			proy.setProyDescripcion(fichaTO.getDescripcion());
			proy.setProyObjetivo(fichaTO.getObjetivo());
			proy.setProyObjPublico(fichaTO.getObjPublico());
			proy.setProySituacionActual(fichaTO.getSituacionActual());
			proy.setProyFactorImpacto(fichaTO.getFactorImpacto());
			proy.setProyGrp(fichaTO.getGrp());
			proy.setProySemaforoAmarillo(fichaTO.getSemaforoAmarillo());
			proy.setProySemaforoRojo(fichaTO.getSemaforoRojo());
			proy.setAreasTematicasSet(fichaTO.getAreasTematicas());
			proy.setAreasRestringidasSet(fichaTO.getAreasRestringidas());

			proy.setProyUsrPmofedFk(fichaTO.getUsrPmofedFk());
			proy.setProyUsrSponsorFk(fichaTO.getUsrSponsorFk());
			proy.setProyUsrAdjuntoFk(fichaTO.getUsrAdjuntoFk());
			proy.setProyUsrGerenteFk(fichaTO.getUsrGerenteFk());
			proy.setProyCroFk(fichaTO.getCroFk());
			proy.setProyPreFk(fichaTO.getPreFk());

			proy.setProyAreaFk(fichaTO.getAreaFk());
			proy.setProyProgFk(fichaTO.getProgFk());
			proy.setProyPeso(fichaTO.getPeso());
			proy.setProyIndices(fichaTO.getProyIndices());

			proy.setAreasRestringidasSet(fichaTO.getAreasRestringidas());
			if (fichaTO.getDocumentos() != null) {
				proy.setDocumentosSet(new HashSet<>(fichaTO.getDocumentos()));
				for (Documentos doc : proy.getDocumentosSet()) {
					if (doc.getDocsFecha() == null) {
						doc.setDocsFecha(new Date());
					}
				}
			}

			if (fichaTO.getInteresados() != null) {
				proy.setInteresadosList(fichaTO.getInteresados());
			}

			if (fichaTO.getEstado() == null) {
				fichaTO.setEstado(new Estados(Estados.ESTADOS.PENDIENTE_PMOT.estado_id));
				fichaTO.setEstadoPendiente(new Estados(Estados.ESTADOS.PENDIENTE_PMOT.estado_id));
			}
			proy.setProyEstFk(fichaTO.getEstado());
			proy.setProyEstPendienteFk(fichaTO.getEstadoPendiente());

			if (fichaTO.getFichaOriginal() != null) {
				proy.setProyectoOriginal((Proyectos) fichaTOToProgProy(fichaTO.getFichaOriginal()));
			}

			//Otros Datos
			proy.setProyPublicable(fichaTO.getPublicable());
			proy.setProyOtrosDatos(fichaTO.getOtrosDatos());
//			proy.setProyLatlngFk(fichaTO.getLatlngProy());
			/**
			 * 12-10-17 Bruno: agregadas las localizaciones
			 */
			if (fichaTO.getLatLngProyList() != null) {
				proy.setLatLngProyList(new HashSet<LatlngProyectos>(fichaTO.getLatLngProyList()));
			}

			proy.setObjetivoEstrategico(fichaTO.getObjetivoEstrategico());
			return proy;
		}

		return null;
	}
        
        
    public static ProyReplanificacion replanifTOaReplanif(Proyectos proy, ProyReplanificacionTO replanificacionTO){
        ProyReplanificacion replanifReturn = null;
        
        /*
        *       Se asume como precondicion que si esta en estado EJECUTANDO o PLANIFICADO,
        *   necesariamente no tiene que ser null. Para los otros estados si es null no pasa nada.
        */        
        if(replanificacionTO != null){
            replanifReturn = new ProyReplanificacion();
            
            replanifReturn.setProyreplanGenerarLineaBase(replanificacionTO.getProyreplanHistorial());
            
            replanifReturn.setProyreplanGenerarPresupuesto(replanificacionTO.getProyreplanGenerarPresupuesto());
            //replanifReturn.setProyreplanGenerarProducto(replanificacionTO.getProyreplanGenerarProducto());
            
            replanifReturn.setProyreplanPermitEditar(replanificacionTO.getProyreplanPermitEditar());
                    
            replanifReturn.setProyreplanHistorial(replanificacionTO.getProyreplanHistorial());
            replanifReturn.setProyreplanDesc(replanificacionTO.getProyreplanDesc());
            
            replanifReturn.setProyectoFk(proy);
            replanifReturn.setProyreplanActivo(true);

        }
        return replanifReturn;
    }
        
        
        
    public static void retrocederEstado(Proyectos p, Integer orgPk, ProyReplanificacion replanificacion) throws BusinessException {
            if (p != null) {
                if (p.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)) {
                        p.setProyEstFk(new Estados(Estados.ESTADOS.INICIO.estado_id));
                        p.setProyEstPendienteFk(null);
                } else if (p.isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
                        ReplanificacionValidacion.validar(replanificacion);
                        if (p.getReplanificaciones() == null) {
                                p.setReplanificaciones(new HashSet<ProyReplanificacion>());
                        }
                        replanificacion.setProyectoFk(p);
                        p.getReplanificaciones().add(replanificacion);
                        p.setProyEstFk(new Estados(Estados.ESTADOS.PLANIFICACION.estado_id));
                        p.setProyEstPendienteFk(null);
                } else if (p.isEstado(Estados.ESTADOS.FINALIZADO.estado_id)) {
                        p.setProyEstFk(new Estados(Estados.ESTADOS.EJECUCION.estado_id));
                        p.setProyEstPendienteFk(null);
                } else {
                        throw new BusinessException(MensajesNegocio.ERROR_RETROCEDER_ESTADO);
                }
        } 

    }
        
                
	public static void retrocederEstado(Proyectos p, SsUsuario usuario, Integer orgPk, ProyReplanificacion replanificacion, boolean aprobPMOF, boolean aprobReplanPMOF) throws BusinessException {
		if (p != null && usuario != null) {
                        boolean isEstadoSolCierreHaciaPMOT = p.getProyEstPendienteFk() == null ? false : p.isEstadoPendiente(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOT.estado_id);
                    
			if ((usuario.isUsuarioPMOT(orgPk)) || (isUsuarioPMOF(p, usuario, orgPk) && aprobReplanPMOF)) {
				if (p.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)) {
					p.setProyEstFk(new Estados(Estados.ESTADOS.INICIO.estado_id));
					p.setProyEstPendienteFk(null);
				} else if (p.isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
					ReplanificacionValidacion.validar(replanificacion);
					if (p.getReplanificaciones() == null) {
						p.setReplanificaciones(new HashSet<ProyReplanificacion>());
					}
					replanificacion.setProyectoFk(p);
                                        p.getReplanificaciones().add(replanificacion);
                                        
                                        if((usuario.isUsuarioPMOT(orgPk)) 
                                                    || (isUsuarioPMOF(p, usuario, orgPk) && (aprobReplanPMOF || !replanificacion.isProyreplanPermitEditar()))){
                                                p.setProyEstFk(new Estados(Estados.ESTADOS.PLANIFICACION.estado_id));
                                                p.setProyEstPendienteFk(null);
                                        }else if(isUsuarioPMOF(p, usuario, orgPk) && 
                                                (replanificacion.isProyreplanPermitEditar() && !aprobReplanPMOF)){
                                            p.setProyEstPendienteFk(new Estados(Estados.ESTADOS.PLANIFICACION.estado_id));
                                        //CREO QUE NO PODRÍA ENTRAR AL SIGUIENTE ELSE IF    
                                        }else if(isUsuarioGerenteOAdjunto(p, usuario) ){
                                            p.setProyEstPendienteFk(new Estados(Estados.ESTADOS.PLANIFICACION.estado_id));
                                        }
				} else if (p.isEstado(Estados.ESTADOS.FINALIZADO.estado_id)) {
					p.setProyEstFk(new Estados(Estados.ESTADOS.EJECUCION.estado_id));
					p.setProyEstPendienteFk(null);
				} else {
					throw new BusinessException(MensajesNegocio.ERROR_RETROCEDER_ESTADO);
				}
			} else if (isUsuarioPMOF(p, usuario, orgPk) && !aprobReplanPMOF || isUsuarioGerenteOAdjunto(p, usuario)) {
				if (p.isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
					ReplanificacionValidacion.validar(replanificacion);
					if (p.getReplanificaciones() == null) {
						p.setReplanificaciones(new HashSet<ProyReplanificacion>());
					}
					replanificacion.setProyectoFk(p);
					p.getReplanificaciones().add(replanificacion);
					p.setProyEstPendienteFk(new Estados(Estados.ESTADOS.PLANIFICACION.estado_id));
				} else {
					throw new BusinessException(MensajesNegocio.ERROR_RETROCEDER_ESTADO);
				}
                        } else {
				throw new BusinessException(MensajesNegocio.ERROR_RETROCEDER_ESTADO);
			}
		}
	}

	public static void rechazarCambioEstado(Proyectos p, SsUsuario usuario, Integer orgPk, ProyReplanificacion replanificacion) throws BusinessException {
		if (p != null && usuario != null) {
			p.setProyEstPendienteFk(null);
//            if (usuario.isUsuarioPMOT(orgPk)) {
//                p.setProyEstPendienteFk(null);
//            } else if (isUsuarioPMOF(p, usuario, orgPk)) {
//                p.setProyEstPendienteFk(null);
//            } else {
//                throw new BusinessException(MensajesNegocio.ERROR_RETROCEDER_ESTADO);
//            }
		}
	}

	public static void cambioEstado(Object obj, Estados estado) {
		if (obj != null) {
			if (obj instanceof Programas) {
				Programas p = (Programas) obj;
				p.setProgEstFk(estado);
				p.setProgEstPendienteFk(null);
			} else if (obj instanceof Proyectos) {
				Proyectos p = (Proyectos) obj;
				p.setProyEstFk(estado);
				p.setProyEstPendienteFk(null);
			}
		}
	}

	public static boolean isUsuarioGerenteOAdjunto(Object progProy, SsUsuario usuario) {
		if (progProy != null && usuario != null) {
			if (progProy instanceof Programas) {
				Programas prog = (Programas) progProy;
				return prog.getProgUsrGerenteFk() != null
					&& prog.getProgUsrAdjuntoFk() != null
					&& (usuario.getUsuId().equals(prog.getProgUsrGerenteFk().getUsuId())
					|| usuario.getUsuId().equals(prog.getProgUsrAdjuntoFk().getUsuId()));
			} else if (progProy instanceof Proyectos) {
				Proyectos proy = (Proyectos) progProy;
				return proy.getProyUsrGerenteFk() != null
					&& proy.getProyUsrAdjuntoFk() != null
					&& (usuario.getUsuId().equals(proy.getProyUsrGerenteFk().getUsuId())
					|| usuario.getUsuId().equals(proy.getProyUsrAdjuntoFk().getUsuId()));
			}
		}
		return false;
	}

	public static boolean isUsuarioPMOT(SsUsuario usu, Integer orgPk) {
		if (usu != null && orgPk != null) {
			return usu.isUsuarioPMOT(orgPk);
		}
		return false;
	}

	public static boolean isUsuarioPMOF(Object progProy, SsUsuario usu, Integer orgPk) {
		if (progProy != null && usu != null && orgPk != null) {
			if (progProy instanceof Programas) {
				Programas prog = (Programas) progProy;
				return prog.getProgUsrPmofedFk() != null
					&& usu.getUsuId().equals(prog.getProgUsrPmofedFk().getUsuId())
					&& (usu.isUsuarioPMOF(orgPk) || usu.isUsuarioPMOT(orgPk));
			} else if (progProy instanceof Proyectos) {
				Proyectos proy = (Proyectos) progProy;
				return proy.getProyUsrPmofedFk() != null
					&& usu.getUsuId().equals(proy.getProyUsrPmofedFk().getUsuId())
					&& (usu.isUsuarioPMOF(orgPk) || usu.isUsuarioPMOT(orgPk));
			}
		}
		return false;
	}
	
    public static String obtenerURL(String urlSistema, Proyectos proy) {

        return urlSistema + URI_PROYECTO + proy.getProyPk();
    }

    public static String obtenerURL(String urlSistema, Programas prog) {

        return urlSistema + URI_PROGRAMA + prog.getProgPk();
    }

}
