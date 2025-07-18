//
//  Viro3DObjectViewManager.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.viromedia.bridge.utility.ViroLog;

/**
 * Viro3DObjectViewManager - 3D Model Loading ViewManager
 * 
 * This ViewManager handles 3D model loading and rendering in ViroReact.
 * It supports various 3D formats (OBJ, FBX, GLTF, GLB, DAE) and provides
 * comprehensive control over model appearance, animations, and transformations.
 * 
 * Key Features:
 * - Multi-format 3D model support
 * - Animation playback and control
 * - Morph target support
 * - Material assignment and lighting
 * - Resource management (textures, materials)
 * - Event callbacks for loading and errors
 * - Integration with ViroReact scene graph
 */
public class Viro3DObjectViewManager extends SimpleViewManager<Viro3DObjectView> {
    
    private static final String TAG = ViroLog.getTag(Viro3DObjectViewManager.class);
    private static final String REACT_CLASS = "Viro3DObject";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public Viro3DObjectView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating Viro3DObjectView instance");
        return new Viro3DObjectView(reactContext);
    }
    
    // 3D Model Source Properties
    @ReactProp(name = "source")
    public void setSource(Viro3DObjectView view, @Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        view.setSource(source);
    }
    
    @ReactProp(name = "uri")
    public void setUri(Viro3DObjectView view, @Nullable String uri) {
        ViroLog.debug(TAG, "Setting URI: " + uri);
        view.setUri(uri);
    }
    
    @ReactProp(name = "type")
    public void setType(Viro3DObjectView view, @Nullable String type) {
        ViroLog.debug(TAG, "Setting type: " + type);
        view.setType(type);
    }
    
    // Model Resources
    @ReactProp(name = "resources")
    public void setResources(Viro3DObjectView view, @Nullable ReadableArray resources) {
        ViroLog.debug(TAG, "Setting resources: " + resources);
        view.setResources(resources);
    }
    
    // Model Appearance
    @ReactProp(name = "materials")
    public void setMaterials(Viro3DObjectView view, @Nullable ReadableArray materials) {
        ViroLog.debug(TAG, "Setting materials: " + materials);
        view.setMaterials(materials);
    }
    
    @ReactProp(name = "lightReceivingBitMask", defaultInt = 1)
    public void setLightReceivingBitMask(Viro3DObjectView view, int lightReceivingBitMask) {
        ViroLog.debug(TAG, "Setting light receiving bit mask: " + lightReceivingBitMask);
        view.setLightReceivingBitMask(lightReceivingBitMask);
    }
    
    @ReactProp(name = "shadowCastingBitMask", defaultInt = 1)
    public void setShadowCastingBitMask(Viro3DObjectView view, int shadowCastingBitMask) {
        ViroLog.debug(TAG, "Setting shadow casting bit mask: " + shadowCastingBitMask);
        view.setShadowCastingBitMask(shadowCastingBitMask);
    }
    
    // Model Transformation
    @ReactProp(name = "scale")
    public void setScale(Viro3DObjectView view, @Nullable ReadableArray scale) {
        ViroLog.debug(TAG, "Setting scale: " + scale);
        view.setScale(scale);
    }
    
    @ReactProp(name = "rotation")
    public void setRotation(Viro3DObjectView view, @Nullable ReadableArray rotation) {
        ViroLog.debug(TAG, "Setting rotation: " + rotation);
        view.setRotation(rotation);
    }
    
    @ReactProp(name = "position")
    public void setPosition(Viro3DObjectView view, @Nullable ReadableArray position) {
        ViroLog.debug(TAG, "Setting position: " + position);
        view.setPosition(position);
    }
    
    @ReactProp(name = "pivot")
    public void setPivot(Viro3DObjectView view, @Nullable ReadableArray pivot) {
        ViroLog.debug(TAG, "Setting pivot: " + pivot);
        view.setPivot(pivot);
    }
    
    // Animation Properties
    @ReactProp(name = "animation")
    public void setAnimation(Viro3DObjectView view, @Nullable ReadableMap animation) {
        ViroLog.debug(TAG, "Setting animation: " + animation);
        view.setAnimation(animation);
    }
    
    @ReactProp(name = "morphTargets")
    public void setMorphTargets(Viro3DObjectView view, @Nullable ReadableArray morphTargets) {
        ViroLog.debug(TAG, "Setting morph targets: " + morphTargets);
        view.setMorphTargets(morphTargets);
    }
    
    // Loading Configuration
    @ReactProp(name = "highAccuracyEvents", defaultBoolean = false)
    public void setHighAccuracyEvents(Viro3DObjectView view, boolean highAccuracyEvents) {
        ViroLog.debug(TAG, "Setting high accuracy events: " + highAccuracyEvents);
        view.setHighAccuracyEvents(highAccuracyEvents);
    }
    
    @ReactProp(name = "ignoreEventHandling", defaultBoolean = false)
    public void setIgnoreEventHandling(Viro3DObjectView view, boolean ignoreEventHandling) {
        ViroLog.debug(TAG, "Setting ignore event handling: " + ignoreEventHandling);
        view.setIgnoreEventHandling(ignoreEventHandling);
    }
    
    // Event Exports
    @Override
    public java.util.Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return java.util.Map.of(
            "onLoadStart", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onLoadStart",
                    "captured", "onLoadStartCapture"
                )
            ),
            "onLoad", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onLoad",
                    "captured", "onLoadCapture"
                )
            ),
            "onError", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onError",
                    "captured", "onErrorCapture"
                )
            ),
            "onAnimationStart", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onAnimationStart",
                    "captured", "onAnimationStartCapture"
                )
            ),
            "onAnimationFinish", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onAnimationFinish",
                    "captured", "onAnimationFinishCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull Viro3DObjectView view) {
        ViroLog.debug(TAG, "Dropping Viro3DObjectView instance");
        super.onDropViewInstance(view);
    }
}