//
//  ViroSoundViewManager.java
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
 * ViroSoundViewManager - Audio Playback ViewManager
 * 
 * This ViewManager handles audio playback for ViroReact applications.
 * It provides comprehensive audio functionality including file loading, playback controls,
 * 3D spatial audio, effects, and real-time analysis capabilities.
 * 
 * Key Features:
 * - Audio file loading and streaming
 * - Playback controls (play, pause, stop, seek)
 * - Volume and pitch control
 * - Looping and crossfading
 * - 3D spatial audio positioning
 * - Audio effects and filters
 * - Multiple audio format support
 * - Real-time audio analysis
 * - Audio visualization integration
 * - Performance optimization
 */
public class ViroSoundViewManager extends SimpleViewManager<ViroSoundView> {
    
    private static final String TAG = ViroLog.getTag(ViroSoundViewManager.class);
    private static final String REACT_CLASS = "ViroSound";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroSoundView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroSoundView instance");
        return new ViroSoundView(reactContext);
    }
    
    // Audio Source
    @ReactProp(name = "source")
    public void setSource(ViroSoundView view, @Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        view.setSource(source);
    }
    
    @ReactProp(name = "uri")
    public void setUri(ViroSoundView view, @Nullable String uri) {
        ViroLog.debug(TAG, "Setting uri: " + uri);
        view.setUri(uri);
    }
    
    @ReactProp(name = "local")
    public void setLocal(ViroSoundView view, @Nullable String local) {
        ViroLog.debug(TAG, "Setting local: " + local);
        view.setLocal(local);
    }
    
    @ReactProp(name = "resource")
    public void setResource(ViroSoundView view, @Nullable String resource) {
        ViroLog.debug(TAG, "Setting resource: " + resource);
        view.setResource(resource);
    }
    
    // Playback Control
    @ReactProp(name = "paused", defaultBoolean = false)
    public void setPaused(ViroSoundView view, boolean paused) {
        ViroLog.debug(TAG, "Setting paused: " + paused);
        view.setPaused(paused);
    }
    
    @ReactProp(name = "loop", defaultBoolean = false)
    public void setLoop(ViroSoundView view, boolean loop) {
        ViroLog.debug(TAG, "Setting loop: " + loop);
        view.setLoop(loop);
    }
    
    @ReactProp(name = "muted", defaultBoolean = false)
    public void setMuted(ViroSoundView view, boolean muted) {
        ViroLog.debug(TAG, "Setting muted: " + muted);
        view.setMuted(muted);
    }
    
    @ReactProp(name = "volume", defaultFloat = 1.0f)
    public void setVolume(ViroSoundView view, float volume) {
        ViroLog.debug(TAG, "Setting volume: " + volume);
        view.setVolume(volume);
    }
    
    @ReactProp(name = "rate", defaultFloat = 1.0f)
    public void setRate(ViroSoundView view, float rate) {
        ViroLog.debug(TAG, "Setting rate: " + rate);
        view.setRate(rate);
    }
    
    @ReactProp(name = "pitch", defaultFloat = 1.0f)
    public void setPitch(ViroSoundView view, float pitch) {
        ViroLog.debug(TAG, "Setting pitch: " + pitch);
        view.setPitch(pitch);
    }
    
    @ReactProp(name = "seekTime", defaultFloat = 0.0f)
    public void setSeekTime(ViroSoundView view, float seekTime) {
        ViroLog.debug(TAG, "Setting seek time: " + seekTime);
        view.setSeekTime(seekTime);
    }
    
    // Audio Properties
    @ReactProp(name = "audioFormat")
    public void setAudioFormat(ViroSoundView view, @Nullable String audioFormat) {
        ViroLog.debug(TAG, "Setting audio format: " + audioFormat);
        view.setAudioFormat(audioFormat);
    }
    
    @ReactProp(name = "channels", defaultInt = 2)
    public void setChannels(ViroSoundView view, int channels) {
        ViroLog.debug(TAG, "Setting channels: " + channels);
        view.setChannels(channels);
    }
    
    @ReactProp(name = "sampleRate", defaultInt = 44100)
    public void setSampleRate(ViroSoundView view, int sampleRate) {
        ViroLog.debug(TAG, "Setting sample rate: " + sampleRate);
        view.setSampleRate(sampleRate);
    }
    
    @ReactProp(name = "bitRate", defaultInt = 128000)
    public void setBitRate(ViroSoundView view, int bitRate) {
        ViroLog.debug(TAG, "Setting bit rate: " + bitRate);
        view.setBitRate(bitRate);
    }
    
    @ReactProp(name = "quality")
    public void setQuality(ViroSoundView view, @Nullable String quality) {
        ViroLog.debug(TAG, "Setting quality: " + quality);
        view.setQuality(quality);
    }
    
    // 3D Spatial Audio
    @ReactProp(name = "position")
    public void setPosition(ViroSoundView view, @Nullable ReadableArray position) {
        ViroLog.debug(TAG, "Setting position: " + position);
        view.setPosition(position);
    }
    
    @ReactProp(name = "rolloffModel")
    public void setRolloffModel(ViroSoundView view, @Nullable String rolloffModel) {
        ViroLog.debug(TAG, "Setting rolloff model: " + rolloffModel);
        view.setRolloffModel(rolloffModel);
    }
    
    @ReactProp(name = "distanceModel")
    public void setDistanceModel(ViroSoundView view, @Nullable String distanceModel) {
        ViroLog.debug(TAG, "Setting distance model: " + distanceModel);
        view.setDistanceModel(distanceModel);
    }
    
    @ReactProp(name = "maxDistance", defaultFloat = 1000.0f)
    public void setMaxDistance(ViroSoundView view, float maxDistance) {
        ViroLog.debug(TAG, "Setting max distance: " + maxDistance);
        view.setMaxDistance(maxDistance);
    }
    
    @ReactProp(name = "referenceDistance", defaultFloat = 1.0f)
    public void setReferenceDistance(ViroSoundView view, float referenceDistance) {
        ViroLog.debug(TAG, "Setting reference distance: " + referenceDistance);
        view.setReferenceDistance(referenceDistance);
    }
    
    @ReactProp(name = "rolloffFactor", defaultFloat = 1.0f)
    public void setRolloffFactor(ViroSoundView view, float rolloffFactor) {
        ViroLog.debug(TAG, "Setting rolloff factor: " + rolloffFactor);
        view.setRolloffFactor(rolloffFactor);
    }
    
    @ReactProp(name = "coneInnerAngle", defaultFloat = 360.0f)
    public void setConeInnerAngle(ViroSoundView view, float coneInnerAngle) {
        ViroLog.debug(TAG, "Setting cone inner angle: " + coneInnerAngle);
        view.setConeInnerAngle(coneInnerAngle);
    }
    
    @ReactProp(name = "coneOuterAngle", defaultFloat = 360.0f)
    public void setConeOuterAngle(ViroSoundView view, float coneOuterAngle) {
        ViroLog.debug(TAG, "Setting cone outer angle: " + coneOuterAngle);
        view.setConeOuterAngle(coneOuterAngle);
    }
    
    @ReactProp(name = "coneOuterGain", defaultFloat = 0.0f)
    public void setConeOuterGain(ViroSoundView view, float coneOuterGain) {
        ViroLog.debug(TAG, "Setting cone outer gain: " + coneOuterGain);
        view.setConeOuterGain(coneOuterGain);
    }
    
    // Audio Effects
    @ReactProp(name = "reverb")
    public void setReverb(ViroSoundView view, @Nullable ReadableMap reverb) {
        ViroLog.debug(TAG, "Setting reverb: " + reverb);
        view.setReverb(reverb);
    }
    
    @ReactProp(name = "delay")
    public void setDelay(ViroSoundView view, @Nullable ReadableMap delay) {
        ViroLog.debug(TAG, "Setting delay: " + delay);
        view.setDelay(delay);
    }
    
    @ReactProp(name = "echo")
    public void setEcho(ViroSoundView view, @Nullable ReadableMap echo) {
        ViroLog.debug(TAG, "Setting echo: " + echo);
        view.setEcho(echo);
    }
    
    @ReactProp(name = "distortion")
    public void setDistortion(ViroSoundView view, @Nullable ReadableMap distortion) {
        ViroLog.debug(TAG, "Setting distortion: " + distortion);
        view.setDistortion(distortion);
    }
    
    @ReactProp(name = "equalizer")
    public void setEqualizer(ViroSoundView view, @Nullable ReadableMap equalizer) {
        ViroLog.debug(TAG, "Setting equalizer: " + equalizer);
        view.setEqualizer(equalizer);
    }
    
    @ReactProp(name = "filters")
    public void setFilters(ViroSoundView view, @Nullable ReadableArray filters) {
        ViroLog.debug(TAG, "Setting filters: " + filters);
        view.setFilters(filters);
    }
    
    // Crossfading
    @ReactProp(name = "crossfade")
    public void setCrossfade(ViroSoundView view, @Nullable ReadableMap crossfade) {
        ViroLog.debug(TAG, "Setting crossfade: " + crossfade);
        view.setCrossfade(crossfade);
    }
    
    @ReactProp(name = "fadeIn", defaultFloat = 0.0f)
    public void setFadeIn(ViroSoundView view, float fadeIn) {
        ViroLog.debug(TAG, "Setting fade in: " + fadeIn);
        view.setFadeIn(fadeIn);
    }
    
    @ReactProp(name = "fadeOut", defaultFloat = 0.0f)
    public void setFadeOut(ViroSoundView view, float fadeOut) {
        ViroLog.debug(TAG, "Setting fade out: " + fadeOut);
        view.setFadeOut(fadeOut);
    }
    
    @ReactProp(name = "fadeInCurve")
    public void setFadeInCurve(ViroSoundView view, @Nullable String fadeInCurve) {
        ViroLog.debug(TAG, "Setting fade in curve: " + fadeInCurve);
        view.setFadeInCurve(fadeInCurve);
    }
    
    @ReactProp(name = "fadeOutCurve")
    public void setFadeOutCurve(ViroSoundView view, @Nullable String fadeOutCurve) {
        ViroLog.debug(TAG, "Setting fade out curve: " + fadeOutCurve);
        view.setFadeOutCurve(fadeOutCurve);
    }
    
    // Audio Analysis
    @ReactProp(name = "analysisEnabled", defaultBoolean = false)
    public void setAnalysisEnabled(ViroSoundView view, boolean analysisEnabled) {
        ViroLog.debug(TAG, "Setting analysis enabled: " + analysisEnabled);
        view.setAnalysisEnabled(analysisEnabled);
    }
    
    @ReactProp(name = "analysisInterval", defaultFloat = 0.1f)
    public void setAnalysisInterval(ViroSoundView view, float analysisInterval) {
        ViroLog.debug(TAG, "Setting analysis interval: " + analysisInterval);
        view.setAnalysisInterval(analysisInterval);
    }
    
    @ReactProp(name = "analysisFrequencyBands", defaultInt = 32)
    public void setAnalysisFrequencyBands(ViroSoundView view, int analysisFrequencyBands) {
        ViroLog.debug(TAG, "Setting analysis frequency bands: " + analysisFrequencyBands);
        view.setAnalysisFrequencyBands(analysisFrequencyBands);
    }
    
    @ReactProp(name = "analysisTimeConstant", defaultFloat = 0.0f)
    public void setAnalysisTimeConstant(ViroSoundView view, float analysisTimeConstant) {
        ViroLog.debug(TAG, "Setting analysis time constant: " + analysisTimeConstant);
        view.setAnalysisTimeConstant(analysisTimeConstant);
    }
    
    @ReactProp(name = "analysisSmoothing", defaultFloat = 0.8f)
    public void setAnalysisSmoothing(ViroSoundView view, float analysisSmoothing) {
        ViroLog.debug(TAG, "Setting analysis smoothing: " + analysisSmoothing);
        view.setAnalysisSmoothing(analysisSmoothing);
    }
    
    // Performance
    @ReactProp(name = "preload", defaultBoolean = false)
    public void setPreload(ViroSoundView view, boolean preload) {
        ViroLog.debug(TAG, "Setting preload: " + preload);
        view.setPreload(preload);
    }
    
    @ReactProp(name = "bufferSize", defaultInt = 4096)
    public void setBufferSize(ViroSoundView view, int bufferSize) {
        ViroLog.debug(TAG, "Setting buffer size: " + bufferSize);
        view.setBufferSize(bufferSize);
    }
    
    @ReactProp(name = "cacheEnabled", defaultBoolean = true)
    public void setCacheEnabled(ViroSoundView view, boolean cacheEnabled) {
        ViroLog.debug(TAG, "Setting cache enabled: " + cacheEnabled);
        view.setCacheEnabled(cacheEnabled);
    }
    
    @ReactProp(name = "streamingEnabled", defaultBoolean = false)
    public void setStreamingEnabled(ViroSoundView view, boolean streamingEnabled) {
        ViroLog.debug(TAG, "Setting streaming enabled: " + streamingEnabled);
        view.setStreamingEnabled(streamingEnabled);
    }
    
    @ReactProp(name = "backgroundPlayback", defaultBoolean = false)
    public void setBackgroundPlayback(ViroSoundView view, boolean backgroundPlayback) {
        ViroLog.debug(TAG, "Setting background playback: " + backgroundPlayback);
        view.setBackgroundPlayback(backgroundPlayback);
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
            "onStop", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onStop",
                    "captured", "onStopCapture"
                )
            ),
            "onFinish", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onFinish",
                    "captured", "onFinishCapture"
                )
            ),
            "onSeek", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onSeek",
                    "captured", "onSeekCapture"
                )
            ),
            "onProgress", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onProgress",
                    "captured", "onProgressCapture"
                )
            ),
            "onVolumeChange", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onVolumeChange",
                    "captured", "onVolumeChangeCapture"
                )
            ),
            "onRateChange", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onRateChange",
                    "captured", "onRateChangeCapture"
                )
            ),
            "onAnalysis", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onAnalysis",
                    "captured", "onAnalysisCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroSoundView view) {
        ViroLog.debug(TAG, "Dropping ViroSoundView instance");
        super.onDropViewInstance(view);
    }
}