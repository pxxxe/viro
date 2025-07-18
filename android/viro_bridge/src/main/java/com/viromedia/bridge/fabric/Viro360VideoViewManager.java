//
//  Viro360VideoViewManager.java
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
 * Viro360VideoViewManager - 360° Immersive Video ViewManager
 * 
 * This ViewManager handles 360° immersive videos for ViroReact applications.
 * It renders spherical video experiences using equirectangular 360° videos,
 * stereoscopic 3D content, and comprehensive playback controls for creating
 * VR video experiences and immersive media content.
 * 
 * Key Features:
 * - 360° equirectangular video support
 * - Stereoscopic 3D video (side-by-side, top-bottom, over-under)
 * - Comprehensive video playback controls
 * - Dynamic video loading and streaming
 * - Resolution and quality optimization
 * - Rotation and orientation controls
 * - Loading states and buffering management
 * - HDR video support
 * - Performance optimization for large videos
 * - Audio synchronization and 3D spatial audio
 */
public class Viro360VideoViewManager extends SimpleViewManager<Viro360VideoView> {
    
    private static final String TAG = ViroLog.getTag(Viro360VideoViewManager.class);
    private static final String REACT_CLASS = "Viro360Video";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public Viro360VideoView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating Viro360VideoView instance");
        return new Viro360VideoView(reactContext);
    }
    
    // Video Source Properties
    @ReactProp(name = "source")
    public void setSource(Viro360VideoView view, @Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        view.setSource(source);
    }
    
    @ReactProp(name = "uri")
    public void setUri(Viro360VideoView view, @Nullable String uri) {
        ViroLog.debug(TAG, "Setting uri: " + uri);
        view.setUri(uri);
    }
    
    @ReactProp(name = "local")
    public void setLocal(Viro360VideoView view, @Nullable String local) {
        ViroLog.debug(TAG, "Setting local: " + local);
        view.setLocal(local);
    }
    
    @ReactProp(name = "resource")
    public void setResource(Viro360VideoView view, @Nullable String resource) {
        ViroLog.debug(TAG, "Setting resource: " + resource);
        view.setResource(resource);
    }
    
    // Video Format and Type
    @ReactProp(name = "format")
    public void setFormat(Viro360VideoView view, @Nullable String format) {
        ViroLog.debug(TAG, "Setting format: " + format);
        view.setFormat(format);
    }
    
    @ReactProp(name = "videoType")
    public void setVideoType(Viro360VideoView view, @Nullable String videoType) {
        ViroLog.debug(TAG, "Setting video type: " + videoType);
        view.setVideoType(videoType);
    }
    
    @ReactProp(name = "projection")
    public void setProjection(Viro360VideoView view, @Nullable String projection) {
        ViroLog.debug(TAG, "Setting projection: " + projection);
        view.setProjection(projection);
    }
    
    @ReactProp(name = "mapping")
    public void setMapping(Viro360VideoView view, @Nullable String mapping) {
        ViroLog.debug(TAG, "Setting mapping: " + mapping);
        view.setMapping(mapping);
    }
    
    // Stereoscopic Properties
    @ReactProp(name = "stereoscopicMode")
    public void setStereoscopicMode(Viro360VideoView view, @Nullable String stereoscopicMode) {
        ViroLog.debug(TAG, "Setting stereoscopic mode: " + stereoscopicMode);
        view.setStereoscopicMode(stereoscopicMode);
    }
    
    @ReactProp(name = "eyeType")
    public void setEyeType(Viro360VideoView view, @Nullable String eyeType) {
        ViroLog.debug(TAG, "Setting eye type: " + eyeType);
        view.setEyeType(eyeType);
    }
    
    @ReactProp(name = "interpupillaryDistance", defaultFloat = 0.064f)
    public void setInterpupillaryDistance(Viro360VideoView view, float interpupillaryDistance) {
        ViroLog.debug(TAG, "Setting interpupillary distance: " + interpupillaryDistance);
        view.setInterpupillaryDistance(interpupillaryDistance);
    }
    
    @ReactProp(name = "eyeSeparation", defaultFloat = 0.064f)
    public void setEyeSeparation(Viro360VideoView view, float eyeSeparation) {
        ViroLog.debug(TAG, "Setting eye separation: " + eyeSeparation);
        view.setEyeSeparation(eyeSeparation);
    }
    
    // Video Playback Control
    @ReactProp(name = "paused", defaultBoolean = false)
    public void setPaused(Viro360VideoView view, boolean paused) {
        ViroLog.debug(TAG, "Setting paused: " + paused);
        view.setPaused(paused);
    }
    
    @ReactProp(name = "loop", defaultBoolean = false)
    public void setLoop(Viro360VideoView view, boolean loop) {
        ViroLog.debug(TAG, "Setting loop: " + loop);
        view.setLoop(loop);
    }
    
    @ReactProp(name = "muted", defaultBoolean = false)
    public void setMuted(Viro360VideoView view, boolean muted) {
        ViroLog.debug(TAG, "Setting muted: " + muted);
        view.setMuted(muted);
    }
    
    @ReactProp(name = "volume", defaultFloat = 1.0f)
    public void setVolume(Viro360VideoView view, float volume) {
        ViroLog.debug(TAG, "Setting volume: " + volume);
        view.setVolume(volume);
    }
    
    @ReactProp(name = "rate", defaultFloat = 1.0f)
    public void setRate(Viro360VideoView view, float rate) {
        ViroLog.debug(TAG, "Setting rate: " + rate);
        view.setRate(rate);
    }
    
    @ReactProp(name = "seekTime", defaultFloat = 0.0f)
    public void setSeekTime(Viro360VideoView view, float seekTime) {
        ViroLog.debug(TAG, "Setting seek time: " + seekTime);
        view.setSeekTime(seekTime);
    }
    
    @ReactProp(name = "currentTime", defaultFloat = 0.0f)
    public void setCurrentTime(Viro360VideoView view, float currentTime) {
        ViroLog.debug(TAG, "Setting current time: " + currentTime);
        view.setCurrentTime(currentTime);
    }
    
    // Video Quality and Streaming
    @ReactProp(name = "quality")
    public void setQuality(Viro360VideoView view, @Nullable String quality) {
        ViroLog.debug(TAG, "Setting quality: " + quality);
        view.setQuality(quality);
    }
    
    @ReactProp(name = "resolution")
    public void setResolution(Viro360VideoView view, @Nullable String resolution) {
        ViroLog.debug(TAG, "Setting resolution: " + resolution);
        view.setResolution(resolution);
    }
    
    @ReactProp(name = "bitrate", defaultInt = 0)
    public void setBitrate(Viro360VideoView view, int bitrate) {
        ViroLog.debug(TAG, "Setting bitrate: " + bitrate);
        view.setBitrate(bitrate);
    }
    
    @ReactProp(name = "maxBitrate", defaultInt = 0)
    public void setMaxBitrate(Viro360VideoView view, int maxBitrate) {
        ViroLog.debug(TAG, "Setting max bitrate: " + maxBitrate);
        view.setMaxBitrate(maxBitrate);
    }
    
    @ReactProp(name = "bufferSize", defaultInt = 1024)
    public void setBufferSize(Viro360VideoView view, int bufferSize) {
        ViroLog.debug(TAG, "Setting buffer size: " + bufferSize);
        view.setBufferSize(bufferSize);
    }
    
    @ReactProp(name = "streamingEnabled", defaultBoolean = true)
    public void setStreamingEnabled(Viro360VideoView view, boolean streamingEnabled) {
        ViroLog.debug(TAG, "Setting streaming enabled: " + streamingEnabled);
        view.setStreamingEnabled(streamingEnabled);
    }
    
    @ReactProp(name = "adaptiveBitrate", defaultBoolean = true)
    public void setAdaptiveBitrate(Viro360VideoView view, boolean adaptiveBitrate) {
        ViroLog.debug(TAG, "Setting adaptive bitrate: " + adaptiveBitrate);
        view.setAdaptiveBitrate(adaptiveBitrate);
    }
    
    // Rotation and Orientation
    @ReactProp(name = "rotation")
    public void setRotation(Viro360VideoView view, @Nullable ReadableArray rotation) {
        ViroLog.debug(TAG, "Setting rotation: " + rotation);
        view.setRotation(rotation);
    }
    
    @ReactProp(name = "rotationX", defaultFloat = 0.0f)
    public void setRotationX(Viro360VideoView view, float rotationX) {
        ViroLog.debug(TAG, "Setting rotation X: " + rotationX);
        view.setRotationX(rotationX);
    }
    
    @ReactProp(name = "rotationY", defaultFloat = 0.0f)
    public void setRotationY(Viro360VideoView view, float rotationY) {
        ViroLog.debug(TAG, "Setting rotation Y: " + rotationY);
        view.setRotationY(rotationY);
    }
    
    @ReactProp(name = "rotationZ", defaultFloat = 0.0f)
    public void setRotationZ(Viro360VideoView view, float rotationZ) {
        ViroLog.debug(TAG, "Setting rotation Z: " + rotationZ);
        view.setRotationZ(rotationZ);
    }
    
    @ReactProp(name = "orientation")
    public void setOrientation(Viro360VideoView view, @Nullable ReadableArray orientation) {
        ViroLog.debug(TAG, "Setting orientation: " + orientation);
        view.setOrientation(orientation);
    }
    
    // Display Properties
    @ReactProp(name = "radius", defaultFloat = 1000.0f)
    public void setRadius(Viro360VideoView view, float radius) {
        ViroLog.debug(TAG, "Setting radius: " + radius);
        view.setRadius(radius);
    }
    
    @ReactProp(name = "segmentWidth", defaultInt = 64)
    public void setSegmentWidth(Viro360VideoView view, int segmentWidth) {
        ViroLog.debug(TAG, "Setting segment width: " + segmentWidth);
        view.setSegmentWidth(segmentWidth);
    }
    
    @ReactProp(name = "segmentHeight", defaultInt = 32)
    public void setSegmentHeight(Viro360VideoView view, int segmentHeight) {
        ViroLog.debug(TAG, "Setting segment height: " + segmentHeight);
        view.setSegmentHeight(segmentHeight);
    }
    
    @ReactProp(name = "renderingOrder", defaultInt = 0)
    public void setRenderingOrder(Viro360VideoView view, int renderingOrder) {
        ViroLog.debug(TAG, "Setting rendering order: " + renderingOrder);
        view.setRenderingOrder(renderingOrder);
    }
    
    @ReactProp(name = "invertNormals", defaultBoolean = true)
    public void setInvertNormals(Viro360VideoView view, boolean invertNormals) {
        ViroLog.debug(TAG, "Setting invert normals: " + invertNormals);
        view.setInvertNormals(invertNormals);
    }
    
    // Loading and Caching
    @ReactProp(name = "preload", defaultBoolean = false)
    public void setPreload(Viro360VideoView view, boolean preload) {
        ViroLog.debug(TAG, "Setting preload: " + preload);
        view.setPreload(preload);
    }
    
    @ReactProp(name = "cacheEnabled", defaultBoolean = true)
    public void setCacheEnabled(Viro360VideoView view, boolean cacheEnabled) {
        ViroLog.debug(TAG, "Setting cache enabled: " + cacheEnabled);
        view.setCacheEnabled(cacheEnabled);
    }
    
    @ReactProp(name = "cacheSize", defaultInt = 100)
    public void setCacheSize(Viro360VideoView view, int cacheSize) {
        ViroLog.debug(TAG, "Setting cache size: " + cacheSize);
        view.setCacheSize(cacheSize);
    }
    
    @ReactProp(name = "loadingTimeout", defaultFloat = 30.0f)
    public void setLoadingTimeout(Viro360VideoView view, float loadingTimeout) {
        ViroLog.debug(TAG, "Setting loading timeout: " + loadingTimeout);
        view.setLoadingTimeout(loadingTimeout);
    }
    
    @ReactProp(name = "retryCount", defaultInt = 3)
    public void setRetryCount(Viro360VideoView view, int retryCount) {
        ViroLog.debug(TAG, "Setting retry count: " + retryCount);
        view.setRetryCount(retryCount);
    }
    
    @ReactProp(name = "progressiveLoading", defaultBoolean = true)
    public void setProgressiveLoading(Viro360VideoView view, boolean progressiveLoading) {
        ViroLog.debug(TAG, "Setting progressive loading: " + progressiveLoading);
        view.setProgressiveLoading(progressiveLoading);
    }
    
    // Audio Properties
    @ReactProp(name = "audioEnabled", defaultBoolean = true)
    public void setAudioEnabled(Viro360VideoView view, boolean audioEnabled) {
        ViroLog.debug(TAG, "Setting audio enabled: " + audioEnabled);
        view.setAudioEnabled(audioEnabled);
    }
    
    @ReactProp(name = "audioTracks")
    public void setAudioTracks(Viro360VideoView view, @Nullable ReadableArray audioTracks) {
        ViroLog.debug(TAG, "Setting audio tracks: " + audioTracks);
        view.setAudioTracks(audioTracks);
    }
    
    @ReactProp(name = "selectedAudioTrack", defaultInt = 0)
    public void setSelectedAudioTrack(Viro360VideoView view, int selectedAudioTrack) {
        ViroLog.debug(TAG, "Setting selected audio track: " + selectedAudioTrack);
        view.setSelectedAudioTrack(selectedAudioTrack);
    }
    
    @ReactProp(name = "spatialAudioEnabled", defaultBoolean = false)
    public void setSpatialAudioEnabled(Viro360VideoView view, boolean spatialAudioEnabled) {
        ViroLog.debug(TAG, "Setting spatial audio enabled: " + spatialAudioEnabled);
        view.setSpatialAudioEnabled(spatialAudioEnabled);
    }
    
    @ReactProp(name = "audioPosition")
    public void setAudioPosition(Viro360VideoView view, @Nullable ReadableArray audioPosition) {
        ViroLog.debug(TAG, "Setting audio position: " + audioPosition);
        view.setAudioPosition(audioPosition);
    }
    
    // Color and Effects
    @ReactProp(name = "tintColor")
    public void setTintColor(Viro360VideoView view, @Nullable ReadableArray tintColor) {
        ViroLog.debug(TAG, "Setting tint color: " + tintColor);
        view.setTintColor(tintColor);
    }
    
    @ReactProp(name = "brightness", defaultFloat = 1.0f)
    public void setBrightness(Viro360VideoView view, float brightness) {
        ViroLog.debug(TAG, "Setting brightness: " + brightness);
        view.setBrightness(brightness);
    }
    
    @ReactProp(name = "contrast", defaultFloat = 1.0f)
    public void setContrast(Viro360VideoView view, float contrast) {
        ViroLog.debug(TAG, "Setting contrast: " + contrast);
        view.setContrast(contrast);
    }
    
    @ReactProp(name = "saturation", defaultFloat = 1.0f)
    public void setSaturation(Viro360VideoView view, float saturation) {
        ViroLog.debug(TAG, "Setting saturation: " + saturation);
        view.setSaturation(saturation);
    }
    
    @ReactProp(name = "gamma", defaultFloat = 1.0f)
    public void setGamma(Viro360VideoView view, float gamma) {
        ViroLog.debug(TAG, "Setting gamma: " + gamma);
        view.setGamma(gamma);
    }
    
    @ReactProp(name = "exposure", defaultFloat = 0.0f)
    public void setExposure(Viro360VideoView view, float exposure) {
        ViroLog.debug(TAG, "Setting exposure: " + exposure);
        view.setExposure(exposure);
    }
    
    // HDR Properties
    @ReactProp(name = "hdrEnabled", defaultBoolean = false)
    public void setHdrEnabled(Viro360VideoView view, boolean hdrEnabled) {
        ViroLog.debug(TAG, "Setting HDR enabled: " + hdrEnabled);
        view.setHdrEnabled(hdrEnabled);
    }
    
    @ReactProp(name = "toneMapping")
    public void setToneMapping(Viro360VideoView view, @Nullable String toneMapping) {
        ViroLog.debug(TAG, "Setting tone mapping: " + toneMapping);
        view.setToneMapping(toneMapping);
    }
    
    @ReactProp(name = "toneMappingExposure", defaultFloat = 1.0f)
    public void setToneMappingExposure(Viro360VideoView view, float toneMappingExposure) {
        ViroLog.debug(TAG, "Setting tone mapping exposure: " + toneMappingExposure);
        view.setToneMappingExposure(toneMappingExposure);
    }
    
    @ReactProp(name = "toneMappingWhitePoint", defaultFloat = 1.0f)
    public void setToneMappingWhitePoint(Viro360VideoView view, float toneMappingWhitePoint) {
        ViroLog.debug(TAG, "Setting tone mapping white point: " + toneMappingWhitePoint);
        view.setToneMappingWhitePoint(toneMappingWhitePoint);
    }
    
    @ReactProp(name = "colorSpace")
    public void setColorSpace(Viro360VideoView view, @Nullable String colorSpace) {
        ViroLog.debug(TAG, "Setting color space: " + colorSpace);
        view.setColorSpace(colorSpace);
    }
    
    // Animation Properties
    @ReactProp(name = "autoRotate", defaultBoolean = false)
    public void setAutoRotate(Viro360VideoView view, boolean autoRotate) {
        ViroLog.debug(TAG, "Setting auto rotate: " + autoRotate);
        view.setAutoRotate(autoRotate);
    }
    
    @ReactProp(name = "autoRotateSpeed", defaultFloat = 1.0f)
    public void setAutoRotateSpeed(Viro360VideoView view, float autoRotateSpeed) {
        ViroLog.debug(TAG, "Setting auto rotate speed: " + autoRotateSpeed);
        view.setAutoRotateSpeed(autoRotateSpeed);
    }
    
    @ReactProp(name = "autoRotateAxis")
    public void setAutoRotateAxis(Viro360VideoView view, @Nullable ReadableArray autoRotateAxis) {
        ViroLog.debug(TAG, "Setting auto rotate axis: " + autoRotateAxis);
        view.setAutoRotateAxis(autoRotateAxis);
    }
    
    @ReactProp(name = "animationDuration", defaultFloat = 0.3f)
    public void setAnimationDuration(Viro360VideoView view, float animationDuration) {
        ViroLog.debug(TAG, "Setting animation duration: " + animationDuration);
        view.setAnimationDuration(animationDuration);
    }
    
    @ReactProp(name = "animationEasing")
    public void setAnimationEasing(Viro360VideoView view, @Nullable String animationEasing) {
        ViroLog.debug(TAG, "Setting animation easing: " + animationEasing);
        view.setAnimationEasing(animationEasing);
    }
    
    // Performance Optimization
    @ReactProp(name = "levelOfDetail")
    public void setLevelOfDetail(Viro360VideoView view, @Nullable String levelOfDetail) {
        ViroLog.debug(TAG, "Setting level of detail: " + levelOfDetail);
        view.setLevelOfDetail(levelOfDetail);
    }
    
    @ReactProp(name = "frameDropThreshold", defaultFloat = 0.1f)
    public void setFrameDropThreshold(Viro360VideoView view, float frameDropThreshold) {
        ViroLog.debug(TAG, "Setting frame drop threshold: " + frameDropThreshold);
        view.setFrameDropThreshold(frameDropThreshold);
    }
    
    @ReactProp(name = "memoryOptimized", defaultBoolean = true)
    public void setMemoryOptimized(Viro360VideoView view, boolean memoryOptimized) {
        ViroLog.debug(TAG, "Setting memory optimized: " + memoryOptimized);
        view.setMemoryOptimized(memoryOptimized);
    }
    
    @ReactProp(name = "hardwareAccelerated", defaultBoolean = true)
    public void setHardwareAccelerated(Viro360VideoView view, boolean hardwareAccelerated) {
        ViroLog.debug(TAG, "Setting hardware accelerated: " + hardwareAccelerated);
        view.setHardwareAccelerated(hardwareAccelerated);
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
            "onBuffer", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onBuffer",
                    "captured", "onBufferCapture"
                )
            ),
            "onSeek", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onSeek",
                    "captured", "onSeekCapture"
                )
            ),
            "onEnd", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onEnd",
                    "captured", "onEndCapture"
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
            "onResume", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onResume",
                    "captured", "onResumeCapture"
                )
            ),
            "onStop", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onStop",
                    "captured", "onStopCapture"
                )
            ),
            "onFullscreen", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onFullscreen",
                    "captured", "onFullscreenCapture"
                )
            ),
            "onRotationChange", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onRotationChange",
                    "captured", "onRotationChangeCapture"
                )
            ),
            "onQualityChange", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onQualityChange",
                    "captured", "onQualityChangeCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull Viro360VideoView view) {
        ViroLog.debug(TAG, "Dropping Viro360VideoView instance");
        super.onDropViewInstance(view);
    }
}