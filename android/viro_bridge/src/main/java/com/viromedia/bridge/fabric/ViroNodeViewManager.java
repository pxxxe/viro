package com.viromedia.bridge.fabric;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.ViroNodeManagerDelegate;
import com.facebook.react.viewmanagers.ViroNodeManagerInterface;

import android.util.Log;

/**
 * ViewManager for ViroNode component in React Native New Architecture.
 * ViroNode is the base container for all 3D objects in ViroReact.
 */
public class ViroNodeViewManager extends ViewGroupManager<ViroNodeView> implements ViroNodeManagerInterface<ViroNodeView> {
    
    private static final String TAG = "ViroNodeViewManager";
    public static final String REACT_CLASS = "ViroNode";
    
    private final ViewManagerDelegate<ViroNodeView> mDelegate;
    
    public ViroNodeViewManager() {
        mDelegate = new ViroNodeManagerDelegate(this);
    }
    
    @Override
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    protected ViroNodeView createViewInstance(ThemedReactContext reactContext) {
        Log.d(TAG, "Creating ViroNodeView instance");
        return new ViroNodeView(reactContext);
    }
    
    @Override
    public ViewManagerDelegate<ViroNodeView> getDelegate() {
        return mDelegate;
    }
    
    // Transform props
    
    @ReactProp(name = "position")
    @Override
    public void setPosition(ViroNodeView view, @Nullable ReadableArray value) {
        Log.d(TAG, "Setting position: " + value);
        view.setPosition(value);
    }
    
    @ReactProp(name = "scale")
    @Override
    public void setScale(ViroNodeView view, @Nullable ReadableArray value) {
        Log.d(TAG, "Setting scale: " + value);
        view.setScale(value);
    }
    
    @ReactProp(name = "rotation")
    @Override
    public void setRotation(ViroNodeView view, @Nullable ReadableArray value) {
        Log.d(TAG, "Setting rotation: " + value);
        view.setRotation(value);
    }
    
    @ReactProp(name = "rotationPivot")
    @Override
    public void setRotationPivot(ViroNodeView view, @Nullable ReadableArray value) {
        Log.d(TAG, "Setting rotation pivot: " + value);
        view.setRotationPivot(value);
    }
    
    @ReactProp(name = "scalePivot")
    @Override
    public void setScalePivot(ViroNodeView view, @Nullable ReadableArray value) {
        Log.d(TAG, "Setting scale pivot: " + value);
        view.setScalePivot(value);
    }
    
    @ReactProp(name = "transformBehaviors")
    @Override
    public void setTransformBehaviors(ViroNodeView view, @Nullable ReadableArray value) {
        Log.d(TAG, "Setting transform behaviors: " + value);
        view.setTransformBehaviors(value);
    }
    
    // Visibility and interaction props
    
    @ReactProp(name = "visible", defaultBoolean = true)
    @Override
    public void setVisible(ViroNodeView view, boolean value) {
        Log.d(TAG, "Setting visible: " + value);
        view.setVisible(value);
    }
    
    @ReactProp(name = "opacity", defaultFloat = 1.0f)
    @Override
    public void setOpacity(ViroNodeView view, float value) {
        Log.d(TAG, "Setting opacity: " + value);
        view.setOpacity(value);
    }
    
    @ReactProp(name = "renderingOrder", defaultInt = 0)
    @Override
    public void setRenderingOrder(ViroNodeView view, int value) {
        Log.d(TAG, "Setting rendering order: " + value);
        view.setRenderingOrder(value);
    }
    
    @ReactProp(name = "ignoreEventHandling", defaultBoolean = false)
    @Override
    public void setIgnoreEventHandling(ViroNodeView view, boolean value) {
        Log.d(TAG, "Setting ignore event handling: " + value);
        view.setIgnoreEventHandling(value);
    }
    
    @ReactProp(name = "dragType")
    @Override
    public void setDragType(ViroNodeView view, @Nullable String value) {
        Log.d(TAG, "Setting drag type: " + value);
        view.setDragType(value);
    }
    
    // Physics props
    
    @ReactProp(name = "physicsBody")
    @Override
    public void setPhysicsBody(ViroNodeView view, @Nullable ReadableMap value) {
        Log.d(TAG, "Setting physics body: " + value);
        view.setPhysicsBody(value);
    }
    
    @ReactProp(name = "highAccuracyEvents", defaultBoolean = false)
    @Override
    public void setHighAccuracyEvents(ViroNodeView view, boolean value) {
        Log.d(TAG, "Setting high accuracy events: " + value);
        view.setHighAccuracyEvents(value);
    }
    
    // Animation props
    
    @ReactProp(name = "animation")
    @Override
    public void setAnimation(ViroNodeView view, @Nullable ReadableMap value) {
        Log.d(TAG, "Setting animation: " + value);
        view.setAnimation(value);
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroNodeView view) {
        Log.d(TAG, "Dropping ViroNodeView instance");
        view.onDropViewInstance();
        super.onDropViewInstance(view);
    }
}