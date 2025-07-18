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
import com.facebook.react.viewmanagers.ViroSceneManagerDelegate;
import com.facebook.react.viewmanagers.ViroSceneManagerInterface;

import android.util.Log;

/**
 * ViewManager for ViroScene component in React Native New Architecture.
 * This manages the 3D scene container that holds all ViroReact objects.
 */
public class ViroSceneViewManager extends ViewGroupManager<ViroSceneView> implements ViroSceneManagerInterface<ViroSceneView> {
    
    private static final String TAG = "ViroSceneViewManager";
    public static final String REACT_CLASS = "ViroScene";
    
    private final ViewManagerDelegate<ViroSceneView> mDelegate;
    
    public ViroSceneViewManager() {
        mDelegate = new ViroSceneManagerDelegate(this);
    }
    
    @Override
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    protected ViroSceneView createViewInstance(ThemedReactContext reactContext) {
        Log.d(TAG, "Creating ViroSceneView instance");
        return new ViroSceneView(reactContext);
    }
    
    @Override
    public ViewManagerDelegate<ViroSceneView> getDelegate() {
        return mDelegate;
    }
    
    // Scene configuration props
    
    @ReactProp(name = "soundRoom")
    @Override
    public void setSoundRoom(ViroSceneView view, @Nullable ReadableMap value) {
        Log.d(TAG, "Setting sound room: " + value);
        view.setSoundRoom(value);
    }
    
    @ReactProp(name = "physicsWorld")
    @Override  
    public void setPhysicsWorld(ViroSceneView view, @Nullable ReadableMap value) {
        Log.d(TAG, "Setting physics world: " + value);
        view.setPhysicsWorld(value);
    }
    
    @ReactProp(name = "postProcessEffects")
    @Override
    public void setPostProcessEffects(ViroSceneView view, @Nullable ReadableArray value) {
        Log.d(TAG, "Setting post process effects: " + value);
        view.setPostProcessEffects(value);
    }
    
    @ReactProp(name = "lightingEnvironment")
    @Override
    public void setLightingEnvironment(ViroSceneView view, @Nullable ReadableMap value) {
        Log.d(TAG, "Setting lighting environment: " + value);
        view.setLightingEnvironment(value);
    }
    
    @ReactProp(name = "backgroundTexture")
    @Override
    public void setBackgroundTexture(ViroSceneView view, @Nullable ReadableMap value) {
        Log.d(TAG, "Setting background texture: " + value);
        view.setBackgroundTexture(value);
    }
    
    @ReactProp(name = "backgroundCubeTexture")
    @Override
    public void setBackgroundCubeTexture(ViroSceneView view, @Nullable ReadableMap value) {
        Log.d(TAG, "Setting background cube texture: " + value);
        view.setBackgroundCubeTexture(value);
    }
    
    // Event handling will be implemented in the view class
    
    @Override
    public void onDropViewInstance(@NonNull ViroSceneView view) {
        Log.d(TAG, "Dropping ViroSceneView instance");
        view.onDropViewInstance();
        super.onDropViewInstance(view);
    }
}