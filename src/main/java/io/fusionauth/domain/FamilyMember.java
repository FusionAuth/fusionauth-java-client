/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * Models a single family member.
 *
 * @author Brian Pontarelli
 */
public class FamilyMember implements Buildable<FamilyMember> {
  // This is used for InternalJSONColumn and we don't document any custom data. If we do end up documenting custom data, remove this annotation.
  @JsonInclude(Include.NON_EMPTY)
  public Map<String, Object> data = new HashMap<>();

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public boolean owner;

  public FamilyRole role;

  public UUID userId;

  @JacksonConstructor
  public FamilyMember() {
  }

  public FamilyMember(FamilyMember familyMember) {
    this.data.putAll(familyMember.data);
    this.insertInstant = familyMember.insertInstant;
    this.lastUpdateInstant = familyMember.lastUpdateInstant;
    this.owner = familyMember.owner;
    this.role = familyMember.role;
    this.userId = familyMember.userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FamilyMember)) {
      return false;
    }
    FamilyMember that = (FamilyMember) o;
    return Objects.equals(data, that.data) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           owner == that.owner &&
           role == that.role &&
           Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, insertInstant, lastUpdateInstant, owner, role, userId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public enum FamilyRole {
    Child,

    Teen,

    Adult
  }
}
