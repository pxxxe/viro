//
//  Viro3DObjectComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface Viro3DObjectComponentView : RCTViewComponentView

// 3D Model source properties
- (void)setSource:(nullable NSDictionary *)source;
- (void)setUri:(nullable NSString *)uri;
- (void)setType:(nullable NSString *)type;

// Model resources
- (void)setResources:(nullable NSArray<NSDictionary *> *)resources;

// Model appearance
- (void)setMaterials:(nullable NSArray<NSString *> *)materials;
- (void)setLightReceivingBitMask:(NSInteger)lightReceivingBitMask;
- (void)setShadowCastingBitMask:(NSInteger)shadowCastingBitMask;

// Model transformation
- (void)setScale:(nullable NSArray<NSNumber *> *)scale;
- (void)setRotation:(nullable NSArray<NSNumber *> *)rotation;
- (void)setPosition:(nullable NSArray<NSNumber *> *)position;
- (void)setPivot:(nullable NSArray<NSNumber *> *)pivot;

// Animation properties
- (void)setAnimation:(nullable NSDictionary *)animation;
- (void)setMorphTargets:(nullable NSArray<NSDictionary *> *)morphTargets;

// Loading configuration
- (void)setHighAccuracyEvents:(BOOL)highAccuracyEvents;
- (void)setIgnoreEventHandling:(BOOL)ignoreEventHandling;

// Events
- (void)setOnLoadStart:(nullable RCTBubblingEventBlock)onLoadStart;
- (void)setOnLoad:(nullable RCTBubblingEventBlock)onLoad;
- (void)setOnError:(nullable RCTBubblingEventBlock)onError;

// Animation control methods
- (void)playAnimation:(NSString *)animationName loop:(BOOL)loop;
- (void)pauseAnimation:(NSString *)animationName;
- (void)stopAnimation:(NSString *)animationName reset:(BOOL)reset;

// Morph target control
- (void)setMorphTargetWeight:(NSString *)targetName weight:(CGFloat)weight;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END