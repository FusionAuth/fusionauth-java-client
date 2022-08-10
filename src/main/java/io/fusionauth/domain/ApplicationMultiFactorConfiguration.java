/*
 * Copyright (c) 2021-2022, FusionAuth, All Rights Reserved
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

  public MultiFactorLoginPolicy loginPolicy;

  public MultiFactorSMSTemplate sms = new MultiFactorSMSTemplate();

  public ApplicationMultiFactorTrustPolicy trustPolicy;

  @JacksonConstructor
  public ApplicationMultiFactorConfiguration() {
  }

  public ApplicationMultiFactorConfiguration(ApplicationMultiFactorConfiguration other) {
    this.email = new MultiFactorEmailTemplate(other.email);
    this.loginPolicy = other.loginPolicy;
    this.sms = new MultiFactorSMSTemplate(other.sms);
    this.trustPolicy = other.trustPolicy;
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
    return Objects.equals(email, that.email) &&
           loginPolicy == that.loginPolicy &&
           Objects.equals(sms, that.sms) &&
           trustPolicy == that.trustPolicy;
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, loginPolicy, sms, trustPolicy);
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
