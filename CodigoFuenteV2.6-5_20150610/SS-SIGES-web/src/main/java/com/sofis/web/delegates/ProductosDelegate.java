package com.sofis.web.delegates;

import com.sofis.business.ejbs.ProductosBean;
import com.sofis.entities.data.ProdMes;
import com.sofis.entities.data.Productos;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
public class ProductosDelegate {

    @Inject
    private ProductosBean productosBean;

    public Productos guardarProducto(Productos prod) {
        return productosBean.guardarProducto(prod);
    }
    
    public List<Productos> guardarProducto(List<Productos> listProd) {
        return productosBean.guardarProducto(listProd);
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
}
