package com.viromedia.bridge.fabric;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ViroReactTurboModule provides the main functionality for ViroReact
 * in React Native's New Architecture (Fabric + TurboModules)
 */
public class ViroReactTurboModule extends com.facebook.react.bridge.ReactContextBaseJavaModule implements TurboModule {
    
    private static final String TAG = "ViroReactTurboModule";
    private static final String MODULE_NAME = "ViroReact";
    
    // Singleton instance
    private static ViroReactTurboModule sInstance;
    
    // Internal state management
    private final Map<String, Map<String, Object>> mScenes = new ConcurrentHashMap<>();
    private final Map<String, Map<String, Object>> mNodes = new ConcurrentHashMap<>();
    private final Map<String, Map<String, Object>> mMaterials = new ConcurrentHashMap<>();
    private final Map<String, Map<String, Object>> mAnimations = new ConcurrentHashMap<>();
    private boolean mIsInitialized = false;
    
    public ViroReactTurboModule(ReactApplicationContext reactContext) {
        super(reactContext);
        sInstance = this;
        Log.i(TAG, "ViroReactTurboModule initialized");
    }
    
    @Override
    public String getName() {
        return MODULE_NAME;
    }
    
    /**
     * Get the singleton instance
     */
    public static ViroReactTurboModule getInstance() {
        return sInstance;
    }
    
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap<>();
        constants.put("version", "2.43.3");
        constants.put("platform", "android");
        constants.put("newArchitecture", true);
        constants.put("supportedEvents", new String[]{
            "ViroSceneChanged",
            "ViroNodeEvent", 
            "ViroAnimationEvent",
            "ViroAREvent",
            "ViroErrorEvent"
        });
        return constants;
    }
    
    // Event listener management
    
    @ReactMethod
    public void addListener(String eventName) {
        // Event listener registration
        Log.i(TAG, "Added listener for event: " + eventName);
    }
    
    @ReactMethod 
    public void removeListeners(double count) {
        // Event listener removal
        Log.i(TAG, "Removed " + (int) count + " listeners");
    }
    
    // Scene management
    
    @ReactMethod
    public void createScene(String sceneId, String sceneType, ReadableMap props) {
        Log.i(TAG, "Creating scene: " + sceneId + " of type: " + sceneType);
        
        // TODO: Integrate with existing ViroReact scene creation
        // This will need to interface with the existing ViroReact native implementation
        
        Map<String, Object> scene = new HashMap<>();
        scene.put("id", sceneId);
        scene.put("type", sceneType);
        scene.put("props", props != null ? props.toHashMap() : new HashMap<>());
        scene.put("created", System.currentTimeMillis() / 1000.0);
        
        mScenes.put(sceneId, scene);
        
        // Emit scene created event
        WritableMap eventData = Arguments.createMap();
        eventData.putString("sceneId", sceneId);
        eventData.putString("action", "created");
        eventData.putString("sceneType", sceneType);
        sendEvent("ViroSceneChanged", eventData);
    }
    
    @ReactMethod
    public void updateScene(String sceneId, ReadableMap props) {
        Log.i(TAG, "Updating scene: " + sceneId);
        
        Map<String, Object> scene = mScenes.get(sceneId);
        if (scene != null) {
            @SuppressWarnings("unchecked")
            Map<String, Object> currentProps = (Map<String, Object>) scene.get("props");
            if (currentProps == null) {
                currentProps = new HashMap<>();
            }
            
            if (props != null) {
                currentProps.putAll(props.toHashMap());
            }
            scene.put("props", currentProps);
            scene.put("updated", System.currentTimeMillis() / 1000.0);
            
            // Emit scene updated event
            WritableMap eventData = Arguments.createMap();
            eventData.putString("sceneId", sceneId);
            eventData.putString("action", "updated");
            eventData.putMap("props", Arguments.makeNativeMap(props.toHashMap()));
            sendEvent("ViroSceneChanged", eventData);
        } else {
            Log.e(TAG, "Scene not found: " + sceneId);
        }
    }
    
    @ReactMethod
    public void destroyScene(String sceneId) {
        Log.i(TAG, "Destroying scene: " + sceneId);
        
        if (mScenes.containsKey(sceneId)) {
            mScenes.remove(sceneId);
            
            // Emit scene destroyed event
            WritableMap eventData = Arguments.createMap();
            eventData.putString("sceneId", sceneId);
            eventData.putString("action", "destroyed");
            sendEvent("ViroSceneChanged", eventData);
        } else {
            Log.e(TAG, "Scene not found: " + sceneId);
        }
    }
    
    @ReactMethod
    public void activateScene(String sceneId) {
        Log.i(TAG, "Activating scene: " + sceneId);
        
        Map<String, Object> scene = mScenes.get(sceneId);
        if (scene != null) {
            scene.put("active", true);
            scene.put("activated", System.currentTimeMillis() / 1000.0);
            
            // Deactivate other scenes
            for (Map.Entry<String, Map<String, Object>> entry : mScenes.entrySet()) {
                if (!entry.getKey().equals(sceneId)) {
                    entry.getValue().put("active", false);
                }
            }
            
            WritableMap eventData = Arguments.createMap();
            eventData.putString("sceneId", sceneId);
            eventData.putString("action", "activated");
            sendEvent("ViroSceneChanged", eventData);
        }
    }
    
    @ReactMethod
    public void deactivateScene(String sceneId) {
        Log.i(TAG, "Deactivating scene: " + sceneId);
        
        Map<String, Object> scene = mScenes.get(sceneId);
        if (scene != null) {
            scene.put("active", false);
            scene.put("deactivated", System.currentTimeMillis() / 1000.0);
            
            WritableMap eventData = Arguments.createMap();
            eventData.putString("sceneId", sceneId);
            eventData.putString("action", "deactivated");
            sendEvent("ViroSceneChanged", eventData);
        }
    }
    
    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getSceneState(String sceneId) {
        Map<String, Object> scene = mScenes.get(sceneId);
        if (scene != null) {
            Boolean isActive = (Boolean) scene.get("active");
            return (isActive != null && isActive) ? "active" : "inactive";
        }
        return "not_found";
    }
    
    // Node management
    
    @ReactMethod
    public void createNode(String nodeId, String nodeType, ReadableMap props) {
        Log.i(TAG, "Creating node: " + nodeId + " of type: " + nodeType);
        
        Map<String, Object> node = new HashMap<>();
        node.put("id", nodeId);
        node.put("type", nodeType);
        node.put("props", props != null ? props.toHashMap() : new HashMap<>());
        node.put("created", System.currentTimeMillis() / 1000.0);
        node.put("children", new java.util.ArrayList<String>());
        
        mNodes.put(nodeId, node);
        
        WritableMap eventData = Arguments.createMap();
        eventData.putString("nodeId", nodeId);
        eventData.putString("action", "created");
        eventData.putString("nodeType", nodeType);
        sendEvent("ViroNodeEvent", eventData);
    }
    
    @ReactMethod
    public void updateNode(String nodeId, ReadableMap props) {
        Log.i(TAG, "Updating node: " + nodeId);
        
        Map<String, Object> node = mNodes.get(nodeId);
        if (node != null) {
            @SuppressWarnings("unchecked")
            Map<String, Object> currentProps = (Map<String, Object>) node.get("props");
            if (currentProps == null) {
                currentProps = new HashMap<>();
            }
            
            if (props != null) {
                currentProps.putAll(props.toHashMap());
            }
            node.put("props", currentProps);
            node.put("updated", System.currentTimeMillis() / 1000.0);
            
            WritableMap eventData = Arguments.createMap();
            eventData.putString("nodeId", nodeId);
            eventData.putString("action", "updated");
            eventData.putMap("props", Arguments.makeNativeMap(props.toHashMap()));
            sendEvent("ViroNodeEvent", eventData);
        }
    }
    
    @ReactMethod
    public void deleteNode(String nodeId) {
        Log.i(TAG, "Deleting node: " + nodeId);
        
        if (mNodes.containsKey(nodeId)) {
            mNodes.remove(nodeId);
            
            WritableMap eventData = Arguments.createMap();
            eventData.putString("nodeId", nodeId);
            eventData.putString("action", "deleted");
            sendEvent("ViroNodeEvent", eventData);
        }
    }
    
    @ReactMethod
    public void addChild(String parentId, String childId) {
        Map<String, Object> parent = mNodes.get(parentId);
        if (parent != null) {
            @SuppressWarnings("unchecked")
            java.util.List<String> children = (java.util.List<String>) parent.get("children");
            if (children != null) {
                children.add(childId);
            }
            
            WritableMap eventData = Arguments.createMap();
            eventData.putString("parentId", parentId);
            eventData.putString("childId", childId);
            eventData.putString("action", "child_added");
            sendEvent("ViroNodeEvent", eventData);
        }
    }
    
    @ReactMethod
    public void removeChild(String parentId, String childId) {
        Map<String, Object> parent = mNodes.get(parentId);
        if (parent != null) {
            @SuppressWarnings("unchecked")
            java.util.List<String> children = (java.util.List<String>) parent.get("children");
            if (children != null) {
                children.remove(childId);
            }
            
            WritableMap eventData = Arguments.createMap();
            eventData.putString("parentId", parentId);
            eventData.putString("childId", childId);
            eventData.putString("action", "child_removed");
            sendEvent("ViroNodeEvent", eventData);
        }
    }
    
    // Material management
    
    @ReactMethod
    public void createMaterial(String materialName, ReadableMap properties) {
        Log.i(TAG, "Creating material: " + materialName);
        
        Map<String, Object> material = new HashMap<>();
        material.put("name", materialName);
        material.put("properties", properties != null ? properties.toHashMap() : new HashMap<>());
        material.put("created", System.currentTimeMillis() / 1000.0);
        
        mMaterials.put(materialName, material);
    }
    
    @ReactMethod
    public void updateMaterial(String materialName, ReadableMap properties) {
        Map<String, Object> material = mMaterials.get(materialName);
        if (material != null) {
            @SuppressWarnings("unchecked")
            Map<String, Object> currentProps = (Map<String, Object>) material.get("properties");
            if (currentProps == null) {
                currentProps = new HashMap<>();
            }
            
            if (properties != null) {
                currentProps.putAll(properties.toHashMap());
            }
            material.put("properties", currentProps);
            material.put("updated", System.currentTimeMillis() / 1000.0);
        }
    }
    
    @ReactMethod
    public void deleteMaterial(String materialName) {
        mMaterials.remove(materialName);
    }
    
    // Animation management
    
    @ReactMethod
    public void createAnimation(String animationName, ReadableMap properties) {
        Log.i(TAG, "Creating animation: " + animationName);
        
        Map<String, Object> animation = new HashMap<>();
        animation.put("name", animationName);
        animation.put("properties", properties != null ? properties.toHashMap() : new HashMap<>());
        animation.put("created", System.currentTimeMillis() / 1000.0);
        
        mAnimations.put(animationName, animation);
    }
    
    @ReactMethod
    public void executeAnimation(String nodeId, String animationName, ReadableMap options) {
        Log.i(TAG, "Executing animation: " + animationName + " on node: " + nodeId);
        
        WritableMap eventData = Arguments.createMap();
        eventData.putString("nodeId", nodeId);
        eventData.putString("animationName", animationName);
        eventData.putString("action", "started");
        eventData.putMap("options", options != null ? Arguments.makeNativeMap(options.toHashMap()) : Arguments.createMap());
        sendEvent("ViroAnimationEvent", eventData);
    }
    
    @ReactMethod
    public void stopAnimation(String nodeId, String animationName) {
        WritableMap eventData = Arguments.createMap();
        eventData.putString("nodeId", nodeId);
        eventData.putString("animationName", animationName);
        eventData.putString("action", "stopped");
        sendEvent("ViroAnimationEvent", eventData);
    }
    
    @ReactMethod
    public void pauseAnimation(String nodeId, String animationName) {
        WritableMap eventData = Arguments.createMap();
        eventData.putString("nodeId", nodeId);
        eventData.putString("animationName", animationName);
        eventData.putString("action", "paused");
        sendEvent("ViroAnimationEvent", eventData);
    }
    
    @ReactMethod
    public void resumeAnimation(String nodeId, String animationName) {
        WritableMap eventData = Arguments.createMap();
        eventData.putString("nodeId", nodeId);
        eventData.putString("animationName", animationName);
        eventData.putString("action", "resumed");
        sendEvent("ViroAnimationEvent", eventData);
    }
    
    // AR functionality
    
    @ReactMethod
    public void setARPlaneDetection(boolean enabled, String alignment) {
        Log.i(TAG, "Setting AR plane detection: " + enabled + " alignment: " + alignment);
        
        WritableMap eventData = Arguments.createMap();
        eventData.putString("action", "plane_detection_changed");
        eventData.putBoolean("enabled", enabled);
        eventData.putString("alignment", alignment != null ? alignment : "horizontal");
        sendEvent("ViroAREvent", eventData);
    }
    
    @ReactMethod
    public void setARImageTargets(ReadableMap targets) {
        Log.i(TAG, "Setting AR image targets");
        
        WritableMap eventData = Arguments.createMap();
        eventData.putString("action", "image_targets_set");
        eventData.putMap("targets", Arguments.makeNativeMap(targets.toHashMap()));
        sendEvent("ViroAREvent", eventData);
    }
    
    @ReactMethod
    public void setARObjectTargets(ReadableMap targets) {
        Log.i(TAG, "Setting AR object targets");
        
        WritableMap eventData = Arguments.createMap();
        eventData.putString("action", "object_targets_set");
        eventData.putMap("targets", Arguments.makeNativeMap(targets.toHashMap()));
        sendEvent("ViroAREvent", eventData);
    }
    
    @ReactMethod
    public void recenterTracking() {
        Log.i(TAG, "Recentering tracking");
        
        WritableMap eventData = Arguments.createMap();
        eventData.putString("action", "tracking_recentered");
        sendEvent("ViroAREvent", eventData);
    }
    
    @ReactMethod
    public void setWorldOrigin(ReadableArray origin) {
        Log.i(TAG, "Setting world origin");
        
        WritableMap eventData = Arguments.createMap();
        eventData.putString("action", "world_origin_set");
        eventData.putArray("origin", Arguments.makeNativeArray(origin.toArrayList()));
        sendEvent("ViroAREvent", eventData);
    }
    
    // Camera controls
    
    @ReactMethod
    public void getCameraPosition(Callback callback) {
        // TODO: Get actual camera position from ViroReact renderer
        WritableArray position = Arguments.createArray();
        position.pushDouble(0.0);
        position.pushDouble(0.0);
        position.pushDouble(0.0);
        callback.invoke(position);
    }
    
    @ReactMethod
    public void setCameraPosition(ReadableArray position) {
        Log.i(TAG, "Setting camera position");
        // TODO: Set actual camera position in ViroReact renderer
    }
    
    @ReactMethod
    public void getCameraRotation(Callback callback) {
        // TODO: Get actual camera rotation from ViroReact renderer
        WritableArray rotation = Arguments.createArray();
        rotation.pushDouble(0.0);
        rotation.pushDouble(0.0);
        rotation.pushDouble(0.0);
        callback.invoke(rotation);
    }
    
    @ReactMethod
    public void setCameraRotation(ReadableArray rotation) {
        Log.i(TAG, "Setting camera rotation");
        // TODO: Set actual camera rotation in ViroReact renderer
    }
    
    // Utility methods
    
    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isReady() {
        return mIsInitialized;
    }
    
    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getVersion() {
        return "2.43.3";
    }
    
    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getMemoryStats() {
        WritableMap stats = Arguments.createMap();
        stats.putInt("scenes", mScenes.size());
        stats.putInt("nodes", mNodes.size());
        stats.putInt("materials", mMaterials.size());
        stats.putInt("animations", mAnimations.size());
        return stats;
    }
    
    @ReactMethod
    public void performMemoryCleanup() {
        Log.i(TAG, "Performing memory cleanup");
        // TODO: Implement actual memory cleanup
    }
    
    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isPlatformSupported() {
        return true;
    }
    
    @ReactMethod
    public void isARSupported(Callback callback) {
        // TODO: Check actual AR support
        callback.invoke(true);
    }
    
    @ReactMethod
    public void isVRSupported(Callback callback) {
        // TODO: Check actual VR support
        callback.invoke(true);
    }
    
    // Helper methods
    
    private void sendEvent(String eventName, WritableMap params) {
        try {
            ReactApplicationContext reactContext = getReactApplicationContext();
            if (reactContext != null && reactContext.hasActiveCatalystInstance()) {
                reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
            } else {
                Log.w(TAG, "Cannot send event " + eventName + ": no active React context");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error sending event " + eventName + ": " + e.getMessage(), e);
        }
    }
    
    @Override
    public void invalidate() {
        super.invalidate();
        Log.i(TAG, "ViroReactTurboModule invalidated");
        if (sInstance == this) {
            sInstance = null;
        }
    }
}