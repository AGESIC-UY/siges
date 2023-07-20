package com.sofis.business.ejbs;

import com.sofis.business.validations.CategoriaProyectosValidacion;
import com.sofis.data.daos.CategoriaProyectosDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.CategoriaProyectos;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.enums.TipoCategoria;
import com.sofis.entities.tipos.FiltroCategoriaProyectosTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Stateless(name = "CategoriaProyectosBean")
@LocalBean
public class CategoriaProyectosBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(CategoriaProyectosBean.class.getName());

    @Inject
    private DatosUsuario du;
    @Inject
    private ExportarVisualizadorBean exportarVisualizadorBean;
	
    public CategoriaProyectos guardar(CategoriaProyectos cp) throws BusinessException {
        if (cp != null) {
            CategoriaProyectosValidacion.validar(cp);
            validarDuplicado(cp);

            CategoriaProyectosDAO dao = new CategoriaProyectosDAO(em);
            try {
                return dao.update(cp, du.getCodigoUsuario(),du.getOrigen());

            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_AREAS_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    @Deprecated
    public List<CategoriaProyectos> obtenerTodas() {
        CategoriaProyectosDAO dao = new CategoriaProyectosDAO(em);
        try {
            return dao.findAll(CategoriaProyectos.class);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CAT_PROY_OBTENER);
            throw be;
        }
    }

    public List<CategoriaProyectos> obtenerTodasPorOrg(Integer orgPk) {
        FiltroCategoriaProyectosTO filtro = new FiltroCategoriaProyectosTO();
        filtro.setOrgPk(orgPk);
        return obtenerPorFiltro(filtro);
    }

    public List<CategoriaProyectos> obtenerPorFiltro(FiltroCategoriaProyectosTO filtro) {
        CategoriaProyectosDAO dao = new CategoriaProyectosDAO(em);
        try {

            List<CriteriaTO> criterios = new ArrayList<>();

            if (filtro.getOrgPk() != null) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "catOrgFk.orgPk", filtro.getOrgPk());
                criterios.add(criterio);
            }

            if (filtro.getTipo() != null) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "catTipo", filtro.getTipo());
                criterios.add(criterio);
            }

            if (filtro.getActivos() != null) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "catProyActivo", true);
                criterios.add(criterio);
            }

            String[] orderBy = new String[]{"catProyNombre"};
            boolean[] asc = new boolean[]{true};

            if (criterios.size() > 1) {
                CriteriaTO criterio = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[criterios.size()]));
                return dao.findEntityByCriteria(CategoriaProyectos.class, criterio, orderBy, asc, null, null);
            } else if (criterios.size() == 1) {
                CriteriaTO criterio = criterios.get(0);
                return dao.findEntityByCriteria(CategoriaProyectos.class, criterio, orderBy, asc, null, null);
            } else {
                return dao.findAll(CategoriaProyectos.class, "catProyNombre");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CAT_PROY_OBTENER);
            throw be;
        }
    }

    public List<CategoriaProyectos> obtenerPrimarias(Integer orgPk) {
        FiltroCategoriaProyectosTO filtro = new FiltroCategoriaProyectosTO();
        filtro.setTipo(TipoCategoria.TEMA_PRIMARIA.cod);
        filtro.setActivos(Boolean.TRUE);
        filtro.setOrgPk(orgPk);
        return obtenerPorFiltro(filtro);
    }

    public List<CategoriaProyectos> obtenerSecundarias(Integer orgPk) {
        FiltroCategoriaProyectosTO filtro = new FiltroCategoriaProyectosTO();
        filtro.setTipo(TipoCategoria.CATEGORIA_SECUNDARIA.cod);
        filtro.setActivos(Boolean.TRUE);
        filtro.setOrgPk(orgPk);
        return obtenerPorFiltro(filtro);
    }

    public CategoriaProyectos obtenerPorId(Integer catPk) {
        CategoriaProyectosDAO dao = new CategoriaProyectosDAO(em);
        try {
            return dao.findById(CategoriaProyectos.class, catPk);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CAT_PROY_OBTENER);
            throw be;
        }
    }

    public CategoriaProyectos obtenerPorCodigoYOrg(String codigo, Integer orgPk) {
        CategoriaProyectosDAO dao = new CategoriaProyectosDAO(em);
        try {

            MatchCriteriaTO criterioCod = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "catProyCodigo", codigo);
            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "catOrgFk.orgPk", orgPk);

            String[] orderBy = new String[]{"catProyNombre"};
            boolean[] asc = new boolean[]{true};

            CriteriaTO criterio = CriteriaTOUtils.createANDTO(criterioCod, criterioOrg);
            List<CategoriaProyectos> result = dao.findEntityByCriteria(CategoriaProyectos.class, criterio, orderBy, asc, null, null);
            return (CategoriaProyectos) DAOUtils.obtenerSingleResult(result);

        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CAT_PROY_OBTENER);
            throw be;
        }
    }

    private void validarDuplicado(CategoriaProyectos cp) {
        List<CategoriaProyectos> list = obtenerTodas();
        if (CollectionsUtils.isNotEmpty(list)) {
            for (CategoriaProyectos cpPersit : list) {
                if (!cpPersit.getCatProyPk().equals(cp.getCatProyPk())
                        && cpPersit.getCatProyCodigo().equals(cp.getCatProyCodigo())
                        && ((cpPersit.getCatOrgFk() == null && cp.getCatOrgFk() == null)
                        || (cpPersit.getCatOrgFk() != null && cp.getCatOrgFk() != null && cpPersit.getCatOrgFk().getOrgPk().equals(cp.getCatOrgFk().getOrgPk())))) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_CAT_PROY_CODIGO_DUPLICADO);
                    throw be;
                }
            }
        }
    }

    public void actualizarCategorias(Integer orgPk) throws BusinessException {
		
        List<CategoriaProyectos> listCatProy = exportarVisualizadorBean.obtenerCategorias(orgPk);
        if (listCatProy != null && !listCatProy.isEmpty()) {
            List<CategoriaProyectos> listCatPersit = this.obtenerTodasPorOrg(orgPk);
            if (listCatPersit != null) {
                for (CategoriaProyectos cp : listCatPersit) {
                    cp.setCatProyActivo(false);
                    this.guardar(cp);
                }
            }
                
            for (CategoriaProyectos cp : listCatProy) {
                CategoriaProyectos cpPersit = this.obtenerPorCodigoYOrg(cp.getCatProyCodigo(), orgPk);
                if (cpPersit == null) {
                    cpPersit = new CategoriaProyectos();
                }
                cpPersit.setCatProyCodigo(cp.getCatProyCodigo());
                cpPersit.setCatProyNombre(cp.getCatProyNombre());
                cpPersit.setCatTipo(cp.getCatTipo());
                cpPersit.setCatProyActivo(cp.getCatProyActivo());
                cpPersit.setCatIcono(cp.getCatIcono());
                cpPersit.setCatIconoMarker(cp.getCatIconoMarker());
                cpPersit.setCatOrgFk(new Organismos(orgPk));
                this.guardar(cpPersit);
            }
        }
    }
}
