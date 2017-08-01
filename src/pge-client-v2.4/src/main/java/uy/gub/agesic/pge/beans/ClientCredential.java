/**
 * 
 */
package uy.gub.agesic.pge.beans;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * @author Guzman Llambias
 * @author Federico Sierra (ACCE)
 *
 */
public class ClientCredential {
	
	X509Certificate certificate;
	PrivateKey privateKey;
	
	public X509Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

}
