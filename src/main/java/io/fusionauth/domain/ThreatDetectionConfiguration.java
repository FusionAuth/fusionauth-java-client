/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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
public class ThreatDetectionConfiguration implements Buildable<ThreatDetectionConfiguration> {
  public CaptchaConfiguration captcha = new CaptchaConfiguration();

  @JacksonConstructor
  public ThreatDetectionConfiguration() {
  }

  public ThreatDetectionConfiguration(ThreatDetectionConfiguration other) {
    this.captcha = new CaptchaConfiguration(other.captcha);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ThreatDetectionConfiguration that = (ThreatDetectionConfiguration) o;
    return Objects.equals(captcha, that.captcha);
  }

  @Override
  public int hashCode() {
    return Objects.hash(captcha);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class CaptchaConfiguration extends Enableable implements Buildable<CaptchaConfiguration> {
    public CaptchaMethod captchaMethod;

    public String secretKey;

    public String siteKey;

    public double threshold;

    @JacksonConstructor
    public CaptchaConfiguration() {
    }

    public CaptchaConfiguration(CaptchaConfiguration other) {
      this.enabled = other.enabled;
      captchaMethod = other.captchaMethod;
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
      CaptchaConfiguration that = (CaptchaConfiguration) o;
      return captchaMethod == that.captchaMethod && secretKey.equals(that.secretKey) && siteKey.equals(that.siteKey) && threshold == that.threshold;
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), captchaMethod, secretKey, siteKey, threshold);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }

    public enum CaptchaMethod {
      GoogleRecaptchaV2,
      GoogleRecaptchaV3,
      HCaptcha,
      HCaptchaEnterprise
    }
  }
}
