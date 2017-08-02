package com.sofis.web.delegates;

import com.sofis.business.ejbs.EtapaBean;
import com.sofis.entities.data.Etapa;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class EtapaDelegate {
    
    @Inject
    EtapaBean etapaBean;

    public List<Etapa> obtenerEtapaPorOrgId(Integer orgPk) {
        return etapaBean.obtenerEtapaPorOrgId(orgPk);
    }
}
