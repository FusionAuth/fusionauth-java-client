/*
 * Copyright (c) 2021, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.ip;

import java.util.Objects;

/**
 * @author Brett Guy
 */
public class IPAddressRangeNode implements Comparable<IPAddressRangeNode> {
  public long endIpAddress;

  public long startIpAddress;

  private IPAddressRangeNode left;

  private IPAddressRangeNode right;

  public IPAddressRangeNode(long startIpAddress, long endIpAddress) {
    this.startIpAddress = startIpAddress;
    this.endIpAddress = endIpAddress;
  }

  @Override
  public int compareTo(IPAddressRangeNode o) {
    int i = Long.compare(this.startIpAddress, o.startIpAddress);
    return i == 0 ? Long.compare(this.endIpAddress, o.endIpAddress) : i;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IPAddressRangeNode ipAddressRangeNode = (IPAddressRangeNode) o;
    return startIpAddress == ipAddressRangeNode.startIpAddress &&
           endIpAddress == ipAddressRangeNode.endIpAddress;
  }

  public IPAddressRangeNode getLeft() {
    return left;
  }

  public void setLeft(IPAddressRangeNode left) {
    this.left = left;
  }

  public IPAddressRangeNode getRight() {
    return right;
  }

  public void setRight(IPAddressRangeNode right) {
    this.right = right;
  }

  @Override
  public int hashCode() {
    return Objects.hash(startIpAddress, endIpAddress);
  }

  @Override
  public String toString() {
    return "[" + startIpAddress + ", " + endIpAddress + "]";
  }
}
