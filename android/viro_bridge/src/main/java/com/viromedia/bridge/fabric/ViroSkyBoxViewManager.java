//
//  ViroSkyBoxViewManager.java
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
 * ViroSkyBoxViewManager - 360° Skybox Environment ViewManager
 * 
 * This ViewManager handles 360° skybox environments for ViroReact applications.
 * It provides immersive background environments using cube maps, equirectangular images,
 * or color gradients that surround the entire 3D scene.
 * 
 * Key Features:
 * - Cube map skybox support (6 individual textures)
 * - Equirectangular (360°) image support
 * - Color gradient skybox generation
 * - HDR skybox support for realistic lighting
 * - Rotation and positioning controls
 * - Fade in/out animations
 * - Format support: JPG, PNG, HDR, EXR
 * - Integration with ViroReact lighting system
 */
public class ViroSkyBoxViewManager extends SimpleViewManager<ViroSkyBoxView> {
    
    private static final String TAG = ViroLog.getTag(ViroSkyBoxViewManager.class);
    private static final String REACT_CLASS = "ViroSkyBox";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroSkyBoxView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroSkyBoxView instance");
        return new ViroSkyBoxView(reactContext);
    }
    
    // Skybox Content
    @ReactProp(name = "source")
    public void setSource(ViroSkyBoxView view, @Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        view.setSource(source);
    }
    
    @ReactProp(name = "cubeMap")
    public void setCubeMap(ViroSkyBoxView view, @Nullable ReadableMap cubeMap) {
        ViroLog.debug(TAG, "Setting cube map: " + cubeMap);
        view.setCubeMap(cubeMap);
    }
    
    @ReactProp(name = "equirectangular")
    public void setEquirectangular(ViroSkyBoxView view, @Nullable ReadableMap equirectangular) {
        ViroLog.debug(TAG, "Setting equirectangular: " + equirectangular);
        view.setEquirectangular(equirectangular);
    }
    
    @ReactProp(name = "colorTop")
    public void setColorTop(ViroSkyBoxView view, @Nullable ReadableArray colorTop) {
        ViroLog.debug(TAG, "Setting color top: " + colorTop);
        view.setColorTop(colorTop);
    }
    
    @ReactProp(name = "colorBottom")
    public void setColorBottom(ViroSkyBoxView view, @Nullable ReadableArray colorBottom) {
        ViroLog.debug(TAG, "Setting color bottom: " + colorBottom);
        view.setColorBottom(colorBottom);
    }
    
    @ReactProp(name = "colorLeft")
    public void setColorLeft(ViroSkyBoxView view, @Nullable ReadableArray colorLeft) {
        ViroLog.debug(TAG, "Setting color left: " + colorLeft);
        view.setColorLeft(colorLeft);
    }
    
    @ReactProp(name = "colorRight")
    public void setColorRight(ViroSkyBoxView view, @Nullable ReadableArray colorRight) {
        ViroLog.debug(TAG, "Setting color right: " + colorRight);
        view.setColorRight(colorRight);
    }
    
    @ReactProp(name = "colorFront")
    public void setColorFront(ViroSkyBoxView view, @Nullable ReadableArray colorFront) {
        ViroLog.debug(TAG, "Setting color front: " + colorFront);
        view.setColorFront(colorFront);
    }
    
    @ReactProp(name = "colorBack")
    public void setColorBack(ViroSkyBoxView view, @Nullable ReadableArray colorBack) {
        ViroLog.debug(TAG, "Setting color back: " + colorBack);
        view.setColorBack(colorBack);
    }
    
    // Skybox Appearance
    @ReactProp(name = "opacity", defaultFloat = 1.0f)
    public void setOpacity(ViroSkyBoxView view, float opacity) {
        ViroLog.debug(TAG, "Setting opacity: " + opacity);
        view.setOpacity(opacity);
    }
    
    @ReactProp(name = "brightness", defaultFloat = 1.0f)
    public void setBrightness(ViroSkyBoxView view, float brightness) {
        ViroLog.debug(TAG, "Setting brightness: " + brightness);
        view.setBrightness(brightness);
    }
    
    @ReactProp(name = "contrast", defaultFloat = 1.0f)
    public void setContrast(ViroSkyBoxView view, float contrast) {
        ViroLog.debug(TAG, "Setting contrast: " + contrast);
        view.setContrast(contrast);
    }
    
    @ReactProp(name = "saturation", defaultFloat = 1.0f)
    public void setSaturation(ViroSkyBoxView view, float saturation) {
        ViroLog.debug(TAG, "Setting saturation: " + saturation);
        view.setSaturation(saturation);
    }
    
    @ReactProp(name = "gamma", defaultFloat = 1.0f)
    public void setGamma(ViroSkyBoxView view, float gamma) {
        ViroLog.debug(TAG, "Setting gamma: " + gamma);
        view.setGamma(gamma);
    }
    
    @ReactProp(name = "exposure", defaultFloat = 0.0f)
    public void setExposure(ViroSkyBoxView view, float exposure) {
        ViroLog.debug(TAG, "Setting exposure: " + exposure);
        view.setExposure(exposure);
    }
    
    // Skybox Geometry
    @ReactProp(name = "rotation")
    public void setRotation(ViroSkyBoxView view, @Nullable ReadableArray rotation) {
        ViroLog.debug(TAG, "Setting rotation: " + rotation);
        view.setRotation(rotation);
    }
    
    @ReactProp(name = "scale")
    public void setScale(ViroSkyBoxView view, @Nullable ReadableArray scale) {
        ViroLog.debug(TAG, "Setting scale: " + scale);
        view.setScale(scale);
    }
    
    @ReactProp(name = "radius", defaultFloat = 1000.0f)
    public void setRadius(ViroSkyBoxView view, float radius) {
        ViroLog.debug(TAG, "Setting radius: " + radius);
        view.setRadius(radius);
    }
    
    @ReactProp(name = "segments", defaultInt = 50)
    public void setSegments(ViroSkyBoxView view, int segments) {
        ViroLog.debug(TAG, "Setting segments: " + segments);
        view.setSegments(segments);
    }
    
    // Skybox Behavior
    @ReactProp(name = "format")
    public void setFormat(ViroSkyBoxView view, @Nullable String format) {
        ViroLog.debug(TAG, "Setting format: " + format);
        view.setFormat(format);
    }
    
    @ReactProp(name = "type")
    public void setType(ViroSkyBoxView view, @Nullable String type) {
        ViroLog.debug(TAG, "Setting type: " + type);
        view.setType(type);
    }
    
    @ReactProp(name = "lightingEnabled", defaultBoolean = true)
    public void setLightingEnabled(ViroSkyBoxView view, boolean lightingEnabled) {
        ViroLog.debug(TAG, "Setting lighting enabled: " + lightingEnabled);
        view.setLightingEnabled(lightingEnabled);
    }
    
    @ReactProp(name = "environmentBlending")
    public void setEnvironmentBlending(ViroSkyBoxView view, @Nullable String environmentBlending) {
        ViroLog.debug(TAG, "Setting environment blending: " + environmentBlending);
        view.setEnvironmentBlending(environmentBlending);
    }
    
    @ReactProp(name = "rotationEnabled", defaultBoolean = false)
    public void setRotationEnabled(ViroSkyBoxView view, boolean rotationEnabled) {
        ViroLog.debug(TAG, "Setting rotation enabled: " + rotationEnabled);
        view.setRotationEnabled(rotationEnabled);
    }
    
    @ReactProp(name = "autoRotate", defaultBoolean = false)
    public void setAutoRotate(ViroSkyBoxView view, boolean autoRotate) {
        ViroLog.debug(TAG, "Setting auto rotate: " + autoRotate);
        view.setAutoRotate(autoRotate);
    }
    
    @ReactProp(name = "rotationSpeed", defaultFloat = 1.0f)
    public void setRotationSpeed(ViroSkyBoxView view, float rotationSpeed) {
        ViroLog.debug(TAG, "Setting rotation speed: " + rotationSpeed);
        view.setRotationSpeed(rotationSpeed);
    }
    
    // Skybox Animation
    @ReactProp(name = "fadeInDuration", defaultFloat = 1.0f)
    public void setFadeInDuration(ViroSkyBoxView view, float fadeInDuration) {
        ViroLog.debug(TAG, "Setting fade in duration: " + fadeInDuration);
        view.setFadeInDuration(fadeInDuration);
    }
    
    @ReactProp(name = "fadeOutDuration", defaultFloat = 1.0f)
    public void setFadeOutDuration(ViroSkyBoxView view, float fadeOutDuration) {
        ViroLog.debug(TAG, "Setting fade out duration: " + fadeOutDuration);
        view.setFadeOutDuration(fadeOutDuration);
    }
    
    @ReactProp(name = "transitionDuration", defaultFloat = 1.0f)
    public void setTransitionDuration(ViroSkyBoxView view, float transitionDuration) {
        ViroLog.debug(TAG, "Setting transition duration: " + transitionDuration);
        view.setTransitionDuration(transitionDuration);
    }
    
    @ReactProp(name = "animationEasing")
    public void setAnimationEasing(ViroSkyBoxView view, @Nullable String animationEasing) {
        ViroLog.debug(TAG, "Setting animation easing: " + animationEasing);
        view.setAnimationEasing(animationEasing);
    }
    
    // Skybox Loading
    @ReactProp(name = "loadingEnabled", defaultBoolean = true)
    public void setLoadingEnabled(ViroSkyBoxView view, boolean loadingEnabled) {
        ViroLog.debug(TAG, "Setting loading enabled: " + loadingEnabled);
        view.setLoadingEnabled(loadingEnabled);
    }
    
    @ReactProp(name = "loadingColor")
    public void setLoadingColor(ViroSkyBoxView view, @Nullable ReadableArray loadingColor) {
        ViroLog.debug(TAG, "Setting loading color: " + loadingColor);
        view.setLoadingColor(loadingColor);
    }
    
    @ReactProp(name = "loadingOpacity", defaultFloat = 0.8f)
    public void setLoadingOpacity(ViroSkyBoxView view, float loadingOpacity) {
        ViroLog.debug(TAG, "Setting loading opacity: " + loadingOpacity);
        view.setLoadingOpacity(loadingOpacity);
    }
    
    @ReactProp(name = "preloadEnabled", defaultBoolean = false)
    public void setPreloadEnabled(ViroSkyBoxView view, boolean preloadEnabled) {
        ViroLog.debug(TAG, "Setting preload enabled: " + preloadEnabled);
        view.setPreloadEnabled(preloadEnabled);
    }
    
    @ReactProp(name = "cacheEnabled", defaultBoolean = true)
    public void setCacheEnabled(ViroSkyBoxView view, boolean cacheEnabled) {
        ViroLog.debug(TAG, "Setting cache enabled: " + cacheEnabled);
        view.setCacheEnabled(cacheEnabled);
    }
    
    // Event Exports
    @Override
    public java.util.Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return java.util.Map.of(
            "onLoad", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onLoad",
                    "captured", "onLoadCapture"
                )
            ),
            "onError", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onError",
                    "captured", "onErrorCapture"
                )
            ),
            "onLoadStart", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onLoadStart",
                    "captured", "onLoadStartCapture"
                )
            ),
            "onLoadEnd", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onLoadEnd",
                    "captured", "onLoadEndCapture"
                )
            ),
            "onProgress", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onProgress",
                    "captured", "onProgressCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroSkyBoxView view) {
        ViroLog.debug(TAG, "Dropping ViroSkyBoxView instance");
        super.onDropViewInstance(view);
    }
}