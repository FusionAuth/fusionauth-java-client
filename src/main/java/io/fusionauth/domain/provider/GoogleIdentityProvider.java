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
 * Google social login provider.
 *
 * @author Daniel DeGroff
 */
public class GoogleIdentityProvider extends BaseIdentityProvider<GoogleApplicationConfiguration> implements Buildable<GoogleIdentityProvider> {
  @InternalJSONColumn
  public String buttonText;

  @InternalJSONColumn
  public String client_id;

  @InternalJSONColumn
  public String client_secret;

  @InternalJSONColumn
  public IdentityProviderLoginMethod loginMethod;

  @InternalJSONColumn
  public String scope;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GoogleIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    GoogleIdentityProvider that = (GoogleIdentityProvider) o;
    return Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(client_id, that.client_id) &&
           Objects.equals(client_secret, that.client_secret) &&
           loginMethod == that.loginMethod &&
           Objects.equals(scope, that.scope);
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.Google;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), buttonText, client_id, client_secret, loginMethod, scope);
  }

  public String lookupButtonText(String clientId) {
    return lookup(() -> buttonText, () -> app(clientId, app -> app.buttonText));
  }

  public String lookupClientId(UUID applicationId) {
    return lookup(() -> client_id, () -> app(applicationId, app -> app.client_id));
  }

  public String lookupClientId(String clientId) {
    return lookup(() -> client_id, () -> app(clientId, app -> app.client_id));
  }

  public String lookupClientSecret(UUID applicationId) {
    return lookup(() -> client_secret, () -> app(applicationId, app -> app.client_secret));
  }

  public IdentityProviderLoginMethod lookupLoginMethod(String clientId) {
    return lookup(() -> loginMethod, () -> app(clientId, app -> app.loginMethod));
  }

  public IdentityProviderLoginMethod lookupLoginMethod(UUID applicationId) {
    return lookup(() -> loginMethod, () -> app(applicationId, app -> app.loginMethod));
  }

  public String lookupScope(String clientId) {
    return lookup(() -> scope, () -> app(clientId, app -> app.scope));
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
