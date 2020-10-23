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

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.util.Normalizer;

/**
 * @author Daniel DeGroff
 */
public class FormField implements Buildable<FormField>, _InternalJSONColumn {
  @InternalJSONColumn
  public boolean confirm;

  public UUID consentId;

  @InternalJSONColumn
  public FormControl control;

  public Map<String, Object> data = new LinkedHashMap<>();

  @InternalJSONColumn
  public String description;

  public UUID id;

  public ZonedDateTime insertInstant;

  @InternalJSONColumn
  public String key;

  public ZonedDateTime lastUpdateInstant;

  public String name;

  @InternalJSONColumn
  public List<String> options = new ArrayList<>();

  @InternalJSONColumn
  public boolean required;

  @InternalJSONColumn
  public FormDataType type;

  @InternalJSONColumn
  public FormFieldValidator validator = new FormFieldValidator();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FormField)) {
      return false;
    }
    FormField formField = (FormField) o;
    return confirm == formField.confirm &&
           required == formField.required &&
           Objects.equals(consentId, formField.consentId) &&
           control == formField.control &&
           Objects.equals(data, formField.data) &&
           Objects.equals(description, formField.description) &&
           Objects.equals(id, formField.id) &&
           Objects.equals(insertInstant, formField.insertInstant) &&
           Objects.equals(lastUpdateInstant, formField.lastUpdateInstant) &&
           Objects.equals(key, formField.key) &&
           Objects.equals(name, formField.name) &&
           Objects.equals(options, formField.options) &&
           type == formField.type &&
           Objects.equals(validator, formField.validator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(confirm, consentId, control, data, description, id, insertInstant, lastUpdateInstant, key, name, options, required, type, validator);
  }

  public void normalize() {
    Normalizer.removeEmpty(options);
    // We want checkbox of type bool to always have 'true' as the first option.
    if (control == FormControl.checkbox && type == FormDataType.bool) {
      Normalizer.toLowerCase(options, ArrayList::new);
      options.sort(Collections.reverseOrder());
    }
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
