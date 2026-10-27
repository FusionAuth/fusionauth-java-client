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
import io.fusionauth.domain.util.SQLTools;

/**
 * Search criteria for CIMD client registrations.
 */
public class CimdClientRegistrationSearchCriteria extends BaseSearchCriteria implements Buildable<CimdClientRegistrationSearchCriteria> {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public UUID cimdDomainId;

  /**
   * Raw {@code client_id} URL for an exact-match lookup. The backing uniqueness constraint is hash-based, so the mapper hashes this
   * in SQL to compare against the stored digest rather than matching the raw value directly.
   */
  public String clientId;

  public UUID tenantId;

  @Override
  public CimdClientRegistrationSearchCriteria prepare() {
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
    return "insertInstant DESC";
  }

  static {
    SortableFields.put("id", "cr.id");
    SortableFields.put("insertInstant", "cr.insert_instant");
  }
}
