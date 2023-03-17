/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
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
  }

  static {
    SortableFields.put("id", "k.id");
    SortableFields.put("name", "k.name");
    SortableFields.put("type", "k.type");
    SortableFields.put("algorithm", "k.algorithm");
    SortableFields.put("expiration", "k.expiration_instant");
    SortableFields.put("insertInstant", "k.insert_instant");
  }
}
