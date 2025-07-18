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
 * Native Android view for ViroBox component.
 * ViroBox represents a 3D box geometry primitive.
 */
public class ViroBoxView extends View {
    
    private static final String TAG = "ViroBoxView";
    
    private ReactContext mReactContext;
    
    // Box geometry properties
    private float mWidth = 1.0f;
    private float mHeight = 1.0f;
    private float mLength = 1.0f;
    
    // Material properties
    private List<String> mMaterials;
    
    public ViroBoxView(@NonNull Context context) {
        super(context);
        mReactContext = (ReactContext) context;
        initializeView();
    }
    
    private void initializeView() {
        Log.d(TAG, "Initializing ViroBoxView");
        
        // TODO: Initialize ViroReact box geometry
        // This will need to integrate with the existing ViroReact box implementation
        
        // Box views are typically transparent since they represent 3D geometry
        setBackgroundColor(android.graphics.Color.TRANSPARENT);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // For 3D geometry, we don't use traditional Android view measurements
        // The size is determined by the 3D geometry properties (width, height, length)
        // Set a minimal size for the view container
        setMeasuredDimension(1, 1);
    }
    
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // Layout is handled by 3D transforms, not 2D layout
        Log.d(TAG, "onLayout called: " + changed + " bounds: [" + left + "," + top + "," + right + "," + bottom + "]");
    }
    
    // Box geometry setters
    
    public void setBoxWidth(float width) {
        Log.d(TAG, "Setting box width: " + width);
        mWidth = width;
        updateBoxGeometry();
    }
    
    public void setBoxHeight(float height) {
        Log.d(TAG, "Setting box height: " + height);
        mHeight = height;
        updateBoxGeometry();
    }
    
    public void setBoxLength(float length) {
        Log.d(TAG, "Setting box length: " + length);
        mLength = length;
        updateBoxGeometry();
    }
    
    private void updateBoxGeometry() {
        Log.d(TAG, "Updating box geometry: " + mWidth + " x " + mHeight + " x " + mLength);
        
        // TODO: Apply box dimensions to ViroReact renderer
        // This should update the underlying 3D box mesh with new dimensions
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
        
        // TODO: Apply materials to ViroReact box
        // Materials can be applied to different faces of the box
        // If only one material is provided, it applies to all faces
        // If 6 materials are provided, they apply to each face in order:
        // [front, right, back, left, top, bottom]
    }
    
    // Event emission (inherited from ViroNode behavior)
    
    public void emitClickEvent() {
        WritableMap event = Arguments.createMap();
        event.putString("source", "ViroBox");
        event.putArray("position", Arguments.fromArray(new float[]{0f, 0f, 0f})); // TODO: Get actual click position
        emitBoxEvent("onClick", event);
    }
    
    public void emitHoverEvent(boolean isHovering) {
        WritableMap event = Arguments.createMap();
        event.putString("source", "ViroBox");
        event.putBoolean("isHovering", isHovering);
        emitBoxEvent("onHover", event);
    }
    
    private void emitBoxEvent(String eventName, @Nullable WritableMap eventData) {
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
        // TODO: Clean up ViroReact box resources
        
        // Clear references
        mMaterials = null;
        mReactContext = null;
    }
    
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "ViroBoxView attached to window");
        
        // TODO: Add box to ViroReact scene when attached
        updateBoxGeometry();
    }
    
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "ViroBoxView detached from window");
        // TODO: Remove box from ViroReact scene when detached
    }
}