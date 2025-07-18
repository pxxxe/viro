//
//  ViroMaterialVideoComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroMaterialVideoComponentView.h"
#import <React/RCTAssert.h>
#import <React/RCTUtils.h>
#import <React/RCTLog.h>
#import <SceneKit/SceneKit.h>
#import <AVFoundation/AVFoundation.h>

@interface ViroMaterialVideoComponentView ()

// Video components
@property (nonatomic, strong) AVPlayer *videoPlayer;
@property (nonatomic, strong) AVPlayerItem *videoPlayerItem;
@property (nonatomic, strong) SCNMaterial *videoMaterial;

// Video source properties
@property (nonatomic, strong) NSDictionary *source;
@property (nonatomic, strong) NSString *uri;
@property (nonatomic, strong) NSString *local;
@property (nonatomic, strong) NSString *resource;

// Video playback control
@property (nonatomic, assign) BOOL paused;
@property (nonatomic, assign) BOOL loop;
@property (nonatomic, assign) BOOL muted;
@property (nonatomic, assign) CGFloat volume;
@property (nonatomic, assign) CGFloat rate;
@property (nonatomic, assign) CGFloat seekTime;

// Material properties
@property (nonatomic, strong) NSString *materialType;
@property (nonatomic, strong) NSString *textureSlot;
@property (nonatomic, assign) CGFloat intensity;
@property (nonatomic, strong) NSString *wrapS;
@property (nonatomic, strong) NSString *wrapT;
@property (nonatomic, strong) NSString *minificationFilter;
@property (nonatomic, strong) NSString *magnificationFilter;
@property (nonatomic, assign) BOOL mipmap;

// Texture properties
@property (nonatomic, assign) NSInteger textureWidth;
@property (nonatomic, assign) NSInteger textureHeight;
@property (nonatomic, strong) NSString *textureFormat;
@property (nonatomic, strong) NSString *alphaMode;
@property (nonatomic, assign) CGFloat alphaThreshold;

// UV mapping
@property (nonatomic, strong) NSArray<NSNumber *> *uvTransform;
@property (nonatomic, strong) NSArray<NSNumber *> *uvOffset;
@property (nonatomic, strong) NSArray<NSNumber *> *uvScale;
@property (nonatomic, assign) CGFloat uvRotation;

// Quality and performance
@property (nonatomic, strong) NSString *quality;
@property (nonatomic, assign) BOOL streamingEnabled;
@property (nonatomic, assign) BOOL preload;
@property (nonatomic, assign) BOOL cacheEnabled;
@property (nonatomic, assign) NSInteger bufferSize;

// Color adjustments
@property (nonatomic, strong) NSArray<NSNumber *> *tintColor;
@property (nonatomic, assign) CGFloat brightness;
@property (nonatomic, assign) CGFloat contrast;
@property (nonatomic, assign) CGFloat saturation;
@property (nonatomic, assign) CGFloat gamma;

// Event callbacks
@property (nonatomic, copy) RCTBubblingEventBlock onLoad;
@property (nonatomic, copy) RCTBubblingEventBlock onError;
@property (nonatomic, copy) RCTBubblingEventBlock onProgress;
@property (nonatomic, copy) RCTBubblingEventBlock onPlay;
@property (nonatomic, copy) RCTBubblingEventBlock onPause;
@property (nonatomic, copy) RCTBubblingEventBlock onEnd;
@property (nonatomic, copy) RCTBubblingEventBlock onBuffer;
@property (nonatomic, copy) RCTBubblingEventBlock onTextureUpdate;

// Internal state
@property (nonatomic, assign) BOOL isLoaded;
@property (nonatomic, assign) BOOL hasError;
@property (nonatomic, assign) CGFloat currentTime;
@property (nonatomic, assign) CGFloat duration;
@property (nonatomic, strong) id timeObserver;

@end

@implementation ViroMaterialVideoComponentView

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
    _paused = NO;
    _loop = NO;
    _muted = NO;
    _volume = 1.0f;
    _rate = 1.0f;
    _seekTime = 0.0f;
    _materialType = @"diffuse";
    _textureSlot = @"diffuse";
    _intensity = 1.0f;
    _wrapS = @"repeat";
    _wrapT = @"repeat";
    _minificationFilter = @"linear";
    _magnificationFilter = @"linear";
    _mipmap = YES;
    _textureWidth = 1024;
    _textureHeight = 1024;
    _textureFormat = @"rgba";
    _alphaMode = @"opaque";
    _alphaThreshold = 0.5f;
    _uvRotation = 0.0f;
    _quality = @"medium";
    _streamingEnabled = YES;
    _preload = NO;
    _cacheEnabled = YES;
    _bufferSize = 1024;
    _brightness = 1.0f;
    _contrast = 1.0f;
    _saturation = 1.0f;
    _gamma = 1.0f;
    
    // Initialize state
    _isLoaded = NO;
    _hasError = NO;
    _currentTime = 0.0f;
    _duration = 0.0f;
    
    // Setup video player
    [self setupVideoPlayer];
    
    // Setup material
    [self setupMaterial];
    
    // Setup observers
    [self setupObservers];
}

- (void)setupVideoPlayer
{
    _videoPlayer = [[AVPlayer alloc] init];
    _videoPlayer.volume = _volume;
    _videoPlayer.rate = _rate;
    _videoPlayer.actionAtItemEnd = _loop ? AVPlayerActionAtItemEndNone : AVPlayerActionAtItemEndPause;
}

- (void)setupMaterial
{
    _videoMaterial = [SCNMaterial material];
    _videoMaterial.lightingModelName = SCNLightingModelLambert;
    _videoMaterial.doubleSided = NO;
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
    if ([self isPlaying]) {
        _videoPlayer.rate = rate;
    }
}

- (void)setSeekTime:(CGFloat)seekTime
{
    _seekTime = seekTime;
    [self seek:seekTime];
}

- (void)setMaterialType:(NSString *)materialType
{
    _materialType = materialType;
    [self updateMaterialProperties];
}

- (void)setTextureSlot:(NSString *)textureSlot
{
    _textureSlot = textureSlot;
    [self updateMaterialProperties];
}

- (void)setIntensity:(CGFloat)intensity
{
    _intensity = intensity;
    [self updateMaterialProperties];
}

- (void)setWrapS:(NSString *)wrapS
{
    _wrapS = wrapS;
    [self updateMaterialProperties];
}

- (void)setWrapT:(NSString *)wrapT
{
    _wrapT = wrapT;
    [self updateMaterialProperties];
}

- (void)setMinificationFilter:(NSString *)minificationFilter
{
    _minificationFilter = minificationFilter;
    [self updateMaterialProperties];
}

- (void)setMagnificationFilter:(NSString *)magnificationFilter
{
    _magnificationFilter = magnificationFilter;
    [self updateMaterialProperties];
}

- (void)setMipmap:(BOOL)mipmap
{
    _mipmap = mipmap;
    [self updateMaterialProperties];
}

- (void)setUvTransform:(NSArray<NSNumber *> *)uvTransform
{
    _uvTransform = uvTransform;
    [self updateMaterialProperties];
}

- (void)setUvOffset:(NSArray<NSNumber *> *)uvOffset
{
    _uvOffset = uvOffset;
    [self updateMaterialProperties];
}

- (void)setUvScale:(NSArray<NSNumber *> *)uvScale
{
    _uvScale = uvScale;
    [self updateMaterialProperties];
}

- (void)setUvRotation:(CGFloat)uvRotation
{
    _uvRotation = uvRotation;
    [self updateMaterialProperties];
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

#pragma mark - Video Loading

- (void)loadVideo
{
    NSString *videoUrl = [self resolveVideoUrl];
    if (!videoUrl) {
        return;
    }
    
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
    
    // Update material with video player
    [self updateMaterialWithVideoPlayer];
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

- (void)updateMaterialWithVideoPlayer
{
    // Set video player as texture content based on texture slot
    if ([_textureSlot isEqualToString:@"diffuse"]) {
        _videoMaterial.diffuse.contents = _videoPlayer;
    } else if ([_textureSlot isEqualToString:@"specular"]) {
        _videoMaterial.specular.contents = _videoPlayer;
    } else if ([_textureSlot isEqualToString:@"emission"]) {
        _videoMaterial.emission.contents = _videoPlayer;
    } else if ([_textureSlot isEqualToString:@"normal"]) {
        _videoMaterial.normal.contents = _videoPlayer;
    } else if ([_textureSlot isEqualToString:@"roughness"]) {
        _videoMaterial.roughness.contents = _videoPlayer;
    } else if ([_textureSlot isEqualToString:@"metalness"]) {
        _videoMaterial.metalness.contents = _videoPlayer;
    } else if ([_textureSlot isEqualToString:@"ambientOcclusion"]) {
        _videoMaterial.ambientOcclusion.contents = _videoPlayer;
    }
    
    [self updateMaterialProperties];
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
}

- (void)removePlayerItemObservers
{
    if (!_videoPlayerItem) return;
    
    @try {
        [_videoPlayerItem removeObserver:self forKeyPath:@"status"];
        [_videoPlayerItem removeObserver:self forKeyPath:@"loadedTimeRanges"];
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
        }
    }
}

- (void)handlePlayerItemStatusChange
{
    switch (_videoPlayerItem.status) {
        case AVPlayerItemStatusReadyToPlay:
            _isLoaded = YES;
            _hasError = NO;
            _duration = CMTimeGetSeconds(_videoPlayerItem.duration);
            [self handleVideoLoaded];
            break;
            
        case AVPlayerItemStatusFailed:
            _isLoaded = NO;
            _hasError = YES;
            [self handleVideoError:_videoPlayerItem.error];
            break;
            
        case AVPlayerItemStatusUnknown:
            _isLoaded = NO;
            _hasError = NO;
            break;
    }
}

- (void)handleLoadedTimeRangesChange
{
    if (_onBuffer) {
        _onBuffer(@{@"duration": @(_duration)});
    }
}

- (void)handleVideoLoaded
{
    if (_onLoad) {
        _onLoad(@{
            @"duration": @(_duration),
            @"width": @(_videoPlayerItem.presentationSize.width),
            @"height": @(_videoPlayerItem.presentationSize.height)
        });
    }
    
    if (_onTextureUpdate) {
        _onTextureUpdate(@{@"textureUpdated": @(YES)});
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

#pragma mark - Material Properties

- (void)updateMaterialProperties
{
    if (!_videoMaterial) return;
    
    // Update material properties based on texture slot
    SCNMaterialProperty *materialProperty = [self getMaterialProperty];
    if (materialProperty) {
        materialProperty.intensity = _intensity;
        
        // Set wrapping mode
        if ([_wrapS isEqualToString:@"repeat"]) {
            materialProperty.wrapS = SCNWrapModeRepeat;
        } else if ([_wrapS isEqualToString:@"clamp"]) {
            materialProperty.wrapS = SCNWrapModeClamp;
        } else if ([_wrapS isEqualToString:@"mirror"]) {
            materialProperty.wrapS = SCNWrapModeMirror;
        }
        
        if ([_wrapT isEqualToString:@"repeat"]) {
            materialProperty.wrapT = SCNWrapModeRepeat;
        } else if ([_wrapT isEqualToString:@"clamp"]) {
            materialProperty.wrapT = SCNWrapModeClamp;
        } else if ([_wrapT isEqualToString:@"mirror"]) {
            materialProperty.wrapT = SCNWrapModeMirror;
        }
        
        // Set filtering
        if ([_minificationFilter isEqualToString:@"linear"]) {
            materialProperty.minificationFilter = SCNFilterModeLinear;
        } else if ([_minificationFilter isEqualToString:@"nearest"]) {
            materialProperty.minificationFilter = SCNFilterModeNearest;
        }
        
        if ([_magnificationFilter isEqualToString:@"linear"]) {
            materialProperty.magnificationFilter = SCNFilterModeLinear;
        } else if ([_magnificationFilter isEqualToString:@"nearest"]) {
            materialProperty.magnificationFilter = SCNFilterModeNearest;
        }
        
        // Set mipmap
        materialProperty.mipFilter = _mipmap ? SCNFilterModeLinear : SCNFilterModeNone;
        
        // Apply UV transformations
        if (_uvTransform && _uvTransform.count >= 6) {
            // Apply transform matrix
            CATransform3D transform = CATransform3DIdentity;
            transform.m11 = [_uvTransform[0] floatValue];
            transform.m12 = [_uvTransform[1] floatValue];
            transform.m21 = [_uvTransform[2] floatValue];
            transform.m22 = [_uvTransform[3] floatValue];
            transform.m41 = [_uvTransform[4] floatValue];
            transform.m42 = [_uvTransform[5] floatValue];
            materialProperty.contentsTransform = transform;
        } else {
            // Apply individual transformations
            CATransform3D transform = CATransform3DIdentity;
            
            if (_uvScale && _uvScale.count >= 2) {
                transform = CATransform3DScale(transform, 
                                             [_uvScale[0] floatValue], 
                                             [_uvScale[1] floatValue], 
                                             1.0f);
            }
            
            if (_uvRotation != 0.0f) {
                transform = CATransform3DRotate(transform, _uvRotation, 0, 0, 1);
            }
            
            if (_uvOffset && _uvOffset.count >= 2) {
                transform = CATransform3DTranslate(transform, 
                                                 [_uvOffset[0] floatValue], 
                                                 [_uvOffset[1] floatValue], 
                                                 0.0f);
            }
            
            materialProperty.contentsTransform = transform;
        }
    }
    
    // Apply color adjustments
    if (_tintColor && _tintColor.count >= 3) {
        CGFloat r = [_tintColor[0] floatValue];
        CGFloat g = [_tintColor[1] floatValue];
        CGFloat b = [_tintColor[2] floatValue];
        CGFloat a = _tintColor.count > 3 ? [_tintColor[3] floatValue] : 1.0f;
        _videoMaterial.multiply.contents = [UIColor colorWithRed:r green:g blue:b alpha:a];
    }
    
    // Apply brightness
    if (materialProperty) {
        materialProperty.intensity = _brightness * _intensity;
    }
}

- (SCNMaterialProperty *)getMaterialProperty
{
    if ([_textureSlot isEqualToString:@"diffuse"]) {
        return _videoMaterial.diffuse;
    } else if ([_textureSlot isEqualToString:@"specular"]) {
        return _videoMaterial.specular;
    } else if ([_textureSlot isEqualToString:@"emission"]) {
        return _videoMaterial.emission;
    } else if ([_textureSlot isEqualToString:@"normal"]) {
        return _videoMaterial.normal;
    } else if ([_textureSlot isEqualToString:@"roughness"]) {
        return _videoMaterial.roughness;
    } else if ([_textureSlot isEqualToString:@"metalness"]) {
        return _videoMaterial.metalness;
    } else if ([_textureSlot isEqualToString:@"ambientOcclusion"]) {
        return _videoMaterial.ambientOcclusion;
    }
    return _videoMaterial.diffuse;
}

#pragma mark - Video Control

- (void)play
{
    if (_isLoaded && !_hasError) {
        _videoPlayer.rate = _rate;
        
        if (_onPlay) {
            _onPlay(@{@"currentTime": @([self getCurrentTime])});
        }
    }
}

- (void)pause
{
    [_videoPlayer pause];
    
    if (_onPause) {
        _onPause(@{@"currentTime": @([self getCurrentTime])});
    }
}

- (void)stop
{
    [_videoPlayer pause];
    [_videoPlayer seekToTime:kCMTimeZero];
    
    _currentTime = 0.0f;
}

- (void)seek:(CGFloat)time
{
    if (_isLoaded && !_hasError) {
        CMTime seekTime = CMTimeMakeWithSeconds(time, 600);
        [_videoPlayer seekToTime:seekTime];
    }
}

- (void)restart
{
    [self stop];
    [self play];
}

#pragma mark - Material Access

- (SCNMaterial *)getMaterial
{
    return _videoMaterial;
}

- (void)applyToMaterial:(SCNMaterial *)material
{
    if (!material) return;
    
    SCNMaterialProperty *sourceProperty = [self getMaterialProperty];
    SCNMaterialProperty *targetProperty = nil;
    
    if ([_textureSlot isEqualToString:@"diffuse"]) {
        targetProperty = material.diffuse;
    } else if ([_textureSlot isEqualToString:@"specular"]) {
        targetProperty = material.specular;
    } else if ([_textureSlot isEqualToString:@"emission"]) {
        targetProperty = material.emission;
    } else if ([_textureSlot isEqualToString:@"normal"]) {
        targetProperty = material.normal;
    } else if ([_textureSlot isEqualToString:@"roughness"]) {
        targetProperty = material.roughness;
    } else if ([_textureSlot isEqualToString:@"metalness"]) {
        targetProperty = material.metalness;
    } else if ([_textureSlot isEqualToString:@"ambientOcclusion"]) {
        targetProperty = material.ambientOcclusion;
    }
    
    if (sourceProperty && targetProperty) {
        targetProperty.contents = sourceProperty.contents;
        targetProperty.intensity = sourceProperty.intensity;
        targetProperty.wrapS = sourceProperty.wrapS;
        targetProperty.wrapT = sourceProperty.wrapT;
        targetProperty.minificationFilter = sourceProperty.minificationFilter;
        targetProperty.magnificationFilter = sourceProperty.magnificationFilter;
        targetProperty.mipFilter = sourceProperty.mipFilter;
        targetProperty.contentsTransform = sourceProperty.contentsTransform;
    }
}

- (void)updateTexture
{
    if (_onTextureUpdate) {
        _onTextureUpdate(@{@"textureUpdated": @(YES)});
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
        if (_onEnd) {
            _onEnd(@{@"currentTime": @(_duration)});
        }
    }
}

#pragma mark - State Information

- (BOOL)isPlaying
{
    return _videoPlayer.rate > 0;
}

- (BOOL)isPaused
{
    return _videoPlayer.rate == 0 && _currentTime > 0;
}

- (BOOL)isLoaded
{
    return _isLoaded;
}

- (CGFloat)getCurrentTime
{
    return _currentTime;
}

- (CGFloat)getDuration
{
    return _duration;
}

- (NSDictionary *)getVideoInfo
{
    return @{
        @"isPlaying": @([self isPlaying]),
        @"isPaused": @([self isPaused]),
        @"isLoaded": @([self isLoaded]),
        @"hasError": @(_hasError),
        @"currentTime": @(_currentTime),
        @"duration": @(_duration),
        @"progress": @(_duration > 0 ? _currentTime / _duration : 0.0f),
        @"width": @(_videoPlayerItem.presentationSize.width),
        @"height": @(_videoPlayerItem.presentationSize.height)
    };
}

#pragma mark - Cleanup

- (void)dealloc
{
    [self removePlayerItemObservers];
    
    if (_timeObserver) {
        [_videoPlayer removeTimeObserver:_timeObserver];
        _timeObserver = nil;
    }
    
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

@end