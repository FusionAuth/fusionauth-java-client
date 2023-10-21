/*
 * Copyright (c) 2021-2023, FusionAuth, All Rights Reserved
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

/**
 * Steam gaming login provider.
 *
 * @author Brett Pontarelli
 */
public class SteamIdentityProvider extends BaseIdentityProvider<SteamApplicationConfiguration> implements Buildable<SteamIdentityProvider>, SupportsPostBindings {
  public SteamAPIMode apiMode = SteamAPIMode.Public;

  public String buttonText = "Login with Steam";

  public String client_id;

  // Note that we are not currently ever passing scope to Steam. Let's not document this unless we're going to use it.
  public String scope;

  public String webAPIKey;

  public SteamIdentityProvider() {
    linkingStrategy = IdentityProviderLinkingStrategy.CreatePendingLink;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SteamIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    SteamIdentityProvider that = (SteamIdentityProvider) o;
    return Objects.equals(apiMode, that.apiMode) &&
           Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(client_id, that.client_id) &&
           Objects.equals(webAPIKey, that.webAPIKey) &&
           Objects.equals(scope, that.scope);
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.Steam;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), apiMode, buttonText, client_id, webAPIKey, scope);
  }

  public SteamAPIMode lookupAPIMode(UUID applicationId) {
    return lookup(() -> apiMode, () -> app(applicationId, app -> app.apiMode));
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

  public String lookupScope(String clientId) {
    return lookup(() -> scope, () -> app(clientId, app -> app.scope));
  }

  public String lookupWebAPIKey(UUID applicationId) {
    return lookup(() -> webAPIKey, () -> app(applicationId, app -> app.webAPIKey));
  }

  @Override
  public boolean postRequestEnabled() {
    return false;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
