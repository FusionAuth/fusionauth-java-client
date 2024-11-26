/*
 * Copyright (c) 2022-2024, FusionAuth, All Rights Reserved
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

/**
 * Search criteria for Group Members
 *
 * @author Daniel DeGroff
 */
public class GroupMemberSearchCriteria extends BaseSearchCriteria implements Buildable<GroupMemberSearchCriteria> {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public UUID groupId;

  public UUID tenantId;

  public UUID userId;

  @Override
  public GroupMemberSearchCriteria prepare() {
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = normalizeOrderBy(orderBy, SortableFields);
    return this;
  }

  @Override
  public Set<String> supportedOrderByColumns() {
    return SortableFields.keySet();
  }

  @Override
  protected String defaultOrderBy() {
    // All memberships for a given group share an insertInstant because they are recreated each time
    // Add userId to provide consistent ordering within a group. The groupId ordering ensures consistency
    // for a single user's memberships across multiple groups if they happen to have the same insertInstant.
    return "insertInstant ASC, userId ASC, groupId ASC";
  }

  static {
    SortableFields.put("id", "gm.id");
    SortableFields.put("insertInstant", "gm.insert_instant");
    SortableFields.put("groupId", "gm.groups_id");
    SortableFields.put("tenantId", "g.tenants_id");
    SortableFields.put("userId", "gm.users_id");
  }
}
