/*
 * Copyright (c) 2023-2026, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.SAMLv2DestinationAssertionPolicy;

/**
 * @author Lyle Schemmerling
 */
public class SAMLv2DestinationAssertionConfiguration implements Buildable<SAMLv2DestinationAssertionConfiguration> {
  public List<String> alternates = new ArrayList<>();

  public SAMLv2DestinationAssertionPolicy policy = SAMLv2DestinationAssertionPolicy.Enabled;

  public SAMLv2DestinationAssertionConfiguration() {
  }

  public SAMLv2DestinationAssertionConfiguration(SAMLv2DestinationAssertionConfiguration other) {
    this.alternates.addAll(other.alternates);
    this.policy = other.policy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SAMLv2DestinationAssertionConfiguration that = (SAMLv2DestinationAssertionConfiguration) o;
    return Objects.equals(alternates, that.alternates) &&
           policy == that.policy;
  }

  @Override
  public int hashCode() {
    return Objects.hash(alternates, policy);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
