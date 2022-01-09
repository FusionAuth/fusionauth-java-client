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
package io.fusionauth.domain.jwt;

import java.time.ZonedDateTime;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class DeviceInfo implements Buildable<DeviceInfo> {
  public String description;

  public String lastAccessedAddress;

  public ZonedDateTime lastAccessedInstant;

  public String name;

  public DeviceType type = DeviceType.UNKNOWN;

  @JacksonConstructor
  public DeviceInfo() {
  }

  public DeviceInfo(DeviceInfo other) {
    this.description = other.description;
    this.lastAccessedAddress = other.lastAccessedAddress;
    this.lastAccessedInstant = other.lastAccessedInstant;
    this.name = other.name;
    this.type = other.type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceInfo device = (DeviceInfo) o;
    return Objects.equals(description, device.description) &&
           Objects.equals(lastAccessedAddress, device.lastAccessedAddress) &&
           Objects.equals(lastAccessedInstant, device.lastAccessedInstant) &&
           Objects.equals(name, device.name) &&
           type == device.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, lastAccessedAddress, lastAccessedInstant, name, type);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public enum DeviceType {
    BROWSER,
    DESKTOP,
    LAPTOP,
    MOBILE,
    OTHER,
    SERVER,
    TABLET,
    TV,
    UNKNOWN;

    public static DeviceType safeValueOf(String type) {
      if (type == null) {
        return null;
      }

      try {
        return DeviceType.valueOf(type);
      } catch (IllegalArgumentException e) {
        return null;
      }
    }
  }
}
