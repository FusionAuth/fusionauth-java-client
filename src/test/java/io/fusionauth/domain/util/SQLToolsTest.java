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

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * @author Daniel DeGroff
 */
public class SQLToolsTest {

  @Test
  public void normalizeColumns() {
    assertNormalized("ORDER BY insertInstant ASC", "ORDER BY insert_instant ASC",
                     "insertInstant", "insert_instant");

    assertNormalized("ORDER BY insertInstant ASC, name DESC, lastUpdateInstant", "ORDER BY insert_instant ASC, name DESC, lastUpdateInstant",
                     "insertInstant", "insert_instant");

    assertNormalized("ORDER BY insertInstant ASC, name DESC, lastUpdateInstant", "ORDER BY insert_instant ASC, name DESC, last_update_instant",
                     "insertInstant", "insert_instant",
                     "lastUpdateInstant", "last_update_instant");

    assertNormalized("ORDER BY insertInstant ASC, name DESC, lastUpdateInstant", "ORDER BY insert_instant ASC, name DESC, last_update_instant",
                     "insertInstant", "insert_instant",
                     "lastUpdateInstant", "last_update_instant",
                     "name", "name");

    assertNormalized("ORDER BY message, insertUser", "ORDER BY message, insert_user",
                     "insertUser", "insert_user");

    assertNormalized("ORDER BY message, insertUser", "ORDER BY message, insertUser",
                     "message", "message");

    assertNormalized("ORDER BY message, insertUser", "ORDER BY message, insert_user",
                     "insertUser", "insert_user",
                     "message", "message");
  }

  @Test
  public void sanitizeOrderBy() {
    // Single column
    assertSafe("name");
    assertSafe("name ASC");
    assertSafe("name Asc");
    assertSafe("name DESC");
    assertSafe("name desc");
    assertSafe("name Desc");
    assertSafe("name Desc ");
    assertSafe(" name Desc ");
    assertSafe("_col Desc ");
    assertSafe("col_1 Desc ");
    assertSafe("col_two asc");

    // Reserved word for columns
    assertSafe("`key`");
    assertSafe("`key` ASC");

    // Multiple column
    assertSafe("name ASC, value");
    assertSafe("name Asc, value DESC");
    assertSafe("name DESC, value ASC");
    assertSafe("name desc, value asc");
    assertSafe("name Desc, value");
    assertSafe("name Desc , value ");
    assertSafe(" name Desc , value ");
    assertSafe(" name Desc , value, foo,   bar,    baz  , boom asc ");

    // Illegal
    assertUnsafe(",name");
    assertUnsafe("`name");
    assertUnsafe("`name ASC");
    assertUnsafe("name value");
    assertUnsafe("`key` `asc`");

    // Note safe
    assertUnsafe("-admin");
    assertUnsafe("7-");
    assertUnsafe("'; DROP TABLE users; -- ");
  }

  @Test
  public void toSearchString() {
    assertSearchString("fusion", "%fusion%");
    assertSearchString("fusion*", "fusion%");
    assertSearchString("*fusion*", "%fusion%");
    assertSearchString("**fusion*", "*fusion%");
    assertSearchString("*fu*", "%fu%");
    assertSearchString("*fu**", "%fu*");
    assertSearchString("*fu*sion", "%fu%sion");
    assertSearchString("*fu**sion", "%fu*sion");
    assertSearchString(null, null);

    // These are working as designed, not sure if this is what we want.
    // - We could try to be smart about % - but then we have to manage two wild cards. Or.. since we are asking our users to enter *, we treat all
    // - I think MySQL and PostgreSQL may both use a \ for an escape , so perhaps the correct way for the end user to write this would be:
    //      %0\% Percent (to match 10% Percent or 100% Percent)
    assertSearchString("*% Percent", "%% percent");
    assertSearchString("*%% Percent", "%%% percent");
  }

  private void assertNormalized(String orderBy, String expected, String... columns) {
    Map<String, String> mapping = new HashMap<>();
    for (int i = 0; i < columns.length; i = i + 2) {
      mapping.put(columns[i], columns[i + 1]);
    }

    assertEquals(SQLTools.normalizeOrderBy(orderBy, mapping), expected);
  }

  private void assertSafe(String orderBy) {
    assertEquals(SQLTools.sanitizeOrderBy(orderBy), orderBy);
  }

  private void assertSearchString(String input, String result) {
    assertEquals(SQLTools.toSearchString(input), result);
  }

  private void assertUnsafe(String orderBy) {
    assertNull(SQLTools.sanitizeOrderBy(orderBy));
  }
}
