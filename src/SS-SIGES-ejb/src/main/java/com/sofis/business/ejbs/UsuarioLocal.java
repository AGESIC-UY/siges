package com.sofis.business.ejbs;

import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.TokenSegundoFactor;
import com.sofis.exceptions.GeneralException;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface UsuarioLocal {
    
    public void bloquearUsuario(Integer usuId);

    public SsUsuario obtenerSsUsuarioPorCodigo(String codigo) throws GeneralException;

    public SsUsuario obtenerSsUsuarioPorMail(String mail) throws GeneralException;

public SsUsuario obtenerSsUsuarioPorLDAPUser(String user) throws GeneralException;

    public boolean usuarioBloqueado(Integer usuId);

    public void resetearIntentosFallidos(Integer usuId);

    public void gestionarIntentosFallidos(Integer usuId);
    public void actualizarFechaLogin(Integer usuId);

    public TokenSegundoFactor crearTokenSegundoFactor(String username);
}
