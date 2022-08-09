/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
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
 * Models an entity type that has a specific set of permissions. These are global objects and can be used across tenants.
 *
 * @author Brian Pontarelli
 */
public class EntityType implements Buildable<EntityType> {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public UUID id;

  public ZonedDateTime insertInstant;

  
  public EntityJWTConfiguration jwtConfiguration = new EntityJWTConfiguration();

  public ZonedDateTime lastUpdateInstant;

  public String name;

  public List<EntityTypePermission> permissions = new ArrayList<>();

  @JacksonConstructor
  public EntityType() {
  }

  public EntityType(String name) {
    this.name = name;
  }

  public EntityType(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EntityType)) {
      return false;
    }
    EntityType group = (EntityType) o;
    return Objects.equals(data, group.data) &&
           Objects.equals(id, group.id) &&
           Objects.equals(insertInstant, group.insertInstant) &&
           Objects.equals(lastUpdateInstant, group.lastUpdateInstant) &&
           Objects.equals(name, group.name) &&
           Objects.equals(permissions, group.permissions);
  }

  public EntityTypePermission getPermission(String name) {
    for (EntityTypePermission permission : permissions) {
      if (permission.name.equals(name)) {
        return permission;
      }
    }

    return null;
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, id, insertInstant, lastUpdateInstant, name, permissions);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  /**
   * JWT Configuration for entities.
   */
  public static class EntityJWTConfiguration extends Enableable implements Buildable<EntityJWTConfiguration> {
    /**
     * The signing key used to sign the access token
     */
    
    public UUID accessTokenKeyId;

    /**
     * The length of time in seconds this JWT is valid from the time it was issued. This should be a non-zero value.
     */
    public int timeToLiveInSeconds;
  }
}
