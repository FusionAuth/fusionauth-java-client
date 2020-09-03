/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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

/**
 * @author Brian Pontarelli
 */
public abstract class BaseSearchCriteria {
  public int numberOfResults = 25;

  public String orderBy;

  public int startRow;

  protected static String toSearchString(String str) {
    if (str == null) {
      return null;
    }

    if (str.contains("*")) {
      return str.trim().toLowerCase().replace("*", "%");
    } else {
      return "%" + str.trim().toLowerCase() + "%";
    }
  }

  public abstract void prepare();

  protected String defaultOrderBy() {
    return null;
  }

  protected void secure() {
    if (orderBy != null && !orderBy.matches("[a-zA-Z_0-9.]+\\s+[adescADESC]+")) {
      orderBy = defaultOrderBy();
    }
  }
}
