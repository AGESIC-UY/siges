package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class DocumentosDao extends HibernateJpaDAOImp<Documentos, Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DocumentosDao.class.getName());

	public DocumentosDao(EntityManager em) {
		super(em);
	}

	public List<Documentos> obtenerDocumentosOrderByFecha(Integer fichaFk, Integer tipoFicha) {
		String queryStr = "";
		if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
			queryStr = "SELECT d FROM Programas p, IN(p.documentosSet) d"
					+ " WHERE p.progPk = :fichaFk"
					+ " ORDER BY d.docsFecha DESC, d.docsPk DESC";

		} else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
			queryStr = "SELECT d FROM Proyectos p, IN(p.documentosSet) d"
					+ " WHERE p.proyPk = :fichaFk"
					+ " ORDER BY d.docsFecha DESC, d.docsPk DESC";
		}

		Query query = super.getEm().createQuery(queryStr);
		query.setParameter("fichaFk", fichaFk);

		List<Documentos> resultado = query.getResultList();
		return resultado;
	}

	/**
	 * Retorna una lista de Documentos ordenados por estado de menor a mayor con
	 * un maximo de registros según lo indicado.
	 *
	 * @param fichaFk
	 * @param tipoFicha
	 * @param maxResult
	 * @return Lista de Documentos según el máximo indicado
	 */
	@SuppressWarnings("unchecked")
	public List<Documentos> obtenerDocumentosPorFicha(Integer fichaFk, Integer tipoFicha, int maxResult) {
		if (fichaFk != null && tipoFicha != null) {
			String queryStr = "";
			if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
				queryStr = "SELECT d FROM Programas p, IN(p.documentosSet) d"
						+ " WHERE p.progPk = :fichaFk"
						+ " ORDER BY d.docsEstado ASC";

			} else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
				queryStr = "SELECT d FROM Proyectos p, IN(p.documentosSet) d"
						+ " WHERE p.proyPk = :fichaFk"
						+ " ORDER BY d.docsEstado ASC";
			}

			Query query = super.getEm().createQuery(queryStr);
			query.setParameter("fichaFk", fichaFk);

			if (maxResult > 0) {
				query.setMaxResults(maxResult);
			}

			return query.getResultList();
		}
		return null;
	}

	public Boolean metodologiaSinAprobar(Integer proyPk) {
		String queryStr = "SELECT d.docsPk "
				+ " FROM Proyectos p, IN(p.documentosSet) d"
				+ " WHERE p.proyPk = :proyPk"
				+ " AND (d.docsAprobado IS NULL OR d.docsAprobado = :docsAprobado)"
				+ " AND d.docsTipo.tipodocExigidoDesde != 0";

		Query query = super.getEm().createQuery(queryStr);
		query.setParameter("proyPk", proyPk);
		query.setParameter("docsAprobado", Boolean.FALSE);

		List<Integer> resultado = query.getResultList();

		return resultado != null && !resultado.isEmpty();
	}

	public Boolean metodologiaSinAprobarProgramas(Integer progPk, Boolean incluirProyectosFinalizados) {
		String queryStr = "SELECT d.docsAprobado "
				+ " FROM Programas p, IN(p.proyectosSet) y, IN(y.documentosSet) d"
				+ " WHERE p.progPk = :progPk"
				+ " AND (y.activo IS NULL OR y.activo = :activo)"
				+ " AND (d.docsAprobado IS NULL OR d.docsAprobado = :docsAprobado)"
				+ " AND d.docsTipo.tipodocExigidoDesde != 0";

		if (!incluirProyectosFinalizados) {
			queryStr += " AND NOT y.proyEstFk.estPk = 5";
		}

		Query query = super.getEm().createQuery(queryStr);
		query.setParameter("progPk", progPk);
		query.setParameter("activo", Boolean.TRUE);
		query.setParameter("docsAprobado", Boolean.FALSE);

		List<Double> resultado = query.getResultList();

		return resultado != null && !resultado.isEmpty();
	}

	public Documentos obtenerResumenEjecutivo(Integer fichaFk, TipoFichaEnum tipoFicha) {
		if (fichaFk != null && tipoFicha != null) {
			String queryStr;
			if (tipoFicha.isPrograma()) {
				queryStr = "SELECT d FROM Programas p, IN(p.documentosSet) d"
						+ " WHERE p.progPk = :fichaFk"
						+ " AND (d.docsTipo.tipodocInstTipoDocFk.tipodocResumenEjecutivo IS NOT NULL"
						+ " AND d.docsTipo.tipodocInstTipoDocFk.tipodocResumenEjecutivo = :resumen)"
						+ " ORDER BY d.docsFecha DESC, d.docsPk DESC";
			} else {
				queryStr = "SELECT d FROM Proyectos p, IN(p.documentosSet) d"
						+ " WHERE p.proyPk = :fichaFk"
						+ " AND (d.docsTipo.tipodocInstTipoDocFk.tipodocResumenEjecutivo IS NOT NULL"
						+ " AND d.docsTipo.tipodocInstTipoDocFk.tipodocResumenEjecutivo = :resumen)"
						+ " ORDER BY d.docsFecha DESC, d.docsPk DESC";
			}
			try {
				Query query = super.getEm().createQuery(queryStr);
				query.setParameter("fichaFk", fichaFk);
				query.setParameter("resumen", Boolean.TRUE);
				query.setMaxResults(1);

				List<Documentos> result = query.getResultList();
				return (Documentos) DAOUtils.obtenerSingleResult(result);
			} catch (Exception e) {
				logger.log(Level.SEVERE, null, e);
			}
		}
		return null;
	}

	public Documentos obtenerUltimoDocPorTipoDocInst(Integer tdiPk) {
		String queryStr = "SELECT d FROM Documentos d"
				+ " WHERE d.docsTipo.tipodocInstPk = :tdiPk"
				+ " ORDER BY d.docsFecha DESC, d.docsPk DESC";
		Query query = super.getEm().createQuery(queryStr);
		query.setParameter("tdiPk", tdiPk);
		query.setMaxResults(1);

		try {
			List<Documentos> result = query.getResultList();
			return (Documentos) DAOUtils.obtenerSingleResult(result);
		} catch (Exception e) {
			return null;
		}
	}

	public void quitarEntregable(Integer entPk) {
		String queryStr = "UPDATE FROM Documentos d"
				+ " SET docsEntregable = null"
				+ " WHERE docsEntregable.entPk = :entPk";
		Query query = super.getEm().createQuery(queryStr);
		query.setParameter("entPk", entPk);

		try {
			query.executeUpdate();
		} catch (Exception ex) {
			TechnicalException te = new TechnicalException(ex);
			te.addError(MensajesNegocio.ERROR_DOCS_QUITAR_ENT);
			throw te;
		}
	}

	public void actualizarNombrePath(Integer docPk, Integer docFilePk, String nuevoPath, boolean inHistorico, Integer rev) {
		try {
			if (inHistorico) {
				String queryStr = "UPDATE aud_doc_file"
						+ " SET docfile_path = '" + nuevoPath + "'"
						+ " WHERE docfile_doc_fk = " + docPk
						+ " AND docfile_pk = " + docFilePk
						+ " AND REV = " + rev;

				Query query = super.getEm().createNativeQuery(queryStr);
				int result = query.executeUpdate();
			} else {
				String queryStr = "UPDATE FROM DocFile df"
						+ " SET docfilePath = :nuevoPath"
						+ " WHERE df.docfileDocFk.docsPk = :docPk "
						+ " AND df.docfilePk = :docFilePk";

				Query query = super.getEm().createQuery(queryStr);

				query.setParameter("nuevoPath", nuevoPath);
				query.setParameter("docPk", docPk);
				query.setParameter("docFilePk", docFilePk);

				query.executeUpdate();
			}

		} catch (Exception ex) {
			TechnicalException te = new TechnicalException(ex);
			te.addError(MensajesNegocio.ERROR_MOVER_PROY_CAMBIAR_PATH);
			throw te;
		}
	}

}
