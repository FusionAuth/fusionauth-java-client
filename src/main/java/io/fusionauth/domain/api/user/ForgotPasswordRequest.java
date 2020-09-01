/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.user;

import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;

/**
 * Forgot password request object.
 *
 * @author Brian Pontarelli
 */
public class ForgotPasswordRequest {
  public UUID applicationId;

  public String changePasswordId;

  public String loginId;

  public boolean sendForgotPasswordEmail = true;

  public Map<String, Object> state;

  @JacksonConstructor
  public ForgotPasswordRequest() {
  }

  public ForgotPasswordRequest(String loginId, Map<String, Object> state) {
    this.loginId = loginId;
    this.state = state;
  }

  public ForgotPasswordRequest(String loginId) {
    this.loginId = loginId;
  }

  public ForgotPasswordRequest(UUID applicationId, String loginId) {
    this.applicationId = applicationId;
    this.loginId = loginId;
  }

  public ForgotPasswordRequest(String loginId, boolean sendForgotPasswordEmail) {
    this.loginId = loginId;
    this.sendForgotPasswordEmail = sendForgotPasswordEmail;
  }

  public String getEmail() {
    return loginId;
  }

  public void setEmail(String email) {
    this.loginId = email;
  }

  public String getUsername() {
    return loginId;
  }

  public void setUsername(String username) {
    this.loginId = username;
  }
}
