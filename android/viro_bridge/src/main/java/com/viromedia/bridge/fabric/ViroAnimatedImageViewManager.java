//
//  ViroAnimatedImageViewManager.java
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
 * ViroAnimatedImageViewManager - Animated Image Sequence ViewManager
 * 
 * This ViewManager handles animated image sequences for ViroReact applications.
 * It supports GIFs, sprite sheets, and frame-based animations with comprehensive
 * playback controls and performance optimization for dynamic visual content.
 * 
 * Key Features:
 * - Animated GIF support with full playback control
 * - Sprite sheet animations with configurable frame layouts
 * - Frame sequence animations from multiple images
 * - Animation timing and easing controls
 * - Loop modes and playback direction
 * - Performance optimization with frame caching
 * - Interactive playback controls (play, pause, stop, seek)
 * - Frame-by-frame stepping and scrubbing
 * - Memory management for large animations
 * - Texture streaming for performance
 */
public class ViroAnimatedImageViewManager extends SimpleViewManager<ViroAnimatedImageView> {
    
    private static final String TAG = ViroLog.getTag(ViroAnimatedImageViewManager.class);
    private static final String REACT_CLASS = "ViroAnimatedImage";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroAnimatedImageView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroAnimatedImageView instance");
        return new ViroAnimatedImageView(reactContext);
    }
    
    // Animation Source Properties
    @ReactProp(name = "source")
    public void setSource(ViroAnimatedImageView view, @Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        view.setSource(source);
    }
    
    @ReactProp(name = "uri")
    public void setUri(ViroAnimatedImageView view, @Nullable String uri) {
        ViroLog.debug(TAG, "Setting uri: " + uri);
        view.setUri(uri);
    }
    
    @ReactProp(name = "local")
    public void setLocal(ViroAnimatedImageView view, @Nullable String local) {
        ViroLog.debug(TAG, "Setting local: " + local);
        view.setLocal(local);
    }
    
    @ReactProp(name = "resource")
    public void setResource(ViroAnimatedImageView view, @Nullable String resource) {
        ViroLog.debug(TAG, "Setting resource: " + resource);
        view.setResource(resource);
    }
    
    @ReactProp(name = "frames")
    public void setFrames(ViroAnimatedImageView view, @Nullable ReadableArray frames) {
        ViroLog.debug(TAG, "Setting frames: " + frames);
        view.setFrames(frames);
    }
    
    // Animation Format and Type
    @ReactProp(name = "format")
    public void setFormat(ViroAnimatedImageView view, @Nullable String format) {
        ViroLog.debug(TAG, "Setting format: " + format);
        view.setFormat(format);
    }
    
    @ReactProp(name = "animationType")
    public void setAnimationType(ViroAnimatedImageView view, @Nullable String animationType) {
        ViroLog.debug(TAG, "Setting animation type: " + animationType);
        view.setAnimationType(animationType);
    }
    
    @ReactProp(name = "imageType")
    public void setImageType(ViroAnimatedImageView view, @Nullable String imageType) {
        ViroLog.debug(TAG, "Setting image type: " + imageType);
        view.setImageType(imageType);
    }
    
    // Sprite Sheet Properties
    @ReactProp(name = "spriteSheet")
    public void setSpriteSheet(ViroAnimatedImageView view, @Nullable ReadableMap spriteSheet) {
        ViroLog.debug(TAG, "Setting sprite sheet: " + spriteSheet);
        view.setSpriteSheet(spriteSheet);
    }
    
    @ReactProp(name = "spriteColumns", defaultInt = 1)
    public void setSpriteColumns(ViroAnimatedImageView view, int spriteColumns) {
        ViroLog.debug(TAG, "Setting sprite columns: " + spriteColumns);
        view.setSpriteColumns(spriteColumns);
    }
    
    @ReactProp(name = "spriteRows", defaultInt = 1)
    public void setSpriteRows(ViroAnimatedImageView view, int spriteRows) {
        ViroLog.debug(TAG, "Setting sprite rows: " + spriteRows);
        view.setSpriteRows(spriteRows);
    }
    
    @ReactProp(name = "frameWidth", defaultInt = 0)
    public void setFrameWidth(ViroAnimatedImageView view, int frameWidth) {
        ViroLog.debug(TAG, "Setting frame width: " + frameWidth);
        view.setFrameWidth(frameWidth);
    }
    
    @ReactProp(name = "frameHeight", defaultInt = 0)
    public void setFrameHeight(ViroAnimatedImageView view, int frameHeight) {
        ViroLog.debug(TAG, "Setting frame height: " + frameHeight);
        view.setFrameHeight(frameHeight);
    }
    
    @ReactProp(name = "frameCount", defaultInt = 1)
    public void setFrameCount(ViroAnimatedImageView view, int frameCount) {
        ViroLog.debug(TAG, "Setting frame count: " + frameCount);
        view.setFrameCount(frameCount);
    }
    
    @ReactProp(name = "frameOrder")
    public void setFrameOrder(ViroAnimatedImageView view, @Nullable ReadableArray frameOrder) {
        ViroLog.debug(TAG, "Setting frame order: " + frameOrder);
        view.setFrameOrder(frameOrder);
    }
    
    // Animation Timing and Playback
    @ReactProp(name = "duration", defaultFloat = 1.0f)
    public void setDuration(ViroAnimatedImageView view, float duration) {
        ViroLog.debug(TAG, "Setting duration: " + duration);
        view.setDuration(duration);
    }
    
    @ReactProp(name = "frameRate", defaultFloat = 30.0f)
    public void setFrameRate(ViroAnimatedImageView view, float frameRate) {
        ViroLog.debug(TAG, "Setting frame rate: " + frameRate);
        view.setFrameRate(frameRate);
    }
    
    @ReactProp(name = "frameDuration", defaultFloat = 0.033f)
    public void setFrameDuration(ViroAnimatedImageView view, float frameDuration) {
        ViroLog.debug(TAG, "Setting frame duration: " + frameDuration);
        view.setFrameDuration(frameDuration);
    }
    
    @ReactProp(name = "speed", defaultFloat = 1.0f)
    public void setSpeed(ViroAnimatedImageView view, float speed) {
        ViroLog.debug(TAG, "Setting speed: " + speed);
        view.setSpeed(speed);
    }
    
    @ReactProp(name = "delay", defaultFloat = 0.0f)
    public void setDelay(ViroAnimatedImageView view, float delay) {
        ViroLog.debug(TAG, "Setting delay: " + delay);
        view.setDelay(delay);
    }
    
    @ReactProp(name = "startFrame", defaultInt = 0)
    public void setStartFrame(ViroAnimatedImageView view, int startFrame) {
        ViroLog.debug(TAG, "Setting start frame: " + startFrame);
        view.setStartFrame(startFrame);
    }
    
    @ReactProp(name = "endFrame", defaultInt = -1)
    public void setEndFrame(ViroAnimatedImageView view, int endFrame) {
        ViroLog.debug(TAG, "Setting end frame: " + endFrame);
        view.setEndFrame(endFrame);
    }
    
    // Animation Control
    @ReactProp(name = "paused", defaultBoolean = false)
    public void setPaused(ViroAnimatedImageView view, boolean paused) {
        ViroLog.debug(TAG, "Setting paused: " + paused);
        view.setPaused(paused);
    }
    
    @ReactProp(name = "loop", defaultBoolean = true)
    public void setLoop(ViroAnimatedImageView view, boolean loop) {
        ViroLog.debug(TAG, "Setting loop: " + loop);
        view.setLoop(loop);
    }
    
    @ReactProp(name = "loopCount", defaultInt = -1)
    public void setLoopCount(ViroAnimatedImageView view, int loopCount) {
        ViroLog.debug(TAG, "Setting loop count: " + loopCount);
        view.setLoopCount(loopCount);
    }
    
    @ReactProp(name = "reverse", defaultBoolean = false)
    public void setReverse(ViroAnimatedImageView view, boolean reverse) {
        ViroLog.debug(TAG, "Setting reverse: " + reverse);
        view.setReverse(reverse);
    }
    
    @ReactProp(name = "autoPlay", defaultBoolean = true)
    public void setAutoPlay(ViroAnimatedImageView view, boolean autoPlay) {
        ViroLog.debug(TAG, "Setting auto play: " + autoPlay);
        view.setAutoPlay(autoPlay);
    }
    
    @ReactProp(name = "playbackDirection")
    public void setPlaybackDirection(ViroAnimatedImageView view, @Nullable String playbackDirection) {
        ViroLog.debug(TAG, "Setting playback direction: " + playbackDirection);
        view.setPlaybackDirection(playbackDirection);
    }
    
    // Animation Easing and Interpolation
    @ReactProp(name = "easing")
    public void setEasing(ViroAnimatedImageView view, @Nullable String easing) {
        ViroLog.debug(TAG, "Setting easing: " + easing);
        view.setEasing(easing);
    }
    
    @ReactProp(name = "interpolation")
    public void setInterpolation(ViroAnimatedImageView view, @Nullable String interpolation) {
        ViroLog.debug(TAG, "Setting interpolation: " + interpolation);
        view.setInterpolation(interpolation);
    }
    
    @ReactProp(name = "smoothing", defaultBoolean = true)
    public void setSmoothing(ViroAnimatedImageView view, boolean smoothing) {
        ViroLog.debug(TAG, "Setting smoothing: " + smoothing);
        view.setSmoothing(smoothing);
    }
    
    @ReactProp(name = "blendMode")
    public void setBlendMode(ViroAnimatedImageView view, @Nullable String blendMode) {
        ViroLog.debug(TAG, "Setting blend mode: " + blendMode);
        view.setBlendMode(blendMode);
    }
    
    // Display Properties
    @ReactProp(name = "width", defaultFloat = 1.0f)
    public void setWidth(ViroAnimatedImageView view, float width) {
        ViroLog.debug(TAG, "Setting width: " + width);
        view.setWidth(width);
    }
    
    @ReactProp(name = "height", defaultFloat = 1.0f)
    public void setHeight(ViroAnimatedImageView view, float height) {
        ViroLog.debug(TAG, "Setting height: " + height);
        view.setHeight(height);
    }
    
    @ReactProp(name = "resizeMode")
    public void setResizeMode(ViroAnimatedImageView view, @Nullable String resizeMode) {
        ViroLog.debug(TAG, "Setting resize mode: " + resizeMode);
        view.setResizeMode(resizeMode);
    }
    
    @ReactProp(name = "aspectRatio", defaultFloat = 1.0f)
    public void setAspectRatio(ViroAnimatedImageView view, float aspectRatio) {
        ViroLog.debug(TAG, "Setting aspect ratio: " + aspectRatio);
        view.setAspectRatio(aspectRatio);
    }
    
    @ReactProp(name = "maintainAspectRatio", defaultBoolean = true)
    public void setMaintainAspectRatio(ViroAnimatedImageView view, boolean maintainAspectRatio) {
        ViroLog.debug(TAG, "Setting maintain aspect ratio: " + maintainAspectRatio);
        view.setMaintainAspectRatio(maintainAspectRatio);
    }
    
    // Material Properties
    @ReactProp(name = "opacity", defaultFloat = 1.0f)
    public void setOpacity(ViroAnimatedImageView view, float opacity) {
        ViroLog.debug(TAG, "Setting opacity: " + opacity);
        view.setOpacity(opacity);
    }
    
    @ReactProp(name = "tintColor")
    public void setTintColor(ViroAnimatedImageView view, @Nullable ReadableArray tintColor) {
        ViroLog.debug(TAG, "Setting tint color: " + tintColor);
        view.setTintColor(tintColor);
    }
    
    @ReactProp(name = "brightness", defaultFloat = 1.0f)
    public void setBrightness(ViroAnimatedImageView view, float brightness) {
        ViroLog.debug(TAG, "Setting brightness: " + brightness);
        view.setBrightness(brightness);
    }
    
    @ReactProp(name = "contrast", defaultFloat = 1.0f)
    public void setContrast(ViroAnimatedImageView view, float contrast) {
        ViroLog.debug(TAG, "Setting contrast: " + contrast);
        view.setContrast(contrast);
    }
    
    @ReactProp(name = "saturation", defaultFloat = 1.0f)
    public void setSaturation(ViroAnimatedImageView view, float saturation) {
        ViroLog.debug(TAG, "Setting saturation: " + saturation);
        view.setSaturation(saturation);
    }
    
    @ReactProp(name = "filterType")
    public void setFilterType(ViroAnimatedImageView view, @Nullable String filterType) {
        ViroLog.debug(TAG, "Setting filter type: " + filterType);
        view.setFilterType(filterType);
    }
    
    // Texture Properties
    @ReactProp(name = "textureWrapS")
    public void setTextureWrapS(ViroAnimatedImageView view, @Nullable String textureWrapS) {
        ViroLog.debug(TAG, "Setting texture wrap S: " + textureWrapS);
        view.setTextureWrapS(textureWrapS);
    }
    
    @ReactProp(name = "textureWrapT")
    public void setTextureWrapT(ViroAnimatedImageView view, @Nullable String textureWrapT) {
        ViroLog.debug(TAG, "Setting texture wrap T: " + textureWrapT);
        view.setTextureWrapT(textureWrapT);
    }
    
    @ReactProp(name = "textureMinification")
    public void setTextureMinification(ViroAnimatedImageView view, @Nullable String textureMinification) {
        ViroLog.debug(TAG, "Setting texture minification: " + textureMinification);
        view.setTextureMinification(textureMinification);
    }
    
    @ReactProp(name = "textureMagnification")
    public void setTextureMagnification(ViroAnimatedImageView view, @Nullable String textureMagnification) {
        ViroLog.debug(TAG, "Setting texture magnification: " + textureMagnification);
        view.setTextureMagnification(textureMagnification);
    }
    
    @ReactProp(name = "mipmap", defaultBoolean = true)
    public void setMipmap(ViroAnimatedImageView view, boolean mipmap) {
        ViroLog.debug(TAG, "Setting mipmap: " + mipmap);
        view.setMipmap(mipmap);
    }
    
    @ReactProp(name = "anisotropy", defaultFloat = 1.0f)
    public void setAnisotropy(ViroAnimatedImageView view, float anisotropy) {
        ViroLog.debug(TAG, "Setting anisotropy: " + anisotropy);
        view.setAnisotropy(anisotropy);
    }
    
    // Performance and Caching
    @ReactProp(name = "preload", defaultBoolean = false)
    public void setPreload(ViroAnimatedImageView view, boolean preload) {
        ViroLog.debug(TAG, "Setting preload: " + preload);
        view.setPreload(preload);
    }
    
    @ReactProp(name = "cacheEnabled", defaultBoolean = true)
    public void setCacheEnabled(ViroAnimatedImageView view, boolean cacheEnabled) {
        ViroLog.debug(TAG, "Setting cache enabled: " + cacheEnabled);
        view.setCacheEnabled(cacheEnabled);
    }
    
    @ReactProp(name = "cacheSize", defaultInt = 50)
    public void setCacheSize(ViroAnimatedImageView view, int cacheSize) {
        ViroLog.debug(TAG, "Setting cache size: " + cacheSize);
        view.setCacheSize(cacheSize);
    }
    
    @ReactProp(name = "maxCacheFrames", defaultInt = 100)
    public void setMaxCacheFrames(ViroAnimatedImageView view, int maxCacheFrames) {
        ViroLog.debug(TAG, "Setting max cache frames: " + maxCacheFrames);
        view.setMaxCacheFrames(maxCacheFrames);
    }
    
    @ReactProp(name = "streamingEnabled", defaultBoolean = false)
    public void setStreamingEnabled(ViroAnimatedImageView view, boolean streamingEnabled) {
        ViroLog.debug(TAG, "Setting streaming enabled: " + streamingEnabled);
        view.setStreamingEnabled(streamingEnabled);
    }
    
    @ReactProp(name = "memoryWarningEnabled", defaultBoolean = true)
    public void setMemoryWarningEnabled(ViroAnimatedImageView view, boolean memoryWarningEnabled) {
        ViroLog.debug(TAG, "Setting memory warning enabled: " + memoryWarningEnabled);
        view.setMemoryWarningEnabled(memoryWarningEnabled);
    }
    
    // Loading Properties
    @ReactProp(name = "loadingTimeout", defaultFloat = 30.0f)
    public void setLoadingTimeout(ViroAnimatedImageView view, float loadingTimeout) {
        ViroLog.debug(TAG, "Setting loading timeout: " + loadingTimeout);
        view.setLoadingTimeout(loadingTimeout);
    }
    
    @ReactProp(name = "retryCount", defaultInt = 3)
    public void setRetryCount(ViroAnimatedImageView view, int retryCount) {
        ViroLog.debug(TAG, "Setting retry count: " + retryCount);
        view.setRetryCount(retryCount);
    }
    
    @ReactProp(name = "lazyLoading", defaultBoolean = false)
    public void setLazyLoading(ViroAnimatedImageView view, boolean lazyLoading) {
        ViroLog.debug(TAG, "Setting lazy loading: " + lazyLoading);
        view.setLazyLoading(lazyLoading);
    }
    
    @ReactProp(name = "progressiveLoading", defaultBoolean = false)
    public void setProgressiveLoading(ViroAnimatedImageView view, boolean progressiveLoading) {
        ViroLog.debug(TAG, "Setting progressive loading: " + progressiveLoading);
        view.setProgressiveLoading(progressiveLoading);
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
            "onStart", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onStart",
                    "captured", "onStartCapture"
                )
            ),
            "onStop", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onStop",
                    "captured", "onStopCapture"
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
            "onLoop", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onLoop",
                    "captured", "onLoopCapture"
                )
            ),
            "onComplete", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onComplete",
                    "captured", "onCompleteCapture"
                )
            ),
            "onFrameChange", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onFrameChange",
                    "captured", "onFrameChangeCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroAnimatedImageView view) {
        ViroLog.debug(TAG, "Dropping ViroAnimatedImageView instance");
        super.onDropViewInstance(view);
    }
}