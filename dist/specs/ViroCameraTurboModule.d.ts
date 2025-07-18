/**
 * ViroCameraTurboModule.ts
 * TurboModule specification for ViroReact Camera Management
 *
 * This module provides camera control functionality including
 * camera movement, rotation, field of view, and rendering controls.
 */
import type { TurboModule } from 'react-native';
export interface Spec extends TurboModule {
    setPosition: (position: Array<number>) => void;
    getPosition: () => Promise<Array<number>>;
    setRotation: (rotation: Array<number>) => void;
    getRotation: () => Promise<Array<number>>;
    setFieldOfView: (fov: number) => void;
    getFieldOfView: () => Promise<number>;
    setNearClippingPlane: (distance: number) => void;
    setFarClippingPlane: (distance: number) => void;
    animateToPosition: (position: Array<number>, duration: number) => void;
    animateToRotation: (rotation: Array<number>, duration: number) => void;
    animateToTransform: (position: Array<number>, rotation: Array<number>, duration: number) => void;
    setActiveCamera: (cameraId: string) => void;
    getActiveCamera: () => Promise<string>;
    createCamera: (cameraId: string, properties: Object) => void;
    deleteCamera: (cameraId: string) => void;
    setOrbitFocalPoint: (point: Array<number>) => void;
    getOrbitFocalPoint: () => Promise<Array<number>>;
    setOrbitDistance: (distance: number) => void;
    getOrbitDistance: () => Promise<number>;
    setARTrackingMode: (mode: string) => void;
    getARTrackingMode: () => Promise<string>;
    setARWorldAlignment: (alignment: string) => void;
    getARWorldAlignment: () => Promise<string>;
    setRenderingEnabled: (enabled: boolean) => void;
    isRenderingEnabled: () => Promise<boolean>;
    setBackgroundColor: (color: Array<number>) => void;
    setAmbientLightColor: (color: Array<number>) => void;
    setAmbientLightIntensity: (intensity: number) => void;
    setViewportSize: (width: number, height: number) => void;
    getViewportSize: () => Promise<Array<number>>;
    project: (worldPosition: Array<number>) => Promise<Array<number>>;
    unproject: (screenPosition: Array<number>) => Promise<Array<number>>;
    takeScreenshot: (filename?: string) => Promise<string>;
    startVideoRecording: (filename?: string) => Promise<boolean>;
    stopVideoRecording: () => Promise<string>;
    isRecording: () => Promise<boolean>;
}
declare const _default: Spec;
export default _default;
