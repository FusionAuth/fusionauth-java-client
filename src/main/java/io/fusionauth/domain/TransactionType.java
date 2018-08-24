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

/**
 * The transaction types for Webhooks and other event systems within FusionAuth.
 *
 * @author Brian Pontarelli
 */
public enum TransactionType {
  None,
  Any,
  SimpleMajority,
  SuperMajority,
  AbsoluteMajority;

  public boolean success(int size, int successCount) {
    switch (this) {
      case None:
        return true;
      case Any:
        return successCount > 0;
      case SimpleMajority:
        return successCount >= ((double) size / 2.0d);
      case SuperMajority:
        return successCount >= ((double) size / 3.0d) * 2.0d;
      case AbsoluteMajority:
        return successCount == size;
    }

    return false;
  }
}
