package uy.gub.agesic.pge.core.ws.addressing;

import javax.xml.namespace.QName;


public class AddressingConstantsImpl implements AddressingConstants
{
   static final String URI_ADDRESSING = "http://www.w3.org/2005/08/addressing";
   static final String PREFIX_ADDRESSING = "wsa";

   public String getNamespaceURI()
   {
      return URI_ADDRESSING;
   }

   public String getNamespacePrefix()
   {
      return PREFIX_ADDRESSING;
   }

   public String getWSDLNamespaceURI()
   {
      return "http://schemas.xmlsoap.org/wsdl/";
   }

   public String getWSDLNamespacePrefix()
   {
      return "wsdl";
   }

   public QName getWSDLExtensibilityQName()
   {
      return null;
   }

   public QName getWSDLActionQName()
   {
      return new QName(URI_ADDRESSING, "Action", "wsa");
   }

   public String getAnonymousURI()
   {
      return "http://www.w3.org/2005/08/addressing/anonymous";
   }

   public String getNoneURI()
   {
      return "http://www.w3.org/2005/08/addressing/none";
   }

   public QName getFromQName()
   {
      return new QName(URI_ADDRESSING, "From", PREFIX_ADDRESSING);
   }

   public QName getToQName()
   {
      return new QName(URI_ADDRESSING, "To", PREFIX_ADDRESSING);
   }
   
   //Agregado 31-03-2015
   public QName getProducerQName(){
       return new QName(URI_ADDRESSING, "producer", PREFIX_ADDRESSING);
   }
   
   //Agregado 31-03-2015
   public QName getTopicQName(){
       return new QName(URI_ADDRESSING, "topic", PREFIX_ADDRESSING);
   }

   public QName getReplyToQName()
   {
      return new QName(URI_ADDRESSING, "ReplyTo", PREFIX_ADDRESSING);
   }

   public QName getFaultToQName()
   {
      return new QName(URI_ADDRESSING, "FaultTo", PREFIX_ADDRESSING);
   }

   public QName getActionQName()
   {
      return new QName(URI_ADDRESSING, "Action", PREFIX_ADDRESSING);
   }

   public QName getMessageIDQName()
   {
      return new QName(URI_ADDRESSING, "MessageID", PREFIX_ADDRESSING);
   }

   public QName getRelationshipReplyQName()
   {
      return new QName(URI_ADDRESSING, "Reply", PREFIX_ADDRESSING);
   }

   public QName getRelatesToQName()
   {
      return new QName(URI_ADDRESSING, "RelatesTo", PREFIX_ADDRESSING);
   }

   public String getRelationshipTypeName()
   {
      return "RelationshipType";
   }

   public QName getMetadataQName()
   {
      return new QName(URI_ADDRESSING, "Metadata", PREFIX_ADDRESSING);
   }

   public QName getAddressQName()
   {
      return new QName(URI_ADDRESSING, "Address", PREFIX_ADDRESSING);
   }

   public QName getReferenceParametersQName()
   {
      return new QName(URI_ADDRESSING, "ReferenceParameters", PREFIX_ADDRESSING);
   }
   
   public String getPackageName()
   {
      return getClass().getPackage().getName();
   }

   public String getIsReferenceParameterName()
   {
      throw new UnsupportedOperationException();
   }

   public QName getInvalidMapQName()
   {
      return new QName(URI_ADDRESSING, "InvalidMessageInformationHeader", PREFIX_ADDRESSING);
   }

   public QName getMapRequiredQName()
   {
      return new QName(URI_ADDRESSING, "MessageAddressingHeaderRequired", PREFIX_ADDRESSING);
   }

   public QName getDestinationUnreachableQName()
   {
      return new QName(URI_ADDRESSING, "DestinationUnreachable", PREFIX_ADDRESSING);
   }

   public QName getActioNotSupportedQName()
   {
      return new QName(URI_ADDRESSING, "ActionNotSupported", PREFIX_ADDRESSING);
   }

   public QName getEndpointUnavailableQName()
   {
      return new QName(URI_ADDRESSING, "EndpointUnavailable", PREFIX_ADDRESSING);
   }

   public String getDefaultFaultAction()
   {
      return "http://www.w3.org/2005/08/addressing/fault";
   }

   public String getActionNotSupportedText()
   {
      return "Action not supported";
   }

   public String getDestinationUnreachableText()
   {
      return "Destination unreachable";
   }

   public String getEndpointUnavailableText()
   {
      return "Endpoint unavailable";
   }

   public String getInvalidMapText()
   {
      return "Invalid Map";
   }

   public String getMapRequiredText()
   {
      return "A required header representing a Message Addressing Property is not present";
   }
}

