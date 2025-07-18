//
//  ViroSkyBoxComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * ViroSkyBoxComponentView - 360° Skybox Environment Component
 * 
 * This component creates a 360° skybox environment for ViroReact scenes.
 * It provides immersive background environments using cube maps, equirectangular images,
 * or color gradients that surround the entire 3D scene.
 * 
 * Key Features:
 * - Cube map skybox support (6 individual textures)
 * - Equirectangular (360°) image support
 * - Color gradient skybox generation
 * - HDR skybox support for realistic lighting
 * - Rotation and positioning controls
 * - Fade in/out animations
 * - Format support: JPG, PNG, HDR, EXR
 * - Integration with ViroReact lighting system
 */
@interface ViroSkyBoxComponentView : RCTViewComponentView

// Skybox content
- (void)setSource:(nullable NSDictionary *)source;
- (void)setCubeMap:(nullable NSDictionary *)cubeMap;
- (void)setEquirectangular:(nullable NSDictionary *)equirectangular;
- (void)setColorTop:(nullable NSArray<NSNumber *> *)colorTop;
- (void)setColorBottom:(nullable NSArray<NSNumber *> *)colorBottom;
- (void)setColorLeft:(nullable NSArray<NSNumber *> *)colorLeft;
- (void)setColorRight:(nullable NSArray<NSNumber *> *)colorRight;
- (void)setColorFront:(nullable NSArray<NSNumber *> *)colorFront;
- (void)setColorBack:(nullable NSArray<NSNumber *> *)colorBack;

// Skybox appearance
- (void)setOpacity:(CGFloat)opacity;
- (void)setBrightness:(CGFloat)brightness;
- (void)setContrast:(CGFloat)contrast;
- (void)setSaturation:(CGFloat)saturation;
- (void)setGamma:(CGFloat)gamma;
- (void)setExposure:(CGFloat)exposure;

// Skybox geometry
- (void)setRotation:(nullable NSArray<NSNumber *> *)rotation;
- (void)setScale:(nullable NSArray<NSNumber *> *)scale;
- (void)setRadius:(CGFloat)radius;
- (void)setSegments:(NSInteger)segments;

// Skybox behavior
- (void)setFormat:(nullable NSString *)format;
- (void)setType:(nullable NSString *)type;
- (void)setLightingEnabled:(BOOL)lightingEnabled;
- (void)setEnvironmentBlending:(nullable NSString *)environmentBlending;
- (void)setRotationEnabled:(BOOL)rotationEnabled;
- (void)setAutoRotate:(BOOL)autoRotate;
- (void)setRotationSpeed:(CGFloat)rotationSpeed;

// Skybox animation
- (void)setFadeInDuration:(CGFloat)fadeInDuration;
- (void)setFadeOutDuration:(CGFloat)fadeOutDuration;
- (void)setTransitionDuration:(CGFloat)transitionDuration;
- (void)setAnimationEasing:(nullable NSString *)animationEasing;

// Skybox loading
- (void)setLoadingEnabled:(BOOL)loadingEnabled;
- (void)setLoadingColor:(nullable NSArray<NSNumber *> *)loadingColor;
- (void)setLoadingOpacity:(CGFloat)loadingOpacity;
- (void)setPreloadEnabled:(BOOL)preloadEnabled;
- (void)setCacheEnabled:(BOOL)cacheEnabled;

// Event callbacks
- (void)setOnLoad:(nullable RCTBubblingEventBlock)onLoad;
- (void)setOnError:(nullable RCTBubblingEventBlock)onError;
- (void)setOnLoadStart:(nullable RCTBubblingEventBlock)onLoadStart;
- (void)setOnLoadEnd:(nullable RCTBubblingEventBlock)onLoadEnd;
- (void)setOnProgress:(nullable RCTBubblingEventBlock)onProgress;

// Skybox control methods
- (void)loadSkybox;
- (void)unloadSkybox;
- (void)reloadSkybox;
- (void)fadeIn;
- (void)fadeOut;
- (void)transitionTo:(NSDictionary *)newSkybox;

// Skybox state information
- (NSString *)getLoadingState;
- (BOOL)isLoaded;
- (BOOL)isLoading;
- (BOOL)hasError;
- (CGFloat)getLoadingProgress;
- (NSDictionary *)getSkyboxInfo;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END