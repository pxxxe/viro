//
//  ViroAnimatedComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroAnimatedComponentView.h"
#import "ViroReactUtils.h"
#import "ViroLog.h"
#import <React/RCTConversions.h>
#import <React/RCTLog.h>
#import <QuartzCore/QuartzCore.h>

@implementation ViroAnimatedComponentView {
    // Animation properties
    NSDictionary *_animation;
    NSString *_animationName;
    BOOL _loop;
    NSTimeInterval _delay;
    NSTimeInterval _duration;
    NSString *_easing;
    NSString *_interpolatorType;
    
    // Animation control
    BOOL _run;
    BOOL _paused;
    BOOL _reverse;
    NSString *_direction;
    NSInteger _iterationCount;
    
    // Animation values
    id _fromValue;
    id _toValue;
    NSArray *_values;
    NSArray<NSNumber *> *_keyTimes;
    
    // Transform animations
    NSArray<NSNumber *> *_positionFrom;
    NSArray<NSNumber *> *_positionTo;
    NSArray<NSNumber *> *_scaleFrom;
    NSArray<NSNumber *> *_scaleTo;
    NSArray<NSNumber *> *_rotationFrom;
    NSArray<NSNumber *> *_rotationTo;
    
    // Opacity animations
    NSNumber *_opacityFrom;
    NSNumber *_opacityTo;
    
    // Color animations
    NSArray<NSNumber *> *_colorFrom;
    NSArray<NSNumber *> *_colorTo;
    
    // Material animations
    NSDictionary *_materialFrom;
    NSDictionary *_materialTo;
    
    // Physics animations
    BOOL _physicsEnabled;
    NSArray<NSNumber *> *_velocity;
    NSArray<NSNumber *> *_acceleration;
    
    // Animation state
    BOOL _isAnimating;
    BOOL _isPaused;
    NSTimeInterval _startTime;
    NSTimeInterval _pauseTime;
    NSTimeInterval _currentTime;
    
    // Core Animation objects
    CAAnimationGroup *_animationGroup;
    NSMutableArray<CAAnimation *> *_animations;
    
    // Event blocks
    RCTBubblingEventBlock _onStart;
    RCTBubblingEventBlock _onFinish;
    RCTBubblingEventBlock _onUpdate;
    RCTBubblingEventBlock _onCancel;
}

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        static const auto defaultProps = std::make_shared<const facebook::react::ViroAnimatedComponentProps>();
        _props = defaultProps;
        
        // Initialize default values
        _loop = NO;
        _delay = 0.0;
        _duration = 1.0;
        _easing = @"linear";
        _interpolatorType = @"linear";
        _run = NO;
        _paused = NO;
        _reverse = NO;
        _direction = @"normal";
        _iterationCount = 1;
        _physicsEnabled = NO;
        
        // Initialize animation state
        _isAnimating = NO;
        _isPaused = NO;
        _startTime = 0.0;
        _pauseTime = 0.0;
        _currentTime = 0.0;
        
        // Initialize animation arrays
        _animations = [NSMutableArray array];
        
        VRTLogDebug(@"ViroAnimatedComponent initialized");
    }
    return self;
}

#pragma mark - RCTComponentViewProtocol

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<facebook::react::ViroAnimatedComponentComponentDescriptor>();
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
    const auto &viroProps = *std::static_pointer_cast<facebook::react::ViroAnimatedComponentProps const>(props);
    const auto &oldViroProps = *std::static_pointer_cast<facebook::react::ViroAnimatedComponentProps const>(oldProps);
    
    [super updateProps:props oldProps:oldProps];
    
    // TODO: Update properties from viroProps
    // This will be implemented when Fabric code generation is complete
    VRTLogDebug(@"ViroAnimatedComponent props updated");
}

#pragma mark - Animation Properties

- (void)setAnimation:(nullable NSDictionary *)animation {
    VRTLogDebug(@"Setting animation: %@", animation);
    _animation = animation;
    
    if (animation) {
        // Parse animation dictionary and set individual properties
        NSString *name = animation[@"name"];
        if (name) {
            [self setAnimationName:name];
        }
        
        NSNumber *loop = animation[@"loop"];
        if (loop) {
            [self setLoop:[loop boolValue]];
        }
        
        NSNumber *delay = animation[@"delay"];
        if (delay) {
            [self setDelay:[delay doubleValue]];
        }
        
        NSNumber *duration = animation[@"duration"];
        if (duration) {
            [self setDuration:[duration doubleValue]];
        }
        
        NSString *easing = animation[@"easing"];
        if (easing) {
            [self setEasing:easing];
        }
        
        NSNumber *run = animation[@"run"];
        if (run) {
            [self setRun:[run boolValue]];
        }
        
        // Parse transform animations
        NSArray *positionFrom = animation[@"positionFrom"];
        if (positionFrom) {
            [self setPositionFrom:positionFrom];
        }
        
        NSArray *positionTo = animation[@"positionTo"];
        if (positionTo) {
            [self setPositionTo:positionTo];
        }
        
        NSArray *scaleFrom = animation[@"scaleFrom"];
        if (scaleFrom) {
            [self setScaleFrom:scaleFrom];
        }
        
        NSArray *scaleTo = animation[@"scaleTo"];
        if (scaleTo) {
            [self setScaleTo:scaleTo];
        }
        
        NSArray *rotationFrom = animation[@"rotationFrom"];
        if (rotationFrom) {
            [self setRotationFrom:rotationFrom];
        }
        
        NSArray *rotationTo = animation[@"rotationTo"];
        if (rotationTo) {
            [self setRotationTo:rotationTo];
        }
        
        // Parse opacity animations
        NSNumber *opacityFrom = animation[@"opacityFrom"];
        if (opacityFrom) {
            [self setOpacityFrom:opacityFrom];
        }
        
        NSNumber *opacityTo = animation[@"opacityTo"];
        if (opacityTo) {
            [self setOpacityTo:opacityTo];
        }
    }
    
    [self updateAnimation];
}

- (void)setAnimationName:(nullable NSString *)animationName {
    VRTLogDebug(@"Setting animation name: %@", animationName);
    _animationName = animationName;
    [self updateAnimation];
}

- (void)setLoop:(BOOL)loop {
    VRTLogDebug(@"Setting loop: %d", loop);
    _loop = loop;
    [self updateAnimation];
}

- (void)setDelay:(NSTimeInterval)delay {
    VRTLogDebug(@"Setting delay: %.2f", delay);
    _delay = delay;
    [self updateAnimation];
}

- (void)setDuration:(NSTimeInterval)duration {
    VRTLogDebug(@"Setting duration: %.2f", duration);
    _duration = duration;
    [self updateAnimation];
}

- (void)setEasing:(nullable NSString *)easing {
    VRTLogDebug(@"Setting easing: %@", easing);
    _easing = easing;
    [self updateAnimation];
}

- (void)setInterpolatorType:(nullable NSString *)interpolatorType {
    VRTLogDebug(@"Setting interpolator type: %@", interpolatorType);
    _interpolatorType = interpolatorType;
    [self updateAnimation];
}

#pragma mark - Animation Control

- (void)setRun:(BOOL)run {
    VRTLogDebug(@"Setting run: %d", run);
    _run = run;
    
    if (run) {
        [self startAnimation];
    } else {
        [self stopAnimation];
    }
}

- (void)setPaused:(BOOL)paused {
    VRTLogDebug(@"Setting paused: %d", paused);
    _paused = paused;
    
    if (paused) {
        [self pauseAnimation];
    } else {
        [self resumeAnimation];
    }
}

- (void)setReverse:(BOOL)reverse {
    VRTLogDebug(@"Setting reverse: %d", reverse);
    _reverse = reverse;
    [self updateAnimation];
}

- (void)setDirection:(nullable NSString *)direction {
    VRTLogDebug(@"Setting direction: %@", direction);
    _direction = direction;
    [self updateAnimation];
}

- (void)setIterationCount:(NSInteger)iterationCount {
    VRTLogDebug(@"Setting iteration count: %ld", (long)iterationCount);
    _iterationCount = iterationCount;
    [self updateAnimation];
}

#pragma mark - Animation Values

- (void)setFromValue:(nullable id)fromValue {
    VRTLogDebug(@"Setting from value: %@", fromValue);
    _fromValue = fromValue;
    [self updateAnimation];
}

- (void)setToValue:(nullable id)toValue {
    VRTLogDebug(@"Setting to value: %@", toValue);
    _toValue = toValue;
    [self updateAnimation];
}

- (void)setValues:(nullable NSArray *)values {
    VRTLogDebug(@"Setting values: %@", values);
    _values = values;
    [self updateAnimation];
}

- (void)setKeyTimes:(nullable NSArray<NSNumber *> *)keyTimes {
    VRTLogDebug(@"Setting key times: %@", keyTimes);
    _keyTimes = keyTimes;
    [self updateAnimation];
}

#pragma mark - Transform Animations

- (void)setPositionFrom:(nullable NSArray<NSNumber *> *)positionFrom {
    VRTLogDebug(@"Setting position from: %@", positionFrom);
    _positionFrom = positionFrom;
    [self updateAnimation];
}

- (void)setPositionTo:(nullable NSArray<NSNumber *> *)positionTo {
    VRTLogDebug(@"Setting position to: %@", positionTo);
    _positionTo = positionTo;
    [self updateAnimation];
}

- (void)setScaleFrom:(nullable NSArray<NSNumber *> *)scaleFrom {
    VRTLogDebug(@"Setting scale from: %@", scaleFrom);
    _scaleFrom = scaleFrom;
    [self updateAnimation];
}

- (void)setScaleTo:(nullable NSArray<NSNumber *> *)scaleTo {
    VRTLogDebug(@"Setting scale to: %@", scaleTo);
    _scaleTo = scaleTo;
    [self updateAnimation];
}

- (void)setRotationFrom:(nullable NSArray<NSNumber *> *)rotationFrom {
    VRTLogDebug(@"Setting rotation from: %@", rotationFrom);
    _rotationFrom = rotationFrom;
    [self updateAnimation];
}

- (void)setRotationTo:(nullable NSArray<NSNumber *> *)rotationTo {
    VRTLogDebug(@"Setting rotation to: %@", rotationTo);
    _rotationTo = rotationTo;
    [self updateAnimation];
}

#pragma mark - Opacity Animations

- (void)setOpacityFrom:(nullable NSNumber *)opacityFrom {
    VRTLogDebug(@"Setting opacity from: %@", opacityFrom);
    _opacityFrom = opacityFrom;
    [self updateAnimation];
}

- (void)setOpacityTo:(nullable NSNumber *)opacityTo {
    VRTLogDebug(@"Setting opacity to: %@", opacityTo);
    _opacityTo = opacityTo;
    [self updateAnimation];
}

#pragma mark - Color Animations

- (void)setColorFrom:(nullable NSArray<NSNumber *> *)colorFrom {
    VRTLogDebug(@"Setting color from: %@", colorFrom);
    _colorFrom = colorFrom;
    [self updateAnimation];
}

- (void)setColorTo:(nullable NSArray<NSNumber *> *)colorTo {
    VRTLogDebug(@"Setting color to: %@", colorTo);
    _colorTo = colorTo;
    [self updateAnimation];
}

#pragma mark - Material Animations

- (void)setMaterialFrom:(nullable NSDictionary *)materialFrom {
    VRTLogDebug(@"Setting material from: %@", materialFrom);
    _materialFrom = materialFrom;
    [self updateAnimation];
}

- (void)setMaterialTo:(nullable NSDictionary *)materialTo {
    VRTLogDebug(@"Setting material to: %@", materialTo);
    _materialTo = materialTo;
    [self updateAnimation];
}

#pragma mark - Physics Animations

- (void)setPhysicsEnabled:(BOOL)physicsEnabled {
    VRTLogDebug(@"Setting physics enabled: %d", physicsEnabled);
    _physicsEnabled = physicsEnabled;
    [self updateAnimation];
}

- (void)setVelocity:(nullable NSArray<NSNumber *> *)velocity {
    VRTLogDebug(@"Setting velocity: %@", velocity);
    _velocity = velocity;
    [self updateAnimation];
}

- (void)setAcceleration:(nullable NSArray<NSNumber *> *)acceleration {
    VRTLogDebug(@"Setting acceleration: %@", acceleration);
    _acceleration = acceleration;
    [self updateAnimation];
}

#pragma mark - Events

- (void)setOnStart:(nullable RCTBubblingEventBlock)onStart {
    _onStart = onStart;
}

- (void)setOnFinish:(nullable RCTBubblingEventBlock)onFinish {
    _onFinish = onFinish;
}

- (void)setOnUpdate:(nullable RCTBubblingEventBlock)onUpdate {
    _onUpdate = onUpdate;
}

- (void)setOnCancel:(nullable RCTBubblingEventBlock)onCancel {
    _onCancel = onCancel;
}

#pragma mark - Animation Control Methods

- (void)startAnimation {
    VRTLogDebug(@"Starting animation");
    
    if (_isAnimating && !_isPaused) {
        VRTLogDebug(@"Animation already running");
        return;
    }
    
    [self buildAnimationGroup];
    
    if (_animationGroup) {
        _isAnimating = YES;
        _isPaused = NO;
        _startTime = CACurrentMediaTime();
        
        // Fire onStart event
        if (_onStart) {
            _onStart(@{});
        }
        
        // Add animation to layer
        [self.layer addAnimation:_animationGroup forKey:@"ViroAnimatedComponent"];
        
        // TODO: Start animation in ViroReact scene
        // This will integrate with the ViroReact animation system
    }
}

- (void)pauseAnimation {
    VRTLogDebug(@"Pausing animation");
    
    if (!_isAnimating || _isPaused) {
        VRTLogDebug(@"Animation not running or already paused");
        return;
    }
    
    _isPaused = YES;
    _pauseTime = CACurrentMediaTime();
    
    // Pause Core Animation
    CFTimeInterval pausedTime = [self.layer convertTime:CACurrentMediaTime() fromLayer:nil];
    self.layer.speed = 0.0;
    self.layer.timeOffset = pausedTime;
    
    // TODO: Pause animation in ViroReact scene
}

- (void)resumeAnimation {
    VRTLogDebug(@"Resuming animation");
    
    if (!_isAnimating || !_isPaused) {
        VRTLogDebug(@"Animation not running or not paused");
        return;
    }
    
    _isPaused = NO;
    
    // Resume Core Animation
    CFTimeInterval pausedTime = [self.layer timeOffset];
    self.layer.speed = 1.0;
    self.layer.timeOffset = 0.0;
    self.layer.beginTime = 0.0;
    CFTimeInterval timeSincePause = [self.layer convertTime:CACurrentMediaTime() fromLayer:nil] - pausedTime;
    self.layer.beginTime = timeSincePause;
    
    // TODO: Resume animation in ViroReact scene
}

- (void)stopAnimation {
    VRTLogDebug(@"Stopping animation");
    
    if (!_isAnimating) {
        VRTLogDebug(@"Animation not running");
        return;
    }
    
    _isAnimating = NO;
    _isPaused = NO;
    
    // Remove Core Animation
    [self.layer removeAnimationForKey:@"ViroAnimatedComponent"];
    
    // Reset layer properties
    self.layer.speed = 1.0;
    self.layer.timeOffset = 0.0;
    self.layer.beginTime = 0.0;
    
    // TODO: Stop animation in ViroReact scene
    
    // Fire onCancel event
    if (_onCancel) {
        _onCancel(@{});
    }
}

- (void)resetAnimation {
    VRTLogDebug(@"Resetting animation");
    
    [self stopAnimation];
    _currentTime = 0.0;
    
    // TODO: Reset animation in ViroReact scene to initial state
}

#pragma mark - Animation State

- (BOOL)isAnimating {
    return _isAnimating;
}

- (BOOL)isPaused {
    return _isPaused;
}

- (NSTimeInterval)currentTime {
    if (_isAnimating && !_isPaused) {
        return CACurrentMediaTime() - _startTime;
    } else if (_isPaused) {
        return _pauseTime - _startTime;
    } else {
        return _currentTime;
    }
}

- (float)progress {
    if (_duration > 0.0) {
        return MIN(1.0f, (float)([self currentTime] / _duration));
    }
    return 0.0f;
}

#pragma mark - Helper Methods

- (void)updateAnimation {
    if (_run && !_isAnimating) {
        [self startAnimation];
    } else if (_isAnimating) {
        // Update running animation
        [self stopAnimation];
        if (_run) {
            [self startAnimation];
        }
    }
}

- (void)buildAnimationGroup {
    [_animations removeAllObjects];
    
    // Build position animation
    if (_positionFrom && _positionTo) {
        CABasicAnimation *positionAnimation = [self createBasicAnimationForKeyPath:@"position"
                                                                        fromValue:_positionFrom
                                                                          toValue:_positionTo];
        if (positionAnimation) {
            [_animations addObject:positionAnimation];
        }
    }
    
    // Build scale animation
    if (_scaleFrom && _scaleTo) {
        CABasicAnimation *scaleAnimation = [self createBasicAnimationForKeyPath:@"transform.scale"
                                                                      fromValue:_scaleFrom
                                                                        toValue:_scaleTo];
        if (scaleAnimation) {
            [_animations addObject:scaleAnimation];
        }
    }
    
    // Build rotation animation
    if (_rotationFrom && _rotationTo) {
        CABasicAnimation *rotationAnimation = [self createBasicAnimationForKeyPath:@"transform.rotation"
                                                                         fromValue:_rotationFrom
                                                                           toValue:_rotationTo];
        if (rotationAnimation) {
            [_animations addObject:rotationAnimation];
        }
    }
    
    // Build opacity animation
    if (_opacityFrom && _opacityTo) {
        CABasicAnimation *opacityAnimation = [self createBasicAnimationForKeyPath:@"opacity"
                                                                        fromValue:_opacityFrom
                                                                          toValue:_opacityTo];
        if (opacityAnimation) {
            [_animations addObject:opacityAnimation];
        }
    }
    
    // Create animation group
    if (_animations.count > 0) {
        _animationGroup = [CAAnimationGroup animation];
        _animationGroup.animations = _animations;
        _animationGroup.duration = _duration;
        _animationGroup.beginTime = CACurrentMediaTime() + _delay;
        _animationGroup.repeatCount = _loop ? HUGE_VALF : (_iterationCount > 0 ? _iterationCount : 1);
        _animationGroup.autoreverses = _reverse;
        _animationGroup.timingFunction = [self timingFunctionForEasing:_easing];
        _animationGroup.fillMode = kCAFillModeForwards;
        _animationGroup.removedOnCompletion = NO;
        
        // Set delegate to handle animation events
        _animationGroup.delegate = self;
    }
}

- (CABasicAnimation *)createBasicAnimationForKeyPath:(NSString *)keyPath
                                           fromValue:(id)fromValue
                                             toValue:(id)toValue {
    CABasicAnimation *animation = [CABasicAnimation animationWithKeyPath:keyPath];
    animation.fromValue = fromValue;
    animation.toValue = toValue;
    return animation;
}

- (CAMediaTimingFunction *)timingFunctionForEasing:(NSString *)easing {
    if ([easing isEqualToString:@"linear"]) {
        return [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionLinear];
    } else if ([easing isEqualToString:@"ease-in"]) {
        return [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseIn];
    } else if ([easing isEqualToString:@"ease-out"]) {
        return [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseOut];
    } else if ([easing isEqualToString:@"ease-in-out"]) {
        return [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    } else {
        return [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionLinear];
    }
}

#pragma mark - CAAnimationDelegate

- (void)animationDidStart:(CAAnimation *)anim {
    VRTLogDebug(@"Animation did start");
    
    if (_onStart) {
        _onStart(@{});
    }
}

- (void)animationDidStop:(CAAnimation *)anim finished:(BOOL)flag {
    VRTLogDebug(@"Animation did stop (finished: %d)", flag);
    
    _isAnimating = NO;
    _isPaused = NO;
    
    if (flag) {
        // Animation completed normally
        if (_onFinish) {
            _onFinish(@{});
        }
    } else {
        // Animation was cancelled
        if (_onCancel) {
            _onCancel(@{});
        }
    }
}

#pragma mark - Layout

- (void)layoutSubviews {
    [super layoutSubviews];
    
    // Update animation if needed based on layout changes
    if (_isAnimating) {
        [self updateAnimation];
    }
}

@end