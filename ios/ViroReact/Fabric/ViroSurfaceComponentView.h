//
//  ViroSurfaceComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>
#import <React/RCTComponent.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroSurfaceComponentView : RCTViewComponentView

// Surface properties
@property (nonatomic, assign) CGFloat width;
@property (nonatomic, assign) CGFloat height;
@property (nonatomic, assign) NSInteger widthSegmentCount;
@property (nonatomic, assign) NSInteger heightSegmentCount;
@property (nonatomic, strong) NSArray<NSNumber *> *uvCoordinates;

// Surface detection properties
@property (nonatomic, assign) BOOL arPlaneDetection;
@property (nonatomic, strong) NSString *planeTypes;
@property (nonatomic, assign) CGFloat minPlaneSize;
@property (nonatomic, assign) CGFloat maxPlaneSize;
@property (nonatomic, assign) BOOL ignoreEventHandling;

// Visual properties
@property (nonatomic, assign) BOOL doubleSided;
@property (nonatomic, strong) NSArray<NSString *> *materials;
@property (nonatomic, strong) NSArray<NSNumber *> *materialWrapMode;

// Event callbacks
@property (nonatomic, copy) RCTBubblingEventBlock onPlaneDetected;
@property (nonatomic, copy) RCTBubblingEventBlock onPlaneUpdated;
@property (nonatomic, copy) RCTBubblingEventBlock onPlaneRemoved;
@property (nonatomic, copy) RCTBubblingEventBlock onClick;
@property (nonatomic, copy) RCTBubblingEventBlock onHover;
@property (nonatomic, copy) RCTBubblingEventBlock onDrag;

// Geometry access
- (void)updateGeometry;
- (void)generateUVCoordinates;
- (void)enablePlaneDetection;
- (void)disablePlaneDetection;

@end

NS_ASSUME_NONNULL_END