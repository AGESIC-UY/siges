package com.sofis.business.ejbs;

import com.sofis.data.daos.LatlngProyectosDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.LatlngProyectos;
import com.sofis.exceptions.TechnicalException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.agesic.siges.visualizador.web.ws.Departamentos;
import org.agesic.siges.visualizador.web.ws.LatlngProyectoImp;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Named
@Stateless(name = "LatlngBean")
@LocalBean
public class LatlngBean implements Serializable {

        private static final Logger logger = Logger.getLogger(LatlngBean.class.getName());
	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	public LatlngProyectoImp crearLatlngProyecto(LatlngProyectos latlngProyectos) {
		if (latlngProyectos != null) {
			LatlngProyectoImp latlngImp = new LatlngProyectoImp();
			latlngImp.setLatlangImpBarrio(latlngProyectos.getLatlangBarrio());
			latlngImp.setLatlangImpCodigopostal(latlngProyectos.getLatlangCodigopostal());
			latlngImp.setLatlangImpLocFk(latlngProyectos.getLatlangLocFk());
			latlngImp.setLatlangImpLoc(latlngProyectos.getLatlangLoc());
			latlngImp.setLatlngImpLat(latlngProyectos.getLatlngLat());
			latlngImp.setLatlngImpLng(latlngProyectos.getLatlngLng());

			if (latlngProyectos.getLatlangDepFk() != null) {
				Departamentos dep = new Departamentos();
				dep.setDepPk(latlngProyectos.getLatlangDepFk().getDepPk());
				dep.setDepNombre(latlngProyectos.getLatlangDepFk().getDepNombre());
				latlngImp.setLatlangImpDepFk(dep);
			}

			return latlngImp;
		}
		return null;
	}

	public LatlngProyectos guardar(LatlngProyectos latlngProyectos) {
		try {
			LatlngProyectosDAO dao = new LatlngProyectosDAO(em);
			return dao.update(latlngProyectos);
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}

	public void eliminar(LatlngProyectos latlngProyectos) {
		try {
			LatlngProyectosDAO dao = new LatlngProyectosDAO(em);
			latlngProyectos = dao.findById(LatlngProyectos.class, latlngProyectos.getLatlngPk());
			dao.delete(latlngProyectos);
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}
        
        public List<LatlngProyectos> obtenerPorProyecto (Integer proyFk){
            try{
                LatlngProyectosDAO dao = new LatlngProyectosDAO(em);
                return dao.findByOneProperty(LatlngProyectos.class, "proyecto.proyPk", proyFk);
            }catch(Exception ex){
                throw new TechnicalException(ex);
            }
        }
	public void eliminarFromWS(LatlngProyectos latlngProyectos) {
		try {
                    
                    LatlngProyectosDAO dao = new LatlngProyectosDAO(em);
                    latlngProyectos = dao.findById(LatlngProyectos.class, latlngProyectos.getLatlngPk());
                    dao.forceDelete(latlngProyectos);
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}
        
        

}
