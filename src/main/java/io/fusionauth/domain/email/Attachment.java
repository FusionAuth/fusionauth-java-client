/*
 * Copyright (c) 2018, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.email;

import java.util.Objects;

import com.inversoft.json.JacksonConstructor;

/**
 * This class is a simple attachment with a byte array, name and MIME type.
 *
 * @author Brian Pontarelli
 */
public class Attachment {
  public byte[] attachment;

  public String mime;

  public String name;

  @JacksonConstructor
  public Attachment() {
  }

  public Attachment(String name, String mime, byte[] attachment) {
    this.name = name;
    this.mime = mime;
    this.attachment = attachment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Attachment)) {
      return false;
    }
    Attachment that = (Attachment) o;
    return Objects.equals(attachment, that.attachment) &&
        Objects.equals(mime, that.mime) &&
        Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attachment, mime, name);
  }
}