//
//  ViroDirectionalLightComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroDirectionalLightComponentView.h"
#import <React/RCTConversions.h>
#import <React/RCTFabricComponentsPlugins.h>
#import <React/RCTLog.h>
#import <React/RCTUtils.h>

@interface ViroDirectionalLightComponentView ()

// Light color and intensity
@property (nonatomic, strong, nullable) NSString *color;
@property (nonatomic, assign) CGFloat intensity;
@property (nonatomic, assign) CGFloat temperature;

// Light direction
@property (nonatomic, strong, nullable) NSArray<NSNumber *> *direction;

// Shadow properties
@property (nonatomic, assign) BOOL castsShadow;
@property (nonatomic, assign) CGFloat shadowOpacity;
@property (nonatomic, assign) NSInteger shadowMapSize;
@property (nonatomic, assign) CGFloat shadowBias;
@property (nonatomic, assign) CGFloat shadowNearZ;
@property (nonatomic, assign) CGFloat shadowFarZ;

// Light influence
@property (nonatomic, assign) NSInteger influenceBitMask;

@end

@implementation ViroDirectionalLightComponentView

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    // TODO: Return proper component descriptor for ViroDirectionalLight
    return nullptr;
}

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        [self commonInit];
    }
    return self;
}

- (void)commonInit
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Initializing");
    
    // Set default directional light values
    _color = @"#FFFFFF";
    _intensity = 1000.0; // Lux
    _temperature = 6500.0; // Kelvin (daylight)
    _direction = @[@0, @-1, @0]; // Pointing downward (like sunlight)
    
    // Default shadow settings
    _castsShadow = YES;
    _shadowOpacity = 0.3;
    _shadowMapSize = 1024;
    _shadowBias = 0.001;
    _shadowNearZ = 1.0;
    _shadowFarZ = 100.0;
    
    _influenceBitMask = 1; // Default influence mask
    
    // TODO: Initialize ViroReact directional light
    // This will need to integrate with the existing ViroReact lighting system
    
    self.backgroundColor = [UIColor clearColor];
    self.clipsToBounds = NO; // Lights don't have visual bounds
}

#pragma mark - Light Color and Intensity

- (void)setColor:(nullable NSString *)color
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Setting color: %@", color);
    _color = color ?: @"#FFFFFF";
    
    // TODO: Update directional light color in ViroReact renderer
    [self updateDirectionalLight];
}

- (void)setIntensity:(CGFloat)intensity
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Setting intensity: %f", intensity);
    _intensity = intensity;
    
    // TODO: Update directional light intensity in ViroReact renderer
    [self updateDirectionalLight];
}

- (void)setTemperature:(CGFloat)temperature
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Setting temperature: %f", temperature);
    _temperature = temperature;
    
    // TODO: Update directional light temperature in ViroReact renderer
    // Temperature affects the color tint (warm/cool)
    [self updateDirectionalLight];
}

#pragma mark - Light Direction

- (void)setDirection:(nullable NSArray<NSNumber *> *)direction
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Setting direction: %@", direction);
    _direction = direction ?: @[@0, @-1, @0];
    
    // TODO: Update directional light direction in ViroReact renderer
    [self updateDirectionalLight];
}

#pragma mark - Shadow Properties

- (void)setCastsShadow:(BOOL)castsShadow
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Setting casts shadow: %@", castsShadow ? @"YES" : @"NO");
    _castsShadow = castsShadow;
    
    // TODO: Update shadow casting in ViroReact renderer
    [self updateDirectionalLight];
}

- (void)setShadowOpacity:(CGFloat)shadowOpacity
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Setting shadow opacity: %f", shadowOpacity);
    _shadowOpacity = shadowOpacity;
    
    // TODO: Update shadow opacity in ViroReact renderer
    [self updateDirectionalLight];
}

- (void)setShadowMapSize:(NSInteger)shadowMapSize
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Setting shadow map size: %ld", (long)shadowMapSize);
    _shadowMapSize = shadowMapSize;
    
    // TODO: Update shadow map resolution in ViroReact renderer
    // Common sizes: 512, 1024, 2048, 4096
    [self updateDirectionalLight];
}

- (void)setShadowBias:(CGFloat)shadowBias
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Setting shadow bias: %f", shadowBias);
    _shadowBias = shadowBias;
    
    // TODO: Update shadow bias in ViroReact renderer
    // Bias helps prevent shadow acne
    [self updateDirectionalLight];
}

- (void)setShadowNearZ:(CGFloat)shadowNearZ
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Setting shadow near Z: %f", shadowNearZ);
    _shadowNearZ = shadowNearZ;
    
    // TODO: Update shadow camera near plane in ViroReact renderer
    [self updateDirectionalLight];
}

- (void)setShadowFarZ:(CGFloat)shadowFarZ
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Setting shadow far Z: %f", shadowFarZ);
    _shadowFarZ = shadowFarZ;
    
    // TODO: Update shadow camera far plane in ViroReact renderer
    [self updateDirectionalLight];
}

#pragma mark - Light Influence

- (void)setInfluenceBitMask:(NSInteger)influenceBitMask
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Setting influence bit mask: %ld", (long)influenceBitMask);
    _influenceBitMask = influenceBitMask;
    
    // TODO: Update directional light influence in ViroReact renderer
    [self updateDirectionalLight];
}

#pragma mark - Light Update

- (void)updateDirectionalLight
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Updating directional light - Color: %@, Intensity: %.1f, Direction: %@, Shadows: %@", 
               _color, _intensity, _direction, _castsShadow ? @"YES" : @"NO");
    
    // TODO: Apply directional light settings to ViroReact renderer
    // Directional light simulates parallel light rays (like sunlight)
    // It has direction but no position - affects all objects equally regardless of distance
    // Can cast shadows using shadow mapping techniques
}

#pragma mark - Helper Methods

- (UIColor *)colorFromHexString:(NSString *)hexString
{
    // Convert hex color string to UIColor
    NSString *cleanString = [hexString stringByReplacingOccurrencesOfString:@"#" withString:@""];
    if ([cleanString length] == 6) {
        unsigned int rgb;
        NSScanner *scanner = [NSScanner scannerWithString:cleanString];
        [scanner scanHexInt:&rgb];
        
        CGFloat red = ((rgb & 0xFF0000) >> 16) / 255.0;
        CGFloat green = ((rgb & 0x00FF00) >> 8) / 255.0;
        CGFloat blue = (rgb & 0x0000FF) / 255.0;
        
        return [UIColor colorWithRed:red green:green blue:blue alpha:1.0];
    }
    
    return [UIColor whiteColor]; // Default fallback
}

- (NSArray<NSNumber *> *)normalizedDirection
{
    // Ensure direction vector is normalized
    if (_direction.count >= 3) {
        CGFloat x = [_direction[0] doubleValue];
        CGFloat y = [_direction[1] doubleValue];
        CGFloat z = [_direction[2] doubleValue];
        
        CGFloat length = sqrt(x*x + y*y + z*z);
        if (length > 0) {
            return @[@(x/length), @(y/length), @(z/length)];
        }
    }
    
    return @[@0, @-1, @0]; // Default downward direction
}

#pragma mark - Layout

- (void)layoutSubviews
{
    [super layoutSubviews];
    
    // Directional lights don't have visual layout, but we can log for debugging
    RCTLogInfo(@"[ViroDirectionalLightComponentView] layoutSubviews: %@", NSStringFromCGRect(self.bounds));
}

#pragma mark - Lifecycle

- (void)didMoveToWindow
{
    [super didMoveToWindow];
    
    if (self.window) {
        RCTLogInfo(@"[ViroDirectionalLightComponentView] Directional light added to window");
        // TODO: Add directional light to ViroReact scene when added to window
        [self updateDirectionalLight];
    } else {
        RCTLogInfo(@"[ViroDirectionalLightComponentView] Directional light removed from window");
        // TODO: Remove directional light from ViroReact scene when removed from window
    }
}

- (void)dealloc
{
    RCTLogInfo(@"[ViroDirectionalLightComponentView] Deallocating");
    // TODO: Clean up ViroReact directional light resources
}

@end

Class<RCTComponentViewProtocol> ViroDirectionalLightCls(void)
{
    return ViroDirectionalLightComponentView.class;
}