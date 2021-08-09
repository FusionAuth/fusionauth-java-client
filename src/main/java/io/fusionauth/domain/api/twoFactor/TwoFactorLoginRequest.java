/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.twoFactor;

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.api.BaseLoginRequest;

/**
 * @author Daniel DeGroff
 */
public class TwoFactorLoginRequest extends BaseLoginRequest implements Buildable<TwoFactorLoginRequest> {
  public String code;

  public boolean trustComputer;

  public String twoFactorId;

  public UUID userId;

  @JacksonConstructor
  public TwoFactorLoginRequest() {
  }

  public TwoFactorLoginRequest(UUID applicationId, String code, String twoFactorId) {
    this.applicationId = applicationId;
    this.code = code;
    this.twoFactorId = twoFactorId;
  }

  public TwoFactorLoginRequest(EventInfo eventInfo, UUID applicationId, String code, String twoFactorId) {
    super(eventInfo);
    this.applicationId = applicationId;
    this.code = code;
    this.twoFactorId = twoFactorId;
  }

  @Deprecated
  public TwoFactorLoginRequest(UUID applicationId, String code, String twoFactorId, String ipAddress) {
    this.applicationId = applicationId;
    this.code = code;
    this.twoFactorId = twoFactorId;

    if (ipAddress != null) {
      eventInfo = eventInfo != null ? eventInfo : new EventInfo();
      eventInfo.ipAddress = ipAddress;
    }
  }
}
