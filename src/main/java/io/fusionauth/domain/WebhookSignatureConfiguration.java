/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
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

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;

/**
 * Configuration for signing webhooks.
 *
 * @author Brent Halsey
 */
public class WebhookSignatureConfiguration extends Enableable implements Buildable<WebhookSignatureConfiguration> {
  public UUID signingKeyId;

  @JacksonConstructor
  public WebhookSignatureConfiguration() {
  }

  public WebhookSignatureConfiguration(WebhookSignatureConfiguration other) {
    this.enabled = other.enabled;
    this.signingKeyId = other.signingKeyId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WebhookSignatureConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    WebhookSignatureConfiguration other = (WebhookSignatureConfiguration) o;
    return Objects.equals(signingKeyId, other.signingKeyId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), signingKeyId);
  }
}
