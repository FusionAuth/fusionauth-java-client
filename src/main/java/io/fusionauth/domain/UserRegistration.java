/*
 * Copyright (c) 2018-2023, FusionAuth, All Rights Reserved
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

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.util.Normalizer;
import static io.fusionauth.domain.util.Normalizer.trim;
import static io.fusionauth.domain.util.Normalizer.trimToNull;

/**
 * User registration information for a single application.
 *
 * @author Brian Pontarelli
 */
public class UserRegistration implements Buildable<UserRegistration> {
  public final Map<String, Object> data;

  public final List<Locale> preferredLanguages = new ArrayList<>();

  /**
   * @deprecated tokens are now stored in the Identity Provider Link. See the /api/identity-provider/link API.
   */
  @Deprecated
  public final Map<String, String> tokens;

  public UUID applicationId;

  public String authenticationToken;

  public UUID cleanSpeakId;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastLoginInstant;

  public ZonedDateTime lastUpdateInstant;

  public SortedSet<String> roles = new TreeSet<>();

  public ZoneId timezone;

  public String username;

  public ContentStatus usernameStatus;

  public boolean verified;

  public ZonedDateTime verifiedInstant;

  public UserRegistration() {
    this.data = new LinkedHashMap<>();
    this.tokens = new LinkedHashMap<>();
  }

  public UserRegistration(Map<String, Object> mockData) {
    this.data = mockData;
    this.tokens = new LinkedHashMap<>();
  }

  public UserRegistration(UserRegistration userRegistration) {
    this.applicationId = userRegistration.applicationId;
    this.authenticationToken = userRegistration.authenticationToken;
    this.cleanSpeakId = userRegistration.cleanSpeakId;
    this.id = userRegistration.id;
    this.insertInstant = userRegistration.insertInstant;
    this.lastLoginInstant = userRegistration.lastLoginInstant;
    this.lastUpdateInstant = userRegistration.lastUpdateInstant;
    this.preferredLanguages.addAll(userRegistration.preferredLanguages);
    this.roles.addAll(userRegistration.roles);
    this.timezone = userRegistration.timezone;
    this.username = userRegistration.username;
    this.usernameStatus = userRegistration.usernameStatus;
    this.verified = userRegistration.verified;
    this.verifiedInstant = userRegistration.verifiedInstant;

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
    return verified == that.verified &&
           Objects.equals(data, that.data) &&
           Objects.equals(preferredLanguages, that.preferredLanguages) &&
           Objects.equals(tokens, that.tokens) &&
           Objects.equals(applicationId, that.applicationId) &&
           Objects.equals(authenticationToken, that.authenticationToken) &&
           Objects.equals(cleanSpeakId, that.cleanSpeakId) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastLoginInstant, that.lastLoginInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(roles, that.roles) &&
           Objects.equals(timezone, that.timezone) &&
           Objects.equals(username, that.username) &&
           usernameStatus == that.usernameStatus &&
           Objects.equals(verifiedInstant, that.verifiedInstant);
  }

  /**
   * Return true if user registration data is provided.
   *
   * @return true if user data exists.
   */
  public boolean hasRegistrationData() {
    return !data.isEmpty();
  }

  @Override
  public int hashCode() {
    return Objects.hash(data,
                        preferredLanguages,
                        tokens,
                        applicationId,
                        authenticationToken,
                        cleanSpeakId,
                        id,
                        insertInstant,
                        lastLoginInstant,
                        lastUpdateInstant,
                        roles,
                        timezone,
                        username,
                        usernameStatus,
                        verified,
                        verifiedInstant);
  }

  public void normalize() {
    authenticationToken = trimToNull(authenticationToken);
    Normalizer.removeEmpty(preferredLanguages);
    Normalizer.deDuplicate(preferredLanguages);
    preferredLanguages.removeIf(l -> l.toString().equals(""));
    username = trim(username);
    Normalizer.removeEmpty(data);
  }

  public String toString() {
    return ToString.toString(this);
  }
}
