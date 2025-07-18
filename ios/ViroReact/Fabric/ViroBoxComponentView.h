//
//  ViroBoxComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroBoxComponentView : RCTViewComponentView

// Box geometry properties
- (void)setWidth:(CGFloat)width;
- (void)setHeight:(CGFloat)height;
- (void)setLength:(CGFloat)length;

// Material properties
- (void)setMaterials:(nullable NSArray<NSString *> *)materials;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END