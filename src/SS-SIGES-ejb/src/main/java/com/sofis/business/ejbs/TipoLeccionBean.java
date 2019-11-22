package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.data.daos.TipoLeccionDAO;
import com.sofis.entities.codigueras.TipoLeccionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.TipoLeccion;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "TipoLeccionBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class TipoLeccionBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(TipoLeccionBean.class.getName());

    public TipoLeccion guardar(TipoLeccion tl) {
        TipoLeccionDAO dao = new TipoLeccionDAO(em);

        try {
            tl = dao.update(tl);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_TIPO_LECCION_GUARDAR);
            throw be;
        }

        return tl;
    }

    public List<TipoLeccion> obtenerTodos() {
        TipoLeccionDAO dao = new TipoLeccionDAO(em);
        try {
            return dao.findAll(TipoLeccion.class);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void controlarTipoLeccionCodigos() {
        List<TipoLeccion> tipoLecList = obtenerTodos();
        if (tipoLecList != null) {
            for (TipoLeccion tipoLec : tipoLecList) {
                if (StringsUtils.isEmpty(tipoLec.getTipolecCodigo())) {
                    switch (tipoLec.getTipolecPk()) {
                        case 1:
                        case 3:
                            tipoLec.setTipolecCodigo(TipoLeccionCodigos.A_EVITAR);
                            break;
                        case 2:
                        case 4:
                            tipoLec.setTipolecCodigo(TipoLeccionCodigos.A_REPETIR);
                            break;
                        default:
                            logger.log(Level.WARNING, "El tipo de lección id=" + tipoLec.getTipolecPk() + " no tiene código y no se reconoce para agregarle uno.");
                            break;
                    }
                    logger.log(Level.INFO, "Se agrega el código:" + tipoLec.getTipolecCodigo() + " al Tipo de lección " + tipoLec.getTipolecPk());
                    guardar(tipoLec);
                }
            }
        }
    }

    public void controlarTipoLeccionFaltantes() {
        controlarTipoLeccionCodigos();

        List<TipoLeccion> tipoLecList = obtenerTodos();
        Map<String, TipoLeccion> tipoLecMap = new HashMap<>();
        if (tipoLecList != null) {
            for (TipoLeccion tp : tipoLecList) {
                tipoLecMap.put(tp.getTipolecCodigo(), tp);
            }
        }

        TipoLeccion[] tipoLecArr = new TipoLeccion[]{
            new TipoLeccion(null, TipoLeccionCodigos.A_EVITAR, "A evitar"),
            new TipoLeccion(null, TipoLeccionCodigos.A_REPETIR, "A repetir")
        };

        for (TipoLeccion tp : tipoLecArr) {
            if (!tipoLecMap.containsKey(tp.getTipolecCodigo())) {
                guardar(tp);
                logger.log(Level.INFO, StringsUtils.concat("Se agregó el tipo de lección '", tp.getTipolecCodigo()), "'");
            }
        }
    }
}
