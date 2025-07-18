//
//  ViroParticleEmitterComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * ViroParticleEmitterComponentView - Particle System Component
 * 
 * This component creates and manages particle systems for ViroReact applications.
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
@interface ViroParticleEmitterComponentView : RCTViewComponentView

// Particle emission
- (void)setEmissionRate:(CGFloat)emissionRate;
- (void)setBurstCount:(NSInteger)burstCount;
- (void)setDuration:(CGFloat)duration;
- (void)setDelay:(CGFloat)delay;
- (void)setLooping:(BOOL)looping;
- (void)setPrewarm:(BOOL)prewarm;
- (void)setMaxParticles:(NSInteger)maxParticles;

// Particle appearance
- (void)setImage:(nullable NSDictionary *)image;
- (void)setColor:(nullable NSArray<NSNumber *> *)color;
- (void)setColorVariation:(nullable NSArray<NSNumber *> *)colorVariation;
- (void)setOpacity:(CGFloat)opacity;
- (void)setOpacityVariation:(CGFloat)opacityVariation;
- (void)setSize:(CGFloat)size;
- (void)setSizeVariation:(CGFloat)sizeVariation;
- (void)setRotation:(CGFloat)rotation;
- (void)setRotationVariation:(CGFloat)rotationVariation;

// Particle physics
- (void)setVelocity:(nullable NSArray<NSNumber *> *)velocity;
- (void)setVelocityVariation:(nullable NSArray<NSNumber *> *)velocityVariation;
- (void)setAcceleration:(nullable NSArray<NSNumber *> *)acceleration;
- (void)setGravity:(nullable NSArray<NSNumber *> *)gravity;
- (void)setDamping:(CGFloat)damping;
- (void)setAngularVelocity:(CGFloat)angularVelocity;
- (void)setAngularAcceleration:(CGFloat)angularAcceleration;

// Particle lifecycle
- (void)setLifetime:(CGFloat)lifetime;
- (void)setLifetimeVariation:(CGFloat)lifetimeVariation;
- (void)setStartSize:(CGFloat)startSize;
- (void)setEndSize:(CGFloat)endSize;
- (void)setStartColor:(nullable NSArray<NSNumber *> *)startColor;
- (void)setEndColor:(nullable NSArray<NSNumber *> *)endColor;
- (void)setStartOpacity:(CGFloat)startOpacity;
- (void)setEndOpacity:(CGFloat)endOpacity;

// Emission shape
- (void)setEmissionShape:(nullable NSString *)emissionShape;
- (void)setEmissionRadius:(CGFloat)emissionRadius;
- (void)setEmissionAngle:(CGFloat)emissionAngle;
- (void)setEmissionWidth:(CGFloat)emissionWidth;
- (void)setEmissionHeight:(CGFloat)emissionHeight;
- (void)setEmissionDepth:(CGFloat)emissionDepth;

// Particle behavior
- (void)setBlendMode:(nullable NSString *)blendMode;
- (void)setSortingMode:(nullable NSString *)sortingMode;
- (void)setAlignment:(nullable NSString *)alignment;
- (void)setStretchWithVelocity:(BOOL)stretchWithVelocity;
- (void)setSpawnBehavior:(nullable NSString *)spawnBehavior;
- (void)setFixedTimeStep:(CGFloat)fixedTimeStep;

// Particle forces
- (void)setAttractors:(nullable NSArray<NSDictionary *> *)attractors;
- (void)setRepulsors:(nullable NSArray<NSDictionary *> *)repulsors;
- (void)setTurbulence:(nullable NSDictionary *)turbulence;
- (void)setWind:(nullable NSArray<NSNumber *> *)wind;
- (void)setMagneticField:(nullable NSArray<NSNumber *> *)magneticField;

// Animation
- (void)setAnimationFrames:(nullable NSArray<NSDictionary *> *)animationFrames;
- (void)setAnimationSpeed:(CGFloat)animationSpeed;
- (void)setAnimationLooping:(BOOL)animationLooping;
- (void)setAnimationRandomStart:(BOOL)animationRandomStart;

// Collision
- (void)setCollisionEnabled:(BOOL)collisionEnabled;
- (void)setCollisionBounce:(CGFloat)collisionBounce;
- (void)setCollisionDamping:(CGFloat)collisionDamping;
- (void)setCollisionLifetimeLoss:(CGFloat)collisionLifetimeLoss;
- (void)setCollisionPlanes:(nullable NSArray<NSDictionary *> *)collisionPlanes;

// Performance
- (void)setCullingEnabled:(BOOL)cullingEnabled;
- (void)setCullingDistance:(CGFloat)cullingDistance;
- (void)setLevelOfDetail:(nullable NSString *)levelOfDetail;
- (void)setUpdateMode:(nullable NSString *)updateMode;

// Event callbacks
- (void)setOnParticleSpawn:(nullable RCTBubblingEventBlock)onParticleSpawn;
- (void)setOnParticleDeath:(nullable RCTBubblingEventBlock)onParticleDeath;
- (void)setOnParticleCollision:(nullable RCTBubblingEventBlock)onParticleCollision;
- (void)setOnSystemStart:(nullable RCTBubblingEventBlock)onSystemStart;
- (void)setOnSystemStop:(nullable RCTBubblingEventBlock)onSystemStop;
- (void)setOnSystemComplete:(nullable RCTBubblingEventBlock)onSystemComplete;

// Particle system control methods
- (void)startEmission;
- (void)stopEmission;
- (void)pauseEmission;
- (void)resumeEmission;
- (void)resetSystem;
- (void)burst:(NSInteger)count;
- (void)setEmissionEnabled:(BOOL)enabled;

// Particle system state information
- (BOOL)isEmitting;
- (BOOL)isPaused;
- (NSInteger)getActiveParticleCount;
- (NSInteger)getTotalParticleCount;
- (CGFloat)getSystemAge;
- (NSDictionary *)getSystemInfo;

// Particle system utilities
- (void)setParticleProperty:(NSString *)propertyName value:(nullable id)value forParticle:(NSInteger)particleId;
- (nullable id)getParticleProperty:(NSString *)propertyName forParticle:(NSInteger)particleId;
- (void)applyForceToParticle:(NSArray<NSNumber *> *)force particleId:(NSInteger)particleId;
- (void)applyForceToAllParticles:(NSArray<NSNumber *> *)force;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END