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
package io.fusionauth.domain.event;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Objects;

import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.Location;

/**
 * @author Brett Guy
 */
public class EmailTemplateTestEvent extends BaseEvent implements Buildable<EmailTemplateTestEvent> {
  public String message;

  @JacksonConstructor
  public EmailTemplateTestEvent() {
    message = "Example FusionAuth Event. This object should only be used when testing FusionAuth email templates.";

    createInstant = ZonedDateTime.now(ZoneOffset.UTC);
    info = new EventInfo().with(e -> e.data = Collections.singletonMap("foo", "bar"))
                          .with(e -> e.deviceDescription = "Test device description")
                          .with(e -> e.deviceName = "Test device name")
                          .with(e -> e.deviceType = "BROWSER")
                          .with(e -> e.ipAddress = "42.42.42.42")
                          .with(e -> e.os = "Darwin")
                          .with(e -> e.userAgent = "Netscape Navigator")
                          .with(e -> e.location =
                              new Location().with(l -> l.city = "Denver")
                                            .with(l -> l.country = "US")
                                            .with(l -> l.latitude = 39.73900)
                                            .with(l -> l.longitude = -104.9800)
                                            .with(l -> l.region = "CO"));
  }

  public EmailTemplateTestEvent(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmailTemplateTestEvent that = (EmailTemplateTestEvent) o;
    return super.equals(o) &&
           Objects.equals(message, that.message);
  }

  @Override
  public EventType getType() {
    return EventType.Test;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), message);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
