/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
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

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * @author Daniel DeGroff
 */
public class IdentityProviderLink implements Buildable<IdentityProviderLink>, _InternalJSONColumn, Tenantable {
  public Map<String, Object> data = new LinkedHashMap<>();

  @InternalJSONColumn
  public String displayName;

  public UUID identityProviderId;

  public String identityProviderUserId;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastLoginInstant;

  public UUID tenantId;

  @InternalJSONColumn
  public String token;

  public UUID userId;

  @JacksonConstructor
  public IdentityProviderLink() {
  }

  public IdentityProviderLink(UUID identityProviderId, String identityProviderUserId, UUID userId) {
    this.identityProviderId = identityProviderId;
    this.identityProviderUserId = identityProviderUserId;
    this.userId = userId;
  }

  public IdentityProviderLink(String displayName, UUID identityProviderId, String identityProviderUserId, UUID userId) {
    this.displayName = displayName;
    this.identityProviderId = identityProviderId;
    this.identityProviderUserId = identityProviderUserId;
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IdentityProviderLink that = (IdentityProviderLink) o;
    return Objects.equals(data, that.data) &&
           Objects.equals(displayName, that.displayName) &&
           Objects.equals(identityProviderId, that.identityProviderId) &&
           Objects.equals(identityProviderUserId, that.identityProviderUserId) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastLoginInstant, that.lastLoginInstant) &&
           Objects.equals(tenantId, that.tenantId) &&
           Objects.equals(token, that.token) &&
           Objects.equals(userId, that.userId);
  }

  @Override
  public UUID getTenantId() {
    return tenantId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, displayName, identityProviderId, identityProviderUserId, insertInstant, lastLoginInstant, tenantId, token, userId);
  }

  @Override
  public String
  toString() {
    return ToString.toString(this);
  }
}
