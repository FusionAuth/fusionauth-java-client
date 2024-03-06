/*
 * Copyright (c) 2021-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Brett Pontarelli
 */
public class TenantCaptchaConfiguration extends Enableable implements Buildable<TenantCaptchaConfiguration> {
  public CaptchaMethod captchaMethod = CaptchaMethod.GoogleRecaptchaV3;

  public String secretKey;

  public String siteKey;

  public double threshold = 0.5;

  @JacksonConstructor
  public TenantCaptchaConfiguration() {
  }

  public TenantCaptchaConfiguration(TenantCaptchaConfiguration other) {
    captchaMethod = other.captchaMethod;
    enabled = other.enabled;
    secretKey = other.secretKey;
    siteKey = other.siteKey;
    threshold = other.threshold;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    TenantCaptchaConfiguration that = (TenantCaptchaConfiguration) o;
    return Double.compare(that.threshold, threshold) == 0 &&
           captchaMethod == that.captchaMethod &&
           Objects.equals(secretKey, that.secretKey) &&
           Objects.equals(siteKey, that.siteKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), captchaMethod, secretKey, siteKey, threshold);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
