/*
 * Copyright (c) 2020-2026, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.message.voice;

import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.LocalizedStrings;
import io.fusionauth.domain.message.MessageTemplate;
import io.fusionauth.domain.message.MessageType;
import io.fusionauth.domain.util.Normalizer;

/**
 * @author Daniel King
 */
public class VoiceMessageTemplate extends MessageTemplate implements Buildable<VoiceMessageTemplate> {
  public String defaultTemplate;

  public LocalizedStrings localizedTemplates = new LocalizedStrings();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    VoiceMessageTemplate that = (VoiceMessageTemplate) o;
    return Objects.equals(defaultTemplate, that.defaultTemplate) && Objects.equals(localizedTemplates, that.localizedTemplates);
  }

  @JsonIgnore
  public Set<Locale> getLocalizations() {
    return new HashSet<>(localizedTemplates.keySet());
  }

  @Override
  public MessageType getType() {
    return MessageType.Voice;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), defaultTemplate, localizedTemplates);
  }

  @Override
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
