//
//  ViroFlexViewComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroFlexViewComponentView : RCTViewComponentView

// FlexView layout properties
- (void)setWidth:(CGFloat)width;
- (void)setHeight:(CGFloat)height;
- (void)setFlexDirection:(nullable NSString *)flexDirection;
- (void)setJustifyContent:(nullable NSString *)justifyContent;
- (void)setAlignItems:(nullable NSString *)alignItems;
- (void)setAlignContent:(nullable NSString *)alignContent;
- (void)setFlexWrap:(nullable NSString *)flexWrap;

// Individual flex item properties
- (void)setFlex:(CGFloat)flex;
- (void)setFlexGrow:(CGFloat)flexGrow;
- (void)setFlexShrink:(CGFloat)flexShrink;
- (void)setFlexBasis:(CGFloat)flexBasis;
- (void)setAlignSelf:(nullable NSString *)alignSelf;

// Margin and padding
- (void)setMargin:(nullable NSArray<NSNumber *> *)margin;
- (void)setPadding:(nullable NSArray<NSNumber *> *)padding;

// Material properties
- (void)setMaterials:(nullable NSArray<NSString *> *)materials;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END