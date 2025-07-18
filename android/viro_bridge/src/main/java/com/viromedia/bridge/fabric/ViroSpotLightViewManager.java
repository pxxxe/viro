//
//  ViroSpotLightViewManager.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.ViroSpotLightManagerInterface;
import com.facebook.react.viewmanagers.ViroSpotLightManagerDelegate;

import com.viromedia.bridge.utility.ViroLog;

import java.util.Map;

/**
 * ViewManager for ViroSpotLight - Cone lighting in ViroReact
 * 
 * ViroSpotLight emits light in a cone from a specific position and direction.
 * It combines the directional properties of directional lights with the positional
 * properties of point lights, making it ideal for flashlights, stage lighting,
 * architectural spotlights, and focused illumination effects.
 */
public class ViroSpotLightViewManager extends SimpleViewManager<ViroSpotLightView>
        implements ViroSpotLightManagerInterface<ViroSpotLightView> {

    public static final String REACT_CLASS = "ViroSpotLight";
    private final ViewManagerDelegate<ViroSpotLightView> mDelegate;

    public ViroSpotLightViewManager() {
        mDelegate = new ViroSpotLightManagerDelegate<>(this);
    }

    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    @NonNull
    public ViroSpotLightView createViewInstance(@NonNull ThemedReactContext context) {
        ViroLog.debug(REACT_CLASS, "Creating ViroSpotLight instance");
        return new ViroSpotLightView(context);
    }

    @Override
    public ViewManagerDelegate<ViroSpotLightView> getDelegate() {
        return mDelegate;
    }

    // Light Color and Intensity
    @Override
    @ReactProp(name = "color")
    public void setColor(ViroSpotLightView view, @Nullable String color) {
        ViroLog.debug(REACT_CLASS, "Setting color: " + color);
        view.setLightColor(color != null ? color : "#FFFFFF");
    }

    @Override
    @ReactProp(name = "intensity", defaultFloat = 1000.0f)
    public void setIntensity(ViroSpotLightView view, float intensity) {
        ViroLog.debug(REACT_CLASS, "Setting intensity: " + intensity);
        view.setIntensity(intensity);
    }

    @Override
    @ReactProp(name = "temperature", defaultFloat = 6500.0f)
    public void setTemperature(ViroSpotLightView view, float temperature) {
        ViroLog.debug(REACT_CLASS, "Setting temperature: " + temperature);
        view.setTemperature(temperature);
    }

    // Light Position and Direction
    @Override
    @ReactProp(name = "position")
    public void setPosition(ViroSpotLightView view, @Nullable ReadableArray position) {
        ViroLog.debug(REACT_CLASS, "Setting position: " + position);
        view.setPosition(position);
    }

    @Override
    @ReactProp(name = "direction")
    public void setDirection(ViroSpotLightView view, @Nullable ReadableArray direction) {
        ViroLog.debug(REACT_CLASS, "Setting direction: " + direction);
        view.setDirection(direction);
    }

    // Spotlight Cone Properties
    @Override
    @ReactProp(name = "innerAngle", defaultFloat = 30.0f)
    public void setInnerAngle(ViroSpotLightView view, float innerAngle) {
        ViroLog.debug(REACT_CLASS, "Setting inner angle: " + innerAngle);
        view.setInnerAngle(innerAngle);
    }

    @Override
    @ReactProp(name = "outerAngle", defaultFloat = 45.0f)
    public void setOuterAngle(ViroSpotLightView view, float outerAngle) {
        ViroLog.debug(REACT_CLASS, "Setting outer angle: " + outerAngle);
        view.setOuterAngle(outerAngle);
    }

    // Light Attenuation
    @Override
    @ReactProp(name = "attenuationStartDistance", defaultFloat = 2.0f)
    public void setAttenuationStartDistance(ViroSpotLightView view, float attenuationStartDistance) {
        ViroLog.debug(REACT_CLASS, "Setting attenuation start distance: " + attenuationStartDistance);
        view.setAttenuationStartDistance(attenuationStartDistance);
    }

    @Override
    @ReactProp(name = "attenuationEndDistance", defaultFloat = 10.0f)
    public void setAttenuationEndDistance(ViroSpotLightView view, float attenuationEndDistance) {
        ViroLog.debug(REACT_CLASS, "Setting attenuation end distance: " + attenuationEndDistance);
        view.setAttenuationEndDistance(attenuationEndDistance);
    }

    // Shadow Properties
    @Override
    @ReactProp(name = "castsShadow", defaultBoolean = true)
    public void setCastsShadow(ViroSpotLightView view, boolean castsShadow) {
        ViroLog.debug(REACT_CLASS, "Setting casts shadow: " + castsShadow);
        view.setCastsShadow(castsShadow);
    }

    @Override
    @ReactProp(name = "shadowOpacity", defaultFloat = 0.3f)
    public void setShadowOpacity(ViroSpotLightView view, float shadowOpacity) {
        ViroLog.debug(REACT_CLASS, "Setting shadow opacity: " + shadowOpacity);
        view.setShadowOpacity(shadowOpacity);
    }

    @Override
    @ReactProp(name = "shadowMapSize", defaultInt = 1024)
    public void setShadowMapSize(ViroSpotLightView view, int shadowMapSize) {
        ViroLog.debug(REACT_CLASS, "Setting shadow map size: " + shadowMapSize);
        view.setShadowMapSize(shadowMapSize);
    }

    @Override
    @ReactProp(name = "shadowBias", defaultFloat = 0.001f)
    public void setShadowBias(ViroSpotLightView view, float shadowBias) {
        ViroLog.debug(REACT_CLASS, "Setting shadow bias: " + shadowBias);
        view.setShadowBias(shadowBias);
    }

    @Override
    @ReactProp(name = "shadowNearZ", defaultFloat = 1.0f)
    public void setShadowNearZ(ViroSpotLightView view, float shadowNearZ) {
        ViroLog.debug(REACT_CLASS, "Setting shadow near Z: " + shadowNearZ);
        view.setShadowNearZ(shadowNearZ);
    }

    @Override
    @ReactProp(name = "shadowFarZ", defaultFloat = 100.0f)
    public void setShadowFarZ(ViroSpotLightView view, float shadowFarZ) {
        ViroLog.debug(REACT_CLASS, "Setting shadow far Z: " + shadowFarZ);
        view.setShadowFarZ(shadowFarZ);
    }

    // Light Influence
    @Override
    @ReactProp(name = "influenceBitMask", defaultInt = 1)
    public void setInfluenceBitMask(ViroSpotLightView view, int influenceBitMask) {
        ViroLog.debug(REACT_CLASS, "Setting influence bit mask: " + influenceBitMask);
        view.setInfluenceBitMask(influenceBitMask);
    }

    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onSpotLightUpdate", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onSpotLightUpdate")))
                .build();
    }
}