/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.time.ZonedDateTime;
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
import io.fusionauth.domain.util.Normalizer;

/**
 * Models a consent.
 *
 * @author Daniel DeGroff
 */
public class Consent implements Buildable<Consent> {
  public final Map<String, Object> data = new LinkedHashMap<>();

  public UUID consentEmailTemplateId;

  /**
   * Two character ISO_3166-1 (alpha-2) country code keyed to the minimum age of consent.
   */
  public LocalizedIntegers countryMinimumAgeForSelfConsent = new LocalizedIntegers();

  public Integer defaultMinimumAgeForSelfConsent;

  public EmailPlus emailPlus = new EmailPlus();

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public boolean multipleValuesAllowed;

  public String name;

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
    Consent consent = (Consent) o;
    return multipleValuesAllowed == consent.multipleValuesAllowed &&
           Objects.equals(data, consent.data) &&
           Objects.equals(consentEmailTemplateId, consent.consentEmailTemplateId) &&
           Objects.equals(countryMinimumAgeForSelfConsent, consent.countryMinimumAgeForSelfConsent) &&
           Objects.equals(defaultMinimumAgeForSelfConsent, consent.defaultMinimumAgeForSelfConsent) &&
           Objects.equals(emailPlus, consent.emailPlus) &&
           Objects.equals(id, consent.id) &&
           Objects.equals(insertInstant, consent.insertInstant) &&
           Objects.equals(lastUpdateInstant, consent.lastUpdateInstant) &&
           Objects.equals(name, consent.name) &&
           Objects.equals(values, consent.values);
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
    return Objects.hash(data, consentEmailTemplateId, countryMinimumAgeForSelfConsent, defaultMinimumAgeForSelfConsent, emailPlus, id, insertInstant, lastUpdateInstant, multipleValuesAllowed, name, values);
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
