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
package io.fusionauth.domain.event;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inversoft.json.JacksonConstructor;
import io.fusionauth.domain.Buildable;
import io.fusionauth.domain.EventInfo;
import io.fusionauth.domain.SubjectTokenValidationStatus;
import io.fusionauth.domain.TokenExchangeAuditLog;
import io.fusionauth.domain.TokenExchangeDelegationSemantics;
import io.fusionauth.domain.TokenExchangeOutcome;
import io.fusionauth.domain.TokenExchangePolicyDecision;
import io.fusionauth.domain.TokenExchangePolicyEngine;

/**
 * Models the Token Exchange event. This event fires for every token exchange attempt (success and failure).
 *
 * @author FusionAuth
 */
public class TokenExchangeEvent extends BaseEvent implements Buildable<TokenExchangeEvent> {

  public Object actChain;

  public UUID exchangerClientId;

  public String intent;

  public IssuedTokenInfo issuedToken;

  public TokenExchangeOutcome outcome;

  public PolicyInfo policy;

  public List<String> requestedAudience;

  public SubjectTokenInfo subjectToken;

  @JacksonConstructor
  public TokenExchangeEvent() {
  }

  public TokenExchangeEvent(EventInfo info, TokenExchangeAuditLog auditLog) {
    super(info);
    this.tenantId = auditLog.tenantId;
    this.exchangerClientId = auditLog.exchangerClientId;
    this.intent = auditLog.intent;
    this.outcome = auditLog.outcome;

    // Build nested objects from audit log
    this.subjectToken = buildSubjectTokenInfo(auditLog);
    this.policy = buildPolicyInfo(auditLog);
    this.issuedToken = buildIssuedTokenInfo(auditLog);

    // Parse requested audience
    if (auditLog.requestedAudience != null && !auditLog.requestedAudience.trim().isEmpty()) {
      this.requestedAudience = Arrays.asList(auditLog.requestedAudience.split("\\s+"));
    }

    // Deserialize act chain from JSON string to Object
    if (auditLog.actChain != null) {
      try {
        ObjectMapper mapper = new ObjectMapper();
        this.actChain = mapper.readValue(auditLog.actChain, Object.class);
      } catch (JsonProcessingException e) {
        // If deserialization fails, leave actChain as null
        this.actChain = null;
      }
    }
  }

  private static IssuedTokenInfo buildIssuedTokenInfo(TokenExchangeAuditLog auditLog) {
    // Only build if we have issued token data
    if (auditLog.issuedTokenJti == null && auditLog.grantedAudience == null && auditLog.grantedScope == null &&
        auditLog.delegationSemantics == null) {
      return null;
    }

    IssuedTokenInfo info = new IssuedTokenInfo();
    info.jti = auditLog.issuedTokenJti;
    info.delegationSemantics = auditLog.delegationSemantics;
    info.grantedScope = auditLog.grantedScope;

    // Parse granted audience
    if (auditLog.grantedAudience != null && !auditLog.grantedAudience.trim().isEmpty()) {
      info.grantedAudience = Arrays.asList(auditLog.grantedAudience.split("\\s+"));
    } else {
      info.grantedAudience = Collections.emptyList();
    }

    return info;
  }

  private static PolicyInfo buildPolicyInfo(TokenExchangeAuditLog auditLog) {
    // Only build if we have policy data
    if (auditLog.policyEngine == null && auditLog.policyDecision == null && auditLog.policyDecisionDurationMs == null) {
      return null;
    }

    PolicyInfo info = new PolicyInfo();
    info.engine = auditLog.policyEngine;
    info.decision = auditLog.policyDecision;
    info.decisionDurationMs = auditLog.policyDecisionDurationMs;
    return info;
  }

  private static SubjectTokenInfo buildSubjectTokenInfo(TokenExchangeAuditLog auditLog) {
    // Only build if we have subject token data
    if (auditLog.subjectTokenJti == null && auditLog.subjectTokenSub == null && auditLog.subjectTokenIss == null &&
        auditLog.subjectTokenType == null && auditLog.subjectTokenValidationStatus == null) {
      return null;
    }

    SubjectTokenInfo info = new SubjectTokenInfo();
    info.jti = auditLog.subjectTokenJti;
    info.sub = auditLog.subjectTokenSub;
    info.iss = auditLog.subjectTokenIss;
    info.type = auditLog.subjectTokenType;
    info.validationStatus = auditLog.subjectTokenValidationStatus;
    return info;
  }

  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) {
      return false;
    }
    TokenExchangeEvent that = (TokenExchangeEvent) o;
    return Objects.equals(actChain, that.actChain) &&
           Objects.equals(exchangerClientId, that.exchangerClientId) &&
           Objects.equals(intent, that.intent) &&
           Objects.equals(issuedToken, that.issuedToken) &&
           outcome == that.outcome &&
           Objects.equals(policy, that.policy) &&
           Objects.equals(requestedAudience, that.requestedAudience) &&
           Objects.equals(subjectToken, that.subjectToken);
  }

  @Override
  public EventType getType() {
    return EventType.TokenExchange;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), actChain, exchangerClientId, intent, issuedToken, outcome, policy,
                        requestedAudience, subjectToken);
  }

  /**
   * Nested structure for issued token information in the webhook payload.
   */
  public static class IssuedTokenInfo {
    public TokenExchangeDelegationSemantics delegationSemantics;

    public List<String> grantedAudience;

    public String grantedScope;

    public String jti;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      IssuedTokenInfo that = (IssuedTokenInfo) o;
      return delegationSemantics == that.delegationSemantics &&
             Objects.equals(grantedAudience, that.grantedAudience) &&
             Objects.equals(grantedScope, that.grantedScope) &&
             Objects.equals(jti, that.jti);
    }

    @Override
    public int hashCode() {
      return Objects.hash(delegationSemantics, grantedAudience, grantedScope, jti);
    }
  }

  /**
   * Nested structure for policy evaluation information in the webhook payload.
   */
  public static class PolicyInfo {
    public TokenExchangePolicyDecision decision;

    public Integer decisionDurationMs;

    public TokenExchangePolicyEngine engine;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      PolicyInfo that = (PolicyInfo) o;
      return decision == that.decision &&
             Objects.equals(decisionDurationMs, that.decisionDurationMs) &&
             engine == that.engine;
    }

    @Override
    public int hashCode() {
      return Objects.hash(decision, decisionDurationMs, engine);
    }
  }

  /**
   * Nested structure for subject token information in the webhook payload.
   */
  public static class SubjectTokenInfo {
    public String iss;

    public String jti;

    public String sub;

    public String type;

    public SubjectTokenValidationStatus validationStatus;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      SubjectTokenInfo that = (SubjectTokenInfo) o;
      return Objects.equals(iss, that.iss) &&
             Objects.equals(jti, that.jti) &&
             Objects.equals(sub, that.sub) &&
             Objects.equals(type, that.type) &&
             validationStatus == that.validationStatus;
    }

    @Override
    public int hashCode() {
      return Objects.hash(iss, jti, sub, type, validationStatus);
    }
  }
}
