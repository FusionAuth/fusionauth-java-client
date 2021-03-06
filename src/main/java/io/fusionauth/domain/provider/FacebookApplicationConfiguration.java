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

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * @author Daniel DeGroff
 */
public class FacebookApplicationConfiguration extends BaseIdentityProviderApplicationConfiguration implements Buildable<FacebookApplicationConfiguration> {
  @InternalJSONColumn
  public String appId;

  @InternalJSONColumn
  public String buttonText;

  @InternalJSONColumn
  public String client_secret;

  @InternalJSONColumn
  public String fields;

  @InternalJSONColumn
  public IdentityProviderLoginMethod loginMethod;

  @InternalJSONColumn
  public String permissions;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FacebookApplicationConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    FacebookApplicationConfiguration that = (FacebookApplicationConfiguration) o;
    return Objects.equals(appId, that.appId) &&
           Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(client_secret, that.client_secret) &&
           Objects.equals(fields, that.fields) &&
           loginMethod == that.loginMethod &&
           Objects.equals(permissions, that.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), appId, buttonText, client_secret, fields, loginMethod, permissions);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
