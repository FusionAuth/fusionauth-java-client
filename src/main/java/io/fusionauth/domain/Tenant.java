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
package io.fusionauth.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * @author Daniel DeGroff
 */
public class Tenant implements Buildable<Tenant>, _InternalJSONColumn {
  public final Map<String, Object> data = new LinkedHashMap<>();

  @InternalJSONColumn
  public TenantEmailConfiguration emailConfiguration = new TenantEmailConfiguration();

  public UUID id;

  public String name;

  public Tenant(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  public Tenant() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Tenant)) {
      return false;
    }
    Tenant tenant = (Tenant) o;
    return Objects.equals(data, tenant.data) &&
        Objects.equals(emailConfiguration, tenant.emailConfiguration) &&
        Objects.equals(name, tenant.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, emailConfiguration, name);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public class TenantEmailConfiguration extends Enableable {
    public UUID forgotPasswordEmailTemplateId;

    public UUID setPasswordEmailTemplateId;

    public UUID verificationEmailTemplateId;

    public boolean verifyEmail;

    public boolean verifyEmailWhenChanged;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TenantEmailConfiguration)) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }

      TenantEmailConfiguration that = (TenantEmailConfiguration) o;
      return verifyEmail == that.verifyEmail &&
          verifyEmailWhenChanged == that.verifyEmailWhenChanged &&
          Objects.equals(forgotPasswordEmailTemplateId, that.forgotPasswordEmailTemplateId) &&
          Objects.equals(setPasswordEmailTemplateId, that.setPasswordEmailTemplateId) &&
          Objects.equals(verificationEmailTemplateId, that.verificationEmailTemplateId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), forgotPasswordEmailTemplateId, setPasswordEmailTemplateId, verificationEmailTemplateId, verifyEmail, verifyEmailWhenChanged);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
