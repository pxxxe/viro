//
//  ViroAnimatedImageComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroAnimatedImageComponentView.h"
#import <React/RCTAssert.h>
#import <React/RCTUtils.h>
#import <React/RCTLog.h>
#import <SceneKit/SceneKit.h>
#import <QuartzCore/QuartzCore.h>
#import <ImageIO/ImageIO.h>

@interface ViroAnimatedImageComponentView ()

// SceneKit components
@property (nonatomic, strong) SCNNode *imageNode;
@property (nonatomic, strong) SCNPlane *planeGeometry;
@property (nonatomic, strong) SCNMaterial *imageMaterial;

// Animation source properties
@property (nonatomic, strong) NSDictionary *source;
@property (nonatomic, strong) NSString *uri;
@property (nonatomic, strong) NSString *local;
@property (nonatomic, strong) NSString *resource;
@property (nonatomic, strong) NSArray<NSString *> *frames;

// Animation format and type
@property (nonatomic, strong) NSString *format;
@property (nonatomic, strong) NSString *animationType;
@property (nonatomic, strong) NSString *imageType;

// Sprite sheet properties
@property (nonatomic, strong) NSDictionary *spriteSheet;
@property (nonatomic, assign) NSInteger spriteColumns;
@property (nonatomic, assign) NSInteger spriteRows;
@property (nonatomic, assign) NSInteger frameWidth;
@property (nonatomic, assign) NSInteger frameHeight;
@property (nonatomic, assign) NSInteger frameCount;
@property (nonatomic, strong) NSArray<NSNumber *> *frameOrder;

// Animation timing and playback
@property (nonatomic, assign) CGFloat duration;
@property (nonatomic, assign) CGFloat frameRate;
@property (nonatomic, assign) CGFloat frameDuration;
@property (nonatomic, assign) CGFloat speed;
@property (nonatomic, assign) CGFloat delay;
@property (nonatomic, assign) NSInteger startFrame;
@property (nonatomic, assign) NSInteger endFrame;

// Animation control
@property (nonatomic, assign) BOOL paused;
@property (nonatomic, assign) BOOL loop;
@property (nonatomic, assign) NSInteger loopCount;
@property (nonatomic, assign) BOOL reverse;
@property (nonatomic, assign) BOOL autoPlay;
@property (nonatomic, strong) NSString *playbackDirection;

// Animation easing and interpolation
@property (nonatomic, strong) NSString *easing;
@property (nonatomic, strong) NSString *interpolation;
@property (nonatomic, assign) BOOL smoothing;
@property (nonatomic, strong) NSString *blendMode;

// Display properties
@property (nonatomic, assign) CGFloat width;
@property (nonatomic, assign) CGFloat height;
@property (nonatomic, strong) NSString *resizeMode;
@property (nonatomic, assign) CGFloat aspectRatio;
@property (nonatomic, assign) BOOL maintainAspectRatio;

// Material properties
@property (nonatomic, assign) CGFloat opacity;
@property (nonatomic, strong) NSArray<NSNumber *> *tintColor;
@property (nonatomic, assign) CGFloat brightness;
@property (nonatomic, assign) CGFloat contrast;
@property (nonatomic, assign) CGFloat saturation;
@property (nonatomic, strong) NSString *filterType;

// Performance and caching
@property (nonatomic, assign) BOOL preload;
@property (nonatomic, assign) BOOL cacheEnabled;
@property (nonatomic, assign) NSInteger cacheSize;
@property (nonatomic, assign) NSInteger maxCacheFrames;
@property (nonatomic, assign) BOOL streamingEnabled;
@property (nonatomic, assign) BOOL memoryWarningEnabled;

// Loading properties
@property (nonatomic, assign) CGFloat loadingTimeout;
@property (nonatomic, assign) NSInteger retryCount;
@property (nonatomic, assign) BOOL lazyLoading;
@property (nonatomic, assign) BOOL progressiveLoading;

// Event callbacks
@property (nonatomic, copy) RCTBubblingEventBlock onLoad;
@property (nonatomic, copy) RCTBubblingEventBlock onError;
@property (nonatomic, copy) RCTBubblingEventBlock onProgress;
@property (nonatomic, copy) RCTBubblingEventBlock onStart;
@property (nonatomic, copy) RCTBubblingEventBlock onStop;
@property (nonatomic, copy) RCTBubblingEventBlock onPause;
@property (nonatomic, copy) RCTBubblingEventBlock onResume;
@property (nonatomic, copy) RCTBubblingEventBlock onLoop;
@property (nonatomic, copy) RCTBubblingEventBlock onComplete;
@property (nonatomic, copy) RCTBubblingEventBlock onFrameChange;

// Internal animation state
@property (nonatomic, strong) NSArray<UIImage *> *animationFrames;
@property (nonatomic, strong) NSMutableDictionary<NSNumber *, UIImage *> *frameCache;
@property (nonatomic, strong) CADisplayLink *animationDisplayLink;
@property (nonatomic, assign) NSInteger currentFrame;
@property (nonatomic, assign) NSInteger totalFrames;
@property (nonatomic, assign) CGFloat currentTime;
@property (nonatomic, assign) CGFloat animationStartTime;
@property (nonatomic, assign) NSInteger currentLoopCount;
@property (nonatomic, assign) BOOL isPlaying;
@property (nonatomic, assign) BOOL isPaused;
@property (nonatomic, assign) BOOL isLoaded;
@property (nonatomic, assign) BOOL hasError;
@property (nonatomic, assign) CGFloat playbackRate;

// Sprite sheet state
@property (nonatomic, strong) UIImage *spriteSheetImage;
@property (nonatomic, assign) CGSize spriteFrameSize;

// Performance monitoring
@property (nonatomic, strong) NSMutableDictionary *performanceMetrics;
@property (nonatomic, assign) CFTimeInterval lastFrameTime;
@property (nonatomic, assign) NSInteger frameDropCount;

@end

@implementation ViroAnimatedImageComponentView

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
    _width = 1.0f;
    _height = 1.0f;
    _duration = 1.0f;
    _frameRate = 30.0f;
    _frameDuration = 1.0f / 30.0f;
    _speed = 1.0f;
    _delay = 0.0f;
    _startFrame = 0;
    _endFrame = -1;
    _paused = NO;
    _loop = YES;
    _loopCount = -1;
    _reverse = NO;
    _autoPlay = YES;
    _playbackDirection = @"forward";
    _smoothing = YES;
    _opacity = 1.0f;
    _brightness = 1.0f;
    _contrast = 1.0f;
    _saturation = 1.0f;
    _aspectRatio = 1.0f;
    _maintainAspectRatio = YES;
    _preload = NO;
    _cacheEnabled = YES;
    _cacheSize = 50;
    _maxCacheFrames = 100;
    _streamingEnabled = NO;
    _memoryWarningEnabled = YES;
    _loadingTimeout = 30.0f;
    _retryCount = 3;
    _lazyLoading = NO;
    _progressiveLoading = NO;
    _spriteColumns = 1;
    _spriteRows = 1;
    _frameCount = 1;
    _currentFrame = 0;
    _totalFrames = 0;
    _currentTime = 0.0f;
    _currentLoopCount = 0;
    _isPlaying = NO;
    _isPaused = NO;
    _isLoaded = NO;
    _hasError = NO;
    _playbackRate = 1.0f;
    
    // Initialize collections
    _frameCache = [NSMutableDictionary dictionary];
    _performanceMetrics = [NSMutableDictionary dictionary];
    
    // Initialize SceneKit components
    [self setupSceneKitComponents];
    
    // Setup memory warning handling
    if (_memoryWarningEnabled) {
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(handleMemoryWarning)
                                                     name:UIApplicationDidReceiveMemoryWarningNotification
                                                   object:nil];
    }
}

- (void)setupSceneKitComponents
{
    // Create plane geometry
    _planeGeometry = [SCNPlane planeWithWidth:_width height:_height];
    
    // Create material
    _imageMaterial = [SCNMaterial material];
    _imageMaterial.diffuse.contents = nil;
    _imageMaterial.lightingModelName = SCNLightingModelConstant;
    _imageMaterial.doubleSided = YES;
    
    // Create image node
    _imageNode = [SCNNode node];
    _imageNode.geometry = _planeGeometry;
    _imageNode.geometry.firstMaterial = _imageMaterial;
}

#pragma mark - Property Setters

- (void)setSource:(NSDictionary *)source
{
    _source = source;
    [self loadAnimation];
}

- (void)setUri:(NSString *)uri
{
    _uri = uri;
    [self loadAnimation];
}

- (void)setLocal:(NSString *)local
{
    _local = local;
    [self loadAnimation];
}

- (void)setResource:(NSString *)resource
{
    _resource = resource;
    [self loadAnimation];
}

- (void)setFrames:(NSArray<NSString *> *)frames
{
    _frames = frames;
    [self loadFrameSequence];
}

- (void)setFormat:(NSString *)format
{
    _format = format;
    [self updateAnimationFormat];
}

- (void)setAnimationType:(NSString *)animationType
{
    _animationType = animationType;
    [self updateAnimationFormat];
}

- (void)setSpriteSheet:(NSDictionary *)spriteSheet
{
    _spriteSheet = spriteSheet;
    [self loadSpriteSheet];
}

- (void)setSpriteColumns:(NSInteger)spriteColumns
{
    _spriteColumns = spriteColumns;
    [self updateSpriteSheetLayout];
}

- (void)setSpriteRows:(NSInteger)spriteRows
{
    _spriteRows = spriteRows;
    [self updateSpriteSheetLayout];
}

- (void)setFrameWidth:(NSInteger)frameWidth
{
    _frameWidth = frameWidth;
    [self updateSpriteSheetLayout];
}

- (void)setFrameHeight:(NSInteger)frameHeight
{
    _frameHeight = frameHeight;
    [self updateSpriteSheetLayout];
}

- (void)setFrameCount:(NSInteger)frameCount
{
    _frameCount = frameCount;
    _totalFrames = frameCount;
    [self updateSpriteSheetLayout];
}

- (void)setDuration:(CGFloat)duration
{
    _duration = duration;
    [self updateAnimationTiming];
}

- (void)setFrameRate:(CGFloat)frameRate
{
    _frameRate = frameRate;
    _frameDuration = 1.0f / frameRate;
    [self updateAnimationTiming];
}

- (void)setSpeed:(CGFloat)speed
{
    _speed = speed;
    [self updateAnimationTiming];
}

- (void)setPaused:(BOOL)paused
{
    _paused = paused;
    if (paused) {
        [self pause];
    } else {
        [self resume];
    }
}

- (void)setLoop:(BOOL)loop
{
    _loop = loop;
}

- (void)setAutoPlay:(BOOL)autoPlay
{
    _autoPlay = autoPlay;
    if (autoPlay && _isLoaded && !_isPlaying) {
        [self play];
    }
}

- (void)setWidth:(CGFloat)width
{
    _width = width;
    [self updateGeometry];
}

- (void)setHeight:(CGFloat)height
{
    _height = height;
    [self updateGeometry];
}

- (void)setOpacity:(CGFloat)opacity
{
    _opacity = opacity;
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

#pragma mark - Animation Loading

- (void)loadAnimation
{
    NSString *animationPath = [self resolveAnimationPath];
    if (!animationPath) {
        return;
    }
    
    _isLoaded = NO;
    _hasError = NO;
    
    if (_onProgress) {
        _onProgress(@{@"progress": @(0.0f)});
    }
    
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        [self loadAnimationFromPath:animationPath];
    });
}

- (NSString *)resolveAnimationPath
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

- (void)loadAnimationFromPath:(NSString *)path
{
    NSURL *url = [NSURL fileURLWithPath:path];
    if ([path hasPrefix:@"http"]) {
        url = [NSURL URLWithString:path];
    }
    
    NSData *data = [NSData dataWithContentsOfURL:url];
    if (!data) {
        [self handleLoadError:[NSError errorWithDomain:@"ViroAnimatedImage" 
                                                  code:1001 
                                              userInfo:@{NSLocalizedDescriptionKey: @"Failed to load animation data"}]];
        return;
    }
    
    // Check if it's a GIF
    if ([self isGIFData:data]) {
        [self loadGIFFromData:data];
    } else {
        // Load as static image
        UIImage *image = [UIImage imageWithData:data];
        if (image) {
            [self loadStaticImage:image];
        } else {
            [self handleLoadError:[NSError errorWithDomain:@"ViroAnimatedImage" 
                                                      code:1002 
                                                  userInfo:@{NSLocalizedDescriptionKey: @"Failed to decode image"}]];
        }
    }
}

- (BOOL)isGIFData:(NSData *)data
{
    if (data.length < 6) return NO;
    
    const unsigned char *bytes = (const unsigned char *)data.bytes;
    return (bytes[0] == 0x47 && bytes[1] == 0x49 && bytes[2] == 0x46);
}

- (void)loadGIFFromData:(NSData *)data
{
    CGImageSourceRef source = CGImageSourceCreateWithData((__bridge CFDataRef)data, NULL);
    if (!source) {
        [self handleLoadError:[NSError errorWithDomain:@"ViroAnimatedImage" 
                                                  code:1003 
                                              userInfo:@{NSLocalizedDescriptionKey: @"Failed to create image source"}]];
        return;
    }
    
    size_t frameCount = CGImageSourceGetCount(source);
    if (frameCount == 0) {
        CFRelease(source);
        [self handleLoadError:[NSError errorWithDomain:@"ViroAnimatedImage" 
                                                  code:1004 
                                              userInfo:@{NSLocalizedDescriptionKey: @"No frames in animation"}]];
        return;
    }
    
    NSMutableArray *frames = [NSMutableArray arrayWithCapacity:frameCount];
    CGFloat totalDuration = 0.0f;
    
    for (size_t i = 0; i < frameCount; i++) {
        CGImageRef frameImage = CGImageSourceCreateImageAtIndex(source, i, NULL);
        if (frameImage) {
            UIImage *frame = [UIImage imageWithCGImage:frameImage];
            [frames addObject:frame];
            CGImageRelease(frameImage);
            
            // Get frame duration
            CFDictionaryRef frameProperties = CGImageSourceCopyPropertiesAtIndex(source, i, NULL);
            if (frameProperties) {
                CFDictionaryRef gifProperties = CFDictionaryGetValue(frameProperties, kCGImagePropertyGIFDictionary);
                if (gifProperties) {
                    CFNumberRef delayTime = CFDictionaryGetValue(gifProperties, kCGImagePropertyGIFDelayTime);
                    if (delayTime) {
                        float delay;
                        CFNumberGetValue(delayTime, kCFNumberFloatType, &delay);
                        totalDuration += delay;
                    }
                }
                CFRelease(frameProperties);
            }
        }
    }
    
    CFRelease(source);
    
    if (frames.count > 0) {
        _totalFrames = (NSInteger)frames.count;
        _duration = totalDuration > 0 ? totalDuration : (CGFloat)frames.count * _frameDuration;
        _frameRate = frames.count / _duration;
        _frameDuration = _duration / frames.count;
        
        dispatch_async(dispatch_get_main_queue(), ^{
            self.animationFrames = frames;
            [self handleAnimationLoaded];
        });
    } else {
        [self handleLoadError:[NSError errorWithDomain:@"ViroAnimatedImage" 
                                                  code:1005 
                                              userInfo:@{NSLocalizedDescriptionKey: @"No valid frames extracted"}]];
    }
}

- (void)loadStaticImage:(UIImage *)image
{
    _totalFrames = 1;
    _duration = 1.0f;
    _frameRate = 1.0f;
    _frameDuration = 1.0f;
    
    dispatch_async(dispatch_get_main_queue(), ^{
        self.animationFrames = @[image];
        [self handleAnimationLoaded];
    });
}

- (void)loadFrameSequence
{
    if (!_frames || _frames.count == 0) {
        return;
    }
    
    NSMutableArray *frameImages = [NSMutableArray arrayWithCapacity:_frames.count];
    
    for (NSString *framePath in _frames) {
        UIImage *frameImage = [UIImage imageNamed:framePath];
        if (frameImage) {
            [frameImages addObject:frameImage];
        }
    }
    
    if (frameImages.count > 0) {
        _totalFrames = (NSInteger)frameImages.count;
        _animationFrames = frameImages;
        [self handleAnimationLoaded];
    }
}

- (void)loadSpriteSheet
{
    if (!_spriteSheet) {
        return;
    }
    
    NSString *spriteSheetPath = _spriteSheet[@"uri"] ?: _spriteSheet[@"local"] ?: _spriteSheet[@"resource"];
    if (!spriteSheetPath) {
        return;
    }
    
    UIImage *spriteImage = [UIImage imageNamed:spriteSheetPath];
    if (!spriteImage) {
        return;
    }
    
    _spriteSheetImage = spriteImage;
    
    // Calculate sprite frame size if not provided
    if (_frameWidth == 0 || _frameHeight == 0) {
        _spriteFrameSize = CGSizeMake(spriteImage.size.width / _spriteColumns,
                                     spriteImage.size.height / _spriteRows);
    } else {
        _spriteFrameSize = CGSizeMake(_frameWidth, _frameHeight);
    }
    
    // Calculate total frames
    _totalFrames = _spriteColumns * _spriteRows;
    if (_frameCount > 0) {
        _totalFrames = _frameCount;
    }
    
    [self extractSpriteFrames];
}

- (void)extractSpriteFrames
{
    if (!_spriteSheetImage) {
        return;
    }
    
    NSMutableArray *frames = [NSMutableArray arrayWithCapacity:_totalFrames];
    
    for (NSInteger i = 0; i < _totalFrames; i++) {
        UIImage *frameImage = [self extractSpriteFrame:i];
        if (frameImage) {
            [frames addObject:frameImage];
        }
    }
    
    if (frames.count > 0) {
        _animationFrames = frames;
        [self handleAnimationLoaded];
    }
}

- (UIImage *)extractSpriteFrame:(NSInteger)frameIndex
{
    if (!_spriteSheetImage || frameIndex >= _totalFrames) {
        return nil;
    }
    
    NSInteger col = frameIndex % _spriteColumns;
    NSInteger row = frameIndex / _spriteColumns;
    
    CGRect frameRect = CGRectMake(col * _spriteFrameSize.width,
                                 row * _spriteFrameSize.height,
                                 _spriteFrameSize.width,
                                 _spriteFrameSize.height);
    
    CGImageRef frameImageRef = CGImageCreateWithImageInRect(_spriteSheetImage.CGImage, frameRect);
    if (!frameImageRef) {
        return nil;
    }
    
    UIImage *frameImage = [UIImage imageWithCGImage:frameImageRef];
    CGImageRelease(frameImageRef);
    
    return frameImage;
}

- (void)handleAnimationLoaded
{
    _isLoaded = YES;
    _hasError = NO;
    
    if (_endFrame < 0) {
        _endFrame = _totalFrames - 1;
    }
    
    [self updateGeometry];
    [self updateMaterialProperties];
    
    if (_onLoad) {
        _onLoad(@{
            @"frameCount": @(_totalFrames),
            @"duration": @(_duration),
            @"frameRate": @(_frameRate),
            @"width": @(_animationFrames.firstObject.size.width),
            @"height": @(_animationFrames.firstObject.size.height)
        });
    }
    
    if (_autoPlay) {
        [self play];
    }
}

- (void)handleLoadError:(NSError *)error
{
    _isLoaded = NO;
    _hasError = YES;
    
    dispatch_async(dispatch_get_main_queue(), ^{
        if (self.onError) {
            self.onError(@{
                @"error": error.localizedDescription,
                @"code": @(error.code)
            });
        }
    });
}

#pragma mark - Animation Control

- (void)play
{
    if (!_isLoaded || _isPlaying) {
        return;
    }
    
    _isPlaying = YES;
    _isPaused = NO;
    _animationStartTime = CACurrentMediaTime();
    
    [self startAnimationTimer];
    
    if (_onStart) {
        _onStart(@{@"frame": @(_currentFrame)});
    }
}

- (void)pause
{
    if (!_isPlaying) {
        return;
    }
    
    _isPlaying = NO;
    _isPaused = YES;
    
    [self stopAnimationTimer];
    
    if (_onPause) {
        _onPause(@{@"frame": @(_currentFrame)});
    }
}

- (void)stop
{
    _isPlaying = NO;
    _isPaused = NO;
    _currentFrame = _startFrame;
    _currentTime = 0.0f;
    _currentLoopCount = 0;
    
    [self stopAnimationTimer];
    [self updateCurrentFrame];
    
    if (_onStop) {
        _onStop(@{@"frame": @(_currentFrame)});
    }
}

- (void)restart
{
    [self stop];
    [self play];
}

- (void)resume
{
    if (!_isPaused) {
        return;
    }
    
    _isPlaying = YES;
    _isPaused = NO;
    
    [self startAnimationTimer];
    
    if (_onResume) {
        _onResume(@{@"frame": @(_currentFrame)});
    }
}

- (void)seek:(CGFloat)time
{
    if (!_isLoaded || _duration == 0) {
        return;
    }
    
    _currentTime = MAX(0, MIN(time, _duration));
    NSInteger targetFrame = (NSInteger)(_currentTime / _frameDuration);
    [self seekToFrame:targetFrame];
}

- (void)seekToFrame:(NSInteger)frame
{
    if (!_isLoaded) {
        return;
    }
    
    _currentFrame = MAX(_startFrame, MIN(frame, _endFrame));
    _currentTime = _currentFrame * _frameDuration;
    
    [self updateCurrentFrame];
    
    if (_onFrameChange) {
        _onFrameChange(@{
            @"frame": @(_currentFrame),
            @"time": @(_currentTime),
            @"progress": @([self getProgress])
        });
    }
}

- (void)seekToProgress:(CGFloat)progress
{
    if (!_isLoaded) {
        return;
    }
    
    CGFloat targetTime = progress * _duration;
    [self seek:targetTime];
}

- (void)nextFrame
{
    if (!_isLoaded) {
        return;
    }
    
    NSInteger nextFrame = _currentFrame + 1;
    if (nextFrame > _endFrame) {
        if (_loop) {
            nextFrame = _startFrame;
        } else {
            nextFrame = _endFrame;
        }
    }
    
    [self seekToFrame:nextFrame];
}

- (void)previousFrame
{
    if (!_isLoaded) {
        return;
    }
    
    NSInteger prevFrame = _currentFrame - 1;
    if (prevFrame < _startFrame) {
        if (_loop) {
            prevFrame = _endFrame;
        } else {
            prevFrame = _startFrame;
        }
    }
    
    [self seekToFrame:prevFrame];
}

- (void)setPlaybackRate:(CGFloat)rate
{
    _playbackRate = rate;
    _speed = rate;
    [self updateAnimationTiming];
}

#pragma mark - Animation Timer

- (void)startAnimationTimer
{
    [self stopAnimationTimer];
    
    _animationDisplayLink = [CADisplayLink displayLinkWithTarget:self selector:@selector(updateAnimation)];
    [_animationDisplayLink addToRunLoop:[NSRunLoop mainRunLoop] forMode:NSRunLoopCommonModes];
}

- (void)stopAnimationTimer
{
    if (_animationDisplayLink) {
        [_animationDisplayLink invalidate];
        _animationDisplayLink = nil;
    }
}

- (void)updateAnimation
{
    if (!_isPlaying || !_isLoaded) {
        return;
    }
    
    CFTimeInterval currentTime = CACurrentMediaTime();
    CFTimeInterval deltaTime = currentTime - _lastFrameTime;
    _lastFrameTime = currentTime;
    
    // Update animation time
    _currentTime += deltaTime * _speed;
    
    // Calculate current frame
    NSInteger targetFrame = (NSInteger)(_currentTime / _frameDuration);
    
    // Handle playback direction
    if ([_playbackDirection isEqualToString:@"reverse"]) {
        targetFrame = _endFrame - targetFrame;
    }
    
    // Handle looping
    if (targetFrame > _endFrame) {
        if (_loop) {
            if (_loopCount > 0 && _currentLoopCount >= _loopCount) {
                [self handleAnimationComplete];
                return;
            }
            
            _currentLoopCount++;
            _currentTime = 0.0f;
            targetFrame = _startFrame;
            
            if (_onLoop) {
                _onLoop(@{@"loopCount": @(_currentLoopCount)});
            }
        } else {
            [self handleAnimationComplete];
            return;
        }
    }
    
    // Update frame if changed
    if (targetFrame != _currentFrame) {
        _currentFrame = targetFrame;
        [self updateCurrentFrame];
        
        if (_onFrameChange) {
            _onFrameChange(@{
                @"frame": @(_currentFrame),
                @"time": @(_currentTime),
                @"progress": @([self getProgress])
            });
        }
    }
}

- (void)updateCurrentFrame
{
    if (!_animationFrames || _currentFrame >= _animationFrames.count) {
        return;
    }
    
    UIImage *frameImage = _animationFrames[_currentFrame];
    _imageMaterial.diffuse.contents = frameImage;
}

- (void)handleAnimationComplete
{
    _isPlaying = NO;
    _currentFrame = _endFrame;
    [self stopAnimationTimer];
    
    if (_onComplete) {
        _onComplete(@{
            @"frame": @(_currentFrame),
            @"loopCount": @(_currentLoopCount)
        });
    }
}

#pragma mark - Geometry and Material Updates

- (void)updateGeometry
{
    if (!_planeGeometry) {
        return;
    }
    
    CGFloat width = _width;
    CGFloat height = _height;
    
    // Maintain aspect ratio if enabled
    if (_maintainAspectRatio && _animationFrames.count > 0) {
        UIImage *firstFrame = _animationFrames.firstObject;
        CGFloat imageAspectRatio = firstFrame.size.width / firstFrame.size.height;
        
        if (_aspectRatio > 0) {
            height = width / _aspectRatio;
        } else {
            height = width / imageAspectRatio;
        }
    }
    
    _planeGeometry.width = width;
    _planeGeometry.height = height;
}

- (void)updateMaterialProperties
{
    if (!_imageMaterial) {
        return;
    }
    
    _imageMaterial.diffuse.intensity = _brightness;
    _imageMaterial.transparency = _opacity;
    
    if (_tintColor && _tintColor.count >= 3) {
        CGFloat r = [_tintColor[0] floatValue];
        CGFloat g = [_tintColor[1] floatValue];
        CGFloat b = [_tintColor[2] floatValue];
        CGFloat a = _tintColor.count > 3 ? [_tintColor[3] floatValue] : 1.0f;
        _imageMaterial.multiply.contents = [UIColor colorWithRed:r green:g blue:b alpha:a];
    }
}

- (void)updateAnimationTiming
{
    if (_frameRate > 0) {
        _frameDuration = 1.0f / (_frameRate * _speed);
    }
    
    if (_totalFrames > 0) {
        _duration = _totalFrames * _frameDuration;
    }
}

- (void)updateAnimationFormat
{
    // Handle different animation formats
    if ([_format isEqualToString:@"gif"]) {
        // GIF-specific handling
    } else if ([_format isEqualToString:@"spritesheet"]) {
        [self loadSpriteSheet];
    } else if ([_format isEqualToString:@"sequence"]) {
        [self loadFrameSequence];
    }
}

- (void)updateSpriteSheetLayout
{
    if (_spriteSheetImage) {
        [self extractSpriteFrames];
    }
}

#pragma mark - State Information

- (BOOL)isPlaying
{
    return _isPlaying;
}

- (BOOL)isPaused
{
    return _isPaused;
}

- (BOOL)isLoaded
{
    return _isLoaded;
}

- (BOOL)hasError
{
    return _hasError;
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
    if (_duration == 0) {
        return 0.0f;
    }
    return _currentTime / _duration;
}

- (NSInteger)getCurrentFrame
{
    return _currentFrame;
}

- (NSInteger)getTotalFrames
{
    return _totalFrames;
}

- (CGFloat)getFrameRate
{
    return _frameRate;
}

- (NSDictionary *)getAnimationInfo
{
    return @{
        @"isPlaying": @(_isPlaying),
        @"isPaused": @(_isPaused),
        @"isLoaded": @(_isLoaded),
        @"hasError": @(_hasError),
        @"currentFrame": @(_currentFrame),
        @"totalFrames": @(_totalFrames),
        @"currentTime": @(_currentTime),
        @"duration": @(_duration),
        @"progress": @([self getProgress]),
        @"frameRate": @(_frameRate),
        @"loopCount": @(_currentLoopCount)
    };
}

#pragma mark - Frame Management

- (void)preloadFrames
{
    // Preload frames into cache
    for (NSInteger i = 0; i < _animationFrames.count; i++) {
        _frameCache[@(i)] = _animationFrames[i];
    }
}

- (void)clearFrameCache
{
    [_frameCache removeAllObjects];
}

- (void)optimizeFrameCache
{
    // Remove frames that are far from current frame
    NSInteger cacheWindow = 10;
    NSArray *keys = [_frameCache.allKeys copy];
    
    for (NSNumber *frameNumber in keys) {
        NSInteger frame = frameNumber.integerValue;
        if (abs((int)(frame - _currentFrame)) > cacheWindow) {
            [_frameCache removeObjectForKey:frameNumber];
        }
    }
}

- (UIImage *)getFrameAtIndex:(NSInteger)index
{
    if (index < 0 || index >= _animationFrames.count) {
        return nil;
    }
    return _animationFrames[index];
}

- (NSArray<UIImage *> *)getAllFrames
{
    return [_animationFrames copy];
}

- (NSInteger)getFrameCacheSize
{
    return _frameCache.count;
}

- (NSInteger)getMemoryUsage
{
    NSInteger totalMemory = 0;
    for (UIImage *image in _animationFrames) {
        totalMemory += image.size.width * image.size.height * 4; // Assuming RGBA
    }
    return totalMemory;
}

#pragma mark - Memory Management

- (void)handleMemoryWarning
{
    [self optimizeFrameCache];
    
    // Release non-essential frames
    if (_frameCache.count > _maxCacheFrames) {
        [self clearFrameCache];
    }
}

- (void)optimizeMemoryUsage
{
    [self optimizeFrameCache];
}

- (void)releaseUnusedFrames
{
    [self optimizeFrameCache];
}

#pragma mark - Cleanup

- (void)dealloc
{
    [self stopAnimationTimer];
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

@end