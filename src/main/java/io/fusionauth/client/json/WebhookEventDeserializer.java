/*
 * Copyright (c) 2024, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.fusionauth.domain.WebhookEventLog;
import io.fusionauth.domain.event.BaseEvent;
import io.fusionauth.domain.event.EventType;

/**
 * Custom JSON de-serializer for BaseEvent.
 *
 * @author Spencer Witt
 */
public class WebhookEventDeserializer extends StdDeserializer<BaseEvent> {
  public WebhookEventDeserializer() {
    super(WebhookEventLog.class);
  }

  @Override
  public BaseEvent deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = p.getCodec().readTree(p);

    BaseEvent newEvent = extractEventType(ctxt, p, node);
    return ((ObjectMapper) p.getCodec()).readerForUpdating(newEvent).readValue(node);
  }

  private BaseEvent extractEventType(DeserializationContext ctxt, JsonParser p, JsonNode eventNode)
      throws IOException {
    JsonNode node = eventNode.at("/type");
    String type = node.asText();

    EventType eventType = EventType.forValue(type);
    if (eventType == null) {
      // Handle an unexpected EventType and provide a useful error
      String sorted = Arrays.stream(EventType.values()).map(Enum::name).sorted().collect(Collectors.joining(", "));
      return (BaseEvent) ctxt.handleUnexpectedToken(BaseEvent.class, node.asToken(), p,
                                                    "Expected the type field to be one of [" + sorted + "], but found [" + node.asText() + "]");
    }

    // Assuming all of our events following this naming schema '{EventType}Event'
    String className = BaseEvent.class.getPackage().getName() + "." + eventType.name() + "Event";
    try {
      Constructor<?> constructor = Class.forName(className).getDeclaredConstructor();
      // the default constructor for an event can be private
      constructor.setAccessible(true);
      return (BaseEvent) constructor.newInstance();
    } catch (Exception e) {
      throw new IllegalStateException("Unexpected type [" + eventType + "]. This is a FusionAuth bug, could not instantiate class [" + className + "].");
    }
  }
}
