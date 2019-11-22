package com.sofis.business.ejbs;

import com.sofis.data.daos.ProdMesDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProdMes;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ProdMesBean")
@LocalBean
public class ProdMesBean {
    
    private static final Logger logger = Logger.getLogger(ProdMesBean.class.getName());
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    
    public List<ProdMes> obtenerOrdenadoPorFecha(Integer prodPk){
        if(prodPk!=null){
            ProdMesDAO dao = new ProdMesDAO(em);
            return dao.obtenerOrdenadoPorFecha(prodPk);
        }
        return null;
    }
}
