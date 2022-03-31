/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
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

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Rob Davis
 */
public class TenantLambdaConfiguration implements Buildable<TenantLambdaConfiguration> {
  public UUID scimEnterpriseUserRequestConverterId;

  public UUID scimEnterpriseUserResponseConverterId;

  public UUID scimGroupRequestConverterId;

  public UUID scimGroupResponseConverterId;

  public UUID scimUserRequestConverterId;

  public UUID scimUserResponseConverterId;

  @JacksonConstructor
  public TenantLambdaConfiguration() {
  }

  public TenantLambdaConfiguration(TenantLambdaConfiguration other) {
    this.scimEnterpriseUserRequestConverterId = other.scimEnterpriseUserRequestConverterId;
    this.scimEnterpriseUserResponseConverterId = other.scimEnterpriseUserResponseConverterId;
    this.scimGroupRequestConverterId = other.scimGroupRequestConverterId;
    this.scimGroupResponseConverterId = other.scimGroupResponseConverterId;
    this.scimUserRequestConverterId = other.scimUserRequestConverterId;
    this.scimUserResponseConverterId = other.scimUserResponseConverterId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TenantLambdaConfiguration)) {
      return false;
    }
    TenantLambdaConfiguration that = (TenantLambdaConfiguration) o;
    return Objects.equals(scimEnterpriseUserRequestConverterId, that.scimEnterpriseUserRequestConverterId) &&
           Objects.equals(scimEnterpriseUserResponseConverterId, that.scimEnterpriseUserResponseConverterId) &&
           Objects.equals(scimGroupRequestConverterId, that.scimGroupRequestConverterId) &&
           Objects.equals(scimGroupResponseConverterId, that.scimGroupResponseConverterId) &&
           Objects.equals(scimUserRequestConverterId, that.scimUserRequestConverterId) &&
           Objects.equals(scimUserResponseConverterId, that.scimUserResponseConverterId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scimEnterpriseUserRequestConverterId,
                        scimEnterpriseUserResponseConverterId,
                        scimGroupRequestConverterId,
                        scimGroupResponseConverterId,
                        scimUserRequestConverterId,
                        scimUserResponseConverterId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}