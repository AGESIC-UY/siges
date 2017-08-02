package com.sofis.web.delegates;

import com.sofis.business.ejbs.ProductosBean;
import com.sofis.entities.data.ProdMes;
import com.sofis.entities.data.Productos;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ProductosDelegate {

    @Inject
    private ProductosBean productosBean;

    public Productos guardarProducto(Productos prod, Boolean actualizarPrograma) {
        return productosBean.guardarProducto(prod, actualizarPrograma);
    }
    
    public List<Productos> guardarProducto(List<Productos> listProd, Boolean actualizarPrograma) {
        return productosBean.guardarProducto(listProd, actualizarPrograma);
    }

    public boolean tieneProdporEnt(Integer entPk) {
        return productosBean.tieneProdPorEnt(entPk);
    }

    public List<Productos> obtenerProdPorProyPk(Integer proyPk) {
        return productosBean.obtenerProdPorProyPk(proyPk);
    }

    public Productos calcularAcumulados(Productos prod) {
        return productosBean.calcularAcumulados(prod);
    }

    public void eliminarProducto(Integer prodPk) {
        productosBean.eliminarProducto(prodPk);
    }

    public String prodMesAcuRealColor(Integer prodmesPk, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        return productosBean.prodMesAcuRealColor(prodmesPk, orgPk, limiteAmarillo, limiteRojo);
    }
    
    public Double obtenerTotalAcuPlan(Integer prodPk) {
        return productosBean.obtenerTotalAcuPlan(prodPk);
    
    }
    public String prodMesAcuRealColor(ProdMes prodMes, Integer orgPk, Integer limiteAmarillo, Integer limiteRojo) {
        return productosBean.prodMesAcuRealColor(prodMes, orgPk, limiteAmarillo, limiteRojo);
    }
    
    public Productos cargarProdMesAuxiliar(Productos prod, Integer anio) {
        return productosBean.cargarProdMesAuxiliar(prod, anio);
    }
    
    public List<Productos> cargarProdMesAuxiliar(List<Productos> listProd, Integer anio) {
        return productosBean.cargarProdMesAuxiliar(listProd, anio);
    }
    
    public List<Productos> obtenerProdPorEnt(Integer entPk) {
        return productosBean.obtenerProdPorEnt(entPk);
    }

    public Map<String, Integer> cantProdPorRango(Integer proyPk, Integer limiteAmarilloProd, Integer limiteRojoProd, Integer orgPk) {
        return productosBean.cantPorcProdPorRango(proyPk, limiteAmarilloProd, limiteRojoProd, orgPk);
    }

    public List<Productos> obtenerResumenAtrasados(Integer proyPk, int i) {
        return productosBean.obtenerResumenAtrasados(proyPk, i);
    }
}
