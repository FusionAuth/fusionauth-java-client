/*
 * Copyright (c) 2024-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.guice;

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
