package uy.gub.agesic.pge.core.ws.addressing;

import java.util.Map;

import javax.xml.namespace.QName;

public interface AttributeExtensible extends AddressingType{
	
	 public Map<QName, String> getAttributes();

	   public void addAttribute(QName name, String value) throws AddressingException;
}
