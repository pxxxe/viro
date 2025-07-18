//
//  ViroButtonComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroButtonComponentView : RCTViewComponentView

// Button content
- (void)setSource:(nullable NSDictionary *)source;
- (void)setImageSource:(nullable NSDictionary *)imageSource;
- (void)setText:(nullable NSString *)text;
- (void)setTextColor:(nullable NSArray<NSNumber *> *)textColor;
- (void)setTextSize:(CGFloat)textSize;
- (void)setTextFont:(nullable NSString *)textFont;
- (void)setTextStyle:(nullable NSString *)textStyle;

// Button appearance
- (void)setGazeSource:(nullable NSDictionary *)gazeSource;
- (void)setClickSource:(nullable NSDictionary *)clickSource;
- (void)setHoverSource:(nullable NSDictionary *)hoverSource;
- (void)setBackgroundColor:(nullable NSArray<NSNumber *> *)backgroundColor;
- (void)setBorderColor:(nullable NSArray<NSNumber *> *)borderColor;
- (void)setBorderWidth:(CGFloat)borderWidth;
- (void)setCornerRadius:(CGFloat)cornerRadius;

// Button dimensions
- (void)setButtonWidth:(CGFloat)buttonWidth;
- (void)setButtonHeight:(CGFloat)buttonHeight;
- (void)setPadding:(nullable NSArray<NSNumber *> *)padding;
- (void)setMargin:(nullable NSArray<NSNumber *> *)margin;

// Button states
- (void)setEnabled:(BOOL)enabled;
- (void)setSelected:(BOOL)selected;
- (void)setHighlighted:(BOOL)highlighted;
- (void)setToggleable:(BOOL)toggleable;

// Button behavior
- (void)setClickTintColor:(nullable NSArray<NSNumber *> *)clickTintColor;
- (void)setHoverTintColor:(nullable NSArray<NSNumber *> *)hoverTintColor;
- (void)setGazeTintColor:(nullable NSArray<NSNumber *> *)gazeTintColor;
- (void)setClickScale:(nullable NSArray<NSNumber *> *)clickScale;
- (void)setHoverScale:(nullable NSArray<NSNumber *> *)hoverScale;
- (void)setGazeScale:(nullable NSArray<NSNumber *> *)gazeScale;

// Button animation
- (void)setAnimationDuration:(CGFloat)animationDuration;
- (void)setAnimationEasing:(nullable NSString *)animationEasing;
- (void)setClickAnimation:(nullable NSDictionary *)clickAnimation;
- (void)setHoverAnimation:(nullable NSDictionary *)hoverAnimation;
- (void)setGazeAnimation:(nullable NSDictionary *)gazeAnimation;

// Button interaction
- (void)setClickDistance:(CGFloat)clickDistance;
- (void)setHoverDistance:(CGFloat)hoverDistance;
- (void)setGazeDistance:(CGFloat)gazeDistance;
- (void)setClickTrigger:(nullable NSString *)clickTrigger;
- (void)setHoverTrigger:(nullable NSString *)hoverTrigger;
- (void)setGazeTrigger:(nullable NSString *)gazeTrigger;

// Button accessibility
- (void)setAccessibilityLabel:(nullable NSString *)accessibilityLabel;
- (void)setAccessibilityHint:(nullable NSString *)accessibilityHint;
- (void)setAccessibilityRole:(nullable NSString *)accessibilityRole;

// Event callbacks
- (void)setOnClick:(nullable RCTBubblingEventBlock)onClick;
- (void)setOnHover:(nullable RCTBubblingEventBlock)onHover;
- (void)setOnGaze:(nullable RCTBubblingEventBlock)onGaze;
- (void)setOnTouch:(nullable RCTBubblingEventBlock)onTouch;
- (void)setOnStateChange:(nullable RCTBubblingEventBlock)onStateChange;

// Button control methods
- (void)performClick;
- (void)setButtonState:(NSString *)state;
- (void)animateToState:(NSString *)state;
- (void)resetButtonState;

// Button state information
- (NSString *)getCurrentState;
- (BOOL)isClickable;
- (BOOL)isHoverable;
- (BOOL)isGazeable;
- (CGRect)getButtonBounds;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END