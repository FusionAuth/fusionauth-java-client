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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;

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
  default String getDataToDatabase() throws IllegalAccessException, NoSuchFieldException, JsonProcessingException {
    return InternalJSONColumnService.getImplementation().getDataToDatabase(this);
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
  default void setDataFromDatabase(String source) throws NoSuchFieldException, IllegalAccessException, IOException {
    InternalJSONColumnService.getImplementation().setDataFromDatabase(this, source);
  }
}
