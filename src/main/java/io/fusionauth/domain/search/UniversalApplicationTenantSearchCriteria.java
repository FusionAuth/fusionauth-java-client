/*
 * Copyright (c) 2025, FusionAuth, All Rights Reserved
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
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import io.fusionauth.domain.Buildable;
import static io.fusionauth.domain.util.SQLTools.normalizeOrderBy;
import static io.fusionauth.domain.util.SQLTools.toSearchString;

/**
 * @author Lyle Schemmerling
 */
public class UniversalApplicationTenantSearchCriteria extends BaseSearchCriteria implements Buildable<UniversalApplicationTenantSearchCriteria> {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public UUID applicationId;

  public UUID tenantId;

  public String tenantName;

  @Override
  public BaseSearchCriteria prepare() {
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = normalizeOrderBy(orderBy, SortableFields, Set.of());
    tenantName = toSearchString(tenantName);
    return this;
  }

  @Override
  public Set<String> supportedOrderByColumns() {
    return SortableFields.keySet();
  }

  @Override
  protected String defaultOrderBy() {
    return "id ASC";
  }

  static {
    SortableFields.put("id", "uat.id");
    SortableFields.put("insertInstant", "uat.insert_instant");
    SortableFields.put("lastUpdateInstant", "uat.last_update_instant");
    SortableFields.put("applicationId", "uat.applications_id");
    SortableFields.put("tenantId", "uat.tenants_id");
    SortableFields.put("tenantName", "t.name");
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UniversalApplicationTenantSearchCriteria that = (UniversalApplicationTenantSearchCriteria) o;
    return Objects.equals(applicationId, that.applicationId) && Objects.equals(tenantId, that.tenantId) && Objects.equals(tenantName, that.tenantName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId, tenantId, tenantName);
  }
}
