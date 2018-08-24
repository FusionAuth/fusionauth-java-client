/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
 */
package io.fusionauth.client;

import io.fusionauth.domain.User;
import org.testng.annotations.Test;

/**
 * @author Brian Pontarelli
 */
public class LambdaDelegateTest {
  @Test
  public void all() {
    FusionAuthClient client = new FusionAuthClient(null, null);
    LambdaDelegate delegate = new LambdaDelegate(client, null, null);

    User user = delegate.execute(c -> c.retrieveUser(null)).user;
  }
}
