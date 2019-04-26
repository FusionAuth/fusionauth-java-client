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
package io.fusionauth.domain.provider;

import java.net.URI;
import java.util.Objects;

import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * @author Brian Pontarelli
 */
public class SAMLv2ApplicationConfiguration extends BaseIdentityProviderApplicationConfiguration implements Buildable<SAMLv2ApplicationConfiguration> {
  @InternalJSONColumn
  public URI buttonImageURL;

  @InternalJSONColumn
  public String buttonText;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SAMLv2ApplicationConfiguration)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    SAMLv2ApplicationConfiguration that = (SAMLv2ApplicationConfiguration) o;
    return Objects.equals(buttonImageURL, that.buttonImageURL) &&
        Objects.equals(buttonText, that.buttonText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), buttonImageURL, buttonText);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
