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

  public int deviceCodeTimeToLiveInSeconds;

  public SecureGeneratorConfiguration deviceUserCodeIdGenerator;

  public SecureGeneratorConfiguration emailVerificationIdGenerator;

  public int emailVerificationIdTimeToLiveInSeconds;

  public SecureGeneratorConfiguration emailVerificationOneTimeCodeGenerator;

  public int externalAuthenticationIdTimeToLiveInSeconds;

  public int oneTimePasswordTimeToLiveInSeconds;

  public SecureGeneratorConfiguration passwordlessLoginGenerator;

  public int passwordlessLoginTimeToLiveInSeconds;

  public SecureGeneratorConfiguration registrationVerificationIdGenerator;

  public int registrationVerificationIdTimeToLiveInSeconds;

  public SecureGeneratorConfiguration registrationVerificationOneTimeCodeGenerator;

  public int samlv2AuthNRequestIdTimeToLiveInSeconds;

  public SecureGeneratorConfiguration setupPasswordIdGenerator;

  public int setupPasswordIdTimeToLiveInSeconds;

  public int twoFactorIdTimeToLiveInSeconds;

  public SecureGeneratorConfiguration twoFactorOneTimeCodeIdGenerator;

  public int twoFactorOneTimeCodeIdTimeToLiveInSeconds;

  public int twoFactorTrustIdTimeToLiveInSeconds;

  @JacksonConstructor
  public ExternalIdentifierConfiguration() {
  }

  public ExternalIdentifierConfiguration(ExternalIdentifierConfiguration other) {
    this.authorizationGrantIdTimeToLiveInSeconds = other.authorizationGrantIdTimeToLiveInSeconds;
    this.changePasswordIdGenerator = new SecureGeneratorConfiguration(other.changePasswordIdGenerator);
    this.changePasswordIdTimeToLiveInSeconds = other.changePasswordIdTimeToLiveInSeconds;
    this.deviceCodeTimeToLiveInSeconds = other.deviceCodeTimeToLiveInSeconds;
    this.deviceUserCodeIdGenerator = new SecureGeneratorConfiguration(other.deviceUserCodeIdGenerator);
    this.emailVerificationIdGenerator = new SecureGeneratorConfiguration(other.emailVerificationIdGenerator);
    this.emailVerificationIdTimeToLiveInSeconds = other.emailVerificationIdTimeToLiveInSeconds;
    this.emailVerificationOneTimeCodeGenerator = new SecureGeneratorConfiguration(other.emailVerificationOneTimeCodeGenerator);
    this.externalAuthenticationIdTimeToLiveInSeconds = other.externalAuthenticationIdTimeToLiveInSeconds;
    this.oneTimePasswordTimeToLiveInSeconds = other.oneTimePasswordTimeToLiveInSeconds;
    this.passwordlessLoginTimeToLiveInSeconds = other.passwordlessLoginTimeToLiveInSeconds;
    this.passwordlessLoginGenerator = new SecureGeneratorConfiguration(other.passwordlessLoginGenerator);
    this.registrationVerificationIdGenerator = new SecureGeneratorConfiguration(other.registrationVerificationIdGenerator);
    this.registrationVerificationIdTimeToLiveInSeconds = other.registrationVerificationIdTimeToLiveInSeconds;
    this.registrationVerificationOneTimeCodeGenerator = new SecureGeneratorConfiguration(other.registrationVerificationOneTimeCodeGenerator);
    this.samlv2AuthNRequestIdTimeToLiveInSeconds = other.samlv2AuthNRequestIdTimeToLiveInSeconds;
    this.setupPasswordIdGenerator = new SecureGeneratorConfiguration(other.setupPasswordIdGenerator);
    this.setupPasswordIdTimeToLiveInSeconds = other.setupPasswordIdTimeToLiveInSeconds;
    this.twoFactorIdTimeToLiveInSeconds = other.twoFactorIdTimeToLiveInSeconds;
    this.twoFactorOneTimeCodeIdGenerator = new SecureGeneratorConfiguration(other.twoFactorOneTimeCodeIdGenerator);
    this.twoFactorOneTimeCodeIdTimeToLiveInSeconds = other.twoFactorOneTimeCodeIdTimeToLiveInSeconds;
    this.twoFactorTrustIdTimeToLiveInSeconds = other.twoFactorTrustIdTimeToLiveInSeconds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExternalIdentifierConfiguration that = (ExternalIdentifierConfiguration) o;
    return authorizationGrantIdTimeToLiveInSeconds == that.authorizationGrantIdTimeToLiveInSeconds &&
           changePasswordIdTimeToLiveInSeconds == that.changePasswordIdTimeToLiveInSeconds &&
           deviceCodeTimeToLiveInSeconds == that.deviceCodeTimeToLiveInSeconds &&
           emailVerificationIdTimeToLiveInSeconds == that.emailVerificationIdTimeToLiveInSeconds &&
           externalAuthenticationIdTimeToLiveInSeconds == that.externalAuthenticationIdTimeToLiveInSeconds &&
           oneTimePasswordTimeToLiveInSeconds == that.oneTimePasswordTimeToLiveInSeconds &&
           passwordlessLoginTimeToLiveInSeconds == that.passwordlessLoginTimeToLiveInSeconds &&
           registrationVerificationIdTimeToLiveInSeconds == that.registrationVerificationIdTimeToLiveInSeconds &&
           samlv2AuthNRequestIdTimeToLiveInSeconds == that.samlv2AuthNRequestIdTimeToLiveInSeconds &&
           setupPasswordIdTimeToLiveInSeconds == that.setupPasswordIdTimeToLiveInSeconds &&
           twoFactorIdTimeToLiveInSeconds == that.twoFactorIdTimeToLiveInSeconds &&
           twoFactorOneTimeCodeIdTimeToLiveInSeconds == that.twoFactorOneTimeCodeIdTimeToLiveInSeconds &&
           twoFactorTrustIdTimeToLiveInSeconds == that.twoFactorTrustIdTimeToLiveInSeconds &&
           Objects.equals(changePasswordIdGenerator, that.changePasswordIdGenerator) &&
           Objects.equals(deviceUserCodeIdGenerator, that.deviceUserCodeIdGenerator) &&
           Objects.equals(emailVerificationIdGenerator, that.emailVerificationIdGenerator) &&
           Objects.equals(emailVerificationOneTimeCodeGenerator, that.emailVerificationOneTimeCodeGenerator) &&
           Objects.equals(passwordlessLoginGenerator, that.passwordlessLoginGenerator) &&
           Objects.equals(registrationVerificationIdGenerator, that.registrationVerificationIdGenerator) &&
           Objects.equals(registrationVerificationOneTimeCodeGenerator, that.registrationVerificationOneTimeCodeGenerator) &&
           Objects.equals(setupPasswordIdGenerator, that.setupPasswordIdGenerator) &&
           Objects.equals(twoFactorOneTimeCodeIdGenerator, that.twoFactorOneTimeCodeIdGenerator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorizationGrantIdTimeToLiveInSeconds,
                        changePasswordIdGenerator,
                        changePasswordIdTimeToLiveInSeconds,
                        deviceCodeTimeToLiveInSeconds,
                        deviceUserCodeIdGenerator,
                        emailVerificationIdGenerator,
                        emailVerificationIdTimeToLiveInSeconds,
                        emailVerificationOneTimeCodeGenerator,
                        externalAuthenticationIdTimeToLiveInSeconds,
                        oneTimePasswordTimeToLiveInSeconds,
                        passwordlessLoginGenerator,
                        passwordlessLoginTimeToLiveInSeconds,
                        registrationVerificationIdGenerator,
                        registrationVerificationIdTimeToLiveInSeconds,
                        registrationVerificationOneTimeCodeGenerator,
                        samlv2AuthNRequestIdTimeToLiveInSeconds,
                        setupPasswordIdGenerator,
                        setupPasswordIdTimeToLiveInSeconds,
                        twoFactorIdTimeToLiveInSeconds,
                        twoFactorOneTimeCodeIdGenerator,
                        twoFactorOneTimeCodeIdTimeToLiveInSeconds,
                        twoFactorTrustIdTimeToLiveInSeconds);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}