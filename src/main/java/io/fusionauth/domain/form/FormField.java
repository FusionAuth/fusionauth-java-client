/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.util.Normalizer;

/**
 * @author Daniel DeGroff
 */
public class FormField implements Buildable<FormField> {
  
  public boolean confirm;

  public UUID consentId;

  
  public FormControl control;

  public Map<String, Object> data = new LinkedHashMap<>();

  
  public String description;

  public UUID id;

  public ZonedDateTime insertInstant;

  
  public String key;

  public ZonedDateTime lastUpdateInstant;

  public String name;

  
  public List<String> options = new ArrayList<>();

  
  public boolean required;

  
  public FormDataType type = FormDataType.string;

  
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

    // WARNING : Gnarls code ahead... (this normalizes all the fields based on type and if they are managed or not)
    FormField managedField = ManagedFields.Values.get(key);

    // Control is optional, set the default, it is text for most managed fields, and default for all custom fields.
    //  - Key may be null when building a consent, it is set later.
    if (control == null && key != null) {
      control = managedField == null ? FormControl.text : managedField.control;
    }

    // Set defaults for all managed fields
    if (managedField != null) {
      // IJ warns about null, but we don't have any nulls in our ManagedFields set, so we are ok.
      //noinspection ConstantConditions
      if (key.equals("user.password")) {
        // The shipped field for use in self-service registration is not set to 'confirm', but the admin field will be.
        // - If the user creates another field for this key, we will default it to 'confirm' true.
        control = FormControl.password;
        required = true;
      }

      // Set default field type
      type = managedField.type;
    }

    // Validation is currently only allowed for number, text or textarea.
    if (control != FormControl.number && control != FormControl.text && control != FormControl.textarea) {
      validator.enabled = false;
      validator.expression = null;
    }

    // Email data type must be text form field
    if (type == FormDataType.email) {
      control = FormControl.text;
    } else if (type == FormDataType.consent) {
      // You cannot confirm a consent, and the key is generated. See setSyntheticConsentFields.
      confirm = false;
      control = null;
      key = "consents['" + consentId + "']";
    }
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
