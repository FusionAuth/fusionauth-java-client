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
package io.fusionauth.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.inversoft.json.ToString;
import io.fusionauth.domain.event.EventType;
import io.fusionauth.domain.util.Normalizer;

/**
 * A server where events are sent. This includes user action events and any other events sent by FusionAuth.
 *
 * @author Brian Pontarelli
 */
public class Webhook implements Buildable<Webhook> {
  public List<UUID> applicationIds = new ArrayList<>();

  public Integer connectTimeout;

  @JsonUnwrapped
  public WebhookData data = new WebhookData();

  public String description;

  public boolean global;

  public HTTPHeaders headers = new HTTPHeaders();

  public String httpAuthenticationPassword;

  public String httpAuthenticationUsername;

  public UUID id;

  public Integer readTimeout;

  public String sslCertificate;

  public URI url;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Webhook)) {
      return false;
    }
    Webhook that = (Webhook) o;
    applicationIds.sort(UUID::compareTo);
    that.applicationIds.sort(UUID::compareTo);
    return Objects.equals(global, that.global) &&
        Objects.equals(applicationIds, that.applicationIds) &&
        Objects.equals(connectTimeout, that.connectTimeout) &&
        Objects.equals(data, that.data) &&
        Objects.equals(description, that.description) &&
        Objects.equals(headers, that.headers) &&
        Objects.equals(httpAuthenticationPassword, that.httpAuthenticationPassword) &&
        Objects.equals(httpAuthenticationUsername, that.httpAuthenticationUsername) &&
        Objects.equals(readTimeout, that.readTimeout) &&
        Objects.equals(sslCertificate, that.sslCertificate) &&
        Objects.equals(url, that.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationIds, connectTimeout, description, global, headers, httpAuthenticationPassword, httpAuthenticationUsername,
                        readTimeout, sslCertificate, url);
  }

  public void normalize() {
    headers.normalize();
    httpAuthenticationPassword = Normalizer.trim(httpAuthenticationPassword);
    httpAuthenticationUsername = Normalizer.trim(httpAuthenticationUsername);
    sslCertificate = Normalizer.trim(sslCertificate);
  }

  public String toString() {
    return ToString.toString(this);
  }

  public static class WebhookData {
    public Map<EventType, Boolean> eventsEnabled = new HashMap<>();

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof WebhookData)) {
        return false;
      }
      WebhookData that = (WebhookData) o;
      return Objects.equals(eventsEnabled, that.eventsEnabled);
    }

    @Override
    public int hashCode() {
      return Objects.hash(eventsEnabled);
    }
  }
}
