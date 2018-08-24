/*
 * Copyright (c) 2018,FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.email;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;

/**
 * An email address.
 *
 * @author Brian Pontarelli
 */
public class EmailAddress {
  public String address;

  public String display;

  @JacksonConstructor
  public EmailAddress() {
  }

  public EmailAddress(String address) {
    this.address = address;
    this.display = null;
  }

  public EmailAddress(String address, String display) {
    this.address = address;
    this.display = display;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EmailAddress)) {
      return false;
    }
    EmailAddress that = (EmailAddress) o;
    return Objects.equals(address, that.address) &&
        Objects.equals(display, that.display);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, display);
  }
}
