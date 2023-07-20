package com.sofis.web.delegates;

import com.sofis.business.ejbs.TipoDocumentoInstanciaBean;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.TipoDocumentoInstancia;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class TipoDocumentoInstanciaDelegate {
    
    @Inject
    private TipoDocumentoInstanciaBean tipoDocumentoInstanciaBean;
    
    public List<TipoDocumentoInstancia> obtenerTipoDocInstResumen(Integer fichaFk, Integer tipoFicha, Estados est, int size) {
        return tipoDocumentoInstanciaBean.obtenerTipoDocInstResumen(fichaFk, tipoFicha, est, size);
    }
    
    public List<TipoDocumentoInstancia> guardarTiposDocsIntancia(List<TipoDocumentoInstancia> instancias) throws GeneralException {
        return tipoDocumentoInstanciaBean.guardarTiposDocsIntancia(instancias);
    }
    
    public List<TipoDocumentoInstancia> obtenerTipoDocsInstanciaPorProgProyId(Integer fichaPk, Integer tipoFicha, Integer orgId) {
        return tipoDocumentoInstanciaBean.obtenerTipoDocsInstanciaPorProgProyId(fichaPk, tipoFicha, orgId);
    }
    
    public List<TipoDocumentoInstancia> obtenerTiposDocumentoInstanciaPorProyecto(Integer proyId) {
        return tipoDocumentoInstanciaBean.obtenerTiposDocumentoInstanciaPorProyecto(proyId);
    }
    
    public List<TipoDocumentoInstancia> obtenerTipoDocsNoContenidosPorProyectoId(Integer proyId, Integer orgId) {
        return tipoDocumentoInstanciaBean.obtenerTipoDocsNoContenidosPorProyectoId(proyId, orgId);
    }
    
    public List<TipoDocumentoInstancia> obtenerTipoDocsNoContenidosPorProgramaId(Integer progId, Integer orgId) {
        return tipoDocumentoInstanciaBean.obtenerTipoDocsNoContenidosPorProgramaId(progId, orgId);
    }
    
    public List<TipoDocumentoInstancia> copiarTiposDocsIntancia(Integer prgOriginalId,Integer prgNuevoId, Integer orgPk) {
        return tipoDocumentoInstanciaBean.guardarCopiaTDIPrograma(prgOriginalId, prgNuevoId, orgPk);
    }
    
}
