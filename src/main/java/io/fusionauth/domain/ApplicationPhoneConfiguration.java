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
 * Hold application phone configuration for template overrides.
 */
public class ApplicationPhoneConfiguration implements Buildable<ApplicationPhoneConfiguration> {

  public UUID forgotPasswordTemplateId;

  public UUID identityUpdateTemplateId;

  public UUID loginIdInUseOnCreateTemplateId;

  public UUID loginIdInUseOnUpdateTemplateId;

  public UUID loginNewDeviceTemplateId;

  public UUID loginSuspiciousTemplateId;

  public UUID passwordResetSuccessTemplateId;

  public UUID passwordUpdateTemplateId;

  public UUID passwordlessTemplateId;

  public UUID setPasswordTemplateId;

  public UUID twoFactorMethodAddTemplateId;

  public UUID twoFactorMethodRemoveTemplateId;

  public UUID verificationCompleteTemplateId;

  public UUID verificationTemplateId;

  @JacksonConstructor
  public ApplicationPhoneConfiguration() {
  }

  public ApplicationPhoneConfiguration(ApplicationPhoneConfiguration other) {
    this.forgotPasswordTemplateId = other.forgotPasswordTemplateId;
    this.identityUpdateTemplateId = other.identityUpdateTemplateId;
    this.loginIdInUseOnCreateTemplateId = other.loginIdInUseOnCreateTemplateId;
    this.loginIdInUseOnUpdateTemplateId = other.loginIdInUseOnUpdateTemplateId;
    this.loginNewDeviceTemplateId = other.loginNewDeviceTemplateId;
    this.loginSuspiciousTemplateId = other.loginSuspiciousTemplateId;
    this.passwordResetSuccessTemplateId = other.passwordResetSuccessTemplateId;
    this.passwordUpdateTemplateId = other.passwordUpdateTemplateId;
    this.passwordlessTemplateId = other.passwordlessTemplateId;
    this.setPasswordTemplateId = other.setPasswordTemplateId;
    this.twoFactorMethodAddTemplateId = other.twoFactorMethodAddTemplateId;
    this.twoFactorMethodRemoveTemplateId = other.twoFactorMethodRemoveTemplateId;
    this.verificationCompleteTemplateId = other.verificationCompleteTemplateId;
    this.verificationTemplateId = other.verificationTemplateId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ApplicationPhoneConfiguration)) {
      return false;
    }
    ApplicationPhoneConfiguration that = (ApplicationPhoneConfiguration) o;
    return Objects.equals(forgotPasswordTemplateId, that.forgotPasswordTemplateId) &&
           Objects.equals(identityUpdateTemplateId, that.identityUpdateTemplateId) &&
           Objects.equals(loginIdInUseOnCreateTemplateId, that.loginIdInUseOnCreateTemplateId) &&
           Objects.equals(loginIdInUseOnUpdateTemplateId, that.loginIdInUseOnUpdateTemplateId) &&
           Objects.equals(loginNewDeviceTemplateId, that.loginNewDeviceTemplateId) &&
           Objects.equals(loginSuspiciousTemplateId, that.loginSuspiciousTemplateId) &&
           Objects.equals(passwordResetSuccessTemplateId, that.passwordResetSuccessTemplateId) &&
           Objects.equals(passwordUpdateTemplateId, that.passwordUpdateTemplateId) &&
           Objects.equals(passwordlessTemplateId, that.passwordlessTemplateId) &&
           Objects.equals(setPasswordTemplateId, that.setPasswordTemplateId) &&
           Objects.equals(twoFactorMethodAddTemplateId, that.twoFactorMethodAddTemplateId) &&
           Objects.equals(twoFactorMethodRemoveTemplateId, that.twoFactorMethodRemoveTemplateId) &&
           Objects.equals(verificationCompleteTemplateId, that.verificationCompleteTemplateId) &&
           Objects.equals(verificationTemplateId, that.verificationTemplateId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(forgotPasswordTemplateId, identityUpdateTemplateId, loginIdInUseOnCreateTemplateId, loginIdInUseOnUpdateTemplateId, loginNewDeviceTemplateId, loginSuspiciousTemplateId, passwordResetSuccessTemplateId, passwordUpdateTemplateId, passwordlessTemplateId, setPasswordTemplateId, twoFactorMethodAddTemplateId, twoFactorMethodRemoveTemplateId, verificationCompleteTemplateId, verificationTemplateId);
  }
}
