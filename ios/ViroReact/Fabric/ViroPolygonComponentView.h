//
//  ViroPolygonComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>
#import <React/RCTComponent.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroPolygonComponentView : RCTViewComponentView

// Polygon properties
@property (nonatomic, strong) NSArray<NSArray<NSNumber *> *> *vertices;
@property (nonatomic, strong) NSArray<NSArray<NSNumber *> *> *holes;
@property (nonatomic, strong) NSArray<NSNumber *> *uvCoordinates;
@property (nonatomic, strong) NSArray<NSNumber *> *normals;
@property (nonatomic, strong) NSArray<NSNumber *> *colors;
@property (nonatomic, assign) CGFloat thickness;
@property (nonatomic, assign) BOOL facesOutward;

// Tessellation properties
@property (nonatomic, assign) NSInteger tessellationFactor;
@property (nonatomic, strong) NSString *tessellationMode;

// Materials
@property (nonatomic, strong) NSArray<NSString *> *materials;

// Event callbacks
@property (nonatomic, copy) RCTBubblingEventBlock onClick;
@property (nonatomic, copy) RCTBubblingEventBlock onHover;
@property (nonatomic, copy) RCTBubblingEventBlock onDrag;

// Geometry access
- (void)updateGeometry;
- (void)calculateNormals;
- (void)generateUVCoordinates;

@end

NS_ASSUME_NONNULL_END