/*
 * Copyright (c) 2021-2026, FusionAuth, All Rights Reserved
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

  public MultiFactorVoiceTemplate voice = new MultiFactorVoiceTemplate();

  @JacksonConstructor
  public ApplicationMultiFactorConfiguration() {
  }

  public ApplicationMultiFactorConfiguration(ApplicationMultiFactorConfiguration other) {
    this.email = new MultiFactorEmailTemplate(other.email);
    this.loginPolicy = other.loginPolicy;
    this.sms = new MultiFactorSMSTemplate(other.sms);
    this.trustPolicy = other.trustPolicy;
    this.voice = new MultiFactorVoiceTemplate(other.voice);
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
           trustPolicy == that.trustPolicy &&
           Objects.equals(voice, that.voice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, loginPolicy, sms, trustPolicy, voice);
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

  public static class MultiFactorVoiceTemplate {
    public UUID templateId;

    @JacksonConstructor
    public MultiFactorVoiceTemplate() {
    }

    public MultiFactorVoiceTemplate(MultiFactorVoiceTemplate other) {
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
      MultiFactorVoiceTemplate that = (MultiFactorVoiceTemplate) o;
      return Objects.equals(templateId, that.templateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(templateId);
    }
  }
}
