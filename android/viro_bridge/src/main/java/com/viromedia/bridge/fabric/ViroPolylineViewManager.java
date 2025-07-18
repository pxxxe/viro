//
//  ViroPolylineViewManager.java
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
 * ViroPolylineViewManager - Line Geometry ViewManager
 * 
 * This ViewManager handles polyline geometry creation for ViroReact applications.
 * It provides comprehensive line rendering capabilities including thickness, styling,
 * smoothing, and advanced line effects for both 2D and 3D line visualizations.
 * 
 * Key Features:
 * - Multi-point line definition with custom points
 * - Thickness control for volumetric lines
 * - Line styling (solid, dashed, dotted)
 * - Smooth line interpolation with curve support
 * - Closed/open polyline modes
 * - Line caps and joins (round, square, miter)
 * - Gradient and multi-color support
 * - UV mapping for texture application
 * - Touch interaction and event handling
 * - Performance optimization for complex lines
 */
public class ViroPolylineViewManager extends SimpleViewManager<ViroPolylineView> {
    
    private static final String TAG = ViroLog.getTag(ViroPolylineViewManager.class);
    private static final String REACT_CLASS = "ViroPolyline";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroPolylineView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroPolylineView instance");
        return new ViroPolylineView(reactContext);
    }
    
    // Polyline Geometry Properties
    @ReactProp(name = "points")
    public void setPoints(ViroPolylineView view, @Nullable ReadableArray points) {
        ViroLog.debug(TAG, "Setting points: " + points);
        view.setPoints(points);
    }
    
    @ReactProp(name = "thickness", defaultFloat = 0.01f)
    public void setThickness(ViroPolylineView view, float thickness) {
        ViroLog.debug(TAG, "Setting thickness: " + thickness);
        view.setThickness(thickness);
    }
    
    @ReactProp(name = "colors")
    public void setColors(ViroPolylineView view, @Nullable ReadableArray colors) {
        ViroLog.debug(TAG, "Setting colors: " + colors);
        view.setColors(colors);
    }
    
    @ReactProp(name = "closed", defaultBoolean = false)
    public void setClosed(ViroPolylineView view, boolean closed) {
        ViroLog.debug(TAG, "Setting closed: " + closed);
        view.setClosed(closed);
    }
    
    // Line Style Properties
    @ReactProp(name = "lineType")
    public void setLineType(ViroPolylineView view, @Nullable String lineType) {
        ViroLog.debug(TAG, "Setting line type: " + lineType);
        view.setLineType(lineType);
    }
    
    @ReactProp(name = "dashLength", defaultFloat = 0.1f)
    public void setDashLength(ViroPolylineView view, float dashLength) {
        ViroLog.debug(TAG, "Setting dash length: " + dashLength);
        view.setDashLength(dashLength);
    }
    
    @ReactProp(name = "gapLength", defaultFloat = 0.05f)
    public void setGapLength(ViroPolylineView view, float gapLength) {
        ViroLog.debug(TAG, "Setting gap length: " + gapLength);
        view.setGapLength(gapLength);
    }
    
    @ReactProp(name = "capType")
    public void setCapType(ViroPolylineView view, @Nullable String capType) {
        ViroLog.debug(TAG, "Setting cap type: " + capType);
        view.setCapType(capType);
    }
    
    @ReactProp(name = "joinType")
    public void setJoinType(ViroPolylineView view, @Nullable String joinType) {
        ViroLog.debug(TAG, "Setting join type: " + joinType);
        view.setJoinType(joinType);
    }
    
    // Segment Properties
    @ReactProp(name = "segments", defaultInt = 10)
    public void setSegments(ViroPolylineView view, int segments) {
        ViroLog.debug(TAG, "Setting segments: " + segments);
        view.setSegments(segments);
    }
    
    @ReactProp(name = "smooth", defaultBoolean = false)
    public void setSmooth(ViroPolylineView view, boolean smooth) {
        ViroLog.debug(TAG, "Setting smooth: " + smooth);
        view.setSmooth(smooth);
    }
    
    @ReactProp(name = "smoothness", defaultFloat = 0.5f)
    public void setSmoothness(ViroPolylineView view, float smoothness) {
        ViroLog.debug(TAG, "Setting smoothness: " + smoothness);
        view.setSmoothness(smoothness);
    }
    
    // Material Properties
    @ReactProp(name = "materials")
    public void setMaterials(ViroPolylineView view, @Nullable ReadableArray materials) {
        ViroLog.debug(TAG, "Setting materials: " + materials);
        view.setMaterials(materials);
    }
    
    // Rendering Properties
    @ReactProp(name = "visible", defaultBoolean = true)
    public void setVisible(ViroPolylineView view, boolean visible) {
        ViroLog.debug(TAG, "Setting visible: " + visible);
        view.setVisible(visible);
    }
    
    @ReactProp(name = "opacity", defaultFloat = 1.0f)
    public void setOpacity(ViroPolylineView view, float opacity) {
        ViroLog.debug(TAG, "Setting opacity: " + opacity);
        view.setOpacity(opacity);
    }
    
    @ReactProp(name = "renderingOrder", defaultInt = 0)
    public void setRenderingOrder(ViroPolylineView view, int renderingOrder) {
        ViroLog.debug(TAG, "Setting rendering order: " + renderingOrder);
        view.setRenderingOrder(renderingOrder);
    }
    
    // Transform Properties
    @ReactProp(name = "position")
    public void setPosition(ViroPolylineView view, @Nullable ReadableArray position) {
        ViroLog.debug(TAG, "Setting position: " + position);
        view.setPosition(position);
    }
    
    @ReactProp(name = "rotation")
    public void setRotation(ViroPolylineView view, @Nullable ReadableArray rotation) {
        ViroLog.debug(TAG, "Setting rotation: " + rotation);
        view.setRotation(rotation);
    }
    
    @ReactProp(name = "scale")
    public void setScale(ViroPolylineView view, @Nullable ReadableArray scale) {
        ViroLog.debug(TAG, "Setting scale: " + scale);
        view.setScale(scale);
    }
    
    @ReactProp(name = "transformBehaviors")
    public void setTransformBehaviors(ViroPolylineView view, @Nullable ReadableArray transformBehaviors) {
        ViroLog.debug(TAG, "Setting transform behaviors: " + transformBehaviors);
        view.setTransformBehaviors(transformBehaviors);
    }
    
    // Animation Properties
    @ReactProp(name = "animation")
    public void setAnimation(ViroPolylineView view, @Nullable ReadableMap animation) {
        ViroLog.debug(TAG, "Setting animation: " + animation);
        view.setAnimation(animation);
    }
    
    // Physics Properties
    @ReactProp(name = "physicsBody")
    public void setPhysicsBody(ViroPolylineView view, @Nullable ReadableMap physicsBody) {
        ViroLog.debug(TAG, "Setting physics body: " + physicsBody);
        view.setPhysicsBody(physicsBody);
    }
    
    @ReactProp(name = "viroTag")
    public void setViroTag(ViroPolylineView view, @Nullable String viroTag) {
        ViroLog.debug(TAG, "Setting viro tag: " + viroTag);
        view.setViroTag(viroTag);
    }
    
    // Interaction Properties
    @ReactProp(name = "highAccuracyEvents", defaultBoolean = false)
    public void setHighAccuracyEvents(ViroPolylineView view, boolean highAccuracyEvents) {
        ViroLog.debug(TAG, "Setting high accuracy events: " + highAccuracyEvents);
        view.setHighAccuracyEvents(highAccuracyEvents);
    }
    
    @ReactProp(name = "onHover", defaultBoolean = false)
    public void setOnHover(ViroPolylineView view, boolean onHover) {
        ViroLog.debug(TAG, "Setting on hover: " + onHover);
        view.setOnHover(onHover);
    }
    
    @ReactProp(name = "onClick", defaultBoolean = false)
    public void setOnClick(ViroPolylineView view, boolean onClick) {
        ViroLog.debug(TAG, "Setting on click: " + onClick);
        view.setOnClick(onClick);
    }
    
    @ReactProp(name = "onTouch", defaultBoolean = false)
    public void setOnTouch(ViroPolylineView view, boolean onTouch) {
        ViroLog.debug(TAG, "Setting on touch: " + onTouch);
        view.setOnTouch(onTouch);
    }
    
    @ReactProp(name = "onDrag", defaultBoolean = false)
    public void setOnDrag(ViroPolylineView view, boolean onDrag) {
        ViroLog.debug(TAG, "Setting on drag: " + onDrag);
        view.setOnDrag(onDrag);
    }
    
    @ReactProp(name = "onPinch", defaultBoolean = false)
    public void setOnPinch(ViroPolylineView view, boolean onPinch) {
        ViroLog.debug(TAG, "Setting on pinch: " + onPinch);
        view.setOnPinch(onPinch);
    }
    
    @ReactProp(name = "onRotate", defaultBoolean = false)
    public void setOnRotate(ViroPolylineView view, boolean onRotate) {
        ViroLog.debug(TAG, "Setting on rotate: " + onRotate);
        view.setOnRotate(onRotate);
    }
    
    @ReactProp(name = "onFuse", defaultBoolean = false)
    public void setOnFuse(ViroPolylineView view, boolean onFuse) {
        ViroLog.debug(TAG, "Setting on fuse: " + onFuse);
        view.setOnFuse(onFuse);
    }
    
    @ReactProp(name = "onCollision", defaultBoolean = false)
    public void setOnCollision(ViroPolylineView view, boolean onCollision) {
        ViroLog.debug(TAG, "Setting on collision: " + onCollision);
        view.setOnCollision(onCollision);
    }
    
    // Lighting Properties
    @ReactProp(name = "lightReceivingBitMask", defaultInt = 1)
    public void setLightReceivingBitMask(ViroPolylineView view, int lightReceivingBitMask) {
        ViroLog.debug(TAG, "Setting light receiving bit mask: " + lightReceivingBitMask);
        view.setLightReceivingBitMask(lightReceivingBitMask);
    }
    
    @ReactProp(name = "shadowCastingBitMask", defaultInt = 1)
    public void setShadowCastingBitMask(ViroPolylineView view, int shadowCastingBitMask) {
        ViroLog.debug(TAG, "Setting shadow casting bit mask: " + shadowCastingBitMask);
        view.setShadowCastingBitMask(shadowCastingBitMask);
    }
    
    // Quality Properties
    @ReactProp(name = "ignoreEventHandling", defaultBoolean = false)
    public void setIgnoreEventHandling(ViroPolylineView view, boolean ignoreEventHandling) {
        ViroLog.debug(TAG, "Setting ignore event handling: " + ignoreEventHandling);
        view.setIgnoreEventHandling(ignoreEventHandling);
    }
    
    @ReactProp(name = "dragType")
    public void setDragType(ViroPolylineView view, @Nullable String dragType) {
        ViroLog.debug(TAG, "Setting drag type: " + dragType);
        view.setDragType(dragType);
    }
    
    @ReactProp(name = "dragPlane")
    public void setDragPlane(ViroPolylineView view, @Nullable ReadableMap dragPlane) {
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
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroPolylineView view) {
        ViroLog.debug(TAG, "Dropping ViroPolylineView instance");
        super.onDropViewInstance(view);
    }
}