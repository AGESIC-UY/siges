package uy.gub.agesic.pge.core.ws.addressing;

import javax.xml.soap.SOAPMessage;

public interface SOAPAddressingProperties extends AddressingProperties {
	public void readHeaders(SOAPMessage message) throws AddressingException;

	public void writeHeaders(SOAPMessage message) throws AddressingException;

	public void setMu(boolean mu);

}