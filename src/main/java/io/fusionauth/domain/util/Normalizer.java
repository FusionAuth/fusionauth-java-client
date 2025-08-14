/*
 * Copyright (c) 2019-2024, FusionAuth, All Rights Reserved
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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Helper methods for normalizing values.
 *
 * @author Brian Pontarelli
 */
public final class Normalizer {
  /**
   * De-duplicate an array list.
   *
   * @param list the list to de-duplicate
   * @param <T>  the type of the list.
   */
  public static <T> void deDuplicate(List<T> list) {
    if (list == null) {
      return;
    }

    Set<T> set = new HashSet<>();
    list.removeIf(o -> !set.add(o));
  }

  /**
   * Normalize line returns
   *
   * @param str The String to normalize
   * @return The normalized string or null
   */
  public static String lineReturns(String str) {
    if (str == null) {
      return null;
    }

    return str.replaceAll("\\r\\n|\\r", "\n");
  }

  /**
   * Cleans the map by trimming all the values.
   *
   * @param map The map to clean.
   * @param <T> the type of map key
   */
  public static <T> void lineReturnsMap(Map<T, String> map) {
    map.forEach((key, value) -> {
      if (value != null) {
        map.put(key, value.replaceAll("\\r\\n|\\r", "\n"));
      }
    });
  }

  /**
   * Removes empty values from the list.
   *
   * @param list The list.
   * @param <T>  the type of list.
   */
  public static <T> void removeEmpty(List<T> list) {
    if (list == null) {
      return;
    }

    list.removeIf(Objects::isNull);
  }

  /**
   * Removes keys whose value are null.
   *
   * @param map The map.
   * @param <T> the type of map key
   * @param <U> the type of map value
   */
  public static <T, U> void removeEmpty(Map<T, U> map) {
    if (map == null) {
      return;
    }

    map.keySet().removeIf(key -> map.get(key) == null);
  }

  /**
   * Remove line returns from the string
   *
   * @param str the string to remove line returns
   * @return the normalized string w/out line returns or null
   */
  public static String removeLineReturns(String str) {
    if (str == null) {
      return null;
    }

    return str.replaceAll("\\r\\n|\\r|\\n", "");
  }

  /**
   * Lowercase the string values of the collection using the provided collection reference.
   *
   * @param collection the collection
   * @param supplier   a collections supplier
   * @param <T>        the type of collection
   */
  public static <T extends Collection<String>> void toLowerCase(Collection<String> collection, Supplier<T> supplier) {
    if (collection == null || collection.isEmpty()) {
      return;
    }

    Collection<String> lc = collection.stream().map(String::toLowerCase).collect(Collectors.toCollection(supplier));
    collection.clear();
    collection.addAll(lc);
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
   * Uppercases the String in a null-safe manner.
   *
   * @param str The String to uppercase.
   * @return The String or null.
   */
  public static String toUpperCase(String str) {
    if (str == null) {
      return null;
    }

    return str.toUpperCase();
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
   * @param <T> the type of map key
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
