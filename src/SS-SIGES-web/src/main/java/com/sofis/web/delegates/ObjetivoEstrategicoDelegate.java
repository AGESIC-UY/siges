/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.delegates;

import com.sofis.business.ejbs.ObjetivoEstrategicoBean;
import com.sofis.entities.data.ObjetivoEstrategico;
import com.sofis.entities.tipos.FiltroObjectivoEstategicoTO;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class ObjetivoEstrategicoDelegate {
    
    @Inject
    private ObjetivoEstrategicoBean bean;
    
    public ObjetivoEstrategico obtenerPorId(Integer objEstPk){
       return bean.obtenerPorId(objEstPk);
    }
    
    public ObjetivoEstrategico guardar(ObjetivoEstrategico objEst){
        return bean.guardar(objEst);
    }
    
    public List<ObjetivoEstrategico> obtenerPorFiltro(FiltroObjectivoEstategicoTO filtro){
        return bean.obtenerPorFiltro(filtro);
    }
    
    public void eliminar(Integer objEstPk){
        bean.eliminar(objEstPk);
    }
    
}
