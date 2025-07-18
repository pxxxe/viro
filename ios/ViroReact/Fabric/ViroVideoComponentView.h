//
//  ViroVideoComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroVideoComponentView : RCTViewComponentView

// Video source properties
- (void)setSource:(nullable NSDictionary *)source;
- (void)setUri:(nullable NSString *)uri;

// Video playback control
- (void)setLoop:(BOOL)loop;
- (void)setMuted:(BOOL)muted;
- (void)setVolume:(CGFloat)volume;
- (void)setPaused:(BOOL)paused;

// Video display properties
- (void)setWidth:(CGFloat)width;
- (void)setHeight:(CGFloat)height;
- (void)setResizeMode:(nullable NSString *)resizeMode;
- (void)setRotation:(nullable NSArray<NSNumber *> *)rotation;

// Video material properties
- (void)setMaterials:(nullable NSArray<NSString *> *)materials;

// Video quality and performance
- (void)setPlaybackRate:(CGFloat)playbackRate;
- (void)setStereoMode:(nullable NSString *)stereoMode;

// Video events
- (void)setOnLoadStart:(nullable RCTBubblingEventBlock)onLoadStart;
- (void)setOnLoad:(nullable RCTBubblingEventBlock)onLoad;
- (void)setOnProgress:(nullable RCTBubblingEventBlock)onProgress;
- (void)setOnEnd:(nullable RCTBubblingEventBlock)onEnd;
- (void)setOnError:(nullable RCTBubblingEventBlock)onError;
- (void)setOnBuffer:(nullable RCTBubblingEventBlock)onBuffer;

// Video control methods
- (void)seekToTime:(CGFloat)seconds;
- (void)play;
- (void)pause;
- (void)stop;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END