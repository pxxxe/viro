//
//  ViroParticleEmitterViewManager.java
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
 * ViroParticleEmitterViewManager - Particle System ViewManager
 * 
 * This ViewManager handles particle systems for ViroReact applications.
 * It provides realistic particle effects including fire, smoke, explosions, rain,
 * snow, and other environmental and visual effects.
 * 
 * Key Features:
 * - Particle emission control (rate, burst, duration)
 * - Particle lifecycle management (spawn, update, death)
 * - Physics simulation (gravity, velocity, acceleration)
 * - Visual properties (color, size, opacity, texture)
 * - Emission shapes (point, sphere, box, cone, circle)
 * - Particle behaviors (attractors, repulsors, turbulence)
 * - Animation and keyframe interpolation
 * - Collision detection and response
 * - Performance optimization with culling
 * - Integration with ViroReact scene graph
 */
public class ViroParticleEmitterViewManager extends SimpleViewManager<ViroParticleEmitterView> {
    
    private static final String TAG = ViroLog.getTag(ViroParticleEmitterViewManager.class);
    private static final String REACT_CLASS = "ViroParticleEmitter";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroParticleEmitterView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroParticleEmitterView instance");
        return new ViroParticleEmitterView(reactContext);
    }
    
    // Particle Emission
    @ReactProp(name = "emissionRate", defaultFloat = 100.0f)
    public void setEmissionRate(ViroParticleEmitterView view, float emissionRate) {
        ViroLog.debug(TAG, "Setting emission rate: " + emissionRate);
        view.setEmissionRate(emissionRate);
    }
    
    @ReactProp(name = "burstCount", defaultInt = 0)
    public void setBurstCount(ViroParticleEmitterView view, int burstCount) {
        ViroLog.debug(TAG, "Setting burst count: " + burstCount);
        view.setBurstCount(burstCount);
    }
    
    @ReactProp(name = "duration", defaultFloat = 0.0f)
    public void setDuration(ViroParticleEmitterView view, float duration) {
        ViroLog.debug(TAG, "Setting duration: " + duration);
        view.setDuration(duration);
    }
    
    @ReactProp(name = "delay", defaultFloat = 0.0f)
    public void setDelay(ViroParticleEmitterView view, float delay) {
        ViroLog.debug(TAG, "Setting delay: " + delay);
        view.setDelay(delay);
    }
    
    @ReactProp(name = "looping", defaultBoolean = true)
    public void setLooping(ViroParticleEmitterView view, boolean looping) {
        ViroLog.debug(TAG, "Setting looping: " + looping);
        view.setLooping(looping);
    }
    
    @ReactProp(name = "prewarm", defaultBoolean = false)
    public void setPrewarm(ViroParticleEmitterView view, boolean prewarm) {
        ViroLog.debug(TAG, "Setting prewarm: " + prewarm);
        view.setPrewarm(prewarm);
    }
    
    @ReactProp(name = "maxParticles", defaultInt = 1000)
    public void setMaxParticles(ViroParticleEmitterView view, int maxParticles) {
        ViroLog.debug(TAG, "Setting max particles: " + maxParticles);
        view.setMaxParticles(maxParticles);
    }
    
    // Particle Appearance
    @ReactProp(name = "image")
    public void setImage(ViroParticleEmitterView view, @Nullable ReadableMap image) {
        ViroLog.debug(TAG, "Setting image: " + image);
        view.setImage(image);
    }
    
    @ReactProp(name = "color")
    public void setColor(ViroParticleEmitterView view, @Nullable ReadableArray color) {
        ViroLog.debug(TAG, "Setting color: " + color);
        view.setColor(color);
    }
    
    @ReactProp(name = "colorVariation")
    public void setColorVariation(ViroParticleEmitterView view, @Nullable ReadableArray colorVariation) {
        ViroLog.debug(TAG, "Setting color variation: " + colorVariation);
        view.setColorVariation(colorVariation);
    }
    
    @ReactProp(name = "opacity", defaultFloat = 1.0f)
    public void setOpacity(ViroParticleEmitterView view, float opacity) {
        ViroLog.debug(TAG, "Setting opacity: " + opacity);
        view.setOpacity(opacity);
    }
    
    @ReactProp(name = "opacityVariation", defaultFloat = 0.0f)
    public void setOpacityVariation(ViroParticleEmitterView view, float opacityVariation) {
        ViroLog.debug(TAG, "Setting opacity variation: " + opacityVariation);
        view.setOpacityVariation(opacityVariation);
    }
    
    @ReactProp(name = "size", defaultFloat = 1.0f)
    public void setSize(ViroParticleEmitterView view, float size) {
        ViroLog.debug(TAG, "Setting size: " + size);
        view.setSize(size);
    }
    
    @ReactProp(name = "sizeVariation", defaultFloat = 0.0f)
    public void setSizeVariation(ViroParticleEmitterView view, float sizeVariation) {
        ViroLog.debug(TAG, "Setting size variation: " + sizeVariation);
        view.setSizeVariation(sizeVariation);
    }
    
    @ReactProp(name = "rotation", defaultFloat = 0.0f)
    public void setRotation(ViroParticleEmitterView view, float rotation) {
        ViroLog.debug(TAG, "Setting rotation: " + rotation);
        view.setRotation(rotation);
    }
    
    @ReactProp(name = "rotationVariation", defaultFloat = 0.0f)
    public void setRotationVariation(ViroParticleEmitterView view, float rotationVariation) {
        ViroLog.debug(TAG, "Setting rotation variation: " + rotationVariation);
        view.setRotationVariation(rotationVariation);
    }
    
    // Particle Physics
    @ReactProp(name = "velocity")
    public void setVelocity(ViroParticleEmitterView view, @Nullable ReadableArray velocity) {
        ViroLog.debug(TAG, "Setting velocity: " + velocity);
        view.setVelocity(velocity);
    }
    
    @ReactProp(name = "velocityVariation")
    public void setVelocityVariation(ViroParticleEmitterView view, @Nullable ReadableArray velocityVariation) {
        ViroLog.debug(TAG, "Setting velocity variation: " + velocityVariation);
        view.setVelocityVariation(velocityVariation);
    }
    
    @ReactProp(name = "acceleration")
    public void setAcceleration(ViroParticleEmitterView view, @Nullable ReadableArray acceleration) {
        ViroLog.debug(TAG, "Setting acceleration: " + acceleration);
        view.setAcceleration(acceleration);
    }
    
    @ReactProp(name = "gravity")
    public void setGravity(ViroParticleEmitterView view, @Nullable ReadableArray gravity) {
        ViroLog.debug(TAG, "Setting gravity: " + gravity);
        view.setGravity(gravity);
    }
    
    @ReactProp(name = "damping", defaultFloat = 0.0f)
    public void setDamping(ViroParticleEmitterView view, float damping) {
        ViroLog.debug(TAG, "Setting damping: " + damping);
        view.setDamping(damping);
    }
    
    @ReactProp(name = "angularVelocity", defaultFloat = 0.0f)
    public void setAngularVelocity(ViroParticleEmitterView view, float angularVelocity) {
        ViroLog.debug(TAG, "Setting angular velocity: " + angularVelocity);
        view.setAngularVelocity(angularVelocity);
    }
    
    @ReactProp(name = "angularAcceleration", defaultFloat = 0.0f)
    public void setAngularAcceleration(ViroParticleEmitterView view, float angularAcceleration) {
        ViroLog.debug(TAG, "Setting angular acceleration: " + angularAcceleration);
        view.setAngularAcceleration(angularAcceleration);
    }
    
    // Particle Lifecycle
    @ReactProp(name = "lifetime", defaultFloat = 5.0f)
    public void setLifetime(ViroParticleEmitterView view, float lifetime) {
        ViroLog.debug(TAG, "Setting lifetime: " + lifetime);
        view.setLifetime(lifetime);
    }
    
    @ReactProp(name = "lifetimeVariation", defaultFloat = 0.0f)
    public void setLifetimeVariation(ViroParticleEmitterView view, float lifetimeVariation) {
        ViroLog.debug(TAG, "Setting lifetime variation: " + lifetimeVariation);
        view.setLifetimeVariation(lifetimeVariation);
    }
    
    @ReactProp(name = "startSize", defaultFloat = 1.0f)
    public void setStartSize(ViroParticleEmitterView view, float startSize) {
        ViroLog.debug(TAG, "Setting start size: " + startSize);
        view.setStartSize(startSize);
    }
    
    @ReactProp(name = "endSize", defaultFloat = 0.0f)
    public void setEndSize(ViroParticleEmitterView view, float endSize) {
        ViroLog.debug(TAG, "Setting end size: " + endSize);
        view.setEndSize(endSize);
    }
    
    @ReactProp(name = "startColor")
    public void setStartColor(ViroParticleEmitterView view, @Nullable ReadableArray startColor) {
        ViroLog.debug(TAG, "Setting start color: " + startColor);
        view.setStartColor(startColor);
    }
    
    @ReactProp(name = "endColor")
    public void setEndColor(ViroParticleEmitterView view, @Nullable ReadableArray endColor) {
        ViroLog.debug(TAG, "Setting end color: " + endColor);
        view.setEndColor(endColor);
    }
    
    @ReactProp(name = "startOpacity", defaultFloat = 1.0f)
    public void setStartOpacity(ViroParticleEmitterView view, float startOpacity) {
        ViroLog.debug(TAG, "Setting start opacity: " + startOpacity);
        view.setStartOpacity(startOpacity);
    }
    
    @ReactProp(name = "endOpacity", defaultFloat = 0.0f)
    public void setEndOpacity(ViroParticleEmitterView view, float endOpacity) {
        ViroLog.debug(TAG, "Setting end opacity: " + endOpacity);
        view.setEndOpacity(endOpacity);
    }
    
    // Emission Shape
    @ReactProp(name = "emissionShape")
    public void setEmissionShape(ViroParticleEmitterView view, @Nullable String emissionShape) {
        ViroLog.debug(TAG, "Setting emission shape: " + emissionShape);
        view.setEmissionShape(emissionShape);
    }
    
    @ReactProp(name = "emissionRadius", defaultFloat = 1.0f)
    public void setEmissionRadius(ViroParticleEmitterView view, float emissionRadius) {
        ViroLog.debug(TAG, "Setting emission radius: " + emissionRadius);
        view.setEmissionRadius(emissionRadius);
    }
    
    @ReactProp(name = "emissionAngle", defaultFloat = 0.0f)
    public void setEmissionAngle(ViroParticleEmitterView view, float emissionAngle) {
        ViroLog.debug(TAG, "Setting emission angle: " + emissionAngle);
        view.setEmissionAngle(emissionAngle);
    }
    
    @ReactProp(name = "emissionWidth", defaultFloat = 1.0f)
    public void setEmissionWidth(ViroParticleEmitterView view, float emissionWidth) {
        ViroLog.debug(TAG, "Setting emission width: " + emissionWidth);
        view.setEmissionWidth(emissionWidth);
    }
    
    @ReactProp(name = "emissionHeight", defaultFloat = 1.0f)
    public void setEmissionHeight(ViroParticleEmitterView view, float emissionHeight) {
        ViroLog.debug(TAG, "Setting emission height: " + emissionHeight);
        view.setEmissionHeight(emissionHeight);
    }
    
    @ReactProp(name = "emissionDepth", defaultFloat = 1.0f)
    public void setEmissionDepth(ViroParticleEmitterView view, float emissionDepth) {
        ViroLog.debug(TAG, "Setting emission depth: " + emissionDepth);
        view.setEmissionDepth(emissionDepth);
    }
    
    // Particle Behavior
    @ReactProp(name = "blendMode")
    public void setBlendMode(ViroParticleEmitterView view, @Nullable String blendMode) {
        ViroLog.debug(TAG, "Setting blend mode: " + blendMode);
        view.setBlendMode(blendMode);
    }
    
    @ReactProp(name = "sortingMode")
    public void setSortingMode(ViroParticleEmitterView view, @Nullable String sortingMode) {
        ViroLog.debug(TAG, "Setting sorting mode: " + sortingMode);
        view.setSortingMode(sortingMode);
    }
    
    @ReactProp(name = "alignment")
    public void setAlignment(ViroParticleEmitterView view, @Nullable String alignment) {
        ViroLog.debug(TAG, "Setting alignment: " + alignment);
        view.setAlignment(alignment);
    }
    
    @ReactProp(name = "stretchWithVelocity", defaultBoolean = false)
    public void setStretchWithVelocity(ViroParticleEmitterView view, boolean stretchWithVelocity) {
        ViroLog.debug(TAG, "Setting stretch with velocity: " + stretchWithVelocity);
        view.setStretchWithVelocity(stretchWithVelocity);
    }
    
    @ReactProp(name = "spawnBehavior")
    public void setSpawnBehavior(ViroParticleEmitterView view, @Nullable String spawnBehavior) {
        ViroLog.debug(TAG, "Setting spawn behavior: " + spawnBehavior);
        view.setSpawnBehavior(spawnBehavior);
    }
    
    @ReactProp(name = "fixedTimeStep", defaultFloat = 0.0166f)
    public void setFixedTimeStep(ViroParticleEmitterView view, float fixedTimeStep) {
        ViroLog.debug(TAG, "Setting fixed time step: " + fixedTimeStep);
        view.setFixedTimeStep(fixedTimeStep);
    }
    
    // Particle Forces
    @ReactProp(name = "attractors")
    public void setAttractors(ViroParticleEmitterView view, @Nullable ReadableArray attractors) {
        ViroLog.debug(TAG, "Setting attractors: " + attractors);
        view.setAttractors(attractors);
    }
    
    @ReactProp(name = "repulsors")
    public void setRepulsors(ViroParticleEmitterView view, @Nullable ReadableArray repulsors) {
        ViroLog.debug(TAG, "Setting repulsors: " + repulsors);
        view.setRepulsors(repulsors);
    }
    
    @ReactProp(name = "turbulence")
    public void setTurbulence(ViroParticleEmitterView view, @Nullable ReadableMap turbulence) {
        ViroLog.debug(TAG, "Setting turbulence: " + turbulence);
        view.setTurbulence(turbulence);
    }
    
    @ReactProp(name = "wind")
    public void setWind(ViroParticleEmitterView view, @Nullable ReadableArray wind) {
        ViroLog.debug(TAG, "Setting wind: " + wind);
        view.setWind(wind);
    }
    
    @ReactProp(name = "magneticField")
    public void setMagneticField(ViroParticleEmitterView view, @Nullable ReadableArray magneticField) {
        ViroLog.debug(TAG, "Setting magnetic field: " + magneticField);
        view.setMagneticField(magneticField);
    }
    
    // Animation
    @ReactProp(name = "animationFrames")
    public void setAnimationFrames(ViroParticleEmitterView view, @Nullable ReadableArray animationFrames) {
        ViroLog.debug(TAG, "Setting animation frames: " + animationFrames);
        view.setAnimationFrames(animationFrames);
    }
    
    @ReactProp(name = "animationSpeed", defaultFloat = 1.0f)
    public void setAnimationSpeed(ViroParticleEmitterView view, float animationSpeed) {
        ViroLog.debug(TAG, "Setting animation speed: " + animationSpeed);
        view.setAnimationSpeed(animationSpeed);
    }
    
    @ReactProp(name = "animationLooping", defaultBoolean = true)
    public void setAnimationLooping(ViroParticleEmitterView view, boolean animationLooping) {
        ViroLog.debug(TAG, "Setting animation looping: " + animationLooping);
        view.setAnimationLooping(animationLooping);
    }
    
    @ReactProp(name = "animationRandomStart", defaultBoolean = false)
    public void setAnimationRandomStart(ViroParticleEmitterView view, boolean animationRandomStart) {
        ViroLog.debug(TAG, "Setting animation random start: " + animationRandomStart);
        view.setAnimationRandomStart(animationRandomStart);
    }
    
    // Collision
    @ReactProp(name = "collisionEnabled", defaultBoolean = false)
    public void setCollisionEnabled(ViroParticleEmitterView view, boolean collisionEnabled) {
        ViroLog.debug(TAG, "Setting collision enabled: " + collisionEnabled);
        view.setCollisionEnabled(collisionEnabled);
    }
    
    @ReactProp(name = "collisionBounce", defaultFloat = 0.8f)
    public void setCollisionBounce(ViroParticleEmitterView view, float collisionBounce) {
        ViroLog.debug(TAG, "Setting collision bounce: " + collisionBounce);
        view.setCollisionBounce(collisionBounce);
    }
    
    @ReactProp(name = "collisionDamping", defaultFloat = 0.5f)
    public void setCollisionDamping(ViroParticleEmitterView view, float collisionDamping) {
        ViroLog.debug(TAG, "Setting collision damping: " + collisionDamping);
        view.setCollisionDamping(collisionDamping);
    }
    
    @ReactProp(name = "collisionLifetimeLoss", defaultFloat = 0.1f)
    public void setCollisionLifetimeLoss(ViroParticleEmitterView view, float collisionLifetimeLoss) {
        ViroLog.debug(TAG, "Setting collision lifetime loss: " + collisionLifetimeLoss);
        view.setCollisionLifetimeLoss(collisionLifetimeLoss);
    }
    
    @ReactProp(name = "collisionPlanes")
    public void setCollisionPlanes(ViroParticleEmitterView view, @Nullable ReadableArray collisionPlanes) {
        ViroLog.debug(TAG, "Setting collision planes: " + collisionPlanes);
        view.setCollisionPlanes(collisionPlanes);
    }
    
    // Performance
    @ReactProp(name = "cullingEnabled", defaultBoolean = true)
    public void setCullingEnabled(ViroParticleEmitterView view, boolean cullingEnabled) {
        ViroLog.debug(TAG, "Setting culling enabled: " + cullingEnabled);
        view.setCullingEnabled(cullingEnabled);
    }
    
    @ReactProp(name = "cullingDistance", defaultFloat = 100.0f)
    public void setCullingDistance(ViroParticleEmitterView view, float cullingDistance) {
        ViroLog.debug(TAG, "Setting culling distance: " + cullingDistance);
        view.setCullingDistance(cullingDistance);
    }
    
    @ReactProp(name = "levelOfDetail")
    public void setLevelOfDetail(ViroParticleEmitterView view, @Nullable String levelOfDetail) {
        ViroLog.debug(TAG, "Setting level of detail: " + levelOfDetail);
        view.setLevelOfDetail(levelOfDetail);
    }
    
    @ReactProp(name = "updateMode")
    public void setUpdateMode(ViroParticleEmitterView view, @Nullable String updateMode) {
        ViroLog.debug(TAG, "Setting update mode: " + updateMode);
        view.setUpdateMode(updateMode);
    }
    
    // Event Exports
    @Override
    public java.util.Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return java.util.Map.of(
            "onParticleSpawn", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onParticleSpawn",
                    "captured", "onParticleSpawnCapture"
                )
            ),
            "onParticleDeath", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onParticleDeath",
                    "captured", "onParticleDeathCapture"
                )
            ),
            "onParticleCollision", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onParticleCollision",
                    "captured", "onParticleCollisionCapture"
                )
            ),
            "onSystemStart", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onSystemStart",
                    "captured", "onSystemStartCapture"
                )
            ),
            "onSystemStop", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onSystemStop",
                    "captured", "onSystemStopCapture"
                )
            ),
            "onSystemComplete", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onSystemComplete",
                    "captured", "onSystemCompleteCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroParticleEmitterView view) {
        ViroLog.debug(TAG, "Dropping ViroParticleEmitterView instance");
        super.onDropViewInstance(view);
    }
}