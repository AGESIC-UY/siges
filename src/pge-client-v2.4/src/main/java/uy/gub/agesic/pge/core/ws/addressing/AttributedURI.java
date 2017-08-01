/**
 * 
 */
package uy.gub.agesic.pge.core.ws.addressing;

import java.net.URI;

import javax.xml.namespace.QName;

/**
 * @author fsierra
 * 
 */
public interface AttributedURI extends AddressingType, AttributeExtensible {
	public URI getURI();

	public void addAttribute(QName name, String value);
}
