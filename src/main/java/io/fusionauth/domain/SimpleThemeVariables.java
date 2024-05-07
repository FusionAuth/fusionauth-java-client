/*
 * Copyright (c) 2024, FusionAuth, All Rights Reserved
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

import java.util.Objects;

/**
 * Theme object for values used in the css variables for simple themes.
 *
 * @author Lyle Schemmerling
 */
public class SimpleThemeVariables implements Buildable<SimpleThemeVariables> {
  public String alertBackgroundColor;

  public String alertFontColor;

  public String backgroundImageUrl;

  public String backgroundRepeat;

  public String backgroundSize;

  public String borderRadius;

  public String deleteButtonColor;

  public String deleteButtonFocusColor;

  public String deleteButtonTextColor;

  public String deleteButtonTextFocusColor;

  public String errorFontColor;

  public String fontColor;

  public String fontFamily;

  public String footerDisplay;

  public String iconBackgroundColor;

  public String iconColor;

  public String inputBackgroundColor;

  public String inputIconColor;

  public String inputTextColor;

  public String linkTextColor;

  public String linkTextFocusColor;

  public String logoImageDisplay;

  public String logoImageSize;

  public String logoImageUrl;

  public String monoFontColor;

  public String monoFontFamily;

  public String pageBackgroundColor;

  public String panelBackgroundColor;

  public String primaryButtonColor;

  public String primaryButtonFocusColor;

  public String primaryButtonTextColor;

  public String primaryButtonTextFocusColor;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimpleThemeVariables that = (SimpleThemeVariables) o;
    return Objects.equals(alertBackgroundColor, that.alertBackgroundColor) &&
           Objects.equals(alertFontColor, that.alertFontColor) &&
           Objects.equals(backgroundImageUrl, that.backgroundImageUrl) &&
           Objects.equals(backgroundRepeat, that.backgroundRepeat) &&
           Objects.equals(backgroundSize, that.backgroundSize) &&
           Objects.equals(deleteButtonColor, that.deleteButtonColor) &&
           Objects.equals(deleteButtonTextColor, that.deleteButtonTextColor) &&
           Objects.equals(deleteButtonTextFocusColor, that.deleteButtonTextFocusColor) &&
           Objects.equals(deleteButtonFocusColor, that.deleteButtonFocusColor) &&
           Objects.equals(errorFontColor, that.errorFontColor) &&
           Objects.equals(fontColor, that.fontColor) &&
           Objects.equals(fontFamily, that.fontFamily) &&
           Objects.equals(footerDisplay, that.footerDisplay) &&
           Objects.equals(iconBackgroundColor, that.iconBackgroundColor) &&
           Objects.equals(iconColor, that.iconColor) &&
           Objects.equals(inputBackgroundColor, that.inputBackgroundColor) &&
           Objects.equals(inputIconColor, that.inputIconColor) &&
           Objects.equals(inputTextColor, that.inputTextColor) &&
           Objects.equals(linkTextColor, that.linkTextColor) &&
           Objects.equals(linkTextFocusColor, that.linkTextFocusColor) &&
           Objects.equals(logoImageDisplay, that.logoImageDisplay) &&
           Objects.equals(logoImageSize, that.logoImageSize) &&
           Objects.equals(logoImageUrl, that.logoImageUrl) &&
           Objects.equals(monoFontColor, that.monoFontColor) &&
           Objects.equals(monoFontFamily, that.monoFontFamily) &&
           Objects.equals(pageBackgroundColor, that.pageBackgroundColor) &&
           Objects.equals(panelBackgroundColor, that.panelBackgroundColor) &&
           Objects.equals(primaryButtonColor, that.primaryButtonColor) &&
           Objects.equals(primaryButtonFocusColor, that.primaryButtonFocusColor) &&
           Objects.equals(primaryButtonTextColor, that.primaryButtonTextColor) &&
           Objects.equals(primaryButtonTextFocusColor, that.primaryButtonTextFocusColor) &&
           Objects.equals(borderRadius, that.borderRadius);
  }

  @Override
  public int hashCode() {
    return Objects.hash(alertBackgroundColor, alertFontColor, backgroundImageUrl, backgroundRepeat, backgroundSize, deleteButtonColor,
                        deleteButtonTextColor, deleteButtonTextFocusColor, deleteButtonFocusColor, errorFontColor, fontColor, fontFamily,
                        footerDisplay, iconBackgroundColor, iconColor, inputBackgroundColor, inputIconColor, inputTextColor, linkTextColor,
                        linkTextFocusColor, logoImageDisplay, logoImageSize, logoImageUrl, monoFontColor, monoFontFamily, pageBackgroundColor,
                        panelBackgroundColor, primaryButtonColor, primaryButtonFocusColor, primaryButtonTextColor, primaryButtonTextFocusColor,
                        borderRadius);
  }
}
