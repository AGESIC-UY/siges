package uy.gub.agesic.pge.core.ws.addressing;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author fsierra
 * 
 */
public class ElementExtensibleImpl implements ElementExtensible {
	private List<Object> elements = new ArrayList<Object>();

	public List<Object> getElements() {
		return elements;
	}

	public void addElement(Object element) {
		elements.add(element);
	}

	public boolean removeElement(Object element) {
		return elements.remove(element);
	}

	public String getNamespaceURI() {
		return AddressingConstantsImpl.URI_ADDRESSING;
	}
}
