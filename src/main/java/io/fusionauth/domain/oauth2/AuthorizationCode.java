/*
 * Copyright (c) 2018,FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.oauth2;

import java.net.URI;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class AuthorizationCode implements Expiring {
  public String clientId;

  public String code;

  public ZonedDateTime createInstant;

  public String encodedJWT;

  public ZonedDateTime expiresInstant;

  public URI redirectURI;

  public UUID userId;

  public AuthorizationCode(String clientId, String code, ZonedDateTime createInstant, ZonedDateTime expiresInstant,
                           URI redirectURI, UUID userId, String encodedJWT) {
    this.clientId = clientId;
    this.code = code;
    this.createInstant = createInstant;
    this.encodedJWT = encodedJWT;
    this.expiresInstant = expiresInstant;
    this.redirectURI = redirectURI;
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorizationCode that = (AuthorizationCode) o;
    return Objects.equals(clientId, that.clientId) &&
        Objects.equals(code, that.code) &&
        Objects.equals(createInstant, that.createInstant) &&
        Objects.equals(encodedJWT, that.encodedJWT) &&
        Objects.equals(expiresInstant, that.expiresInstant) &&
        Objects.equals(redirectURI, that.redirectURI) &&
        Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, code, createInstant, encodedJWT, expiresInstant, redirectURI, userId);
  }

  @Override
  public boolean isExpired() {
    return ZonedDateTime.now(ZoneOffset.UTC).isAfter(expiresInstant);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
