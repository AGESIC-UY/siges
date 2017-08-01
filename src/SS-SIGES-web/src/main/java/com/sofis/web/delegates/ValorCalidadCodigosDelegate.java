package com.sofis.web.delegates;

import com.sofis.business.ejbs.ValorCalidadCodigosBean;
import com.sofis.entities.data.ValorCalidadCodigos;
import com.sofis.entities.tipos.ComboItemTO;
import com.sofis.exceptions.GeneralException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ValorCalidadCodigosDelegate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private ValorCalidadCodigosBean valorCalidadCodigosBean;

    public ValorCalidadCodigos obtenerPorCodigo(String codigo) throws GeneralException {
        return valorCalidadCodigosBean.obtenerPorCodigo(codigo);
    }

    public List<ValorCalidadCodigos> obtenerTodos() {
        return valorCalidadCodigosBean.obtenerTodos();
    }
    
    public List<ComboItemTO> obtenerTodosParaCombo() {
        return valorCalidadCodigosBean.obtenerTodosParaCombo();
    }

    public List<ComboItemTO> obtenerRangoValoresParaCombo() {
        return valorCalidadCodigosBean.obtenerRangoValoresParaCombo();
    }

    public List<ValorCalidadCodigos> obtenerRangoValores() {
        return valorCalidadCodigosBean.obtenerRangoValores();
    }

    public ValorCalidadCodigos obtenerPorId(Integer vcaPk) {
        return valorCalidadCodigosBean.obtenerPorId(vcaPk);
    }
}
