 
package com.sofis.web.delegates;

import com.sofis.entities.data.PgeConfiguraciones;
import com.sofis.exceptions.GeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.List;
import javax.inject.Inject;
import com.sofis.business.ejbs.PgeConfiguracionBean;

/**
 *
 * @author Usuario
 */
public class PgeConfiguracionDelegate {
     @Inject
    PgeConfiguracionBean cnfBean;
    
     public PgeConfiguraciones guardar(PgeConfiguraciones tdp) throws GeneralException {
         return cnfBean.guardar(tdp);
     }
     public PgeConfiguraciones obtenerPgeConfiguracionesPorId(Integer id) throws GeneralException {
         return cnfBean.obtenerPgeConfiguracionesPorId(id);
     }
    
     
      public PgeConfiguraciones obtenerPgeConfiguracionesPorCodigo(String codigo) throws GeneralException {
          return cnfBean.obtenerPgeConfiguracionesPorCodigo(codigo);
      }
      
      public List<PgeConfiguraciones> obtenerTodos() throws GeneralException {
          return cnfBean.obtenerTodos();
      }
      
       
       
       public void eliminar(Integer id) throws GeneralException {
            cnfBean.eliminar(id);
      }
      
      public List<PgeConfiguraciones> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
          return cnfBean.obtenerPorCriteria(cto, orderBy, ascending, startPosition, cantidad);
      }
}
