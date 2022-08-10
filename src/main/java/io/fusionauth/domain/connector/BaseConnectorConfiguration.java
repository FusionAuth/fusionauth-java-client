/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.connector;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inversoft.json.ToString;



/**
 * Models a connector.
 *
 * @author Trevor Smith
 */
// Do not require a setter for 'type', it is defined by the concrete class and is not mutable
@JsonIgnoreProperties(value = "type", allowGetters = true, allowSetters = false)
public abstract class BaseConnectorConfiguration  {
  public static final UUID FUSIONAUTH_CONNECTOR_ID = UUID.fromString("e3306678-a53a-4964-9040-1c96f36dda72");

  public Map<String, Object> data = new LinkedHashMap<>();

  
  public boolean debug;

  public UUID id;

  public ZonedDateTime insertInstant;

  public ZonedDateTime lastUpdateInstant;

  public String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseConnectorConfiguration)) {
      return false;
    }
    BaseConnectorConfiguration that = (BaseConnectorConfiguration) o;
    return debug == that.debug &&
           Objects.equals(data, that.data) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           Objects.equals(name, that.name);
  }

  public abstract ConnectorType getType();

  @Override
  public int hashCode() {
    return Objects.hash(data, debug, id, insertInstant, lastUpdateInstant, name);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
