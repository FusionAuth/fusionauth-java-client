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
package io.fusionauth.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.fusionauth.domain.event.BaseEvent;
import io.fusionauth.domain.event.EventRequest;
import io.fusionauth.domain.event.EventType;

/**
 * Custom JSON deserializer for BaseEvent.
 *
 * @author Daniel DeGroff
 */
public class EventRequestDeserializer extends StdDeserializer<EventRequest> {
  public EventRequestDeserializer() {
    super(EventRequest.class);
  }

  @Override
  public EventRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return deserialize(p, ctxt, new EventRequest());
  }

  @Override
  public EventRequest deserialize(JsonParser p, DeserializationContext ctxt, EventRequest req)
      throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    JsonNode eventNode = node.at("/event");

    if (req.event == null) {
      EventType eventType = extractType(ctxt, p, eventNode);
      // Assuming all of our events following this naming schema '{EventType}Event'
      String className = BaseEvent.class.getPackage().getName() + "." + eventType.name() + "Event";
      try {
        req.event = (BaseEvent) Class.forName(className).getConstructor().newInstance();
      } catch (Exception e) {
        throw new IllegalStateException("Unexpected type [" + eventType + "]. This is a FusionAuth bug, could not instantiate class [" + className + "].");
      }
    }

    ((ObjectMapper) p.getCodec()).readerForUpdating(req.event).readValue(eventNode);
    return req;
  }

  private EventType extractType(DeserializationContext ctxt, JsonParser p, JsonNode eventNode)
      throws IOException {
    JsonNode node = eventNode.at("/type");
    String type = node.asText();

    EventType eventType = EventType.forValue(type);
    if (eventType == null) {
      // This does actually throw an exception, but this is how Jackson rolls.
      String sorted = Arrays.stream(EventType.values()).map(Enum::name).sorted().collect(Collectors.joining(", "));
      return (EventType) ctxt.handleUnexpectedToken(BaseEvent.class, node.asToken(), p,
                                                    "Expected the type field to be one of [" + sorted + "], but found [" + node.asText() + "]");
    }

    return eventType;
  }
}
