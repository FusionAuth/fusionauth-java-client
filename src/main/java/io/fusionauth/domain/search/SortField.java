/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.search;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class SortField {

  /**
   * Sets the value when a field is missing. Can also be set to <code>_last</code> or
   * <code>_first</code> to sort missing last or first respectively.
   */
  public String missing = "_last";

  /**
   * Field name.
   */
  public String name;

  /**
   * Sort ascending or descending;
   */
  public Sort order = Sort.asc;

  @JacksonConstructor
  public SortField() {
  }

  public SortField(String name) {
    this.name = name;
  }

  public SortField(String name, Sort order) {
    this.name = name;
    this.order = order;
  }

  public SortField(String name, Sort order, String missing) {
    this.name = name;
    this.order = order;
    this.missing = missing;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
