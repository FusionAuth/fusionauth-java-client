/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.provider;

import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;


/**
 * @author Brett Pontarelli
 */
public class NintendoApplicationConfiguration extends BaseIdentityProviderApplicationConfiguration implements Buildable<NintendoApplicationConfiguration> {
  
  public String buttonText;

  
  public String client_id;

  
  public String client_secret;

  
  public String emailClaim = "email";

  
  public String scope;

  
  public String uniqueIdClaim = "id";

  
  public String usernameClaim = "preferred_username";

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NintendoApplicationConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    NintendoApplicationConfiguration that = (NintendoApplicationConfiguration) o;
    return Objects.equals(buttonText, that.buttonText) &&
           Objects.equals(client_id, that.client_id) &&
           Objects.equals(client_secret, that.client_secret) &&
           Objects.equals(scope, that.scope) &&
           Objects.equals(uniqueIdClaim, that.uniqueIdClaim) &&
           Objects.equals(emailClaim, that.emailClaim) &&
           Objects.equals(usernameClaim, that.usernameClaim);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), buttonText, client_id, client_secret, scope, uniqueIdClaim, emailClaim, usernameClaim);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
