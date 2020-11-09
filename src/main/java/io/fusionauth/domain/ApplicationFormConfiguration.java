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

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal.annotation.ExcludeFromDatabaseDataColumn;

/**
 * @author Daniel DeGroff
 */
public class ApplicationFormConfiguration implements Buildable<ApplicationFormConfiguration> {
  @ExcludeFromDatabaseDataColumn
  public UUID adminRegistrationFormId;

  @JacksonConstructor
  public ApplicationFormConfiguration() {
  }

  public ApplicationFormConfiguration(ApplicationFormConfiguration other) {
    this.adminRegistrationFormId = other.adminRegistrationFormId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationFormConfiguration that = (ApplicationFormConfiguration) o;
    return Objects.equals(adminRegistrationFormId, that.adminRegistrationFormId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adminRegistrationFormId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
