package uy.gub.agesic.pge.core.ws.addressing;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.EndpointReference;

public class AddressingPropertiesImpl implements AddressingProperties {


	private List<Object> elements = new ArrayList<Object>();

	// A REQUIRED absolute URI representing the address of the intended receiver
	// of this message.
	private AttributedURI to;
	// A REQUIRED absolute URI that uniquely identifies the semantics implied by
	// this message.
        
        //Agregado 31-03-2015
        private AttributedURI topic;
        private AttributedURI producer;
        
        
        
	private AttributedURI action;
	// An OPTIONAL absolute URI that uniquely identifies the message.
	private AttributedURI messageId;
	// An OPTIONAL pair of values that indicate how this message relates to
	// another message.
	private Relationship[] relatesTo;
	// An OPTIONAL endpoint reference for the intended receiver for replies to
	// this message.
	private URI replyTo;
	// An OPTIONAL endpoint reference for the intended receiver for faults
	// related to this message.
	private EndpointReference faultTo;
	// An OPTIONAL reference to the endpoint from which the message originated.
	private EndpointReference from;
	// Corresponds to the value of the [reference parameters] property of the
	// endpoint reference to which the message is addressed.
	private ReferenceParameters refParams = new ReferenceParametersImpl();

	private Map<QName, AddressingType> addrTypes = new HashMap<QName, AddressingType>();

	public AttributedURI getTo() {
		return to;
	}

	public void setTo(AttributedURI to) {
		this.to = to;
	}
        
        
               
        //Agregado 31-03-2015
	public AttributedURI getTopic() {
		return topic;
	}

        public void setTopic(AttributedURI topic) {
                this.topic = topic;
        }

        public AttributedURI getProducer() {
                return producer;
        }

        public void setProducer(AttributedURI producer) {
                this.producer = producer;
        }
        //Fin agregado
        

	public AttributedURI getAction() {
		return action;
	}

	public void setAction(AttributedURI action) {
		this.action = action;
	}

	public AttributedURI getMessageID() {
		return messageId;
	}

	public void setMessageID(AttributedURI iri) {
		this.messageId = iri;
	}

	public Relationship[] getRelatesTo() {
		return relatesTo;
	}

	public void setRelatesTo(Relationship[] relatesTo) {
		this.relatesTo = relatesTo;
	}

	public URI getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(URI replyTo) {
		this.replyTo = replyTo;
	}

	public EndpointReference getFaultTo() {
		return faultTo;
	}

	public void setFaultTo(EndpointReference faultTo) {
		this.faultTo = faultTo;
	}

	public EndpointReference getFrom() {
		return from;
	}

	public void setFrom(EndpointReference from) {
		this.from = from;
	}

	public ReferenceParameters getReferenceParameters() {
		return refParams;
	}

	
	public int size() {
		return addrTypes.size();
	}

	public boolean isEmpty() {
		return addrTypes.isEmpty();
	}

	public boolean containsKey(Object arg0) {
		return addrTypes.containsKey(arg0);
	}

	public boolean containsValue(Object arg0) {
		return addrTypes.containsValue(arg0);
	}

	public AddressingType get(Object arg0) {
		return addrTypes.get(arg0);
	}

	public AddressingType put(QName arg0, AddressingType arg1) {
		return addrTypes.put(arg0, arg1);
	}

	public AddressingType remove(Object arg0) {
		return addrTypes.remove(arg0);
	}

	public void putAll(Map<? extends QName, ? extends AddressingType> arg0) {
		addrTypes.putAll(arg0);
	}

	public void clear() {
		addrTypes.clear();
	}

	public Set<QName> keySet() {
		return addrTypes.keySet();
	}

	public Collection<AddressingType> values() {
		return addrTypes.values();
	}

	public Set<Entry<QName, AddressingType>> entrySet() {
		return addrTypes.entrySet();
	}

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
