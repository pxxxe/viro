//
//  ViroFabricComponentsPlugins.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroFabricComponentsPlugins.h"
#import "ViroSceneNavigatorComponentView.h"
#import "ViroSceneComponentView.h"
#import "ViroNodeComponentView.h"
#import "ViroBoxComponentView.h"
#import "ViroTextComponentView.h"
#import "ViroImageComponentView.h"
#import "ViroSphereComponentView.h"
#import "ViroCameraComponentView.h"
#import "ViroAmbientLightComponentView.h"
#import "ViroFlexViewComponentView.h"
#import "ViroQuadComponentView.h"
#import "ViroDirectionalLightComponentView.h"
#import "ViroOmniLightComponentView.h"
#import "ViroSpotLightComponentView.h"
#import "ViroVideoComponentView.h"
#import "Viro3DObjectComponentView.h"
#import "ViroAnimatedComponentView.h"
#import "ViroGeometryComponentView.h"
#import "ViroButtonComponentView.h"
#import "ViroSkyBoxComponentView.h"
#import "ViroSpinnerComponentView.h"
#import "ViroParticleEmitterComponentView.h"
#import "ViroSoundComponentView.h"
#import "Viro360ImageComponentView.h"
#import "ViroAnimatedImageComponentView.h"
#import "Viro360VideoComponentView.h"
#import "ViroMaterialVideoComponentView.h"
#import "ViroPolygonComponentView.h"
#import "ViroPolylineComponentView.h"
#import "ViroSurfaceComponentView.h"
#import "ViroSoundFieldComponentView.h"
#import "ViroSpatialSoundComponentView.h"
#import <React/RCTLog.h>

// For now, we'll implement ViroSceneNavigator and provide placeholders for others
// TODO: Implement all ComponentViews and their corresponding registrations

#pragma mark - Scene Navigator Components

Class<RCTComponentViewProtocol> ViroSceneNavigatorCls(void)
{
    return ViroSceneNavigatorComponentView.class;
}

Class<RCTComponentViewProtocol> ViroARSceneNavigatorCls(void)
{
    // TODO: Implement ViroARSceneNavigatorComponentView
    RCTLogWarn(@"ViroARSceneNavigator Fabric component not yet implemented");
    return nil;
}

Class<RCTComponentViewProtocol> ViroVRSceneNavigatorCls(void)
{
    // TODO: Implement ViroVRSceneNavigatorComponentView
    RCTLogWarn(@"ViroVRSceneNavigator Fabric component not yet implemented");
    return nil;
}

Class<RCTComponentViewProtocol> Viro3DSceneNavigatorCls(void)
{
    // TODO: Implement Viro3DSceneNavigatorComponentView
    RCTLogWarn(@"Viro3DSceneNavigator Fabric component not yet implemented");
    return nil;
}

#pragma mark - Scene Components

Class<RCTComponentViewProtocol> ViroSceneCls(void)
{
    return ViroSceneComponentView.class;
}

Class<RCTComponentViewProtocol> ViroARSceneCls(void)
{
    // TODO: Implement ViroARSceneComponentView
    RCTLogWarn(@"ViroARScene Fabric component not yet implemented");
    return nil;
}

Class<RCTComponentViewProtocol> ViroPortalSceneCls(void)
{
    // TODO: Implement ViroPortalSceneComponentView
    RCTLogWarn(@"ViroPortalScene Fabric component not yet implemented");
    return nil;
}

#pragma mark - Node Components

Class<RCTComponentViewProtocol> ViroNodeCls(void)
{
    return ViroNodeComponentView.class;
}

Class<RCTComponentViewProtocol> ViroFlexViewCls(void)
{
    return ViroFlexViewComponentView.class;
}

Class<RCTComponentViewProtocol> ViroAnimatedComponentCls(void)
{
    return ViroAnimatedComponentView.class;
}

#pragma mark - 3D Object Components

Class<RCTComponentViewProtocol> Viro3DObjectCls(void)
{
    return Viro3DObjectComponentView.class;
}

Class<RCTComponentViewProtocol> ViroBoxCls(void)
{
    return ViroBoxComponentView.class;
}

Class<RCTComponentViewProtocol> ViroSphereCls(void)
{
    return ViroSphereComponentView.class;
}

Class<RCTComponentViewProtocol> ViroQuadCls(void)
{
    return ViroQuadComponentView.class;
}

Class<RCTComponentViewProtocol> ViroGeometryCls(void)
{
    return ViroGeometryComponentView.class;
}

Class<RCTComponentViewProtocol> ViroPolygonCls(void)
{
    return ViroPolygonComponentView.class;
}

Class<RCTComponentViewProtocol> ViroPolylineCls(void)
{
    return ViroPolylineComponentView.class;
}

#pragma mark - Media Components

Class<RCTComponentViewProtocol> ViroImageCls(void)
{
    return ViroImageComponentView.class;
}

Class<RCTComponentViewProtocol> ViroVideoCls(void)
{
    return ViroVideoComponentView.class;
}

Class<RCTComponentViewProtocol> Viro360ImageCls(void)
{
    return Viro360ImageComponentView.class;
}

Class<RCTComponentViewProtocol> Viro360VideoCls(void)
{
    return Viro360VideoComponentView.class;
}

Class<RCTComponentViewProtocol> ViroAnimatedImageCls(void)
{
    return ViroAnimatedImageComponentView.class;
}

Class<RCTComponentViewProtocol> ViroMaterialVideoCls(void)
{
    return ViroMaterialVideoComponentView.class;
}

#pragma mark - UI Components

Class<RCTComponentViewProtocol> ViroTextCls(void)
{
    return ViroTextComponentView.class;
}

Class<RCTComponentViewProtocol> ViroButtonCls(void)
{
    return ViroButtonComponentView.class;
}

Class<RCTComponentViewProtocol> ViroSpinnerCls(void)
{
    return ViroSpinnerComponentView.class;
}

#pragma mark - Lighting Components

Class<RCTComponentViewProtocol> ViroAmbientLightCls(void)
{
    return ViroAmbientLightComponentView.class;
}

Class<RCTComponentViewProtocol> ViroDirectionalLightCls(void)
{
    return ViroDirectionalLightComponentView.class;
}

Class<RCTComponentViewProtocol> ViroOmniLightCls(void)
{
    return ViroOmniLightComponentView.class;
}

Class<RCTComponentViewProtocol> ViroSpotLightCls(void)
{
    return ViroSpotLightComponentView.class;
}

Class<RCTComponentViewProtocol> ViroLightingEnvironmentCls(void)
{
    // TODO: Implement ViroLightingEnvironmentComponentView
    RCTLogWarn(@"ViroLightingEnvironment Fabric component not yet implemented");
    return nil;
}

#pragma mark - Camera Components

Class<RCTComponentViewProtocol> ViroCameraCls(void)
{
    return ViroCameraComponentView.class;
}

Class<RCTComponentViewProtocol> ViroARCameraCls(void)
{
    // TODO: Implement ViroARCameraComponentView
    RCTLogWarn(@"ViroARCamera Fabric component not yet implemented");
    return nil;
}

Class<RCTComponentViewProtocol> ViroOrbitCameraCls(void)
{
    // TODO: Implement ViroOrbitCameraComponentView
    RCTLogWarn(@"ViroOrbitCamera Fabric component not yet implemented");
    return nil;
}

#pragma mark - Environment Components

Class<RCTComponentViewProtocol> ViroSkyBoxCls(void)
{
    return ViroSkyBoxComponentView.class;
}

Class<RCTComponentViewProtocol> ViroSurfaceCls(void)
{
    return ViroSurfaceComponentView.class;
}

Class<RCTComponentViewProtocol> ViroPortalCls(void)
{
    // TODO: Implement ViroPortalComponentView
    RCTLogWarn(@"ViroPortal Fabric component not yet implemented");
    return nil;
}

#pragma mark - Audio Components

Class<RCTComponentViewProtocol> ViroSoundCls(void)
{
    return ViroSoundComponentView.class;
}

Class<RCTComponentViewProtocol> ViroSoundFieldCls(void)
{
    return ViroSoundFieldComponentView.class;
}

Class<RCTComponentViewProtocol> ViroSpatialSoundCls(void)
{
    return ViroSpatialSoundComponentView.class;
}

#pragma mark - Effects Components

Class<RCTComponentViewProtocol> ViroParticleEmitterCls(void)
{
    return ViroParticleEmitterComponentView.class;
}

#pragma mark - AR Components

Class<RCTComponentViewProtocol> ViroARPlaneCls(void)
{
    // TODO: Implement ViroARPlaneComponentView
    RCTLogWarn(@"ViroARPlane Fabric component not yet implemented");
    return nil;
}

Class<RCTComponentViewProtocol> ViroARPlaneSelectorCls(void)
{
    // TODO: Implement ViroARPlaneSelectorComponentView
    RCTLogWarn(@"ViroARPlaneSelector Fabric component not yet implemented");
    return nil;
}

Class<RCTComponentViewProtocol> ViroARImageMarkerCls(void)
{
    // TODO: Implement ViroARImageMarkerComponentView
    RCTLogWarn(@"ViroARImageMarker Fabric component not yet implemented");
    return nil;
}

Class<RCTComponentViewProtocol> ViroARObjectMarkerCls(void)
{
    // TODO: Implement ViroARObjectMarkerComponentView
    RCTLogWarn(@"ViroARObjectMarker Fabric component not yet implemented");
    return nil;
}

#pragma mark - VR/Input Components

Class<RCTComponentViewProtocol> ViroControllerCls(void)
{
    // TODO: Implement ViroControllerComponentView
    RCTLogWarn(@"ViroController Fabric component not yet implemented");
    return nil;
}