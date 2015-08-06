/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.delegates;

import com.sofis.business.ejbs.ProySitActHistoricoBean;
import com.sofis.entities.data.ProySitactHistorico;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ProySitActHistoricoDelegate {
    
    @Inject
    ProySitActHistoricoBean sitActHistoricoBean;
    
    public List<ProySitactHistorico> obtenerHistoricoSitActTodos(Integer proyPk){
        return sitActHistoricoBean.obtenerHistoricoSitActTodos(proyPk);
    }
    
    public Date obtenerUltimaFechaSitAct(Integer proyPk){
        return sitActHistoricoBean.obtenerUltimaFechaSitAct(proyPk);
    }
}
