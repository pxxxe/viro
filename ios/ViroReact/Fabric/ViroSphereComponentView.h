//
//  ViroSphereComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroSphereComponentView : RCTViewComponentView

// Sphere geometry properties
- (void)setRadius:(CGFloat)radius;
- (void)setWidthSegmentCount:(NSInteger)widthSegmentCount;
- (void)setHeightSegmentCount:(NSInteger)heightSegmentCount;
- (void)setPhiStart:(CGFloat)phiStart;
- (void)setPhiLength:(CGFloat)phiLength;
- (void)setThetaStart:(CGFloat)thetaStart;
- (void)setThetaLength:(CGFloat)thetaLength;

// Material properties
- (void)setMaterials:(nullable NSArray<NSString *> *)materials;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END