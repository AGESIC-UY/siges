/*
 * 
 * 
 */
package com.sofis.web.delegates;

import com.sofis.entities.data.Error;
import com.sofis.exceptions.GeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.List;
import javax.inject.Inject;
import com.sofis.business.ejbs.ErrorBean;

/**
 *
 * @author Usuario
 */
public class ErrorDelegate {
     @Inject
    ErrorBean errBean;
    
     public Error guardar(Error err) throws GeneralException {
         return errBean.guardar(err);
     }
     public Error obtenerErrorPorId(Integer id) throws GeneralException {
         return errBean.obtenerErrorPorId(id);
     }
    
     
      public Error obtenerErrorPorCodigo(String codigo) throws GeneralException {
          return errBean.obtenerErrorPorCodigo(codigo);
      }
      
      public List<Error> obtenerTodos() throws GeneralException {
          return errBean.obtenerTodos();
      }
      
      public List<Error> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
          return errBean.obtenerPorCriteria(cto, orderBy, ascending, startPosition, cantidad);
      }
}
