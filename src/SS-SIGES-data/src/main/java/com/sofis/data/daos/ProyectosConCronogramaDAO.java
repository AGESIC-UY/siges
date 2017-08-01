package com.sofis.data.daos;

import com.sofis.entities.data.ProyectosConCronograma;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class ProyectosConCronogramaDAO extends HibernateJpaDAOImp<ProyectosConCronograma, Integer> implements Serializable {

  private static final long serialVersionUID = 1L;

  public ProyectosConCronogramaDAO(EntityManager em) {
    super(em);
  }
}
