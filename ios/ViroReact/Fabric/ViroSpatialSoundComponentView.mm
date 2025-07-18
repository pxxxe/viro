//
//  ViroSpatialSoundComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroSpatialSoundComponentView.h"
#import <React/RCTAssert.h>
#import <React/RCTUtils.h>
#import <React/RCTLog.h>
#import <AVFoundation/AVFoundation.h>
#import <SceneKit/SceneKit.h>

@interface ViroSpatialSoundComponentView ()

// Audio components
@property (nonatomic, strong) AVAudioEngine *audioEngine;
@property (nonatomic, strong) AVAudioPlayerNode *playerNode;
@property (nonatomic, strong) AVAudioFile *audioFile;
@property (nonatomic, strong) AVAudio3DMixerNode *spatialMixer;
@property (nonatomic, strong) AVAudioUnitReverb *reverbUnit;

// SceneKit audio node
@property (nonatomic, strong) SCNNode *soundNode;
@property (nonatomic, strong) SCNAudioSource *audioSource;

// State management
@property (nonatomic, assign) BOOL isLoaded;
@property (nonatomic, assign) BOOL isPlaying;
@property (nonatomic, assign) CGFloat currentTime;
@property (nonatomic, assign) CGFloat duration;

@end

@implementation ViroSpatialSoundComponentView

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
    _paused = YES;
    _loop = NO;
    _muted = NO;
    _volume = 1.0f;
    _rate = 1.0f;
    _seekTime = 0.0f;
    _position = @[@0, @0, @0];
    _distanceFilter = 0.0f;
    _rolloffFactor = 1.0f;
    _minDistance = 1.0f;
    _maxDistance = 100.0f;
    
    // Initialize state
    _isLoaded = NO;
    _isPlaying = NO;
    _currentTime = 0.0f;
    _duration = 0.0f;
    
    // Setup audio components
    [self setupAudioEngine];
    [self setupSceneKitComponents];
}

- (void)setupAudioEngine
{
    _audioEngine = [[AVAudioEngine alloc] init];
    _playerNode = [[AVAudioPlayerNode alloc] init];
    _spatialMixer = [[AVAudio3DMixerNode alloc] init];
    _reverbUnit = [[AVAudioUnitReverb alloc] init];
    
    // Connect audio nodes
    [_audioEngine attachNode:_playerNode];
    [_audioEngine attachNode:_spatialMixer];
    [_audioEngine attachNode:_reverbUnit];
    
    // Connect the chain
    [_audioEngine connect:_playerNode to:_spatialMixer format:nil];
    [_audioEngine connect:_spatialMixer to:_reverbUnit format:nil];
    [_audioEngine connect:_reverbUnit to:_audioEngine.mainMixerNode format:nil];
    
    // Configure spatial mixer
    _spatialMixer.renderingAlgorithm = AVAudio3DMixingRenderingAlgorithmHRTFHQ;
    _spatialMixer.sourceMode = AVAudio3DMixingSourceModeAmbienceBed;
    _spatialMixer.pointSourceInHeadMode = AVAudio3DMixingPointSourceInHeadModeMono;
    
    // Configure reverb
    _reverbUnit.wetDryMix = 15.0f;
    _reverbUnit.loadFactoryPreset:AVAudioUnitReverbPresetMediumRoom;
    
    // Start audio engine
    NSError *error;
    [_audioEngine startAndReturnError:&error];
    if (error) {
        NSLog(@"Audio engine start error: %@", error);
    }
}

- (void)setupSceneKitComponents
{
    _soundNode = [SCNNode node];
    _soundNode.name = @"ViroSpatialSound";
    
    // Set initial position
    [self updateSpatialPosition];
}

#pragma mark - Property Setters

- (void)setSource:(NSDictionary *)source
{
    _source = source;
    [self loadAudio];
}

- (void)setUri:(NSString *)uri
{
    _uri = uri;
    [self loadAudio];
}

- (void)setLocal:(NSString *)local
{
    _local = local;
    [self loadAudio];
}

- (void)setResource:(NSString *)resource
{
    _resource = resource;
    [self loadAudio];
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
    [self updatePlayerSettings];
}

- (void)setMuted:(BOOL)muted
{
    _muted = muted;
    [self updatePlayerSettings];
}

- (void)setVolume:(CGFloat)volume
{
    _volume = volume;
    [self updatePlayerSettings];
}

- (void)setRate:(CGFloat)rate
{
    _rate = rate;
    [self updatePlayerSettings];
}

- (void)setSeekTime:(CGFloat)seekTime
{
    _seekTime = seekTime;
    [self seek:seekTime];
}

- (void)setPosition:(NSArray<NSNumber *> *)position
{
    _position = position;
    [self updateSpatialPosition];
}

- (void)setDistanceFilter:(CGFloat)distanceFilter
{
    _distanceFilter = distanceFilter;
    [self updateSpatialSettings];
}

- (void)setRolloffFactor:(CGFloat)rolloffFactor
{
    _rolloffFactor = rolloffFactor;
    [self updateSpatialSettings];
}

- (void)setMinDistance:(CGFloat)minDistance
{
    _minDistance = minDistance;
    [self updateSpatialSettings];
}

- (void)setMaxDistance:(CGFloat)maxDistance
{
    _maxDistance = maxDistance;
    [self updateSpatialSettings];
}

#pragma mark - Audio Loading

- (void)loadAudio
{
    NSString *audioPath = [self resolveAudioPath];
    if (!audioPath) {
        return;
    }
    
    NSURL *audioURL = [NSURL fileURLWithPath:audioPath];
    if (!audioURL) {
        audioURL = [NSURL URLWithString:audioPath];
    }
    
    NSError *error;
    _audioFile = [[AVAudioFile alloc] initForReading:audioURL error:&error];
    
    if (error) {
        if (_onError) {
            _onError(@{
                @"error": error.localizedDescription,
                @"code": @(error.code)
            });
        }
        return;
    }
    
    // Create SceneKit audio source
    _audioSource = [[SCNAudioSource alloc] initWithAVAudioFile:_audioFile];
    _audioSource.loops = _loop;
    _audioSource.volume = _volume;
    _audioSource.rate = _rate;
    _audioSource.positional = YES;
    _audioSource.reverbBlend = 0.3f;
    _audioSource.load;
    
    // Update state
    _isLoaded = YES;
    _duration = (CGFloat)_audioFile.length / (CGFloat)_audioFile.processingFormat.sampleRate;
    
    // Setup spatial audio properties
    [self updateSpatialSettings];
    
    // Auto-play if not paused
    if (!_paused) {
        [self play];
    }
}

- (NSString *)resolveAudioPath
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

#pragma mark - Audio Control

- (void)play
{
    if (!_isLoaded) {
        return;
    }
    
    if (_audioSource && _soundNode) {
        // Remove existing audio player
        [_soundNode removeAllAudioPlayers];
        
        // Create new audio player
        SCNAudioPlayer *player = [SCNAudioPlayer audioPlayerWithSource:_audioSource];
        [_soundNode addAudioPlayer:player];
        
        // Start playback
        [player play];
        
        _isPlaying = YES;
        
        if (_onProgress) {
            // Start progress timer
            [self startProgressTimer];
        }
    }
}

- (void)pause
{
    if (_soundNode) {
        for (SCNAudioPlayer *player in _soundNode.audioPlayers) {
            [player pause];
        }
        _isPlaying = NO;
    }
}

- (void)stop
{
    if (_soundNode) {
        for (SCNAudioPlayer *player in _soundNode.audioPlayers) {
            [player stop];
        }
        _isPlaying = NO;
        _currentTime = 0.0f;
    }
}

- (void)seek:(CGFloat)time
{
    // SceneKit doesn't support seeking directly, would need custom implementation
    // For now, restart from beginning
    if (_isPlaying) {
        [self stop];
        [self play];
    }
}

#pragma mark - Spatial Audio Updates

- (void)updateSpatialPosition
{
    if (_soundNode && _position.count >= 3) {
        float x = [_position[0] floatValue];
        float y = [_position[1] floatValue];
        float z = [_position[2] floatValue];
        
        _soundNode.position = SCNVector3Make(x, y, z);
        
        // Update 3D mixer position
        if (_spatialMixer) {
            AVAudio3DPoint position = AVAudioMake3DPoint(x, y, z);
            _spatialMixer.position = position;
        }
    }
}

- (void)updateSpatialSettings
{
    if (_spatialMixer) {
        // Set distance attenuation
        _spatialMixer.distanceType = AVAudio3DMixingDistanceTypeLinear;
        _spatialMixer.attenuationCurve = AVAudio3DMixingAttenuationCurveLinear;
        
        // Configure distance parameters
        _spatialMixer.rate = _rolloffFactor;
        
        // Apply distance filter
        if (_distanceFilter > 0) {
            _spatialMixer.sourceMode = AVAudio3DMixingSourceModeAmbienceBed;
        }
    }
}

- (void)updatePlayerSettings
{
    if (_audioSource) {
        _audioSource.loops = _loop;
        _audioSource.volume = _muted ? 0.0f : _volume;
        _audioSource.rate = _rate;
    }
    
    if (_spatialMixer) {
        _spatialMixer.globalGain = _muted ? 0.0f : _volume;
    }
}

- (void)startProgressTimer
{
    // Simple progress simulation - in real implementation would track actual playback
    dispatch_async(dispatch_get_main_queue(), ^{
        [NSTimer scheduledTimerWithTimeInterval:0.1 repeats:YES block:^(NSTimer * _Nonnull timer) {
            if (!self->_isPlaying) {
                [timer invalidate];
                return;
            }
            
            self->_currentTime += 0.1f;
            
            if (self->_currentTime >= self->_duration) {
                self->_currentTime = self->_duration;
                self->_isPlaying = NO;
                [timer invalidate];
                
                if (self->_onFinish) {
                    self->_onFinish(@{});
                }
                
                if (self->_loop) {
                    self->_currentTime = 0.0f;
                    [self play];
                }
            }
            
            if (self->_onProgress) {
                self->_onProgress(@{
                    @"currentTime": @(self->_currentTime),
                    @"duration": @(self->_duration)
                });
            }
        }];
    });
}

#pragma mark - Public Methods

- (SCNNode *)getSoundNode
{
    return _soundNode;
}

- (SCNAudioSource *)getAudioSource
{
    return _audioSource;
}

- (AVAudio3DMixerNode *)getSpatialMixer
{
    return _spatialMixer;
}

- (BOOL)isLoaded
{
    return _isLoaded;
}

- (BOOL)isPlaying
{
    return _isPlaying;
}

- (CGFloat)getCurrentTime
{
    return _currentTime;
}

- (CGFloat)getDuration
{
    return _duration;
}

- (void)dealloc
{
    [self stop];
    [_audioEngine stop];
}

@end