/*
 * Copyright (c) 2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inversoft.json.ToString;
import io.fusionauth.domain.event.EventRequest;
import io.fusionauth.domain.event.EventType;

/**
 * An instance of a webhook event log.
 *
 * @author Spencer Witt
 */

@JsonIgnoreProperties(value = {"successfulAttempts", "failedAttempts"}, allowGetters = true)
public class WebhookEventLog implements Buildable<WebhookEventLog> {
  // Do not include the webhook event log Id for individual attempts when returning as part of the full event
  @JsonIgnoreProperties("webhookEventLogId")
  public List<WebhookAttemptLog> attempts = new ArrayList<>();

  public Map<String, Object> data = new LinkedHashMap<>();

  public EventRequest event;

  public WebhookEventResult eventResult = WebhookEventResult.Running;

  public EventType eventType;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastAttemptInstant;

  public ZonedDateTime lastUpdateInstant;

  public UUID linkedObjectId;

  public Long sequence;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebhookEventLog that = (WebhookEventLog) o;
    return Objects.equals(attempts, that.attempts) &&
           Objects.equals(data, that.data) &&
           Objects.equals(event, that.event) &&
           eventResult == that.eventResult &&
           eventType == that.eventType &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastAttemptInstant, that.lastAttemptInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(linkedObjectId, that.linkedObjectId) &&
           Objects.equals(sequence, that.sequence);
  }

  public Integer getFailedAttempts() {
    return Math.toIntExact(attempts.stream()
                                   .filter(attempt -> attempt.getAttemptResult().equals(WebhookAttemptResult.Failure))
                                   .count());
  }

  public Integer getSuccessfulAttempts() {
    return Math.toIntExact(attempts.stream()
                                   .filter(attempt -> attempt.getAttemptResult().equals(WebhookAttemptResult.Success))
                                   .count());
  }

  @Override
  public int hashCode() {
    return Objects.hash(attempts, data, event, eventResult, eventType, id, insertInstant, lastAttemptInstant, lastUpdateInstant, linkedObjectId, sequence);
  }

  public String toString() {
    return ToString.toString(this);
  }
}
