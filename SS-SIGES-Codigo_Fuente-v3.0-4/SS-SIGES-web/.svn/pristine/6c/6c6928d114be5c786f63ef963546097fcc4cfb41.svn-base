package com.sofis.web.delegates;

import com.sofis.business.ejbs.ArchivoContenidoLocalLocal;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.utils.EJBUtils;

/**
 *
 * @author Usuario
 */
public class ArchivoContenidoLocalDelegate {

    public byte[] obtenerArchivoContenido(Integer id) throws GeneralException {
        ArchivoContenidoLocalLocal achLoc = EJBUtils.getArchivoContenidoLocal();
        return achLoc.obtenerArchivoContenido(id);
    }
}
