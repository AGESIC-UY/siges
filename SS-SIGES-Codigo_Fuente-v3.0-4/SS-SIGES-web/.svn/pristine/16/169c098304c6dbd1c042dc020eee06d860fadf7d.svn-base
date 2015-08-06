package com.sofis.web.delegates;

import com.sofis.business.ejbs.ConfiguracionBean;
import com.sofis.entities.data.Configuracion;
import com.sofis.exceptions.GeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ConfiguracionDelegate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private ConfiguracionBean cnfBean;

    public Configuracion guardar(Configuracion cnf) throws GeneralException {
        return cnfBean.guardar(cnf);
    }

    public Configuracion obtenerCnfPorId(Integer id) throws GeneralException {
        return cnfBean.obtenerCnfPorId(id);
    }

    public Configuracion obtenerCnfPorCodigoYOrg(String codigo, Integer orgPk) throws GeneralException {
        return cnfBean.obtenerCnfPorCodigoYOrg(codigo, orgPk);
    }

    @Deprecated
    public List<Configuracion> obtenerTodos() throws GeneralException {
        return cnfBean.obtenerTodos();
    }

    public List<Configuracion> obtenerTodosPorOrg(Integer orgPk) throws GeneralException {
        return cnfBean.obtenerTodosPorOrg(orgPk);
    }

    public List<Configuracion> obtenerPorCriteria(CriteriaTO cto, String[] orderBy, boolean[] ascending, Long startPosition, Long cantidad) throws GeneralException {
        return cnfBean.obtenerPorCriteria(cto, orderBy, ascending, startPosition, cantidad);
    }

    public void controlarCnfFaltantes() {
        cnfBean.controlarCnfFaltantes();
    }
}
