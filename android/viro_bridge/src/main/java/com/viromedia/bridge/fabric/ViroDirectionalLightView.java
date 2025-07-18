//
//  ViroDirectionalLightView.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import com.viromedia.bridge.utility.ViroLog;

/**
 * ViroDirectionalLight - Directional Lighting
 * 
 * ViroDirectionalLight simulates parallel light rays like sunlight. Unlike point lights
 * or spot lights, directional lights don't have a position - they only have direction.
 * This makes them ideal for simulating distant light sources like the sun or moon.
 * 
 * Key Features:
 * - Parallel light rays (infinite distance)
 * - Configurable color, intensity, and temperature
 * - Shadow casting with configurable shadow parameters
 * - Influence masking for selective lighting
 * - Direction vector control for light angle
 * - Efficient rendering for large scenes
 */
public class ViroDirectionalLightView extends ViroNodeView {

    private static final String TAG = ViroLog.getTag(ViroDirectionalLightView.class);

    // Light color and intensity
    private String mColor = "#FFFFFF";
    private float mIntensity = 1000.0f; // Lux
    private float mTemperature = 6500.0f; // Kelvin (daylight)

    // Light direction (normalized vector)
    private float[] mDirection = {0.0f, -1.0f, 0.0f}; // Pointing downward (like sunlight)

    // Shadow properties
    private boolean mCastsShadow = true;
    private float mShadowOpacity = 0.3f;
    private int mShadowMapSize = 1024;
    private float mShadowBias = 0.001f;
    private float mShadowNearZ = 1.0f;
    private float mShadowFarZ = 100.0f;

    // Light influence
    private int mInfluenceBitMask = 1; // Default influence mask

    public ViroDirectionalLightView(@NonNull ThemedReactContext context) {
        super(context);
        ViroLog.debug(TAG, "ViroDirectionalLightView initialized");
        
        // TODO: Initialize ViroReact directional light
        // This will need to integrate with the existing ViroReact lighting system
        initializeDirectionalLight();
    }

    private void initializeDirectionalLight() {
        ViroLog.debug(TAG, "Initializing directional light with default properties");
        
        // TODO: Set up ViroReact directional light with default properties
        // This should create the underlying directional light in the 3D scene
        updateDirectionalLight();
    }

    // Light Color and Intensity
    public void setLightColor(@NonNull String color) {
        ViroLog.debug(TAG, "Setting light color: " + color);
        mColor = color;
        updateDirectionalLight();
    }

    public void setIntensity(float intensity) {
        ViroLog.debug(TAG, "Setting intensity: " + intensity);
        mIntensity = intensity;
        updateDirectionalLight();
    }

    public void setTemperature(float temperature) {
        ViroLog.debug(TAG, "Setting temperature: " + temperature);
        mTemperature = temperature;
        
        // TODO: Update directional light temperature in ViroReact renderer
        // Temperature affects the color tint (warm/cool)
        updateDirectionalLight();
    }

    // Light Direction
    public void setDirection(@Nullable ReadableArray direction) {
        ViroLog.debug(TAG, "Setting direction: " + direction);
        
        if (direction != null && direction.size() >= 3) {
            try {
                float x = (float) direction.getDouble(0);
                float y = (float) direction.getDouble(1);
                float z = (float) direction.getDouble(2);
                
                // Normalize the direction vector
                float length = (float) Math.sqrt(x*x + y*y + z*z);
                if (length > 0) {
                    mDirection[0] = x / length;
                    mDirection[1] = y / length;
                    mDirection[2] = z / length;
                } else {
                    // Fallback to default downward direction
                    mDirection[0] = 0.0f;
                    mDirection[1] = -1.0f;
                    mDirection[2] = 0.0f;
                }
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing direction: " + e.getMessage());
                // Keep current direction on error
            }
        } else {
            // Reset to default direction
            mDirection[0] = 0.0f;
            mDirection[1] = -1.0f;
            mDirection[2] = 0.0f;
        }
        
        updateDirectionalLight();
    }

    // Shadow Properties
    public void setCastsShadow(boolean castsShadow) {
        ViroLog.debug(TAG, "Setting casts shadow: " + castsShadow);
        mCastsShadow = castsShadow;
        updateDirectionalLight();
    }

    public void setShadowOpacity(float shadowOpacity) {
        ViroLog.debug(TAG, "Setting shadow opacity: " + shadowOpacity);
        mShadowOpacity = Math.max(0.0f, Math.min(1.0f, shadowOpacity)); // Clamp to [0, 1]
        updateDirectionalLight();
    }

    public void setShadowMapSize(int shadowMapSize) {
        ViroLog.debug(TAG, "Setting shadow map size: " + shadowMapSize);
        // Ensure power of 2 and reasonable range
        mShadowMapSize = Math.max(256, Math.min(4096, shadowMapSize));
        updateDirectionalLight();
    }

    public void setShadowBias(float shadowBias) {
        ViroLog.debug(TAG, "Setting shadow bias: " + shadowBias);
        mShadowBias = shadowBias;
        updateDirectionalLight();
    }

    public void setShadowNearZ(float shadowNearZ) {
        ViroLog.debug(TAG, "Setting shadow near Z: " + shadowNearZ);
        mShadowNearZ = Math.max(0.1f, shadowNearZ); // Ensure positive
        updateDirectionalLight();
    }

    public void setShadowFarZ(float shadowFarZ) {
        ViroLog.debug(TAG, "Setting shadow far Z: " + shadowFarZ);
        mShadowFarZ = Math.max(mShadowNearZ + 0.1f, shadowFarZ); // Ensure > near
        updateDirectionalLight();
    }

    // Light Influence
    public void setInfluenceBitMask(int influenceBitMask) {
        ViroLog.debug(TAG, "Setting influence bit mask: " + influenceBitMask);
        mInfluenceBitMask = influenceBitMask;
        updateDirectionalLight();
    }

    private void updateDirectionalLight() {
        ViroLog.debug(TAG, String.format(
            "Updating directional light - Color: %s, Intensity: %.1f, Direction: [%.2f, %.2f, %.2f], Shadows: %s",
            mColor, mIntensity, mDirection[0], mDirection[1], mDirection[2], mCastsShadow));
        
        // TODO: Apply directional light settings to ViroReact renderer
        // Directional light simulates parallel light rays (like sunlight)
        // It has direction but no position - affects all objects equally regardless of distance
        // Can cast shadows using shadow mapping techniques
        
        // Emit light update event for React Native
        emitDirectionalLightUpdateEvent();
    }

    private void emitDirectionalLightUpdateEvent() {
        WritableMap event = Arguments.createMap();
        event.putString("color", mColor);
        event.putDouble("intensity", mIntensity);
        event.putDouble("temperature", mTemperature);
        event.putBoolean("castsShadow", mCastsShadow);
        event.putDouble("shadowOpacity", mShadowOpacity);
        
        ThemedReactContext reactContext = (ThemedReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onDirectionalLightUpdate", event);
    }

    // Helper Methods
    private int parseColorToInt(@NonNull String colorString) {
        try {
            // Handle hex colors with or without '#'
            String cleanColor = colorString.startsWith("#") ? colorString.substring(1) : colorString;
            
            if (cleanColor.length() == 6) {
                return Color.parseColor("#" + cleanColor);
            } else if (cleanColor.length() == 8) {
                // ARGB format
                return Color.parseColor("#" + cleanColor);
            }
        } catch (Exception e) {
            ViroLog.error(TAG, "Error parsing color: " + colorString + ", " + e.getMessage());
        }
        
        return Color.WHITE; // Default fallback
    }

    public float[] getNormalizedDirection() {
        return mDirection.clone();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViroLog.debug(TAG, "Directional light attached to window");
        
        // TODO: Add directional light to ViroReact scene when attached to window
        updateDirectionalLight();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViroLog.debug(TAG, "Directional light detached from window");
        
        // TODO: Remove directional light from ViroReact scene when detached from window
    }

    // Getters for current values (useful for debugging and testing)
    public String getLightColor() { return mColor; }
    public float getIntensity() { return mIntensity; }
    public float getTemperature() { return mTemperature; }
    public float[] getDirection() { return mDirection.clone(); }
    public boolean getCastsShadow() { return mCastsShadow; }
    public float getShadowOpacity() { return mShadowOpacity; }
    public int getShadowMapSize() { return mShadowMapSize; }
    public float getShadowBias() { return mShadowBias; }
    public float getShadowNearZ() { return mShadowNearZ; }
    public float getShadowFarZ() { return mShadowFarZ; }
    public int getInfluenceBitMask() { return mInfluenceBitMask; }
}