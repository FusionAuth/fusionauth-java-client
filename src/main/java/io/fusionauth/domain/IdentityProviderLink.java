/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;



/**
 * @author Daniel DeGroff
 */
public class IdentityProviderLink implements Buildable<IdentityProviderLink>, Tenantable {
  // This is used for InternalJSONColumn and we don't document any custom data. If we do end up documenting custom data, remove this annotation.
  @JsonInclude(Include.NON_EMPTY)
  public Map<String, Object> data = new LinkedHashMap<>();

  
  public String displayName;

  public UUID identityProviderId;

  public String identityProviderUserId;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastLoginInstant;

  public UUID tenantId;

  
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
