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

import java.net.URI;
import java.util.Objects;

import com.inversoft.json.ToString;

/**
 * A webhook call response.
 *
 * @author Spencer Witt
 */
public class WebhookCallResponse implements Buildable<WebhookCallResponse> {
  public String exception;

  public int statusCode;

  /**
   * The URI for the webhook endpoint or {@code null} if the event was sent to a Kafka topic.
   */
  public URI url;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebhookCallResponse that = (WebhookCallResponse) o;
    return statusCode == that.statusCode &&
           Objects.equals(exception, that.exception) &&
           Objects.equals(url, that.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exception, statusCode, url);
  }

  public String toString() {
    return ToString.toString(this);
  }
}
