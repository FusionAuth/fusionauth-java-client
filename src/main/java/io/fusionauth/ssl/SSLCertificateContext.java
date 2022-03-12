package io.fusionauth.ssl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Base64;

/**
 * @author zcyang
 * @description get Singleton SSLSocketFactory
 */
public class SSLCertificateContext {

    private volatile static SSLCertificateContext instance = null;

    private static final Logger logger = LoggerFactory.getLogger(SSLCertificateContext.class);

    private final SSLSocketFactory sslSocketFactory;

    public static SSLCertificateContext getInstance(String cert) {
        if (instance == null) {
            synchronized (SSLCertificateContext.class) {
                if (instance == null) {
                    instance = new SSLCertificateContext(getSSLContext(cert));
                }
            }
        }
        return instance;
    }

    private SSLCertificateContext(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    public static SSLSocketFactory getSSLContext(String certificate) {
        SSLContext context = null;
        SSLSocketFactory factory = null;
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(certificate));
            Certificate ca = certificateFactory.generateCertificate(inputStream);
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(null, null);
            keystore.setCertificateEntry("ca", ca);
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm);
            trustManagerFactory.init(keystore);
            context = SSLContext.getInstance("TLS");
            context.init(null, trustManagerFactory.getTrustManagers(), null);
            factory = context.getSocketFactory();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return factory;
    }
}