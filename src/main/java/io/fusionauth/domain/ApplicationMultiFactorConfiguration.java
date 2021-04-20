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
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class ApplicationMultiFactorConfiguration {
  public MultiFactorEmailTemplate email = new MultiFactorEmailTemplate();

  public MultiFactorSMSTemplate sms = new MultiFactorSMSTemplate();

  @JacksonConstructor
  public ApplicationMultiFactorConfiguration() {
  }

  public ApplicationMultiFactorConfiguration(ApplicationMultiFactorConfiguration other) {
    this.email = new MultiFactorEmailTemplate(other.email);
    this.sms = new MultiFactorSMSTemplate(other.sms);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationMultiFactorConfiguration that = (ApplicationMultiFactorConfiguration) o;
    return Objects.equals(email, that.email) && Objects.equals(sms, that.sms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, sms);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class MultiFactorEmailTemplate {
    public UUID templateId;

    @JacksonConstructor
    public MultiFactorEmailTemplate() {
    }

    public MultiFactorEmailTemplate(MultiFactorEmailTemplate other) {
      this.templateId = other.templateId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      MultiFactorEmailTemplate that = (MultiFactorEmailTemplate) o;
      return Objects.equals(templateId, that.templateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(templateId);
    }
  }

  public static class MultiFactorSMSTemplate {
    public UUID templateId;

    @JacksonConstructor
    public MultiFactorSMSTemplate() {
    }

    public MultiFactorSMSTemplate(MultiFactorSMSTemplate other) {
      this.templateId = other.templateId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      MultiFactorSMSTemplate that = (MultiFactorSMSTemplate) o;
      return Objects.equals(templateId, that.templateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(templateId);
    }
  }
}
