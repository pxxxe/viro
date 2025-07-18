//
//  ViroSpinnerComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * ViroSpinnerComponentView - Loading Indicator Component
 * 
 * This component provides animated loading indicators for ViroReact applications.
 * It supports various spinner types, animations, and customization options
 * to create engaging loading experiences in 3D environments.
 * 
 * Key Features:
 * - Multiple spinner types (circular, dots, bars, ring, pulse)
 * - Customizable colors, sizes, and animations
 * - Speed and direction control
 * - Fade in/out animations
 * - Auto-hide on completion
 * - Progress indication support
 * - Integration with ViroReact scene graph
 */
@interface ViroSpinnerComponentView : RCTViewComponentView

// Spinner appearance
- (void)setType:(nullable NSString *)type;
- (void)setColor:(nullable NSArray<NSNumber *> *)color;
- (void)setSize:(CGFloat)size;
- (void)setThickness:(CGFloat)thickness;
- (void)setRadius:(CGFloat)radius;
- (void)setSpacing:(CGFloat)spacing;

// Spinner animation
- (void)setAnimating:(BOOL)animating;
- (void)setSpeed:(CGFloat)speed;
- (void)setDirection:(nullable NSString *)direction;
- (void)setDuration:(CGFloat)duration;
- (void)setEasing:(nullable NSString *)easing;
- (void)setDelay:(CGFloat)delay;

// Spinner behavior
- (void)setVisible:(BOOL)visible;
- (void)setAutoHide:(BOOL)autoHide;
- (void)setHideDelay:(CGFloat)hideDelay;
- (void)setFadeInDuration:(CGFloat)fadeInDuration;
- (void)setFadeOutDuration:(CGFloat)fadeOutDuration;

// Spinner progress
- (void)setProgress:(CGFloat)progress;
- (void)setProgressColor:(nullable NSArray<NSNumber *> *)progressColor;
- (void)setProgressBackgroundColor:(nullable NSArray<NSNumber *> *)progressBackgroundColor;
- (void)setShowProgress:(BOOL)showProgress;
- (void)setProgressText:(nullable NSString *)progressText;

// Spinner text
- (void)setText:(nullable NSString *)text;
- (void)setTextColor:(nullable NSArray<NSNumber *> *)textColor;
- (void)setTextSize:(CGFloat)textSize;
- (void)setTextFont:(nullable NSString *)textFont;
- (void)setTextPosition:(nullable NSString *)textPosition;
- (void)setTextOffset:(CGFloat)textOffset;

// Spinner customization
- (void)setDotCount:(NSInteger)dotCount;
- (void)setDotSize:(CGFloat)dotSize;
- (void)setBarCount:(NSInteger)barCount;
- (void)setBarWidth:(CGFloat)barWidth;
- (void)setBarHeight:(CGFloat)barHeight;
- (void)setRingWidth:(CGFloat)ringWidth;
- (void)setPulseScale:(CGFloat)pulseScale;

// Event callbacks
- (void)setOnStart:(nullable RCTBubblingEventBlock)onStart;
- (void)setOnStop:(nullable RCTBubblingEventBlock)onStop;
- (void)setOnComplete:(nullable RCTBubblingEventBlock)onComplete;
- (void)setOnProgressChange:(nullable RCTBubblingEventBlock)onProgressChange;

// Spinner control methods
- (void)startAnimating;
- (void)stopAnimating;
- (void)show;
- (void)hide;
- (void)fadeIn;
- (void)fadeOut;
- (void)setProgressValue:(CGFloat)progress animated:(BOOL)animated;

// Spinner state information
- (BOOL)isAnimating;
- (BOOL)isVisible;
- (CGFloat)getCurrentProgress;
- (NSString *)getSpinnerType;
- (NSDictionary *)getSpinnerInfo;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END