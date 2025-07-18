/**
 * ViroSceneNavigatorTurboModule.ts
 * TurboModule specification for ViroReact Scene Navigation
 * 
 * This module provides scene navigation functionality including
 * push, pop, jump, and replace operations for scene management.
 */

import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  // Scene navigation
  push: (sceneId: string, scene: Object, animated?: boolean) => void;
  pop: (animated?: boolean) => void;
  popN: (n: number, animated?: boolean) => void;
  jump: (sceneId: string, scene: Object, animated?: boolean) => void;
  replace: (sceneId: string, scene: Object, animated?: boolean) => void;
  
  // Scene stack management
  getCurrentScene: () => Object;
  getSceneHistory: () => Array<Object>;
  getSceneStackSize: () => number;
  clearSceneHistory: () => void;
  
  // Scene state
  setSceneState: (sceneId: string, state: Object) => void;
  getSceneState: (sceneId: string) => Object;
  
  // Navigation controls
  setNavigationEnabled: (enabled: boolean) => void;
  isNavigationEnabled: () => boolean;
  
  // AR/VR specific navigation
  recenterTracking: () => void;
  resetTrackingOrigin: () => void;
  
  // Performance controls
  setPerformanceMode: (mode: string) => void;
  getPerformanceMode: () => string;
  
  // Event callbacks (these will be called from native)
  onSceneChanged: (sceneId: string, previousSceneId?: string) => void;
  onNavigationStarted: (fromScene: string, toScene: string) => void;
  onNavigationFinished: (fromScene: string, toScene: string) => void;
  onNavigationFailed: (error: string) => void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('ViroSceneNavigator');