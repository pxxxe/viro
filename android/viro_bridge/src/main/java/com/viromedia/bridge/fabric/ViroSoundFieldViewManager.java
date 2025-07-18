//
//  ViroSoundFieldViewManager.java
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
 * ViroSoundFieldViewManager - Spatial Sound Field ViewManager
 * 
 * This ViewManager handles spatial sound field audio for ViroReact applications.
 * It provides comprehensive spatial audio capabilities including 3D sound field generation,
 * ambisonic audio support, environmental audio effects, and immersive audio experiences.
 * 
 * Key Features:
 * - 3D spatial sound field generation
 * - Ambisonic audio support (1st, 2nd, 3rd order)
 * - Environmental audio effects and reverb
 * - Real-time audio processing
 * - Multiple audio source management
 * - Directional audio patterns
 * - Audio field visualization
 * - Performance optimized processing
 * - Interactive audio manipulation
 * - Cross-platform compatibility
 */
public class ViroSoundFieldViewManager extends SimpleViewManager<ViroSoundFieldView> {
    
    private static final String TAG = ViroLog.getTag(ViroSoundFieldViewManager.class);
    private static final String REACT_CLASS = "ViroSoundField";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroSoundFieldView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroSoundFieldView instance");
        return new ViroSoundFieldView(reactContext);
    }
    
    // Audio Source Properties
    @ReactProp(name = "source")
    public void setSource(ViroSoundFieldView view, @Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        view.setSource(source);
    }
    
    @ReactProp(name = "uri")
    public void setUri(ViroSoundFieldView view, @Nullable String uri) {
        ViroLog.debug(TAG, "Setting uri: " + uri);
        view.setUri(uri);
    }
    
    @ReactProp(name = "local")
    public void setLocal(ViroSoundFieldView view, @Nullable String local) {
        ViroLog.debug(TAG, "Setting local: " + local);
        view.setLocal(local);
    }
    
    @ReactProp(name = "resource")
    public void setResource(ViroSoundFieldView view, @Nullable String resource) {
        ViroLog.debug(TAG, "Setting resource: " + resource);
        view.setResource(resource);
    }
    
    @ReactProp(name = "audioFormat")
    public void setAudioFormat(ViroSoundFieldView view, @Nullable String audioFormat) {
        ViroLog.debug(TAG, "Setting audio format: " + audioFormat);
        view.setAudioFormat(audioFormat);
    }
    
    // Playback Control Properties
    @ReactProp(name = "paused", defaultBoolean = false)
    public void setPaused(ViroSoundFieldView view, boolean paused) {
        ViroLog.debug(TAG, "Setting paused: " + paused);
        view.setPaused(paused);
    }
    
    @ReactProp(name = "loop", defaultBoolean = false)
    public void setLoop(ViroSoundFieldView view, boolean loop) {
        ViroLog.debug(TAG, "Setting loop: " + loop);
        view.setLoop(loop);
    }
    
    @ReactProp(name = "muted", defaultBoolean = false)
    public void setMuted(ViroSoundFieldView view, boolean muted) {
        ViroLog.debug(TAG, "Setting muted: " + muted);
        view.setMuted(muted);
    }
    
    @ReactProp(name = "volume", defaultFloat = 1.0f)
    public void setVolume(ViroSoundFieldView view, float volume) {
        ViroLog.debug(TAG, "Setting volume: " + volume);
        view.setVolume(volume);
    }
    
    @ReactProp(name = "rate", defaultFloat = 1.0f)
    public void setRate(ViroSoundFieldView view, float rate) {
        ViroLog.debug(TAG, "Setting rate: " + rate);
        view.setRate(rate);
    }
    
    @ReactProp(name = "seekTime", defaultFloat = 0.0f)
    public void setSeekTime(ViroSoundFieldView view, float seekTime) {
        ViroLog.debug(TAG, "Setting seek time: " + seekTime);
        view.setSeekTime(seekTime);
    }
    
    // Spatial Audio Properties
    @ReactProp(name = "position")
    public void setPosition(ViroSoundFieldView view, @Nullable ReadableArray position) {
        ViroLog.debug(TAG, "Setting position: " + position);
        view.setPosition(position);
    }
    
    @ReactProp(name = "rotation")
    public void setRotation(ViroSoundFieldView view, @Nullable ReadableArray rotation) {
        ViroLog.debug(TAG, "Setting rotation: " + rotation);
        view.setRotation(rotation);
    }
    
    @ReactProp(name = "scale")
    public void setScale(ViroSoundFieldView view, @Nullable ReadableArray scale) {
        ViroLog.debug(TAG, "Setting scale: " + scale);
        view.setScale(scale);
    }
    
    @ReactProp(name = "spatialAudioEnabled", defaultBoolean = true)
    public void setSpatialAudioEnabled(ViroSoundFieldView view, boolean spatialAudioEnabled) {
        ViroLog.debug(TAG, "Setting spatial audio enabled: " + spatialAudioEnabled);
        view.setSpatialAudioEnabled(spatialAudioEnabled);
    }
    
    @ReactProp(name = "spatialAudioQuality")
    public void setSpatialAudioQuality(ViroSoundFieldView view, @Nullable String spatialAudioQuality) {
        ViroLog.debug(TAG, "Setting spatial audio quality: " + spatialAudioQuality);
        view.setSpatialAudioQuality(spatialAudioQuality);
    }
    
    // Sound Field Properties
    @ReactProp(name = "fieldType")
    public void setFieldType(ViroSoundFieldView view, @Nullable String fieldType) {
        ViroLog.debug(TAG, "Setting field type: " + fieldType);
        view.setFieldType(fieldType);
    }
    
    @ReactProp(name = "fieldSize")
    public void setFieldSize(ViroSoundFieldView view, @Nullable ReadableArray fieldSize) {
        ViroLog.debug(TAG, "Setting field size: " + fieldSize);
        view.setFieldSize(fieldSize);
    }
    
    @ReactProp(name = "fieldIntensity", defaultFloat = 1.0f)
    public void setFieldIntensity(ViroSoundFieldView view, float fieldIntensity) {
        ViroLog.debug(TAG, "Setting field intensity: " + fieldIntensity);
        view.setFieldIntensity(fieldIntensity);
    }
    
    @ReactProp(name = "fieldFalloff", defaultFloat = 1.0f)
    public void setFieldFalloff(ViroSoundFieldView view, float fieldFalloff) {
        ViroLog.debug(TAG, "Setting field falloff: " + fieldFalloff);
        view.setFieldFalloff(fieldFalloff);
    }
    
    @ReactProp(name = "fieldDirection")
    public void setFieldDirection(ViroSoundFieldView view, @Nullable ReadableArray fieldDirection) {
        ViroLog.debug(TAG, "Setting field direction: " + fieldDirection);
        view.setFieldDirection(fieldDirection);
    }
    
    @ReactProp(name = "fieldPattern")
    public void setFieldPattern(ViroSoundFieldView view, @Nullable String fieldPattern) {
        ViroLog.debug(TAG, "Setting field pattern: " + fieldPattern);
        view.setFieldPattern(fieldPattern);
    }
    
    // Ambisonic Audio Properties
    @ReactProp(name = "ambisonicEnabled", defaultBoolean = false)
    public void setAmbisonicEnabled(ViroSoundFieldView view, boolean ambisonicEnabled) {
        ViroLog.debug(TAG, "Setting ambisonic enabled: " + ambisonicEnabled);
        view.setAmbisonicEnabled(ambisonicEnabled);
    }
    
    @ReactProp(name = "ambisonicOrder", defaultInt = 1)
    public void setAmbisonicOrder(ViroSoundFieldView view, int ambisonicOrder) {
        ViroLog.debug(TAG, "Setting ambisonic order: " + ambisonicOrder);
        view.setAmbisonicOrder(ambisonicOrder);
    }
    
    @ReactProp(name = "ambisonicFormat")
    public void setAmbisonicFormat(ViroSoundFieldView view, @Nullable String ambisonicFormat) {
        ViroLog.debug(TAG, "Setting ambisonic format: " + ambisonicFormat);
        view.setAmbisonicFormat(ambisonicFormat);
    }
    
    @ReactProp(name = "ambisonicChannelOrder")
    public void setAmbisonicChannelOrder(ViroSoundFieldView view, @Nullable String ambisonicChannelOrder) {
        ViroLog.debug(TAG, "Setting ambisonic channel order: " + ambisonicChannelOrder);
        view.setAmbisonicChannelOrder(ambisonicChannelOrder);
    }
    
    @ReactProp(name = "ambisonicNormalization")
    public void setAmbisonicNormalization(ViroSoundFieldView view, @Nullable String ambisonicNormalization) {
        ViroLog.debug(TAG, "Setting ambisonic normalization: " + ambisonicNormalization);
        view.setAmbisonicNormalization(ambisonicNormalization);
    }
    
    // Environmental Audio Properties
    @ReactProp(name = "environmentalAudio")
    public void setEnvironmentalAudio(ViroSoundFieldView view, @Nullable ReadableMap environmentalAudio) {
        ViroLog.debug(TAG, "Setting environmental audio: " + environmentalAudio);
        view.setEnvironmentalAudio(environmentalAudio);
    }
    
    @ReactProp(name = "reverb")
    public void setReverb(ViroSoundFieldView view, @Nullable ReadableMap reverb) {
        ViroLog.debug(TAG, "Setting reverb: " + reverb);
        view.setReverb(reverb);
    }
    
    @ReactProp(name = "roomSize")
    public void setRoomSize(ViroSoundFieldView view, @Nullable ReadableArray roomSize) {
        ViroLog.debug(TAG, "Setting room size: " + roomSize);
        view.setRoomSize(roomSize);
    }
    
    @ReactProp(name = "roomMaterials")
    public void setRoomMaterials(ViroSoundFieldView view, @Nullable ReadableMap roomMaterials) {
        ViroLog.debug(TAG, "Setting room materials: " + roomMaterials);
        view.setRoomMaterials(roomMaterials);
    }
    
    @ReactProp(name = "reflections")
    public void setReflections(ViroSoundFieldView view, @Nullable ReadableMap reflections) {
        ViroLog.debug(TAG, "Setting reflections: " + reflections);
        view.setReflections(reflections);
    }
    
    // Audio Effects Properties
    @ReactProp(name = "effects")
    public void setEffects(ViroSoundFieldView view, @Nullable ReadableArray effects) {
        ViroLog.debug(TAG, "Setting effects: " + effects);
        view.setEffects(effects);
    }
    
    @ReactProp(name = "filters")
    public void setFilters(ViroSoundFieldView view, @Nullable ReadableArray filters) {
        ViroLog.debug(TAG, "Setting filters: " + filters);
        view.setFilters(filters);
    }
    
    @ReactProp(name = "equalizer")
    public void setEqualizer(ViroSoundFieldView view, @Nullable ReadableMap equalizer) {
        ViroLog.debug(TAG, "Setting equalizer: " + equalizer);
        view.setEqualizer(equalizer);
    }
    
    @ReactProp(name = "compressor")
    public void setCompressor(ViroSoundFieldView view, @Nullable ReadableMap compressor) {
        ViroLog.debug(TAG, "Setting compressor: " + compressor);
        view.setCompressor(compressor);
    }
    
    @ReactProp(name = "limiter")
    public void setLimiter(ViroSoundFieldView view, @Nullable ReadableMap limiter) {
        ViroLog.debug(TAG, "Setting limiter: " + limiter);
        view.setLimiter(limiter);
    }
    
    // Distance and Attenuation Properties
    @ReactProp(name = "distanceModel")
    public void setDistanceModel(ViroSoundFieldView view, @Nullable String distanceModel) {
        ViroLog.debug(TAG, "Setting distance model: " + distanceModel);
        view.setDistanceModel(distanceModel);
    }
    
    @ReactProp(name = "maxDistance", defaultFloat = 1000.0f)
    public void setMaxDistance(ViroSoundFieldView view, float maxDistance) {
        ViroLog.debug(TAG, "Setting max distance: " + maxDistance);
        view.setMaxDistance(maxDistance);
    }
    
    @ReactProp(name = "referenceDistance", defaultFloat = 1.0f)
    public void setReferenceDistance(ViroSoundFieldView view, float referenceDistance) {
        ViroLog.debug(TAG, "Setting reference distance: " + referenceDistance);
        view.setReferenceDistance(referenceDistance);
    }
    
    @ReactProp(name = "rolloffFactor", defaultFloat = 1.0f)
    public void setRolloffFactor(ViroSoundFieldView view, float rolloffFactor) {
        ViroLog.debug(TAG, "Setting rolloff factor: " + rolloffFactor);
        view.setRolloffFactor(rolloffFactor);
    }
    
    @ReactProp(name = "attenuationModel")
    public void setAttenuationModel(ViroSoundFieldView view, @Nullable String attenuationModel) {
        ViroLog.debug(TAG, "Setting attenuation model: " + attenuationModel);
        view.setAttenuationModel(attenuationModel);
    }
    
    // Occlusion and Obstruction Properties
    @ReactProp(name = "occlusionEnabled", defaultBoolean = false)
    public void setOcclusionEnabled(ViroSoundFieldView view, boolean occlusionEnabled) {
        ViroLog.debug(TAG, "Setting occlusion enabled: " + occlusionEnabled);
        view.setOcclusionEnabled(occlusionEnabled);
    }
    
    @ReactProp(name = "occlusionStrength", defaultFloat = 1.0f)
    public void setOcclusionStrength(ViroSoundFieldView view, float occlusionStrength) {
        ViroLog.debug(TAG, "Setting occlusion strength: " + occlusionStrength);
        view.setOcclusionStrength(occlusionStrength);
    }
    
    @ReactProp(name = "obstructionEnabled", defaultBoolean = false)
    public void setObstructionEnabled(ViroSoundFieldView view, boolean obstructionEnabled) {
        ViroLog.debug(TAG, "Setting obstruction enabled: " + obstructionEnabled);
        view.setObstructionEnabled(obstructionEnabled);
    }
    
    @ReactProp(name = "obstructionStrength", defaultFloat = 1.0f)
    public void setObstructionStrength(ViroSoundFieldView view, float obstructionStrength) {
        ViroLog.debug(TAG, "Setting obstruction strength: " + obstructionStrength);
        view.setObstructionStrength(obstructionStrength);
    }
    
    // Visualization Properties
    @ReactProp(name = "visualizationEnabled", defaultBoolean = false)
    public void setVisualizationEnabled(ViroSoundFieldView view, boolean visualizationEnabled) {
        ViroLog.debug(TAG, "Setting visualization enabled: " + visualizationEnabled);
        view.setVisualizationEnabled(visualizationEnabled);
    }
    
    @ReactProp(name = "visualizationType")
    public void setVisualizationType(ViroSoundFieldView view, @Nullable String visualizationType) {
        ViroLog.debug(TAG, "Setting visualization type: " + visualizationType);
        view.setVisualizationType(visualizationType);
    }
    
    @ReactProp(name = "visualizationOpacity", defaultFloat = 0.5f)
    public void setVisualizationOpacity(ViroSoundFieldView view, float visualizationOpacity) {
        ViroLog.debug(TAG, "Setting visualization opacity: " + visualizationOpacity);
        view.setVisualizationOpacity(visualizationOpacity);
    }
    
    @ReactProp(name = "visualizationColor")
    public void setVisualizationColor(ViroSoundFieldView view, @Nullable String visualizationColor) {
        ViroLog.debug(TAG, "Setting visualization color: " + visualizationColor);
        view.setVisualizationColor(visualizationColor);
    }
    
    // Performance Properties
    @ReactProp(name = "processingQuality")
    public void setProcessingQuality(ViroSoundFieldView view, @Nullable String processingQuality) {
        ViroLog.debug(TAG, "Setting processing quality: " + processingQuality);
        view.setProcessingQuality(processingQuality);
    }
    
    @ReactProp(name = "bufferSize", defaultInt = 4096)
    public void setBufferSize(ViroSoundFieldView view, int bufferSize) {
        ViroLog.debug(TAG, "Setting buffer size: " + bufferSize);
        view.setBufferSize(bufferSize);
    }
    
    @ReactProp(name = "sampleRate", defaultInt = 44100)
    public void setSampleRate(ViroSoundFieldView view, int sampleRate) {
        ViroLog.debug(TAG, "Setting sample rate: " + sampleRate);
        view.setSampleRate(sampleRate);
    }
    
    @ReactProp(name = "optimizationEnabled", defaultBoolean = true)
    public void setOptimizationEnabled(ViroSoundFieldView view, boolean optimizationEnabled) {
        ViroLog.debug(TAG, "Setting optimization enabled: " + optimizationEnabled);
        view.setOptimizationEnabled(optimizationEnabled);
    }
    
    // Animation Properties
    @ReactProp(name = "animation")
    public void setAnimation(ViroSoundFieldView view, @Nullable ReadableMap animation) {
        ViroLog.debug(TAG, "Setting animation: " + animation);
        view.setAnimation(animation);
    }
    
    @ReactProp(name = "transformBehaviors")
    public void setTransformBehaviors(ViroSoundFieldView view, @Nullable ReadableArray transformBehaviors) {
        ViroLog.debug(TAG, "Setting transform behaviors: " + transformBehaviors);
        view.setTransformBehaviors(transformBehaviors);
    }
    
    @ReactProp(name = "viroTag")
    public void setViroTag(ViroSoundFieldView view, @Nullable String viroTag) {
        ViroLog.debug(TAG, "Setting viro tag: " + viroTag);
        view.setViroTag(viroTag);
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
            "onFieldUpdate", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onFieldUpdate",
                    "captured", "onFieldUpdateCapture"
                )
            ),
            "onAmbisonicUpdate", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onAmbisonicUpdate",
                    "captured", "onAmbisonicUpdateCapture"
                )
            ),
            "onEnvironmentalUpdate", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onEnvironmentalUpdate",
                    "captured", "onEnvironmentalUpdateCapture"
                )
            ),
            "onVisualizationUpdate", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onVisualizationUpdate",
                    "captured", "onVisualizationUpdateCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroSoundFieldView view) {
        ViroLog.debug(TAG, "Dropping ViroSoundFieldView instance");
        super.onDropViewInstance(view);
    }
}