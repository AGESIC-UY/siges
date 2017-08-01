package com.sofis.web.delegates;

import com.sofis.business.ejbs.IndicadoresBean;
import javax.inject.Inject;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class IndicadoresDelegate {
    
    @Inject
    private IndicadoresBean indicadoresBean;
    
    public void recalcularIndicadores(Integer orgPk, Boolean forzar) {
        indicadoresBean.recalcularIndicadores(orgPk, forzar);
    }
}
