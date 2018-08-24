/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.PasswordValidationRules;

/**
 * @author Daniel DeGroff
 */
public class PasswordValidationRulesResponse {
  public PasswordValidationRules passwordValidationRules;

  public PasswordValidationRulesResponse(PasswordValidationRules passwordValidationRules) {
    this.passwordValidationRules = passwordValidationRules;
  }

  @JacksonConstructor
  public PasswordValidationRulesResponse() {
  }
}
