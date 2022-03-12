package io.fusionauth.ssl;

import com.inversoft.net.ssl.SSLTools;
import com.inversoft.rest.ClientResponse;
import com.inversoft.rest.RESTClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zcyang
 * @description custom RESTClient go() method
 */
public class SSLRestClient<RS, ERS> extends RESTClient<RS, ERS> {

    private static final Logger logger = LoggerFactory.getLogger(SSLRestClient.class);

    public SSLRestClient(Class<RS> successType, Class<ERS> errorType) {
        super(successType, errorType);
    }

    @Override
    public ClientResponse<RS, ERS> go() {
        if (this.url.length() == 0) {
            throw new IllegalStateException("You must specify a URL");
        } else {
            Objects.requireNonNull(this.method, "You must specify a HTTP method");
            if (this.successType != Void.TYPE && this.successResponseHandler == null) {
                throw new IllegalStateException("You specified a success response type, you must then provide a success response handler.");
            } else if (this.errorType != Void.TYPE && this.errorResponseHandler == null) {
                throw new IllegalStateException("You specified an error response type, you must then provide an error response handler.");
            } else {
                ClientResponse<RS, ERS> response = new ClientResponse();
                response.request = this.bodyHandler != null ? this.bodyHandler.getBodyObject() : null;
                response.method = this.method;

                HttpURLConnection huc;
                try {
                    if (this.parameters.size() > 0) {
                        if (this.url.indexOf("?") == -1) {
                            this.url.append("?");
                        }

                        Iterator i = this.parameters.entrySet().iterator();

                        while (i.hasNext()) {
                            Map.Entry<String, List<Object>> entry = (Map.Entry) i.next();
                            Iterator j = ((List) entry.getValue()).iterator();

                            while (j.hasNext()) {
                                Object value = j.next();
                                this.url.append(URLEncoder.encode((String) entry.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(value.toString(), "UTF-8"));
                                if (j.hasNext()) {
                                    this.url.append("&");
                                }
                            }

                            if (i.hasNext()) {
                                this.url.append("&");
                            }
                        }
                    }

                    response.url = new URL(this.url.toString());
                    huc = (HttpURLConnection) response.url.openConnection();
                    if (response.url.getProtocol().toLowerCase().equals("https")) {
                        HttpsURLConnection hsuc = (HttpsURLConnection) huc;
                        if (this.certificate != null) {
                            if (this.key != null) {
                                hsuc.setSSLSocketFactory(SSLTools.getSSLServerContext(this.certificate, this.key).getSocketFactory());
                            } else {
                                // I don't know why this method get ssl context not available, so custom it
                                //hsuc.setSSLSocketFactory(SSLTools.getSSLSocketFactory(this.certificate));
                                hsuc.setSSLSocketFactory(SSLCertificateContext.getInstance(this.certificate).getSslSocketFactory());
                            }
                        }

                        if (this.sniVerificationDisabled) {
                            hsuc.setHostnameVerifier((hostname, session) -> {
                                return true;
                            });
                        }
                    }

                    huc.setDoOutput(this.bodyHandler != null);
                    huc.setConnectTimeout(this.connectTimeout);
                    huc.setReadTimeout(this.readTimeout);
                    huc.setRequestMethod(this.method.toString());
                    if (this.headers.keySet().stream().noneMatch((name) -> {
                        return name.equalsIgnoreCase("User-Agent");
                    })) {
                        this.headers.put("User-Agent", this.userAgent);
                    }

                    if (this.headers.size() > 0) {
                        this.headers.forEach(huc::addRequestProperty);
                    }

                    if (this.bodyHandler != null) {
                        this.bodyHandler.setHeaders(huc);
                    }

                    huc.connect();
                    if (this.bodyHandler != null) {
                        OutputStream os = huc.getOutputStream();
                        Throwable var74 = null;

                        try {
                            this.bodyHandler.accept(os);
                            os.flush();
                        } catch (Throwable var64) {
                            var74 = var64;
                            throw var64;
                        } finally {
                            if (os != null) {
                                if (var74 != null) {
                                    try {
                                        os.close();
                                    } catch (Throwable var58) {
                                        var74.addSuppressed(var58);
                                    }
                                } else {
                                    os.close();
                                }
                            }

                        }
                    }
                } catch (Exception var70) {
                    logger.debug("Error calling REST WebService at [" + this.url + "]", var70);
                    response.status = -1;
                    response.exception = var70;
                    return response;
                }

                int status;
                try {
                    status = huc.getResponseCode();
                } catch (Exception var63) {
                    logger.debug("Error calling REST WebService at [" + this.url + "]", var63);
                    response.status = -1;
                    response.exception = var63;
                    return response;
                }

                response.status = status;
                InputStream is;
                Throwable var76;
                if (status >= 200 && status <= 299) {
                    if (this.successResponseHandler == null || this.method == RESTClient.HTTPMethod.HEAD) {
                        return response;
                    }

                    try {
                        is = huc.getInputStream();
                        var76 = null;

                        try {
                            response.successResponse = this.successResponseHandler.apply(is);
                        } catch (Throwable var61) {
                            var76 = var61;
                            throw var61;
                        } finally {
                            if (is != null) {
                                if (var76 != null) {
                                    try {
                                        is.close();
                                    } catch (Throwable var59) {
                                        var76.addSuppressed(var59);
                                    }
                                } else {
                                    is.close();
                                }
                            }

                        }
                    } catch (Exception var67) {
                        logger.debug("Error calling REST WebService at [" + this.url + "]", var67);
                        response.exception = var67;
                        return response;
                    }
                } else {
                    if (this.errorResponseHandler == null) {
                        return response;
                    }

                    try {
                        is = huc.getErrorStream();
                        var76 = null;

                        try {
                            response.errorResponse = this.errorResponseHandler.apply(is);
                        } catch (Throwable var62) {
                            var76 = var62;
                            throw var62;
                        } finally {
                            if (is != null) {
                                if (var76 != null) {
                                    try {
                                        is.close();
                                    } catch (Throwable var60) {
                                        var76.addSuppressed(var60);
                                    }
                                } else {
                                    is.close();
                                }
                            }

                        }
                    } catch (Exception var69) {
                        logger.debug("Error calling REST WebService at [" + this.url + "]", var69);
                        response.exception = var69;
                        return response;
                    }
                }

                return response;
            }
        }
    }
}
