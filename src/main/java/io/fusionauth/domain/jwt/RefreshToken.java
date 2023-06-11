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
package io.fusionauth.domain.jwt;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;


import io.fusionauth.domain.Application;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.JWTConfiguration;
import io.fusionauth.domain.RefreshTokenExpirationPolicy;
import io.fusionauth.domain.Tenant;

/**
 * Models a JWT Refresh Token.
 *
 * @author Daniel DeGroff
 */
public class RefreshToken implements Buildable<RefreshToken> {
  public UUID applicationId;

  public Map<String, Object> data = new LinkedHashMap<>();

  public UUID id;

  /**
   * The time this token was created. The start time of this token may be prior to the insert instant when generating
   * refresh tokens for another application in an SSO scenario.
   */
  public ZonedDateTime insertInstant;

  
  public MetaData metaData = new MetaData();

  /**
   * The time at which the life started of this token. The start + ttl = expiration. The expiration should be calculated
   * using the start instant.
   * <p>
   * When using a sliding window expiration policy, this value gets reset each time the token is used.
   */
  public ZonedDateTime startInstant;

  // Only used for SSO tokens
  public UUID tenantId;

  public String token;

  public UUID userId;

  @JacksonConstructor
  public RefreshToken() {
  }

  public RefreshToken(RefreshToken other) {
    this.applicationId = other.applicationId;
    if (other.data != null) {
      this.data.putAll(other.data);
    }
    this.id = other.id;
    this.insertInstant = other.insertInstant;
    this.metaData = new MetaData(other.metaData);
    this.startInstant = other.startInstant;
    this.tenantId = other.tenantId;
    this.token = other.token;
    this.userId = other.userId;
  }

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
           Objects.equals(data, that.data) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(metaData, that.metaData) &&
           Objects.equals(startInstant, that.startInstant) &&
           Objects.equals(tenantId, that.tenantId) &&
           Objects.equals(token, that.token) &&
           Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId, data, id, insertInstant, metaData, startInstant, tenantId, token, userId);
  }

  @JsonIgnore
  public boolean isExpired(Tenant tenant, Application application) {
    ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
    JWTConfiguration jwtConfiguration = tenant.lookupJWTConfiguration(application);

    // if the rt expired, we're done here.
    if (startInstant.plusMinutes(jwtConfiguration.refreshTokenTimeToLiveInMinutes).isBefore(now)) {
      return true;
    }

    // The token is not expired, but it may have reached the maximum TTL when configured.
    return jwtConfiguration.refreshTokenExpirationPolicy == RefreshTokenExpirationPolicy.SlidingWindowWithMaximumLifetime
           && insertInstant.plusMinutes(jwtConfiguration.refreshTokenSlidingWindowConfiguration.maximumTimeToLiveInMinutes).isBefore(now);
  }

  public RefreshToken secure() {
    data = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class MetaData implements Buildable<MetaData> {
    public Map<String, Object> data;

    public DeviceInfo device = new DeviceInfo();

    @JsonDeserialize(as = LinkedHashSet.class)
    public Set<String> scopes;

    @JacksonConstructor
    public MetaData() {
    }

    public MetaData(MetaData other) {
      if (other.data != null) {
        this.data = new LinkedHashMap<>(other.data);
      }
      this.device = new DeviceInfo(other.device);
      if (other.scopes != null) {
        this.scopes = new LinkedHashSet<>(other.scopes);
      }
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MetaData)) {
        return false;
      }
      MetaData metaData = (MetaData) o;
      return Objects.equals(data, metaData.data) &&
             Objects.equals(device, metaData.device) &&
             Objects.equals(scopes, metaData.scopes);
    }

    @Override
    public int hashCode() {
      return Objects.hash(data, device, scopes);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
