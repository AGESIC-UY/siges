package uy.gub.agesic.pge.core.ws.addressing;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;


public class AttributedURIImpl implements AttributedURI {
	private URI uri;
	private Map<QName, String> extMap = new HashMap<QName, String>();

	public AttributedURIImpl(URI uri) {
		this.uri = uri;
	}

	public AttributedURIImpl(String uri) {
		try {
			this.uri = new URI(uri);
		} catch (URISyntaxException ex) {
			throw new IllegalArgumentException(ex.toString());
		}
	}

	public URI getURI() {
		return uri;
	}

	public String getNamespaceURI() {
		return AddressingConstantsImpl.URI_ADDRESSING;
	}

	public Map<QName, String> getAttributes() {
		return extMap;
	}

	public void addAttribute(QName name, String value)
			throws AddressingException {
		extMap.put(name, value);
	}
}
