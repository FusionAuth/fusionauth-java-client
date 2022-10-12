/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
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
  
  public String buttonText = "Login with Steam";

  
  public String client_id;

  
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
    return Objects.equals(buttonText, that.buttonText) &&
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
    return Objects.hash(super.hashCode(), buttonText, client_id, webAPIKey, scope);
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
