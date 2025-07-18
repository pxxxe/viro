//
//  Viro360ImageComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * Viro360ImageComponentView - 360° Panoramic Image Component
 * 
 * This component renders 360° panoramic images creating immersive spherical environments.
 * It supports equirectangular panoramas, cube maps, and stereoscopic 3D images for
 * creating VR experiences and environmental backgrounds.
 * 
 * Key Features:
 * - 360° equirectangular panorama support
 * - Cube map texture loading (6 faces)
 * - Stereoscopic 3D image support (side-by-side, top-bottom)
 * - Dynamic texture loading and caching
 * - Resolution and quality optimization
 * - Rotation and orientation controls
 * - Loading states and error handling
 * - HDR and LDR image support
 * - Texture compression and streaming
 * - Performance optimization for large images
 */
@interface Viro360ImageComponentView : RCTViewComponentView

// Image source properties
- (void)setSource:(nullable NSDictionary *)source;
- (void)setUri:(nullable NSString *)uri;
- (void)setLocal:(nullable NSString *)local;
- (void)setResource:(nullable NSString *)resource;

// Image format and type
- (void)setFormat:(nullable NSString *)format;
- (void)setImageType:(nullable NSString *)imageType;
- (void)setProjection:(nullable NSString *)projection;
- (void)setMapping:(nullable NSString *)mapping;

// Stereoscopic properties
- (void)setStereoscopicMode:(nullable NSString *)stereoscopicMode;
- (void)setEyeType:(nullable NSString *)eyeType;
- (void)setInterpupillaryDistance:(CGFloat)interpupillaryDistance;

// Cube map properties
- (void)setCubeFaces:(nullable NSDictionary *)cubeFaces;
- (void)setFacePositiveX:(nullable NSString *)facePositiveX;
- (void)setFaceNegativeX:(nullable NSString *)faceNegativeX;
- (void)setFacePositiveY:(nullable NSString *)facePositiveY;
- (void)setFaceNegativeY:(nullable NSString *)faceNegativeY;
- (void)setFacePositiveZ:(nullable NSString *)facePositiveZ;
- (void)setFaceNegativeZ:(nullable NSString *)faceNegativeZ;

// Rotation and orientation
- (void)setRotation:(nullable NSArray<NSNumber *> *)rotation;
- (void)setRotationX:(CGFloat)rotationX;
- (void)setRotationY:(CGFloat)rotationY;
- (void)setRotationZ:(CGFloat)rotationZ;
- (void)setOrientation:(nullable NSArray<NSNumber *> *)orientation;

// Display properties
- (void)setRadius:(CGFloat)radius;
- (void)setSegmentWidth:(NSInteger)segmentWidth;
- (void)setSegmentHeight:(NSInteger)segmentHeight;
- (void)setRenderingOrder:(NSInteger)renderingOrder;
- (void)setInvertNormals:(BOOL)invertNormals;

// Texture properties
- (void)setTextureMinification:(nullable NSString *)textureMinification;
- (void)setTextureMagnification:(nullable NSString *)textureMagnification;
- (void)setTextureWrapS:(nullable NSString *)textureWrapS;
- (void)setTextureWrapT:(nullable NSString *)textureWrapT;
- (void)setMipmap:(BOOL)mipmap;
- (void)setAnisotropy:(CGFloat)anisotropy;

// Quality and performance
- (void)setQuality:(nullable NSString *)quality;
- (void)setResolution:(nullable NSString *)resolution;
- (void)setCompression:(nullable NSString *)compression;
- (void)setMaxTextureSize:(NSInteger)maxTextureSize;
- (void)setStreamingEnabled:(BOOL)streamingEnabled;

// Loading and caching
- (void)setPreload:(BOOL)preload;
- (void)setCacheEnabled:(BOOL)cacheEnabled;
- (void)setCacheSize:(NSInteger)cacheSize;
- (void)setLoadingTimeout:(CGFloat)loadingTimeout;
- (void)setRetryCount:(NSInteger)retryCount;

// Color and effects
- (void)setTintColor:(nullable NSArray<NSNumber *> *)tintColor;
- (void)setBrightness:(CGFloat)brightness;
- (void)setContrast:(CGFloat)contrast;
- (void)setSaturation:(CGFloat)saturation;
- (void)setGamma:(CGFloat)gamma;
- (void)setExposure:(CGFloat)exposure;

// HDR properties
- (void)setHdrEnabled:(BOOL)hdrEnabled;
- (void)setToneMapping:(nullable NSString *)toneMapping;
- (void)setToneMappingExposure:(CGFloat)toneMappingExposure;
- (void)setToneMappingWhitePoint:(CGFloat)toneMappingWhitePoint;

// Animation properties
- (void)setAutoRotate:(BOOL)autoRotate;
- (void)setAutoRotateSpeed:(CGFloat)autoRotateSpeed;
- (void)setAutoRotateAxis:(nullable NSArray<NSNumber *> *)autoRotateAxis;
- (void)setAnimationDuration:(CGFloat)animationDuration;
- (void)setAnimationEasing:(nullable NSString *)animationEasing;

// Event callbacks
- (void)setOnLoad:(nullable RCTBubblingEventBlock)onLoad;
- (void)setOnError:(nullable RCTBubblingEventBlock)onError;
- (void)setOnProgress:(nullable RCTBubblingEventBlock)onProgress;
- (void)setOnLoadStart:(nullable RCTBubblingEventBlock)onLoadStart;
- (void)setOnLoadEnd:(nullable RCTBubblingEventBlock)onLoadEnd;
- (void)setOnRotationChange:(nullable RCTBubblingEventBlock)onRotationChange;

// Image control methods
- (void)reload;
- (void)preloadImage;
- (void)clearCache;
- (void)setRotationAnimated:(NSArray<NSNumber *> *)rotation duration:(CGFloat)duration;
- (void)resetRotation;
- (void)startAutoRotation;
- (void)stopAutoRotation;

// Image state information
- (BOOL)isLoaded;
- (BOOL)isLoading;
- (BOOL)hasError;
- (NSInteger)getTextureWidth;
- (NSInteger)getTextureHeight;
- (CGFloat)getLoadProgress;
- (NSDictionary *)getImageInfo;
- (NSString *)getCurrentFormat;
- (NSString *)getCurrentProjection;

// Texture utilities
- (void)setTextureParameters:(NSDictionary *)parameters;
- (void)optimizeTexture;
- (void)releaseTexture;
- (NSInteger)getTextureMemoryUsage;

// Stereoscopic utilities
- (void)setStereoscopicParameters:(NSDictionary *)parameters;
- (void)calibrateStereoscopic;
- (CGFloat)getOptimalInterpupillaryDistance;

// Cube map utilities
- (void)setCubeMapFromFiles:(NSArray<NSString *> *)files;
- (void)validateCubeMap;
- (BOOL)isCubeMapComplete;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END