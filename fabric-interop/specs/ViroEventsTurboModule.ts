/**
 * ViroEventsTurboModule.ts
 * TurboModule specification for Viro event emission
 *
 * This module provides proper event emission for JSI callbacks
 * in React Native's New Architecture (Fabric + TurboModules)
 *
 * @format
 */

import type { TurboModule } from "react-native";
import { TurboModuleRegistry } from "react-native";

export interface Spec extends TurboModule {
  // Event listener management
  readonly addListener: (eventName: string) => void;
  readonly removeListeners: (count: number) => void;

  // Event emission methods
  readonly emitJSICallback: (callbackId: string, eventData: Object) => void;
  readonly emitNodeEvent: (nodeId: string, eventName: string, eventData: Object) => void;
  readonly emitSceneEvent: (sceneId: string, eventName: string, eventData: Object) => void;

  // Utility methods
  readonly isEventSystemReady: () => boolean;
  readonly getActiveListenerCount: () => number;
}

export default TurboModuleRegistry.getEnforcing<Spec>("ViroEvents");
