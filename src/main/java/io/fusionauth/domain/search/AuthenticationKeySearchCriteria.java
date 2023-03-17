/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import io.fusionauth.domain.Buildable;
import static io.fusionauth.domain.util.SQLTools.normalizeOrderBy;
import static io.fusionauth.domain.util.SQLTools.toSearchString;

public class AuthenticationKeySearchCriteria extends BaseSearchCriteria implements Buildable<AuthenticationKeySearchCriteria> {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public String description;

  public Boolean keyManager;

  public UUID tenantId;

  public AuthenticationKeySearchCriteria() {
  }

  public AuthenticationKeySearchCriteria(AuthenticationKeySearchCriteria other) {
    this.description = other.description;
    this.keyManager = other.keyManager;
    this.tenantId = other.tenantId;
    this.numberOfResults = other.numberOfResults;
    this.orderBy = other.orderBy;
    this.startRow = other.startRow;
  }

  @Override
  public BaseSearchCriteria prepare() {
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = normalizeOrderBy(orderBy, SortableFields);
    description = toSearchString(description);
    return this;
  }

  @Override
  public Set<String> supportedOrderByColumns() {
    return SortableFields.keySet();
  }

  @Override
  protected String defaultOrderBy() {
    return "insertInstant ASC";
  }

  static {
    SortableFields.put("id", "k.id");
    SortableFields.put("insertInstant", "k.insert_instant");
    SortableFields.put("keyManager", "k.key_manager");
    SortableFields.put("keyValue", "k.key_value");
  }
}
