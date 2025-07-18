/**
 * ViroReactTurboModule.ts
 * Core TurboModule specification for ViroReact
 * 
 * This module provides the main functionality for ViroReact including
 * scene management, node management, materials, animations, and AR features.
 */

import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  // Event listener management
  addListener: (eventName: string) => void;
  removeListeners: (count: number) => void;
  
  // Scene management
  createScene: (sceneId: string, sceneType: string, props: Object) => void;
  updateScene: (sceneId: string, props: Object) => void;
  destroyScene: (sceneId: string) => void;
  activateScene: (sceneId: string) => void;
  deactivateScene: (sceneId: string) => void;
  getSceneState: (sceneId: string) => string;
  
  // Node management
  createNode: (nodeId: string, nodeType: string, props: Object) => void;
  updateNode: (nodeId: string, props: Object) => void;
  deleteNode: (nodeId: string) => void;
  addChild: (parentId: string, childId: string) => void;
  removeChild: (parentId: string, childId: string) => void;
  
  // Material management
  createMaterial: (materialName: string, properties: Object) => void;
  updateMaterial: (materialName: string, properties: Object) => void;
  deleteMaterial: (materialName: string) => void;
  
  // Animation management
  createAnimation: (animationName: string, properties: Object) => void;
  executeAnimation: (nodeId: string, animationName: string, options?: Object) => void;
  stopAnimation: (nodeId: string, animationName: string) => void;
  pauseAnimation: (nodeId: string, animationName: string) => void;
  resumeAnimation: (nodeId: string, animationName: string) => void;
  
  // AR specific functionality
  setARPlaneDetection: (enabled: boolean, alignment?: string) => void;
  setARImageTargets: (targets: Object) => void;
  setARObjectTargets: (targets: Object) => void;
  recenterTracking: () => void;
  setWorldOrigin: (origin: Array<number>) => void;
  
  // Camera controls
  getCameraPosition: () => Promise<Array<number>>;
  setCameraPosition: (position: Array<number>) => void;
  getCameraRotation: () => Promise<Array<number>>;
  setCameraRotation: (rotation: Array<number>) => void;
  
  // Utility methods
  isReady: () => boolean;
  getVersion: () => string;
  getMemoryStats: () => Object;
  performMemoryCleanup: () => void;
  
  // Platform detection
  isPlatformSupported: () => boolean;
  isARSupported: () => Promise<boolean>;
  isVRSupported: () => Promise<boolean>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('ViroReact');