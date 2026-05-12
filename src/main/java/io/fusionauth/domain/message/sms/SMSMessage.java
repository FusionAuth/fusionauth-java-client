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
package io.fusionauth.domain.message.sms;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.message.Message;
import io.fusionauth.domain.message.MessageType;

/**
 * @author Michael Sleevi
 */
public class SMSMessage implements Message {
  public String code;

  public String phoneNumber;

  public String textMessage;

  public UUID userId;

  @JacksonConstructor
  public SMSMessage() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SMSMessage that = (SMSMessage) o;
    return Objects.equals(code, that.code) &&
           Objects.equals(textMessage, that.textMessage) &&
           Objects.equals(phoneNumber, that.phoneNumber) &&
           Objects.equals(userId, that.userId);
  }

  @Override
  public MessageType getType() {
    return MessageType.SMS;
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, textMessage, phoneNumber, userId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
