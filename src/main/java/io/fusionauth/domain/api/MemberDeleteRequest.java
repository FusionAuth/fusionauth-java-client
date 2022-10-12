/*
 * Copyright (c) 2018-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.inversoft.json.JacksonConstructor;

/**
 * Group Member Delete Request
 *
 * @author Daniel DeGroff
 */
public class MemberDeleteRequest {
  // Group member Ids
  public List<UUID> memberIds;

  // Group Id -> List of User Ids
  public Map<UUID, List<UUID>> members;

  @JacksonConstructor
  public MemberDeleteRequest() {
  }

  public MemberDeleteRequest(List<UUID> memberIds) {
    this.memberIds = memberIds;
  }

  public MemberDeleteRequest(Map<UUID, List<UUID>> members) {
    this.members = members;
  }
}
