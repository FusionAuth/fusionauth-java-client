/*
 * Copyright (c) 2026, FusionAuth, All Rights Reserved
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
package io.fusionauth.domain;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import com.inversoft.json.ToString;

/**
 * An audit record for a single token exchange attempt. Exactly one of these is written for every attempt, whether it succeeded or failed,
 * so this is the authoritative record of who exchanged what for what.
 */
public class TokenExchangeAuditLog implements Buildable<TokenExchangeAuditLog> {
  /**
   * The nested {@code act} claim chain from the issued token, serialized as JSON. Null unless a token was issued.
   */
  public String actChain;

  /**
   * The delegation semantics applied to the issued token, generally distinguishing acting-on-behalf-of from impersonation.
   */
  public TokenExchangeDelegationSemantics delegationSemantics;

  public UUID eventId;

  /**
   * The client Id of the caller performing the exchange. Null when the attempt failed before the client could be identified.
   */
  public UUID exchangerClientId;

  /**
   * The audience actually granted. Null when no token was issued.
   */
  public String grantedAudience;

  public String grantedScope;

  public ZonedDateTime insertInstant;

  /**
   * The stated purpose of the exchange, captured even on failure so denials can be attributed to an intent.
   */
  public String intent;

  public String issuedTokenJti;

  /**
   * The outcome of the attempt. Never null — a row exists precisely because an outcome was reached.
   */
  public TokenExchangeOutcome outcome;

  public TokenExchangePolicyDecision policyDecision;

  public Integer policyDecisionDurationMs;

  public TokenExchangePolicyEngine policyEngine;

  public String requestedAudience;

  public String subjectTokenIss;

  public String subjectTokenJti;

  public String subjectTokenSub;

  public String subjectTokenType;

  public SubjectTokenValidationStatus subjectTokenValidationStatus;

  public UUID tenantId;

  public TokenExchangeAuditLog() {
  }

  public TokenExchangeAuditLog(TokenExchangeAuditLog other) {
    this.actChain = other.actChain;
    this.delegationSemantics = other.delegationSemantics;
    this.eventId = other.eventId;
    this.exchangerClientId = other.exchangerClientId;
    this.grantedAudience = other.grantedAudience;
    this.grantedScope = other.grantedScope;
    this.insertInstant = other.insertInstant;
    this.intent = other.intent;
    this.issuedTokenJti = other.issuedTokenJti;
    this.outcome = other.outcome;
    this.policyDecision = other.policyDecision;
    this.policyDecisionDurationMs = other.policyDecisionDurationMs;
    this.policyEngine = other.policyEngine;
    this.requestedAudience = other.requestedAudience;
    this.subjectTokenIss = other.subjectTokenIss;
    this.subjectTokenJti = other.subjectTokenJti;
    this.subjectTokenSub = other.subjectTokenSub;
    this.subjectTokenType = other.subjectTokenType;
    this.subjectTokenValidationStatus = other.subjectTokenValidationStatus;
    this.tenantId = other.tenantId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenExchangeAuditLog that = (TokenExchangeAuditLog) o;
    return Objects.equals(actChain, that.actChain) &&
           delegationSemantics == that.delegationSemantics &&
           Objects.equals(eventId, that.eventId) &&
           Objects.equals(exchangerClientId, that.exchangerClientId) &&
           Objects.equals(grantedAudience, that.grantedAudience) &&
           Objects.equals(grantedScope, that.grantedScope) &&
           Objects.equals(insertInstant, that.insertInstant) &&
           Objects.equals(intent, that.intent) &&
           Objects.equals(issuedTokenJti, that.issuedTokenJti) &&
           outcome == that.outcome &&
           policyDecision == that.policyDecision &&
           Objects.equals(policyDecisionDurationMs, that.policyDecisionDurationMs) &&
           Objects.equals(policyEngine, that.policyEngine) &&
           Objects.equals(requestedAudience, that.requestedAudience) &&
           Objects.equals(subjectTokenIss, that.subjectTokenIss) &&
           Objects.equals(subjectTokenJti, that.subjectTokenJti) &&
           Objects.equals(subjectTokenSub, that.subjectTokenSub) &&
           Objects.equals(subjectTokenType, that.subjectTokenType) &&
           subjectTokenValidationStatus == that.subjectTokenValidationStatus &&
           Objects.equals(tenantId, that.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actChain, delegationSemantics, eventId, exchangerClientId, grantedAudience, grantedScope, insertInstant,
                        intent, issuedTokenJti, outcome, policyDecision, policyDecisionDurationMs, policyEngine, requestedAudience,
                        subjectTokenIss, subjectTokenJti, subjectTokenSub, subjectTokenType, subjectTokenValidationStatus, tenantId);
  }

  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
