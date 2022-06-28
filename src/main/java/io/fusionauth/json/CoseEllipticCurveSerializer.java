/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.api.webauthn.enums.CoseEllipticCurve;

import java.io.IOException;

public class CoseEllipticCurveSerializer extends StdSerializer<CoseEllipticCurve> {
  @JacksonConstructor
  public CoseEllipticCurveSerializer() {
    this(null);
  }

  public CoseEllipticCurveSerializer(Class<CoseEllipticCurve> t) {
    super(t);
  }

  @Override
  public void serialize(CoseEllipticCurve value, JsonGenerator gen,
                        SerializerProvider provider) throws IOException {
    gen.writeNumber(value.crv);
  }
}
