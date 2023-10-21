/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.internal.annotation.InternalUse;

/**
 * @author Brian Pontarelli
 */
public class SystemConfiguration implements Buildable<SystemConfiguration> {
  public AuditLogConfiguration auditLogConfiguration = new AuditLogConfiguration();

  /**
   * Base64 encoded Encryption Key for prime-mvc. This is currently only used to encrypt and de-crypt saved request
   * cookies.
   */
  @InternalUse
  public String cookieEncryptionKey;

  public CORSConfiguration corsConfiguration = new CORSConfiguration();

  public Map<String, Object> data = new HashMap<>();

  public EventLogConfiguration eventLogConfiguration = new EventLogConfiguration();

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public LoginRecordConfiguration loginRecordConfiguration = new LoginRecordConfiguration();

  public ZoneId reportTimezone;

  public UIConfiguration uiConfiguration = new UIConfiguration();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SystemConfiguration that = (SystemConfiguration) o;
    return Objects.equals(auditLogConfiguration, that.auditLogConfiguration) &&
           Objects.equals(cookieEncryptionKey, that.cookieEncryptionKey) &&
           Objects.equals(corsConfiguration, that.corsConfiguration) &&
           Objects.equals(data, that.data) &&
           Objects.equals(eventLogConfiguration, that.eventLogConfiguration) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(loginRecordConfiguration, that.loginRecordConfiguration) &&
           Objects.equals(reportTimezone, that.reportTimezone) &&
           Objects.equals(uiConfiguration, that.uiConfiguration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(auditLogConfiguration, cookieEncryptionKey, corsConfiguration, data, eventLogConfiguration, insertInstant, lastUpdateInstant, loginRecordConfiguration, reportTimezone, uiConfiguration);
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
    cookieEncryptionKey = null;
    return this;
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }

  public static class AuditLogConfiguration {
    public DeleteConfiguration delete = new DeleteConfiguration(365);

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

  public static class DeleteConfiguration extends Enableable {
    public int numberOfDaysToRetain = 365;

    @JacksonConstructor
    public DeleteConfiguration() {
    }

    public DeleteConfiguration(int numberOfDaysToRetain) {
      this.numberOfDaysToRetain = numberOfDaysToRetain;
    }

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
    public DeleteConfiguration delete = new DeleteConfiguration(365);

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
