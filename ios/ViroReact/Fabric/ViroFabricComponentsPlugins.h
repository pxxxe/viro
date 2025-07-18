//
//  ViroFabricComponentsPlugins.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <React/RCTComponentViewProtocol.h>

NS_ASSUME_NONNULL_BEGIN

// ComponentView class providers for ViroReact Fabric components

Class<RCTComponentViewProtocol> ViroSceneNavigatorCls(void);
Class<RCTComponentViewProtocol> ViroARSceneNavigatorCls(void);
Class<RCTComponentViewProtocol> ViroVRSceneNavigatorCls(void);
Class<RCTComponentViewProtocol> Viro3DSceneNavigatorCls(void);

// Scene components
Class<RCTComponentViewProtocol> ViroSceneCls(void);
Class<RCTComponentViewProtocol> ViroARSceneCls(void);
Class<RCTComponentViewProtocol> ViroPortalSceneCls(void);

// Node components
Class<RCTComponentViewProtocol> ViroNodeCls(void);
Class<RCTComponentViewProtocol> ViroFlexViewCls(void);
Class<RCTComponentViewProtocol> ViroAnimatedComponentCls(void);

// 3D Object components
Class<RCTComponentViewProtocol> Viro3DObjectCls(void);
Class<RCTComponentViewProtocol> ViroBoxCls(void);
Class<RCTComponentViewProtocol> ViroSphereCls(void);
Class<RCTComponentViewProtocol> ViroQuadCls(void);
Class<RCTComponentViewProtocol> ViroGeometryCls(void);
Class<RCTComponentViewProtocol> ViroPolygonCls(void);
Class<RCTComponentViewProtocol> ViroPolylineCls(void);

// Media components
Class<RCTComponentViewProtocol> ViroImageCls(void);
Class<RCTComponentViewProtocol> ViroVideoCls(void);
Class<RCTComponentViewProtocol> Viro360ImageCls(void);
Class<RCTComponentViewProtocol> Viro360VideoCls(void);
Class<RCTComponentViewProtocol> ViroAnimatedImageCls(void);
Class<RCTComponentViewProtocol> ViroMaterialVideoCls(void);

// UI components
Class<RCTComponentViewProtocol> ViroTextCls(void);
Class<RCTComponentViewProtocol> ViroButtonCls(void);
Class<RCTComponentViewProtocol> ViroSpinnerCls(void);

// Lighting components
Class<RCTComponentViewProtocol> ViroAmbientLightCls(void);
Class<RCTComponentViewProtocol> ViroDirectionalLightCls(void);
Class<RCTComponentViewProtocol> ViroOmniLightCls(void);
Class<RCTComponentViewProtocol> ViroSpotLightCls(void);
Class<RCTComponentViewProtocol> ViroLightingEnvironmentCls(void);

// Camera components
Class<RCTComponentViewProtocol> ViroCameraCls(void);
Class<RCTComponentViewProtocol> ViroARCameraCls(void);
Class<RCTComponentViewProtocol> ViroOrbitCameraCls(void);

// Environment components
Class<RCTComponentViewProtocol> ViroSkyBoxCls(void);
Class<RCTComponentViewProtocol> ViroSurfaceCls(void);
Class<RCTComponentViewProtocol> ViroPortalCls(void);

// Audio components
Class<RCTComponentViewProtocol> ViroSoundCls(void);
Class<RCTComponentViewProtocol> ViroSoundFieldCls(void);
Class<RCTComponentViewProtocol> ViroSpatialSoundCls(void);

// Effects components
Class<RCTComponentViewProtocol> ViroParticleEmitterCls(void);

// AR-specific components
Class<RCTComponentViewProtocol> ViroARPlaneCls(void);
Class<RCTComponentViewProtocol> ViroARPlaneSelectorCls(void);
Class<RCTComponentViewProtocol> ViroARImageMarkerCls(void);
Class<RCTComponentViewProtocol> ViroARObjectMarkerCls(void);

// VR/Input components
Class<RCTComponentViewProtocol> ViroControllerCls(void);

NS_ASSUME_NONNULL_END