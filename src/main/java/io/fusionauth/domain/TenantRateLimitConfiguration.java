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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class TenantRateLimitConfiguration implements Buildable<TenantRateLimitConfiguration> {
  public RateLimitedRequestConfiguration failedLogin = new RateLimitedRequestConfiguration(5, 60);

  public RateLimitedRequestConfiguration forgotPassword = new RateLimitedRequestConfiguration(5, 60);

  public RateLimitedRequestConfiguration sendEmailVerification = new RateLimitedRequestConfiguration(10, 60);

  public RateLimitedRequestConfiguration sendPasswordless = new RateLimitedRequestConfiguration(10, 60);

  public RateLimitedRequestConfiguration sendRegistrationVerification = new RateLimitedRequestConfiguration(10, 60);

  public RateLimitedRequestConfiguration sendTwoFactor = new RateLimitedRequestConfiguration(10, 60);

  @JacksonConstructor
  public TenantRateLimitConfiguration() {
  }

  public TenantRateLimitConfiguration(TenantRateLimitConfiguration other) {
    this.failedLogin = new RateLimitedRequestConfiguration(other.failedLogin);
    this.forgotPassword = new RateLimitedRequestConfiguration(other.forgotPassword);
    this.sendEmailVerification = new RateLimitedRequestConfiguration(other.sendEmailVerification);
    this.sendPasswordless = new RateLimitedRequestConfiguration(other.sendPasswordless);
    this.sendRegistrationVerification = new RateLimitedRequestConfiguration(other.sendRegistrationVerification);
    this.sendTwoFactor = new RateLimitedRequestConfiguration(other.sendTwoFactor);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TenantRateLimitConfiguration that = (TenantRateLimitConfiguration) o;
    return Objects.equals(failedLogin, that.failedLogin) &&
           Objects.equals(forgotPassword, that.forgotPassword) &&
           Objects.equals(sendEmailVerification, that.sendEmailVerification) &&
           Objects.equals(sendPasswordless, that.sendPasswordless) &&
           Objects.equals(sendRegistrationVerification, that.sendRegistrationVerification) &&
           Objects.equals(sendTwoFactor, that.sendTwoFactor);
  }

  @JsonIgnore
  public RateLimitedRequestConfiguration getConfiguration(RateLimitedRequestType type) {
    switch (type) {
      case FailedLogin:
        return failedLogin;
      case ForgotPassword:
        return forgotPassword;
      case SendEmailVerification:
        return sendEmailVerification;
      case SendPasswordless:
        return sendPasswordless;
      case SendRegistrationVerification:
        return sendRegistrationVerification;
      case SendTwoFactor:
        return sendTwoFactor;
      default:
        throw new IllegalArgumentException("Unexpected request type [" + type + "].");
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(failedLogin, forgotPassword, sendEmailVerification, sendPasswordless, sendRegistrationVerification, sendTwoFactor);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
