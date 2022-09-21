/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.api.webauthn.CoseKeyType;

import java.io.IOException;

public class CoseKeyTypeSerializer extends StdSerializer<CoseKeyType> {
  @JacksonConstructor
  public CoseKeyTypeSerializer() {
    this(null);
  }

  public CoseKeyTypeSerializer(Class<CoseKeyType> t) {
    super(t);
  }

  @Override
  public void serialize(CoseKeyType value, JsonGenerator gen,
                        SerializerProvider provider) throws IOException {
    gen.writeNumber(value.kty);
  }
}
