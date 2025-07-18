//
//  ViroSceneNavigatorComponentView.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTViewComponentView.h>

NS_ASSUME_NONNULL_BEGIN

@interface ViroSceneNavigatorComponentView : RCTViewComponentView

// Scene navigation methods
- (void)push:(NSDictionary *)scene passProps:(nullable NSDictionary *)passProps;
- (void)pop;
- (void)popN:(NSInteger)n;
- (void)replace:(NSDictionary *)scene passProps:(nullable NSDictionary *)passProps;
- (void)jumpToScene:(NSDictionary *)scene passProps:(nullable NSDictionary *)passProps;

// Configuration methods
- (void)setInitialScene:(nullable NSDictionary *)scene;
- (void)setViroAppProps:(nullable NSDictionary *)props;
- (void)setAutofocus:(BOOL)autofocus;
- (void)setBloomEnabled:(BOOL)enabled;
- (void)setShadowsEnabled:(BOOL)enabled;
- (void)setMultisamplingEnabled:(BOOL)enabled;
- (void)setHdrEnabled:(BOOL)enabled;
- (void)setPbrEnabled:(BOOL)enabled;
- (void)setWorldAlignment:(nullable NSString *)alignment;
- (void)setVideoQuality:(nullable NSString *)quality;
- (void)setNumberOfTrackedImages:(NSInteger)count;
- (void)setVrModeEnabled:(BOOL)enabled;
- (void)setDebug:(BOOL)debug;
- (void)setCanCameraTransformUpdate:(BOOL)canUpdate;

@end

NS_ASSUME_NONNULL_END