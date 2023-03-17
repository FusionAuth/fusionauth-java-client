/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
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

    // Since we're ORing the query parameters together, we don't want toSearchString() to convert an empty string to "%%", or it will match all rows.
    // Instead, set it to null to omit the condition completely.
    name = "".equals(name) ? null : toSearchString(name);
    body = "".equals(body) ? null : toSearchString(body);

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
