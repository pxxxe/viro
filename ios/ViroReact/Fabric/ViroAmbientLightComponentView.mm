//
//  ViroAmbientLightComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroAmbientLightComponentView.h"
#import <React/RCTConversions.h>
#import <React/RCTFabricComponentsPlugins.h>
#import <React/RCTLog.h>
#import <React/RCTUtils.h>

@interface ViroAmbientLightComponentView ()

// Light color and intensity
@property (nonatomic, strong, nullable) NSString *color;
@property (nonatomic, assign) CGFloat intensity;
@property (nonatomic, assign) CGFloat temperature;

// Light influence
@property (nonatomic, assign) NSInteger influenceBitMask;

@end

@implementation ViroAmbientLightComponentView

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    // TODO: Return proper component descriptor for ViroAmbientLight
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
    RCTLogInfo(@"[ViroAmbientLightComponentView] Initializing");
    
    // Set default light values
    _color = @"#FFFFFF";
    _intensity = 1000.0; // Lux
    _temperature = 6500.0; // Kelvin (daylight)
    _influenceBitMask = 1; // Default influence mask
    
    // TODO: Initialize ViroReact ambient light
    // This will need to integrate with the existing ViroReact lighting system
    
    self.backgroundColor = [UIColor clearColor];
    self.clipsToBounds = NO; // Lights don't have visual bounds
}

#pragma mark - Light Color and Intensity

- (void)setColor:(nullable NSString *)color
{
    RCTLogInfo(@"[ViroAmbientLightComponentView] Setting color: %@", color);
    _color = color ?: @"#FFFFFF";
    
    // TODO: Update ambient light color in ViroReact renderer
    [self updateAmbientLight];
}

- (void)setIntensity:(CGFloat)intensity
{
    RCTLogInfo(@"[ViroAmbientLightComponentView] Setting intensity: %f", intensity);
    _intensity = intensity;
    
    // TODO: Update ambient light intensity in ViroReact renderer
    [self updateAmbientLight];
}

- (void)setTemperature:(CGFloat)temperature
{
    RCTLogInfo(@"[ViroAmbientLightComponentView] Setting temperature: %f", temperature);
    _temperature = temperature;
    
    // TODO: Update ambient light temperature in ViroReact renderer
    // Temperature affects the color tint (warm/cool)
    [self updateAmbientLight];
}

#pragma mark - Light Influence

- (void)setInfluenceBitMask:(NSInteger)influenceBitMask
{
    RCTLogInfo(@"[ViroAmbientLightComponentView] Setting influence bit mask: %ld", (long)influenceBitMask);
    _influenceBitMask = influenceBitMask;
    
    // TODO: Update ambient light influence in ViroReact renderer
    // Influence bit mask determines which objects are affected by this light
    [self updateAmbientLight];
}

#pragma mark - Light Update

- (void)updateAmbientLight
{
    RCTLogInfo(@"[ViroAmbientLightComponentView] Updating ambient light - Color: %@, Intensity: %.1f, Temperature: %.1f, Influence: %ld", 
               _color, _intensity, _temperature, (long)_influenceBitMask);
    
    // TODO: Apply ambient light settings to ViroReact renderer
    // Ambient light provides uniform illumination to all objects in the scene
    // It doesn't have position or direction - it affects everything equally
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

- (NSArray<NSNumber *> *)rgbComponentsFromColor:(UIColor *)color
{
    CGFloat red, green, blue, alpha;
    if ([color getRed:&red green:&green blue:&blue alpha:&alpha]) {
        return @[@(red), @(green), @(blue)];
    }
    
    return @[@1.0, @1.0, @1.0]; // Default white
}

#pragma mark - Layout

- (void)layoutSubviews
{
    [super layoutSubviews];
    
    // Ambient lights don't have visual layout, but we can log for debugging
    RCTLogInfo(@"[ViroAmbientLightComponentView] layoutSubviews: %@", NSStringFromCGRect(self.bounds));
}

#pragma mark - Lifecycle

- (void)didMoveToWindow
{
    [super didMoveToWindow];
    
    if (self.window) {
        RCTLogInfo(@"[ViroAmbientLightComponentView] Ambient light added to window");
        // TODO: Add ambient light to ViroReact scene when added to window
        [self updateAmbientLight];
    } else {
        RCTLogInfo(@"[ViroAmbientLightComponentView] Ambient light removed from window");
        // TODO: Remove ambient light from ViroReact scene when removed from window
    }
}

- (void)dealloc
{
    RCTLogInfo(@"[ViroAmbientLightComponentView] Deallocating");
    // TODO: Clean up ViroReact ambient light resources
}

@end

Class<RCTComponentViewProtocol> ViroAmbientLightCls(void)
{
    return ViroAmbientLightComponentView.class;
}