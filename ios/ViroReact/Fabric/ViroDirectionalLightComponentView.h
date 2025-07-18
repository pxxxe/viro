//
//  ViroDirectionalLightComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroDirectionalLightComponentView : RCTViewComponentView

// Light color and intensity
- (void)setColor:(nullable NSString *)color;
- (void)setIntensity:(CGFloat)intensity;
- (void)setTemperature:(CGFloat)temperature;

// Light direction
- (void)setDirection:(nullable NSArray<NSNumber *> *)direction;

// Shadow properties
- (void)setCastsShadow:(BOOL)castsShadow;
- (void)setShadowOpacity:(CGFloat)shadowOpacity;
- (void)setShadowMapSize:(NSInteger)shadowMapSize;
- (void)setShadowBias:(CGFloat)shadowBias;
- (void)setShadowNearZ:(CGFloat)shadowNearZ;
- (void)setShadowFarZ:(CGFloat)shadowFarZ;

// Light influence
- (void)setInfluenceBitMask:(NSInteger)influenceBitMask;

@end

NS_ASSUME_NONNULL_END