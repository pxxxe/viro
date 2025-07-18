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
import com.facebook.react.viewmanagers.ViroImageManagerDelegate;
import com.facebook.react.viewmanagers.ViroImageManagerInterface;

import android.util.Log;

/**
 * ViewManager for ViroImage component in React Native New Architecture.
 * ViroImage displays 2D images in 3D space.
 */
public class ViroImageViewManager extends ViewGroupManager<ViroImageView> implements ViroImageManagerInterface<ViroImageView> {
    
    private static final String TAG = "ViroImageViewManager";
    public static final String REACT_CLASS = "ViroImage";
    
    private final ViewManagerDelegate<ViroImageView> mDelegate;
    
    public ViroImageViewManager() {
        mDelegate = new ViroImageManagerDelegate(this);
    }
    
    @Override
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    protected ViroImageView createViewInstance(ThemedReactContext reactContext) {
        Log.d(TAG, "Creating ViroImageView instance");
        return new ViroImageView(reactContext);
    }
    
    @Override
    public ViewManagerDelegate<ViroImageView> getDelegate() {
        return mDelegate;
    }
    
    // Image source and content props
    
    @ReactProp(name = "source")
    @Override
    public void setSource(ViroImageView view, @Nullable ReadableMap value) {
        Log.d(TAG, "Setting source: " + value);
        view.setSource(value);
    }
    
    @ReactProp(name = "placeholderSource")
    @Override
    public void setPlaceholderSource(ViroImageView view, @Nullable ReadableMap value) {
        Log.d(TAG, "Setting placeholder source: " + value);
        view.setPlaceholderSource(value);
    }
    
    // Image dimensions props
    
    @ReactProp(name = "width", defaultFloat = 1.0f)
    @Override
    public void setWidth(ViroImageView view, float value) {
        Log.d(TAG, "Setting width: " + value);
        view.setImageWidth(value);
    }
    
    @ReactProp(name = "height", defaultFloat = 1.0f)
    @Override
    public void setHeight(ViroImageView view, float value) {
        Log.d(TAG, "Setting height: " + value);
        view.setImageHeight(value);
    }
    
    // Image display properties props
    
    @ReactProp(name = "format")
    @Override
    public void setFormat(ViroImageView view, @Nullable String value) {
        Log.d(TAG, "Setting format: " + value);
        view.setFormat(value);
    }
    
    @ReactProp(name = "mipmap", defaultBoolean = true)
    @Override
    public void setMipmap(ViroImageView view, boolean value) {
        Log.d(TAG, "Setting mipmap: " + value);
        view.setMipmap(value);
    }
    
    @ReactProp(name = "wrapS")
    @Override
    public void setWrapS(ViroImageView view, @Nullable String value) {
        Log.d(TAG, "Setting wrapS: " + value);
        view.setWrapS(value);
    }
    
    @ReactProp(name = "wrapT")
    @Override
    public void setWrapT(ViroImageView view, @Nullable String value) {
        Log.d(TAG, "Setting wrapT: " + value);
        view.setWrapT(value);
    }
    
    @ReactProp(name = "minificationFilter")
    @Override
    public void setMinificationFilter(ViroImageView view, @Nullable String value) {
        Log.d(TAG, "Setting minification filter: " + value);
        view.setMinificationFilter(value);
    }
    
    @ReactProp(name = "magnificationFilter")
    @Override
    public void setMagnificationFilter(ViroImageView view, @Nullable String value) {
        Log.d(TAG, "Setting magnification filter: " + value);
        view.setMagnificationFilter(value);
    }
    
    @ReactProp(name = "resizeMode")
    @Override
    public void setResizeMode(ViroImageView view, @Nullable String value) {
        Log.d(TAG, "Setting resize mode: " + value);
        view.setResizeMode(value);
    }
    
    @ReactProp(name = "imageClipMode")
    @Override
    public void setImageClipMode(ViroImageView view, @Nullable String value) {
        Log.d(TAG, "Setting image clip mode: " + value);
        view.setImageClipMode(value);
    }
    
    // Stereo image properties
    
    @ReactProp(name = "stereoMode")
    @Override
    public void setStereoMode(ViroImageView view, @Nullable String value) {
        Log.d(TAG, "Setting stereo mode: " + value);
        view.setStereoMode(value);
    }
    
    // Material props
    
    @ReactProp(name = "materials")
    @Override
    public void setMaterials(ViroImageView view, @Nullable ReadableArray value) {
        Log.d(TAG, "Setting materials: " + value);
        view.setMaterials(value);
    }
    
    @Override
    public void onDropViewInstance(@NonNull ViroImageView view) {
        Log.d(TAG, "Dropping ViroImageView instance");
        view.onDropViewInstance();
        super.onDropViewInstance(view);
    }
}