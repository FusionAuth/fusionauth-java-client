/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;



/**
 * @author Daniel DeGroff
 */
public class IdentityProviderTenantConfiguration implements Buildable<IdentityProviderTenantConfiguration> {
  // This is used for InternalJSONColumn and we don't document any custom data. If we do end up documenting custom data, remove this annotation.
  @JsonInclude(Include.NON_EMPTY)
  public final Map<String, Object> data = new HashMap<>();

  
  public IdentityProviderLimitUserLinkingPolicy limitUserLinkCount = new IdentityProviderLimitUserLinkingPolicy();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IdentityProviderTenantConfiguration)) {
      return false;
    }
    IdentityProviderTenantConfiguration that = (IdentityProviderTenantConfiguration) o;
    return Objects.equals(data, that.data) && Objects.equals(limitUserLinkCount, that.limitUserLinkCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, limitUserLinkCount);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
