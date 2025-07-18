//
//  Viro360VideoComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "Viro360VideoComponentView.h"
#import <React/RCTAssert.h>
#import <React/RCTUtils.h>
#import <React/RCTLog.h>
#import <SceneKit/SceneKit.h>
#import <AVFoundation/AVFoundation.h>
#import <QuartzCore/QuartzCore.h>

typedef NS_ENUM(NSInteger, Viro360VideoState) {
    Viro360VideoStateUnknown,
    Viro360VideoStateLoading,
    Viro360VideoStateLoaded,
    Viro360VideoStatePlaying,
    Viro360VideoStatePaused,
    Viro360VideoStateStopped,
    Viro360VideoStateBuffering,
    Viro360VideoStateError
};

@interface Viro360VideoComponentView ()

// SceneKit and AVFoundation components
@property (nonatomic, strong) SCNNode *videoNode;
@property (nonatomic, strong) SCNSphere *sphereGeometry;
@property (nonatomic, strong) SCNMaterial *videoMaterial;
@property (nonatomic, strong) AVPlayer *videoPlayer;
@property (nonatomic, strong) AVPlayerItem *videoPlayerItem;
@property (nonatomic, strong) AVPlayerLayer *videoPlayerLayer;

// Video source properties
@property (nonatomic, strong) NSDictionary *source;
@property (nonatomic, strong) NSString *uri;
@property (nonatomic, strong) NSString *local;
@property (nonatomic, strong) NSString *resource;

// Video format and type
@property (nonatomic, strong) NSString *format;
@property (nonatomic, strong) NSString *videoType;
@property (nonatomic, strong) NSString *projection;
@property (nonatomic, strong) NSString *mapping;

// Stereoscopic properties
@property (nonatomic, strong) NSString *stereoscopicMode;
@property (nonatomic, strong) NSString *eyeType;
@property (nonatomic, assign) CGFloat interpupillaryDistance;
@property (nonatomic, assign) CGFloat eyeSeparation;

// Video playback control
@property (nonatomic, assign) BOOL paused;
@property (nonatomic, assign) BOOL loop;
@property (nonatomic, assign) BOOL muted;
@property (nonatomic, assign) CGFloat volume;
@property (nonatomic, assign) CGFloat rate;
@property (nonatomic, assign) CGFloat seekTime;
@property (nonatomic, assign) CGFloat currentTime;

// Video quality and streaming
@property (nonatomic, strong) NSString *quality;
@property (nonatomic, strong) NSString *resolution;
@property (nonatomic, assign) NSInteger bitrate;
@property (nonatomic, assign) NSInteger maxBitrate;
@property (nonatomic, assign) NSInteger bufferSize;
@property (nonatomic, assign) BOOL streamingEnabled;
@property (nonatomic, assign) BOOL adaptiveBitrate;

// Rotation and orientation
@property (nonatomic, strong) NSArray<NSNumber *> *rotation;
@property (nonatomic, assign) CGFloat rotationX;
@property (nonatomic, assign) CGFloat rotationY;
@property (nonatomic, assign) CGFloat rotationZ;
@property (nonatomic, strong) NSArray<NSNumber *> *orientation;

// Display properties
@property (nonatomic, assign) CGFloat radius;
@property (nonatomic, assign) NSInteger segmentWidth;
@property (nonatomic, assign) NSInteger segmentHeight;
@property (nonatomic, assign) NSInteger renderingOrder;
@property (nonatomic, assign) BOOL invertNormals;

// Loading and caching
@property (nonatomic, assign) BOOL preload;
@property (nonatomic, assign) BOOL cacheEnabled;
@property (nonatomic, assign) NSInteger cacheSize;
@property (nonatomic, assign) CGFloat loadingTimeout;
@property (nonatomic, assign) NSInteger retryCount;
@property (nonatomic, assign) BOOL progressiveLoading;

// Audio properties
@property (nonatomic, assign) BOOL audioEnabled;
@property (nonatomic, strong) NSArray<NSString *> *audioTracks;
@property (nonatomic, assign) NSInteger selectedAudioTrack;
@property (nonatomic, assign) BOOL spatialAudioEnabled;
@property (nonatomic, strong) NSArray<NSNumber *> *audioPosition;

// Color and effects
@property (nonatomic, strong) NSArray<NSNumber *> *tintColor;
@property (nonatomic, assign) CGFloat brightness;
@property (nonatomic, assign) CGFloat contrast;
@property (nonatomic, assign) CGFloat saturation;
@property (nonatomic, assign) CGFloat gamma;
@property (nonatomic, assign) CGFloat exposure;

// HDR properties
@property (nonatomic, assign) BOOL hdrEnabled;
@property (nonatomic, strong) NSString *toneMapping;
@property (nonatomic, assign) CGFloat toneMappingExposure;
@property (nonatomic, assign) CGFloat toneMappingWhitePoint;
@property (nonatomic, strong) NSString *colorSpace;

// Animation properties
@property (nonatomic, assign) BOOL autoRotate;
@property (nonatomic, assign) CGFloat autoRotateSpeed;
@property (nonatomic, strong) NSArray<NSNumber *> *autoRotateAxis;
@property (nonatomic, assign) CGFloat animationDuration;
@property (nonatomic, strong) NSString *animationEasing;

// Performance optimization
@property (nonatomic, strong) NSString *levelOfDetail;
@property (nonatomic, assign) CGFloat frameDropThreshold;
@property (nonatomic, assign) BOOL memoryOptimized;
@property (nonatomic, assign) BOOL hardwareAccelerated;

// Event callbacks
@property (nonatomic, copy) RCTBubblingEventBlock onLoad;
@property (nonatomic, copy) RCTBubblingEventBlock onError;
@property (nonatomic, copy) RCTBubblingEventBlock onProgress;
@property (nonatomic, copy) RCTBubblingEventBlock onBuffer;
@property (nonatomic, copy) RCTBubblingEventBlock onSeek;
@property (nonatomic, copy) RCTBubblingEventBlock onEnd;
@property (nonatomic, copy) RCTBubblingEventBlock onPlay;
@property (nonatomic, copy) RCTBubblingEventBlock onPause;
@property (nonatomic, copy) RCTBubblingEventBlock onResume;
@property (nonatomic, copy) RCTBubblingEventBlock onStop;
@property (nonatomic, copy) RCTBubblingEventBlock onFullscreen;
@property (nonatomic, copy) RCTBubblingEventBlock onRotationChange;
@property (nonatomic, copy) RCTBubblingEventBlock onQualityChange;

// Internal state
@property (nonatomic, assign) Viro360VideoState videoState;
@property (nonatomic, assign) BOOL isBuffering;
@property (nonatomic, assign) CGFloat duration;
@property (nonatomic, assign) CGFloat bufferProgress;
@property (nonatomic, strong) NSTimer *progressTimer;
@property (nonatomic, strong) NSTimer *autoRotateTimer;
@property (nonatomic, strong) id timeObserver;
@property (nonatomic, strong) NSMutableDictionary *performanceMetrics;
@property (nonatomic, assign) NSInteger droppedFrames;
@property (nonatomic, assign) CGFloat frameRate;
@property (nonatomic, assign) BOOL isFullscreen;

@end

@implementation Viro360VideoComponentView

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        [self commonInit];
    }
    return self;
}

- (void)commonInit
{
    // Initialize default values
    _radius = 1000.0f;
    _segmentWidth = 64;
    _segmentHeight = 32;
    _renderingOrder = 0;
    _invertNormals = YES;
    _paused = NO;
    _loop = NO;
    _muted = NO;
    _volume = 1.0f;
    _rate = 1.0f;
    _seekTime = 0.0f;
    _currentTime = 0.0f;
    _streamingEnabled = YES;
    _adaptiveBitrate = YES;
    _bufferSize = 1024;
    _preload = NO;
    _cacheEnabled = YES;
    _cacheSize = 100;
    _loadingTimeout = 30.0f;
    _retryCount = 3;
    _progressiveLoading = YES;
    _audioEnabled = YES;
    _selectedAudioTrack = 0;
    _spatialAudioEnabled = NO;
    _brightness = 1.0f;
    _contrast = 1.0f;
    _saturation = 1.0f;
    _gamma = 1.0f;
    _exposure = 0.0f;
    _hdrEnabled = NO;
    _toneMappingExposure = 1.0f;
    _toneMappingWhitePoint = 1.0f;
    _autoRotate = NO;
    _autoRotateSpeed = 1.0f;
    _animationDuration = 0.3f;
    _frameDropThreshold = 0.1f;
    _memoryOptimized = YES;
    _hardwareAccelerated = YES;
    _interpupillaryDistance = 0.064f;
    _eyeSeparation = 0.064f;
    
    // Initialize state
    _videoState = Viro360VideoStateUnknown;
    _isBuffering = NO;
    _duration = 0.0f;
    _bufferProgress = 0.0f;
    _droppedFrames = 0;
    _frameRate = 0.0f;
    _isFullscreen = NO;
    _performanceMetrics = [NSMutableDictionary dictionary];
    
    // Initialize SceneKit components
    [self setupSceneKitComponents];
    
    // Setup video player
    [self setupVideoPlayer];
    
    // Setup observers
    [self setupObservers];
}

- (void)setupSceneKitComponents
{
    // Create sphere geometry for equirectangular projection
    _sphereGeometry = [SCNSphere sphereWithRadius:_radius];
    _sphereGeometry.segmentCount = _segmentWidth;
    _sphereGeometry.geodesic = NO;
    
    // Create video material
    _videoMaterial = [SCNMaterial material];
    _videoMaterial.cullMode = _invertNormals ? SCNCullModeFront : SCNCullModeBack;
    _videoMaterial.doubleSided = NO;
    _videoMaterial.lightingModelName = SCNLightingModelConstant;
    
    // Create video node
    _videoNode = [SCNNode node];
    _videoNode.geometry = _sphereGeometry;
    _videoNode.geometry.firstMaterial = _videoMaterial;
    _videoNode.renderingOrder = _renderingOrder;
    
    // Apply default rotation (invert sphere for inside viewing)
    if (_invertNormals) {
        _videoNode.scale = SCNVector3Make(-1, 1, 1);
    }
}

- (void)setupVideoPlayer
{
    // Create video player
    _videoPlayer = [[AVPlayer alloc] init];
    _videoPlayer.volume = _volume;
    _videoPlayer.rate = _rate;
    _videoPlayer.actionAtItemEnd = _loop ? AVPlayerActionAtItemEndNone : AVPlayerActionAtItemEndPause;
    
    // Enable hardware acceleration if supported
    if (_hardwareAccelerated) {
        _videoPlayer.automaticallyWaitsToMinimizeStalling = YES;
    }
}

- (void)setupObservers
{
    // Add periodic time observer
    __weak typeof(self) weakSelf = self;
    _timeObserver = [_videoPlayer addPeriodicTimeObserverForInterval:CMTimeMake(1, 30)
                                                               queue:dispatch_get_main_queue()
                                                          usingBlock:^(CMTime time) {
        __strong typeof(weakSelf) strongSelf = weakSelf;
        if (strongSelf) {
            [strongSelf updateProgress];
        }
    }];
    
    // Add notification observers
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(playerItemDidReachEnd:)
                                                 name:AVPlayerItemDidPlayToEndTimeNotification
                                               object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(playerItemFailedToPlayToEnd:)
                                                 name:AVPlayerItemFailedToPlayToEndTimeNotification
                                               object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(playerItemStalled:)
                                                 name:AVPlayerItemPlaybackStalledNotification
                                               object:nil];
}

#pragma mark - Property Setters

- (void)setSource:(NSDictionary *)source
{
    _source = source;
    [self loadVideo];
}

- (void)setUri:(NSString *)uri
{
    _uri = uri;
    [self loadVideo];
}

- (void)setLocal:(NSString *)local
{
    _local = local;
    [self loadVideo];
}

- (void)setResource:(NSString *)resource
{
    _resource = resource;
    [self loadVideo];
}

- (void)setPaused:(BOOL)paused
{
    _paused = paused;
    if (paused) {
        [self pause];
    } else {
        [self play];
    }
}

- (void)setLoop:(BOOL)loop
{
    _loop = loop;
    _videoPlayer.actionAtItemEnd = loop ? AVPlayerActionAtItemEndNone : AVPlayerActionAtItemEndPause;
}

- (void)setMuted:(BOOL)muted
{
    _muted = muted;
    _videoPlayer.muted = muted;
}

- (void)setVolume:(CGFloat)volume
{
    _volume = volume;
    _videoPlayer.volume = volume;
}

- (void)setRate:(CGFloat)rate
{
    _rate = rate;
    if (_videoState == Viro360VideoStatePlaying) {
        _videoPlayer.rate = rate;
    }
}

- (void)setSeekTime:(CGFloat)seekTime
{
    _seekTime = seekTime;
    [self seek:seekTime];
}

- (void)setRadius:(CGFloat)radius
{
    _radius = radius;
    [self updateGeometry];
}

- (void)setSegmentWidth:(NSInteger)segmentWidth
{
    _segmentWidth = segmentWidth;
    [self updateGeometry];
}

- (void)setSegmentHeight:(NSInteger)segmentHeight
{
    _segmentHeight = segmentHeight;
    [self updateGeometry];
}

- (void)setRenderingOrder:(NSInteger)renderingOrder
{
    _renderingOrder = renderingOrder;
    _videoNode.renderingOrder = renderingOrder;
}

- (void)setInvertNormals:(BOOL)invertNormals
{
    _invertNormals = invertNormals;
    [self updateGeometry];
}

- (void)setRotation:(NSArray<NSNumber *> *)rotation
{
    _rotation = rotation;
    [self updateRotation];
}

- (void)setRotationX:(CGFloat)rotationX
{
    _rotationX = rotationX;
    [self updateRotation];
}

- (void)setRotationY:(CGFloat)rotationY
{
    _rotationY = rotationY;
    [self updateRotation];
}

- (void)setRotationZ:(CGFloat)rotationZ
{
    _rotationZ = rotationZ;
    [self updateRotation];
}

- (void)setAutoRotate:(BOOL)autoRotate
{
    _autoRotate = autoRotate;
    if (autoRotate) {
        [self startAutoRotation];
    } else {
        [self stopAutoRotation];
    }
}

- (void)setAutoRotateSpeed:(CGFloat)autoRotateSpeed
{
    _autoRotateSpeed = autoRotateSpeed;
    if (_autoRotate) {
        [self startAutoRotation];
    }
}

- (void)setTintColor:(NSArray<NSNumber *> *)tintColor
{
    _tintColor = tintColor;
    [self updateMaterialProperties];
}

- (void)setBrightness:(CGFloat)brightness
{
    _brightness = brightness;
    [self updateMaterialProperties];
}

- (void)setContrast:(CGFloat)contrast
{
    _contrast = contrast;
    [self updateMaterialProperties];
}

- (void)setSaturation:(CGFloat)saturation
{
    _saturation = saturation;
    [self updateMaterialProperties];
}

- (void)setGamma:(CGFloat)gamma
{
    _gamma = gamma;
    [self updateMaterialProperties];
}

- (void)setExposure:(CGFloat)exposure
{
    _exposure = exposure;
    [self updateMaterialProperties];
}

#pragma mark - Video Loading

- (void)loadVideo
{
    NSString *videoUrl = [self resolveVideoUrl];
    if (!videoUrl) {
        return;
    }
    
    _videoState = Viro360VideoStateLoading;
    
    // Remove existing player item observers
    [self removePlayerItemObservers];
    
    // Create new player item
    NSURL *url = [NSURL URLWithString:videoUrl];
    if (!url) {
        url = [NSURL fileURLWithPath:videoUrl];
    }
    
    _videoPlayerItem = [AVPlayerItem playerItemWithURL:url];
    
    // Add player item observers
    [self addPlayerItemObservers];
    
    // Replace current item
    [_videoPlayer replaceCurrentItemWithPlayerItem:_videoPlayerItem];
    
    // Set video as texture
    _videoMaterial.diffuse.contents = _videoPlayer;
    
    [self updateMaterialProperties];
}

- (NSString *)resolveVideoUrl
{
    if (_uri) {
        return _uri;
    } else if (_local) {
        return [[NSBundle mainBundle] pathForResource:_local ofType:nil];
    } else if (_resource) {
        return [[NSBundle mainBundle] pathForResource:_resource ofType:nil];
    } else if (_source && _source[@"uri"]) {
        return _source[@"uri"];
    }
    return nil;
}

- (void)addPlayerItemObservers
{
    if (!_videoPlayerItem) return;
    
    [_videoPlayerItem addObserver:self
                       forKeyPath:@"status"
                          options:NSKeyValueObservingOptionNew
                          context:nil];
    
    [_videoPlayerItem addObserver:self
                       forKeyPath:@"loadedTimeRanges"
                          options:NSKeyValueObservingOptionNew
                          context:nil];
    
    [_videoPlayerItem addObserver:self
                       forKeyPath:@"playbackBufferEmpty"
                          options:NSKeyValueObservingOptionNew
                          context:nil];
    
    [_videoPlayerItem addObserver:self
                       forKeyPath:@"playbackLikelyToKeepUp"
                          options:NSKeyValueObservingOptionNew
                          context:nil];
}

- (void)removePlayerItemObservers
{
    if (!_videoPlayerItem) return;
    
    @try {
        [_videoPlayerItem removeObserver:self forKeyPath:@"status"];
        [_videoPlayerItem removeObserver:self forKeyPath:@"loadedTimeRanges"];
        [_videoPlayerItem removeObserver:self forKeyPath:@"playbackBufferEmpty"];
        [_videoPlayerItem removeObserver:self forKeyPath:@"playbackLikelyToKeepUp"];
    } @catch (NSException *exception) {
        // Observer was already removed
    }
}

#pragma mark - KVO

- (void)observeValueForKeyPath:(NSString *)keyPath
                      ofObject:(id)object
                        change:(NSDictionary<NSKeyValueChangeKey,id> *)change
                       context:(void *)context
{
    if (object == _videoPlayerItem) {
        if ([keyPath isEqualToString:@"status"]) {
            [self handlePlayerItemStatusChange];
        } else if ([keyPath isEqualToString:@"loadedTimeRanges"]) {
            [self handleLoadedTimeRangesChange];
        } else if ([keyPath isEqualToString:@"playbackBufferEmpty"]) {
            [self handleBufferEmptyChange];
        } else if ([keyPath isEqualToString:@"playbackLikelyToKeepUp"]) {
            [self handleBufferKeepUpChange];
        }
    }
}

- (void)handlePlayerItemStatusChange
{
    switch (_videoPlayerItem.status) {
        case AVPlayerItemStatusReadyToPlay:
            _videoState = Viro360VideoStateLoaded;
            _duration = CMTimeGetSeconds(_videoPlayerItem.duration);
            [self handleVideoLoaded];
            break;
            
        case AVPlayerItemStatusFailed:
            _videoState = Viro360VideoStateError;
            [self handleVideoError:_videoPlayerItem.error];
            break;
            
        case AVPlayerItemStatusUnknown:
            _videoState = Viro360VideoStateUnknown;
            break;
    }
}

- (void)handleLoadedTimeRangesChange
{
    if (_videoPlayerItem.loadedTimeRanges.count > 0) {
        CMTimeRange timeRange = [_videoPlayerItem.loadedTimeRanges.firstObject CMTimeRangeValue];
        CMTime bufferTime = CMTimeAdd(timeRange.start, timeRange.duration);
        _bufferProgress = CMTimeGetSeconds(bufferTime) / _duration;
        
        if (_onBuffer) {
            _onBuffer(@{
                @"bufferProgress": @(_bufferProgress),
                @"duration": @(_duration)
            });
        }
    }
}

- (void)handleBufferEmptyChange
{
    _isBuffering = _videoPlayerItem.playbackBufferEmpty;
    if (_isBuffering) {
        _videoState = Viro360VideoStateBuffering;
    }
}

- (void)handleBufferKeepUpChange
{
    if (_videoPlayerItem.playbackLikelyToKeepUp && _isBuffering) {
        _isBuffering = NO;
        _videoState = Viro360VideoStateLoaded;
    }
}

- (void)handleVideoLoaded
{
    if (_onLoad) {
        _onLoad(@{
            @"duration": @(_duration),
            @"width": @(_videoPlayerItem.presentationSize.width),
            @"height": @(_videoPlayerItem.presentationSize.height),
            @"format": _format ?: @"unknown",
            @"projection": _projection ?: @"equirectangular"
        });
    }
    
    if (!_paused) {
        [self play];
    }
}

- (void)handleVideoError:(NSError *)error
{
    if (_onError) {
        _onError(@{
            @"error": error.localizedDescription,
            @"code": @(error.code)
        });
    }
}

#pragma mark - Video Control

- (void)play
{
    if (_videoState == Viro360VideoStateLoaded || _videoState == Viro360VideoStatePaused) {
        _videoState = Viro360VideoStatePlaying;
        _videoPlayer.rate = _rate;
        
        if (_onPlay) {
            _onPlay(@{@"currentTime": @([self getCurrentTime])});
        }
    }
}

- (void)pause
{
    if (_videoState == Viro360VideoStatePlaying) {
        _videoState = Viro360VideoStatePaused;
        [_videoPlayer pause];
        
        if (_onPause) {
            _onPause(@{@"currentTime": @([self getCurrentTime])});
        }
    }
}

- (void)stop
{
    _videoState = Viro360VideoStateStopped;
    [_videoPlayer pause];
    [_videoPlayer seekToTime:kCMTimeZero];
    
    if (_onStop) {
        _onStop(@{@"currentTime": @(0.0f)});
    }
}

- (void)restart
{
    [self stop];
    [self play];
}

- (void)seek:(CGFloat)time
{
    if (_videoState == Viro360VideoStateLoaded || _videoState == Viro360VideoStatePlaying || _videoState == Viro360VideoStatePaused) {
        CMTime seekTime = CMTimeMakeWithSeconds(time, 600);
        [_videoPlayer seekToTime:seekTime completionHandler:^(BOOL finished) {
            if (finished && self.onSeek) {
                self.onSeek(@{@"currentTime": @(time)});
            }
        }];
    }
}

- (void)seekToProgress:(CGFloat)progress
{
    if (_duration > 0) {
        CGFloat targetTime = progress * _duration;
        [self seek:targetTime];
    }
}

- (void)setPlaybackRate:(CGFloat)rate
{
    _rate = rate;
    if (_videoState == Viro360VideoStatePlaying) {
        _videoPlayer.rate = rate;
    }
}

#pragma mark - Rotation and Animation

- (void)updateRotation
{
    SCNVector3 eulerAngles = SCNVector3Zero;
    
    if (_rotation && _rotation.count >= 3) {
        eulerAngles.x = [_rotation[0] floatValue];
        eulerAngles.y = [_rotation[1] floatValue];
        eulerAngles.z = [_rotation[2] floatValue];
    } else {
        eulerAngles.x = _rotationX;
        eulerAngles.y = _rotationY;
        eulerAngles.z = _rotationZ;
    }
    
    _videoNode.eulerAngles = eulerAngles;
    
    if (_onRotationChange) {
        _onRotationChange(@{
            @"rotation": @[@(eulerAngles.x), @(eulerAngles.y), @(eulerAngles.z)]
        });
    }
}

- (void)setRotationAnimated:(NSArray<NSNumber *> *)rotation duration:(CGFloat)duration
{
    if (!rotation || rotation.count < 3) return;
    
    SCNVector3 targetRotation = SCNVector3Make([rotation[0] floatValue],
                                              [rotation[1] floatValue],
                                              [rotation[2] floatValue]);
    
    [SCNTransaction begin];
    [SCNTransaction setAnimationDuration:duration];
    _videoNode.eulerAngles = targetRotation;
    [SCNTransaction commit];
}

- (void)resetRotation
{
    [SCNTransaction begin];
    [SCNTransaction setAnimationDuration:_animationDuration];
    _videoNode.eulerAngles = SCNVector3Zero;
    [SCNTransaction commit];
}

- (void)startAutoRotation
{
    [self stopAutoRotation];
    
    if (_autoRotateSpeed <= 0) return;
    
    CGFloat interval = 1.0f / 60.0f; // 60 FPS
    _autoRotateTimer = [NSTimer scheduledTimerWithTimeInterval:interval
                                                        target:self
                                                      selector:@selector(updateAutoRotation)
                                                      userInfo:nil
                                                       repeats:YES];
}

- (void)stopAutoRotation
{
    if (_autoRotateTimer) {
        [_autoRotateTimer invalidate];
        _autoRotateTimer = nil;
    }
}

- (void)updateAutoRotation
{
    CGFloat rotationIncrement = _autoRotateSpeed * (1.0f / 60.0f);
    
    if (_autoRotateAxis && _autoRotateAxis.count >= 3) {
        // Rotate around specified axis
        SCNVector3 axis = SCNVector3Make([_autoRotateAxis[0] floatValue],
                                        [_autoRotateAxis[1] floatValue],
                                        [_autoRotateAxis[2] floatValue]);
        SCNMatrix4 rotation = SCNMatrix4MakeRotation(rotationIncrement, axis.x, axis.y, axis.z);
        _videoNode.transform = SCNMatrix4Mult(_videoNode.transform, rotation);
    } else {
        // Default Y-axis rotation
        SCNVector3 currentRotation = _videoNode.eulerAngles;
        currentRotation.y += rotationIncrement;
        _videoNode.eulerAngles = currentRotation;
    }
}

#pragma mark - Geometry and Material Updates

- (void)updateGeometry
{
    _sphereGeometry.radius = _radius;
    _sphereGeometry.segmentCount = _segmentWidth;
    
    // Update material culling
    _videoMaterial.cullMode = _invertNormals ? SCNCullModeFront : SCNCullModeBack;
    
    // Update node scale for inverted normals
    if (_invertNormals) {
        _videoNode.scale = SCNVector3Make(-1, 1, 1);
    } else {
        _videoNode.scale = SCNVector3Make(1, 1, 1);
    }
}

- (void)updateMaterialProperties
{
    if (!_videoMaterial) return;
    
    _videoMaterial.diffuse.intensity = _brightness;
    
    if (_tintColor && _tintColor.count >= 3) {
        CGFloat r = [_tintColor[0] floatValue];
        CGFloat g = [_tintColor[1] floatValue];
        CGFloat b = [_tintColor[2] floatValue];
        CGFloat a = _tintColor.count > 3 ? [_tintColor[3] floatValue] : 1.0f;
        _videoMaterial.multiply.contents = [UIColor colorWithRed:r green:g blue:b alpha:a];
    }
}

#pragma mark - Progress Updates

- (void)updateProgress
{
    if (_videoPlayerItem && _duration > 0) {
        _currentTime = CMTimeGetSeconds(_videoPlayer.currentTime);
        CGFloat progress = _currentTime / _duration;
        
        if (_onProgress) {
            _onProgress(@{
                @"currentTime": @(_currentTime),
                @"duration": @(_duration),
                @"progress": @(progress)
            });
        }
    }
}

#pragma mark - Notification Handlers

- (void)playerItemDidReachEnd:(NSNotification *)notification
{
    if (_loop) {
        [_videoPlayer seekToTime:kCMTimeZero];
        [_videoPlayer play];
    } else {
        _videoState = Viro360VideoStateStopped;
        if (_onEnd) {
            _onEnd(@{@"currentTime": @(_duration)});
        }
    }
}

- (void)playerItemFailedToPlayToEnd:(NSNotification *)notification
{
    NSError *error = notification.userInfo[AVPlayerItemFailedToPlayToEndTimeErrorKey];
    [self handleVideoError:error];
}

- (void)playerItemStalled:(NSNotification *)notification
{
    _isBuffering = YES;
    _videoState = Viro360VideoStateBuffering;
}

#pragma mark - State Information

- (BOOL)isPlaying
{
    return _videoState == Viro360VideoStatePlaying;
}

- (BOOL)isPaused
{
    return _videoState == Viro360VideoStatePaused;
}

- (BOOL)isLoaded
{
    return _videoState == Viro360VideoStateLoaded || _videoState == Viro360VideoStatePlaying || _videoState == Viro360VideoStatePaused;
}

- (BOOL)hasError
{
    return _videoState == Viro360VideoStateError;
}

- (BOOL)isBuffering
{
    return _isBuffering;
}

- (CGFloat)getCurrentTime
{
    return _currentTime;
}

- (CGFloat)getDuration
{
    return _duration;
}

- (CGFloat)getProgress
{
    if (_duration == 0) return 0.0f;
    return _currentTime / _duration;
}

- (CGFloat)getBufferProgress
{
    return _bufferProgress;
}

- (CGFloat)getCurrentVolume
{
    return _videoPlayer.volume;
}

- (CGFloat)getCurrentRate
{
    return _videoPlayer.rate;
}

- (NSDictionary *)getVideoInfo
{
    return @{
        @"isPlaying": @([self isPlaying]),
        @"isPaused": @([self isPaused]),
        @"isLoaded": @([self isLoaded]),
        @"hasError": @([self hasError]),
        @"isBuffering": @([self isBuffering]),
        @"currentTime": @(_currentTime),
        @"duration": @(_duration),
        @"progress": @([self getProgress]),
        @"bufferProgress": @(_bufferProgress),
        @"volume": @(_videoPlayer.volume),
        @"rate": @(_videoPlayer.rate),
        @"width": @(_videoPlayerItem.presentationSize.width),
        @"height": @(_videoPlayerItem.presentationSize.height)
    };
}

- (NSArray<NSString *> *)getAvailableQualities
{
    // Return available quality levels
    return @[@"auto", @"high", @"medium", @"low"];
}

- (NSString *)getCurrentQuality
{
    return _quality ?: @"auto";
}

#pragma mark - Utility Methods

- (void)preloadVideo
{
    if (!_videoPlayerItem) {
        [self loadVideo];
    }
}

- (void)clearCache
{
    _videoMaterial.diffuse.contents = nil;
    [_videoPlayer replaceCurrentItemWithPlayerItem:nil];
    _videoState = Viro360VideoStateUnknown;
}

- (void)optimizePerformance
{
    // Optimize video playback for performance
    if (_memoryOptimized) {
        _videoPlayer.automaticallyWaitsToMinimizeStalling = YES;
    }
}

- (void)setVideoQuality:(NSString *)quality
{
    _quality = quality;
    // Implementation would depend on available video tracks
}

- (void)captureFrame
{
    // Capture current video frame
    // Implementation would use AVPlayerItemVideoOutput
}

- (void)enterFullscreen
{
    _isFullscreen = YES;
    if (_onFullscreen) {
        _onFullscreen(@{@"isFullscreen": @(YES)});
    }
}

- (void)exitFullscreen
{
    _isFullscreen = NO;
    if (_onFullscreen) {
        _onFullscreen(@{@"isFullscreen": @(NO)});
    }
}

- (BOOL)isFullscreen
{
    return _isFullscreen;
}

#pragma mark - Performance Monitoring

- (void)startPerformanceMonitoring
{
    // Start monitoring performance metrics
    _performanceMetrics[@"startTime"] = @([[NSDate date] timeIntervalSince1970]);
}

- (void)stopPerformanceMonitoring
{
    // Stop monitoring performance metrics
    _performanceMetrics[@"endTime"] = @([[NSDate date] timeIntervalSince1970]);
}

- (NSDictionary *)getPerformanceMetrics
{
    return [_performanceMetrics copy];
}

- (NSInteger)getDroppedFrames
{
    return _droppedFrames;
}

- (CGFloat)getFrameRate
{
    return _frameRate;
}

- (NSInteger)getMemoryUsage
{
    // Calculate approximate memory usage
    return 0; // Implementation would calculate actual usage
}

#pragma mark - Cleanup

- (void)dealloc
{
    [self stopAutoRotation];
    [self removePlayerItemObservers];
    
    if (_timeObserver) {
        [_videoPlayer removeTimeObserver:_timeObserver];
        _timeObserver = nil;
    }
    
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

@end