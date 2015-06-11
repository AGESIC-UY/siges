package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.GastosValidacion;
import com.sofis.data.daos.GastosDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Gastos;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.TipoGasto;
import com.sofis.entities.tipos.MonedaImporteTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "GastoBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class GastosBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @Inject
    private DatosUsuario du;
    @Inject
    private MonedaBean monedaBean;
    @Inject
    private ProyectosBean proyectosBean;

    public Gastos guardarGasto(Gastos gasto, Integer orgPk) {
        if (gasto == null) {
            return null;
        }
        GastosValidacion.validar(gasto);
        GastosDAO dao = new GastosDAO(em);
        try {
            gasto = dao.update(gasto);
            proyectosBean.guardarIndicadores(gasto.getGasProyFk().getProyPk(), orgPk);

        } catch (BusinessException be) {
            //Si es de tipo negocio envía la misma excepción
            throw be;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, ParticipantesBean.class.getName(), "registrarHoras", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException();
            ge.addError(ex.getMessage());
            throw ge;
        }
        return gasto;
    }

    public List<Gastos> obtenerRegistrosGastos(Integer usuId, Integer proyPk, Date fDesde, Date fHasta, TipoGasto tipoGasto, Long desde, Long cant, Integer aprobado) {
        List<Gastos> registrosGastos = new LinkedList<>();
        try {
            GastosDAO dao = new GastosDAO(em);

            List<CriteriaTO> crits = new LinkedList<>();

            if (usuId != null) {
                crits.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gasUsuarioFk.usuId", usuId));
            }
            if (proyPk != null) {
                crits.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gasProyFk.proyPk", proyPk));
            }
            if (fDesde != null) {
                crits.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.GREATEREQUAL, "gasFecha", fDesde));
            }
            if (fHasta != null) {
                crits.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.LESSEQUAL, "gasFecha", fHasta));
            }
            if (tipoGasto != null) {
                crits.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gasTipoFk.tipogasPk", tipoGasto.getTipogasPk()));
            }
            if (aprobado != null) {
                if (aprobado.equals(0)) {
                    CriteriaTO aproFalse = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gasAprobado", Boolean.FALSE);
                    CriteriaTO aproNull = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NULL, "gasAprobado", 1);
                    crits.add(CriteriaTOUtils.createORTO(aproFalse, aproNull));
                } else if (aprobado.equals(1)) {
                    crits.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "gasAprobado", Boolean.TRUE));
                }
            }

            if (crits.isEmpty()) {
                registrosGastos = dao.findAll(Gastos.class);
            } else {
                CriteriaTO crit = CriteriaTOUtils.createANDTO(crits.toArray(new CriteriaTO[0]));
                String[] orderBy1 = new String[]{"gasFecha", "gasUsuarioFk.usuPrimerNombre", "gasUsuarioFk.usuPrimerApellido", "gasProyFk.proyNombre"};
                boolean[] orderBy2 = new boolean[]{false, true, true, true};
                registrosGastos = dao.findEntityByCriteria(Gastos.class, crit, orderBy1, orderBy2, desde, cant);
            }
            return registrosGastos;
        } catch (BusinessException be) {
            //Si es de tipo negocio envía la misma excepción
            throw be;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, ParticipantesBean.class.getName(), "registrarHoras", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException();
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public MonedaImporteTO[] obtenerGastosPorProyYMon(Integer proyPk, Integer usuId, Boolean aprobado) {
        if (proyPk != null && usuId != null) {
            List<Moneda> listMoneda = monedaBean.obtenerMonedas();
            if (listMoneda != null) {
                MonedaImporteTO[] result = new MonedaImporteTO[listMoneda.size()];
                GastosDAO dao = new GastosDAO(em);
                int ind = 0;

                for (Moneda moneda : listMoneda) {
                    Double monto = dao.obtenerGastosPorProyYMon(proyPk, usuId, moneda.getMonPk(), aprobado);
                    result[ind] = new MonedaImporteTO(moneda, monto);
                    ind++;
                }
                return result;
            }
        }
        return null;
    }
    
    public Double obtenerGastosPorProyYMon(Integer proyPk, Integer usuId, Integer monPk, Boolean aprobado) {
        GastosDAO dao = new GastosDAO(em);
        return dao.obtenerGastosPorProyYMon(proyPk, usuId, monPk, aprobado);
    }

    public List<Gastos> guardarGastos(List<Gastos> listaGastos, Integer orgPk) {
        if (CollectionsUtils.isNotEmpty(listaGastos)) {
            for (Gastos gasto : listaGastos) {
                if (gasto.getGasAprobado() == null) {
                    gasto.setGasAprobado(false);
                }
                guardarGasto(gasto, orgPk);
            }
        }
        return listaGastos;
    }

    /**
     * Retorna true si el usuario tiene gastos aprobados para dicho proyecto.
     *
     * @param usuId
     * @param proyPk
     * @return True si tiene gastos.
     */
    public boolean usuTieneGastosAprob(Integer usuId, Integer proyPk) {
        if (usuId != null && proyPk != null) {
            GastosDAO dao = new GastosDAO(em);
            return dao.usuTieneHorasAprob(usuId, proyPk);
        }
        return false;
    }

    public List<Moneda> obtenerMonedasPorProy(Integer proyPk) {
        if (proyPk != null) {
            GastosDAO dao = new GastosDAO(em);
            return dao.obtenerMonedasPorProy(proyPk);
        }
        return null;
    }

    public Double obtenerImpTotalGastosPorProy(Integer proyPk, Integer monPk, Integer mes, Integer anio, Boolean aprobado) {
        if (proyPk != null && monPk != null) {
            GastosDAO dao = new GastosDAO(em);
            return dao.obtenerImpTotalGastosPorProy(proyPk, monPk, mes, anio, aprobado);
        }
        return null;
    }

    /**
     * Si asc es true retorna la primera fecha, si no la última.
     *
     * @param proyPk
     * @param asc
     * @return
     */
    public Date obtenerPrimeraFecha(Integer proyPk, boolean asc) {
        if (proyPk != null) {
            GastosDAO dao = new GastosDAO(em);
            return dao.obtenerPrimeraFecha(proyPk, asc);
        }
        return null;
    }

    public Gastos obtenerGastoPorPk(Integer gastoPk) {
        if (gastoPk != null) {
            GastosDAO dao = new GastosDAO(em);
            try {
                return dao.findById(Gastos.class, gastoPk);
            } catch (DAOGeneralException ex) {
                Logger.getLogger(GastosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void eliminarGastos(Integer gastoPk) {
        if (gastoPk != null) {
            GastosDAO dao = new GastosDAO(em);
            try {
                Gastos gasto = obtenerGastoPorPk(gastoPk);
                dao.delete(gasto, du.getCodigoUsuario(), du.getOrigen());
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_GASTO_ELIMINAR);
                throw be;
            }
        }
    }
}
