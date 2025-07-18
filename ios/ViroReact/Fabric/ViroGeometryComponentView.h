//
//  ViroGeometryComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroGeometryComponentView : RCTViewComponentView

// Geometry definition
- (void)setVertices:(nullable NSArray<NSArray<NSNumber *> *> *)vertices;
- (void)setNormals:(nullable NSArray<NSArray<NSNumber *> *> *)normals;
- (void)setTexcoords:(nullable NSArray<NSArray<NSNumber *> *> *)texcoords;
- (void)setTriangleIndices:(nullable NSArray<NSArray<NSNumber *> *> *)triangleIndices;

// Geometry properties
- (void)setMaterials:(nullable NSArray<NSString *> *)materials;
- (void)setMorphTargets:(nullable NSArray<NSDictionary *> *)morphTargets;

// Geometry configuration
- (void)setGeometryType:(nullable NSString *)geometryType;
- (void)setPrimitiveType:(nullable NSString *)primitiveType;
- (void)setVertexFormat:(nullable NSString *)vertexFormat;

// Subdivision properties
- (void)setSubdivisionLevel:(NSInteger)subdivisionLevel;
- (void)setSubdivisionType:(nullable NSString *)subdivisionType;

// Lighting properties
- (void)setLightReceivingBitMask:(NSInteger)lightReceivingBitMask;
- (void)setShadowCastingBitMask:(NSInteger)shadowCastingBitMask;

// Rendering properties
- (void)setCullMode:(nullable NSString *)cullMode;
- (void)setBlendMode:(nullable NSString *)blendMode;
- (void)setTransparencyMode:(nullable NSString *)transparencyMode;

// Bounding box
- (void)setBoundingBoxMin:(nullable NSArray<NSNumber *> *)boundingBoxMin;
- (void)setBoundingBoxMax:(nullable NSArray<NSNumber *> *)boundingBoxMax;

// Geometry generation
- (void)setSphereRadius:(CGFloat)sphereRadius;
- (void)setSphereSegments:(NSInteger)sphereSegments;
- (void)setBoxWidth:(CGFloat)boxWidth;
- (void)setBoxHeight:(CGFloat)boxHeight;
- (void)setBoxLength:(CGFloat)boxLength;
- (void)setCylinderRadius:(CGFloat)cylinderRadius;
- (void)setCylinderHeight:(CGFloat)cylinderHeight;
- (void)setCylinderSegments:(NSInteger)cylinderSegments;

// Parametric surface
- (void)setParametricSurface:(nullable NSDictionary *)parametricSurface;
- (void)setUSubdivisions:(NSInteger)uSubdivisions;
- (void)setVSubdivisions:(NSInteger)vSubdivisions;

// Event callbacks
- (void)setOnGeometryError:(nullable RCTBubblingEventBlock)onGeometryError;
- (void)setOnGeometryReady:(nullable RCTBubblingEventBlock)onGeometryReady;

// Geometry control methods
- (void)updateGeometry;
- (void)generateGeometry;
- (void)optimizeGeometry;
- (void)computeNormals;
- (void)computeTangents;

// Geometry information
- (NSInteger)getVertexCount;
- (NSInteger)getTriangleCount;
- (NSArray<NSNumber *> *)getBoundingBox;
- (CGFloat)getSurfaceArea;
- (CGFloat)getVolume;

// Inherited from ViroNode:
// - Transform properties (position, scale, rotation, etc.)
// - Visibility properties (visible, opacity, renderingOrder)
// - Physics properties (physicsBody)
// - Event handlers (onClick, onHover, etc.)

@end

NS_ASSUME_NONNULL_END