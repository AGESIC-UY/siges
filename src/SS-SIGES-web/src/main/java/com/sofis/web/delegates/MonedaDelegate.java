package com.sofis.web.delegates;

import com.sofis.business.ejbs.MonedaBean;
import com.sofis.entities.data.Moneda;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class MonedaDelegate {

    private static final Map<Integer, Moneda> cache = new HashMap();

    @Inject
    MonedaBean monedaBean;

    public Moneda obtenerMonedaPorId(Integer monPk) {
        if (cache.containsKey(monPk)) {
            return cache.get(monPk);
        }
        Moneda moneda = monedaBean.obtenerMonedaPorId(monPk);
        cache.put(monPk, moneda);
        return moneda;
    }

//    public Moneda obtenerMonedaPorId(Integer monPk) {
//        return monedaBean.obtenerMonedaPorId(monPk);
//    }

    public List<Moneda> obtenerMonedas() {
        return monedaBean.obtenerMonedas();
    }

    public void eliminarMoneda(Integer monedaPk) {
        monedaBean.eliminarMoneda(monedaPk);
    }

    public List<Moneda> busquedaMonedaFiltro(Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente, int and) {
        return monedaBean.busquedaMonedaFiltro(mapFiltro, elementoOrdenacion, ascendente, and);
    }

    public Moneda guardarMoneda(Moneda monedaEnEdicion) {
        return monedaBean.guardarMoneda(monedaEnEdicion);
    }
}
