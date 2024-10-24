/*
 * Copyright (c) 2024-2024, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.fusionauth.domain.IdentityType;

/**
 * Serialize an IdentityType
 *
 * @author Daniel DeGroff
 */
public class IdentityTypeSerializer extends JsonSerializer<IdentityType> {
  @Override
  public void serialize(IdentityType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeString(value.name);
  }

  @Override
  public void serializeWithType(IdentityType value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
    super.serializeWithType(value, gen, serializers, typeSer);
  }
}
