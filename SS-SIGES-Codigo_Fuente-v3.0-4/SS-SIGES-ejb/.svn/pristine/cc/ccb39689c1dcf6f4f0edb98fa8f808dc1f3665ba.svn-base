package com.sofis.business.ejbs;

import com.sofis.business.utils.ProductosUtils;
import com.sofis.business.validations.ProductosValidacion;
import com.sofis.data.daos.ProdMesDAO;
import com.sofis.data.daos.ProductosDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.constantes.ConstantesLogica;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.ProdMes;
import com.sofis.entities.data.Productos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ProductosBean")
@LocalBean
public class ProductosBean {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;
    @Inject
    private ConfiguracionBean configuracionBean;
    @Inject
    private EntregablesBean entregablesBean;

    public List<Productos> obtenerProdPorProyPk(Integer proyPk) {
        ProductosDAO dao = new ProductosDAO(em);
        return dao.obtenerProdPorProyPk(proyPk);
    }

    public boolean tieneProdPorEnt(Integer entPk) {
        boolean result = CollectionsUtils.isNotEmpty(obtenerProdPorEnt(entPk));
        return result;
    }

    public List<Productos> obtenerProdPorEnt(Integer entPk) {
        ProductosDAO dao = new ProductosDAO(em);
        return dao.obtenerProdPorEnt(entPk);
    }

    private Productos guardar(Productos prod) {
        ProductosValidacion.validar(prod);

        ProductosDAO dao = new ProductosDAO(em);
        try {
            prod = dao.update(prod, du.getCodigoUsuario(), du.getOrigen());
            entregablesBean.guardarAvanceReal(prod.getProdEntregableFk().getEntPk());
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException(MensajesNegocio.ERROR_PRODUCTO_GUARDAR);
            throw be;
        }
        return prod;
    }

    public List<Productos> guardarProducto(List<Productos> listProd) {
        if (CollectionsUtils.isNotEmpty(listProd)) {
            for (Productos prod : listProd) {
                prod = guardarProducto(prod);
            }
        }
        return listProd;
    }

    public Productos guardarProducto(Productos prod) {
        prod = agregarProdMes(prod);

        if (prod.getProdFechaCrea() == null) {
            prod.setProdFechaCrea(new Date());
        }
        if (prod.getProdPeso() == 0) {
            prod.setProdPeso(1);
        }
        prod = calcularAcumulados(prod);

        return guardar(prod);
    }

    /**
     * Si no tiene ProdMes, se los agrega.
     *
     * @param prod
     * @return
     */
    private Productos agregarProdMes(Productos prod) {
        if (prod != null && prod.getProdEntregableFk() != null) {
            Entregables ent = prod.getProdEntregableFk();
            Calendar calInicio = GregorianCalendar.getInstance();
            Date dInicio = ent.getEntInicioLineaBaseDate() == null || !DatesUtils.esMayor(ent.getEntInicioDate(), ent.getEntInicioLineaBaseDate()) ? ent.getEntInicioDate() : ent.getEntInicioLineaBaseDate();
            calInicio.setTime(dInicio);

            Calendar calFin = GregorianCalendar.getInstance();
            Date dFin = ent.getEntFinLineaBaseDate() == null || DatesUtils.esMayor(ent.getEntFinDate(), ent.getEntFinLineaBaseDate()) ? ent.getEntFinDate() : ent.getEntFinLineaBaseDate();
            calFin.setTime(dFin);

            if (prod.getProdMesList() == null) {
                prod.setProdMesList(new ArrayList<ProdMes>());
            }

            //Quita los meses sobrantes
            if (CollectionsUtils.isNotEmpty(prod.getProdMesList())) {
                List<ProdMes> removerProdMes = new ArrayList<>();
                for (ProdMes prodMes : prod.getProdMesList()) {
                    if ((prodMes.getProdmesAnio() < calInicio.get(Calendar.YEAR)
                            || (prodMes.getProdmesAnio() == calInicio.get(Calendar.YEAR)
                            && prodMes.getProdmesMes() < calInicio.get(Calendar.MONTH) + 1))
                            || (prodMes.getProdmesAnio() > calFin.get(Calendar.YEAR)
                            || (prodMes.getProdmesAnio() == calFin.get(Calendar.YEAR)
                            && prodMes.getProdmesMes() > calFin.get(Calendar.MONTH) + 1))) {
                        removerProdMes.add(prodMes);
                    }
                }
                prod.getProdMesList().removeAll(removerProdMes);
            }

            //Agrega los meses faltantes
            while (calInicio.get(Calendar.YEAR) < calFin.get(Calendar.YEAR)
                    || (calInicio.get(Calendar.YEAR) == calFin.get(Calendar.YEAR)
                    && calInicio.get(Calendar.MONTH) <= calFin.get(Calendar.MONTH))) {
                boolean agregar = !contieneMesAnio(prod.getProdMesList(), calInicio.get(Calendar.MONTH) + 1, calInicio.get(Calendar.YEAR));

                if (agregar) {
                    ProdMes prodMes = new ProdMes();
                    prodMes.setProdmesMes((short) (calInicio.get(Calendar.MONTH) + 1));
                    prodMes.setProdmesAnio((short) calInicio.get(Calendar.YEAR));
                    prodMes.setProdmesPlan(0d);
                    prodMes.setProdmesReal(0d);
                    prodMes.setProdmesAcuPlan(0d);
                    prodMes.setProdmesAcuReal(0d);
                    prodMes.setProdmesProdFk(prod);
                    prod.getProdMesList().add(prodMes);
                }

                calInicio.add(Calendar.MONTH, 1);
            }
        }
        return prod;
    }

    public boolean contieneMesAnio(List<ProdMes> list, int mes, int anio) {
        if (CollectionsUtils.isNotEmpty(list)) {
            for (ProdMes prodMes : list) {
                if (prodMes.getProdmesMes() == mes && prodMes.getProdmesAnio() == anio) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Carga el acumulado para cada mes del producto.
     *
     * @param prod
     * @param mes
     * @param fase
     * @return Double
     */
    public Productos calcularAcumulados(Productos prod) {
        if (prod != null && CollectionsUtils.isNotEmpty(prod.getProdMesList())) {
            Calendar calIni = null;
            Calendar calFin = null;
            Calendar calToProcess = null;
            Map<String, ProdMes> mapProdMes = new HashMap<>();
            Double acumuladoPlan = 0d;
            Double acumuladoReal = 0d;
            List<ProdMes> prodMesRecalculados = new ArrayList<>();

            //Obtengo la menor y la mayor fecha a procesar.
            for (ProdMes prodMes : prod.getProdMesList()) {
                if (calIni == null
                        || calIni.get(Calendar.YEAR) > prodMes.getProdmesAnio()
                        || (calIni.get(Calendar.YEAR) == prodMes.getProdmesAnio()
                        && (calIni.get(Calendar.MONTH) + 1) > prodMes.getProdmesMes())) {
                    calIni = new GregorianCalendar(prodMes.getProdmesAnio(), prodMes.getProdmesMes() - 1, 1);
                }
                if (calFin == null
                        || calFin.get(Calendar.YEAR) < prodMes.getProdmesAnio()
                        || (calFin.get(Calendar.YEAR) == prodMes.getProdmesAnio()
                        && (calFin.get(Calendar.MONTH) + 1) < prodMes.getProdmesMes())) {
                    calFin = new GregorianCalendar(prodMes.getProdmesAnio(), prodMes.getProdmesMes() - 1, 1);
                }

                String key = prodMes.getProdmesAnio() + "-" + prodMes.getProdmesMes();
                mapProdMes.put(key, prodMes);
            }

            calToProcess = new GregorianCalendar(calIni.get(Calendar.YEAR), calIni.get(Calendar.MONTH), 1);
            while (calToProcess.get(Calendar.YEAR) < calFin.get(Calendar.YEAR)
                    || (calToProcess.get(Calendar.YEAR) == calFin.get(Calendar.YEAR)
                    && calToProcess.get(Calendar.MONTH) <= calFin.get(Calendar.MONTH))) {

                String key = calToProcess.get(Calendar.YEAR) + "-" + (calToProcess.get(Calendar.MONTH) + 1);
                ProdMes prodMesProcesar = mapProdMes.get(key);

                if (prodMesProcesar != null) {
                    if (prodMesProcesar.getProdmesPlan() != null) {
                        acumuladoPlan += prodMesProcesar.getProdmesPlan();
                        prodMesProcesar.setProdmesAcuPlan(acumuladoPlan);
                    }
                    if (prodMesProcesar.getProdmesReal() != null) {
                        acumuladoReal += prodMesProcesar.getProdmesReal();
                        prodMesProcesar.setProdmesAcuReal(acumuladoReal);
                    }

                    prodMesRecalculados.add(prodMesProcesar);
                }
                calToProcess.add(Calendar.MONTH, 1);
            }

            if (prod.getProdMesList() != null) {
                prod.getProdMesList().clear();
                prod.getProdMesList().addAll(prodMesRecalculados);
            } else {
                prod.setProdMesList(prodMesRecalculados);
            }

        }
        return prod;
    }

    public void eliminarProducto(Integer prodPk) {
        if (prodPk != null) {
            ProductosDAO dao = new ProductosDAO(em);
            try {
                Productos prod = dao.findById(Productos.class, prodPk);
                dao.delete(prod, du.getCodigoUsuario(), du.getOrigen());
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);

                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_PRODUCTO_ELIMINAR);
                if (ex.getCause() != null
                        && ex.getCause() instanceof javax.persistence.PersistenceException
                        && ex.getCause().getCause() != null
                        && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    be.addError(MensajesNegocio.ERROR_PRODUCTO_CONST_VIOLATION);
                }
                throw be;
            }
        } else {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PRODUCTO_ELIMINAR);
            throw be;
        }
    }

    public void actualizarProdPorEnt(Set<Entregables> colEnt) {
        if (CollectionsUtils.isNotEmpty(colEnt)) {
            for (Entregables ent : colEnt) {
                List<Productos> prodList = obtenerProdPorEnt(ent.getEntPk());
                if (CollectionsUtils.isNotEmpty(prodList)) {
                    for (Productos prod : prodList) {
                        guardarProducto(prod);
                    }
                }
            }
        }
    }

    public Double obtenerTotalAcuPlan(Integer prodPk) {
        Productos prod = obtenerProdProdId(prodPk);
        Double result = null;
        short anio = 0;
        short mes = 0;
        for (ProdMes prodMes : prod.getProdMesList()) {
            if (prodMes.getProdmesAnio() > anio
                    || (prodMes.getProdmesAnio() == anio
                    && prodMes.getProdmesMes() > mes)) {
                anio = prodMes.getProdmesAnio();
                mes = prodMes.getProdmesMes();
                result = prodMes.getProdmesAcuPlan();
            }
        }
        return result;
    }

    public String prodMesAcuRealColor(Integer prodmesPk, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        ProdMes prodmes = obtenerProdMesProdId(prodmesPk);
        return prodMesAcuRealColor(prodmes, orgPk, limiteAmarillo, limiteRojo);
    }

    public String prodMesAcuRealColor(ProdMes prodMes, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        if (prodMes != null
                && prodMes.getProdmesAcuReal() != null
                && prodMes.getProdmesAcuPlan() != null) {
            if (limiteAmarillo == null) {
                limiteAmarillo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_AMARILLO, orgPk).getCnfValor());
            }
            if (limiteRojo == null) {
                limiteRojo = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_ROJO, orgPk).getCnfValor());
            }

            Double totalAcuPlan = obtenerTotalAcuPlan(prodMes.getProdmesProdFk().getProdPk());
            double porcAcuReal = prodMes.getProdmesAcuReal() * 100 / prodMes.getProdmesAcuPlan();

            if (porcAcuReal >= 100) {
                if (prodMes.getProdmesAcuReal() >= totalAcuPlan) {
                    return ConstantesEstandares.SEMAFORO_AZUL;
                } else {
                    return ConstantesEstandares.SEMAFORO_VERDE;
                }
            } else if (porcAcuReal < 100 && porcAcuReal > (100 - limiteAmarillo)) {
                return ConstantesEstandares.SEMAFORO_VERDE;
            } else if (porcAcuReal <= (100 - limiteAmarillo) && porcAcuReal > (100 - limiteRojo)) {
                return ConstantesEstandares.SEMAFORO_AMARILLO;
            } else if (porcAcuReal <= (100 - limiteRojo)) {
                return ConstantesEstandares.SEMAFORO_ROJO;
            }
        }
        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    public Set<Productos> copiarProyProductos(Entregables ent, Entregables nvoEnt, int desfasajeDias) {
        if (ent != null && nvoEnt != null) {
            Set<Productos> result = new HashSet<>();
            List<Productos> prodList = obtenerProdPorEnt(ent.getEntPk());
            if (CollectionsUtils.isNotEmpty(prodList)) {
                for (Productos prod : prodList) {
                    Productos nvoProd = new Productos();
                    nvoProd.setProdAcumulado(prod.getProdAcumulado());
                    nvoProd.setProdDesc(prod.getProdDesc());
                    nvoProd.setProdEntregableFk(nvoEnt);
                    nvoProd.setProdFechaCrea(new Date());
                    nvoProd.setProdMesList(copiarProyProdMes(prod.getProdMesList(), nvoProd, desfasajeDias));
                    nvoProd.setProdPeso(prod.getProdPeso());
                    nvoProd.setProdUltMod(null);
                    nvoProd.setProdUndMedida(prod.getProdUndMedida());
                    result.add(nvoProd);
                }
            }
            return result;
        }
        return null;
    }

    public List<ProdMes> copiarProyProdMes(List<ProdMes> prodMesList, Productos nvoProd, int desfasajeDias) {
        if (CollectionsUtils.isNotEmpty(prodMesList) && nvoProd != null) {
            List<ProdMes> result = new ArrayList<>();
            for (ProdMes prodMes : prodMesList) {
                ProdMes pm = new ProdMes();
                pm.setProdmesAcuPlan(prodMes.getProdmesAcuPlan());
                pm.setProdmesAcuReal(prodMes.getProdmesAcuReal());
                pm.setProdmesPlan(prodMes.getProdmesPlan());
                pm.setProdmesReal(prodMes.getProdmesReal());
                pm.setProdmesProdFk(nvoProd);

                Calendar cal = new GregorianCalendar(prodMes.getProdmesAnio(), prodMes.getProdmesMes(), 1);
                cal = DatesUtils.incrementarDias(cal, desfasajeDias);
                pm.setProdmesAnio((short) cal.get(Calendar.YEAR));
                pm.setProdmesMes((short) (cal.get(Calendar.MONTH) + 1));

                result.add(pm);
            }
            return result;
        }
        return null;
    }

    private Productos obtenerProdProdId(Integer prodPk) {
        ProductosDAO dao = new ProductosDAO(em);
        try {
            return dao.findById(Productos.class, prodPk);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private ProdMes obtenerProdMesProdId(Integer prodmesPk) {
        if (prodmesPk != null) {
            ProdMesDAO dao = new ProdMesDAO(em);
            try {
                return dao.findById(ProdMes.class, prodmesPk);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public List<Productos> cargarProdMesAuxiliar(List<Productos> listProd, Integer anio) {
        if (CollectionsUtils.isNotEmpty(listProd)) {
            for (Productos prod : listProd) {
                prod = cargarProdMesAux(prod, anio);
            }
        }
        return listProd;
    }

    public Productos cargarProdMesAuxiliar(Productos prod, Integer anio) {
        if (prod != null) {
            int year = anio != null && anio > 0 ? anio : new GregorianCalendar().get(Calendar.YEAR);
            int yearAux = 0;
            for (ProdMes pM : prod.getProdMesList()) {
                if (pM.getProdmesAnio() > yearAux) {
                    yearAux = pM.getProdmesAnio();
                }
            }
            if (yearAux < year) {
                year = yearAux;
            }

            return cargarProdMesAux(prod, year);
        }
        return null;
    }

    private Productos cargarProdMesAux(Productos prod, Integer anio) {
        if (prod != null) {
            int year = anio != null && anio > 0 ? anio : new GregorianCalendar().get(Calendar.YEAR);

            prod.setAnio(year);
            ProdMes[] prodMesArr = new ProdMes[12];
            for (ProdMes pM : prod.getProdMesList()) {
                if (pM.getProdmesAnio() == year) {
                    prodMesArr[pM.getProdmesMes() - 1] = pM;
                }
            }
            for (int i = 0; i < prodMesArr.length; i++) {
                if (prodMesArr[i] == null) {
                    ProdMes pm = new ProdMes();
                    pm.setProdmesMes((short) (i + 1));
                    pm.setProdmesAnio((short) year);
                    pm.setProdmesPlan(0d);
                    pm.setProdmesReal(0d);
                    pm.setProdmesAcuPlan(0d);
                    pm.setProdmesAcuReal(0d);
                    prodMesArr[i] = pm;
                }
            }
            prod.setProdMesAuxList(Arrays.asList(prodMesArr));
        }
        return prod;
    }

    Calendar obtenerPrimeraFechaProd(Integer prodPk) {
        ProductosDAO dao = new ProductosDAO(em);
        return dao.obtenerPrimeraFechaProd(prodPk);
    }

    Calendar obtenerUltimaFechaProd(Integer prodPk) {
        ProductosDAO dao = new ProductosDAO(em);
        return dao.obtenerUltimaFechaProd(prodPk);
    }

    /**
     * Calcula las cantidades y porcentajes de productos según el rango o
     * situación en la que se encuentran.
     *
     * @param proyPk
     * @param limiteAmarilloProd
     * @param limiteRojoProd
     * @param orgPk
     * @return Map
     */
    public Map<String, Integer> cantPorcProdPorRango(Integer proyPk, Integer limiteAmarilloProd, Integer limiteRojoProd, Integer orgPk) {
        if (limiteAmarilloProd == null && orgPk != null) {
            limiteAmarilloProd = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_AMARILLO, orgPk).getCnfValor());
        }
        if (limiteRojoProd == null && orgPk != null) {
            limiteRojoProd = Integer.valueOf(configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PRODUCTO_INDICE_LIMITE_ROJO, orgPk).getCnfValor());
        }

        Map<String, Integer> mapResult = new HashMap<>();
        ProductosDAO dao = new ProductosDAO(em);
        List<Productos> listProd = dao.obtenerProdPorProyPk(proyPk);
        int cantResult = 0;

        for (Productos prod : listProd) {
            ProdMes ultimoProdMes = null;
            for (ProdMes prodMes : prod.getProdMesList()) {
                if (ultimoProdMes == null
                        || (prodMes.getProdmesAnio() > ultimoProdMes.getProdmesAnio()
                        || (prodMes.getProdmesAnio() == ultimoProdMes.getProdmesAnio()
                        && prodMes.getProdmesMes() > ultimoProdMes.getProdmesMes()))) {
                    ultimoProdMes = prodMes;
                }
            }

            if (ultimoProdMes.getProdmesAcuPlan() != null && ultimoProdMes.getProdmesAcuReal() != null) {
                cantResult++;
                double porc = ultimoProdMes.getProdmesAcuReal() * 100 / ultimoProdMes.getProdmesAcuPlan();
                //0-Rojo, 1-Amarillo, 2-Verde, 3-Azul.
                Integer cantA=null;
                if (porc < 100 && porc >= (100 - limiteAmarilloProd)
                        || (ultimoProdMes.getProdmesAcuPlan().equals(0D) && ultimoProdMes.getProdmesAcuReal().equals(0D))) {
                    cantA = mapResult.get(ConstantesLogica.PROD_CANT_VERDE) != null ? mapResult.get(ConstantesLogica.PROD_CANT_VERDE) + 1 : 1;
                    mapResult.put(ConstantesLogica.PROD_CANT_VERDE, cantA);
                } else if (ultimoProdMes.getProdmesAcuPlan() <= ultimoProdMes.getProdmesAcuReal()) {
                    cantA = mapResult.get(ConstantesLogica.PROD_CANT_AZUL) != null ? mapResult.get(ConstantesLogica.PROD_CANT_AZUL) + 1 : 1;
                    mapResult.put(ConstantesLogica.PROD_CANT_AZUL, cantA);
                } else if (porc < (100 - limiteAmarilloProd) && porc >= (100 - limiteRojoProd)) {
                    cantA = mapResult.get(ConstantesLogica.PROD_CANT_AMARILLO) != null ? (mapResult.get(ConstantesLogica.PROD_CANT_AMARILLO)) + 1 : 1;
                    mapResult.put(ConstantesLogica.PROD_CANT_AMARILLO, cantA);
                } else if (porc < (100 - limiteRojoProd)) {
                    cantA = mapResult.get(ConstantesLogica.PROD_CANT_ROJO) != null ? (mapResult.get(ConstantesLogica.PROD_CANT_ROJO)) + 1 : 1;
                    mapResult.put(ConstantesLogica.PROD_CANT_ROJO, cantA);
                }
            }
        }

        if (!mapResult.isEmpty()) {
            Map<String, Integer> mewMap = new HashMap<>();
            for (String key : mapResult.keySet()) {
                String newKey = null;
                if (key != null) {
                    switch (key) {
                        case ConstantesLogica.PROD_CANT_AZUL:
                            newKey = ConstantesLogica.PROD_PORC_AZUL;
                            break;
                        case ConstantesLogica.PROD_CANT_VERDE:
                            newKey = ConstantesLogica.PROD_PORC_VERDE;
                            break;
                        case ConstantesLogica.PROD_CANT_AMARILLO:
                            newKey = ConstantesLogica.PROD_PORC_AMARILLO;
                            break;
                        case ConstantesLogica.PROD_CANT_ROJO:
                            newKey = ConstantesLogica.PROD_PORC_ROJO;
                            break;
                    }
                    if (newKey != null) {
                        mewMap.put(newKey, Math.round((float) (mapResult.get(key)) * 100 / (float) (cantResult)));
                    }
                }

            }
            if (!mewMap.isEmpty()) {
                mapResult.putAll(mewMap);
            }
        }

        return mapResult;
    }

    /**
     * Retorna los productos cuyo mes anterior a hoy el acumulado real es menor
     * al acumulado planificado.
     *
     * @param proyPk Id del Proyecto.
     * @param max Número máximo de registros a devolver.
     * @return List de Productos
     */
    public List<Productos> obtenerResumenAtrasados(Integer proyPk, int max) {
        if (proyPk != null) {
            ProductosDAO dao = new ProductosDAO(em);
            List<Productos> listProd = dao.obtenerProdPorProyPk(proyPk);
            Calendar calMesAnterior = new GregorianCalendar();
            calMesAnterior.set(Calendar.DATE, 1);
            calMesAnterior.add(Calendar.MONTH, -1);

            if (CollectionsUtils.isNotEmpty(listProd)) {
                List<Productos> prodAtrasados = new ArrayList<>();
                for (Productos prod : listProd) {
                    ProdMes prodMesCalcular = null;
                    ProdMes prodMesAnterior = prod.obtenerProdMes(calMesAnterior.get(Calendar.YEAR), calMesAnterior.get(Calendar.MONTH) + 1);
                    if (prodMesAnterior != null) {
                        prodMesCalcular = prodMesAnterior;
                    } else {
                        ProdMes ultimoProdMes = prod.ultimoProdMes();
                        if (ultimoProdMes != null) {
                            Calendar calUltimoMes = new GregorianCalendar();
                            calUltimoMes.set(ultimoProdMes.getProdmesAnio(), ultimoProdMes.getProdmesMes(), 1);
                            if (calUltimoMes.before(calMesAnterior)) {
                                prodMesCalcular = ultimoProdMes;
                            }
                        }
                    }

                    if (prodMesCalcular != null
                            && prodMesCalcular.getProdmesAcuReal() < prodMesCalcular.getProdmesAcuPlan()) {
                        prodAtrasados.add(prod);
                    }
                }

                if (CollectionsUtils.isNotEmpty(prodAtrasados)) {
                    prodAtrasados = ProductosUtils.sortByFechaFin(prodAtrasados, true);
                    List<Productos> result = new ArrayList<>();
                    for (int i = 0; i < max && i < prodAtrasados.size(); i++) {
                        result.add(prodAtrasados.get(i));
                    }

                    return result;
                }
            }
        }
        return null;
    }
}
