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
package io.fusionauth.domain.message;

import java.time.ZonedDateTime;
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
 * Stores an message template used to distribute messages;
 *
 * @author Michael Sleevi
 */
public class MessageTemplate implements Buildable<MessageTemplate> {
  public String defaultTemplate;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public LocalizedStrings localizedTemplates = new LocalizedStrings();

  public String name;

  @JacksonConstructor
  public MessageTemplate() {

  }

  public MessageTemplate(String defaultTemplate, UUID id, ZonedDateTime insertInstant, ZonedDateTime lastUpdateInstant,
                         LocalizedStrings localizedTemplates, String name) {
    this.defaultTemplate = defaultTemplate;
    this.id = id;
    this.insertInstant = insertInstant;
    this.lastUpdateInstant = lastUpdateInstant;
    this.localizedTemplates = localizedTemplates;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageTemplate that = (MessageTemplate) o;
    return defaultTemplate.equals(that.defaultTemplate) &&
           id.equals(that.id) &&
           insertInstant.equals(that.insertInstant) &&
           lastUpdateInstant.equals(that.lastUpdateInstant) &&
           Objects.equals(localizedTemplates, that.localizedTemplates) &&
           name.equals(that.name);
  }

  @JsonIgnore
  public Set<Locale> getLocalizations() {
    return new HashSet<>(localizedTemplates.keySet());
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultTemplate, id, insertInstant, lastUpdateInstant, localizedTemplates, name);
  }

  public void normalize() {
    defaultTemplate = Normalizer.trim(defaultTemplate);
    name = Normalizer.trim(name);


    if (localizedTemplates != null) {
      localizedTemplates.normalize();
    }
  }

  public String toString() {
    return ToString.toString(this);
  }
}
