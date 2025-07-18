//
//  ViroButtonViewManager.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.viromedia.bridge.utility.ViroLog;

/**
 * ViroButtonViewManager - Interactive UI Button ViewManager
 * 
 * This ViewManager handles interactive button components in ViroReact.
 * It provides comprehensive button functionality including text, images,
 * multiple interaction states, animations, and accessibility features.
 * 
 * Key Features:
 * - Text and image content support
 * - Multiple interaction states (normal, highlighted, selected, disabled)
 * - Hover, gaze, and click interactions
 * - Customizable appearance (colors, borders, corners, dimensions)
 * - Animation support for state transitions
 * - Accessibility compliance
 * - Event callbacks for user interactions
 * - Toggle button functionality
 * - Integration with ViroReact scene graph
 */
public class ViroButtonViewManager extends SimpleViewManager<ViroButtonView> {
    
    private static final String TAG = ViroLog.getTag(ViroButtonViewManager.class);
    private static final String REACT_CLASS = "ViroButton";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroButtonView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroButtonView instance");
        return new ViroButtonView(reactContext);
    }
    
    // Button Content
    @ReactProp(name = "source")
    public void setSource(ViroButtonView view, @Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        view.setSource(source);
    }
    
    @ReactProp(name = "imageSource")
    public void setImageSource(ViroButtonView view, @Nullable ReadableMap imageSource) {
        ViroLog.debug(TAG, "Setting image source: " + imageSource);
        view.setImageSource(imageSource);
    }
    
    @ReactProp(name = "text")
    public void setText(ViroButtonView view, @Nullable String text) {
        ViroLog.debug(TAG, "Setting text: " + text);
        view.setText(text);
    }
    
    @ReactProp(name = "textColor")
    public void setTextColor(ViroButtonView view, @Nullable ReadableArray textColor) {
        ViroLog.debug(TAG, "Setting text color: " + textColor);
        view.setTextColor(textColor);
    }
    
    @ReactProp(name = "textSize", defaultFloat = 16.0f)
    public void setTextSize(ViroButtonView view, float textSize) {
        ViroLog.debug(TAG, "Setting text size: " + textSize);
        view.setTextSize(textSize);
    }
    
    @ReactProp(name = "textFont")
    public void setTextFont(ViroButtonView view, @Nullable String textFont) {
        ViroLog.debug(TAG, "Setting text font: " + textFont);
        view.setTextFont(textFont);
    }
    
    @ReactProp(name = "textStyle")
    public void setTextStyle(ViroButtonView view, @Nullable String textStyle) {
        ViroLog.debug(TAG, "Setting text style: " + textStyle);
        view.setTextStyle(textStyle);
    }
    
    // Button Appearance
    @ReactProp(name = "gazeSource")
    public void setGazeSource(ViroButtonView view, @Nullable ReadableMap gazeSource) {
        ViroLog.debug(TAG, "Setting gaze source: " + gazeSource);
        view.setGazeSource(gazeSource);
    }
    
    @ReactProp(name = "clickSource")
    public void setClickSource(ViroButtonView view, @Nullable ReadableMap clickSource) {
        ViroLog.debug(TAG, "Setting click source: " + clickSource);
        view.setClickSource(clickSource);
    }
    
    @ReactProp(name = "hoverSource")
    public void setHoverSource(ViroButtonView view, @Nullable ReadableMap hoverSource) {
        ViroLog.debug(TAG, "Setting hover source: " + hoverSource);
        view.setHoverSource(hoverSource);
    }
    
    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(ViroButtonView view, @Nullable ReadableArray backgroundColor) {
        ViroLog.debug(TAG, "Setting background color: " + backgroundColor);
        view.setBackgroundColor(backgroundColor);
    }
    
    @ReactProp(name = "borderColor")
    public void setBorderColor(ViroButtonView view, @Nullable ReadableArray borderColor) {
        ViroLog.debug(TAG, "Setting border color: " + borderColor);
        view.setBorderColor(borderColor);
    }
    
    @ReactProp(name = "borderWidth", defaultFloat = 1.0f)
    public void setBorderWidth(ViroButtonView view, float borderWidth) {
        ViroLog.debug(TAG, "Setting border width: " + borderWidth);
        view.setBorderWidth(borderWidth);
    }
    
    @ReactProp(name = "cornerRadius", defaultFloat = 8.0f)
    public void setCornerRadius(ViroButtonView view, float cornerRadius) {
        ViroLog.debug(TAG, "Setting corner radius: " + cornerRadius);
        view.setCornerRadius(cornerRadius);
    }
    
    // Button Dimensions
    @ReactProp(name = "buttonWidth", defaultFloat = 100.0f)
    public void setButtonWidth(ViroButtonView view, float buttonWidth) {
        ViroLog.debug(TAG, "Setting button width: " + buttonWidth);
        view.setButtonWidth(buttonWidth);
    }
    
    @ReactProp(name = "buttonHeight", defaultFloat = 40.0f)
    public void setButtonHeight(ViroButtonView view, float buttonHeight) {
        ViroLog.debug(TAG, "Setting button height: " + buttonHeight);
        view.setButtonHeight(buttonHeight);
    }
    
    @ReactProp(name = "padding")
    public void setPadding(ViroButtonView view, @Nullable ReadableArray padding) {
        ViroLog.debug(TAG, "Setting padding: " + padding);
        view.setPadding(padding);
    }
    
    @ReactProp(name = "margin")
    public void setMargin(ViroButtonView view, @Nullable ReadableArray margin) {
        ViroLog.debug(TAG, "Setting margin: " + margin);
        view.setMargin(margin);
    }
    
    // Button States
    @ReactProp(name = "enabled", defaultBoolean = true)
    public void setEnabled(ViroButtonView view, boolean enabled) {
        ViroLog.debug(TAG, "Setting enabled: " + enabled);
        view.setEnabled(enabled);
    }
    
    @ReactProp(name = "selected", defaultBoolean = false)
    public void setSelected(ViroButtonView view, boolean selected) {
        ViroLog.debug(TAG, "Setting selected: " + selected);
        view.setSelected(selected);
    }
    
    @ReactProp(name = "highlighted", defaultBoolean = false)
    public void setHighlighted(ViroButtonView view, boolean highlighted) {
        ViroLog.debug(TAG, "Setting highlighted: " + highlighted);
        view.setHighlighted(highlighted);
    }
    
    @ReactProp(name = "toggleable", defaultBoolean = false)
    public void setToggleable(ViroButtonView view, boolean toggleable) {
        ViroLog.debug(TAG, "Setting toggleable: " + toggleable);
        view.setToggleable(toggleable);
    }
    
    // Button Behavior
    @ReactProp(name = "clickTintColor")
    public void setClickTintColor(ViroButtonView view, @Nullable ReadableArray clickTintColor) {
        ViroLog.debug(TAG, "Setting click tint color: " + clickTintColor);
        view.setClickTintColor(clickTintColor);
    }
    
    @ReactProp(name = "hoverTintColor")
    public void setHoverTintColor(ViroButtonView view, @Nullable ReadableArray hoverTintColor) {
        ViroLog.debug(TAG, "Setting hover tint color: " + hoverTintColor);
        view.setHoverTintColor(hoverTintColor);
    }
    
    @ReactProp(name = "gazeTintColor")
    public void setGazeTintColor(ViroButtonView view, @Nullable ReadableArray gazeTintColor) {
        ViroLog.debug(TAG, "Setting gaze tint color: " + gazeTintColor);
        view.setGazeTintColor(gazeTintColor);
    }
    
    @ReactProp(name = "clickScale")
    public void setClickScale(ViroButtonView view, @Nullable ReadableArray clickScale) {
        ViroLog.debug(TAG, "Setting click scale: " + clickScale);
        view.setClickScale(clickScale);
    }
    
    @ReactProp(name = "hoverScale")
    public void setHoverScale(ViroButtonView view, @Nullable ReadableArray hoverScale) {
        ViroLog.debug(TAG, "Setting hover scale: " + hoverScale);
        view.setHoverScale(hoverScale);
    }
    
    @ReactProp(name = "gazeScale")
    public void setGazeScale(ViroButtonView view, @Nullable ReadableArray gazeScale) {
        ViroLog.debug(TAG, "Setting gaze scale: " + gazeScale);
        view.setGazeScale(gazeScale);
    }
    
    // Button Animation
    @ReactProp(name = "animationDuration", defaultFloat = 0.2f)
    public void setAnimationDuration(ViroButtonView view, float animationDuration) {
        ViroLog.debug(TAG, "Setting animation duration: " + animationDuration);
        view.setAnimationDuration(animationDuration);
    }
    
    @ReactProp(name = "animationEasing")
    public void setAnimationEasing(ViroButtonView view, @Nullable String animationEasing) {
        ViroLog.debug(TAG, "Setting animation easing: " + animationEasing);
        view.setAnimationEasing(animationEasing);
    }
    
    @ReactProp(name = "clickAnimation")
    public void setClickAnimation(ViroButtonView view, @Nullable ReadableMap clickAnimation) {
        ViroLog.debug(TAG, "Setting click animation: " + clickAnimation);
        view.setClickAnimation(clickAnimation);
    }
    
    @ReactProp(name = "hoverAnimation")
    public void setHoverAnimation(ViroButtonView view, @Nullable ReadableMap hoverAnimation) {
        ViroLog.debug(TAG, "Setting hover animation: " + hoverAnimation);
        view.setHoverAnimation(hoverAnimation);
    }
    
    @ReactProp(name = "gazeAnimation")
    public void setGazeAnimation(ViroButtonView view, @Nullable ReadableMap gazeAnimation) {
        ViroLog.debug(TAG, "Setting gaze animation: " + gazeAnimation);
        view.setGazeAnimation(gazeAnimation);
    }
    
    // Button Interaction
    @ReactProp(name = "clickDistance", defaultFloat = 0.1f)
    public void setClickDistance(ViroButtonView view, float clickDistance) {
        ViroLog.debug(TAG, "Setting click distance: " + clickDistance);
        view.setClickDistance(clickDistance);
    }
    
    @ReactProp(name = "hoverDistance", defaultFloat = 0.5f)
    public void setHoverDistance(ViroButtonView view, float hoverDistance) {
        ViroLog.debug(TAG, "Setting hover distance: " + hoverDistance);
        view.setHoverDistance(hoverDistance);
    }
    
    @ReactProp(name = "gazeDistance", defaultFloat = 2.0f)
    public void setGazeDistance(ViroButtonView view, float gazeDistance) {
        ViroLog.debug(TAG, "Setting gaze distance: " + gazeDistance);
        view.setGazeDistance(gazeDistance);
    }
    
    @ReactProp(name = "clickTrigger")
    public void setClickTrigger(ViroButtonView view, @Nullable String clickTrigger) {
        ViroLog.debug(TAG, "Setting click trigger: " + clickTrigger);
        view.setClickTrigger(clickTrigger);
    }
    
    @ReactProp(name = "hoverTrigger")
    public void setHoverTrigger(ViroButtonView view, @Nullable String hoverTrigger) {
        ViroLog.debug(TAG, "Setting hover trigger: " + hoverTrigger);
        view.setHoverTrigger(hoverTrigger);
    }
    
    @ReactProp(name = "gazeTrigger")
    public void setGazeTrigger(ViroButtonView view, @Nullable String gazeTrigger) {
        ViroLog.debug(TAG, "Setting gaze trigger: " + gazeTrigger);
        view.setGazeTrigger(gazeTrigger);
    }
    
    // Button Accessibility
    @ReactProp(name = "accessibilityLabel")
    public void setAccessibilityLabel(ViroButtonView view, @Nullable String accessibilityLabel) {
        ViroLog.debug(TAG, "Setting accessibility label: " + accessibilityLabel);
        view.setAccessibilityLabel(accessibilityLabel);
    }
    
    @ReactProp(name = "accessibilityHint")
    public void setAccessibilityHint(ViroButtonView view, @Nullable String accessibilityHint) {
        ViroLog.debug(TAG, "Setting accessibility hint: " + accessibilityHint);
        view.setAccessibilityHint(accessibilityHint);
    }
    
    @ReactProp(name = "accessibilityRole")
    public void setAccessibilityRole(ViroButtonView view, @Nullable String accessibilityRole) {
        ViroLog.debug(TAG, "Setting accessibility role: " + accessibilityRole);
        view.setAccessibilityRole(accessibilityRole);
    }
    
    // Event Exports
    @Override
    public java.util.Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return java.util.Map.of(
            "onClick", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onClick",
                    "captured", "onClickCapture"
                )
            ),
            "onHover", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onHover",
                    "captured", "onHoverCapture"
                )
            ),
            "onGaze", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onGaze",
                    "captured", "onGazeCapture"
                )
            ),
            "onTouch", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onTouch",
                    "captured", "onTouchCapture"
                )
            ),
            "onStateChange", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onStateChange",
                    "captured", "onStateChangeCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroButtonView view) {
        ViroLog.debug(TAG, "Dropping ViroButtonView instance");
        super.onDropViewInstance(view);
    }
}