//
//  ViroMaterialVideoViewManager.java
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
 * ViroMaterialVideoViewManager - Video-as-Material ViewManager
 * 
 * This ViewManager handles videos as material textures for ViroReact applications.
 * It provides comprehensive video playback controls while serving as a texture source
 * for other 3D geometry components, enabling dynamic material effects.
 * 
 * Key Features:
 * - Video as texture material for 3D objects
 * - Comprehensive video playback controls
 * - Dynamic texture updating and synchronization
 * - Multiple video format support
 * - Texture mapping and UV controls
 * - Performance optimization for 3D rendering
 * - Alpha channel and transparency support
 * - Texture filtering and quality controls
 * - Memory management for video textures
 * - Integration with 3D materials
 */
public class ViroMaterialVideoViewManager extends SimpleViewManager<ViroMaterialVideoView> {
    
    private static final String TAG = ViroLog.getTag(ViroMaterialVideoViewManager.class);
    private static final String REACT_CLASS = "ViroMaterialVideo";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroMaterialVideoView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroMaterialVideoView instance");
        return new ViroMaterialVideoView(reactContext);
    }
    
    // Video Source Properties
    @ReactProp(name = "source")
    public void setSource(ViroMaterialVideoView view, @Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        view.setSource(source);
    }
    
    @ReactProp(name = "uri")
    public void setUri(ViroMaterialVideoView view, @Nullable String uri) {
        ViroLog.debug(TAG, "Setting uri: " + uri);
        view.setUri(uri);
    }
    
    @ReactProp(name = "local")
    public void setLocal(ViroMaterialVideoView view, @Nullable String local) {
        ViroLog.debug(TAG, "Setting local: " + local);
        view.setLocal(local);
    }
    
    @ReactProp(name = "resource")
    public void setResource(ViroMaterialVideoView view, @Nullable String resource) {
        ViroLog.debug(TAG, "Setting resource: " + resource);
        view.setResource(resource);
    }
    
    // Video Playback Control
    @ReactProp(name = "paused", defaultBoolean = false)
    public void setPaused(ViroMaterialVideoView view, boolean paused) {
        ViroLog.debug(TAG, "Setting paused: " + paused);
        view.setPaused(paused);
    }
    
    @ReactProp(name = "loop", defaultBoolean = false)
    public void setLoop(ViroMaterialVideoView view, boolean loop) {
        ViroLog.debug(TAG, "Setting loop: " + loop);
        view.setLoop(loop);
    }
    
    @ReactProp(name = "muted", defaultBoolean = false)
    public void setMuted(ViroMaterialVideoView view, boolean muted) {
        ViroLog.debug(TAG, "Setting muted: " + muted);
        view.setMuted(muted);
    }
    
    @ReactProp(name = "volume", defaultFloat = 1.0f)
    public void setVolume(ViroMaterialVideoView view, float volume) {
        ViroLog.debug(TAG, "Setting volume: " + volume);
        view.setVolume(volume);
    }
    
    @ReactProp(name = "rate", defaultFloat = 1.0f)
    public void setRate(ViroMaterialVideoView view, float rate) {
        ViroLog.debug(TAG, "Setting rate: " + rate);
        view.setRate(rate);
    }
    
    @ReactProp(name = "seekTime", defaultFloat = 0.0f)
    public void setSeekTime(ViroMaterialVideoView view, float seekTime) {
        ViroLog.debug(TAG, "Setting seek time: " + seekTime);
        view.setSeekTime(seekTime);
    }
    
    // Material Properties
    @ReactProp(name = "materialType")
    public void setMaterialType(ViroMaterialVideoView view, @Nullable String materialType) {
        ViroLog.debug(TAG, "Setting material type: " + materialType);
        view.setMaterialType(materialType);
    }
    
    @ReactProp(name = "textureSlot")
    public void setTextureSlot(ViroMaterialVideoView view, @Nullable String textureSlot) {
        ViroLog.debug(TAG, "Setting texture slot: " + textureSlot);
        view.setTextureSlot(textureSlot);
    }
    
    @ReactProp(name = "intensity", defaultFloat = 1.0f)
    public void setIntensity(ViroMaterialVideoView view, float intensity) {
        ViroLog.debug(TAG, "Setting intensity: " + intensity);
        view.setIntensity(intensity);
    }
    
    @ReactProp(name = "wrapS")
    public void setWrapS(ViroMaterialVideoView view, @Nullable String wrapS) {
        ViroLog.debug(TAG, "Setting wrap S: " + wrapS);
        view.setWrapS(wrapS);
    }
    
    @ReactProp(name = "wrapT")
    public void setWrapT(ViroMaterialVideoView view, @Nullable String wrapT) {
        ViroLog.debug(TAG, "Setting wrap T: " + wrapT);
        view.setWrapT(wrapT);
    }
    
    @ReactProp(name = "minificationFilter")
    public void setMinificationFilter(ViroMaterialVideoView view, @Nullable String minificationFilter) {
        ViroLog.debug(TAG, "Setting minification filter: " + minificationFilter);
        view.setMinificationFilter(minificationFilter);
    }
    
    @ReactProp(name = "magnificationFilter")
    public void setMagnificationFilter(ViroMaterialVideoView view, @Nullable String magnificationFilter) {
        ViroLog.debug(TAG, "Setting magnification filter: " + magnificationFilter);
        view.setMagnificationFilter(magnificationFilter);
    }
    
    @ReactProp(name = "mipmap", defaultBoolean = true)
    public void setMipmap(ViroMaterialVideoView view, boolean mipmap) {
        ViroLog.debug(TAG, "Setting mipmap: " + mipmap);
        view.setMipmap(mipmap);
    }
    
    // Texture Properties
    @ReactProp(name = "textureWidth", defaultInt = 1024)
    public void setTextureWidth(ViroMaterialVideoView view, int textureWidth) {
        ViroLog.debug(TAG, "Setting texture width: " + textureWidth);
        view.setTextureWidth(textureWidth);
    }
    
    @ReactProp(name = "textureHeight", defaultInt = 1024)
    public void setTextureHeight(ViroMaterialVideoView view, int textureHeight) {
        ViroLog.debug(TAG, "Setting texture height: " + textureHeight);
        view.setTextureHeight(textureHeight);
    }
    
    @ReactProp(name = "textureFormat")
    public void setTextureFormat(ViroMaterialVideoView view, @Nullable String textureFormat) {
        ViroLog.debug(TAG, "Setting texture format: " + textureFormat);
        view.setTextureFormat(textureFormat);
    }
    
    @ReactProp(name = "alphaMode")
    public void setAlphaMode(ViroMaterialVideoView view, @Nullable String alphaMode) {
        ViroLog.debug(TAG, "Setting alpha mode: " + alphaMode);
        view.setAlphaMode(alphaMode);
    }
    
    @ReactProp(name = "alphaThreshold", defaultFloat = 0.5f)
    public void setAlphaThreshold(ViroMaterialVideoView view, float alphaThreshold) {
        ViroLog.debug(TAG, "Setting alpha threshold: " + alphaThreshold);
        view.setAlphaThreshold(alphaThreshold);
    }
    
    // UV Mapping
    @ReactProp(name = "uvTransform")
    public void setUvTransform(ViroMaterialVideoView view, @Nullable ReadableArray uvTransform) {
        ViroLog.debug(TAG, "Setting UV transform: " + uvTransform);
        view.setUvTransform(uvTransform);
    }
    
    @ReactProp(name = "uvOffset")
    public void setUvOffset(ViroMaterialVideoView view, @Nullable ReadableArray uvOffset) {
        ViroLog.debug(TAG, "Setting UV offset: " + uvOffset);
        view.setUvOffset(uvOffset);
    }
    
    @ReactProp(name = "uvScale")
    public void setUvScale(ViroMaterialVideoView view, @Nullable ReadableArray uvScale) {
        ViroLog.debug(TAG, "Setting UV scale: " + uvScale);
        view.setUvScale(uvScale);
    }
    
    @ReactProp(name = "uvRotation", defaultFloat = 0.0f)
    public void setUvRotation(ViroMaterialVideoView view, float uvRotation) {
        ViroLog.debug(TAG, "Setting UV rotation: " + uvRotation);
        view.setUvRotation(uvRotation);
    }
    
    // Quality and Performance
    @ReactProp(name = "quality")
    public void setQuality(ViroMaterialVideoView view, @Nullable String quality) {
        ViroLog.debug(TAG, "Setting quality: " + quality);
        view.setQuality(quality);
    }
    
    @ReactProp(name = "streamingEnabled", defaultBoolean = true)
    public void setStreamingEnabled(ViroMaterialVideoView view, boolean streamingEnabled) {
        ViroLog.debug(TAG, "Setting streaming enabled: " + streamingEnabled);
        view.setStreamingEnabled(streamingEnabled);
    }
    
    @ReactProp(name = "preload", defaultBoolean = false)
    public void setPreload(ViroMaterialVideoView view, boolean preload) {
        ViroLog.debug(TAG, "Setting preload: " + preload);
        view.setPreload(preload);
    }
    
    @ReactProp(name = "cacheEnabled", defaultBoolean = true)
    public void setCacheEnabled(ViroMaterialVideoView view, boolean cacheEnabled) {
        ViroLog.debug(TAG, "Setting cache enabled: " + cacheEnabled);
        view.setCacheEnabled(cacheEnabled);
    }
    
    @ReactProp(name = "bufferSize", defaultInt = 1024)
    public void setBufferSize(ViroMaterialVideoView view, int bufferSize) {
        ViroLog.debug(TAG, "Setting buffer size: " + bufferSize);
        view.setBufferSize(bufferSize);
    }
    
    // Color Adjustments
    @ReactProp(name = "tintColor")
    public void setTintColor(ViroMaterialVideoView view, @Nullable ReadableArray tintColor) {
        ViroLog.debug(TAG, "Setting tint color: " + tintColor);
        view.setTintColor(tintColor);
    }
    
    @ReactProp(name = "brightness", defaultFloat = 1.0f)
    public void setBrightness(ViroMaterialVideoView view, float brightness) {
        ViroLog.debug(TAG, "Setting brightness: " + brightness);
        view.setBrightness(brightness);
    }
    
    @ReactProp(name = "contrast", defaultFloat = 1.0f)
    public void setContrast(ViroMaterialVideoView view, float contrast) {
        ViroLog.debug(TAG, "Setting contrast: " + contrast);
        view.setContrast(contrast);
    }
    
    @ReactProp(name = "saturation", defaultFloat = 1.0f)
    public void setSaturation(ViroMaterialVideoView view, float saturation) {
        ViroLog.debug(TAG, "Setting saturation: " + saturation);
        view.setSaturation(saturation);
    }
    
    @ReactProp(name = "gamma", defaultFloat = 1.0f)
    public void setGamma(ViroMaterialVideoView view, float gamma) {
        ViroLog.debug(TAG, "Setting gamma: " + gamma);
        view.setGamma(gamma);
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
            "onProgress", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onProgress",
                    "captured", "onProgressCapture"
                )
            ),
            "onPlay", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onPlay",
                    "captured", "onPlayCapture"
                )
            ),
            "onPause", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onPause",
                    "captured", "onPauseCapture"
                )
            ),
            "onEnd", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onEnd",
                    "captured", "onEndCapture"
                )
            ),
            "onBuffer", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onBuffer",
                    "captured", "onBufferCapture"
                )
            ),
            "onTextureUpdate", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onTextureUpdate",
                    "captured", "onTextureUpdateCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroMaterialVideoView view) {
        ViroLog.debug(TAG, "Dropping ViroMaterialVideoView instance");
        super.onDropViewInstance(view);
    }
}