/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
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

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class LoginRecordSearchCriteria extends BaseSearchCriteria implements Buildable<LoginRecordSearchCriteria> {
  public UUID applicationId;

  public ZonedDateTime end;

  public ZonedDateTime start;

  public UUID userId;

  @JacksonConstructor
  public LoginRecordSearchCriteria() {
    orderBy = null;
  }

  public LoginRecordSearchCriteria(UUID userId, UUID applicationId, ZonedDateTime start, ZonedDateTime end) {
    this.userId = userId;
    this.applicationId = applicationId;
    this.start = start;
    this.end = end;
    orderBy = null;
  }

  public LoginRecordSearchCriteria(UUID userId, UUID applicationId, ZonedDateTime start, ZonedDateTime end, int startRow,
                                   int numberOfResults) {
    this.userId = userId;
    this.applicationId = applicationId;
    this.start = start;
    this.end = end;
    this.startRow = startRow;
    this.numberOfResults = numberOfResults;
    orderBy = null;
  }

  @Override
  public LoginRecordSearchCriteria prepare() {
    orderBy = null;
    return this;
  }

  @Override
  public Set<String> supportedOrderByColumns() {
    // Currently, no sorting is allowed using this Search API.
    return Collections.emptySet();
  }
}
