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

  public UUID messengerId;

  public UUID passwordlessTemplateId;

  public PhoneUnverifiedOptions unverified = new PhoneUnverifiedOptions();

  public UUID verificationCompleteTemplateId;

  public VerificationStrategy verificationStrategy = VerificationStrategy.ClickableLink;

  public UUID verificationTemplateId;

  public boolean verifyPhoneNumber;

  @JacksonConstructor
  public TenantPhoneConfiguration() {
  }

  public TenantPhoneConfiguration(TenantPhoneConfiguration other) {
    messengerId = other.messengerId;
    forgotPasswordTemplateId = other.forgotPasswordTemplateId;
    passwordlessTemplateId = other.passwordlessTemplateId;
    unverified = new PhoneUnverifiedOptions(other.unverified);
    verificationCompleteTemplateId = other.verificationCompleteTemplateId;
    verificationStrategy = other.verificationStrategy;
    verificationTemplateId = other.verificationTemplateId;
    verifyPhoneNumber = other.verifyPhoneNumber;
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
    return Objects.equals(messengerId, that.messengerId) &&
           Objects.equals(forgotPasswordTemplateId, that.forgotPasswordTemplateId) &&
           Objects.equals(passwordlessTemplateId, that.passwordlessTemplateId) &&
           Objects.equals(unverified, that.unverified) &&
           Objects.equals(verificationCompleteTemplateId, that.verificationCompleteTemplateId) &&
           verificationStrategy == that.verificationStrategy &&
           Objects.equals(verificationTemplateId, that.verificationTemplateId) &&
           verifyPhoneNumber == that.verifyPhoneNumber;
  }

  @Override
  public int hashCode() {
    return Objects.hash(messengerId, forgotPasswordTemplateId, unverified, verificationCompleteTemplateId, verificationStrategy, verificationTemplateId, verifyPhoneNumber);
  }
}
