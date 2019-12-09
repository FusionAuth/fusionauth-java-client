/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.api.user;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.util.Normalizer;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * The user action request object.
 *
 * @author Brian Pontarelli
 */
public class ActionRequest {
  public ActionData action;

  public boolean broadcast;

  @JacksonConstructor
  public ActionRequest() {
  }

  public ActionRequest(ActionData action, boolean broadcast) {
    this.action = action;
    this.broadcast = broadcast;
  }

  public void normalize() {
    if (action != null) {
      action.normalize();
    }
  }

  public static class ActionData implements Buildable<ActionData> {
    public UUID actioneeUserId;

    public UUID actionerUserId;

    public List<UUID> applicationIds;

    public String comment;

    public boolean emailUser;

    public ZonedDateTime expiry;

    /**
     * Flag instructing webhooks to notify the user
     */
    public boolean notifyUser;

    public String option;

    public UUID reasonId;

    public UUID userActionId;

    public ActionData() {
    }

    public ActionData(UUID userActionId, UUID actioneeUserId, UUID actionerUserId, String comment, ZonedDateTime expiry,
                      boolean notifyUser, boolean emailUser, String option, UUID reasonId,
                      UUID... applicationIds) {
      this.userActionId = userActionId;
      this.actioneeUserId = actioneeUserId;
      this.actionerUserId = actionerUserId;
      this.comment = comment;
      this.expiry = expiry;
      this.notifyUser = notifyUser;
      this.emailUser = emailUser;
      this.option = option;
      this.reasonId = reasonId;
      normalize();
      Collections.addAll(this.applicationIds, applicationIds);
    }

    public void normalize() {
      comment = trim(comment);
      if (applicationIds == null) {
        applicationIds = new ArrayList<>();
      } else {
        Normalizer.removeEmpty(applicationIds);
      }
    }
  }
}
