//
//  ViroCameraComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroCameraComponentView.h"
#import <React/RCTConversions.h>
#import <React/RCTFabricComponentsPlugins.h>
#import <React/RCTLog.h>
#import <React/RCTUtils.h>

@interface ViroCameraComponentView ()

// Camera position and orientation
@property (nonatomic, strong, nullable) NSArray<NSNumber *> *position;
@property (nonatomic, strong, nullable) NSArray<NSNumber *> *rotation;
@property (nonatomic, assign) CGFloat fieldOfView;

// Camera projection
@property (nonatomic, assign) CGFloat nearClippingPlane;
@property (nonatomic, assign) CGFloat farClippingPlane;
@property (nonatomic, strong, nullable) NSString *projectionType;
@property (nonatomic, assign) CGFloat focalLength;

// Camera animation and controls
@property (nonatomic, assign) CGFloat animationDuration;
@property (nonatomic, strong, nullable) NSString *animationType;

// Camera settings
@property (nonatomic, assign) BOOL active;

// Event blocks
@property (nonatomic, copy, nullable) RCTBubblingEventBlock onTransformUpdate;
@property (nonatomic, copy, nullable) RCTBubblingEventBlock onCameraDidMount;
@property (nonatomic, copy, nullable) RCTBubblingEventBlock onCameraWillUnmount;

@end

@implementation ViroCameraComponentView

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    // TODO: Return proper component descriptor for ViroCamera
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
    RCTLogInfo(@"[ViroCameraComponentView] Initializing");
    
    // Set default camera values
    _position = @[@0, @0, @0];
    _rotation = @[@0, @0, @0];
    _fieldOfView = 90.0; // degrees
    _nearClippingPlane = 0.1;
    _farClippingPlane = 1000.0;
    _projectionType = @"perspective";
    _focalLength = 50.0; // mm
    _animationDuration = 1.0; // seconds
    _animationType = @"easeIn";
    _active = NO;
    
    // TODO: Initialize ViroReact camera
    // This will need to integrate with the existing ViroReact camera implementation
    
    self.backgroundColor = [UIColor clearColor];
    self.clipsToBounds = NO; // Cameras don't have visual bounds
}

#pragma mark - Camera Position and Orientation

- (void)setPosition:(nullable NSArray<NSNumber *> *)position
{
    RCTLogInfo(@"[ViroCameraComponentView] Setting position: %@", position);
    _position = position ?: @[@0, @0, @0];
    
    // TODO: Update camera position in ViroReact renderer
    [self updateCameraTransform];
}

- (void)setRotation:(nullable NSArray<NSNumber *> *)rotation
{
    RCTLogInfo(@"[ViroCameraComponentView] Setting rotation: %@", rotation);
    _rotation = rotation ?: @[@0, @0, @0];
    
    // TODO: Update camera rotation in ViroReact renderer
    [self updateCameraTransform];
}

- (void)setFieldOfView:(CGFloat)fieldOfView
{
    RCTLogInfo(@"[ViroCameraComponentView] Setting field of view: %f", fieldOfView);
    _fieldOfView = fieldOfView;
    
    // TODO: Update camera field of view in ViroReact renderer
    [self updateCameraProjection];
}

- (void)updateCameraTransform
{
    RCTLogInfo(@"[ViroCameraComponentView] Updating camera transform - Position: %@, Rotation: %@", _position, _rotation);
    
    // TODO: Apply camera position and rotation to ViroReact renderer
    
    // Emit transform update event
    if (_onTransformUpdate && _active) {
        _onTransformUpdate(@{
            @"position": _position,
            @"rotation": _rotation,
            @"source": @"ViroCamera"
        });
    }
}

#pragma mark - Camera Projection

- (void)setNearClippingPlane:(CGFloat)nearClippingPlane
{
    RCTLogInfo(@"[ViroCameraComponentView] Setting near clipping plane: %f", nearClippingPlane);
    _nearClippingPlane = nearClippingPlane;
    
    // TODO: Update camera projection in ViroReact renderer
    [self updateCameraProjection];
}

- (void)setFarClippingPlane:(CGFloat)farClippingPlane
{
    RCTLogInfo(@"[ViroCameraComponentView] Setting far clipping plane: %f", farClippingPlane);
    _farClippingPlane = farClippingPlane;
    
    // TODO: Update camera projection in ViroReact renderer
    [self updateCameraProjection];
}

- (void)setProjectionType:(nullable NSString *)projectionType
{
    RCTLogInfo(@"[ViroCameraComponentView] Setting projection type: %@", projectionType);
    _projectionType = projectionType ?: @"perspective";
    
    // TODO: Update camera projection type in ViroReact renderer
    // Types: "perspective", "orthographic"
    [self updateCameraProjection];
}

- (void)setFocalLength:(CGFloat)focalLength
{
    RCTLogInfo(@"[ViroCameraComponentView] Setting focal length: %f", focalLength);
    _focalLength = focalLength;
    
    // TODO: Update camera focal length in ViroReact renderer
    [self updateCameraProjection];
}

- (void)updateCameraProjection
{
    RCTLogInfo(@"[ViroCameraComponentView] Updating camera projection - FOV: %.1f, Near: %.2f, Far: %.2f, Type: %@", 
               _fieldOfView, _nearClippingPlane, _farClippingPlane, _projectionType);
    
    // TODO: Apply camera projection settings to ViroReact renderer
}

#pragma mark - Camera Animation and Controls

- (void)setAnimationDuration:(CGFloat)animationDuration
{
    RCTLogInfo(@"[ViroCameraComponentView] Setting animation duration: %f", animationDuration);
    _animationDuration = animationDuration;
    
    // TODO: Update camera animation settings in ViroReact renderer
}

- (void)setAnimationType:(nullable NSString *)animationType
{
    RCTLogInfo(@"[ViroCameraComponentView] Setting animation type: %@", animationType);
    _animationType = animationType ?: @"easeIn";
    
    // TODO: Update camera animation type in ViroReact renderer
    // Types: "easeIn", "easeOut", "easeInOut", "linear", "bounce"
}

#pragma mark - Camera Settings

- (void)setActive:(BOOL)active
{
    RCTLogInfo(@"[ViroCameraComponentView] Setting active: %@", active ? @"YES" : @"NO");
    
    BOOL wasActive = _active;
    _active = active;
    
    if (active && !wasActive) {
        // Camera became active
        // TODO: Set camera as active in ViroReact renderer
        [self updateCameraTransform];
        [self updateCameraProjection];
        
        if (_onCameraDidMount) {
            _onCameraDidMount(@{
                @"source": @"ViroCamera"
            });
        }
    } else if (!active && wasActive) {
        // Camera became inactive
        // TODO: Set camera as inactive in ViroReact renderer
        
        if (_onCameraWillUnmount) {
            _onCameraWillUnmount(@{
                @"source": @"ViroCamera"
            });
        }
    }
}

#pragma mark - Event Callbacks

- (void)setOnTransformUpdate:(nullable RCTBubblingEventBlock)onTransformUpdate
{
    _onTransformUpdate = onTransformUpdate;
}

- (void)setOnCameraDidMount:(nullable RCTBubblingEventBlock)onCameraDidMount
{
    _onCameraDidMount = onCameraDidMount;
}

- (void)setOnCameraWillUnmount:(nullable RCTBubblingEventBlock)onCameraWillUnmount
{
    _onCameraWillUnmount = onCameraWillUnmount;
}

#pragma mark - Camera Control Methods

- (void)animateToPosition:(NSArray<NSNumber *> *)position 
                 rotation:(NSArray<NSNumber *> *)rotation
                 duration:(CGFloat)duration
{
    RCTLogInfo(@"[ViroCameraComponentView] Animating to position: %@, rotation: %@, duration: %f", 
               position, rotation, duration);
    
    // TODO: Implement camera animation in ViroReact renderer
    // This should smoothly transition the camera from current position/rotation to target
    
    // Update properties after animation completes
    _position = position;
    _rotation = rotation;
}

#pragma mark - Layout

- (void)layoutSubviews
{
    [super layoutSubviews];
    
    // Cameras don't have visual layout, but we can log for debugging
    RCTLogInfo(@"[ViroCameraComponentView] layoutSubviews: %@", NSStringFromCGRect(self.bounds));
}

#pragma mark - Lifecycle

- (void)didMoveToWindow
{
    [super didMoveToWindow];
    
    if (self.window) {
        RCTLogInfo(@"[ViroCameraComponentView] Camera added to window");
        // TODO: Add camera to ViroReact scene when added to window
        if (_active) {
            [self updateCameraTransform];
            [self updateCameraProjection];
        }
    } else {
        RCTLogInfo(@"[ViroCameraComponentView] Camera removed from window");
        // TODO: Remove camera from ViroReact scene when removed from window
    }
}

- (void)dealloc
{
    RCTLogInfo(@"[ViroCameraComponentView] Deallocating");
    
    if (_active && _onCameraWillUnmount) {
        _onCameraWillUnmount(@{
            @"source": @"ViroCamera"
        });
    }
    
    // TODO: Clean up ViroReact camera resources
}

@end

Class<RCTComponentViewProtocol> ViroCameraCls(void)
{
    return ViroCameraComponentView.class;
}