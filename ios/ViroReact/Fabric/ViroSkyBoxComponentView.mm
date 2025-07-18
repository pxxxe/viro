//
//  ViroSkyBoxComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroSkyBoxComponentView.h"
#import "ViroReactUtils.h"
#import "ViroLog.h"
#import <React/RCTConversions.h>
#import <React/RCTLog.h>
#import <SceneKit/SceneKit.h>
#import <MetalKit/MetalKit.h>

typedef NS_ENUM(NSInteger, ViroSkyboxType) {
    ViroSkyboxTypeCube = 0,
    ViroSkyboxTypeEquirectangular,
    ViroSkyboxTypeGradient,
    ViroSkyboxTypeHDR
};

typedef NS_ENUM(NSInteger, ViroSkyboxLoadingState) {
    ViroSkyboxLoadingStateIdle = 0,
    ViroSkyboxLoadingStateLoading,
    ViroSkyboxLoadingStateLoaded,
    ViroSkyboxLoadingStateError
};

@implementation ViroSkyBoxComponentView {
    // Skybox content
    NSDictionary *_source;
    NSDictionary *_cubeMap;
    NSDictionary *_equirectangular;
    NSArray<NSNumber *> *_colorTop;
    NSArray<NSNumber *> *_colorBottom;
    NSArray<NSNumber *> *_colorLeft;
    NSArray<NSNumber *> *_colorRight;
    NSArray<NSNumber *> *_colorFront;
    NSArray<NSNumber *> *_colorBack;
    
    // Skybox appearance
    CGFloat _opacity;
    CGFloat _brightness;
    CGFloat _contrast;
    CGFloat _saturation;
    CGFloat _gamma;
    CGFloat _exposure;
    
    // Skybox geometry
    NSArray<NSNumber *> *_rotation;
    NSArray<NSNumber *> *_scale;
    CGFloat _radius;
    NSInteger _segments;
    
    // Skybox behavior
    NSString *_format;
    NSString *_type;
    BOOL _lightingEnabled;
    NSString *_environmentBlending;
    BOOL _rotationEnabled;
    BOOL _autoRotate;
    CGFloat _rotationSpeed;
    
    // Skybox animation
    CGFloat _fadeInDuration;
    CGFloat _fadeOutDuration;
    CGFloat _transitionDuration;
    NSString *_animationEasing;
    
    // Skybox loading
    BOOL _loadingEnabled;
    NSArray<NSNumber *> *_loadingColor;
    CGFloat _loadingOpacity;
    BOOL _preloadEnabled;
    BOOL _cacheEnabled;
    
    // Internal state
    ViroSkyboxType _skyboxType;
    ViroSkyboxLoadingState _loadingState;
    CGFloat _loadingProgress;
    NSError *_lastError;
    SCNNode *_skyboxNode;
    SCNMaterial *_skyboxMaterial;
    NSTimer *_rotationTimer;
    
    // Event blocks
    RCTBubblingEventBlock _onLoad;
    RCTBubblingEventBlock _onError;
    RCTBubblingEventBlock _onLoadStart;
    RCTBubblingEventBlock _onLoadEnd;
    RCTBubblingEventBlock _onProgress;
}

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        static const auto defaultProps = std::make_shared<const facebook::react::ViroSkyBoxProps>();
        _props = defaultProps;
        
        // Initialize default values
        _colorTop = @[@(0.5), @(0.7), @(1.0), @(1.0)];
        _colorBottom = @[@(0.1), @(0.3), @(0.8), @(1.0)];
        _colorLeft = @[@(0.3), @(0.5), @(0.9), @(1.0)];
        _colorRight = @[@(0.3), @(0.5), @(0.9), @(1.0)];
        _colorFront = @[@(0.3), @(0.5), @(0.9), @(1.0)];
        _colorBack = @[@(0.3), @(0.5), @(0.9), @(1.0)];
        
        // Initialize skybox appearance
        _opacity = 1.0f;
        _brightness = 1.0f;
        _contrast = 1.0f;
        _saturation = 1.0f;
        _gamma = 1.0f;
        _exposure = 0.0f;
        
        // Initialize skybox geometry
        _rotation = @[@(0.0), @(0.0), @(0.0)];
        _scale = @[@(1.0), @(1.0), @(1.0)];
        _radius = 1000.0f;
        _segments = 50;
        
        // Initialize skybox behavior
        _format = @"jpg";
        _type = @"cube";
        _lightingEnabled = YES;
        _environmentBlending = @"normal";
        _rotationEnabled = NO;
        _autoRotate = NO;
        _rotationSpeed = 1.0f;
        
        // Initialize skybox animation
        _fadeInDuration = 1.0f;
        _fadeOutDuration = 1.0f;
        _transitionDuration = 1.0f;
        _animationEasing = @"ease-in-out";
        
        // Initialize skybox loading
        _loadingEnabled = YES;
        _loadingColor = @[@(0.5), @(0.5), @(0.5), @(1.0)];
        _loadingOpacity = 0.8f;
        _preloadEnabled = NO;
        _cacheEnabled = YES;
        
        // Initialize internal state
        _skyboxType = ViroSkyboxTypeCube;
        _loadingState = ViroSkyboxLoadingStateIdle;
        _loadingProgress = 0.0f;
        
        // Create skybox node
        [self createSkyboxNode];
        
        VRTLogDebug(@"ViroSkyBox initialized");
    }
    return self;
}

#pragma mark - RCTComponentViewProtocol

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<facebook::react::ViroSkyBoxComponentDescriptor>();
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
    const auto &viroProps = *std::static_pointer_cast<facebook::react::ViroSkyBoxProps const>(props);
    const auto &oldViroProps = *std::static_pointer_cast<facebook::react::ViroSkyBoxProps const>(oldProps);
    
    [super updateProps:props oldProps:oldProps];
    
    // TODO: Update properties from viroProps
    // This will be implemented when Fabric code generation is complete
    VRTLogDebug(@"ViroSkyBox props updated");
}

#pragma mark - Skybox Content

- (void)setSource:(nullable NSDictionary *)source {
    VRTLogDebug(@"Setting source: %@", source);
    _source = source;
    [self updateSkyboxContent];
}

- (void)setCubeMap:(nullable NSDictionary *)cubeMap {
    VRTLogDebug(@"Setting cube map: %@", cubeMap);
    _cubeMap = cubeMap;
    _skyboxType = ViroSkyboxTypeCube;
    [self updateSkyboxContent];
}

- (void)setEquirectangular:(nullable NSDictionary *)equirectangular {
    VRTLogDebug(@"Setting equirectangular: %@", equirectangular);
    _equirectangular = equirectangular;
    _skyboxType = ViroSkyboxTypeEquirectangular;
    [self updateSkyboxContent];
}

- (void)setColorTop:(nullable NSArray<NSNumber *> *)colorTop {
    VRTLogDebug(@"Setting color top: %@", colorTop);
    _colorTop = colorTop ?: @[@(0.5), @(0.7), @(1.0), @(1.0)];
    [self updateSkyboxGradient];
}

- (void)setColorBottom:(nullable NSArray<NSNumber *> *)colorBottom {
    VRTLogDebug(@"Setting color bottom: %@", colorBottom);
    _colorBottom = colorBottom ?: @[@(0.1), @(0.3), @(0.8), @(1.0)];
    [self updateSkyboxGradient];
}

- (void)setColorLeft:(nullable NSArray<NSNumber *> *)colorLeft {
    VRTLogDebug(@"Setting color left: %@", colorLeft);
    _colorLeft = colorLeft ?: @[@(0.3), @(0.5), @(0.9), @(1.0)];
    [self updateSkyboxGradient];
}

- (void)setColorRight:(nullable NSArray<NSNumber *> *)colorRight {
    VRTLogDebug(@"Setting color right: %@", colorRight);
    _colorRight = colorRight ?: @[@(0.3), @(0.5), @(0.9), @(1.0)];
    [self updateSkyboxGradient];
}

- (void)setColorFront:(nullable NSArray<NSNumber *> *)colorFront {
    VRTLogDebug(@"Setting color front: %@", colorFront);
    _colorFront = colorFront ?: @[@(0.3), @(0.5), @(0.9), @(1.0)];
    [self updateSkyboxGradient];
}

- (void)setColorBack:(nullable NSArray<NSNumber *> *)colorBack {
    VRTLogDebug(@"Setting color back: %@", colorBack);
    _colorBack = colorBack ?: @[@(0.3), @(0.5), @(0.9), @(1.0)];
    [self updateSkyboxGradient];
}

#pragma mark - Skybox Appearance

- (void)setOpacity:(CGFloat)opacity {
    VRTLogDebug(@"Setting opacity: %.2f", opacity);
    _opacity = opacity;
    [self updateSkyboxMaterial];
}

- (void)setBrightness:(CGFloat)brightness {
    VRTLogDebug(@"Setting brightness: %.2f", brightness);
    _brightness = brightness;
    [self updateSkyboxMaterial];
}

- (void)setContrast:(CGFloat)contrast {
    VRTLogDebug(@"Setting contrast: %.2f", contrast);
    _contrast = contrast;
    [self updateSkyboxMaterial];
}

- (void)setSaturation:(CGFloat)saturation {
    VRTLogDebug(@"Setting saturation: %.2f", saturation);
    _saturation = saturation;
    [self updateSkyboxMaterial];
}

- (void)setGamma:(CGFloat)gamma {
    VRTLogDebug(@"Setting gamma: %.2f", gamma);
    _gamma = gamma;
    [self updateSkyboxMaterial];
}

- (void)setExposure:(CGFloat)exposure {
    VRTLogDebug(@"Setting exposure: %.2f", exposure);
    _exposure = exposure;
    [self updateSkyboxMaterial];
}

#pragma mark - Skybox Geometry

- (void)setRotation:(nullable NSArray<NSNumber *> *)rotation {
    VRTLogDebug(@"Setting rotation: %@", rotation);
    _rotation = rotation ?: @[@(0.0), @(0.0), @(0.0)];
    [self updateSkyboxTransform];
}

- (void)setScale:(nullable NSArray<NSNumber *> *)scale {
    VRTLogDebug(@"Setting scale: %@", scale);
    _scale = scale ?: @[@(1.0), @(1.0), @(1.0)];
    [self updateSkyboxTransform];
}

- (void)setRadius:(CGFloat)radius {
    VRTLogDebug(@"Setting radius: %.1f", radius);
    _radius = radius;
    [self updateSkyboxGeometry];
}

- (void)setSegments:(NSInteger)segments {
    VRTLogDebug(@"Setting segments: %ld", (long)segments);
    _segments = segments;
    [self updateSkyboxGeometry];
}

#pragma mark - Skybox Behavior

- (void)setFormat:(nullable NSString *)format {
    VRTLogDebug(@"Setting format: %@", format);
    _format = format ?: @"jpg";
}

- (void)setType:(nullable NSString *)type {
    VRTLogDebug(@"Setting type: %@", type);
    _type = type ?: @"cube";
    
    if ([type isEqualToString:@"cube"]) {
        _skyboxType = ViroSkyboxTypeCube;
    } else if ([type isEqualToString:@"equirectangular"]) {
        _skyboxType = ViroSkyboxTypeEquirectangular;
    } else if ([type isEqualToString:@"gradient"]) {
        _skyboxType = ViroSkyboxTypeGradient;
    } else if ([type isEqualToString:@"hdr"]) {
        _skyboxType = ViroSkyboxTypeHDR;
    }
    
    [self updateSkyboxContent];
}

- (void)setLightingEnabled:(BOOL)lightingEnabled {
    VRTLogDebug(@"Setting lighting enabled: %d", lightingEnabled);
    _lightingEnabled = lightingEnabled;
    [self updateSkyboxMaterial];
}

- (void)setEnvironmentBlending:(nullable NSString *)environmentBlending {
    VRTLogDebug(@"Setting environment blending: %@", environmentBlending);
    _environmentBlending = environmentBlending ?: @"normal";
    [self updateSkyboxMaterial];
}

- (void)setRotationEnabled:(BOOL)rotationEnabled {
    VRTLogDebug(@"Setting rotation enabled: %d", rotationEnabled);
    _rotationEnabled = rotationEnabled;
    [self updateRotationBehavior];
}

- (void)setAutoRotate:(BOOL)autoRotate {
    VRTLogDebug(@"Setting auto rotate: %d", autoRotate);
    _autoRotate = autoRotate;
    [self updateRotationBehavior];
}

- (void)setRotationSpeed:(CGFloat)rotationSpeed {
    VRTLogDebug(@"Setting rotation speed: %.2f", rotationSpeed);
    _rotationSpeed = rotationSpeed;
    [self updateRotationBehavior];
}

#pragma mark - Skybox Animation

- (void)setFadeInDuration:(CGFloat)fadeInDuration {
    VRTLogDebug(@"Setting fade in duration: %.2f", fadeInDuration);
    _fadeInDuration = fadeInDuration;
}

- (void)setFadeOutDuration:(CGFloat)fadeOutDuration {
    VRTLogDebug(@"Setting fade out duration: %.2f", fadeOutDuration);
    _fadeOutDuration = fadeOutDuration;
}

- (void)setTransitionDuration:(CGFloat)transitionDuration {
    VRTLogDebug(@"Setting transition duration: %.2f", transitionDuration);
    _transitionDuration = transitionDuration;
}

- (void)setAnimationEasing:(nullable NSString *)animationEasing {
    VRTLogDebug(@"Setting animation easing: %@", animationEasing);
    _animationEasing = animationEasing ?: @"ease-in-out";
}

#pragma mark - Skybox Loading

- (void)setLoadingEnabled:(BOOL)loadingEnabled {
    VRTLogDebug(@"Setting loading enabled: %d", loadingEnabled);
    _loadingEnabled = loadingEnabled;
}

- (void)setLoadingColor:(nullable NSArray<NSNumber *> *)loadingColor {
    VRTLogDebug(@"Setting loading color: %@", loadingColor);
    _loadingColor = loadingColor ?: @[@(0.5), @(0.5), @(0.5), @(1.0)];
}

- (void)setLoadingOpacity:(CGFloat)loadingOpacity {
    VRTLogDebug(@"Setting loading opacity: %.2f", loadingOpacity);
    _loadingOpacity = loadingOpacity;
}

- (void)setPreloadEnabled:(BOOL)preloadEnabled {
    VRTLogDebug(@"Setting preload enabled: %d", preloadEnabled);
    _preloadEnabled = preloadEnabled;
}

- (void)setCacheEnabled:(BOOL)cacheEnabled {
    VRTLogDebug(@"Setting cache enabled: %d", cacheEnabled);
    _cacheEnabled = cacheEnabled;
}

#pragma mark - Events

- (void)setOnLoad:(nullable RCTBubblingEventBlock)onLoad {
    _onLoad = onLoad;
}

- (void)setOnError:(nullable RCTBubblingEventBlock)onError {
    _onError = onError;
}

- (void)setOnLoadStart:(nullable RCTBubblingEventBlock)onLoadStart {
    _onLoadStart = onLoadStart;
}

- (void)setOnLoadEnd:(nullable RCTBubblingEventBlock)onLoadEnd {
    _onLoadEnd = onLoadEnd;
}

- (void)setOnProgress:(nullable RCTBubblingEventBlock)onProgress {
    _onProgress = onProgress;
}

#pragma mark - Skybox Control Methods

- (void)loadSkybox {
    VRTLogDebug(@"Loading skybox");
    
    if (_loadingState == ViroSkyboxLoadingStateLoading) {
        VRTLogDebug(@"Skybox already loading");
        return;
    }
    
    _loadingState = ViroSkyboxLoadingStateLoading;
    _loadingProgress = 0.0f;
    
    // Fire load start event
    if (_onLoadStart) {
        _onLoadStart(@{});
    }
    
    // Load based on skybox type
    switch (_skyboxType) {
        case ViroSkyboxTypeCube:
            [self loadCubeSkybox];
            break;
        case ViroSkyboxTypeEquirectangular:
            [self loadEquirectangularSkybox];
            break;
        case ViroSkyboxTypeGradient:
            [self loadGradientSkybox];
            break;
        case ViroSkyboxTypeHDR:
            [self loadHDRSkybox];
            break;
    }
}

- (void)unloadSkybox {
    VRTLogDebug(@"Unloading skybox");
    
    if (_skyboxMaterial) {
        _skyboxMaterial.diffuse.contents = nil;
        _skyboxMaterial = nil;
    }
    
    _loadingState = ViroSkyboxLoadingStateIdle;
    _loadingProgress = 0.0f;
}

- (void)reloadSkybox {
    VRTLogDebug(@"Reloading skybox");
    [self unloadSkybox];
    [self loadSkybox];
}

- (void)fadeIn {
    VRTLogDebug(@"Fading in skybox");
    
    if (_skyboxNode) {
        [CATransaction begin];
        [CATransaction setAnimationDuration:_fadeInDuration];
        _skyboxNode.opacity = 1.0f;
        [CATransaction commit];
    }
}

- (void)fadeOut {
    VRTLogDebug(@"Fading out skybox");
    
    if (_skyboxNode) {
        [CATransaction begin];
        [CATransaction setAnimationDuration:_fadeOutDuration];
        _skyboxNode.opacity = 0.0f;
        [CATransaction commit];
    }
}

- (void)transitionTo:(NSDictionary *)newSkybox {
    VRTLogDebug(@"Transitioning to new skybox: %@", newSkybox);
    
    // TODO: Implement smooth transition between skyboxes
    // This would fade out current skybox, load new one, and fade in
}

#pragma mark - Skybox State Information

- (NSString *)getLoadingState {
    switch (_loadingState) {
        case ViroSkyboxLoadingStateIdle:
            return @"idle";
        case ViroSkyboxLoadingStateLoading:
            return @"loading";
        case ViroSkyboxLoadingStateLoaded:
            return @"loaded";
        case ViroSkyboxLoadingStateError:
            return @"error";
        default:
            return @"idle";
    }
}

- (BOOL)isLoaded {
    return _loadingState == ViroSkyboxLoadingStateLoaded;
}

- (BOOL)isLoading {
    return _loadingState == ViroSkyboxLoadingStateLoading;
}

- (BOOL)hasError {
    return _loadingState == ViroSkyboxLoadingStateError;
}

- (CGFloat)getLoadingProgress {
    return _loadingProgress;
}

- (NSDictionary *)getSkyboxInfo {
    return @{
        @"type": _type,
        @"format": _format,
        @"loadingState": [self getLoadingState],
        @"loadingProgress": @(_loadingProgress),
        @"radius": @(_radius),
        @"segments": @(_segments),
        @"lightingEnabled": @(_lightingEnabled)
    };
}

#pragma mark - Helper Methods

- (void)createSkyboxNode {
    // Create sphere geometry for skybox
    SCNSphere *sphere = [SCNSphere sphereWithRadius:_radius];
    sphere.segmentCount = _segments;
    sphere.firstMaterial = [SCNMaterial material];
    sphere.firstMaterial.isDoubleSided = YES;
    sphere.firstMaterial.cullMode = SCNCullModeFront;
    
    _skyboxNode = [SCNNode nodeWithGeometry:sphere];
    _skyboxMaterial = sphere.firstMaterial;
    
    // TODO: Add skybox node to scene
    // This would be done when integrated with ViroReact scene graph
    
    [self updateSkyboxMaterial];
}

- (void)updateSkyboxContent {
    if (_loadingEnabled && _loadingState == ViroSkyboxLoadingStateIdle) {
        [self loadSkybox];
    }
}

- (void)updateSkyboxMaterial {
    if (!_skyboxMaterial) {
        return;
    }
    
    // Update material properties
    _skyboxMaterial.transparency = _opacity;
    _skyboxMaterial.lightingModel = _lightingEnabled ? SCNLightingModelPhong : SCNLightingModelConstant;
    
    // Apply color adjustments
    // TODO: Implement brightness, contrast, saturation, gamma, exposure adjustments
    // This would involve custom shader modifications or post-processing
}

- (void)updateSkyboxTransform {
    if (!_skyboxNode) {
        return;
    }
    
    // Update node transform
    if (_rotation && _rotation.count >= 3) {
        _skyboxNode.eulerAngles = SCNVector3Make(
            [_rotation[0] floatValue],
            [_rotation[1] floatValue],
            [_rotation[2] floatValue]
        );
    }
    
    if (_scale && _scale.count >= 3) {
        _skyboxNode.scale = SCNVector3Make(
            [_scale[0] floatValue],
            [_scale[1] floatValue],
            [_scale[2] floatValue]
        );
    }
}

- (void)updateSkyboxGeometry {
    if (!_skyboxNode) {
        return;
    }
    
    // Update sphere geometry
    SCNSphere *sphere = (SCNSphere *)_skyboxNode.geometry;
    sphere.radius = _radius;
    sphere.segmentCount = _segments;
}

- (void)updateSkyboxGradient {
    if (_skyboxType != ViroSkyboxTypeGradient) {
        return;
    }
    
    // TODO: Generate gradient skybox texture
    // This would create a cube map from the gradient colors
}

- (void)updateRotationBehavior {
    if (_rotationTimer) {
        [_rotationTimer invalidate];
        _rotationTimer = nil;
    }
    
    if (_autoRotate && _rotationEnabled) {
        _rotationTimer = [NSTimer scheduledTimerWithTimeInterval:1.0/60.0 target:self selector:@selector(rotateStep) userInfo:nil repeats:YES];
    }
}

- (void)rotateStep {
    if (_skyboxNode && _rotationEnabled) {
        SCNVector3 currentRotation = _skyboxNode.eulerAngles;
        currentRotation.y += _rotationSpeed * (1.0/60.0);
        _skyboxNode.eulerAngles = currentRotation;
    }
}

- (void)loadCubeSkybox {
    VRTLogDebug(@"Loading cube skybox");
    
    // TODO: Load cube map textures
    // This would load 6 individual textures for each face
    
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        // Simulate loading
        dispatch_async(dispatch_get_main_queue(), ^{
            self->_loadingState = ViroSkyboxLoadingStateLoaded;
            self->_loadingProgress = 1.0f;
            
            if (self->_onLoad) {
                self->_onLoad(@{});
            }
            
            if (self->_onLoadEnd) {
                self->_onLoadEnd(@{});
            }
        });
    });
}

- (void)loadEquirectangularSkybox {
    VRTLogDebug(@"Loading equirectangular skybox");
    
    // TODO: Load equirectangular texture
    // This would load a single 360° image
    
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        // Simulate loading
        dispatch_async(dispatch_get_main_queue(), ^{
            self->_loadingState = ViroSkyboxLoadingStateLoaded;
            self->_loadingProgress = 1.0f;
            
            if (self->_onLoad) {
                self->_onLoad(@{});
            }
            
            if (self->_onLoadEnd) {
                self->_onLoadEnd(@{});
            }
        });
    });
}

- (void)loadGradientSkybox {
    VRTLogDebug(@"Loading gradient skybox");
    
    // Generate gradient skybox immediately
    _loadingState = ViroSkyboxLoadingStateLoaded;
    _loadingProgress = 1.0f;
    
    if (_onLoad) {
        _onLoad(@{});
    }
    
    if (_onLoadEnd) {
        _onLoadEnd(@{});
    }
}

- (void)loadHDRSkybox {
    VRTLogDebug(@"Loading HDR skybox");
    
    // TODO: Load HDR texture
    // This would load HDR format for realistic lighting
    
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        // Simulate loading
        dispatch_async(dispatch_get_main_queue(), ^{
            self->_loadingState = ViroSkyboxLoadingStateLoaded;
            self->_loadingProgress = 1.0f;
            
            if (self->_onLoad) {
                self->_onLoad(@{});
            }
            
            if (self->_onLoadEnd) {
                self->_onLoadEnd(@{});
            }
        });
    });
}

- (UIColor *)colorFromArray:(NSArray<NSNumber *> *)colorArray {
    if (!colorArray || colorArray.count < 3) {
        return [UIColor whiteColor];
    }
    
    CGFloat red = [colorArray[0] floatValue];
    CGFloat green = [colorArray[1] floatValue];
    CGFloat blue = [colorArray[2] floatValue];
    CGFloat alpha = colorArray.count > 3 ? [colorArray[3] floatValue] : 1.0f;
    
    return [UIColor colorWithRed:red green:green blue:blue alpha:alpha];
}

- (void)dealloc {
    if (_rotationTimer) {
        [_rotationTimer invalidate];
        _rotationTimer = nil;
    }
}

@end