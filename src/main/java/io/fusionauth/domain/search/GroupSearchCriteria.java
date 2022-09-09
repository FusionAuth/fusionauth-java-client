/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static io.fusionauth.domain.util.SQLTools.normalizeOrderBy;
import static io.fusionauth.domain.util.SQLTools.toSearchString;

/**
 * Search criteria for Groups
 *
 * @author Daniel DeGroff
 */
public class GroupSearchCriteria extends BaseSearchCriteria {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public UUID id;

  public String name;

  public UUID tenantId;

  @Override
  public GroupSearchCriteria prepare() {
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = normalizeOrderBy(orderBy, SortableFields);
    name = toSearchString(name);
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
    SortableFields.put("id", "g.id");
    SortableFields.put("insertInstant", "g.insert_instant");
    SortableFields.put("name", "g.name");
    SortableFields.put("tenantId", "g.tenants_id");
  }
}
