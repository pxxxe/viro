//
//  ViroSoundComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroSoundComponentView.h"
#import "ViroReactUtils.h"
#import "ViroLog.h"
#import <React/RCTConversions.h>
#import <React/RCTLog.h>
#import <AVFoundation/AVFoundation.h>
#import <AudioToolbox/AudioToolbox.h>

typedef NS_ENUM(NSInteger, ViroSoundState) {
    ViroSoundStateIdle = 0,
    ViroSoundStateLoading,
    ViroSoundStateReady,
    ViroSoundStatePlaying,
    ViroSoundStatePaused,
    ViroSoundStateStopped,
    ViroSoundStateError
};

@implementation ViroSoundComponentView {
    // Audio source
    NSDictionary *_source;
    NSString *_uri;
    NSString *_local;
    NSString *_resource;
    
    // Playback control
    BOOL _paused;
    BOOL _loop;
    BOOL _muted;
    CGFloat _volume;
    CGFloat _rate;
    CGFloat _pitch;
    CGFloat _seekTime;
    
    // Audio properties
    NSString *_audioFormat;
    NSInteger _channels;
    NSInteger _sampleRate;
    NSInteger _bitRate;
    NSString *_quality;
    
    // 3D spatial audio
    NSArray<NSNumber *> *_position;
    NSString *_rolloffModel;
    NSString *_distanceModel;
    CGFloat _maxDistance;
    CGFloat _referenceDistance;
    CGFloat _rolloffFactor;
    CGFloat _coneInnerAngle;
    CGFloat _coneOuterAngle;
    CGFloat _coneOuterGain;
    
    // Audio effects
    NSDictionary *_reverb;
    NSDictionary *_delay;
    NSDictionary *_echo;
    NSDictionary *_distortion;
    NSDictionary *_equalizer;
    NSArray<NSDictionary *> *_filters;
    
    // Crossfading
    NSDictionary *_crossfade;
    CGFloat _fadeIn;
    CGFloat _fadeOut;
    NSString *_fadeInCurve;
    NSString *_fadeOutCurve;
    
    // Audio analysis
    BOOL _analysisEnabled;
    CGFloat _analysisInterval;
    NSInteger _analysisFrequencyBands;
    CGFloat _analysisTimeConstant;
    CGFloat _analysisSmoothing;
    
    // Performance
    BOOL _preload;
    NSInteger _bufferSize;
    BOOL _cacheEnabled;
    BOOL _streamingEnabled;
    BOOL _backgroundPlayback;
    
    // Internal state
    ViroSoundState _soundState;
    AVAudioPlayer *_audioPlayer;
    AVAudioEngine *_audioEngine;
    AVAudioPlayerNode *_playerNode;
    AVAudioMixerNode *_mixerNode;
    AVAudioUnitReverb *_reverbUnit;
    AVAudioUnitDelay *_delayUnit;
    AVAudioUnitEQ *_eqUnit;
    AVAudioUnitDistortion *_distortionUnit;
    AVAudioFile *_audioFile;
    NSURL *_audioURL;
    NSTimer *_progressTimer;
    NSTimer *_analysisTimer;
    CGFloat _duration;
    CGFloat _currentTime;
    NSError *_lastError;
    
    // Event blocks
    RCTBubblingEventBlock _onLoad;
    RCTBubblingEventBlock _onError;
    RCTBubblingEventBlock _onPlay;
    RCTBubblingEventBlock _onPause;
    RCTBubblingEventBlock _onStop;
    RCTBubblingEventBlock _onFinish;
    RCTBubblingEventBlock _onSeek;
    RCTBubblingEventBlock _onProgress;
    RCTBubblingEventBlock _onVolumeChange;
    RCTBubblingEventBlock _onRateChange;
    RCTBubblingEventBlock _onAnalysis;
}

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        static const auto defaultProps = std::make_shared<const facebook::react::ViroSoundProps>();
        _props = defaultProps;
        
        // Initialize playback control
        _paused = YES;
        _loop = NO;
        _muted = NO;
        _volume = 1.0f;
        _rate = 1.0f;
        _pitch = 1.0f;
        _seekTime = 0.0f;
        
        // Initialize audio properties
        _audioFormat = @"auto";
        _channels = 2;
        _sampleRate = 44100;
        _bitRate = 0;
        _quality = @"medium";
        
        // Initialize 3D spatial audio
        _position = @[@(0.0), @(0.0), @(0.0)];
        _rolloffModel = @"inverse";
        _distanceModel = @"inverse";
        _maxDistance = 100.0f;
        _referenceDistance = 1.0f;
        _rolloffFactor = 1.0f;
        _coneInnerAngle = 360.0f;
        _coneOuterAngle = 360.0f;
        _coneOuterGain = 0.0f;
        
        // Initialize crossfading
        _fadeIn = 0.0f;
        _fadeOut = 0.0f;
        _fadeInCurve = @"linear";
        _fadeOutCurve = @"linear";
        
        // Initialize audio analysis
        _analysisEnabled = NO;
        _analysisInterval = 1.0f / 30.0f;
        _analysisFrequencyBands = 32;
        _analysisTimeConstant = 0.8f;
        _analysisSmoothing = 0.8f;
        
        // Initialize performance
        _preload = YES;
        _bufferSize = 4096;
        _cacheEnabled = YES;
        _streamingEnabled = NO;
        _backgroundPlayback = NO;
        
        // Initialize internal state
        _soundState = ViroSoundStateIdle;
        _duration = 0.0f;
        _currentTime = 0.0f;
        
        // Set up audio session
        [self setupAudioSession];
        
        // Set up audio engine
        [self setupAudioEngine];
        
        VRTLogDebug(@"ViroSound initialized");
    }
    return self;
}

#pragma mark - RCTComponentViewProtocol

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<facebook::react::ViroSoundComponentDescriptor>();
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
    const auto &viroProps = *std::static_pointer_cast<facebook::react::ViroSoundProps const>(props);
    const auto &oldViroProps = *std::static_pointer_cast<facebook::react::ViroSoundProps const>(oldProps);
    
    [super updateProps:props oldProps:oldProps];
    
    // TODO: Update properties from viroProps
    // This will be implemented when Fabric code generation is complete
    VRTLogDebug(@"ViroSound props updated");
}

#pragma mark - Audio Source

- (void)setSource:(nullable NSDictionary *)source {
    VRTLogDebug(@"Setting source: %@", source);
    _source = source;
    [self loadAudioFromSource];
}

- (void)setUri:(nullable NSString *)uri {
    VRTLogDebug(@"Setting uri: %@", uri);
    _uri = uri;
    [self loadAudioFromURI];
}

- (void)setLocal:(nullable NSString *)local {
    VRTLogDebug(@"Setting local: %@", local);
    _local = local;
    [self loadAudioFromLocal];
}

- (void)setResource:(nullable NSString *)resource {
    VRTLogDebug(@"Setting resource: %@", resource);
    _resource = resource;
    [self loadAudioFromResource];
}

#pragma mark - Playback Control

- (void)setPaused:(BOOL)paused {
    VRTLogDebug(@"Setting paused: %d", paused);
    _paused = paused;
    
    if (paused) {
        [self pause];
    } else {
        [self play];
    }
}

- (void)setLoop:(BOOL)loop {
    VRTLogDebug(@"Setting loop: %d", loop);
    _loop = loop;
    
    if (_audioPlayer) {
        _audioPlayer.numberOfLoops = loop ? -1 : 0;
    }
}

- (void)setMuted:(BOOL)muted {
    VRTLogDebug(@"Setting muted: %d", muted);
    _muted = muted;
    [self updateVolume];
}

- (void)setVolume:(CGFloat)volume {
    VRTLogDebug(@"Setting volume: %.2f", volume);
    _volume = MAX(0.0f, MIN(1.0f, volume));
    [self updateVolume];
    
    if (_onVolumeChange) {
        _onVolumeChange(@{@"volume": @(_volume)});
    }
}

- (void)setRate:(CGFloat)rate {
    VRTLogDebug(@"Setting rate: %.2f", rate);
    _rate = MAX(0.0f, MIN(2.0f, rate));
    
    if (_audioPlayer) {
        _audioPlayer.rate = _rate;
    }
    
    if (_onRateChange) {
        _onRateChange(@{@"rate": @(_rate)});
    }
}

- (void)setPitch:(CGFloat)pitch {
    VRTLogDebug(@"Setting pitch: %.2f", pitch);
    _pitch = pitch;
    // TODO: Implement pitch shifting using audio units
}

- (void)setSeekTime:(CGFloat)seekTime {
    VRTLogDebug(@"Setting seek time: %.2f", seekTime);
    _seekTime = seekTime;
    [self seekToTime:seekTime];
}

#pragma mark - Audio Properties

- (void)setAudioFormat:(nullable NSString *)audioFormat {
    VRTLogDebug(@"Setting audio format: %@", audioFormat);
    _audioFormat = audioFormat ?: @"auto";
}

- (void)setChannels:(NSInteger)channels {
    VRTLogDebug(@"Setting channels: %ld", (long)channels);
    _channels = channels;
}

- (void)setSampleRate:(NSInteger)sampleRate {
    VRTLogDebug(@"Setting sample rate: %ld", (long)sampleRate);
    _sampleRate = sampleRate;
}

- (void)setBitRate:(NSInteger)bitRate {
    VRTLogDebug(@"Setting bit rate: %ld", (long)bitRate);
    _bitRate = bitRate;
}

- (void)setQuality:(nullable NSString *)quality {
    VRTLogDebug(@"Setting quality: %@", quality);
    _quality = quality ?: @"medium";
}

#pragma mark - 3D Spatial Audio

- (void)setPosition:(nullable NSArray<NSNumber *> *)position {
    VRTLogDebug(@"Setting position: %@", position);
    _position = position ?: @[@(0.0), @(0.0), @(0.0)];
    [self updateSpatialAudio];
}

- (void)setRolloffModel:(nullable NSString *)rolloffModel {
    VRTLogDebug(@"Setting rolloff model: %@", rolloffModel);
    _rolloffModel = rolloffModel ?: @"inverse";
    [self updateSpatialAudio];
}

- (void)setDistanceModel:(nullable NSString *)distanceModel {
    VRTLogDebug(@"Setting distance model: %@", distanceModel);
    _distanceModel = distanceModel ?: @"inverse";
    [self updateSpatialAudio];
}

- (void)setMaxDistance:(CGFloat)maxDistance {
    VRTLogDebug(@"Setting max distance: %.2f", maxDistance);
    _maxDistance = maxDistance;
    [self updateSpatialAudio];
}

- (void)setReferenceDistance:(CGFloat)referenceDistance {
    VRTLogDebug(@"Setting reference distance: %.2f", referenceDistance);
    _referenceDistance = referenceDistance;
    [self updateSpatialAudio];
}

- (void)setRolloffFactor:(CGFloat)rolloffFactor {
    VRTLogDebug(@"Setting rolloff factor: %.2f", rolloffFactor);
    _rolloffFactor = rolloffFactor;
    [self updateSpatialAudio];
}

- (void)setConeInnerAngle:(CGFloat)coneInnerAngle {
    VRTLogDebug(@"Setting cone inner angle: %.2f", coneInnerAngle);
    _coneInnerAngle = coneInnerAngle;
    [self updateSpatialAudio];
}

- (void)setConeOuterAngle:(CGFloat)coneOuterAngle {
    VRTLogDebug(@"Setting cone outer angle: %.2f", coneOuterAngle);
    _coneOuterAngle = coneOuterAngle;
    [self updateSpatialAudio];
}

- (void)setConeOuterGain:(CGFloat)coneOuterGain {
    VRTLogDebug(@"Setting cone outer gain: %.2f", coneOuterGain);
    _coneOuterGain = coneOuterGain;
    [self updateSpatialAudio];
}

#pragma mark - Audio Effects

- (void)setReverb:(nullable NSDictionary *)reverb {
    VRTLogDebug(@"Setting reverb: %@", reverb);
    _reverb = reverb;
    [self updateReverbEffect];
}

- (void)setDelay:(nullable NSDictionary *)delay {
    VRTLogDebug(@"Setting delay: %@", delay);
    _delay = delay;
    [self updateDelayEffect];
}

- (void)setEcho:(nullable NSDictionary *)echo {
    VRTLogDebug(@"Setting echo: %@", echo);
    _echo = echo;
    [self updateEchoEffect];
}

- (void)setDistortion:(nullable NSDictionary *)distortion {
    VRTLogDebug(@"Setting distortion: %@", distortion);
    _distortion = distortion;
    [self updateDistortionEffect];
}

- (void)setEqualizer:(nullable NSDictionary *)equalizer {
    VRTLogDebug(@"Setting equalizer: %@", equalizer);
    _equalizer = equalizer;
    [self updateEqualizerEffect];
}

- (void)setFilters:(nullable NSArray<NSDictionary *> *)filters {
    VRTLogDebug(@"Setting filters: %@", filters);
    _filters = filters;
    [self updateFilters];
}

#pragma mark - Crossfading

- (void)setCrossfade:(nullable NSDictionary *)crossfade {
    VRTLogDebug(@"Setting crossfade: %@", crossfade);
    _crossfade = crossfade;
}

- (void)setFadeIn:(CGFloat)fadeIn {
    VRTLogDebug(@"Setting fade in: %.2f", fadeIn);
    _fadeIn = fadeIn;
}

- (void)setFadeOut:(CGFloat)fadeOut {
    VRTLogDebug(@"Setting fade out: %.2f", fadeOut);
    _fadeOut = fadeOut;
}

- (void)setFadeInCurve:(nullable NSString *)fadeInCurve {
    VRTLogDebug(@"Setting fade in curve: %@", fadeInCurve);
    _fadeInCurve = fadeInCurve ?: @"linear";
}

- (void)setFadeOutCurve:(nullable NSString *)fadeOutCurve {
    VRTLogDebug(@"Setting fade out curve: %@", fadeOutCurve);
    _fadeOutCurve = fadeOutCurve ?: @"linear";
}

#pragma mark - Audio Analysis

- (void)setAnalysisEnabled:(BOOL)analysisEnabled {
    VRTLogDebug(@"Setting analysis enabled: %d", analysisEnabled);
    _analysisEnabled = analysisEnabled;
    [self updateAnalysis];
}

- (void)setAnalysisInterval:(CGFloat)analysisInterval {
    VRTLogDebug(@"Setting analysis interval: %.3f", analysisInterval);
    _analysisInterval = analysisInterval;
    [self updateAnalysis];
}

- (void)setAnalysisFrequencyBands:(NSInteger)analysisFrequencyBands {
    VRTLogDebug(@"Setting analysis frequency bands: %ld", (long)analysisFrequencyBands);
    _analysisFrequencyBands = analysisFrequencyBands;
    [self updateAnalysis];
}

- (void)setAnalysisTimeConstant:(CGFloat)analysisTimeConstant {
    VRTLogDebug(@"Setting analysis time constant: %.2f", analysisTimeConstant);
    _analysisTimeConstant = analysisTimeConstant;
}

- (void)setAnalysisSmoothing:(CGFloat)analysisSmoothing {
    VRTLogDebug(@"Setting analysis smoothing: %.2f", analysisSmoothing);
    _analysisSmoothing = analysisSmoothing;
}

#pragma mark - Performance

- (void)setPreload:(BOOL)preload {
    VRTLogDebug(@"Setting preload: %d", preload);
    _preload = preload;
}

- (void)setBufferSize:(NSInteger)bufferSize {
    VRTLogDebug(@"Setting buffer size: %ld", (long)bufferSize);
    _bufferSize = bufferSize;
}

- (void)setCacheEnabled:(BOOL)cacheEnabled {
    VRTLogDebug(@"Setting cache enabled: %d", cacheEnabled);
    _cacheEnabled = cacheEnabled;
}

- (void)setStreamingEnabled:(BOOL)streamingEnabled {
    VRTLogDebug(@"Setting streaming enabled: %d", streamingEnabled);
    _streamingEnabled = streamingEnabled;
}

- (void)setBackgroundPlayback:(BOOL)backgroundPlayback {
    VRTLogDebug(@"Setting background playback: %d", backgroundPlayback);
    _backgroundPlayback = backgroundPlayback;
}

#pragma mark - Events

- (void)setOnLoad:(nullable RCTBubblingEventBlock)onLoad {
    _onLoad = onLoad;
}

- (void)setOnError:(nullable RCTBubblingEventBlock)onError {
    _onError = onError;
}

- (void)setOnPlay:(nullable RCTBubblingEventBlock)onPlay {
    _onPlay = onPlay;
}

- (void)setOnPause:(nullable RCTBubblingEventBlock)onPause {
    _onPause = onPause;
}

- (void)setOnStop:(nullable RCTBubblingEventBlock)onStop {
    _onStop = onStop;
}

- (void)setOnFinish:(nullable RCTBubblingEventBlock)onFinish {
    _onFinish = onFinish;
}

- (void)setOnSeek:(nullable RCTBubblingEventBlock)onSeek {
    _onSeek = onSeek;
}

- (void)setOnProgress:(nullable RCTBubblingEventBlock)onProgress {
    _onProgress = onProgress;
}

- (void)setOnVolumeChange:(nullable RCTBubblingEventBlock)onVolumeChange {
    _onVolumeChange = onVolumeChange;
}

- (void)setOnRateChange:(nullable RCTBubblingEventBlock)onRateChange {
    _onRateChange = onRateChange;
}

- (void)setOnAnalysis:(nullable RCTBubblingEventBlock)onAnalysis {
    _onAnalysis = onAnalysis;
}

#pragma mark - Audio Control Methods

- (void)play {
    VRTLogDebug(@"Playing audio");
    
    if (_soundState == ViroSoundStateError) {
        VRTLogDebug(@"Cannot play audio in error state");
        return;
    }
    
    if (_soundState != ViroSoundStateReady && _soundState != ViroSoundStatePaused) {
        VRTLogDebug(@"Audio not ready to play");
        return;
    }
    
    if (_audioPlayer) {
        [_audioPlayer play];
        _soundState = ViroSoundStatePlaying;
        
        [self startProgressTimer];
        
        if (_fadeIn > 0) {
            [self fadeInWithDuration:_fadeIn];
        }
        
        if (_onPlay) {
            _onPlay(@{});
        }
    }
}

- (void)pause {
    VRTLogDebug(@"Pausing audio");
    
    if (_soundState != ViroSoundStatePlaying) {
        VRTLogDebug(@"Audio not playing");
        return;
    }
    
    if (_audioPlayer) {
        [_audioPlayer pause];
        _soundState = ViroSoundStatePaused;
        
        [self stopProgressTimer];
        
        if (_onPause) {
            _onPause(@{});
        }
    }
}

- (void)stop {
    VRTLogDebug(@"Stopping audio");
    
    if (_soundState == ViroSoundStateIdle || _soundState == ViroSoundStateStopped) {
        VRTLogDebug(@"Audio already stopped");
        return;
    }
    
    if (_audioPlayer) {
        [_audioPlayer stop];
        _audioPlayer.currentTime = 0;
        _soundState = ViroSoundStateStopped;
        _currentTime = 0.0f;
        
        [self stopProgressTimer];
        
        if (_onStop) {
            _onStop(@{});
        }
    }
}

- (void)restart {
    VRTLogDebug(@"Restarting audio");
    [self stop];
    [self play];
}

- (void)seekToTime:(CGFloat)time {
    VRTLogDebug(@"Seeking to time: %.2f", time);
    
    if (_audioPlayer) {
        _audioPlayer.currentTime = MAX(0.0f, MIN(time, _duration));
        _currentTime = _audioPlayer.currentTime;
        
        if (_onSeek) {
            _onSeek(@{@"time": @(_currentTime)});
        }
    }
}

- (void)seekToProgress:(CGFloat)progress {
    VRTLogDebug(@"Seeking to progress: %.2f", progress);
    
    CGFloat time = _duration * MAX(0.0f, MIN(1.0f, progress));
    [self seekToTime:time];
}

- (void)fadeInWithDuration:(CGFloat)duration {
    VRTLogDebug(@"Fading in with duration: %.2f", duration);
    
    if (_audioPlayer) {
        _audioPlayer.volume = 0.0f;
        
        [UIView animateWithDuration:duration animations:^{
            [self updateVolume];
        }];
    }
}

- (void)fadeOutWithDuration:(CGFloat)duration {
    VRTLogDebug(@"Fading out with duration: %.2f", duration);
    
    if (_audioPlayer) {
        [UIView animateWithDuration:duration animations:^{
            self->_audioPlayer.volume = 0.0f;
        } completion:^(BOOL finished) {
            [self pause];
            [self updateVolume];
        }];
    }
}

- (void)crossfadeToSource:(NSDictionary *)newSource duration:(CGFloat)duration {
    VRTLogDebug(@"Crossfading to new source with duration: %.2f", duration);
    
    // TODO: Implement crossfading between two audio sources
    // This would require maintaining two audio players and fading between them
}

#pragma mark - Audio State Information

- (BOOL)isPlaying {
    return _soundState == ViroSoundStatePlaying;
}

- (BOOL)isPaused {
    return _soundState == ViroSoundStatePaused;
}

- (BOOL)isLoaded {
    return _soundState == ViroSoundStateReady || _soundState == ViroSoundStatePlaying || _soundState == ViroSoundStatePaused;
}

- (BOOL)hasError {
    return _soundState == ViroSoundStateError;
}

- (CGFloat)getCurrentTime {
    if (_audioPlayer) {
        _currentTime = _audioPlayer.currentTime;
    }
    return _currentTime;
}

- (CGFloat)getDuration {
    return _duration;
}

- (CGFloat)getProgress {
    if (_duration > 0) {
        return _currentTime / _duration;
    }
    return 0.0f;
}

- (CGFloat)getCurrentVolume {
    return _audioPlayer ? _audioPlayer.volume : _volume;
}

- (CGFloat)getCurrentRate {
    return _audioPlayer ? _audioPlayer.rate : _rate;
}

- (NSDictionary *)getAudioInfo {
    return @{
        @"uri": _uri ?: @"",
        @"duration": @(_duration),
        @"currentTime": @(_currentTime),
        @"volume": @([self getCurrentVolume]),
        @"rate": @([self getCurrentRate]),
        @"channels": @(_channels),
        @"sampleRate": @(_sampleRate),
        @"bitRate": @(_bitRate),
        @"format": _audioFormat,
        @"quality": _quality,
        @"isPlaying": @([self isPlaying]),
        @"isPaused": @([self isPaused]),
        @"isLoaded": @([self isLoaded]),
        @"hasError": @([self hasError])
    };
}

- (NSArray<NSNumber *> *)getFrequencyData {
    // TODO: Implement frequency data analysis
    return @[];
}

- (NSArray<NSNumber *> *)getTimeData {
    // TODO: Implement time domain data analysis
    return @[];
}

#pragma mark - Audio Utilities

- (void)setAudioSessionCategory:(NSString *)category {
    VRTLogDebug(@"Setting audio session category: %@", category);
    
    AVAudioSessionCategory sessionCategory = AVAudioSessionCategoryPlayback;
    
    if ([category isEqualToString:@"ambient"]) {
        sessionCategory = AVAudioSessionCategoryAmbient;
    } else if ([category isEqualToString:@"solo_ambient"]) {
        sessionCategory = AVAudioSessionCategorySoloAmbient;
    } else if ([category isEqualToString:@"playback"]) {
        sessionCategory = AVAudioSessionCategoryPlayback;
    } else if ([category isEqualToString:@"record"]) {
        sessionCategory = AVAudioSessionCategoryRecord;
    } else if ([category isEqualToString:@"play_and_record"]) {
        sessionCategory = AVAudioSessionCategoryPlayAndRecord;
    }
    
    NSError *error;
    [[AVAudioSession sharedInstance] setCategory:sessionCategory error:&error];
    if (error) {
        VRTLogError(@"Failed to set audio session category: %@", error);
    }
}

- (void)setAudioSessionMode:(NSString *)mode {
    VRTLogDebug(@"Setting audio session mode: %@", mode);
    
    AVAudioSessionMode sessionMode = AVAudioSessionModeDefault;
    
    if ([mode isEqualToString:@"voice_chat"]) {
        sessionMode = AVAudioSessionModeVoiceChat;
    } else if ([mode isEqualToString:@"game_chat"]) {
        sessionMode = AVAudioSessionModeGameChat;
    } else if ([mode isEqualToString:@"video_recording"]) {
        sessionMode = AVAudioSessionModeVideoRecording;
    } else if ([mode isEqualToString:@"measurement"]) {
        sessionMode = AVAudioSessionModeMeasurement;
    } else if ([mode isEqualToString:@"movie_playback"]) {
        sessionMode = AVAudioSessionModeMoviePlayback;
    } else if ([mode isEqualToString:@"video_chat"]) {
        sessionMode = AVAudioSessionModeVideoChat;
    }
    
    NSError *error;
    [[AVAudioSession sharedInstance] setMode:sessionMode error:&error];
    if (error) {
        VRTLogError(@"Failed to set audio session mode: %@", error);
    }
}

- (void)setAudioSessionOptions:(NSArray<NSString *> *)options {
    VRTLogDebug(@"Setting audio session options: %@", options);
    
    AVAudioSessionCategoryOptions sessionOptions = 0;
    
    for (NSString *option in options) {
        if ([option isEqualToString:@"mix_with_others"]) {
            sessionOptions |= AVAudioSessionCategoryOptionMixWithOthers;
        } else if ([option isEqualToString:@"duck_others"]) {
            sessionOptions |= AVAudioSessionCategoryOptionDuckOthers;
        } else if ([option isEqualToString:@"allow_bluetooth"]) {
            sessionOptions |= AVAudioSessionCategoryOptionAllowBluetooth;
        } else if ([option isEqualToString:@"default_to_speaker"]) {
            sessionOptions |= AVAudioSessionCategoryOptionDefaultToSpeaker;
        }
    }
    
    NSError *error;
    [[AVAudioSession sharedInstance] setCategory:[[AVAudioSession sharedInstance] category] options:sessionOptions error:&error];
    if (error) {
        VRTLogError(@"Failed to set audio session options: %@", error);
    }
}

- (void)activateAudioSession {
    VRTLogDebug(@"Activating audio session");
    
    NSError *error;
    [[AVAudioSession sharedInstance] setActive:YES error:&error];
    if (error) {
        VRTLogError(@"Failed to activate audio session: %@", error);
    }
}

- (void)deactivateAudioSession {
    VRTLogDebug(@"Deactivating audio session");
    
    NSError *error;
    [[AVAudioSession sharedInstance] setActive:NO error:&error];
    if (error) {
        VRTLogError(@"Failed to deactivate audio session: %@", error);
    }
}

#pragma mark - Helper Methods

- (void)setupAudioSession {
    NSError *error;
    AVAudioSession *session = [AVAudioSession sharedInstance];
    
    [session setCategory:AVAudioSessionCategoryPlayback error:&error];
    if (error) {
        VRTLogError(@"Failed to set audio session category: %@", error);
    }
    
    [session setActive:YES error:&error];
    if (error) {
        VRTLogError(@"Failed to activate audio session: %@", error);
    }
}

- (void)setupAudioEngine {
    _audioEngine = [[AVAudioEngine alloc] init];
    _playerNode = [[AVAudioPlayerNode alloc] init];
    _mixerNode = _audioEngine.mainMixerNode;
    
    [_audioEngine attachNode:_playerNode];
    [_audioEngine connect:_playerNode to:_mixerNode format:nil];
    
    // Set up audio units
    _reverbUnit = [[AVAudioUnitReverb alloc] init];
    _delayUnit = [[AVAudioUnitDelay alloc] init];
    _eqUnit = [[AVAudioUnitEQ alloc] initWithNumberOfBands:10];
    _distortionUnit = [[AVAudioUnitDistortion alloc] init];
    
    [_audioEngine attachNode:_reverbUnit];
    [_audioEngine attachNode:_delayUnit];
    [_audioEngine attachNode:_eqUnit];
    [_audioEngine attachNode:_distortionUnit];
}

- (void)loadAudioFromSource {
    if (!_source) {
        return;
    }
    
    NSString *uri = _source[@"uri"];
    if (uri) {
        _uri = uri;
        [self loadAudioFromURI];
    }
}

- (void)loadAudioFromURI {
    if (!_uri) {
        return;
    }
    
    VRTLogDebug(@"Loading audio from URI: %@", _uri);
    
    _soundState = ViroSoundStateLoading;
    _audioURL = [NSURL URLWithString:_uri];
    
    NSError *error;
    _audioPlayer = [[AVAudioPlayer alloc] initWithContentsOfURL:_audioURL error:&error];
    
    if (error) {
        VRTLogError(@"Failed to load audio: %@", error);
        _soundState = ViroSoundStateError;
        _lastError = error;
        
        if (_onError) {
            _onError(@{@"error": error.localizedDescription});
        }
        return;
    }
    
    [self configureAudioPlayer];
}

- (void)loadAudioFromLocal {
    if (!_local) {
        return;
    }
    
    VRTLogDebug(@"Loading audio from local file: %@", _local);
    
    _soundState = ViroSoundStateLoading;
    
    NSString *path = [[NSBundle mainBundle] pathForResource:_local ofType:nil];
    if (!path) {
        VRTLogError(@"Local audio file not found: %@", _local);
        _soundState = ViroSoundStateError;
        return;
    }
    
    _audioURL = [NSURL fileURLWithPath:path];
    
    NSError *error;
    _audioPlayer = [[AVAudioPlayer alloc] initWithContentsOfURL:_audioURL error:&error];
    
    if (error) {
        VRTLogError(@"Failed to load local audio: %@", error);
        _soundState = ViroSoundStateError;
        _lastError = error;
        
        if (_onError) {
            _onError(@{@"error": error.localizedDescription});
        }
        return;
    }
    
    [self configureAudioPlayer];
}

- (void)loadAudioFromResource {
    if (!_resource) {
        return;
    }
    
    VRTLogDebug(@"Loading audio from resource: %@", _resource);
    
    _soundState = ViroSoundStateLoading;
    
    NSString *resourceName = [_resource stringByDeletingPathExtension];
    NSString *resourceExtension = [_resource pathExtension];
    
    NSString *path = [[NSBundle mainBundle] pathForResource:resourceName ofType:resourceExtension];
    if (!path) {
        VRTLogError(@"Audio resource not found: %@", _resource);
        _soundState = ViroSoundStateError;
        return;
    }
    
    _audioURL = [NSURL fileURLWithPath:path];
    
    NSError *error;
    _audioPlayer = [[AVAudioPlayer alloc] initWithContentsOfURL:_audioURL error:&error];
    
    if (error) {
        VRTLogError(@"Failed to load audio resource: %@", error);
        _soundState = ViroSoundStateError;
        _lastError = error;
        
        if (_onError) {
            _onError(@{@"error": error.localizedDescription});
        }
        return;
    }
    
    [self configureAudioPlayer];
}

- (void)configureAudioPlayer {
    if (!_audioPlayer) {
        return;
    }
    
    _audioPlayer.delegate = self;
    _audioPlayer.numberOfLoops = _loop ? -1 : 0;
    _audioPlayer.volume = _muted ? 0.0f : _volume;
    _audioPlayer.rate = _rate;
    _audioPlayer.enableRate = YES;
    
    if (_preload) {
        [_audioPlayer prepareToPlay];
    }
    
    _duration = _audioPlayer.duration;
    _soundState = ViroSoundStateReady;
    
    if (_onLoad) {
        _onLoad(@{
            @"duration": @(_duration),
            @"channels": @(_audioPlayer.numberOfChannels),
            @"url": _audioURL.absoluteString
        });
    }
}

- (void)updateVolume {
    if (_audioPlayer) {
        _audioPlayer.volume = _muted ? 0.0f : _volume;
    }
}

- (void)updateSpatialAudio {
    // TODO: Implement 3D spatial audio positioning
    // This would use AVAudioEnvironmentNode for 3D audio
}

- (void)updateReverbEffect {
    if (_reverb && _reverbUnit) {
        // TODO: Configure reverb parameters
        _reverbUnit.wetDryMix = [_reverb[@"wetDryMix"] floatValue];
        _reverbUnit.loadFactoryPreset:AVAudioUnitReverbPresetMediumHall;
    }
}

- (void)updateDelayEffect {
    if (_delay && _delayUnit) {
        _delayUnit.delayTime = [_delay[@"delayTime"] floatValue];
        _delayUnit.feedback = [_delay[@"feedback"] floatValue];
        _delayUnit.wetDryMix = [_delay[@"wetDryMix"] floatValue];
    }
}

- (void)updateEchoEffect {
    // TODO: Implement echo effect
}

- (void)updateDistortionEffect {
    if (_distortion && _distortionUnit) {
        _distortionUnit.wetDryMix = [_distortion[@"wetDryMix"] floatValue];
        _distortionUnit.loadFactoryPreset:AVAudioUnitDistortionPresetDrumsBitBrush;
    }
}

- (void)updateEqualizerEffect {
    if (_equalizer && _eqUnit) {
        // TODO: Configure EQ bands
        for (NSInteger i = 0; i < _eqUnit.bands.count; i++) {
            AVAudioUnitEQFilterParameters *band = _eqUnit.bands[i];
            // Configure band parameters
        }
    }
}

- (void)updateFilters {
    // TODO: Implement custom filters
}

- (void)updateAnalysis {
    if (_analysisEnabled) {
        [self startAnalysisTimer];
    } else {
        [self stopAnalysisTimer];
    }
}

- (void)startProgressTimer {
    [self stopProgressTimer];
    
    _progressTimer = [NSTimer scheduledTimerWithTimeInterval:0.1f target:self selector:@selector(updateProgress) userInfo:nil repeats:YES];
}

- (void)stopProgressTimer {
    if (_progressTimer) {
        [_progressTimer invalidate];
        _progressTimer = nil;
    }
}

- (void)updateProgress {
    _currentTime = [self getCurrentTime];
    
    if (_onProgress) {
        _onProgress(@{
            @"currentTime": @(_currentTime),
            @"duration": @(_duration),
            @"progress": @([self getProgress])
        });
    }
}

- (void)startAnalysisTimer {
    [self stopAnalysisTimer];
    
    _analysisTimer = [NSTimer scheduledTimerWithTimeInterval:_analysisInterval target:self selector:@selector(performAnalysis) userInfo:nil repeats:YES];
}

- (void)stopAnalysisTimer {
    if (_analysisTimer) {
        [_analysisTimer invalidate];
        _analysisTimer = nil;
    }
}

- (void)performAnalysis {
    if (_onAnalysis) {
        _onAnalysis(@{
            @"frequencyData": [self getFrequencyData],
            @"timeData": [self getTimeData]
        });
    }
}

#pragma mark - AVAudioPlayerDelegate

- (void)audioPlayerDidFinishPlaying:(AVAudioPlayer *)player successfully:(BOOL)flag {
    VRTLogDebug(@"Audio player finished playing successfully: %d", flag);
    
    _soundState = ViroSoundStateStopped;
    _currentTime = 0.0f;
    
    [self stopProgressTimer];
    
    if (_onFinish) {
        _onFinish(@{@"success": @(flag)});
    }
}

- (void)audioPlayerDecodeErrorDidOccur:(AVAudioPlayer *)player error:(NSError *)error {
    VRTLogError(@"Audio player decode error: %@", error);
    
    _soundState = ViroSoundStateError;
    _lastError = error;
    
    [self stopProgressTimer];
    
    if (_onError) {
        _onError(@{@"error": error.localizedDescription});
    }
}

- (void)dealloc {
    [self stopProgressTimer];
    [self stopAnalysisTimer];
    
    if (_audioPlayer) {
        [_audioPlayer stop];
        _audioPlayer = nil;
    }
    
    if (_audioEngine) {
        [_audioEngine stop];
        _audioEngine = nil;
    }
}

@end