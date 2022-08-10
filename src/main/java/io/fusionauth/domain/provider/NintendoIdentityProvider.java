/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
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
 * Nintendo gaming login provider.
 *
 * @author Brett Pontarelli
 */
public class NintendoIdentityProvider extends BaseIdentityProvider<NintendoApplicationConfiguration> implements Buildable<NintendoIdentityProvider>, SupportsPostBindings {
  
  public String buttonText = "Login with Nintendo";

  
  public String client_id;

  
  public String client_secret;

  
  public String emailClaim = "email";

  
  public String scope;

  
  public String uniqueIdClaim = "id";

  
  public String usernameClaim = "preferred_username";

  public NintendoIdentityProvider() {
    linkingStrategy = IdentityProviderLinkingStrategy.CreatePendingLink;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NintendoIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    NintendoIdentityProvider that = (NintendoIdentityProvider) o;
    return Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(client_id, that.client_id) &&
           Objects.equals(client_secret, that.client_secret) &&
           Objects.equals(emailClaim, that.emailClaim) &&
           Objects.equals(scope, that.scope) &&
           Objects.equals(uniqueIdClaim, that.uniqueIdClaim) &&
           Objects.equals(usernameClaim, that.usernameClaim);
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.Nintendo;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), buttonText, client_id, client_secret, emailClaim, scope, uniqueIdClaim, usernameClaim);
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

  public String lookupEmailClaim(UUID applicationId) {
    return lookup(() -> emailClaim, () -> app(emailClaim, app -> app.emailClaim));
  }

  public String lookupScope(String clientId) {
    return lookup(() -> scope, () -> app(clientId, app -> app.scope));
  }

  public String lookupUniqueIdClaim(UUID applicationId) {
    return lookup(() -> uniqueIdClaim, () -> app(uniqueIdClaim, app -> app.uniqueIdClaim));
  }

  public String lookupUsernameClaim(UUID applicationId) {
    return lookup(() -> usernameClaim, () -> app(usernameClaim, app -> app.usernameClaim));
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
