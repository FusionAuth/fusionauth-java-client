/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.fusionauth.domain.internal.annotation.InternalJSONColumn;

/**
 * A very local helper for caching reflection information for the domain objects.
 *
 * @author Brian Pontarelli
 */
public class ReflectionTools {
  private static final Map<Class<?>, List<Field>> internalDataColumns = new ConcurrentHashMap<>();

  public static List<Field> internalDataColumns(Class<?> type) {
    List<Field> fields = internalDataColumns.get(type);
    if (fields != null) {
      return fields;
    }

    // This could potentially happen multiple times, but since the HashMap is concurrent, it will eventually catch up and the above statement
    // will take precedence.
    fields = new ArrayList<>();
    for (Field field : type.getFields()) {
      InternalJSONColumn annotation = field.getAnnotation(InternalJSONColumn.class);
      if (annotation != null) {
        fields.add(field);
      }
    }

    // Store the fields even if empty
    internalDataColumns.put(type, fields);

    return fields;
  }
}
