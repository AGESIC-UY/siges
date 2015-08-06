/*
 * 
 * 
 */
package com.sofis.web.delegates;

import com.sofis.entities.data.Departamento;
import com.sofis.entities.data.Localidad;
import com.sofis.entities.data.Pais;
import com.sofis.entities.data.Paridad;
import com.sofis.entities.data.TipoEntradaColectiva;
import com.sofis.entities.data.TipoVialidad;
import java.util.List;
import javax.inject.Inject;
import com.sofis.business.ejbs.NomenclatorBean;

/**
 *
 * @author Usuario
 */
public class NomenclatorDelegate {
     @Inject
     NomenclatorBean nomBean;
    
     
      public  List<Pais> obtenerPaises() {
         return nomBean.obtenerPaises();
     }
      
      
     public  List<Departamento> obtenerDepartamentos() {
         return nomBean.obtenerDepartamentos();
     }
     
     public List<TipoVialidad> obtenerTiposVialidad() {
         return  nomBean.obtenerTiposVialidad();
     }
     
     public List<TipoEntradaColectiva> obtenerTipoEntradaColectiva() {
         return nomBean.obtenerTipoEntradaColectiva();
     }
     
     public List<Localidad> obtenerLocalidadesPorDeptoId(Integer dptoId) {
         return nomBean.obtenerLocalidadesPorDeptoId(dptoId);
     }
     
      public List<Paridad> obtenerParidades() {
         return nomBean.obtenerParidades();
     }
      
      public Pais obtenerPaisPorCodigo2(String codigo) {
          return nomBean.obtenerPaisPorCodigo2(codigo);
      }
}
