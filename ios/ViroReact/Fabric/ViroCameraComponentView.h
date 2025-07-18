//
//  ViroCameraComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroCameraComponentView : RCTViewComponentView

// Camera position and orientation
- (void)setPosition:(nullable NSArray<NSNumber *> *)position;
- (void)setRotation:(nullable NSArray<NSNumber *> *)rotation;
- (void)setFieldOfView:(CGFloat)fieldOfView;

// Camera projection
- (void)setNearClippingPlane:(CGFloat)nearClippingPlane;
- (void)setFarClippingPlane:(CGFloat)farClippingPlane;
- (void)setProjectionType:(nullable NSString *)projectionType;
- (void)setFocalLength:(CGFloat)focalLength;

// Camera animation and controls
- (void)setAnimationDuration:(CGFloat)animationDuration;
- (void)setAnimationType:(nullable NSString *)animationType;

// Camera settings
- (void)setActive:(BOOL)active;

// Event callbacks
- (void)setOnTransformUpdate:(nullable RCTBubblingEventBlock)onTransformUpdate;
- (void)setOnCameraDidMount:(nullable RCTBubblingEventBlock)onCameraDidMount;
- (void)setOnCameraWillUnmount:(nullable RCTBubblingEventBlock)onCameraWillUnmount;

@end

NS_ASSUME_NONNULL_END