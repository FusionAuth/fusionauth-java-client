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

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import com.inversoft.rest.ClientResponse;

/**
 * This is a helper delegate that makes it simpler to invoke the {@link FusionAuthClient} methods and automatically deals
 * with the {@link ClientResponse} object for you.
 *
 * @author Brian Pontarelli
 */
public class LambdaDelegate {
  public final FusionAuthClient client;

  public final Consumer<ClientResponse<?, ?>> errorConsumer;

  public final Function<ClientResponse<?, ?>, ?> successFunction;

  public LambdaDelegate(FusionAuthClient client, Function<ClientResponse<?, ?>, ?> successFunction,
                        Consumer<ClientResponse<?, ?>> errorConsumer) {
    Objects.requireNonNull(client, "You can't use the lambda delegate unless you supply a FusionAuthClient");
    Objects.requireNonNull(successFunction, "You can't use the lambda delegate unless you supply a success Function and error Consumer");
    Objects.requireNonNull(errorConsumer, "You can't use the lambda delegate unless you supply a success Function and error Consumer");
    this.client = client;
    this.errorConsumer = errorConsumer;
    this.successFunction = successFunction;
  }

  /**
   * Executes the delegated FusionAuthClient call using the given function (to make the API call) and the function and
   * consumer passed into the constructor.
   *
   * @param function The Function that should invoke the FusionAuthClient method.
   * @param <T>      The success response type.
   * @param <U>      The error response type.
   * @return The success response type if the API call was successful.
   */
  @SuppressWarnings("unchecked")
  public <T, U> T execute(Function<FusionAuthClient, ClientResponse<T, U>> function) {
    ClientResponse<T, U> response = function.apply(this.client);
    if (response.wasSuccessful()) {
      return (T) successFunction.apply(response);
    } else {
      errorConsumer.accept(response);
    }

    return null;
  }
}
