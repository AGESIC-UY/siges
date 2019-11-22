/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.data.daos;

import com.sofis.entities.data.LatlngProyectos;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bruno
 */
public class LatlngProyectosDAO extends HibernateJpaDAOImp<LatlngProyectos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LatlngProyectosDAO.class.getName());  
    
    public LatlngProyectosDAO(EntityManager em) {
            super(em);
    }

    /*
    *   02-07-2018 Nico: Se crea esta operación para poder eliminar directamente las locaclizaciones desde el ServicioWeb
    *           dado que al hacerlo por el Proyecto, como no se tenia activado el orphanRemoval no se eliminaban
    */
    
    public void forceDelete(LatlngProyectos locToDelete){

        String queryStr = "DELETE FROM LatlngProyectos loc WHERE loc.latlngPk = :latlngPk";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("latlngPk", locToDelete.getLatlngPk());

        query.executeUpdate();        
    }
}
