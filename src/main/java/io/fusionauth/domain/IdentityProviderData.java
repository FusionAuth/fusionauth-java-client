/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class IdentityProviderData implements Buildable<IdentityProviderData> {
  public Map<String, String> claimMap = new LinkedHashMap<>();

  public String headerKeyParameter;

  /**
   * Map of keys used for signature validation for this provider. Key Id to PEM encoded certificate of public key.
   */
  public Map<String, String> keys = new HashMap<>();

  public IdentityProviderOauth2Configuration oauth2 = new IdentityProviderOauth2Configuration();

  public String uniqueIdentityClaim;

  public UniqueIdentityClaimType uniqueIdentityClaimType;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof io.fusionauth.domain.IdentityProviderData)) {
      return false;
    }
    io.fusionauth.domain.IdentityProviderData that = (io.fusionauth.domain.IdentityProviderData) o;
    return Objects.equals(claimMap, that.claimMap) &&
        Objects.equals(headerKeyParameter, that.headerKeyParameter) &&
        Objects.equals(keys, that.keys) &&
        Objects.equals(oauth2, that.oauth2) &&
        Objects.equals(uniqueIdentityClaim, that.uniqueIdentityClaim) &&
        Objects.equals(uniqueIdentityClaimType, that.uniqueIdentityClaimType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(claimMap, headerKeyParameter, keys, oauth2, uniqueIdentityClaim, uniqueIdentityClaimType);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
