package com.sofis.web.delegates;

import com.sofis.business.ejbs.MisAdquisicionesBean;
import com.sofis.entities.tipos.AdquisicionTO;
import com.sofis.entities.tipos.FiltroMisAdquisicionesTO;
import com.sofis.entities.tipos.MisAdquisicionesTO;
import com.sofis.entities.tipos.PagoTO;
import javax.inject.Inject;

public class MisAdquisicionesDelegate {

	@Inject
	private MisAdquisicionesBean misAdquisicionesBean;

	public MisAdquisicionesTO buscar(FiltroMisAdquisicionesTO filtro) {

		return misAdquisicionesBean.buscar(filtro);
	}

	public void actualizarAdquisicion(AdquisicionTO adquisicion) {

		misAdquisicionesBean.actualizarAdquisicion(adquisicion);
	}

	public AdquisicionTO obtenerAdquisicion(Integer id) {

		return misAdquisicionesBean.obtenerAdquisicion(id);
	}
	
	public void actualizarPago(PagoTO pago) {

		misAdquisicionesBean.actualizarPago(pago);
	}

	public boolean cambiarEstadoPago(PagoTO pago) {

		return misAdquisicionesBean.cambiarEstadoPago(pago);
	}

	public PagoTO obtenerPago(Integer id) {

		return misAdquisicionesBean.obtenerPago(id);
	}


}
