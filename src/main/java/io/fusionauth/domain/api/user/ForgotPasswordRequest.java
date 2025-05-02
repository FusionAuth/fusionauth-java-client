/*
 * Copyright (c) 2018-2025, FusionAuth, All Rights Reserved
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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.IdentityType;
import io.fusionauth.domain.api.BaseEventRequest;

/**
 * Forgot password request object.
 *
 * @author Brian Pontarelli
 */
@SuppressWarnings("unused")
public class ForgotPasswordRequest extends BaseEventRequest {
  public UUID applicationId;

  public String changePasswordId;

  public String loginId;

  public List<String> loginIdTypes;

  @Deprecated
  public boolean sendForgotPasswordEmail = true;

  public Boolean sendForgotPasswordMessage;

  public Map<String, Object> state;

  @JacksonConstructor
  public ForgotPasswordRequest() {
  }

  public ForgotPasswordRequest(String loginId) {
    this.loginId = loginId;
  }

  public ForgotPasswordRequest(String loginId, Map<String, Object> state) {
    this.loginId = loginId;
    this.state = state;
  }

  public ForgotPasswordRequest(UUID applicationId, String loginId) {
    this.applicationId = applicationId;
    this.loginId = loginId;
  }

  public ForgotPasswordRequest(String loginId, boolean sendForgotPasswordMessage) {
    this.loginId = loginId;
    this.sendForgotPasswordMessage = sendForgotPasswordMessage;
  }

  public ForgotPasswordRequest(EventInfo eventInfo, UUID applicationId, String loginId) {
    super(eventInfo);
    this.applicationId = applicationId;
    this.loginId = loginId;
  }

  public ForgotPasswordRequest(EventInfo eventInfo, String loginId, Map<String, Object> state) {
    super(eventInfo);
    this.loginId = loginId;
    this.state = state;
  }

  public String getEmail() {
    return loginId;
  }

  public void setEmail(String email) {
    this.loginId = email;
    this.loginIdTypes = Collections.singletonList(IdentityType.email.name);
  }

  public String getUsername() {
    return loginId;
  }

  public void setUsername(String username) {
    this.loginId = username;
    this.loginIdTypes = Collections.singletonList(IdentityType.username.name);
  }
}
