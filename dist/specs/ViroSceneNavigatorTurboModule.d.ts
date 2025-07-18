/**
 * ViroSceneNavigatorTurboModule.ts
 * TurboModule specification for ViroReact Scene Navigation
 *
 * This module provides scene navigation functionality including
 * push, pop, jump, and replace operations for scene management.
 */
import type { TurboModule } from 'react-native';
export interface Spec extends TurboModule {
    push: (sceneId: string, scene: Object, animated?: boolean) => void;
    pop: (animated?: boolean) => void;
    popN: (n: number, animated?: boolean) => void;
    jump: (sceneId: string, scene: Object, animated?: boolean) => void;
    replace: (sceneId: string, scene: Object, animated?: boolean) => void;
    getCurrentScene: () => Object;
    getSceneHistory: () => Array<Object>;
    getSceneStackSize: () => number;
    clearSceneHistory: () => void;
    setSceneState: (sceneId: string, state: Object) => void;
    getSceneState: (sceneId: string) => Object;
    setNavigationEnabled: (enabled: boolean) => void;
    isNavigationEnabled: () => boolean;
    recenterTracking: () => void;
    resetTrackingOrigin: () => void;
    setPerformanceMode: (mode: string) => void;
    getPerformanceMode: () => string;
    onSceneChanged: (sceneId: string, previousSceneId?: string) => void;
    onNavigationStarted: (fromScene: string, toScene: string) => void;
    onNavigationFinished: (fromScene: string, toScene: string) => void;
    onNavigationFailed: (error: string) => void;
}
declare const _default: Spec;
export default _default;
