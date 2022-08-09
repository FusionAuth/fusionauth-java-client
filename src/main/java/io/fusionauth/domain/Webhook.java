/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
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

  public String sslCertificate;

  public List<UUID> tenantIds = new ArrayList<>();

  public URI url;

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
           Objects.equals(data, webhook.data) &&
           Objects.equals(description, webhook.description) &&
           Objects.equals(eventsEnabled, webhook.eventsEnabled) &&
           Objects.equals(headers, webhook.headers) &&
           Objects.equals(httpAuthenticationPassword, webhook.httpAuthenticationPassword) &&
           Objects.equals(httpAuthenticationUsername, webhook.httpAuthenticationUsername) &&
           Objects.equals(id, webhook.id) &&
           Objects.equals(insertInstant, webhook.insertInstant) &&
           Objects.equals(lastUpdateInstant, webhook.lastUpdateInstant) &&
           Objects.equals(readTimeout, webhook.readTimeout) &&
           Objects.equals(sslCertificate, webhook.sslCertificate) &&
           Objects.equals(tenantIds, webhook.tenantIds) &&
           Objects.equals(url, webhook.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(connectTimeout, data, description, eventsEnabled, global, headers, httpAuthenticationPassword, httpAuthenticationUsername, id, insertInstant, lastUpdateInstant, readTimeout, sslCertificate, tenantIds, url);
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
