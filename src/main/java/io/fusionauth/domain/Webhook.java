/*
 * Copyright (c) 2018-2024, FusionAuth, All Rights Reserved
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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.event.EventType;
import io.fusionauth.domain.util.Normalizer;

/**
 * A server where events are sent. This includes user action events and any other events sent by FusionAuth.
 *
 * @author Brian Pontarelli
 */
public class Webhook implements Buildable<Webhook> {
  public Integer connectTimeout;

  public Map<String, Object> data = new LinkedHashMap<>();

  public String description;

  public Map<EventType, Boolean> eventsEnabled = new HashMap<>();

  public boolean global;

  public HTTPHeaders headers = new HTTPHeaders();

  public String httpAuthenticationPassword;

  public String httpAuthenticationUsername;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public Integer readTimeout;

  public WebhookSignatureConfiguration signatureConfiguration = new WebhookSignatureConfiguration();

  @Deprecated
  public String sslCertificate;

  public UUID sslCertificateKeyId;

  public List<UUID> tenantIds = new ArrayList<>();

  public URI url;

  @JacksonConstructor
  public Webhook() {
  }

  public Webhook(Webhook other) {
    this.connectTimeout = other.connectTimeout;
    this.data.putAll(other.data);
    this.description = other.description;
    this.eventsEnabled.putAll(other.eventsEnabled);
    this.global = other.global;
    this.headers.putAll(other.headers);
    this.httpAuthenticationPassword = other.httpAuthenticationPassword;
    this.httpAuthenticationUsername = other.httpAuthenticationUsername;
    this.id = other.id;
    this.insertInstant = other.insertInstant;
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.readTimeout = other.readTimeout;
    this.signatureConfiguration = new WebhookSignatureConfiguration(other.signatureConfiguration);
    this.sslCertificate = other.sslCertificate;
    this.sslCertificateKeyId = other.sslCertificateKeyId;
    this.tenantIds.addAll(other.tenantIds);
    this.url = other.url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Webhook)) {
      return false;
    }
    Webhook webhook = (Webhook) o;
    return global == webhook.global &&
           Objects.equals(connectTimeout, webhook.connectTimeout) &&
           Objects.equals(data, webhook.data) && Objects.equals(description, webhook.description) &&
           Objects.equals(eventsEnabled, webhook.eventsEnabled) && Objects.equals(headers, webhook.headers) &&
           Objects.equals(httpAuthenticationPassword, webhook.httpAuthenticationPassword) &&
           Objects.equals(httpAuthenticationUsername, webhook.httpAuthenticationUsername) &&
           Objects.equals(id, webhook.id) && Objects.equals(insertInstant, webhook.insertInstant) &&
           Objects.equals(lastUpdateInstant, webhook.lastUpdateInstant) &&
           Objects.equals(readTimeout, webhook.readTimeout) &&
           Objects.equals(signatureConfiguration, webhook.signatureConfiguration) &&
           Objects.equals(sslCertificate, webhook.sslCertificate) &&
           Objects.equals(sslCertificateKeyId, webhook.sslCertificateKeyId) &&
           Objects.equals(tenantIds, webhook.tenantIds) &&
           Objects.equals(url, webhook.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(connectTimeout, data, description, eventsEnabled, global, headers, httpAuthenticationPassword, httpAuthenticationUsername, id, insertInstant, lastUpdateInstant, readTimeout, signatureConfiguration, sslCertificate, sslCertificateKeyId, tenantIds, url);
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
}
