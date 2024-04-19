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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import static io.fusionauth.domain.util.Normalizer.trim;
import static io.fusionauth.domain.util.Normalizer.trimToNull;

/**
 * A custom OAuth scope for a specific application.
 *
 * @author Spencer Witt
 */
public class ApplicationOAuthScope implements Comparable<ApplicationOAuthScope>, Buildable<ApplicationOAuthScope> {
  public UUID applicationId;

  public Map<String, Object> data = new LinkedHashMap<>();

  /**
   * Default detail to display when prompting for consent.
   */
  public String defaultConsentDetail;

  /**
   * Default message to display when prompting for consent.
   */
  public String defaultConsentMessage;

  /**
   * Scope description for internal/admin use.
   */
  public String description;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  /**
   * The name of the OAuth scope. This value will be used in OAuth workflows to request scopes and in tokens to indicate consented scopes.
   */
  public String name;

  /**
   * Whether the scope is required. Users must consent to required scopes to complete the OAuth workflow.
   */
  public boolean required;

  @JacksonConstructor
  public ApplicationOAuthScope() {
  }

  public ApplicationOAuthScope(UUID id, UUID applicationId, String name) {
    this.id = id;
    this.applicationId = applicationId;
    this.name = name;
  }

  public ApplicationOAuthScope(String name) {
    this(null, null, name);
  }

  public ApplicationOAuthScope(ApplicationOAuthScope other) {
    this.applicationId = other.applicationId;
    this.data.putAll(other.data);
    this.defaultConsentDetail = other.defaultConsentDetail;
    this.defaultConsentMessage = other.defaultConsentMessage;
    this.description = other.description;
    this.id = other.id;
    this.insertInstant = other.insertInstant;
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.name = other.name;
    this.required = other.required;
  }

  @Override
  public int compareTo(ApplicationOAuthScope o) {
    return name.compareTo(o.name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationOAuthScope that = (ApplicationOAuthScope) o;
    return required == that.required &&
           Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(data, that.data) &&
           Objects.equals(defaultConsentDetail, that.defaultConsentDetail) &&
           Objects.equals(defaultConsentMessage, that.defaultConsentMessage) &&
           Objects.equals(description, that.description) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId, data, defaultConsentDetail, defaultConsentMessage, description, id, insertInstant, lastUpdateInstant, name, required);
  }

  public void normalize() {
    defaultConsentDetail = trimToNull(defaultConsentDetail);
    defaultConsentMessage = trimToNull(defaultConsentMessage);
    description = trimToNull(description);
    name = trim(name);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
