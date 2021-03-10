/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.EventLogType;

/**
 * Search criteria for the event log.
 *
 * @author Brian Pontarelli
 */
public class EventLogSearchCriteria extends BaseSearchCriteria {
  public ZonedDateTime end;

  public String message;

  public ZonedDateTime start;

  public EventLogType type;

  @JacksonConstructor
  public EventLogSearchCriteria() {
    orderBy = defaultOrderBy();
  }

  public EventLogSearchCriteria(String message, EventLogType type, ZonedDateTime start, ZonedDateTime end, int startRow,
                                int numberOfResults, String orderBy) {
    this.end = end;
    this.message = message;
    this.numberOfResults = numberOfResults;
    this.orderBy = orderBy;
    this.start = start;
    this.startRow = startRow;
    this.type = type;
  }

  public EventLogSearchCriteria(int startRow, int numberOfResults) {
    this.numberOfResults = numberOfResults;
    orderBy = defaultOrderBy();
    this.startRow = startRow;
  }

  @Override
  public void prepare() {
    secure();
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }
    message = toSearchString(message);
  }

  @Override
  protected String defaultOrderBy() {
    return "insert_instant DESC, id DESC";
  }
}
