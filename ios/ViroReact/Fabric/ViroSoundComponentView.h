//
//  ViroSoundComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * ViroSoundComponentView - Audio Playback Component
 * 
 * This component provides audio playback capabilities for ViroReact applications.
 * It supports various audio formats, 3D spatial audio, and comprehensive playback
 * controls for creating immersive audio experiences.
 * 
 * Key Features:
 * - Audio file loading and streaming
 * - Playback controls (play, pause, stop, seek)
 * - Volume and pitch control
 * - Looping and crossfading
 * - 3D spatial audio positioning
 * - Audio effects and filters
 * - Multiple audio format support
 * - Real-time audio analysis
 * - Audio visualization integration
 * - Performance optimization
 */
@interface ViroSoundComponentView : RCTViewComponentView

// Audio source
- (void)setSource:(nullable NSDictionary *)source;
- (void)setUri:(nullable NSString *)uri;
- (void)setLocal:(nullable NSString *)local;
- (void)setResource:(nullable NSString *)resource;

// Playback control
- (void)setPaused:(BOOL)paused;
- (void)setLoop:(BOOL)loop;
- (void)setMuted:(BOOL)muted;
- (void)setVolume:(CGFloat)volume;
- (void)setRate:(CGFloat)rate;
- (void)setPitch:(CGFloat)pitch;
- (void)setSeekTime:(CGFloat)seekTime;

// Audio properties
- (void)setAudioFormat:(nullable NSString *)audioFormat;
- (void)setChannels:(NSInteger)channels;
- (void)setSampleRate:(NSInteger)sampleRate;
- (void)setBitRate:(NSInteger)bitRate;
- (void)setQuality:(nullable NSString *)quality;

// 3D spatial audio
- (void)setPosition:(nullable NSArray<NSNumber *> *)position;
- (void)setRolloffModel:(nullable NSString *)rolloffModel;
- (void)setDistanceModel:(nullable NSString *)distanceModel;
- (void)setMaxDistance:(CGFloat)maxDistance;
- (void)setReferenceDistance:(CGFloat)referenceDistance;
- (void)setRolloffFactor:(CGFloat)rolloffFactor;
- (void)setConeInnerAngle:(CGFloat)coneInnerAngle;
- (void)setConeOuterAngle:(CGFloat)coneOuterAngle;
- (void)setConeOuterGain:(CGFloat)coneOuterGain;

// Audio effects
- (void)setReverb:(nullable NSDictionary *)reverb;
- (void)setDelay:(nullable NSDictionary *)delay;
- (void)setEcho:(nullable NSDictionary *)echo;
- (void)setDistortion:(nullable NSDictionary *)distortion;
- (void)setEqualizer:(nullable NSDictionary *)equalizer;
- (void)setFilters:(nullable NSArray<NSDictionary *> *)filters;

// Crossfading
- (void)setCrossfade:(nullable NSDictionary *)crossfade;
- (void)setFadeIn:(CGFloat)fadeIn;
- (void)setFadeOut:(CGFloat)fadeOut;
- (void)setFadeInCurve:(nullable NSString *)fadeInCurve;
- (void)setFadeOutCurve:(nullable NSString *)fadeOutCurve;

// Audio analysis
- (void)setAnalysisEnabled:(BOOL)analysisEnabled;
- (void)setAnalysisInterval:(CGFloat)analysisInterval;
- (void)setAnalysisFrequencyBands:(NSInteger)analysisFrequencyBands;
- (void)setAnalysisTimeConstant:(CGFloat)analysisTimeConstant;
- (void)setAnalysisSmoothing:(CGFloat)analysisSmoothing;

// Performance
- (void)setPreload:(BOOL)preload;
- (void)setBufferSize:(NSInteger)bufferSize;
- (void)setCacheEnabled:(BOOL)cacheEnabled;
- (void)setStreamingEnabled:(BOOL)streamingEnabled;
- (void)setBackgroundPlayback:(BOOL)backgroundPlayback;

// Event callbacks
- (void)setOnLoad:(nullable RCTBubblingEventBlock)onLoad;
- (void)setOnError:(nullable RCTBubblingEventBlock)onError;
- (void)setOnPlay:(nullable RCTBubblingEventBlock)onPlay;
- (void)setOnPause:(nullable RCTBubblingEventBlock)onPause;
- (void)setOnStop:(nullable RCTBubblingEventBlock)onStop;
- (void)setOnFinish:(nullable RCTBubblingEventBlock)onFinish;
- (void)setOnSeek:(nullable RCTBubblingEventBlock)onSeek;
- (void)setOnProgress:(nullable RCTBubblingEventBlock)onProgress;
- (void)setOnVolumeChange:(nullable RCTBubblingEventBlock)onVolumeChange;
- (void)setOnRateChange:(nullable RCTBubblingEventBlock)onRateChange;
- (void)setOnAnalysis:(nullable RCTBubblingEventBlock)onAnalysis;

// Audio control methods
- (void)play;
- (void)pause;
- (void)stop;
- (void)restart;
- (void)seekToTime:(CGFloat)time;
- (void)seekToProgress:(CGFloat)progress;
- (void)fadeInWithDuration:(CGFloat)duration;
- (void)fadeOutWithDuration:(CGFloat)duration;
- (void)crossfadeToSource:(NSDictionary *)newSource duration:(CGFloat)duration;

// Audio state information
- (BOOL)isPlaying;
- (BOOL)isPaused;
- (BOOL)isLoaded;
- (BOOL)hasError;
- (CGFloat)getCurrentTime;
- (CGFloat)getDuration;
- (CGFloat)getProgress;
- (CGFloat)getCurrentVolume;
- (CGFloat)getCurrentRate;
- (NSDictionary *)getAudioInfo;
- (NSArray<NSNumber *> *)getFrequencyData;
- (NSArray<NSNumber *> *)getTimeData;

// Audio utilities
- (void)setAudioSessionCategory:(NSString *)category;
- (void)setAudioSessionMode:(NSString *)mode;
- (void)setAudioSessionOptions:(NSArray<NSString *> *)options;
- (void)activateAudioSession;
- (void)deactivateAudioSession;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END