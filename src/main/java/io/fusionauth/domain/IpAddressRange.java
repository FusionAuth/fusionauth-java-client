/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;

/**
 * TODO : ip-allow-block : Fix names so they are all the same. I prefer `IP`.
 *
 * @author Brett Guy
 */
public class IpAddressRange implements Buildable<IpAddressRange> {
  // TODO : ip-allow-block : Should we use InetAddress and convert it for MyBatis and Jackson?
  // TODO : ip-allow-block : Fix names so they are all consistent. I prefer `IP`
  public String endIpAddress;

  // TODO description field

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public AddressRangeMode mode;

  // TODO : ip-allow-block : Should we use InetAddress and convert it for MyBatis and Jackson?
  // TODO : ip-allow-block : Fix names so they are all consistent. I prefer `IP`
  public String startIpAddress;

  public IpAddressRange() {
  }

  public IpAddressRange(String startIpAddress, String endIP, AddressRangeMode mode) {
    this.startIpAddress = startIpAddress;
    this.endIpAddress = endIP;
    this.mode = mode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IpAddressRange that = (IpAddressRange) o;
    return Objects.equals(endIpAddress, that.endIpAddress) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(mode, that.mode) &&
           Objects.equals(startIpAddress, that.startIpAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(endIpAddress, id, insertInstant, lastUpdateInstant, mode, startIpAddress);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}