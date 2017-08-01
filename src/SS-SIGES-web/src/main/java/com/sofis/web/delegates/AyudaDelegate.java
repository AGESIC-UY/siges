/*
 * 
 * 
 */
package com.sofis.web.delegates;

import com.sofis.entities.data.Ayuda;
import com.sofis.exceptions.GeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.List;
import javax.inject.Inject;
import com.sofis.business.ejbs.AyudaBean;

/**
 *
 * @author Usuario
 */
public class AyudaDelegate {
     @Inject
    AyudaBean ncmBean;
    
     public Ayuda guardar(Ayuda tdp) throws GeneralException {
         return ncmBean.guardar(tdp);
     }
     public Ayuda obtenerAyudaPorId(Integer id) throws GeneralException {
         return ncmBean.obtenerAyudaPorId(id);
     }
    
     
      public Ayuda obtenerAyudaPorCodigo(String codigo) throws GeneralException {
          return ncmBean.obtenerAyudaPorCodigo(codigo);
      }
      
      public List<Ayuda> obtenerTodos() throws GeneralException {
          return ncmBean.obtenerTodos();
      }
      
       
      
      public List<Ayuda> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
          return ncmBean.obtenerPorCriteria(cto, orderBy, ascending, startPosition, cantidad);
      }
}
