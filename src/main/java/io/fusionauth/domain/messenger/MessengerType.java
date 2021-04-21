/*
 * Copyright (c) 2020, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.messenger;

/**
 * @author Brett Guy
 */
public enum MessengerType {
  Generic,
  Kafka,
  Twilio;

  /**
   * Return the messenger type from a a string name returning null if the value is unknown.
   *
   * @param value the string name of the messenger type
   * @return the messenger type or null if it is not one of the possible values.
   */
  public static MessengerType safeValueOf(String value) {
    if (value == null) {
      return null;
    }

    try {
      return MessengerType.valueOf(value);
    } catch (Exception e) {
      return null;
    }
  }
}
