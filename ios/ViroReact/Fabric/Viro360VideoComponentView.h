//
//  Viro360VideoComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * Viro360VideoComponentView - 360° Immersive Video Component
 * 
 * This component renders 360° immersive videos creating fully immersive spherical video experiences.
 * It supports equirectangular 360° videos, stereoscopic 3D content, and comprehensive playback controls
 * for creating VR video experiences and immersive media content.
 * 
 * Key Features:
 * - 360° equirectangular video support
 * - Stereoscopic 3D video (side-by-side, top-bottom, over-under)
 * - Comprehensive video playback controls
 * - Dynamic video loading and streaming
 * - Resolution and quality optimization
 * - Rotation and orientation controls
 * - Loading states and buffering management
 * - HDR video support
 * - Performance optimization for large videos
 * - Audio synchronization and 3D spatial audio
 */
@interface Viro360VideoComponentView : RCTViewComponentView

// Video source properties
- (void)setSource:(nullable NSDictionary *)source;
- (void)setUri:(nullable NSString *)uri;
- (void)setLocal:(nullable NSString *)local;
- (void)setResource:(nullable NSString *)resource;

// Video format and type
- (void)setFormat:(nullable NSString *)format;
- (void)setVideoType:(nullable NSString *)videoType;
- (void)setProjection:(nullable NSString *)projection;
- (void)setMapping:(nullable NSString *)mapping;

// Stereoscopic properties
- (void)setStereoscopicMode:(nullable NSString *)stereoscopicMode;
- (void)setEyeType:(nullable NSString *)eyeType;
- (void)setInterpupillaryDistance:(CGFloat)interpupillaryDistance;
- (void)setEyeSeparation:(CGFloat)eyeSeparation;

// Video playback control
- (void)setPaused:(BOOL)paused;
- (void)setLoop:(BOOL)loop;
- (void)setMuted:(BOOL)muted;
- (void)setVolume:(CGFloat)volume;
- (void)setRate:(CGFloat)rate;
- (void)setSeekTime:(CGFloat)seekTime;
- (void)setCurrentTime:(CGFloat)currentTime;

// Video quality and streaming
- (void)setQuality:(nullable NSString *)quality;
- (void)setResolution:(nullable NSString *)resolution;
- (void)setBitrate:(NSInteger)bitrate;
- (void)setMaxBitrate:(NSInteger)maxBitrate;
- (void)setBufferSize:(NSInteger)bufferSize;
- (void)setStreamingEnabled:(BOOL)streamingEnabled;
- (void)setAdaptiveBitrate:(BOOL)adaptiveBitrate;

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

// Loading and caching
- (void)setPreload:(BOOL)preload;
- (void)setCacheEnabled:(BOOL)cacheEnabled;
- (void)setCacheSize:(NSInteger)cacheSize;
- (void)setLoadingTimeout:(CGFloat)loadingTimeout;
- (void)setRetryCount:(NSInteger)retryCount;
- (void)setProgressiveLoading:(BOOL)progressiveLoading;

// Audio properties
- (void)setAudioEnabled:(BOOL)audioEnabled;
- (void)setAudioTracks:(nullable NSArray<NSString *> *)audioTracks;
- (void)setSelectedAudioTrack:(NSInteger)selectedAudioTrack;
- (void)setSpatialAudioEnabled:(BOOL)spatialAudioEnabled;
- (void)setAudioPosition:(nullable NSArray<NSNumber *> *)audioPosition;

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
- (void)setColorSpace:(nullable NSString *)colorSpace;

// Animation properties
- (void)setAutoRotate:(BOOL)autoRotate;
- (void)setAutoRotateSpeed:(CGFloat)autoRotateSpeed;
- (void)setAutoRotateAxis:(nullable NSArray<NSNumber *> *)autoRotateAxis;
- (void)setAnimationDuration:(CGFloat)animationDuration;
- (void)setAnimationEasing:(nullable NSString *)animationEasing;

// Performance optimization
- (void)setLevelOfDetail:(nullable NSString *)levelOfDetail;
- (void)setFrameDropThreshold:(CGFloat)frameDropThreshold;
- (void)setMemoryOptimized:(BOOL)memoryOptimized;
- (void)setHardwareAccelerated:(BOOL)hardwareAccelerated;

// Event callbacks
- (void)setOnLoad:(nullable RCTBubblingEventBlock)onLoad;
- (void)setOnError:(nullable RCTBubblingEventBlock)onError;
- (void)setOnProgress:(nullable RCTBubblingEventBlock)onProgress;
- (void)setOnBuffer:(nullable RCTBubblingEventBlock)onBuffer;
- (void)setOnSeek:(nullable RCTBubblingEventBlock)onSeek;
- (void)setOnEnd:(nullable RCTBubblingEventBlock)onEnd;
- (void)setOnPlay:(nullable RCTBubblingEventBlock)onPlay;
- (void)setOnPause:(nullable RCTBubblingEventBlock)onPause;
- (void)setOnResume:(nullable RCTBubblingEventBlock)onResume;
- (void)setOnStop:(nullable RCTBubblingEventBlock)onStop;
- (void)setOnFullscreen:(nullable RCTBubblingEventBlock)onFullscreen;
- (void)setOnRotationChange:(nullable RCTBubblingEventBlock)onRotationChange;
- (void)setOnQualityChange:(nullable RCTBubblingEventBlock)onQualityChange;

// Video control methods
- (void)play;
- (void)pause;
- (void)stop;
- (void)restart;
- (void)seek:(CGFloat)time;
- (void)seekToProgress:(CGFloat)progress;
- (void)setPlaybackRate:(CGFloat)rate;
- (void)setRotationAnimated:(NSArray<NSNumber *> *)rotation duration:(CGFloat)duration;
- (void)resetRotation;
- (void)startAutoRotation;
- (void)stopAutoRotation;

// Video state information
- (BOOL)isPlaying;
- (BOOL)isPaused;
- (BOOL)isLoaded;
- (BOOL)hasError;
- (BOOL)isBuffering;
- (CGFloat)getCurrentTime;
- (CGFloat)getDuration;
- (CGFloat)getProgress;
- (CGFloat)getBufferProgress;
- (CGFloat)getCurrentVolume;
- (CGFloat)getCurrentRate;
- (NSDictionary *)getVideoInfo;
- (NSArray<NSString *> *)getAvailableQualities;
- (NSString *)getCurrentQuality;

// Video utilities
- (void)preloadVideo;
- (void)clearCache;
- (void)optimizePerformance;
- (void)setVideoQuality:(NSString *)quality;
- (void)captureFrame;
- (void)enterFullscreen;
- (void)exitFullscreen;
- (BOOL)isFullscreen;

// Stereoscopic utilities
- (void)setStereoscopicParameters:(NSDictionary *)parameters;
- (void)calibrateStereoscopic;
- (CGFloat)getOptimalInterpupillaryDistance;
- (void)switchEye:(NSString *)eyeType;

// Audio utilities
- (void)selectAudioTrack:(NSInteger)trackIndex;
- (NSArray<NSDictionary *> *)getAudioTracks;
- (void)setAudioBalance:(CGFloat)balance;
- (void)enableSpatialAudio:(BOOL)enabled;

// Performance monitoring
- (void)startPerformanceMonitoring;
- (void)stopPerformanceMonitoring;
- (NSDictionary *)getPerformanceMetrics;
- (NSInteger)getDroppedFrames;
- (CGFloat)getFrameRate;
- (NSInteger)getMemoryUsage;

// Network utilities
- (void)setNetworkHeaders:(NSDictionary *)headers;
- (void)setUserAgent:(NSString *)userAgent;
- (void)setNetworkTimeout:(CGFloat)timeout;
- (NSDictionary *)getNetworkStats;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END