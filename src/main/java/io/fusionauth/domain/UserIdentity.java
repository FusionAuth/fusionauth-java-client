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

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import static io.fusionauth.domain.util.Normalizer.toLowerCase;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * @author Daniel DeGroff
 */
// jackson
public class UserIdentity implements Buildable<UserIdentity> {
  // we don't need DB generated IDs in API responses
  @JsonIgnore
  public long id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastLoginInstant;

  public ZonedDateTime lastUpdateInstant;

  // TODO : ENG-1 : Daniel : This is the old "public ContentStatus usernameStatus = ContentStatus.ACTIVE;" value from SecureIdentity.
  //                         I think this still has value because we can filter and moderate usernames. But perhaps only valuable for that type?
  //                         So it seems like this should be nullable, and perhaps renamed to moderationStatus, contentStatus? Not sure
  //                         we want to limit it to usernames, although that would be the primary use, and perhaps the only thing that is supported
  //                         through the CleanSpeak integration.
  public ContentStatus moderationStatus;

  // TODO : ENG-1800 : We should probably leave this set to false, but for now enabling to simplify "look up enabled" checks.
  public boolean primary = true;

  // TODO : ENG-1787 : Brady if you think we need this, we need a public object.
  //                This is a internal only object and is not accessible to fusionauth-java-client.
  //                Anything in io.fusionauth.api.domain is private and not accessible via fusionauth-java-client.
//  public UserIdentityStatus status;

  // TODO : ENG-1 : Daniel : Assuming this is being returned on the User object so this is redundant. But it is in the schema, so we could either not
  //                         retrieve it.. but we may want it if we create an Identity API which I think we will need.
  @JsonIgnore
  public UUID tenantId;

  public IdentityType type;

  public String uniqueValue;

  // TODO : ENG-1 : Daniel : Assuming this is being returned on the User object so this is redundant. But it is in the schema, so we could either not
  //                         retrieve it.. but we may want it if we create an Identity API which I think we will need.
  @JsonIgnore
  public UUID userId;

  // When unique usernames are enabled, this value may be different than 'uniqueUsername' and in that case will represent the base username the user selected.
  public String value;

  public boolean verified;

  public ZonedDateTime verifiedInstant;

  public UserIdentity(UserIdentity other) {
    this.id = other.id;
    this.insertInstant = other.insertInstant;
    this.lastLoginInstant = other.lastLoginInstant;
    this.lastUpdateInstant = other.lastUpdateInstant;
    this.moderationStatus = other.moderationStatus;
    this.primary = other.primary;
    this.type = new IdentityType(other.type);
    this.tenantId = other.tenantId;
    this.uniqueValue = other.uniqueValue;
    this.userId = other.userId;
    this.value = other.value;
    this.verified = other.verified;
    this.verifiedInstant = other.verifiedInstant;
  }

  @JacksonConstructor
  public UserIdentity() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserIdentity that = (UserIdentity) o;
    return id == that.id && primary == that.primary && verified == that.verified && Objects.equals(insertInstant, that.insertInstant) && Objects.equals(lastLoginInstant, that.lastLoginInstant) && Objects.equals(lastUpdateInstant, that.lastUpdateInstant) && moderationStatus == that.moderationStatus && Objects.equals(tenantId, that.tenantId) && Objects.equals(type, that.type) && Objects.equals(uniqueValue, that.uniqueValue) && Objects.equals(userId, that.userId) && Objects.equals(value, that.value) && Objects.equals(verifiedInstant, that.verifiedInstant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, insertInstant, lastLoginInstant, lastUpdateInstant, moderationStatus, primary, tenantId, type, uniqueValue, userId, value, verified, verifiedInstant);
  }

  public void normalize() {
    // TODO : ENG-1 : Daniel : Should we move this to the service? This is here for backwards compatibility, or parity with prior behavior.
    value = trim(value);
    if (type == IdentityType.email) {
      value = toLowerCase(value);
    } else if (type == IdentityType.username) {
      if (value != null && value.length() == 0) {
        value = null;
      }
    } else if (type == IdentityType.phoneNumber) {
      // TODO : ENG-1 : I don't think I want to normalize this because this should fail validation instead.
//      value = PhoneNumberTools.safeToE164format(value);
    }
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
