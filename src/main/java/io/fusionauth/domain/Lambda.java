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
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;

/**
 * A JavaScript lambda function that is executed during certain events inside FusionAuth.
 *
 * @author Brian Pontarelli
 */
public class Lambda extends Enableable implements Buildable<Lambda> {
  public String body;

  public String description;

  public UUID id;

  public ZonedDateTime insertInstant;

  public LambdaType type;

  public Lambda() {
    enabled = true;
  }

  public Lambda(Lambda lambda) {
    this.body = lambda.body;
    this.description = lambda.description;
    this.enabled = lambda.enabled;
    this.id = lambda.id;
    this.insertInstant = lambda.insertInstant;
    this.type = lambda.type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Lambda)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Lambda lambda = (Lambda) o;
    return Objects.equals(body, lambda.body) &&
        Objects.equals(description, lambda.description) &&
        Objects.equals(insertInstant, lambda.insertInstant) &&
        type == lambda.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), body, description, insertInstant, type);
  }

  public String toString() {
    return ToString.toString(this);
  }
}
