package com.sofis.web.delegates;

import com.sofis.business.ejbs.TipoDocumentoPersonaBean;
import com.sofis.entities.data.TipoDocumentoPersona;
import com.sofis.exceptions.GeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class TipoDocumentoPersonaDelegate {

    @Inject
    TipoDocumentoPersonaBean tdpBean;

    public TipoDocumentoPersona guardar(TipoDocumentoPersona tdp) throws GeneralException {
        return tdpBean.guardar(tdp);
    }

    public TipoDocumentoPersona obtenerTipoPersonaDocumentoPorId(Integer id) throws GeneralException {
        return tdpBean.obtenerTipoPersonaDocumentoPorId(id);
    }

    public TipoDocumentoPersona obtenerTipoPersonaDocumentoPorCodigo(String codigo) throws GeneralException {
        return tdpBean.obtenerTipoPersonaDocumentoPorCodigo(codigo);
    }

    public List<TipoDocumentoPersona> obtenerTodos() throws GeneralException {
        return tdpBean.obtenerTodos();
    }

    public List<TipoDocumentoPersona> obtenerHabilitados() throws GeneralException {
        return tdpBean.obtenerHabilitados();
    }

    public List<TipoDocumentoPersona> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
        return tdpBean.obtenerPorCriteria(cto, orderBy, ascending, startPosition, cantidad);
    }
}
