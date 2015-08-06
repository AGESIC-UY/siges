/*
 * 
 * 
 */
package com.sofis.certpyme.pgedelegate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import uy.gub.acce.rupe.wrappers.PGEProxyResultado;

/**
 *
 * @author Usuario
 */
public class DGIEmpresa {
    
    private Integer codigoRespuesta;
    private String descripcionCodigoRespuesta;
    private String razonSocial;
    private String rut;
    private DGIDomicilioFiscal domFiscal;
    private String nombreFantasia;
    private List<DGIMensaje> mensajes;
    private Date fechaInicioActividad;
    
    public DGIEmpresa() {
        
    }
    
    public DGIEmpresa(String error) {
        codigoRespuesta=2;
        descripcionCodigoRespuesta=error;
        
    }
    
   /* public DGIEmpresa(PGEProxyResultado res) throws ParseException {
        try{
        if (res!=null) {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            razonSocial=(String)res.getMap().get("/MapData/WS_Entidad/RazonSocial");
            rut=(String) res.getMap().get("/MapData/WS_Entidad/RUC");
            domFiscal = new DGIDomicilioFiscal(res);
            nombreFantasia= (String)res.getMap().get("/MapData/WS_Entidad/Local_Nom_Fnt");
            String fechaString  = (String)res.getMap().get("/MapData/WS_Entidad/WS_DomicilioFiscalPrincipal/Local_Fec_Ini");
            fechaInicioActividad = df.parse(fechaString);
            String menId= (String)res.getMap().get("/MapMensajes/Messages/Messages.Message/Type");
            //System.out.println("Mensaje id= "+menId);
            if (menId!=null && Integer.parseInt(menId)==1) {
                String menDesc= (String)res.getMap().get("/MapMensajes/Messages/Messages.Message/Description");
                DGIMensaje men = new DGIMensaje();
                men.setCodMensaje(menId);
                men.setDesMensaje(menDesc);
                mensajes=new ArrayList();
                mensajes.add(men);
            }
            if (mensajes==null || mensajes.isEmpty()) {
                codigoRespuesta=0;
            } else {
                codigoRespuesta=1;
                descripcionCodigoRespuesta="SE PRODUJO UN ERROR. VER MENSAJES";
            }
                    
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
*/
    public Date getFechaInicioActividad() {
        return fechaInicioActividad;
    }

    public void setFechaInicioActividad(Date fechaInicioActividad) {
        this.fechaInicioActividad = fechaInicioActividad;
    }
    
    

    public Integer getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(Integer codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getDescripcionCodigoRespuesta() {
        return descripcionCodigoRespuesta;
    }

    public void setDescripcionCodigoRespuesta(String descripcionCodigoRespuesta) {
        this.descripcionCodigoRespuesta = descripcionCodigoRespuesta;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public DGIDomicilioFiscal getDomFiscal() {
        return domFiscal;
    }

    public void setDomFiscal(DGIDomicilioFiscal domFiscal) {
        this.domFiscal = domFiscal;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public List<DGIMensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<DGIMensaje> mensajes) {
        this.mensajes = mensajes;
    }
    
    
    
    
}
