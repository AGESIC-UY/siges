package com.sofis.web.delegates;

import com.sofis.business.ejbs.MailsTemplateBean;
import com.sofis.entities.data.MailsTemplate;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class MailsTemplateDelegate {

    @Inject
    MailsTemplateBean mailsTemplateBean;

    public List<MailsTemplate> obtenerTodosPorOrg(Integer orgPk) {
        return mailsTemplateBean.obtenerTodosPorOrg(orgPk);
    }

    public void controlarMailTmpFaltantes() {
        mailsTemplateBean.controlarMailTmpFaltantes();
    }

    public void eliminarMail(Integer mailPk) {
        mailsTemplateBean.eliminarMail(mailPk);
    }

    public List<MailsTemplate> busquedaMailFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        return mailsTemplateBean.busquedaMailFiltro(orgPk, mapFiltro, elementoOrdenacion, ascendente);
    }

    public MailsTemplate obtenerMailPorPk(Integer aPk) {
        return mailsTemplateBean.obtenerMailPorPk(aPk);
    }

    public MailsTemplate guardarMail(MailsTemplate mt) {
        return mailsTemplateBean.guardar(mt);
    }
}
