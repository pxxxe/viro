//
//  ViroNodeComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroNodeComponentView : RCTViewComponentView

// Transform methods
- (void)setPosition:(nullable NSArray<NSNumber *> *)position;
- (void)setScale:(nullable NSArray<NSNumber *> *)scale;
- (void)setRotation:(nullable NSArray<NSNumber *> *)rotation;
- (void)setRotationPivot:(nullable NSArray<NSNumber *> *)rotationPivot;
- (void)setScalePivot:(nullable NSArray<NSNumber *> *)scalePivot;
- (void)setTransformBehaviors:(nullable NSArray<NSString *> *)transformBehaviors;

// Visibility and interaction
- (void)setVisible:(BOOL)visible;
- (void)setOpacity:(CGFloat)opacity;
- (void)setRenderingOrder:(NSInteger)renderingOrder;
- (void)setIgnoreEventHandling:(BOOL)ignoreEventHandling;
- (void)setDragType:(nullable NSString *)dragType;

// Physics
- (void)setPhysicsBody:(nullable NSDictionary *)physicsBody;
- (void)setHighAccuracyEvents:(BOOL)highAccuracyEvents;

// Animation
- (void)setAnimation:(nullable NSDictionary *)animation;

// Event callbacks
- (void)setOnTransformUpdate:(nullable RCTBubblingEventBlock)onTransformUpdate;
- (void)setOnClick:(nullable RCTBubblingEventBlock)onClick;
- (void)setOnHover:(nullable RCTBubblingEventBlock)onHover;
- (void)setOnDrag:(nullable RCTBubblingEventBlock)onDrag;
- (void)setOnFuse:(nullable RCTBubblingEventBlock)onFuse;
- (void)setOnTouch:(nullable RCTBubblingEventBlock)onTouch;
- (void)setOnScroll:(nullable RCTBubblingEventBlock)onScroll;
- (void)setOnSwipe:(nullable RCTBubblingEventBlock)onSwipe;
- (void)setOnCollision:(nullable RCTBubblingEventBlock)onCollision;

@end

NS_ASSUME_NONNULL_END