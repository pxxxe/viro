//
//  ViroFlexViewView.java
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

/**
 * ViroFlexView - 3D Flexbox Container
 * 
 * ViroFlexView provides flexbox layout capabilities in 3D space, allowing developers
 * to arrange 3D components using familiar CSS flexbox properties. It extends the base
 * ViroNode functionality to include flex layout calculations and positioning.
 * 
 * Key Features:
 * - Full flexbox layout support (direction, justify, align, wrap)
 * - 3D space layout calculations
 * - Margin and padding support
 * - Flexible sizing and positioning of child components
 * - Integration with Yoga layout engine for 3D
 */
public class ViroFlexViewView extends ViroNodeView {

    private static final String TAG = ViroLog.getTag(ViroFlexViewView.class);

    // FlexView layout properties
    private float mWidth = 1.0f;
    private float mHeight = 1.0f;
    private String mFlexDirection = "column";
    private String mJustifyContent = "flex-start";
    private String mAlignItems = "stretch";
    private String mAlignContent = "stretch";
    private String mFlexWrap = "nowrap";

    // Individual flex item properties
    private float mFlex = 0.0f;
    private float mFlexGrow = 0.0f;
    private float mFlexShrink = 1.0f;
    private float mFlexBasis = 0.0f;
    private String mAlignSelf = "auto";

    // Margin and padding
    private float[] mMargin = new float[4]; // top, right, bottom, left
    private float[] mPadding = new float[4]; // top, right, bottom, left

    // Material properties
    private ReadableArray mMaterials;

    public ViroFlexViewView(@NonNull ThemedReactContext context) {
        super(context);
        ViroLog.debug(TAG, "ViroFlexViewView initialized");
        
        // Initialize default margin and padding
        for (int i = 0; i < 4; i++) {
            mMargin[i] = 0.0f;
            mPadding[i] = 0.0f;
        }
        
        // TODO: Initialize ViroReact flex view
        // This will need to integrate with the existing ViroReact flex layout system
        initializeFlexView();
    }

    private void initializeFlexView() {
        ViroLog.debug(TAG, "Initializing flex view with default properties");
        
        // TODO: Set up ViroReact flex view with default properties
        // This should initialize the underlying 3D flex container
        updateFlexLayout();
    }

    // FlexView Layout Properties
    public void setFlexWidth(float width) {
        ViroLog.debug(TAG, "Setting flex width: " + width);
        mWidth = width;
        updateFlexLayout();
    }

    public void setFlexHeight(float height) {
        ViroLog.debug(TAG, "Setting flex height: " + height);
        mHeight = height;
        updateFlexLayout();
    }

    public void setFlexDirection(@NonNull String flexDirection) {
        ViroLog.debug(TAG, "Setting flex direction: " + flexDirection);
        mFlexDirection = flexDirection;
        updateFlexLayout();
    }

    public void setJustifyContent(@NonNull String justifyContent) {
        ViroLog.debug(TAG, "Setting justify content: " + justifyContent);
        mJustifyContent = justifyContent;
        updateFlexLayout();
    }

    public void setAlignItems(@NonNull String alignItems) {
        ViroLog.debug(TAG, "Setting align items: " + alignItems);
        mAlignItems = alignItems;
        updateFlexLayout();
    }

    public void setAlignContent(@NonNull String alignContent) {
        ViroLog.debug(TAG, "Setting align content: " + alignContent);
        mAlignContent = alignContent;
        updateFlexLayout();
    }

    public void setFlexWrap(@NonNull String flexWrap) {
        ViroLog.debug(TAG, "Setting flex wrap: " + flexWrap);
        mFlexWrap = flexWrap;
        updateFlexLayout();
    }

    // Individual Flex Item Properties
    public void setFlex(float flex) {
        ViroLog.debug(TAG, "Setting flex: " + flex);
        mFlex = flex;
        updateFlexLayout();
    }

    public void setFlexGrow(float flexGrow) {
        ViroLog.debug(TAG, "Setting flex grow: " + flexGrow);
        mFlexGrow = flexGrow;
        updateFlexLayout();
    }

    public void setFlexShrink(float flexShrink) {
        ViroLog.debug(TAG, "Setting flex shrink: " + flexShrink);
        mFlexShrink = flexShrink;
        updateFlexLayout();
    }

    public void setFlexBasis(float flexBasis) {
        ViroLog.debug(TAG, "Setting flex basis: " + flexBasis);
        mFlexBasis = flexBasis;
        updateFlexLayout();
    }

    public void setAlignSelf(@NonNull String alignSelf) {
        ViroLog.debug(TAG, "Setting align self: " + alignSelf);
        mAlignSelf = alignSelf;
        updateFlexLayout();
    }

    // Margin and Padding
    public void setMargin(@Nullable ReadableArray margin) {
        ViroLog.debug(TAG, "Setting margin: " + margin);
        if (margin != null) {
            parseSpacingArray(margin, mMargin);
            updateFlexLayout();
        }
    }

    public void setPadding(@Nullable ReadableArray padding) {
        ViroLog.debug(TAG, "Setting padding: " + padding);
        if (padding != null) {
            parseSpacingArray(padding, mPadding);
            updateFlexLayout();
        }
    }

    private void parseSpacingArray(@NonNull ReadableArray spacingArray, @NonNull float[] output) {
        // Parse spacing array in CSS format
        // [all] or [vertical, horizontal] or [top, right, bottom, left]
        int size = spacingArray.size();
        
        if (size == 1) {
            // [all]
            float all = (float) spacingArray.getDouble(0);
            output[0] = output[1] = output[2] = output[3] = all;
        } else if (size == 2) {
            // [vertical, horizontal]
            float vertical = (float) spacingArray.getDouble(0);
            float horizontal = (float) spacingArray.getDouble(1);
            output[0] = output[2] = vertical;   // top, bottom
            output[1] = output[3] = horizontal; // right, left
        } else if (size >= 4) {
            // [top, right, bottom, left]
            output[0] = (float) spacingArray.getDouble(0); // top
            output[1] = (float) spacingArray.getDouble(1); // right
            output[2] = (float) spacingArray.getDouble(2); // bottom
            output[3] = (float) spacingArray.getDouble(3); // left
        }
    }

    // Material Properties
    public void setMaterials(@Nullable ReadableArray materials) {
        ViroLog.debug(TAG, "Setting materials: " + materials);
        mMaterials = materials;
        
        // TODO: Apply materials to ViroReact flex view
        // FlexView can have a background material
    }

    private void updateFlexLayout() {
        ViroLog.debug(TAG, String.format(
            "Updating flex layout - Size: %.2fx%.2f, Direction: %s, Justify: %s, Align: %s",
            mWidth, mHeight, mFlexDirection, mJustifyContent, mAlignItems));
        
        // TODO: Apply flex layout properties to ViroReact renderer
        // This should trigger a layout pass for all child components
        // FlexView uses Yoga layout engine for 3D space layout
        
        // Emit layout update event for React Native
        emitFlexLayoutUpdateEvent();
    }

    private void emitFlexLayoutUpdateEvent() {
        WritableMap event = Arguments.createMap();
        event.putDouble("width", mWidth);
        event.putDouble("height", mHeight);
        event.putString("flexDirection", mFlexDirection);
        event.putString("justifyContent", mJustifyContent);
        event.putString("alignItems", mAlignItems);
        
        ThemedReactContext reactContext = (ThemedReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onFlexLayoutUpdate", event);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        
        if (changed) {
            ViroLog.debug(TAG, String.format("FlexView layout changed: %dx%d", 
                right - left, bottom - top));
            
            // FlexView handles 3D layout of child components using flexbox rules
            updateFlexLayout();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViroLog.debug(TAG, "FlexView attached to window");
        
        // TODO: Add flex view to ViroReact scene when attached to window
        updateFlexLayout();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViroLog.debug(TAG, "FlexView detached from window");
        
        // TODO: Remove flex view from ViroReact scene when detached from window
    }

    // Getters for current values (useful for debugging and testing)
    public float getFlexWidth() { return mWidth; }
    public float getFlexHeight() { return mHeight; }
    public String getFlexDirection() { return mFlexDirection; }
    public String getJustifyContent() { return mJustifyContent; }
    public String getAlignItems() { return mAlignItems; }
    public float getFlex() { return mFlex; }
    public float getFlexGrow() { return mFlexGrow; }
    public float getFlexShrink() { return mFlexShrink; }
    public float getFlexBasis() { return mFlexBasis; }
    public String getAlignSelf() { return mAlignSelf; }
}