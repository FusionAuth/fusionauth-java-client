/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
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

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;

/**
 * The results of an identity provider connection test.
 */
public class IdentityProviderConnectionTestResult implements Buildable<IdentityProviderConnectionTestResult> {
  public String email;

  public UUID identityProviderId;

  public String identityProviderUserId;

  public ZonedDateTime startInstant;

  public List<IdentityProviderLoginStep> steps = new ArrayList<>();

  public boolean success;

  public String username;

  public IdentityProviderConnectionTestResult() {
  }

  public IdentityProviderConnectionTestResult(IdentityProviderConnectionTestResult other) {
    this.email = other.email;
    this.identityProviderId = other.identityProviderId;
    this.identityProviderUserId = other.identityProviderUserId;
    this.startInstant = other.startInstant;
    this.steps.addAll(other.steps);
    this.success = other.success;
    this.username = other.username;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IdentityProviderConnectionTestResult that = (IdentityProviderConnectionTestResult) o;
    return Objects.equals(email, that.email) &&
           Objects.equals(identityProviderId, that.identityProviderId) &&
           Objects.equals(identityProviderUserId, that.identityProviderUserId) &&
           success == that.success &&
           Objects.equals(startInstant, that.startInstant) &&
           Objects.equals(steps, that.steps) &&
           Objects.equals(username, that.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, identityProviderId, identityProviderUserId, startInstant, steps, success, username);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class IdentityProviderLoginStep {
    public String detail;

    public boolean success;

    public String title;

    @JacksonConstructor
    public IdentityProviderLoginStep() {
    }

    public IdentityProviderLoginStep(String title, boolean success, String detail) {
      this.title = title;
      this.success = success;
      this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      IdentityProviderLoginStep that = (IdentityProviderLoginStep) o;
      return success == that.success && Objects.equals(detail, that.detail) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
      return Objects.hash(detail, success, title);
    }
  }
}
