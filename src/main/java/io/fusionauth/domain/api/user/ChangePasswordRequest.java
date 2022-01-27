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

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.api.BaseEventRequest;

/**
 * Change password request object.
 *
 * @author Brian Pontarelli
 */
public class ChangePasswordRequest extends BaseEventRequest implements Buildable<ChangePasswordRequest> {
  public UUID applicationId;

  public String changePasswordId;

  public String currentPassword;

  public String loginId;

  public String password;

  public String refreshToken;

  @JacksonConstructor
  public ChangePasswordRequest() {
  }

  public ChangePasswordRequest(String password) {
    this.password = password;
  }

  public ChangePasswordRequest(String loginId, String currentPassword, String password) {
    this.currentPassword = currentPassword;
    this.loginId = loginId;
    this.password = password;
  }
}
