//
//  ViroMaterialVideoComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * ViroMaterialVideoComponentView - Video-as-Material Component
 * 
 * This component renders videos as material textures that can be applied to 3D objects.
 * It provides comprehensive video playback controls while serving as a texture source
 * for other 3D geometry components, enabling dynamic material effects.
 * 
 * Key Features:
 * - Video as texture material for 3D objects
 * - Comprehensive video playback controls
 * - Dynamic texture updating and synchronization
 * - Multiple video format support
 * - Texture mapping and UV controls
 * - Performance optimization for 3D rendering
 * - Alpha channel and transparency support
 * - Texture filtering and quality controls
 * - Memory management for video textures
 * - Integration with SceneKit materials
 */
@interface ViroMaterialVideoComponentView : RCTViewComponentView

// Video source properties
- (void)setSource:(nullable NSDictionary *)source;
- (void)setUri:(nullable NSString *)uri;
- (void)setLocal:(nullable NSString *)local;
- (void)setResource:(nullable NSString *)resource;

// Video playback control
- (void)setPaused:(BOOL)paused;
- (void)setLoop:(BOOL)loop;
- (void)setMuted:(BOOL)muted;
- (void)setVolume:(CGFloat)volume;
- (void)setRate:(CGFloat)rate;
- (void)setSeekTime:(CGFloat)seekTime;

// Material properties
- (void)setMaterialType:(nullable NSString *)materialType;
- (void)setTextureSlot:(nullable NSString *)textureSlot;
- (void)setIntensity:(CGFloat)intensity;
- (void)setWrapS:(nullable NSString *)wrapS;
- (void)setWrapT:(nullable NSString *)wrapT;
- (void)setMinificationFilter:(nullable NSString *)minificationFilter;
- (void)setMagnificationFilter:(nullable NSString *)magnificationFilter;
- (void)setMipmap:(BOOL)mipmap;

// Texture properties
- (void)setTextureWidth:(NSInteger)textureWidth;
- (void)setTextureHeight:(NSInteger)textureHeight;
- (void)setTextureFormat:(nullable NSString *)textureFormat;
- (void)setAlphaMode:(nullable NSString *)alphaMode;
- (void)setAlphaThreshold:(CGFloat)alphaThreshold;

// UV mapping
- (void)setUvTransform:(nullable NSArray<NSNumber *> *)uvTransform;
- (void)setUvOffset:(nullable NSArray<NSNumber *> *)uvOffset;
- (void)setUvScale:(nullable NSArray<NSNumber *> *)uvScale;
- (void)setUvRotation:(CGFloat)uvRotation;

// Quality and performance
- (void)setQuality:(nullable NSString *)quality;
- (void)setStreamingEnabled:(BOOL)streamingEnabled;
- (void)setPreload:(BOOL)preload;
- (void)setCacheEnabled:(BOOL)cacheEnabled;
- (void)setBufferSize:(NSInteger)bufferSize;

// Color adjustments
- (void)setTintColor:(nullable NSArray<NSNumber *> *)tintColor;
- (void)setBrightness:(CGFloat)brightness;
- (void)setContrast:(CGFloat)contrast;
- (void)setSaturation:(CGFloat)saturation;
- (void)setGamma:(CGFloat)gamma;

// Event callbacks
- (void)setOnLoad:(nullable RCTBubblingEventBlock)onLoad;
- (void)setOnError:(nullable RCTBubblingEventBlock)onError;
- (void)setOnProgress:(nullable RCTBubblingEventBlock)onProgress;
- (void)setOnPlay:(nullable RCTBubblingEventBlock)onPlay;
- (void)setOnPause:(nullable RCTBubblingEventBlock)onPause;
- (void)setOnEnd:(nullable RCTBubblingEventBlock)onEnd;
- (void)setOnBuffer:(nullable RCTBubblingEventBlock)onBuffer;
- (void)setOnTextureUpdate:(nullable RCTBubblingEventBlock)onTextureUpdate;

// Video control methods
- (void)play;
- (void)pause;
- (void)stop;
- (void)seek:(CGFloat)time;
- (void)restart;

// Material access methods
- (SCNMaterial *)getMaterial;
- (void)applyToMaterial:(SCNMaterial *)material;
- (void)updateTexture;

// State information
- (BOOL)isPlaying;
- (BOOL)isPaused;
- (BOOL)isLoaded;
- (CGFloat)getCurrentTime;
- (CGFloat)getDuration;
- (NSDictionary *)getVideoInfo;

@end

NS_ASSUME_NONNULL_END