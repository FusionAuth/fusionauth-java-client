/*
 * Copyright (c) 2019, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain;

import java.util.Objects;

/**
 * Something that can be required and thus also optional. This currently extends Enableable because anything that is
 * require/optional is almost always enableable as well.
 *
 * @author Brian Pontarelli
 */
public class Requirable extends Enableable {
  public boolean required;

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
