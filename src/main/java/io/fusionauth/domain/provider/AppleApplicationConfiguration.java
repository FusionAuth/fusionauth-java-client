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

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * @author Daniel DeGroff
 */
public class AppleApplicationConfiguration extends BaseIdentityProviderApplicationConfiguration implements Buildable<AppleApplicationConfiguration> {
  @InternalJSONColumn
  public String buttonText;

  public UUID keyId;

  @InternalJSONColumn
  public String scope;

  @InternalJSONColumn
  public String servicesId;

  @InternalJSONColumn
  public String teamId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AppleApplicationConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    AppleApplicationConfiguration that = (AppleApplicationConfiguration) o;
    return Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(keyId, that.keyId) &&
           Objects.equals(scope, that.scope) &&
           Objects.equals(servicesId, that.servicesId) &&
           Objects.equals(teamId, that.teamId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), buttonText, keyId, scope, servicesId, teamId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
