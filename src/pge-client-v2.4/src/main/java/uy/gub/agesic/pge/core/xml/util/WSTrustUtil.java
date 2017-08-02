package uy.gub.agesic.pge.core.xml.util;

import java.net.URI;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.GregorianCalendar;

import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.picketlink.identity.federation.core.saml.v1.SAML11Constants;
import org.picketlink.identity.federation.core.saml.v2.common.IDGenerator;
import org.picketlink.identity.federation.core.saml.v2.util.DocumentUtil;
import org.picketlink.identity.federation.core.saml.v2.util.XMLTimeUtil;
import org.picketlink.identity.federation.core.wstrust.WSTrustConstants;
import org.picketlink.identity.federation.core.wstrust.plugins.saml.SAMLUtil;
import org.picketlink.identity.federation.core.wstrust.wrappers.Lifetime;
import org.picketlink.identity.federation.saml.v1.assertion.SAML11AssertionType;
import org.picketlink.identity.federation.saml.v1.assertion.SAML11AttributeStatementType;
import org.picketlink.identity.federation.saml.v1.assertion.SAML11AttributeType;
import org.picketlink.identity.federation.saml.v1.assertion.SAML11AuthenticationStatementType;
import org.picketlink.identity.federation.saml.v1.assertion.SAML11ConditionsType;
import org.picketlink.identity.federation.saml.v1.assertion.SAML11NameIdentifierType;
import org.picketlink.identity.federation.saml.v1.assertion.SAML11SubjectConfirmationType;
import org.picketlink.identity.federation.saml.v1.assertion.SAML11SubjectType;
import org.picketlink.identity.federation.saml.v1.assertion.SAML11SubjectType.SAML11SubjectTypeChoice;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import uy.gub.agesic.pge.AgesicConstants;
import uy.gub.agesic.pge.beans.ClientCredential;
import uy.gub.agesic.pge.beans.RSTBean;
import uy.gub.agesic.pge.beans.SAMLAssertion;
import uy.gub.agesic.pge.exceptions.RequestSecurityTokenException;


/**
 * 
 * @author fsierra
 *
 */
public class WSTrustUtil {
	
	private static final Long DEFAULT_TOKENTIMEOUT = 900000l;
	/**
	 * Create a RequestSecurityToken (RST) message using a
	 * {@link SAMLAssertion} and the {@link RSTBean} information.
	 * 
	 * @param rstBean
	 *            the information to build the RST message (eg. service,
	 *            policyName, user role, etc)
	 * @param assertion
	 *            the assertion to put in the message
	 * @return A string representation of the RST message
	 * @throws RequestSecurityTokenException 
	 */
	public static SOAPMessage createRequestSecurityTokenMessage(RSTBean rstBean, ClientCredential credential, Long tokenTimeOut) throws RequestSecurityTokenException {
		 try {
			 XMLGregorianCalendar issueInstant =  XMLTimeUtil.getIssueInstant();
        	String assertionID = IDGenerator.create(AgesicConstants.ID_PREFIX);
        	
        	SAML11AssertionType assertionType = new SAML11AssertionType(assertionID, issueInstant);
            assertionType.setIssuer(rstBean.getIssuer());
        	
        	//subject
        	SAML11SubjectType subject = new SAML11SubjectType();
        	SAML11NameIdentifierType nameId = new SAML11NameIdentifierType(rstBean.getRole());
        	nameId.setFormat(URI.create(AgesicConstants.NAMEID_FORMAT));
        	SAML11SubjectTypeChoice subjectChoice = new SAML11SubjectTypeChoice(nameId);
        	
        	//confirmation method
        	SAML11SubjectConfirmationType subjectConfirmation = new SAML11SubjectConfirmationType();
        	subjectConfirmation.addConfirmationMethod(URI.create(AgesicConstants.SAML10_BEARER_CONFIRMATION_METHOD));
        	subject.setChoice(subjectChoice);
        	subject.setSubjectConfirmation(subjectConfirmation);
         	
        	//Conditions
        	if(tokenTimeOut == null){
        		tokenTimeOut = DEFAULT_TOKENTIMEOUT;
        	}
        	Lifetime lifetime = createDefaultLifetime(tokenTimeOut); 
        	SAML11ConditionsType conditions = new SAML11ConditionsType();
            conditions.setNotBefore(lifetime.getCreated());
            conditions.setNotOnOrAfter(lifetime.getExpires());
            assertionType.setConditions(conditions);
            
            //autentication statement
            URI authenticationMethod = URI.create(AgesicConstants.SAML10_PASSWD_AUTH_METHOD);
            SAML11AuthenticationStatementType authenticationStatementType = new SAML11AuthenticationStatementType(authenticationMethod, issueInstant);
            authenticationStatementType.setSubject(subject);
            assertionType.add(authenticationStatementType);
            
            SAML11AttributeStatementType attributeStatementType = new SAML11AttributeStatementType();
            SAML11AttributeType attributeType = new SAML11AttributeType(AgesicConstants.USER_ATTRIBUTE_NAME, URI.create(rstBean.getPolicyName()));
            attributeType.add("");
            attributeStatementType.setSubject(subject);
            attributeStatementType.add(attributeType);
            
            assertionType.add(attributeStatementType);
            
            Element assertion = SAMLUtil.toElement(assertionType);
            MessageFactory messageFactory = MessageFactory.newInstance();
	        
	        
	        Document tokenDocument = DocumentUtil.createDocument();
            assertion = (Element) tokenDocument.importNode(assertion, true);
            tokenDocument.appendChild(assertion);
            
            setupIDAttribute(assertion);
            X509Certificate x509Certificate = credential.getCertificate();
            PrivateKey privateKey = credential.getPrivateKey();
            tokenDocument = XMLSignatureUtil.sign(tokenDocument, assertion, privateKey, x509Certificate, 
            		DigestMethod.SHA1, SignatureMethod.RSA_SHA1, "#"+assertionID);
	        
            assertion = (Element)tokenDocument.getFirstChild();
            
            String messageID = IDGenerator.create(AgesicConstants.ID_PREFIX);
			String policyName = rstBean.getPolicyName();
			String role = rstBean.getRole();
			String service = rstBean.getService();
			
			SOAPMessage soapMessage = messageFactory.createMessage();
			SOAPPart part = soapMessage.getSOAPPart();
	        SOAPEnvelope envelope = part.getEnvelope();
	        envelope.addNamespaceDeclaration(AgesicConstants.WSA_PREFIX, AgesicConstants.WSA_NS);
	        
	        addHeader(soapMessage, messageID);
	        addRequestSecurityToken(soapMessage, policyName, service, assertion, role);
	        
	        return soapMessage;
		} catch (Exception e) {
			throw new RequestSecurityTokenException(e.getMessage(), e);
		} 
	}
	
	public static Lifetime createDefaultLifetime(long tokenTimeout) {
        GregorianCalendar created = new GregorianCalendar();
        GregorianCalendar expires = new GregorianCalendar();
        created.setTimeInMillis(created.getTimeInMillis() - tokenTimeout);
        expires.setTimeInMillis(expires.getTimeInMillis() + tokenTimeout);

        return new Lifetime(created, expires);
    }
	
	private static String setupIDAttribute(Node node) {
        if (node instanceof Element) {
            Element assertion = (Element) node;
            if (assertion.getLocalName().equals("Assertion")) {
                if (assertion.getNamespaceURI().equals(WSTrustConstants.SAML2_ASSERTION_NS) && assertion.hasAttribute("ID")) {
                    assertion.setIdAttribute("ID", true);
                    return "#" + assertion.getAttribute("ID");
                } else if (assertion.getNamespaceURI().equals(SAML11Constants.ASSERTION_11_NSURI)
                        && assertion.hasAttribute(SAML11Constants.ASSERTIONID)) {
                    assertion.setIdAttribute(SAML11Constants.ASSERTIONID, true);
                    return "#" + assertion.getAttribute(SAML11Constants.ASSERTIONID);
                }
            }
        }
        return "";
    }
	
	private static void addHeader(SOAPMessage soapMessage, String messageID) throws SOAPException {
		SOAPFactory factory = SOAPFactory.newInstance();
		
		SOAPElement actionElement = factory.createElement(AgesicConstants.ACTION, AgesicConstants.WSA_PREFIX, AgesicConstants.WSA_NS);
		QName actionName = new QName(AgesicConstants.WSE_NS, AgesicConstants.MUST_UNDERSTAND, AgesicConstants.WSE_PREFIX);
        actionElement.addAttribute(actionName, "1");
        actionElement.addTextNode(" ");
        soapMessage.getSOAPHeader().addChildElement(actionElement);	
        
        SOAPElement msgIdElement = factory.createElement(AgesicConstants.MSGID, AgesicConstants.WSA_PREFIX, AgesicConstants.WSA_NS);
        msgIdElement.addTextNode(messageID);
		soapMessage.getSOAPHeader().addChildElement(msgIdElement);	
	}
	
	private static void addRequestSecurityToken(SOAPMessage soapMessage, String policyName, String service, Element assertion, String role) throws SOAPException {
        SOAPFactory factory = SOAPFactory.newInstance();
        
        SOAPElement requestSecurityToken = factory.createElement(AgesicConstants.RST, AgesicConstants.WST_PREFIX, AgesicConstants.WST_NS);
        requestSecurityToken.addNamespaceDeclaration(AgesicConstants.WST_PREFIX, AgesicConstants.WST_NS);
        
        //creamos el tipo de token
        SOAPElement tokenType = factory.createElement(AgesicConstants.TOKEN_TYPE, AgesicConstants.WST_PREFIX, AgesicConstants.WST_NS);
        tokenType.addTextNode(AgesicConstants.SAML11_TOKEN_TYPE);
        requestSecurityToken.addChildElement(tokenType);
        
        //creamos el AppliesTo
        SOAPElement appliesTo = factory.createElement(AgesicConstants.APPLIESTO, AgesicConstants.WSP_PREFIX, AgesicConstants.WSP_NS);
        SOAPElement endpointReference = factory.createElement(AgesicConstants.ENDPOINT_REFERENCE, AgesicConstants.WSA_PREFIX, AgesicConstants.WSA_NS);
        SOAPElement address = factory.createElement(AgesicConstants.ADDRESS, AgesicConstants.WSA_PREFIX, AgesicConstants.WSA_NS);
        address.addTextNode(service);
        endpointReference.addChildElement(address);
        appliesTo.addChildElement(endpointReference);
        requestSecurityToken.addChildElement(appliesTo);
        
        //creamos el tipo de request
        SOAPElement requestType = factory.createElement(AgesicConstants.REQUEST_TYPE, AgesicConstants.WST_PREFIX, AgesicConstants.WST_NS);
        requestType.addTextNode(AgesicConstants.ISSUE_REQUEST);
        requestSecurityToken.addChildElement(requestType);
        
        //creamos el issuer
        SOAPElement issuer = factory.createElement(AgesicConstants.ISSUER, AgesicConstants.WST_PREFIX, AgesicConstants.WST_NS);
        address = factory.createElement(AgesicConstants.ADDRESS, AgesicConstants.WSA_PREFIX, AgesicConstants.WSA_NS);
        address.addTextNode(policyName);
        issuer.addChildElement(address);
        requestSecurityToken.addChildElement(issuer);
        
        //agregamos el token SAML
        SOAPElement base = factory.createElement(AgesicConstants.BASE, AgesicConstants.WST_PREFIX, AgesicConstants.WST_NS);
        Document doc = base.getOwnerDocument();
        assertion = (Element)doc.importNode(assertion, true);
        base.appendChild(assertion);
        requestSecurityToken.addChildElement(base);
        
        //agregamos el token rol
        SOAPElement secondaryParameters = factory.createElement(AgesicConstants.SECONDARY_PARAMETERS, AgesicConstants.WST_PREFIX, AgesicConstants.WST_NS);
        SOAPElement roleElement = factory.createElement(AgesicConstants.SECONDARY_PARAMETERS, AgesicConstants.WST_PREFIX, AgesicConstants.WST_NS);
        roleElement.addTextNode(role);
        secondaryParameters.addChildElement(roleElement);
        requestSecurityToken.addChildElement(secondaryParameters);
        
        soapMessage.getSOAPBody().addChildElement(requestSecurityToken);
    }
	
}
