/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;



/**
 * Models a User consent.
 *
 * @author Daniel DeGroff
 */
public class UserConsent implements Buildable<UserConsent> {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public Consent consent;

  public UUID consentId;

  public UUID giverUserId;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  
  public ConsentStatus status;

  public UUID userId;

  
  public List<String> values = new ArrayList<>();

  public UserConsent(UserConsent userConsent) {
    this.data.putAll(userConsent.data);
    this.consent = userConsent.consent;
    this.consentId = userConsent.consentId;
    this.giverUserId = userConsent.giverUserId;
    this.id = userConsent.id;
    this.insertInstant = userConsent.insertInstant;
    this.lastUpdateInstant = userConsent.lastUpdateInstant;
    this.status = userConsent.status;
    this.userId = userConsent.userId;
    if (userConsent.values != null) {
      this.values = userConsent.values;
    }
  }

  @JacksonConstructor
  public UserConsent() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserConsent)) {
      return false;
    }
    UserConsent userConsent = (UserConsent) o;
    return Objects.equals(consent, userConsent.consent) &&
           Objects.equals(consentId, userConsent.consentId) &&
           Objects.equals(data, userConsent.data) &&
           Objects.equals(giverUserId, userConsent.giverUserId) &&
           Objects.equals(id, userConsent.id) &&
           Objects.equals(insertInstant, userConsent.insertInstant) &&
           Objects.equals(lastUpdateInstant, userConsent.lastUpdateInstant) &&
           status == userConsent.status &&
           Objects.equals(userId, userConsent.userId) &&
           Objects.equals(values, userConsent.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(consent, consentId, data, giverUserId, id, insertInstant, lastUpdateInstant, status, userId, values);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
