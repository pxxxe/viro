//
//  ViroSurfaceViewManager.java
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
 * ViroSurfaceViewManager - AR Surface Detection ViewManager
 * 
 * This ViewManager handles AR surface detection and plane visualization for ViroReact applications.
 * It provides comprehensive surface detection capabilities including AR plane detection,
 * surface geometry customization, and material support for AR experiences.
 * 
 * Key Features:
 * - AR plane detection (horizontal, vertical, all)
 * - Surface geometry customization (width, height, segments)
 * - Material support for surface visualization
 * - Plane anchor management
 * - Surface interaction and touch events
 * - Performance optimized AR tracking
 * - Real-time surface updates
 * - Multiple surface types support
 */
public class ViroSurfaceViewManager extends SimpleViewManager<ViroSurfaceView> {
    
    private static final String TAG = ViroLog.getTag(ViroSurfaceViewManager.class);
    private static final String REACT_CLASS = "ViroSurface";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroSurfaceView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroSurfaceView instance");
        return new ViroSurfaceView(reactContext);
    }
    
    // Surface Geometry Properties
    @ReactProp(name = "width", defaultFloat = 1.0f)
    public void setWidth(ViroSurfaceView view, float width) {
        ViroLog.debug(TAG, "Setting width: " + width);
        view.setWidth(width);
    }
    
    @ReactProp(name = "height", defaultFloat = 1.0f)
    public void setHeight(ViroSurfaceView view, float height) {
        ViroLog.debug(TAG, "Setting height: " + height);
        view.setHeight(height);
    }
    
    @ReactProp(name = "segments", defaultInt = 1)
    public void setSegments(ViroSurfaceView view, int segments) {
        ViroLog.debug(TAG, "Setting segments: " + segments);
        view.setSegments(segments);
    }
    
    @ReactProp(name = "widthSegments", defaultInt = 1)
    public void setWidthSegments(ViroSurfaceView view, int widthSegments) {
        ViroLog.debug(TAG, "Setting width segments: " + widthSegments);
        view.setWidthSegments(widthSegments);
    }
    
    @ReactProp(name = "heightSegments", defaultInt = 1)
    public void setHeightSegments(ViroSurfaceView view, int heightSegments) {
        ViroLog.debug(TAG, "Setting height segments: " + heightSegments);
        view.setHeightSegments(heightSegments);
    }
    
    // Material Properties
    @ReactProp(name = "materials")
    public void setMaterials(ViroSurfaceView view, @Nullable ReadableArray materials) {
        ViroLog.debug(TAG, "Setting materials: " + materials);
        view.setMaterials(materials);
    }
    
    // AR Plane Detection Properties
    @ReactProp(name = "arPlaneDetection", defaultBoolean = false)
    public void setArPlaneDetection(ViroSurfaceView view, boolean arPlaneDetection) {
        ViroLog.debug(TAG, "Setting AR plane detection: " + arPlaneDetection);
        view.setArPlaneDetection(arPlaneDetection);
    }
    
    @ReactProp(name = "arPlaneDetectionTypes")
    public void setArPlaneDetectionTypes(ViroSurfaceView view, @Nullable ReadableArray arPlaneDetectionTypes) {
        ViroLog.debug(TAG, "Setting AR plane detection types: " + arPlaneDetectionTypes);
        view.setArPlaneDetectionTypes(arPlaneDetectionTypes);
    }
    
    @ReactProp(name = "planeAlignment")
    public void setPlaneAlignment(ViroSurfaceView view, @Nullable String planeAlignment) {
        ViroLog.debug(TAG, "Setting plane alignment: " + planeAlignment);
        view.setPlaneAlignment(planeAlignment);
    }
    
    @ReactProp(name = "planeDetectionMode")
    public void setPlaneDetectionMode(ViroSurfaceView view, @Nullable String planeDetectionMode) {
        ViroLog.debug(TAG, "Setting plane detection mode: " + planeDetectionMode);
        view.setPlaneDetectionMode(planeDetectionMode);
    }
    
    @ReactProp(name = "minPlaneSize")
    public void setMinPlaneSize(ViroSurfaceView view, @Nullable ReadableArray minPlaneSize) {
        ViroLog.debug(TAG, "Setting min plane size: " + minPlaneSize);
        view.setMinPlaneSize(minPlaneSize);
    }
    
    @ReactProp(name = "maxPlaneSize")
    public void setMaxPlaneSize(ViroSurfaceView view, @Nullable ReadableArray maxPlaneSize) {
        ViroLog.debug(TAG, "Setting max plane size: " + maxPlaneSize);
        view.setMaxPlaneSize(maxPlaneSize);
    }
    
    @ReactProp(name = "planeAnchor")
    public void setPlaneAnchor(ViroSurfaceView view, @Nullable ReadableMap planeAnchor) {
        ViroLog.debug(TAG, "Setting plane anchor: " + planeAnchor);
        view.setPlaneAnchor(planeAnchor);
    }
    
    @ReactProp(name = "anchorId")
    public void setAnchorId(ViroSurfaceView view, @Nullable String anchorId) {
        ViroLog.debug(TAG, "Setting anchor ID: " + anchorId);
        view.setAnchorId(anchorId);
    }
    
    // Surface Tracking Properties
    @ReactProp(name = "trackingEnabled", defaultBoolean = true)
    public void setTrackingEnabled(ViroSurfaceView view, boolean trackingEnabled) {
        ViroLog.debug(TAG, "Setting tracking enabled: " + trackingEnabled);
        view.setTrackingEnabled(trackingEnabled);
    }
    
    @ReactProp(name = "trackingQuality")
    public void setTrackingQuality(ViroSurfaceView view, @Nullable String trackingQuality) {
        ViroLog.debug(TAG, "Setting tracking quality: " + trackingQuality);
        view.setTrackingQuality(trackingQuality);
    }
    
    @ReactProp(name = "updateFrequency", defaultFloat = 30.0f)
    public void setUpdateFrequency(ViroSurfaceView view, float updateFrequency) {
        ViroLog.debug(TAG, "Setting update frequency: " + updateFrequency);
        view.setUpdateFrequency(updateFrequency);
    }
    
    // Rendering Properties
    @ReactProp(name = "visible", defaultBoolean = true)
    public void setVisible(ViroSurfaceView view, boolean visible) {
        ViroLog.debug(TAG, "Setting visible: " + visible);
        view.setVisible(visible);
    }
    
    @ReactProp(name = "opacity", defaultFloat = 1.0f)
    public void setOpacity(ViroSurfaceView view, float opacity) {
        ViroLog.debug(TAG, "Setting opacity: " + opacity);
        view.setOpacity(opacity);
    }
    
    @ReactProp(name = "renderingOrder", defaultInt = 0)
    public void setRenderingOrder(ViroSurfaceView view, int renderingOrder) {
        ViroLog.debug(TAG, "Setting rendering order: " + renderingOrder);
        view.setRenderingOrder(renderingOrder);
    }
    
    // Transform Properties
    @ReactProp(name = "position")
    public void setPosition(ViroSurfaceView view, @Nullable ReadableArray position) {
        ViroLog.debug(TAG, "Setting position: " + position);
        view.setPosition(position);
    }
    
    @ReactProp(name = "rotation")
    public void setRotation(ViroSurfaceView view, @Nullable ReadableArray rotation) {
        ViroLog.debug(TAG, "Setting rotation: " + rotation);
        view.setRotation(rotation);
    }
    
    @ReactProp(name = "scale")
    public void setScale(ViroSurfaceView view, @Nullable ReadableArray scale) {
        ViroLog.debug(TAG, "Setting scale: " + scale);
        view.setScale(scale);
    }
    
    @ReactProp(name = "transformBehaviors")
    public void setTransformBehaviors(ViroSurfaceView view, @Nullable ReadableArray transformBehaviors) {
        ViroLog.debug(TAG, "Setting transform behaviors: " + transformBehaviors);
        view.setTransformBehaviors(transformBehaviors);
    }
    
    // Animation Properties
    @ReactProp(name = "animation")
    public void setAnimation(ViroSurfaceView view, @Nullable ReadableMap animation) {
        ViroLog.debug(TAG, "Setting animation: " + animation);
        view.setAnimation(animation);
    }
    
    // Physics Properties
    @ReactProp(name = "physicsBody")
    public void setPhysicsBody(ViroSurfaceView view, @Nullable ReadableMap physicsBody) {
        ViroLog.debug(TAG, "Setting physics body: " + physicsBody);
        view.setPhysicsBody(physicsBody);
    }
    
    @ReactProp(name = "viroTag")
    public void setViroTag(ViroSurfaceView view, @Nullable String viroTag) {
        ViroLog.debug(TAG, "Setting viro tag: " + viroTag);
        view.setViroTag(viroTag);
    }
    
    // Interaction Properties
    @ReactProp(name = "highAccuracyEvents", defaultBoolean = false)
    public void setHighAccuracyEvents(ViroSurfaceView view, boolean highAccuracyEvents) {
        ViroLog.debug(TAG, "Setting high accuracy events: " + highAccuracyEvents);
        view.setHighAccuracyEvents(highAccuracyEvents);
    }
    
    @ReactProp(name = "onHover", defaultBoolean = false)
    public void setOnHover(ViroSurfaceView view, boolean onHover) {
        ViroLog.debug(TAG, "Setting on hover: " + onHover);
        view.setOnHover(onHover);
    }
    
    @ReactProp(name = "onClick", defaultBoolean = false)
    public void setOnClick(ViroSurfaceView view, boolean onClick) {
        ViroLog.debug(TAG, "Setting on click: " + onClick);
        view.setOnClick(onClick);
    }
    
    @ReactProp(name = "onTouch", defaultBoolean = false)
    public void setOnTouch(ViroSurfaceView view, boolean onTouch) {
        ViroLog.debug(TAG, "Setting on touch: " + onTouch);
        view.setOnTouch(onTouch);
    }
    
    @ReactProp(name = "onDrag", defaultBoolean = false)
    public void setOnDrag(ViroSurfaceView view, boolean onDrag) {
        ViroLog.debug(TAG, "Setting on drag: " + onDrag);
        view.setOnDrag(onDrag);
    }
    
    @ReactProp(name = "onPinch", defaultBoolean = false)
    public void setOnPinch(ViroSurfaceView view, boolean onPinch) {
        ViroLog.debug(TAG, "Setting on pinch: " + onPinch);
        view.setOnPinch(onPinch);
    }
    
    @ReactProp(name = "onRotate", defaultBoolean = false)
    public void setOnRotate(ViroSurfaceView view, boolean onRotate) {
        ViroLog.debug(TAG, "Setting on rotate: " + onRotate);
        view.setOnRotate(onRotate);
    }
    
    @ReactProp(name = "onFuse", defaultBoolean = false)
    public void setOnFuse(ViroSurfaceView view, boolean onFuse) {
        ViroLog.debug(TAG, "Setting on fuse: " + onFuse);
        view.setOnFuse(onFuse);
    }
    
    @ReactProp(name = "onCollision", defaultBoolean = false)
    public void setOnCollision(ViroSurfaceView view, boolean onCollision) {
        ViroLog.debug(TAG, "Setting on collision: " + onCollision);
        view.setOnCollision(onCollision);
    }
    
    // AR Plane Event Properties
    @ReactProp(name = "onPlaneDetected", defaultBoolean = false)
    public void setOnPlaneDetected(ViroSurfaceView view, boolean onPlaneDetected) {
        ViroLog.debug(TAG, "Setting on plane detected: " + onPlaneDetected);
        view.setOnPlaneDetected(onPlaneDetected);
    }
    
    @ReactProp(name = "onPlaneUpdated", defaultBoolean = false)
    public void setOnPlaneUpdated(ViroSurfaceView view, boolean onPlaneUpdated) {
        ViroLog.debug(TAG, "Setting on plane updated: " + onPlaneUpdated);
        view.setOnPlaneUpdated(onPlaneUpdated);
    }
    
    @ReactProp(name = "onPlaneRemoved", defaultBoolean = false)
    public void setOnPlaneRemoved(ViroSurfaceView view, boolean onPlaneRemoved) {
        ViroLog.debug(TAG, "Setting on plane removed: " + onPlaneRemoved);
        view.setOnPlaneRemoved(onPlaneRemoved);
    }
    
    @ReactProp(name = "onAnchorFound", defaultBoolean = false)
    public void setOnAnchorFound(ViroSurfaceView view, boolean onAnchorFound) {
        ViroLog.debug(TAG, "Setting on anchor found: " + onAnchorFound);
        view.setOnAnchorFound(onAnchorFound);
    }
    
    @ReactProp(name = "onAnchorUpdated", defaultBoolean = false)
    public void setOnAnchorUpdated(ViroSurfaceView view, boolean onAnchorUpdated) {
        ViroLog.debug(TAG, "Setting on anchor updated: " + onAnchorUpdated);
        view.setOnAnchorUpdated(onAnchorUpdated);
    }
    
    @ReactProp(name = "onAnchorRemoved", defaultBoolean = false)
    public void setOnAnchorRemoved(ViroSurfaceView view, boolean onAnchorRemoved) {
        ViroLog.debug(TAG, "Setting on anchor removed: " + onAnchorRemoved);
        view.setOnAnchorRemoved(onAnchorRemoved);
    }
    
    // Lighting Properties
    @ReactProp(name = "lightReceivingBitMask", defaultInt = 1)
    public void setLightReceivingBitMask(ViroSurfaceView view, int lightReceivingBitMask) {
        ViroLog.debug(TAG, "Setting light receiving bit mask: " + lightReceivingBitMask);
        view.setLightReceivingBitMask(lightReceivingBitMask);
    }
    
    @ReactProp(name = "shadowCastingBitMask", defaultInt = 1)
    public void setShadowCastingBitMask(ViroSurfaceView view, int shadowCastingBitMask) {
        ViroLog.debug(TAG, "Setting shadow casting bit mask: " + shadowCastingBitMask);
        view.setShadowCastingBitMask(shadowCastingBitMask);
    }
    
    // Quality Properties
    @ReactProp(name = "ignoreEventHandling", defaultBoolean = false)
    public void setIgnoreEventHandling(ViroSurfaceView view, boolean ignoreEventHandling) {
        ViroLog.debug(TAG, "Setting ignore event handling: " + ignoreEventHandling);
        view.setIgnoreEventHandling(ignoreEventHandling);
    }
    
    @ReactProp(name = "dragType")
    public void setDragType(ViroSurfaceView view, @Nullable String dragType) {
        ViroLog.debug(TAG, "Setting drag type: " + dragType);
        view.setDragType(dragType);
    }
    
    @ReactProp(name = "dragPlane")
    public void setDragPlane(ViroSurfaceView view, @Nullable ReadableMap dragPlane) {
        ViroLog.debug(TAG, "Setting drag plane: " + dragPlane);
        view.setDragPlane(dragPlane);
    }
    
    // Event Exports
    @Override
    public java.util.Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return java.util.Map.of(
            "onHover", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onHover",
                    "captured", "onHoverCapture"
                )
            ),
            "onClick", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onClick",
                    "captured", "onClickCapture"
                )
            ),
            "onTouch", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onTouch",
                    "captured", "onTouchCapture"
                )
            ),
            "onDrag", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onDrag",
                    "captured", "onDragCapture"
                )
            ),
            "onPinch", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onPinch",
                    "captured", "onPinchCapture"
                )
            ),
            "onRotate", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onRotate",
                    "captured", "onRotateCapture"
                )
            ),
            "onFuse", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onFuse",
                    "captured", "onFuseCapture"
                )
            ),
            "onCollision", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onCollision",
                    "captured", "onCollisionCapture"
                )
            ),
            "onPlaneDetected", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onPlaneDetected",
                    "captured", "onPlaneDetectedCapture"
                )
            ),
            "onPlaneUpdated", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onPlaneUpdated",
                    "captured", "onPlaneUpdatedCapture"
                )
            ),
            "onPlaneRemoved", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onPlaneRemoved",
                    "captured", "onPlaneRemovedCapture"
                )
            ),
            "onAnchorFound", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onAnchorFound",
                    "captured", "onAnchorFoundCapture"
                )
            ),
            "onAnchorUpdated", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onAnchorUpdated",
                    "captured", "onAnchorUpdatedCapture"
                )
            ),
            "onAnchorRemoved", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onAnchorRemoved",
                    "captured", "onAnchorRemovedCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroSurfaceView view) {
        ViroLog.debug(TAG, "Dropping ViroSurfaceView instance");
        super.onDropViewInstance(view);
    }
}