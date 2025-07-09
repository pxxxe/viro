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
export interface Spec extends TurboModule {
    readonly addListener: (eventName: string) => void;
    readonly removeListeners: (count: number) => void;
    readonly emitJSICallback: (callbackId: string, eventData: Object) => void;
    readonly emitNodeEvent: (nodeId: string, eventName: string, eventData: Object) => void;
    readonly emitSceneEvent: (sceneId: string, eventName: string, eventData: Object) => void;
    readonly isEventSystemReady: () => boolean;
    readonly getActiveListenerCount: () => number;
}
declare const _default: Spec;
export default _default;
//# sourceMappingURL=ViroEventsTurboModule.d.ts.map