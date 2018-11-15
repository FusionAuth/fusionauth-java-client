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
package io.fusionauth.domain.util;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Helper methods for normalizing values.
 *
 * @author Brian Pontarelli
 */
public final class Normalizer {
  /**
   * Removes keys whose value are null.
   *
   * @param map The map.
   */
  public static <T, U> void removeEmpty(Map<T, U> map) {
    if (map == null) {
      return;
    }

    map.keySet().removeIf(key -> map.get(key) == null);
  }

  /**
   * Removes empty values from the list.
   *
   * @param list The list.
   */
  public static <T> void removeEmpty(List<T> list) {
    list.removeIf(Objects::isNull);
  }

  /**
   * Lowercases the String in a null-safe manner.
   *
   * @param str The String to lowercase.
   * @return The String or null.
   */
  public static String toLowerCase(String str) {
    if (str == null) {
      return null;
    }

    return str.toLowerCase();
  }

  /**
   * Trims the String in a null safe manner.
   *
   * @param str The String to trim.
   * @return The trimmed String or null.
   */
  public static String trim(String str) {
    if (str == null) {
      return null;
    }

    return str.trim();
  }

  /**
   * Cleans the map by trimming all of the values.
   *
   * @param map The map to clean.
   */
  public static <T> void trimMap(Map<T, String> map) {
    map.forEach((key, value) -> {
      if (value != null) {
        map.put(key, value.trim());
      }
    });
  }

  /**
   * Trims the String in a null safe manner and if the String ends up being empty, then this returns null.
   *
   * @param str The String to trim.
   * @return The trimmed String or null.
   */
  public static String trimToNull(String str) {
    if (str == null) {
      return null;
    }

    str = str.trim();
    if (str.isEmpty()) {
      return null;
    }

    return str;
  }

  /**
   * Truncate the provided time to milliseconds.
   *
   * @param time the time
   * @return a truncated time
   */
  public static ZonedDateTime truncateToMilliseconds(ZonedDateTime time) {
    if (time == null) {
      return null;
    }

    return time.truncatedTo(ChronoUnit.MILLIS);
  }
}
