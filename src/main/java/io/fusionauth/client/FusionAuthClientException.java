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

import com.inversoft.error.Errors;

/**
 * @author Brian Pontarelli
 */
public class FusionAuthClientException extends RuntimeException {
  public final Errors errors;

  public FusionAuthClientException(String message) {
    super(message);
    this.errors = null;
  }

  public FusionAuthClientException(Errors errors) {
    this.errors = errors;
  }

  public FusionAuthClientException(Throwable cause) {
    super(cause);
    this.errors = null;
  }
}
