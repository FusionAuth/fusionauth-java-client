/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
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
import io.fusionauth.domain.Entity;
import io.fusionauth.domain.EventInfo;

/**
 * Models the Entity Delete Complete Event.
 * <p>
 * This is different than the entity.delete event in that it will be sent after the entity has been deleted. This event cannot be made
 * transactional.
 */
public class EntityDeleteCompleteEvent extends BaseEntityEvent implements Buildable<EntityDeleteCompleteEvent>, NonTransactionalEvent {
  @JacksonConstructor
  public EntityDeleteCompleteEvent() {
  }

  public EntityDeleteCompleteEvent(EventInfo info, Entity entity) {
    super(info, entity);
  }

  @Override
  public EventType getType() {
    return EventType.EntityDeleteComplete;
  }
}
