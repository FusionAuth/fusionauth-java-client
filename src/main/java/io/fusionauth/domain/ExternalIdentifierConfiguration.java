/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class ExternalIdentifierConfiguration implements Buildable<ExternalIdentifierConfiguration> {
  public int authorizationGrantIdTimeToLiveInSeconds = 30;

  public SecureGeneratorConfiguration changePasswordIdGenerator = new SecureGeneratorConfiguration(32, SecureGeneratorType.randomBytes);

  public int changePasswordIdTimeToLiveInSeconds = 600;

  public int deviceCodeTimeToLiveInSeconds = 300;

  public SecureGeneratorConfiguration deviceUserCodeIdGenerator = new SecureGeneratorConfiguration(6, SecureGeneratorType.randomAlphaNumeric);

  public SecureGeneratorConfiguration emailVerificationIdGenerator = new SecureGeneratorConfiguration(32, SecureGeneratorType.randomBytes);

  public int emailVerificationIdTimeToLiveInSeconds = 86400;

  public SecureGeneratorConfiguration emailVerificationOneTimeCodeGenerator = new SecureGeneratorConfiguration(6, SecureGeneratorType.randomAlphaNumeric);

  public int externalAuthenticationIdTimeToLiveInSeconds = 300;

  public int oneTimePasswordTimeToLiveInSeconds = 60;

  public SecureGeneratorConfiguration passwordlessLoginGenerator = new SecureGeneratorConfiguration(32, SecureGeneratorType.randomBytes);

  public int passwordlessLoginTimeToLiveInSeconds = 180;

  public int pendingAccountLinkTimeToLiveInSeconds = 60 * 60;

  public SecureGeneratorConfiguration registrationVerificationIdGenerator = new SecureGeneratorConfiguration(32, SecureGeneratorType.randomBytes);

  public int registrationVerificationIdTimeToLiveInSeconds = 86400;

  public SecureGeneratorConfiguration registrationVerificationOneTimeCodeGenerator = new SecureGeneratorConfiguration(6, SecureGeneratorType.randomAlphaNumeric);

  public int samlv2AuthNRequestIdTimeToLiveInSeconds = 300;

  public SecureGeneratorConfiguration setupPasswordIdGenerator = new SecureGeneratorConfiguration(32, SecureGeneratorType.randomBytes);

  public int setupPasswordIdTimeToLiveInSeconds = 86400;

  public int trustTokenTimeToLiveInSeconds = 180;

  public int twoFactorIdTimeToLiveInSeconds = 300;

  public SecureGeneratorConfiguration twoFactorOneTimeCodeIdGenerator = new SecureGeneratorConfiguration(6, SecureGeneratorType.randomDigits);

  public int twoFactorOneTimeCodeIdTimeToLiveInSeconds = 60;

  public int twoFactorTrustIdTimeToLiveInSeconds = 2592000;

  public int webAuthnAuthenticationChallengeTimeToLiveInSeconds = 180;

  public int webAuthnRegistrationChallengeTimeToLiveInSeconds = 180;

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
    this.pendingAccountLinkTimeToLiveInSeconds = other.pendingAccountLinkTimeToLiveInSeconds;
    this.registrationVerificationIdGenerator = new SecureGeneratorConfiguration(other.registrationVerificationIdGenerator);
    this.registrationVerificationIdTimeToLiveInSeconds = other.registrationVerificationIdTimeToLiveInSeconds;
    this.registrationVerificationOneTimeCodeGenerator = new SecureGeneratorConfiguration(other.registrationVerificationOneTimeCodeGenerator);
    this.samlv2AuthNRequestIdTimeToLiveInSeconds = other.samlv2AuthNRequestIdTimeToLiveInSeconds;
    this.setupPasswordIdGenerator = new SecureGeneratorConfiguration(other.setupPasswordIdGenerator);
    this.setupPasswordIdTimeToLiveInSeconds = other.setupPasswordIdTimeToLiveInSeconds;
    this.trustTokenTimeToLiveInSeconds = other.trustTokenTimeToLiveInSeconds;
    this.twoFactorIdTimeToLiveInSeconds = other.twoFactorIdTimeToLiveInSeconds;
    this.twoFactorOneTimeCodeIdGenerator = new SecureGeneratorConfiguration(other.twoFactorOneTimeCodeIdGenerator);
    this.twoFactorOneTimeCodeIdTimeToLiveInSeconds = other.twoFactorOneTimeCodeIdTimeToLiveInSeconds;
    this.twoFactorTrustIdTimeToLiveInSeconds = other.twoFactorTrustIdTimeToLiveInSeconds;
    this.webAuthnAuthenticationChallengeTimeToLiveInSeconds = other.webAuthnAuthenticationChallengeTimeToLiveInSeconds;
    this.webAuthnRegistrationChallengeTimeToLiveInSeconds = other.webAuthnRegistrationChallengeTimeToLiveInSeconds;
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
           pendingAccountLinkTimeToLiveInSeconds == that.pendingAccountLinkTimeToLiveInSeconds &&
           registrationVerificationIdTimeToLiveInSeconds == that.registrationVerificationIdTimeToLiveInSeconds &&
           samlv2AuthNRequestIdTimeToLiveInSeconds == that.samlv2AuthNRequestIdTimeToLiveInSeconds &&
           setupPasswordIdTimeToLiveInSeconds == that.setupPasswordIdTimeToLiveInSeconds &&
           trustTokenTimeToLiveInSeconds == that.trustTokenTimeToLiveInSeconds &&
           twoFactorIdTimeToLiveInSeconds == that.twoFactorIdTimeToLiveInSeconds &&
           twoFactorOneTimeCodeIdTimeToLiveInSeconds == that.twoFactorOneTimeCodeIdTimeToLiveInSeconds &&
           twoFactorTrustIdTimeToLiveInSeconds == that.twoFactorTrustIdTimeToLiveInSeconds &&
           webAuthnAuthenticationChallengeTimeToLiveInSeconds == that.webAuthnAuthenticationChallengeTimeToLiveInSeconds &&
           webAuthnRegistrationChallengeTimeToLiveInSeconds == that.webAuthnRegistrationChallengeTimeToLiveInSeconds &&
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
                        pendingAccountLinkTimeToLiveInSeconds,
                        registrationVerificationIdGenerator,
                        registrationVerificationIdTimeToLiveInSeconds,
                        registrationVerificationOneTimeCodeGenerator,
                        samlv2AuthNRequestIdTimeToLiveInSeconds,
                        setupPasswordIdGenerator,
                        setupPasswordIdTimeToLiveInSeconds,
                        trustTokenTimeToLiveInSeconds,
                        twoFactorIdTimeToLiveInSeconds,
                        twoFactorOneTimeCodeIdGenerator,
                        twoFactorOneTimeCodeIdTimeToLiveInSeconds,
                        twoFactorTrustIdTimeToLiveInSeconds,
                        webAuthnAuthenticationChallengeTimeToLiveInSeconds,
                        webAuthnRegistrationChallengeTimeToLiveInSeconds);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}