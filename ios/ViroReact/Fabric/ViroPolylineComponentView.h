//
//  ViroPolylineComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>
#import <React/RCTComponent.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroPolylineComponentView : RCTViewComponentView

// Polyline properties
@property (nonatomic, strong) NSArray<NSArray<NSNumber *> *> *points;
@property (nonatomic, assign) CGFloat thickness;
@property (nonatomic, strong) NSArray<NSNumber *> *colors;
@property (nonatomic, assign) BOOL closed;

// Line style properties
@property (nonatomic, strong) NSString *lineType;
@property (nonatomic, assign) CGFloat dashLength;
@property (nonatomic, assign) CGFloat gapLength;
@property (nonatomic, strong) NSString *capType;
@property (nonatomic, strong) NSString *joinType;

// Segment properties
@property (nonatomic, assign) NSInteger segments;
@property (nonatomic, assign) BOOL smooth;
@property (nonatomic, assign) CGFloat smoothness;

// Materials
@property (nonatomic, strong) NSArray<NSString *> *materials;

// Event callbacks
@property (nonatomic, copy) RCTBubblingEventBlock onClick;
@property (nonatomic, copy) RCTBubblingEventBlock onHover;
@property (nonatomic, copy) RCTBubblingEventBlock onDrag;

// Geometry access
- (void)updateGeometry;
- (void)generateLineGeometry;
- (void)applyLineStyle;

@end

NS_ASSUME_NONNULL_END