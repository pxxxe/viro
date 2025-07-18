//
//  ViroAnimatedComponentViewManager.java
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
 * ViroAnimatedComponentViewManager - Animation Container ViewManager
 * 
 * This ViewManager handles animation containers in ViroReact. It provides
 * comprehensive animation capabilities including transform animations, opacity
 * animations, color animations, material animations, and physics-based animations.
 * 
 * Key Features:
 * - Transform animations (position, scale, rotation)
 * - Opacity and color animations
 * - Material property animations
 * - Physics-based animations with velocity and acceleration
 * - Animation control (play, pause, reverse, loop)
 * - Keyframe animations with custom timing
 * - Easing functions and interpolation types
 * - Event callbacks for animation lifecycle
 * - Integration with ViroReact scene graph
 */
public class ViroAnimatedComponentViewManager extends SimpleViewManager<ViroAnimatedComponentView> {
    
    private static final String TAG = ViroLog.getTag(ViroAnimatedComponentViewManager.class);
    private static final String REACT_CLASS = "ViroAnimatedComponent";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroAnimatedComponentView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroAnimatedComponentView instance");
        return new ViroAnimatedComponentView(reactContext);
    }
    
    // Animation Properties
    @ReactProp(name = "animation")
    public void setAnimation(ViroAnimatedComponentView view, @Nullable ReadableMap animation) {
        ViroLog.debug(TAG, "Setting animation: " + animation);
        view.setAnimation(animation);
    }
    
    @ReactProp(name = "animationName")
    public void setAnimationName(ViroAnimatedComponentView view, @Nullable String animationName) {
        ViroLog.debug(TAG, "Setting animation name: " + animationName);
        view.setAnimationName(animationName);
    }
    
    @ReactProp(name = "loop", defaultBoolean = false)
    public void setLoop(ViroAnimatedComponentView view, boolean loop) {
        ViroLog.debug(TAG, "Setting loop: " + loop);
        view.setLoop(loop);
    }
    
    @ReactProp(name = "delay", defaultFloat = 0.0f)
    public void setDelay(ViroAnimatedComponentView view, float delay) {
        ViroLog.debug(TAG, "Setting delay: " + delay);
        view.setDelay(delay);
    }
    
    @ReactProp(name = "duration", defaultFloat = 1.0f)
    public void setDuration(ViroAnimatedComponentView view, float duration) {
        ViroLog.debug(TAG, "Setting duration: " + duration);
        view.setDuration(duration);
    }
    
    @ReactProp(name = "easing")
    public void setEasing(ViroAnimatedComponentView view, @Nullable String easing) {
        ViroLog.debug(TAG, "Setting easing: " + easing);
        view.setEasing(easing);
    }
    
    @ReactProp(name = "interpolatorType")
    public void setInterpolatorType(ViroAnimatedComponentView view, @Nullable String interpolatorType) {
        ViroLog.debug(TAG, "Setting interpolator type: " + interpolatorType);
        view.setInterpolatorType(interpolatorType);
    }
    
    // Animation Control
    @ReactProp(name = "run", defaultBoolean = false)
    public void setRun(ViroAnimatedComponentView view, boolean run) {
        ViroLog.debug(TAG, "Setting run: " + run);
        view.setRun(run);
    }
    
    @ReactProp(name = "paused", defaultBoolean = false)
    public void setPaused(ViroAnimatedComponentView view, boolean paused) {
        ViroLog.debug(TAG, "Setting paused: " + paused);
        view.setPaused(paused);
    }
    
    @ReactProp(name = "reverse", defaultBoolean = false)
    public void setReverse(ViroAnimatedComponentView view, boolean reverse) {
        ViroLog.debug(TAG, "Setting reverse: " + reverse);
        view.setReverse(reverse);
    }
    
    @ReactProp(name = "direction")
    public void setDirection(ViroAnimatedComponentView view, @Nullable String direction) {
        ViroLog.debug(TAG, "Setting direction: " + direction);
        view.setDirection(direction);
    }
    
    @ReactProp(name = "iterationCount", defaultInt = 1)
    public void setIterationCount(ViroAnimatedComponentView view, int iterationCount) {
        ViroLog.debug(TAG, "Setting iteration count: " + iterationCount);
        view.setIterationCount(iterationCount);
    }
    
    // Animation Values
    @ReactProp(name = "fromValue")
    public void setFromValue(ViroAnimatedComponentView view, @Nullable ReadableMap fromValue) {
        ViroLog.debug(TAG, "Setting from value: " + fromValue);
        view.setFromValue(fromValue);
    }
    
    @ReactProp(name = "toValue")
    public void setToValue(ViroAnimatedComponentView view, @Nullable ReadableMap toValue) {
        ViroLog.debug(TAG, "Setting to value: " + toValue);
        view.setToValue(toValue);
    }
    
    @ReactProp(name = "values")
    public void setValues(ViroAnimatedComponentView view, @Nullable ReadableArray values) {
        ViroLog.debug(TAG, "Setting values: " + values);
        view.setValues(values);
    }
    
    @ReactProp(name = "keyTimes")
    public void setKeyTimes(ViroAnimatedComponentView view, @Nullable ReadableArray keyTimes) {
        ViroLog.debug(TAG, "Setting key times: " + keyTimes);
        view.setKeyTimes(keyTimes);
    }
    
    // Transform Animations
    @ReactProp(name = "positionFrom")
    public void setPositionFrom(ViroAnimatedComponentView view, @Nullable ReadableArray positionFrom) {
        ViroLog.debug(TAG, "Setting position from: " + positionFrom);
        view.setPositionFrom(positionFrom);
    }
    
    @ReactProp(name = "positionTo")
    public void setPositionTo(ViroAnimatedComponentView view, @Nullable ReadableArray positionTo) {
        ViroLog.debug(TAG, "Setting position to: " + positionTo);
        view.setPositionTo(positionTo);
    }
    
    @ReactProp(name = "scaleFrom")
    public void setScaleFrom(ViroAnimatedComponentView view, @Nullable ReadableArray scaleFrom) {
        ViroLog.debug(TAG, "Setting scale from: " + scaleFrom);
        view.setScaleFrom(scaleFrom);
    }
    
    @ReactProp(name = "scaleTo")
    public void setScaleTo(ViroAnimatedComponentView view, @Nullable ReadableArray scaleTo) {
        ViroLog.debug(TAG, "Setting scale to: " + scaleTo);
        view.setScaleTo(scaleTo);
    }
    
    @ReactProp(name = "rotationFrom")
    public void setRotationFrom(ViroAnimatedComponentView view, @Nullable ReadableArray rotationFrom) {
        ViroLog.debug(TAG, "Setting rotation from: " + rotationFrom);
        view.setRotationFrom(rotationFrom);
    }
    
    @ReactProp(name = "rotationTo")
    public void setRotationTo(ViroAnimatedComponentView view, @Nullable ReadableArray rotationTo) {
        ViroLog.debug(TAG, "Setting rotation to: " + rotationTo);
        view.setRotationTo(rotationTo);
    }
    
    // Opacity Animations
    @ReactProp(name = "opacityFrom")
    public void setOpacityFrom(ViroAnimatedComponentView view, @Nullable Float opacityFrom) {
        ViroLog.debug(TAG, "Setting opacity from: " + opacityFrom);
        view.setOpacityFrom(opacityFrom);
    }
    
    @ReactProp(name = "opacityTo")
    public void setOpacityTo(ViroAnimatedComponentView view, @Nullable Float opacityTo) {
        ViroLog.debug(TAG, "Setting opacity to: " + opacityTo);
        view.setOpacityTo(opacityTo);
    }
    
    // Color Animations
    @ReactProp(name = "colorFrom")
    public void setColorFrom(ViroAnimatedComponentView view, @Nullable ReadableArray colorFrom) {
        ViroLog.debug(TAG, "Setting color from: " + colorFrom);
        view.setColorFrom(colorFrom);
    }
    
    @ReactProp(name = "colorTo")
    public void setColorTo(ViroAnimatedComponentView view, @Nullable ReadableArray colorTo) {
        ViroLog.debug(TAG, "Setting color to: " + colorTo);
        view.setColorTo(colorTo);
    }
    
    // Material Animations
    @ReactProp(name = "materialFrom")
    public void setMaterialFrom(ViroAnimatedComponentView view, @Nullable ReadableMap materialFrom) {
        ViroLog.debug(TAG, "Setting material from: " + materialFrom);
        view.setMaterialFrom(materialFrom);
    }
    
    @ReactProp(name = "materialTo")
    public void setMaterialTo(ViroAnimatedComponentView view, @Nullable ReadableMap materialTo) {
        ViroLog.debug(TAG, "Setting material to: " + materialTo);
        view.setMaterialTo(materialTo);
    }
    
    // Physics Animations
    @ReactProp(name = "physicsEnabled", defaultBoolean = false)
    public void setPhysicsEnabled(ViroAnimatedComponentView view, boolean physicsEnabled) {
        ViroLog.debug(TAG, "Setting physics enabled: " + physicsEnabled);
        view.setPhysicsEnabled(physicsEnabled);
    }
    
    @ReactProp(name = "velocity")
    public void setVelocity(ViroAnimatedComponentView view, @Nullable ReadableArray velocity) {
        ViroLog.debug(TAG, "Setting velocity: " + velocity);
        view.setVelocity(velocity);
    }
    
    @ReactProp(name = "acceleration")
    public void setAcceleration(ViroAnimatedComponentView view, @Nullable ReadableArray acceleration) {
        ViroLog.debug(TAG, "Setting acceleration: " + acceleration);
        view.setAcceleration(acceleration);
    }
    
    // Event Exports
    @Override
    public java.util.Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return java.util.Map.of(
            "onStart", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onStart",
                    "captured", "onStartCapture"
                )
            ),
            "onFinish", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onFinish",
                    "captured", "onFinishCapture"
                )
            ),
            "onUpdate", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onUpdate",
                    "captured", "onUpdateCapture"
                )
            ),
            "onCancel", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onCancel",
                    "captured", "onCancelCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroAnimatedComponentView view) {
        ViroLog.debug(TAG, "Dropping ViroAnimatedComponentView instance");
        super.onDropViewInstance(view);
    }
}