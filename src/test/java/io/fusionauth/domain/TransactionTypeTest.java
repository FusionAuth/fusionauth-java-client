/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Brian Pontarelli
 */
@Test(groups = "unit")
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
