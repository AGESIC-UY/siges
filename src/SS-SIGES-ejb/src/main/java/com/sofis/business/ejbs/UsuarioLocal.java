package com.sofis.business.ejbs;

import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.GeneralException;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface UsuarioLocal {
    
    public SsUsuario obtenerSsUsuarioPorCodigo(String codigo) throws GeneralException;
    
    public SsUsuario obtenerSsUsuarioPorMail(String mail) throws GeneralException;
	
	public SsUsuario obtenerSsUsuarioPorLDAPUser(String user) throws GeneralException;
}
