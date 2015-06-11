package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.tipos.FiltroMisTareasTO;
import com.sofis.entities.tipos.MisTareasTO;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class EntregablesDAO extends HibernateJpaDAOImp<Entregables, Integer> implements Serializable {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final long serialVersionUID = 1L;

    public EntregablesDAO(EntityManager em) {
        super(em);
    }

    public void deleteEntregables(Integer entPk) {
        String queryStr = "DELETE FROM Entregables e WHERE e.entPk = :entPk";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("entPk", entPk);

        query.executeUpdate();
    }

    public List<Entregables> obtenerEntregablesPorProy(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT e FROM Proyectos p, Cronogramas c, Entregables e"
                    + " WHERE p.proyPk = :proyPk"
                    + " AND p.proyCroFk.croPk = c.croPk"
                    + " AND c.croPk = e.entCroFk.croPk";
            Query q = super.getEm().createQuery(queryStr);
            q.setParameter("proyPk", proyPk);
            try {
                return q.getResultList();
            } catch (Exception w) {
                logger.log(Level.SEVERE, w.getMessage(), w);
                TechnicalException te = new TechnicalException();
                te.addError(w.getMessage());
                throw te;
            }
        }
        return null;
    }

    public boolean tieneDependencias(Integer entPk) {
        if (entPk != null) {
            // Dependencia en Pagos.
            String queryStr = "SELECT COUNT(p.pagPk) AS cant FROM Pagos p"
                    + " WHERE p.entregables.entPk = :entPk";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("entPk", entPk);
            try {
                Long cant = (Long) query.getSingleResult();
                if (cant > 0) {
                    return true;
                }
            } catch (Exception w) {
                logger.log(Level.SEVERE, w.getMessage(), w);
            }

            // Dependencia en RegistrosHoras.
            queryStr = "SELECT COUNT(p.rhPk) AS cant FROM RegistrosHoras p"
                    + " WHERE p.rhEntregableFk.entPk = :entPk";
            query = super.getEm().createQuery(queryStr);
            query.setParameter("entPk", entPk);
            try {
                Long cant = (Long) query.getSingleResult();
                if (cant > 0) {
                    return true;
                }
            } catch (Exception w) {
                logger.log(Level.SEVERE, w.getMessage(), w);
            }

            // Dependencia en Participantes.
            queryStr = "SELECT COUNT(p.partPk) AS cant FROM Participantes p"
                    + " WHERE p.partEntregablesFk.entPk = :entPk";
            query = super.getEm().createQuery(queryStr);
            query.setParameter("entPk", entPk);
            try {
                Long cant = (Long) query.getSingleResult();
                if (cant > 0) {
                    return true;
                }
            } catch (Exception w) {
                logger.log(Level.SEVERE, w.getMessage(), w);
            }

            // Dependencia en Productos.
            queryStr = "SELECT COUNT(p.prodPk) AS cant FROM Productos p"
                    + " WHERE p.prodEntregableFk.entPk = :entPk";
            query = super.getEm().createQuery(queryStr);
            query.setParameter("entPk", entPk);
            try {
                Long cant = (Long) query.getSingleResult();
                if (cant > 0) {
                    return true;
                }
            } catch (Exception w) {
                logger.log(Level.SEVERE, w.getMessage(), w);
            }

            // Dependencia en Documentos.
            queryStr = "SELECT COUNT(p.docsPk) AS cant FROM Documentos p"
                    + " WHERE p.docsEntregable.entPk = :entPk";
            query = super.getEm().createQuery(queryStr);
            query.setParameter("entPk", entPk);
            try {
                Long cant = (Long) query.getSingleResult();
                if (cant > 0) {
                    return true;
                }
            } catch (Exception w) {
                logger.log(Level.SEVERE, w.getMessage(), w);
            }

            // Dependencia en Riesgos.
            queryStr = "SELECT COUNT(p.riskPk) AS cant FROM Riesgos p"
                    + " WHERE p.riskEntregable.entPk = :entPk";
            query = super.getEm().createQuery(queryStr);
            query.setParameter("entPk", entPk);
            try {
                Long cant = (Long) query.getSingleResult();
                if (cant > 0) {
                    return true;
                }
            } catch (Exception w) {
                logger.log(Level.SEVERE, w.getMessage(), w);
            }

            // Dependencia en Calidad.
            queryStr = "SELECT COUNT(p.calPk) AS cant FROM Calidad p"
                    + " WHERE p.calEntFk.entPk = :entPk";
            query = super.getEm().createQuery(queryStr);
            query.setParameter("entPk", entPk);
            try {
                Long cant = (Long) query.getSingleResult();
                if (cant > 0) {
                    return true;
                }
            } catch (Exception w) {
                logger.log(Level.SEVERE, w.getMessage(), w);
            }

            // Dependencia en Interesados.
            queryStr = "SELECT COUNT(p.intPk) AS cant FROM Interesados p"
                    + " WHERE p.intEntregable.entPk = :entPk";
            query = super.getEm().createQuery(queryStr);
            query.setParameter("entPk", entPk);
            try {
                Long cant = (Long) query.getSingleResult();
                if (cant > 0) {
                    return true;
                }
            } catch (Exception w) {
                logger.log(Level.SEVERE, w.getMessage(), w);
            }
        }
        return false;
    }

    public List<Entregables> obtenerEntregablesPorCoord(Integer proyPk, Integer coord) {
        if (proyPk != null) {
            String queryStr = "SELECT e FROM Proyectos p, Cronogramas c, Entregables e"
                    + " WHERE p.proyPk = :proyPk"
                    + " AND p.proyCroFk.croPk = c.croPk"
                    + " AND c.croPk = e.entCroFk.croPk"
                    + " AND e.coordinadorUsuFk.usuId = :coord";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            query.setParameter("coord", coord);
            try {
                return query.getResultList();
            } catch (Exception w) {
                logger.log(Level.SEVERE, w.getMessage(), w);
                TechnicalException te = new TechnicalException();
                te.addError(w.getMessage());
                throw te;
            }
        }
        return null;
    }

    public List<Entregables> obtenerEntPorProyPk(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT e FROM Proyectos p, Cronogramas c, Entregables e"
                    + " WHERE p.proyPk = :proyPk"
                    + " AND p.proyCroFk.croPk = c.croPk"
                    + " AND c.croPk = e.entCroFk.croPk";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            try {
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                TechnicalException te = new TechnicalException();
                te.addError(MensajesNegocio.ERROR_ENTREGABLES_OBTENER);
                throw te;
            }
        }
        return null;
    }

    public List<MisTareasTO> obtenerMisTareasPorFiltro(FiltroMisTareasTO filtro, Integer orgPk) {
        if (filtro != null) {

            String queryStr = "SELECT NEW com.sofis.entities.tipos.MisTareasTO"
                    + "(p.proyProgFk.progPk, p.proyProgFk.progNombre, p.proyPk,"
                    + " p.proyNombre, e.entPk, e.entNombre, e.entInicio, e.entFin, e.entDuracion,"
                    + " e.entInicioLineaBase, e.entDuracionLineaBase, e.entFinLineaBase)"
                    + " FROM Entregables e, Proyectos p"
                    + " WHERE e.entCroFk.croPk = p.proyCroFk.croPk"
                    + " AND e.entCroFk.proyecto.proyOrgFk.orgPk = :orgPk";

            Map<String, Object> parameterMap = new HashMap<>();
            if (filtro.getProgPk() != null) {
                queryStr = StringsUtils.concat(queryStr, " AND p.proyProgFk.progPk = :progPk");
                parameterMap.put("progPk", filtro.getProgPk());
            }
            if (!StringsUtils.isEmpty(filtro.getProyNombre())) {
                queryStr = StringsUtils.concat(queryStr, " AND p.proyNombre LIKE :proyNombre");
                parameterMap.put("proyNombre", StringsUtils.concat("%", filtro.getProyNombre(), "%"));
            }
            if (filtro.getUsuCoordPk() != null) {
                queryStr = StringsUtils.concat(queryStr, " AND e.coordinadorUsuFk.usuId = :usuCoordId");
                parameterMap.put("usuCoordId", filtro.getUsuCoordPk());
            }
            if (filtro.getTareaFinalizada() != null && filtro.getTareaFinalizada()) {
                queryStr = StringsUtils.concat(queryStr, " AND e.entProgreso >= :progreso");
                parameterMap.put("progreso", 100);
            }
            if (filtro.getAnio() != null && filtro.getAnio() > 0) {
                Calendar calIni = new GregorianCalendar(filtro.getAnio(), Calendar.JANUARY, 1, 0, 0);
                Calendar calFin = new GregorianCalendar(filtro.getAnio(), Calendar.DECEMBER, 31, 0, 0);
                Long timeIni = calIni.getTimeInMillis();
                Long timeFin = calFin.getTimeInMillis();

                queryStr = StringsUtils.concat(queryStr, " AND ((e.entInicio <= ", timeIni.toString(), " AND e.entFin >= ", timeIni.toString(), ")",
                        " OR (e.entInicio <= ", timeFin.toString(), " AND e.entFin >= ", timeFin.toString(), ")",
                        " OR (e.entInicio > ", timeIni.toString(), " AND e.entFin < ", timeFin.toString(), "))");
            }

            queryStr = StringsUtils.concat(queryStr, " ORDER BY p.proyProgFk.progNombre, p.proyNombre");
            System.out.println("queryStr:" + queryStr);
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("orgPk", orgPk);

            if (parameterMap.size() > 0) {
                Set<String> keySet = parameterMap.keySet();
                for (String str : keySet) {
                    query.setParameter(str, parameterMap.get(str));
                }
            }

            try {
                List<MisTareasTO> result = query.getResultList();
                return result;
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                TechnicalException te = new TechnicalException();
                te.addError(MensajesNegocio.ERROR_ENTREGABLES_OBTENER);
                throw te;
            }
        }
        return null;
    }

    /**
     * Rotorna la suma del esfuerzo de los entregables del proyecto aportado. Si
     * se aporta el avance, se suman todos los que superan dicho avance.
     *
     * @param proyPk
     * @param porcAvance
     * @return Double
     */
    public Double obtenerEsfuerzoTotal(Integer proyPk, Integer porcAvance) {
        if (proyPk != null) {
            String queryStr = "SELECT SUM(e.entEsfuerzo) FROM Proyectos p, Entregables e"
                    + " WHERE p.proyPk = :proyPk"
                    + " AND p.proyCroFk.croPk = e.entCroFk.croPk";

            if (porcAvance != null) {
                queryStr = queryStr + " AND e.entProgreso >= " + porcAvance;
            }

            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            try {
                Long result = (Long)query.getSingleResult();
                return result != null ? (double) result : 0D;
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return null;
    }
}
