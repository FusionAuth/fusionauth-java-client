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
package io.fusionauth.domain;

import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;

/**
 * @author Daniel DeGroff
 */
public class PasswordBreachDetection extends Enableable implements Buildable<PasswordBreachDetection> {
  public BreachMatchMode matchMode;

  public UUID notifyUserEmailTemplateId;

  public BreachAction onLogin;

  @JacksonConstructor
  public PasswordBreachDetection() {

  }

  public PasswordBreachDetection(PasswordBreachDetection other) {
    this.enabled = other.enabled;
    this.matchMode = other.matchMode;
    this.notifyUserEmailTemplateId = other.notifyUserEmailTemplateId;
    this.onLogin = other.onLogin;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PasswordBreachDetection)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    PasswordBreachDetection that = (PasswordBreachDetection) o;
    return matchMode == that.matchMode &&
        Objects.equals(notifyUserEmailTemplateId, that.notifyUserEmailTemplateId) &&
        onLogin == that.onLogin;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), matchMode, notifyUserEmailTemplateId, onLogin);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public enum BreachAction {
    Off,
    RecordOnly,
    NotifyUser,
    RequireChange
  }

  public enum BreachMatchMode {
    Low,
    Medium,
    High
  }
}
