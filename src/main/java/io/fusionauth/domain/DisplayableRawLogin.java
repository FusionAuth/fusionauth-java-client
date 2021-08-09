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
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.ToString;

/**
 * A displayable raw login that includes application name and user loginId.
 *
 * @author Brian Pontarelli
 */
public class DisplayableRawLogin extends RawLogin implements Buildable<DisplayableRawLogin> {
  public String applicationName;

  public Location location;

  public String loginId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DisplayableRawLogin)) {
      return false;
    }
    DisplayableRawLogin rawLogin = (DisplayableRawLogin) o;
    return Objects.equals(applicationId, rawLogin.applicationId) &&
           Objects.equals(applicationName, rawLogin.applicationName) &&
           Objects.equals(instant, rawLogin.instant) &&
           Objects.equals(ipAddress, rawLogin.ipAddress) &&
           Objects.equals(location, rawLogin.location) &&
           Objects.equals(loginId, rawLogin.loginId) &&
           Objects.equals(userId, rawLogin.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId, applicationName, instant, ipAddress, location, loginId, userId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
