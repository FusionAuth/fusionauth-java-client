/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.util.SQLTools;
import static io.fusionauth.domain.util.SQLTools.toSearchString;

/**
 * @author Brian Pontarelli
 */
public class AuditLogSearchCriteria extends BaseSearchCriteria implements Buildable<AuditLogSearchCriteria> {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public ZonedDateTime end;

  public String message;

  public String newValue;

  public String oldValue;

  public String reason;

  public ZonedDateTime start;

  public String user;

  @JacksonConstructor
  public AuditLogSearchCriteria() {
    prepare();
  }

  public AuditLogSearchCriteria(String message, String user, ZonedDateTime start, ZonedDateTime end, String orderBy) {
    this.end = end;
    this.message = message;
    this.start = start;
    this.user = user;
    this.orderBy = orderBy;
  }

  public AuditLogSearchCriteria(String message, String user, ZonedDateTime start, ZonedDateTime end, int startRow,
                                int numberOfResults, String orderBy) {
    this.end = end;
    this.message = message;
    this.start = start;
    this.user = user;
    this.startRow = startRow;
    this.numberOfResults = numberOfResults;
    this.orderBy = orderBy;
  }

  @Override
  public AuditLogSearchCriteria prepare() {
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = SQLTools.normalizeOrderBy(orderBy, SortableFields);
    user = toSearchString(user);
    message = toSearchString(message);
    newValue = toSearchString(newValue);
    oldValue = toSearchString(oldValue);
    reason = toSearchString(reason);
    return this;
  }

  @Override
  protected String defaultOrderBy() {
    return "insertInstant DESC";
  }

  static {
    SortableFields.put("insertInstant", "insert_instant");
    SortableFields.put("insertUser", "insert_user");
    SortableFields.put("message", "message");
  }
}
