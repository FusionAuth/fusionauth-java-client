/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import static io.fusionauth.domain.util.SQLTools.normalizeOrderBy;
import static io.fusionauth.domain.util.SQLTools.toSearchString;

/**
 * Search criteria for webhooks.
 *
 * @author Spencer Witt
 */
public class WebhookSearchCriteria extends BaseSearchCriteria {
  public static final Map<String, String> SortableFields = new LinkedHashMap<>();

  public String description;

  public UUID tenantId;

  @JacksonConstructor
  public WebhookSearchCriteria() {
  }

  @Override
  public WebhookSearchCriteria prepare() {
    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = normalizeOrderBy(orderBy, SortableFields);
    description = toSearchString(description);
    return this;
  }

  @Override
  public Set<String> supportedOrderByColumns() {
    return SortableFields.keySet();
  }

  @Override
  protected String defaultOrderBy() {
    return "insertInstant DESC, id DESC";
  }

  static {
    SortableFields.put("id", "w.id");
    SortableFields.put("insertInstant", "w.insert_instant");
    SortableFields.put("description", "w.description");
    SortableFields.put("url", "w.url");
  }
}
