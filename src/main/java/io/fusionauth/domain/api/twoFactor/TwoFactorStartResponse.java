/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.twoFactor;

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.TwoFactorMethod;

/**
 * @author Daniel DeGroff
 */
public class TwoFactorStartResponse implements Buildable<TwoFactorStartResponse> {
  public String code;

  public List<TwoFactorMethod> methods;

  public String twoFactorId;

  @JacksonConstructor
  public TwoFactorStartResponse() {
  }

  public TwoFactorStartResponse(String code, List<TwoFactorMethod> methods, String twoFactorId) {
    this.code = code;
    this.methods = methods;
    this.twoFactorId = twoFactorId;
  }
}
