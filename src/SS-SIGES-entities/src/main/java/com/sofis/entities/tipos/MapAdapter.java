package com.sofis.entities.tipos;

import com.sofis.entities.data.Configuracion;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class MapAdapter extends XmlAdapter<MapElement[], Map<String, Configuracion>> {

    @Override
    public Map<String, Configuracion> unmarshal(MapElement[] v) throws Exception {
	Map<String, Configuracion> r = new HashMap<String, Configuracion>();
	for (MapElement mapelement : v) {
	    r.put(mapelement.getKey(), mapelement.getValue());
	}
	return r;
    }

    @Override
    public MapElement[] marshal(Map<String, Configuracion> v) throws Exception {
	MapElement[] mapElements = new MapElement[v.size()];
	int i = 0;
	for (Map.Entry<String, Configuracion> entry : v.entrySet()) {
	    mapElements[i++] = new MapElement(entry.getKey(), entry.getValue());
	}
	return mapElements;
    }

}
