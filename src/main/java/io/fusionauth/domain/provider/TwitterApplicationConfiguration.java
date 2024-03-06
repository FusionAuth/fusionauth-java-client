/*
 * Copyright (c) 2018-2024, FusionAuth, All Rights Reserved
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
 * @author Daniel DeGroff
 */
public class TwitterApplicationConfiguration extends BaseIdentityProviderApplicationConfiguration implements Buildable<TwitterApplicationConfiguration> {
  public String buttonText;

  public String consumerKey;

  public String consumerSecret;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TwitterApplicationConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    TwitterApplicationConfiguration that = (TwitterApplicationConfiguration) o;
    return Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(consumerKey, that.consumerKey) &&
           Objects.equals(consumerSecret, that.consumerSecret);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), buttonText, consumerKey, consumerSecret);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
