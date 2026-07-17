package com.sofis.web.seguridad;


import com.sofis.entities.tipos.TokenSegundoFactor;
import java.security.Principal;

public class SigesPrincipal implements Principal {

    Principal identity;
    TokenSegundoFactor token;

    public SigesPrincipal(Principal identity, TokenSegundoFactor token) {
        this.identity = identity;
        this.token = token;
    }

    @Override
    public String getName() {
        return identity.getName();
    }

    public Principal getIdentity() {
        return identity;
    }

    public TokenSegundoFactor getToken() {
        return token;
    }

}
