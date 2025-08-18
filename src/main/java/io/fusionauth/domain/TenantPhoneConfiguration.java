/*
 * Copyright (c) 2024-2025, FusionAuth, All Rights Reserved
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
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;

/**
 * Hold tenant phone configuration for passwordless and verification cases.
 *
 * @author Brady Wied
 */
public class TenantPhoneConfiguration implements Buildable<TenantPhoneConfiguration> {

  public UUID forgotPasswordTemplateId;

  public UUID identityUpdateTemplateId;

  public boolean implicitPhoneVerificationAllowed = true;

  public UUID loginIdInUseOnCreateTemplateId;

  public UUID loginIdInUseOnUpdateTemplateId;

  public UUID loginNewDeviceTemplateId;

  public UUID loginSuspiciousTemplateId;

  public UUID messengerId;

  public UUID passwordResetSuccessTemplateId;

  public UUID passwordUpdateTemplateId;

  public UUID passwordlessTemplateId;

  public UUID setPasswordTemplateId;

  public UUID twoFactorMethodAddTemplateId;

  public UUID twoFactorMethodRemoveTemplateId;

  public PhoneUnverifiedOptions unverified = new PhoneUnverifiedOptions();

  public UUID verificationCompleteTemplateId;

  public VerificationStrategy verificationStrategy = VerificationStrategy.ClickableLink;

  public UUID verificationTemplateId;

  public boolean verifyPhoneNumber;

  @JacksonConstructor
  public TenantPhoneConfiguration() {
  }

  public TenantPhoneConfiguration(TenantPhoneConfiguration other) {
    this.forgotPasswordTemplateId = other.forgotPasswordTemplateId;
    this.identityUpdateTemplateId = other.identityUpdateTemplateId;
    this.implicitPhoneVerificationAllowed = other.implicitPhoneVerificationAllowed;
    this.loginIdInUseOnCreateTemplateId = other.loginIdInUseOnCreateTemplateId;
    this.loginIdInUseOnUpdateTemplateId = other.loginIdInUseOnUpdateTemplateId;
    this.loginNewDeviceTemplateId = other.loginNewDeviceTemplateId;
    this.loginSuspiciousTemplateId = other.loginSuspiciousTemplateId;
    this.messengerId = other.messengerId;
    this.passwordResetSuccessTemplateId = other.passwordResetSuccessTemplateId;
    this.passwordUpdateTemplateId = other.passwordUpdateTemplateId;
    this.passwordlessTemplateId = other.passwordlessTemplateId;
    this.setPasswordTemplateId = other.setPasswordTemplateId;
    this.twoFactorMethodAddTemplateId = other.twoFactorMethodAddTemplateId;
    this.twoFactorMethodRemoveTemplateId = other.twoFactorMethodRemoveTemplateId;
    this.unverified = new PhoneUnverifiedOptions(other.unverified);
    this.verificationCompleteTemplateId = other.verificationCompleteTemplateId;
    this.verificationStrategy = other.verificationStrategy;
    this.verificationTemplateId = other.verificationTemplateId;
    this.verifyPhoneNumber = other.verifyPhoneNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TenantPhoneConfiguration)) {
      return false;
    }
    TenantPhoneConfiguration that = (TenantPhoneConfiguration) o;
    return implicitPhoneVerificationAllowed == that.implicitPhoneVerificationAllowed &&
           verifyPhoneNumber == that.verifyPhoneNumber &&
           Objects.equals(forgotPasswordTemplateId, that.forgotPasswordTemplateId) &&
           Objects.equals(identityUpdateTemplateId, that.identityUpdateTemplateId) &&
           Objects.equals(loginIdInUseOnCreateTemplateId, that.loginIdInUseOnCreateTemplateId) &&
           Objects.equals(loginIdInUseOnUpdateTemplateId, that.loginIdInUseOnUpdateTemplateId) &&
           Objects.equals(loginNewDeviceTemplateId, that.loginNewDeviceTemplateId) &&
           Objects.equals(loginSuspiciousTemplateId, that.loginSuspiciousTemplateId) &&
           Objects.equals(messengerId, that.messengerId) &&
           Objects.equals(passwordResetSuccessTemplateId, that.passwordResetSuccessTemplateId) &&
           Objects.equals(passwordUpdateTemplateId, that.passwordUpdateTemplateId) &&
           Objects.equals(passwordlessTemplateId, that.passwordlessTemplateId) &&
           Objects.equals(setPasswordTemplateId, that.setPasswordTemplateId) &&
           Objects.equals(twoFactorMethodAddTemplateId, that.twoFactorMethodAddTemplateId) &&
           Objects.equals(twoFactorMethodRemoveTemplateId, that.twoFactorMethodRemoveTemplateId) &&
           Objects.equals(unverified, that.unverified) &&
           Objects.equals(verificationCompleteTemplateId, that.verificationCompleteTemplateId) &&
           verificationStrategy == that.verificationStrategy &&
           Objects.equals(verificationTemplateId, that.verificationTemplateId) &&
           verifyPhoneNumber == that.verifyPhoneNumber;
  }

  @Override
  public int hashCode() {
    return Objects.hash(forgotPasswordTemplateId, identityUpdateTemplateId, implicitPhoneVerificationAllowed, loginIdInUseOnCreateTemplateId, loginIdInUseOnUpdateTemplateId, loginNewDeviceTemplateId, loginSuspiciousTemplateId, messengerId, passwordResetSuccessTemplateId, passwordUpdateTemplateId, passwordlessTemplateId, setPasswordTemplateId, twoFactorMethodAddTemplateId, twoFactorMethodRemoveTemplateId, unverified, verificationCompleteTemplateId, verificationStrategy, verificationTemplateId, verifyPhoneNumber);
  }
}
