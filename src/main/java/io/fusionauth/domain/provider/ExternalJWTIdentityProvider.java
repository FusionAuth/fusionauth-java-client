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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.util.Normalizer;

/**
 * External JWT-only identity provider.
 *
 * @author Daniel DeGroff and Brian Pontarelli
 */
public class ExternalJWTIdentityProvider extends BaseIdentityProvider<ExternalJWTApplicationConfiguration> implements Buildable<ExternalJWTIdentityProvider>, DomainBasedIdentityProvider {
  public final Map<String, String> claimMap = new LinkedHashMap<>();

  public final Set<String> domains = new LinkedHashSet<>();

  /**
   * The default key used for External JWT verification.
   *
   * @deprecated Use {@link #verificationKeyIds} instead. Populated on read for backward compatibility from the first
   * entry in {@link #verificationKeyIds}.
   */
  @Deprecated
  public UUID defaultKeyId;

  /**
   * All configured verification key IDs. Sorted with the default key first ({@code is_default=true}).
   * The first entry also populates the deprecated {@link #defaultKeyId} field on read for backward compatibility.
   */
  public List<UUID> verificationKeyIds = new ArrayList<>();

  public String headerKeyParameter = "kid";

  public IdentityProviderOauth2Configuration oauth2 = new IdentityProviderOauth2Configuration();

  /**
   * If this value is defined, it will take precedence.
   *
   * @deprecated use oauth2.uniqueIdClaim, oauth2.usernameClaim and oauth2.emailClaim instead.
   */
  @Deprecated
  public String uniqueIdentityClaim;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ExternalJWTIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ExternalJWTIdentityProvider that = (ExternalJWTIdentityProvider) o;
    return Objects.equals(claimMap, that.claimMap) &&
           Objects.equals(defaultKeyId, that.defaultKeyId) &&
           Objects.equals(headerKeyParameter, that.headerKeyParameter) &&
           Objects.equals(oauth2, that.oauth2) &&
           Objects.equals(uniqueIdentityClaim, that.uniqueIdentityClaim) &&
           Objects.equals(domains, that.domains) &&
           Objects.equals(verificationKeyIds, that.verificationKeyIds);
  }

  @Override
  public Set<String> getDomains() {
    return domains;
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.ExternalJWT;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), claimMap, defaultKeyId, headerKeyParameter, oauth2, uniqueIdentityClaim, domains, verificationKeyIds);
  }

  @Override
  public void normalize() {
    super.normalize();
    Normalizer.toLowerCase(domains, HashSet::new);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
