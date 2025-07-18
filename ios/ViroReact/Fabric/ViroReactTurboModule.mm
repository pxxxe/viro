//
//  ViroReactTurboModule.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroReactTurboModule.h"
#import <React/RCTLog.h>
#import <React/RCTUtils.h>

@interface ViroReactTurboModule ()
@property (nonatomic, strong) NSMutableDictionary<NSString *, id> *scenes;
@property (nonatomic, strong) NSMutableDictionary<NSString *, id> *nodes;
@property (nonatomic, strong) NSMutableDictionary<NSString *, id> *materials;
@property (nonatomic, strong) NSMutableDictionary<NSString *, id> *animations;
@property (nonatomic, assign) BOOL isInitialized;
@end

@implementation ViroReactTurboModule

static ViroReactTurboModule *sharedInstance = nil;

+ (instancetype)sharedInstance {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        sharedInstance = [[ViroReactTurboModule alloc] init];
    });
    return sharedInstance;
}

- (instancetype)init {
    self = [super init];
    if (self) {
        _scenes = [[NSMutableDictionary alloc] init];
        _nodes = [[NSMutableDictionary alloc] init];
        _materials = [[NSMutableDictionary alloc] init];
        _animations = [[NSMutableDictionary alloc] init];
        _isInitialized = NO;
        
        RCTLogInfo(@"[ViroReactTurboModule] Initialized");
    }
    return self;
}

#pragma mark - RCTEventEmitter

- (NSArray<NSString *> *)supportedEvents {
    return @[
        @"ViroSceneChanged",
        @"ViroNodeEvent",
        @"ViroAnimationEvent",
        @"ViroAREvent",
        @"ViroErrorEvent"
    ];
}

+ (BOOL)requiresMainQueueSetup {
    return YES;
}

- (NSDictionary *)constantsToExport {
    return @{
        @"version": @"2.43.3",
        @"platform": @"ios",
        @"newArchitecture": @YES,
        @"supportedEvents": [self supportedEvents]
    };
}

#pragma mark - TurboModule Registration

RCT_EXPORT_MODULE(ViroReact)

#pragma mark - Scene Management

RCT_EXPORT_METHOD(createScene:(NSString *)sceneId 
                  sceneType:(NSString *)sceneType 
                      props:(NSDictionary *)props) {
    RCTLogInfo(@"[ViroReactTurboModule] Creating scene: %@ of type: %@", sceneId, sceneType);
    
    // TODO: Integrate with existing ViroReact scene creation
    // This will need to interface with the existing ViroReact native implementation
    
    NSMutableDictionary *scene = [[NSMutableDictionary alloc] init];
    scene[@"id"] = sceneId;
    scene[@"type"] = sceneType;
    scene[@"props"] = props ?: @{};
    scene[@"created"] = @([[NSDate date] timeIntervalSince1970]);
    
    _scenes[sceneId] = scene;
    
    // Emit scene created event
    [self sendEventWithName:@"ViroSceneChanged" body:@{
        @"sceneId": sceneId,
        @"action": @"created",
        @"sceneType": sceneType
    }];
}

RCT_EXPORT_METHOD(updateScene:(NSString *)sceneId 
                        props:(NSDictionary *)props) {
    RCTLogInfo(@"[ViroReactTurboModule] Updating scene: %@", sceneId);
    
    NSMutableDictionary *scene = _scenes[sceneId];
    if (scene) {
        NSMutableDictionary *currentProps = [scene[@"props"] mutableCopy];
        [currentProps addEntriesFromDictionary:props];
        scene[@"props"] = currentProps;
        scene[@"updated"] = @([[NSDate date] timeIntervalSince1970]);
        
        // Emit scene updated event
        [self sendEventWithName:@"ViroSceneChanged" body:@{
            @"sceneId": sceneId,
            @"action": @"updated",
            @"props": props
        }];
    } else {
        RCTLogError(@"[ViroReactTurboModule] Scene not found: %@", sceneId);
    }
}

RCT_EXPORT_METHOD(destroyScene:(NSString *)sceneId) {
    RCTLogInfo(@"[ViroReactTurboModule] Destroying scene: %@", sceneId);
    
    if (_scenes[sceneId]) {
        [_scenes removeObjectForKey:sceneId];
        
        // Emit scene destroyed event
        [self sendEventWithName:@"ViroSceneChanged" body:@{
            @"sceneId": sceneId,
            @"action": @"destroyed"
        }];
    } else {
        RCTLogError(@"[ViroReactTurboModule] Scene not found: %@", sceneId);
    }
}

RCT_EXPORT_METHOD(activateScene:(NSString *)sceneId) {
    RCTLogInfo(@"[ViroReactTurboModule] Activating scene: %@", sceneId);
    
    NSMutableDictionary *scene = _scenes[sceneId];
    if (scene) {
        scene[@"active"] = @YES;
        scene[@"activated"] = @([[NSDate date] timeIntervalSince1970]);
        
        // Deactivate other scenes
        for (NSString *otherSceneId in _scenes.allKeys) {
            if (![otherSceneId isEqualToString:sceneId]) {
                NSMutableDictionary *otherScene = _scenes[otherSceneId];
                otherScene[@"active"] = @NO;
            }
        }
        
        [self sendEventWithName:@"ViroSceneChanged" body:@{
            @"sceneId": sceneId,
            @"action": @"activated"
        }];
    }
}

RCT_EXPORT_METHOD(deactivateScene:(NSString *)sceneId) {
    RCTLogInfo(@"[ViroReactTurboModule] Deactivating scene: %@", sceneId);
    
    NSMutableDictionary *scene = _scenes[sceneId];
    if (scene) {
        scene[@"active"] = @NO;
        scene[@"deactivated"] = @([[NSDate date] timeIntervalSince1970]);
        
        [self sendEventWithName:@"ViroSceneChanged" body:@{
            @"sceneId": sceneId,
            @"action": @"deactivated"
        }];
    }
}

RCT_EXPORT_SYNCHRONOUS_TYPED_METHOD(NSString *, getSceneState:(NSString *)sceneId) {
    NSDictionary *scene = _scenes[sceneId];
    if (scene) {
        BOOL isActive = [scene[@"active"] boolValue];
        return isActive ? @"active" : @"inactive";
    }
    return @"not_found";
}

#pragma mark - Node Management

RCT_EXPORT_METHOD(createNode:(NSString *)nodeId 
                    nodeType:(NSString *)nodeType 
                       props:(NSDictionary *)props) {
    RCTLogInfo(@"[ViroReactTurboModule] Creating node: %@ of type: %@", nodeId, nodeType);
    
    NSMutableDictionary *node = [[NSMutableDictionary alloc] init];
    node[@"id"] = nodeId;
    node[@"type"] = nodeType;
    node[@"props"] = props ?: @{};
    node[@"created"] = @([[NSDate date] timeIntervalSince1970]);
    node[@"children"] = [[NSMutableArray alloc] init];
    
    _nodes[nodeId] = node;
    
    [self sendEventWithName:@"ViroNodeEvent" body:@{
        @"nodeId": nodeId,
        @"action": @"created",
        @"nodeType": nodeType
    }];
}

RCT_EXPORT_METHOD(updateNode:(NSString *)nodeId 
                       props:(NSDictionary *)props) {
    RCTLogInfo(@"[ViroReactTurboModule] Updating node: %@", nodeId);
    
    NSMutableDictionary *node = _nodes[nodeId];
    if (node) {
        NSMutableDictionary *currentProps = [node[@"props"] mutableCopy];
        [currentProps addEntriesFromDictionary:props];
        node[@"props"] = currentProps;
        node[@"updated"] = @([[NSDate date] timeIntervalSince1970]);
        
        [self sendEventWithName:@"ViroNodeEvent" body:@{
            @"nodeId": nodeId,
            @"action": @"updated",
            @"props": props
        }];
    }
}

RCT_EXPORT_METHOD(deleteNode:(NSString *)nodeId) {
    RCTLogInfo(@"[ViroReactTurboModule] Deleting node: %@", nodeId);
    
    if (_nodes[nodeId]) {
        [_nodes removeObjectForKey:nodeId];
        
        [self sendEventWithName:@"ViroNodeEvent" body:@{
            @"nodeId": nodeId,
            @"action": @"deleted"
        }];
    }
}

RCT_EXPORT_METHOD(addChild:(NSString *)parentId 
                   childId:(NSString *)childId) {
    NSMutableDictionary *parent = _nodes[parentId];
    if (parent) {
        NSMutableArray *children = parent[@"children"];
        [children addObject:childId];
        
        [self sendEventWithName:@"ViroNodeEvent" body:@{
            @"parentId": parentId,
            @"childId": childId,
            @"action": @"child_added"
        }];
    }
}

RCT_EXPORT_METHOD(removeChild:(NSString *)parentId 
                       childId:(NSString *)childId) {
    NSMutableDictionary *parent = _nodes[parentId];
    if (parent) {
        NSMutableArray *children = parent[@"children"];
        [children removeObject:childId];
        
        [self sendEventWithName:@"ViroNodeEvent" body:@{
            @"parentId": parentId,
            @"childId": childId,
            @"action": @"child_removed"
        }];
    }
}

#pragma mark - Material Management

RCT_EXPORT_METHOD(createMaterial:(NSString *)materialName 
                       properties:(NSDictionary *)properties) {
    RCTLogInfo(@"[ViroReactTurboModule] Creating material: %@", materialName);
    
    NSMutableDictionary *material = [[NSMutableDictionary alloc] init];
    material[@"name"] = materialName;
    material[@"properties"] = properties ?: @{};
    material[@"created"] = @([[NSDate date] timeIntervalSince1970]);
    
    _materials[materialName] = material;
}

RCT_EXPORT_METHOD(updateMaterial:(NSString *)materialName 
                       properties:(NSDictionary *)properties) {
    NSMutableDictionary *material = _materials[materialName];
    if (material) {
        NSMutableDictionary *currentProps = [material[@"properties"] mutableCopy];
        [currentProps addEntriesFromDictionary:properties];
        material[@"properties"] = currentProps;
        material[@"updated"] = @([[NSDate date] timeIntervalSince1970]);
    }
}

RCT_EXPORT_METHOD(deleteMaterial:(NSString *)materialName) {
    [_materials removeObjectForKey:materialName];
}

#pragma mark - Animation Management

RCT_EXPORT_METHOD(createAnimation:(NSString *)animationName 
                        properties:(NSDictionary *)properties) {
    RCTLogInfo(@"[ViroReactTurboModule] Creating animation: %@", animationName);
    
    NSMutableDictionary *animation = [[NSMutableDictionary alloc] init];
    animation[@"name"] = animationName;
    animation[@"properties"] = properties ?: @{};
    animation[@"created"] = @([[NSDate date] timeIntervalSince1970]);
    
    _animations[animationName] = animation;
}

RCT_EXPORT_METHOD(executeAnimation:(NSString *)nodeId 
                      animationName:(NSString *)animationName 
                            options:(nullable NSDictionary *)options) {
    RCTLogInfo(@"[ViroReactTurboModule] Executing animation: %@ on node: %@", animationName, nodeId);
    
    [self sendEventWithName:@"ViroAnimationEvent" body:@{
        @"nodeId": nodeId,
        @"animationName": animationName,
        @"action": @"started",
        @"options": options ?: @{}
    }];
}

RCT_EXPORT_METHOD(stopAnimation:(NSString *)nodeId 
                   animationName:(NSString *)animationName) {
    [self sendEventWithName:@"ViroAnimationEvent" body:@{
        @"nodeId": nodeId,
        @"animationName": animationName,
        @"action": @"stopped"
    }];
}

RCT_EXPORT_METHOD(pauseAnimation:(NSString *)nodeId 
                    animationName:(NSString *)animationName) {
    [self sendEventWithName:@"ViroAnimationEvent" body:@{
        @"nodeId": nodeId,
        @"animationName": animationName,
        @"action": @"paused"
    }];
}

RCT_EXPORT_METHOD(resumeAnimation:(NSString *)nodeId 
                     animationName:(NSString *)animationName) {
    [self sendEventWithName:@"ViroAnimationEvent" body:@{
        @"nodeId": nodeId,
        @"animationName": animationName,
        @"action": @"resumed"
    }];
}

#pragma mark - AR Functionality

RCT_EXPORT_METHOD(setARPlaneDetection:(BOOL)enabled 
                             alignment:(nullable NSString *)alignment) {
    RCTLogInfo(@"[ViroReactTurboModule] Setting AR plane detection: %@ alignment: %@", 
               enabled ? @"YES" : @"NO", alignment);
    
    [self sendEventWithName:@"ViroAREvent" body:@{
        @"action": @"plane_detection_changed",
        @"enabled": @(enabled),
        @"alignment": alignment ?: @"horizontal"
    }];
}

RCT_EXPORT_METHOD(setARImageTargets:(NSDictionary *)targets) {
    RCTLogInfo(@"[ViroReactTurboModule] Setting AR image targets: %@", targets);
    
    [self sendEventWithName:@"ViroAREvent" body:@{
        @"action": @"image_targets_set",
        @"targets": targets
    }];
}

RCT_EXPORT_METHOD(setARObjectTargets:(NSDictionary *)targets) {
    RCTLogInfo(@"[ViroReactTurboModule] Setting AR object targets: %@", targets);
    
    [self sendEventWithName:@"ViroAREvent" body:@{
        @"action": @"object_targets_set",
        @"targets": targets
    }];
}

RCT_EXPORT_METHOD(recenterTracking) {
    RCTLogInfo(@"[ViroReactTurboModule] Recentering tracking");
    
    [self sendEventWithName:@"ViroAREvent" body:@{
        @"action": @"tracking_recentered"
    }];
}

RCT_EXPORT_METHOD(setWorldOrigin:(NSArray<NSNumber *> *)origin) {
    RCTLogInfo(@"[ViroReactTurboModule] Setting world origin: %@", origin);
    
    [self sendEventWithName:@"ViroAREvent" body:@{
        @"action": @"world_origin_set",
        @"origin": origin
    }];
}

#pragma mark - Camera Controls

RCT_EXPORT_METHOD(getCameraPosition:(RCTResponseSenderBlock)callback) {
    // TODO: Get actual camera position from ViroReact renderer
    NSArray *position = @[@0, @0, @0];
    callback(@[position]);
}

RCT_EXPORT_METHOD(setCameraPosition:(NSArray<NSNumber *> *)position) {
    RCTLogInfo(@"[ViroReactTurboModule] Setting camera position: %@", position);
    // TODO: Set actual camera position in ViroReact renderer
}

RCT_EXPORT_METHOD(getCameraRotation:(RCTResponseSenderBlock)callback) {
    // TODO: Get actual camera rotation from ViroReact renderer
    NSArray *rotation = @[@0, @0, @0];
    callback(@[rotation]);
}

RCT_EXPORT_METHOD(setCameraRotation:(NSArray<NSNumber *> *)rotation) {
    RCTLogInfo(@"[ViroReactTurboModule] Setting camera rotation: %@", rotation);
    // TODO: Set actual camera rotation in ViroReact renderer
}

#pragma mark - Utility Methods

RCT_EXPORT_SYNCHRONOUS_TYPED_METHOD(NSNumber *, isReady) {
    return @(_isInitialized);
}

RCT_EXPORT_SYNCHRONOUS_TYPED_METHOD(NSString *, getVersion) {
    return @"2.43.3";
}

RCT_EXPORT_SYNCHRONOUS_TYPED_METHOD(NSDictionary *, getMemoryStats) {
    return @{
        @"scenes": @(_scenes.count),
        @"nodes": @(_nodes.count),
        @"materials": @(_materials.count),
        @"animations": @(_animations.count)
    };
}

RCT_EXPORT_METHOD(performMemoryCleanup) {
    RCTLogInfo(@"[ViroReactTurboModule] Performing memory cleanup");
    // TODO: Implement actual memory cleanup
}

RCT_EXPORT_SYNCHRONOUS_TYPED_METHOD(NSNumber *, isPlatformSupported) {
    return @YES;
}

RCT_EXPORT_METHOD(isARSupported:(RCTResponseSenderBlock)callback) {
    // TODO: Check actual AR support
    callback(@[@YES]);
}

RCT_EXPORT_METHOD(isVRSupported:(RCTResponseSenderBlock)callback) {
    // TODO: Check actual VR support
    callback(@[@YES]);
}

#pragma mark - Lifecycle

- (void)dealloc {
    RCTLogInfo(@"[ViroReactTurboModule] Deallocated");
}

@end