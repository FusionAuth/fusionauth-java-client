/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.user;

import com.inversoft.json.JacksonConstructor;

/**
 * @author Daniel DeGroff
 */
public class VerifyEmailResponse {

  public String verificationId;

  @JacksonConstructor
  public VerifyEmailResponse() {
  }

  public VerifyEmailResponse(String verificationId) {
    this.verificationId = verificationId;
  }
}
