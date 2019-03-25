/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * SAML v2 identity provider configuration.
 *
 * @author Brian Pontarelli
 */
public class SAML2IdentityProvider extends BaseIdentityProvider<SAML2ApplicationConfiguration> implements Buildable<SAML2IdentityProvider>, DomainBasedIdentityProvider {
  @InternalJSONColumn
  public final Map<String, String> claimMap = new LinkedHashMap<>();

  public final Set<String> domains = new HashSet<>();

  @InternalJSONColumn
  public URI buttonImageURL;

  @InternalJSONColumn
  public String buttonText;

  @InternalJSONColumn
  public String emailClaim;

  @InternalJSONColumn
  public String idpEndpoint;

  @InternalJSONColumn
  public String requestPrivateKey;

  @InternalJSONColumn
  public String requestPublicKey;

  @InternalJSONColumn
  public String responsePublicKey;

  @InternalJSONColumn
  public String rolesClaim;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SAML2IdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    SAML2IdentityProvider that = (SAML2IdentityProvider) o;
    return Objects.equals(claimMap, that.claimMap) &&
        Objects.equals(domains, that.domains) &&
        Objects.equals(buttonImageURL, that.buttonImageURL) &&
        Objects.equals(buttonText, that.buttonText) &&
        Objects.equals(emailClaim, that.emailClaim) &&
        Objects.equals(idpEndpoint, that.idpEndpoint) &&
        Objects.equals(requestPrivateKey, that.requestPrivateKey) &&
        Objects.equals(requestPublicKey, that.requestPublicKey) &&
        Objects.equals(responsePublicKey, that.responsePublicKey) &&
        Objects.equals(rolesClaim, that.rolesClaim);
  }

  @Override
  public Set<String> getDomains() {
    return domains;
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.SAML2;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), claimMap, domains, buttonImageURL, buttonText, emailClaim, idpEndpoint, requestPrivateKey, requestPublicKey, responsePublicKey, rolesClaim);
  }

  @Override
  public SAML2IdentityProvider normalize() {
    // Lowercase the domains
    if (domains.size() > 0) {
      Set<String> newDomains = domains.stream().map(d -> d.toLowerCase().trim()).collect(Collectors.toSet());
      domains.clear();
      domains.addAll(newDomains);
    }

    return this;
  }

  public SAML2IdentityProvider secure() {
    claimMap.clear();
    domains.clear();
    emailClaim = null;
    requestPrivateKey = null;
    rolesClaim = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
