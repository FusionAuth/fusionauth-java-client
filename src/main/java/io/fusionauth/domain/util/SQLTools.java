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
package io.fusionauth.domain.util;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Daniel DeGroff
 */
public class SQLTools {
  private static final Pattern EmptySpace = Pattern.compile("\\s{2,}");

  private static final Pattern ValidOrderBy = Pattern.compile(
      "(([a-z0-9_]+|`[a-z0-9_]+`)(\\s+(asc|desc))?\\s*(,\\s*(?=[a-z0-9_`])|$))+",
      Pattern.CASE_INSENSITIVE);

  public static String normalizeOrderBy(String orderBy, Map<String, String> sortableFields) {
    for (String field : sortableFields.keySet()) {
      orderBy = orderBy.replace(field, sortableFields.get(field));
    }

    return orderBy;
  }

  public static String sanitizeOrderBy(String orderBy) {
    if (orderBy == null) {
      return null;
    }

    String normalized = EmptySpace.matcher(orderBy).replaceAll(" ").trim();
    if (ValidOrderBy.matcher(normalized).matches()) {
      return orderBy;
    }

    return null;
  }

  public static String toSearchString(String s) {
    if (s == null) {
      return null;
    }

    int index = s.indexOf("*");
    if (index == -1) {
      return "%" + s.trim().toLowerCase() + "%";
    }

    StringBuilder sb = new StringBuilder();
    index = 0;
    s = s.trim().toLowerCase();
    while (index < s.length()) {

      if (s.charAt(index) == '*') {
        if (index < s.length() - 1 && s.charAt(index + 1) == '*') {
          sb.append(s.charAt(index));
          index++;
        } else {
          sb.append('%');
        }
      } else {
        sb.append(s.charAt(index));
      }

      index++;
    }

    return sb.toString();
  }
}
