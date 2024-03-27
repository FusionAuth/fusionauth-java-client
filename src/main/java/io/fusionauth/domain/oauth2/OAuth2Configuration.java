/*
 * Copyright (c) 2018-2024, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain.oauth2;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.inversoft.json.JacksonConstructor;
import com.inversoft.json.ToString;
import io.fusionauth.domain.Buildable;
import static io.fusionauth.domain.util.Normalizer.removeEmpty;
import static io.fusionauth.domain.util.Normalizer.trim;

/**
 * @author Daniel DeGroff
 */
public class OAuth2Configuration implements Buildable<OAuth2Configuration> {
  @JsonMerge(OptBoolean.FALSE)
  public List<URI> authorizedOriginURLs = new ArrayList<>();

  @JsonMerge(OptBoolean.FALSE)
  public List<URI> authorizedRedirectURLs = new ArrayList<>();

  public Oauth2AuthorizedURLValidationPolicy authorizedURLValidationPolicy = Oauth2AuthorizedURLValidationPolicy.ExactMatch;

  public ClientAuthenticationPolicy clientAuthenticationPolicy;

  public String clientId;

  public String clientSecret;

  public OAuthScopeConsentMode consentMode = OAuthScopeConsentMode.AlwaysPrompt;

  public boolean debug;

  public URI deviceVerificationURL;

  @JsonMerge(OptBoolean.FALSE)
  public Set<GrantType> enabledGrants = new TreeSet<>(Comparator.comparing(GrantType::grantName));

  public boolean generateRefreshTokens;

  public LogoutBehavior logoutBehavior = LogoutBehavior.AllApplications;

  /**
   * Logout redirect URL when calling the <code>/oauth2/logout</code> endpoint. If this is left null,
   * <code>Application.oauthConfiguration.logoutURL</code> will be used instead.
   */
  public URI logoutURL;

  public ProofKeyForCodeExchangePolicy proofKeyForCodeExchangePolicy = ProofKeyForCodeExchangePolicy.NotRequired;

  public ProvidedScopePolicy providedScopePolicy = new ProvidedScopePolicy();

  public OAuthApplicationRelationship relationship = OAuthApplicationRelationship.FirstParty;

  /**
   * @deprecated use {@link #clientAuthenticationPolicy} instead.
   */
  @Deprecated
  public boolean requireClientAuthentication = true;

  public boolean requireRegistration;

  public OAuthScopeHandlingPolicy scopeHandlingPolicy = OAuthScopeHandlingPolicy.Strict;

  public UnknownScopePolicy unknownScopePolicy = UnknownScopePolicy.Reject;

  @JacksonConstructor
  public OAuth2Configuration() {
  }

  public OAuth2Configuration(OAuth2Configuration other) {
    this.authorizedOriginURLs.addAll(other.authorizedOriginURLs);
    this.authorizedRedirectURLs.addAll(other.authorizedRedirectURLs);
    this.authorizedURLValidationPolicy = other.authorizedURLValidationPolicy;
    this.clientAuthenticationPolicy = other.clientAuthenticationPolicy;
    this.clientId = other.clientId;
    this.clientSecret = other.clientSecret;
    this.consentMode = other.consentMode;
    this.debug = other.debug;
    this.deviceVerificationURL = other.deviceVerificationURL;
    this.enabledGrants.addAll(other.enabledGrants);
    this.generateRefreshTokens = other.generateRefreshTokens;
    this.logoutBehavior = other.logoutBehavior;
    this.logoutURL = other.logoutURL;
    this.providedScopePolicy = new ProvidedScopePolicy(other.providedScopePolicy);
    this.proofKeyForCodeExchangePolicy = other.proofKeyForCodeExchangePolicy;
    this.relationship = other.relationship;
    this.requireClientAuthentication = other.requireClientAuthentication;
    this.requireRegistration = other.requireRegistration;
    this.scopeHandlingPolicy = other.scopeHandlingPolicy;
    this.unknownScopePolicy = other.unknownScopePolicy;
  }

  public OAuth2Configuration(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OAuth2Configuration)) {
      return false;
    }
    OAuth2Configuration that = (OAuth2Configuration) o;
    return debug == that.debug &&
           generateRefreshTokens == that.generateRefreshTokens &&
           requireClientAuthentication == that.requireClientAuthentication &&
           requireRegistration == that.requireRegistration &&
           Objects.equals(authorizedOriginURLs, that.authorizedOriginURLs) &&
           Objects.equals(authorizedRedirectURLs, that.authorizedRedirectURLs) &&
           Objects.equals(authorizedURLValidationPolicy, that.authorizedURLValidationPolicy) &&
           Objects.equals(clientAuthenticationPolicy, that.clientAuthenticationPolicy) &&
           Objects.equals(clientId, that.clientId) &&
           Objects.equals(clientSecret, that.clientSecret) &&
           Objects.equals(consentMode, that.consentMode) &&
           Objects.equals(deviceVerificationURL, that.deviceVerificationURL) &&
           Objects.equals(enabledGrants, that.enabledGrants) &&
           Objects.equals(logoutBehavior, that.logoutBehavior) &&
           Objects.equals(logoutURL, that.logoutURL) &&
           Objects.equals(providedScopePolicy, that.providedScopePolicy) &&
           Objects.equals(proofKeyForCodeExchangePolicy, that.proofKeyForCodeExchangePolicy) &&
           Objects.equals(relationship, that.relationship) &&
           Objects.equals(scopeHandlingPolicy, that.scopeHandlingPolicy) &&
           Objects.equals(unknownScopePolicy, that.unknownScopePolicy);
  }

  /**
   * @return the first authorized redirect URI excluding any patterns or null if one cannot be found.
   */
  @JsonIgnore
  public URI getFirstAuthorizedRedirectURLIgnoringPatterns() {
    return authorizedRedirectURLs.stream()
                                 .filter(uri -> !uri.toString().contains("*"))
                                 .findFirst()
                                 .orElse(null);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorizedOriginURLs, authorizedRedirectURLs, authorizedURLValidationPolicy, clientAuthenticationPolicy, clientId, clientSecret, consentMode, debug, deviceVerificationURL, enabledGrants, generateRefreshTokens, logoutBehavior, logoutURL, providedScopePolicy, proofKeyForCodeExchangePolicy, relationship, requireClientAuthentication, requireRegistration, scopeHandlingPolicy, unknownScopePolicy);
  }

  public void normalize() {
    removeEmpty(authorizedOriginURLs);
    removeEmpty(authorizedRedirectURLs);
    clientId = trim(clientId);
    clientSecret = trim(clientSecret);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
