/*
 * Copyright (c) 2020-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.messenger;

import java.net.URI;
import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * @author Brett Guy
 */
public class TwilioMessengerConfiguration extends BaseMessengerConfiguration implements Buildable<TwilioMessengerConfiguration> {
  public String accountSID;

  public String authToken;

  public String fromPhoneNumber;

  public String messagingServiceSid;

  public URI url;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TwilioMessengerConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    TwilioMessengerConfiguration that = (TwilioMessengerConfiguration) o;
    return super.equals(o) &&
           Objects.equals(accountSID, that.accountSID) &&
           Objects.equals(authToken, that.authToken) &&
           Objects.equals(fromPhoneNumber, that.fromPhoneNumber) &&
           Objects.equals(messagingServiceSid, that.messagingServiceSid) &&
           Objects.equals(url, that.url);
  }

  @Override
  public MessengerType getType() {
    return MessengerType.Twilio;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), accountSID, authToken, fromPhoneNumber, messagingServiceSid, url);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
