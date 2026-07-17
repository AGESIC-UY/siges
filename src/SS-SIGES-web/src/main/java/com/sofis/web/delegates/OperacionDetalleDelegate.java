package com.sofis.web.delegates;

import com.sofis.business.ejbs.OperacionDetalleBean;
import com.sofis.entities.data.OperacionDetalle;
import com.sofis.exceptions.GeneralException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class OperacionDetalleDelegate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private OperacionDetalleBean cnfBean;

    
    public List<OperacionDetalle>  listarPorOrg( Integer orgPk) throws GeneralException {
        return cnfBean.listarPorOrg(orgPk);
    }

   
    
}
