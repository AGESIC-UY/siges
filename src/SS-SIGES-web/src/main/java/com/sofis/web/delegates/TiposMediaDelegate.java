package com.sofis.web.delegates;

import com.sofis.business.ejbs.TiposMediaBean;
import com.sofis.entities.data.TiposMedia;
import com.sofis.exceptions.GeneralException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class TiposMediaDelegate {
    
    @Inject
    TiposMediaBean tiposMediaBean;
    
    public List<TiposMedia> obtenerTodos() throws GeneralException {
        return tiposMediaBean.obtenerTodos();
    }
}
