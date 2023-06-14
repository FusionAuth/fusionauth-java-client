/*
 * Copyright (c) 2018-2023, FusionAuth, All Rights Reserved
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

import java.util.UUID;

import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.jwt.RefreshToken.MetaData;

/**
 * @author Daniel DeGroff
 */
public class BaseLoginRequest extends BaseEventRequest {
  public UUID applicationId;

  @Deprecated
  public String ipAddress;

  @Deprecated
  public MetaData metaData;

  public boolean newDevice;

  public boolean noJWT;

  public BaseLoginRequest() {
  }

  public BaseLoginRequest(EventInfo eventInfo) {
    super(eventInfo);
  }

  public void normalize() {
    eventInfo = eventInfo != null ? eventInfo : new EventInfo();
    if (eventInfo.ipAddress == null) {
      eventInfo.ipAddress = ipAddress;
    }

    if (metaData == null || metaData.device == null) {
      return;
    }

    if (eventInfo.deviceDescription == null) {
      eventInfo.deviceDescription = metaData.device.description;
    }

    if (eventInfo.deviceName == null) {
      eventInfo.deviceName = metaData.device.name;
    }

    if (eventInfo.deviceType == null) {
      eventInfo.deviceType = metaData.device.type;
    }
  }
}
