/**
 * ViroCameraTurboModule.ts
 * TurboModule specification for ViroReact Camera Management
 * 
 * This module provides camera control functionality including
 * camera movement, rotation, field of view, and rendering controls.
 */

import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  // Camera position and rotation
  setPosition: (position: Array<number>) => void;
  getPosition: () => Promise<Array<number>>;
  setRotation: (rotation: Array<number>) => void;
  getRotation: () => Promise<Array<number>>;
  
  // Camera properties
  setFieldOfView: (fov: number) => void;
  getFieldOfView: () => Promise<number>;
  setNearClippingPlane: (distance: number) => void;
  setFarClippingPlane: (distance: number) => void;
  
  // Camera movement
  animateToPosition: (position: Array<number>, duration: number) => void;
  animateToRotation: (rotation: Array<number>, duration: number) => void;
  animateToTransform: (position: Array<number>, rotation: Array<number>, duration: number) => void;
  
  // Camera controls
  setActiveCamera: (cameraId: string) => void;
  getActiveCamera: () => Promise<string>;
  createCamera: (cameraId: string, properties: Object) => void;
  deleteCamera: (cameraId: string) => void;
  
  // Orbit camera controls
  setOrbitFocalPoint: (point: Array<number>) => void;
  getOrbitFocalPoint: () => Promise<Array<number>>;
  setOrbitDistance: (distance: number) => void;
  getOrbitDistance: () => Promise<number>;
  
  // AR Camera specific
  setARTrackingMode: (mode: string) => void;
  getARTrackingMode: () => Promise<string>;
  setARWorldAlignment: (alignment: string) => void;
  getARWorldAlignment: () => Promise<string>;
  
  // Rendering controls
  setRenderingEnabled: (enabled: boolean) => void;
  isRenderingEnabled: () => Promise<boolean>;
  setBackgroundColor: (color: Array<number>) => void;
  setAmbientLightColor: (color: Array<number>) => void;
  setAmbientLightIntensity: (intensity: number) => void;
  
  // Viewport controls
  setViewportSize: (width: number, height: number) => void;
  getViewportSize: () => Promise<Array<number>>;
  
  // Projection controls
  project: (worldPosition: Array<number>) => Promise<Array<number>>;
  unproject: (screenPosition: Array<number>) => Promise<Array<number>>;
  
  // Screenshot and recording
  takeScreenshot: (filename?: string) => Promise<string>;
  startVideoRecording: (filename?: string) => Promise<boolean>;
  stopVideoRecording: () => Promise<string>;
  isRecording: () => Promise<boolean>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('ViroCamera');