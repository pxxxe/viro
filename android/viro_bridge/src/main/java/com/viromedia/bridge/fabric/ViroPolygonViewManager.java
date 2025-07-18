//
//  ViroPolygonViewManager.java
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
 * ViroPolygonViewManager - Custom Polygon Geometry ViewManager
 * 
 * This ViewManager handles custom polygon geometry creation for ViroReact applications.
 * It provides comprehensive polygon rendering capabilities including holes, tessellation,
 * and material support for complex 2D and 3D polygon shapes.
 * 
 * Key Features:
 * - Custom polygon vertex definition
 * - Hole support for complex shapes
 * - Automatic tessellation and triangulation
 * - UV coordinate generation
 * - Normal calculation and custom normals
 * - Thickness/extrusion support
 * - Multi-material support
 * - Vertex coloring
 * - Touch interaction
 * - Performance optimized rendering
 */
public class ViroPolygonViewManager extends SimpleViewManager<ViroPolygonView> {
    
    private static final String TAG = ViroLog.getTag(ViroPolygonViewManager.class);
    private static final String REACT_CLASS = "ViroPolygon";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroPolygonView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroPolygonView instance");
        return new ViroPolygonView(reactContext);
    }
    
    // Polygon Geometry Properties
    @ReactProp(name = "vertices")
    public void setVertices(ViroPolygonView view, @Nullable ReadableArray vertices) {
        ViroLog.debug(TAG, "Setting vertices: " + vertices);
        view.setVertices(vertices);
    }
    
    @ReactProp(name = "holes")
    public void setHoles(ViroPolygonView view, @Nullable ReadableArray holes) {
        ViroLog.debug(TAG, "Setting holes: " + holes);
        view.setHoles(holes);
    }
    
    @ReactProp(name = "uvCoordinates")
    public void setUvCoordinates(ViroPolygonView view, @Nullable ReadableArray uvCoordinates) {
        ViroLog.debug(TAG, "Setting UV coordinates: " + uvCoordinates);
        view.setUvCoordinates(uvCoordinates);
    }
    
    @ReactProp(name = "normals")
    public void setNormals(ViroPolygonView view, @Nullable ReadableArray normals) {
        ViroLog.debug(TAG, "Setting normals: " + normals);
        view.setNormals(normals);
    }
    
    @ReactProp(name = "colors")
    public void setColors(ViroPolygonView view, @Nullable ReadableArray colors) {
        ViroLog.debug(TAG, "Setting colors: " + colors);
        view.setColors(colors);
    }
    
    @ReactProp(name = "thickness", defaultFloat = 0.0f)
    public void setThickness(ViroPolygonView view, float thickness) {
        ViroLog.debug(TAG, "Setting thickness: " + thickness);
        view.setThickness(thickness);
    }
    
    @ReactProp(name = "facesOutward", defaultBoolean = true)
    public void setFacesOutward(ViroPolygonView view, boolean facesOutward) {
        ViroLog.debug(TAG, "Setting faces outward: " + facesOutward);
        view.setFacesOutward(facesOutward);
    }
    
    // Tessellation Properties
    @ReactProp(name = "tessellationFactor", defaultInt = 1)
    public void setTessellationFactor(ViroPolygonView view, int tessellationFactor) {
        ViroLog.debug(TAG, "Setting tessellation factor: " + tessellationFactor);
        view.setTessellationFactor(tessellationFactor);
    }
    
    @ReactProp(name = "tessellationMode")
    public void setTessellationMode(ViroPolygonView view, @Nullable String tessellationMode) {
        ViroLog.debug(TAG, "Setting tessellation mode: " + tessellationMode);
        view.setTessellationMode(tessellationMode);
    }
    
    // Material Properties
    @ReactProp(name = "materials")
    public void setMaterials(ViroPolygonView view, @Nullable ReadableArray materials) {
        ViroLog.debug(TAG, "Setting materials: " + materials);
        view.setMaterials(materials);
    }
    
    // Rendering Properties
    @ReactProp(name = "visible", defaultBoolean = true)
    public void setVisible(ViroPolygonView view, boolean visible) {
        ViroLog.debug(TAG, "Setting visible: " + visible);
        view.setVisible(visible);
    }
    
    @ReactProp(name = "opacity", defaultFloat = 1.0f)
    public void setOpacity(ViroPolygonView view, float opacity) {
        ViroLog.debug(TAG, "Setting opacity: " + opacity);
        view.setOpacity(opacity);
    }
    
    @ReactProp(name = "renderingOrder", defaultInt = 0)
    public void setRenderingOrder(ViroPolygonView view, int renderingOrder) {
        ViroLog.debug(TAG, "Setting rendering order: " + renderingOrder);
        view.setRenderingOrder(renderingOrder);
    }
    
    // Transform Properties
    @ReactProp(name = "position")
    public void setPosition(ViroPolygonView view, @Nullable ReadableArray position) {
        ViroLog.debug(TAG, "Setting position: " + position);
        view.setPosition(position);
    }
    
    @ReactProp(name = "rotation")
    public void setRotation(ViroPolygonView view, @Nullable ReadableArray rotation) {
        ViroLog.debug(TAG, "Setting rotation: " + rotation);
        view.setRotation(rotation);
    }
    
    @ReactProp(name = "scale")
    public void setScale(ViroPolygonView view, @Nullable ReadableArray scale) {
        ViroLog.debug(TAG, "Setting scale: " + scale);
        view.setScale(scale);
    }
    
    @ReactProp(name = "transformBehaviors")
    public void setTransformBehaviors(ViroPolygonView view, @Nullable ReadableArray transformBehaviors) {
        ViroLog.debug(TAG, "Setting transform behaviors: " + transformBehaviors);
        view.setTransformBehaviors(transformBehaviors);
    }
    
    // Animation Properties
    @ReactProp(name = "animation")
    public void setAnimation(ViroPolygonView view, @Nullable ReadableMap animation) {
        ViroLog.debug(TAG, "Setting animation: " + animation);
        view.setAnimation(animation);
    }
    
    // Physics Properties
    @ReactProp(name = "physicsBody")
    public void setPhysicsBody(ViroPolygonView view, @Nullable ReadableMap physicsBody) {
        ViroLog.debug(TAG, "Setting physics body: " + physicsBody);
        view.setPhysicsBody(physicsBody);
    }
    
    @ReactProp(name = "viroTag")
    public void setViroTag(ViroPolygonView view, @Nullable String viroTag) {
        ViroLog.debug(TAG, "Setting viro tag: " + viroTag);
        view.setViroTag(viroTag);
    }
    
    // Interaction Properties
    @ReactProp(name = "highAccuracyEvents", defaultBoolean = false)
    public void setHighAccuracyEvents(ViroPolygonView view, boolean highAccuracyEvents) {
        ViroLog.debug(TAG, "Setting high accuracy events: " + highAccuracyEvents);
        view.setHighAccuracyEvents(highAccuracyEvents);
    }
    
    @ReactProp(name = "onHover", defaultBoolean = false)
    public void setOnHover(ViroPolygonView view, boolean onHover) {
        ViroLog.debug(TAG, "Setting on hover: " + onHover);
        view.setOnHover(onHover);
    }
    
    @ReactProp(name = "onClick", defaultBoolean = false)
    public void setOnClick(ViroPolygonView view, boolean onClick) {
        ViroLog.debug(TAG, "Setting on click: " + onClick);
        view.setOnClick(onClick);
    }
    
    @ReactProp(name = "onTouch", defaultBoolean = false)
    public void setOnTouch(ViroPolygonView view, boolean onTouch) {
        ViroLog.debug(TAG, "Setting on touch: " + onTouch);
        view.setOnTouch(onTouch);
    }
    
    @ReactProp(name = "onDrag", defaultBoolean = false)
    public void setOnDrag(ViroPolygonView view, boolean onDrag) {
        ViroLog.debug(TAG, "Setting on drag: " + onDrag);
        view.setOnDrag(onDrag);
    }
    
    @ReactProp(name = "onPinch", defaultBoolean = false)
    public void setOnPinch(ViroPolygonView view, boolean onPinch) {
        ViroLog.debug(TAG, "Setting on pinch: " + onPinch);
        view.setOnPinch(onPinch);
    }
    
    @ReactProp(name = "onRotate", defaultBoolean = false)
    public void setOnRotate(ViroPolygonView view, boolean onRotate) {
        ViroLog.debug(TAG, "Setting on rotate: " + onRotate);
        view.setOnRotate(onRotate);
    }
    
    @ReactProp(name = "onFuse", defaultBoolean = false)
    public void setOnFuse(ViroPolygonView view, boolean onFuse) {
        ViroLog.debug(TAG, "Setting on fuse: " + onFuse);
        view.setOnFuse(onFuse);
    }
    
    @ReactProp(name = "onCollision", defaultBoolean = false)
    public void setOnCollision(ViroPolygonView view, boolean onCollision) {
        ViroLog.debug(TAG, "Setting on collision: " + onCollision);
        view.setOnCollision(onCollision);
    }
    
    // Lighting Properties
    @ReactProp(name = "lightReceivingBitMask", defaultInt = 1)
    public void setLightReceivingBitMask(ViroPolygonView view, int lightReceivingBitMask) {
        ViroLog.debug(TAG, "Setting light receiving bit mask: " + lightReceivingBitMask);
        view.setLightReceivingBitMask(lightReceivingBitMask);
    }
    
    @ReactProp(name = "shadowCastingBitMask", defaultInt = 1)
    public void setShadowCastingBitMask(ViroPolygonView view, int shadowCastingBitMask) {
        ViroLog.debug(TAG, "Setting shadow casting bit mask: " + shadowCastingBitMask);
        view.setShadowCastingBitMask(shadowCastingBitMask);
    }
    
    // Quality Properties
    @ReactProp(name = "ignoreEventHandling", defaultBoolean = false)
    public void setIgnoreEventHandling(ViroPolygonView view, boolean ignoreEventHandling) {
        ViroLog.debug(TAG, "Setting ignore event handling: " + ignoreEventHandling);
        view.setIgnoreEventHandling(ignoreEventHandling);
    }
    
    @ReactProp(name = "dragType")
    public void setDragType(ViroPolygonView view, @Nullable String dragType) {
        ViroLog.debug(TAG, "Setting drag type: " + dragType);
        view.setDragType(dragType);
    }
    
    @ReactProp(name = "dragPlane")
    public void setDragPlane(ViroPolygonView view, @Nullable ReadableMap dragPlane) {
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
    public void onDropViewInstance(@NonNull ViroPolygonView view) {
        ViroLog.debug(TAG, "Dropping ViroPolygonView instance");
        super.onDropViewInstance(view);
    }
}