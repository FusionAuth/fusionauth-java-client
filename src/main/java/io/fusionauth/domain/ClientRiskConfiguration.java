/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
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
 * Flags to enable or disable specific risk signals that contribute to the composite client risk calculation.
 */
public class ClientRiskConfiguration extends Enableable implements Buildable<ClientRiskConfiguration> {

  public boolean blocklistedIp = true;

  public boolean botDetected = true;

  public boolean dormantAccount = true;

  public boolean dormantPassword = true;

  public boolean impossibleTravel = true;

  public boolean recentIdentityChange = true;

  public boolean recentPasswordChange = true;

  public boolean suspiciousUserAgent = true;

  public boolean unrecognizedDevice = true;

  public boolean untrustedDevice = true;

  public ClientRiskConfiguration() {
  }

  public ClientRiskConfiguration(ClientRiskConfiguration other) {
    this.enabled = other.enabled;
    this.botDetected = other.botDetected;
    this.dormantAccount = other.dormantAccount;
    this.impossibleTravel = other.impossibleTravel;
    this.blocklistedIp = other.blocklistedIp;
    this.unrecognizedDevice = other.unrecognizedDevice;
    this.recentIdentityChange = other.recentIdentityChange;
    this.dormantPassword = other.dormantPassword;
    this.recentPasswordChange = other.recentPasswordChange;
    this.untrustedDevice = other.untrustedDevice;
    this.suspiciousUserAgent = other.suspiciousUserAgent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ClientRiskConfiguration)) {
      return false;
    }
    ClientRiskConfiguration that = (ClientRiskConfiguration) o;
    return enabled == that.enabled &&
           blocklistedIp == that.blocklistedIp &&
           botDetected == that.botDetected &&
           dormantAccount == that.dormantAccount &&
           dormantPassword == that.dormantPassword &&
           impossibleTravel == that.impossibleTravel &&
           recentIdentityChange == that.recentIdentityChange &&
           recentPasswordChange == that.recentPasswordChange &&
           suspiciousUserAgent == that.suspiciousUserAgent &&
           unrecognizedDevice == that.unrecognizedDevice &&
           untrustedDevice == that.untrustedDevice;
  }

  @Override
  public int hashCode() {
    return Objects.hash(enabled, blocklistedIp, botDetected, dormantAccount, dormantPassword, impossibleTravel, recentIdentityChange,
                        recentPasswordChange, suspiciousUserAgent, unrecognizedDevice, untrustedDevice);
  }

  @Override
  public String toString() {
    return com.inversoft.json.ToString.toString(this);
  }
}
