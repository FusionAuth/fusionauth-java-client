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
