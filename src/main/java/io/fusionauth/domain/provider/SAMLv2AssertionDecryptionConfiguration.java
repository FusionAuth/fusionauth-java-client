/*
 * Copyright (c) 2024, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.Enableable;

/**
 * Configuration for encrypted assertions when acting as SAML Service Provider
 *
 * @author Jaret Hendrickson
 */
public class SAMLv2AssertionDecryptionConfiguration extends Enableable {
  public UUID keyTransportDecryptionKeyId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SAMLv2AssertionDecryptionConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    SAMLv2AssertionDecryptionConfiguration that = (SAMLv2AssertionDecryptionConfiguration) o;
    return Objects.equals(keyTransportDecryptionKeyId, that.keyTransportDecryptionKeyId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), keyTransportDecryptionKeyId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
