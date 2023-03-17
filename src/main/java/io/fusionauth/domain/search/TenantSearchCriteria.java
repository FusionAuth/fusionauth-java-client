/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import io.fusionauth.domain.Buildable;
import static io.fusionauth.domain.util.SQLTools.normalizeOrderBy;
import static io.fusionauth.domain.util.SQLTools.toSearchString;

/**
 * Search criteria for Tenants
 *
 * @author Mark Manes
 */
public class TenantSearchCriteria extends BaseSearchCriteria implements Buildable<TenantSearchCriteria> {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public String name;

  @Override
  public TenantSearchCriteria prepare() {
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
    return "name ASC";
  }

  static {
    SortableFields.put("id", "t.id");
    SortableFields.put("insertInstant", "t.insert_instant");
    SortableFields.put("name", "t.name");
  }
}
