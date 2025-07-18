//
//  ViroQuadComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroQuadComponentView : RCTViewComponentView

// Quad geometry properties
- (void)setWidth:(CGFloat)width;
- (void)setHeight:(CGFloat)height;
- (void)setWidthSegmentCount:(NSInteger)widthSegmentCount;
- (void)setHeightSegmentCount:(NSInteger)heightSegmentCount;

// UV mapping
- (void)setUvCoordinates:(nullable NSArray<NSArray<NSNumber *> *> *)uvCoordinates;

// Material properties
- (void)setMaterials:(nullable NSArray<NSString *> *)materials;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END