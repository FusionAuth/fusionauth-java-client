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
package io.fusionauth.domain.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * @author Daniel DeGroff
 */
public class IdentityProviderTenantConfiguration implements Buildable<IdentityProviderTenantConfiguration>, _InternalJSONColumn {
  public final Map<String, Object> data = new HashMap<>();

  @InternalJSONColumn
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
