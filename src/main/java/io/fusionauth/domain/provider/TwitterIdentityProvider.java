/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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
 * Twitter social login provider.
 *
 * @author Daniel DeGroff
 */
public class TwitterIdentityProvider extends BaseIdentityProvider<TwitterApplicationConfiguration> implements Buildable<TwitterIdentityProvider>, SupportsPostBindings {
  @InternalJSONColumn
  public String buttonText = "Login with Twitter";

  @InternalJSONColumn
  public String consumerKey;

  @InternalJSONColumn
  public String consumerSecret;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TwitterIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    TwitterIdentityProvider that = (TwitterIdentityProvider) o;
    return Objects.equals(buttonText, that.buttonText) &&
        Objects.equals(consumerKey, that.consumerKey) &&
        Objects.equals(consumerSecret, that.consumerSecret);
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.Twitter;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), buttonText, consumerKey, consumerSecret);
  }

  public String lookupButtonText(UUID applicationId) {
    return lookup(() -> buttonText, () -> app(applicationId, app -> app.buttonText));
  }

  public String lookupButtonText(String clientId) {
    return lookup(() -> buttonText, () -> app(clientId, app -> app.buttonText));
  }

  public String lookupConsumerKey(UUID applicationId) {
    return lookup(() -> consumerKey, () -> app(applicationId, app -> app.consumerKey));
  }

  public String lookupConsumerKey(String clientId) {
    return lookup(() -> consumerKey, () -> app(clientId, app -> app.consumerKey));
  }

  public String lookupConsumerSecret(UUID applicationId) {
    return lookup(() -> consumerSecret, () -> app(applicationId, app -> app.consumerSecret));
  }

  public String lookupConsumerSecret(String clientId) {
    return lookup(() -> consumerSecret, () -> app(clientId, app -> app.consumerSecret));
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
