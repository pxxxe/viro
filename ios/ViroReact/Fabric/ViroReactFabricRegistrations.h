//
//  ViroReactFabricRegistrations.h
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * Registers all ViroReact Fabric components with the React Native Fabric system.
 * This function should be called during app initialization to ensure ViroReact
 * components are available in New Architecture apps.
 */
void ViroReactRegisterFabricComponents(void);

NS_ASSUME_NONNULL_END