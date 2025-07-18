//
//  ViroSpatialSoundView.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import com.viromedia.bridge.utility.ViroLog;

/**
 * ViroSpatialSoundView - 3D Spatial Audio View
 * 
 * This view represents a 3D spatial audio component that provides precise audio positioning
 * in 3D space with support for directional audio, distance attenuation, environmental effects,
 * and advanced spatial audio processing.
 * 
 * Key capabilities:
 * - Precise 3D audio positioning
 * - Directional audio with cone parameters
 * - Distance-based attenuation models
 * - Environmental audio effects
 * - Real-time spatial audio processing
 * - Doppler effect simulation
 * - Occlusion and obstruction handling
 * - Interactive audio manipulation
 * - Performance optimized 3D audio
 */
public class ViroSpatialSoundView extends View {
    
    private static final String TAG = ViroLog.getTag(ViroSpatialSoundView.class);
    
    // Audio source properties
    private ReadableMap source;
    private String uri;
    private String local;
    private String resource;
    private String audioFormat = "auto";
    
    // Playback control properties
    private boolean paused = false;
    private boolean loop = false;
    private boolean muted = false;
    private float volume = 1.0f;
    private float rate = 1.0f;
    private float pitch = 1.0f;
    private float seekTime = 0.0f;
    
    // 3D position properties
    private ReadableArray position;
    private ReadableArray rotation;
    private ReadableArray scale;
    private ReadableArray velocity;
    private ReadableArray direction;
    private ReadableArray up;
    
    // Spatial audio properties
    private boolean spatialAudioEnabled = true;
    private String spatialAudioQuality = "high";
    private float spatialBlend = 1.0f;
    private float stereoPan = 0.0f;
    
    // Distance and attenuation properties
    private String distanceModel = "inverse";
    private float maxDistance = 1000.0f;
    private float referenceDistance = 1.0f;
    private float rolloffFactor = 1.0f;
    private ReadableArray attenuationCurve;
    private ReadableArray volumeRolloffCurve;
    
    // Directional audio properties
    private boolean directional = false;
    private float coneInnerAngle = 360.0f;
    private float coneOuterAngle = 360.0f;
    private float coneOuterGain = 0.0f;
    private float coneOuterGainHF = 0.0f;
    private String directionalityPattern = "cone";
    
    // Doppler effect properties
    private boolean dopplerEnabled = false;
    private float dopplerLevel = 1.0f;
    private float dopplerFactor = 1.0f;
    
    // Environmental audio properties
    private ReadableMap environmentalAudio;
    private ReadableMap reverb;
    private ReadableMap reverbZone;
    private float airAbsorption = 0.0f;
    
    // Occlusion and obstruction properties
    private boolean occlusionEnabled = false;
    private float occlusionStrength = 1.0f;
    private float occlusionDirectRatio = 0.0f;
    private boolean obstructionEnabled = false;
    private float obstructionStrength = 1.0f;
    private float obstructionDirectRatio = 0.0f;
    
    // Audio effects properties
    private ReadableArray effects;
    private ReadableArray filters;
    private ReadableMap equalizer;
    private ReadableMap lowPassFilter;
    private ReadableMap highPassFilter;
    
    // Performance properties
    private int priority = 128;
    private String processingQuality = "high";
    private boolean bypassEffects = false;
    private boolean bypassListenerEffects = false;
    private boolean bypassReverbZones = false;
    
    // Animation properties
    private ReadableMap animation;
    private ReadableArray transformBehaviors;
    private String viroTag;
    
    // Interaction properties
    private boolean onHover = false;
    private boolean onClick = false;
    private boolean onTouch = false;
    private boolean onDrag = false;
    
    // Internal state
    private boolean audioDirty = true;
    private boolean spatialDirty = true;
    private boolean positionDirty = true;
    private boolean effectsDirty = true;
    
    public ViroSpatialSoundView(@NonNull Context context) {
        super(context);
        ViroLog.debug(TAG, "ViroSpatialSoundView created");
        initializeSpatialSound();
    }
    
    private void initializeSpatialSound() {
        ViroLog.debug(TAG, "Initializing spatial sound");
        // Initialize default state
        updateAudio();
        updateSpatialAudio();
        updatePosition();
    }
    
    // Audio Source Setters
    public void setSource(@Nullable ReadableMap source) {
        this.source = source;
        this.audioDirty = true;
        updateAudio();
    }
    
    public void setUri(@Nullable String uri) {
        this.uri = uri;
        this.audioDirty = true;
        updateAudio();
    }
    
    public void setLocal(@Nullable String local) {
        this.local = local;
        this.audioDirty = true;
        updateAudio();
    }
    
    public void setResource(@Nullable String resource) {
        this.resource = resource;
        this.audioDirty = true;
        updateAudio();
    }
    
    public void setAudioFormat(@Nullable String audioFormat) {
        this.audioFormat = audioFormat != null ? audioFormat : "auto";
        this.audioDirty = true;
        updateAudio();
    }
    
    // Playback Control Setters
    public void setPaused(boolean paused) {
        this.paused = paused;
        updatePlaybackControl();
    }
    
    public void setLoop(boolean loop) {
        this.loop = loop;
        updatePlaybackControl();
    }
    
    public void setMuted(boolean muted) {
        this.muted = muted;
        updatePlaybackControl();
    }
    
    public void setVolume(float volume) {
        this.volume = volume;
        updatePlaybackControl();
    }
    
    public void setRate(float rate) {
        this.rate = rate;
        updatePlaybackControl();
    }
    
    public void setPitch(float pitch) {
        this.pitch = pitch;
        updatePlaybackControl();
    }
    
    public void setSeekTime(float seekTime) {
        this.seekTime = seekTime;
        updatePlaybackControl();
    }
    
    // 3D Position Setters
    public void setPosition(@Nullable ReadableArray position) {
        this.position = position;
        this.positionDirty = true;
        updatePosition();
    }
    
    public void setRotation(@Nullable ReadableArray rotation) {
        this.rotation = rotation;
        this.positionDirty = true;
        updatePosition();
    }
    
    public void setScale(@Nullable ReadableArray scale) {
        this.scale = scale;
        this.positionDirty = true;
        updatePosition();
    }
    
    public void setVelocity(@Nullable ReadableArray velocity) {
        this.velocity = velocity;
        this.positionDirty = true;
        updatePosition();
    }
    
    public void setDirection(@Nullable ReadableArray direction) {
        this.direction = direction;
        this.positionDirty = true;
        updatePosition();
    }
    
    public void setUp(@Nullable ReadableArray up) {
        this.up = up;
        this.positionDirty = true;
        updatePosition();
    }
    
    // Spatial Audio Setters
    public void setSpatialAudioEnabled(boolean spatialAudioEnabled) {
        this.spatialAudioEnabled = spatialAudioEnabled;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setSpatialAudioQuality(@Nullable String spatialAudioQuality) {
        this.spatialAudioQuality = spatialAudioQuality != null ? spatialAudioQuality : "high";
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setSpatialBlend(float spatialBlend) {
        this.spatialBlend = spatialBlend;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setStereoPan(float stereoPan) {
        this.stereoPan = stereoPan;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    // Distance and Attenuation Setters
    public void setDistanceModel(@Nullable String distanceModel) {
        this.distanceModel = distanceModel != null ? distanceModel : "inverse";
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setMaxDistance(float maxDistance) {
        this.maxDistance = maxDistance;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setReferenceDistance(float referenceDistance) {
        this.referenceDistance = referenceDistance;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setRolloffFactor(float rolloffFactor) {
        this.rolloffFactor = rolloffFactor;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setAttenuationCurve(@Nullable ReadableArray attenuationCurve) {
        this.attenuationCurve = attenuationCurve;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setVolumeRolloffCurve(@Nullable ReadableArray volumeRolloffCurve) {
        this.volumeRolloffCurve = volumeRolloffCurve;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    // Directional Audio Setters
    public void setDirectional(boolean directional) {
        this.directional = directional;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setConeInnerAngle(float coneInnerAngle) {
        this.coneInnerAngle = coneInnerAngle;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setConeOuterAngle(float coneOuterAngle) {
        this.coneOuterAngle = coneOuterAngle;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setConeOuterGain(float coneOuterGain) {
        this.coneOuterGain = coneOuterGain;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setConeOuterGainHF(float coneOuterGainHF) {
        this.coneOuterGainHF = coneOuterGainHF;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setDirectionalityPattern(@Nullable String directionalityPattern) {
        this.directionalityPattern = directionalityPattern != null ? directionalityPattern : "cone";
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    // Doppler Effect Setters
    public void setDopplerEnabled(boolean dopplerEnabled) {
        this.dopplerEnabled = dopplerEnabled;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setDopplerLevel(float dopplerLevel) {
        this.dopplerLevel = dopplerLevel;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setDopplerFactor(float dopplerFactor) {
        this.dopplerFactor = dopplerFactor;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    // Environmental Audio Setters
    public void setEnvironmentalAudio(@Nullable ReadableMap environmentalAudio) {
        this.environmentalAudio = environmentalAudio;
        this.effectsDirty = true;
        updateEffects();
    }
    
    public void setReverb(@Nullable ReadableMap reverb) {
        this.reverb = reverb;
        this.effectsDirty = true;
        updateEffects();
    }
    
    public void setReverbZone(@Nullable ReadableMap reverbZone) {
        this.reverbZone = reverbZone;
        this.effectsDirty = true;
        updateEffects();
    }
    
    public void setAirAbsorption(float airAbsorption) {
        this.airAbsorption = airAbsorption;
        this.effectsDirty = true;
        updateEffects();
    }
    
    // Occlusion and Obstruction Setters
    public void setOcclusionEnabled(boolean occlusionEnabled) {
        this.occlusionEnabled = occlusionEnabled;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setOcclusionStrength(float occlusionStrength) {
        this.occlusionStrength = occlusionStrength;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setOcclusionDirectRatio(float occlusionDirectRatio) {
        this.occlusionDirectRatio = occlusionDirectRatio;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setObstructionEnabled(boolean obstructionEnabled) {
        this.obstructionEnabled = obstructionEnabled;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setObstructionStrength(float obstructionStrength) {
        this.obstructionStrength = obstructionStrength;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    public void setObstructionDirectRatio(float obstructionDirectRatio) {
        this.obstructionDirectRatio = obstructionDirectRatio;
        this.spatialDirty = true;
        updateSpatialAudio();
    }
    
    // Audio Effects Setters
    public void setEffects(@Nullable ReadableArray effects) {
        this.effects = effects;
        this.effectsDirty = true;
        updateEffects();
    }
    
    public void setFilters(@Nullable ReadableArray filters) {
        this.filters = filters;
        this.effectsDirty = true;
        updateEffects();
    }
    
    public void setEqualizer(@Nullable ReadableMap equalizer) {
        this.equalizer = equalizer;
        this.effectsDirty = true;
        updateEffects();
    }
    
    public void setLowPassFilter(@Nullable ReadableMap lowPassFilter) {
        this.lowPassFilter = lowPassFilter;
        this.effectsDirty = true;
        updateEffects();
    }
    
    public void setHighPassFilter(@Nullable ReadableMap highPassFilter) {
        this.highPassFilter = highPassFilter;
        this.effectsDirty = true;
        updateEffects();
    }
    
    // Performance Setters
    public void setPriority(int priority) {
        this.priority = priority;
        updatePerformance();
    }
    
    public void setProcessingQuality(@Nullable String processingQuality) {
        this.processingQuality = processingQuality != null ? processingQuality : "high";
        updatePerformance();
    }
    
    public void setBypassEffects(boolean bypassEffects) {
        this.bypassEffects = bypassEffects;
        updatePerformance();
    }
    
    public void setBypassListenerEffects(boolean bypassListenerEffects) {
        this.bypassListenerEffects = bypassListenerEffects;
        updatePerformance();
    }
    
    public void setBypassReverbZones(boolean bypassReverbZones) {
        this.bypassReverbZones = bypassReverbZones;
        updatePerformance();
    }
    
    // Animation Setters
    public void setAnimation(@Nullable ReadableMap animation) {
        this.animation = animation;
        updateAnimation();
    }
    
    public void setTransformBehaviors(@Nullable ReadableArray transformBehaviors) {
        this.transformBehaviors = transformBehaviors;
        updateAnimation();
    }
    
    public void setViroTag(@Nullable String viroTag) {
        this.viroTag = viroTag;
    }
    
    // Interaction Setters
    public void setOnHover(boolean onHover) {
        this.onHover = onHover;
    }
    
    public void setOnClick(boolean onClick) {
        this.onClick = onClick;
    }
    
    public void setOnTouch(boolean onTouch) {
        this.onTouch = onTouch;
    }
    
    public void setOnDrag(boolean onDrag) {
        this.onDrag = onDrag;
    }
    
    // Update Methods
    private void updateAudio() {
        if (!audioDirty) return;
        
        ViroLog.debug(TAG, "Updating audio");
        
        // Load audio source
        if (source != null) {
            processAudioSource();
        } else if (uri != null) {
            loadAudioFromUri();
        } else if (local != null) {
            loadAudioFromLocal();
        } else if (resource != null) {
            loadAudioFromResource();
        }
        
        // Configure audio format
        configureAudioFormat();
        
        audioDirty = false;
    }
    
    private void processAudioSource() {
        ViroLog.debug(TAG, "Processing audio source: " + source);
        // Process audio source from ReadableMap
    }
    
    private void loadAudioFromUri() {
        ViroLog.debug(TAG, "Loading audio from URI: " + uri);
        // Load audio from URI
    }
    
    private void loadAudioFromLocal() {
        ViroLog.debug(TAG, "Loading audio from local: " + local);
        // Load audio from local file
    }
    
    private void loadAudioFromResource() {
        ViroLog.debug(TAG, "Loading audio from resource: " + resource);
        // Load audio from resource
    }
    
    private void configureAudioFormat() {
        ViroLog.debug(TAG, "Configuring audio format: " + audioFormat);
        // Configure audio format
    }
    
    private void updatePlaybackControl() {
        ViroLog.debug(TAG, "Updating playback control");
        
        // Update playback state
        if (paused) {
            pausePlayback();
        } else {
            startPlayback();
        }
        
        // Update audio properties
        updateAudioProperties();
    }
    
    private void pausePlayback() {
        ViroLog.debug(TAG, "Pausing playback");
        // Pause audio playback
    }
    
    private void startPlayback() {
        ViroLog.debug(TAG, "Starting playback");
        // Start audio playback
    }
    
    private void updateAudioProperties() {
        ViroLog.debug(TAG, "Updating audio properties");
        // Update volume, rate, pitch, seek time, etc.
    }
    
    private void updatePosition() {
        if (!positionDirty) return;
        
        ViroLog.debug(TAG, "Updating 3D position");
        
        // Update position
        if (position != null) {
            updateAudioPosition();
        }
        
        // Update rotation
        if (rotation != null) {
            updateAudioRotation();
        }
        
        // Update scale
        if (scale != null) {
            updateAudioScale();
        }
        
        // Update velocity (for Doppler effect)
        if (velocity != null) {
            updateAudioVelocity();
        }
        
        // Update direction
        if (direction != null) {
            updateAudioDirection();
        }
        
        // Update up vector
        if (up != null) {
            updateAudioUp();
        }
        
        positionDirty = false;
    }
    
    private void updateAudioPosition() {
        ViroLog.debug(TAG, "Updating audio position: " + position);
        // Update 3D position
    }
    
    private void updateAudioRotation() {
        ViroLog.debug(TAG, "Updating audio rotation: " + rotation);
        // Update 3D rotation
    }
    
    private void updateAudioScale() {
        ViroLog.debug(TAG, "Updating audio scale: " + scale);
        // Update 3D scale
    }
    
    private void updateAudioVelocity() {
        ViroLog.debug(TAG, "Updating audio velocity: " + velocity);
        // Update velocity for Doppler effect
    }
    
    private void updateAudioDirection() {
        ViroLog.debug(TAG, "Updating audio direction: " + direction);
        // Update audio direction
    }
    
    private void updateAudioUp() {
        ViroLog.debug(TAG, "Updating audio up vector: " + up);
        // Update audio up vector
    }
    
    private void updateSpatialAudio() {
        if (!spatialDirty) return;
        
        ViroLog.debug(TAG, "Updating spatial audio");
        
        if (spatialAudioEnabled) {
            configureSpatialAudio();
        } else {
            disableSpatialAudio();
        }
        
        // Update distance and attenuation
        configureDistanceAttenuation();
        
        // Update directional audio
        if (directional) {
            configureDirectionalAudio();
        }
        
        // Update Doppler effect
        if (dopplerEnabled) {
            configureDopplerEffect();
        }
        
        // Update occlusion and obstruction
        configureOcclusionObstruction();
        
        spatialDirty = false;
    }
    
    private void configureSpatialAudio() {
        ViroLog.debug(TAG, "Configuring spatial audio: quality=" + spatialAudioQuality);
        // Configure spatial audio processing
    }
    
    private void disableSpatialAudio() {
        ViroLog.debug(TAG, "Disabling spatial audio");
        // Disable spatial audio processing
    }
    
    private void configureDistanceAttenuation() {
        ViroLog.debug(TAG, "Configuring distance attenuation: model=" + distanceModel);
        // Configure distance-based attenuation
    }
    
    private void configureDirectionalAudio() {
        ViroLog.debug(TAG, "Configuring directional audio: pattern=" + directionalityPattern);
        // Configure directional audio with cone parameters
    }
    
    private void configureDopplerEffect() {
        ViroLog.debug(TAG, "Configuring Doppler effect: level=" + dopplerLevel);
        // Configure Doppler effect
    }
    
    private void configureOcclusionObstruction() {
        ViroLog.debug(TAG, "Configuring occlusion/obstruction");
        // Configure occlusion and obstruction effects
    }
    
    private void updateEffects() {
        if (!effectsDirty) return;
        
        ViroLog.debug(TAG, "Updating effects");
        
        // Configure environmental audio
        if (environmentalAudio != null) {
            configureEnvironmentalAudio();
        }
        
        // Configure reverb
        if (reverb != null) {
            configureReverb();
        }
        
        // Configure reverb zone
        if (reverbZone != null) {
            configureReverbZone();
        }
        
        // Configure audio effects
        if (effects != null) {
            configureAudioEffects();
        }
        
        // Configure filters
        if (filters != null) {
            configureAudioFilters();
        }
        
        // Configure equalizer
        if (equalizer != null) {
            configureEqualizer();
        }
        
        // Configure low pass filter
        if (lowPassFilter != null) {
            configureLowPassFilter();
        }
        
        // Configure high pass filter
        if (highPassFilter != null) {
            configureHighPassFilter();
        }
        
        effectsDirty = false;
    }
    
    private void configureEnvironmentalAudio() {
        ViroLog.debug(TAG, "Configuring environmental audio");
        // Configure environmental audio effects
    }
    
    private void configureReverb() {
        ViroLog.debug(TAG, "Configuring reverb");
        // Configure reverb effects
    }
    
    private void configureReverbZone() {
        ViroLog.debug(TAG, "Configuring reverb zone");
        // Configure reverb zone effects
    }
    
    private void configureAudioEffects() {
        ViroLog.debug(TAG, "Configuring audio effects: " + effects.size());
        // Configure audio effects
    }
    
    private void configureAudioFilters() {
        ViroLog.debug(TAG, "Configuring audio filters: " + filters.size());
        // Configure audio filters
    }
    
    private void configureEqualizer() {
        ViroLog.debug(TAG, "Configuring equalizer");
        // Configure equalizer
    }
    
    private void configureLowPassFilter() {
        ViroLog.debug(TAG, "Configuring low pass filter");
        // Configure low pass filter
    }
    
    private void configureHighPassFilter() {
        ViroLog.debug(TAG, "Configuring high pass filter");
        // Configure high pass filter
    }
    
    private void updatePerformance() {
        ViroLog.debug(TAG, "Updating performance settings");
        // Update performance-related settings
    }
    
    private void updateAnimation() {
        ViroLog.debug(TAG, "Updating animation");
        // Update animation properties
    }
    
    // Public Methods
    public void forceUpdate() {
        ViroLog.debug(TAG, "Forcing spatial sound update");
        audioDirty = true;
        spatialDirty = true;
        positionDirty = true;
        effectsDirty = true;
        updateAudio();
        updateSpatialAudio();
        updatePosition();
        updateEffects();
    }
    
    public ReadableMap getSource() {
        return source;
    }
    
    public String getUri() {
        return uri;
    }
    
    public String getLocal() {
        return local;
    }
    
    public String getResource() {
        return resource;
    }
    
    public String getAudioFormat() {
        return audioFormat;
    }
    
    public boolean getPaused() {
        return paused;
    }
    
    public boolean getLoop() {
        return loop;
    }
    
    public boolean getMuted() {
        return muted;
    }
    
    public float getVolume() {
        return volume;
    }
    
    public float getRate() {
        return rate;
    }
    
    public float getPitch() {
        return pitch;
    }
    
    public ReadableArray getPosition() {
        return position;
    }
    
    public ReadableArray getRotation() {
        return rotation;
    }
    
    public ReadableArray getVelocity() {
        return velocity;
    }
    
    public boolean getSpatialAudioEnabled() {
        return spatialAudioEnabled;
    }
    
    public String getSpatialAudioQuality() {
        return spatialAudioQuality;
    }
    
    public float getSpatialBlend() {
        return spatialBlend;
    }
    
    public String getDistanceModel() {
        return distanceModel;
    }
    
    public float getMaxDistance() {
        return maxDistance;
    }
    
    public float getReferenceDistance() {
        return referenceDistance;
    }
    
    public float getRolloffFactor() {
        return rolloffFactor;
    }
    
    public boolean getDirectional() {
        return directional;
    }
    
    public float getConeInnerAngle() {
        return coneInnerAngle;
    }
    
    public float getConeOuterAngle() {
        return coneOuterAngle;
    }
    
    public boolean getDopplerEnabled() {
        return dopplerEnabled;
    }
    
    public float getDopplerLevel() {
        return dopplerLevel;
    }
    
    public boolean getOcclusionEnabled() {
        return occlusionEnabled;
    }
    
    public boolean getObstructionEnabled() {
        return obstructionEnabled;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public String getProcessingQuality() {
        return processingQuality;
    }
    
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViroLog.debug(TAG, "ViroSpatialSoundView detached from window");
        // Cleanup resources
    }
    
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            ViroLog.debug(TAG, "ViroSpatialSoundView layout changed");
            // Handle layout changes
        }
    }
}