/*
 * Copyright (c) 2020, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.message;

/**
 * @author Mikey Sleevi
 */
public interface Message {
  /**
   * @return the message type.
   */
  MessageType getType();
}
