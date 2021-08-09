/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.api.BaseLoginRequest;

/**
 * @author Daniel DeGroff
 */
public class PasswordlessLoginRequest extends BaseLoginRequest implements Buildable<PasswordlessLoginRequest> {
  public String code;

  public String twoFactorTrustId;

  @JacksonConstructor
  public PasswordlessLoginRequest() {
  }

  public PasswordlessLoginRequest(String code) {
    this.code = code;
  }

  public PasswordlessLoginRequest(String code, String ipAddress) {
    this.code = code;

    if (ipAddress != null) {
      eventInfo = eventInfo != null ? eventInfo : new EventInfo();
      eventInfo.ipAddress = ipAddress;
    }
  }

  public PasswordlessLoginRequest(EventInfo eventInfo, String code) {
    super(eventInfo);
    this.code = code;
  }
}
