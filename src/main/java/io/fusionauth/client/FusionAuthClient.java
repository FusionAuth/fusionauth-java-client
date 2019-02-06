/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
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
package io.fusionauth.client;

import java.util.Collection;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.inversoft.error.Errors;
import com.inversoft.json.JacksonModule;
import com.inversoft.rest.ClientResponse;
import com.inversoft.rest.JSONBodyHandler;
import com.inversoft.rest.JSONResponseHandler;
import com.inversoft.rest.RESTClient;
import io.fusionauth.domain.api.ApplicationRequest;
import io.fusionauth.domain.api.ApplicationResponse;
import io.fusionauth.domain.api.AuditLogRequest;
import io.fusionauth.domain.api.AuditLogResponse;
import io.fusionauth.domain.api.AuditLogSearchRequest;
import io.fusionauth.domain.api.AuditLogSearchResponse;
import io.fusionauth.domain.api.EmailTemplateRequest;
import io.fusionauth.domain.api.EmailTemplateResponse;
import io.fusionauth.domain.api.GroupRequest;
import io.fusionauth.domain.api.GroupResponse;
import io.fusionauth.domain.api.IdentityProviderRequest;
import io.fusionauth.domain.api.IdentityProviderResponse;
import io.fusionauth.domain.api.IntegrationRequest;
import io.fusionauth.domain.api.IntegrationResponse;
import io.fusionauth.domain.api.LoginRequest;
import io.fusionauth.domain.api.LoginResponse;
import io.fusionauth.domain.api.MemberDeleteRequest;
import io.fusionauth.domain.api.MemberRequest;
import io.fusionauth.domain.api.MemberResponse;
import io.fusionauth.domain.api.OAuthConfigurationResponse;
import io.fusionauth.domain.api.PasswordValidationRulesResponse;
import io.fusionauth.domain.api.PreviewRequest;
import io.fusionauth.domain.api.PreviewResponse;
import io.fusionauth.domain.api.PublicKeyResponse;
import io.fusionauth.domain.api.SystemConfigurationRequest;
import io.fusionauth.domain.api.SystemConfigurationResponse;
import io.fusionauth.domain.api.TenantRequest;
import io.fusionauth.domain.api.TenantResponse;
import io.fusionauth.domain.api.TwoFactorRequest;
import io.fusionauth.domain.api.UserActionReasonRequest;
import io.fusionauth.domain.api.UserActionReasonResponse;
import io.fusionauth.domain.api.UserActionRequest;
import io.fusionauth.domain.api.UserActionResponse;
import io.fusionauth.domain.api.UserCommentRequest;
import io.fusionauth.domain.api.UserCommentResponse;
import io.fusionauth.domain.api.UserDeleteRequest;
import io.fusionauth.domain.api.UserRequest;
import io.fusionauth.domain.api.UserResponse;
import io.fusionauth.domain.api.WebhookRequest;
import io.fusionauth.domain.api.WebhookResponse;
import io.fusionauth.domain.api.email.SendRequest;
import io.fusionauth.domain.api.email.SendResponse;
import io.fusionauth.domain.api.identityProvider.IdentityProviderLoginRequest;
import io.fusionauth.domain.api.identityProvider.LookupResponse;
import io.fusionauth.domain.api.jwt.IssueResponse;
import io.fusionauth.domain.api.jwt.RefreshRequest;
import io.fusionauth.domain.api.jwt.RefreshResponse;
import io.fusionauth.domain.api.jwt.ValidateResponse;
import io.fusionauth.domain.api.report.DailyActiveUserReportResponse;
import io.fusionauth.domain.api.report.LoginReportResponse;
import io.fusionauth.domain.api.report.MonthlyActiveUserReportResponse;
import io.fusionauth.domain.api.report.RegistrationReportResponse;
import io.fusionauth.domain.api.report.TotalsReportResponse;
import io.fusionauth.domain.api.twoFactor.SecretResponse;
import io.fusionauth.domain.api.twoFactor.TwoFactorLoginRequest;
import io.fusionauth.domain.api.twoFactor.TwoFactorSendRequest;
import io.fusionauth.domain.api.user.ActionRequest;
import io.fusionauth.domain.api.user.ActionResponse;
import io.fusionauth.domain.api.user.ChangePasswordRequest;
import io.fusionauth.domain.api.user.ForgotPasswordRequest;
import io.fusionauth.domain.api.user.ForgotPasswordResponse;
import io.fusionauth.domain.api.user.ImportRequest;
import io.fusionauth.domain.api.user.RecentLoginResponse;
import io.fusionauth.domain.api.user.RegistrationRequest;
import io.fusionauth.domain.api.user.RegistrationResponse;
import io.fusionauth.domain.api.user.SearchRequest;
import io.fusionauth.domain.api.user.SearchResponse;
import io.fusionauth.domain.api.user.VerifyEmailResponse;
import io.fusionauth.domain.api.user.VerifyRegistrationResponse;

/**
 * Client that connects to a FusionAuth server and provides access to the full set of FusionAuth APIs.
 * <p>
 * When any method is called the return value is always a ClientResponse object. When an API call was successful, the
 * response will contain the response from the server. This might be empty or contain an success object or an error
 * object. If there was a validation error or any other type of error, this will return the Errors object in the
 * response. Additionally, if FusionAuth could not be contacted because it is down or experiencing a failure, the response
 * will contain an Exception, which could be an IOException.
 *
 * @author Brian Pontarelli
 */
@SuppressWarnings("unused")
public class FusionAuthClient {
  public static String TENANT_ID_HEADER = "X-FusionAuth-TenantId";

  public static final ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                                                                    .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
                                                                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                                                                    .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
                                                                    .configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false)
                                                                    .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
                                                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                                                    .registerModule(new JacksonModule());

  private final String apiKey;

  private final String baseURL;

  private final String tenantId;

  public int connectTimeout = 2000;

  public int readTimeout = 2000;

  public FusionAuthClient(String apiKey, String baseURL) {
    this(apiKey, baseURL, null);
  }

  public FusionAuthClient(String apiKey, String baseURL, String tenantId) {
    this(apiKey, baseURL, 2000, 2000, tenantId);
  }

  public FusionAuthClient(String apiKey, String baseURL, int connectTimeout, int readTimeout) {
    this(apiKey, baseURL, connectTimeout, readTimeout, null);
  }

  public FusionAuthClient(String apiKey, String baseURL, int connectTimeout, int readTimeout, String tenantId) {
    this.apiKey = apiKey;
    this.baseURL = baseURL;
    this.connectTimeout = connectTimeout;
    this.readTimeout = readTimeout;
    this.tenantId = tenantId;
  }

  /**
  * Creates a new copy of this client with the provided tenant Id. When more than one tenant is configured in FusionAuth
  * use this method to set the tenant Id prior to making API calls.
  * <p>
  * When only one tenant is configured, or you have you have not configured tenants, setting the tenant is not necessary.
  *
  * @param tenantId The tenant Id
  * @return the new FusionAuthClient
  */
  public FusionAuthClient setTenantId(UUID tenantId) {
    if (tenantId == null) {
      return this;
    }

    return new FusionAuthClient(apiKey, baseURL, connectTimeout, readTimeout, tenantId.toString());
  }

  /**
   * Takes an action on a user. The user being actioned is called the "actionee" and the user taking the action is called the
   * "actioner". Both user ids are required. You pass the actionee's user id into the method and the actioner's is put into the
   * request object.
   *
   * @param actioneeUserId The actionee's user id.
   * @param request The action request that includes all of the information about the action being taken including
   *     the id of the action, any options and the duration (if applicable).
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> actionUser(UUID actioneeUserId, ActionRequest request) {
    return start(ActionResponse.class, Errors.class).uri("/api/user/action")
                            .urlSegment(actioneeUserId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Cancels the user action.
   *
   * @param actionId The action id of the action to cancel.
   * @param request The action request that contains the information about the cancellation.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> cancelAction(UUID actionId, ActionRequest request) {
    return start(ActionResponse.class, Errors.class).uri("/api/user/action")
                            .urlSegment(actionId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .delete()
                            .go();
  }

  /**
   * Changes a user's password using the change password Id. This usually occurs after an email has been sent to the user
   * and they clicked on a link to reset their password.
   *
   * @param changePasswordId The change password Id used to find the user. This value is generated by FusionAuth once the change password workflow has been initiated.
   * @param request The change password request that contains all of the information used to change the password.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> changePassword(String changePasswordId, ChangePasswordRequest request) {
    return start(Void.TYPE, Errors.class).uri("/api/user/change-password")
                            .urlSegment(changePasswordId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Changes a user's password using their identity (login id and password). Using a loginId instead of the changePasswordId
   * bypasses the email verification and allows a password to be changed directly without first calling the #forgotPassword
   * method.
   *
   * @param request The change password request that contains all of the information used to change the password.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> changePasswordByIdentity(ChangePasswordRequest request) {
    return start(Void.TYPE, Errors.class).uri("/api/user/change-password")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Adds a comment to the user's account.
   *
   * @param request The request object that contains all of the information used to create the user comment.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> commentOnUser(UserCommentRequest request) {
    return start(Void.TYPE, Errors.class).uri("/api/user/comment")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates an application. You can optionally specify an Id for the application, if not provided one will be generated.
   *
   * @param applicationId (Optional) The Id to use for the application. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all of the information used to create the application.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> createApplication(UUID applicationId, ApplicationRequest request) {
    return start(ApplicationResponse.class, Errors.class).uri("/api/application")
                            .urlSegment(applicationId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates a new role for an application. You must specify the id of the application you are creating the role for.
   * You can optionally specify an Id for the role inside the ApplicationRole object itself, if not provided one will be generated.
   *
   * @param applicationId The Id of the application to create the role on.
   * @param roleId (Optional) The Id of the role. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all of the information used to create the application role.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> createApplicationRole(UUID applicationId, UUID roleId, ApplicationRequest request) {
    return start(ApplicationResponse.class, Errors.class).uri("/api/application")
                            .urlSegment(applicationId)
                            .urlSegment("role")
                            .urlSegment(roleId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates an audit log with the message and user name (usually an email). Audit logs should be written anytime you
   * make changes to the FusionAuth database. When using the FusionAuth App web interface, any changes are automatically
   * written to the audit log. However, if you are accessing the API, you must write the audit logs yourself.
   *
   * @param request The request object that contains all of the information used to create the audit log entry.
   * @return The ClientResponse object.
   */
  public ClientResponse<AuditLogResponse, Errors> createAuditLog(AuditLogRequest request) {
    return start(AuditLogResponse.class, Errors.class).uri("/api/system/audit-log")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates an email template. You can optionally specify an Id for the template, if not provided one will be generated.
   *
   * @param emailTemplateId (Optional) The Id for the template. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all of the information used to create the email template.
   * @return The ClientResponse object.
   */
  public ClientResponse<EmailTemplateResponse, Errors> createEmailTemplate(UUID emailTemplateId, EmailTemplateRequest request) {
    return start(EmailTemplateResponse.class, Errors.class).uri("/api/email/template")
                            .urlSegment(emailTemplateId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates a group. You can optionally specify an Id for the group, if not provided one will be generated.
   *
   * @param groupId (Optional) The Id for the group. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all of the information used to create the group.
   * @return The ClientResponse object.
   */
  public ClientResponse<GroupResponse, Errors> createGroup(UUID groupId, GroupRequest request) {
    return start(GroupResponse.class, Errors.class).uri("/api/group")
                            .urlSegment(groupId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates a member in a group.
   *
   * @param request The request object that contains all of the information used to create the group member(s).
   * @return The ClientResponse object.
   */
  public ClientResponse<MemberResponse, Errors> createGroupMembers(MemberRequest request) {
    return start(MemberResponse.class, Errors.class).uri("/api/group/member")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates an identity provider. You can optionally specify an Id for the identity provider, if not provided one will be generated.
   *
   * @param identityProviderId (Optional) The Id of the identity provider. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all of the information used to create the identity provider.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderResponse, Errors> createIdentityProvider(UUID identityProviderId, IdentityProviderRequest request) {
    return start(IdentityProviderResponse.class, Errors.class).uri("/api/identity-provider")
                            .urlSegment(identityProviderId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates a tenant. You can optionally specify an Id for the tenant, if not provided one will be generated.
   *
   * @param tenantId (Optional) The Id for the tenant. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all of the information used to create the tenant.
   * @return The ClientResponse object.
   */
  public ClientResponse<TenantResponse, Errors> createTenant(UUID tenantId, TenantRequest request) {
    return start(TenantResponse.class, Errors.class).uri("/api/tenant")
                            .urlSegment(tenantId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates a user. You can optionally specify an Id for the user, if not provided one will be generated.
   *
   * @param userId (Optional) The Id for the user. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all of the information used to create the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> createUser(UUID userId, UserRequest request) {
    return start(UserResponse.class, Errors.class).uri("/api/user")
                            .urlSegment(userId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates a user action. This action cannot be taken on a user until this call successfully returns. Anytime after
   * that the user action can be applied to any user.
   *
   * @param userActionId (Optional) The Id for the user action. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all of the information used to create the user action.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Errors> createUserAction(UUID userActionId, UserActionRequest request) {
    return start(UserActionResponse.class, Errors.class).uri("/api/user-action")
                            .urlSegment(userActionId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates a user reason. This user action reason cannot be used when actioning a user until this call completes
   * successfully. Anytime after that the user action reason can be used.
   *
   * @param userActionReasonId (Optional) The Id for the user action reason. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all of the information used to create the user action reason.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionReasonResponse, Errors> createUserActionReason(UUID userActionReasonId, UserActionReasonRequest request) {
    return start(UserActionReasonResponse.class, Errors.class).uri("/api/user-action-reason")
                            .urlSegment(userActionReasonId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Creates a webhook. You can optionally specify an Id for the webhook, if not provided one will be generated.
   *
   * @param webhookId (Optional) The Id for the webhook. If not provided a secure random UUID will be generated.
   * @param request The request object that contains all of the information used to create the webhook.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookResponse, Errors> createWebhook(UUID webhookId, WebhookRequest request) {
    return start(WebhookResponse.class, Errors.class).uri("/api/webhook")
                            .urlSegment(webhookId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Deactivates the application with the given Id.
   *
   * @param applicationId The Id of the application to deactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deactivateApplication(UUID applicationId) {
    return start(Void.TYPE, Errors.class).uri("/api/application")
                            .urlSegment(applicationId)
                            .delete()
                            .go();
  }

  /**
   * Deactivates the user with the given Id.
   *
   * @param userId The Id of the user to deactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deactivateUser(UUID userId) {
    return start(Void.TYPE, Errors.class).uri("/api/user")
                            .urlSegment(userId)
                            .delete()
                            .go();
  }

  /**
   * Deactivates the user action with the given Id.
   *
   * @param userActionId The Id of the user action to deactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deactivateUserAction(UUID userActionId) {
    return start(Void.TYPE, Errors.class).uri("/api/user-action")
                            .urlSegment(userActionId)
                            .delete()
                            .go();
  }

  /**
   * Deactivates the users with the given ids.
   *
   * @param userIds The ids of the users to deactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deactivateUsers(Collection<UUID> userIds) {
    return start(Void.TYPE, Errors.class).uri("/api/user/bulk")
                            .urlParameter("userId", userIds)
                            .delete()
                            .go();
  }

  /**
   * Hard deletes an application. This is a dangerous operation and should not be used in most circumstances. This will
   * delete the application, any registrations for that application, metrics and reports for the application, all the
   * roles for the application, and any other data associated with the application. This operation could take a very
   * long time, depending on the amount of data in your database.
   *
   * @param applicationId The Id of the application to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteApplication(UUID applicationId) {
    return start(Void.TYPE, Errors.class).uri("/api/application")
                            .urlSegment(applicationId)
                            .urlParameter("hardDelete", true)
                            .delete()
                            .go();
  }

  /**
   * Hard deletes an application role. This is a dangerous operation and should not be used in most circumstances. This
   * permanently removes the given role from all users that had it.
   *
   * @param applicationId The Id of the application to deactivate.
   * @param roleId The Id of the role to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteApplicationRole(UUID applicationId, UUID roleId) {
    return start(Void.TYPE, Errors.class).uri("/api/application")
                            .urlSegment(applicationId)
                            .urlSegment("role")
                            .urlSegment(roleId)
                            .delete()
                            .go();
  }

  /**
   * Deletes the email template for the given Id.
   *
   * @param emailTemplateId The Id of the email template to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteEmailTemplate(UUID emailTemplateId) {
    return start(Void.TYPE, Errors.class).uri("/api/email/template")
                            .urlSegment(emailTemplateId)
                            .delete()
                            .go();
  }

  /**
   * Deletes the group for the given Id.
   *
   * @param groupId The Id of the group to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteGroup(UUID groupId) {
    return start(Void.TYPE, Errors.class).uri("/api/group")
                            .urlSegment(groupId)
                            .delete()
                            .go();
  }

  /**
   * Removes users as members of a group.
   *
   * @param request The member request that contains all of the information used to remove members to the group.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteGroupMembers(MemberDeleteRequest request) {
    return start(Void.TYPE, Errors.class).uri("/api/group/member")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .delete()
                            .go();
  }

  /**
   * Deletes the identity provider for the given Id.
   *
   * @param identityProviderId The Id of the identity provider to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteIdentityProvider(UUID identityProviderId) {
    return start(Void.TYPE, Errors.class).uri("/api/identity-provider")
                            .urlSegment(identityProviderId)
                            .delete()
                            .go();
  }

  /**
   * Deletes the user registration for the given user and application.
   *
   * @param userId The Id of the user whose registration is being deleted.
   * @param applicationId The Id of the application to remove the registration for.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteRegistration(UUID userId, UUID applicationId) {
    return start(Void.TYPE, Errors.class).uri("/api/user/registration")
                            .urlSegment(userId)
                            .urlSegment(applicationId)
                            .delete()
                            .go();
  }

  /**
   * Deletes the tenant for the given Id.
   *
   * @param tenantId The Id of the tenant to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteTenant(UUID tenantId) {
    return start(Void.TYPE, Errors.class).uri("/api/tenant")
                            .urlSegment(tenantId)
                            .delete()
                            .go();
  }

  /**
   * Deletes the user for the given Id. This permanently deletes all information, metrics, reports and data associated
   * with the user.
   *
   * @param userId The Id of the user to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteUser(UUID userId) {
    return start(Void.TYPE, Errors.class).uri("/api/user")
                            .urlSegment(userId)
                            .urlParameter("hardDelete", true)
                            .delete()
                            .go();
  }

  /**
   * Deletes the user action for the given Id. This permanently deletes the user action and also any history and logs of
   * the action being applied to any users.
   *
   * @param userActionId The Id of the user action to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteUserAction(UUID userActionId) {
    return start(Void.TYPE, Errors.class).uri("/api/user-action")
                            .urlSegment(userActionId)
                            .urlParameter("hardDelete", true)
                            .delete()
                            .go();
  }

  /**
   * Deletes the user action reason for the given Id.
   *
   * @param userActionReasonId The Id of the user action reason to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteUserActionReason(UUID userActionReasonId) {
    return start(Void.TYPE, Errors.class).uri("/api/user-action-reason")
                            .urlSegment(userActionReasonId)
                            .delete()
                            .go();
  }

  /**
   * Deletes the users with the given ids.
   *
   * @param request The ids of the users to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteUsers(UserDeleteRequest request) {
    return start(Void.TYPE, Errors.class).uri("/api/user/bulk")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .delete()
                            .go();
  }

  /**
   * Deletes the webhook for the given Id.
   *
   * @param webhookId The Id of the webhook to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> deleteWebhook(UUID webhookId) {
    return start(Void.TYPE, Errors.class).uri("/api/webhook")
                            .urlSegment(webhookId)
                            .delete()
                            .go();
  }

  /**
   * Disable Two Factor authentication for a user.
   *
   * @param userId The Id of the User for which you're disabling Two Factor authentication.
   * @param code The Two Factor code used verify the the caller knows the Two Factor secret.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> disableTwoFactor(UUID userId, String code) {
    return start(Void.TYPE, Errors.class).uri("/api/user/two-factor")
                            .urlParameter("userId", userId)
                            .urlParameter("code", code)
                            .delete()
                            .go();
  }

  /**
   * Enable Two Factor authentication for a user.
   *
   * @param userId The Id of the user to enable Two Factor authentication.
   * @param request The two factor enable request information.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> enableTwoFactor(UUID userId, TwoFactorRequest request) {
    return start(Void.TYPE, Errors.class).uri("/api/user/two-factor")
                            .urlSegment(userId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Exchange a refresh token for a new JWT.
   *
   * @param request The refresh request.
   * @return The ClientResponse object.
   */
  public ClientResponse<RefreshResponse, Errors> exchangeRefreshTokenForJWT(RefreshRequest request) {
    return start(RefreshResponse.class, Errors.class).uri("/api/jwt/refresh")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Begins the forgot password sequence, which kicks off an email to the user so that they can reset their password.
   *
   * @param request The request that contains the information about the user so that they can be emailed.
   * @return The ClientResponse object.
   */
  public ClientResponse<ForgotPasswordResponse, Errors> forgotPassword(ForgotPasswordRequest request) {
    return start(ForgotPasswordResponse.class, Errors.class).uri("/api/user/forgot-password")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Generate a new Email Verification Id to be used with the Verify Email API. This API will not attempt to send an
   * email to the User. This API may be used to collect the verificationId for use with a third party system.
   *
   * @param email The email address of the user that needs a new verification email.
   * @return The ClientResponse object.
   */
  public ClientResponse<VerifyEmailResponse, Void> generateEmailVerificationId(String email) {
    return start(VerifyEmailResponse.class, Void.TYPE).uri("/api/user/verify-email")
                            .urlParameter("email", email)
                            .urlParameter("sendVerifyPasswordEmail", false)
                            .put()
                            .go();
  }

  /**
   * Generate a new Application Registration Verification Id to be used with the Verify Registration API. This API will not attempt to send an
   * email to the User. This API may be used to collect the verificationId for use with a third party system.
   *
   * @param email The email address of the user that needs a new verification email.
   * @param applicationId The Id of the application to be verified.
   * @return The ClientResponse object.
   */
  public ClientResponse<VerifyRegistrationResponse, Void> generateRegistrationVerificationId(String email, UUID applicationId) {
    return start(VerifyRegistrationResponse.class, Void.TYPE).uri("/api/user/verify-registration")
                            .urlParameter("email", email)
                            .urlParameter("sendVerifyPasswordEmail", false)
                            .urlParameter("applicationId", applicationId)
                            .put()
                            .go();
  }

  /**
   * Generate a Two Factor secret that can be used to enable Two Factor authentication for a User. The response will contain
   * both the secret and a Base32 encoded form of the secret which can be shown to a User when using a 2 Step Authentication
   * application such as Google Authenticator.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<SecretResponse, Void> generateTwoFactorSecret() {
    return start(SecretResponse.class, Void.TYPE).uri("/api/two-factor/secret")
                            .get()
                            .go();
  }

  /**
   * Generate a Two Factor secret that can be used to enable Two Factor authentication for a User. The response will contain
   * both the secret and a Base32 encoded form of the secret which can be shown to a User when using a 2 Step Authentication
   * application such as Google Authenticator.
   *
   * @param encodedJWT The encoded JWT (access token).
   * @return The ClientResponse object.
   */
  public ClientResponse<SecretResponse, Void> generateTwoFactorSecretUsingJWT(String encodedJWT) {
    return start(SecretResponse.class, Void.TYPE).uri("/api/two-factor/secret")
                            .authorization("JWT " + encodedJWT)
                            .get()
                            .go();
  }

  /**
   * Handles login via third-parties including Social login, external OAuth and OpenID Connect, and other
   * login systems.
   *
   * @param request The third-party login request that contains information from the third-party login
   *     providers that FusionAuth uses to reconcile the user's account.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> identityProviderLogin(IdentityProviderLoginRequest request) {
    return start(LoginResponse.class, Errors.class).uri("/api/identity-provider/login")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Bulk imports multiple users. This does some validation, but then tries to run batch inserts of users. This reduces
   * latency when inserting lots of users. Therefore, the error response might contain some information about failures,
   * but it will likely be pretty generic.
   *
   * @param request The request that contains all of the information about all of the users to import.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> importUsers(ImportRequest request) {
    return start(Void.TYPE, Errors.class).uri("/api/user/import")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Issue a new access token (JWT) for the requested Application after ensuring the provided JWT is valid. A valid
   * access token is properly signed and not expired.
   * <p>
   * This API may be used in an SSO configuration to issue new tokens for another application after the user has
   * obtained a valid token from authentication.
   *
   * @param applicationId The Application Id for which you are requesting a new access token be issued.
   * @param encodedJWT The encoded JWT (access token).
   * @return The ClientResponse object.
   */
  public ClientResponse<IssueResponse, Errors> issueJWT(UUID applicationId, String encodedJWT) {
    return start(IssueResponse.class, Errors.class).uri("/api/jwt/issue")
                            .authorization("JWT " + encodedJWT)
                            .urlParameter("applicationId", applicationId)
                            .get()
                            .go();
  }

  /**
   * Logs a user in.
   *
   * @param request The login request that contains the user credentials used to log them in.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> login(LoginRequest request) {
    return start(LoginResponse.class, Errors.class).uri("/api/login")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Sends a ping to FusionAuth indicating that the user was automatically logged into an application. When using
   * FusionAuth's SSO or your own, you should call this if the user is already logged in centrally, but accesses an
   * application where they no longer have a session. This helps correctly track login counts, times and helps with
   * reporting.
   *
   * @param userId The Id of the user that was logged in.
   * @param applicationId The Id of the application that they logged into.
   * @param callerIPAddress (Optional) The IP address of the end-user that is logging in. If a null value is provided
   *     the IP address will be that of the client or last proxy that sent the request.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> loginPing(UUID userId, UUID applicationId, String callerIPAddress) {
    return start(Void.TYPE, Errors.class).uri("/api/login")
                            .urlSegment(userId)
                            .urlSegment(applicationId)
                            .urlParameter("ipAddress", callerIPAddress)
                            .put()
                            .go();
  }

  /**
   * The Logout API is intended to be used to remove the refresh token and access token cookies if they exist on the
   * client and revoke the refresh token stored. This API does nothing if the request does not contain an access
   * token or refresh token cookies.
   *
   * @param global When this value is set to true all of the refresh tokens issued to the owner of the
   *     provided token will be revoked.
   * @param refreshToken (Optional) The refresh_token as a request parameter instead of coming in via a cookie.
   *     If provided this takes precedence over the cookie.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> logout(boolean global, String refreshToken) {
    return start(Void.TYPE, Void.TYPE).uri("/api/logout")
                            .urlParameter("global", global)
                            .urlParameter("refreshToken", refreshToken)
                            .post()
                            .go();
  }

  /**
   * Retrieves the identity provider for the given domain. A 200 response code indicates the domain is managed
   * by a registered identity provider. A 404 indicates the domain is not managed.
   *
   * @param domain The domain or email address to lookup.
   * @return The ClientResponse object.
   */
  public ClientResponse<LookupResponse, Void> lookupIdentityProvider(String domain) {
    return start(LookupResponse.class, Void.TYPE).uri("/api/identity-provider/lookup")
                            .urlParameter("domain", domain)
                            .get()
                            .go();
  }

  /**
   * Modifies a temporal user action by changing the expiration of the action and optionally adding a comment to the
   * action.
   *
   * @param actionId The Id of the action to modify. This is technically the user action log id.
   * @param request The request that contains all of the information about the modification.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> modifyAction(UUID actionId, ActionRequest request) {
    return start(ActionResponse.class, Errors.class).uri("/api/user/action")
                            .urlSegment(actionId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Reactivates the application with the given Id.
   *
   * @param applicationId The Id of the application to reactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> reactivateApplication(UUID applicationId) {
    return start(ApplicationResponse.class, Errors.class).uri("/api/application")
                            .urlSegment(applicationId)
                            .urlParameter("reactivate", true)
                            .put()
                            .go();
  }

  /**
   * Reactivates the user with the given Id.
   *
   * @param userId The Id of the user to reactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> reactivateUser(UUID userId) {
    return start(UserResponse.class, Errors.class).uri("/api/user")
                            .urlSegment(userId)
                            .urlParameter("reactivate", true)
                            .put()
                            .go();
  }

  /**
   * Reactivates the user action with the given Id.
   *
   * @param userActionId The Id of the user action to reactivate.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Errors> reactivateUserAction(UUID userActionId) {
    return start(UserActionResponse.class, Errors.class).uri("/api/user-action")
                            .urlSegment(userActionId)
                            .urlParameter("reactivate", true)
                            .put()
                            .go();
  }

  /**
   * Reconcile a User to FusionAuth using JWT issued from another Identity Provider.
   *
   * @param request The reconcile request that contains the data to reconcile the User.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> reconcileJWT(IdentityProviderLoginRequest request) {
    return start(LoginResponse.class, Errors.class).uri("/api/jwt/reconcile")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Registers a user for an application. If you provide the User and the UserRegistration object on this request, it
   * will create the user as well as register them for the application. This is called a Full Registration. However, if
   * you only provide the UserRegistration object, then the user must already exist and they will be registered for the
   * application. The user id can also be provided and it will either be used to look up an existing user or it will be
   * used for the newly created User.
   *
   * @param userId (Optional) The Id of the user being registered for the application and optionally created.
   * @param request The request that optionally contains the User and must contain the UserRegistration.
   * @return The ClientResponse object.
   */
  public ClientResponse<RegistrationResponse, Errors> register(UUID userId, RegistrationRequest request) {
    return start(RegistrationResponse.class, Errors.class).uri("/api/user/registration")
                            .urlSegment(userId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Re-sends the verification email to the user.
   *
   * @param email The email address of the user that needs a new verification email.
   * @return The ClientResponse object.
   */
  public ClientResponse<VerifyEmailResponse, Void> resendEmailVerification(String email) {
    return start(VerifyEmailResponse.class, Void.TYPE).uri("/api/user/verify-email")
                            .urlParameter("email", email)
                            .put()
                            .go();
  }

  /**
   * Re-sends the application registration verification email to the user.
   *
   * @param email The email address of the user that needs a new verification email.
   * @param applicationId The Id of the application to be verified.
   * @return The ClientResponse object.
   */
  public ClientResponse<VerifyRegistrationResponse, Void> resendRegistrationVerification(String email, UUID applicationId) {
    return start(VerifyRegistrationResponse.class, Void.TYPE).uri("/api/user/verify-registration")
                            .urlParameter("email", email)
                            .urlParameter("applicationId", applicationId)
                            .put()
                            .go();
  }

  /**
   * Retrieves a single action log (the log of a user action that was taken on a user previously) for the given Id.
   *
   * @param actionId The Id of the action to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> retrieveAction(UUID actionId) {
    return start(ActionResponse.class, Errors.class).uri("/api/user/action")
                            .urlSegment(actionId)
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the actions for the user with the given Id. This will return all time based actions that are active,
   * and inactive as well as non-time based actions.
   *
   * @param userId The Id of the user to fetch the actions for.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> retrieveActions(UUID userId) {
    return start(ActionResponse.class, Errors.class).uri("/api/user/action")
                            .urlParameter("userId", userId)
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the actions for the user with the given Id that are currently preventing the User from logging in.
   *
   * @param userId The Id of the user to fetch the actions for.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> retrieveActionsPreventingLogin(UUID userId) {
    return start(ActionResponse.class, Errors.class).uri("/api/user/action")
                            .urlParameter("userId", userId)
                            .urlParameter("preventingLogin", true)
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the actions for the user with the given Id that are currently active.
   * An active action means one that is time based and has not been canceled, and has not ended.
   *
   * @param userId The Id of the user to fetch the actions for.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> retrieveActiveActions(UUID userId) {
    return start(ActionResponse.class, Errors.class).uri("/api/user/action")
                            .urlParameter("userId", userId)
                            .urlParameter("active", true)
                            .get()
                            .go();
  }

  /**
   * Retrieves the application for the given id or all of the applications if the id is null.
   *
   * @param applicationId (Optional) The application id.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Void> retrieveApplication(UUID applicationId) {
    return start(ApplicationResponse.class, Void.TYPE).uri("/api/application")
                            .urlSegment(applicationId)
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the applications.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Void> retrieveApplications() {
    return start(ApplicationResponse.class, Void.TYPE).uri("/api/application")
                            .get()
                            .go();
  }

  /**
   * Retrieves a single audit log for the given Id.
   *
   * @param auditLogId The Id of the audit log to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<AuditLogResponse, Errors> retrieveAuditLog(Integer auditLogId) {
    return start(AuditLogResponse.class, Errors.class).uri("/api/system/audit-log")
                            .urlSegment(auditLogId)
                            .get()
                            .go();
  }

  /**
   * Retrieves the daily active user report between the two instants. If you specify an application id, it will only
   * return the daily active counts for that application.
   *
   * @param applicationId (Optional) The application id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<DailyActiveUserReportResponse, Errors> retrieveDailyActiveReport(UUID applicationId, long start, long end) {
    return start(DailyActiveUserReportResponse.class, Errors.class).uri("/api/report/daily-active-user")
                            .urlParameter("applicationId", applicationId)
                            .urlParameter("start", start)
                            .urlParameter("end", end)
                            .get()
                            .go();
  }

  /**
   * Retrieves the email template for the given Id. If you don't specify the id, this will return all of the email templates.
   *
   * @param emailTemplateId (Optional) The Id of the email template.
   * @return The ClientResponse object.
   */
  public ClientResponse<EmailTemplateResponse, Void> retrieveEmailTemplate(UUID emailTemplateId) {
    return start(EmailTemplateResponse.class, Void.TYPE).uri("/api/email/template")
                            .urlSegment(emailTemplateId)
                            .get()
                            .go();
  }

  /**
   * Creates a preview of the email template provided in the request. This allows you to preview an email template that
   * hasn't been saved to the database yet. The entire email template does not need to be provided on the request. This
   * will create the preview based on whatever is given.
   *
   * @param request The request that contains the email template and optionally a locale to render it in.
   * @return The ClientResponse object.
   */
  public ClientResponse<PreviewResponse, Errors> retrieveEmailTemplatePreview(PreviewRequest request) {
    return start(PreviewResponse.class, Errors.class).uri("/api/email/template/preview")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Retrieves all of the email templates.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<EmailTemplateResponse, Void> retrieveEmailTemplates() {
    return start(EmailTemplateResponse.class, Void.TYPE).uri("/api/email/template")
                            .get()
                            .go();
  }

  /**
   * Retrieves the group for the given Id.
   *
   * @param groupId The Id of the group.
   * @return The ClientResponse object.
   */
  public ClientResponse<GroupResponse, Errors> retrieveGroup(UUID groupId) {
    return start(GroupResponse.class, Errors.class).uri("/api/group")
                            .urlSegment(groupId)
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the groups.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<GroupResponse, Void> retrieveGroups() {
    return start(GroupResponse.class, Void.TYPE).uri("/api/group")
                            .get()
                            .go();
  }

  /**
   * Retrieves the identity provider for the given id or all of the identity providers if the id is null.
   *
   * @param identityProviderId (Optional) The identity provider id.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderResponse, Void> retrieveIdentityProvider(UUID identityProviderId) {
    return start(IdentityProviderResponse.class, Void.TYPE).uri("/api/identity-provider")
                            .urlSegment(identityProviderId)
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the identity providers.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderResponse, Void> retrieveIdentityProviders() {
    return start(IdentityProviderResponse.class, Void.TYPE).uri("/api/identity-provider")
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the actions for the user with the given Id that are currently inactive.
   * An inactive action means one that is time based and has been canceled or has expired, or is not time based.
   *
   * @param userId The Id of the user to fetch the actions for.
   * @return The ClientResponse object.
   */
  public ClientResponse<ActionResponse, Errors> retrieveInactiveActions(UUID userId) {
    return start(ActionResponse.class, Errors.class).uri("/api/user/action")
                            .urlParameter("userId", userId)
                            .urlParameter("active", false)
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the applications that are currently inactive.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Void> retrieveInactiveApplications() {
    return start(ApplicationResponse.class, Void.TYPE).uri("/api/application")
                            .urlParameter("inactive", true)
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the user actions that are currently inactive.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Void> retrieveInactiveUserActions() {
    return start(UserActionResponse.class, Void.TYPE).uri("/api/user-action")
                            .urlParameter("inactive", true)
                            .get()
                            .go();
  }

  /**
   * Retrieves the available integrations.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<IntegrationResponse, Void> retrieveIntegration() {
    return start(IntegrationResponse.class, Void.TYPE).uri("/api/integration")
                            .get()
                            .go();
  }

  /**
   * Retrieves the Public Key configured for verifying JSON Web Tokens (JWT) by the key Id. If the key Id is provided a
   * single public key will be returned if one is found by that id. If the optional parameter key Id is not provided all
   * public keys will be returned.
   *
   * @param keyId (Optional) The Id of the public key.
   * @return The ClientResponse object.
   */
  public ClientResponse<PublicKeyResponse, Void> retrieveJWTPublicKey(String keyId) {
    return start(PublicKeyResponse.class, Void.TYPE).uri("/api/jwt/public-key")
                            .urlSegment(keyId)
                            .get()
                            .go();
  }

  /**
   * Retrieves all Public Keys configured for verifying JSON Web Tokens (JWT).
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<PublicKeyResponse, Void> retrieveJWTPublicKeys() {
    return start(PublicKeyResponse.class, Void.TYPE).uri("/api/jwt/public-key")
                            .get()
                            .go();
  }

  /**
   * Retrieves the login report between the two instants. If you specify an application id, it will only return the
   * login counts for that application.
   *
   * @param applicationId (Optional) The application id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginReportResponse, Errors> retrieveLoginReport(UUID applicationId, long start, long end) {
    return start(LoginReportResponse.class, Errors.class).uri("/api/report/login")
                            .urlParameter("applicationId", applicationId)
                            .urlParameter("start", start)
                            .urlParameter("end", end)
                            .get()
                            .go();
  }

  /**
   * Retrieves the monthly active user report between the two instants. If you specify an application id, it will only
   * return the monthly active counts for that application.
   *
   * @param applicationId (Optional) The application id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<MonthlyActiveUserReportResponse, Errors> retrieveMonthlyActiveReport(UUID applicationId, long start, long end) {
    return start(MonthlyActiveUserReportResponse.class, Errors.class).uri("/api/report/monthly-active-user")
                            .urlParameter("applicationId", applicationId)
                            .urlParameter("start", start)
                            .urlParameter("end", end)
                            .get()
                            .go();
  }

  /**
   * Retrieves the Oauth2 configuration for the application for the given Application Id.
   *
   * @param applicationId The Id of the Application to retrieve OAuth configuration.
   * @return The ClientResponse object.
   */
  public ClientResponse<OAuthConfigurationResponse, Errors> retrieveOauthConfiguration(UUID applicationId) {
    return start(OAuthConfigurationResponse.class, Errors.class).uri("/api/application")
                            .urlSegment(applicationId)
                            .urlSegment("oauth-configuration")
                            .get()
                            .go();
  }

  /**
   * Retrieves the password validation rules.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<PasswordValidationRulesResponse, Void> retrievePasswordValidationRules() {
    return start(PasswordValidationRulesResponse.class, Void.TYPE).uri("/api/system-configuration/password-validation-rules")
                            .get()
                            .go();
  }

  /**
   * Retrieves the last number of login records.
   *
   * @param offset The initial record. e.g. 0 is the last login, 100 will be the 100th most recent login.
   * @param limit (Optional, defaults to 10) The number of records to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<RecentLoginResponse, Errors> retrieveRecentLogins(int offset, Integer limit) {
    return start(RecentLoginResponse.class, Errors.class).uri("/api/user/recent-login")
                            .urlParameter("offset", offset)
                            .urlParameter("limit", limit)
                            .get()
                            .go();
  }

  /**
   * Retrieves the refresh tokens that belong to the user with the given Id.
   *
   * @param userId The Id of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<RefreshResponse, Errors> retrieveRefreshTokens(UUID userId) {
    return start(RefreshResponse.class, Errors.class).uri("/api/jwt/refresh")
                            .urlParameter("userId", userId)
                            .get()
                            .go();
  }

  /**
   * Retrieves the user registration for the user with the given id and the given application id.
   *
   * @param userId The Id of the user.
   * @param applicationId The Id of the application.
   * @return The ClientResponse object.
   */
  public ClientResponse<RegistrationResponse, Errors> retrieveRegistration(UUID userId, UUID applicationId) {
    return start(RegistrationResponse.class, Errors.class).uri("/api/user/registration")
                            .urlSegment(userId)
                            .urlSegment(applicationId)
                            .get()
                            .go();
  }

  /**
   * Retrieves the registration report between the two instants. If you specify an application id, it will only return
   * the registration counts for that application.
   *
   * @param applicationId (Optional) The application id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<RegistrationReportResponse, Errors> retrieveRegistrationReport(UUID applicationId, long start, long end) {
    return start(RegistrationReportResponse.class, Errors.class).uri("/api/report/registration")
                            .urlParameter("applicationId", applicationId)
                            .urlParameter("start", start)
                            .urlParameter("end", end)
                            .get()
                            .go();
  }

  /**
   * Retrieves the system configuration.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<SystemConfigurationResponse, Void> retrieveSystemConfiguration() {
    return start(SystemConfigurationResponse.class, Void.TYPE).uri("/api/system-configuration")
                            .get()
                            .go();
  }

  /**
   * Retrieves the tenant for the given Id.
   *
   * @param tenantId The Id of the tenant.
   * @return The ClientResponse object.
   */
  public ClientResponse<TenantResponse, Errors> retrieveTenant(UUID tenantId) {
    return start(TenantResponse.class, Errors.class).uri("/api/tenant")
                            .urlSegment(tenantId)
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the tenants.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<TenantResponse, Void> retrieveTenants() {
    return start(TenantResponse.class, Void.TYPE).uri("/api/tenant")
                            .get()
                            .go();
  }

  /**
   * Retrieves the totals report. This contains all of the total counts for each application and the global registration
   * count.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<TotalsReportResponse, Void> retrieveTotalReport() {
    return start(TotalsReportResponse.class, Void.TYPE).uri("/api/report/totals")
                            .get()
                            .go();
  }

  /**
   * Retrieves the user for the given Id.
   *
   * @param userId The Id of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUser(UUID userId) {
    return start(UserResponse.class, Errors.class).uri("/api/user")
                            .urlSegment(userId)
                            .get()
                            .go();
  }

  /**
   * Retrieves the user action for the given Id. If you pass in null for the id, this will return all of the user
   * actions.
   *
   * @param userActionId (Optional) The Id of the user action.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Void> retrieveUserAction(UUID userActionId) {
    return start(UserActionResponse.class, Void.TYPE).uri("/api/user-action")
                            .urlSegment(userActionId)
                            .get()
                            .go();
  }

  /**
   * Retrieves the user action reason for the given Id. If you pass in null for the id, this will return all of the user
   * action reasons.
   *
   * @param userActionReasonId (Optional) The Id of the user action reason.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionReasonResponse, Void> retrieveUserActionReason(UUID userActionReasonId) {
    return start(UserActionReasonResponse.class, Void.TYPE).uri("/api/user-action-reason")
                            .urlSegment(userActionReasonId)
                            .get()
                            .go();
  }

  /**
   * Retrieves all the user action reasons.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionReasonResponse, Void> retrieveUserActionReasons() {
    return start(UserActionReasonResponse.class, Void.TYPE).uri("/api/user-action-reason")
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the user actions.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Void> retrieveUserActions() {
    return start(UserActionResponse.class, Void.TYPE).uri("/api/user-action")
                            .get()
                            .go();
  }

  /**
   * Retrieves the user by a change password Id. The intended use of this API is to retrieve a user after the forgot
   * password workflow has been initiated and you may not know the user's email or username.
   *
   * @param changePasswordId The unique change password Id that was sent via email or returned by the Forgot Password API.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserByChangePasswordId(String changePasswordId) {
    return start(UserResponse.class, Errors.class).uri("/api/user")
                            .urlParameter("changePasswordId", changePasswordId)
                            .get()
                            .go();
  }

  /**
   * Retrieves the user for the given email.
   *
   * @param email The email of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserByEmail(String email) {
    return start(UserResponse.class, Errors.class).uri("/api/user")
                            .urlParameter("email", email)
                            .get()
                            .go();
  }

  /**
   * Retrieves the user for the loginId. The loginId can be either the username or the email.
   *
   * @param loginId The email or username of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserByLoginId(String loginId) {
    return start(UserResponse.class, Errors.class).uri("/api/user")
                            .urlParameter("loginId", loginId)
                            .get()
                            .go();
  }

  /**
   * Retrieves the user for the given username.
   *
   * @param username The username of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserByUsername(String username) {
    return start(UserResponse.class, Errors.class).uri("/api/user")
                            .urlParameter("username", username)
                            .get()
                            .go();
  }

  /**
   * Retrieves the user by a verificationId. The intended use of this API is to retrieve a user after the forgot
   * password workflow has been initiated and you may not know the user's email or username.
   *
   * @param verificationId The unique verification Id that has been set on the user object.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserByVerificationId(String verificationId) {
    return start(UserResponse.class, Errors.class).uri("/api/user")
                            .urlParameter("verificationId", verificationId)
                            .get()
                            .go();
  }

  /**
   * Retrieves all of the comments for the user with the given Id.
   *
   * @param userId The Id of the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserCommentResponse, Errors> retrieveUserComments(UUID userId) {
    return start(UserCommentResponse.class, Errors.class).uri("/api/user/comment")
                            .urlSegment(userId)
                            .get()
                            .go();
  }

  /**
   * Retrieves the login report between the two instants for a particular user by Id. If you specify an application id, it will only return the
   * login counts for that application.
   *
   * @param applicationId (Optional) The application id.
   * @param userId The userId id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginReportResponse, Errors> retrieveUserLoginReport(UUID applicationId, UUID userId, long start, long end) {
    return start(LoginReportResponse.class, Errors.class).uri("/api/report/login")
                            .urlParameter("applicationId", applicationId)
                            .urlParameter("userId", userId)
                            .urlParameter("start", start)
                            .urlParameter("end", end)
                            .get()
                            .go();
  }

  /**
   * Retrieves the login report between the two instants for a particular user by login Id. If you specify an application id, it will only return the
   * login counts for that application.
   *
   * @param applicationId (Optional) The application id.
   * @param loginId The userId id.
   * @param start The start instant as UTC milliseconds since Epoch.
   * @param end The end instant as UTC milliseconds since Epoch.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginReportResponse, Errors> retrieveUserLoginReportByLoginId(UUID applicationId, String loginId, long start, long end) {
    return start(LoginReportResponse.class, Errors.class).uri("/api/report/login")
                            .urlParameter("applicationId", applicationId)
                            .urlParameter("loginId", loginId)
                            .urlParameter("start", start)
                            .urlParameter("end", end)
                            .get()
                            .go();
  }

  /**
   * Retrieves the last number of login records for a user.
   *
   * @param userId The Id of the user.
   * @param offset The initial record. e.g. 0 is the last login, 100 will be the 100th most recent login.
   * @param limit (Optional, defaults to 10) The number of records to retrieve.
   * @return The ClientResponse object.
   */
  public ClientResponse<RecentLoginResponse, Errors> retrieveUserRecentLogins(UUID userId, int offset, Integer limit) {
    return start(RecentLoginResponse.class, Errors.class).uri("/api/user/recent-login")
                            .urlParameter("userId", userId)
                            .urlParameter("offset", offset)
                            .urlParameter("limit", limit)
                            .get()
                            .go();
  }

  /**
   * Retrieves the user for the given Id. This method does not use an API key, instead it uses a JSON Web Token (JWT) for authentication.
   *
   * @param encodedJWT The encoded JWT (access token).
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> retrieveUserUsingJWT(String encodedJWT) {
    return start(UserResponse.class, Errors.class).uri("/api/user")
                            .authorization("JWT " + encodedJWT)
                            .get()
                            .go();
  }

  /**
   * Retrieves the webhook for the given Id. If you pass in null for the id, this will return all the webhooks.
   *
   * @param webhookId (Optional) The Id of the webhook.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookResponse, Void> retrieveWebhook(UUID webhookId) {
    return start(WebhookResponse.class, Void.TYPE).uri("/api/webhook")
                            .urlSegment(webhookId)
                            .get()
                            .go();
  }

  /**
   * Retrieves all the webhooks.
   *
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookResponse, Void> retrieveWebhooks() {
    return start(WebhookResponse.class, Void.TYPE).uri("/api/webhook")
                            .get()
                            .go();
  }

  /**
   * Revokes a single refresh token, all tokens for a user or all tokens for an application. If you provide a user id
   * and an application id, this will delete all the refresh tokens for that user for that application.
   *
   * @param token (Optional) The refresh token to delete.
   * @param userId (Optional) The user id whose tokens to delete.
   * @param applicationId (Optional) The application id of the tokens to delete.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> revokeRefreshToken(String token, UUID userId, UUID applicationId) {
    return start(Void.TYPE, Errors.class).uri("/api/jwt/refresh")
                            .urlParameter("token", token)
                            .urlParameter("userId", userId)
                            .urlParameter("applicationId", applicationId)
                            .delete()
                            .go();
  }

  /**
   * Searches the audit logs with the specified criteria and pagination.
   *
   * @param request The search criteria and pagination information.
   * @return The ClientResponse object.
   */
  public ClientResponse<AuditLogSearchResponse, Void> searchAuditLogs(AuditLogSearchRequest request) {
    return start(AuditLogSearchResponse.class, Void.TYPE).uri("/api/system/audit-log/search")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Retrieves the users for the given ids. If any id is invalid, it is ignored.
   *
   * @param ids The user ids to search for.
   * @return The ClientResponse object.
   */
  public ClientResponse<SearchResponse, Errors> searchUsers(Collection<UUID> ids) {
    return start(SearchResponse.class, Errors.class).uri("/api/user/search")
                            .urlParameter("ids", ids)
                            .get()
                            .go();
  }

  /**
   * Retrieves the users for the given search criteria and pagination.
   *
   * @param request The search criteria and pagination constraints. Fields used: queryString, numberOfResults, startRow,
   *     and sort fields.
   * @return The ClientResponse object.
   */
  public ClientResponse<SearchResponse, Errors> searchUsersByQueryString(SearchRequest request) {
    return start(SearchResponse.class, Errors.class).uri("/api/user/search")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Send an email using an email template id. You can optionally provide <code>requestData</code> to access key value
   * pairs in the email template.
   *
   * @param emailTemplateId The id for the template.
   * @param request The send email request that contains all of the information used to send the email.
   * @return The ClientResponse object.
   */
  public ClientResponse<SendResponse, Errors> sendEmail(UUID emailTemplateId, SendRequest request) {
    return start(SendResponse.class, Errors.class).uri("/api/email/send")
                            .urlSegment(emailTemplateId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Send a Two Factor authentication code to assist in setting up Two Factor authentication or disabling.
   *
   * @param request The request object that contains all of the information used to send the code.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> sendTwoFactorCode(TwoFactorSendRequest request) {
    return start(Void.TYPE, Errors.class).uri("/api/two-factor/send")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Send a Two Factor authentication code to allow the completion of Two Factor authentication.
   *
   * @param twoFactorId The Id returned by the Login API necessary to complete Two Factor authentication.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Errors> sendTwoFactorCodeForLogin(String twoFactorId) {
    return start(Void.TYPE, Errors.class).uri("/api/two-factor/send")
                            .urlSegment(twoFactorId)
                            .post()
                            .go();
  }

  /**
   * Complete login using a 2FA challenge
   *
   * @param request The login request that contains the user credentials used to log them in.
   * @return The ClientResponse object.
   */
  public ClientResponse<LoginResponse, Errors> twoFactorLogin(TwoFactorLoginRequest request) {
    return start(LoginResponse.class, Errors.class).uri("/api/two-factor/login")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .post()
                            .go();
  }

  /**
   * Updates the application with the given Id.
   *
   * @param applicationId The Id of the application to update.
   * @param request The request that contains all of the new application information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> updateApplication(UUID applicationId, ApplicationRequest request) {
    return start(ApplicationResponse.class, Errors.class).uri("/api/application")
                            .urlSegment(applicationId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the application role with the given id for the application.
   *
   * @param applicationId The Id of the application that the role belongs to.
   * @param roleId The Id of the role to update.
   * @param request The request that contains all of the new role information.
   * @return The ClientResponse object.
   */
  public ClientResponse<ApplicationResponse, Errors> updateApplicationRole(UUID applicationId, UUID roleId, ApplicationRequest request) {
    return start(ApplicationResponse.class, Errors.class).uri("/api/application")
                            .urlSegment(applicationId)
                            .urlSegment("role")
                            .urlSegment(roleId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the email template with the given Id.
   *
   * @param emailTemplateId The Id of the email template to update.
   * @param request The request that contains all of the new email template information.
   * @return The ClientResponse object.
   */
  public ClientResponse<EmailTemplateResponse, Errors> updateEmailTemplate(UUID emailTemplateId, EmailTemplateRequest request) {
    return start(EmailTemplateResponse.class, Errors.class).uri("/api/email/template")
                            .urlSegment(emailTemplateId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the group with the given Id.
   *
   * @param groupId The Id of the group to update.
   * @param request The request that contains all of the new group information.
   * @return The ClientResponse object.
   */
  public ClientResponse<GroupResponse, Errors> updateGroup(UUID groupId, GroupRequest request) {
    return start(GroupResponse.class, Errors.class).uri("/api/group")
                            .urlSegment(groupId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the identity provider with the given Id.
   *
   * @param identityProviderId The Id of the identity provider to update.
   * @param request The request object that contains the updated identity provider.
   * @return The ClientResponse object.
   */
  public ClientResponse<IdentityProviderResponse, Errors> updateIdentityProvider(UUID identityProviderId, IdentityProviderRequest request) {
    return start(IdentityProviderResponse.class, Errors.class).uri("/api/identity-provider")
                            .urlSegment(identityProviderId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the available integrations.
   *
   * @param request The request that contains all of the new integration information.
   * @return The ClientResponse object.
   */
  public ClientResponse<IntegrationResponse, Errors> updateIntegrations(IntegrationRequest request) {
    return start(IntegrationResponse.class, Errors.class).uri("/api/integration")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the registration for the user with the given id and the application defined in the request.
   *
   * @param userId The Id of the user whose registration is going to be updated.
   * @param request The request that contains all of the new registration information.
   * @return The ClientResponse object.
   */
  public ClientResponse<RegistrationResponse, Errors> updateRegistration(UUID userId, RegistrationRequest request) {
    return start(RegistrationResponse.class, Errors.class).uri("/api/user/registration")
                            .urlSegment(userId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the system configuration.
   *
   * @param request The request that contains all of the new system configuration information.
   * @return The ClientResponse object.
   */
  public ClientResponse<SystemConfigurationResponse, Errors> updateSystemConfiguration(SystemConfigurationRequest request) {
    return start(SystemConfigurationResponse.class, Errors.class).uri("/api/system-configuration")
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the tenant with the given Id.
   *
   * @param tenantId The Id of the tenant to update.
   * @param request The request that contains all of the new tenant information.
   * @return The ClientResponse object.
   */
  public ClientResponse<TenantResponse, Errors> updateTenant(UUID tenantId, TenantRequest request) {
    return start(TenantResponse.class, Errors.class).uri("/api/tenant")
                            .urlSegment(tenantId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the user with the given Id.
   *
   * @param userId The Id of the user to update.
   * @param request The request that contains all of the new user information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserResponse, Errors> updateUser(UUID userId, UserRequest request) {
    return start(UserResponse.class, Errors.class).uri("/api/user")
                            .urlSegment(userId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the user action with the given Id.
   *
   * @param userActionId The Id of the user action to update.
   * @param request The request that contains all of the new user action information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionResponse, Errors> updateUserAction(UUID userActionId, UserActionRequest request) {
    return start(UserActionResponse.class, Errors.class).uri("/api/user-action")
                            .urlSegment(userActionId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the user action reason with the given Id.
   *
   * @param userActionReasonId The Id of the user action reason to update.
   * @param request The request that contains all of the new user action reason information.
   * @return The ClientResponse object.
   */
  public ClientResponse<UserActionReasonResponse, Errors> updateUserActionReason(UUID userActionReasonId, UserActionReasonRequest request) {
    return start(UserActionReasonResponse.class, Errors.class).uri("/api/user-action-reason")
                            .urlSegment(userActionReasonId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Updates the webhook with the given Id.
   *
   * @param webhookId The Id of the webhook to update.
   * @param request The request that contains all of the new webhook information.
   * @return The ClientResponse object.
   */
  public ClientResponse<WebhookResponse, Errors> updateWebhook(UUID webhookId, WebhookRequest request) {
    return start(WebhookResponse.class, Errors.class).uri("/api/webhook")
                            .urlSegment(webhookId)
                            .bodyHandler(new JSONBodyHandler(request, objectMapper))
                            .put()
                            .go();
  }

  /**
   * Validates the provided JWT (encoded JWT string) to ensure the token is valid. A valid access token is properly
   * signed and not expired.
   * <p>
   * This API may be used to verify the JWT as well as decode the encoded JWT into human readable identity claims.
   *
   * @param encodedJWT The encoded JWT (access token).
   * @return The ClientResponse object.
   */
  public ClientResponse<ValidateResponse, Void> validateJWT(String encodedJWT) {
    return start(ValidateResponse.class, Void.TYPE).uri("/api/jwt/validate")
                            .authorization("JWT " + encodedJWT)
                            .get()
                            .go();
  }

  /**
   * Confirms a email verification. The Id given is usually from an email sent to the user.
   *
   * @param verificationId The email verification id sent to the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> verifyEmail(String verificationId) {
    return start(Void.TYPE, Void.TYPE).uri("/api/user/verify-email")
                            .urlSegment(verificationId)
                            .post()
                            .go();
  }

  /**
   * Confirms an application registration. The Id given is usually from an email sent to the user.
   *
   * @param verificationId The registration verification Id sent to the user.
   * @return The ClientResponse object.
   */
  public ClientResponse<Void, Void> verifyRegistration(String verificationId) {
    return start(Void.TYPE, Void.TYPE).uri("/api/user/verify-registration")
                            .urlSegment(verificationId)
                            .post()
                            .go();
  }

  protected <T, U> RESTClient<T, U> start(Class<T> type, Class<U> errorType) {
    RESTClient<T, U> client = new RESTClient<>(type, errorType).authorization(apiKey)
                                               .successResponseHandler(type != Void.TYPE ? new JSONResponseHandler<>(type, objectMapper) : null)
                                               .errorResponseHandler(errorType != Void.TYPE ? new JSONResponseHandler<>(errorType, objectMapper) : null)
                                               .url(baseURL)
                                               .connectTimeout(connectTimeout)
                                               .readTimeout(readTimeout);

    if (tenantId != null) {
      client.header(TENANT_ID_HEADER, tenantId);
    }

    return client;
  }
}