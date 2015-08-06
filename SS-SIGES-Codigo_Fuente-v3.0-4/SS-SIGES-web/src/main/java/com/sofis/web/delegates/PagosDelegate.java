package com.sofis.web.delegates;

import com.sofis.business.ejbs.PagosBean;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Pagos;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class PagosDelegate {
    
    @Inject
    PagosBean pagosBean;

    public List<Pagos> obtenerPagosPorFichaId(Integer fichaFk, Integer tipoFicha) throws GeneralException {
        return pagosBean.obtenerPagosPorFichaId(fichaFk, tipoFicha);
    }
    
    public Pagos guardarPago(Pagos adqisicion, Integer proyPk, Integer progPk, Integer orgPk) throws GeneralException {
        return pagosBean.guardarPago(adqisicion, proyPk, progPk, orgPk);
    }

    public void eliminarPago(Integer pagPk) throws GeneralException{
        pagosBean.eliminarPago(pagPk);
    }

    public Pagos obtenerPagosPorId(Integer pagPk) {
        return pagosBean.obtenerPagosPorId(pagPk);
    }
}
