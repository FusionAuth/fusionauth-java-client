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

import java.util.Collections;
import java.util.List;

/**
 * @author Brett Guy
 */

/**
 * An implementation of an Interval Tree used to store IP address ranges.
 *
 * https://en.wikipedia.org/wiki/Interval_tree
 */
public class IPAddressRangeTree {
  private IPAddressRangeNode root;

  public boolean contains(long address) {
    IPAddressRangeNode result = search(root, address);
    return result != null;
  }

  public void insert(IPAddressRangeNode range) {
    root = insert(root, range);
  }

  public IPAddressRangeNode insert(IPAddressRangeNode rangeRoot, IPAddressRangeNode newRange) {
    if (rangeRoot == null) {
      rangeRoot = newRange;
      return rangeRoot;
    }

    if (rangeRoot.compareTo(newRange) <= 0) {
      if (rangeRoot.getRight() == null) {
        rangeRoot.setRight(newRange);
      } else {
        insert(rangeRoot.getRight(), newRange);
      }
    } else {
      if (rangeRoot.getLeft() == null) {
        rangeRoot.setLeft(newRange);
      } else {
        insert(rangeRoot.getLeft(), newRange);
      }
    }
    return rangeRoot;
  }

  public void insertAll(List<IPAddressRangeNode> ranges) {
    // Use the median node as the root for efficiency
    if (ranges.size() > 1) {
      Collections.sort(ranges);
      int medianIdx = ranges.size() / 2;
      IPAddressRangeNode median = ranges.get(medianIdx);

      insert(median);
      ranges.remove(medianIdx);
    }
    ranges.forEach(this::insert);
  }

  private IPAddressRangeNode search(IPAddressRangeNode rangeRoot, long address) {
    if (rangeRoot == null || (rangeRoot.startIpAddress <= address && address <= rangeRoot.endIpAddress)) {
      return rangeRoot;
    }

    if (rangeRoot.endIpAddress > address) {
      return search(rangeRoot.getLeft(), address);
    }

    return search(rangeRoot.getRight(), address);
  }
}
