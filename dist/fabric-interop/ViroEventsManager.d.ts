declare const _default: ViroEventsManager;
export default _default;
declare class ViroEventsManager {
    eventEmitter: NativeEventEmitter;
    listeners: Map<any, any>;
    callbackRegistry: Map<any, any>;
    setupEventListeners(): void;
    /**
     * Register a callback for JSI events
     * @param {string} callbackId - The callback ID from JSI
     * @param {function} callback - The JavaScript callback function
     */
    registerJSICallback(callbackId: string, callback: Function): void;
    /**
     * Unregister a JSI callback
     * @param {string} callbackId - The callback ID to remove
     */
    unregisterJSICallback(callbackId: string): void;
    /**
     * Register a listener for node events
     * @param {string} nodeId - The node ID
     * @param {string} eventName - The event name
     * @param {function} callback - The callback function
     */
    registerNodeEventListener(nodeId: string, eventName: string, callback: Function): void;
    /**
     * Register a listener for scene events
     * @param {string} sceneId - The scene ID
     * @param {string} eventName - The event name
     * @param {function} callback - The callback function
     */
    registerSceneEventListener(sceneId: string, eventName: string, callback: Function): void;
    /**
     * Handle JSI callback events
     * @private
     */
    private handleJSICallback;
    /**
     * Handle node events
     * @private
     */
    private handleNodeEvent;
    /**
     * Handle scene events
     * @private
     */
    private handleSceneEvent;
    /**
     * Check if the event system is ready
     * @returns {boolean}
     */
    isEventSystemReady(): boolean;
    /**
     * Get the number of active listeners
     * @returns {number}
     */
    getActiveListenerCount(): number;
    /**
     * Manually emit a JSI callback (for testing)
     * @param {string} callbackId - The callback ID
     * @param {object} eventData - The event data
     */
    emitJSICallback(callbackId: string, eventData?: object): void;
    /**
     * Manually emit a node event (for testing)
     * @param {string} nodeId - The node ID
     * @param {string} eventName - The event name
     * @param {object} eventData - The event data
     */
    emitNodeEvent(nodeId: string, eventName: string, eventData?: object): void;
    /**
     * Manually emit a scene event (for testing)
     * @param {string} sceneId - The scene ID
     * @param {string} eventName - The event name
     * @param {object} eventData - The event data
     */
    emitSceneEvent(sceneId: string, eventName: string, eventData?: object): void;
    /**
     * Clean up all listeners and callbacks
     */
    cleanup(): void;
}
import { NativeEventEmitter } from "react-native";
