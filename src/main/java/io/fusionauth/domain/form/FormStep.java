/*
 * Copyright (c) 2020, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class FormStep implements Buildable<FormStep> {
  public List<UUID> fields = new ArrayList<>();

  @JacksonConstructor
  public FormStep() {
  }

  public FormStep(UUID... fieldIds) {
    fields.addAll(Arrays.asList(fieldIds));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FormStep)) {
      return false;
    }
    FormStep formStep = (FormStep) o;
    return Objects.equals(fields, formStep.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fields);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
