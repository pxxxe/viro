//
//  ViroAnimatedImageComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * ViroAnimatedImageComponentView - Animated Image Sequence Component
 * 
 * This component renders animated image sequences including GIFs, sprite sheets,
 * and frame-based animations. It provides comprehensive animation controls and
 * supports various formats for creating dynamic visual content in 3D space.
 * 
 * Key Features:
 * - Animated GIF support with full playback control
 * - Sprite sheet animations with configurable frame layouts
 * - Frame sequence animations from multiple images
 * - Animation timing and easing controls
 * - Loop modes and playback direction
 * - Performance optimization with frame caching
 * - Interactive playback controls (play, pause, stop, seek)
 * - Frame-by-frame stepping and scrubbing
 * - Memory management for large animations
 * - Texture streaming for performance
 */
@interface ViroAnimatedImageComponentView : RCTViewComponentView

// Animation source properties
- (void)setSource:(nullable NSDictionary *)source;
- (void)setUri:(nullable NSString *)uri;
- (void)setLocal:(nullable NSString *)local;
- (void)setResource:(nullable NSString *)resource;
- (void)setFrames:(nullable NSArray<NSString *> *)frames;

// Animation format and type
- (void)setFormat:(nullable NSString *)format;
- (void)setAnimationType:(nullable NSString *)animationType;
- (void)setImageType:(nullable NSString *)imageType;

// Sprite sheet properties
- (void)setSpriteSheet:(nullable NSDictionary *)spriteSheet;
- (void)setSpriteColumns:(NSInteger)spriteColumns;
- (void)setSpriteRows:(NSInteger)spriteRows;
- (void)setFrameWidth:(NSInteger)frameWidth;
- (void)setFrameHeight:(NSInteger)frameHeight;
- (void)setFrameCount:(NSInteger)frameCount;
- (void)setFrameOrder:(nullable NSArray<NSNumber *> *)frameOrder;

// Animation timing and playback
- (void)setDuration:(CGFloat)duration;
- (void)setFrameRate:(CGFloat)frameRate;
- (void)setFrameDuration:(CGFloat)frameDuration;
- (void)setSpeed:(CGFloat)speed;
- (void)setDelay:(CGFloat)delay;
- (void)setStartFrame:(NSInteger)startFrame;
- (void)setEndFrame:(NSInteger)endFrame;

// Animation control
- (void)setPaused:(BOOL)paused;
- (void)setLoop:(BOOL)loop;
- (void)setLoopCount:(NSInteger)loopCount;
- (void)setReverse:(BOOL)reverse;
- (void)setAutoPlay:(BOOL)autoPlay;
- (void)setPlaybackDirection:(nullable NSString *)playbackDirection;

// Animation easing and interpolation
- (void)setEasing:(nullable NSString *)easing;
- (void)setInterpolation:(nullable NSString *)interpolation;
- (void)setSmoothing:(BOOL)smoothing;
- (void)setBlendMode:(nullable NSString *)blendMode;

// Display properties
- (void)setWidth:(CGFloat)width;
- (void)setHeight:(CGFloat)height;
- (void)setResizeMode:(nullable NSString *)resizeMode;
- (void)setAspectRatio:(CGFloat)aspectRatio;
- (void)setMaintainAspectRatio:(BOOL)maintainAspectRatio;

// Material properties
- (void)setOpacity:(CGFloat)opacity;
- (void)setTintColor:(nullable NSArray<NSNumber *> *)tintColor;
- (void)setBrightness:(CGFloat)brightness;
- (void)setContrast:(CGFloat)contrast;
- (void)setSaturation:(CGFloat)saturation;
- (void)setFilterType:(nullable NSString *)filterType;

// Texture properties
- (void)setTextureWrapS:(nullable NSString *)textureWrapS;
- (void)setTextureWrapT:(nullable NSString *)textureWrapT;
- (void)setTextureMinification:(nullable NSString *)textureMinification;
- (void)setTextureMagnification:(nullable NSString *)textureMagnification;
- (void)setMipmap:(BOOL)mipmap;
- (void)setAnisotropy:(CGFloat)anisotropy;

// Performance and caching
- (void)setPreload:(BOOL)preload;
- (void)setCacheEnabled:(BOOL)cacheEnabled;
- (void)setCacheSize:(NSInteger)cacheSize;
- (void)setMaxCacheFrames:(NSInteger)maxCacheFrames;
- (void)setStreamingEnabled:(BOOL)streamingEnabled;
- (void)setMemoryWarningEnabled:(BOOL)memoryWarningEnabled;

// Loading properties
- (void)setLoadingTimeout:(CGFloat)loadingTimeout;
- (void)setRetryCount:(NSInteger)retryCount;
- (void)setLazyLoading:(BOOL)lazyLoading;
- (void)setProgressiveLoading:(BOOL)progressiveLoading;

// Animation events
- (void)setOnLoad:(nullable RCTBubblingEventBlock)onLoad;
- (void)setOnError:(nullable RCTBubblingEventBlock)onError;
- (void)setOnProgress:(nullable RCTBubblingEventBlock)onProgress;
- (void)setOnStart:(nullable RCTBubblingEventBlock)onStart;
- (void)setOnStop:(nullable RCTBubblingEventBlock)onStop;
- (void)setOnPause:(nullable RCTBubblingEventBlock)onPause;
- (void)setOnResume:(nullable RCTBubblingEventBlock)onResume;
- (void)setOnLoop:(nullable RCTBubblingEventBlock)onLoop;
- (void)setOnComplete:(nullable RCTBubblingEventBlock)onComplete;
- (void)setOnFrameChange:(nullable RCTBubblingEventBlock)onFrameChange;

// Animation control methods
- (void)play;
- (void)pause;
- (void)stop;
- (void)restart;
- (void)resume;
- (void)seek:(CGFloat)time;
- (void)seekToFrame:(NSInteger)frame;
- (void)seekToProgress:(CGFloat)progress;
- (void)nextFrame;
- (void)previousFrame;
- (void)setPlaybackRate:(CGFloat)rate;

// Animation state information
- (BOOL)isPlaying;
- (BOOL)isPaused;
- (BOOL)isLoaded;
- (BOOL)hasError;
- (CGFloat)getCurrentTime;
- (CGFloat)getDuration;
- (CGFloat)getProgress;
- (NSInteger)getCurrentFrame;
- (NSInteger)getTotalFrames;
- (CGFloat)getFrameRate;
- (NSDictionary *)getAnimationInfo;

// Frame management
- (void)preloadFrames;
- (void)clearFrameCache;
- (void)optimizeFrameCache;
- (UIImage *)getFrameAtIndex:(NSInteger)index;
- (NSArray<UIImage *> *)getAllFrames;
- (NSInteger)getFrameCacheSize;
- (NSInteger)getMemoryUsage;

// Sprite sheet utilities
- (CGRect)getSpriteFrameRect:(NSInteger)frameIndex;
- (UIImage *)extractSpriteFrame:(NSInteger)frameIndex;
- (void)validateSpriteSheet;
- (NSInteger)calculateSpriteFrameCount;

// Animation utilities
- (void)setAnimationCurve:(CAMediaTimingFunction *)timingFunction;
- (void)setCustomFrameTiming:(NSArray<NSNumber *> *)frameTiming;
- (void)enableFrameBlending:(BOOL)enabled;
- (void)setFrameBlendMode:(NSString *)blendMode;

// Performance monitoring
- (void)startPerformanceMonitoring;
- (void)stopPerformanceMonitoring;
- (NSDictionary *)getPerformanceMetrics;

// Memory management
- (void)handleMemoryWarning;
- (void)optimizeMemoryUsage;
- (void)releaseUnusedFrames;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END