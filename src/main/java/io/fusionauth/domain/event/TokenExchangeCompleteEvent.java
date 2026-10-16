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
package io.fusionauth.domain.event;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.TokenExchangeAuditLog;

/**
 * Models the Token Exchange Complete event. This is a non-transactional variant of the token.exchange event.
 * Delivery failure does not block or fail the token exchange request.
 */
public class TokenExchangeCompleteEvent extends TokenExchangeEvent implements NonTransactionalEvent {

  @JacksonConstructor
  public TokenExchangeCompleteEvent() {
  }

  public TokenExchangeCompleteEvent(EventInfo info, TokenExchangeAuditLog auditLog) {
    super(info, auditLog);
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public EventType getType() {
    return EventType.TokenExchangeComplete;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }
}
