package uy.gub.agesic.pge.core.ws.addressing;

import java.net.URI;
import java.net.URISyntaxException;


public abstract class AddressingBuilder implements AddressingType {

	protected AddressingBuilder() {
	}

	public static AddressingBuilder getAddressingBuilder() {

		return new SOAPAddressingBuilderImpl();
	}

	public abstract AttributedURI newURI(URI uri);

	public abstract AttributedURI newURI(String uri) throws URISyntaxException;

	public abstract AddressingProperties newAddressingProperties();

	public abstract AddressingConstants newAddressingConstants();
}
