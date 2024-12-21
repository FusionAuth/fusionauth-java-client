/*
 * Copyright (c) 2019-2024, FusionAuth, All Rights Reserved
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

/**
 * A JavaScript lambda function that is executed during certain events inside FusionAuth.
 *
 * @author Brian Pontarelli
 */
public class Lambda implements Buildable<Lambda> {
  // TODO : ENG-2074 : Brady : Not yet configurable via API
  @JsonIgnore
  public APIVersion apiVersion = APIVersion.V1;

  public String body;

  public boolean debug;

  /**
   * Was never used, only left here for backwards compatibility.
   *
   * @deprecated Do not use this property, if you are binding to it, be advised this will be removed in a future version.
   */
  @Deprecated
  @JsonIgnore
  public boolean enabled;

  public LambdaEngineType engineType = LambdaEngineType.GraalJS;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public String name;

  public LambdaType type;

  @JacksonConstructor
  public Lambda() {
  }

  public Lambda(Lambda lambda) {
    this.apiVersion = lambda.apiVersion;
    this.body = lambda.body;
    this.name = lambda.name;
    this.debug = lambda.debug;
    this.enabled = lambda.enabled;
    this.engineType = lambda.engineType;
    this.id = lambda.id;
    this.insertInstant = lambda.insertInstant;
    this.lastUpdateInstant = lambda.lastUpdateInstant;
    this.type = lambda.type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Lambda lambda = (Lambda) o;
    return debug == lambda.debug &&
           apiVersion == lambda.apiVersion &&
           enabled == lambda.enabled &&
           Objects.equals(body, lambda.body) &&
           engineType == lambda.engineType &&
           Objects.equals(id, lambda.id) &&
           Objects.equals(insertInstant, lambda.insertInstant) &&
           Objects.equals(lastUpdateInstant, lambda.lastUpdateInstant) &&
           Objects.equals(name, lambda.name) &&
           type == lambda.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(body, apiVersion, debug, enabled, engineType, id, insertInstant, lastUpdateInstant, name, type);
  }

  public String toString() {
    return ToString.toString(this);
  }
}
