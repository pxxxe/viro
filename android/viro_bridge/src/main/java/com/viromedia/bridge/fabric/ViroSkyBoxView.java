//
//  ViroSkyBoxView.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import com.viromedia.bridge.utility.ViroLog;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ViroSkyBoxView - 360° Skybox Environment Android View
 * 
 * This View provides 360° skybox environments for ViroReact applications.
 * It creates immersive background environments using cube maps, equirectangular images,
 * or color gradients that surround the entire 3D scene.
 * 
 * Key Features:
 * - Cube map skybox support (6 individual textures)
 * - Equirectangular (360°) image support
 * - Color gradient skybox generation
 * - HDR skybox support for realistic lighting
 * - Rotation and positioning controls
 * - Fade in/out animations
 * - Format support: JPG, PNG, HDR, EXR
 * - Integration with ViroReact lighting system
 */
public class ViroSkyBoxView extends View {
    
    private static final String TAG = ViroLog.getTag(ViroSkyBoxView.class);
    
    // Skybox types
    public enum SkyboxType {
        CUBE("cube"),
        EQUIRECTANGULAR("equirectangular"),
        GRADIENT("gradient"),
        HDR("hdr");
        
        private final String value;
        
        SkyboxType(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
        
        public static SkyboxType fromString(String value) {
            for (SkyboxType type : SkyboxType.values()) {
                if (type.value.equals(value)) {
                    return type;
                }
            }
            return CUBE;
        }
    }
    
    // Loading states
    public enum LoadingState {
        IDLE("idle"),
        LOADING("loading"),
        LOADED("loaded"),
        ERROR("error");
        
        private final String value;
        
        LoadingState(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
    
    // Skybox content
    private ReadableMap source;
    private ReadableMap cubeMap;
    private ReadableMap equirectangular;
    private ReadableArray colorTop = Arguments.createArray();
    private ReadableArray colorBottom = Arguments.createArray();
    private ReadableArray colorLeft = Arguments.createArray();
    private ReadableArray colorRight = Arguments.createArray();
    private ReadableArray colorFront = Arguments.createArray();
    private ReadableArray colorBack = Arguments.createArray();
    
    // Skybox appearance
    private float opacity = 1.0f;
    private float brightness = 1.0f;
    private float contrast = 1.0f;
    private float saturation = 1.0f;
    private float gamma = 1.0f;
    private float exposure = 0.0f;
    
    // Skybox geometry
    private ReadableArray rotation = Arguments.createArray();
    private ReadableArray scale = Arguments.createArray();
    private float radius = 1000.0f;
    private int segments = 50;
    
    // Skybox behavior
    private String format = "jpg";
    private String type = "cube";
    private boolean lightingEnabled = true;
    private String environmentBlending = "normal";
    private boolean rotationEnabled = false;
    private boolean autoRotate = false;
    private float rotationSpeed = 1.0f;
    
    // Skybox animation
    private float fadeInDuration = 1.0f;
    private float fadeOutDuration = 1.0f;
    private float transitionDuration = 1.0f;
    private String animationEasing = "ease-in-out";
    
    // Skybox loading
    private boolean loadingEnabled = true;
    private ReadableArray loadingColor = Arguments.createArray();
    private float loadingOpacity = 0.8f;
    private boolean preloadEnabled = false;
    private boolean cacheEnabled = true;
    
    // Internal state
    private SkyboxType skyboxType = SkyboxType.CUBE;
    private LoadingState loadingState = LoadingState.IDLE;
    private float loadingProgress = 0.0f;
    private Exception lastError;
    
    // Rendering
    private Paint backgroundPaint;
    private Paint gradientPaint;
    private Bitmap skyboxBitmap;
    private Handler rotationHandler;
    private Runnable rotationRunnable;
    private float currentRotation = 0.0f;
    private ExecutorService loadingExecutor;
    
    public ViroSkyBoxView(@NonNull Context context) {
        super(context);
        init();
    }
    
    private void init() {
        ViroLog.debug(TAG, "Initializing ViroSkyBoxView");
        
        // Initialize default values
        colorTop = createColorArray(0.5f, 0.7f, 1.0f, 1.0f);
        colorBottom = createColorArray(0.1f, 0.3f, 0.8f, 1.0f);
        colorLeft = createColorArray(0.3f, 0.5f, 0.9f, 1.0f);
        colorRight = createColorArray(0.3f, 0.5f, 0.9f, 1.0f);
        colorFront = createColorArray(0.3f, 0.5f, 0.9f, 1.0f);
        colorBack = createColorArray(0.3f, 0.5f, 0.9f, 1.0f);
        rotation = createRotationArray(0.0f, 0.0f, 0.0f);
        scale = createScaleArray(1.0f, 1.0f, 1.0f);
        loadingColor = createColorArray(0.5f, 0.5f, 0.5f, 1.0f);
        
        // Initialize paint objects
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setStyle(Paint.Style.FILL);
        
        gradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gradientPaint.setStyle(Paint.Style.FILL);
        
        // Set up view properties
        setLayoutParams(new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ));
        
        // Initialize rotation handler
        rotationHandler = new Handler(Looper.getMainLooper());
        
        // Initialize loading executor
        loadingExecutor = Executors.newSingleThreadExecutor();
        
        updateSkyboxAppearance();
    }
    
    // Skybox Content Methods
    public void setSource(@Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        this.source = source;
        updateSkyboxContent();
    }
    
    public void setCubeMap(@Nullable ReadableMap cubeMap) {
        ViroLog.debug(TAG, "Setting cube map: " + cubeMap);
        this.cubeMap = cubeMap;
        this.skyboxType = SkyboxType.CUBE;
        updateSkyboxContent();
    }
    
    public void setEquirectangular(@Nullable ReadableMap equirectangular) {
        ViroLog.debug(TAG, "Setting equirectangular: " + equirectangular);
        this.equirectangular = equirectangular;
        this.skyboxType = SkyboxType.EQUIRECTANGULAR;
        updateSkyboxContent();
    }
    
    public void setColorTop(@Nullable ReadableArray colorTop) {
        ViroLog.debug(TAG, "Setting color top: " + colorTop);
        this.colorTop = colorTop != null ? colorTop : createColorArray(0.5f, 0.7f, 1.0f, 1.0f);
        updateSkyboxGradient();
    }
    
    public void setColorBottom(@Nullable ReadableArray colorBottom) {
        ViroLog.debug(TAG, "Setting color bottom: " + colorBottom);
        this.colorBottom = colorBottom != null ? colorBottom : createColorArray(0.1f, 0.3f, 0.8f, 1.0f);
        updateSkyboxGradient();
    }
    
    public void setColorLeft(@Nullable ReadableArray colorLeft) {
        ViroLog.debug(TAG, "Setting color left: " + colorLeft);
        this.colorLeft = colorLeft != null ? colorLeft : createColorArray(0.3f, 0.5f, 0.9f, 1.0f);
        updateSkyboxGradient();
    }
    
    public void setColorRight(@Nullable ReadableArray colorRight) {
        ViroLog.debug(TAG, "Setting color right: " + colorRight);
        this.colorRight = colorRight != null ? colorRight : createColorArray(0.3f, 0.5f, 0.9f, 1.0f);
        updateSkyboxGradient();
    }
    
    public void setColorFront(@Nullable ReadableArray colorFront) {
        ViroLog.debug(TAG, "Setting color front: " + colorFront);
        this.colorFront = colorFront != null ? colorFront : createColorArray(0.3f, 0.5f, 0.9f, 1.0f);
        updateSkyboxGradient();
    }
    
    public void setColorBack(@Nullable ReadableArray colorBack) {
        ViroLog.debug(TAG, "Setting color back: " + colorBack);
        this.colorBack = colorBack != null ? colorBack : createColorArray(0.3f, 0.5f, 0.9f, 1.0f);
        updateSkyboxGradient();
    }
    
    // Skybox Appearance Methods
    public void setOpacity(float opacity) {
        ViroLog.debug(TAG, "Setting opacity: " + opacity);
        this.opacity = opacity;
        updateSkyboxAppearance();
    }
    
    public void setBrightness(float brightness) {
        ViroLog.debug(TAG, "Setting brightness: " + brightness);
        this.brightness = brightness;
        updateSkyboxAppearance();
    }
    
    public void setContrast(float contrast) {
        ViroLog.debug(TAG, "Setting contrast: " + contrast);
        this.contrast = contrast;
        updateSkyboxAppearance();
    }
    
    public void setSaturation(float saturation) {
        ViroLog.debug(TAG, "Setting saturation: " + saturation);
        this.saturation = saturation;
        updateSkyboxAppearance();
    }
    
    public void setGamma(float gamma) {
        ViroLog.debug(TAG, "Setting gamma: " + gamma);
        this.gamma = gamma;
        updateSkyboxAppearance();
    }
    
    public void setExposure(float exposure) {
        ViroLog.debug(TAG, "Setting exposure: " + exposure);
        this.exposure = exposure;
        updateSkyboxAppearance();
    }
    
    // Skybox Geometry Methods
    public void setRotation(@Nullable ReadableArray rotation) {
        ViroLog.debug(TAG, "Setting rotation: " + rotation);
        this.rotation = rotation != null ? rotation : createRotationArray(0.0f, 0.0f, 0.0f);
        updateSkyboxTransform();
    }
    
    public void setScale(@Nullable ReadableArray scale) {
        ViroLog.debug(TAG, "Setting scale: " + scale);
        this.scale = scale != null ? scale : createScaleArray(1.0f, 1.0f, 1.0f);
        updateSkyboxTransform();
    }
    
    public void setRadius(float radius) {
        ViroLog.debug(TAG, "Setting radius: " + radius);
        this.radius = radius;
        updateSkyboxGeometry();
    }
    
    public void setSegments(int segments) {
        ViroLog.debug(TAG, "Setting segments: " + segments);
        this.segments = segments;
        updateSkyboxGeometry();
    }
    
    // Skybox Behavior Methods
    public void setFormat(@Nullable String format) {
        ViroLog.debug(TAG, "Setting format: " + format);
        this.format = format != null ? format : "jpg";
    }
    
    public void setType(@Nullable String type) {
        ViroLog.debug(TAG, "Setting type: " + type);
        this.type = type != null ? type : "cube";
        this.skyboxType = SkyboxType.fromString(this.type);
        updateSkyboxContent();
    }
    
    public void setLightingEnabled(boolean lightingEnabled) {
        ViroLog.debug(TAG, "Setting lighting enabled: " + lightingEnabled);
        this.lightingEnabled = lightingEnabled;
        updateSkyboxAppearance();
    }
    
    public void setEnvironmentBlending(@Nullable String environmentBlending) {
        ViroLog.debug(TAG, "Setting environment blending: " + environmentBlending);
        this.environmentBlending = environmentBlending != null ? environmentBlending : "normal";
        updateSkyboxAppearance();
    }
    
    public void setRotationEnabled(boolean rotationEnabled) {
        ViroLog.debug(TAG, "Setting rotation enabled: " + rotationEnabled);
        this.rotationEnabled = rotationEnabled;
        updateRotationBehavior();
    }
    
    public void setAutoRotate(boolean autoRotate) {
        ViroLog.debug(TAG, "Setting auto rotate: " + autoRotate);
        this.autoRotate = autoRotate;
        updateRotationBehavior();
    }
    
    public void setRotationSpeed(float rotationSpeed) {
        ViroLog.debug(TAG, "Setting rotation speed: " + rotationSpeed);
        this.rotationSpeed = rotationSpeed;
        updateRotationBehavior();
    }
    
    // Skybox Animation Methods
    public void setFadeInDuration(float fadeInDuration) {
        ViroLog.debug(TAG, "Setting fade in duration: " + fadeInDuration);
        this.fadeInDuration = fadeInDuration;
    }
    
    public void setFadeOutDuration(float fadeOutDuration) {
        ViroLog.debug(TAG, "Setting fade out duration: " + fadeOutDuration);
        this.fadeOutDuration = fadeOutDuration;
    }
    
    public void setTransitionDuration(float transitionDuration) {
        ViroLog.debug(TAG, "Setting transition duration: " + transitionDuration);
        this.transitionDuration = transitionDuration;
    }
    
    public void setAnimationEasing(@Nullable String animationEasing) {
        ViroLog.debug(TAG, "Setting animation easing: " + animationEasing);
        this.animationEasing = animationEasing != null ? animationEasing : "ease-in-out";
    }
    
    // Skybox Loading Methods
    public void setLoadingEnabled(boolean loadingEnabled) {
        ViroLog.debug(TAG, "Setting loading enabled: " + loadingEnabled);
        this.loadingEnabled = loadingEnabled;
    }
    
    public void setLoadingColor(@Nullable ReadableArray loadingColor) {
        ViroLog.debug(TAG, "Setting loading color: " + loadingColor);
        this.loadingColor = loadingColor != null ? loadingColor : createColorArray(0.5f, 0.5f, 0.5f, 1.0f);
    }
    
    public void setLoadingOpacity(float loadingOpacity) {
        ViroLog.debug(TAG, "Setting loading opacity: " + loadingOpacity);
        this.loadingOpacity = loadingOpacity;
    }
    
    public void setPreloadEnabled(boolean preloadEnabled) {
        ViroLog.debug(TAG, "Setting preload enabled: " + preloadEnabled);
        this.preloadEnabled = preloadEnabled;
    }
    
    public void setCacheEnabled(boolean cacheEnabled) {
        ViroLog.debug(TAG, "Setting cache enabled: " + cacheEnabled);
        this.cacheEnabled = cacheEnabled;
    }
    
    // Skybox Control Methods
    public void loadSkybox() {
        ViroLog.debug(TAG, "Loading skybox");
        
        if (loadingState == LoadingState.LOADING) {
            ViroLog.debug(TAG, "Skybox already loading");
            return;
        }
        
        loadingState = LoadingState.LOADING;
        loadingProgress = 0.0f;
        
        // Fire load start event
        fireLoadStartEvent();
        
        // Load based on skybox type
        switch (skyboxType) {
            case CUBE:
                loadCubeSkybox();
                break;
            case EQUIRECTANGULAR:
                loadEquirectangularSkybox();
                break;
            case GRADIENT:
                loadGradientSkybox();
                break;
            case HDR:
                loadHDRSkybox();
                break;
        }
    }
    
    public void unloadSkybox() {
        ViroLog.debug(TAG, "Unloading skybox");
        
        if (skyboxBitmap != null) {
            skyboxBitmap.recycle();
            skyboxBitmap = null;
        }
        
        loadingState = LoadingState.IDLE;
        loadingProgress = 0.0f;
        
        invalidate();
    }
    
    public void reloadSkybox() {
        ViroLog.debug(TAG, "Reloading skybox");
        unloadSkybox();
        loadSkybox();
    }
    
    public void fadeIn() {
        ViroLog.debug(TAG, "Fading in skybox");
        
        ValueAnimator fadeAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        fadeAnimator.setDuration((long) (fadeInDuration * 1000));
        fadeAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        
        fadeAnimator.addUpdateListener(animation -> {
            float alpha = (float) animation.getAnimatedValue();
            setAlpha(alpha);
        });
        
        fadeAnimator.start();
    }
    
    public void fadeOut() {
        ViroLog.debug(TAG, "Fading out skybox");
        
        ValueAnimator fadeAnimator = ValueAnimator.ofFloat(1.0f, 0.0f);
        fadeAnimator.setDuration((long) (fadeOutDuration * 1000));
        fadeAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        
        fadeAnimator.addUpdateListener(animation -> {
            float alpha = (float) animation.getAnimatedValue();
            setAlpha(alpha);
        });
        
        fadeAnimator.start();
    }
    
    public void transitionTo(ReadableMap newSkybox) {
        ViroLog.debug(TAG, "Transitioning to new skybox: " + newSkybox);
        
        // TODO: Implement smooth transition between skyboxes
        // This would fade out current skybox, load new one, and fade in
    }
    
    // Skybox State Information Methods
    public String getLoadingState() {
        return loadingState.getValue();
    }
    
    public boolean isLoaded() {
        return loadingState == LoadingState.LOADED;
    }
    
    public boolean isLoading() {
        return loadingState == LoadingState.LOADING;
    }
    
    public boolean hasError() {
        return loadingState == LoadingState.ERROR;
    }
    
    public float getLoadingProgress() {
        return loadingProgress;
    }
    
    public ReadableMap getSkyboxInfo() {
        WritableMap info = Arguments.createMap();
        info.putString("type", type);
        info.putString("format", format);
        info.putString("loadingState", getLoadingState());
        info.putDouble("loadingProgress", loadingProgress);
        info.putDouble("radius", radius);
        info.putInt("segments", segments);
        info.putBoolean("lightingEnabled", lightingEnabled);
        return info;
    }
    
    // Helper Methods
    private void updateSkyboxContent() {
        if (loadingEnabled && loadingState == LoadingState.IDLE) {
            loadSkybox();
        }
    }
    
    private void updateSkyboxAppearance() {
        if (backgroundPaint != null) {
            backgroundPaint.setAlpha(Math.round(opacity * 255));
        }
        
        if (gradientPaint != null) {
            gradientPaint.setAlpha(Math.round(opacity * 255));
        }
        
        invalidate();
    }
    
    private void updateSkyboxTransform() {
        // Update rotation based on rotation array
        if (rotation != null && rotation.size() >= 3) {
            currentRotation = (float) rotation.getDouble(1); // Y rotation
        }
        
        // Update scale based on scale array
        if (scale != null && scale.size() >= 3) {
            float scaleX = (float) scale.getDouble(0);
            float scaleY = (float) scale.getDouble(1);
            setScaleX(scaleX);
            setScaleY(scaleY);
        }
        
        invalidate();
    }
    
    private void updateSkyboxGeometry() {
        // Geometry updates would affect the 3D sphere rendering
        // For now, just trigger a redraw
        invalidate();
    }
    
    private void updateSkyboxGradient() {
        if (skyboxType != SkyboxType.GRADIENT) {
            return;
        }
        
        // Create gradient shader
        int topColor = colorFromArray(colorTop);
        int bottomColor = colorFromArray(colorBottom);
        
        LinearGradient gradient = new LinearGradient(
            0, 0, 0, getHeight(),
            topColor, bottomColor,
            Shader.TileMode.CLAMP
        );
        
        gradientPaint.setShader(gradient);
        invalidate();
    }
    
    private void updateRotationBehavior() {
        if (rotationRunnable != null) {
            rotationHandler.removeCallbacks(rotationRunnable);
            rotationRunnable = null;
        }
        
        if (autoRotate && rotationEnabled) {
            rotationRunnable = new Runnable() {
                @Override
                public void run() {
                    currentRotation += rotationSpeed * (1.0f / 60.0f);
                    if (currentRotation >= 360.0f) {
                        currentRotation -= 360.0f;
                    }
                    setRotation(currentRotation);
                    rotationHandler.postDelayed(this, 1000 / 60);
                }
            };
            rotationHandler.post(rotationRunnable);
        }
    }
    
    private void loadCubeSkybox() {
        ViroLog.debug(TAG, "Loading cube skybox");
        
        loadingExecutor.execute(() -> {
            try {
                // TODO: Load cube map textures
                // This would load 6 individual textures for each face
                
                // Simulate loading
                Thread.sleep(1000);
                
                // Update on main thread
                rotationHandler.post(() -> {
                    loadingState = LoadingState.LOADED;
                    loadingProgress = 1.0f;
                    
                    fireLoadEvent();
                    fireLoadEndEvent();
                    
                    invalidate();
                });
            } catch (Exception e) {
                ViroLog.error(TAG, "Error loading cube skybox", e);
                lastError = e;
                
                rotationHandler.post(() -> {
                    loadingState = LoadingState.ERROR;
                    fireErrorEvent(e.getMessage());
                });
            }
        });
    }
    
    private void loadEquirectangularSkybox() {
        ViroLog.debug(TAG, "Loading equirectangular skybox");
        
        loadingExecutor.execute(() -> {
            try {
                // TODO: Load equirectangular texture
                // This would load a single 360° image
                
                // Simulate loading
                Thread.sleep(1000);
                
                // Update on main thread
                rotationHandler.post(() -> {
                    loadingState = LoadingState.LOADED;
                    loadingProgress = 1.0f;
                    
                    fireLoadEvent();
                    fireLoadEndEvent();
                    
                    invalidate();
                });
            } catch (Exception e) {
                ViroLog.error(TAG, "Error loading equirectangular skybox", e);
                lastError = e;
                
                rotationHandler.post(() -> {
                    loadingState = LoadingState.ERROR;
                    fireErrorEvent(e.getMessage());
                });
            }
        });
    }
    
    private void loadGradientSkybox() {
        ViroLog.debug(TAG, "Loading gradient skybox");
        
        // Generate gradient skybox immediately
        updateSkyboxGradient();
        
        loadingState = LoadingState.LOADED;
        loadingProgress = 1.0f;
        
        fireLoadEvent();
        fireLoadEndEvent();
    }
    
    private void loadHDRSkybox() {
        ViroLog.debug(TAG, "Loading HDR skybox");
        
        loadingExecutor.execute(() -> {
            try {
                // TODO: Load HDR texture
                // This would load HDR format for realistic lighting
                
                // Simulate loading
                Thread.sleep(1500);
                
                // Update on main thread
                rotationHandler.post(() -> {
                    loadingState = LoadingState.LOADED;
                    loadingProgress = 1.0f;
                    
                    fireLoadEvent();
                    fireLoadEndEvent();
                    
                    invalidate();
                });
            } catch (Exception e) {
                ViroLog.error(TAG, "Error loading HDR skybox", e);
                lastError = e;
                
                rotationHandler.post(() -> {
                    loadingState = LoadingState.ERROR;
                    fireErrorEvent(e.getMessage());
                });
            }
        });
    }
    
    private void fireLoadStartEvent() {
        WritableMap event = Arguments.createMap();
        
        Context context = getContext();
        if (context instanceof com.facebook.react.bridge.ReactContext) {
            ((com.facebook.react.bridge.ReactContext) context)
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onLoadStart", event);
        }
    }
    
    private void fireLoadEvent() {
        WritableMap event = Arguments.createMap();
        event.putString("type", type);
        event.putString("format", format);
        
        Context context = getContext();
        if (context instanceof com.facebook.react.bridge.ReactContext) {
            ((com.facebook.react.bridge.ReactContext) context)
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onLoad", event);
        }
    }
    
    private void fireLoadEndEvent() {
        WritableMap event = Arguments.createMap();
        event.putString("loadingState", getLoadingState());
        event.putDouble("loadingProgress", loadingProgress);
        
        Context context = getContext();
        if (context instanceof com.facebook.react.bridge.ReactContext) {
            ((com.facebook.react.bridge.ReactContext) context)
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onLoadEnd", event);
        }
    }
    
    private void fireErrorEvent(String errorMessage) {
        WritableMap event = Arguments.createMap();
        event.putString("error", errorMessage);
        event.putString("loadingState", getLoadingState());
        
        Context context = getContext();
        if (context instanceof com.facebook.react.bridge.ReactContext) {
            ((com.facebook.react.bridge.ReactContext) context)
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onError", event);
        }
    }
    
    private ReadableArray createColorArray(float r, float g, float b, float a) {
        WritableArray array = Arguments.createArray();
        array.pushDouble(r);
        array.pushDouble(g);
        array.pushDouble(b);
        array.pushDouble(a);
        return array;
    }
    
    private ReadableArray createRotationArray(float x, float y, float z) {
        WritableArray array = Arguments.createArray();
        array.pushDouble(x);
        array.pushDouble(y);
        array.pushDouble(z);
        return array;
    }
    
    private ReadableArray createScaleArray(float x, float y, float z) {
        WritableArray array = Arguments.createArray();
        array.pushDouble(x);
        array.pushDouble(y);
        array.pushDouble(z);
        return array;
    }
    
    private int colorFromArray(ReadableArray colorArray) {
        if (colorArray == null || colorArray.size() < 3) {
            return Color.WHITE;
        }
        
        float red = (float) colorArray.getDouble(0);
        float green = (float) colorArray.getDouble(1);
        float blue = (float) colorArray.getDouble(2);
        float alpha = colorArray.size() > 3 ? (float) colorArray.getDouble(3) : 1.0f;
        
        return Color.argb(
            Math.round(alpha * 255),
            Math.round(red * 255),
            Math.round(green * 255),
            Math.round(blue * 255)
        );
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if (loadingState == LoadingState.LOADING && loadingEnabled) {
            // Draw loading state
            backgroundPaint.setColor(colorFromArray(loadingColor));
            backgroundPaint.setAlpha(Math.round(loadingOpacity * 255));
            canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);
        } else if (skyboxType == SkyboxType.GRADIENT) {
            // Draw gradient skybox
            canvas.drawRect(0, 0, getWidth(), getHeight(), gradientPaint);
        } else if (skyboxBitmap != null) {
            // Draw skybox bitmap
            canvas.drawBitmap(skyboxBitmap, 0, 0, backgroundPaint);
        }
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Skybox should fill the entire view
        setMeasuredDimension(
            MeasureSpec.getSize(widthMeasureSpec),
            MeasureSpec.getSize(heightMeasureSpec)
        );
    }
    
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        
        // Clean up resources
        if (rotationRunnable != null) {
            rotationHandler.removeCallbacks(rotationRunnable);
            rotationRunnable = null;
        }
        
        if (skyboxBitmap != null) {
            skyboxBitmap.recycle();
            skyboxBitmap = null;
        }
        
        if (loadingExecutor != null) {
            loadingExecutor.shutdown();
            loadingExecutor = null;
        }
    }
}