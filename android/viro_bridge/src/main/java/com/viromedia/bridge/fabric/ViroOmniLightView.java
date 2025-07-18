//
//  ViroOmniLightView.java
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
 * ViroOmniLight - Point Light Source
 * 
 * ViroOmniLight (Point Light) emits light uniformly in all directions from a single
 * point in 3D space. Unlike directional lights which have parallel rays, omni lights
 * radiate outward like a light bulb, with intensity decreasing with distance.
 * 
 * Key Features:
 * - Omnidirectional light emission from a point
 * - Configurable color, intensity, and temperature
 * - Distance-based attenuation (start/end distances)
 * - Position-based lighting (has specific 3D coordinates)
 * - Influence masking for selective lighting
 * - Real-time light calculations for dynamic scenes
 */
public class ViroOmniLightView extends ViroNodeView {

    private static final String TAG = ViroLog.getTag(ViroOmniLightView.class);

    // Light color and intensity
    private String mColor = "#FFFFFF";
    private float mIntensity = 1000.0f; // Lumens
    private float mTemperature = 6500.0f; // Kelvin (daylight)

    // Light position
    private float[] mPosition = {0.0f, 0.0f, 0.0f}; // Light source position

    // Light attenuation
    private float mAttenuationStartDistance = 2.0f; // Distance where attenuation begins
    private float mAttenuationEndDistance = 10.0f; // Distance where light has no effect

    // Light influence
    private int mInfluenceBitMask = 1; // Default influence mask

    public ViroOmniLightView(@NonNull ThemedReactContext context) {
        super(context);
        ViroLog.debug(TAG, "ViroOmniLightView initialized");
        
        // TODO: Initialize ViroReact omni light
        // This will need to integrate with the existing ViroReact lighting system
        initializeOmniLight();
    }

    private void initializeOmniLight() {
        ViroLog.debug(TAG, "Initializing omni light with default properties");
        
        // TODO: Set up ViroReact omni light with default properties
        // This should create the underlying omnidirectional light in the 3D scene
        updateOmniLight();
    }

    // Light Color and Intensity
    public void setLightColor(@NonNull String color) {
        ViroLog.debug(TAG, "Setting light color: " + color);
        mColor = color;
        updateOmniLight();
    }

    public void setIntensity(float intensity) {
        ViroLog.debug(TAG, "Setting intensity: " + intensity);
        mIntensity = intensity;
        updateOmniLight();
    }

    public void setTemperature(float temperature) {
        ViroLog.debug(TAG, "Setting temperature: " + temperature);
        mTemperature = temperature;
        
        // TODO: Update omni light temperature in ViroReact renderer
        // Temperature affects the color tint (warm/cool)
        updateOmniLight();
    }

    // Light Position
    public void setPosition(@Nullable ReadableArray position) {
        ViroLog.debug(TAG, "Setting position: " + position);
        
        if (position != null && position.size() >= 3) {
            try {
                mPosition[0] = (float) position.getDouble(0); // X
                mPosition[1] = (float) position.getDouble(1); // Y
                mPosition[2] = (float) position.getDouble(2); // Z
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing position: " + e.getMessage());
                // Keep current position on error
            }
        } else {
            // Reset to default position
            mPosition[0] = 0.0f;
            mPosition[1] = 0.0f;
            mPosition[2] = 0.0f;
        }
        
        updateOmniLight();
    }

    // Light Attenuation
    public void setAttenuationStartDistance(float attenuationStartDistance) {
        ViroLog.debug(TAG, "Setting attenuation start distance: " + attenuationStartDistance);
        mAttenuationStartDistance = Math.max(0.1f, attenuationStartDistance); // Ensure positive
        updateOmniLight();
    }

    public void setAttenuationEndDistance(float attenuationEndDistance) {
        ViroLog.debug(TAG, "Setting attenuation end distance: " + attenuationEndDistance);
        mAttenuationEndDistance = Math.max(mAttenuationStartDistance + 0.1f, attenuationEndDistance); // Ensure > start
        updateOmniLight();
    }

    // Light Influence
    public void setInfluenceBitMask(int influenceBitMask) {
        ViroLog.debug(TAG, "Setting influence bit mask: " + influenceBitMask);
        mInfluenceBitMask = influenceBitMask;
        updateOmniLight();
    }

    private void updateOmniLight() {
        ViroLog.debug(TAG, String.format(
            "Updating omni light - Color: %s, Intensity: %.1f, Position: [%.2f, %.2f, %.2f], Attenuation: %.1f-%.1f",
            mColor, mIntensity, mPosition[0], mPosition[1], mPosition[2], 
            mAttenuationStartDistance, mAttenuationEndDistance));
        
        // TODO: Apply omni light settings to ViroReact renderer
        // Omni light (point light) emits light in all directions from a single point
        // Light intensity decreases with distance based on attenuation settings
        // Unlike directional lights, omni lights have a specific position in 3D space
        
        // Emit light update event for React Native
        emitOmniLightUpdateEvent();
    }

    private void emitOmniLightUpdateEvent() {
        WritableMap event = Arguments.createMap();
        event.putString("color", mColor);
        event.putDouble("intensity", mIntensity);
        event.putDouble("temperature", mTemperature);
        event.putDouble("attenuationStartDistance", mAttenuationStartDistance);
        event.putDouble("attenuationEndDistance", mAttenuationEndDistance);
        
        ThemedReactContext reactContext = (ThemedReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onOmniLightUpdate", event);
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

    public float calculateAttenuationAtDistance(float distance) {
        // Calculate light attenuation based on distance
        if (distance <= mAttenuationStartDistance) {
            return 1.0f; // Full intensity within start distance
        } else if (distance >= mAttenuationEndDistance) {
            return 0.0f; // No light beyond end distance
        } else {
            // Linear attenuation between start and end distances
            float range = mAttenuationEndDistance - mAttenuationStartDistance;
            float relativeDistance = distance - mAttenuationStartDistance;
            return 1.0f - (relativeDistance / range);
        }
    }

    public float calculateDistanceToPoint(float x, float y, float z) {
        // Calculate 3D distance from light position to a point
        float dx = x - mPosition[0];
        float dy = y - mPosition[1];
        float dz = z - mPosition[2];
        return (float) Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViroLog.debug(TAG, "Omni light attached to window");
        
        // TODO: Add omni light to ViroReact scene when attached to window
        updateOmniLight();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViroLog.debug(TAG, "Omni light detached from window");
        
        // TODO: Remove omni light from ViroReact scene when detached from window
    }

    // Getters for current values (useful for debugging and testing)
    public String getLightColor() { return mColor; }
    public float getIntensity() { return mIntensity; }
    public float getTemperature() { return mTemperature; }
    public float[] getPosition() { return mPosition.clone(); }
    public float getAttenuationStartDistance() { return mAttenuationStartDistance; }
    public float getAttenuationEndDistance() { return mAttenuationEndDistance; }
    public int getInfluenceBitMask() { return mInfluenceBitMask; }
}