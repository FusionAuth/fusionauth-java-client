/*
 * Copyright (c) 2024-2025, FusionAuth, All Rights Reserved
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

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.WebhookEventResult;
import io.fusionauth.domain.event.EventType;
import static io.fusionauth.domain.util.SQLTools.normalizeOrderBy;
import static io.fusionauth.domain.util.SQLTools.toSearchString;

/**
 * Search criteria for the webhook event log.
 *
 * @author Spencer Witt
 */
public class WebhookEventLogSearchCriteria extends BaseSearchCriteria implements Buildable<WebhookEventLogSearchCriteria> {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public ZonedDateTime end = ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(1).truncatedTo(ChronoUnit.MINUTES);

  public String event;

  public WebhookEventResult eventResult;

  public EventType eventType;

  public ZonedDateTime start = ZonedDateTime.now(ZoneOffset.UTC).minusHours(1).truncatedTo(ChronoUnit.MINUTES);

  @JacksonConstructor
  public WebhookEventLogSearchCriteria() {
  }

  @Override
  public WebhookEventLogSearchCriteria prepare() {
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = normalizeOrderBy(orderBy, SortableFields);
    event = toSearchString(event);
    return this;
  }

  @Override
  public Set<String> supportedOrderByColumns() {
    return SortableFields.keySet();
  }

  @Override
  protected String defaultOrderBy() {
    return "insertInstant DESC";
  }

  static {
    SortableFields.put("eventResult", "w.event_result");
    SortableFields.put("eventType", "w.event_type");
    SortableFields.put("id", "w.id");
    SortableFields.put("insertInstant", "w.insert_instant");
    SortableFields.put("lastAttemptInstant", "w.last_attempt_instant");
    SortableFields.put("linkedObjectId", "w.linked_object_id");
    SortableFields.put("sequence", "w.sequence");
  }
}
