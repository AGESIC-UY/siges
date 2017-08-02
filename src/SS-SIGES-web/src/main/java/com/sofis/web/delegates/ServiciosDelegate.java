/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.delegates;

import com.sofis.business.ejbs.ServiciosBean;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.ws.gestion.data.RequestGuardarProyectoTO;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author bruno
 */
@Named
public class ServiciosDelegate implements Serializable {
    
    @Inject
    private ServiciosBean serviciosBean;
    
    
    public Integer guardarFicha(RequestGuardarProyectoTO request) throws GeneralException {
        return serviciosBean.guardarFicha(request);
    }
    
    
}
