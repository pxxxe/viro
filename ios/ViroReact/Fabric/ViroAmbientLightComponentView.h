//
//  ViroAmbientLightComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroAmbientLightComponentView : RCTViewComponentView

// Light color and intensity
- (void)setColor:(nullable NSString *)color;
- (void)setIntensity:(CGFloat)intensity;
- (void)setTemperature:(CGFloat)temperature;

// Light influence
- (void)setInfluenceBitMask:(NSInteger)influenceBitMask;

@end

NS_ASSUME_NONNULL_END