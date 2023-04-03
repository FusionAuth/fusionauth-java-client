/*
 * Copyright (c) 2018-2023, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.User;

/**
 * User API request object.
 *
 * @author Brian Pontarelli
 */
public class UserRequest extends BaseEventRequest implements Buildable<UserRequest> {
  public UUID applicationId;

  // Used for password update. If it's provided, it'll be validated.
  public String currentPassword;

  public boolean disableDomainBlock;

  public boolean sendSetPasswordEmail;

  public boolean skipVerification;

  public User user;

  @JacksonConstructor
  public UserRequest() {
  }

  public UserRequest(User user) {
    this.sendSetPasswordEmail = false;
    this.skipVerification = true;
    this.user = user;
  }

  public UserRequest(boolean sendSetPasswordEmail, boolean skipVerification, User user) {
    this.sendSetPasswordEmail = sendSetPasswordEmail;
    this.skipVerification = skipVerification;
    this.user = user;
  }

  public UserRequest(EventInfo eventInfo, User user) {
    super(eventInfo);
    this.sendSetPasswordEmail = false;
    this.skipVerification = true;
    this.user = user;
  }

  public UserRequest(EventInfo eventInfo, UUID applicationId, boolean sendSetPasswordEmail, boolean skipVerification, String currentPassword,
                     User user) {
    super(eventInfo);
    this.applicationId = applicationId;
    this.currentPassword = currentPassword;
    this.sendSetPasswordEmail = sendSetPasswordEmail;
    this.skipVerification = skipVerification;
    this.user = user;
  }
}
