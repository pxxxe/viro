//
//  ViroOmniLightViewManager.java
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
import com.facebook.react.viewmanagers.ViroOmniLightManagerInterface;
import com.facebook.react.viewmanagers.ViroOmniLightManagerDelegate;

import com.viromedia.bridge.utility.ViroLog;

import java.util.Map;

/**
 * ViewManager for ViroOmniLight - Point lighting in ViroReact
 * 
 * ViroOmniLight (also known as Point Light) emits light uniformly in all directions
 * from a single point in 3D space. It's commonly used for simulating light bulbs,
 * torches, campfires, or any light source that radiates omnidirectionally.
 */
public class ViroOmniLightViewManager extends SimpleViewManager<ViroOmniLightView>
        implements ViroOmniLightManagerInterface<ViroOmniLightView> {

    public static final String REACT_CLASS = "ViroOmniLight";
    private final ViewManagerDelegate<ViroOmniLightView> mDelegate;

    public ViroOmniLightViewManager() {
        mDelegate = new ViroOmniLightManagerDelegate<>(this);
    }

    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    @NonNull
    public ViroOmniLightView createViewInstance(@NonNull ThemedReactContext context) {
        ViroLog.debug(REACT_CLASS, "Creating ViroOmniLight instance");
        return new ViroOmniLightView(context);
    }

    @Override
    public ViewManagerDelegate<ViroOmniLightView> getDelegate() {
        return mDelegate;
    }

    // Light Color and Intensity
    @Override
    @ReactProp(name = "color")
    public void setColor(ViroOmniLightView view, @Nullable String color) {
        ViroLog.debug(REACT_CLASS, "Setting color: " + color);
        view.setLightColor(color != null ? color : "#FFFFFF");
    }

    @Override
    @ReactProp(name = "intensity", defaultFloat = 1000.0f)
    public void setIntensity(ViroOmniLightView view, float intensity) {
        ViroLog.debug(REACT_CLASS, "Setting intensity: " + intensity);
        view.setIntensity(intensity);
    }

    @Override
    @ReactProp(name = "temperature", defaultFloat = 6500.0f)
    public void setTemperature(ViroOmniLightView view, float temperature) {
        ViroLog.debug(REACT_CLASS, "Setting temperature: " + temperature);
        view.setTemperature(temperature);
    }

    // Light Position
    @Override
    @ReactProp(name = "position")
    public void setPosition(ViroOmniLightView view, @Nullable ReadableArray position) {
        ViroLog.debug(REACT_CLASS, "Setting position: " + position);
        view.setPosition(position);
    }

    // Light Attenuation
    @Override
    @ReactProp(name = "attenuationStartDistance", defaultFloat = 2.0f)
    public void setAttenuationStartDistance(ViroOmniLightView view, float attenuationStartDistance) {
        ViroLog.debug(REACT_CLASS, "Setting attenuation start distance: " + attenuationStartDistance);
        view.setAttenuationStartDistance(attenuationStartDistance);
    }

    @Override
    @ReactProp(name = "attenuationEndDistance", defaultFloat = 10.0f)
    public void setAttenuationEndDistance(ViroOmniLightView view, float attenuationEndDistance) {
        ViroLog.debug(REACT_CLASS, "Setting attenuation end distance: " + attenuationEndDistance);
        view.setAttenuationEndDistance(attenuationEndDistance);
    }

    // Light Influence
    @Override
    @ReactProp(name = "influenceBitMask", defaultInt = 1)
    public void setInfluenceBitMask(ViroOmniLightView view, int influenceBitMask) {
        ViroLog.debug(REACT_CLASS, "Setting influence bit mask: " + influenceBitMask);
        view.setInfluenceBitMask(influenceBitMask);
    }

    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onOmniLightUpdate", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onOmniLightUpdate")))
                .build();
    }
}