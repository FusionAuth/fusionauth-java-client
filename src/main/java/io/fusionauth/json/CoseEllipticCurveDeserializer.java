/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.api.webauthn.CoseEllipticCurve;

import java.io.IOException;

public class CoseEllipticCurveDeserializer extends StdDeserializer<CoseEllipticCurve> {
  @JacksonConstructor
  public CoseEllipticCurveDeserializer() {
    this(null);
  }

  public CoseEllipticCurveDeserializer(Class<CoseEllipticCurve> t) {
    super(t);
  }

  @Override
  public CoseEllipticCurve deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    int crv = p.getIntValue();
    return CoseEllipticCurve.valueOfCrv(crv);
  }
}
