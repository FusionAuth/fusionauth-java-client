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
package io.fusionauth.domain.search;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.TokenExchangeOutcome;
import io.fusionauth.domain.util.SQLTools;

/**
 * Search criteria for the token exchange audit log. Filterable on exactly {@code tenantId}, {@code exchangerClientId}, {@code outcome},
 * and an exchange instant range — no other fields, per the ARC decision to limit the index count on this table.
 */
public class TokenExchangeAuditLogSearchCriteria extends BaseSearchCriteria implements Buildable<TokenExchangeAuditLogSearchCriteria> {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public ZonedDateTime end;

  public UUID exchangerClientId;

  public TokenExchangeOutcome outcome;

  public ZonedDateTime start;

  public UUID tenantId;

  @JacksonConstructor
  public TokenExchangeAuditLogSearchCriteria() {
  }

  @Override
  public TokenExchangeAuditLogSearchCriteria prepare() {
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = SQLTools.normalizeOrderBy(orderBy, SortableFields);
    return this;
  }

  @Override
  public Set<String> supportedOrderByColumns() {
    return SortableFields.keySet();
  }

  @Override
  protected String defaultOrderBy() {
    return "insertInstant DESC, eventId DESC";
  }

  static {
    SortableFields.put("eventId", "t.event_id");
    SortableFields.put("insertInstant", "t.insert_instant");
  }
}
