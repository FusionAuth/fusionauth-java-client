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

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import static io.fusionauth.domain.internal.DatabaseObjectMapperHolder.objectMapper;

@SuppressWarnings("unused")
public interface _InternalJSONColumn {
  /**
   * <strong>Internal Use Only</strong>
   * <p>
   * Getter for use in the MyBatis SQL. Do not call this manually.
   * </p>
   *
   * @return The Map to set into SQL.
   * @throws IllegalAccessException  If a field could not be retrieved for some reason.
   * @throws NoSuchFieldException    You know the field does not exist, so why are you even trying?
   * @throws JsonProcessingException When Jackson explodes.
   */
  @JsonIgnore
  @SuppressWarnings("unchecked")
  default String getDataToDatabase() throws IllegalAccessException, NoSuchFieldException, JsonProcessingException {
    Map<String, Object> fromInstance = (Map<String, Object>) getClass().getField("data").get(this);
    Map<String, Object> forDatabase = new LinkedHashMap<>();
    forDatabase.put("data", fromInstance);

    List<Field> fields = ReflectionTools.internalDataColumns(getClass());
    for (Field field : fields) {
      Object value = field.get(this);
      forDatabase.put(field.getName(), value);
    }

    return objectMapper.writeValueAsString(forDatabase);
  }

  /**
   * <strong>Internal Use Only</strong>
   * <p>
   * Setter for use in the MyBatis SQL. Do not call this manually.
   * </p>
   *
   * @param source the string source from the database to set into this object.
   * @throws NoSuchFieldException   I think you know what you did.
   * @throws IllegalAccessException You know better than to access that.
   * @throws IOException            I "O" you know better than to ask about this one.
   */
  @JsonIgnore
  @SuppressWarnings("unchecked")
  default void setDataFromDatabase(String source) throws NoSuchFieldException, IllegalAccessException, IOException {
    Object result = objectMapper.readerFor(getClass()).readValue(source);
    List<Field> fields = ReflectionTools.internalDataColumns(getClass());
    for (Field field : fields) {
      // This is an assumption that final fields are collections of some type
      if ((field.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {
        // Check for Map and Collection
        if (Map.class.isAssignableFrom(field.getType())) {
          Map<Object, Object> sourceMap = (Map<Object, Object>) result.getClass().getField(field.getName()).get(result);
          Map<Object, Object> targetMap = (Map<Object, Object>) field.get(this);
          targetMap.putAll(sourceMap);
        } else if (Collection.class.isAssignableFrom(field.getType())) {
          Collection<Object> sourceCollection = (Collection<Object>) result.getClass().getField(field.getName()).get(result);
          Collection<Object> targetCollection = (Collection<Object>) field.get(this);
          targetCollection.addAll(sourceCollection);
        }
      } else {
        field.set(this, result.getClass().getField(field.getName()).get(result));
      }
    }

    // Put the remainder into the data map
    Map<String, Object> fromInstance = (Map<String, Object>) getClass().getField("data").get(this);
    fromInstance.clear();
    fromInstance.putAll((Map<String, Object>) result.getClass().getField("data").get(result));
  }
}
