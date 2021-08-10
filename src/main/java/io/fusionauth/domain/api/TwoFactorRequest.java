/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api;

import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;

/**
 * @author Brian Pontarelli
 */
public class TwoFactorRequest extends BaseEventRequest implements Buildable<TwoFactorRequest> {
  public UUID applicationId;

  public String authenticatorId;

  public String code;

  public String email;

  public String method;

  public String mobilePhone;

  public String secret;

  public String secretBase32Encoded;

  @JacksonConstructor
  public TwoFactorRequest() {
  }

  public TwoFactorRequest(String code, String secret) {
    this(code, null, secret);
  }

  public TwoFactorRequest(String code, String method, String secret) {
    this.code = code;
    this.method = method;
    this.secret = secret;
  }

  public TwoFactorRequest(EventInfo eventInfo, UUID applicationId, String code, String secret) {
    this(eventInfo, applicationId, code, null, secret);
  }

  public TwoFactorRequest(EventInfo eventInfo, UUID applicationId, String code, String method, String secret) {
    super(eventInfo);
    this.applicationId = applicationId;
    this.code = code;
    this.method = method;
    this.secret = secret;
  }
}
