/*
 * 
 * 
 */
package com.sofis.web.delegates;

import com.sofis.entities.data.TipoTelefono;
import com.sofis.exceptions.GeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.util.List;
import javax.inject.Inject;
import com.sofis.business.ejbs.TipoTelefonoBean;

/**
 *
 * @author Usuario
 */
public class TipoTelefonoDelegate {
     @Inject
    TipoTelefonoBean ncmBean;
    
     public TipoTelefono guardar(TipoTelefono tdp) throws GeneralException {
         return ncmBean.guardar(tdp);
     }
     public TipoTelefono obtenerTipoTelefonoPorId(Integer id) throws GeneralException {
         return ncmBean.obtenerTipoTelefonoPorId(id);
     }
    
     
      public TipoTelefono obtenerTipoTelefonoPorCodigo(String codigo) throws GeneralException {
          return ncmBean.obtenerTipoTelefonoPorCodigo(codigo);
      }
      
      public List<TipoTelefono> obtenerTodos() throws GeneralException {
          return ncmBean.obtenerTodos();
      }
      
      public List<TipoTelefono> obtenerHabilitados() throws GeneralException {
          return ncmBean.obtenerHabilitados();
      }
      
      public List<TipoTelefono> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
          return ncmBean.obtenerPorCriteria(cto, orderBy, ascending, startPosition, cantidad);
      }
}
