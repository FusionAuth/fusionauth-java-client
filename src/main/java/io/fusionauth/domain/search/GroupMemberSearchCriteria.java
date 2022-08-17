/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static io.fusionauth.domain.util.SQLTools.normalizeOrderBy;

/**
 * Search criteria for Group Members
 *
 * @author Daniel DeGroff
 */
public class GroupMemberSearchCriteria extends BaseSearchCriteria {
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
    return "insertInstant ASC";
  }

  static {
    SortableFields.put("id", "id");
    SortableFields.put("insertInstant", "gm.insert_instant");
    SortableFields.put("groupId", "gm.groups_id");
    SortableFields.put("tenantId", "g.tenants_id");
    SortableFields.put("userId", "gm.users_id");
  }
}
