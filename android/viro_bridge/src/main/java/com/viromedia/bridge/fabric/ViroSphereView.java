package com.viromedia.bridge.fabric;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.ArrayList;
import java.util.List;

/**
 * Native Android view for ViroSphere component.
 * ViroSphere represents a 3D sphere geometry primitive.
 */
public class ViroSphereView extends View {
    
    private static final String TAG = "ViroSphereView";
    
    private ReactContext mReactContext;
    
    // Sphere geometry properties
    private float mRadius = 1.0f;
    private int mWidthSegmentCount = 20;
    private int mHeightSegmentCount = 20;
    private float mPhiStart = 0.0f;
    private float mPhiLength = (float)(2 * Math.PI); // Full circle
    private float mThetaStart = 0.0f;
    private float mThetaLength = (float)Math.PI; // Half circle (full sphere)
    
    // Material properties
    private List<String> mMaterials;
    
    public ViroSphereView(@NonNull Context context) {
        super(context);
        mReactContext = (ReactContext) context;
        initializeView();
    }
    
    private void initializeView() {
        Log.d(TAG, "Initializing ViroSphereView");
        
        // TODO: Initialize ViroReact sphere geometry
        // This will need to integrate with the existing ViroReact sphere implementation
        
        // Sphere views are typically transparent since they represent 3D geometry
        setBackgroundColor(android.graphics.Color.TRANSPARENT);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // For 3D geometry, we don't use traditional Android view measurements
        // The size is determined by the 3D sphere radius and geometry parameters
        // Set a minimal size for the view container
        setMeasuredDimension(1, 1);
    }
    
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // Layout is handled by 3D transforms and sphere parameters, not 2D layout
        Log.d(TAG, "onLayout called: " + changed + " bounds: [" + left + "," + top + "," + right + "," + bottom + "]");
    }
    
    // Sphere geometry setters
    
    public void setRadius(float radius) {
        Log.d(TAG, "Setting sphere radius: " + radius);
        mRadius = radius;
        updateSphereGeometry();
    }
    
    public void setWidthSegmentCount(int widthSegmentCount) {
        Log.d(TAG, "Setting width segment count: " + widthSegmentCount);
        mWidthSegmentCount = widthSegmentCount;
        updateSphereGeometry();
    }
    
    public void setHeightSegmentCount(int heightSegmentCount) {
        Log.d(TAG, "Setting height segment count: " + heightSegmentCount);
        mHeightSegmentCount = heightSegmentCount;
        updateSphereGeometry();
    }
    
    public void setPhiStart(float phiStart) {
        Log.d(TAG, "Setting phi start: " + phiStart);
        mPhiStart = phiStart;
        updateSphereGeometry();
    }
    
    public void setPhiLength(float phiLength) {
        Log.d(TAG, "Setting phi length: " + phiLength);
        mPhiLength = phiLength;
        updateSphereGeometry();
    }
    
    public void setThetaStart(float thetaStart) {
        Log.d(TAG, "Setting theta start: " + thetaStart);
        mThetaStart = thetaStart;
        updateSphereGeometry();
    }
    
    public void setThetaLength(float thetaLength) {
        Log.d(TAG, "Setting theta length: " + thetaLength);
        mThetaLength = thetaLength;
        updateSphereGeometry();
    }
    
    private void updateSphereGeometry() {
        Log.d(TAG, "Updating sphere geometry: radius=" + mRadius + 
                   ", segments=" + mWidthSegmentCount + "x" + mHeightSegmentCount + 
                   ", phi=[" + mPhiStart + "," + mPhiLength + "]" +
                   ", theta=[" + mThetaStart + "," + mThetaLength + "]");
        
        // TODO: Apply sphere parameters to ViroReact renderer
        // This should update the underlying 3D sphere mesh with new parameters
        // - radius: size of the sphere
        // - widthSegmentCount/heightSegmentCount: mesh resolution
        // - phi/theta parameters: for creating partial spheres (like domes or wedges)
    }
    
    // Material setters
    
    public void setMaterials(@Nullable ReadableArray materials) {
        if (materials != null) {
            mMaterials = new ArrayList<>();
            for (int i = 0; i < materials.size(); i++) {
                String material = materials.getString(i);
                if (material != null) {
                    mMaterials.add(material);
                }
            }
        } else {
            mMaterials = null;
        }
        
        Log.d(TAG, "Setting materials: " + mMaterials);
        
        // TODO: Apply materials to ViroReact sphere
        // Materials can be applied to different sections of the sphere
        // If only one material is provided, it applies to the entire sphere
    }
    
    // Event emission (inherited from ViroNode behavior)
    
    public void emitClickEvent() {
        WritableMap event = Arguments.createMap();
        event.putString("source", "ViroSphere");
        event.putArray("position", Arguments.fromArray(new float[]{0f, 0f, 0f})); // TODO: Get actual click position
        emitSphereEvent("onClick", event);
    }
    
    public void emitHoverEvent(boolean isHovering) {
        WritableMap event = Arguments.createMap();
        event.putString("source", "ViroSphere");
        event.putBoolean("isHovering", isHovering);
        emitSphereEvent("onHover", event);
    }
    
    private void emitSphereEvent(String eventName, @Nullable WritableMap eventData) {
        try {
            if (mReactContext != null && mReactContext.hasActiveCatalystInstance()) {
                mReactContext.getJSModule(RCTEventEmitter.class)
                    .receiveEvent(getId(), eventName, eventData);
            } else {
                Log.w(TAG, "Cannot emit event " + eventName + ": no active React context");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error emitting event " + eventName + ": " + e.getMessage(), e);
        }
    }
    
    // Lifecycle methods
    
    public void onDropViewInstance() {
        Log.d(TAG, "onDropViewInstance called");
        // TODO: Clean up ViroReact sphere resources
        
        // Clear references
        mMaterials = null;
        mReactContext = null;
    }
    
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "ViroSphereView attached to window");
        
        // TODO: Add sphere to ViroReact scene when attached
        updateSphereGeometry();
    }
    
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "ViroSphereView detached from window");
        // TODO: Remove sphere from ViroReact scene when detached
    }
}