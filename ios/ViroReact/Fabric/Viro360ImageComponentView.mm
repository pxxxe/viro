//
//  Viro360ImageComponentView.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "Viro360ImageComponentView.h"
#import <React/RCTAssert.h>
#import <React/RCTUtils.h>
#import <React/RCTLog.h>
#import <SceneKit/SceneKit.h>
#import <QuartzCore/QuartzCore.h>

@interface Viro360ImageComponentView ()

// SceneKit components
@property (nonatomic, strong) SCNNode *imageNode;
@property (nonatomic, strong) SCNSphere *sphereGeometry;
@property (nonatomic, strong) SCNMaterial *sphereMaterial;
@property (nonatomic, strong) SCNBox *cubeGeometry;
@property (nonatomic, strong) NSArray<SCNMaterial *> *cubeMaterials;

// Image properties
@property (nonatomic, strong) NSDictionary *source;
@property (nonatomic, strong) NSString *uri;
@property (nonatomic, strong) NSString *local;
@property (nonatomic, strong) NSString *resource;
@property (nonatomic, strong) NSString *format;
@property (nonatomic, strong) NSString *imageType;
@property (nonatomic, strong) NSString *projection;
@property (nonatomic, strong) NSString *mapping;

// Stereoscopic properties
@property (nonatomic, strong) NSString *stereoscopicMode;
@property (nonatomic, strong) NSString *eyeType;
@property (nonatomic, assign) CGFloat interpupillaryDistance;

// Cube map properties
@property (nonatomic, strong) NSDictionary *cubeFaces;
@property (nonatomic, strong) NSString *facePositiveX;
@property (nonatomic, strong) NSString *faceNegativeX;
@property (nonatomic, strong) NSString *facePositiveY;
@property (nonatomic, strong) NSString *faceNegativeY;
@property (nonatomic, strong) NSString *facePositiveZ;
@property (nonatomic, strong) NSString *faceNegativeZ;

// Rotation and orientation
@property (nonatomic, strong) NSArray<NSNumber *> *rotation;
@property (nonatomic, assign) CGFloat rotationX;
@property (nonatomic, assign) CGFloat rotationY;
@property (nonatomic, assign) CGFloat rotationZ;
@property (nonatomic, strong) NSArray<NSNumber *> *orientation;

// Display properties
@property (nonatomic, assign) CGFloat radius;
@property (nonatomic, assign) NSInteger segmentWidth;
@property (nonatomic, assign) NSInteger segmentHeight;
@property (nonatomic, assign) NSInteger renderingOrder;
@property (nonatomic, assign) BOOL invertNormals;

// Texture properties
@property (nonatomic, strong) NSString *textureMinification;
@property (nonatomic, strong) NSString *textureMagnification;
@property (nonatomic, strong) NSString *textureWrapS;
@property (nonatomic, strong) NSString *textureWrapT;
@property (nonatomic, assign) BOOL mipmap;
@property (nonatomic, assign) CGFloat anisotropy;

// Quality and performance
@property (nonatomic, strong) NSString *quality;
@property (nonatomic, strong) NSString *resolution;
@property (nonatomic, strong) NSString *compression;
@property (nonatomic, assign) NSInteger maxTextureSize;
@property (nonatomic, assign) BOOL streamingEnabled;

// Loading and caching
@property (nonatomic, assign) BOOL preload;
@property (nonatomic, assign) BOOL cacheEnabled;
@property (nonatomic, assign) NSInteger cacheSize;
@property (nonatomic, assign) CGFloat loadingTimeout;
@property (nonatomic, assign) NSInteger retryCount;

// Color and effects
@property (nonatomic, strong) NSArray<NSNumber *> *tintColor;
@property (nonatomic, assign) CGFloat brightness;
@property (nonatomic, assign) CGFloat contrast;
@property (nonatomic, assign) CGFloat saturation;
@property (nonatomic, assign) CGFloat gamma;
@property (nonatomic, assign) CGFloat exposure;

// HDR properties
@property (nonatomic, assign) BOOL hdrEnabled;
@property (nonatomic, strong) NSString *toneMapping;
@property (nonatomic, assign) CGFloat toneMappingExposure;
@property (nonatomic, assign) CGFloat toneMappingWhitePoint;

// Animation properties
@property (nonatomic, assign) BOOL autoRotate;
@property (nonatomic, assign) CGFloat autoRotateSpeed;
@property (nonatomic, strong) NSArray<NSNumber *> *autoRotateAxis;
@property (nonatomic, assign) CGFloat animationDuration;
@property (nonatomic, strong) NSString *animationEasing;

// Event callbacks
@property (nonatomic, copy) RCTBubblingEventBlock onLoad;
@property (nonatomic, copy) RCTBubblingEventBlock onError;
@property (nonatomic, copy) RCTBubblingEventBlock onProgress;
@property (nonatomic, copy) RCTBubblingEventBlock onLoadStart;
@property (nonatomic, copy) RCTBubblingEventBlock onLoadEnd;
@property (nonatomic, copy) RCTBubblingEventBlock onRotationChange;

// Internal state
@property (nonatomic, assign) BOOL isLoaded;
@property (nonatomic, assign) BOOL isLoading;
@property (nonatomic, assign) BOOL hasError;
@property (nonatomic, assign) NSInteger textureWidth;
@property (nonatomic, assign) NSInteger textureHeight;
@property (nonatomic, assign) CGFloat loadProgress;
@property (nonatomic, strong) NSTimer *autoRotateTimer;
@property (nonatomic, strong) NSURLSessionDataTask *loadingTask;

@end

@implementation Viro360ImageComponentView

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
    _radius = 1000.0f;
    _segmentWidth = 64;
    _segmentHeight = 32;
    _renderingOrder = 0;
    _invertNormals = YES;
    _mipmap = YES;
    _anisotropy = 1.0f;
    _maxTextureSize = 2048;
    _streamingEnabled = NO;
    _preload = NO;
    _cacheEnabled = YES;
    _cacheSize = 100;
    _loadingTimeout = 30.0f;
    _retryCount = 3;
    _brightness = 1.0f;
    _contrast = 1.0f;
    _saturation = 1.0f;
    _gamma = 1.0f;
    _exposure = 0.0f;
    _hdrEnabled = NO;
    _toneMappingExposure = 1.0f;
    _toneMappingWhitePoint = 1.0f;
    _autoRotate = NO;
    _autoRotateSpeed = 1.0f;
    _animationDuration = 0.3f;
    _interpupillaryDistance = 0.064f;
    
    // Initialize SceneKit components
    [self setupSceneKitComponents];
}

- (void)setupSceneKitComponents
{
    // Create sphere geometry for equirectangular projection
    _sphereGeometry = [SCNSphere sphereWithRadius:_radius];
    _sphereGeometry.segmentCount = _segmentWidth;
    _sphereGeometry.geodesic = NO;
    
    // Create cube geometry for cube map projection
    _cubeGeometry = [SCNBox boxWithWidth:_radius * 2 
                                  height:_radius * 2 
                                  length:_radius * 2 
                          chamferRadius:0];
    
    // Create material
    _sphereMaterial = [SCNMaterial material];
    _sphereMaterial.cullMode = _invertNormals ? SCNCullModeFront : SCNCullModeBack;
    _sphereMaterial.doubleSided = NO;
    _sphereMaterial.lightingModelName = SCNLightingModelConstant;
    
    // Create image node
    _imageNode = [SCNNode node];
    _imageNode.geometry = _sphereGeometry;
    _imageNode.geometry.firstMaterial = _sphereMaterial;
    _imageNode.renderingOrder = _renderingOrder;
    
    // Apply default rotation (invert sphere for inside viewing)
    if (_invertNormals) {
        _imageNode.scale = SCNVector3Make(-1, 1, 1);
    }
}

#pragma mark - Property Setters

- (void)setSource:(NSDictionary *)source
{
    _source = source;
    [self loadImage];
}

- (void)setUri:(NSString *)uri
{
    _uri = uri;
    [self loadImage];
}

- (void)setLocal:(NSString *)local
{
    _local = local;
    [self loadImage];
}

- (void)setResource:(NSString *)resource
{
    _resource = resource;
    [self loadImage];
}

- (void)setFormat:(NSString *)format
{
    _format = format;
    [self updateImageFormat];
}

- (void)setImageType:(NSString *)imageType
{
    _imageType = imageType;
    [self updateImageFormat];
}

- (void)setProjection:(NSString *)projection
{
    _projection = projection;
    [self updateProjection];
}

- (void)setMapping:(NSString *)mapping
{
    _mapping = mapping;
    [self updateProjection];
}

- (void)setStereoscopicMode:(NSString *)stereoscopicMode
{
    _stereoscopicMode = stereoscopicMode;
    [self updateStereoscopicMode];
}

- (void)setEyeType:(NSString *)eyeType
{
    _eyeType = eyeType;
    [self updateStereoscopicMode];
}

- (void)setInterpupillaryDistance:(CGFloat)interpupillaryDistance
{
    _interpupillaryDistance = interpupillaryDistance;
    [self updateStereoscopicMode];
}

- (void)setCubeFaces:(NSDictionary *)cubeFaces
{
    _cubeFaces = cubeFaces;
    [self loadCubeMap];
}

- (void)setRotation:(NSArray<NSNumber *> *)rotation
{
    _rotation = rotation;
    [self updateRotation];
}

- (void)setRotationX:(CGFloat)rotationX
{
    _rotationX = rotationX;
    [self updateRotation];
}

- (void)setRotationY:(CGFloat)rotationY
{
    _rotationY = rotationY;
    [self updateRotation];
}

- (void)setRotationZ:(CGFloat)rotationZ
{
    _rotationZ = rotationZ;
    [self updateRotation];
}

- (void)setRadius:(CGFloat)radius
{
    _radius = radius;
    [self updateGeometry];
}

- (void)setSegmentWidth:(NSInteger)segmentWidth
{
    _segmentWidth = segmentWidth;
    [self updateGeometry];
}

- (void)setSegmentHeight:(NSInteger)segmentHeight
{
    _segmentHeight = segmentHeight;
    [self updateGeometry];
}

- (void)setRenderingOrder:(NSInteger)renderingOrder
{
    _renderingOrder = renderingOrder;
    _imageNode.renderingOrder = renderingOrder;
}

- (void)setInvertNormals:(BOOL)invertNormals
{
    _invertNormals = invertNormals;
    [self updateGeometry];
}

- (void)setAutoRotate:(BOOL)autoRotate
{
    _autoRotate = autoRotate;
    if (autoRotate) {
        [self startAutoRotation];
    } else {
        [self stopAutoRotation];
    }
}

- (void)setAutoRotateSpeed:(CGFloat)autoRotateSpeed
{
    _autoRotateSpeed = autoRotateSpeed;
    if (_autoRotate) {
        [self startAutoRotation];
    }
}

- (void)setTintColor:(NSArray<NSNumber *> *)tintColor
{
    _tintColor = tintColor;
    [self updateMaterialProperties];
}

- (void)setBrightness:(CGFloat)brightness
{
    _brightness = brightness;
    [self updateMaterialProperties];
}

- (void)setContrast:(CGFloat)contrast
{
    _contrast = contrast;
    [self updateMaterialProperties];
}

- (void)setSaturation:(CGFloat)saturation
{
    _saturation = saturation;
    [self updateMaterialProperties];
}

- (void)setGamma:(CGFloat)gamma
{
    _gamma = gamma;
    [self updateMaterialProperties];
}

- (void)setExposure:(CGFloat)exposure
{
    _exposure = exposure;
    [self updateMaterialProperties];
}

#pragma mark - Image Loading

- (void)loadImage
{
    if (_isLoading) {
        [_loadingTask cancel];
    }
    
    NSString *imageUrl = [self resolveImageUrl];
    if (!imageUrl) {
        return;
    }
    
    _isLoading = YES;
    _hasError = NO;
    _loadProgress = 0.0f;
    
    if (_onLoadStart) {
        _onLoadStart(@{@"url": imageUrl});
    }
    
    [self loadImageFromUrl:imageUrl];
}

- (NSString *)resolveImageUrl
{
    if (_uri) {
        return _uri;
    } else if (_local) {
        return [[NSBundle mainBundle] pathForResource:_local ofType:nil];
    } else if (_resource) {
        return [[NSBundle mainBundle] pathForResource:_resource ofType:nil];
    } else if (_source && _source[@"uri"]) {
        return _source[@"uri"];
    }
    return nil;
}

- (void)loadImageFromUrl:(NSString *)imageUrl
{
    NSURL *url = [NSURL URLWithString:imageUrl];
    if (!url) {
        url = [NSURL fileURLWithPath:imageUrl];
    }
    
    NSURLSessionConfiguration *config = [NSURLSessionConfiguration defaultSessionConfiguration];
    config.timeoutIntervalForRequest = _loadingTimeout;
    NSURLSession *session = [NSURLSession sessionWithConfiguration:config];
    
    __weak typeof(self) weakSelf = self;
    _loadingTask = [session dataTaskWithURL:url completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
        dispatch_async(dispatch_get_main_queue(), ^{
            __strong typeof(weakSelf) strongSelf = weakSelf;
            if (!strongSelf) return;
            
            strongSelf.isLoading = NO;
            strongSelf.loadingTask = nil;
            
            if (error) {
                [strongSelf handleLoadError:error];
                return;
            }
            
            UIImage *image = [UIImage imageWithData:data];
            if (!image) {
                [strongSelf handleLoadError:[NSError errorWithDomain:@"Viro360Image" 
                                                                code:1001 
                                                            userInfo:@{NSLocalizedDescriptionKey: @"Failed to decode image"}]];
                return;
            }
            
            [strongSelf handleImageLoaded:image];
        });
    }];
    
    [_loadingTask resume];
}

- (void)handleImageLoaded:(UIImage *)image
{
    _isLoaded = YES;
    _hasError = NO;
    _loadProgress = 1.0f;
    _textureWidth = (NSInteger)image.size.width;
    _textureHeight = (NSInteger)image.size.height;
    
    // Create texture from image
    _sphereMaterial.diffuse.contents = image;
    [self updateMaterialProperties];
    
    if (_onLoad) {
        _onLoad(@{
            @"width": @(_textureWidth),
            @"height": @(_textureHeight),
            @"format": _format ?: @"unknown",
            @"projection": _projection ?: @"equirectangular"
        });
    }
    
    if (_onLoadEnd) {
        _onLoadEnd(@{@"success": @YES});
    }
}

- (void)handleLoadError:(NSError *)error
{
    _isLoaded = NO;
    _hasError = YES;
    _loadProgress = 0.0f;
    
    if (_onError) {
        _onError(@{
            @"error": error.localizedDescription,
            @"code": @(error.code)
        });
    }
    
    if (_onLoadEnd) {
        _onLoadEnd(@{@"success": @NO});
    }
}

#pragma mark - Cube Map Loading

- (void)loadCubeMap
{
    if (!_cubeFaces || _cubeFaces.count < 6) {
        return;
    }
    
    // Create cube materials array
    NSMutableArray *materials = [NSMutableArray arrayWithCapacity:6];
    NSArray *faceKeys = @[@"px", @"nx", @"py", @"ny", @"pz", @"nz"];
    
    for (NSString *faceKey in faceKeys) {
        SCNMaterial *material = [SCNMaterial material];
        material.lightingModelName = SCNLightingModelConstant;
        material.cullMode = SCNCullModeBack;
        material.doubleSided = NO;
        
        NSString *faceImagePath = _cubeFaces[faceKey];
        if (faceImagePath) {
            UIImage *faceImage = [UIImage imageNamed:faceImagePath];
            if (faceImage) {
                material.diffuse.contents = faceImage;
            }
        }
        
        [materials addObject:material];
    }
    
    _cubeMaterials = materials;
    _cubeGeometry.materials = materials;
    _imageNode.geometry = _cubeGeometry;
}

#pragma mark - Geometry Updates

- (void)updateGeometry
{
    if ([_projection isEqualToString:@"cubemap"]) {
        _cubeGeometry.width = _radius * 2;
        _cubeGeometry.height = _radius * 2;
        _cubeGeometry.length = _radius * 2;
        _imageNode.geometry = _cubeGeometry;
    } else {
        _sphereGeometry.radius = _radius;
        _sphereGeometry.segmentCount = _segmentWidth;
        _imageNode.geometry = _sphereGeometry;
    }
    
    // Update material culling
    SCNMaterial *material = _imageNode.geometry.firstMaterial;
    material.cullMode = _invertNormals ? SCNCullModeFront : SCNCullModeBack;
    
    // Update node scale for inverted normals
    if (_invertNormals) {
        _imageNode.scale = SCNVector3Make(-1, 1, 1);
    } else {
        _imageNode.scale = SCNVector3Make(1, 1, 1);
    }
}

- (void)updateProjection
{
    if ([_projection isEqualToString:@"cubemap"]) {
        [self loadCubeMap];
    } else {
        _imageNode.geometry = _sphereGeometry;
        _imageNode.geometry.firstMaterial = _sphereMaterial;
    }
}

- (void)updateRotation
{
    SCNVector3 eulerAngles = SCNVector3Zero;
    
    if (_rotation && _rotation.count >= 3) {
        eulerAngles.x = [_rotation[0] floatValue];
        eulerAngles.y = [_rotation[1] floatValue];
        eulerAngles.z = [_rotation[2] floatValue];
    } else {
        eulerAngles.x = _rotationX;
        eulerAngles.y = _rotationY;
        eulerAngles.z = _rotationZ;
    }
    
    _imageNode.eulerAngles = eulerAngles;
    
    if (_onRotationChange) {
        _onRotationChange(@{
            @"rotation": @[@(eulerAngles.x), @(eulerAngles.y), @(eulerAngles.z)]
        });
    }
}

- (void)updateMaterialProperties
{
    if (!_sphereMaterial) return;
    
    // Apply color adjustments
    if (_tintColor && _tintColor.count >= 3) {
        CGFloat r = [_tintColor[0] floatValue];
        CGFloat g = [_tintColor[1] floatValue];
        CGFloat b = [_tintColor[2] floatValue];
        CGFloat a = _tintColor.count > 3 ? [_tintColor[3] floatValue] : 1.0f;
        _sphereMaterial.diffuse.intensity = 1.0f;
        _sphereMaterial.multiply.contents = [UIColor colorWithRed:r green:g blue:b alpha:a];
    }
    
    // Apply brightness, contrast, saturation adjustments
    // Note: These would typically be applied via custom shaders or post-processing
    _sphereMaterial.diffuse.intensity = _brightness;
}

- (void)updateStereoscopicMode
{
    // Implementation for stereoscopic 3D would involve:
    // 1. Splitting the image into left/right eye views
    // 2. Applying appropriate UV mapping
    // 3. Adjusting for interpupillary distance
    // This is a complex feature that would require custom shaders
}

- (void)updateImageFormat
{
    // Handle different image formats (HDR, EXR, etc.)
    // This would involve format-specific loading and processing
}

#pragma mark - Auto Rotation

- (void)startAutoRotation
{
    [self stopAutoRotation];
    
    if (_autoRotateSpeed <= 0) return;
    
    CGFloat interval = 1.0f / 60.0f; // 60 FPS
    _autoRotateTimer = [NSTimer scheduledTimerWithTimeInterval:interval
                                                        target:self
                                                      selector:@selector(updateAutoRotation)
                                                      userInfo:nil
                                                       repeats:YES];
}

- (void)stopAutoRotation
{
    if (_autoRotateTimer) {
        [_autoRotateTimer invalidate];
        _autoRotateTimer = nil;
    }
}

- (void)updateAutoRotation
{
    CGFloat rotationIncrement = _autoRotateSpeed * (1.0f / 60.0f);
    
    if (_autoRotateAxis && _autoRotateAxis.count >= 3) {
        // Rotate around specified axis
        SCNVector3 axis = SCNVector3Make([_autoRotateAxis[0] floatValue],
                                        [_autoRotateAxis[1] floatValue],
                                        [_autoRotateAxis[2] floatValue]);
        SCNMatrix4 rotation = SCNMatrix4MakeRotation(rotationIncrement, axis.x, axis.y, axis.z);
        _imageNode.transform = SCNMatrix4Mult(_imageNode.transform, rotation);
    } else {
        // Default Y-axis rotation
        SCNVector3 currentRotation = _imageNode.eulerAngles;
        currentRotation.y += rotationIncrement;
        _imageNode.eulerAngles = currentRotation;
    }
}

#pragma mark - Control Methods

- (void)reload
{
    [self loadImage];
}

- (void)preloadImage
{
    if (!_isLoaded && !_isLoading) {
        [self loadImage];
    }
}

- (void)clearCache
{
    // Clear texture cache
    _sphereMaterial.diffuse.contents = nil;
    _isLoaded = NO;
    _hasError = NO;
    _loadProgress = 0.0f;
}

- (void)setRotationAnimated:(NSArray<NSNumber *> *)rotation duration:(CGFloat)duration
{
    if (!rotation || rotation.count < 3) return;
    
    SCNVector3 targetRotation = SCNVector3Make([rotation[0] floatValue],
                                              [rotation[1] floatValue],
                                              [rotation[2] floatValue]);
    
    [SCNTransaction begin];
    [SCNTransaction setAnimationDuration:duration];
    _imageNode.eulerAngles = targetRotation;
    [SCNTransaction commit];
}

- (void)resetRotation
{
    [SCNTransaction begin];
    [SCNTransaction setAnimationDuration:_animationDuration];
    _imageNode.eulerAngles = SCNVector3Zero;
    [SCNTransaction commit];
}

#pragma mark - State Information

- (BOOL)isLoaded
{
    return _isLoaded;
}

- (BOOL)isLoading
{
    return _isLoading;
}

- (BOOL)hasError
{
    return _hasError;
}

- (NSInteger)getTextureWidth
{
    return _textureWidth;
}

- (NSInteger)getTextureHeight
{
    return _textureHeight;
}

- (CGFloat)getLoadProgress
{
    return _loadProgress;
}

- (NSDictionary *)getImageInfo
{
    return @{
        @"width": @(_textureWidth),
        @"height": @(_textureHeight),
        @"format": _format ?: @"unknown",
        @"projection": _projection ?: @"equirectangular",
        @"isLoaded": @(_isLoaded),
        @"isLoading": @(_isLoading),
        @"hasError": @(_hasError),
        @"progress": @(_loadProgress)
    };
}

- (NSString *)getCurrentFormat
{
    return _format ?: @"unknown";
}

- (NSString *)getCurrentProjection
{
    return _projection ?: @"equirectangular";
}

#pragma mark - Texture Utilities

- (void)setTextureParameters:(NSDictionary *)parameters
{
    if (!_sphereMaterial) return;
    
    // Apply texture parameters
    if (parameters[@"minFilter"]) {
        // Set texture minification filter
    }
    if (parameters[@"magFilter"]) {
        // Set texture magnification filter
    }
    if (parameters[@"wrapS"]) {
        // Set S-coordinate wrapping
    }
    if (parameters[@"wrapT"]) {
        // Set T-coordinate wrapping
    }
}

- (void)optimizeTexture
{
    // Optimize texture for performance
    // This could involve compression, mipmap generation, etc.
}

- (void)releaseTexture
{
    _sphereMaterial.diffuse.contents = nil;
    _isLoaded = NO;
}

- (NSInteger)getTextureMemoryUsage
{
    // Calculate approximate memory usage
    return _textureWidth * _textureHeight * 4; // Assuming RGBA format
}

#pragma mark - Cleanup

- (void)dealloc
{
    [self stopAutoRotation];
    [_loadingTask cancel];
}

@end