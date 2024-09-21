/*
 * Copyright (c) 2018-2024, FusionAuth, All Rights Reserved
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;

/**
 * Login API request object.
 *
 * @author Seth Musselman
 */
public class LoginRequest extends BaseLoginRequest implements Buildable<LoginRequest> {
  public String loginId;

  public String oneTimePassword;

  public String password;

  public String twoFactorTrustId;

  public List<String> types = new ArrayList<>();

  @JacksonConstructor
  public LoginRequest() {
  }

  public LoginRequest(UUID applicationId, String loginId, String password) {
    this.applicationId = applicationId;
    this.loginId = loginId;
    this.password = password;
  }

  @Deprecated
  public LoginRequest(UUID applicationId, String loginId, String password, String ipAddress) {
    this.applicationId = applicationId;
    this.loginId = loginId;
    this.password = password;

    if (ipAddress != null) {
      eventInfo = eventInfo != null ? eventInfo : new EventInfo();
      eventInfo.ipAddress = ipAddress;
    }
  }

  public LoginRequest(EventInfo eventInfo, UUID applicationId, String loginId, String password) {
    super(eventInfo);
    this.applicationId = applicationId;
    this.loginId = loginId;
    this.password = password;
  }
}
