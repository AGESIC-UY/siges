package uy.gub.agesic.pge.core.ws.addressing;

import javax.xml.namespace.QName;

public interface AddressingConstants {
	String getNamespaceURI();

	   String getNamespacePrefix();

	   String getWSDLNamespaceURI();

	   String getWSDLNamespacePrefix();

	   QName getWSDLExtensibilityQName();

	   QName getWSDLActionQName();

	   String getAnonymousURI();

	   String getNoneURI();

	   QName getFromQName();

	   QName getToQName();
           
           //Agregado 31-03-2015
           QName getTopicQName();
           QName getProducerQName();

	   QName getReplyToQName();

	   QName getFaultToQName();

	   QName getActionQName();

	   QName getMessageIDQName();

	   QName getRelationshipReplyQName();

	   QName getRelatesToQName();

	   String getRelationshipTypeName();

	   // [TODO] Add this method
	   QName getReferenceParametersQName();

	   QName getMetadataQName();

	   QName getAddressQName();

	   String getPackageName();

	   String getIsReferenceParameterName();

	   QName getInvalidMapQName();

	   QName getMapRequiredQName();

	   QName getDestinationUnreachableQName();

	   QName getActioNotSupportedQName();

	   QName getEndpointUnavailableQName();

	   String getDefaultFaultAction();

	   String getActionNotSupportedText();

	   String getDestinationUnreachableText();

	   String getEndpointUnavailableText();

	   String getInvalidMapText();

	   String getMapRequiredText();
}
