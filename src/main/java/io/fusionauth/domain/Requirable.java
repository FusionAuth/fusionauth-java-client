/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;

/**
 * Something that can be required and thus also optional. This currently extends Enableable because anything that is
 * require/optional is almost always enableable as well.
 *
 * @author Brian Pontarelli
 */
public class Requirable extends Enableable {
  public boolean required;

  @JacksonConstructor
  public Requirable() {
  }

  public Requirable(Requirable other) {
    this.enabled = other.enabled;
    this.required = other.required;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Requirable)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Requirable that = (Requirable) o;
    return required == that.required;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), required);
  }
}
