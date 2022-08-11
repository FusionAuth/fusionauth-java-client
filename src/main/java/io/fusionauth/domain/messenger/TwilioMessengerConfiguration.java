/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
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
