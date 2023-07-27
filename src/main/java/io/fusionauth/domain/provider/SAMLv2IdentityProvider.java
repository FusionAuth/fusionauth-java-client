/*
 * Copyright (c) 2019-2023, FusionAuth, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package io.fusionauth.domain.provider;

import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.ToString;

import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.CORSConfiguration;
import io.fusionauth.domain.CanonicalizationMethod;
import io.fusionauth.domain.RequiresCORSConfiguration;
import io.fusionauth.domain.util.HTTPMethod;

/**
 * SAML v2 identity provider configuration.
 *
 * @author Brian Pontarelli
 */
public class SAMLv2IdentityProvider extends BaseSAMLv2IdentityProvider<SAMLv2ApplicationConfiguration>
    implements Buildable<SAMLv2IdentityProvider>, DomainBasedIdentityProvider, RequiresCORSConfiguration, SupportsPostBindings {
  public final Set<String> domains = new LinkedHashSet<>();

  
  public SAMLv2AssertionConfiguration assertionConfiguration = new SAMLv2AssertionConfiguration();

  
  public URI buttonImageURL;

  
  public String buttonText = "Login with SAML";

  
  public URI idpEndpoint;

  
  public SAMLv2IdpInitiatedConfiguration idpInitiatedConfiguration = new SAMLv2IdpInitiatedConfiguration(false);

  /**
   * @deprecated The 'issuer' is auto generated to be unique per configuration. Do not use this value any longer.  The 'issuer' will be equal to
   * ${public_url}/samlv2/sp/${identityProviderId}.
   */
  @Deprecated
  
  public String issuer;

  
  // Note: Once we de-couple fusionauth-java-client this should be either copied or moved to io.fusionauth.domain since it is used in multiple places.
  public LoginHintConfiguration loginHintConfiguration = new LoginHintConfiguration(true);

  
  public String nameIdFormat = "urn:oasis:names:tc:SAML:2.0:nameid-format:persistent";

  
  public boolean postRequest;

  public UUID requestSigningKeyId;

  
  public boolean signRequest;

  
  public CanonicalizationMethod xmlSignatureC14nMethod;

  @Override
  @JsonIgnore
  public CORSConfiguration corsConfiguration() {
    return new CORSConfiguration().with(override -> override.allowedMethods.add(HTTPMethod.POST))
                                  .with(override -> override.allowedOrigins.add(URI.create(idpEndpoint.getScheme() + "://" + idpEndpoint.getHost() + (idpEndpoint.getPort() == -1 ? "" : ":" + idpEndpoint.getPort()))));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SAMLv2IdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    SAMLv2IdentityProvider that = (SAMLv2IdentityProvider) o;
    return postRequest == that.postRequest &&
           signRequest == that.signRequest &&
           Objects.equals(domains, that.domains) &&
           Objects.equals(assertionConfiguration, that.assertionConfiguration) &&
           Objects.equals(buttonImageURL, that.buttonImageURL) &&
           Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(idpEndpoint, that.idpEndpoint) &&
           Objects.equals(issuer, that.issuer) &&
           Objects.equals(loginHintConfiguration, that.loginHintConfiguration) &&
           Objects.equals(nameIdFormat, that.nameIdFormat) &&
           Objects.equals(requestSigningKeyId, that.requestSigningKeyId) &&
           Objects.equals(idpInitiatedConfiguration, that.idpInitiatedConfiguration) &&
           xmlSignatureC14nMethod == that.xmlSignatureC14nMethod;
  }

  @Override
  public Set<String> getDomains() {
    return domains;
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.SAMLv2;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(),
                        domains,
                        assertionConfiguration,
                        buttonImageURL,
                        buttonText,
                        idpEndpoint,
                        issuer,
                        loginHintConfiguration,
                        nameIdFormat,
                        postRequest,
                        requestSigningKeyId,
                        signRequest,
                        idpInitiatedConfiguration,
                        xmlSignatureC14nMethod);
  }

  public URI lookupButtonImageURL(String clientId) {
    return lookup(() -> buttonImageURL, () -> app(clientId, app -> app.buttonImageURL));
  }

  public URI lookupButtonImageURL(UUID applicationId) {
    return lookup(() -> buttonImageURL, () -> app(applicationId, app -> app.buttonImageURL));
  }

  public String lookupButtonText(String clientId) {
    return lookup(() -> buttonText, () -> app(clientId, app -> app.buttonText));
  }

  public String lookupButtonText(UUID applicationId) {
    return lookup(() -> buttonText, () -> app(applicationId, app -> app.buttonText));
  }

  @Override
  public void normalize() {
    super.normalize();
    normalizeDomains();
  }

  @Override
  public boolean postRequestEnabled() {
    return postRequest;
  }

  public SAMLv2IdentityProvider secure() {
    domains.clear();
    emailClaim = null;
    usernameClaim = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
