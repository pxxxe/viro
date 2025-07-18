//
//  ViroReactTurboModule.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <React/RCTEventEmitter.h>
#import <ReactCommon/RCTTurboModule.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroReactTurboModule : RCTEventEmitter <RCTTurboModule>

// Singleton access
+ (instancetype)sharedInstance;

// Scene management
- (void)createScene:(NSString *)sceneId 
          sceneType:(NSString *)sceneType 
              props:(NSDictionary *)props;
- (void)updateScene:(NSString *)sceneId 
              props:(NSDictionary *)props;
- (void)destroyScene:(NSString *)sceneId;
- (void)activateScene:(NSString *)sceneId;
- (void)deactivateScene:(NSString *)sceneId;
- (NSString *)getSceneState:(NSString *)sceneId;

// Node management
- (void)createNode:(NSString *)nodeId 
          nodeType:(NSString *)nodeType 
             props:(NSDictionary *)props;
- (void)updateNode:(NSString *)nodeId 
             props:(NSDictionary *)props;
- (void)deleteNode:(NSString *)nodeId;
- (void)addChild:(NSString *)parentId 
         childId:(NSString *)childId;
- (void)removeChild:(NSString *)parentId 
            childId:(NSString *)childId;

// Material management
- (void)createMaterial:(NSString *)materialName 
            properties:(NSDictionary *)properties;
- (void)updateMaterial:(NSString *)materialName 
            properties:(NSDictionary *)properties;
- (void)deleteMaterial:(NSString *)materialName;

// Animation management
- (void)createAnimation:(NSString *)animationName 
             properties:(NSDictionary *)properties;
- (void)executeAnimation:(NSString *)nodeId 
           animationName:(NSString *)animationName 
                 options:(nullable NSDictionary *)options;
- (void)stopAnimation:(NSString *)nodeId 
        animationName:(NSString *)animationName;
- (void)pauseAnimation:(NSString *)nodeId 
         animationName:(NSString *)animationName;
- (void)resumeAnimation:(NSString *)nodeId 
          animationName:(NSString *)animationName;

// AR functionality
- (void)setARPlaneDetection:(BOOL)enabled 
                  alignment:(nullable NSString *)alignment;
- (void)setARImageTargets:(NSDictionary *)targets;
- (void)setARObjectTargets:(NSDictionary *)targets;
- (void)recenterTracking;
- (void)setWorldOrigin:(NSArray<NSNumber *> *)origin;

// Camera controls
- (void)getCameraPosition:(RCTResponseSenderBlock)callback;
- (void)setCameraPosition:(NSArray<NSNumber *> *)position;
- (void)getCameraRotation:(RCTResponseSenderBlock)callback;
- (void)setCameraRotation:(NSArray<NSNumber *> *)rotation;

// Utility methods
- (NSNumber *)isReady;
- (NSString *)getVersion;
- (NSDictionary *)getMemoryStats;
- (void)performMemoryCleanup;
- (NSNumber *)isPlatformSupported;
- (void)isARSupported:(RCTResponseSenderBlock)callback;
- (void)isVRSupported:(RCTResponseSenderBlock)callback;

@end

NS_ASSUME_NONNULL_END