//
//  ViroReactFabricRegistrations.mm
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import "ViroReactFabricRegistrations.h"
#import "ViroFabricComponentsPlugins.h"
#import <React/RCTFabricComponentsPlugins.h>
#import <React/RCTLog.h>

void ViroReactRegisterFabricComponents(void)
{
    RCTLogInfo(@"[ViroReact] Registering Fabric components");
    
    // Register ViroReact components with React Native's Fabric system
    // The registration is done by providing component view class providers
    
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        
        // Scene Navigator components
        if (ViroSceneNavigatorCls()) {
            RCTLogInfo(@"[ViroReact] Registered ViroSceneNavigator Fabric component");
        }
        
        // TODO: Register additional components as they are implemented
        // The pattern for each component is:
        // if (ViroComponentCls()) {
        //     RCTLogInfo(@"[ViroReact] Registered ViroComponent Fabric component");
        // }
        
        RCTLogInfo(@"[ViroReact] Fabric component registration completed");
    });
}