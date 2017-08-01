package uy.gub.agesic.pge.core.ws.addressing;

import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;


public class ReferenceParametersImpl implements ReferenceParameters {
	public ReferenceParametersImpl() {
		super();
	}

	private AttributeExtensible attrExt = new AttributeExtensibleImpl();
	private ElementExtensible elmtExt = new ElementExtensibleImpl();

	public Map<QName, String> getAttributes() {
		return attrExt.getAttributes();
	}

	public void addAttribute(QName name, String value)
			throws AddressingException {
		attrExt.addAttribute(name, value);
	}

	public List<Object> getElements() {
		return elmtExt.getElements();
	}

	public void addElement(Object element) {
		elmtExt.addElement(element);
	}

	public boolean removeElement(Object element) {
		return elmtExt.removeElement(element);
	}

	public String getNamespaceURI() {
		return AddressingConstantsImpl.URI_ADDRESSING;
	}
}
