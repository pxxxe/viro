//
//  ViroQuadView.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import com.viromedia.bridge.utility.ViroLog;

import java.util.ArrayList;
import java.util.List;

/**
 * ViroQuad - 3D Quad Geometry
 * 
 * ViroQuad represents a flat rectangular surface in 3D space. It's one of the most
 * commonly used geometries for creating UI panels, billboards, ground planes, and
 * other flat surfaces that need precise texture mapping and positioning in 3D.
 * 
 * Key Features:
 * - Configurable width and height dimensions
 * - Mesh segmentation for displacement/normal mapping effects
 * - Custom UV coordinate mapping for texture control
 * - Material support for textures, colors, and lighting
 * - Efficient rendering with minimal geometry overhead
 */
public class ViroQuadView extends ViroNodeView {

    private static final String TAG = ViroLog.getTag(ViroQuadView.class);

    // Quad geometry properties
    private float mWidth = 1.0f;
    private float mHeight = 1.0f;
    private int mWidthSegmentCount = 1;
    private int mHeightSegmentCount = 1;

    // UV mapping coordinates
    private float[][] mUvCoordinates = {
        {0.0f, 1.0f}, // Bottom-left
        {1.0f, 1.0f}, // Bottom-right
        {1.0f, 0.0f}, // Top-right
        {0.0f, 0.0f}  // Top-left
    };

    // Material properties
    private ReadableArray mMaterials;

    public ViroQuadView(@NonNull ThemedReactContext context) {
        super(context);
        ViroLog.debug(TAG, "ViroQuadView initialized");
        
        // TODO: Initialize ViroReact quad geometry
        // This will need to integrate with the existing ViroReact quad implementation
        initializeQuadGeometry();
    }

    private void initializeQuadGeometry() {
        ViroLog.debug(TAG, "Initializing quad geometry with default properties");
        
        // TODO: Set up ViroReact quad with default properties
        // This should create the underlying 3D quad mesh
        updateQuadGeometry();
    }

    // Quad Geometry Properties
    public void setQuadWidth(float width) {
        ViroLog.debug(TAG, "Setting quad width: " + width);
        mWidth = width;
        updateQuadGeometry();
    }

    public void setQuadHeight(float height) {
        ViroLog.debug(TAG, "Setting quad height: " + height);
        mHeight = height;
        updateQuadGeometry();
    }

    public void setWidthSegmentCount(int widthSegmentCount) {
        ViroLog.debug(TAG, "Setting width segment count: " + widthSegmentCount);
        mWidthSegmentCount = Math.max(1, widthSegmentCount);
        updateQuadGeometry();
    }

    public void setHeightSegmentCount(int heightSegmentCount) {
        ViroLog.debug(TAG, "Setting height segment count: " + heightSegmentCount);
        mHeightSegmentCount = Math.max(1, heightSegmentCount);
        updateQuadGeometry();
    }

    private void updateQuadGeometry() {
        ViroLog.debug(TAG, String.format(
            "Updating quad geometry: %.2f x %.2f, segments: %dx%d",
            mWidth, mHeight, mWidthSegmentCount, mHeightSegmentCount));
        
        // TODO: Apply quad parameters to ViroReact renderer
        // This should update the underlying 3D quad mesh with new dimensions and segments
        // - width/height: size of the quad
        // - widthSegmentCount/heightSegmentCount: mesh resolution for displacement/normal mapping
        
        // Emit geometry update event for React Native
        emitQuadGeometryUpdateEvent();
    }

    // UV Mapping
    public void setUvCoordinates(@Nullable ReadableArray uvCoordinates) {
        ViroLog.debug(TAG, "Setting UV coordinates: " + uvCoordinates);
        
        if (uvCoordinates != null && uvCoordinates.size() >= 4) {
            parseUvCoordinates(uvCoordinates);
            updateQuadUVMapping();
        } else {
            // Reset to default UV coordinates
            mUvCoordinates = new float[][] {
                {0.0f, 1.0f}, // Bottom-left
                {1.0f, 1.0f}, // Bottom-right
                {1.0f, 0.0f}, // Top-right
                {0.0f, 0.0f}  // Top-left
            };
            updateQuadUVMapping();
        }
    }

    private void parseUvCoordinates(@NonNull ReadableArray uvArray) {
        try {
            int coordinateCount = Math.min(4, uvArray.size());
            mUvCoordinates = new float[4][2];
            
            for (int i = 0; i < coordinateCount; i++) {
                ReadableArray uvPair = uvArray.getArray(i);
                if (uvPair != null && uvPair.size() >= 2) {
                    mUvCoordinates[i][0] = (float) uvPair.getDouble(0); // U coordinate
                    mUvCoordinates[i][1] = (float) uvPair.getDouble(1); // V coordinate
                } else {
                    // Fallback to default coordinate for this corner
                    switch (i) {
                        case 0: mUvCoordinates[i] = new float[]{0.0f, 1.0f}; break; // Bottom-left
                        case 1: mUvCoordinates[i] = new float[]{1.0f, 1.0f}; break; // Bottom-right
                        case 2: mUvCoordinates[i] = new float[]{1.0f, 0.0f}; break; // Top-right
                        case 3: mUvCoordinates[i] = new float[]{0.0f, 0.0f}; break; // Top-left
                    }
                }
            }
            
            // Fill remaining coordinates with defaults if needed
            for (int i = coordinateCount; i < 4; i++) {
                switch (i) {
                    case 0: mUvCoordinates[i] = new float[]{0.0f, 1.0f}; break;
                    case 1: mUvCoordinates[i] = new float[]{1.0f, 1.0f}; break;
                    case 2: mUvCoordinates[i] = new float[]{1.0f, 0.0f}; break;
                    case 3: mUvCoordinates[i] = new float[]{0.0f, 0.0f}; break;
                }
            }
        } catch (Exception e) {
            ViroLog.error(TAG, "Error parsing UV coordinates: " + e.getMessage());
            // Reset to defaults on error
            setUvCoordinates(null);
        }
    }

    private void updateQuadUVMapping() {
        ViroLog.debug(TAG, "Updating quad UV mapping");
        
        // TODO: Apply UV coordinates to ViroReact renderer
        // This affects how textures and materials are applied to the quad surface
        // UV coordinates define the mapping from 2D texture space to 3D quad surface
    }

    // Material Properties
    public void setMaterials(@Nullable ReadableArray materials) {
        ViroLog.debug(TAG, "Setting materials: " + materials);
        mMaterials = materials;
        
        // TODO: Apply materials to ViroReact quad
        // Quads typically use a single material, but can support front/back materials
    }

    // Helper Methods
    public List<Float> getUvCoordinatesFlattened() {
        // Convert 2D UV array to flat list for renderer
        List<Float> flattened = new ArrayList<>();
        for (float[] uvPair : mUvCoordinates) {
            flattened.add(uvPair[0]); // U coordinate
            flattened.add(uvPair[1]); // V coordinate
        }
        return flattened;
    }

    private void emitQuadGeometryUpdateEvent() {
        WritableMap event = Arguments.createMap();
        event.putDouble("width", mWidth);
        event.putDouble("height", mHeight);
        event.putInt("widthSegmentCount", mWidthSegmentCount);
        event.putInt("heightSegmentCount", mHeightSegmentCount);
        
        ThemedReactContext reactContext = (ThemedReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onQuadGeometryUpdate", event);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        
        if (changed) {
            ViroLog.debug(TAG, String.format("Quad layout changed: %dx%d", 
                right - left, bottom - top));
            
            // For 3D geometry, layout is handled by 3D transforms and geometry properties
            // The 2D bounds don't directly affect the 3D quad dimensions
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViroLog.debug(TAG, "Quad attached to window");
        
        // TODO: Add quad to ViroReact scene when attached to window
        updateQuadGeometry();
        updateQuadUVMapping();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViroLog.debug(TAG, "Quad detached from window");
        
        // TODO: Remove quad from ViroReact scene when detached from window
    }

    // Getters for current values (useful for debugging and testing)
    public float getQuadWidth() { return mWidth; }
    public float getQuadHeight() { return mHeight; }
    public int getWidthSegmentCount() { return mWidthSegmentCount; }
    public int getHeightSegmentCount() { return mHeightSegmentCount; }
    public float[][] getUvCoordinates() { return mUvCoordinates; }
}