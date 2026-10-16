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

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * The system configuration for token exchange audit log data.
 * <p>
 * Deleting these entries is mandatory, so this configuration intentionally has no enabled flag. Token exchange volume can be high enough
 * that retaining entries indefinitely would grow the database without bound. Longer retention is served by ingesting the token exchange
 * webhooks into external storage.
 */
public class TokenExchangeAuditLogConfiguration {
  public int numberOfDaysToRetain = 7;

  @JacksonConstructor
  public TokenExchangeAuditLogConfiguration() {
  }

  public TokenExchangeAuditLogConfiguration(TokenExchangeAuditLogConfiguration other) {
    this.numberOfDaysToRetain = other.numberOfDaysToRetain;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TokenExchangeAuditLogConfiguration)) {
      return false;
    }
    TokenExchangeAuditLogConfiguration that = (TokenExchangeAuditLogConfiguration) o;
    return numberOfDaysToRetain == that.numberOfDaysToRetain;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numberOfDaysToRetain);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
