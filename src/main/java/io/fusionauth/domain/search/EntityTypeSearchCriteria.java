/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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
 * Search criteria for entity types.
 *
 * @author Brian Pontarelli
 */
public class EntityTypeSearchCriteria extends BaseSearchCriteria {
  public String name;

  @Override
  public void prepare() {
    secure();

    if (orderBy == null) {
      orderBy = defaultOrderBy();
    }

    orderBy = orderBy.replace("insertInstant", "insert_instant")
                     .replace("lastUpdateInstant", "last_update_instant");

    name = toSearchString(name);
  }

  @Override
  protected String defaultOrderBy() {
    return "name ASC";
  }
}
