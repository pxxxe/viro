//
//  ViroOmniLightComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroOmniLightComponentView : RCTViewComponentView

// Light color and intensity
- (void)setColor:(nullable NSString *)color;
- (void)setIntensity:(CGFloat)intensity;
- (void)setTemperature:(CGFloat)temperature;

// Light position
- (void)setPosition:(nullable NSArray<NSNumber *> *)position;

// Light attenuation
- (void)setAttenuationStartDistance:(CGFloat)attenuationStartDistance;
- (void)setAttenuationEndDistance:(CGFloat)attenuationEndDistance;

// Light influence
- (void)setInfluenceBitMask:(NSInteger)influenceBitMask;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END