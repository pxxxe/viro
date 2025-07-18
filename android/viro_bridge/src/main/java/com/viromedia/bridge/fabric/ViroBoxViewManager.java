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
import com.facebook.react.viewmanagers.ViroBoxManagerDelegate;
import com.facebook.react.viewmanagers.ViroBoxManagerInterface;

import android.util.Log;

/**
 * ViewManager for ViroBox component in React Native New Architecture.
 * ViroBox represents a 3D box geometry primitive.
 */
public class ViroBoxViewManager extends ViewGroupManager<ViroBoxView> implements ViroBoxManagerInterface<ViroBoxView> {
    
    private static final String TAG = "ViroBoxViewManager";
    public static final String REACT_CLASS = "ViroBox";
    
    private final ViewManagerDelegate<ViroBoxView> mDelegate;
    
    public ViroBoxViewManager() {
        mDelegate = new ViroBoxManagerDelegate(this);
    }
    
    @Override
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    protected ViroBoxView createViewInstance(ThemedReactContext reactContext) {
        Log.d(TAG, "Creating ViroBoxView instance");
        return new ViroBoxView(reactContext);
    }
    
    @Override
    public ViewManagerDelegate<ViroBoxView> getDelegate() {
        return mDelegate;
    }
    
    // Box geometry props
    
    @ReactProp(name = "width", defaultFloat = 1.0f)
    @Override
    public void setWidth(ViroBoxView view, float value) {
        Log.d(TAG, "Setting width: " + value);
        view.setBoxWidth(value);
    }
    
    @ReactProp(name = "height", defaultFloat = 1.0f)
    @Override
    public void setHeight(ViroBoxView view, float value) {
        Log.d(TAG, "Setting height: " + value);
        view.setBoxHeight(value);
    }
    
    @ReactProp(name = "length", defaultFloat = 1.0f)
    @Override
    public void setLength(ViroBoxView view, float value) {
        Log.d(TAG, "Setting length: " + value);
        view.setBoxLength(value);
    }
    
    // Material props
    
    @ReactProp(name = "materials")
    @Override
    public void setMaterials(ViroBoxView view, @Nullable ReadableArray value) {
        Log.d(TAG, "Setting materials: " + value);
        view.setMaterials(value);
    }
    
    // Inherited ViroNode props will be handled by extending ViroNodeViewManager
    // For now, we'll implement ViroBox as a standalone component
    
    @Override
    public void onDropViewInstance(@NonNull ViroBoxView view) {
        Log.d(TAG, "Dropping ViroBoxView instance");
        view.onDropViewInstance();
        super.onDropViewInstance(view);
    }
}