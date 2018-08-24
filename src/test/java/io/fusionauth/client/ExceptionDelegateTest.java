/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client;

import io.fusionauth.domain.User;
import org.testng.annotations.Test;

/**
 * @author Brian Pontarelli
 */
public class ExceptionDelegateTest {
  @Test
  public void all() {
    FusionAuthClient client = new FusionAuthClient(null, null);
    ExceptionDelegate delegate = new ExceptionDelegate();

    User user = delegate.execute(() -> client.retrieveUser(null)).user;
  }
}
