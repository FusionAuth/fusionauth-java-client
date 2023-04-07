/*
 * Copyright (c) 2023-2023, FusionAuth, All Rights Reserved
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

import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.LambdaType;
import static io.fusionauth.domain.util.SQLTools.normalizeOrderBy;
import static io.fusionauth.domain.util.SQLTools.toSearchString;

/**
 * Search criteria for Lambdas
 *
 * @author Mark Manes
 */
public class LambdaSearchCriteria extends BaseSearchCriteria implements Buildable<LambdaSearchCriteria> {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public String body;

  public String name;

  public LambdaType type;

  @Override
  public LambdaSearchCriteria prepare() {
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = normalizeOrderBy(orderBy, SortableFields);
    body = toSearchString(body);
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
    SortableFields.put("id", "id");
    SortableFields.put("insertInstant", "insert_instant");
    SortableFields.put("name", "name");
    SortableFields.put("engineType", "engine_type");
  }
}
