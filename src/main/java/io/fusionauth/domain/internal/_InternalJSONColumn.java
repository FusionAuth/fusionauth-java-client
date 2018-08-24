/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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
   * @throws IllegalAccessException If a field could not be retrieve for some reason.
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
   */
  @JsonIgnore
  @SuppressWarnings("unchecked")
  default void setDataFromDatabase(String source) throws NoSuchFieldException, IllegalAccessException, IOException {
    Object result = objectMapper.readerFor(getClass()).readValue(source);
    List<Field> fields = ReflectionTools.internalDataColumns(getClass());
    for (Field field : fields) {
      // This is an assumption that final fields are collections of some type
      if ((field.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {
        Collection sourceCollection = (Collection) result.getClass().getField(field.getName()).get(result);
        Collection targetCollection = (Collection) field.get(this);
        targetCollection.addAll(sourceCollection);
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
