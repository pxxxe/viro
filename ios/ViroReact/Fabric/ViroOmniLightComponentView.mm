//
//  ViroOmniLightComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroOmniLightComponentView.h"
#import <React/RCTConversions.h>
#import <React/RCTFabricComponentsPlugins.h>
#import <React/RCTLog.h>
#import <React/RCTUtils.h>

@interface ViroOmniLightComponentView ()

// Light color and intensity
@property (nonatomic, strong, nullable) NSString *color;
@property (nonatomic, assign) CGFloat intensity;
@property (nonatomic, assign) CGFloat temperature;

// Light position
@property (nonatomic, strong, nullable) NSArray<NSNumber *> *position;

// Light attenuation
@property (nonatomic, assign) CGFloat attenuationStartDistance;
@property (nonatomic, assign) CGFloat attenuationEndDistance;

// Light influence
@property (nonatomic, assign) NSInteger influenceBitMask;

@end

@implementation ViroOmniLightComponentView

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    // TODO: Return proper component descriptor for ViroOmniLight
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
    RCTLogInfo(@"[ViroOmniLightComponentView] Initializing");
    
    // Set default omni light values
    _color = @"#FFFFFF";
    _intensity = 1000.0; // Lumens
    _temperature = 6500.0; // Kelvin (daylight)
    _position = @[@0, @0, @0]; // Light source position
    
    // Default attenuation settings
    _attenuationStartDistance = 2.0; // Distance where attenuation begins
    _attenuationEndDistance = 10.0; // Distance where light has no effect
    
    _influenceBitMask = 1; // Default influence mask
    
    // TODO: Initialize ViroReact omni light
    // This will need to integrate with the existing ViroReact lighting system
    
    self.backgroundColor = [UIColor clearColor];
    self.clipsToBounds = NO; // Lights don't have visual bounds
}

#pragma mark - Light Color and Intensity

- (void)setColor:(nullable NSString *)color
{
    RCTLogInfo(@"[ViroOmniLightComponentView] Setting color: %@", color);
    _color = color ?: @"#FFFFFF";
    
    // TODO: Update omni light color in ViroReact renderer
    [self updateOmniLight];
}

- (void)setIntensity:(CGFloat)intensity
{
    RCTLogInfo(@"[ViroOmniLightComponentView] Setting intensity: %f", intensity);
    _intensity = intensity;
    
    // TODO: Update omni light intensity in ViroReact renderer
    [self updateOmniLight];
}

- (void)setTemperature:(CGFloat)temperature
{
    RCTLogInfo(@"[ViroOmniLightComponentView] Setting temperature: %f", temperature);
    _temperature = temperature;
    
    // TODO: Update omni light temperature in ViroReact renderer
    // Temperature affects the color tint (warm/cool)
    [self updateOmniLight];
}

#pragma mark - Light Position

- (void)setPosition:(nullable NSArray<NSNumber *> *)position
{
    RCTLogInfo(@"[ViroOmniLightComponentView] Setting position: %@", position);
    _position = position ?: @[@0, @0, @0];
    
    // TODO: Update omni light position in ViroReact renderer
    [self updateOmniLight];
}

#pragma mark - Light Attenuation

- (void)setAttenuationStartDistance:(CGFloat)attenuationStartDistance
{
    RCTLogInfo(@"[ViroOmniLightComponentView] Setting attenuation start distance: %f", attenuationStartDistance);
    _attenuationStartDistance = attenuationStartDistance;
    
    // TODO: Update attenuation start distance in ViroReact renderer
    [self updateOmniLight];
}

- (void)setAttenuationEndDistance:(CGFloat)attenuationEndDistance
{
    RCTLogInfo(@"[ViroOmniLightComponentView] Setting attenuation end distance: %f", attenuationEndDistance);
    _attenuationEndDistance = attenuationEndDistance;
    
    // TODO: Update attenuation end distance in ViroReact renderer
    [self updateOmniLight];
}

#pragma mark - Light Influence

- (void)setInfluenceBitMask:(NSInteger)influenceBitMask
{
    RCTLogInfo(@"[ViroOmniLightComponentView] Setting influence bit mask: %ld", (long)influenceBitMask);
    _influenceBitMask = influenceBitMask;
    
    // TODO: Update omni light influence in ViroReact renderer
    [self updateOmniLight];
}

#pragma mark - Light Update

- (void)updateOmniLight
{
    RCTLogInfo(@"[ViroOmniLightComponentView] Updating omni light - Color: %@, Intensity: %.1f, Position: %@, Attenuation: %.1f-%.1f", 
               _color, _intensity, _position, _attenuationStartDistance, _attenuationEndDistance);
    
    // TODO: Apply omni light settings to ViroReact renderer
    // Omni light (point light) emits light in all directions from a single point
    // Light intensity decreases with distance based on attenuation settings
    // Unlike directional lights, omni lights have a specific position in 3D space
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

- (CGFloat)calculateAttenuationAtDistance:(CGFloat)distance
{
    // Calculate light attenuation based on distance
    if (distance <= _attenuationStartDistance) {
        return 1.0; // Full intensity within start distance
    } else if (distance >= _attenuationEndDistance) {
        return 0.0; // No light beyond end distance
    } else {
        // Linear attenuation between start and end distances
        CGFloat range = _attenuationEndDistance - _attenuationStartDistance;
        CGFloat relativeDistance = distance - _attenuationStartDistance;
        return 1.0 - (relativeDistance / range);
    }
}

#pragma mark - Layout

- (void)layoutSubviews
{
    [super layoutSubviews];
    
    // Omni lights don't have visual layout, but we can log for debugging
    RCTLogInfo(@"[ViroOmniLightComponentView] layoutSubviews: %@", NSStringFromCGRect(self.bounds));
}

#pragma mark - Lifecycle

- (void)didMoveToWindow
{
    [super didMoveToWindow];
    
    if (self.window) {
        RCTLogInfo(@"[ViroOmniLightComponentView] Omni light added to window");
        // TODO: Add omni light to ViroReact scene when added to window
        [self updateOmniLight];
    } else {
        RCTLogInfo(@"[ViroOmniLightComponentView] Omni light removed from window");
        // TODO: Remove omni light from ViroReact scene when removed from window
    }
}

- (void)dealloc
{
    RCTLogInfo(@"[ViroOmniLightComponentView] Deallocating");
    // TODO: Clean up ViroReact omni light resources
}

@end

Class<RCTComponentViewProtocol> ViroOmniLightCls(void)
{
    return ViroOmniLightComponentView.class;
}