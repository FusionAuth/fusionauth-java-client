/*
 * Copyright (c) 2019-2025, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.passwordless;

import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class PasswordlessSendRequest implements Buildable<PasswordlessSendRequest> {
  public UUID applicationId;

  public String code;

  public String loginId;

  public Map<String, Object> state;

  @JacksonConstructor
  public PasswordlessSendRequest() {
  }

  /**
   * Construct a passwordless send request using a code from the Start API
   *
   * @param code code from the start API
   */
  public PasswordlessSendRequest(String code) {
    this.code = code;
  }

  /**
   * Construct a passwordless send request
   *
   * @param applicationId application id
   * @param loginId       login id of user
   * @param code          passwordless code
   * @deprecated Passwordless send is done either with a loginId or code, but not both. See {@link #PasswordlessSendRequest(String)}
   * or {@link #PasswordlessSendRequest(UUID, String, Map)}.
   */
  @Deprecated
  public PasswordlessSendRequest(UUID applicationId, String loginId, String code) {
    this.applicationId = applicationId;
    this.loginId = loginId;
    this.code = code;
  }

  /**
   * Construct a passwordless send request without a code from the Start API
   *
   * @param applicationId application id
   * @param loginId       login id of user (either username or email address)
   * @param state         Optional state that will be returned when completing the login request.
   */
  @SuppressWarnings("unused")
  public PasswordlessSendRequest(UUID applicationId, String loginId, Map<String, Object> state) {
    this.applicationId = applicationId;
    this.loginId = loginId;
    this.state = state;
  }
}
