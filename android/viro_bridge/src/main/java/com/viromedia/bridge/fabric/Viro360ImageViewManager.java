//
//  Viro360ImageViewManager.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.viromedia.bridge.utility.ViroLog;

/**
 * Viro360ImageViewManager - 360° Panoramic Image ViewManager
 * 
 * This ViewManager handles 360° panoramic images for ViroReact applications.
 * It renders spherical environments using equirectangular panoramas, cube maps,
 * and stereoscopic 3D images to create immersive VR experiences.
 * 
 * Key Features:
 * - 360° equirectangular panorama support
 * - Cube map texture loading (6 faces)
 * - Stereoscopic 3D image support (side-by-side, top-bottom)
 * - Dynamic texture loading and caching
 * - Resolution and quality optimization
 * - Rotation and orientation controls
 * - Loading states and error handling
 * - HDR and LDR image support
 * - Texture compression and streaming
 * - Performance optimization for large images
 */
public class Viro360ImageViewManager extends SimpleViewManager<Viro360ImageView> {
    
    private static final String TAG = ViroLog.getTag(Viro360ImageViewManager.class);
    private static final String REACT_CLASS = "Viro360Image";
    
    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }
    
    @Override
    @NonNull
    public Viro360ImageView createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViroLog.debug(TAG, "Creating Viro360ImageView instance");
        return new Viro360ImageView(reactContext);
    }
    
    // Image Source Properties
    @ReactProp(name = "source")
    public void setSource(Viro360ImageView view, @Nullable ReadableMap source) {
        ViroLog.debug(TAG, "Setting source: " + source);
        view.setSource(source);
    }
    
    @ReactProp(name = "uri")
    public void setUri(Viro360ImageView view, @Nullable String uri) {
        ViroLog.debug(TAG, "Setting uri: " + uri);
        view.setUri(uri);
    }
    
    @ReactProp(name = "local")
    public void setLocal(Viro360ImageView view, @Nullable String local) {
        ViroLog.debug(TAG, "Setting local: " + local);
        view.setLocal(local);
    }
    
    @ReactProp(name = "resource")
    public void setResource(Viro360ImageView view, @Nullable String resource) {
        ViroLog.debug(TAG, "Setting resource: " + resource);
        view.setResource(resource);
    }
    
    // Image Format and Type
    @ReactProp(name = "format")
    public void setFormat(Viro360ImageView view, @Nullable String format) {
        ViroLog.debug(TAG, "Setting format: " + format);
        view.setFormat(format);
    }
    
    @ReactProp(name = "imageType")
    public void setImageType(Viro360ImageView view, @Nullable String imageType) {
        ViroLog.debug(TAG, "Setting image type: " + imageType);
        view.setImageType(imageType);
    }
    
    @ReactProp(name = "projection")
    public void setProjection(Viro360ImageView view, @Nullable String projection) {
        ViroLog.debug(TAG, "Setting projection: " + projection);
        view.setProjection(projection);
    }
    
    @ReactProp(name = "mapping")
    public void setMapping(Viro360ImageView view, @Nullable String mapping) {
        ViroLog.debug(TAG, "Setting mapping: " + mapping);
        view.setMapping(mapping);
    }
    
    // Stereoscopic Properties
    @ReactProp(name = "stereoscopicMode")
    public void setStereoscopicMode(Viro360ImageView view, @Nullable String stereoscopicMode) {
        ViroLog.debug(TAG, "Setting stereoscopic mode: " + stereoscopicMode);
        view.setStereoscopicMode(stereoscopicMode);
    }
    
    @ReactProp(name = "eyeType")
    public void setEyeType(Viro360ImageView view, @Nullable String eyeType) {
        ViroLog.debug(TAG, "Setting eye type: " + eyeType);
        view.setEyeType(eyeType);
    }
    
    @ReactProp(name = "interpupillaryDistance", defaultFloat = 0.064f)
    public void setInterpupillaryDistance(Viro360ImageView view, float interpupillaryDistance) {
        ViroLog.debug(TAG, "Setting interpupillary distance: " + interpupillaryDistance);
        view.setInterpupillaryDistance(interpupillaryDistance);
    }
    
    // Cube Map Properties
    @ReactProp(name = "cubeFaces")
    public void setCubeFaces(Viro360ImageView view, @Nullable ReadableMap cubeFaces) {
        ViroLog.debug(TAG, "Setting cube faces: " + cubeFaces);
        view.setCubeFaces(cubeFaces);
    }
    
    @ReactProp(name = "facePositiveX")
    public void setFacePositiveX(Viro360ImageView view, @Nullable String facePositiveX) {
        ViroLog.debug(TAG, "Setting face positive X: " + facePositiveX);
        view.setFacePositiveX(facePositiveX);
    }
    
    @ReactProp(name = "faceNegativeX")
    public void setFaceNegativeX(Viro360ImageView view, @Nullable String faceNegativeX) {
        ViroLog.debug(TAG, "Setting face negative X: " + faceNegativeX);
        view.setFaceNegativeX(faceNegativeX);
    }
    
    @ReactProp(name = "facePositiveY")
    public void setFacePositiveY(Viro360ImageView view, @Nullable String facePositiveY) {
        ViroLog.debug(TAG, "Setting face positive Y: " + facePositiveY);
        view.setFacePositiveY(facePositiveY);
    }
    
    @ReactProp(name = "faceNegativeY")
    public void setFaceNegativeY(Viro360ImageView view, @Nullable String faceNegativeY) {
        ViroLog.debug(TAG, "Setting face negative Y: " + faceNegativeY);
        view.setFaceNegativeY(faceNegativeY);
    }
    
    @ReactProp(name = "facePositiveZ")
    public void setFacePositiveZ(Viro360ImageView view, @Nullable String facePositiveZ) {
        ViroLog.debug(TAG, "Setting face positive Z: " + facePositiveZ);
        view.setFacePositiveZ(facePositiveZ);
    }
    
    @ReactProp(name = "faceNegativeZ")
    public void setFaceNegativeZ(Viro360ImageView view, @Nullable String faceNegativeZ) {
        ViroLog.debug(TAG, "Setting face negative Z: " + faceNegativeZ);
        view.setFaceNegativeZ(faceNegativeZ);
    }
    
    // Rotation and Orientation
    @ReactProp(name = "rotation")
    public void setRotation(Viro360ImageView view, @Nullable ReadableArray rotation) {
        ViroLog.debug(TAG, "Setting rotation: " + rotation);
        view.setRotation(rotation);
    }
    
    @ReactProp(name = "rotationX", defaultFloat = 0.0f)
    public void setRotationX(Viro360ImageView view, float rotationX) {
        ViroLog.debug(TAG, "Setting rotation X: " + rotationX);
        view.setRotationX(rotationX);
    }
    
    @ReactProp(name = "rotationY", defaultFloat = 0.0f)
    public void setRotationY(Viro360ImageView view, float rotationY) {
        ViroLog.debug(TAG, "Setting rotation Y: " + rotationY);
        view.setRotationY(rotationY);
    }
    
    @ReactProp(name = "rotationZ", defaultFloat = 0.0f)
    public void setRotationZ(Viro360ImageView view, float rotationZ) {
        ViroLog.debug(TAG, "Setting rotation Z: " + rotationZ);
        view.setRotationZ(rotationZ);
    }
    
    @ReactProp(name = "orientation")
    public void setOrientation(Viro360ImageView view, @Nullable ReadableArray orientation) {
        ViroLog.debug(TAG, "Setting orientation: " + orientation);
        view.setOrientation(orientation);
    }
    
    // Display Properties
    @ReactProp(name = "radius", defaultFloat = 1000.0f)
    public void setRadius(Viro360ImageView view, float radius) {
        ViroLog.debug(TAG, "Setting radius: " + radius);
        view.setRadius(radius);
    }
    
    @ReactProp(name = "segmentWidth", defaultInt = 64)
    public void setSegmentWidth(Viro360ImageView view, int segmentWidth) {
        ViroLog.debug(TAG, "Setting segment width: " + segmentWidth);
        view.setSegmentWidth(segmentWidth);
    }
    
    @ReactProp(name = "segmentHeight", defaultInt = 32)
    public void setSegmentHeight(Viro360ImageView view, int segmentHeight) {
        ViroLog.debug(TAG, "Setting segment height: " + segmentHeight);
        view.setSegmentHeight(segmentHeight);
    }
    
    @ReactProp(name = "renderingOrder", defaultInt = 0)
    public void setRenderingOrder(Viro360ImageView view, int renderingOrder) {
        ViroLog.debug(TAG, "Setting rendering order: " + renderingOrder);
        view.setRenderingOrder(renderingOrder);
    }
    
    @ReactProp(name = "invertNormals", defaultBoolean = true)
    public void setInvertNormals(Viro360ImageView view, boolean invertNormals) {
        ViroLog.debug(TAG, "Setting invert normals: " + invertNormals);
        view.setInvertNormals(invertNormals);
    }
    
    // Texture Properties
    @ReactProp(name = "textureMinification")
    public void setTextureMinification(Viro360ImageView view, @Nullable String textureMinification) {
        ViroLog.debug(TAG, "Setting texture minification: " + textureMinification);
        view.setTextureMinification(textureMinification);
    }
    
    @ReactProp(name = "textureMagnification")
    public void setTextureMagnification(Viro360ImageView view, @Nullable String textureMagnification) {
        ViroLog.debug(TAG, "Setting texture magnification: " + textureMagnification);
        view.setTextureMagnification(textureMagnification);
    }
    
    @ReactProp(name = "textureWrapS")
    public void setTextureWrapS(Viro360ImageView view, @Nullable String textureWrapS) {
        ViroLog.debug(TAG, "Setting texture wrap S: " + textureWrapS);
        view.setTextureWrapS(textureWrapS);
    }
    
    @ReactProp(name = "textureWrapT")
    public void setTextureWrapT(Viro360ImageView view, @Nullable String textureWrapT) {
        ViroLog.debug(TAG, "Setting texture wrap T: " + textureWrapT);
        view.setTextureWrapT(textureWrapT);
    }
    
    @ReactProp(name = "mipmap", defaultBoolean = true)
    public void setMipmap(Viro360ImageView view, boolean mipmap) {
        ViroLog.debug(TAG, "Setting mipmap: " + mipmap);
        view.setMipmap(mipmap);
    }
    
    @ReactProp(name = "anisotropy", defaultFloat = 1.0f)
    public void setAnisotropy(Viro360ImageView view, float anisotropy) {
        ViroLog.debug(TAG, "Setting anisotropy: " + anisotropy);
        view.setAnisotropy(anisotropy);
    }
    
    // Quality and Performance
    @ReactProp(name = "quality")
    public void setQuality(Viro360ImageView view, @Nullable String quality) {
        ViroLog.debug(TAG, "Setting quality: " + quality);
        view.setQuality(quality);
    }
    
    @ReactProp(name = "resolution")
    public void setResolution(Viro360ImageView view, @Nullable String resolution) {
        ViroLog.debug(TAG, "Setting resolution: " + resolution);
        view.setResolution(resolution);
    }
    
    @ReactProp(name = "compression")
    public void setCompression(Viro360ImageView view, @Nullable String compression) {
        ViroLog.debug(TAG, "Setting compression: " + compression);
        view.setCompression(compression);
    }
    
    @ReactProp(name = "maxTextureSize", defaultInt = 2048)
    public void setMaxTextureSize(Viro360ImageView view, int maxTextureSize) {
        ViroLog.debug(TAG, "Setting max texture size: " + maxTextureSize);
        view.setMaxTextureSize(maxTextureSize);
    }
    
    @ReactProp(name = "streamingEnabled", defaultBoolean = false)
    public void setStreamingEnabled(Viro360ImageView view, boolean streamingEnabled) {
        ViroLog.debug(TAG, "Setting streaming enabled: " + streamingEnabled);
        view.setStreamingEnabled(streamingEnabled);
    }
    
    // Loading and Caching
    @ReactProp(name = "preload", defaultBoolean = false)
    public void setPreload(Viro360ImageView view, boolean preload) {
        ViroLog.debug(TAG, "Setting preload: " + preload);
        view.setPreload(preload);
    }
    
    @ReactProp(name = "cacheEnabled", defaultBoolean = true)
    public void setCacheEnabled(Viro360ImageView view, boolean cacheEnabled) {
        ViroLog.debug(TAG, "Setting cache enabled: " + cacheEnabled);
        view.setCacheEnabled(cacheEnabled);
    }
    
    @ReactProp(name = "cacheSize", defaultInt = 100)
    public void setCacheSize(Viro360ImageView view, int cacheSize) {
        ViroLog.debug(TAG, "Setting cache size: " + cacheSize);
        view.setCacheSize(cacheSize);
    }
    
    @ReactProp(name = "loadingTimeout", defaultFloat = 30.0f)
    public void setLoadingTimeout(Viro360ImageView view, float loadingTimeout) {
        ViroLog.debug(TAG, "Setting loading timeout: " + loadingTimeout);
        view.setLoadingTimeout(loadingTimeout);
    }
    
    @ReactProp(name = "retryCount", defaultInt = 3)
    public void setRetryCount(Viro360ImageView view, int retryCount) {
        ViroLog.debug(TAG, "Setting retry count: " + retryCount);
        view.setRetryCount(retryCount);
    }
    
    // Color and Effects
    @ReactProp(name = "tintColor")
    public void setTintColor(Viro360ImageView view, @Nullable ReadableArray tintColor) {
        ViroLog.debug(TAG, "Setting tint color: " + tintColor);
        view.setTintColor(tintColor);
    }
    
    @ReactProp(name = "brightness", defaultFloat = 1.0f)
    public void setBrightness(Viro360ImageView view, float brightness) {
        ViroLog.debug(TAG, "Setting brightness: " + brightness);
        view.setBrightness(brightness);
    }
    
    @ReactProp(name = "contrast", defaultFloat = 1.0f)
    public void setContrast(Viro360ImageView view, float contrast) {
        ViroLog.debug(TAG, "Setting contrast: " + contrast);
        view.setContrast(contrast);
    }
    
    @ReactProp(name = "saturation", defaultFloat = 1.0f)
    public void setSaturation(Viro360ImageView view, float saturation) {
        ViroLog.debug(TAG, "Setting saturation: " + saturation);
        view.setSaturation(saturation);
    }
    
    @ReactProp(name = "gamma", defaultFloat = 1.0f)
    public void setGamma(Viro360ImageView view, float gamma) {
        ViroLog.debug(TAG, "Setting gamma: " + gamma);
        view.setGamma(gamma);
    }
    
    @ReactProp(name = "exposure", defaultFloat = 0.0f)
    public void setExposure(Viro360ImageView view, float exposure) {
        ViroLog.debug(TAG, "Setting exposure: " + exposure);
        view.setExposure(exposure);
    }
    
    // HDR Properties
    @ReactProp(name = "hdrEnabled", defaultBoolean = false)
    public void setHdrEnabled(Viro360ImageView view, boolean hdrEnabled) {
        ViroLog.debug(TAG, "Setting HDR enabled: " + hdrEnabled);
        view.setHdrEnabled(hdrEnabled);
    }
    
    @ReactProp(name = "toneMapping")
    public void setToneMapping(Viro360ImageView view, @Nullable String toneMapping) {
        ViroLog.debug(TAG, "Setting tone mapping: " + toneMapping);
        view.setToneMapping(toneMapping);
    }
    
    @ReactProp(name = "toneMappingExposure", defaultFloat = 1.0f)
    public void setToneMappingExposure(Viro360ImageView view, float toneMappingExposure) {
        ViroLog.debug(TAG, "Setting tone mapping exposure: " + toneMappingExposure);
        view.setToneMappingExposure(toneMappingExposure);
    }
    
    @ReactProp(name = "toneMappingWhitePoint", defaultFloat = 1.0f)
    public void setToneMappingWhitePoint(Viro360ImageView view, float toneMappingWhitePoint) {
        ViroLog.debug(TAG, "Setting tone mapping white point: " + toneMappingWhitePoint);
        view.setToneMappingWhitePoint(toneMappingWhitePoint);
    }
    
    // Animation Properties
    @ReactProp(name = "autoRotate", defaultBoolean = false)
    public void setAutoRotate(Viro360ImageView view, boolean autoRotate) {
        ViroLog.debug(TAG, "Setting auto rotate: " + autoRotate);
        view.setAutoRotate(autoRotate);
    }
    
    @ReactProp(name = "autoRotateSpeed", defaultFloat = 1.0f)
    public void setAutoRotateSpeed(Viro360ImageView view, float autoRotateSpeed) {
        ViroLog.debug(TAG, "Setting auto rotate speed: " + autoRotateSpeed);
        view.setAutoRotateSpeed(autoRotateSpeed);
    }
    
    @ReactProp(name = "autoRotateAxis")
    public void setAutoRotateAxis(Viro360ImageView view, @Nullable ReadableArray autoRotateAxis) {
        ViroLog.debug(TAG, "Setting auto rotate axis: " + autoRotateAxis);
        view.setAutoRotateAxis(autoRotateAxis);
    }
    
    @ReactProp(name = "animationDuration", defaultFloat = 0.3f)
    public void setAnimationDuration(Viro360ImageView view, float animationDuration) {
        ViroLog.debug(TAG, "Setting animation duration: " + animationDuration);
        view.setAnimationDuration(animationDuration);
    }
    
    @ReactProp(name = "animationEasing")
    public void setAnimationEasing(Viro360ImageView view, @Nullable String animationEasing) {
        ViroLog.debug(TAG, "Setting animation easing: " + animationEasing);
        view.setAnimationEasing(animationEasing);
    }
    
    // Event Exports
    @Override
    public java.util.Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return java.util.Map.of(
            "onLoad", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onLoad",
                    "captured", "onLoadCapture"
                )
            ),
            "onError", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onError",
                    "captured", "onErrorCapture"
                )
            ),
            "onProgress", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onProgress",
                    "captured", "onProgressCapture"
                )
            ),
            "onLoadStart", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onLoadStart",
                    "captured", "onLoadStartCapture"
                )
            ),
            "onLoadEnd", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onLoadEnd",
                    "captured", "onLoadEndCapture"
                )
            ),
            "onRotationChange", java.util.Map.of(
                "phasedRegistrationNames", java.util.Map.of(
                    "bubbled", "onRotationChange",
                    "captured", "onRotationChangeCapture"
                )
            )
        );
    }
    
    @Override
    public void onDropViewInstance(@NonNull Viro360ImageView view) {
        ViroLog.debug(TAG, "Dropping Viro360ImageView instance");
        super.onDropViewInstance(view);
    }
}