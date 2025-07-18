//
//  ViroGeometryViewManager.java
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
 * ViroGeometryViewManager - Custom Geometry ViewManager
 * 
 * This ViewManager handles custom geometry creation and rendering in ViroReact.
 * It provides comprehensive geometry manipulation capabilities including vertex
 * data, normals, texture coordinates, materials, and advanced features like
 * subdivision surfaces and parametric geometry generation.
 * 
 * Key Features:
 * - Custom vertex, normal, and texture coordinate data
 * - Material assignment and morph target support
 * - Procedural geometry generation (sphere, box, cylinder)
 * - Parametric surface generation
 * - Subdivision surface algorithms
 * - Lighting and rendering property control
 * - Bounding box computation and optimization
 * - Event callbacks for geometry lifecycle
 * - Integration with ViroReact scene graph
 */
public class ViroGeometryViewManager extends SimpleViewManager<ViroGeometryView> {
    
    private static final String TAG = ViroLog.getTag(ViroGeometryViewManager.class);
    private static final String REACT_CLASS = "ViroGeometry";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public ViroGeometryView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating ViroGeometryView instance");
        return new ViroGeometryView(reactContext);
    }
    
    // Geometry Definition
    @ReactProp(name = "vertices")
    public void setVertices(ViroGeometryView view, @Nullable ReadableArray vertices) {
        ViroLog.debug(TAG, "Setting vertices: " + (vertices != null ? vertices.size() : 0));
        view.setVertices(vertices);
    }
    
    @ReactProp(name = "normals")
    public void setNormals(ViroGeometryView view, @Nullable ReadableArray normals) {
        ViroLog.debug(TAG, "Setting normals: " + (normals != null ? normals.size() : 0));
        view.setNormals(normals);
    }
    
    @ReactProp(name = "texcoords")
    public void setTexcoords(ViroGeometryView view, @Nullable ReadableArray texcoords) {
        ViroLog.debug(TAG, "Setting texcoords: " + (texcoords != null ? texcoords.size() : 0));
        view.setTexcoords(texcoords);
    }
    
    @ReactProp(name = "triangleIndices")
    public void setTriangleIndices(ViroGeometryView view, @Nullable ReadableArray triangleIndices) {
        ViroLog.debug(TAG, "Setting triangle indices: " + (triangleIndices != null ? triangleIndices.size() : 0));
        view.setTriangleIndices(triangleIndices);
    }
    
    // Geometry Properties
    @ReactProp(name = "materials")
    public void setMaterials(ViroGeometryView view, @Nullable ReadableArray materials) {
        ViroLog.debug(TAG, "Setting materials: " + materials);
        view.setMaterials(materials);
    }
    
    @ReactProp(name = "morphTargets")
    public void setMorphTargets(ViroGeometryView view, @Nullable ReadableArray morphTargets) {
        ViroLog.debug(TAG, "Setting morph targets: " + morphTargets);
        view.setMorphTargets(morphTargets);
    }
    
    // Geometry Configuration
    @ReactProp(name = "geometryType")
    public void setGeometryType(ViroGeometryView view, @Nullable String geometryType) {
        ViroLog.debug(TAG, "Setting geometry type: " + geometryType);
        view.setGeometryType(geometryType);
    }
    
    @ReactProp(name = "primitiveType")
    public void setPrimitiveType(ViroGeometryView view, @Nullable String primitiveType) {
        ViroLog.debug(TAG, "Setting primitive type: " + primitiveType);
        view.setPrimitiveType(primitiveType);
    }
    
    @ReactProp(name = "vertexFormat")
    public void setVertexFormat(ViroGeometryView view, @Nullable String vertexFormat) {
        ViroLog.debug(TAG, "Setting vertex format: " + vertexFormat);
        view.setVertexFormat(vertexFormat);
    }
    
    // Subdivision Properties
    @ReactProp(name = "subdivisionLevel", defaultInt = 0)
    public void setSubdivisionLevel(ViroGeometryView view, int subdivisionLevel) {
        ViroLog.debug(TAG, "Setting subdivision level: " + subdivisionLevel);
        view.setSubdivisionLevel(subdivisionLevel);
    }
    
    @ReactProp(name = "subdivisionType")
    public void setSubdivisionType(ViroGeometryView view, @Nullable String subdivisionType) {
        ViroLog.debug(TAG, "Setting subdivision type: " + subdivisionType);
        view.setSubdivisionType(subdivisionType);
    }
    
    // Lighting Properties
    @ReactProp(name = "lightReceivingBitMask", defaultInt = 1)
    public void setLightReceivingBitMask(ViroGeometryView view, int lightReceivingBitMask) {
        ViroLog.debug(TAG, "Setting light receiving bit mask: " + lightReceivingBitMask);
        view.setLightReceivingBitMask(lightReceivingBitMask);
    }
    
    @ReactProp(name = "shadowCastingBitMask", defaultInt = 1)
    public void setShadowCastingBitMask(ViroGeometryView view, int shadowCastingBitMask) {
        ViroLog.debug(TAG, "Setting shadow casting bit mask: " + shadowCastingBitMask);
        view.setShadowCastingBitMask(shadowCastingBitMask);
    }
    
    // Rendering Properties
    @ReactProp(name = "cullMode")
    public void setCullMode(ViroGeometryView view, @Nullable String cullMode) {
        ViroLog.debug(TAG, "Setting cull mode: " + cullMode);
        view.setCullMode(cullMode);
    }
    
    @ReactProp(name = "blendMode")
    public void setBlendMode(ViroGeometryView view, @Nullable String blendMode) {
        ViroLog.debug(TAG, "Setting blend mode: " + blendMode);
        view.setBlendMode(blendMode);
    }
    
    @ReactProp(name = "transparencyMode")
    public void setTransparencyMode(ViroGeometryView view, @Nullable String transparencyMode) {
        ViroLog.debug(TAG, "Setting transparency mode: " + transparencyMode);
        view.setTransparencyMode(transparencyMode);
    }
    
    // Bounding Box
    @ReactProp(name = "boundingBoxMin")
    public void setBoundingBoxMin(ViroGeometryView view, @Nullable ReadableArray boundingBoxMin) {
        ViroLog.debug(TAG, "Setting bounding box min: " + boundingBoxMin);
        view.setBoundingBoxMin(boundingBoxMin);
    }
    
    @ReactProp(name = "boundingBoxMax")
    public void setBoundingBoxMax(ViroGeometryView view, @Nullable ReadableArray boundingBoxMax) {
        ViroLog.debug(TAG, "Setting bounding box max: " + boundingBoxMax);
        view.setBoundingBoxMax(boundingBoxMax);
    }
    
    // Geometry Generation
    @ReactProp(name = "sphereRadius", defaultFloat = 0.5f)
    public void setSphereRadius(ViroGeometryView view, float sphereRadius) {
        ViroLog.debug(TAG, "Setting sphere radius: " + sphereRadius);
        view.setSphereRadius(sphereRadius);
    }
    
    @ReactProp(name = "sphereSegments", defaultInt = 20)
    public void setSphereSegments(ViroGeometryView view, int sphereSegments) {
        ViroLog.debug(TAG, "Setting sphere segments: " + sphereSegments);
        view.setSphereSegments(sphereSegments);
    }
    
    @ReactProp(name = "boxWidth", defaultFloat = 1.0f)
    public void setBoxWidth(ViroGeometryView view, float boxWidth) {
        ViroLog.debug(TAG, "Setting box width: " + boxWidth);
        view.setBoxWidth(boxWidth);
    }
    
    @ReactProp(name = "boxHeight", defaultFloat = 1.0f)
    public void setBoxHeight(ViroGeometryView view, float boxHeight) {
        ViroLog.debug(TAG, "Setting box height: " + boxHeight);
        view.setBoxHeight(boxHeight);
    }
    
    @ReactProp(name = "boxLength", defaultFloat = 1.0f)
    public void setBoxLength(ViroGeometryView view, float boxLength) {
        ViroLog.debug(TAG, "Setting box length: " + boxLength);
        view.setBoxLength(boxLength);
    }
    
    @ReactProp(name = "cylinderRadius", defaultFloat = 0.5f)
    public void setCylinderRadius(ViroGeometryView view, float cylinderRadius) {
        ViroLog.debug(TAG, "Setting cylinder radius: " + cylinderRadius);
        view.setCylinderRadius(cylinderRadius);
    }
    
    @ReactProp(name = "cylinderHeight", defaultFloat = 1.0f)
    public void setCylinderHeight(ViroGeometryView view, float cylinderHeight) {
        ViroLog.debug(TAG, "Setting cylinder height: " + cylinderHeight);
        view.setCylinderHeight(cylinderHeight);
    }
    
    @ReactProp(name = "cylinderSegments", defaultInt = 12)
    public void setCylinderSegments(ViroGeometryView view, int cylinderSegments) {
        ViroLog.debug(TAG, "Setting cylinder segments: " + cylinderSegments);
        view.setCylinderSegments(cylinderSegments);
    }
    
    // Parametric Surface
    @ReactProp(name = "parametricSurface")
    public void setParametricSurface(ViroGeometryView view, @Nullable ReadableMap parametricSurface) {
        ViroLog.debug(TAG, "Setting parametric surface: " + parametricSurface);
        view.setParametricSurface(parametricSurface);
    }
    
    @ReactProp(name = "uSubdivisions", defaultInt = 10)
    public void setUSubdivisions(ViroGeometryView view, int uSubdivisions) {
        ViroLog.debug(TAG, "Setting U subdivisions: " + uSubdivisions);
        view.setUSubdivisions(uSubdivisions);
    }
    
    @ReactProp(name = "vSubdivisions", defaultInt = 10)
    public void setVSubdivisions(ViroGeometryView view, int vSubdivisions) {
        ViroLog.debug(TAG, "Setting V subdivisions: " + vSubdivisions);
        view.setVSubdivisions(vSubdivisions);
    }
    
    // Event Exports
    @Override
    public java.util.Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return java.util.Map.of(
            "onGeometryError", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onGeometryError",
                    "captured", "onGeometryErrorCapture"
                )
            ),
            "onGeometryReady", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onGeometryReady",
                    "captured", "onGeometryReadyCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroGeometryView view) {
        ViroLog.debug(TAG, "Dropping ViroGeometryView instance");
        super.onDropViewInstance(view);
    }
}