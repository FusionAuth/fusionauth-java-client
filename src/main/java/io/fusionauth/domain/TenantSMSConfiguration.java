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
package io.fusionauth.domain;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;

/**
 * Hold SMS configuration for passwordless and verification cases.
 *
 * @author Brady Wied
 */
public class TenantSMSConfiguration implements Buildable<TenantSMSConfiguration> {
  public UUID messengerId; // TODO : ENG-1 : Brent - validate MessengerType not kafka.  write test of deleting messenger

  public UUID passwordlessTemplateId;

  public VerificationStrategy verificationStrategy;

  public UUID verificationTemplateId;

  public boolean verifyPhoneNumber;

  @JacksonConstructor
  public TenantSMSConfiguration() {
  }

  public TenantSMSConfiguration(TenantSMSConfiguration other) {
    messengerId = other.messengerId;
    passwordlessTemplateId = other.passwordlessTemplateId;
    verifyPhoneNumber = other.verifyPhoneNumber;
    verificationTemplateId = other.verificationTemplateId;
    verificationStrategy = other.verificationStrategy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TenantSMSConfiguration)) {
      return false;
    }
    TenantSMSConfiguration that = (TenantSMSConfiguration) o;
    return verifyPhoneNumber == that.verifyPhoneNumber &&
      Objects.equals(messengerId, that.messengerId) &&
      Objects.equals(passwordlessTemplateId, that.passwordlessTemplateId) &&
      verificationStrategy == that.verificationStrategy &&
      Objects.equals(verificationTemplateId, that.verificationTemplateId) ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(messengerId, passwordlessTemplateId, verificationStrategy, verificationTemplateId, verifyPhoneNumber);
  }
}
