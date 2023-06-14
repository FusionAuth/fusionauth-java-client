/*
 * Copyright (c) 2021-2023, FusionAuth, All Rights Reserved
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

import java.util.Map;
import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.jwt.DeviceInfo;
import io.fusionauth.domain.jwt.RefreshToken.MetaData;

/**
 * Information about a user event (login, register, etc) that helps identify the source of the event (location, device type, OS, etc).
 *
 * @author Brian Pontarelli
 */
public class EventInfo implements Buildable<EventInfo> {
  public Map<String, Object> data;

  public String deviceDescription;

  public String deviceName;

  public String deviceType;

  public String ipAddress;

  public Location location;

  public String os;

  public String userAgent;

  public EventInfo() {
  }

  public EventInfo(MetaData metaData) {
    this.deviceDescription = (metaData != null && metaData.device != null) ? metaData.device.description : null;
    this.deviceName = (metaData != null && metaData.device != null) ? metaData.device.name : null;
    this.deviceType = (metaData != null && metaData.device != null && metaData.device.type != null) ? metaData.device.type.toString() : null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventInfo eventInfo = (EventInfo) o;
    return Objects.equals(deviceDescription, eventInfo.deviceDescription) &&
           Objects.equals(data, eventInfo.data) &&
           Objects.equals(deviceName, eventInfo.deviceName) &&
           Objects.equals(deviceType, eventInfo.deviceType) &&
           Objects.equals(ipAddress, eventInfo.ipAddress) &&
           Objects.equals(location, eventInfo.location) &&
           Objects.equals(os, eventInfo.os) &&
           Objects.equals(userAgent, eventInfo.userAgent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, deviceDescription, deviceName, deviceType, ipAddress, location, os, userAgent);
  }

  public MetaData toMetaData() {
    return new MetaData().with(md -> md.device = new DeviceInfo())
                         .with(md -> md.device.description = deviceDescription)
                         .with(md -> md.device.name = deviceName)
                         .with(md -> md.device.type = deviceType);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
