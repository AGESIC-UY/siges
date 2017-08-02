package com.sofis.web.utils;

import java.util.LinkedList;
import java.util.List;

import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.sofisform.to.CriteriaTO;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import com.sofis.web.delegates.EntityManagementDelegate;

@Named
public class EntityReferenceDataProvider implements IDataProvider, Serializable {

    private String[] propiedades;
    private String className;
    private CriteriaTO condicion;
    private String[] orderBy = null;
    private boolean[] ascending = null;

    private EntityManagementDelegate emd;

    public EntityReferenceDataProvider() {
        emd = new EntityManagementDelegate();
    }

    public EntityReferenceDataProvider(String[] propiedades, String className, CriteriaTO condicion) {
        this.propiedades = propiedades;
        this.className = className;
        this.condicion = condicion;
        this.orderBy = null;
        this.ascending = null;
        emd = new EntityManagementDelegate();
    }

    public EntityReferenceDataProvider(String[] propiedades, String className,
            CriteriaTO condicion, String[] orderBy, boolean[] asc) {
        this.propiedades = propiedades;
        this.className = className;
        this.condicion = condicion;
        this.orderBy = orderBy;
        this.ascending = asc;
        emd = new EntityManagementDelegate();
    }

    public List<EntityReference<Integer>> getBufferedData(Integer startRow,
            Integer offset) {
        try {
            List<EntityReference<Integer>> resultado = emd.getEntitiesReferenceByCriteria(
                    className, condicion, startRow, startRow + offset, propiedades,
                    orderBy, ascending);

            return resultado;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LinkedList<EntityReference<Integer>>();
        }
    }

    @Override
    public Integer getCountData() {
        try {
            Long resultado = emd.getCountsByCriteria(className, null, null, condicion);
            return new Integer(resultado.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return 500;
        }
    }
}
