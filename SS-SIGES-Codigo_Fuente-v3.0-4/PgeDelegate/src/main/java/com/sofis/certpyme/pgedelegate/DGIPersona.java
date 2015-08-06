
package com.sofis.certpyme.pgedelegate;

import java.util.ArrayList;
import java.util.List;
//import uy.gub.acce.rupe.wrappers.PGEProxyResultado;

/**
 *
 * @author Usuario
 */
public class DGIPersona {
    
    
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String razonSocial;
    private Integer codigoRespuesta;
    private String descripcionCodigoRespuesta;
    private List<DGIMensaje> mensajes;
    
    public DGIPersona() {
        
    }
    
    public DGIPersona(String men) {
        codigoRespuesta=2;
        descripcionCodigoRespuesta=men;
    }
    
   /* public DGIPersona(PGEProxyResultado res) {
        primerNombre=(String) res.getMap().get("/MapData/WS_PersonaDatos/PrimerNombre");
        segundoNombre=(String) res.getMap().get("/MapData/WS_PersonaDatos/SegundoNombre");
        primerApellido=(String) res.getMap().get("/MapData/WS_PersonaDatos/PrimerApellido");
        segundoApellido=(String) res.getMap().get("/MapData/WS_PersonaDatos/SegundoApellido");
        razonSocial=(String) res.getMap().get("/MapData/WS_PersonaDatos/RazonSocial");
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
                
    }*/

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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

    public List<DGIMensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<DGIMensaje> mensajes) {
        this.mensajes = mensajes;
    }
    
}
