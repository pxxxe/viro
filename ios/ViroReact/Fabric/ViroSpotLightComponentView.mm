//
//  ViroSpotLightComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroSpotLightComponentView.h"
#import <React/RCTConversions.h>
#import <React/RCTFabricComponentsPlugins.h>
#import <React/RCTLog.h>
#import <React/RCTUtils.h>

@interface ViroSpotLightComponentView ()

// Light color and intensity
@property (nonatomic, strong, nullable) NSString *color;
@property (nonatomic, assign) CGFloat intensity;
@property (nonatomic, assign) CGFloat temperature;

// Light position and direction
@property (nonatomic, strong, nullable) NSArray<NSNumber *> *position;
@property (nonatomic, strong, nullable) NSArray<NSNumber *> *direction;

// Spotlight cone properties
@property (nonatomic, assign) CGFloat innerAngle;
@property (nonatomic, assign) CGFloat outerAngle;

// Light attenuation
@property (nonatomic, assign) CGFloat attenuationStartDistance;
@property (nonatomic, assign) CGFloat attenuationEndDistance;

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

@implementation ViroSpotLightComponentView

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    // TODO: Return proper component descriptor for ViroSpotLight
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
    RCTLogInfo(@"[ViroSpotLightComponentView] Initializing");
    
    // Set default spot light values
    _color = @"#FFFFFF";
    _intensity = 1000.0; // Lumens
    _temperature = 6500.0; // Kelvin (daylight)
    _position = @[@0, @2, @0]; // Light source position (above origin)
    _direction = @[@0, @-1, @0]; // Pointing downward
    
    // Default spotlight cone settings
    _innerAngle = 30.0; // Degrees - full intensity cone
    _outerAngle = 45.0; // Degrees - falloff cone
    
    // Default attenuation settings
    _attenuationStartDistance = 2.0; // Distance where attenuation begins
    _attenuationEndDistance = 10.0; // Distance where light has no effect
    
    // Default shadow settings
    _castsShadow = YES;
    _shadowOpacity = 0.3;
    _shadowMapSize = 1024;
    _shadowBias = 0.001;
    _shadowNearZ = 1.0;
    _shadowFarZ = 100.0;
    
    _influenceBitMask = 1; // Default influence mask
    
    // TODO: Initialize ViroReact spot light
    // This will need to integrate with the existing ViroReact lighting system
    
    self.backgroundColor = [UIColor clearColor];
    self.clipsToBounds = NO; // Lights don't have visual bounds
}

#pragma mark - Light Color and Intensity

- (void)setColor:(nullable NSString *)color
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting color: %@", color);
    _color = color ?: @"#FFFFFF";
    
    // TODO: Update spot light color in ViroReact renderer
    [self updateSpotLight];
}

- (void)setIntensity:(CGFloat)intensity
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting intensity: %f", intensity);
    _intensity = intensity;
    
    // TODO: Update spot light intensity in ViroReact renderer
    [self updateSpotLight];
}

- (void)setTemperature:(CGFloat)temperature
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting temperature: %f", temperature);
    _temperature = temperature;
    
    // TODO: Update spot light temperature in ViroReact renderer
    // Temperature affects the color tint (warm/cool)
    [self updateSpotLight];
}

#pragma mark - Light Position and Direction

- (void)setPosition:(nullable NSArray<NSNumber *> *)position
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting position: %@", position);
    _position = position ?: @[@0, @2, @0];
    
    // TODO: Update spot light position in ViroReact renderer
    [self updateSpotLight];
}

- (void)setDirection:(nullable NSArray<NSNumber *> *)direction
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting direction: %@", direction);
    _direction = direction ?: @[@0, @-1, @0];
    
    // TODO: Update spot light direction in ViroReact renderer
    [self updateSpotLight];
}

#pragma mark - Spotlight Cone Properties

- (void)setInnerAngle:(CGFloat)innerAngle
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting inner angle: %f", innerAngle);
    _innerAngle = innerAngle;
    
    // TODO: Update spot light inner cone angle in ViroReact renderer
    // Inner angle defines the cone where light has full intensity
    [self updateSpotLight];
}

- (void)setOuterAngle:(CGFloat)outerAngle
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting outer angle: %f", outerAngle);
    _outerAngle = outerAngle;
    
    // TODO: Update spot light outer cone angle in ViroReact renderer
    // Outer angle defines the cone where light falls off to zero
    [self updateSpotLight];
}

#pragma mark - Light Attenuation

- (void)setAttenuationStartDistance:(CGFloat)attenuationStartDistance
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting attenuation start distance: %f", attenuationStartDistance);
    _attenuationStartDistance = attenuationStartDistance;
    
    // TODO: Update attenuation start distance in ViroReact renderer
    [self updateSpotLight];
}

- (void)setAttenuationEndDistance:(CGFloat)attenuationEndDistance
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting attenuation end distance: %f", attenuationEndDistance);
    _attenuationEndDistance = attenuationEndDistance;
    
    // TODO: Update attenuation end distance in ViroReact renderer
    [self updateSpotLight];
}

#pragma mark - Shadow Properties

- (void)setCastsShadow:(BOOL)castsShadow
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting casts shadow: %@", castsShadow ? @"YES" : @"NO");
    _castsShadow = castsShadow;
    
    // TODO: Update shadow casting in ViroReact renderer
    [self updateSpotLight];
}

- (void)setShadowOpacity:(CGFloat)shadowOpacity
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting shadow opacity: %f", shadowOpacity);
    _shadowOpacity = shadowOpacity;
    
    // TODO: Update shadow opacity in ViroReact renderer
    [self updateSpotLight];
}

- (void)setShadowMapSize:(NSInteger)shadowMapSize
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting shadow map size: %ld", (long)shadowMapSize);
    _shadowMapSize = shadowMapSize;
    
    // TODO: Update shadow map resolution in ViroReact renderer
    // Common sizes: 512, 1024, 2048, 4096
    [self updateSpotLight];
}

- (void)setShadowBias:(CGFloat)shadowBias
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting shadow bias: %f", shadowBias);
    _shadowBias = shadowBias;
    
    // TODO: Update shadow bias in ViroReact renderer
    // Bias helps prevent shadow acne
    [self updateSpotLight];
}

- (void)setShadowNearZ:(CGFloat)shadowNearZ
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting shadow near Z: %f", shadowNearZ);
    _shadowNearZ = shadowNearZ;
    
    // TODO: Update shadow camera near plane in ViroReact renderer
    [self updateSpotLight];
}

- (void)setShadowFarZ:(CGFloat)shadowFarZ
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting shadow far Z: %f", shadowFarZ);
    _shadowFarZ = shadowFarZ;
    
    // TODO: Update shadow camera far plane in ViroReact renderer
    [self updateSpotLight];
}

#pragma mark - Light Influence

- (void)setInfluenceBitMask:(NSInteger)influenceBitMask
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Setting influence bit mask: %ld", (long)influenceBitMask);
    _influenceBitMask = influenceBitMask;
    
    // TODO: Update spot light influence in ViroReact renderer
    [self updateSpotLight];
}

#pragma mark - Light Update

- (void)updateSpotLight
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Updating spot light - Color: %@, Intensity: %.1f, Position: %@, Direction: %@, Angles: %.1f°-%.1f°, Shadows: %@", 
               _color, _intensity, _position, _direction, _innerAngle, _outerAngle, _castsShadow ? @"YES" : @"NO");
    
    // TODO: Apply spot light settings to ViroReact renderer
    // Spot light emits light in a cone from a specific position and direction
    // Light intensity decreases with distance and angle from the light's direction
    // Inner angle defines full intensity cone, outer angle defines falloff cone
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

- (CGFloat)calculateConeAttenuationForAngle:(CGFloat)angle
{
    // Calculate light attenuation based on angle from spotlight direction
    if (angle <= _innerAngle) {
        return 1.0; // Full intensity within inner cone
    } else if (angle >= _outerAngle) {
        return 0.0; // No light beyond outer cone
    } else {
        // Smooth falloff between inner and outer cones
        CGFloat range = _outerAngle - _innerAngle;
        CGFloat relativeAngle = angle - _innerAngle;
        return 1.0 - (relativeAngle / range);
    }
}

#pragma mark - Layout

- (void)layoutSubviews
{
    [super layoutSubviews];
    
    // Spot lights don't have visual layout, but we can log for debugging
    RCTLogInfo(@"[ViroSpotLightComponentView] layoutSubviews: %@", NSStringFromCGRect(self.bounds));
}

#pragma mark - Lifecycle

- (void)didMoveToWindow
{
    [super didMoveToWindow];
    
    if (self.window) {
        RCTLogInfo(@"[ViroSpotLightComponentView] Spot light added to window");
        // TODO: Add spot light to ViroReact scene when added to window
        [self updateSpotLight];
    } else {
        RCTLogInfo(@"[ViroSpotLightComponentView] Spot light removed from window");
        // TODO: Remove spot light from ViroReact scene when removed from window
    }
}

- (void)dealloc
{
    RCTLogInfo(@"[ViroSpotLightComponentView] Deallocating");
    // TODO: Clean up ViroReact spot light resources
}

@end

Class<RCTComponentViewProtocol> ViroSpotLightCls(void)
{
    return ViroSpotLightComponentView.class;
}