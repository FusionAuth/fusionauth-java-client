/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.provider;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;

import io.fusionauth.domain.Buildable;

/**
 * Twitch gaming login provider.
 *
 * @author Brett Pontarelli
 */
public class TwitchIdentityProvider extends BaseIdentityProvider<TwitchApplicationConfiguration> implements Buildable<TwitchIdentityProvider>, SupportsPostBindings {
  
  public String buttonText = "Login with Twitch";

  
  public String client_id;

  
  public String client_secret;

  
  public String scope = "openid user:read:email";

  public TwitchIdentityProvider() {
    linkingStrategy = IdentityProviderLinkingStrategy.CreatePendingLink;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TwitchIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    TwitchIdentityProvider that = (TwitchIdentityProvider) o;
    return Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(client_id, that.client_id) &&
           Objects.equals(client_secret, that.client_secret) &&
           Objects.equals(scope, that.scope);
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.Twitch;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), buttonText, client_id, client_secret, scope);
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

  public String lookupScope(String clientId) {
    return lookup(() -> scope, () -> app(clientId, app -> app.scope));
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
