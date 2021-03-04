/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.identityProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.api.BaseLoginRequest;

/**
 * Login API request object used for login to third-party systems (i.e. Login with Facebook).
 *
 * @author Brian Pontarelli
 */
public class IdentityProviderLoginRequest extends BaseLoginRequest implements Buildable<IdentityProviderLoginRequest> {
  public Map<String, String> data = new HashMap<>(1);

  public UUID identityProviderId;

  public boolean bypassAuthTokenRequest;

  @JacksonConstructor
  public IdentityProviderLoginRequest() {
  }

  public IdentityProviderLoginRequest addData(String key, String value) {
    if (value == null) {
      return this;
    }

    data.put(key, value);
    return this;
  }

  public String getEncodedJWT() {
    return data.get("token");
  }

  public void setEncodedJWT(String encodedJWT) {
    this.data.put("token", encodedJWT);
  }
}