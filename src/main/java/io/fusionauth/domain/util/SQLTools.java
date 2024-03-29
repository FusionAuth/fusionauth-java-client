/*
 * Copyright (c) 2021-2023, FusionAuth, All Rights Reserved
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Daniel DeGroff
 */
public class SQLTools {
  private static final Pattern EmptySpace = Pattern.compile("\\s{2,}");

  private static final Pattern ValidOrderBy = Pattern.compile(
      "(([a-z0-9_]+|`[a-z0-9_]+`)(\\s+(asc|desc))?\\s*(,\\s*(?=[a-z0-9_`])|$))+",
      Pattern.CASE_INSENSITIVE);

  public static String normalizeOrderBy(String orderBy, Map<String, String> sortableFields) {
    return normalizeOrderBy(orderBy, sortableFields, Collections.emptySet());
  }

  public static String normalizeOrderBy(String orderBy, Map<String, String> sortableFields, Set<String> nullableFields) {
    @SuppressWarnings("SimplifyStreamApiCallChains")
    List<List<String>> orderBys = Arrays.stream(orderBy.split(","))
                                        .map(s -> Arrays.stream(s.trim().split("\\s+")).collect(Collectors.toList()))
                                        .collect(Collectors.toList());

    // If any nullable fields appear in ORDER BY, add NULL sorting to be consistent between database types
    List<String> orderByWithNulls = new ArrayList<>();
    for (List<String> statement : orderBys) {
      // Only perform this transformation if:
      //  1) The ORDER BY statement for this column is `<columnName>` or `<columnName> ASC` or `<columnName> DESC`
      //  2) The column is nullable
      //
      // The statement.size() check should filter out cases where someone is manually doing a IS NULL or IS NOT NULL sort
      String field = statement.get(0);
      if (statement.size() <= 2 && nullableFields.contains(field)) {
        // If it is a nullable field, add a null sort to the clause before regular ordering
        if (statement.get(statement.size() - 1).equalsIgnoreCase("desc")) {
          // Order was DESC
          orderByWithNulls.add(field + " IS NULL");
        } else {
          // Order was ASC or implied ASC
          orderByWithNulls.add(field + " IS NOT NULL");
        }
      }

      orderByWithNulls.add(String.join(" ", statement));
    }

    orderBy = String.join(", ", orderByWithNulls);

    // replace the requested column with the actual mapped column.
    // Example: insertInstant -> insert_instant
    for (String field : sortableFields.keySet()) {
      // Use regex with word boundary to prevent replacing the same field multiple times
      orderBy = orderBy.replaceAll(String.format("\\b%s\\b", field), sortableFields.get(field));
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

    // Escape '%' and '_' as these are reserved characters in a LIKE statement.
    StringBuilder sb = new StringBuilder();
    int index = 0;
    while (index < s.length()) {
      if (s.charAt(index) == '%' || s.charAt(index) == '_') {
        sb.append('\\')
          .append(s.charAt(index));
      } else {
        sb.append(s.charAt(index));
      }

      index++;
    }

    if (sb.length() > s.length()) {
      s = sb.toString();
    }

    // If we don't have any wild cards, bookend the string with wild cards, and return.
    index = s.indexOf('*');
    if (index == -1) {
      return "%" + s.trim().toLowerCase() + "%";
    }

    // Convert '*' to '%'
    // - Allow a literal '*' to be escaped by using a double '**'.
    sb = new StringBuilder();
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
