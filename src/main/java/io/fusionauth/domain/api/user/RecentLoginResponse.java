/*
 * Copyright (c) 2018-2019, FusionAuth, All Rights Reserved
 */
package io.fusionauth.domain.api.user;

import java.util.ArrayList;
import java.util.List;

import io.fusionauth.domain.DisplayableRawLogin;

/**
 * Response for the user login report.
 *
 * @author Seth Musselman
 */
public class RecentLoginResponse {
  public List<DisplayableRawLogin> logins = new ArrayList<>();
}
