/*
 * Copyright (c) 2021-2023, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.fusionauth.domain.Entity;

/**
 * Search request for entities
 *
 * @author Brett Guy
 */
public class EntitySearchResponse {
  public List<Entity> entities;

  public String nextResults;

  public long total;

  // When this is 'true', it indicates the total returned may not be the actual number of entities matching the query.
  // - The search index will cap the total hit count to limit query performance, once this limit is reached the count no longer increments.
  // - To request an accurate total, see 'accurateTotal' on the API request.
  // Not returning this on the API for now, Elasticsearch does not seem to provide a reliable answer, it is simply and indicator.
  @JsonIgnore
  public boolean totalEqualToActual;

  public EntitySearchResponse() {
  }

  public EntitySearchResponse(List<Entity> entities, long total) {
    this.entities = entities;
    this.total = total;
  }
}
