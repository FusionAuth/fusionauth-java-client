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
package io.fusionauth.domain.api;

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.TokenExchangeAuditLog;

/**
 * Token exchange audit log chain response.
 */
public class TokenExchangeAuditLogChainResponse {
  /**
   * The exchanges that led to the requested token, ordered by depth. The exchange that issued the requested token is first, followed by the
   * exchange that issued its subject token, and so on.
   */
  public List<TokenExchangeAuditLog> tokenExchangeAuditLogs;

  @JacksonConstructor
  public TokenExchangeAuditLogChainResponse() {
  }

  public TokenExchangeAuditLogChainResponse(List<TokenExchangeAuditLog> tokenExchangeAuditLogs) {
    this.tokenExchangeAuditLogs = tokenExchangeAuditLogs;
  }
}
