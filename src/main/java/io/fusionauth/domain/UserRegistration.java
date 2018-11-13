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

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.util.Normalizer;
import static io.fusionauth.domain.util.Normalizer.trim;
import static io.fusionauth.domain.util.Normalizer.trimToNull;

/**
 * User registration information for a single application.
 *
 * @author Brian Pontarelli
 */
public class UserRegistration implements Buildable<UserRegistration>, _InternalJSONColumn {
  public final Map<String, Object> data;

  @InternalJSONColumn
  public final List<Locale> preferredLanguages = new ArrayList<>();

  @InternalJSONColumn
  public final Map<String, String> tokens;

  @JsonIgnore
  public Application application;

  public UUID applicationId;

  public String authenticationToken;

  public UUID cleanSpeakId;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastLoginInstant;

  public SortedSet<String> roles = new TreeSet<>();

  public String timezone;

  @JsonIgnore
  public UUID userId;

  public String username;

  public ContentStatus usernameStatus;

  public boolean verified;

  public UserRegistration() {
    tokens = new LinkedHashMap<>();
    this.data = new LinkedHashMap<>();
  }

  public UserRegistration(UUID id, UUID applicationId, UUID userId, ZonedDateTime lastLoginInstant, String username,
                          ContentStatus usernameStatus, UUID cleanSpeakId, Map<String, Object> data,
                          List<Locale> preferredLanguages, String... roles) {
    this.id = id;
    this.applicationId = applicationId;
    this.userId = userId;
    this.cleanSpeakId = cleanSpeakId;
    this.lastLoginInstant = lastLoginInstant;
    this.tokens = new LinkedHashMap<>();
    this.username = username;
    this.usernameStatus = usernameStatus;
    this.verified = true;
    this.preferredLanguages.addAll(preferredLanguages);

    this.data = new LinkedHashMap<>();
    if (data != null) {
      this.data.putAll(data);
    }

    Collections.addAll(this.roles, roles);
  }

  public UserRegistration(UserRegistration userRegistration) {
    this.application = userRegistration.application;
    this.applicationId = userRegistration.applicationId;
    this.authenticationToken = userRegistration.authenticationToken;
    this.cleanSpeakId = userRegistration.cleanSpeakId;
    this.id = userRegistration.id;
    this.insertInstant = userRegistration.insertInstant;
    this.lastLoginInstant = userRegistration.lastLoginInstant;
    this.preferredLanguages.addAll(userRegistration.preferredLanguages);
    this.roles.addAll(userRegistration.roles);
    this.timezone = userRegistration.timezone;
    this.userId = userRegistration.userId;
    this.username = userRegistration.username;
    this.usernameStatus = userRegistration.usernameStatus;
    this.verified = userRegistration.verified;

    this.data = new LinkedHashMap<>();
    if (userRegistration.data != null) {
      this.data.putAll(userRegistration.data);
    }

    this.tokens = new LinkedHashMap<>();
    if (userRegistration.tokens != null) {
      this.tokens.putAll(userRegistration.tokens);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserRegistration)) {
      return false;
    }
    UserRegistration that = (UserRegistration) o;
    return Objects.equals(applicationId, that.applicationId) &&
        Objects.equals(authenticationToken, that.authenticationToken) &&
        Objects.equals(cleanSpeakId, that.cleanSpeakId) &&
        Objects.equals(data, that.data) &&
        Objects.equals(insertInstant, that.insertInstant) &&
        Objects.equals(lastLoginInstant, that.lastLoginInstant) &&
        Objects.equals(roles, that.roles) &&
        Objects.equals(tokens, that.tokens) &&
        Objects.equals(userId, that.userId) &&
        Objects.equals(username, that.username) &&
        Objects.equals(usernameStatus, that.usernameStatus) &&
        Objects.equals(verified, that.verified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId, authenticationToken, cleanSpeakId, data, insertInstant, lastLoginInstant, roles, tokens, userId, username, usernameStatus);
  }

  public void normalize() {
    authenticationToken = trimToNull(authenticationToken);
    preferredLanguages.removeIf(Objects::isNull);
    username = trim(username);
    Normalizer.removeEmpty(data);
  }

  public String toString() {
    return ToString.toString(this);
  }
}
