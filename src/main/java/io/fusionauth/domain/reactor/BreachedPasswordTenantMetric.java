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
package io.fusionauth.domain.reactor;

import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class BreachedPasswordTenantMetric implements Buildable<BreachedPasswordTenantMetric> {
  public int actionRequired;

  public int matchedCommonPasswordCount;

  public int matchedExactCount;

  public int matchedPasswordCount;

  public int matchedSubAddressCount;

  public int passwordsCheckedCount;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BreachedPasswordTenantMetric)) {
      return false;
    }
    BreachedPasswordTenantMetric that = (BreachedPasswordTenantMetric) o;
    return actionRequired == that.actionRequired &&
           matchedCommonPasswordCount == that.matchedCommonPasswordCount &&
           matchedExactCount == that.matchedExactCount &&
           matchedPasswordCount == that.matchedPasswordCount &&
           matchedSubAddressCount == that.matchedSubAddressCount &&
           passwordsCheckedCount == that.passwordsCheckedCount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(actionRequired,
                        matchedCommonPasswordCount,
                        matchedExactCount,
                        matchedPasswordCount,
                        matchedSubAddressCount,
                        passwordsCheckedCount);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public int totalBreached() {
    return matchedCommonPasswordCount + matchedExactCount + matchedPasswordCount + matchedSubAddressCount;
  }
}