package com.sofis.web.delegates;

import com.sofis.business.ejbs.DocumentosBean;
import com.sofis.entities.data.DocFile;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoDocumentoInstancia;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class DocumentosDelegate {

    @Inject
    private DocumentosBean documentosBean;

    public Object guardarDocumentos(Documentos documento, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {
        return documentosBean.guardarDocumentos(documento, fichaFk, tipoFicha, usuario, orgPk);
    }

    
    public List<DocFile> obtenerHistoricoDocFile(Documentos d) throws GeneralException {
	return this.documentosBean.obtenerHistoricoDocFile(d);
    }
    
    public Documentos obtenerDocumentosPorId(Integer id) throws GeneralException {
        return documentosBean.obtenerDocumentosPorId(id);
    }

    public List<Double> obtenerEstados() {
        return documentosBean.obtenerEstados();
    }

    public void obtenerEstadosColorTipoDocInst(List<TipoDocumentoInstancia> tdi, Estados est) {
        if (tdi != null) {
            for (TipoDocumentoInstancia tDoc : tdi) {
                documentosBean.obtenerEstadosColorTipoDocInst(tDoc, est);
            }
        }
    }

    public void obtenerEstadosColor(List<Documentos> docs, Estados est) {
        if (docs != null) {
            for (Documentos d : docs) {
                documentosBean.obtenerEstadosColor(d, est);
            }
        }
    }

    public List<Documentos> obtenerDocumentosResumen(Integer fichaPk, Integer tipoFicha, Estados est, int size) {
        return documentosBean.obtenerDocumentosResumen(fichaPk, tipoFicha, est, size);
    }

    public List<Documentos> obtenerDocumentosPorFichaPk(Integer fichaPk, Integer tipoFicha) {
        return documentosBean.obtenerDocumentosPorFichaPk(fichaPk, tipoFicha);
    }

    public void eliminarDocumento(Integer docPk, Integer fichaPk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) throws GeneralException {
        documentosBean.eliminarDocumento(docPk, fichaPk, tipoFicha, usuario, orgPk);
    }

    public List<Documentos> obtenerDocumentosOrderByFecha(Integer fichaFk, Integer tipoFicha) {
        return documentosBean.obtenerDocumentosOrderByFecha(fichaFk, tipoFicha);
    }

//    public Double calcularIndiceEstadoMetodologiaPrograma(Set<Proyectos> proyectos) {
//        return documentosBean.calcularIndiceEstadoMetodologiaPrograma(proyectos);
//    }

    public Double calcularIndiceEstadoMetodologiaProyecto(List<Documentos> documentos, Integer proyPk, Integer orgPk, Estados estado) {
        return documentosBean.calcularIndiceEstadoMetodologiaProyecto(documentos, proyPk, orgPk, estado);
    }

    public String calcularIndiceEstadoMetodologiaColor(Double indiceEstado, Integer orgPk, Map<String, String> configs) {
        return documentosBean.calcularIndiceEstadoMetodologiaColor(indiceEstado, orgPk, configs);
    }

    public Documentos obtenerResumenEjecutivo(Integer fichaPk, TipoFichaEnum tipoFicha) {
        return documentosBean.obtenerResumenEjecutivo(fichaPk, tipoFicha);
    }

    public Documentos cargarArchivosDocumentos(Documentos doc) {
        return documentosBean.cargarArchivosDocumentos(doc);
    }

    public List<Documentos> cargarArchivosDocumentos(List<Documentos> listDocumentos) {
        return documentosBean.cargarArchivosDocumentos(listDocumentos);
    }

    public DocFile obtenerDocFilePorDocId(Integer docPk) {
        return documentosBean.obtenerDocFilePorDocId(docPk);
    }

    public List<DocFile> obtenerDocFilePorDocId(List<Documentos> listDocs) {
        return documentosBean.obtenerDocFilePorDocId(listDocs);
    }
    
    public String moverArchivosDocToFS() {
	return documentosBean.moverArchivosDocToFS();
    }

    public Boolean procesarMoverDocFS() {
	return documentosBean.procesarMoverDocFS();
    }
}
