/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.ws.gestion;

import com.sofis.business.ejbs.ServiciosBean;
import com.sofis.business.properties.LabelsEJB;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.web.delegates.ServiciosDelegate;
import com.sofis.web.ws.gestion.data.CategoriaProyectoTO;
import com.sofis.web.ws.gestion.data.RequestGuardarProyectoTO;
import com.sofis.web.ws.gestion.data.RequestListarCategorias;
import com.sofis.web.ws.gestion.data.ResponseGuardarProyectoTO;
import com.sofis.web.ws.gestion.data.ResponseListarCategorias;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author bruno
 */
@WebService(serviceName = "ServiciosGestion")
@Stateless
public class Servicios {

    private static final Logger logger = Logger.getLogger(Servicios.class.getName());

    /*
    *   22-03-18 Nico: Dejo comentado el "ServicioBean" porque en realidad se debería hacer mediante 
    *           "ServicioDelegate".
    */
    
    @Inject
    private ServiciosBean serviciosBean;
    @Inject
    private ServiciosDelegate servicioDelegate;
    
    @WebMethod(operationName = "guardarProyecto")
    public ResponseGuardarProyectoTO guardarProyecto(
            @WebParam(name = "request")
            @XmlElement(required = true) RequestGuardarProyectoTO request) {

        ResponseGuardarProyectoTO response = new ResponseGuardarProyectoTO();
        try {
            Integer proyPk = request.getProyecto().getProyPk();
            proyPk = serviciosBean.guardarProyecto(request);
            //proyPk = servicioDelegate.guardarProyecto(request);
            response.setStatus("OK");
            response.setMsg("operación realizada con éxito");
            response.setProyPk(proyPk != null ? proyPk : 1);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            response.setStatus("ERROR");
            response = procesarErroresResp(response, ex.getErrores());
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, null, ex);
            response.setStatus("ERROR");
            response = procesarErroresResp(response, ex.getErrores());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            response.setStatus("ERROR");
            response.setMsg(ex.getMessage());
        }
        return response;

    }
 
    @WebMethod(operationName = "listarCategorias")
    public ResponseListarCategorias listarCategorias(
            @WebParam(name = "request")
            @XmlElement(required = true) RequestListarCategorias request) {

        ResponseListarCategorias response = new ResponseListarCategorias();
        try {           
            List<CategoriaProyectoTO> infoResp = servicioDelegate.obtenerCategoriasProyecto(request);
            
            response.setStatus("OK");
            response.setMsg("operación realizada con éxito");
            response.setCategorias(infoResp);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            response.setStatus("ERROR");
            response = procesarErroresResp(response, ex.getErrores());
        } catch (TechnicalException ex) {
            logger.log(Level.SEVERE, null, ex);
            response.setStatus("ERROR");
            response = procesarErroresResp(response, ex.getErrores());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            response.setStatus("ERROR");
            response.setMsg(ex.getMessage());
        }
        return response;

    }
    
    
    private ResponseGuardarProyectoTO procesarErroresResp(ResponseGuardarProyectoTO resp, List<String> errores){
        String err = "";
        for(String m : errores){
            err += LabelsEJB.getValue(m) + "\n";
        }
        resp.setMsg(err);
        return resp;
    }
    
    private ResponseListarCategorias procesarErroresResp(ResponseListarCategorias resp, List<String> errores){
        String err = "";
        for(String m : errores){
            err += LabelsEJB.getValue(m) + "\n";
        }
        resp.setMsg(err);
        return resp;
    }

}
