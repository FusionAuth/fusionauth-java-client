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
import io.fusionauth.domain.Enableable;

/**
 * @author Daniel DeGroff
 */
public class IdentityProviderLimitUserLinkingPolicy extends Enableable implements Buildable<IdentityProviderLimitUserLinkingPolicy> {
  public int maximumLinks = 42;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    IdentityProviderLimitUserLinkingPolicy that = (IdentityProviderLimitUserLinkingPolicy) o;
    return maximumLinks == that.maximumLinks;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), maximumLinks);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
