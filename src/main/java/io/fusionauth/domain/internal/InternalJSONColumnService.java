/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * An SPI that allows the user of the `fusionauth-java-client` library to provide the InternalJSONColumn handling (if needed). The implementation is
 * specified using the {@link #setImplementation(InternalJSONColumnService)} method.
 *
 * @author Brian Pontarelli
 */
public abstract class InternalJSONColumnService {
  private static InternalJSONColumnService Implementation;

  /**
   * @return The current implementation.
   * @throws IllegalStateException If there is no implementation specified.
   */
  public static InternalJSONColumnService getImplementation() {
    if (Implementation == null) {
      throw new IllegalStateException("You must specify an implementation of this service in order to use InternalJSONColumn in fusionauth-java-client");
    }

    return Implementation;
  }

  /**
   * Sets the implementation of this service.
   *
   * @param implementation The implementation of this SPI.
   */
  public static void setImplementation(InternalJSONColumnService implementation) {
    Implementation = implementation;
  }

  /**
   * <strong>Internal Use Only</strong>
   * <p>
   * Getter for use in the MyBatis SQL. Do not call this manually.
   * </p>
   *
   * @param object The object being stored in the database.
   * @return The Map to set into SQL.
   * @throws IllegalAccessException  If a field could not be retrieved for some reason.
   * @throws NoSuchFieldException    You know the field does not exist, so why are you even trying?
   * @throws JsonProcessingException When Jackson explodes.
   */
  public abstract String getDataToDatabase(Object object) throws IllegalAccessException, NoSuchFieldException, JsonProcessingException;

  /**
   * <strong>Internal Use Only</strong>
   * <p>
   * Setter for use in the MyBatis SQL. Do not call this manually.
   * </p>
   *
   * @param object The object being retrieved from the database.
   * @param source the string source from the database to set into this object.
   * @throws NoSuchFieldException   I think you know what you did.
   * @throws IllegalAccessException You know better than to access that.
   * @throws IOException            I "O" you know better than to ask about this one.
   */
  public abstract void setDataFromDatabase(Object object, String source) throws NoSuchFieldException, IllegalAccessException, IOException;
}
