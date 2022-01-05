/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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
