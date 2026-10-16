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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.util.Normalizer;

/**
 * Tenant level configuration for RFC 8693 token exchange.
 */
public class TenantTokenExchangeConfiguration extends Enableable implements Buildable<TenantTokenExchangeConfiguration> {
  public List<String> allowedIssuers = new ArrayList<>();

  public boolean debug;

  /**
   * Maximum allowed depth of the <code>act</code> claim chain on an issued token. Default is 2, configurable up to a
   * platform ceiling of 10.
   */
  public int maxClaimDepth = 2;

  public TokenExchangePolicyEngine policyEngine;

  public UUID policyEngineLambdaId;

  public UUID populateLambdaId;

  @JacksonConstructor
  public TenantTokenExchangeConfiguration() {
  }

  public TenantTokenExchangeConfiguration(TenantTokenExchangeConfiguration other) {
    this.enabled = other.enabled;
    this.allowedIssuers.addAll(other.allowedIssuers);
    this.debug = other.debug;
    this.maxClaimDepth = other.maxClaimDepth;
    this.policyEngine = other.policyEngine;
    this.policyEngineLambdaId = other.policyEngineLambdaId;
    this.populateLambdaId = other.populateLambdaId;
  }

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
    TenantTokenExchangeConfiguration that = (TenantTokenExchangeConfiguration) o;
    return debug == that.debug &&
           maxClaimDepth == that.maxClaimDepth &&
           Objects.equals(allowedIssuers, that.allowedIssuers) &&
           policyEngine == that.policyEngine &&
           Objects.equals(policyEngineLambdaId, that.policyEngineLambdaId) &&
           Objects.equals(populateLambdaId, that.populateLambdaId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(),
                        allowedIssuers,
                        debug,
                        maxClaimDepth,
                        policyEngine,
                        policyEngineLambdaId,
                        populateLambdaId);
  }

  public void normalize() {
    Normalizer.removeEmpty(allowedIssuers);
    Normalizer.deDuplicate(allowedIssuers);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
