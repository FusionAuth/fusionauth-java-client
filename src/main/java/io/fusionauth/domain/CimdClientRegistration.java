/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
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

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * A CIMD (Client ID Metadata Document) client registration. Represents one concrete, fetched {@code client_id} URL registered against
 * a {@link CimdDomain}.
 */
public class CimdClientRegistration implements Buildable<CimdClientRegistration>, Tenantable {
  public final Map<String, Object> data = new LinkedHashMap<>();

  /**
   * When the cached metadata document expires. Once past this instant (and while within {@link #graceExpirationInstant}), the
   * registration is in the {@code Grace} state.
   */
  public ZonedDateTime cacheExpirationInstant;

  public UUID cimdDomainId;

  public String clientIdUrl;

  public String clientName;

  public String clientUri;

  /**
   * Once past this instant, the registration is {@code Expired} and eligible for reaping.
   */
  public ZonedDateTime graceExpirationInstant;

  public UUID id;

  public ZonedDateTime insertInstant;

  public UUID jwtPopulateLambdaId;

  public ZonedDateTime lastUpdateInstant;

  /**
   * The raw, validated CIMD document body, stored so it can be re-used when a new {@code resource} is requested for this registration
   * without a second fetch.
   */
 @JsonIgnore
  public String metadataDocument;

  /**
   * The registration lambda's cached decision for each {@code resource} this client has been seen with, keyed by {@code resource}.
   */
  public Map<String, CimdClientRegistrationResourceOverride> overridesByResource = new LinkedHashMap<>();

  public List<String> redirectUris = new ArrayList<>();

  public UUID tenantId;

  @JacksonConstructor
  public CimdClientRegistration() {
  }

  public CimdClientRegistration(CimdClientRegistration other) {
    this.cacheExpirationInstant = other.cacheExpirationInstant;
    this.cimdDomainId = other.cimdDomainId;
    this.clientIdUrl = other.clientIdUrl;
    this.clientName = other.clientName;
    this.clientUri = other.clientUri;
    this.data.putAll(other.data);
    this.graceExpirationInstant = other.graceExpirationInstant;
    this.id = other.id;
    this.insertInstant = other.insertInstant;
    this.jwtPopulateLambdaId = other.jwtPopulateLambdaId;
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.metadataDocument = other.metadataDocument;
    other.overridesByResource.forEach((resource, override) -> this.overridesByResource.put(resource,
                                                                                           override == null ? null : new CimdClientRegistrationResourceOverride(override)));
    this.redirectUris.addAll(other.redirectUris);
    this.tenantId = other.tenantId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CimdClientRegistration that = (CimdClientRegistration) o;
    return Objects.equals(cacheExpirationInstant, that.cacheExpirationInstant) &&
           Objects.equals(cimdDomainId, that.cimdDomainId) &&
           Objects.equals(clientIdUrl, that.clientIdUrl) &&
           Objects.equals(clientName, that.clientName) &&
           Objects.equals(clientUri, that.clientUri) &&
           Objects.equals(data, that.data) &&
           Objects.equals(graceExpirationInstant, that.graceExpirationInstant) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(jwtPopulateLambdaId, that.jwtPopulateLambdaId) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(metadataDocument, that.metadataDocument) &&
           Objects.equals(overridesByResource, that.overridesByResource) &&
           Objects.equals(redirectUris, that.redirectUris) &&
           Objects.equals(tenantId, that.tenantId);
  }

  /**
   * @return The computed state (Active/Grace/Expired). This is never persisted; it is derived from {@link #cacheExpirationInstant} and
   * {@link #graceExpirationInstant} compared against the current instant.
   */
  public CimdClientRegistrationState getState() {
    ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
    if (cacheExpirationInstant != null && now.isAfter(cacheExpirationInstant)) {
      if (graceExpirationInstant != null && now.isAfter(graceExpirationInstant)) {
        return CimdClientRegistrationState.Expired;
      }
      return CimdClientRegistrationState.Grace;
    }

    return CimdClientRegistrationState.Active;
  }

  @Override
  public UUID getTenantId() {
    return tenantId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(cacheExpirationInstant, cimdDomainId, clientIdUrl, clientName, clientUri, data,
                        graceExpirationInstant, id, insertInstant, jwtPopulateLambdaId, lastUpdateInstant, metadataDocument,
                        overridesByResource, redirectUris, tenantId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
