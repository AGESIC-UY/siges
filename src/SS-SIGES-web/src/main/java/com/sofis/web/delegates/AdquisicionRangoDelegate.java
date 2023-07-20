package com.sofis.web.delegates;

import com.sofis.business.ejbs.AdquisicionRangoBean;
import com.sofis.entities.data.AdquisicionRango;
import com.sofis.exceptions.GeneralException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.List;
import javax.inject.Inject;

public class AdquisicionRangoDelegate {

	@Inject
	private AdquisicionRangoBean adquisicionBean;

	public AdquisicionRango guardar(AdquisicionRango adquisicion) throws GeneralException {
		return adquisicionBean.guardar(adquisicion);
	}

    public List<AdquisicionRango> listarPorOrganismo(Integer orgPk) {
        return adquisicionBean.listarPorOrganismo(orgPk);
    }

    public List<AdquisicionRango> listarPorOrganismoYArea(Integer orgPk, Integer areaPk) {
       return adquisicionBean.listarPorOrganismoYArea(orgPk,areaPk);
    }

    public List<AdquisicionRango> listarPorOrganismoAreaDivision(Integer orgPk, Integer areaPk, Integer divisionPk) {
       return adquisicionBean.listarPorOrganismoAreaDivision(orgPk,areaPk,divisionPk);
    }

     public List<AdquisicionRango> listarPorOrganismoYDivision(Integer orgPk, Integer divisionPk) {
       return adquisicionBean.listarPorOrganismoYDivision(orgPk, divisionPk);
    }

    public List<AdquisicionRango> listarPorOrganismoYAreaDivisionNull(Integer orgPk, Integer areaPk) {
        return adquisicionBean.listarPorOrganismoYAreaDivisionNull(orgPk,areaPk);
    }

    public AdquisicionRango obtenerPorId(Integer id) {
        return adquisicionBean.obtenerPorId(id);
    }

    public void eliminar(Integer aPk) {
       adquisicionBean.eliminar(aPk);
    }
    
     public boolean existeRango(Integer orgPk, Integer inicio,Integer fin,Integer pk) throws DAOGeneralException {
        return adquisicionBean.existeRango(orgPk, inicio, fin,pk);
    }

}
