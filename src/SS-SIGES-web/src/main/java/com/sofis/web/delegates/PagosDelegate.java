package com.sofis.web.delegates;

import com.sofis.business.ejbs.PagosBean;
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

    public void eliminarPagoActInd(Integer pagPk, Integer proyPk, Integer orgPk) throws GeneralException {
        pagosBean.eliminarPagoActInd(pagPk, proyPk, orgPk);
    }

    public Pagos obtenerPagosPorId(Integer pagPk) {
        return pagosBean.obtenerPagosPorId(pagPk);
    }
    
    public Double getImportePlanificado(Integer pagPk) {
        return pagosBean.obtenerImportePlanificado(pagPk);
    }
}
