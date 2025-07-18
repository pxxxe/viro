//
//  ViroSpinnerComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroSpinnerComponentView.h"
#import "ViroReactUtils.h"
#import "ViroLog.h"
#import <React/RCTConversions.h>
#import <React/RCTLog.h>
#import <QuartzCore/QuartzCore.h>

typedef NS_ENUM(NSInteger, ViroSpinnerType) {
    ViroSpinnerTypeCircular = 0,
    ViroSpinnerTypeDots,
    ViroSpinnerTypeBars,
    ViroSpinnerTypeRing,
    ViroSpinnerTypePulse
};

@implementation ViroSpinnerComponentView {
    // Spinner appearance
    NSString *_type;
    NSArray<NSNumber *> *_color;
    CGFloat _size;
    CGFloat _thickness;
    CGFloat _radius;
    CGFloat _spacing;
    
    // Spinner animation
    BOOL _animating;
    CGFloat _speed;
    NSString *_direction;
    CGFloat _duration;
    NSString *_easing;
    CGFloat _delay;
    
    // Spinner behavior
    BOOL _visible;
    BOOL _autoHide;
    CGFloat _hideDelay;
    CGFloat _fadeInDuration;
    CGFloat _fadeOutDuration;
    
    // Spinner progress
    CGFloat _progress;
    NSArray<NSNumber *> *_progressColor;
    NSArray<NSNumber *> *_progressBackgroundColor;
    BOOL _showProgress;
    NSString *_progressText;
    
    // Spinner text
    NSString *_text;
    NSArray<NSNumber *> *_textColor;
    CGFloat _textSize;
    NSString *_textFont;
    NSString *_textPosition;
    CGFloat _textOffset;
    
    // Spinner customization
    NSInteger _dotCount;
    CGFloat _dotSize;
    NSInteger _barCount;
    CGFloat _barWidth;
    CGFloat _barHeight;
    CGFloat _ringWidth;
    CGFloat _pulseScale;
    
    // Internal state
    ViroSpinnerType _spinnerType;
    CAShapeLayer *_spinnerLayer;
    CAShapeLayer *_progressLayer;
    CATextLayer *_textLayer;
    CATextLayer *_progressTextLayer;
    CAAnimationGroup *_currentAnimation;
    NSMutableArray<CAShapeLayer *> *_dotLayers;
    NSMutableArray<CAShapeLayer *> *_barLayers;
    
    // Event blocks
    RCTBubblingEventBlock _onStart;
    RCTBubblingEventBlock _onStop;
    RCTBubblingEventBlock _onComplete;
    RCTBubblingEventBlock _onProgressChange;
}

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        static const auto defaultProps = std::make_shared<const facebook::react::ViroSpinnerProps>();
        _props = defaultProps;
        
        // Initialize default values
        _type = @"circular";
        _color = @[@(1.0), @(1.0), @(1.0), @(1.0)];
        _size = 50.0f;
        _thickness = 4.0f;
        _radius = 20.0f;
        _spacing = 8.0f;
        
        // Initialize spinner animation
        _animating = NO;
        _speed = 1.0f;
        _direction = @"clockwise";
        _duration = 1.0f;
        _easing = @"linear";
        _delay = 0.0f;
        
        // Initialize spinner behavior
        _visible = YES;
        _autoHide = NO;
        _hideDelay = 0.0f;
        _fadeInDuration = 0.3f;
        _fadeOutDuration = 0.3f;
        
        // Initialize spinner progress
        _progress = 0.0f;
        _progressColor = @[@(0.0), @(0.7), @(1.0), @(1.0)];
        _progressBackgroundColor = @[@(0.3), @(0.3), @(0.3), @(0.3)];
        _showProgress = NO;
        _progressText = @"";
        
        // Initialize spinner text
        _text = @"";
        _textColor = @[@(1.0), @(1.0), @(1.0), @(1.0)];
        _textSize = 14.0f;
        _textFont = @"System";
        _textPosition = @"bottom";
        _textOffset = 10.0f;
        
        // Initialize spinner customization
        _dotCount = 8;
        _dotSize = 8.0f;
        _barCount = 12;
        _barWidth = 3.0f;
        _barHeight = 12.0f;
        _ringWidth = 4.0f;
        _pulseScale = 1.5f;
        
        // Initialize internal state
        _spinnerType = ViroSpinnerTypeCircular;
        _dotLayers = [NSMutableArray array];
        _barLayers = [NSMutableArray array];
        
        // Set up spinner layers
        [self setupSpinnerLayers];
        
        VRTLogDebug(@"ViroSpinner initialized");
    }
    return self;
}

#pragma mark - RCTComponentViewProtocol

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<facebook::react::ViroSpinnerComponentDescriptor>();
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
    const auto &viroProps = *std::static_pointer_cast<facebook::react::ViroSpinnerProps const>(props);
    const auto &oldViroProps = *std::static_pointer_cast<facebook::react::ViroSpinnerProps const>(oldProps);
    
    [super updateProps:props oldProps:oldProps];
    
    // TODO: Update properties from viroProps
    // This will be implemented when Fabric code generation is complete
    VRTLogDebug(@"ViroSpinner props updated");
}

#pragma mark - Spinner Appearance

- (void)setType:(nullable NSString *)type {
    VRTLogDebug(@"Setting type: %@", type);
    _type = type ?: @"circular";
    
    if ([_type isEqualToString:@"circular"]) {
        _spinnerType = ViroSpinnerTypeCircular;
    } else if ([_type isEqualToString:@"dots"]) {
        _spinnerType = ViroSpinnerTypeDots;
    } else if ([_type isEqualToString:@"bars"]) {
        _spinnerType = ViroSpinnerTypeBars;
    } else if ([_type isEqualToString:@"ring"]) {
        _spinnerType = ViroSpinnerTypeRing;
    } else if ([_type isEqualToString:@"pulse"]) {
        _spinnerType = ViroSpinnerTypePulse;
    }
    
    [self updateSpinnerType];
}

- (void)setColor:(nullable NSArray<NSNumber *> *)color {
    VRTLogDebug(@"Setting color: %@", color);
    _color = color ?: @[@(1.0), @(1.0), @(1.0), @(1.0)];
    [self updateSpinnerAppearance];
}

- (void)setSize:(CGFloat)size {
    VRTLogDebug(@"Setting size: %.1f", size);
    _size = size;
    [self updateSpinnerGeometry];
}

- (void)setThickness:(CGFloat)thickness {
    VRTLogDebug(@"Setting thickness: %.1f", thickness);
    _thickness = thickness;
    [self updateSpinnerGeometry];
}

- (void)setRadius:(CGFloat)radius {
    VRTLogDebug(@"Setting radius: %.1f", radius);
    _radius = radius;
    [self updateSpinnerGeometry];
}

- (void)setSpacing:(CGFloat)spacing {
    VRTLogDebug(@"Setting spacing: %.1f", spacing);
    _spacing = spacing;
    [self updateSpinnerGeometry];
}

#pragma mark - Spinner Animation

- (void)setAnimating:(BOOL)animating {
    VRTLogDebug(@"Setting animating: %d", animating);
    _animating = animating;
    
    if (animating) {
        [self startAnimating];
    } else {
        [self stopAnimating];
    }
}

- (void)setSpeed:(CGFloat)speed {
    VRTLogDebug(@"Setting speed: %.2f", speed);
    _speed = speed;
    [self updateSpinnerAnimation];
}

- (void)setDirection:(nullable NSString *)direction {
    VRTLogDebug(@"Setting direction: %@", direction);
    _direction = direction ?: @"clockwise";
    [self updateSpinnerAnimation];
}

- (void)setDuration:(CGFloat)duration {
    VRTLogDebug(@"Setting duration: %.2f", duration);
    _duration = duration;
    [self updateSpinnerAnimation];
}

- (void)setEasing:(nullable NSString *)easing {
    VRTLogDebug(@"Setting easing: %@", easing);
    _easing = easing ?: @"linear";
    [self updateSpinnerAnimation];
}

- (void)setDelay:(CGFloat)delay {
    VRTLogDebug(@"Setting delay: %.2f", delay);
    _delay = delay;
    [self updateSpinnerAnimation];
}

#pragma mark - Spinner Behavior

- (void)setVisible:(BOOL)visible {
    VRTLogDebug(@"Setting visible: %d", visible);
    _visible = visible;
    
    if (visible) {
        [self show];
    } else {
        [self hide];
    }
}

- (void)setAutoHide:(BOOL)autoHide {
    VRTLogDebug(@"Setting auto hide: %d", autoHide);
    _autoHide = autoHide;
}

- (void)setHideDelay:(CGFloat)hideDelay {
    VRTLogDebug(@"Setting hide delay: %.2f", hideDelay);
    _hideDelay = hideDelay;
}

- (void)setFadeInDuration:(CGFloat)fadeInDuration {
    VRTLogDebug(@"Setting fade in duration: %.2f", fadeInDuration);
    _fadeInDuration = fadeInDuration;
}

- (void)setFadeOutDuration:(CGFloat)fadeOutDuration {
    VRTLogDebug(@"Setting fade out duration: %.2f", fadeOutDuration);
    _fadeOutDuration = fadeOutDuration;
}

#pragma mark - Spinner Progress

- (void)setProgress:(CGFloat)progress {
    VRTLogDebug(@"Setting progress: %.2f", progress);
    _progress = MAX(0.0f, MIN(1.0f, progress));
    [self updateSpinnerProgress];
    
    if (_onProgressChange) {
        _onProgressChange(@{@"progress": @(_progress)});
    }
}

- (void)setProgressColor:(nullable NSArray<NSNumber *> *)progressColor {
    VRTLogDebug(@"Setting progress color: %@", progressColor);
    _progressColor = progressColor ?: @[@(0.0), @(0.7), @(1.0), @(1.0)];
    [self updateSpinnerAppearance];
}

- (void)setProgressBackgroundColor:(nullable NSArray<NSNumber *> *)progressBackgroundColor {
    VRTLogDebug(@"Setting progress background color: %@", progressBackgroundColor);
    _progressBackgroundColor = progressBackgroundColor ?: @[@(0.3), @(0.3), @(0.3), @(0.3)];
    [self updateSpinnerAppearance];
}

- (void)setShowProgress:(BOOL)showProgress {
    VRTLogDebug(@"Setting show progress: %d", showProgress);
    _showProgress = showProgress;
    [self updateSpinnerProgress];
}

- (void)setProgressText:(nullable NSString *)progressText {
    VRTLogDebug(@"Setting progress text: %@", progressText);
    _progressText = progressText ?: @"";
    [self updateSpinnerText];
}

#pragma mark - Spinner Text

- (void)setText:(nullable NSString *)text {
    VRTLogDebug(@"Setting text: %@", text);
    _text = text ?: @"";
    [self updateSpinnerText];
}

- (void)setTextColor:(nullable NSArray<NSNumber *> *)textColor {
    VRTLogDebug(@"Setting text color: %@", textColor);
    _textColor = textColor ?: @[@(1.0), @(1.0), @(1.0), @(1.0)];
    [self updateSpinnerText];
}

- (void)setTextSize:(CGFloat)textSize {
    VRTLogDebug(@"Setting text size: %.1f", textSize);
    _textSize = textSize;
    [self updateSpinnerText];
}

- (void)setTextFont:(nullable NSString *)textFont {
    VRTLogDebug(@"Setting text font: %@", textFont);
    _textFont = textFont ?: @"System";
    [self updateSpinnerText];
}

- (void)setTextPosition:(nullable NSString *)textPosition {
    VRTLogDebug(@"Setting text position: %@", textPosition);
    _textPosition = textPosition ?: @"bottom";
    [self updateSpinnerText];
}

- (void)setTextOffset:(CGFloat)textOffset {
    VRTLogDebug(@"Setting text offset: %.1f", textOffset);
    _textOffset = textOffset;
    [self updateSpinnerText];
}

#pragma mark - Spinner Customization

- (void)setDotCount:(NSInteger)dotCount {
    VRTLogDebug(@"Setting dot count: %ld", (long)dotCount);
    _dotCount = dotCount;
    [self updateSpinnerType];
}

- (void)setDotSize:(CGFloat)dotSize {
    VRTLogDebug(@"Setting dot size: %.1f", dotSize);
    _dotSize = dotSize;
    [self updateSpinnerGeometry];
}

- (void)setBarCount:(NSInteger)barCount {
    VRTLogDebug(@"Setting bar count: %ld", (long)barCount);
    _barCount = barCount;
    [self updateSpinnerType];
}

- (void)setBarWidth:(CGFloat)barWidth {
    VRTLogDebug(@"Setting bar width: %.1f", barWidth);
    _barWidth = barWidth;
    [self updateSpinnerGeometry];
}

- (void)setBarHeight:(CGFloat)barHeight {
    VRTLogDebug(@"Setting bar height: %.1f", barHeight);
    _barHeight = barHeight;
    [self updateSpinnerGeometry];
}

- (void)setRingWidth:(CGFloat)ringWidth {
    VRTLogDebug(@"Setting ring width: %.1f", ringWidth);
    _ringWidth = ringWidth;
    [self updateSpinnerGeometry];
}

- (void)setPulseScale:(CGFloat)pulseScale {
    VRTLogDebug(@"Setting pulse scale: %.2f", pulseScale);
    _pulseScale = pulseScale;
    [self updateSpinnerAnimation];
}

#pragma mark - Events

- (void)setOnStart:(nullable RCTBubblingEventBlock)onStart {
    _onStart = onStart;
}

- (void)setOnStop:(nullable RCTBubblingEventBlock)onStop {
    _onStop = onStop;
}

- (void)setOnComplete:(nullable RCTBubblingEventBlock)onComplete {
    _onComplete = onComplete;
}

- (void)setOnProgressChange:(nullable RCTBubblingEventBlock)onProgressChange {
    _onProgressChange = onProgressChange;
}

#pragma mark - Spinner Control Methods

- (void)startAnimating {
    VRTLogDebug(@"Starting spinner animation");
    
    if (_animating) {
        VRTLogDebug(@"Spinner already animating");
        return;
    }
    
    _animating = YES;
    
    // Create and start animation based on spinner type
    switch (_spinnerType) {
        case ViroSpinnerTypeCircular:
            [self startCircularAnimation];
            break;
        case ViroSpinnerTypeDots:
            [self startDotsAnimation];
            break;
        case ViroSpinnerTypeBars:
            [self startBarsAnimation];
            break;
        case ViroSpinnerTypeRing:
            [self startRingAnimation];
            break;
        case ViroSpinnerTypePulse:
            [self startPulseAnimation];
            break;
    }
    
    // Fire start event
    if (_onStart) {
        _onStart(@{});
    }
}

- (void)stopAnimating {
    VRTLogDebug(@"Stopping spinner animation");
    
    if (!_animating) {
        VRTLogDebug(@"Spinner not animating");
        return;
    }
    
    _animating = NO;
    
    // Stop all animations
    [self stopAllAnimations];
    
    // Fire stop event
    if (_onStop) {
        _onStop(@{});
    }
    
    // Auto-hide if enabled
    if (_autoHide) {
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(_hideDelay * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            [self hide];
        });
    }
}

- (void)show {
    VRTLogDebug(@"Showing spinner");
    
    if (_visible) {
        return;
    }
    
    _visible = YES;
    self.hidden = NO;
    
    [self fadeIn];
}

- (void)hide {
    VRTLogDebug(@"Hiding spinner");
    
    if (!_visible) {
        return;
    }
    
    _visible = NO;
    
    [self fadeOut];
}

- (void)fadeIn {
    VRTLogDebug(@"Fading in spinner");
    
    [CATransaction begin];
    [CATransaction setAnimationDuration:_fadeInDuration];
    self.layer.opacity = 1.0f;
    [CATransaction commit];
}

- (void)fadeOut {
    VRTLogDebug(@"Fading out spinner");
    
    [CATransaction begin];
    [CATransaction setAnimationDuration:_fadeOutDuration];
    [CATransaction setCompletionBlock:^{
        self.hidden = YES;
    }];
    self.layer.opacity = 0.0f;
    [CATransaction commit];
}

- (void)setProgressValue:(CGFloat)progress animated:(BOOL)animated {
    VRTLogDebug(@"Setting progress value: %.2f animated: %d", progress, animated);
    
    if (animated) {
        [CATransaction begin];
        [CATransaction setAnimationDuration:0.3f];
        [self setProgress:progress];
        [CATransaction commit];
    } else {
        [self setProgress:progress];
    }
}

#pragma mark - Spinner State Information

- (BOOL)isAnimating {
    return _animating;
}

- (BOOL)isVisible {
    return _visible;
}

- (CGFloat)getCurrentProgress {
    return _progress;
}

- (NSString *)getSpinnerType {
    return _type;
}

- (NSDictionary *)getSpinnerInfo {
    return @{
        @"type": _type,
        @"animating": @(_animating),
        @"visible": @(_visible),
        @"progress": @(_progress),
        @"size": @(_size),
        @"color": _color,
        @"speed": @(_speed),
        @"direction": _direction
    };
}

#pragma mark - Helper Methods

- (void)setupSpinnerLayers {
    // Create main spinner layer
    _spinnerLayer = [CAShapeLayer layer];
    _spinnerLayer.fillColor = [UIColor clearColor].CGColor;
    _spinnerLayer.strokeColor = [self colorFromArray:_color].CGColor;
    _spinnerLayer.lineWidth = _thickness;
    _spinnerLayer.lineCap = kCALineCapRound;
    [self.layer addSublayer:_spinnerLayer];
    
    // Create progress layer
    if (_showProgress) {
        _progressLayer = [CAShapeLayer layer];
        _progressLayer.fillColor = [UIColor clearColor].CGColor;
        _progressLayer.strokeColor = [self colorFromArray:_progressColor].CGColor;
        _progressLayer.lineWidth = _thickness;
        _progressLayer.lineCap = kCALineCapRound;
        [self.layer addSublayer:_progressLayer];
    }
    
    // Create text layer
    _textLayer = [CATextLayer layer];
    _textLayer.alignmentMode = kCAAlignmentCenter;
    _textLayer.truncationMode = kCATruncationEnd;
    _textLayer.fontSize = _textSize;
    _textLayer.foregroundColor = [self colorFromArray:_textColor].CGColor;
    [self.layer addSublayer:_textLayer];
    
    // Create progress text layer
    if (_showProgress) {
        _progressTextLayer = [CATextLayer layer];
        _progressTextLayer.alignmentMode = kCAAlignmentCenter;
        _progressTextLayer.truncationMode = kCATruncationEnd;
        _progressTextLayer.fontSize = _textSize * 0.8f;
        _progressTextLayer.foregroundColor = [self colorFromArray:_textColor].CGColor;
        [self.layer addSublayer:_progressTextLayer];
    }
}

- (void)updateSpinnerType {
    // Clear existing dot/bar layers
    for (CAShapeLayer *layer in _dotLayers) {
        [layer removeFromSuperlayer];
    }
    [_dotLayers removeAllObjects];
    
    for (CAShapeLayer *layer in _barLayers) {
        [layer removeFromSuperlayer];
    }
    [_barLayers removeAllObjects];
    
    // Create new layers based on type
    switch (_spinnerType) {
        case ViroSpinnerTypeDots:
            [self createDotLayers];
            break;
        case ViroSpinnerTypeBars:
            [self createBarLayers];
            break;
        default:
            break;
    }
    
    [self updateSpinnerGeometry];
}

- (void)updateSpinnerAppearance {
    // Update spinner layer colors
    _spinnerLayer.strokeColor = [self colorFromArray:_color].CGColor;
    
    if (_progressLayer) {
        _progressLayer.strokeColor = [self colorFromArray:_progressColor].CGColor;
    }
    
    // Update dot layers
    for (CAShapeLayer *layer in _dotLayers) {
        layer.fillColor = [self colorFromArray:_color].CGColor;
    }
    
    // Update bar layers
    for (CAShapeLayer *layer in _barLayers) {
        layer.fillColor = [self colorFromArray:_color].CGColor;
    }
}

- (void)updateSpinnerGeometry {
    CGPoint center = CGPointMake(self.bounds.size.width / 2, self.bounds.size.height / 2);
    
    // Update main spinner path
    UIBezierPath *path = [UIBezierPath bezierPathWithArcCenter:center
                                                        radius:_radius
                                                    startAngle:0
                                                      endAngle:2 * M_PI
                                                     clockwise:YES];
    _spinnerLayer.path = path.CGPath;
    _spinnerLayer.lineWidth = _thickness;
    
    // Update progress path
    if (_progressLayer) {
        UIBezierPath *progressPath = [UIBezierPath bezierPathWithArcCenter:center
                                                                    radius:_radius
                                                                startAngle:-M_PI_2
                                                                  endAngle:(-M_PI_2 + 2 * M_PI * _progress)
                                                                 clockwise:YES];
        _progressLayer.path = progressPath.CGPath;
        _progressLayer.lineWidth = _thickness;
    }
    
    // Update dot positions
    [self updateDotPositions];
    
    // Update bar positions
    [self updateBarPositions];
}

- (void)updateSpinnerAnimation {
    if (_animating) {
        [self stopAllAnimations];
        [self startAnimating];
    }
}

- (void)updateSpinnerProgress {
    if (_progressLayer) {
        _progressLayer.hidden = !_showProgress;
        [self updateSpinnerGeometry];
    }
    
    if (_progressTextLayer) {
        _progressTextLayer.hidden = !_showProgress;
        _progressTextLayer.string = [NSString stringWithFormat:@"%.0f%%", _progress * 100];
    }
}

- (void)updateSpinnerText {
    // Update main text
    if (_text && _text.length > 0) {
        _textLayer.string = _text;
        _textLayer.fontSize = _textSize;
        _textLayer.foregroundColor = [self colorFromArray:_textColor].CGColor;
        _textLayer.hidden = NO;
    } else {
        _textLayer.hidden = YES;
    }
    
    // Update progress text
    if (_progressTextLayer) {
        if (_showProgress && _progressText && _progressText.length > 0) {
            _progressTextLayer.string = _progressText;
            _progressTextLayer.hidden = NO;
        } else {
            _progressTextLayer.hidden = YES;
        }
    }
    
    // Update text positions
    [self updateTextPositions];
}

- (void)createDotLayers {
    for (NSInteger i = 0; i < _dotCount; i++) {
        CAShapeLayer *dotLayer = [CAShapeLayer layer];
        dotLayer.fillColor = [self colorFromArray:_color].CGColor;
        
        UIBezierPath *dotPath = [UIBezierPath bezierPathWithOvalInRect:CGRectMake(0, 0, _dotSize, _dotSize)];
        dotLayer.path = dotPath.CGPath;
        
        [self.layer addSublayer:dotLayer];
        [_dotLayers addObject:dotLayer];
    }
}

- (void)createBarLayers {
    for (NSInteger i = 0; i < _barCount; i++) {
        CAShapeLayer *barLayer = [CAShapeLayer layer];
        barLayer.fillColor = [self colorFromArray:_color].CGColor;
        
        UIBezierPath *barPath = [UIBezierPath bezierPathWithRect:CGRectMake(0, 0, _barWidth, _barHeight)];
        barLayer.path = barPath.CGPath;
        
        [self.layer addSublayer:barLayer];
        [_barLayers addObject:barLayer];
    }
}

- (void)updateDotPositions {
    CGPoint center = CGPointMake(self.bounds.size.width / 2, self.bounds.size.height / 2);
    
    for (NSInteger i = 0; i < _dotLayers.count; i++) {
        CAShapeLayer *dotLayer = _dotLayers[i];
        
        CGFloat angle = (2 * M_PI * i) / _dotCount;
        CGFloat x = center.x + (_radius + _spacing) * cos(angle) - _dotSize / 2;
        CGFloat y = center.y + (_radius + _spacing) * sin(angle) - _dotSize / 2;
        
        dotLayer.position = CGPointMake(x, y);
    }
}

- (void)updateBarPositions {
    CGPoint center = CGPointMake(self.bounds.size.width / 2, self.bounds.size.height / 2);
    
    for (NSInteger i = 0; i < _barLayers.count; i++) {
        CAShapeLayer *barLayer = _barLayers[i];
        
        CGFloat angle = (2 * M_PI * i) / _barCount;
        CGFloat x = center.x + _radius * cos(angle) - _barWidth / 2;
        CGFloat y = center.y + _radius * sin(angle) - _barHeight / 2;
        
        barLayer.position = CGPointMake(x, y);
        barLayer.transform = CATransform3DMakeRotation(angle, 0, 0, 1);
    }
}

- (void)updateTextPositions {
    CGPoint center = CGPointMake(self.bounds.size.width / 2, self.bounds.size.height / 2);
    CGFloat textY = center.y;
    
    if ([_textPosition isEqualToString:@"top"]) {
        textY = center.y - _radius - _textOffset - _textSize;
    } else if ([_textPosition isEqualToString:@"bottom"]) {
        textY = center.y + _radius + _textOffset;
    } else if ([_textPosition isEqualToString:@"center"]) {
        textY = center.y - _textSize / 2;
    }
    
    _textLayer.frame = CGRectMake(0, textY, self.bounds.size.width, _textSize + 4);
    
    if (_progressTextLayer) {
        _progressTextLayer.frame = CGRectMake(0, center.y - _textSize / 2, self.bounds.size.width, _textSize + 4);
    }
}

- (void)startCircularAnimation {
    CABasicAnimation *rotationAnimation = [CABasicAnimation animationWithKeyPath:@"transform.rotation"];
    rotationAnimation.fromValue = @(0);
    rotationAnimation.toValue = @(2 * M_PI);
    rotationAnimation.duration = _duration / _speed;
    rotationAnimation.repeatCount = HUGE_VALF;
    rotationAnimation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionLinear];
    
    if ([_direction isEqualToString:@"counterclockwise"]) {
        rotationAnimation.toValue = @(-2 * M_PI);
    }
    
    [_spinnerLayer addAnimation:rotationAnimation forKey:@"rotation"];
}

- (void)startDotsAnimation {
    for (NSInteger i = 0; i < _dotLayers.count; i++) {
        CAShapeLayer *dotLayer = _dotLayers[i];
        
        CABasicAnimation *scaleAnimation = [CABasicAnimation animationWithKeyPath:@"transform.scale"];
        scaleAnimation.fromValue = @(0.5);
        scaleAnimation.toValue = @(1.0);
        scaleAnimation.duration = _duration / _speed;
        scaleAnimation.repeatCount = HUGE_VALF;
        scaleAnimation.autoreverses = YES;
        scaleAnimation.beginTime = CACurrentMediaTime() + (i * _duration / _dotCount);
        
        [dotLayer addAnimation:scaleAnimation forKey:@"scale"];
    }
}

- (void)startBarsAnimation {
    for (NSInteger i = 0; i < _barLayers.count; i++) {
        CAShapeLayer *barLayer = _barLayers[i];
        
        CABasicAnimation *scaleAnimation = [CABasicAnimation animationWithKeyPath:@"transform.scale.y"];
        scaleAnimation.fromValue = @(0.3);
        scaleAnimation.toValue = @(1.0);
        scaleAnimation.duration = _duration / _speed;
        scaleAnimation.repeatCount = HUGE_VALF;
        scaleAnimation.autoreverses = YES;
        scaleAnimation.beginTime = CACurrentMediaTime() + (i * _duration / _barCount);
        
        [barLayer addAnimation:scaleAnimation forKey:@"scale"];
    }
}

- (void)startRingAnimation {
    CABasicAnimation *strokeAnimation = [CABasicAnimation animationWithKeyPath:@"strokeEnd"];
    strokeAnimation.fromValue = @(0.0);
    strokeAnimation.toValue = @(1.0);
    strokeAnimation.duration = _duration / _speed;
    strokeAnimation.repeatCount = HUGE_VALF;
    strokeAnimation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    
    [_spinnerLayer addAnimation:strokeAnimation forKey:@"stroke"];
}

- (void)startPulseAnimation {
    CABasicAnimation *scaleAnimation = [CABasicAnimation animationWithKeyPath:@"transform.scale"];
    scaleAnimation.fromValue = @(1.0);
    scaleAnimation.toValue = @(_pulseScale);
    scaleAnimation.duration = _duration / _speed;
    scaleAnimation.repeatCount = HUGE_VALF;
    scaleAnimation.autoreverses = YES;
    scaleAnimation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    
    CABasicAnimation *opacityAnimation = [CABasicAnimation animationWithKeyPath:@"opacity"];
    opacityAnimation.fromValue = @(1.0);
    opacityAnimation.toValue = @(0.0);
    opacityAnimation.duration = _duration / _speed;
    opacityAnimation.repeatCount = HUGE_VALF;
    opacityAnimation.autoreverses = YES;
    opacityAnimation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    
    [_spinnerLayer addAnimation:scaleAnimation forKey:@"scale"];
    [_spinnerLayer addAnimation:opacityAnimation forKey:@"opacity"];
}

- (void)stopAllAnimations {
    [_spinnerLayer removeAllAnimations];
    
    for (CAShapeLayer *layer in _dotLayers) {
        [layer removeAllAnimations];
    }
    
    for (CAShapeLayer *layer in _barLayers) {
        [layer removeAllAnimations];
    }
}

- (UIColor *)colorFromArray:(NSArray<NSNumber *> *)colorArray {
    if (!colorArray || colorArray.count < 3) {
        return [UIColor whiteColor];
    }
    
    CGFloat red = [colorArray[0] floatValue];
    CGFloat green = [colorArray[1] floatValue];
    CGFloat blue = [colorArray[2] floatValue];
    CGFloat alpha = colorArray.count > 3 ? [colorArray[3] floatValue] : 1.0f;
    
    return [UIColor colorWithRed:red green:green blue:blue alpha:alpha];
}

#pragma mark - Layout

- (void)layoutSubviews {
    [super layoutSubviews];
    [self updateSpinnerGeometry];
    [self updateTextPositions];
}

@end