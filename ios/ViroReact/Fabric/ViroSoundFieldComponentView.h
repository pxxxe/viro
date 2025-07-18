//
//  ViroSoundFieldComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>
#import <React/RCTComponent.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroSoundFieldComponentView : RCTViewComponentView

// Audio source properties
@property (nonatomic, strong) NSDictionary *source;
@property (nonatomic, strong) NSString *uri;
@property (nonatomic, strong) NSString *local;
@property (nonatomic, strong) NSString *resource;

// Playback control
@property (nonatomic, assign) BOOL paused;
@property (nonatomic, assign) BOOL loop;
@property (nonatomic, assign) BOOL muted;
@property (nonatomic, assign) CGFloat volume;
@property (nonatomic, assign) CGFloat rate;
@property (nonatomic, assign) CGFloat seekTime;

// Spatial audio properties
@property (nonatomic, assign) CGFloat innerAngle;
@property (nonatomic, assign) CGFloat outerAngle;
@property (nonatomic, assign) CGFloat outerGain;
@property (nonatomic, assign) CGFloat rolloffFactor;
@property (nonatomic, assign) CGFloat minDistance;
@property (nonatomic, assign) CGFloat maxDistance;

// Event callbacks
@property (nonatomic, copy) RCTBubblingEventBlock onFinish;
@property (nonatomic, copy) RCTBubblingEventBlock onError;
@property (nonatomic, copy) RCTBubblingEventBlock onProgress;

// Audio control
- (void)play;
- (void)pause;
- (void)stop;
- (void)seek:(CGFloat)time;

@end

NS_ASSUME_NONNULL_END