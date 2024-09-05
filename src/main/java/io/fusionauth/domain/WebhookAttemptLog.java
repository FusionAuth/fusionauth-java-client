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

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.ToString;

/**
 * A webhook call attempt log.
 *
 * @author Spencer Witt
 */
public class WebhookAttemptLog implements Buildable<WebhookAttemptLog>, Comparable<WebhookAttemptLog> {
  public Map<String, Object> data = new LinkedHashMap<>();

  public ZonedDateTime endInstant;

  public UUID id;

  public ZonedDateTime startInstant;

  public WebhookCallResponse webhookCallResponse;

  public UUID webhookEventLogId;

  /**
   * The webhook Id for this attempt or {@code null} if it was sent to a Kafka topic.
   */
  public UUID webhookId;

  @Override
  public int compareTo(WebhookAttemptLog o) {
    // Sort by startInstant and then Id
    return startInstant.compareTo(o.startInstant) != 0 ? startInstant.compareTo(o.startInstant) : id.compareTo(o.id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebhookAttemptLog that = (WebhookAttemptLog) o;
    return Objects.equals(data, that.data) &&
           Objects.equals(endInstant, that.endInstant) &&
           Objects.equals(id, that.id) &&
           Objects.equals(startInstant, that.startInstant) &&
           Objects.equals(webhookCallResponse, that.webhookCallResponse) &&
           Objects.equals(webhookEventLogId, that.webhookEventLogId) &&
           Objects.equals(webhookId, that.webhookId);
  }

  public WebhookAttemptResult getAttemptResult() {
    if (webhookCallResponse != null) {
      return webhookCallResponse.statusCode >= 200 && webhookCallResponse.statusCode <= 299 ? WebhookAttemptResult.Success : WebhookAttemptResult.Failure;
    }
    return WebhookAttemptResult.Unknown;
  }

  @JsonIgnore
  public long getDuration() {
    return Duration.between(startInstant, endInstant).toMillis();
  }

  @JsonIgnore
  // using Url instead of URL so that we can access this as a property named .url
  public String getUrl() {
    return webhookCallResponse != null && webhookCallResponse.url != null ? webhookCallResponse.url.toString() : null;
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, endInstant, id, startInstant, webhookCallResponse, webhookEventLogId, webhookId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
