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
import com.facebook.react.viewmanagers.ViroSphereManagerDelegate;
import com.facebook.react.viewmanagers.ViroSphereManagerInterface;

import android.util.Log;

/**
 * ViewManager for ViroSphere component in React Native New Architecture.
 * ViroSphere represents a 3D sphere geometry primitive.
 */
public class ViroSphereViewManager extends ViewGroupManager<ViroSphereView> implements ViroSphereManagerInterface<ViroSphereView> {
    
    private static final String TAG = "ViroSphereViewManager";
    public static final String REACT_CLASS = "ViroSphere";
    
    private final ViewManagerDelegate<ViroSphereView> mDelegate;
    
    public ViroSphereViewManager() {
        mDelegate = new ViroSphereManagerDelegate(this);
    }
    
    @Override
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    protected ViroSphereView createViewInstance(ThemedReactContext reactContext) {
        Log.d(TAG, "Creating ViroSphereView instance");
        return new ViroSphereView(reactContext);
    }
    
    @Override
    public ViewManagerDelegate<ViroSphereView> getDelegate() {
        return mDelegate;
    }
    
    // Sphere geometry props
    
    @ReactProp(name = "radius", defaultFloat = 1.0f)
    @Override
    public void setRadius(ViroSphereView view, float value) {
        Log.d(TAG, "Setting radius: " + value);
        view.setRadius(value);
    }
    
    @ReactProp(name = "widthSegmentCount", defaultInt = 20)
    @Override
    public void setWidthSegmentCount(ViroSphereView view, int value) {
        Log.d(TAG, "Setting width segment count: " + value);
        view.setWidthSegmentCount(value);
    }
    
    @ReactProp(name = "heightSegmentCount", defaultInt = 20)
    @Override
    public void setHeightSegmentCount(ViroSphereView view, int value) {
        Log.d(TAG, "Setting height segment count: " + value);
        view.setHeightSegmentCount(value);
    }
    
    @ReactProp(name = "phiStart", defaultFloat = 0.0f)
    @Override
    public void setPhiStart(ViroSphereView view, float value) {
        Log.d(TAG, "Setting phi start: " + value);
        view.setPhiStart(value);
    }
    
    @ReactProp(name = "phiLength", defaultFloat = 6.283185f) // 2 * PI
    @Override
    public void setPhiLength(ViroSphereView view, float value) {
        Log.d(TAG, "Setting phi length: " + value);
        view.setPhiLength(value);
    }
    
    @ReactProp(name = "thetaStart", defaultFloat = 0.0f)
    @Override
    public void setThetaStart(ViroSphereView view, float value) {
        Log.d(TAG, "Setting theta start: " + value);
        view.setThetaStart(value);
    }
    
    @ReactProp(name = "thetaLength", defaultFloat = 3.141593f) // PI
    @Override
    public void setThetaLength(ViroSphereView view, float value) {
        Log.d(TAG, "Setting theta length: " + value);
        view.setThetaLength(value);
    }
    
    // Material props
    
    @ReactProp(name = "materials")
    @Override
    public void setMaterials(ViroSphereView view, @Nullable ReadableArray value) {
        Log.d(TAG, "Setting materials: " + value);
        view.setMaterials(value);
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroSphereView view) {
        Log.d(TAG, "Dropping ViroSphereView instance");
        view.onDropViewInstance();
        super.onDropViewInstance(view);
    }
}