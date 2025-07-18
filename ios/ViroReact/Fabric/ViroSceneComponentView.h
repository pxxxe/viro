//
//  ViroSceneComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroSceneComponentView : RCTViewComponentView

// Scene configuration methods
- (void)setSoundRoom:(nullable NSDictionary *)soundRoom;
- (void)setPhysicsWorld:(nullable NSDictionary *)physicsWorld;
- (void)setPostProcessEffects:(nullable NSArray *)effects;
- (void)setLightingEnvironment:(nullable NSDictionary *)lightingEnv;
- (void)setBackgroundTexture:(nullable NSDictionary *)texture;
- (void)setBackgroundCubeTexture:(nullable NSDictionary *)cubeTexture;

// Scene state methods
- (void)setOnPlatformUpdate:(nullable RCTBubblingEventBlock)onPlatformUpdate;
- (void)setOnCameraTransformUpdate:(nullable RCTBubblingEventBlock)onCameraTransformUpdate;

@end

NS_ASSUME_NONNULL_END