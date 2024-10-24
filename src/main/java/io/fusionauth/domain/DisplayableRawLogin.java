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
package io.fusionauth.domain;

import java.util.Objects;

/**
 * A displayable raw login that includes application name and user loginId.
 *
 * @author Brian Pontarelli
 */
public class DisplayableRawLogin extends RawLogin implements Buildable<DisplayableRawLogin> {
  public String applicationName;

  public Location location;

  public String loginId;

  public IdentityType loginIdType;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    DisplayableRawLogin that = (DisplayableRawLogin) o;
    return Objects.equals(applicationName, that.applicationName) && Objects.equals(location, that.location) && Objects.equals(loginId, that.loginId) && Objects.equals(loginIdType, that.loginIdType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), applicationName, location, loginId, loginIdType);
  }
}
