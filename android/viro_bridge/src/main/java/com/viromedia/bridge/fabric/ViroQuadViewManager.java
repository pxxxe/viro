//
//  ViroQuadViewManager.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.ViroQuadManagerInterface;
import com.facebook.react.viewmanagers.ViroQuadManagerDelegate;

import com.viromedia.bridge.utility.ViroLog;

import java.util.Map;

/**
 * ViewManager for ViroQuad - 3D quad geometry in ViroReact
 * 
 * ViroQuad creates a flat rectangular surface in 3D space that can be textured,
 * lit, and transformed. It's commonly used for UI panels, billboards, ground planes,
 * and other flat surfaces that need to exist in 3D space.
 */
public class ViroQuadViewManager extends SimpleViewManager<ViroQuadView>
        implements ViroQuadManagerInterface<ViroQuadView> {

    public static final String REACT_CLASS = "ViroQuad";
    private final ViewManagerDelegate<ViroQuadView> mDelegate;

    public ViroQuadViewManager() {
        mDelegate = new ViroQuadManagerDelegate<>(this);
    }

    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    @NonNull
    public ViroQuadView createViewInstance(@NonNull ThemedReactContext context) {
        ViroLog.debug(REACT_CLASS, "Creating ViroQuad instance");
        return new ViroQuadView(context);
    }

    @Override
    public ViewManagerDelegate<ViroQuadView> getDelegate() {
        return mDelegate;
    }

    // Quad Geometry Properties
    @Override
    @ReactProp(name = "width", defaultFloat = 1.0f)
    public void setWidth(ViroQuadView view, float width) {
        ViroLog.debug(REACT_CLASS, "Setting width: " + width);
        view.setQuadWidth(width);
    }

    @Override
    @ReactProp(name = "height", defaultFloat = 1.0f)
    public void setHeight(ViroQuadView view, float height) {
        ViroLog.debug(REACT_CLASS, "Setting height: " + height);
        view.setQuadHeight(height);
    }

    @Override
    @ReactProp(name = "widthSegmentCount", defaultInt = 1)
    public void setWidthSegmentCount(ViroQuadView view, int widthSegmentCount) {
        ViroLog.debug(REACT_CLASS, "Setting width segment count: " + widthSegmentCount);
        view.setWidthSegmentCount(widthSegmentCount);
    }

    @Override
    @ReactProp(name = "heightSegmentCount", defaultInt = 1)
    public void setHeightSegmentCount(ViroQuadView view, int heightSegmentCount) {
        ViroLog.debug(REACT_CLASS, "Setting height segment count: " + heightSegmentCount);
        view.setHeightSegmentCount(heightSegmentCount);
    }

    // UV Mapping
    @Override
    @ReactProp(name = "uvCoordinates")
    public void setUvCoordinates(ViroQuadView view, @Nullable ReadableArray uvCoordinates) {
        ViroLog.debug(REACT_CLASS, "Setting UV coordinates: " + uvCoordinates);
        view.setUvCoordinates(uvCoordinates);
    }

    // Material Properties
    @Override
    @ReactProp(name = "materials")
    public void setMaterials(ViroQuadView view, @Nullable ReadableArray materials) {
        ViroLog.debug(REACT_CLASS, "Setting materials: " + materials);
        view.setMaterials(materials);
    }

    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onQuadGeometryUpdate", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onQuadGeometryUpdate")))
                .build();
    }
}