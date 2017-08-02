package com.sofis.entities.tipos;

import com.sofis.entities.data.Areas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class FiltroInicioResultadoTO implements Serializable{

    private Areas area;
    private ArrayList<Map.Entry<FiltroInicioItem, List>> primerNivel = null;

    public Areas getArea() {
	return area;
    }

    public void setArea(Areas area) {
	this.area = area;
    }

    public ArrayList<Map.Entry<FiltroInicioItem, List>> getPrimerNivel() {
	return primerNivel;
    }

    public void setPrimerNivel(ArrayList<Map.Entry<FiltroInicioItem, List>> primerNivel) {
	this.primerNivel = primerNivel;
    }
}
