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

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Key.KeyAlgorithm;
import io.fusionauth.domain.Key.KeyType;
import static io.fusionauth.domain.util.SQLTools.normalizeOrderBy;
import static io.fusionauth.domain.util.SQLTools.toSearchString;

/**
 * Search criteria for Keys
 *
 * @author Spencer Witt
 */
public class KeySearchCriteria extends BaseSearchCriteria {
  public static final Set<String> NullableFields = new HashSet<>();

  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public KeyAlgorithm algorithm;

  public String name;

  public KeyType type;

  @JacksonConstructor
  public KeySearchCriteria() {
  }

  @Override
  public KeySearchCriteria prepare() {
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
    NullableFields.add("algorithm");
    NullableFields.add("expiration");

    SortableFields.put("id", "k.id");
    SortableFields.put("name", "k.name");
    SortableFields.put("type", "k.type");
    SortableFields.put("algorithm", "k.algorithm");
    SortableFields.put("expiration", "k.expiration_instant");
    SortableFields.put("insertInstant", "k.insert_instant");
  }
}
