/**
 * 
 */
package uy.gub.agesic.pge;

/**
 * @author Guzman Llambias
 * @author Federico Sierra (ACCE)
 */
public class AgesicConstants {
	public static final String PASS_MASK_PREFIX = "MASK-";
	public static final String ID_PREFIX = "uuid-";
	public final static String SAML10_PASSWD_AUTH_METHOD = "urn:oasis:names:tc:SAML:1.0:am:password";
	public final static String NAMEID_FORMAT = "urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress";
	public final static String SAML10_BEARER_CONFIRMATION_METHOD = "urn:oasis:names:tc:SAML:1.0:cm:bearer";
	public final static String USER_ATTRIBUTE_NAME = "User";
	public static final String SAML1_PROPERTY = "uy.gub.agesic.security.saml";
	public static final String SAML_ACTOR = "actorDP";
	
	public static final String WST_PREFIX = "wst";
	
	public static final String RST = "RequestSecurityToken";
	public static final String TOKEN_TYPE = "TokenType";
	public static final String REQUEST_TYPE = "RequestType";
	public static final String ISSUER = "Issuer";
	public static final String ADDRESS = "Address";
	public static final String APPLIESTO = "AppliesTo";
	public static final String ENDPOINT_REFERENCE = "EndpointReference";
	public static final String BASE = "Base";
	public static final String SECONDARY_PARAMETERS = "SecondaryParameters";
	public static final String ACTION = "Action";
	public static final String MUST_UNDERSTAND ="mustUnderstand";
	public static final String MSGID = "MessageID";
	
	 // WSS namespaces values.
	public static final String WST_NS = "http://schemas.xmlsoap.org/ws/2005/02/trust";
	public static final String WSA_NS = "http://www.w3.org/2005/08/addressing";
	public static final String WSP_NS = "http://schemas.xmlsoap.org/ws/2004/09/policy";
	public static final String WSE_NS = "http://schemas.xmlsoap.org/soap/envelope/";
	public static final  String ISSUE_REQUEST = "http://schemas.xmlsoap.org/ws/2005/02/trust/Issue";
	
	
	public static final String WSA_PREFIX = "wsa";
	public static final String WSE_PREFIX = "env";
	public static final String WSP_PREFIX = "wsp";
	
	public static final String SAML11_TOKEN_TYPE = "http://docs.oasis-open.org/wss/oasis-wss-saml-token-profile-1.1#SAMLV1.1";
	
}

