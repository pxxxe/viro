//
//  ViroButtonComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroButtonComponentView.h"
#import "ViroReactUtils.h"
#import "ViroLog.h"
#import <React/RCTConversions.h>
#import <React/RCTLog.h>
#import <QuartzCore/QuartzCore.h>

typedef NS_ENUM(NSInteger, ViroButtonState) {
    ViroButtonStateNormal = 0,
    ViroButtonStateHighlighted,
    ViroButtonStateSelected,
    ViroButtonStateDisabled,
    ViroButtonStateHovered,
    ViroButtonStateGazed
};

@implementation ViroButtonComponentView {
    // Button content
    NSDictionary *_source;
    NSDictionary *_imageSource;
    NSString *_text;
    NSArray<NSNumber *> *_textColor;
    CGFloat _textSize;
    NSString *_textFont;
    NSString *_textStyle;
    
    // Button appearance
    NSDictionary *_gazeSource;
    NSDictionary *_clickSource;
    NSDictionary *_hoverSource;
    NSArray<NSNumber *> *_backgroundColor;
    NSArray<NSNumber *> *_borderColor;
    CGFloat _borderWidth;
    CGFloat _cornerRadius;
    
    // Button dimensions
    CGFloat _buttonWidth;
    CGFloat _buttonHeight;
    NSArray<NSNumber *> *_padding;
    NSArray<NSNumber *> *_margin;
    
    // Button states
    BOOL _enabled;
    BOOL _selected;
    BOOL _highlighted;
    BOOL _toggleable;
    ViroButtonState _currentState;
    
    // Button behavior
    NSArray<NSNumber *> *_clickTintColor;
    NSArray<NSNumber *> *_hoverTintColor;
    NSArray<NSNumber *> *_gazeTintColor;
    NSArray<NSNumber *> *_clickScale;
    NSArray<NSNumber *> *_hoverScale;
    NSArray<NSNumber *> *_gazeScale;
    
    // Button animation
    CGFloat _animationDuration;
    NSString *_animationEasing;
    NSDictionary *_clickAnimation;
    NSDictionary *_hoverAnimation;
    NSDictionary *_gazeAnimation;
    
    // Button interaction
    CGFloat _clickDistance;
    CGFloat _hoverDistance;
    CGFloat _gazeDistance;
    NSString *_clickTrigger;
    NSString *_hoverTrigger;
    NSString *_gazeTrigger;
    
    // Button accessibility
    NSString *_accessibilityLabel;
    NSString *_accessibilityHint;
    NSString *_accessibilityRole;
    
    // Internal state
    BOOL _isAnimating;
    CALayer *_backgroundLayer;
    CALayer *_borderLayer;
    CATextLayer *_textLayer;
    CALayer *_imageLayer;
    
    // Event blocks
    RCTBubblingEventBlock _onClick;
    RCTBubblingEventBlock _onHover;
    RCTBubblingEventBlock _onGaze;
    RCTBubblingEventBlock _onTouch;
    RCTBubblingEventBlock _onStateChange;
}

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        static const auto defaultProps = std::make_shared<const facebook::react::ViroButtonProps>();
        _props = defaultProps;
        
        // Initialize default values
        _textColor = @[@(1.0), @(1.0), @(1.0), @(1.0)];
        _textSize = 16.0f;
        _textFont = @"System";
        _textStyle = @"normal";
        _backgroundColor = @[@(0.2), @(0.2), @(0.2), @(0.8)];
        _borderColor = @[@(1.0), @(1.0), @(1.0), @(1.0)];
        _borderWidth = 1.0f;
        _cornerRadius = 8.0f;
        _buttonWidth = 100.0f;
        _buttonHeight = 40.0f;
        _padding = @[@(10.0), @(10.0), @(10.0), @(10.0)];
        _margin = @[@(0.0), @(0.0), @(0.0), @(0.0)];
        
        // Initialize button states
        _enabled = YES;
        _selected = NO;
        _highlighted = NO;
        _toggleable = NO;
        _currentState = ViroButtonStateNormal;
        
        // Initialize button behavior
        _clickTintColor = @[@(0.8), @(0.8), @(0.8), @(1.0)];
        _hoverTintColor = @[@(0.9), @(0.9), @(0.9), @(1.0)];
        _gazeTintColor = @[@(0.95), @(0.95), @(0.95), @(1.0)];
        _clickScale = @[@(0.95), @(0.95), @(0.95)];
        _hoverScale = @[@(1.05), @(1.05), @(1.05)];
        _gazeScale = @[@(1.02), @(1.02), @(1.02)];
        
        // Initialize button animation
        _animationDuration = 0.2f;
        _animationEasing = @"ease-in-out";
        
        // Initialize button interaction
        _clickDistance = 0.1f;
        _hoverDistance = 0.5f;
        _gazeDistance = 2.0f;
        _clickTrigger = @"touch";
        _hoverTrigger = @"hover";
        _gazeTrigger = @"gaze";
        
        // Initialize accessibility
        _accessibilityRole = @"button";
        
        // Initialize internal state
        _isAnimating = NO;
        
        // Set up button layers
        [self setupButtonLayers];
        
        VRTLogDebug(@"ViroButton initialized");
    }
    return self;
}

#pragma mark - RCTComponentViewProtocol

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<facebook::react::ViroButtonComponentDescriptor>();
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
    const auto &viroProps = *std::static_pointer_cast<facebook::react::ViroButtonProps const>(props);
    const auto &oldViroProps = *std::static_pointer_cast<facebook::react::ViroButtonProps const>(oldProps);
    
    [super updateProps:props oldProps:oldProps];
    
    // TODO: Update properties from viroProps
    // This will be implemented when Fabric code generation is complete
    VRTLogDebug(@"ViroButton props updated");
}

#pragma mark - Button Content

- (void)setSource:(nullable NSDictionary *)source {
    VRTLogDebug(@"Setting source: %@", source);
    _source = source;
    [self updateButtonAppearance];
}

- (void)setImageSource:(nullable NSDictionary *)imageSource {
    VRTLogDebug(@"Setting image source: %@", imageSource);
    _imageSource = imageSource;
    [self updateButtonContent];
}

- (void)setText:(nullable NSString *)text {
    VRTLogDebug(@"Setting text: %@", text);
    _text = text;
    [self updateButtonContent];
}

- (void)setTextColor:(nullable NSArray<NSNumber *> *)textColor {
    VRTLogDebug(@"Setting text color: %@", textColor);
    _textColor = textColor ?: @[@(1.0), @(1.0), @(1.0), @(1.0)];
    [self updateButtonContent];
}

- (void)setTextSize:(CGFloat)textSize {
    VRTLogDebug(@"Setting text size: %.1f", textSize);
    _textSize = textSize;
    [self updateButtonContent];
}

- (void)setTextFont:(nullable NSString *)textFont {
    VRTLogDebug(@"Setting text font: %@", textFont);
    _textFont = textFont ?: @"System";
    [self updateButtonContent];
}

- (void)setTextStyle:(nullable NSString *)textStyle {
    VRTLogDebug(@"Setting text style: %@", textStyle);
    _textStyle = textStyle ?: @"normal";
    [self updateButtonContent];
}

#pragma mark - Button Appearance

- (void)setGazeSource:(nullable NSDictionary *)gazeSource {
    VRTLogDebug(@"Setting gaze source: %@", gazeSource);
    _gazeSource = gazeSource;
    [self updateButtonAppearance];
}

- (void)setClickSource:(nullable NSDictionary *)clickSource {
    VRTLogDebug(@"Setting click source: %@", clickSource);
    _clickSource = clickSource;
    [self updateButtonAppearance];
}

- (void)setHoverSource:(nullable NSDictionary *)hoverSource {
    VRTLogDebug(@"Setting hover source: %@", hoverSource);
    _hoverSource = hoverSource;
    [self updateButtonAppearance];
}

- (void)setBackgroundColor:(nullable NSArray<NSNumber *> *)backgroundColor {
    VRTLogDebug(@"Setting background color: %@", backgroundColor);
    _backgroundColor = backgroundColor ?: @[@(0.2), @(0.2), @(0.2), @(0.8)];
    [self updateButtonAppearance];
}

- (void)setBorderColor:(nullable NSArray<NSNumber *> *)borderColor {
    VRTLogDebug(@"Setting border color: %@", borderColor);
    _borderColor = borderColor ?: @[@(1.0), @(1.0), @(1.0), @(1.0)];
    [self updateButtonAppearance];
}

- (void)setBorderWidth:(CGFloat)borderWidth {
    VRTLogDebug(@"Setting border width: %.1f", borderWidth);
    _borderWidth = borderWidth;
    [self updateButtonAppearance];
}

- (void)setCornerRadius:(CGFloat)cornerRadius {
    VRTLogDebug(@"Setting corner radius: %.1f", cornerRadius);
    _cornerRadius = cornerRadius;
    [self updateButtonAppearance];
}

#pragma mark - Button Dimensions

- (void)setButtonWidth:(CGFloat)buttonWidth {
    VRTLogDebug(@"Setting button width: %.1f", buttonWidth);
    _buttonWidth = buttonWidth;
    [self updateButtonGeometry];
}

- (void)setButtonHeight:(CGFloat)buttonHeight {
    VRTLogDebug(@"Setting button height: %.1f", buttonHeight);
    _buttonHeight = buttonHeight;
    [self updateButtonGeometry];
}

- (void)setPadding:(nullable NSArray<NSNumber *> *)padding {
    VRTLogDebug(@"Setting padding: %@", padding);
    _padding = padding ?: @[@(10.0), @(10.0), @(10.0), @(10.0)];
    [self updateButtonGeometry];
}

- (void)setMargin:(nullable NSArray<NSNumber *> *)margin {
    VRTLogDebug(@"Setting margin: %@", margin);
    _margin = margin ?: @[@(0.0), @(0.0), @(0.0), @(0.0)];
    [self updateButtonGeometry];
}

#pragma mark - Button States

- (void)setEnabled:(BOOL)enabled {
    VRTLogDebug(@"Setting enabled: %d", enabled);
    _enabled = enabled;
    [self updateButtonState];
}

- (void)setSelected:(BOOL)selected {
    VRTLogDebug(@"Setting selected: %d", selected);
    _selected = selected;
    [self updateButtonState];
}

- (void)setHighlighted:(BOOL)highlighted {
    VRTLogDebug(@"Setting highlighted: %d", highlighted);
    _highlighted = highlighted;
    [self updateButtonState];
}

- (void)setToggleable:(BOOL)toggleable {
    VRTLogDebug(@"Setting toggleable: %d", toggleable);
    _toggleable = toggleable;
}

#pragma mark - Button Behavior

- (void)setClickTintColor:(nullable NSArray<NSNumber *> *)clickTintColor {
    VRTLogDebug(@"Setting click tint color: %@", clickTintColor);
    _clickTintColor = clickTintColor ?: @[@(0.8), @(0.8), @(0.8), @(1.0)];
}

- (void)setHoverTintColor:(nullable NSArray<NSNumber *> *)hoverTintColor {
    VRTLogDebug(@"Setting hover tint color: %@", hoverTintColor);
    _hoverTintColor = hoverTintColor ?: @[@(0.9), @(0.9), @(0.9), @(1.0)];
}

- (void)setGazeTintColor:(nullable NSArray<NSNumber *> *)gazeTintColor {
    VRTLogDebug(@"Setting gaze tint color: %@", gazeTintColor);
    _gazeTintColor = gazeTintColor ?: @[@(0.95), @(0.95), @(0.95), @(1.0)];
}

- (void)setClickScale:(nullable NSArray<NSNumber *> *)clickScale {
    VRTLogDebug(@"Setting click scale: %@", clickScale);
    _clickScale = clickScale ?: @[@(0.95), @(0.95), @(0.95)];
}

- (void)setHoverScale:(nullable NSArray<NSNumber *> *)hoverScale {
    VRTLogDebug(@"Setting hover scale: %@", hoverScale);
    _hoverScale = hoverScale ?: @[@(1.05), @(1.05), @(1.05)];
}

- (void)setGazeScale:(nullable NSArray<NSNumber *> *)gazeScale {
    VRTLogDebug(@"Setting gaze scale: %@", gazeScale);
    _gazeScale = gazeScale ?: @[@(1.02), @(1.02), @(1.02)];
}

#pragma mark - Button Animation

- (void)setAnimationDuration:(CGFloat)animationDuration {
    VRTLogDebug(@"Setting animation duration: %.2f", animationDuration);
    _animationDuration = animationDuration;
}

- (void)setAnimationEasing:(nullable NSString *)animationEasing {
    VRTLogDebug(@"Setting animation easing: %@", animationEasing);
    _animationEasing = animationEasing ?: @"ease-in-out";
}

- (void)setClickAnimation:(nullable NSDictionary *)clickAnimation {
    VRTLogDebug(@"Setting click animation: %@", clickAnimation);
    _clickAnimation = clickAnimation;
}

- (void)setHoverAnimation:(nullable NSDictionary *)hoverAnimation {
    VRTLogDebug(@"Setting hover animation: %@", hoverAnimation);
    _hoverAnimation = hoverAnimation;
}

- (void)setGazeAnimation:(nullable NSDictionary *)gazeAnimation {
    VRTLogDebug(@"Setting gaze animation: %@", gazeAnimation);
    _gazeAnimation = gazeAnimation;
}

#pragma mark - Button Interaction

- (void)setClickDistance:(CGFloat)clickDistance {
    VRTLogDebug(@"Setting click distance: %.2f", clickDistance);
    _clickDistance = clickDistance;
}

- (void)setHoverDistance:(CGFloat)hoverDistance {
    VRTLogDebug(@"Setting hover distance: %.2f", hoverDistance);
    _hoverDistance = hoverDistance;
}

- (void)setGazeDistance:(CGFloat)gazeDistance {
    VRTLogDebug(@"Setting gaze distance: %.2f", gazeDistance);
    _gazeDistance = gazeDistance;
}

- (void)setClickTrigger:(nullable NSString *)clickTrigger {
    VRTLogDebug(@"Setting click trigger: %@", clickTrigger);
    _clickTrigger = clickTrigger ?: @"touch";
}

- (void)setHoverTrigger:(nullable NSString *)hoverTrigger {
    VRTLogDebug(@"Setting hover trigger: %@", hoverTrigger);
    _hoverTrigger = hoverTrigger ?: @"hover";
}

- (void)setGazeTrigger:(nullable NSString *)gazeTrigger {
    VRTLogDebug(@"Setting gaze trigger: %@", gazeTrigger);
    _gazeTrigger = gazeTrigger ?: @"gaze";
}

#pragma mark - Button Accessibility

- (void)setAccessibilityLabel:(nullable NSString *)accessibilityLabel {
    VRTLogDebug(@"Setting accessibility label: %@", accessibilityLabel);
    _accessibilityLabel = accessibilityLabel;
    self.accessibilityLabel = accessibilityLabel;
}

- (void)setAccessibilityHint:(nullable NSString *)accessibilityHint {
    VRTLogDebug(@"Setting accessibility hint: %@", accessibilityHint);
    _accessibilityHint = accessibilityHint;
    self.accessibilityHint = accessibilityHint;
}

- (void)setAccessibilityRole:(nullable NSString *)accessibilityRole {
    VRTLogDebug(@"Setting accessibility role: %@", accessibilityRole);
    _accessibilityRole = accessibilityRole ?: @"button";
}

#pragma mark - Events

- (void)setOnClick:(nullable RCTBubblingEventBlock)onClick {
    _onClick = onClick;
}

- (void)setOnHover:(nullable RCTBubblingEventBlock)onHover {
    _onHover = onHover;
}

- (void)setOnGaze:(nullable RCTBubblingEventBlock)onGaze {
    _onGaze = onGaze;
}

- (void)setOnTouch:(nullable RCTBubblingEventBlock)onTouch {
    _onTouch = onTouch;
}

- (void)setOnStateChange:(nullable RCTBubblingEventBlock)onStateChange {
    _onStateChange = onStateChange;
}

#pragma mark - Button Control Methods

- (void)performClick {
    VRTLogDebug(@"Performing click");
    
    if (!_enabled) {
        VRTLogDebug(@"Button is disabled, ignoring click");
        return;
    }
    
    // Handle toggle behavior
    if (_toggleable) {
        _selected = !_selected;
    }
    
    // Animate click
    [self animateClick];
    
    // Fire click event
    if (_onClick) {
        _onClick(@{
            @"selected": @(_selected),
            @"state": [self getCurrentState]
        });
    }
    
    // Fire state change event
    if (_onStateChange) {
        _onStateChange(@{
            @"state": [self getCurrentState],
            @"selected": @(_selected)
        });
    }
}

- (void)setButtonState:(NSString *)state {
    VRTLogDebug(@"Setting button state: %@", state);
    
    if ([state isEqualToString:@"normal"]) {
        _currentState = ViroButtonStateNormal;
    } else if ([state isEqualToString:@"highlighted"]) {
        _currentState = ViroButtonStateHighlighted;
    } else if ([state isEqualToString:@"selected"]) {
        _currentState = ViroButtonStateSelected;
    } else if ([state isEqualToString:@"disabled"]) {
        _currentState = ViroButtonStateDisabled;
    } else if ([state isEqualToString:@"hovered"]) {
        _currentState = ViroButtonStateHovered;
    } else if ([state isEqualToString:@"gazed"]) {
        _currentState = ViroButtonStateGazed;
    }
    
    [self updateButtonState];
}

- (void)animateToState:(NSString *)state {
    VRTLogDebug(@"Animating to state: %@", state);
    
    [self setButtonState:state];
    
    // TODO: Implement smooth animation transition
    // This would animate between current and target states
}

- (void)resetButtonState {
    VRTLogDebug(@"Resetting button state");
    _currentState = ViroButtonStateNormal;
    _selected = NO;
    _highlighted = NO;
    [self updateButtonState];
}

#pragma mark - Button State Information

- (NSString *)getCurrentState {
    switch (_currentState) {
        case ViroButtonStateNormal:
            return @"normal";
        case ViroButtonStateHighlighted:
            return @"highlighted";
        case ViroButtonStateSelected:
            return @"selected";
        case ViroButtonStateDisabled:
            return @"disabled";
        case ViroButtonStateHovered:
            return @"hovered";
        case ViroButtonStateGazed:
            return @"gazed";
        default:
            return @"normal";
    }
}

- (BOOL)isClickable {
    return _enabled && [_clickTrigger isEqualToString:@"touch"];
}

- (BOOL)isHoverable {
    return _enabled && [_hoverTrigger isEqualToString:@"hover"];
}

- (BOOL)isGazeable {
    return _enabled && [_gazeTrigger isEqualToString:@"gaze"];
}

- (CGRect)getButtonBounds {
    return CGRectMake(0, 0, _buttonWidth, _buttonHeight);
}

#pragma mark - Helper Methods

- (void)setupButtonLayers {
    // Create background layer
    _backgroundLayer = [CALayer layer];
    _backgroundLayer.masksToBounds = YES;
    [self.layer addSublayer:_backgroundLayer];
    
    // Create border layer
    _borderLayer = [CALayer layer];
    _borderLayer.fillColor = [UIColor clearColor].CGColor;
    [self.layer addSublayer:_borderLayer];
    
    // Create text layer
    _textLayer = [CATextLayer layer];
    _textLayer.alignmentMode = kCAAlignmentCenter;
    _textLayer.truncationMode = kCATruncationEnd;
    [self.layer addSublayer:_textLayer];
    
    // Create image layer
    _imageLayer = [CALayer layer];
    _imageLayer.contentsGravity = kCAGravityResizeAspect;
    [self.layer addSublayer:_imageLayer];
}

- (void)updateButtonContent {
    // Update text layer
    if (_text && _text.length > 0) {
        _textLayer.string = _text;
        _textLayer.fontSize = _textSize;
        _textLayer.foregroundColor = [self colorFromArray:_textColor].CGColor;
        _textLayer.hidden = NO;
        _imageLayer.hidden = YES;
    } else {
        _textLayer.hidden = YES;
        _imageLayer.hidden = NO;
    }
    
    // Update image layer
    if (_imageSource) {
        // TODO: Load image from source
        // This would load the image and set it to _imageLayer.contents
    }
}

- (void)updateButtonAppearance {
    // Update background color
    _backgroundLayer.backgroundColor = [self colorFromArray:_backgroundColor].CGColor;
    _backgroundLayer.cornerRadius = _cornerRadius;
    
    // Update border
    _borderLayer.borderColor = [self colorFromArray:_borderColor].CGColor;
    _borderLayer.borderWidth = _borderWidth;
    _borderLayer.cornerRadius = _cornerRadius;
}

- (void)updateButtonGeometry {
    CGRect bounds = CGRectMake(0, 0, _buttonWidth, _buttonHeight);
    
    // Update layer frames
    _backgroundLayer.frame = bounds;
    _borderLayer.frame = bounds;
    
    // Update text layer frame with padding
    if (_padding && _padding.count >= 4) {
        CGFloat top = [_padding[0] floatValue];
        CGFloat right = [_padding[1] floatValue];
        CGFloat bottom = [_padding[2] floatValue];
        CGFloat left = [_padding[3] floatValue];
        
        _textLayer.frame = CGRectMake(left, top, _buttonWidth - left - right, _buttonHeight - top - bottom);
        _imageLayer.frame = CGRectMake(left, top, _buttonWidth - left - right, _buttonHeight - top - bottom);
    } else {
        _textLayer.frame = bounds;
        _imageLayer.frame = bounds;
    }
}

- (void)updateButtonState {
    // Update enabled state
    self.alpha = _enabled ? 1.0f : 0.5f;
    
    // Update visual state based on current state
    switch (_currentState) {
        case ViroButtonStateNormal:
            [self applyNormalState];
            break;
        case ViroButtonStateHighlighted:
            [self applyHighlightedState];
            break;
        case ViroButtonStateSelected:
            [self applySelectedState];
            break;
        case ViroButtonStateDisabled:
            [self applyDisabledState];
            break;
        case ViroButtonStateHovered:
            [self applyHoveredState];
            break;
        case ViroButtonStateGazed:
            [self applyGazedState];
            break;
    }
}

- (void)applyNormalState {
    // Reset to normal appearance
    [self updateButtonAppearance];
    self.transform = CGAffineTransformIdentity;
}

- (void)applyHighlightedState {
    // Apply highlighted appearance
    if (_clickTintColor) {
        _backgroundLayer.backgroundColor = [self colorFromArray:_clickTintColor].CGColor;
    }
    if (_clickScale) {
        CGFloat scale = [_clickScale[0] floatValue];
        self.transform = CGAffineTransformMakeScale(scale, scale);
    }
}

- (void)applySelectedState {
    // Apply selected appearance
    // TODO: Implement selected state appearance
}

- (void)applyDisabledState {
    // Apply disabled appearance
    self.alpha = 0.5f;
}

- (void)applyHoveredState {
    // Apply hovered appearance
    if (_hoverTintColor) {
        _backgroundLayer.backgroundColor = [self colorFromArray:_hoverTintColor].CGColor;
    }
    if (_hoverScale) {
        CGFloat scale = [_hoverScale[0] floatValue];
        self.transform = CGAffineTransformMakeScale(scale, scale);
    }
}

- (void)applyGazedState {
    // Apply gazed appearance
    if (_gazeTintColor) {
        _backgroundLayer.backgroundColor = [self colorFromArray:_gazeTintColor].CGColor;
    }
    if (_gazeScale) {
        CGFloat scale = [_gazeScale[0] floatValue];
        self.transform = CGAffineTransformMakeScale(scale, scale);
    }
}

- (void)animateClick {
    if (_isAnimating) {
        return;
    }
    
    _isAnimating = YES;
    
    // Create click animation
    CABasicAnimation *scaleAnimation = [CABasicAnimation animationWithKeyPath:@"transform.scale"];
    scaleAnimation.fromValue = @(1.0);
    scaleAnimation.toValue = @([_clickScale[0] floatValue]);
    scaleAnimation.duration = _animationDuration / 2.0;
    scaleAnimation.autoreverses = YES;
    scaleAnimation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    
    // Set completion block
    scaleAnimation.delegate = self;
    
    [self.layer addAnimation:scaleAnimation forKey:@"clickAnimation"];
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

#pragma mark - CAAnimationDelegate

- (void)animationDidStop:(CAAnimation *)anim finished:(BOOL)flag {
    _isAnimating = NO;
}

#pragma mark - Touch Events

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [super touchesBegan:touches withEvent:event];
    
    if (_enabled && [self isClickable]) {
        _currentState = ViroButtonStateHighlighted;
        [self updateButtonState];
    }
}

- (void)touchesEnded:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [super touchesEnded:touches withEvent:event];
    
    if (_enabled && [self isClickable]) {
        _currentState = ViroButtonStateNormal;
        [self updateButtonState];
        [self performClick];
    }
}

- (void)touchesCancelled:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [super touchesCancelled:touches withEvent:event];
    
    if (_enabled) {
        _currentState = ViroButtonStateNormal;
        [self updateButtonState];
    }
}

#pragma mark - Layout

- (void)layoutSubviews {
    [super layoutSubviews];
    [self updateButtonGeometry];
}

@end