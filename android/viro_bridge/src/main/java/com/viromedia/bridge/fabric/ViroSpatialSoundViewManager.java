//
//  ViroSpatialSoundViewManager.java
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
 * ViroSpatialSoundViewManager - 3D Spatial Audio ViewManager
 * 
 * This ViewManager handles 3D spatial audio positioning for ViroReact applications.
 * It provides comprehensive spatial audio capabilities including precise 3D positioning,
 * directional audio, environmental effects, and advanced spatial audio processing.
 * 
 * Key Features:
 * - Precise 3D audio positioning
 * - Directional audio with cone parameters
 * - Distance-based attenuation models
 * - Environmental audio effects
 * - Real-time spatial audio processing
 * - Doppler effect simulation
 * - Occlusion and obstruction handling
 * - Performance optimized 3D audio
 * - Cross-platform compatibility
 * - Interactive audio manipulation
 */
public class ViroSpatialSoundViewManager extends SimpleViewManager<ViroSpatialSoundView> {
    
    private static final String TAG = ViroLog.getTag(ViroSpatialSoundViewManager.class);
    private static final String REACT_CLASS = "ViroSpatialSound";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroSpatialSoundView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroSpatialSoundView instance");
        return new ViroSpatialSoundView(reactContext);
    }
    
    // Audio Source Properties
    @ReactProp(name = "source")
    public void setSource(ViroSpatialSoundView view, @Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        view.setSource(source);
    }
    
    @ReactProp(name = "uri")
    public void setUri(ViroSpatialSoundView view, @Nullable String uri) {
        ViroLog.debug(TAG, "Setting uri: " + uri);
        view.setUri(uri);
    }
    
    @ReactProp(name = "local")
    public void setLocal(ViroSpatialSoundView view, @Nullable String local) {
        ViroLog.debug(TAG, "Setting local: " + local);
        view.setLocal(local);
    }
    
    @ReactProp(name = "resource")
    public void setResource(ViroSpatialSoundView view, @Nullable String resource) {
        ViroLog.debug(TAG, "Setting resource: " + resource);
        view.setResource(resource);
    }
    
    @ReactProp(name = "audioFormat")
    public void setAudioFormat(ViroSpatialSoundView view, @Nullable String audioFormat) {
        ViroLog.debug(TAG, "Setting audio format: " + audioFormat);
        view.setAudioFormat(audioFormat);
    }
    
    // Playback Control Properties
    @ReactProp(name = "paused", defaultBoolean = false)
    public void setPaused(ViroSpatialSoundView view, boolean paused) {
        ViroLog.debug(TAG, "Setting paused: " + paused);
        view.setPaused(paused);
    }
    
    @ReactProp(name = "loop", defaultBoolean = false)
    public void setLoop(ViroSpatialSoundView view, boolean loop) {
        ViroLog.debug(TAG, "Setting loop: " + loop);
        view.setLoop(loop);
    }
    
    @ReactProp(name = "muted", defaultBoolean = false)
    public void setMuted(ViroSpatialSoundView view, boolean muted) {
        ViroLog.debug(TAG, "Setting muted: " + muted);
        view.setMuted(muted);
    }
    
    @ReactProp(name = "volume", defaultFloat = 1.0f)
    public void setVolume(ViroSpatialSoundView view, float volume) {
        ViroLog.debug(TAG, "Setting volume: " + volume);
        view.setVolume(volume);
    }
    
    @ReactProp(name = "rate", defaultFloat = 1.0f)
    public void setRate(ViroSpatialSoundView view, float rate) {
        ViroLog.debug(TAG, "Setting rate: " + rate);
        view.setRate(rate);
    }
    
    @ReactProp(name = "pitch", defaultFloat = 1.0f)
    public void setPitch(ViroSpatialSoundView view, float pitch) {
        ViroLog.debug(TAG, "Setting pitch: " + pitch);
        view.setPitch(pitch);
    }
    
    @ReactProp(name = "seekTime", defaultFloat = 0.0f)
    public void setSeekTime(ViroSpatialSoundView view, float seekTime) {
        ViroLog.debug(TAG, "Setting seek time: " + seekTime);
        view.setSeekTime(seekTime);
    }
    
    // 3D Position Properties
    @ReactProp(name = "position")
    public void setPosition(ViroSpatialSoundView view, @Nullable ReadableArray position) {
        ViroLog.debug(TAG, "Setting position: " + position);
        view.setPosition(position);
    }
    
    @ReactProp(name = "rotation")
    public void setRotation(ViroSpatialSoundView view, @Nullable ReadableArray rotation) {
        ViroLog.debug(TAG, "Setting rotation: " + rotation);
        view.setRotation(rotation);
    }
    
    @ReactProp(name = "scale")
    public void setScale(ViroSpatialSoundView view, @Nullable ReadableArray scale) {
        ViroLog.debug(TAG, "Setting scale: " + scale);
        view.setScale(scale);
    }
    
    @ReactProp(name = "velocity")
    public void setVelocity(ViroSpatialSoundView view, @Nullable ReadableArray velocity) {
        ViroLog.debug(TAG, "Setting velocity: " + velocity);
        view.setVelocity(velocity);
    }
    
    @ReactProp(name = "direction")
    public void setDirection(ViroSpatialSoundView view, @Nullable ReadableArray direction) {
        ViroLog.debug(TAG, "Setting direction: " + direction);
        view.setDirection(direction);
    }
    
    @ReactProp(name = "up")
    public void setUp(ViroSpatialSoundView view, @Nullable ReadableArray up) {
        ViroLog.debug(TAG, "Setting up: " + up);
        view.setUp(up);
    }
    
    // Spatial Audio Properties
    @ReactProp(name = "spatialAudioEnabled", defaultBoolean = true)
    public void setSpatialAudioEnabled(ViroSpatialSoundView view, boolean spatialAudioEnabled) {
        ViroLog.debug(TAG, "Setting spatial audio enabled: " + spatialAudioEnabled);
        view.setSpatialAudioEnabled(spatialAudioEnabled);
    }
    
    @ReactProp(name = "spatialAudioQuality")
    public void setSpatialAudioQuality(ViroSpatialSoundView view, @Nullable String spatialAudioQuality) {
        ViroLog.debug(TAG, "Setting spatial audio quality: " + spatialAudioQuality);
        view.setSpatialAudioQuality(spatialAudioQuality);
    }
    
    @ReactProp(name = "spatialBlend", defaultFloat = 1.0f)
    public void setSpatialBlend(ViroSpatialSoundView view, float spatialBlend) {
        ViroLog.debug(TAG, "Setting spatial blend: " + spatialBlend);
        view.setSpatialBlend(spatialBlend);
    }
    
    @ReactProp(name = "stereoPan", defaultFloat = 0.0f)
    public void setStereoPan(ViroSpatialSoundView view, float stereoPan) {
        ViroLog.debug(TAG, "Setting stereo pan: " + stereoPan);
        view.setStereoPan(stereoPan);
    }
    
    // Distance and Attenuation Properties
    @ReactProp(name = "distanceModel")
    public void setDistanceModel(ViroSpatialSoundView view, @Nullable String distanceModel) {
        ViroLog.debug(TAG, "Setting distance model: " + distanceModel);
        view.setDistanceModel(distanceModel);
    }
    
    @ReactProp(name = "maxDistance", defaultFloat = 1000.0f)
    public void setMaxDistance(ViroSpatialSoundView view, float maxDistance) {
        ViroLog.debug(TAG, "Setting max distance: " + maxDistance);
        view.setMaxDistance(maxDistance);
    }
    
    @ReactProp(name = "referenceDistance", defaultFloat = 1.0f)
    public void setReferenceDistance(ViroSpatialSoundView view, float referenceDistance) {
        ViroLog.debug(TAG, "Setting reference distance: " + referenceDistance);
        view.setReferenceDistance(referenceDistance);
    }
    
    @ReactProp(name = "rolloffFactor", defaultFloat = 1.0f)
    public void setRolloffFactor(ViroSpatialSoundView view, float rolloffFactor) {
        ViroLog.debug(TAG, "Setting rolloff factor: " + rolloffFactor);
        view.setRolloffFactor(rolloffFactor);
    }
    
    @ReactProp(name = "attenuationCurve")
    public void setAttenuationCurve(ViroSpatialSoundView view, @Nullable ReadableArray attenuationCurve) {
        ViroLog.debug(TAG, "Setting attenuation curve: " + attenuationCurve);
        view.setAttenuationCurve(attenuationCurve);
    }
    
    @ReactProp(name = "volumeRolloffCurve")
    public void setVolumeRolloffCurve(ViroSpatialSoundView view, @Nullable ReadableArray volumeRolloffCurve) {
        ViroLog.debug(TAG, "Setting volume rolloff curve: " + volumeRolloffCurve);
        view.setVolumeRolloffCurve(volumeRolloffCurve);
    }
    
    // Directional Audio Properties
    @ReactProp(name = "directional", defaultBoolean = false)
    public void setDirectional(ViroSpatialSoundView view, boolean directional) {
        ViroLog.debug(TAG, "Setting directional: " + directional);
        view.setDirectional(directional);
    }
    
    @ReactProp(name = "coneInnerAngle", defaultFloat = 360.0f)
    public void setConeInnerAngle(ViroSpatialSoundView view, float coneInnerAngle) {
        ViroLog.debug(TAG, "Setting cone inner angle: " + coneInnerAngle);
        view.setConeInnerAngle(coneInnerAngle);
    }
    
    @ReactProp(name = "coneOuterAngle", defaultFloat = 360.0f)
    public void setConeOuterAngle(ViroSpatialSoundView view, float coneOuterAngle) {
        ViroLog.debug(TAG, "Setting cone outer angle: " + coneOuterAngle);
        view.setConeOuterAngle(coneOuterAngle);
    }
    
    @ReactProp(name = "coneOuterGain", defaultFloat = 0.0f)
    public void setConeOuterGain(ViroSpatialSoundView view, float coneOuterGain) {
        ViroLog.debug(TAG, "Setting cone outer gain: " + coneOuterGain);
        view.setConeOuterGain(coneOuterGain);
    }
    
    @ReactProp(name = "coneOuterGainHF", defaultFloat = 0.0f)
    public void setConeOuterGainHF(ViroSpatialSoundView view, float coneOuterGainHF) {
        ViroLog.debug(TAG, "Setting cone outer gain HF: " + coneOuterGainHF);
        view.setConeOuterGainHF(coneOuterGainHF);
    }
    
    @ReactProp(name = "directionalityPattern")
    public void setDirectionalityPattern(ViroSpatialSoundView view, @Nullable String directionalityPattern) {
        ViroLog.debug(TAG, "Setting directionality pattern: " + directionalityPattern);
        view.setDirectionalityPattern(directionalityPattern);
    }
    
    // Doppler Effect Properties
    @ReactProp(name = "dopplerEnabled", defaultBoolean = false)
    public void setDopplerEnabled(ViroSpatialSoundView view, boolean dopplerEnabled) {
        ViroLog.debug(TAG, "Setting doppler enabled: " + dopplerEnabled);
        view.setDopplerEnabled(dopplerEnabled);
    }
    
    @ReactProp(name = "dopplerLevel", defaultFloat = 1.0f)
    public void setDopplerLevel(ViroSpatialSoundView view, float dopplerLevel) {
        ViroLog.debug(TAG, "Setting doppler level: " + dopplerLevel);
        view.setDopplerLevel(dopplerLevel);
    }
    
    @ReactProp(name = "dopplerFactor", defaultFloat = 1.0f)
    public void setDopplerFactor(ViroSpatialSoundView view, float dopplerFactor) {
        ViroLog.debug(TAG, "Setting doppler factor: " + dopplerFactor);
        view.setDopplerFactor(dopplerFactor);
    }
    
    // Environmental Audio Properties
    @ReactProp(name = "environmentalAudio")
    public void setEnvironmentalAudio(ViroSpatialSoundView view, @Nullable ReadableMap environmentalAudio) {
        ViroLog.debug(TAG, "Setting environmental audio: " + environmentalAudio);
        view.setEnvironmentalAudio(environmentalAudio);
    }
    
    @ReactProp(name = "reverb")
    public void setReverb(ViroSpatialSoundView view, @Nullable ReadableMap reverb) {
        ViroLog.debug(TAG, "Setting reverb: " + reverb);
        view.setReverb(reverb);
    }
    
    @ReactProp(name = "reverbZone")
    public void setReverbZone(ViroSpatialSoundView view, @Nullable ReadableMap reverbZone) {
        ViroLog.debug(TAG, "Setting reverb zone: " + reverbZone);
        view.setReverbZone(reverbZone);
    }
    
    @ReactProp(name = "airAbsorption", defaultFloat = 0.0f)
    public void setAirAbsorption(ViroSpatialSoundView view, float airAbsorption) {
        ViroLog.debug(TAG, "Setting air absorption: " + airAbsorption);
        view.setAirAbsorption(airAbsorption);
    }
    
    // Occlusion and Obstruction Properties
    @ReactProp(name = "occlusionEnabled", defaultBoolean = false)
    public void setOcclusionEnabled(ViroSpatialSoundView view, boolean occlusionEnabled) {
        ViroLog.debug(TAG, "Setting occlusion enabled: " + occlusionEnabled);
        view.setOcclusionEnabled(occlusionEnabled);
    }
    
    @ReactProp(name = "occlusionStrength", defaultFloat = 1.0f)
    public void setOcclusionStrength(ViroSpatialSoundView view, float occlusionStrength) {
        ViroLog.debug(TAG, "Setting occlusion strength: " + occlusionStrength);
        view.setOcclusionStrength(occlusionStrength);
    }
    
    @ReactProp(name = "occlusionDirectRatio", defaultFloat = 0.0f)
    public void setOcclusionDirectRatio(ViroSpatialSoundView view, float occlusionDirectRatio) {
        ViroLog.debug(TAG, "Setting occlusion direct ratio: " + occlusionDirectRatio);
        view.setOcclusionDirectRatio(occlusionDirectRatio);
    }
    
    @ReactProp(name = "obstructionEnabled", defaultBoolean = false)
    public void setObstructionEnabled(ViroSpatialSoundView view, boolean obstructionEnabled) {
        ViroLog.debug(TAG, "Setting obstruction enabled: " + obstructionEnabled);
        view.setObstructionEnabled(obstructionEnabled);
    }
    
    @ReactProp(name = "obstructionStrength", defaultFloat = 1.0f)
    public void setObstructionStrength(ViroSpatialSoundView view, float obstructionStrength) {
        ViroLog.debug(TAG, "Setting obstruction strength: " + obstructionStrength);
        view.setObstructionStrength(obstructionStrength);
    }
    
    @ReactProp(name = "obstructionDirectRatio", defaultFloat = 0.0f)
    public void setObstructionDirectRatio(ViroSpatialSoundView view, float obstructionDirectRatio) {
        ViroLog.debug(TAG, "Setting obstruction direct ratio: " + obstructionDirectRatio);
        view.setObstructionDirectRatio(obstructionDirectRatio);
    }
    
    // Audio Effects Properties
    @ReactProp(name = "effects")
    public void setEffects(ViroSpatialSoundView view, @Nullable ReadableArray effects) {
        ViroLog.debug(TAG, "Setting effects: " + effects);
        view.setEffects(effects);
    }
    
    @ReactProp(name = "filters")
    public void setFilters(ViroSpatialSoundView view, @Nullable ReadableArray filters) {
        ViroLog.debug(TAG, "Setting filters: " + filters);
        view.setFilters(filters);
    }
    
    @ReactProp(name = "equalizer")
    public void setEqualizer(ViroSpatialSoundView view, @Nullable ReadableMap equalizer) {
        ViroLog.debug(TAG, "Setting equalizer: " + equalizer);
        view.setEqualizer(equalizer);
    }
    
    @ReactProp(name = "lowPassFilter")
    public void setLowPassFilter(ViroSpatialSoundView view, @Nullable ReadableMap lowPassFilter) {
        ViroLog.debug(TAG, "Setting low pass filter: " + lowPassFilter);
        view.setLowPassFilter(lowPassFilter);
    }
    
    @ReactProp(name = "highPassFilter")
    public void setHighPassFilter(ViroSpatialSoundView view, @Nullable ReadableMap highPassFilter) {
        ViroLog.debug(TAG, "Setting high pass filter: " + highPassFilter);
        view.setHighPassFilter(highPassFilter);
    }
    
    // Performance Properties
    @ReactProp(name = "priority", defaultInt = 128)
    public void setPriority(ViroSpatialSoundView view, int priority) {
        ViroLog.debug(TAG, "Setting priority: " + priority);
        view.setPriority(priority);
    }
    
    @ReactProp(name = "processingQuality")
    public void setProcessingQuality(ViroSpatialSoundView view, @Nullable String processingQuality) {
        ViroLog.debug(TAG, "Setting processing quality: " + processingQuality);
        view.setProcessingQuality(processingQuality);
    }
    
    @ReactProp(name = "bypassEffects", defaultBoolean = false)
    public void setBypassEffects(ViroSpatialSoundView view, boolean bypassEffects) {
        ViroLog.debug(TAG, "Setting bypass effects: " + bypassEffects);
        view.setBypassEffects(bypassEffects);
    }
    
    @ReactProp(name = "bypassListenerEffects", defaultBoolean = false)
    public void setBypassListenerEffects(ViroSpatialSoundView view, boolean bypassListenerEffects) {
        ViroLog.debug(TAG, "Setting bypass listener effects: " + bypassListenerEffects);
        view.setBypassListenerEffects(bypassListenerEffects);
    }
    
    @ReactProp(name = "bypassReverbZones", defaultBoolean = false)
    public void setBypassReverbZones(ViroSpatialSoundView view, boolean bypassReverbZones) {
        ViroLog.debug(TAG, "Setting bypass reverb zones: " + bypassReverbZones);
        view.setBypassReverbZones(bypassReverbZones);
    }
    
    // Animation Properties
    @ReactProp(name = "animation")
    public void setAnimation(ViroSpatialSoundView view, @Nullable ReadableMap animation) {
        ViroLog.debug(TAG, "Setting animation: " + animation);
        view.setAnimation(animation);
    }
    
    @ReactProp(name = "transformBehaviors")
    public void setTransformBehaviors(ViroSpatialSoundView view, @Nullable ReadableArray transformBehaviors) {
        ViroLog.debug(TAG, "Setting transform behaviors: " + transformBehaviors);
        view.setTransformBehaviors(transformBehaviors);
    }
    
    @ReactProp(name = "viroTag")
    public void setViroTag(ViroSpatialSoundView view, @Nullable String viroTag) {
        ViroLog.debug(TAG, "Setting viro tag: " + viroTag);
        view.setViroTag(viroTag);
    }
    
    // Interaction Properties
    @ReactProp(name = "onHover", defaultBoolean = false)
    public void setOnHover(ViroSpatialSoundView view, boolean onHover) {
        ViroLog.debug(TAG, "Setting on hover: " + onHover);
        view.setOnHover(onHover);
    }
    
    @ReactProp(name = "onClick", defaultBoolean = false)
    public void setOnClick(ViroSpatialSoundView view, boolean onClick) {
        ViroLog.debug(TAG, "Setting on click: " + onClick);
        view.setOnClick(onClick);
    }
    
    @ReactProp(name = "onTouch", defaultBoolean = false)
    public void setOnTouch(ViroSpatialSoundView view, boolean onTouch) {
        ViroLog.debug(TAG, "Setting on touch: " + onTouch);
        view.setOnTouch(onTouch);
    }
    
    @ReactProp(name = "onDrag", defaultBoolean = false)
    public void setOnDrag(ViroSpatialSoundView view, boolean onDrag) {
        ViroLog.debug(TAG, "Setting on drag: " + onDrag);
        view.setOnDrag(onDrag);
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
            "onPositionUpdate", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onPositionUpdate",
                    "captured", "onPositionUpdateCapture"
                )
            ),
            "onVolumeChange", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onVolumeChange",
                    "captured", "onVolumeChangeCapture"
                )
            ),
            "onSpatialUpdate", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onSpatialUpdate",
                    "captured", "onSpatialUpdateCapture"
                )
            ),
            "onDistanceChange", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onDistanceChange",
                    "captured", "onDistanceChangeCapture"
                )
            ),
            "onDirectionChange", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onDirectionChange",
                    "captured", "onDirectionChangeCapture"
                )
            ),
            "onHover", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onHover",
                    "captured", "onHoverCapture"
                )
            ),
            "onClick", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onClick",
                    "captured", "onClickCapture"
                )
            ),
            "onTouch", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onTouch",
                    "captured", "onTouchCapture"
                )
            ),
            "onDrag", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onDrag",
                    "captured", "onDragCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroSpatialSoundView view) {
        ViroLog.debug(TAG, "Dropping ViroSpatialSoundView instance");
        super.onDropViewInstance(view);
    }
}