/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class HYPRIdentityProvider extends BaseIdentityProvider<HYPRApplicationConfiguration> implements Buildable<HYPRIdentityProvider>, PasswordlessIdentityProvider {
  public String relyingPartyApplicationId;

  public URI relyingPartyURL;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof HYPRIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    HYPRIdentityProvider that = (HYPRIdentityProvider) o;
    return Objects.equals(relyingPartyApplicationId, that.relyingPartyApplicationId) &&
           Objects.equals(relyingPartyURL, that.relyingPartyURL);
  }

  @Override
  public IdentityProviderType getType() {
    return IdentityProviderType.HYPR;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), relyingPartyApplicationId, relyingPartyURL);
  }

  public String lookupRelyingPartyApplicationId(UUID applicationId) {
    return lookup(() -> relyingPartyApplicationId, () -> app(applicationId, app -> app.relyingPartyApplicationId));
  }

  public URI lookupRelyingPartyURL(UUID applicationId) {
    return lookup(() -> relyingPartyURL, () -> app(applicationId, app -> app.relyingPartyURL));
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
