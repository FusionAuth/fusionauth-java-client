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

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.message.Message;
import io.fusionauth.domain.message.MessageType;

/**
 * @author Daniel King
 */
public class VoiceMessage implements Message {
  public String code;

  public Locale locale;

  public String message;

  public String phoneNumber;

  public UUID userId;

  @JacksonConstructor
  public VoiceMessage() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VoiceMessage that = (VoiceMessage) o;
    return Objects.equals(code, that.code) &&
           Objects.equals(locale, that.locale) &&
           Objects.equals(message, that.message) &&
           Objects.equals(phoneNumber, that.phoneNumber) &&
           Objects.equals(userId, that.userId);
  }

  @Override
  public MessageType getType() {
    return MessageType.Voice;
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, locale, message, phoneNumber, userId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
