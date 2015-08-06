package com.sofis.business.ejbs;

import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface ArchivoContenidoLocalLocal {

    public byte[] obtenerArchivoContenido(Integer id);
    
}
