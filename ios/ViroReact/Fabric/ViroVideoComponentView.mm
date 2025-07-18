//
//  ViroVideoComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroVideoComponentView.h"
#import <React/RCTConversions.h>
#import <React/RCTFabricComponentsPlugins.h>
#import <React/RCTLog.h>
#import <React/RCTUtils.h>
#import <AVFoundation/AVFoundation.h>

@interface ViroVideoComponentView ()

// Video source properties
@property (nonatomic, strong, nullable) NSDictionary *source;
@property (nonatomic, strong, nullable) NSString *uri;

// Video playback control
@property (nonatomic, assign) BOOL loop;
@property (nonatomic, assign) BOOL muted;
@property (nonatomic, assign) CGFloat volume;
@property (nonatomic, assign) BOOL paused;

// Video display properties
@property (nonatomic, assign) CGFloat width;
@property (nonatomic, assign) CGFloat height;
@property (nonatomic, strong, nullable) NSString *resizeMode;
@property (nonatomic, strong, nullable) NSArray<NSNumber *> *rotation;

// Video material properties
@property (nonatomic, strong, nullable) NSArray<NSString *> *materials;

// Video quality and performance
@property (nonatomic, assign) CGFloat playbackRate;
@property (nonatomic, strong, nullable) NSString *stereoMode;

// Video events
@property (nonatomic, copy, nullable) RCTBubblingEventBlock onLoadStart;
@property (nonatomic, copy, nullable) RCTBubblingEventBlock onLoad;
@property (nonatomic, copy, nullable) RCTBubblingEventBlock onProgress;
@property (nonatomic, copy, nullable) RCTBubblingEventBlock onEnd;
@property (nonatomic, copy, nullable) RCTBubblingEventBlock onError;
@property (nonatomic, copy, nullable) RCTBubblingEventBlock onBuffer;

// Internal video player
@property (nonatomic, strong, nullable) AVPlayer *player;
@property (nonatomic, strong, nullable) AVPlayerItem *playerItem;
@property (nonatomic, strong, nullable) id timeObserver;

@end

@implementation ViroVideoComponentView

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    // TODO: Return proper component descriptor for ViroVideo
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
    RCTLogInfo(@"[ViroVideoComponentView] Initializing");
    
    // Set default video properties
    _loop = NO;
    _muted = NO;
    _volume = 1.0;
    _paused = NO;
    _width = 1.0;
    _height = 1.0;
    _resizeMode = @"scaleAspectFit";
    _rotation = @[@0, @0, @0];
    _playbackRate = 1.0;
    _stereoMode = @"none";
    
    // TODO: Initialize ViroReact video
    // This will need to integrate with the existing ViroReact video system
    
    self.backgroundColor = [UIColor clearColor];
    self.clipsToBounds = NO; // Allow 3D content to extend beyond bounds
}

#pragma mark - Video Source Properties

- (void)setSource:(nullable NSDictionary *)source
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting source: %@", source);
    _source = source;
    
    // Extract URI from source dictionary
    if (source && [source isKindOfClass:[NSDictionary class]]) {
        NSString *uri = source[@"uri"];
        if (uri) {
            [self setUri:uri];
        }
    }
}

- (void)setUri:(nullable NSString *)uri
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting URI: %@", uri);
    _uri = uri;
    
    if (uri) {
        [self loadVideoWithURI:uri];
    } else {
        [self stopVideo];
    }
}

#pragma mark - Video Playback Control

- (void)setLoop:(BOOL)loop
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting loop: %@", loop ? @"YES" : @"NO");
    _loop = loop;
    
    // TODO: Update video loop setting in ViroReact renderer
    [self updateVideoPlayback];
}

- (void)setMuted:(BOOL)muted
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting muted: %@", muted ? @"YES" : @"NO");
    _muted = muted;
    
    if (_player) {
        _player.muted = muted;
    }
    
    [self updateVideoPlayback];
}

- (void)setVolume:(CGFloat)volume
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting volume: %f", volume);
    _volume = MAX(0.0, MIN(1.0, volume)); // Clamp to [0, 1]
    
    if (_player) {
        _player.volume = _volume;
    }
    
    [self updateVideoPlayback];
}

- (void)setPaused:(BOOL)paused
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting paused: %@", paused ? @"YES" : @"NO");
    _paused = paused;
    
    if (_player) {
        if (paused) {
            [_player pause];
        } else {
            [_player play];
        }
    }
}

#pragma mark - Video Display Properties

- (void)setWidth:(CGFloat)width
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting width: %f", width);
    _width = width;
    
    // TODO: Update video dimensions in ViroReact renderer
    [self updateVideoGeometry];
}

- (void)setHeight:(CGFloat)height
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting height: %f", height);
    _height = height;
    
    // TODO: Update video dimensions in ViroReact renderer
    [self updateVideoGeometry];
}

- (void)setResizeMode:(nullable NSString *)resizeMode
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting resize mode: %@", resizeMode);
    _resizeMode = resizeMode ?: @"scaleAspectFit";
    
    // TODO: Update video resize mode in ViroReact renderer
    // Modes: scaleAspectFit, scaleAspectFill, scaleToFill
    [self updateVideoGeometry];
}

- (void)setRotation:(nullable NSArray<NSNumber *> *)rotation
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting rotation: %@", rotation);
    _rotation = rotation ?: @[@0, @0, @0];
    
    // TODO: Update video rotation in ViroReact renderer
    [self updateVideoGeometry];
}

#pragma mark - Video Material Properties

- (void)setMaterials:(nullable NSArray<NSString *> *)materials
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting materials: %@", materials);
    _materials = materials;
    
    // TODO: Apply materials to ViroReact video surface
    // Video can be used as texture on 3D geometry
}

#pragma mark - Video Quality and Performance

- (void)setPlaybackRate:(CGFloat)playbackRate
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting playback rate: %f", playbackRate);
    _playbackRate = playbackRate;
    
    if (_player) {
        _player.rate = _paused ? 0.0 : playbackRate;
    }
    
    [self updateVideoPlayback];
}

- (void)setStereoMode:(nullable NSString *)stereoMode
{
    RCTLogInfo(@"[ViroVideoComponentView] Setting stereo mode: %@", stereoMode);
    _stereoMode = stereoMode ?: @"none";
    
    // TODO: Update stereo mode in ViroReact renderer
    // Modes: none, leftRight, rightLeft, topBottom, bottomTop
    [self updateVideoPlayback];
}

#pragma mark - Video Control Methods

- (void)seekToTime:(CGFloat)seconds
{
    RCTLogInfo(@"[ViroVideoComponentView] Seeking to time: %f seconds", seconds);
    
    if (_player && _playerItem) {
        CMTime seekTime = CMTimeMakeWithSeconds(seconds, NSEC_PER_SEC);
        [_player seekToTime:seekTime toleranceBefore:kCMTimeZero toleranceAfter:kCMTimeZero];
    }
}

- (void)play
{
    RCTLogInfo(@"[ViroVideoComponentView] Playing video");
    [self setPaused:NO];
}

- (void)pause
{
    RCTLogInfo(@"[ViroVideoComponentView] Pausing video");
    [self setPaused:YES];
}

- (void)stop
{
    RCTLogInfo(@"[ViroVideoComponentView] Stopping video");
    [self setPaused:YES];
    [self seekToTime:0.0];
}

#pragma mark - Video Loading and Management

- (void)loadVideoWithURI:(NSString *)uri
{
    RCTLogInfo(@"[ViroVideoComponentView] Loading video from URI: %@", uri);
    
    // Clean up existing player
    [self cleanupPlayer];
    
    // Fire onLoadStart event
    if (_onLoadStart) {
        _onLoadStart(@{});
    }
    
    // Create new player item
    NSURL *videoURL = [NSURL URLWithString:uri];
    if (!videoURL) {
        [self fireErrorEvent:@"Invalid video URI"];
        return;
    }
    
    _playerItem = [AVPlayerItem playerItemWithURL:videoURL];
    if (!_playerItem) {
        [self fireErrorEvent:@"Failed to create player item"];
        return;
    }
    
    // Create player
    _player = [AVPlayer playerWithPlayerItem:_playerItem];
    if (!_player) {
        [self fireErrorEvent:@"Failed to create player"];
        return;
    }
    
    // Configure player
    _player.muted = _muted;
    _player.volume = _volume;
    
    // Add observers
    [self addPlayerObservers];
    
    // TODO: Connect AVPlayer to ViroReact video surface
    // This should render the video as texture in 3D space
    
    // Auto-play if not paused
    if (!_paused) {
        [_player play];
    }
}

- (void)addPlayerObservers
{
    if (!_player || !_playerItem) return;
    
    // Add time observer for progress updates
    __weak typeof(self) weakSelf = self;
    _timeObserver = [_player addPeriodicTimeObserverForInterval:CMTimeMake(1, 4) // 4 times per second
                                                          queue:dispatch_get_main_queue()
                                                     usingBlock:^(CMTime time) {
        [weakSelf handleProgressUpdate:time];
    }];
    
    // Add player item observers
    [_playerItem addObserver:self forKeyPath:@"status" options:NSKeyValueObservingOptionNew context:nil];
    [_playerItem addObserver:self forKeyPath:@"loadedTimeRanges" options:NSKeyValueObservingOptionNew context:nil];
    [_playerItem addObserver:self forKeyPath:@"playbackBufferEmpty" options:NSKeyValueObservingOptionNew context:nil];
    [_playerItem addObserver:self forKeyPath:@"playbackLikelyToKeepUp" options:NSKeyValueObservingOptionNew context:nil];
    
    // Add notification observers
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(handlePlayerItemDidPlayToEndTime:)
                                                 name:AVPlayerItemDidPlayToEndTimeNotification
                                               object:_playerItem];
}

- (void)cleanupPlayer
{
    if (_timeObserver && _player) {
        [_player removeTimeObserver:_timeObserver];
        _timeObserver = nil;
    }
    
    if (_playerItem) {
        [_playerItem removeObserver:self forKeyPath:@"status"];
        [_playerItem removeObserver:self forKeyPath:@"loadedTimeRanges"];
        [_playerItem removeObserver:self forKeyPath:@"playbackBufferEmpty"];
        [_playerItem removeObserver:self forKeyPath:@"playbackLikelyToKeepUp"];
    }
    
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    
    _player = nil;
    _playerItem = nil;
}

- (void)stopVideo
{
    RCTLogInfo(@"[ViroVideoComponentView] Stopping video");
    [self cleanupPlayer];
    
    // TODO: Remove video texture from ViroReact surface
}

#pragma mark - Video Updates

- (void)updateVideoPlayback
{
    RCTLogInfo(@"[ViroVideoComponentView] Updating video playback - Loop: %@, Muted: %@, Volume: %.2f, Rate: %.2f", 
               _loop ? @"YES" : @"NO", _muted ? @"YES" : @"NO", _volume, _playbackRate);
    
    // TODO: Apply video playback settings to ViroReact renderer
    // This should update the video texture properties and playback state
}

- (void)updateVideoGeometry
{
    RCTLogInfo(@"[ViroVideoComponentView] Updating video geometry - Size: %.2fx%.2f, Mode: %@, Rotation: %@", 
               _width, _height, _resizeMode, _rotation);
    
    // TODO: Apply video geometry settings to ViroReact renderer
    // This should update the 3D surface dimensions and orientation
}

#pragma mark - Event Handling

- (void)handleProgressUpdate:(CMTime)time
{
    if (_onProgress && _playerItem) {
        CGFloat currentTime = CMTimeGetSeconds(time);
        CGFloat duration = CMTimeGetSeconds(_playerItem.duration);
        
        if (isfinite(currentTime) && isfinite(duration) && duration > 0) {
            _onProgress(@{
                @"currentTime": @(currentTime),
                @"duration": @(duration),
                @"progress": @(currentTime / duration)
            });
        }
    }
}

- (void)handlePlayerItemDidPlayToEndTime:(NSNotification *)notification
{
    RCTLogInfo(@"[ViroVideoComponentView] Video playback ended");
    
    if (_onEnd) {
        _onEnd(@{});
    }
    
    if (_loop) {
        [self seekToTime:0.0];
        if (!_paused) {
            [_player play];
        }
    }
}

- (void)fireErrorEvent:(NSString *)errorMessage
{
    RCTLogError(@"[ViroVideoComponentView] Video error: %@", errorMessage);
    
    if (_onError) {
        _onError(@{
            @"error": errorMessage
        });
    }
}

#pragma mark - KVO

- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context
{
    if (object == _playerItem) {
        if ([keyPath isEqualToString:@"status"]) {
            AVPlayerItemStatus status = [[change objectForKey:NSKeyValueChangeNewKey] integerValue];
            
            switch (status) {
                case AVPlayerItemStatusReadyToPlay:
                    RCTLogInfo(@"[ViroVideoComponentView] Video ready to play");
                    if (_onLoad) {
                        CGFloat duration = CMTimeGetSeconds(_playerItem.duration);
                        _onLoad(@{
                            @"duration": isfinite(duration) ? @(duration) : @(0),
                            @"naturalSize": @{
                                @"width": @(_playerItem.presentationSize.width),
                                @"height": @(_playerItem.presentationSize.height)
                            }
                        });
                    }
                    break;
                    
                case AVPlayerItemStatusFailed:
                    [self fireErrorEvent:_playerItem.error.localizedDescription ?: @"Unknown playback error"];
                    break;
                    
                case AVPlayerItemStatusUnknown:
                    break;
            }
        } else if ([keyPath isEqualToString:@"playbackBufferEmpty"]) {
            if (_playerItem.playbackBufferEmpty && _onBuffer) {
                _onBuffer(@{@"buffering": @YES});
            }
        } else if ([keyPath isEqualToString:@"playbackLikelyToKeepUp"]) {
            if (_playerItem.playbackLikelyToKeepUp && _onBuffer) {
                _onBuffer(@{@"buffering": @NO});
            }
        }
    }
}

#pragma mark - Lifecycle

- (void)didMoveToWindow
{
    [super didMoveToWindow];
    
    if (self.window) {
        RCTLogInfo(@"[ViroVideoComponentView] Video added to window");
        // TODO: Add video to ViroReact scene when added to window
        [self updateVideoGeometry];
        [self updateVideoPlayback];
    } else {
        RCTLogInfo(@"[ViroVideoComponentView] Video removed from window");
        // TODO: Remove video from ViroReact scene when removed from window
    }
}

- (void)dealloc
{
    RCTLogInfo(@"[ViroVideoComponentView] Deallocating");
    [self cleanupPlayer];
    // TODO: Clean up ViroReact video resources
}

@end

Class<RCTComponentViewProtocol> ViroVideoCls(void)
{
    return ViroVideoComponentView.class;
}