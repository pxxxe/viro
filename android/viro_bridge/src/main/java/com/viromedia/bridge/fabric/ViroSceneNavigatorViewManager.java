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
import com.facebook.react.viewmanagers.ViroSceneNavigatorManagerDelegate;
import com.facebook.react.viewmanagers.ViroSceneNavigatorManagerInterface;

import android.util.Log;

/**
 * ViewManager for ViroSceneNavigator component in React Native New Architecture.
 * This serves as the main container for ViroReact scenes.
 */
public class ViroSceneNavigatorViewManager extends ViewGroupManager<ViroSceneNavigatorView> implements ViroSceneNavigatorManagerInterface<ViroSceneNavigatorView> {
    
    private static final String TAG = "ViroSceneNavigatorViewManager";
    public static final String REACT_CLASS = "ViroSceneNavigator";
    
    private final ViewManagerDelegate<ViroSceneNavigatorView> mDelegate;
    
    public ViroSceneNavigatorViewManager() {
        mDelegate = new ViroSceneNavigatorManagerDelegate(this);
    }
    
    @Override
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    protected ViroSceneNavigatorView createViewInstance(ThemedReactContext reactContext) {
        Log.d(TAG, "Creating ViroSceneNavigatorView instance");
        return new ViroSceneNavigatorView(reactContext);
    }
    
    @Override
    public ViewManagerDelegate<ViroSceneNavigatorView> getDelegate() {
        return mDelegate;
    }
    
    // Scene configuration props
    
    @ReactProp(name = "initialScene")
    @Override
    public void setInitialScene(ViroSceneNavigatorView view, @Nullable ReadableMap value) {
        Log.d(TAG, "Setting initial scene: " + value);
        view.setInitialScene(value);
    }
    
    @ReactProp(name = "viroAppProps")
    @Override
    public void setViroAppProps(ViroSceneNavigatorView view, @Nullable ReadableMap value) {
        view.setViroAppProps(value);
    }
    
    @ReactProp(name = "autofocus", defaultBoolean = true)
    @Override
    public void setAutofocus(ViroSceneNavigatorView view, boolean value) {
        view.setAutofocus(value);
    }
    
    @ReactProp(name = "bloomEnabled", defaultBoolean = false)
    @Override
    public void setBloomEnabled(ViroSceneNavigatorView view, boolean value) {
        view.setBloomEnabled(value);
    }
    
    @ReactProp(name = "shadowsEnabled", defaultBoolean = true)
    @Override
    public void setShadowsEnabled(ViroSceneNavigatorView view, boolean value) {
        view.setShadowsEnabled(value);
    }
    
    @ReactProp(name = "multisamplingEnabled", defaultBoolean = true)
    @Override
    public void setMultisamplingEnabled(ViroSceneNavigatorView view, boolean value) {
        view.setMultisamplingEnabled(value);
    }
    
    @ReactProp(name = "hdrEnabled", defaultBoolean = false)
    @Override
    public void setHdrEnabled(ViroSceneNavigatorView view, boolean value) {
        view.setHdrEnabled(value);
    }
    
    @ReactProp(name = "pbrEnabled", defaultBoolean = true)
    @Override
    public void setPbrEnabled(ViroSceneNavigatorView view, boolean value) {
        view.setPbrEnabled(value);
    }
    
    // Camera configuration
    
    @ReactProp(name = "worldAlignment")
    @Override
    public void setWorldAlignment(ViroSceneNavigatorView view, @Nullable String value) {
        view.setWorldAlignment(value);
    }
    
    @ReactProp(name = "videoQuality")
    @Override
    public void setVideoQuality(ViroSceneNavigatorView view, @Nullable String value) {
        view.setVideoQuality(value);
    }
    
    @ReactProp(name = "numberOfTrackedImages", defaultInt = 1)
    @Override
    public void setNumberOfTrackedImages(ViroSceneNavigatorView view, int value) {
        view.setNumberOfTrackedImages(value);
    }
    
    // VR specific props
    
    @ReactProp(name = "vrModeEnabled", defaultBoolean = false) 
    @Override
    public void setVrModeEnabled(ViroSceneNavigatorView view, boolean value) {
        view.setVrModeEnabled(value);
    }
    
    @ReactProp(name = "debug", defaultBoolean = false)
    @Override
    public void setDebug(ViroSceneNavigatorView view, boolean value) {
        view.setDebug(value);
    }
    
    // Performance props
    
    @ReactProp(name = "canCameraTransformUpdate", defaultBoolean = false)
    @Override
    public void setCanCameraTransformUpdate(ViroSceneNavigatorView view, boolean value) {
        view.setCanCameraTransformUpdate(value);
    }
    
    // Event handling will be implemented in the view class
    
    @Override
    public void onDropViewInstance(@NonNull ViroSceneNavigatorView view) {
        Log.d(TAG, "Dropping ViroSceneNavigatorView instance");
        view.onDropViewInstance();
        super.onDropViewInstance(view);
    }
}