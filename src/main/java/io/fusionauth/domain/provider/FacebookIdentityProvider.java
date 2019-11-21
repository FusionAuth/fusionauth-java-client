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
 * Facebook social login provider.
 *
 * @author Brian Pontarelli
 */
public class FacebookIdentityProvider extends BaseIdentityProvider<FacebookApplicationConfiguration> implements Buildable<FacebookIdentityProvider> {
  @InternalJSONColumn
  public String appId;

  @InternalJSONColumn
  public String buttonText;

  @InternalJSONColumn
  public String client_secret;

  @InternalJSONColumn
  public String fields;

  @InternalJSONColumn
  public String permissions;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FacebookIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    FacebookIdentityProvider that = (FacebookIdentityProvider) o;
    return Objects.equals(appId, that.appId) &&
        Objects.equals(buttonText, that.buttonText) &&
        Objects.equals(client_secret, that.client_secret) &&
        Objects.equals(fields, that.fields) &&
        Objects.equals(permissions, that.permissions);
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.Facebook;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), appId, buttonText, client_secret, fields, permissions);
  }

  public String lookupAppId(UUID applicationId) {
    return lookup(() -> appId, () -> app(applicationId, app -> app.appId));
  }

  public String lookupAppId(String clientId) {
    return lookup(() -> appId, () -> app(clientId, app -> app.appId));
  }

  public String lookupButtonText(UUID applicationId) {
    return lookup(() -> buttonText, () -> app(applicationId, app -> app.buttonText));
  }

  public String lookupButtonText(String clientId) {
    return lookup(() -> buttonText, () -> app(clientId, app -> app.buttonText));
  }

  public String lookupClientSecret(UUID applicationId) {
    return lookup(() -> client_secret, () -> app(applicationId, app -> app.client_secret));
  }

  public String lookupFields(UUID applicationId) {
    return lookup(() -> fields, () -> app(applicationId, app -> app.fields));
  }

  public String lookupPermissions(UUID applicationId) {
    return lookup(() -> permissions, () -> app(applicationId, app -> app.permissions));
  }

  public String lookupPermissions(String clientId) {
    return lookup(() -> permissions, () -> app(clientId, app -> app.permissions));
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
