//
//  ViroSpotLightComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroSpotLightComponentView : RCTViewComponentView

// Light color and intensity
- (void)setColor:(nullable NSString *)color;
- (void)setIntensity:(CGFloat)intensity;
- (void)setTemperature:(CGFloat)temperature;

// Light position and direction
- (void)setPosition:(nullable NSArray<NSNumber *> *)position;
- (void)setDirection:(nullable NSArray<NSNumber *> *)direction;

// Spotlight cone properties
- (void)setInnerAngle:(CGFloat)innerAngle;
- (void)setOuterAngle:(CGFloat)outerAngle;

// Light attenuation
- (void)setAttenuationStartDistance:(CGFloat)attenuationStartDistance;
- (void)setAttenuationEndDistance:(CGFloat)attenuationEndDistance;

// Shadow properties
- (void)setCastsShadow:(BOOL)castsShadow;
- (void)setShadowOpacity:(CGFloat)shadowOpacity;
- (void)setShadowMapSize:(NSInteger)shadowMapSize;
- (void)setShadowBias:(CGFloat)shadowBias;
- (void)setShadowNearZ:(CGFloat)shadowNearZ;
- (void)setShadowFarZ:(CGFloat)shadowFarZ;

// Light influence
- (void)setInfluenceBitMask:(NSInteger)influenceBitMask;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END