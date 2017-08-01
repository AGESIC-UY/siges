package com.sofis.web.mb;

import com.sofis.business.ejbs.CategoriaProyectosBean;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.genericos.constantes.ConstantesNavegacion;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@ManagedBean(name = "actualizarCategoriasMB")
@SessionScoped
public class ActualizarCategoriasMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    
    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    
    @Inject
    private CategoriaProyectosBean categoriaProyectosBean;
    
    /**
     * Creates a new instance of ActualizarCategoriasMB
     */
    public ActualizarCategoriasMB() {
    }

    @PostConstruct
    public void init() {
	
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }
    
    public String actualizarAction() {
        logger.log(Level.INFO, "-- Actualizar Categorias desde el Visualizador");
        try {
            categoriaProyectosBean.actualizarCategorias(inicioMB.getOrganismo().getOrgPk());
            JSFUtils.agregarMsgInfo("Las Categorias se actualizaron correctamente.");
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, "Error al actualizar las Categorias.", be);
            JSFUtils.agregarMsgError("Error al actualizar las Categorias.");
        }
                
        return null;
    }
    
    public String salirAction() {
        return ConstantesNavegacion.IR_A_INICIO;
    }
}