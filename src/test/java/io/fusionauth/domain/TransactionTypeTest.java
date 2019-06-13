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
package io.fusionauth.domain;

import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Brian Pontarelli
 */
public class TransactionTypeTest {
  @Test
  public void all() {
    assertTrue(TransactionType.None.success(10, 0));
    assertTrue(TransactionType.None.success(10, 10));

    assertFalse(TransactionType.Any.success(10, 0));
    assertTrue(TransactionType.Any.success(10, 1));
    assertTrue(TransactionType.Any.success(10, 10));

    assertFalse(TransactionType.SimpleMajority.success(10, 0));
    assertFalse(TransactionType.SimpleMajority.success(10, 4));
    assertTrue(TransactionType.SimpleMajority.success(10, 5));
    assertTrue(TransactionType.SimpleMajority.success(10, 10));

    assertFalse(TransactionType.SuperMajority.success(10, 0));
    assertFalse(TransactionType.SuperMajority.success(10, 5));
    assertFalse(TransactionType.SuperMajority.success(10, 6));
    assertTrue(TransactionType.SuperMajority.success(10, 7));
    assertTrue(TransactionType.SuperMajority.success(10, 10));

    assertFalse(TransactionType.AbsoluteMajority.success(10, 0));
    assertFalse(TransactionType.AbsoluteMajority.success(10, 9));
    assertTrue(TransactionType.AbsoluteMajority.success(10, 10));
  }
}
