package uy.gub.agesic.pge.core.ws.addressing;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

/**
 * 
 * @author fsierra
 *
 */
public class AttributeExtensibleImpl implements AttributeExtensible {
	private Map<QName, String> extMap = new HashMap<QName, String>();

	public Map<QName, String> getAttributes() {
		return extMap;
	}

	public void addAttribute(QName name, String value)
			throws AddressingException {
		extMap.put(name, value);
	}

	public String getNamespaceURI() {
		return AddressingConstantsImpl.URI_ADDRESSING;
	}
}