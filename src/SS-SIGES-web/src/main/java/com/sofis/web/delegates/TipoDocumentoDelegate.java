package com.sofis.web.delegates;

import com.sofis.business.ejbs.TipoDocumentoBean;
import com.sofis.entities.data.TipoDocumento;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class TipoDocumentoDelegate {

    @Inject
    TipoDocumentoBean tipoDocumentoBean;

    public TipoDocumento obtenerTipoDocPorId(Integer tipoDocPk) {
        return tipoDocumentoBean.obtenerTipoDocPorId(tipoDocPk);
    }

    public List<TipoDocumento> obtenerTodos(Integer orgPk) {
        return tipoDocumentoBean.obtenerTodos(orgPk);
    }

    public void eliminarTipoDoc(Integer aPk) {
        tipoDocumentoBean.eliminarTipoDoc(aPk);
    }

    public List<TipoDocumento> busquedaTipoDocFiltro(Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente, Integer orgPk) {
        return tipoDocumentoBean.busquedaTipoDocFiltro(mapFiltro, elementoOrdenacion, ascendente, orgPk);
    }

    public TipoDocumento guardarTipoDoc(TipoDocumento tipoDocEnEdicion) {
        return tipoDocumentoBean.guardar(tipoDocEnEdicion);
    }
}
