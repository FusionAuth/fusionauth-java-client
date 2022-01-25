/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.AuthenticatorConfiguration.TOTPAlgorithm;

/**
 * @author Mikey Sleevi
 */
public class TenantMultiFactorConfiguration implements Buildable<TenantMultiFactorConfiguration> {
  public MultiFactorAuthenticatorMethod authenticator = new MultiFactorAuthenticatorMethod().with(mfa -> mfa.enabled = true);

  public MultiFactorEmailMethod email = new MultiFactorEmailMethod();

  public MultiFactorSMSMethod sms = new MultiFactorSMSMethod();

  @JacksonConstructor
  public TenantMultiFactorConfiguration() {
  }

  public TenantMultiFactorConfiguration(TenantMultiFactorConfiguration other) {
    this.authenticator = new MultiFactorAuthenticatorMethod(other.authenticator);
    this.email = new MultiFactorEmailMethod(other.email);
    this.sms = new MultiFactorSMSMethod(other.sms);
  }

  /**
   * Returns true if at least one Multi-Factor method is enabled.
   *
   * @return true if at least one Multi-Factor method is enabled
   */
  @JsonIgnore
  public boolean anyEnabled() {
    return authenticator.enabled || sms.enabled || email.enabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TenantMultiFactorConfiguration that = (TenantMultiFactorConfiguration) o;
    return Objects.equals(email, that.email) && Objects.equals(sms, that.sms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, sms);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class MultiFactorAuthenticatorMethod extends Enableable implements Buildable<MultiFactorAuthenticatorMethod> {
    public TOTPAlgorithm algorithm = TOTPAlgorithm.HmacSHA1;

    public int codeLength = 6;

    public int timeStep = 30;

    @JacksonConstructor
    public MultiFactorAuthenticatorMethod() {
    }

    public MultiFactorAuthenticatorMethod(MultiFactorAuthenticatorMethod other) {
      this.algorithm = other.algorithm;
      this.codeLength = other.codeLength;
      this.enabled = other.enabled;
      this.timeStep = other.timeStep;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }
      MultiFactorAuthenticatorMethod that = (MultiFactorAuthenticatorMethod) o;
      return codeLength == that.codeLength && timeStep == that.timeStep && algorithm == that.algorithm;
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), algorithm, codeLength, timeStep);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class MultiFactorEmailMethod extends Enableable implements Buildable<MultiFactorEmailMethod> {
    public UUID templateId;

    @JacksonConstructor
    public MultiFactorEmailMethod() {
    }

    public MultiFactorEmailMethod(MultiFactorEmailMethod other) {
      this.enabled = other.enabled;
      this.templateId = other.templateId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }
      MultiFactorEmailMethod that = (MultiFactorEmailMethod) o;
      return Objects.equals(templateId, that.templateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), templateId);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class MultiFactorSMSMethod extends Enableable implements Buildable<MultiFactorSMSMethod> {
    public UUID messengerId;

    public UUID templateId;

    @JacksonConstructor
    public MultiFactorSMSMethod() {
    }

    public MultiFactorSMSMethod(MultiFactorSMSMethod other) {
      this.enabled = other.enabled;
      this.messengerId = other.messengerId;
      this.templateId = other.templateId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }
      MultiFactorSMSMethod that = (MultiFactorSMSMethod) o;
      return Objects.equals(messengerId, that.messengerId) && Objects.equals(templateId, that.templateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), messengerId, templateId);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
