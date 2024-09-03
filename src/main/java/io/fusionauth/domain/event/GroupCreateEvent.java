/*
 * Copyright (c) 2022-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.event;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.Group;

/**
 * Models the Group Create Event.
 *
 * @author Daniel DeGroff
 */
public class GroupCreateEvent extends BaseGroupEvent implements Buildable<GroupCreateEvent> {

  @JacksonConstructor
  public GroupCreateEvent() {
  }

  public GroupCreateEvent(EventInfo info, Group group) {
    super(info, group);
  }

  @Override
  public EventType getType() {
    return EventType.GroupCreate;
  }

}
