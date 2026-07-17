package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.GrupoTrabajoUsuarioDAO;
import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.GrupoTrabajo;
import com.sofis.entities.data.GrupoTrabajoUsuario;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.AND_TO;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named
@Stateless(name = "GrupoTrabajoUsuarioBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class GrupoTrabajoUsuarioBean {

    private static final Logger LOGGER = Logger.getLogger(GrupoTrabajoUsuarioBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    public List<GrupoTrabajoUsuario> listarPorOrgYUsuario(Integer orgPk, Integer usuPk) throws GeneralException {

        GrupoTrabajoUsuarioDAO dao = new GrupoTrabajoUsuarioDAO(em);
        try {

            CriteriaTO criteria;
            CriteriaTO criteriaOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gtuGrupoFk.gtbOrgFk.orgPk", orgPk);
            CriteriaTO criteriaUsu = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gtuUsuarioFk.usuId", usuPk);
            criteria = CriteriaTOUtils.createANDTO(criteriaOrg, criteriaUsu);

            List<GrupoTrabajoUsuario> resultado = dao.findEntityByCriteria(GrupoTrabajoUsuario.class, criteria, new String[]{}, new boolean[]{}, null, null);

            return resultado;

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }

    }

    public List<GrupoTrabajo> listarPorOrgYUsuario(int orgaPk, int usuarioPk, Map<String, Object> mapFiltro) {
        GrupoTrabajoUsuarioDAO dao = new GrupoTrabajoUsuarioDAO(em);
        List<GrupoTrabajo> listaReturn = new ArrayList();
        try {
            List<CriteriaTO> criterios = new ArrayList<>();
            CriteriaTO criteriaOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gtuGrupoFk.gtbOrgFk.orgPk", orgaPk);
            CriteriaTO criteriaUsu = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gtuUsuarioFk.usuId", usuarioPk);
            criterios.add(criteriaOrg);
            criterios.add(criteriaUsu);

            String nombre = mapFiltro != null ? (String) mapFiltro.get("nombre") : null;
            if (!StringsUtils.isEmpty(nombre)) {
//		MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "fueNombre", nombre);
                CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("gtuGrupoFk.gtbNombre", nombre);
                criterios.add(criterio);
            }

            if (mapFiltro.get("habilitada") != null) {
                criterios.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gtuGrupoFk.gtbHabilitado", (Boolean) mapFiltro.get("habilitada")));
            }

            CriteriaTO criteria = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));

            List<GrupoTrabajoUsuario> resultado = dao.findEntityByCriteria(GrupoTrabajoUsuario.class, criteria, new String[]{}, new boolean[]{}, null, null);

            for (GrupoTrabajoUsuario gtu : resultado) {
                listaReturn.add(gtu.getGtuGrupoFk());
            }

        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
        return listaReturn;
    }

    public void eliminarGrupo(Integer grupoPk) {
         String queryStr = "delete from grupos_trabajo_usuarios where gtu_grupo_fk =" + grupoPk;
        Query query = em.createNativeQuery(queryStr);
        int result = query.executeUpdate();
    }
    
     public void inserTarGrupo(Integer grupoPk, Integer opPk) {
        String queryStr = "INSERT INTO grupos_trabajo_usuarios\n"
                + "(gtu_grupo_fk, gtu_usuario_fk)\n"
                + "VALUES(" + grupoPk + ", " + opPk + ")";
        Query query = em.createNativeQuery(queryStr);
        int result = query.executeUpdate();

    }

}
