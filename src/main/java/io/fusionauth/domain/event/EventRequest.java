/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.event;

import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.client.json.EventRequestDeserializer;

/**
 * Container for the event information. This is the JSON that is sent from FusionAuth to webhooks.
 *
 * @author Brian Pontarelli
 */
@JsonDeserialize(using = EventRequestDeserializer.class)
public class EventRequest {
  public BaseEvent event;

  @JacksonConstructor
  public EventRequest() {
  }

  public EventRequest(BaseEvent event) {
    this.event = event;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventRequest that = (EventRequest) o;
    return Objects.equals(event, that.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(event);
  }

  public String toString() {
    return ToString.toString(this);
  }
}
