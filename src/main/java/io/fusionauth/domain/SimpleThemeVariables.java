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

  public String backgroundImageURL;

  public String backgroundSize;

  public String borderRadius;

  public String deleteButtonColor;

  public String deleteButtonFocusColor;

  public String deleteButtonTextColor;

  public String deleteButtonTextFocusColor;

  public String errorFontColor;

  public String errorIconColor;

  public String fontColor;

  public String fontFamily;

  public boolean footerDisplay;

  public String iconBackgroundColor;

  public String iconColor;

  public String infoIconColor;

  public String inputBackgroundColor;

  public String inputIconColor;

  public String inputTextColor;

  public String linkTextColor;

  public String linkTextFocusColor;

  public String logoImageSize;

  public String logoImageURL;

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
           Objects.equals(backgroundImageURL, that.backgroundImageURL) &&
           Objects.equals(backgroundSize, that.backgroundSize) &&
           Objects.equals(borderRadius, that.borderRadius) &&
           Objects.equals(deleteButtonColor, that.deleteButtonColor) &&
           Objects.equals(deleteButtonTextColor, that.deleteButtonTextColor) &&
           Objects.equals(deleteButtonTextFocusColor, that.deleteButtonTextFocusColor) &&
           Objects.equals(deleteButtonFocusColor, that.deleteButtonFocusColor) &&
           Objects.equals(errorFontColor, that.errorFontColor) &&
           Objects.equals(errorIconColor, that.errorIconColor) &&
           Objects.equals(fontColor, that.fontColor) &&
           Objects.equals(fontFamily, that.fontFamily) &&
           Objects.equals(footerDisplay, that.footerDisplay) &&
           Objects.equals(iconBackgroundColor, that.iconBackgroundColor) &&
           Objects.equals(iconColor, that.iconColor) &&
           Objects.equals(infoIconColor, that.infoIconColor) &&
           Objects.equals(inputBackgroundColor, that.inputBackgroundColor) &&
           Objects.equals(inputIconColor, that.inputIconColor) &&
           Objects.equals(inputTextColor, that.inputTextColor) &&
           Objects.equals(linkTextColor, that.linkTextColor) &&
           Objects.equals(linkTextFocusColor, that.linkTextFocusColor) &&
           Objects.equals(logoImageSize, that.logoImageSize) &&
           Objects.equals(logoImageURL, that.logoImageURL) &&
           Objects.equals(monoFontColor, that.monoFontColor) &&
           Objects.equals(monoFontFamily, that.monoFontFamily) &&
           Objects.equals(pageBackgroundColor, that.pageBackgroundColor) &&
           Objects.equals(panelBackgroundColor, that.panelBackgroundColor) &&
           Objects.equals(primaryButtonColor, that.primaryButtonColor) &&
           Objects.equals(primaryButtonFocusColor, that.primaryButtonFocusColor) &&
           Objects.equals(primaryButtonTextColor, that.primaryButtonTextColor) &&
           Objects.equals(primaryButtonTextFocusColor, that.primaryButtonTextFocusColor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(alertBackgroundColor, alertFontColor, backgroundImageURL, backgroundSize, borderRadius, deleteButtonColor,
                        deleteButtonTextColor, deleteButtonTextFocusColor, deleteButtonFocusColor, errorFontColor, errorIconColor, fontColor, fontFamily,
                        footerDisplay, iconBackgroundColor, iconColor, infoIconColor, inputBackgroundColor, inputIconColor, inputTextColor, linkTextColor,
                        linkTextFocusColor, logoImageSize, logoImageURL, monoFontColor, monoFontFamily, pageBackgroundColor,
                        panelBackgroundColor, primaryButtonColor, primaryButtonFocusColor, primaryButtonTextColor, primaryButtonTextFocusColor);
  }
}
