/*
 * Copyright (c) 2022-2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import java.util.List;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.WebAuthnCredential;
import io.fusionauth.domain.api.webauthn.enums.AuthenticatorTransport;
import io.fusionauth.domain.api.webauthn.enums.PublicKeyCredentialType;

/**
 * Contains attributes for the Relying Party to refer to an existing public key credential as an input parameter.
 *
 * @author Spencer Witt
 */
public class PublicKeyCredentialDescriptor implements Buildable<PublicKeyCredentialDescriptor> {
  /**
   * The base64URL-encoded credential ID.
   */
  public String id;

  /**
   * A hint as to how the client may communicate with the <i>authenticator</i> managing this credential.
   */
  public List<AuthenticatorTransport> transports;

  /**
   * The type of credential being referenced.
   */
  public PublicKeyCredentialType type = PublicKeyCredentialType.PUBLIC_KEY;

  @JacksonConstructor
  public PublicKeyCredentialDescriptor() {
  }

  public PublicKeyCredentialDescriptor(String id) {
    this.id = id;
  }

  public PublicKeyCredentialDescriptor(WebAuthnCredential webauthnCredential) {
    this.id = webauthnCredential.credentialId;
    this.transports = webauthnCredential.transports;
  }
}
