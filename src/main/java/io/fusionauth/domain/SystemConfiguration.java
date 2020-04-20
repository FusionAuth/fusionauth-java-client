/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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

import java.net.URI;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.inversoft.json.ToString;
import io.fusionauth.domain.internal._InternalJSONColumn;
import io.fusionauth.domain.internal.annotation.InternalJSONColumn;
import io.fusionauth.domain.util.HTTPMethod;

/**
 * @author Brian Pontarelli
 */
public class SystemConfiguration implements Buildable<SystemConfiguration>, _InternalJSONColumn {
  @InternalJSONColumn
  public AuditLogConfiguration auditLogConfiguration = new AuditLogConfiguration();

  /**
   * Base64 encoded Initialization Vector for prime-mvc. This is currently only used to encrypt and de-crypt saved
   * request cookies.
   */
  @InternalJSONColumn
  public String cookieEncryptionIV;

  /**
   * Base64 encoded Encryption Key for prime-mvc. This is currently only used to encrypt and de-crypt saved request
   * cookies.
   */
  @InternalJSONColumn
  public String cookieEncryptionKey;

  @InternalJSONColumn
  public CORSConfiguration corsConfiguration = new CORSConfiguration();

  public Map<String, Object> data = new HashMap<>();

  @InternalJSONColumn
  public EventLogConfiguration eventLogConfiguration = new EventLogConfiguration();

  @InternalJSONColumn
  public LoginRecordConfiguration loginRecordConfiguration = new LoginRecordConfiguration();

  public ZoneId reportTimezone;

  @InternalJSONColumn
  public UIConfiguration uiConfiguration = new UIConfiguration();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SystemConfiguration)) {
      return false;
    }
    SystemConfiguration that = (SystemConfiguration) o;
    return Objects.equals(auditLogConfiguration, that.auditLogConfiguration) &&
           Objects.equals(cookieEncryptionIV, that.cookieEncryptionIV) &&
           Objects.equals(cookieEncryptionKey, that.cookieEncryptionKey) &&
           Objects.equals(corsConfiguration, that.corsConfiguration) &&
           Objects.equals(data, that.data) &&
           Objects.equals(eventLogConfiguration, that.eventLogConfiguration) &&
           Objects.equals(loginRecordConfiguration, that.loginRecordConfiguration) &&
           Objects.equals(reportTimezone, that.reportTimezone) &&
           Objects.equals(uiConfiguration, that.uiConfiguration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cookieEncryptionIV, cookieEncryptionKey, corsConfiguration, data, reportTimezone, uiConfiguration);
  }

  public void normalize() {
    if (uiConfiguration != null) {
      uiConfiguration.normalize();
    }
    if (corsConfiguration != null) {
      corsConfiguration.normalize();
    }
  }

  public SystemConfiguration secure() {
    cookieEncryptionIV = null;
    cookieEncryptionKey = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class AuditLogConfiguration {
    public DeleteConfiguration delete = new DeleteConfiguration();

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof AuditLogConfiguration)) {
        return false;
      }
      AuditLogConfiguration that = (AuditLogConfiguration) o;
      return Objects.equals(delete, that.delete);
    }

    @Override
    public int hashCode() {
      return Objects.hash(delete);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class CORSConfiguration extends Enableable implements Buildable<CORSConfiguration> {
    public boolean allowCredentials;

    public List<String> allowedHeaders = new ArrayList<>();

    public List<HTTPMethod> allowedMethods = new ArrayList<>();

    public List<URI> allowedOrigins = new ArrayList<>();

    public List<String> exposedHeaders = new ArrayList<>();

    public int preflightMaxAgeInSeconds;

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
      CORSConfiguration that = (CORSConfiguration) o;
      Collections.sort(allowedHeaders);
      Collections.sort(that.allowedHeaders);
      Collections.sort(allowedMethods);
      Collections.sort(that.allowedMethods);
      Collections.sort(allowedOrigins);
      Collections.sort(that.allowedOrigins);
      Collections.sort(exposedHeaders);
      Collections.sort(that.exposedHeaders);
      return preflightMaxAgeInSeconds == that.preflightMaxAgeInSeconds &&
             allowCredentials == that.allowCredentials &&
             Objects.equals(allowedHeaders, that.allowedHeaders) &&
             Objects.equals(allowedMethods, that.allowedMethods) &&
             Objects.equals(allowedOrigins, that.allowedOrigins) &&
             Objects.equals(exposedHeaders, that.exposedHeaders);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), allowedHeaders, allowedMethods, allowedOrigins, exposedHeaders, preflightMaxAgeInSeconds, allowCredentials);
    }

    public void normalize() {
      Set<HTTPMethod> methods = new HashSet<>(allowedMethods);
      allowedMethods.clear();
      allowedMethods.addAll(methods);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class DeleteConfiguration extends Enableable {
    public Integer numberOfDaysToRetain;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof DeleteConfiguration)) {
        return false;
      }
      if (!super.equals(o)) {
        return false;
      }
      DeleteConfiguration that = (DeleteConfiguration) o;
      return Objects.equals(numberOfDaysToRetain, that.numberOfDaysToRetain);
    }

    @Override
    public int hashCode() {
      return Objects.hash(super.hashCode(), numberOfDaysToRetain);
    }


    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class EventLogConfiguration {
    public int numberToRetain = 10_000;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof EventLogConfiguration)) {
        return false;
      }
      EventLogConfiguration that = (EventLogConfiguration) o;
      return numberToRetain == that.numberToRetain;
    }

    @Override
    public int hashCode() {
      return Objects.hash(numberToRetain);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class LoginRecordConfiguration {
    public DeleteConfiguration delete = new DeleteConfiguration();

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof LoginRecordConfiguration)) {
        return false;
      }
      LoginRecordConfiguration that = (LoginRecordConfiguration) o;
      return Objects.equals(delete, that.delete);
    }

    @Override
    public int hashCode() {
      return Objects.hash(delete);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }

  public static class UIConfiguration implements Buildable<UIConfiguration> {
    public String headerColor;

    public String logoURL;

    public String menuFontColor;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof UIConfiguration)) {
        return false;
      }
      UIConfiguration that = (UIConfiguration) o;
      return Objects.equals(logoURL, that.logoURL) &&
             Objects.equals(headerColor, that.headerColor) &&
             Objects.equals(menuFontColor, that.menuFontColor);
    }

    @Override
    public int hashCode() {
      return Objects.hash(logoURL, headerColor, menuFontColor);
    }

    public void normalize() {
    }
  }
}
