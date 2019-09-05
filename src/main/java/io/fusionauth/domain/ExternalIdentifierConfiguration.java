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
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class ExternalIdentifierConfiguration implements Buildable<ExternalIdentifierConfiguration> {
  public int authorizationGrantIdTimeToLiveInSeconds;

  public SecureGeneratorConfiguration changePasswordIdGenerator;

  public int changePasswordIdTimeToLiveInSeconds;

  public SecureGeneratorConfiguration emailVerificationIdGenerator;

  public int emailVerificationIdTimeToLiveInSeconds;

  public int oneTimePasswordTimeToLiveInSeconds;

  public SecureGeneratorConfiguration passwordlessLoginGenerator;

  public int passwordlessLoginTimeToLiveInSeconds;

  public SecureGeneratorConfiguration registrationVerificationIdGenerator;

  public int registrationVerificationIdTimeToLiveInSeconds;

  public SecureGeneratorConfiguration setupPasswordIdGenerator;

  public int setupPasswordIdTimeToLiveInSeconds;

  public int twoFactorIdTimeToLiveInSeconds;

  public int twoFactorTrustIdTimeToLiveInSeconds;

  @JacksonConstructor
  public ExternalIdentifierConfiguration() {
  }

  public ExternalIdentifierConfiguration(ExternalIdentifierConfiguration other) {
    this.authorizationGrantIdTimeToLiveInSeconds = other.authorizationGrantIdTimeToLiveInSeconds;
    this.changePasswordIdGenerator = other.changePasswordIdGenerator;
    this.changePasswordIdTimeToLiveInSeconds = other.changePasswordIdTimeToLiveInSeconds;
    this.emailVerificationIdGenerator = other.emailVerificationIdGenerator;
    this.emailVerificationIdTimeToLiveInSeconds = other.emailVerificationIdTimeToLiveInSeconds;
    this.oneTimePasswordTimeToLiveInSeconds = other.oneTimePasswordTimeToLiveInSeconds;
    this.passwordlessLoginTimeToLiveInSeconds = other.passwordlessLoginTimeToLiveInSeconds;
    this.passwordlessLoginGenerator = other.passwordlessLoginGenerator;
    this.registrationVerificationIdGenerator = other.registrationVerificationIdGenerator;
    this.registrationVerificationIdTimeToLiveInSeconds = other.registrationVerificationIdTimeToLiveInSeconds;
    this.setupPasswordIdGenerator = other.setupPasswordIdGenerator;
    this.setupPasswordIdTimeToLiveInSeconds = other.setupPasswordIdTimeToLiveInSeconds;
    this.twoFactorIdTimeToLiveInSeconds = other.twoFactorIdTimeToLiveInSeconds;
    this.twoFactorTrustIdTimeToLiveInSeconds = other.twoFactorTrustIdTimeToLiveInSeconds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ExternalIdentifierConfiguration)) {
      return false;
    }
    ExternalIdentifierConfiguration that = (ExternalIdentifierConfiguration) o;
    return authorizationGrantIdTimeToLiveInSeconds == that.authorizationGrantIdTimeToLiveInSeconds &&
        changePasswordIdTimeToLiveInSeconds == that.changePasswordIdTimeToLiveInSeconds &&
        emailVerificationIdTimeToLiveInSeconds == that.emailVerificationIdTimeToLiveInSeconds &&
        oneTimePasswordTimeToLiveInSeconds == that.oneTimePasswordTimeToLiveInSeconds &&
        passwordlessLoginTimeToLiveInSeconds == that.passwordlessLoginTimeToLiveInSeconds &&
        registrationVerificationIdTimeToLiveInSeconds == that.registrationVerificationIdTimeToLiveInSeconds &&
        setupPasswordIdTimeToLiveInSeconds == that.setupPasswordIdTimeToLiveInSeconds &&
        twoFactorIdTimeToLiveInSeconds == that.twoFactorIdTimeToLiveInSeconds &&
        twoFactorTrustIdTimeToLiveInSeconds == that.twoFactorTrustIdTimeToLiveInSeconds &&
        Objects.equals(changePasswordIdGenerator, that.changePasswordIdGenerator) &&
        Objects.equals(emailVerificationIdGenerator, that.emailVerificationIdGenerator) &&
        Objects.equals(passwordlessLoginGenerator, that.passwordlessLoginGenerator) &&
        Objects.equals(registrationVerificationIdGenerator, that.registrationVerificationIdGenerator) &&
        Objects.equals(setupPasswordIdGenerator, that.setupPasswordIdGenerator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorizationGrantIdTimeToLiveInSeconds, changePasswordIdGenerator, changePasswordIdTimeToLiveInSeconds, emailVerificationIdGenerator, emailVerificationIdTimeToLiveInSeconds, oneTimePasswordTimeToLiveInSeconds, passwordlessLoginTimeToLiveInSeconds, passwordlessLoginGenerator, registrationVerificationIdGenerator, registrationVerificationIdTimeToLiveInSeconds, setupPasswordIdGenerator, setupPasswordIdTimeToLiveInSeconds, twoFactorIdTimeToLiveInSeconds, twoFactorTrustIdTimeToLiveInSeconds);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}