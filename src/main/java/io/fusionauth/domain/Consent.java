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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal.annotation.ExcludeFromDatabaseDataColumn;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.util.Normalizer;

/**
 * Models a consent.
 *
 * @author Daniel DeGroff
 */
public class Consent implements Buildable<Consent>, _InternalJSONColumn {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public UUID consentEmailTemplateId;

  /**
   * Two character ISO_3166-1 (alpha-2) country code keyed to the minimum age of consent.
   */
  @InternalJSONColumn
  public LocalizedIntegers countryMinimumAgeForSelfConsent = new LocalizedIntegers();

  @InternalJSONColumn
  public Integer defaultMinimumAgeForSelfConsent;

  @InternalJSONColumn
  public EmailPlus emailPlus = new EmailPlus();

  public UUID id;

  @InternalJSONColumn
  public boolean multipleValuesAllowed;

  public String name;

  @InternalJSONColumn
  public List<String> values = new ArrayList<>();

  @JacksonConstructor
  public Consent() {
  }

  @JsonIgnore
  public boolean canSelfConsent(User user) {
    Integer ageOfSelfConsent = getMinimumSelfConsentAge(user);
    if (ageOfSelfConsent == 0) {
      return true;
    }

    return user.getAge() >= ageOfSelfConsent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Consent)) {
      return false;
    }
    Consent that = (Consent) o;
    return multipleValuesAllowed == that.multipleValuesAllowed &&
        Objects.equals(data, that.data) &&
        Objects.equals(consentEmailTemplateId, that.consentEmailTemplateId) &&
        Objects.equals(countryMinimumAgeForSelfConsent, that.countryMinimumAgeForSelfConsent) &&
        Objects.equals(defaultMinimumAgeForSelfConsent, that.defaultMinimumAgeForSelfConsent) &&
        Objects.equals(emailPlus, that.emailPlus) &&
        Objects.equals(id, that.id) &&
        Objects.equals(name, that.name) &&
        Objects.equals(values, that.values);
  }

  @JsonIgnore
  public Integer getMinimumSelfConsentAge(User user) {
    for (Locale locale : user.preferredLanguages) {
      Integer override = countryMinimumAgeForSelfConsent.get(locale);
      if (override != null) {
        return override;
      }
    }

    return defaultMinimumAgeForSelfConsent;
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, consentEmailTemplateId, countryMinimumAgeForSelfConsent, defaultMinimumAgeForSelfConsent, emailPlus, id, multipleValuesAllowed, name, values);
  }

  public void normalize() {
    if (values != null) {
      Normalizer.removeEmpty(values);
    }

    if (countryMinimumAgeForSelfConsent != null) {
      countryMinimumAgeForSelfConsent.removeEmpty();
    }
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class EmailPlus extends Enableable {
    @ExcludeFromDatabaseDataColumn
    public UUID emailTemplateId;

    public int maximumTimeToSendEmailInHours = 48;

    public int minimumTimeToSendEmailInHours = 24;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof EmailPlus)) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }
      EmailPlus emailPlus = (EmailPlus) o;
      return maximumTimeToSendEmailInHours == emailPlus.maximumTimeToSendEmailInHours &&
          minimumTimeToSendEmailInHours == emailPlus.minimumTimeToSendEmailInHours &&
          Objects.equals(emailTemplateId, emailPlus.emailTemplateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), emailTemplateId, maximumTimeToSendEmailInHours, minimumTimeToSendEmailInHours);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
