/*
 * Copyright (c) 2019-2023, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * Password Encryption Scheme Configuration
 *
 * @author Daniel DeGroff
 */
public class PasswordEncryptionConfiguration implements Buildable<PasswordEncryptionConfiguration> {
  public String encryptionScheme;

  public int encryptionSchemeFactor;

  public boolean modifyEncryptionSchemeOnLogin;

  @JacksonConstructor
  public PasswordEncryptionConfiguration() {
  }

  public PasswordEncryptionConfiguration(PasswordEncryptionConfiguration other) {
    this.encryptionScheme = other.encryptionScheme;
    this.encryptionSchemeFactor = other.encryptionSchemeFactor;
    this.modifyEncryptionSchemeOnLogin = other.modifyEncryptionSchemeOnLogin;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PasswordEncryptionConfiguration that = (PasswordEncryptionConfiguration) o;
    return modifyEncryptionSchemeOnLogin == that.modifyEncryptionSchemeOnLogin &&
           Objects.equals(encryptionScheme, that.encryptionScheme) &&
           Objects.equals(encryptionSchemeFactor, that.encryptionSchemeFactor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(encryptionScheme, encryptionSchemeFactor, modifyEncryptionSchemeOnLogin);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
