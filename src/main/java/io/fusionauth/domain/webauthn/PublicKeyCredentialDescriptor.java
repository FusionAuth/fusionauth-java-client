/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.webauthn;

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.WebAuthnCredential;

/**
 * Contains attributes for the Relying Party to refer to an existing public key credential as an input parameter.
 *
 * @author Spencer Witt
 */
public class PublicKeyCredentialDescriptor implements Buildable<PublicKeyCredentialDescriptor> {
  /**
   * The base64URL-encoded credential Id.
   */
  public String id;

  /**
   * A hint as to how the client may communicate with the <i>authenticator</i> managing this credential.
   */
  public List<String> transports;

  /**
   * The type of credential being referenced.
   */
  public PublicKeyCredentialType type = PublicKeyCredentialType.publicKey;

  @JacksonConstructor
  public PublicKeyCredentialDescriptor() {
  }

  public PublicKeyCredentialDescriptor(WebAuthnCredential webauthnCredential) {
    this.id = webauthnCredential.credentialId;
    this.transports = webauthnCredential.transports;
  }
}
