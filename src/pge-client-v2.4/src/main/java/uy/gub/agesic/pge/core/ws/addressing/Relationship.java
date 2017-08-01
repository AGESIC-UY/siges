package uy.gub.agesic.pge.core.ws.addressing;

import java.net.URI;

import javax.xml.namespace.QName;

public interface Relationship extends AttributeExtensible {

	public URI getID();

	public QName getType();

	public void setType(QName type);

}
