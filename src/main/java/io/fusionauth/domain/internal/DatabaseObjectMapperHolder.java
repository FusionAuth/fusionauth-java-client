/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author Brian Pontarelli
 */
public final class DatabaseObjectMapperHolder {
  @Inject
  @Named("DatabaseObjectMapper")
  public static ObjectMapper objectMapper;
}
