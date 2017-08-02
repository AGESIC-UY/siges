package uy.gub.agesic.pge.core.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class KeyStoreUtil {

    public static KeyStore loadKeyStore(String keyStoreUrl, String keyStorePwd) {
        InputStream keyStoreFis = getKeyStoreInputStream(keyStoreUrl);
        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance("JKS");
            keyStore.load(keyStoreFis, keyStorePwd.toCharArray());
            return keyStore;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static InputStream getKeyStoreInputStream(String keyStore) {
        InputStream is = null;

        try {
            // Try the file method
            File file = new File(keyStore);
            is = new FileInputStream(file);
            return is;
        } catch (Exception e) {
            URL url = null;
            try {
                url = new URL(keyStore);
                is = url.openStream();
                return is;
            } catch (Exception ex) {
                url = SecurityActions.loadResource(KeyStoreUtil.class, keyStore);
                if (url != null) {
                    try {
                        is = url.openStream();
                        return is;
                    } catch (IOException e1) {
                    }
                }
            }
        }

        throw new RuntimeException("Keystore not located: " + keyStore);

    }

    @SuppressWarnings({"resource"})
    public static String getKeystorePath(String keyStore) {
        InputStream is = null;
        try {
            // Try the file method
            File file = new File(keyStore);
            is = new FileInputStream(file);
            return keyStore;
        } catch (Exception e) {
            URL url = null;
            try {
                url = new URL(keyStore);
                return url.toString();
            } catch (Exception ex) {
                url = SecurityActions.loadResource(KeyStoreUtil.class, keyStore);
                if (url != null) {
                    return url.toString();
                }
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e1) {
                    }
                }
            }
        }

        throw new RuntimeException("Keystore not located: " + keyStore);

    }

    public static TrustManager[] getTrustManagers(KeyStore trustStore)
            throws NoSuchAlgorithmException, KeyStoreException {
        String alg = KeyManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory fac = TrustManagerFactory.getInstance(alg);
        fac.init(trustStore);
        return fac.getTrustManagers();
    }

    public static KeyManager[] getKeyManagers(KeyStore keyStore, String keyPassword)
            throws GeneralSecurityException, IOException {
        String alg = KeyManagerFactory.getDefaultAlgorithm();
        char[] keyPass = keyPassword != null ? keyPassword.toCharArray() : null;
        KeyManagerFactory fac = KeyManagerFactory.getInstance(alg);
        fac.init(keyStore, keyPass);
        return fac.getKeyManagers();
    }

    public static void main(String[] args) {

        KeyStoreUtil.loadKeyStore("file:///C:/servidores/jboss-as-7.1.1.FinalOT/PGE/vsiges_ssl.keystore", "agesic");
    }

}
