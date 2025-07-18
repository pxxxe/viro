//
//  ViroButtonView.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import com.viromedia.bridge.utility.ViroLog;

/**
 * ViroButtonView - Interactive UI Button Android View
 * 
 * This View provides comprehensive button functionality for ViroReact applications,
 * supporting text, images, multiple interaction states, animations, and accessibility.
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
public class ViroButtonView extends View {
    
    private static final String TAG = ViroLog.getTag(ViroButtonView.class);
    
    // Button states
    public enum ButtonState {
        NORMAL("normal"),
        HIGHLIGHTED("highlighted"),
        SELECTED("selected"),
        DISABLED("disabled"),
        HOVERED("hovered"),
        GAZED("gazed");
        
        private final String value;
        
        ButtonState(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
        
        public static ButtonState fromString(String value) {
            for (ButtonState state : ButtonState.values()) {
                if (state.value.equals(value)) {
                    return state;
                }
            }
            return NORMAL;
        }
    }
    
    // Button content
    private ReadableMap source;
    private ReadableMap imageSource;
    private String text = "";
    private ReadableArray textColor = Arguments.createArray();
    private float textSize = 16.0f;
    private String textFont = "System";
    private String textStyle = "normal";
    
    // Button appearance
    private ReadableMap gazeSource;
    private ReadableMap clickSource;
    private ReadableMap hoverSource;
    private ReadableArray backgroundColor = Arguments.createArray();
    private ReadableArray borderColor = Arguments.createArray();
    private float borderWidth = 1.0f;
    private float cornerRadius = 8.0f;
    
    // Button dimensions
    private float buttonWidth = 100.0f;
    private float buttonHeight = 40.0f;
    private ReadableArray padding = Arguments.createArray();
    private ReadableArray margin = Arguments.createArray();
    
    // Button states
    private boolean enabled = true;
    private boolean selected = false;
    private boolean highlighted = false;
    private boolean toggleable = false;
    private ButtonState currentState = ButtonState.NORMAL;
    
    // Button behavior
    private ReadableArray clickTintColor = Arguments.createArray();
    private ReadableArray hoverTintColor = Arguments.createArray();
    private ReadableArray gazeTintColor = Arguments.createArray();
    private ReadableArray clickScale = Arguments.createArray();
    private ReadableArray hoverScale = Arguments.createArray();
    private ReadableArray gazeScale = Arguments.createArray();
    
    // Button animation
    private float animationDuration = 0.2f;
    private String animationEasing = "ease-in-out";
    private ReadableMap clickAnimation;
    private ReadableMap hoverAnimation;
    private ReadableMap gazeAnimation;
    
    // Button interaction
    private float clickDistance = 0.1f;
    private float hoverDistance = 0.5f;
    private float gazeDistance = 2.0f;
    private String clickTrigger = "touch";
    private String hoverTrigger = "hover";
    private String gazeTrigger = "gaze";
    
    // Button accessibility
    private String accessibilityLabel;
    private String accessibilityHint;
    private String accessibilityRole = "button";
    
    // Internal state
    private boolean isAnimating = false;
    private Paint backgroundPaint;
    private Paint borderPaint;
    private Paint textPaint;
    private RectF buttonRect;
    private ValueAnimator currentAnimator;
    
    public ViroButtonView(@NonNull Context context) {
        super(context);
        init();
    }
    
    private void init() {
        ViroLog.debug(TAG, "Initializing ViroButtonView");
        
        // Initialize default values
        textColor = createColorArray(1.0f, 1.0f, 1.0f, 1.0f);
        backgroundColor = createColorArray(0.2f, 0.2f, 0.2f, 0.8f);
        borderColor = createColorArray(1.0f, 1.0f, 1.0f, 1.0f);
        clickTintColor = createColorArray(0.8f, 0.8f, 0.8f, 1.0f);
        hoverTintColor = createColorArray(0.9f, 0.9f, 0.9f, 1.0f);
        gazeTintColor = createColorArray(0.95f, 0.95f, 0.95f, 1.0f);
        clickScale = createScaleArray(0.95f, 0.95f, 0.95f);
        hoverScale = createScaleArray(1.05f, 1.05f, 1.05f);
        gazeScale = createScaleArray(1.02f, 1.02f, 1.02f);
        padding = createPaddingArray(10.0f, 10.0f, 10.0f, 10.0f);
        margin = createPaddingArray(0.0f, 0.0f, 0.0f, 0.0f);
        
        // Initialize paint objects
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setStyle(Paint.Style.FILL);
        
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.DEFAULT);
        
        buttonRect = new RectF();
        
        // Set up button properties
        setLayoutParams(new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        
        setClickable(true);
        setFocusable(true);
        
        updateButtonAppearance();
    }
    
    // Button Content Methods
    public void setSource(@Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        this.source = source;
        updateButtonAppearance();
    }
    
    public void setImageSource(@Nullable ReadableMap imageSource) {
        ViroLog.debug(TAG, "Setting image source: " + imageSource);
        this.imageSource = imageSource;
        updateButtonContent();
    }
    
    public void setText(@Nullable String text) {
        ViroLog.debug(TAG, "Setting text: " + text);
        this.text = text != null ? text : "";
        updateButtonContent();
    }
    
    public void setTextColor(@Nullable ReadableArray textColor) {
        ViroLog.debug(TAG, "Setting text color: " + textColor);
        this.textColor = textColor != null ? textColor : createColorArray(1.0f, 1.0f, 1.0f, 1.0f);
        updateButtonContent();
    }
    
    public void setTextSize(float textSize) {
        ViroLog.debug(TAG, "Setting text size: " + textSize);
        this.textSize = textSize;
        updateButtonContent();
    }
    
    public void setTextFont(@Nullable String textFont) {
        ViroLog.debug(TAG, "Setting text font: " + textFont);
        this.textFont = textFont != null ? textFont : "System";
        updateButtonContent();
    }
    
    public void setTextStyle(@Nullable String textStyle) {
        ViroLog.debug(TAG, "Setting text style: " + textStyle);
        this.textStyle = textStyle != null ? textStyle : "normal";
        updateButtonContent();
    }
    
    // Button Appearance Methods
    public void setGazeSource(@Nullable ReadableMap gazeSource) {
        ViroLog.debug(TAG, "Setting gaze source: " + gazeSource);
        this.gazeSource = gazeSource;
        updateButtonAppearance();
    }
    
    public void setClickSource(@Nullable ReadableMap clickSource) {
        ViroLog.debug(TAG, "Setting click source: " + clickSource);
        this.clickSource = clickSource;
        updateButtonAppearance();
    }
    
    public void setHoverSource(@Nullable ReadableMap hoverSource) {
        ViroLog.debug(TAG, "Setting hover source: " + hoverSource);
        this.hoverSource = hoverSource;
        updateButtonAppearance();
    }
    
    public void setBackgroundColor(@Nullable ReadableArray backgroundColor) {
        ViroLog.debug(TAG, "Setting background color: " + backgroundColor);
        this.backgroundColor = backgroundColor != null ? backgroundColor : createColorArray(0.2f, 0.2f, 0.2f, 0.8f);
        updateButtonAppearance();
    }
    
    public void setBorderColor(@Nullable ReadableArray borderColor) {
        ViroLog.debug(TAG, "Setting border color: " + borderColor);
        this.borderColor = borderColor != null ? borderColor : createColorArray(1.0f, 1.0f, 1.0f, 1.0f);
        updateButtonAppearance();
    }
    
    public void setBorderWidth(float borderWidth) {
        ViroLog.debug(TAG, "Setting border width: " + borderWidth);
        this.borderWidth = borderWidth;
        updateButtonAppearance();
    }
    
    public void setCornerRadius(float cornerRadius) {
        ViroLog.debug(TAG, "Setting corner radius: " + cornerRadius);
        this.cornerRadius = cornerRadius;
        updateButtonAppearance();
    }
    
    // Button Dimensions Methods
    public void setButtonWidth(float buttonWidth) {
        ViroLog.debug(TAG, "Setting button width: " + buttonWidth);
        this.buttonWidth = buttonWidth;
        updateButtonGeometry();
    }
    
    public void setButtonHeight(float buttonHeight) {
        ViroLog.debug(TAG, "Setting button height: " + buttonHeight);
        this.buttonHeight = buttonHeight;
        updateButtonGeometry();
    }
    
    public void setPadding(@Nullable ReadableArray padding) {
        ViroLog.debug(TAG, "Setting padding: " + padding);
        this.padding = padding != null ? padding : createPaddingArray(10.0f, 10.0f, 10.0f, 10.0f);
        updateButtonGeometry();
    }
    
    public void setMargin(@Nullable ReadableArray margin) {
        ViroLog.debug(TAG, "Setting margin: " + margin);
        this.margin = margin != null ? margin : createPaddingArray(0.0f, 0.0f, 0.0f, 0.0f);
        updateButtonGeometry();
    }
    
    // Button States Methods
    public void setEnabled(boolean enabled) {
        ViroLog.debug(TAG, "Setting enabled: " + enabled);
        this.enabled = enabled;
        updateButtonState();
    }
    
    public void setSelected(boolean selected) {
        ViroLog.debug(TAG, "Setting selected: " + selected);
        this.selected = selected;
        updateButtonState();
    }
    
    public void setHighlighted(boolean highlighted) {
        ViroLog.debug(TAG, "Setting highlighted: " + highlighted);
        this.highlighted = highlighted;
        updateButtonState();
    }
    
    public void setToggleable(boolean toggleable) {
        ViroLog.debug(TAG, "Setting toggleable: " + toggleable);
        this.toggleable = toggleable;
    }
    
    // Button Behavior Methods
    public void setClickTintColor(@Nullable ReadableArray clickTintColor) {
        ViroLog.debug(TAG, "Setting click tint color: " + clickTintColor);
        this.clickTintColor = clickTintColor != null ? clickTintColor : createColorArray(0.8f, 0.8f, 0.8f, 1.0f);
    }
    
    public void setHoverTintColor(@Nullable ReadableArray hoverTintColor) {
        ViroLog.debug(TAG, "Setting hover tint color: " + hoverTintColor);
        this.hoverTintColor = hoverTintColor != null ? hoverTintColor : createColorArray(0.9f, 0.9f, 0.9f, 1.0f);
    }
    
    public void setGazeTintColor(@Nullable ReadableArray gazeTintColor) {
        ViroLog.debug(TAG, "Setting gaze tint color: " + gazeTintColor);
        this.gazeTintColor = gazeTintColor != null ? gazeTintColor : createColorArray(0.95f, 0.95f, 0.95f, 1.0f);
    }
    
    public void setClickScale(@Nullable ReadableArray clickScale) {
        ViroLog.debug(TAG, "Setting click scale: " + clickScale);
        this.clickScale = clickScale != null ? clickScale : createScaleArray(0.95f, 0.95f, 0.95f);
    }
    
    public void setHoverScale(@Nullable ReadableArray hoverScale) {
        ViroLog.debug(TAG, "Setting hover scale: " + hoverScale);
        this.hoverScale = hoverScale != null ? hoverScale : createScaleArray(1.05f, 1.05f, 1.05f);
    }
    
    public void setGazeScale(@Nullable ReadableArray gazeScale) {
        ViroLog.debug(TAG, "Setting gaze scale: " + gazeScale);
        this.gazeScale = gazeScale != null ? gazeScale : createScaleArray(1.02f, 1.02f, 1.02f);
    }
    
    // Button Animation Methods
    public void setAnimationDuration(float animationDuration) {
        ViroLog.debug(TAG, "Setting animation duration: " + animationDuration);
        this.animationDuration = animationDuration;
    }
    
    public void setAnimationEasing(@Nullable String animationEasing) {
        ViroLog.debug(TAG, "Setting animation easing: " + animationEasing);
        this.animationEasing = animationEasing != null ? animationEasing : "ease-in-out";
    }
    
    public void setClickAnimation(@Nullable ReadableMap clickAnimation) {
        ViroLog.debug(TAG, "Setting click animation: " + clickAnimation);
        this.clickAnimation = clickAnimation;
    }
    
    public void setHoverAnimation(@Nullable ReadableMap hoverAnimation) {
        ViroLog.debug(TAG, "Setting hover animation: " + hoverAnimation);
        this.hoverAnimation = hoverAnimation;
    }
    
    public void setGazeAnimation(@Nullable ReadableMap gazeAnimation) {
        ViroLog.debug(TAG, "Setting gaze animation: " + gazeAnimation);
        this.gazeAnimation = gazeAnimation;
    }
    
    // Button Interaction Methods
    public void setClickDistance(float clickDistance) {
        ViroLog.debug(TAG, "Setting click distance: " + clickDistance);
        this.clickDistance = clickDistance;
    }
    
    public void setHoverDistance(float hoverDistance) {
        ViroLog.debug(TAG, "Setting hover distance: " + hoverDistance);
        this.hoverDistance = hoverDistance;
    }
    
    public void setGazeDistance(float gazeDistance) {
        ViroLog.debug(TAG, "Setting gaze distance: " + gazeDistance);
        this.gazeDistance = gazeDistance;
    }
    
    public void setClickTrigger(@Nullable String clickTrigger) {
        ViroLog.debug(TAG, "Setting click trigger: " + clickTrigger);
        this.clickTrigger = clickTrigger != null ? clickTrigger : "touch";
    }
    
    public void setHoverTrigger(@Nullable String hoverTrigger) {
        ViroLog.debug(TAG, "Setting hover trigger: " + hoverTrigger);
        this.hoverTrigger = hoverTrigger != null ? hoverTrigger : "hover";
    }
    
    public void setGazeTrigger(@Nullable String gazeTrigger) {
        ViroLog.debug(TAG, "Setting gaze trigger: " + gazeTrigger);
        this.gazeTrigger = gazeTrigger != null ? gazeTrigger : "gaze";
    }
    
    // Button Accessibility Methods
    public void setAccessibilityLabel(@Nullable String accessibilityLabel) {
        ViroLog.debug(TAG, "Setting accessibility label: " + accessibilityLabel);
        this.accessibilityLabel = accessibilityLabel;
        setContentDescription(accessibilityLabel);
    }
    
    public void setAccessibilityHint(@Nullable String accessibilityHint) {
        ViroLog.debug(TAG, "Setting accessibility hint: " + accessibilityHint);
        this.accessibilityHint = accessibilityHint;
    }
    
    public void setAccessibilityRole(@Nullable String accessibilityRole) {
        ViroLog.debug(TAG, "Setting accessibility role: " + accessibilityRole);
        this.accessibilityRole = accessibilityRole != null ? accessibilityRole : "button";
    }
    
    // Button Control Methods
    public void performClick() {
        ViroLog.debug(TAG, "Performing click");
        
        if (!enabled) {
            ViroLog.debug(TAG, "Button is disabled, ignoring click");
            return;
        }
        
        // Handle toggle behavior
        if (toggleable) {
            selected = !selected;
        }
        
        // Animate click
        animateClick();
        
        // Fire click event
        fireClickEvent();
        
        // Fire state change event
        fireStateChangeEvent();
    }
    
    public void setButtonState(String state) {
        ViroLog.debug(TAG, "Setting button state: " + state);
        currentState = ButtonState.fromString(state);
        updateButtonState();
    }
    
    public void animateToState(String state) {
        ViroLog.debug(TAG, "Animating to state: " + state);
        setButtonState(state);
        // TODO: Implement smooth animation transition
    }
    
    public void resetButtonState() {
        ViroLog.debug(TAG, "Resetting button state");
        currentState = ButtonState.NORMAL;
        selected = false;
        highlighted = false;
        updateButtonState();
    }
    
    // Button State Information Methods
    public String getCurrentState() {
        return currentState.getValue();
    }
    
    public boolean isClickable() {
        return enabled && "touch".equals(clickTrigger);
    }
    
    public boolean isHoverable() {
        return enabled && "hover".equals(hoverTrigger);
    }
    
    public boolean isGazeable() {
        return enabled && "gaze".equals(gazeTrigger);
    }
    
    public RectF getButtonBounds() {
        return new RectF(0, 0, buttonWidth, buttonHeight);
    }
    
    // Helper Methods
    private void updateButtonContent() {
        if (textPaint != null) {
            textPaint.setTextSize(textSize * getResources().getDisplayMetrics().scaledDensity);
            textPaint.setColor(colorFromArray(textColor));
            
            // Set font style
            if ("bold".equals(textStyle)) {
                textPaint.setTypeface(Typeface.DEFAULT_BOLD);
            } else if ("italic".equals(textStyle)) {
                textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
            } else {
                textPaint.setTypeface(Typeface.DEFAULT);
            }
        }
        
        invalidate();
    }
    
    private void updateButtonAppearance() {
        if (backgroundPaint != null) {
            backgroundPaint.setColor(colorFromArray(backgroundColor));
        }
        
        if (borderPaint != null) {
            borderPaint.setColor(colorFromArray(borderColor));
            borderPaint.setStrokeWidth(borderWidth);
        }
        
        invalidate();
    }
    
    private void updateButtonGeometry() {
        if (buttonRect != null) {
            buttonRect.set(0, 0, buttonWidth, buttonHeight);
        }
        
        requestLayout();
        invalidate();
    }
    
    private void updateButtonState() {
        // Update enabled state
        setAlpha(enabled ? 1.0f : 0.5f);
        
        // Update visual state based on current state
        switch (currentState) {
            case NORMAL:
                applyNormalState();
                break;
            case HIGHLIGHTED:
                applyHighlightedState();
                break;
            case SELECTED:
                applySelectedState();
                break;
            case DISABLED:
                applyDisabledState();
                break;
            case HOVERED:
                applyHoveredState();
                break;
            case GAZED:
                applyGazedState();
                break;
        }
    }
    
    private void applyNormalState() {
        // Reset to normal appearance
        updateButtonAppearance();
        setScaleX(1.0f);
        setScaleY(1.0f);
    }
    
    private void applyHighlightedState() {
        // Apply highlighted appearance
        if (clickTintColor != null) {
            backgroundPaint.setColor(colorFromArray(clickTintColor));
        }
        if (clickScale != null && clickScale.size() > 0) {
            float scale = (float) clickScale.getDouble(0);
            setScaleX(scale);
            setScaleY(scale);
        }
        invalidate();
    }
    
    private void applySelectedState() {
        // Apply selected appearance
        // TODO: Implement selected state appearance
        invalidate();
    }
    
    private void applyDisabledState() {
        // Apply disabled appearance
        setAlpha(0.5f);
    }
    
    private void applyHoveredState() {
        // Apply hovered appearance
        if (hoverTintColor != null) {
            backgroundPaint.setColor(colorFromArray(hoverTintColor));
        }
        if (hoverScale != null && hoverScale.size() > 0) {
            float scale = (float) hoverScale.getDouble(0);
            setScaleX(scale);
            setScaleY(scale);
        }
        invalidate();
    }
    
    private void applyGazedState() {
        // Apply gazed appearance
        if (gazeTintColor != null) {
            backgroundPaint.setColor(colorFromArray(gazeTintColor));
        }
        if (gazeScale != null && gazeScale.size() > 0) {
            float scale = (float) gazeScale.getDouble(0);
            setScaleX(scale);
            setScaleY(scale);
        }
        invalidate();
    }
    
    private void animateClick() {
        if (isAnimating) {
            return;
        }
        
        isAnimating = true;
        
        // Create click animation
        float fromScale = 1.0f;
        float toScale = clickScale != null && clickScale.size() > 0 ? (float) clickScale.getDouble(0) : 0.95f;
        
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(fromScale, toScale, fromScale);
        scaleAnimator.setDuration((long) (animationDuration * 1000));
        scaleAnimator.setInterpolator(getInterpolator(animationEasing));
        
        scaleAnimator.addUpdateListener(animation -> {
            float scale = (float) animation.getAnimatedValue();
            setScaleX(scale);
            setScaleY(scale);
        });
        
        scaleAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
            }
        });
        
        currentAnimator = scaleAnimator;
        scaleAnimator.start();
    }
    
    private android.view.animation.Interpolator getInterpolator(String easing) {
        switch (easing) {
            case "linear":
                return new LinearInterpolator();
            case "ease-in":
                return new AccelerateDecelerateInterpolator();
            case "ease-out":
                return new DecelerateInterpolator();
            case "ease-in-out":
            default:
                return new AccelerateDecelerateInterpolator();
        }
    }
    
    private void fireClickEvent() {
        WritableMap event = Arguments.createMap();
        event.putBoolean("selected", selected);
        event.putString("state", getCurrentState());
        
        Context context = getContext();
        if (context instanceof com.facebook.react.bridge.ReactContext) {
            ((com.facebook.react.bridge.ReactContext) context)
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onClick", event);
        }
    }
    
    private void fireStateChangeEvent() {
        WritableMap event = Arguments.createMap();
        event.putString("state", getCurrentState());
        event.putBoolean("selected", selected);
        
        Context context = getContext();
        if (context instanceof com.facebook.react.bridge.ReactContext) {
            ((com.facebook.react.bridge.ReactContext) context)
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onStateChange", event);
        }
    }
    
    private ReadableArray createColorArray(float r, float g, float b, float a) {
        WritableArray array = Arguments.createArray();
        array.pushDouble(r);
        array.pushDouble(g);
        array.pushDouble(b);
        array.pushDouble(a);
        return array;
    }
    
    private ReadableArray createScaleArray(float x, float y, float z) {
        WritableArray array = Arguments.createArray();
        array.pushDouble(x);
        array.pushDouble(y);
        array.pushDouble(z);
        return array;
    }
    
    private ReadableArray createPaddingArray(float top, float right, float bottom, float left) {
        WritableArray array = Arguments.createArray();
        array.pushDouble(top);
        array.pushDouble(right);
        array.pushDouble(bottom);
        array.pushDouble(left);
        return array;
    }
    
    private int colorFromArray(ReadableArray colorArray) {
        if (colorArray == null || colorArray.size() < 3) {
            return Color.WHITE;
        }
        
        float red = (float) colorArray.getDouble(0);
        float green = (float) colorArray.getDouble(1);
        float blue = (float) colorArray.getDouble(2);
        float alpha = colorArray.size() > 3 ? (float) colorArray.getDouble(3) : 1.0f;
        
        return Color.argb(
            Math.round(alpha * 255),
            Math.round(red * 255),
            Math.round(green * 255),
            Math.round(blue * 255)
        );
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if (buttonRect == null) {
            return;
        }
        
        // Draw background
        if (backgroundPaint != null) {
            canvas.drawRoundRect(buttonRect, cornerRadius, cornerRadius, backgroundPaint);
        }
        
        // Draw border
        if (borderPaint != null && borderWidth > 0) {
            canvas.drawRoundRect(buttonRect, cornerRadius, cornerRadius, borderPaint);
        }
        
        // Draw text
        if (text != null && !text.isEmpty() && textPaint != null) {
            float textX = buttonRect.centerX();
            float textY = buttonRect.centerY() - ((textPaint.descent() + textPaint.ascent()) / 2);
            canvas.drawText(text, textX, textY, textPaint);
        }
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) buttonWidth;
        int height = (int) buttonHeight;
        
        // Apply margin
        if (margin != null && margin.size() >= 4) {
            width += (int) (margin.getDouble(1) + margin.getDouble(3)); // right + left
            height += (int) (margin.getDouble(0) + margin.getDouble(2)); // top + bottom
        }
        
        setMeasuredDimension(width, height);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!enabled || !isClickable()) {
            return false;
        }
        
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentState = ButtonState.HIGHLIGHTED;
                updateButtonState();
                return true;
                
            case MotionEvent.ACTION_UP:
                currentState = ButtonState.NORMAL;
                updateButtonState();
                performClick();
                return true;
                
            case MotionEvent.ACTION_CANCEL:
                currentState = ButtonState.NORMAL;
                updateButtonState();
                return true;
                
            default:
                return super.onTouchEvent(event);
        }
    }
    
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        
        // Clean up any running animations
        if (currentAnimator != null) {
            currentAnimator.cancel();
            currentAnimator = null;
        }
    }
}