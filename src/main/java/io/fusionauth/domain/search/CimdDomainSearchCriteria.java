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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import io.fusionauth.domain.Buildable;
import static io.fusionauth.domain.util.SQLTools.normalizeOrderBy;
import static io.fusionauth.domain.util.SQLTools.toSearchString;

/**
 * Search criteria for CIMD domains.
 */
public class CimdDomainSearchCriteria extends BaseSearchCriteria implements Buildable<CimdDomainSearchCriteria> {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public String domain;

  public String name;

  public UUID tenantId;

  @Override
  public CimdDomainSearchCriteria prepare() {
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = normalizeOrderBy(orderBy, SortableFields);
    domain = toSearchString(domain);
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
    SortableFields.put("id", "cd.id");
    SortableFields.put("insertInstant", "cd.insert_instant");
    SortableFields.put("domain", "cd.domain");
    SortableFields.put("name", "cd.name");
  }
}
