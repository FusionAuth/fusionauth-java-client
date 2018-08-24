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
package io.fusionauth.domain.api.report;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;

/**
 * The response from the total report. This report stores the total numbers for each application.
 *
 * @author Brian Pontarelli
 */
public class TotalsReportResponse {
  public Map<UUID, Totals> applicationTotals = new HashMap<>();

  /**
   * Current global registrations. This value is the result of taking the total registrations for all time and
   * subtracting the users who have been deleted.
   */
  public long globalRegistrations;

  /**
   * This is the total users who have registered for all time. When a user is removed from FusionAuth the total
   * registration count is not decremented.
   */
  public long totalGlobalRegistrations;

  public static class Totals {
    /**
     * The total number of logins for all time for this application. When a user is un-registered from this application
     * or removed from FusionAuth this value is not decremented.
     */
    public long logins;

    /**
     * The current number of registrations for this application. When a user is un-registered from this application or
     * removed from FusionAuth this value is decremented.
     */
    public long registrations;

    /**
     * This is the total users who have registered for this application for all time. When a user is un-registered from
     * this application or deleted from FusionAuth the total count is not decremented.
     */
    public long totalRegistrations;

    @JacksonConstructor
    public Totals() {
    }

    public Totals(long logins, long registrations, long totalRegistrations) {
      this.logins = logins;
      this.registrations = registrations;
      this.totalRegistrations = totalRegistrations;
    }
  }
}
