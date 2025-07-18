//
//  ViroFlexViewViewManager.java
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
import com.facebook.react.viewmanagers.ViroFlexViewManagerInterface;
import com.facebook.react.viewmanagers.ViroFlexViewManagerDelegate;

import com.viromedia.bridge.utility.ViroLog;

import java.util.Map;

/**
 * ViewManager for ViroFlexView - 3D flexbox container in ViroReact
 * 
 * ViroFlexView provides Flexbox layout capabilities in 3D space, allowing developers
 * to create complex 3D layouts using familiar CSS flexbox properties. It acts as a
 * container that positions its children according to flexbox rules in 3D coordinates.
 */
public class ViroFlexViewViewManager extends SimpleViewManager<ViroFlexViewView>
        implements ViroFlexViewManagerInterface<ViroFlexViewView> {

    public static final String REACT_CLASS = "ViroFlexView";
    private final ViewManagerDelegate<ViroFlexViewView> mDelegate;

    public ViroFlexViewViewManager() {
        mDelegate = new ViroFlexViewManagerDelegate<>(this);
    }

    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    @NonNull
    public ViroFlexViewView createViewInstance(@NonNull ThemedReactContext context) {
        ViroLog.debug(REACT_CLASS, "Creating ViroFlexView instance");
        return new ViroFlexViewView(context);
    }

    @Override
    public ViewManagerDelegate<ViroFlexViewView> getDelegate() {
        return mDelegate;
    }

    // FlexView Layout Properties
    @Override
    @ReactProp(name = "width", defaultFloat = 1.0f)
    public void setWidth(ViroFlexViewView view, float width) {
        ViroLog.debug(REACT_CLASS, "Setting width: " + width);
        view.setFlexWidth(width);
    }

    @Override
    @ReactProp(name = "height", defaultFloat = 1.0f)
    public void setHeight(ViroFlexViewView view, float height) {
        ViroLog.debug(REACT_CLASS, "Setting height: " + height);
        view.setFlexHeight(height);
    }

    @Override
    @ReactProp(name = "flexDirection")
    public void setFlexDirection(ViroFlexViewView view, @Nullable String flexDirection) {
        ViroLog.debug(REACT_CLASS, "Setting flex direction: " + flexDirection);
        view.setFlexDirection(flexDirection != null ? flexDirection : "column");
    }

    @Override
    @ReactProp(name = "justifyContent")
    public void setJustifyContent(ViroFlexViewView view, @Nullable String justifyContent) {
        ViroLog.debug(REACT_CLASS, "Setting justify content: " + justifyContent);
        view.setJustifyContent(justifyContent != null ? justifyContent : "flex-start");
    }

    @Override
    @ReactProp(name = "alignItems")
    public void setAlignItems(ViroFlexViewView view, @Nullable String alignItems) {
        ViroLog.debug(REACT_CLASS, "Setting align items: " + alignItems);
        view.setAlignItems(alignItems != null ? alignItems : "stretch");
    }

    @Override
    @ReactProp(name = "alignContent")
    public void setAlignContent(ViroFlexViewView view, @Nullable String alignContent) {
        ViroLog.debug(REACT_CLASS, "Setting align content: " + alignContent);
        view.setAlignContent(alignContent != null ? alignContent : "stretch");
    }

    @Override
    @ReactProp(name = "flexWrap")
    public void setFlexWrap(ViroFlexViewView view, @Nullable String flexWrap) {
        ViroLog.debug(REACT_CLASS, "Setting flex wrap: " + flexWrap);
        view.setFlexWrap(flexWrap != null ? flexWrap : "nowrap");
    }

    // Individual Flex Item Properties
    @Override
    @ReactProp(name = "flex", defaultFloat = 0.0f)
    public void setFlex(ViroFlexViewView view, float flex) {
        ViroLog.debug(REACT_CLASS, "Setting flex: " + flex);
        view.setFlex(flex);
    }

    @Override
    @ReactProp(name = "flexGrow", defaultFloat = 0.0f)
    public void setFlexGrow(ViroFlexViewView view, float flexGrow) {
        ViroLog.debug(REACT_CLASS, "Setting flex grow: " + flexGrow);
        view.setFlexGrow(flexGrow);
    }

    @Override
    @ReactProp(name = "flexShrink", defaultFloat = 1.0f)
    public void setFlexShrink(ViroFlexViewView view, float flexShrink) {
        ViroLog.debug(REACT_CLASS, "Setting flex shrink: " + flexShrink);
        view.setFlexShrink(flexShrink);
    }

    @Override
    @ReactProp(name = "flexBasis", defaultFloat = 0.0f)
    public void setFlexBasis(ViroFlexViewView view, float flexBasis) {
        ViroLog.debug(REACT_CLASS, "Setting flex basis: " + flexBasis);
        view.setFlexBasis(flexBasis);
    }

    @Override
    @ReactProp(name = "alignSelf")
    public void setAlignSelf(ViroFlexViewView view, @Nullable String alignSelf) {
        ViroLog.debug(REACT_CLASS, "Setting align self: " + alignSelf);
        view.setAlignSelf(alignSelf != null ? alignSelf : "auto");
    }

    // Margin and Padding
    @Override
    @ReactProp(name = "margin")
    public void setMargin(ViroFlexViewView view, @Nullable ReadableArray margin) {
        ViroLog.debug(REACT_CLASS, "Setting margin: " + margin);
        view.setMargin(margin);
    }

    @Override
    @ReactProp(name = "padding")
    public void setPadding(ViroFlexViewView view, @Nullable ReadableArray padding) {
        ViroLog.debug(REACT_CLASS, "Setting padding: " + padding);
        view.setPadding(padding);
    }

    // Material Properties
    @Override
    @ReactProp(name = "materials")
    public void setMaterials(ViroFlexViewView view, @Nullable ReadableArray materials) {
        ViroLog.debug(REACT_CLASS, "Setting materials: " + materials);
        view.setMaterials(materials);
    }

    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onFlexLayoutUpdate", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onFlexLayoutUpdate")))
                .build();
    }
}