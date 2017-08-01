package uy.gub.agesic.pge.core.ws.addressing;

import java.net.URI;
import java.util.Map;

import javax.xml.namespace.QName;

public interface AddressingProperties extends AddressingType,
		Map<QName, AddressingType> {

	public AttributedURI getTo();

	public void setTo(AttributedURI iri);
        
        //Agregado 31-03-2015
        public void setTopic(AttributedURI iri);
        public AttributedURI getTopic();
        public void setProducer(AttributedURI iri);
        public AttributedURI getProducer();

	public AttributedURI getAction();

	public void setAction(AttributedURI iri);

	public AttributedURI getMessageID();
	
	public URI getReplyTo();

	public void setReplyTo(URI ref);

	public void setMessageID(AttributedURI iri);

	public Relationship[] getRelatesTo();

	public void setRelatesTo(Relationship[] relationship);

	public ReferenceParameters getReferenceParameters();

}
