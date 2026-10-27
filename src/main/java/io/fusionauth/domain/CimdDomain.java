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

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.oauth2.OAuthScopeConsentMode;
import io.fusionauth.domain.oauth2.ProvidedScopePolicy;

/**
 * A CIMD (Client ID Metadata Document) domain. Represents one trusted {@code client_id} domain (or the wildcard {@code *} for open mode)
 * within a tenant.
 */
public class CimdDomain implements Buildable<CimdDomain>, Tenantable {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public Boolean allowLocalhostRedirectUris = false;

  /**
   * Allowed RFC 8707 resource URIs for this domain.
   */
  public List<URI> authorizedResourceUris = new ArrayList<>();

  /**
   * User consent behavior for this domain's OAuth scopes.
   */
  public OAuthScopeConsentMode consentMode = OAuthScopeConsentMode.AlwaysPrompt;

  public boolean debug;

  /**
   * Normalized {@code scheme://host[:port]}, or the literal {@code *} for open mode.
   */
  public String domain;

  public int gracePeriodInSeconds = 0;

  public UUID id;

  public ZonedDateTime insertInstant;

  public UUID jwtPopulateLambdaId;

  public ZonedDateTime lastUpdateInstant;

  /**
   * The ceiling, in seconds, applied to the {@code Cache-Control: max-age} value returned by the client's metadata document. The
   * {@code Expires} header is not honored. Must be at least 900 seconds (15 minutes).
   */
  public int maxCacheTtlInSeconds = 86_400;

  public String name;

  public ProvidedScopePolicy providedScopePolicy = new ProvidedScopePolicy();

  public UUID registrationLambdaId;

  // Do not include the domainId
  @JsonIgnoreProperties("domainId")
  public List<CimdDomainOAuthScope> scopes = new ArrayList<>();

  public UUID tenantId;

  @JacksonConstructor
  public CimdDomain() {
  }

  public CimdDomain(CimdDomain other) {
    this.allowLocalhostRedirectUris = other.allowLocalhostRedirectUris;
    this.authorizedResourceUris.addAll(other.authorizedResourceUris);
    this.consentMode = other.consentMode;
    this.data.putAll(other.data);
    this.debug = other.debug;
    this.domain = other.domain;
    this.gracePeriodInSeconds = other.gracePeriodInSeconds;
    this.id = other.id;
    this.insertInstant = other.insertInstant;
    this.jwtPopulateLambdaId = other.jwtPopulateLambdaId;
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.maxCacheTtlInSeconds = other.maxCacheTtlInSeconds;
    this.name = other.name;
    this.providedScopePolicy = new ProvidedScopePolicy(other.providedScopePolicy);
    this.registrationLambdaId = other.registrationLambdaId;
    other.scopes.forEach(scope -> this.scopes.add(new CimdDomainOAuthScope(scope)));
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
    CimdDomain that = (CimdDomain) o;
    return debug == that.debug &&
           Objects.equals(allowLocalhostRedirectUris, that.allowLocalhostRedirectUris) &&
           gracePeriodInSeconds == that.gracePeriodInSeconds &&
           maxCacheTtlInSeconds == that.maxCacheTtlInSeconds &&
           Objects.equals(authorizedResourceUris, that.authorizedResourceUris) &&
           consentMode == that.consentMode &&
           Objects.equals(data, that.data) &&
           Objects.equals(domain, that.domain) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(jwtPopulateLambdaId, that.jwtPopulateLambdaId) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(name, that.name) &&
           Objects.equals(registrationLambdaId, that.registrationLambdaId) &&
           Objects.equals(providedScopePolicy, that.providedScopePolicy) &&
           Objects.equals(scopes, that.scopes) &&
           Objects.equals(tenantId, that.tenantId);
  }

  @Override
  public UUID getTenantId() {
    return tenantId;
  }

  public void setTenantId(UUID tenantId) {
    this.tenantId = tenantId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(allowLocalhostRedirectUris, authorizedResourceUris, consentMode, data, debug, domain, gracePeriodInSeconds, id, insertInstant,
                        jwtPopulateLambdaId, lastUpdateInstant, maxCacheTtlInSeconds, name, registrationLambdaId, providedScopePolicy, scopes, tenantId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
