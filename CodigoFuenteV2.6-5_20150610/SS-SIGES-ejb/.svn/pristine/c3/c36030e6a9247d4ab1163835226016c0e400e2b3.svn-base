package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.validations.ValorHoraValidacion;
import com.sofis.data.daos.ValorHoraDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ValorHora;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ValorHoraBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ValorHoraBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @Inject
    private DatosUsuario du;

    public ValorHora obtenerValorHoraPorAnio(Integer usuId, Integer orgPk, Integer anio) {
        List<ValorHora> list = this.obtenerValorHoraPorUsu(usuId, orgPk, anio);
        if (list == null || list.isEmpty()) {
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            BusinessException be = new BusinessException();
            be.addError(ConstantesErrores.ERROR_DEMASIADOS_RESULTADOS);
            throw be;
        }
    }

    public List<ValorHora> obtenerValorHoraPorUsu(Integer usuId, Integer orgPk) {
        return this.obtenerValorHoraPorUsu(usuId, orgPk, null);
    }

    public List<ValorHora> obtenerValorHoraPorUsu(Integer usuId, Integer orgPk, Integer anio) {
        if (usuId != null && orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioUsu = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "valHorUsuarioFk.usuId", usuId);
            criterios.add(criterioUsu);

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "valHorOrganismosFk.orgPk", orgPk);
            criterios.add(criterioOrg);

            if (anio != null) {
                MatchCriteriaTO criterioAnio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "valHorAnio", anio);
                criterios.add(criterioAnio);
            }

            CriteriaTO condicion = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));
            String[] orderBy = new String[]{"valHorAnio"};
            boolean[] asc = new boolean[]{false};

            ValorHoraDAO dao = new ValorHoraDAO(em);

            try {
                return dao.findEntityByCriteria(ValorHora.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_AMBITO_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public ValorHora guardarValHora(ValorHora valHoraEnEdicion) {
        if (valHoraEnEdicion != null) {
            ValorHoraValidacion.validar(valHoraEnEdicion);
            validarDuplicado(valHoraEnEdicion);

            ValorHoraDAO dao = new ValorHoraDAO(em);
            try {
                return dao.update(valHoraEnEdicion, du.getCodigoUsuario(), du.getOrigen());
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_VALOR_HORA_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    private void validarDuplicado(ValorHora valHora) {
        List<ValorHora> list = obtenerValorHoraPorUsu(valHora.getValHorUsuarioFk().getUsuId(), valHora.getValHorOrganismosFk().getOrgPk());

        if (CollectionsUtils.isNotEmpty(list)) {
            for (ValorHora vh : list) {
                if (!vh.getValHorPk().equals(valHora.getValHorPk())
                        && vh.getValHorAnio().equals(valHora.getValHorAnio())) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_VALOR_HORA_ANIO_DUPLICADO);
                    throw be;
                }
            }
        }
    }
}
