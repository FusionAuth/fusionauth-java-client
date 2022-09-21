/*
 * Copyright (c) 2022, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.webauthn;

import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;

/**
 * Supply information on credential type and algorithm to the <i>authenticator</i>.
 *
 * @author Spencer Witt
 */
public class PublicKeyCredentialParameters implements Buildable<PublicKeyCredentialParameters> {
  /**
   * The cryptographic signature algorithm to be used with the new credential. Controls the type of asymmetric key pair to be created.
   */
  public CoseAlgorithmIdentifier alg;

  /**
   * The type of credential to be created.
   */
  public PublicKeyCredentialType type = PublicKeyCredentialType.PUBLIC_KEY;

  @JacksonConstructor
  public PublicKeyCredentialParameters() {
  }

  public PublicKeyCredentialParameters(CoseAlgorithmIdentifier alg) {
    this.alg = alg;
  }

  public PublicKeyCredentialParameters(CoseAlgorithmIdentifier alg, PublicKeyCredentialType type) {
    this.alg = alg;
    this.type = type;
  }
}
