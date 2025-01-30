/*
 * Copyright (c) 2024, FusionAuth, All Rights Reserved
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

import java.util.Arrays;
import java.util.List;

/**
 * The possible result states of a webhook event. This tracks the success of the overall webhook transaction according to the {@link TransactionType}
 * and configured webhooks.
 *
 * @author Spencer Witt
 */
public enum WebhookEventResult {
  Failed,
  Running,
  Succeeded;

  /**
   * @return Return all available results in displayable order.
   */
  public static List<WebhookEventResult> allResults() {
    return Arrays.asList(WebhookEventResult.Succeeded,
                         WebhookEventResult.Running,
                         WebhookEventResult.Failed);

  }
}

