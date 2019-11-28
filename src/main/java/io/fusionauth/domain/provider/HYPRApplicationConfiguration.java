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

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * @author Daniel DeGroff
 */
public class HYPRApplicationConfiguration extends BaseIdentityProviderApplicationConfiguration implements Buildable<HYPRApplicationConfiguration> {
  @InternalJSONColumn
  public boolean licensingEnabled;

  // An additional boolean is used to identify if licensingEnabled and licensingURL have been configured for this application.
  @InternalJSONColumn
  public boolean licensingEnabledOverride;

  @InternalJSONColumn
  public URI licensingURL;

  @InternalJSONColumn
  public String relyingPartyApplicationId;

  @InternalJSONColumn
  public URI relyingPartyURL;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof HYPRApplicationConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    HYPRApplicationConfiguration that = (HYPRApplicationConfiguration) o;
    return licensingEnabledOverride == that.licensingEnabledOverride &&
        Objects.equals(licensingEnabled, that.licensingEnabled) &&
        Objects.equals(licensingURL, that.licensingURL) &&
        Objects.equals(relyingPartyApplicationId, that.relyingPartyApplicationId) &&
        Objects.equals(relyingPartyURL, that.relyingPartyURL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), licensingEnabled, licensingEnabledOverride, licensingURL, relyingPartyApplicationId, relyingPartyURL);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
