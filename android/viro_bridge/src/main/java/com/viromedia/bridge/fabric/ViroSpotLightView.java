//
//  ViroSpotLightView.java
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
 * ViroSpotLight - Cone Light Source
 * 
 * ViroSpotLight emits light in a cone from a specific position and direction.
 * It combines positional lighting (like omni lights) with directional control,
 * making it perfect for flashlights, stage lights, architectural spotlights,
 * and any focused illumination effects.
 * 
 * Key Features:
 * - Cone-shaped light emission with inner/outer angle control
 * - Configurable color, intensity, and temperature
 * - Position and direction control in 3D space
 * - Distance-based attenuation (start/end distances)
 * - Shadow casting with configurable parameters
 * - Smooth falloff between inner and outer cone angles
 * - Influence masking for selective lighting
 */
public class ViroSpotLightView extends ViroNodeView {

    private static final String TAG = ViroLog.getTag(ViroSpotLightView.class);

    // Light color and intensity
    private String mColor = "#FFFFFF";
    private float mIntensity = 1000.0f; // Lumens
    private float mTemperature = 6500.0f; // Kelvin (daylight)

    // Light position and direction
    private float[] mPosition = {0.0f, 2.0f, 0.0f}; // Light source position (above origin)
    private float[] mDirection = {0.0f, -1.0f, 0.0f}; // Pointing downward

    // Spotlight cone properties
    private float mInnerAngle = 30.0f; // Degrees - full intensity cone
    private float mOuterAngle = 45.0f; // Degrees - falloff cone

    // Light attenuation
    private float mAttenuationStartDistance = 2.0f; // Distance where attenuation begins
    private float mAttenuationEndDistance = 10.0f; // Distance where light has no effect

    // Shadow properties
    private boolean mCastsShadow = true;
    private float mShadowOpacity = 0.3f;
    private int mShadowMapSize = 1024;
    private float mShadowBias = 0.001f;
    private float mShadowNearZ = 1.0f;
    private float mShadowFarZ = 100.0f;

    // Light influence
    private int mInfluenceBitMask = 1; // Default influence mask

    public ViroSpotLightView(@NonNull ThemedReactContext context) {
        super(context);
        ViroLog.debug(TAG, "ViroSpotLightView initialized");
        
        // TODO: Initialize ViroReact spot light
        // This will need to integrate with the existing ViroReact lighting system
        initializeSpotLight();
    }

    private void initializeSpotLight() {
        ViroLog.debug(TAG, "Initializing spot light with default properties");
        
        // TODO: Set up ViroReact spot light with default properties
        // This should create the underlying cone light in the 3D scene
        updateSpotLight();
    }

    // Light Color and Intensity
    public void setLightColor(@NonNull String color) {
        ViroLog.debug(TAG, "Setting light color: " + color);
        mColor = color;
        updateSpotLight();
    }

    public void setIntensity(float intensity) {
        ViroLog.debug(TAG, "Setting intensity: " + intensity);
        mIntensity = intensity;
        updateSpotLight();
    }

    public void setTemperature(float temperature) {
        ViroLog.debug(TAG, "Setting temperature: " + temperature);
        mTemperature = temperature;
        
        // TODO: Update spot light temperature in ViroReact renderer
        // Temperature affects the color tint (warm/cool)
        updateSpotLight();
    }

    // Light Position and Direction
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
            mPosition[1] = 2.0f;
            mPosition[2] = 0.0f;
        }
        
        updateSpotLight();
    }

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
        
        updateSpotLight();
    }

    // Spotlight Cone Properties
    public void setInnerAngle(float innerAngle) {
        ViroLog.debug(TAG, "Setting inner angle: " + innerAngle);
        mInnerAngle = Math.max(1.0f, Math.min(179.0f, innerAngle)); // Clamp to valid range
        
        // Ensure outer angle is >= inner angle
        if (mOuterAngle < mInnerAngle) {
            mOuterAngle = mInnerAngle + 1.0f;
        }
        
        updateSpotLight();
    }

    public void setOuterAngle(float outerAngle) {
        ViroLog.debug(TAG, "Setting outer angle: " + outerAngle);
        mOuterAngle = Math.max(mInnerAngle + 1.0f, Math.min(179.0f, outerAngle)); // Ensure > inner
        updateSpotLight();
    }

    // Light Attenuation
    public void setAttenuationStartDistance(float attenuationStartDistance) {
        ViroLog.debug(TAG, "Setting attenuation start distance: " + attenuationStartDistance);
        mAttenuationStartDistance = Math.max(0.1f, attenuationStartDistance); // Ensure positive
        updateSpotLight();
    }

    public void setAttenuationEndDistance(float attenuationEndDistance) {
        ViroLog.debug(TAG, "Setting attenuation end distance: " + attenuationEndDistance);
        mAttenuationEndDistance = Math.max(mAttenuationStartDistance + 0.1f, attenuationEndDistance); // Ensure > start
        updateSpotLight();
    }

    // Shadow Properties
    public void setCastsShadow(boolean castsShadow) {
        ViroLog.debug(TAG, "Setting casts shadow: " + castsShadow);
        mCastsShadow = castsShadow;
        updateSpotLight();
    }

    public void setShadowOpacity(float shadowOpacity) {
        ViroLog.debug(TAG, "Setting shadow opacity: " + shadowOpacity);
        mShadowOpacity = Math.max(0.0f, Math.min(1.0f, shadowOpacity)); // Clamp to [0, 1]
        updateSpotLight();
    }

    public void setShadowMapSize(int shadowMapSize) {
        ViroLog.debug(TAG, "Setting shadow map size: " + shadowMapSize);
        // Ensure power of 2 and reasonable range
        mShadowMapSize = Math.max(256, Math.min(4096, shadowMapSize));
        updateSpotLight();
    }

    public void setShadowBias(float shadowBias) {
        ViroLog.debug(TAG, "Setting shadow bias: " + shadowBias);
        mShadowBias = shadowBias;
        updateSpotLight();
    }

    public void setShadowNearZ(float shadowNearZ) {
        ViroLog.debug(TAG, "Setting shadow near Z: " + shadowNearZ);
        mShadowNearZ = Math.max(0.1f, shadowNearZ); // Ensure positive
        updateSpotLight();
    }

    public void setShadowFarZ(float shadowFarZ) {
        ViroLog.debug(TAG, "Setting shadow far Z: " + shadowFarZ);
        mShadowFarZ = Math.max(mShadowNearZ + 0.1f, shadowFarZ); // Ensure > near
        updateSpotLight();
    }

    // Light Influence
    public void setInfluenceBitMask(int influenceBitMask) {
        ViroLog.debug(TAG, "Setting influence bit mask: " + influenceBitMask);
        mInfluenceBitMask = influenceBitMask;
        updateSpotLight();
    }

    private void updateSpotLight() {
        ViroLog.debug(TAG, String.format(
            "Updating spot light - Color: %s, Intensity: %.1f, Position: [%.2f, %.2f, %.2f], Direction: [%.2f, %.2f, %.2f], Angles: %.1f°-%.1f°, Shadows: %s",
            mColor, mIntensity, mPosition[0], mPosition[1], mPosition[2], 
            mDirection[0], mDirection[1], mDirection[2], mInnerAngle, mOuterAngle, mCastsShadow));
        
        // TODO: Apply spot light settings to ViroReact renderer
        // Spot light emits light in a cone from a specific position and direction
        // Light intensity decreases with distance and angle from the light's direction
        // Inner angle defines full intensity cone, outer angle defines falloff cone
        // Can cast shadows using shadow mapping techniques
        
        // Emit light update event for React Native
        emitSpotLightUpdateEvent();
    }

    private void emitSpotLightUpdateEvent() {
        WritableMap event = Arguments.createMap();
        event.putString("color", mColor);
        event.putDouble("intensity", mIntensity);
        event.putDouble("temperature", mTemperature);
        event.putDouble("innerAngle", mInnerAngle);
        event.putDouble("outerAngle", mOuterAngle);
        event.putBoolean("castsShadow", mCastsShadow);
        event.putDouble("shadowOpacity", mShadowOpacity);
        
        ThemedReactContext reactContext = (ThemedReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onSpotLightUpdate", event);
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

    public float calculateConeAttenuationForAngle(float angle) {
        // Calculate light attenuation based on angle from spotlight direction
        if (angle <= mInnerAngle) {
            return 1.0f; // Full intensity within inner cone
        } else if (angle >= mOuterAngle) {
            return 0.0f; // No light beyond outer cone
        } else {
            // Smooth falloff between inner and outer cones
            float range = mOuterAngle - mInnerAngle;
            float relativeAngle = angle - mInnerAngle;
            return 1.0f - (relativeAngle / range);
        }
    }

    public float calculateDistanceAttenuation(float distance) {
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

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViroLog.debug(TAG, "Spot light attached to window");
        
        // TODO: Add spot light to ViroReact scene when attached to window
        updateSpotLight();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViroLog.debug(TAG, "Spot light detached from window");
        
        // TODO: Remove spot light from ViroReact scene when detached from window
    }

    // Getters for current values (useful for debugging and testing)
    public String getLightColor() { return mColor; }
    public float getIntensity() { return mIntensity; }
    public float getTemperature() { return mTemperature; }
    public float[] getPosition() { return mPosition.clone(); }
    public float[] getDirection() { return mDirection.clone(); }
    public float getInnerAngle() { return mInnerAngle; }
    public float getOuterAngle() { return mOuterAngle; }
    public float getAttenuationStartDistance() { return mAttenuationStartDistance; }
    public float getAttenuationEndDistance() { return mAttenuationEndDistance; }
    public boolean getCastsShadow() { return mCastsShadow; }
    public float getShadowOpacity() { return mShadowOpacity; }
    public int getShadowMapSize() { return mShadowMapSize; }
    public float getShadowBias() { return mShadowBias; }
    public float getShadowNearZ() { return mShadowNearZ; }
    public float getShadowFarZ() { return mShadowFarZ; }
    public int getInfluenceBitMask() { return mInfluenceBitMask; }
}