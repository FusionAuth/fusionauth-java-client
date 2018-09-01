/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.email;

import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.LocalizedStrings;
import io.fusionauth.domain.util.Normalizer;

/**
 * Stores an email template used to send emails to users.
 *
 * @author Brian Pontarelli
 */
public class EmailTemplate implements Buildable<EmailTemplate> {
  public String defaultFromName;

  public String defaultHtmlTemplate;

  public String defaultSubject;

  public String defaultTextTemplate;

  public String fromEmail;

  public UUID id;

  public LocalizedStrings localizedFromNames = new LocalizedStrings();

  public LocalizedStrings localizedHtmlTemplates = new LocalizedStrings();

  public LocalizedStrings localizedSubjects = new LocalizedStrings();

  public LocalizedStrings localizedTextTemplates = new LocalizedStrings();

  public String name;

  @JacksonConstructor
  public EmailTemplate() {
  }

  public EmailTemplate(UUID id, String name, String defaultFromName, String fromEmail, String defaultSubject,
                       String defaultHtmlTemplate, String defaultTextTemplate) {
    this.defaultFromName = defaultFromName;
    this.fromEmail = fromEmail;
    this.defaultHtmlTemplate = defaultHtmlTemplate;
    this.defaultSubject = defaultSubject;
    this.defaultTextTemplate = defaultTextTemplate;
    this.id = id;
    this.name = name;
  }

  public EmailTemplate(UUID id, String name, String defaultFromName, String fromEmail, String defaultSubject,
                       String defaultHtmlTemplate, String defaultTextTemplate, LocalizedStrings localizedFromNames,
                       LocalizedStrings localizedSubjects, LocalizedStrings localizedHtmlTemplates,
                       LocalizedStrings localizedTextTemplates) {
    this.fromEmail = fromEmail;
    this.defaultFromName = defaultFromName;
    this.defaultHtmlTemplate = defaultHtmlTemplate;
    this.defaultSubject = defaultSubject;
    this.defaultTextTemplate = defaultTextTemplate;
    this.id = id;
    this.localizedFromNames = localizedFromNames;
    this.localizedHtmlTemplates = localizedHtmlTemplates;
    this.localizedSubjects = localizedSubjects;
    this.localizedTextTemplates = localizedTextTemplates;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EmailTemplate)) {
      return false;
    }
    EmailTemplate that = (EmailTemplate) o;
    return Objects.equals(fromEmail, that.fromEmail) &&
        Objects.equals(defaultFromName, that.defaultFromName) &&
        Objects.equals(defaultHtmlTemplate, that.defaultHtmlTemplate) &&
        Objects.equals(defaultSubject, that.defaultSubject) &&
        Objects.equals(defaultTextTemplate, that.defaultTextTemplate) &&
        Objects.equals(localizedFromNames, that.localizedFromNames) &&
        Objects.equals(localizedHtmlTemplates, that.localizedHtmlTemplates) &&
        Objects.equals(localizedSubjects, that.localizedSubjects) &&
        Objects.equals(localizedTextTemplates, that.localizedTextTemplates) &&
        Objects.equals(name, that.name);
  }

  @JsonIgnore
  public Set<Locale> getLocalizations() {
    Set<Locale> locales = new HashSet<>(localizedFromNames.keySet());
    locales.addAll(localizedHtmlTemplates.keySet());
    locales.addAll(localizedSubjects.keySet());
    locales.addAll(localizedTextTemplates.keySet());
    return locales;
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultFromName, fromEmail, defaultHtmlTemplate, defaultSubject, defaultTextTemplate, localizedFromNames,
                        localizedHtmlTemplates, localizedSubjects, localizedTextTemplates, name);
  }

  public void normalize() {
    defaultFromName = Normalizer.trim(defaultFromName);
    defaultHtmlTemplate = Normalizer.trim(defaultHtmlTemplate);
    defaultSubject = Normalizer.trim(defaultSubject);
    defaultTextTemplate = Normalizer.trim(defaultTextTemplate);
    fromEmail = Normalizer.trim(fromEmail);
    name = Normalizer.trim(name);

    if (localizedFromNames != null) {
      localizedFromNames.normalize();
    }

    if (localizedHtmlTemplates != null) {
      localizedHtmlTemplates.normalize();
    }

    if (localizedSubjects != null) {
      localizedSubjects.normalize();
    }

    if (localizedTextTemplates != null) {
      localizedTextTemplates.normalize();
    }
  }

  public String toString() {
    return ToString.toString(this);
  }
}
