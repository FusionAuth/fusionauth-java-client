/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.jwt;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Application;
import io.fusionauth.domain.Buildable;

/**
 * Models a JWT Refresh Token.
 *
 * @author Daniel DeGroff
 */
public class RefreshToken implements Buildable<RefreshToken> {

  @JsonIgnore
  public Application application;

  public UUID applicationId;

  /**
   * The time this token was created. The start time of this token may be prior to the insert instant when generating
   * refresh tokens for another application in a SSO scenario.
   */
  public ZonedDateTime insertInstant;

  public MetaData metaData = new MetaData();

  /**
   * The time at which the life started of this token. The start + ttl = expiration. The expiration should be calculated
   * using the start instant.
   */
  public ZonedDateTime startInstant;

  public String token;

  public UUID userId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RefreshToken that = (RefreshToken) o;
    return Objects.equals(applicationId, that.applicationId) &&
        Objects.equals(insertInstant, that.insertInstant) &&
        Objects.equals(metaData, that.metaData) &&
        Objects.equals(startInstant, that.startInstant) &&
        Objects.equals(token, that.token) &&
        Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId, insertInstant, metaData, startInstant, token, userId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class MetaData implements Buildable<MetaData> {
    public DeviceInfo device = new DeviceInfo();

    public Set<String> scopes;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MetaData)) {
        return false;
      }
      MetaData metaData = (MetaData) o;
      return Objects.equals(device, metaData.device) &&
          Objects.equals(scopes, metaData.scopes);
    }

    @Override
    public int hashCode() {
      return Objects.hash(device, scopes);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
