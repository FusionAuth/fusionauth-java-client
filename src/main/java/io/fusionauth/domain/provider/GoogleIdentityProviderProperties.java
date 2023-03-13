/*
 * Copyright (c) 2018-2023, FusionAuth, All Rights Reserved
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

/**
 * Google social login provider parameters.
 *
 * @author Daniel DeGroff
 */
public class GoogleIdentityProviderProperties implements Buildable<GoogleIdentityProviderProperties> {
  public String api;

  public String button;

  public GoogleIdentityProviderProperties() {
  }

  public GoogleIdentityProviderProperties(String api, String button) {
    this.api = api;
    this.button = button;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GoogleIdentityProviderProperties that = (GoogleIdentityProviderProperties) o;
    return Objects.equals(api, that.api) && Objects.equals(button, that.button);
  }

  @Override
  public int hashCode() {
    return Objects.hash(api, button);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
