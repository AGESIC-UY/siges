package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.AreaConocimiento;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.LeccionesAprendidas;
import com.sofis.entities.tipos.FiltroLeccAprTO;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import com.sofis.sofisform.to.AND_TO;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.sofisform.to.OR_TO;
import com.sofis.utils.CriteriaTOUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class LeccionesAprendidasDAO extends HibernateJpaDAOImp<LeccionesAprendidas, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public LeccionesAprendidasDAO(EntityManager em) {
        super(em);
    }

    public List<LeccionesAprendidas> buscarLeccionesPorFiltro(FiltroLeccAprTO filtro, Integer orgPk) {
        // Organismo
        CriteriaTO criteriaOrga = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "lecaprOrgFk.orgPk", orgPk);
        // Activos
        CriteriaTO criteriaActivoTrue = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "activo", Boolean.TRUE);
        CriteriaTO criteriaActivoNull = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NULL, "activo", 1);
        OR_TO criteriaActivo = new OR_TO(criteriaActivoTrue, criteriaActivoNull);

        CriteriaTO criteriaFiltroProg = null;
        if (filtro != null) {
            criteriaFiltroProg = crearFiltroLeccAprendidas(filtro);
        }

        CriteriaTO criteria;
        if (criteriaFiltroProg != null) {
            criteria = CriteriaTOUtils.createANDTO(criteriaOrga, criteriaActivo, criteriaFiltroProg);
        } else {
            criteria = CriteriaTOUtils.createANDTO(criteriaOrga, criteriaActivo);
        }

        List<LeccionesAprendidas> result;
        try {
            result = this.findEntityByCriteria(LeccionesAprendidas.class, criteria, new String[]{"lecaprFecha"}, new boolean[]{false}, null, null);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException();
            te.addError(ex.getMessage());
            throw te;
        }

        return result;
    }

    private CriteriaTO crearFiltroLeccAprendidas(FiltroLeccAprTO filtro) {
        if (filtro != null) {
            List<CriteriaTO> criterias = new ArrayList<>();

            if (filtro.getAnio() != null && filtro.getAnio() > 0) {
                MatchCriteriaTO anioDesde = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.GREATEREQUAL, "lecaprFecha", filtro.getAnioDesde());
                MatchCriteriaTO anioHasta = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.LESSEQUAL, "lecaprFecha", filtro.getAnioHasta());
                AND_TO anioAnd = new AND_TO(anioDesde, anioHasta);
                criterias.add(anioAnd);
            }

            if (CollectionsUtils.isNotEmpty(filtro.getAreasConocimiento())) {
                List<CriteriaTO> areaConCriteria = new ArrayList<>();
                for (AreaConocimiento areaCon : filtro.getAreasConocimiento()) {
                    MatchCriteriaTO areaCon1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "areaConocimientosSet.conPk", areaCon.getConPk());
                    areaConCriteria.add(areaCon1);
                }

                if (areaConCriteria.size() > 1) {
                    criterias.add(CriteriaTOUtils.createORTO(areaConCriteria.toArray(new CriteriaTO[]{})));
                } else {
                    criterias.add(areaConCriteria.get(0));
                }
            }

            if (CollectionsUtils.isNotEmpty(filtro.getAreasTematicas())) {
                List<CriteriaTO> areaTemCriteria = new ArrayList<>();
                for (AreasTags areaTem : filtro.getAreasTematicas()) {
                    MatchCriteriaTO areaTag1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "lecaprProyFk.areasTematicasSet.arastagPk", areaTem.getArastagPk());
                    areaTemCriteria.add(areaTag1);
                }

                if (areaTemCriteria.size() > 1) {
                    criterias.add(CriteriaTOUtils.createORTO(areaTemCriteria.toArray(new CriteriaTO[]{})));
                } else {
                    criterias.add(areaTemCriteria.get(0));
                }
            }

            if (filtro.getInteresado() != null) {
                MatchCriteriaTO interesado = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "lecaprProyFk.interesadosList.intOrgaFk.orgaPk", filtro.getInteresado().getOrgaPk());
                criterias.add(interesado);
            }

            if (filtro.getProgPk() != null) {
                MatchCriteriaTO prog = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "lecaprProyFk.proyProgFk.progPk", filtro.getProgPk());
                criterias.add(prog);
            }

            if (filtro.getProveedor() != null) {
                MatchCriteriaTO proveedor = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "lecaprProyFk.interesadosList.intOrgaFk.orgaPk", filtro.getProveedor().getOrgaPk());
                MatchCriteriaTO isProveedor = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "lecaprProyFk.interesadosList.intOrgaFk.orgaProveedor", Boolean.TRUE);
                AND_TO provAnd = new AND_TO(proveedor, isProveedor);
                criterias.add(provAnd);
            }

            if (filtro.getProyPk() != null) {
                MatchCriteriaTO nombre = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "lecaprProyFk.proyPk", filtro.getProyPk());
                criterias.add(nombre);
            }

            if (!StringsUtils.isEmpty(filtro.getTexto())) {
                MatchCriteriaTO texto = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "lecaprDesc", filtro.getTexto());
                criterias.add(texto);
            }

            if (filtro.getTipo() != null) {
                MatchCriteriaTO texto = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "lecaprTipoFk.tipolecPk", filtro.getTipo().getTipolecPk());
                criterias.add(texto);
            }

            if (criterias.size() == 1) {
                return criterias.get(0);
            }
            if (criterias.size() > 1) {
                AND_TO criteria = CriteriaTOUtils.createANDTO(criterias.toArray(new CriteriaTO[]{}));
                return criteria;
            }
        }

        return null;
    }
}
