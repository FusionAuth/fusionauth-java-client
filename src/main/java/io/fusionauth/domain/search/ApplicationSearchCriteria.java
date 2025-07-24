/*
 * Copyright (c) 2023-2025, FusionAuth, All Rights Reserved
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

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.ObjectState;
import static io.fusionauth.domain.util.SQLTools.normalizeOrderBy;
import static io.fusionauth.domain.util.SQLTools.toSearchString;

/**
 * Search criteria for Applications
 *
 * @author Spencer Witt
 */
public class ApplicationSearchCriteria extends BaseSearchCriteria implements Buildable<ApplicationSearchCriteria> {
  public static final Set<String> NullableFields = new HashSet<>();

  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public String name;

  public ObjectState state;

  public UUID tenantId;

  @Override
  public ApplicationSearchCriteria prepare() {
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = normalizeOrderBy(orderBy, SortableFields, NullableFields);
    name = toSearchString(name);
    return this;
  }

  @Override
  public Set<String> supportedOrderByColumns() {
    return SortableFields.keySet();
  }

  @Override
  protected String defaultOrderBy() {
    return "name ASC";
  }

  static {
    NullableFields.add("tenant");

    SortableFields.put("id", "a.id");
    SortableFields.put("insertInstant", "a.insert_instant");
    SortableFields.put("name", "a.name");
    SortableFields.put("tenant", "t.name");
  }
}
