package uy.gub.agesic.pge.core.xml.util;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.picketlink.identity.federation.core.exceptions.ParsingException;
import org.picketlink.identity.federation.core.exceptions.ProcessingException;
import org.picketlink.identity.federation.core.saml.v2.constants.JBossSAMLURIConstants;
import org.picketlink.identity.federation.core.saml.v2.util.DocumentUtil;
import org.picketlink.identity.federation.core.wstrust.WSTrustConstants;
import org.picketlink.identity.xmlsec.w3.xmldsig.DSAKeyValueType;
import org.picketlink.identity.xmlsec.w3.xmldsig.RSAKeyValueType;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLSignatureUtil {
    
    private static final Logger logger = Logger.getLogger(XMLSignatureUtil.class);
    
    
    private static String canonicalizationMethodType = CanonicalizationMethod.EXCLUSIVE;

    private static XMLSignatureFactory fac = getXMLSignatureFactory();

    /**
     * By default, we include the keyinfo in the signature
     */
    private static boolean includeKeyInfoInSignature = true;

    private static XMLSignatureFactory getXMLSignatureFactory() {
        XMLSignatureFactory xsf = null;

        try {
            xsf = XMLSignatureFactory.getInstance("DOM", "ApacheXMLDSig");
        } catch (NoSuchProviderException ex) {
            try {
                xsf = XMLSignatureFactory.getInstance("DOM");
            } catch (Exception err) {
                throw new RuntimeException(err);
            }
        }
        return xsf;
    }

    /**
     * Set the canonicalization method type
     *
     * @param canonical
     */
    public static void setCanonicalizationMethodType(String canonical) {
        if (canonical != null)
            canonicalizationMethodType = canonical;
    }

    /**
     * Use this method to not include the KeyInfo in the signature
     *
     * @param includeKeyInfoInSignature
     * @since v2.0.1
     */
    public static void setIncludeKeyInfoInSignature(boolean includeKeyInfoInSignature) {
        XMLSignatureUtil.includeKeyInfoInSignature = includeKeyInfoInSignature;
    }

    /**
     * Precheck whether the document that will be validated has the right signedinfo
     *
     * @param doc
     * @return
     */
    public static boolean preCheckSignedInfo(Document doc) {
        NodeList nl = doc.getElementsByTagNameNS(JBossSAMLURIConstants.XMLDSIG_NSURI.get(), "SignedInfo");
        return nl != null ? nl.getLength() > 0 : false;
    }


    /**
     * Sign a node in a document
     * 
     * @param doc
     * @param nodeToBeSigned
     * @param signingKey
     * @param certificate
     * @param digestMethod
     * @param signatureMethod
     * @param referenceURI
     * @return
     * @throws ParserConfigurationException
     * @throws GeneralSecurityException
     * @throws MarshalException
     * @throws XMLSignatureException
     */
    public static Document sign(Document doc, Node nodeToBeSigned, PrivateKey signingKey, X509Certificate certificate, String digestMethod,
            String signatureMethod, String referenceURI) throws ParserConfigurationException, GeneralSecurityException,
            MarshalException, XMLSignatureException {
        if (nodeToBeSigned == null)
            throw new IllegalArgumentException("Node to be signed");
        
        if (logger.isTraceEnabled()) {
            logger.trace("Document to be signed=" + DocumentUtil.asString(doc));
        }

        Node parentNode = nodeToBeSigned.getParentNode();

        // Let us create a new Document
        Document newDoc = DocumentUtil.createDocument();
        // Import the node
        Node signingNode = newDoc.importNode(nodeToBeSigned, true);
        newDoc.appendChild(signingNode);

        if (!referenceURI.isEmpty()) {
            propagateIDAttributeSetup(nodeToBeSigned, newDoc.getDocumentElement());
        }
        newDoc = sign(newDoc, signingKey, certificate, digestMethod, signatureMethod, referenceURI);

        // if the signed element is a SAMLv2.0 assertion we need to move the signature element to the position
        // specified in the schema (before the assertion subject element).
        if (nodeToBeSigned.getLocalName().equals("Assertion")
                && WSTrustConstants.SAML2_ASSERTION_NS.equals(nodeToBeSigned.getNamespaceURI())) {
            Node signatureNode = DocumentUtil.getElement(newDoc, new QName(WSTrustConstants.DSIG_NS, "Signature"));
            Node subjectNode = DocumentUtil.getElement(newDoc, new QName(WSTrustConstants.SAML2_ASSERTION_NS, "Subject"));
            if (signatureNode != null && subjectNode != null) {
                newDoc.getDocumentElement().removeChild(signatureNode);
                newDoc.getDocumentElement().insertBefore(signatureNode, subjectNode);
            }
        }

        // Now let us import this signed doc into the original document we got in the method call
        Node signedNode = doc.importNode(newDoc.getFirstChild(), true);

        if (!referenceURI.isEmpty()) {
            propagateIDAttributeSetup(newDoc.getDocumentElement(), (Element) signedNode);
        }

        parentNode.replaceChild(signedNode, nodeToBeSigned);
        // doc.getDocumentElement().replaceChild(signedNode, nodeToBeSigned);

        return doc;
    }

    /**
     * Sign only specified element (assumption is that it already has ID attribute set)
     *
     * @param elementToSign element to sign with set ID
     * @param nextSibling child of elementToSign, which will be used as next sibling of created signature
     * @param keyPair
     * @param digestMethod
     * @param signatureMethod
     * @param referenceURI
     * @throws GeneralSecurityException
     * @throws MarshalException
     * @throws XMLSignatureException
     */
    public static void sign(Element elementToSign, Node nextSibling, KeyPair keyPair, String digestMethod,
                            String signatureMethod, String referenceURI)
            throws GeneralSecurityException, MarshalException, XMLSignatureException {
        PrivateKey signingKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        DOMSignContext dsc = new DOMSignContext(signingKey, elementToSign, nextSibling);

        signImpl(dsc, digestMethod, signatureMethod, referenceURI, publicKey);
    }
    
    /**
     * Sign only specified element (assumption is that it already has ID attribute set)
     * 
     * @param elementToSign
     * @param nextSibling
     * @param privateKey
     * @param certificate
     * @param digestMethod
     * @param signatureMethod
     * @param referenceURI
     * @throws GeneralSecurityException
     * @throws MarshalException
     * @throws XMLSignatureException
     */
    public static void sign(Element elementToSign, Node nextSibling, PrivateKey privateKey, X509Certificate certificate, String digestMethod,
            String signatureMethod, String referenceURI)
		throws GeneralSecurityException, MarshalException, XMLSignatureException {
		
		DOMSignContext dsc = new DOMSignContext(privateKey, elementToSign, nextSibling);
		signImpl(dsc, digestMethod, signatureMethod, referenceURI, certificate);
	}
    

    /**
     * Setup the ID attribute into <code>destElement</code> depending on the <code>isId</code> flag of an attribute of
     * <code>sourceNode</code>.
     *
     * @param sourceNode
     * @param destElement
     */
    public static void propagateIDAttributeSetup(Node sourceNode, Element destElement) {
        NamedNodeMap nnm = sourceNode.getAttributes();
        for (int i = 0; i < nnm.getLength(); i++) {
            Attr attr = (Attr) nnm.item(i);
            if (attr.isId()) {
                destElement.setIdAttribute(attr.getName(), true);
                break;
            }
        }
    }

   /**
    * Sign the root element
    * 
    * @param doc
    * @param signingKey
    * @param certificate
    * @param digestMethod
    * @param signatureMethod
    * @param referenceURI
    * @return
    * @throws GeneralSecurityException
    * @throws MarshalException
    * @throws XMLSignatureException
    */
    public static Document sign(Document doc, PrivateKey signingKey, X509Certificate certificate, String digestMethod, String signatureMethod, String referenceURI)
            throws GeneralSecurityException, MarshalException, XMLSignatureException {
        logger.trace("Document to be signed=" + DocumentUtil.asString(doc));
        
        DOMSignContext dsc = new DOMSignContext(signingKey, doc.getDocumentElement());

        signImpl(dsc, digestMethod, signatureMethod, referenceURI, certificate);

        return doc;
    }
   

    /**
     * Validate a signed document with the given public key
     *
     * @param signedDoc
     * @param publicKey
     * @return
     * @throws MarshalException
     * @throws XMLSignatureException
     */
    @SuppressWarnings("unchecked")
    public static boolean validate(Document signedDoc, Key publicKey) throws MarshalException, XMLSignatureException {
        if (signedDoc == null)
            throw new IllegalArgumentException("Signed Document");

        propagateIDAttributeSetup(signedDoc.getDocumentElement(), signedDoc.getDocumentElement());

        NodeList nl = signedDoc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nl == null || nl.getLength() == 0) {
            throw new RuntimeException("Cannot find Signature element");
        }
        if (publicKey == null)
            throw new RuntimeException("Public Key");

        DOMValidateContext valContext = new DOMValidateContext(publicKey, nl.item(0));
        XMLSignature signature = fac.unmarshalXMLSignature(valContext);

        boolean coreValidity = signature.validate(valContext);

        if (logger.isTraceEnabled() && !coreValidity) {
            boolean sv = signature.getSignatureValue().validate(valContext);
            logger.trace("Signature validation status: " + sv);

            List<Reference> references = signature.getSignedInfo().getReferences();
            for (Reference ref : references) {
                logger.trace("[Ref id=" + ref.getId() + ":uri=" + ref.getURI() + "]validity status:" + ref.validate(valContext));
            }
        }
        return coreValidity;
    }

    /**
     * Marshall the signed document to an output stream
     *
     * @param signedDocument
     * @param os
     * @throws TransformerException
     */
    public static void marshall(Document signedDocument, OutputStream os) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(DocumentUtil.getXMLSource(signedDocument), new StreamResult(os));
    }

    /**
     * Given the X509Certificate in the keyinfo element, get a {@link X509Certificate}
     *
     * @param certificateString
     * @return
     * @throws ProcessingException
     */
    public static X509Certificate getX509CertificateFromKeyInfoString(String certificateString) throws ProcessingException {
        X509Certificate cert = null;
        StringBuilder builder = new StringBuilder();
        builder.append("-----BEGIN CERTIFICATE-----\n").append(certificateString).append("\n-----END CERTIFICATE-----");

        String derFormattedString = builder.toString();

        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream bais = new ByteArrayInputStream(derFormattedString.getBytes());

            while (bais.available() > 0) {
                cert = (X509Certificate) cf.generateCertificate(bais);
            }
        } catch (java.security.cert.CertificateException e) {
            throw new RuntimeException(e);
        }
        return cert;
    }

    
    /**
     * Given a dsig:DSAKeyValue element, return {@link DSAKeyValueType}
     * @param element
     * @return
     * @throws ProcessingException
     */
    public static RSAKeyValueType getRSAKeyValue(Element element) throws ParsingException {
        RSAKeyValueType rsa = new RSAKeyValueType();
        NodeList nl  = element.getChildNodes();
        int length = nl.getLength();

        for(int i = 0; i < length; i++){
            Node node  = nl.item(i);
            if(node instanceof Element){
                Element childElement = (Element) node;
                String tag = childElement.getLocalName();
                
                byte[] text = childElement.getTextContent().getBytes();
                
                if(WSTrustConstants.XMLDSig.MODULUS.equals(tag)){
                    rsa.setModulus(text);
                } else if(WSTrustConstants.XMLDSig.EXPONENT.equals(tag)){
                    rsa.setExponent(text);
                }
            }
        }

        return rsa;
    }

    

    private static void signImpl(DOMSignContext dsc, String digestMethod, String signatureMethod, String referenceURI, PublicKey publicKey)
            throws GeneralSecurityException, MarshalException, XMLSignatureException {
        dsc.setDefaultNamespacePrefix("dsig");

        DigestMethod digestMethodObj = fac.newDigestMethod(digestMethod, null);
        Transform transform1 = fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
        Transform transform2 = fac.newTransform("http://www.w3.org/2001/10/xml-exc-c14n#", (TransformParameterSpec) null);

        List<Transform> transformList = new ArrayList<Transform>();
        transformList.add(transform1);
        transformList.add(transform2);

        Reference ref = fac.newReference(referenceURI, digestMethodObj, transformList, null, null);

        CanonicalizationMethod canonicalizationMethod = fac.newCanonicalizationMethod(canonicalizationMethodType,
            (C14NMethodParameterSpec) null);

        List<Reference> referenceList = Collections.singletonList(ref);
        SignatureMethod signatureMethodObj = fac.newSignatureMethod(signatureMethod, null);
        SignedInfo si = fac.newSignedInfo(canonicalizationMethod, signatureMethodObj, referenceList);

        KeyInfoFactory kif = fac.getKeyInfoFactory();
        KeyValue kv = kif.newKeyValue(publicKey);
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kv));

        if (!includeKeyInfoInSignature) {
            ki = null;
        }
        XMLSignature signature = fac.newXMLSignature(si, ki);

        signature.sign(dsc);
    }
    
    private static void signImpl(DOMSignContext dsc, String digestMethod, String signatureMethod, String referenceURI, X509Certificate certificate)
            throws GeneralSecurityException, MarshalException, XMLSignatureException {
        dsc.setDefaultNamespacePrefix("dsig");

        DigestMethod digestMethodObj = fac.newDigestMethod(digestMethod, null);
        Transform transform1 = fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
        List<String> prefixList = new ArrayList<String>();
        prefixList.add("xs");
        TransformParameterSpec parameterSpec = new ExcC14NParameterSpec(prefixList);
        Transform transform2 = fac.newTransform("http://www.w3.org/2001/10/xml-exc-c14n#", parameterSpec);

        List<Transform> transformList = new ArrayList<Transform>();
        transformList.add(transform1);
        transformList.add(transform2);

        Reference ref = fac.newReference(referenceURI, digestMethodObj, transformList, null, null);

        CanonicalizationMethod canonicalizationMethod = fac.newCanonicalizationMethod(canonicalizationMethodType,
            (C14NMethodParameterSpec) null);

        List<Reference> referenceList = Collections.singletonList(ref);
        SignatureMethod signatureMethodObj = fac.newSignatureMethod(signatureMethod, null);
        SignedInfo si = fac.newSignedInfo(canonicalizationMethod, signatureMethodObj, referenceList);

        KeyInfoFactory kif = fac.getKeyInfoFactory();
        X509Data x509Data = kif.newX509Data(Collections.singletonList(certificate));
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(x509Data));

        XMLSignature signature = fac.newXMLSignature(si, ki);

        signature.sign(dsc);
    }
}
