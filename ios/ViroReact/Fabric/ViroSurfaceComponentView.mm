//
//  ViroSurfaceComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroSurfaceComponentView.h"
#import <React/RCTAssert.h>
#import <React/RCTUtils.h>
#import <React/RCTLog.h>
#import <SceneKit/SceneKit.h>
#import <ARKit/ARKit.h>

@interface ViroSurfaceComponentView () <ARSessionDelegate>

// SceneKit components
@property (nonatomic, strong) SCNNode *surfaceNode;
@property (nonatomic, strong) SCNGeometry *surfaceGeometry;
@property (nonatomic, strong) NSMutableArray<SCNMaterial *> *surfaceMaterials;

// AR components
@property (nonatomic, strong) ARSession *arSession;
@property (nonatomic, strong) ARWorldTrackingConfiguration *arConfiguration;
@property (nonatomic, strong) NSMutableSet<ARAnchor *> *detectedPlanes;

// Geometry data
@property (nonatomic, strong) NSMutableArray<NSValue *> *vertices;
@property (nonatomic, strong) NSMutableArray<NSNumber *> *indices;
@property (nonatomic, strong) NSMutableArray<NSValue *> *normals;
@property (nonatomic, strong) NSMutableArray<NSValue *> *uvs;

// Internal state
@property (nonatomic, assign) BOOL geometryDirty;
@property (nonatomic, assign) BOOL planeDetectionEnabled;

@end

@implementation ViroSurfaceComponentView

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        [self commonInit];
    }
    return self;
}

- (void)commonInit
{
    // Initialize default values
    _width = 1.0f;
    _height = 1.0f;
    _widthSegmentCount = 1;
    _heightSegmentCount = 1;
    _uvCoordinates = @[];
    _arPlaneDetection = NO;
    _planeTypes = @"horizontal";
    _minPlaneSize = 0.1f;
    _maxPlaneSize = 100.0f;
    _ignoreEventHandling = NO;
    _doubleSided = NO;
    _materials = @[];
    _materialWrapMode = @[];
    
    // Initialize collections
    _vertices = [NSMutableArray array];
    _indices = [NSMutableArray array];
    _normals = [NSMutableArray array];
    _uvs = [NSMutableArray array];
    _surfaceMaterials = [NSMutableArray array];
    _detectedPlanes = [NSMutableSet set];
    
    // Initialize state
    _geometryDirty = YES;
    _planeDetectionEnabled = NO;
    
    // Setup SceneKit components
    [self setupSceneKitComponents];
}

- (void)setupSceneKitComponents
{
    _surfaceNode = [SCNNode node];
    _surfaceNode.name = @"ViroSurface";
    
    // Create initial geometry
    [self updateGeometry];
}

#pragma mark - Property Setters

- (void)setWidth:(CGFloat)width
{
    _width = width;
    _geometryDirty = YES;
    [self updateGeometry];
}

- (void)setHeight:(CGFloat)height
{
    _height = height;
    _geometryDirty = YES;
    [self updateGeometry];
}

- (void)setWidthSegmentCount:(NSInteger)widthSegmentCount
{
    _widthSegmentCount = widthSegmentCount;
    _geometryDirty = YES;
    [self updateGeometry];
}

- (void)setHeightSegmentCount:(NSInteger)heightSegmentCount
{
    _heightSegmentCount = heightSegmentCount;
    _geometryDirty = YES;
    [self updateGeometry];
}

- (void)setUvCoordinates:(NSArray<NSNumber *> *)uvCoordinates
{
    _uvCoordinates = uvCoordinates;
    _geometryDirty = YES;
    [self updateGeometry];
}

- (void)setArPlaneDetection:(BOOL)arPlaneDetection
{
    _arPlaneDetection = arPlaneDetection;
    
    if (arPlaneDetection) {
        [self enablePlaneDetection];
    } else {
        [self disablePlaneDetection];
    }
}

- (void)setPlaneTypes:(NSString *)planeTypes
{
    _planeTypes = planeTypes;
    if (_planeDetectionEnabled) {
        [self updatePlaneDetectionConfiguration];
    }
}

- (void)setMinPlaneSize:(CGFloat)minPlaneSize
{
    _minPlaneSize = minPlaneSize;
}

- (void)setMaxPlaneSize:(CGFloat)maxPlaneSize
{
    _maxPlaneSize = maxPlaneSize;
}

- (void)setDoubleSided:(BOOL)doubleSided
{
    _doubleSided = doubleSided;
    [self updateMaterials];
}

- (void)setMaterials:(NSArray<NSString *> *)materials
{
    _materials = materials;
    [self updateMaterials];
}

- (void)setMaterialWrapMode:(NSArray<NSNumber *> *)materialWrapMode
{
    _materialWrapMode = materialWrapMode;
    [self updateMaterials];
}

#pragma mark - Geometry Generation

- (void)updateGeometry
{
    if (!_geometryDirty) return;
    
    [self generateVertices];
    [self generateIndices];
    [self generateNormals];
    [self generateUVCoordinates];
    [self createSceneKitGeometry];
    [self updateMaterials];
    
    _geometryDirty = NO;
}

- (void)generateVertices
{
    [_vertices removeAllObjects];
    
    // Generate vertices for a subdivided plane
    for (NSInteger j = 0; j <= _heightSegmentCount; j++) {
        for (NSInteger i = 0; i <= _widthSegmentCount; i++) {
            float x = ((float)i / (float)_widthSegmentCount - 0.5f) * _width;
            float y = 0.0f;
            float z = ((float)j / (float)_heightSegmentCount - 0.5f) * _height;
            
            SCNVector3 vertex = SCNVector3Make(x, y, z);
            [_vertices addObject:[NSValue valueWithSCNVector3:vertex]];
        }
    }
}

- (void)generateIndices
{
    [_indices removeAllObjects];
    
    // Generate triangle indices for the subdivided plane
    for (NSInteger j = 0; j < _heightSegmentCount; j++) {
        for (NSInteger i = 0; i < _widthSegmentCount; i++) {
            NSInteger a = j * (_widthSegmentCount + 1) + i;
            NSInteger b = j * (_widthSegmentCount + 1) + i + 1;
            NSInteger c = (j + 1) * (_widthSegmentCount + 1) + i + 1;
            NSInteger d = (j + 1) * (_widthSegmentCount + 1) + i;
            
            // Triangle 1
            [_indices addObject:@(a)];
            [_indices addObject:@(b)];
            [_indices addObject:@(c)];
            
            // Triangle 2
            [_indices addObject:@(a)];
            [_indices addObject:@(c)];
            [_indices addObject:@(d)];
        }
    }
}

- (void)generateNormals
{
    [_normals removeAllObjects];
    
    // Generate normals (all pointing up for a horizontal plane)
    SCNVector3 normal = SCNVector3Make(0, 1, 0);
    for (NSInteger i = 0; i < _vertices.count; i++) {
        [_normals addObject:[NSValue valueWithSCNVector3:normal]];
    }
}

- (void)generateUVCoordinates
{
    [_uvs removeAllObjects];
    
    if (_uvCoordinates.count > 0) {
        // Use provided UV coordinates
        for (NSInteger i = 0; i < _uvCoordinates.count; i += 2) {
            if (i + 1 < _uvCoordinates.count) {
                float u = [_uvCoordinates[i] floatValue];
                float v = [_uvCoordinates[i + 1] floatValue];
                [_uvs addObject:[NSValue valueWithCGPoint:CGPointMake(u, v)]];
            }
        }
    } else {
        // Generate UV coordinates based on vertex positions
        for (NSInteger j = 0; j <= _heightSegmentCount; j++) {
            for (NSInteger i = 0; i <= _widthSegmentCount; i++) {
                float u = (float)i / (float)_widthSegmentCount;
                float v = (float)j / (float)_heightSegmentCount;
                [_uvs addObject:[NSValue valueWithCGPoint:CGPointMake(u, v)]];
            }
        }
    }
}

- (void)createSceneKitGeometry
{
    if (_vertices.count == 0 || _indices.count == 0) {
        return;
    }
    
    // Create SCNGeometry
    SCNGeometrySource *vertexSource = [SCNGeometrySource geometrySourceWithVertices:(SCNVector3 *)[_vertices.firstObject pointerValue] count:_vertices.count];
    SCNGeometrySource *normalSource = [SCNGeometrySource geometrySourceWithNormals:(SCNVector3 *)[_normals.firstObject pointerValue] count:_normals.count];
    SCNGeometrySource *uvSource = [SCNGeometrySource geometrySourceWithTextureCoordinates:(CGPoint *)[_uvs.firstObject pointerValue] count:_uvs.count];
    
    // Create triangle data
    NSData *indexData = [NSData dataWithBytes:_indices.firstObject length:_indices.count * sizeof(uint32_t)];
    SCNGeometryElement *element = [SCNGeometryElement geometryElementWithData:indexData
                                                                primitiveType:SCNGeometryPrimitiveTypeTriangles
                                                               primitiveCount:_indices.count / 3
                                                                bytesPerIndex:sizeof(uint32_t)];
    
    _surfaceGeometry = [SCNGeometry geometryWithSources:@[vertexSource, normalSource, uvSource] elements:@[element]];
    
    // Assign geometry to node
    _surfaceNode.geometry = _surfaceGeometry;
}

- (void)updateMaterials
{
    [_surfaceMaterials removeAllObjects];
    
    if (_materials.count == 0) {
        // Create default material
        SCNMaterial *material = [SCNMaterial material];
        material.diffuse.contents = [UIColor colorWithRed:0.8f green:0.8f blue:0.8f alpha:1.0f];
        material.lightingModelName = SCNLightingModelLambert;
        material.doubleSided = _doubleSided;
        [_surfaceMaterials addObject:material];
    } else {
        // Create materials from material names
        for (NSString *materialName in _materials) {
            SCNMaterial *material = [SCNMaterial material];
            material.name = materialName;
            material.lightingModelName = SCNLightingModelLambert;
            material.doubleSided = _doubleSided;
            [_surfaceMaterials addObject:material];
        }
    }
    
    // Apply material wrap modes
    if (_materialWrapMode.count > 0) {
        for (NSInteger i = 0; i < _surfaceMaterials.count && i < _materialWrapMode.count; i++) {
            SCNMaterial *material = _surfaceMaterials[i];
            NSInteger wrapMode = [_materialWrapMode[i] integerValue];
            
            switch (wrapMode) {
                case 0: // Clamp
                    material.diffuse.wrapS = SCNWrapModeClamp;
                    material.diffuse.wrapT = SCNWrapModeClamp;
                    break;
                case 1: // Repeat
                    material.diffuse.wrapS = SCNWrapModeRepeat;
                    material.diffuse.wrapT = SCNWrapModeRepeat;
                    break;
                case 2: // Mirror
                    material.diffuse.wrapS = SCNWrapModeMirror;
                    material.diffuse.wrapT = SCNWrapModeMirror;
                    break;
            }
        }
    }
    
    _surfaceGeometry.materials = _surfaceMaterials;
}

#pragma mark - AR Plane Detection

- (void)enablePlaneDetection
{
    if (_planeDetectionEnabled) return;
    
    _planeDetectionEnabled = YES;
    
    // Initialize AR session
    _arSession = [[ARSession alloc] init];
    _arSession.delegate = self;
    
    // Configure AR session
    [self updatePlaneDetectionConfiguration];
    
    // Start AR session
    [_arSession runWithConfiguration:_arConfiguration];
}

- (void)disablePlaneDetection
{
    if (!_planeDetectionEnabled) return;
    
    _planeDetectionEnabled = NO;
    
    // Stop AR session
    [_arSession pause];
    _arSession.delegate = nil;
    _arSession = nil;
    _arConfiguration = nil;
    
    // Clear detected planes
    [_detectedPlanes removeAllObjects];
}

- (void)updatePlaneDetectionConfiguration
{
    if (!_planeDetectionEnabled) return;
    
    _arConfiguration = [[ARWorldTrackingConfiguration alloc] init];
    
    // Configure plane detection based on planeTypes
    if ([_planeTypes containsString:@"horizontal"]) {
        _arConfiguration.planeDetection |= ARPlaneDetectionHorizontal;
    }
    if ([_planeTypes containsString:@"vertical"]) {
        _arConfiguration.planeDetection |= ARPlaneDetectionVertical;
    }
    
    // Update session configuration if running
    if (_arSession && _arSession.currentFrame) {
        [_arSession runWithConfiguration:_arConfiguration];
    }
}

#pragma mark - ARSessionDelegate

- (void)session:(ARSession *)session didAddAnchors:(NSArray<ARAnchor *> *)anchors
{
    for (ARAnchor *anchor in anchors) {
        if ([anchor isKindOfClass:[ARPlaneAnchor class]]) {
            ARPlaneAnchor *planeAnchor = (ARPlaneAnchor *)anchor;
            
            // Check plane size constraints
            float planeWidth = planeAnchor.planeExtent.x;
            float planeHeight = planeAnchor.planeExtent.z;
            float planeSize = MAX(planeWidth, planeHeight);
            
            if (planeSize >= _minPlaneSize && planeSize <= _maxPlaneSize) {
                [_detectedPlanes addObject:anchor];
                
                if (_onPlaneDetected) {
                    _onPlaneDetected(@{
                        @"planeId": anchor.identifier.UUIDString,
                        @"type": [self planeTypeFromAnchor:planeAnchor],
                        @"center": @[@(planeAnchor.center.x), @(planeAnchor.center.y), @(planeAnchor.center.z)],
                        @"extent": @[@(planeAnchor.planeExtent.x), @(planeAnchor.planeExtent.z)],
                        @"transform": [self transformArrayFromMatrix:planeAnchor.transform]
                    });
                }
            }
        }
    }
}

- (void)session:(ARSession *)session didUpdateAnchors:(NSArray<ARAnchor *> *)anchors
{
    for (ARAnchor *anchor in anchors) {
        if ([anchor isKindOfClass:[ARPlaneAnchor class]] && [_detectedPlanes containsObject:anchor]) {
            ARPlaneAnchor *planeAnchor = (ARPlaneAnchor *)anchor;
            
            if (_onPlaneUpdated) {
                _onPlaneUpdated(@{
                    @"planeId": anchor.identifier.UUIDString,
                    @"type": [self planeTypeFromAnchor:planeAnchor],
                    @"center": @[@(planeAnchor.center.x), @(planeAnchor.center.y), @(planeAnchor.center.z)],
                    @"extent": @[@(planeAnchor.planeExtent.x), @(planeAnchor.planeExtent.z)],
                    @"transform": [self transformArrayFromMatrix:planeAnchor.transform]
                });
            }
        }
    }
}

- (void)session:(ARSession *)session didRemoveAnchors:(NSArray<ARAnchor *> *)anchors
{
    for (ARAnchor *anchor in anchors) {
        if ([anchor isKindOfClass:[ARPlaneAnchor class]] && [_detectedPlanes containsObject:anchor]) {
            [_detectedPlanes removeObject:anchor];
            
            if (_onPlaneRemoved) {
                _onPlaneRemoved(@{
                    @"planeId": anchor.identifier.UUIDString
                });
            }
        }
    }
}

#pragma mark - Helper Methods

- (NSString *)planeTypeFromAnchor:(ARPlaneAnchor *)anchor
{
    if (@available(iOS 11.3, *)) {
        switch (anchor.alignment) {
            case ARPlaneAnchorAlignmentHorizontal:
                return @"horizontal";
            case ARPlaneAnchorAlignmentVertical:
                return @"vertical";
            default:
                return @"unknown";
        }
    } else {
        return @"horizontal";
    }
}

- (NSArray<NSNumber *> *)transformArrayFromMatrix:(simd_float4x4)matrix
{
    return @[
        @(matrix.columns[0].x), @(matrix.columns[0].y), @(matrix.columns[0].z), @(matrix.columns[0].w),
        @(matrix.columns[1].x), @(matrix.columns[1].y), @(matrix.columns[1].z), @(matrix.columns[1].w),
        @(matrix.columns[2].x), @(matrix.columns[2].y), @(matrix.columns[2].z), @(matrix.columns[2].w),
        @(matrix.columns[3].x), @(matrix.columns[3].y), @(matrix.columns[3].z), @(matrix.columns[3].w)
    ];
}

#pragma mark - Touch Handling

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event
{
    [super touchesBegan:touches withEvent:event];
    
    if (_onClick) {
        UITouch *touch = touches.anyObject;
        CGPoint location = [touch locationInView:self];
        
        _onClick(@{
            @"position": @[@(location.x), @(location.y)],
            @"source": @"touch"
        });
    }
}

#pragma mark - Public Methods

- (SCNNode *)getSurfaceNode
{
    return _surfaceNode;
}

- (SCNGeometry *)getSurfaceGeometry
{
    return _surfaceGeometry;
}

- (NSArray<SCNMaterial *> *)getSurfaceMaterials
{
    return _surfaceMaterials;
}

- (NSSet<ARAnchor *> *)getDetectedPlanes
{
    return _detectedPlanes;
}

- (void)forceUpdate
{
    _geometryDirty = YES;
    [self updateGeometry];
}

- (void)dealloc
{
    [self disablePlaneDetection];
}

@end