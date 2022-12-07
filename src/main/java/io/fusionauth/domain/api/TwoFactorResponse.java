/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

/**
 * @author Daniel DeGroff
 */
public class TwoFactorResponse implements Buildable<TwoFactorResponse> {
  public String code;

  public List<String> recoveryCodes;

  @JacksonConstructor
  public TwoFactorResponse() {
  }

  public TwoFactorResponse(List<String> recoveryCodes) {
    this.recoveryCodes = recoveryCodes;
  }
}
