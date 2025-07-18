//
//  ViroAnimatedComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroAnimatedComponentView : RCTViewComponentView

// Animation properties
- (void)setAnimation:(nullable NSDictionary *)animation;
- (void)setAnimationName:(nullable NSString *)animationName;
- (void)setLoop:(BOOL)loop;
- (void)setDelay:(NSTimeInterval)delay;
- (void)setDuration:(NSTimeInterval)duration;
- (void)setEasing:(nullable NSString *)easing;
- (void)setInterpolatorType:(nullable NSString *)interpolatorType;

// Animation control
- (void)setRun:(BOOL)run;
- (void)setPaused:(BOOL)paused;
- (void)setReverse:(BOOL)reverse;
- (void)setDirection:(nullable NSString *)direction;
- (void)setIterationCount:(NSInteger)iterationCount;

// Animation values
- (void)setFromValue:(nullable id)fromValue;
- (void)setToValue:(nullable id)toValue;
- (void)setValues:(nullable NSArray *)values;
- (void)setKeyTimes:(nullable NSArray<NSNumber *> *)keyTimes;

// Transform animations
- (void)setPositionFrom:(nullable NSArray<NSNumber *> *)positionFrom;
- (void)setPositionTo:(nullable NSArray<NSNumber *> *)positionTo;
- (void)setScaleFrom:(nullable NSArray<NSNumber *> *)scaleFrom;
- (void)setScaleTo:(nullable NSArray<NSNumber *> *)scaleTo;
- (void)setRotationFrom:(nullable NSArray<NSNumber *> *)rotationFrom;
- (void)setRotationTo:(nullable NSArray<NSNumber *> *)rotationTo;

// Opacity animations
- (void)setOpacityFrom:(nullable NSNumber *)opacityFrom;
- (void)setOpacityTo:(nullable NSNumber *)opacityTo;

// Color animations
- (void)setColorFrom:(nullable NSArray<NSNumber *> *)colorFrom;
- (void)setColorTo:(nullable NSArray<NSNumber *> *)colorTo;

// Material animations
- (void)setMaterialFrom:(nullable NSDictionary *)materialFrom;
- (void)setMaterialTo:(nullable NSDictionary *)materialTo;

// Physics animations
- (void)setPhysicsEnabled:(BOOL)physicsEnabled;
- (void)setVelocity:(nullable NSArray<NSNumber *> *)velocity;
- (void)setAcceleration:(nullable NSArray<NSNumber *> *)acceleration;

// Event callbacks
- (void)setOnStart:(nullable RCTBubblingEventBlock)onStart;
- (void)setOnFinish:(nullable RCTBubblingEventBlock)onFinish;
- (void)setOnUpdate:(nullable RCTBubblingEventBlock)onUpdate;
- (void)setOnCancel:(nullable RCTBubblingEventBlock)onCancel;

// Animation control methods
- (void)startAnimation;
- (void)pauseAnimation;
- (void)resumeAnimation;
- (void)stopAnimation;
- (void)resetAnimation;

// Animation state
- (BOOL)isAnimating;
- (BOOL)isPaused;
- (NSTimeInterval)currentTime;
- (float)progress;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END