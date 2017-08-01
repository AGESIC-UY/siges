/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.ws.gestion;

import com.sofis.business.ejbs.ServiciosBean;
import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.LatlngProyectos;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Personas;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.RolesInteresados;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.validations.FichaValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.ws.gestion.data.AdquisicionTO;
import com.sofis.web.ws.gestion.data.CronogramaTO;
import com.sofis.web.ws.gestion.data.EntregableTO;
import com.sofis.web.ws.gestion.data.PagoTO;
import com.sofis.web.ws.gestion.data.PresupuestoTO;
import com.sofis.web.ws.gestion.data.ProyectoTO;
import com.sofis.web.ws.gestion.data.RequestGuardarProyectoTO;
import com.sofis.web.ws.gestion.data.ResponseGuardarProyectoTO;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.bind.annotation.XmlElement;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

/**
 *
 * @author bruno
 */
@WebService(serviceName = "ServiciosGestion")
@Stateless
public class Servicios {

    private static final Logger logger = Logger.getLogger(Servicios.class.getName());

    private static final PolicyFactory policy = new HtmlPolicyBuilder()
            .disallowElements("script", "link")
            .toFactory();

    @Inject
    private ServiciosBean serviciosBean;

    @WebMethod(operationName = "guardarProyecto")
    public ResponseGuardarProyectoTO guardarProyecto(
            @WebParam(name = "request")
            @XmlElement(required = true) RequestGuardarProyectoTO request) {

        ResponseGuardarProyectoTO response = new ResponseGuardarProyectoTO();
        try {
            Integer proyPk = request.getProyecto().getProyPk();
            proyPk = serviciosBean.guardarFicha(request);
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
    
    private ResponseGuardarProyectoTO procesarErroresResp(ResponseGuardarProyectoTO resp, List<String> errores){
        String err = "";
        for(String m : errores){
            err += LabelsEJB.getValue(m) + "\n";
        }
        resp.setMsg(err);
        return resp;
    }

}
