package com.sofis.web.delegates;

import com.sofis.business.ejbs.MailTemplateDefectoBean;
import com.sofis.entities.data.MailTemplateDefecto;
import com.sofis.entities.tipos.FiltroMailTemplateDefectoTO;
import java.util.List;
import javax.inject.Inject;

public class MailTemplateDefectoDelegate {

	@Inject
	private MailTemplateDefectoBean mailTemplateDefectoBean;

	public List<MailTemplateDefecto> obtenerTodos() {
		return mailTemplateDefectoBean.obtenerTodos();
	}

	public void eliminar(Integer mailPk) {
		mailTemplateDefectoBean.eliminar(mailPk);
	}

	public List<MailTemplateDefecto> buscar(FiltroMailTemplateDefectoTO filtro, String elementoOrdenacion, int ascendente) {
		return mailTemplateDefectoBean.buscar(filtro, elementoOrdenacion, ascendente);
	}

	public MailTemplateDefecto obtenerPorId(Integer id) {
		return mailTemplateDefectoBean.obtenerPorId(id);
	}

	public MailTemplateDefecto guardar(MailTemplateDefecto mt) {
		return mailTemplateDefectoBean.guardar(mt);
	}
}
