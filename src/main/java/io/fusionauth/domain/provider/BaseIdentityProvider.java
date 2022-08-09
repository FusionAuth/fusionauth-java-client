/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.provider;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Enableable;



/**
 * @author Daniel DeGroff
 */
// Do not require a setter for 'type', it is defined by the concrete class and is not mutable
@JsonIgnoreProperties(value = "type", allowGetters = true, allowSetters = false)
public abstract class BaseIdentityProvider<D extends BaseIdentityProviderApplicationConfiguration> extends Enableable  {
  // This is used for InternalJSONColumn and we don't document any custom data. If we do end up documenting custom data, remove this annotation.
  @JsonInclude(Include.NON_EMPTY)
  public final Map<String, Object> data = new HashMap<>();

  public Map<UUID, D> applicationConfiguration = new HashMap<>();

  
  public boolean debug;

  public UUID id;

  public ZonedDateTime insertInstant;

  public LambdaConfiguration lambdaConfiguration = new LambdaConfiguration();

  public ZonedDateTime lastUpdateInstant;

  
  public IdentityProviderLinkingStrategy linkingStrategy = IdentityProviderLinkingStrategy.LinkByEmail;

  public String name;

  public Map<UUID, IdentityProviderTenantConfiguration> tenantConfiguration = new HashMap<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseIdentityProvider)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    BaseIdentityProvider<?> that = (BaseIdentityProvider<?>) o;
    return debug == that.debug &&
           Objects.equals(data, that.data) &&
           Objects.equals(applicationConfiguration, that.applicationConfiguration) &&
           Objects.equals(id, that.id) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(lambdaConfiguration, that.lambdaConfiguration) &&
           Objects.equals(lastUpdateInstant, that.lastUpdateInstant) &&
           linkingStrategy == that.linkingStrategy &&
           Objects.equals(name, that.name) &&
           Objects.equals(tenantConfiguration, that.tenantConfiguration);
  }

  public abstract IdentityProviderType getType();

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), data, applicationConfiguration, debug, id, insertInstant, lambdaConfiguration, lastUpdateInstant, linkingStrategy, name, tenantConfiguration);
  }

  @JsonIgnore
  public boolean inUse() {
    return enabled && applicationConfiguration.values().stream().anyMatch(c -> c.enabled);
  }

  public boolean isEnabledForApplicationId(UUID applicationId) {
    D configuration = applicationConfiguration.get(applicationId);
    return configuration != null && configuration.enabled;
  }

  /**
   * Normalizes the data in the IdentityProvider if necessary.
   */
  public void normalize() {
  }

  protected <R> R app(UUID applicationId, Function<D, R> app) {
    D config = applicationConfiguration.get(applicationId);
    return config == null ? null : app.apply(config);
  }

  protected <R> R app(String clientId, Function<D, R> app) {
    return app(parseUUID(clientId), app);
  }

  protected <R> R lookup(Supplier<R> global, Supplier<R> app) {
    R v = app.get();
    return v != null ? v : global.get();
  }

  protected UUID parseUUID(String clientId) {
    try {
      return UUID.fromString(clientId);
    } catch (IllegalArgumentException | NullPointerException e) {
      return null;
    }
  }

  public static class LambdaConfiguration {
    public UUID reconcileId;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof LambdaConfiguration)) {
        return false;
      }
      LambdaConfiguration that = (LambdaConfiguration) o;
      return Objects.equals(reconcileId, that.reconcileId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(reconcileId);
    }

    @Override
    public String toString() {
      return ToString.toString(this);
    }
  }
}
