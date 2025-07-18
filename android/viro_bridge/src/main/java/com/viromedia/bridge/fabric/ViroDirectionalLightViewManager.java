//
//  ViroDirectionalLightViewManager.java
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
import com.facebook.react.viewmanagers.ViroDirectionalLightManagerInterface;
import com.facebook.react.viewmanagers.ViroDirectionalLightManagerDelegate;

import com.viromedia.bridge.utility.ViroLog;

import java.util.Map;

/**
 * ViewManager for ViroDirectionalLight - Directional lighting in ViroReact
 * 
 * ViroDirectionalLight simulates parallel light rays like sunlight. It provides
 * infinite directional lighting that affects all objects in the scene uniformly
 * regardless of their distance from the light source. Commonly used for outdoor
 * scenes, general scene lighting, and shadow casting.
 */
public class ViroDirectionalLightViewManager extends SimpleViewManager<ViroDirectionalLightView>
        implements ViroDirectionalLightManagerInterface<ViroDirectionalLightView> {

    public static final String REACT_CLASS = "ViroDirectionalLight";
    private final ViewManagerDelegate<ViroDirectionalLightView> mDelegate;

    public ViroDirectionalLightViewManager() {
        mDelegate = new ViroDirectionalLightManagerDelegate<>(this);
    }

    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    @NonNull
    public ViroDirectionalLightView createViewInstance(@NonNull ThemedReactContext context) {
        ViroLog.debug(REACT_CLASS, "Creating ViroDirectionalLight instance");
        return new ViroDirectionalLightView(context);
    }

    @Override
    public ViewManagerDelegate<ViroDirectionalLightView> getDelegate() {
        return mDelegate;
    }

    // Light Color and Intensity
    @Override
    @ReactProp(name = "color")
    public void setColor(ViroDirectionalLightView view, @Nullable String color) {
        ViroLog.debug(REACT_CLASS, "Setting color: " + color);
        view.setLightColor(color != null ? color : "#FFFFFF");
    }

    @Override
    @ReactProp(name = "intensity", defaultFloat = 1000.0f)
    public void setIntensity(ViroDirectionalLightView view, float intensity) {
        ViroLog.debug(REACT_CLASS, "Setting intensity: " + intensity);
        view.setIntensity(intensity);
    }

    @Override
    @ReactProp(name = "temperature", defaultFloat = 6500.0f)
    public void setTemperature(ViroDirectionalLightView view, float temperature) {
        ViroLog.debug(REACT_CLASS, "Setting temperature: " + temperature);
        view.setTemperature(temperature);
    }

    // Light Direction
    @Override
    @ReactProp(name = "direction")
    public void setDirection(ViroDirectionalLightView view, @Nullable ReadableArray direction) {
        ViroLog.debug(REACT_CLASS, "Setting direction: " + direction);
        view.setDirection(direction);
    }

    // Shadow Properties
    @Override
    @ReactProp(name = "castsShadow", defaultBoolean = true)
    public void setCastsShadow(ViroDirectionalLightView view, boolean castsShadow) {
        ViroLog.debug(REACT_CLASS, "Setting casts shadow: " + castsShadow);
        view.setCastsShadow(castsShadow);
    }

    @Override
    @ReactProp(name = "shadowOpacity", defaultFloat = 0.3f)
    public void setShadowOpacity(ViroDirectionalLightView view, float shadowOpacity) {
        ViroLog.debug(REACT_CLASS, "Setting shadow opacity: " + shadowOpacity);
        view.setShadowOpacity(shadowOpacity);
    }

    @Override
    @ReactProp(name = "shadowMapSize", defaultInt = 1024)
    public void setShadowMapSize(ViroDirectionalLightView view, int shadowMapSize) {
        ViroLog.debug(REACT_CLASS, "Setting shadow map size: " + shadowMapSize);
        view.setShadowMapSize(shadowMapSize);
    }

    @Override
    @ReactProp(name = "shadowBias", defaultFloat = 0.001f)
    public void setShadowBias(ViroDirectionalLightView view, float shadowBias) {
        ViroLog.debug(REACT_CLASS, "Setting shadow bias: " + shadowBias);
        view.setShadowBias(shadowBias);
    }

    @Override
    @ReactProp(name = "shadowNearZ", defaultFloat = 1.0f)
    public void setShadowNearZ(ViroDirectionalLightView view, float shadowNearZ) {
        ViroLog.debug(REACT_CLASS, "Setting shadow near Z: " + shadowNearZ);
        view.setShadowNearZ(shadowNearZ);
    }

    @Override
    @ReactProp(name = "shadowFarZ", defaultFloat = 100.0f)
    public void setShadowFarZ(ViroDirectionalLightView view, float shadowFarZ) {
        ViroLog.debug(REACT_CLASS, "Setting shadow far Z: " + shadowFarZ);
        view.setShadowFarZ(shadowFarZ);
    }

    // Light Influence
    @Override
    @ReactProp(name = "influenceBitMask", defaultInt = 1)
    public void setInfluenceBitMask(ViroDirectionalLightView view, int influenceBitMask) {
        ViroLog.debug(REACT_CLASS, "Setting influence bit mask: " + influenceBitMask);
        view.setInfluenceBitMask(influenceBitMask);
    }

    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onDirectionalLightUpdate", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onDirectionalLightUpdate")))
                .build();
    }
}