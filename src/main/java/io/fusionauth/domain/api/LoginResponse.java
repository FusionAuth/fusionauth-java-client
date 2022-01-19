/*
 * Copyright (c) 2018-2020, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.AuthenticationThreats;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.ChangePasswordReason;
import io.fusionauth.domain.TwoFactorMethod;
import io.fusionauth.domain.User;

/**
 * @author Brian Pontarelli
 */
public class LoginResponse implements Buildable<LoginResponse> {
  public List<LoginPreventedResponse> actions;

  public String changePasswordId;

  public ChangePasswordReason changePasswordReason;

  public String emailVerificationId;

  public List<TwoFactorMethod> methods;

  public String pendingIdPLinkId;

  public String refreshToken;

  public String registrationVerificationId;

  public Map<String, Object> state;

  public Set<AuthenticationThreats> threatsDetected;

  public String token;

  public ZonedDateTime tokenExpirationInstant;

  public String twoFactorId;

  public String twoFactorTrustId;

  public User user;

  @JacksonConstructor
  public LoginResponse() {
  }

  public LoginResponse(User user, String token) {
    this.user = user;
    this.token = token;
  }

  public LoginResponse(User user) {
    this.user = user;
  }

  public LoginResponse(List<LoginPreventedResponse> actions) {
    this.actions = actions;
  }
}
