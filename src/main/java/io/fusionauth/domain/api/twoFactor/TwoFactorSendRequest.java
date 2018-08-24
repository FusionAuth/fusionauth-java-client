/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.twoFactor;

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class TwoFactorSendRequest implements Buildable<TwoFactorSendRequest> {
  public String mobilePhone;

  public String secret;

  public UUID userId;

  @JacksonConstructor
  public TwoFactorSendRequest() {
  }

  public TwoFactorSendRequest(UUID userId) {
    this.userId = userId;
  }

  public TwoFactorSendRequest(String mobilePhone, String secret) {
    this.mobilePhone = mobilePhone;
    this.secret = secret;
  }
}
