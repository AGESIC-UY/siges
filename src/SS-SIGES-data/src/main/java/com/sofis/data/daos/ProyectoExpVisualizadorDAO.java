package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.codigueras.EstadoPublicacionCodigos;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.ProyectoExpVisualizador;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.FiltroExpVisuaTO;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class ProyectoExpVisualizadorDAO extends HibernateJpaDAOImp<ProyectoExpVisualizador, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ProyectoExpVisualizadorDAO.class.getName()); 

    public ProyectoExpVisualizadorDAO(EntityManager em) {
            super(em);
    }

    public List<FichaTO> buscarPorFiltroExpVisua(FiltroExpVisuaTO filtro) {

            List<CriteriaTO> citeriaList = new ArrayList<>();

            /**
             * Filtro los proyectos activos
             */
            citeriaList.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "activo", Boolean.TRUE));

            /**
             * Filtro por organismo
             */
            if (filtro.getOrgPk() != null) {
                    citeriaList.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyOrgFk.orgPk", filtro.getOrgPk()));
            }

            /**
             * Filtro por nombre
             */
            if (!StringsUtils.isEmpty(filtro.getNombre())) {
//	    MatchCriteriaTO criterioNombre = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "proyNombre", filtro.getNombre());
                    CriteriaTO criterioNombre = DAOUtils.createMatchCriteriaTOString("proyNombre", filtro.getNombre());
                    citeriaList.add(criterioNombre);
            }

            /**
             * Filtro por área
             */
            if (filtro.getArea() != null) {
                    MatchCriteriaTO criterioEP = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyAreaFk.areaPk", filtro.getArea().getAreaPk());
                    citeriaList.add(criterioEP);
            }

            /**
             * 14-12-2016 No es para publicar: debe mostrar todos los proyectos que
             * no tienen marcado el check de “Publicar” y no están finalizados. No
             * debe permitir publicar. Pendiente de Cargar Datos: Son los proyectos
             * que tienen el check de “Publicar” marcado pero les falta alguno de
             * los datos obligatorios. No debe permitir publicar. Pendiente de
             * Publicar: son todos los proyectos que tienen marcado el check de
             * publicar y tienen cargados sus datos obligatorios para la publicación
             * y están en una de estas dos situaciones (incluye los que están
             * finalizados): No fueron publicados nunca La fecha de la última
             * publicación es anterior a la de la última actualización Publicados:
             * son todos los proyectos que no están finalizados, fueron publicados
             * alguna vez y la fecha de publicación es igual o mayor a la fecha de
             * su última actualización.
             */
            if (filtro.getEstadoPublicacion() != null) {

                    if (EstadoPublicacionCodigos.NO_ES_PARA_PUBLICAR.equalsIgnoreCase(filtro.getEstadoPublicacion().getEstPubCodigo())) {
                            citeriaList.add(
                                    CriteriaTOUtils.createMatchCriteriaTO(
                                            MatchCriteriaTO.types.NOT_EQUALS, "proyEstFk.estPk", Estados.ESTADOS.FINALIZADO.estado_id
                                    )
                            );
                    } else {
                            citeriaList.add(
                                    CriteriaTOUtils.createMatchCriteriaTO(
                                            MatchCriteriaTO.types.EQUALS, "proyPublicable", Boolean.TRUE
                                    )
                            );
                    }
            }

            if (CollectionsUtils.isNotEmpty(filtro.getEstados())) {
                    List<CriteriaTO> estadosList = new ArrayList<>();
                    for (Estados estado : filtro.getEstados()) {
                            MatchCriteriaTO criterioEst = CriteriaTOUtils.createMatchCriteriaTO(
                                    MatchCriteriaTO.types.EQUALS, "proyEstFk.estPk", estado.getEstPk());
                            estadosList.add(criterioEst);
                    }
                    CriteriaTO estCritTO = CriteriaTOUtils.createORTO(estadosList.toArray(new CriteriaTO[0]));
                    citeriaList.add(estCritTO);
            }

            if (filtro.getFechaDesde() != null) {
                    MatchCriteriaTO criterioDesde = CriteriaTOUtils.createMatchCriteriaTO(
                            MatchCriteriaTO.types.GREATEREQUAL, "proyFechaActPub", filtro.getFechaDesde());
                    citeriaList.add(criterioDesde);
            }

            if (filtro.getFechaHasta() != null) {
                /*
                *   22-06-2018 Nico: Se agrega un dia más a la "fechaHasta" porque en la ocnsulta no se estaba tomando el equal.
                */
                
                    Date auxFechaHasta = filtro.getFechaHasta();
                    Calendar auxCalendar = Calendar.getInstance();
                    auxCalendar.setTime(auxFechaHasta);
                    auxCalendar.add(Calendar.DATE, 1);
                    auxFechaHasta = auxCalendar.getTime();
                
                    MatchCriteriaTO criterioHasta = CriteriaTOUtils.createMatchCriteriaTO(
                            MatchCriteriaTO.types.LESSEQUAL, "proyFechaActPub", auxFechaHasta);
                    citeriaList.add(criterioHasta);
            }

            if (filtro.getEstadoPublicacion() != null) {

                    if (filtro.getEstadoPublicacion().getEstPubCodigo().equalsIgnoreCase(EstadoPublicacionCodigos.PENDIENTE_PUBLICAR)) {
                            MatchCriteriaTO criterioEP = CriteriaTOUtils.createMatchCriteriaTO(
                                    MatchCriteriaTO.types.EQUALS, "proyOtrosDatos.proyOtrEstPubFk.estPubPk", filtro.getEstadoPublicacion().getEstPubPk());
                            //PUBLICADO
                            MatchCriteriaTO criterioPublicados = CriteriaTOUtils.createMatchCriteriaTO(
                                    MatchCriteriaTO.types.EQUALS, "proyOtrosDatos.proyOtrEstPubFk.estPubCodigo", EstadoPublicacionCodigos.PUBLICADO);

                            citeriaList.add(CriteriaTOUtils.createORTO(criterioEP, criterioPublicados));

                    } else {
                            MatchCriteriaTO criterioEP = CriteriaTOUtils.createMatchCriteriaTO(
                                    MatchCriteriaTO.types.EQUALS, "proyOtrosDatos.proyOtrEstPubFk.estPubPk", filtro.getEstadoPublicacion().getEstPubPk());
                            citeriaList.add(criterioEP);
                    }

            }

            if (filtro.getActualizados()) {
                    //FECHA ACTUALIZACION > a ultima publicacion
                    MatchCriteriaTO criterioFechaPub = CriteriaTOUtils.createMatchCriteriaTO(
                            MatchCriteriaTO.types.SUBQUERY, "proyFechaActPub",
                            " >= (select CASE WHEN (max(h.proyPublicaFecha) is null) THEN '1971-01-01' ELSE max(h.proyPublicaFecha) end from ProyPublicaHist h where h.proyectoFk = a.proyPk)");
                    citeriaList.add(criterioFechaPub);
            }

            CriteriaTO condicion = null;
            if (citeriaList.size() == 1) {
                    condicion = citeriaList.get(0);
            } else {
                    condicion = CriteriaTOUtils.createANDTO(citeriaList.toArray(new CriteriaTO[0]));
            }

            try {
                    String[] orderBy = new String[]{"proyNombre"};
                    boolean[] ascending = new boolean[]{true};
                    List<ProyectoExpVisualizador> proyectosResult = super.findEntityByCriteria(ProyectoExpVisualizador.class, condicion, orderBy, ascending, null, null);

                    if (CollectionsUtils.isNotEmpty(proyectosResult)) {
                            List<FichaTO> listFicha = new ArrayList<>();
                            for (ProyectoExpVisualizador proy : proyectosResult) {
                                    FichaTO f = new FichaTO(proy);
                                    listFicha.add(f);
                            }
                            return listFicha;
                    }
            } catch (DAOGeneralException ex) {
                    logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
            return null;
    }

}
