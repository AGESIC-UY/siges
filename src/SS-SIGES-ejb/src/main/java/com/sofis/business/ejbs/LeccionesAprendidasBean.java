package com.sofis.business.ejbs;

import com.sofis.business.validations.LeccAprendidasValidacion;
import com.sofis.data.daos.LeccionesAprendidasDAO;
import com.sofis.data.daos.TipoLeccionDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.AreaConocimiento;
import com.sofis.entities.data.LeccionesAprendidas;
import com.sofis.entities.data.TipoLeccion;
import com.sofis.entities.tipos.FiltroLeccAprTO;
import com.sofis.entities.tipos.LeccAprendidasTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
@Stateless(name = "LeccionesAprendidasBean")
@LocalBean
public class LeccionesAprendidasBean {

    @Inject
    private DatosUsuario du;
    @Inject
    private AreasConocimientoBean areasConocimientoBean;
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(LeccionesAprendidasBean.class.getName());

    //private String usuario;
    //private String origen;

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    private LeccionesAprendidas guardar(LeccionesAprendidas leccApre) {
        LeccAprendidasValidacion.validar(leccApre);
        LeccionesAprendidasDAO dao = new LeccionesAprendidasDAO(em);

        try {
            leccApre = dao.update(leccApre, du.getCodigoUsuario(),du.getOrigen());

        } catch (BusinessException be) {
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }

        return leccApre;
    }

    public List<TipoLeccion> obtenerTipoLecciones() {
        try {
            TipoLeccionDAO dao = new TipoLeccionDAO(em);
            return dao.findAll(TipoLeccion.class);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public List<LeccAprendidasTO> buscarLeccionesPorFiltro(FiltroLeccAprTO filtro, Integer orgPk) {

        LeccionesAprendidasDAO dao = new LeccionesAprendidasDAO(em);
        List<LeccionesAprendidas> result = null;

        try {
            result = dao.buscarLeccionesPorFiltro(filtro, orgPk);
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return convertLeccAprendidasTO(result);
    }

    public List<LeccAprendidasTO> convertLeccAprendidasTO(List<LeccionesAprendidas> listLeccApr) {
        if (listLeccApr != null) {
            List<LeccAprendidasTO> result = new ArrayList<>();
            for (LeccionesAprendidas leccApr : listLeccApr) {
                result.add(convertLeccAprendidasTO(leccApr));
            }
            return result;
        }
        return null;
    }

    public LeccAprendidasTO convertLeccAprendidasTO(LeccionesAprendidas leccApr) {
        if (leccApr != null) {
            LeccAprendidasTO leccAprTO = new LeccAprendidasTO();
            leccAprTO.setLecaprFk(leccApr.getLecaprPk() != null ? leccApr.getLecaprPk() : null);
            leccAprTO.setLecaprFecha(leccApr.getLecaprFecha() != null ? leccApr.getLecaprFecha() : null);
            leccAprTO.setLecaprTexto(leccApr.getLecaprDesc() != null ? leccApr.getLecaprDesc() : null);
            leccAprTO.setLecaprTipoFk(leccApr.getLecaprTipoFk() != null ? leccApr.getLecaprTipoFk().getTipolecPk() : null);

            leccAprTO.setAreaConocimientosSet(leccApr.getAreaConocimientosSet() != null ? leccApr.getAreaConocimientosSet() : null);
            if (leccAprTO.getAreaConocimientosSet() == null || leccAprTO.getAreaConocimientosSet().isEmpty()) {
                Set<AreaConocimiento> areaConSet = new HashSet<>(areasConocimientoBean.obtenerAreasConPorLeccPk(leccApr.getLecaprPk()));
                leccAprTO.setAreaConocimientosSet(areaConSet);
            }

            if (leccApr.getUsuarioFk() != null) {
                leccAprTO.setLecaprUsuNombre(leccApr.getUsuarioFk().getUsuNombreApellido());
                leccAprTO.setUsuarioFk(leccApr.getUsuarioFk().getUsuId());
            }

            if (leccApr.getLecaprProyFk() != null) {
                leccAprTO.setLecaprProyFk(leccApr.getLecaprProyFk().getProyPk());
                leccAprTO.setLecaprProyNombre(leccApr.getLecaprProyFk().getProyNombre());
            }
            return leccAprTO;
        }
        return null;
    }

    public LeccionesAprendidas guardarLeccion(LeccionesAprendidas leccAprendida) {
        if (leccAprendida != null && leccAprendida.getLecaprFecha() == null) {
            leccAprendida.setLecaprFecha(new Date());
        }

        return guardar(leccAprendida);
    }

    public LeccionesAprendidas obtenerLeccionPorPk(Integer leccAprPk) {
        LeccionesAprendidasDAO dao = new LeccionesAprendidasDAO(em);
        try {
            return dao.findById(LeccionesAprendidas.class, leccAprPk);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public LeccionesAprendidas inactivarLeccionPorPk(Integer leccAprPk) {
        LeccionesAprendidas leccApr = obtenerLeccionPorPk(leccAprPk);
        leccApr.setActivo(false);
        return guardar(leccApr);
    }
}
