/*
 * Copyright (c) 2019-2023, FusionAuth, All Rights Reserved
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

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.EventLog;
import io.fusionauth.domain.search.SearchResults;

/**
 * Event log response.
 *
 * @author Brian Pontarelli
 */
public class EventLogSearchResponse {
  public List<EventLog> eventLogs;

  public long total;

  @JacksonConstructor
  public EventLogSearchResponse() {
  }

  public EventLogSearchResponse(SearchResults<EventLog> searchResults) {
    eventLogs = searchResults.results;
    total = searchResults.total;
  }
}
